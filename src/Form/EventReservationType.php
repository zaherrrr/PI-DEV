<?php

namespace App\Form;

use App\Entity\Evenement;
use App\Entity\ReservationEvenement;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class EventReservationType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
            ->add('LesEvenements',EntityType::class, [
            "class" => Evenement::class,
            "choice_label" => "Titre"
            ])
            ->add('tel',TextType::class,[
                'empty_data' =>''
            ])
            ->add('NbrReservations',ChoiceType::class,["choices"=>["1"=>1,"2"=>2,"3"=>3,"4"=>4   ]])
            
        
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => ReservationEvenement::class,
        ]);
    }
}
