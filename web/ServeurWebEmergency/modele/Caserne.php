<?php

    class Caserne extends BDD{

        public function __construct()
        {
            $this->getConnection();
        }

        function getAllCasernes(){
            $sql = "SELECT * FROM caserne";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            return $resultat;
        }

        function getCaserneById($id){
            $sql = " SELECT * FROM caserne WHERE id_caserne = $id "; 
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            return $resultat;
        }

        function putAllCasernes($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){
                $sql = "
                    INSERT INTO caserne (id_caserne, nom_caserne, coordonnee_x, coordonnee_y, total_pompier) 
                    VALUES ({$data[$key]['id_caserne']}, {$data[$key]['nom_caserne']}, {$data[$key]['coordonnee_x']}, {$data[$key]['coordonnee_y']}, {$data[$key]['total_pompier']})
                    ON DUPLICATE KEY UPDATE nom_caserne= VALUES(nom_caserne), coordonnee_x= VALUES(coordonnee_x), coordonnee_y=  VALUES(coordonnee_y), total_pompier=  VALUES(total_pompier)
                ";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }
        }

        function deleteCaserneById($id){
            $sql = "DELETE FROM caserne WHERE id_caserne = {$id}";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
        }

        function deleteCasernes($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){
                $sql = " DELETE FROM caserne WHERE id_caserne = {$data[$key]['id_caserne']} ";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }
        }
    }

?>