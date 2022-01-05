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

    $pompiers = new Pompier;
    $liste_pompiers = $pompiers->getAllPompiers();

    require('vue/carte.php');
}

?>