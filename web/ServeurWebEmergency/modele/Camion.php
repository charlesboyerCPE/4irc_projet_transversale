<?php

    class Camion extends BDD{

        public function __construct()
        {
            $this->getConnection();
        }

        function getAllCamions(){
            $sql = "SELECT * FROM camion";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function getCamionById($id){
            $sql = " SELECT * FROM camion WHERE id_camion = $id "; 
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function putAllCamions($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){
                $sql = "
                    INSERT INTO camion (id_camion, id_caserne, type_produit, disponibilite, capacite, nb_pompier, coordonnee_x, coordonnee_y, coordonnee_dest_x, coordonnee_dest_x) 
                    VALUES ({$data[$key]['id_camion']}, {$data[$key]['id_caserne']}, {$data[$key]['type_produit']}, {$data[$key]['disponibilite']}, {$data[$key]['capacite']}, {$data[$key]['nb_pompier']}, {$data[$key]['coordonnee_x']}, {$data[$key]['coordonnee_y']}, {$data[$key]['coordonnee_dest_x']}, {$data[$key]['coordonnee_dest_y']})
                    ON DUPLICATE KEY UPDATE id_caserne= VALUES(id_caserne), type_produit= VALUES(type_produit), disponibilite=  VALUES(disponibilite), capacite=  VALUES(capacite), nb_pompier=  VALUES(nb_pompier), coordonnee_x=  VALUES(coordonnee_x), coordonnee_y=  VALUES(coordonnee_y), coordonnee_dest_x=  VALUES(coordonnee_dest_x), coordonnee_dest_y=  VALUES(coordonnee_dest_y)
                ";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }
        }

        function deleteCamionById($id){
            $sql = "DELETE FROM camion WHERE id_camion = {$id}";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
        }

        function deleteCamions($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){
                $sql = "DELETE FROM camion WHERE id_camion = {$data[$key]['id_camion']}";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }
        }
    }
    

?>