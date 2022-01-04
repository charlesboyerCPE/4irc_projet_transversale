<?php

require_once ('./BDD.php');



$arrayConsultation = array();
        $bdd = connectDb();
        $req = $bdd->query('SELECT * FROM capteur');
        $resultat = $req->fetchAll();
        $nbre_lignes = count($resultat);
        foreach($resultat as $row) { 
                echo $row['id_capteur'];
        }




?>