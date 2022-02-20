<?php

    class Constitution extends BDD{

        public function __construct()
        {
            $this->getConnection();
        }

        function getAllConstitutions(){
            $sql = "SELECT * FROM constitution";
            $query = $this->_connexion->prepare($sql);
            $query->execute();
            $resultat = $query->fetchAll(PDO::FETCH_CLASS);
            //var_dump($resultat);
            return $resultat;
        }

        
    }
?>