<?php
require_once ('./modele/Camion.php');
require_once ('./modele/Capteur.php');
require_once ('./modele/Caserne.php');
require_once ('./modele/Constitution.php');
require_once ('./modele/Equipe.php');
require_once ('./modele/Feu.php');
require_once ('./modele/Operation.php');
require_once ('./modele/Pompier.php');

// Camion
function api_get_camion($id){
    $camion = new Camion;
    if(isset($id)){
        $listeCamion = $camion->getCamionById($id);
    }else{
        $listeCamion = $camion->getAllCamions();
    }
    echo json_encode($listeCamion);
}

function api_put_camion($data){
    $camion = new Camion;
    $camion->putAllCamions($data);
}

function api_put_update_camion($data){
    $camion = new Camion;
    $camion->putUpdateCamions($data);
}

function api_delete_camion($id, $data){
    $camion = new Camion;
    if(isset($id)){
        $camion->deleteCamionById($id);
    }else{
        $camion->deleteCamions($data);
    }
}

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

//Caserne
function api_get_caserne($id){
    $caserne = new Caserne;
    if(isset($id)){
        $listeCaserne = $caserne->getCaserneById($id);
    }else{
        $listeCaserne = $caserne->getAllCasernes();
    }
    echo json_encode($listeCaserne);
}

function api_put_caserne($data){
    $caserne = new Caserne;
    $caserne->putAllCasernes($data);
}

function api_delete_caserne($id, $data){
    $caserne = new Caserne;
    if(isset($id)){
        $caserne->deleteCaserneById($id);
    }else{
        $caserne->deleteCasernes($data);
    }   
}

//Constitution
/*
function api_get_constitution($id){
    $constitution = new Constitution;
    if(isset($id)){
        $listeConstitution = $constitution->getConstitutionById($id);
    }else{
        $listeConstitution = $constitution->getAllConstitutions();
    }
    echo json_encode($listeConstitution);
}
*/

//Equipe
function api_get_equipe($id){
    $equipe = new Equipe;
    if(isset($id)){
        $listeEquipe = $equipe->getEquipeById($id);
    }else{
        $listeEquipe = $equipe->getAllEquipes();
    }
    echo json_encode($listeEquipe);
}

function api_put_equipe($data){
    $equipe = new Equipe;
    $equipe->putAllEquipes($data);
}

function api_delete_equipe($id, $data){
    $equipe = new Equipe;
    if(isset($id)){
        $equipe->deleteEquipeById($id);
    }else{
        $equipe->deleteEquipes($data);
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

//Operation
function api_get_operation($id){
    $operation = new Operation;
    if(isset($id)){
        $listeOperation = $operation->getOperationById($id);
    }else{
        $listeOperation = $operation->getAllOperations();
    }
    echo json_encode($listeOperation);
}

function api_put_operation($data){
    $operation = new Operation;
    $operation->putAllOperations($data);
}

function api_delete_operation($id, $data){
    $operation = new Operation;
    if(isset($id)){
        $operation->deleteOperationById($id);
    }else{
        $operation->deleteOperations($data);
    }  
}

//Pompier
function api_get_pompier($id){
    $pompier = new Pompier;
    if(isset($id)){
        $listePompier = $pompier->getPompierById($id);
    }else{
        $listePompier = $pompier->getAllPompiers();
    }
    echo json_encode($listePompier);
}

function api_put_pompier($data){
    $pompier = new Pompier;
    $pompier->putAllPompiers($data);
}

function api_delete_pompier($id, $data){
    $pompier = new Pompier;
    if(isset($id)){
        $pompier->deletePompierById($id);
    }else{
        $pompier->deletePompiers($data);
    }  
}

?>