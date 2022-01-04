<?php

    class Operation extends BDD{

        public function __construct()
        {
            $this->getConnection();
        }

        function getAllOperations(){
            $sql = "SELECT * FROM Operation";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function getOperationById($id){
            $sql = " SELECT * FROM Operation WHERE id_feu = $id "; 
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }
    }

?>