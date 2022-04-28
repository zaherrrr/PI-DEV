<?php

namespace App\Form;

use App\Entity\Evenement;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\ColorType;
use Symfony\Component\Form\Extension\Core\Type\DateTimeType;
use Symfony\Component\Form\Extension\Core\Type\TextareaType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class EventType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options): void
    {
        $builder
        ->add('Titre',TextType::class , [
            'label'  => 'Titre :',
            'empty_data' =>''
        ])
        ->add('start',DateTimeType::class, [
            'date_widget' => 'single_text' ,
            'label'  => 'Date de debut de l\'evenement : '
        ])
        ->add('end', DateTimeType::class, [
            'date_widget' => 'single_text' ,
            'label'  => 'Date de fin de l\'evenement : '
        ])
        ->add('description' ,TextareaType::class , [
            'label'  => 'Description : ',
            'empty_data' =>''
        ])
        ->add('background_color',ColorType::class)
        ;
    }

    public function configureOptions(OptionsResolver $resolver): void
    {
        $resolver->setDefaults([
            'data_class' => Evenement::class,
        ]);
    }
}
