<?php

    class Operation extends BDD{

        public function __construct()
        {
            $this->getConnection();
        }

        function getAllOperations(){
            $sql = "SELECT * FROM operation";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function getOperationById($id){
            $sql = " SELECT * FROM operation WHERE id_feu = $id "; 
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
                    INSERT INTO operation (id_feu, id_camion, id_equipe, debut, fin) 
                    VALUES ({$data[$key]['id_feu']}, {$data[$key]['id_camion']}, {$data[$key]['id_equipe']}, {$data[$key]['debut']}, {$data[$key]['fin']})
                    ON DUPLICATE KEY UPDATE id_equipe= VALUES(id_equipe), debut= VALUES(debut), fin= VALUES(fin)
                ";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }
        }

        function deleteOperationById($id){
            $sql = "DELETE FROM operation WHERE id_feu = {$id}";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
        }

        function deleteOperations($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){
                $sql = " DELETE FROM operation WHERE id_feu = {$data[$key]['id_feu']} ";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }
        }
    }

?>