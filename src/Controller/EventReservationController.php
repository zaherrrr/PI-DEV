<?php

namespace App\Controller;

use App\Entity\Evenement;
use App\Entity\RechercheReservationEvent;
use App\Entity\ReservationEvenement;
use App\Form\EventReservationType;
use App\Form\RechercheReservationEventType;
use App\Repository\EvenementRepository;
use App\Repository\ReservationEvenementRepository;
use Dompdf\Dompdf;
use Dompdf\Options;
use Infobip\Api\SendSmsApi;
use Infobip\Configuration;
use Infobip\Model\SmsAdvancedTextualRequest;
use Infobip\Model\SmsDestination;
use Infobip\Model\SmsTextualMessage;
use Knp\Component\Pager\PaginatorInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
/**
 * @Route("/reservation")
 */
class EventReservationController extends AbstractController
{
    public function message_tel($num,$text)
    {
        // 1. Create configuration object and client
        $baseurl = $this->getParameter("sms_gateway.baseurl");
        $apikey = $this->getParameter("sms_gateway.apikey");
        $apikeyPrefix = $this->getParameter("sms_gateway.apikeyprefix");
        
        $configuration = (new Configuration())
            ->setHost($baseurl)
            ->setApiKeyPrefix('Authorization', $apikeyPrefix)
            ->setApiKey('Authorization', $apikey);

        $client = new \GuzzleHttp\Client();
        $sendSmsApi = new SendSmsApi($client, $configuration);
        
        // 2. Create message object with destination
        $destination = (new SmsDestination())->setTo($num);
        $message = (new SmsTextualMessage())
            // Alphanumeric sender ID length should be between 3 and 11 characters (Example: `CompanyName`). 
            // Numeric sender ID length should be between 3 and 14 characters.
            ->setFrom('Ifood')
            ->setText($text)
            ->setDestinations([$destination]);
        
        // 3. Create message request with all the messages that you want to send
        $request = (new SmsAdvancedTextualRequest())->setMessages([$message]);
        
        // 4. Send !
        try {
            $smsResponse = $sendSmsApi->sendSmsMessage($request);
            
            dump($smsResponse);
        } catch (\Throwable $apiException) {
            // HANDLE THE EXCEPTION
            dump($apiException);
        }
        
        return new Response("Success (?)");
    }
    /**
     * @Route("/pdf", name="pdf_reservation_event",methods={"GET"}) 
     */
    public function pdf(ReservationEvenementRepository $calendarRepository)
    { 
        
       // Configure Dompdf according to your needs
       $pdfOptions = new Options();
       $pdfOptions->set('defaultFont', 'Arial');
       
       // Instantiate Dompdf with our options
       $dompdf = new Dompdf($pdfOptions);
       $donnee = $calendarRepository->findAll();
       
       // Retrieve the HTML generated in our twig file
       $html = $this->renderView('Reservation_event/Reservation_pdf.html.twig', [
        'events_reservations' => $donnee,
        
        
    ]);
       
       // Load HTML to Dompdf
       $dompdf->loadHtml($html);
       
       // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
       $dompdf->setPaper('A4', 'portrait');

       // Render the HTML as PDF
       $dompdf->render();

       // Output the generated PDF to Browser (force download)
       $dompdf->stream("Tableau_Reservation.pdf", [
           "Attachment" => true
       ]);
 
        
           
          
        
       
        
    }
    /**
     * @Route("/show", name="show_reservation_event")
     */
    public function index(ReservationEvenementRepository $eventsReservationRepository,Request $request,PaginatorInterface $paginator): Response
    {
        $search = new RechercheReservationEvent();
        $form = $this->createForm(RechercheReservationEventType::class,$search);
        $form->handleRequest($request);

   
        $eventsReservationRepository = $paginator->paginate(
            $eventsReservationRepository->FindWithTrie($search),
            $request->query->getInt('page', 1),
            10
        );
        
       
        return $this->render('Reservation_event/Reservation_show.html.twig', [
            'events_reservations' => $eventsReservationRepository,
            'form' =>$form->createView(),
            
        ]);
    }

    /**
     * @Route("/detail/{id}", name="detail_reservation_event", methods={"GET"})
     */
    
    public function show(ReservationEvenement $calendar): Response
    {
        return $this->render('Reservation_event/Reservation_details.html.twig', [
            'events_reservation' => $calendar,
        ]);
    }
    /**
     * @Route("/new", name="new_reservation_event", methods={"GET", "POST"})
     */
    public function new(Request $request, ReservationEvenementRepository $eventsReservationRepository,EvenementRepository $rep): Response
    {
        $bool = false;
        $eventsReservation = new ReservationEvenement();
        $form = $this->createForm(EventReservationType::class, $eventsReservation);
        if(count($rep->findAll() ) != 0 ) 
        {
            $bool = false;
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $eventsReservation->setApprobation('Non');
            
            $eventsReservationRepository->add($eventsReservation);
            $numero = '216'.$eventsReservation->gettel();
            
            
            $this->message_tel($numero,'Vous venez de reserver '.$eventsReservation->getNbrReservations().' place pour l\'evenement '.$eventsReservation->getLesEvenements()->getTitre().' , Soyez le bienvenue !');
            return $this->redirectToRoute('base', [], Response::HTTP_SEE_OTHER);
        }
        
    }
    else 
    {
        $bool = true;
    }
    return $this->render('Reservation_event/Reservation_new.html.twig', [
        'events_reservation' => $eventsReservation,
        'form' => $form->createView(),
        'nonE' => $bool,
    ]);
    }
    /**
     * @Route("edit/{id}", name="edit_reservation_event", methods={"GET", "POST"})
     */
    public function edit(Request $request, ReservationEvenement $eventsReservation, ReservationEvenementRepository $eventsReservationRepository): Response
    {
        $form = $this->createForm(EventReservationType::class, $eventsReservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $eventsReservationRepository->add($eventsReservation);
            return $this->redirectToRoute('show_reservation_event', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('Reservation_event/Reservation_edit.html.twig', [
            'events_reservation' => $eventsReservation,
            'form' => $form->createView(),
        ]);
    }
    /**
     * @Route("/delete/{id}", name="delete_reservation_event", methods={"POST"})
     */
    public function delete(Request $request, ReservationEvenement $eventsReservation, ReservationEvenementRepository $eventsReservationRepository): Response
    {
        if ($this->isCsrfTokenValid('delete'.$eventsReservation->getId(), $request->request->get('_token'))) {
            $eventsReservationRepository->remove($eventsReservation);
        }

        return $this->redirectToRoute('show_reservation_event', [], Response::HTTP_SEE_OTHER);
    }
    /**
     * @Route("/approuvee/{id}", name="approuvee_reservation_event",methods={"GET", "POST"})
     */
    public function approuvee(Request $request, ReservationEvenement $eventsReservation, ReservationEvenementRepository $eventsReservationRepository): Response
    {
        
        $eventsReservation->setApprobation('Oui');
        $eventsReservationRepository->add($eventsReservation);
        return $this->redirectToRoute('show_reservation_event', [], Response::HTTP_SEE_OTHER);
           
        
    }

}