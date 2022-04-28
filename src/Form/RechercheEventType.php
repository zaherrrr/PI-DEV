<?php

namespace App\Form;

use App\Entity\RechercheEvent;
use PhpParser\Parser\Multiple;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\SubmitType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class RechercheEventType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('Trie' , ChoiceType::class,[
            
            'label' =>false,
            'choices'=> ['trie par titre' =>'titre' , 'trie par description'  => 'description' ],
            'multiple' => false,
            'expanded' => true,
            'data' => 'titre',
            
            
            


            
            
        ])
        ->add('attrR' , ChoiceType::class,[
            
            'label' =>'Recherche par :',
            'choices'=> ['titre' =>'titre' , 'description'  => 'description' ],
            'multiple' => false,
            'expanded' => true,
            'data' => 'titre'
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
            'data_class' => RechercheEvent::class,
            'method' => 'get',
            'csrf_protection' => false
        ]);
    }
}
