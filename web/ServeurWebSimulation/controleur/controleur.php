<?php

require_once ('./modele/BDD.php');
require('./modele/Capteur.php');

function carte()
{
    $capteurs = new Capteur;
    $liste_capteurs = $capteurs->getAllCapteurs();

    $feux = new Feu;
    $liste_feux = $feux->getAllFeux();

    require('vue/carte.php');
}

?>