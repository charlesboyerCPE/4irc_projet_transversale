<?php

    class Caserne extends BDD{

        public function __construct()
        {
            $this->getConnection();
        }

        function getAllCasernes(){
            $sql = "SELECT * FROM Caserne";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function getCaserneById($id){
            $sql = " SELECT * FROM Caserne WHERE id_caserne = $id "; 
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }
    }

?>