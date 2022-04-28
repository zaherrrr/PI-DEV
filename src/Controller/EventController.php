<?php

namespace App\Controller;

use App\Entity\Evenement;
use App\Entity\RechercheEvent;
use App\Form\EventType;
use App\Form\RechercheEventType;
use App\Repository\EvenementRepository;
use Doctrine\ORM\EntityManagerInterface;
use Knp\Component\Pager\PaginatorInterface;
use Doctrine\ORM\Tools\Pagination\Paginator;
use Dompdf\Dompdf;
use Dompdf\Options;
use Infobip\Api\SendSmsApi;
use Infobip\Configuration;
use Infobip\Model\SmsAdvancedTextualRequest;
use Infobip\Model\SmsDestination;
use Infobip\Model\SmsTextualMessage;
use MercurySeries\FlashyBundle\FlashyNotifier;
use phpDocumentor\Reflection\Types\String_;
use SebastianBergmann\Environment\Console;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Bundle\MakerBundle\EventRegistry;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Util\StringUtil;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/Event")
 */
class EventController extends AbstractController
{
    

    /**
     * @Route("/calendar", name="calendar_event", methods={"POST","GET"})
     */
    public function calendar(EvenementRepository $calendar)
    {
        $events = $calendar->findAll();

        $rdvs = [];

        foreach($events as $event){
            $rdvs[] = [
                'id' => $event->getId(),
                'start' => $event->getStart()->format('Y-m-d H:i:s'),
                'end' => $event->getEnd()->format('Y-m-d H:i:s'),
                'title' => $event->getTitre(),
                'description' => $event->getDescription(),
                'backgroundColor' =>$event->getBackgroundColor()
                
            ];
        }

        $data = json_encode($rdvs);

        return $this->render('event/Event_calendar.html.twig', compact('data'));
    }
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
            ->setFrom('Gotrip')
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
     * @Route("/pdf", name="pdf_event",methods={"GET", "POST"}) 
     */
    public function pdf(EvenementRepository $calendarRepository)
    { 
        
       // Configure Dompdf according to your needs
       $pdfOptions = new Options();
   
       
      
       
       // Instantiate Dompdf with our options
       $dompdf = new Dompdf($pdfOptions);
      
       
     
       $donnee = $calendarRepository->findAll();
       
       
       // Retrieve the HTML generated in our twig file
       $html = $this->renderView('event/Event_pdf.html.twig', [
        'calendars' => $donnee,
        
        
    ]);
   
    $dompdf->loadHtml($html);
       // Load HTML to Dompdf
      
       
       // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
       $dompdf->setPaper('A4', 'portrait');
       $dompdf->getOptions()->setChroot('C:\\Users\\sofia\\CrudCS\\public');

       // Render the HTML as PDF
       $dompdf->render();

       // Output the generated PDF to Browser (force download)
       $dompdf->stream("Tableau_Evenement.pdf", [
           "Attachment" => 1
       ]);
 
        
           
          
        
       
        
    }
    /**
     * @Route("/pdfS", name="show_pdf_event",methods={"GET", "POST"}) 
     */
    public function show_pdf(EvenementRepository $calendarRepository)
    { 
        
       // Configure Dompdf according to your needs
     
       // Instantiate Dompdf with our options
     
       $donnee = $calendarRepository->findAll();
       return $this->render('event/Event_pdf.html.twig', [
       'calendars' =>$donnee
       ]);


       
        

    }
    /**
     * @Route("/show", name="show_event",methods={"GET", "POST"})
     * @return Response 
     */
    public function index(Request $request,EvenementRepository $calendarRepository,PaginatorInterface $paginator): Response
    { 
        
        $search = new RechercheEvent();
        $form = $this->createForm(RechercheEventType::class,$search);
        $form->handleRequest($request);
 
        $calendarRepository = $paginator->paginate(
            $calendarRepository->FindWithTrie($search),
            $request->query->getInt('page', 1),
            10
        );
        
       
        return $this->render('event/Event_show.html.twig', [
            'calendars' => $calendarRepository,
            'form' =>$form->createView(),
            
        ]);
    }
    /**
     * @Route("/new", name="new_event", methods={"GET", "POST"})
     */
    public function new(Request $request,EntityManagerInterface $entityManager): Response
    {
        $calendar = new Evenement();
        $form = $this->createForm(EventType::class, $calendar);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {

            
            
            $entityManager->persist($calendar);
            $entityManager->flush();
            $this->message_tel('21653508475','Un nouveau evenement a ete ajouté , vous pouvez le consulter a travers le backend');
            return $this->redirectToRoute('show_event', [], Response::HTTP_SEE_OTHER);
            
        }

        return $this->render('event/Event_new.html.twig', [
            'calendar' => $calendar,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="detail_event", methods={"GET"})
     */
    public function show(Evenement $calendar): Response
    {
        return $this->render('event/Event_details.html.twig', [
            'calendar' => $calendar,
        ]);
    }
    /**
     * @Route("/{id}/edit", name="edit_event", methods={"GET", "POST"})
     */
    public function edit(Request $request, Evenement $calendar, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(EventType::class, $calendar);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('show_event', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('event/Event_edit.html.twig', [
            'calendar' => $calendar,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{id}", name="delete_event", methods={"POST"})
     */
    public function delete(Request $request, Evenement $calendar, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$calendar->getId(), $request->request->get('_token'))) {
            $entityManager->remove($calendar);
            $entityManager->flush();
        }

        return $this->redirectToRoute('show_event', [], Response::HTTP_SEE_OTHER);
    }
   

    
}
