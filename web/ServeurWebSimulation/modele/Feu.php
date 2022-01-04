<?php

    class Feu extends BDD{

        public function __construct()
        {
            $this->getConnection();
        }

        function getAllFeux(){
            $sql = "SELECT * FROM Feu";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function getFeuById($id){
            $sql = " SELECT * FROM Feu WHERE id_feu = $id "; 
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }
    }

?>