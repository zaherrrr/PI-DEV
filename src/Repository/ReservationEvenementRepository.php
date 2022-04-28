<?php

namespace App\Repository;

use App\Entity\ReservationEvenement;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\DBAL\Query\QueryBuilder;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method ReservationEvenement|null find($id, $lockMode = null, $lockVersion = null)
 * @method ReservationEvenement|null findOneBy(array $criteria, array $orderBy = null)
 * @method ReservationEvenement[]    findAll()
 * @method ReservationEvenement[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class ReservationEvenementRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, ReservationEvenement::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(ReservationEvenement $entity, bool $flush = true): void
    {
        $this->_em->persist($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function remove(ReservationEvenement $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    // /**
    //  * @return ReservationEvenement[] Returns an array of ReservationEvenement objects
    //  */
    
    public function findById($value)
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->orderBy('r.id', 'ASC')
            ->setMaxResults(10)
            ->getQuery()
            ->getResult()
        ;
    }
    public function FindWithTrie($value)
    {
        switch($value->getattrR())
        {
            case 'tel':
                $build = $this->createQueryBuilder('e');
                
                    $build = $build->Andwhere('e.tel LIKE :val')
                    ->setParameter('val',$value->getRecherche().'%');
                
                return $this->trie($build,$value);
            
            break;
            case 'nbr_reservation':
                $build = $this->createQueryBuilder('e');
                
                $build = $build->Andwhere('e.NbrReservations LIKE :val')
                ->setParameter('val',$value->getRecherche().'%');
                return $this->trie($build,$value);
            
            break;
            default:
            return $this->findAll();
            break;
        }
        
        
       
    }
    public function trie($Builder,$attribut)
    {
        if($attribut->getTrie() == 'tel')
                {
                return $Builder->orderBy('e.tel','ASC')
                ->getQuery()
                ->getResult();
                }
        else if($attribut->getTrie() == 'nbr_reservation')
                {
                return $Builder->orderBy('e.NbrReservations','ASC')
                ->getQuery()
                ->getResult();
                }
    }
    

    /*
    public function findOneBySomeField($value): ?ReservationEvenement
    {
        return $this->createQueryBuilder('r')
            ->andWhere('r.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
