<?php

    class Pompier extends BDD{

        public function __construct()
        {
            $this->getConnection();
        }

        function getAllPompiers(){
            $sql = "SELECT * FROM pompier";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function getPompierById($id){
            $sql = " SELECT * FROM pompier WHERE id_pompier = $id "; 
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function putAllPompiers($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){
                $sql = "
                    INSERT INTO pompier (id_pompier, id_caserne, fatigue) 
                    VALUES ('".$data[$key]['id_pompier']."', '".$data[$key]['id_caserne']."', '".$data[$key]['fatigue']."')
                    ON DUPLICATE KEY UPDATE id_caserne= VALUES(id_caserne), fatigue= VALUES(fatigue)
                ";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }
        }

        function deletePompierById($id){
            $sql = "DELETE FROM pompier WHERE id_pompier = {$id}";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
        }

        function deletePompiers($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){
                $sql = " DELETE FROM pompier WHERE id_pompier = {$data[$key]['id_pompier']} ";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }
        }
    }

?>