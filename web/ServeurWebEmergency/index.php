<?php
require('./controleur/controleur.php');
require('./controleur/api.php');

$action = isset($_GET['action']) ? $_GET['action'] : NULL; // récupérer l'action navbar

$params = explode('/', htmlspecialchars(rtrim($_GET['action'],'/')));
//if(!empty($params[0])) test();
//$params[0]();

if($params[0] == "api"){
   test();
}
elseif($params[0] == "getCarte"){
    carte();
}elseif($params[0] == "getDashboard"){
    dashboard();
}else{
    carte();
}


/*else{   carte();
    if (isset($_GET['action'])){  
    
        if($action=="getDashboard"){ 
            dashboard();
        }elseif($action=="getCarte"){
            carte();
        }

    }else{
        carte();
    }



}*/