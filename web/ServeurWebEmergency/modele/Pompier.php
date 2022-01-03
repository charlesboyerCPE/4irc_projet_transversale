<?php

    class Pompier extends BDD{

        public function __construct()
        {
            $this->getConnection();
        }

        function getAllPompiers(){
            $sql = "SELECT * FROM Pompier";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function getPompierById($id){
            $sql = " SELECT * FROM Pompier WHERE id_pompier = $id "; 
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }
    }

?>