<<<<<<< HEAD
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
=======
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
>>>>>>> fe20742a4296f16765957b6c799131b15483f342
?>