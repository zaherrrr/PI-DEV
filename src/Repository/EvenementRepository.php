<?php

namespace App\Repository;

use App\Entity\Evenement;
use Doctrine\Bundle\DoctrineBundle\Repository\ServiceEntityRepository;
use Doctrine\DBAL\Query\QueryBuilder;
use Doctrine\ORM\OptimisticLockException;
use Doctrine\ORM\ORMException;
use Doctrine\Persistence\ManagerRegistry;

/**
 * @method Evenement|null find($id, $lockMode = null, $lockVersion = null)
 * @method Evenement|null findOneBy(array $criteria, array $orderBy = null)
 * @method Evenement[]    findAll()
 * @method Evenement[]    findBy(array $criteria, array $orderBy = null, $limit = null, $offset = null)
 */
class EvenementRepository extends ServiceEntityRepository
{
    public function __construct(ManagerRegistry $registry)
    {
        parent::__construct($registry, Evenement::class);
    }

    /**
     * @throws ORMException
     * @throws OptimisticLockException
     */
    public function add(Evenement $entity, bool $flush = true): void
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
    public function remove(Evenement $entity, bool $flush = true): void
    {
        $this->_em->remove($entity);
        if ($flush) {
            $this->_em->flush();
        }
    }

    // /**
    //  * @return Evenement[] Returns an array of Evenement objects
    //  */
    
    public function FindWithTrie($value)
    {
        switch($value->getTrie())
        {
            case 'titre':
                $build = $this->createQueryBuilder('e');
                if($value->getRecherche() != '' && $value->getattrR() == 'titre' )
                {
                    $build = $build->Andwhere('e.Titre LIKE :val')
                    ->setParameter('val',$value->getRecherche().'%');
                }
                return $build->orderBy('e.Titre','ASC')
                ->getQuery()
                ->getResult();
            
            break;
            case 'description':
                $build = $this->createQueryBuilder('e');
                if($value->getRecherche() != '' && $value->getattrR() == 'description')
                {
                    $build = $build->Andwhere('e.description LIKE :val')
                    ->setParameter('val',$value->getRecherche().'%');
                }
                return $build->orderBy('e.description','ASC')
                ->getQuery()
                ->getResult();
            
            break;
            default:
            return $this->findAll();
            break;
        }
        
        ;
    }
    

    /*
    public function findOneBySomeField($value): ?Evenement
    {
        return $this->createQueryBuilder('e')
            ->andWhere('e.exampleField = :val')
            ->setParameter('val', $value)
            ->getQuery()
            ->getOneOrNullResult()
        ;
    }
    */
}
