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
function api_camion($id){
    $camion = new Camion;
    $listeCamion = $camion->getCamionById($id);
    echo json_encode($listeCamion);
}

function api_camions(){
    $camion = new Camion;
    $listeCamions = $camion->getAllCamions();
    echo json_encode($listeCamions);
}

//Capteur
function api_capteur($id){
    $capteur = new Capteur;
    $listeCapteur = $capteur->getCapteurById($id);
    echo json_encode($listeCapteur);
}

function api_capteurs(){
    $capteur = new Capteur;
    $listeCapteurs = $capteur->getAllCapteurs();
    echo json_encode($listeCapteurs);
}

//Caserne
function api_caserne($id){
    $caserne = new Caserne;
    $listeCaserne = $caserne->getCaserneById($id);
    echo json_encode($listeCaserne);
}

function api_casernes(){
    $caserne = new Caserne;
    $listeCasernes = $caserne->getAllCasernes();
    echo json_encode($listeCasernes);
}

//Constitution
function api_constitution($id){
    $constitution = new Constitution;
    $listeConstitution = $constitution->getConstitutionById($id);
    echo json_encode($listeConstitution);
}

function api_constitutions(){
    $constitution = new Constitution;
    $listeConstitutions = $constitution->getAllConstitutions();
    echo json_encode($listeConstitutions);
}

//Equipe
function api_equipe($id){
    $equipe = new Equipe;
    $listeEquipe = $equipe->getEquipeById($id);
    echo json_encode($listeEquipe);
}

function api_equipes(){
    $equipe = new Equipe;
    $listeEquipes = $equipe->getAllEquipes();
    echo json_encode($listeEquipes);
}

//Feu
function api_feu($id){
    $feu = new Feu;
    $listeFeu = $feu->getFeuById($id);
    echo json_encode($listeFeu);
}

function api_feux(){
    $feu = new Feu;
    $listeFeux = $feu->getAllFeux();
    echo json_encode($listeFeux);
}

//Operation
function api_operation($id){
    $operation = new Operation;
    $listeOperation = $operation->getOperationById($id);
    echo json_encode($listeOperation);
}

function api_feux(){
    $operation = new Operation;
    $listeOperations = $operation->getAllOperations();
    echo json_encode($listeOperations);
}

//Pompier
function api_pompier($id){
    $pompier = new Pompier;
    $listePompier = $pompier->getPompierById($id);
    echo json_encode($listePompier);
}

function api_pompiers(){
    $pompier = new Pompier;
    $listePompiers = $pompier->getAllPompiers();
    echo json_encode($listePompiers);
}

/*
insertion
update
suppression
*/


?>