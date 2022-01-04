<?php

    class Capteur extends BDD{

        public function __construct()
        {
            $this->getConnection();
        }

        function getAllCapteurs(){
            $sql = "SELECT * FROM Capteur";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            return $resultat;
        }

        function getCapteurById($id){
            $sql = " SELECT * FROM Capteur WHERE id_capteur = $id "; // 
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }
    }

    
                /*
                //$dbh = connectDb();
                $sql = " SELECT * FROM Capteur WHERE intensite != 0 ";
                $stmt = $this->_connexion->prepare($sql);
                $stmt->setFetchMode(PDO::FETCH_CLASS, 'Capteur'); 
                $stmt->execute();
                $obj = $stmt->fetch();
                $stmt->closeCursor();
                var_dump($obj);
                //sendJSON($obj);
                */

?>