<?php
require('./controleur/controleur.php');
require('./controleur/api.php');

error_reporting(E_ALL ^ E_NOTICE);
//$_GET['action'] = 'api/casernes';
$params = explode('/', htmlspecialchars(rtrim($_GET['action'],'/')));

//$ess= '[{"nom_caserne":"CPE Lyon","id_caserne":20,"total_pompier":0,"coordonnee_x":45.7,"coordonnee_y":4.8},{"nom_caserne":"CPE Lyon","id_caserne":21,"total_pompier":0,"coordonnee_x":45.7,"coordonnee_y":4.8}]';
//$ess = '[{"id_camion":"1","id_caserne":"0","type_produit":"test","disponibilite":"0","capacite":"10","nb_pompier":"9","coordonnee_x":"45.74","coordonnee_y":"4.83","coordonnee_dest_x":"45.7719","coordonnee_dest_y":"4.83566"}]';
$ess ='[{"id_capteur":"20","intensite":"0","perimetre":"10","coordonnee_x":"6","coordonnee_y":"0"},{"id_capteur":"1","intensite":"0","perimetre":"10","coordonnee_x":"8","coordonnee_y":"5"},{"id_capteur":"2","intensite":"0","perimetre":"10","coordonnee_x":"4","coordonnee_y":"1"},{"id_capteur":"3","intensite":"0","perimetre":"10","coordonnee_x":"7","coordonnee_y":"1"},{"id_capteur":"4","intensite":"0","perimetre":"10","coordonnee_x":"0","coordonnee_y":"2"},{"id_capteur":"10","intensite":"3","perimetre":"3","coordonnee_x":"45.764","coordonnee_y":"4.83566"}]';
//api_put_caserne($ess);
//api_put_update_camion($ess);
//api_put_capteur($ess);
function recupJson(){
    $chaine ='';
    $putdata = fopen("php://input", "r");
    while ($data = fread($putdata, 1024)){
        $chaine.= $data;
    }
    error_log('chaine : '.$chaine);
    return $chaine;
}

if($params[0] == "api"){
    $chaine ="";
    $request_method = $_SERVER["REQUEST_METHOD"];
    //$request_method = 'PUT';
    switch($request_method){
        
        case 'GET':
            if($params[1]=="camion" || $params[1]=="camions"){
                api_get_camion($params[2]);
            }elseif($params[1]=="capteur" || $params[1]=="capteurs"){
                api_get_capteur($params[2]);
            }elseif($params[1]=="caserne" || $params[1]=="casernes"){
                api_get_caserne($params[2]);
            }elseif($params[1]=="equipe" || $params[1]=="equipes"){
                api_get_equipe($params[2]);
            }elseif($params[1]=="feu" || $params[1]=="feux"){
                api_get_feu($params[2]);
            }elseif($params[1]=="operation" || $params[1]=="operations"){
                api_get_operation($params[2]);
            }elseif($params[1]=="pompier" || $params[1]=="pompiers"){
                api_get_pompier($params[2]);
            }else{
                header("HTTP/1.1 404 NOT FOUND");
                return "break";
            }
            break;

        case 'PUT':
            $chaine = recupJson(); 
            //$chaine = $ess;      
            if(!isset($params[2])){
                if($params[1]=="camions"){
                    api_put_camion($chaine);
                    header("HTTP/1.1 201 CREATED");   
                }elseif($params[1]=="updateCamions"){
                    api_put_update_camion($chaine);
                    header("HTTP/1.1 201 CREATED");   
                }elseif($params[1]=="updateCamionsDest"){
                    api_put_update_camion_dest($chaine);
                    header("HTTP/1.1 201 CREATED");   
                }elseif($params[1]=="capteurs"){
                    api_put_capteur($chaine);
                    header("HTTP/1.1 201 CREATED");
                }elseif($params[1]=="casernes"){
                    api_put_caserne($chaine);
                    header("HTTP/1.1 201 CREATED");
                }elseif($params[1]=="equipes"){
                    api_put_equipe($chaine);
                    header("HTTP/1.1 201 CREATED");
                }elseif($params[1]=="feux"){
                    api_put_feu($chaine);
                    header("HTTP/1.1 201 CREATED");
                }elseif($params[1]=="operations"){
                    api_put_operation($chaine);
                    header("HTTP/1.1 201 CREATED");
                }elseif($params[1]=="pompiers"){
                    api_put_pompier($chaine);
                    header("HTTP/1.1 201 CREATED");
                }else{
                    header("HTTP/1.1 404 NOT FOUND");
                    break;
                }
            }else{
                header("HTTP/1.1 404 NOT FOUND");
                break;
            }
            break;
              
        case 'DELETE' :
            if(!isset($params[2])){
                $chaine = recupJson();
            }
            if($params[1]=="camion" || $params[1]=="camions"){
                    api_delete_camion($params[2], $chaine);
                    header("HTTP/1.1 200 DELETED");
            }elseif($params[1]=="capteur" || $params[1]=="capteurs"){
                api_delete_capteur($params[2], $chaine);
                header("HTTP/1.1 200 DELETED");
            }elseif($params[1]=="caserne" || $params[1]=="casernes"){
                api_delete_caserne($params[2], $chaine);
                header("HTTP/1.1 200 DELETED");
            }elseif($params[1]=="equipe" || $params[1]=="equipes"){
                api_delete_equipe($params[2], $chaine);
                header("HTTP/1.1 200 DELETED");
            }elseif($params[1]=="feu" || $params[1]=="feux"){
                api_delete_feu($params[2], $chaine);
                header("HTTP/1.1 200 DELETED");
            }elseif($params[1]=="operation" || $params[1]=="operations"){
                api_delete_operation($params[2], $chaine);
                header("HTTP/1.1 200 DELETED");
            }elseif($params[1]=="pompier" || $params[1]=="pompiers"){
                api_delete_pompier($params[2], $chaine);
                header("HTTP/1.1 200 DELETED");
            }else{
                header("HTTP/1.1 404 NOT FOUND");
                break;
            }
            break;
            
    }exit();
}else{
    carte(); 
}