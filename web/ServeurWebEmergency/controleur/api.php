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

function api_put_camions($data){
    $camion = new Camion;
    $listeCamions = $camion->putAllCamions($data);
}

function api_delete_camion($id){
    $camion = new Camion;
    $listeCamion = $camion->deleteCamionById($id);
    echo json_encode($listeCamion);
}

function api_delete_camions($data){
    $camion = new Camion;
    $listeCamions = $camion->deleteCamions($data);
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

function api_put_capteurs($data){
    $capteur = new Capteur;
    $listeCapteurs = $capteur->putAllCapteurs($data);
}

function api_delete_capteur($id){
    $capteur = new Capteur;
    $listeCapteur = $capteur->deleteCapteurById($id);
}

function api_delete_capteurs($data){
    $capteur = new Capteur;
    $listeCapteurs = $capteur->deleteCapteurs($data);
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

function api_put_casernes($data){
    $caserne = new Caserne;
    $listeCasernes = $caserne->putAllCasernes($data);
}

function api_delete_caserne($id){
    $caserne = new Caserne;
    $listeCaserne = $caserne->deleteCaserneById($id);
}

function api_delete_casernes($data){
    $caserne = new Caserne;
    $listeCasernes = $caserne->deleteCasernes($data);
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

function api_put_equipes($data){
    $equipe = new Equipe;
    $listeEquipes = $equipe->putAllEquipes($data);
}

function api_delete_equipe($id){
    $equipe = new Equipe;
    $listeEquipes = $equipe->deleteEquipeById($id);
}

function api_delete_equipes($data){
    $equipe = new Equipe;
    $listeEquipes = $equipe->deleteEquipes($data);
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

function api_put_feux($data){
    $feu = new Feu;
    $listeFeux = $feu->putAllFeux($data);
}

function api_delete_feu($id){
    $feu = new Feu;
    $listeFeu = $feu->deleteFeuById($id);
}

function api_delete_feux($data){
    $feu = new Feu;
    $listeFeux = $feu->deleteFeux($data);
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

function api_put_operations($data){
    $operation = new Operation;
    $listeOperations = $operation->putAllOperations($data);
}

function api_delete_operation($id){
    $operation = new Operation;
    $listeOperation = $operation->deleteOperationById($id);
}

function api_delete_operations($data){
    $operation = new Operation;
    $listeOperations = $operation->deleteOperations($data);
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

function api_put_pompiers($data){
    $pompier = new Pompier;
    $listePompiers = $pompier->putAllPompiers($data);
}

function api_delete_pompier($id){
    $pompier = new Pompier;
    $listePompier = $pompier->deletePompierById($id);
}

function api_delete_pompiers($data){
    $pompier = new Pompier;
    $listePompiers = $pompier->deletePompiers($data);
}

?>