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

        function putAllOperations($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){
                $sql = "
                    INSERT INTO Operation (id_feu, id_camion, id_equipe, debut, fin, coordonnee_x, coordonnee_y) 
                    VALUES ({$data[$key]['id_feu']}, {$data[$key]['id_camion']}, {$data[$key]['id_equipe']}, {$data[$key]['debut']}, {$data[$key]['fin']}, {$data[$key]['coordonnee_x']}, {$data[$key]['coordonnee_y']})
                    ON DUPLICATE KEY UPDATE id_equipe= VALUES(id_equipe), debut= VALUES(debut), fin= VALUES(fin), coordonnee_x=  VALUES(coordonnee_x), coordonnee_y=  VALUES(coordonnee_y)
                ";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }
        }

        function deleteOperationById($id){
            $sql = "DELETE FROM Operation WHERE id_feu = {$id}";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
        }

        function deleteOperations($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){
                $sql = " DELETE FROM Operation WHERE id_feu = {$data[$key]['id_feu']} ";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }
        }
    }

?>