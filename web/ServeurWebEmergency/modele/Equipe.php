<?php

    class Equipe extends BDD{

        public function __construct()
        {
            $this->getConnection();
        }

        function getAllEquipes(){
            $sql = "SELECT * FROM Equipe";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function getEquipeById($id){
            $sql = " SELECT * FROM Equipe WHERE id_equipe = $id "; 
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }
    }

?>