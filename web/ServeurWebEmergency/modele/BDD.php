<?php
		
	function connectDb(){
            $host = 'localhost'; // ou localhost
            $user = 'root';      // ou login
            $pwd = '';      // ou xxxxxx
            $db = 'database_emergency';
            try {
                  
                  $bdd = new PDO('mysql:host='.$host.';dbname='.$db.
                              ';charset=utf8', $user, $pwd,
                              array(PDO::ATTR_ERRMODE => PDO::ERRMODE_EXCEPTION));
                              
                 
                  // $bdd = new PDO("pgsql:host=$host;port=5432;dbname=$db;", $user, $pwd);
                  //$bdd = new PDO("pgsql:host=localhost;port=5432;dbname=DatabaseEmergency;", $user, $pwd);

                  return $bdd;
            }catch (Exception $e) {
                  exit('Erreur : '.$e->getMessage());
            }
      }


 /*
  $dsn = "pgsql:host=$host;port=5432;dbname=$dbname;user=$username;password=$password";
   
  try{
     $conn = new PDO($dsn);
     
     if($conn){
      echo "Connecté à $dbname avec succès!";
     }
  }catch (PDOException $e){
     echo $e->getMessage();
  }
*/

?>