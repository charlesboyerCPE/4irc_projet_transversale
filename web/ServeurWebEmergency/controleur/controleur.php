<?php

require_once ('./modele/BDD.php');
require('./modele/Capteur.php');

function carte()
{
    $capteurs = new Capteur;
    $liste_capteurs = $capteurs->getAllCapteurs();

    $feux = new Feu;
    $liste_feux = $feux->getAllFeux();


    $camions = new Camion;
    $liste_camions = $camions->getAllCamions();
    //var_dump($liste_camions);
    /*
    for(;;) {
        $liste_camions = $camions->getAllCamions();
        sleep(10);
    }
    */

    $caserne = new Caserne;
    $liste_casernes = $caserne->getAllCasernes();

    require('vue/carte.php');
}

?>