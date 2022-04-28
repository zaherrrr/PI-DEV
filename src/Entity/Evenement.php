<?php

namespace App\Entity;

use App\Repository\EvenementRepository;
use Doctrine\Common\Collections\ArrayCollection;
use Doctrine\Common\Collections\Collection;
use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use App\Entity\ReservationEvenement;

/**
 * @ORM\Entity(repositoryClass=EvenementRepository::class)
 */
class Evenement
{
    /**
     * @ORM\Id
     * @ORM\GeneratedValue
     * @ORM\Column(type="integer")
     */
    private $id;

    /**
     * @ORM\Column(type="string", length=255)
     * @Assert\NotBlank(message="Veuillez ajouter un titre .")
     */
    private $Titre;

    /**
     * @ORM\Column(type="datetime")
     * @Assert\NotBlank(message="Veuillez saisir une date de debut .")
     * @Assert\GreaterThan("today",message="La date de debut doit etre superieur a la date d'ajourd'hui .")
     */
    private $start;

    /**
     * @ORM\Column(type="datetime")
     * @Assert\NotBlank(message="Veuillez saisir une date de fin")
     * @Assert\Expression(
     *     "this.getstart() < this.getend()",
     *     message="La date de fin ne doit pas être antérieure à la date début ."
     * )
     */
    private $end;

    /**
     * @ORM\Column(type="string" , length=255)
     * @Assert\NotBlank(message="Veuillez saisir une description")
     * @Assert\Length(min=10,minMessage="La description doit contenir au moins 10 caractères .")
     */
    private $description;

    /**
     * @ORM\OneToMany(targetEntity=ReservationEvenement::class, mappedBy="LesEvenements",cascade={"all"})
     */
    private $Reservations;

    /**
     * @ORM\Column(type="string", length=7)
     */
    private $background_color;

    public function __construct()
    {
        $this->Reservations = new ArrayCollection();
    }

    

   

    public function getId(): ?int
    {
        return $this->id;
    }

    public function getTitre(): ?string
    {
        return $this->Titre;
    }

    public function setTitre(string $Titre): self
    {
        $this->Titre = $Titre;

        return $this;
    }

    public function getStart(): ?\DateTimeInterface
    {
        return $this->start;
    }

    public function setStart(\DateTimeInterface $start): self
    {
        $this->start = $start;

        return $this;
    }

    public function getEnd(): ?\DateTimeInterface
    {
        return $this->end;
    }

    public function setEnd(\DateTimeInterface $end): self
    {
        $this->end = $end;

        return $this;
    }

    public function getDescription(): ?string
    {
        return $this->description;
    }

    public function setDescription(string $description): self
    {
        $this->description = $description;

        return $this;
    }

    /**
     * @return Collection<int, ReservationEvenement>
     */
    public function getReservations(): Collection
    {
        return $this->Reservations;
    }

    public function addReservation(ReservationEvenement $reservation): self
    {
        if (!$this->Reservations->contains($reservation)) {
            $this->Reservations[] = $reservation;
            
        }

        return $this;
    }

    public function removeReservation(ReservationEvenement $reservation): self
    {
        $this->Reservations->removeElement($reservation);

        return $this;
    }

    public function getBackgroundColor(): ?string
    {
        return $this->background_color;
    }

    public function setBackgroundColor(string $background_color): self
    {
        $this->background_color = $background_color;

        return $this;
    }

    
}
