<?php
require_once ('./modele/Capteur.php');
require_once ('./modele/Feu.php');

//Capteur
function api_get_capteur($id){
    $capteur = new Capteur;
    if(isset($id)){
        $listeCapteur = $capteur->getCapteurById($id);
    }else{
        $listeCapteur = $capteur->getAllCapteurs();
    }
    echo json_encode($listeCapteur);
}

function api_put_capteur($data){
    $capteur = new Capteur;
    $capteur->putAllCapteurs($data);
}

function api_delete_capteur($id, $data){
    $capteur = new Capteur;
    if(isset($id)){
        $capteur->deleteCapteurById($id);
    }else{
        $capteur->deleteCapteurs($data);
    }
}

//Feu
function api_get_feu($id){
    $feu = new Feu;
    if(isset($id)){
        $listeFeu = $feu->getFeuById($id);
    }else{
        $listeFeu = $feu->getAllFeux();
    }
    echo json_encode($listeFeu);
}

function api_put_feu($data){
    $feu = new Feu;
    $feu->putAllFeux($data);
}

function api_delete_feu($id, $data){
    $feu = new Feu;
    if(isset($id)){
        $feu->deleteFeuById($id);
    }else{
        $feu->deleteFeux($data);
    }  
}

?>