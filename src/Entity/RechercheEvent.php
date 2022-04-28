<?php

namespace App\Entity;



class RechercheEvent
{
    /**
     * @var string|null
     */
    private $Recherche;
    /**
     * @var string|null
     */
    private $Trie; 
    /**
     * @var string|null
     */
    private $attrR;  
 

    /**
     * @return string|null
     */
    public function getRecherche() : ? string
    {
        return $this->Recherche;
    }

    /**
     * @return string|null
     */
    public function gettrie() : ? string
    {
        return $this->Trie;
    }
    /**
     * @return string|null
     */
    public function getattrR() : ? string
    {
        return $this->attrR;
    }
    /**
    * @param int|null $attrR
    */
    public function  setattrR(string $attrR):RechercheEvent
    {
        $this->attrR= $attrR;
        return $this;
    }
    /**
    * @param int|null $Recherche
    */
    public function  setRecherche(string $recherche):RechercheEvent
    {
        $this->Recherche = $recherche;
        return $this;
    }
    /**
    * @param int|null $Trie
    */
    public function  setTrie(string $trie):RechercheEvent
    {
        $this->Trie = $trie;
        return $this;
    }
}

