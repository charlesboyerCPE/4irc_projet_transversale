<?php

    class Equipe extends BDD{

        public function __construct()
        {
            $this->getConnection();
        }

        function getAllEquipes(){
            $sql = "SELECT * FROM Equipe";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function getEquipeById($id){
            $sql = " SELECT * FROM Equipe WHERE id_equipe = $id "; 
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        function putAllEquipes($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){
                $sql = "
                    INSERT INTO Equipe (id_equipe, date_constitution) 
                    VALUES ({$data[$key]['id_equipe']}, {$data[$key]['date_constitution']})
                    ON DUPLICATE KEY UPDATE date_constitution= VALUES(date_constitution)
                ";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }
        }

        function deleteEquipeById($id){
            $sql = "DELETE FROM Equipe WHERE id_equipe = {$id}";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
        }

        function deleteEquipes($data){
            $data = json_decode($data, true);
            foreach($data as $key => $val){
                $sql = " DELETE FROM Equipe WHERE id_equipe = {$data[$key]['id_equipe']} ";
                $query = $this->_connexion->prepare($sql);
                $query->execute();
            }
        }
    }

?>