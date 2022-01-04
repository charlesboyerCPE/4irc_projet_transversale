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

        function putAllCapteurs($data){

            $data = json_decode($data, true);
            foreach($data as $key => $val){
                echo $data[$key]['id_capteur'];
                $sql = "
                    INSERT INTO Capteur (id_capteur, intensite, perimetre, coordonnee_x, coordonnee_y) 
                    VALUES ({$data[$key]['id_capteur']}, {$data[$key]['intensite']}, {$data[$key]['perimetre']}, {$data[$key]['coordonnee_x']}, {$data[$key]['coordonnee_y']})
                    ON DUPLICATE KEY UPDATE intensite= VALUES(intensite), perimetre= VALUES(perimetre), coordonnee_x=  VALUES(coordonnee_x), coordonnee_y=  VALUES(coordonnee_y)
                ";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }

        }

        function putCapteurById($id){

        }

        /*
        INSERT INTO Capteur (id_capteur, intensite, perimetre, coordonnee_x, coordonnee_y) VALUES (1, 5, 5, 5.0, 5.0)
        ON DUPLICATE KEY UPDATE id_capteur = 1, intensite= 5, perimetre= 5, coordonnee_x= 5, coordonnee_y= 5
        */
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