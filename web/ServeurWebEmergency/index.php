<?php
require('./controleur/controleur.php');
require('./controleur/api.php');

//$action = isset($_GET['action']) ? $_GET['action'] : NULL; // récupérer l'action navbar

$params = explode('/', htmlspecialchars(rtrim($_GET['action'],'/')));
//if(!empty($params[0])) test();
//$params[0]();

$ess = '[{"id_capteur":0,"perimetre":10,"coordonnee_x":1,"intensite":0,"coordonnee_y":6}, {"id_capteur":0,"perimetre":10,"coordonnee_x":7,"intensite":0,"coordonnee_y":4}, {"id_capteur":0,"perimetre":10,"coordonnee_x":3,"intensite":0,"coordonnee_y":0}]';

//api_put_feux($ess);

function recupJson(){
    /* Les données PUT arrivent du flux */
    $chaine ='';
    $putdata = fopen("php://input", "r");
    /* Lecture des données, 1 Ko à la fois et écriture dans le fichier */
    while ($data = fread($putdata, 1024)){
        $chaine.= $data;
    }
    error_log('chaine : '.$chaine);
    return $chaine;
}

            /*
fonciton ($param1 $param2, mot = "pompier", erreur )
elseif($param1==$mot){ 
                    if(isset($params[2])){
                        return true
                    }
                    else{
                        header($erreur);
                        break;
                        rerurn false
                    }
                }
if( fonction )
api_delete_pompier($params[2]);

            */
            /*
        if(verifGet("", "", $params[1],$params[2], "" )){
            api_get_capteur($params[2]);
         }else{
             break;
         }
function verifGetByID($un, $param1, $param2, $erreur){
    if($param1== $un){
        if(isset($param2)){
            return true;
        }
        else{
            header($erreur);
            return false;
        }
    }

}

function verifGetAll($deux, $param1, $param2, $erreur){
    if($param1==$deux){
        if(!isset($param2)){
            api_get_capteurs();
        }
        else{
            header($erreur);
            break;
        }
    }
}
*/
if($params[0] == "api"){
    
   $request_method = $_SERVER["REQUEST_METHOD"];
   switch($request_method){
        case 'GET':
        //Camion
            if($params[1]=="camion"){
                if(isset($params[2])){
                    api_get_camion($params[2]);
                }
                else{
                    header("HTTP/1.1 405 METHOD NOT ALLOWED");
                    break;
                }
            }
            elseif($params[1]=="camions"){
                if(!isset($params[2])){
                    api_get_camions();
                }
                else{
                    header("HTTP/1.1 404 NOT FOUND");
                    break;
                }
            }
        //Capteur
            elseif($params[1]=="capteur"){
                if(isset($params[2])){
                    api_get_capteur($params[2]);
                }
                else{
                    header("HTTP/1.1 405 METHOD NOT ALLOWED");
                    break;
                }
            }
            elseif($params[1]=="capteurs"){
                if(!isset($params[2])){
                    api_get_capteurs();
                }
                else{
                    header("HTTP/1.1 404 NOT FOUND");
                    break;
                }
            }
        //Caserne
            elseif($params[1]=="caserne"){
                if(isset($params[2])){
                    api_get_caserne($params[2]);
                }
                else{
                    header("HTTP/1.1 405 METHOD NOT ALLOWED");
                    break;
                }
            }
            elseif($params[1]=="casernes"){
                if(!isset($params[2])){
                    api_get_casernes();
                }
                else{
                    header("HTTP/1.1 404 NOT FOUND");
                    break;
                }
            }
        //Constitution ?
        //Equipe
            elseif($params[1]=="equipe"){
                if(isset($params[2])){
                    api_get_equipe($params[2]);
                }
                else{
                    header("HTTP/1.1 405 METHOD NOT ALLOWED");
                    break;
                }
            }
            elseif($params[1]=="equipes"){
                if(!isset($params[2])){
                    api_get_equipes();
                }
                else{
                    header("HTTP/1.1 404 NOT FOUND");
                    break;
                }
            }
        //Feu
            elseif($params[1]=="feu"){
                if(isset($params[2])){
                    api_get_feu($params[2]);
                }
                else{
                    header("HTTP/1.1 405 METHOD NOT ALLOWED");
                    break;
                }
            }
            elseif($params[1]=="feux"){
                if(!isset($params[2])){
                    api_get_feux();
                }
                else{
                    header("HTTP/1.1 404 NOT FOUND");
                    break;
                }
            }
        //Operation
            elseif($params[1]=="operation"){
                if(isset($params[2])){
                    api_get_operation($params[2]);
                }
                else{
                    header("HTTP/1.1 405 METHOD NOT ALLOWED");
                    break;
                }
            }
            elseif($params[1]=="operations"){
                if(!isset($params[2])){
                    api_get_operations();
                }
                else{
                    header("HTTP/1.1 404 NOT FOUND");
                    break;
                }
            }
        //Pompier
            elseif($params[1]=="pompier"){
                if(isset($params[2])){
                    api_get_pompier($params[2]);
                }
                else{
                    header("HTTP/1.1 405 METHOD NOT ALLOWED");
                    break;
                }
            }
            elseif($params[1]=="pompiers"){
                if(!isset($params[2])){
                    api_get_pompiers();
                }
                else{
                    header("HTTP/1.1 404 NOT FOUND");
                    break;
                }
            }
            else{
                header("HTTP/1.1 404 NOT FOUND");
                break;
            }
            break;
        case 'PUT':
            //Camion
                if($params[1]=="camions"){
                    if(!isset($params[2])){
                        $chaine = recupJson();
                        api_put_camions($chaine);
                        header("HTTP/1.1 201 CREATED");   
                    }
                    else{
                        header("HTTP/1.1 404 NOT FOUND");
                        break;
                    }
                }
            //Capteur
                elseif($params[1]=="capteurs"){
                    if(!isset($params[2])){
                        $chaine = recupJson();
                        api_put_capteurs($chaine);
                        header("HTTP/1.1 201 CREATED");
                    }
                    else{
                        header("HTTP/1.1 404 NOT FOUND");
                        break;
                    }
                }
            //Caserne
                elseif($params[1]=="casernes"){
                    if(!isset($params[2])){
                        $chaine = recupJson();
                        api_put_casernes($chaine);
                        header("HTTP/1.1 201 CREATED");
                    }
                    else{
                        header("HTTP/1.1 404 NOT FOUND");
                        break;
                    }
                }
            //Constitution ?
            //Equipe
                elseif($params[1]=="equipes"){
                    if(!isset($params[2])){
                        $chaine = recupJson();
                        api_put_equipes($chaine);
                        header("HTTP/1.1 201 CREATED");
                    }
                    else{
                        header("HTTP/1.1 404 NOT FOUND");
                        break;
                    }
                }
            //Feu
                elseif($params[1]=="feux"){
                    if(!isset($params[2])){
                        $chaine = recupJson();
                        api_put_feux($chaine);
                        header("HTTP/1.1 201 CREATED");
                    }
                    else{
                        header("HTTP/1.1 404 NOT FOUND");
                        break;
                    }
                }
            //Operation
                elseif($params[1]=="operations"){
                    if(!isset($params[2])){
                        $chaine = recupJson();
                        api_put_operations($chaine);
                        header("HTTP/1.1 201 CREATED");
                    }
                    else{
                        header("HTTP/1.1 404 NOT FOUND");
                        break;
                    }
                }
            //Pompier
                elseif($params[1]=="pompiers"){
                    if(!isset($params[2])){
                        $chaine = recupJson();
                        api_put_pompiers($chaine);
                        header("HTTP/1.1 201 CREATED");
                    }
                    else{
                        header("HTTP/1.1 404 NOT FOUND");
                        break;
                    }
                }
                else{
                    header("HTTP/1.1 404 NOT FOUND");
                    break;
                }
                break;
                
        case 'DELETE' :
            //Camion
                if($params[1]=="camion"){
                    if(isset($params[2])){
                        api_delete_camion($params[2]);
                        header("HTTP/1.1 200 DELETED");
                    }
                    else{
                        header("HTTP/1.1 405 METHOD NOT ALLOWED");
                        break;
                    }
                }
                elseif($params[1]=="camions"){
                    if(!isset($params[2])){
                        $chaine = recupJson();
                        api_delete_camions($chaine);
                        header("HTTP/1.1 200 DELETED");
                    }
                    else{
                        header("HTTP/1.1 404 NOT FOUND");
                        break;
                    }
                }
            //Capteur
                elseif($params[1]=="capteur"){
                    if(isset($params[2])){
                        api_delete_capteur($params[2]);
                        header("HTTP/1.1 200 DELETED");
                    }
                    else{
                        header("HTTP/1.1 405 METHOD NOT ALLOWED");
                        break;
                    }
                }
                elseif($params[1]=="capteurs"){
                    if(!isset($params[2])){
                        $chaine = recupJson();
                        api_delete_capteurs($chaine);
                        header("HTTP/1.1 200 DELETED");
                    }
                    else{
                        header("HTTP/1.1 404 NOT FOUND");
                        break;
                    }
                }
            //Caserne
                elseif($params[1]=="caserne"){
                    if(isset($params[2])){
                        api_delete_caserne($params[2]);
                        header("HTTP/1.1 200 DELETED");
                    }
                    else{
                        header("HTTP/1.1 405 METHOD NOT ALLOWED");
                        break;
                    }
                }
                elseif($params[1]=="casernes"){
                    if(!isset($params[2])){
                        $chaine = recupJson();
                        api_delete_casernes($chaine);
                        header("HTTP/1.1 200 DELETED");
                    }
                    else{
                        header("HTTP/1.1 404 NOT FOUND");
                        break;
                    }
                }
            //Constitution ?
            //Equipe
                elseif($params[1]=="equipe"){
                    if(isset($params[2])){
                        api_delete_equipe($params[2]);
                        header("HTTP/1.1 200 DELETED");
                    }
                    else{
                        header("HTTP/1.1 405 METHOD NOT ALLOWED");
                        break;
                    }
                }
                elseif($params[1]=="equipes"){
                    if(!isset($params[2])){
                        $chaine = recupJson();
                        api_delete_equipes($chaine);
                        header("HTTP/1.1 200 DELETED");
                    }
                    else{
                        header("HTTP/1.1 404 NOT FOUND");
                        break;
                    }
                }
            //Feu
                elseif($params[1]=="feu"){
                    if(isset($params[2])){
                        api_delete_feu($params[2]);
                        header("HTTP/1.1 200 DELETED");
                    }
                    else{
                        header("HTTP/1.1 405 METHOD NOT ALLOWED");
                        break;
                    }
                }
                elseif($params[1]=="feux"){
                    if(!isset($params[2])){
                        $chaine = recupJson();
                        api_delete_feux($chaine);
                        header("HTTP/1.1 200 DELETED");
                    }
                    else{
                        header("HTTP/1.1 404 NOT FOUND");
                        break;
                    }
                }
            //Operation
                elseif($params[1]=="operation"){
                    if(isset($params[2])){
                        api_delete_operation($params[2]);
                        header("HTTP/1.1 200 DELETED");
                    }
                    else{
                        header("HTTP/1.1 405 METHOD NOT ALLOWED");
                        break;
                    }
                }
                elseif($params[1]=="operations"){
                    if(!isset($params[2])){
                        $chaine = recupJson();
                        api_delete_operations($chaine);
                        header("HTTP/1.1 200 DELETED");
                    }
                    else{
                        header("HTTP/1.1 404 NOT FOUND");
                        break;
                    }
                }
            //Pompier
            /*
fonciton ($param1 $param2, mot = "pompier", erreur )
elseif($param1==$mot){ 
                    if(isset($params[2])){
                        return true
                    }
                    else{
                        header($erreur);
                        break;
                        rerurn false
                    }
                }
if( fonction )
api_delete_pompier($params[2]);

            */
                elseif($params[1]=="pompier"){ 
                    if(isset($params[2])){
                        api_delete_pompier($params[2]);
                        header("HTTP/1.1 200 DELETED");
                    }
                    else{
                        header("HTTP/1.1 405 METHOD NOT ALLOWED");
                        break;
                    }
                }
                elseif($params[1]=="pompiers"){
                    if(!isset($params[2])){
                        $chaine = recupJson();
                        api_delete_pompiers($chaine);
                        header("HTTP/1.1 200 DELETED");
                    }
                    else{
                        header("HTTP/1.1 404 NOT FOUND");
                        break;
                    }
                }
                else{
                    header("HTTP/1.1 404 NOT FOUND");
                    break;
                }
                break;
   }
   exit();
}
elseif($params[0] == "getCarte"){
    carte();
}
else{
    carte(); 
}