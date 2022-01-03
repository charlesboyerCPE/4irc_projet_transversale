<?php
require('./controleur/controleur.php');

$action = isset($_GET['action']) ? $_GET['action'] : NULL; // récupérer l'action navbar

if (isset($_GET['action'])) {
  
    if($action=="getDashboard"){ 
    
        dashboard();
    }elseif($action=="getCarte"){ 
    
        carte();
    }
}else{  //echo 'oui';
    carte();
}