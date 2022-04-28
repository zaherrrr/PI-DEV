<?php

namespace App\Entity;

use App\Repository\ReservationEvenementRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use App\Entity\Evenement;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;

/**
 * @ORM\Entity(repositoryClass=ReservationEvenementRepository::class)
 */
class ReservationEvenement
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Veuillez saisir un numero de telephone")
     * @Assert\Length(
     * min=8,
     * max=8,
     * exactMessage="Le numero de telephone doit contenir 8 chiffre")
     */
    
    private $tel;

    /**
     * @ORM\Column(type="integer")
     */
    private $NbrReservations;

    /**
     * @ORM\ManyToOne(targetEntity=Evenement::class, inversedBy="Reservations")
     */
    private $LesEvenements;

    /**
     * @ORM\Column(type="string", length=3, options={"default"="Non"}))
     */
    private $Approbation;

    

    

    

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTel(): ?string
    {
        return $this->tel;
    }

    public function setTel(string $tel): self
    {
        $this->tel = $tel;

        return $this;
    }

    public function getNbrReservations(): ?int
    {
        return $this->NbrReservations;
    }

    public function setNbrReservations(int $NbrReservations): self
    {
        $this->NbrReservations = $NbrReservations;

        return $this;
    }

    
    public function getLesEvenements()
    {
        return $this->LesEvenements;
    }
    

   

    public function setLesEvenements($var)
    {
        $this->LesEvenements = $var;
    }

    public function getApprobation(): ?string
    {
        return $this->Approbation;
    }

    public function setApprobation(string $Approbation): self
    {
        $this->Approbation = $Approbation;

        return $this;
    }

   
   

    
}
