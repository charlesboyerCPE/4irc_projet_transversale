<?php

require_once ('./modele/BDD.php');
require('./modele/Capteur.php');

function carte()
{
    $capteurs = new Capteur;
    $liste_capteurs = $capteurs->getAllCapteurs();
    //var_dump($liste_capteurs);

   require('vue/carte.php');
}


function dashboard()
{
    
    require('vue/dashboard.php');
}
?>