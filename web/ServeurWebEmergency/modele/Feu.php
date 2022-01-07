<?php

    class Feu extends BDD{

        public function __construct()
        {
            $this->getConnection();
        }

        function getAllFeux(){
            $sql = "SELECT * FROM feu";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function getFeuById($id){
            $sql = " SELECT * FROM feu WHERE id_feu = $id "; 
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function putAllFeux($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){
                $sql = "
                    INSERT INTO feu (id_feu, id_capteur, intensite, frequence, coordonnee_x, coordonnee_y) 
                    VALUES ({$data[$key]['id_feu']}, {$data[$key]['id_capteur']}, {$data[$key]['intensite']}, {$data[$key]['frequence']}, {$data[$key]['coordonnee_x']}, {$data[$key]['coordonnee_y']})
                    ON DUPLICATE KEY UPDATE intensite= VALUES(intensite), frequence= VALUES(frequence), coordonnee_x=  VALUES(coordonnee_x), coordonnee_y=  VALUES(coordonnee_y)
                ";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }
        }

        function deleteFeuById($id){
            $sql = "DELETE FROM feu WHERE id_feu = {$id}";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
        }

        function deleteFeux($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){
                $sql = " DELETE FROM feu WHERE id_feu = {$data[$key]['id_feu']} ";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }
        }
    }

?>