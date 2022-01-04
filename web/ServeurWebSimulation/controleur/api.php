<?php
//require_once ('./modele/BDD.php');
require_once ('./modele/Capteur.php');
require_once ('./modele/Feu.php');

//Capteur
function api_get_capteur($id){
    $capteur = new Capteur;
    $listeCapteur = $capteur->getCapteurById($id);
    echo json_encode($listeCapteur);
}

function api_get_capteurs(){
    $capteur = new Capteur;
    $listeCapteurs = $capteur->getAllCapteurs();
    echo json_encode($listeCapteurs);
}

function api_put_capteur($id){
    $capteur = new Capteur;
    $listeCapteur = $capteur->putCapteurById($id);
}

function api_put_capteurs($data){
    $capteur = new Capteur;
    $listeCapteurs = $capteur->putAllCapteurs($data);
}

//Feu
function api_get_feu($id){
    $feu = new Feu;
    $listeFeu = $feu->getFeuById($id);
    echo json_encode($listeFeu);
}

function api_get_feux(){
    $feu = new Feu;
    $listeFeux = $feu->getAllFeux();
    echo json_encode($listeFeux);
}

function api_put_feu($id){
    $feu = new Feu;
    $listeFeu = $feu->putFeuById($id);
}

function api_put_feux($data){
    $feu = new Feu;
    $listeFeux = $feu->putAllFeux($data);
}


?>