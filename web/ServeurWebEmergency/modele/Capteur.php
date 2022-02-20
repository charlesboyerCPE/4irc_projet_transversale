<<<<<<< HEAD
<?php

    class Capteur extends BDD{

        public function __construct()
        {
            $this->getConnection();
        }

        function getAllCapteurs(){
            $sql = "SELECT * FROM capteur";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            return $resultat;
        }

        function getCapteurById($id){
            $sql = " SELECT * FROM capteur WHERE id_capteur = $id "; // 
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function putAllCapteurs($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){                
                $sql = "
                    INSERT INTO capteur (id_capteur, intensite, perimetre, coordonnee_x, coordonnee_y) 
                    VALUES ('".$data[$key]['id_capteur']."', '".$data[$key]['intensite']."', '".$data[$key]['perimetre']."', '".$data[$key]['coordonnee_x']."', '".$data[$key]['coordonnee_y']."')
                    ON DUPLICATE KEY UPDATE intensite= VALUES(intensite), perimetre= VALUES(perimetre), coordonnee_x=  VALUES(coordonnee_x), coordonnee_y=  VALUES(coordonnee_y)
                ";
                $query = $this->_connexion->prepare($sql);
                $query->execute(); 
               
            } 
        }

        function deleteCapteurById($id){
            $sql = "DELETE FROM capteur WHERE id_capteur = {$id}";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
        }

        function deleteCapteurs($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){
                $sql = " DELETE FROM capteur WHERE id_capteur = {$data[$key]['id_capteur']} ";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }
        }

    }

=======
<?php

    class Capteur extends BDD{

        public function __construct()
        {
            $this->getConnection();
        }

        function getAllCapteurs(){
            $sql = "SELECT * FROM capteur";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            return $resultat;
        }

        function getCapteurById($id){
            $sql = " SELECT * FROM capteur WHERE id_capteur = $id "; // 
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function putAllCapteurs($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){                
                $sql = "
                    INSERT INTO capteur (id_capteur, intensite, perimetre, coordonnee_x, coordonnee_y) 
                    VALUES ('".$data[$key]['id_capteur']."', '".$data[$key]['intensite']."', '".$data[$key]['perimetre']."', '".$data[$key]['coordonnee_x']."', '".$data[$key]['coordonnee_y']."')
                    ON DUPLICATE KEY UPDATE intensite= VALUES(intensite), perimetre= VALUES(perimetre), coordonnee_x=  VALUES(coordonnee_x), coordonnee_y=  VALUES(coordonnee_y)
                ";
                $query = $this->_connexion->prepare($sql);
                $query->execute(); 
               
            } 
        }

        function deleteCapteurById($id){
            $sql = "DELETE FROM capteur WHERE id_capteur = {$id}";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
        }

        function deleteCapteurs($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){
                $sql = " DELETE FROM capteur WHERE id_capteur = {$data[$key]['id_capteur']} ";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }
        }

    }

>>>>>>> fe20742a4296f16765957b6c799131b15483f342
?>