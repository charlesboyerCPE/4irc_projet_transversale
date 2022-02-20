<?php

abstract class BDD{
      private $host = "localhost";
      private $db_name = "database_emergency";
      private $username = "root";
      private $password = "";

      protected $_connexion;

      public function getConnection(){
          $this->_connexion = null;
          try{
              $this->_connexion = new PDO("mysql:host=" . $this->host . ";dbname=" . $this->db_name, $this->username, $this->password);
              $this->_connexion->exec("set names utf8");
          }catch(PDOException $exception){
              echo "Erreur de connexion : " . $exception->getMessage();
          }
      }   
}

?>