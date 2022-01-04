<?php
//require_once ('./modele/BDD.php');
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
    $listeCamion = $camion->getCamionById($id);
    echo json_encode($listeCamion);
}

function api_get_camions(){
    $camion = new Camion;
    $listeCamions = $camion->getAllCamions();
    echo json_encode($listeCamions);
}

function api_set_camion($id){
    $camion = new Camion;
    $listeCamion = $camion->setCamionById($id);
}

function api_set_camions($data){
    $camion = new Camion;
    $listeCamions = $camion->setAllCamions($data);
}

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

function api_set_capteur($id){
    $capteur = new Capteur;
    $listeCapteur = $capteur->setCapteurById($id);
}

function api_set_capteurs($data){
    $capteur = new Capteur;
    $listeCapteurs = $capteur->setAllCapteurs($data);
}

//Caserne
function api_get_caserne($id){
    $caserne = new Caserne;
    $listeCaserne = $caserne->getCaserneById($id);
    echo json_encode($listeCaserne);
}

function api_get_casernes(){
    $caserne = new Caserne;
    $listeCasernes = $caserne->getAllCasernes();
    echo json_encode($listeCasernes);
}

function api_set_caserne($id){
    $caserne = new Caserne;
    $listeCaserne = $caserne->setCaserneById($id);
}

function api_set_casernes($data){
    $caserne = new Caserne;
    $listeCasernes = $caserne->setAllCasernes($data);
}

//Constitution
/*
function api_get_constitution($id){
    $constitution = new Constitution;
    $listeConstitution = $constitution->getConstitutionById($id);
    echo json_encode($listeConstitution);
}

function api_get_constitutions(){
    $constitution = new Constitution;
    $listeConstitutions = $constitution->getAllConstitutions();
    echo json_encode($listeConstitutions);
}
*/

//Equipe
function api_get_equipe($id){
    $equipe = new Equipe;
    $listeEquipe = $equipe->getEquipeById($id);
    echo json_encode($listeEquipe);
}

function api_get_equipes(){
    $equipe = new Equipe;
    $listeEquipes = $equipe->getAllEquipes();
    echo json_encode($listeEquipes);
}

function api_set_equipe($id){
    $equipe = new Equipe;
    $listeEquipe = $equipe->setEquipeById($id);
}

function api_set_equipes($data){
    $equipe = new Equipe;
    $listeEquipes = $equipe->setAllEquipes($data);
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

function api_set_feu($id){
    $feu = new Feu;
    $listeFeu = $feu->setFeuById($id);
}

function api_set_feux($data){
    $feu = new Feu;
    $listeFeux = $feu->setAllFeux($data);
}


//Operation
function api_get_operation($id){
    $operation = new Operation;
    $listeOperation = $operation->getOperationById($id);
    echo json_encode($listeOperation);
}

function api_get_operations(){
    $operation = new Operation;
    $listeOperations = $operation->getAllOperations();
    echo json_encode($listeOperations);
}

function api_set_operation($id){
    $operation = new Operation;
    $listeOperation = $operation->setOperationById($id);
}

function api_set_operations($data){
    $operation = new Operation;
    $listeOperations = $operation->setAllOperations($data);
}

//Pompier
function api_get_pompier($id){
    $pompier = new Pompier;
    $listePompier = $pompier->getPompierById($id);
    echo json_encode($listePompier);
}

function api_get_pompiers(){
    $pompier = new Pompier;
    $listePompiers = $pompier->getAllPompiers();
    echo json_encode($listePompiers);
}

function api_set_pompier($id){
    $pompier = new Pompier;
    $listePompier = $pompier->setPompierById($id);
}

function api_set_pompiers($data){
    $pompier = new Pompier;
    $listePompiers = $pompier->setAllPompiers($data);
}


?>