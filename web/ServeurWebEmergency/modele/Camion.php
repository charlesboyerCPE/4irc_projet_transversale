<?php

    class Camion extends BDD{

        public function __construct()
        {
            $this->getConnection();
        }

        function getAllCamions(){
            $sql = "SELECT * FROM Camion";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function getCamionById($id){
            $sql = " SELECT * FROM Camion WHERE id_camion = $id "; 
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }
    }

?>