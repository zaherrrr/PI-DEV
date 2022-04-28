<?php

namespace App\Form;

use App\Entity\RechercheReservationEvent;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class RechercheReservationEventType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('Trie' , ChoiceType::class,[
            
            'label' =>false,
            'choices'=> [ 'trie par Nombre de reservation'  => 'nbr_reservation','trie par tel' =>'tel'  ],
            'multiple' => false,
            'expanded' => true,
            'data' => 'nbr_reservation',
            
            
            


            
            
        ])
        ->add('attrR' , ChoiceType::class,[
            
            'label' =>'Recherche par :',
            'choices'=> [ 'Nombre de reservation'  => 'nbr_reservation','Telephone' =>'tel' ],
            'multiple' => false,
            'expanded' => true,
            'data' => 'nbr_reservation'
        ])
        ->add('Recherche' ,TextType::class ,[
            'required' => false
        ])
        
        ->add('Submit' , SubmitType::class , [
            'label' => 'Rafraichir',
            
        ]);
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => RechercheReservationEvent::class,
            'method' => 'get',
            'csrf_protection' => false
        ]);
    }
}
