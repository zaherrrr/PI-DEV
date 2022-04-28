<?php

namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class BaseController extends AbstractController
{
    
    /**
     * @Route("/admin", name="admin")
     */
    public function index_admin(): Response
    {
        return $this->render('base/base_BACK.html.twig', [
            'controller_name' => 'BaseController',
        ]);
    }
    /**
     * @Route("/", name="base")
     */
public function index_front(): Response
    {
        return $this->render('base/base_FRONT.html.twig', [
            'controller_name' => 'BaseController',
        ]);
    }
    /**
     * @Route("/script", name="script")
     */
    public function index_script(): Response
    {
        return $this->render('base/base_script.html', [
            'controller_name' => 'BaseController',
        ]);
    }

}
