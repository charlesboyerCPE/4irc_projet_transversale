<?php
require('./controleur/controleur.php');
require('./controleur/api.php');

error_reporting(E_ALL ^ E_NOTICE);
$params = explode('/', htmlspecialchars(rtrim($_GET['action'],'/')));

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
    switch($request_method){
        case 'GET':
            if($params[1]=="capteur" || $params[1]=="capteurs"){
                api_get_capteur($params[2]);
            }elseif($params[1]=="feu" || $params[1]=="feux"){
                api_get_feu($params[2]);
            }else{
                header("HTTP/1.1 404 NOT FOUND");
                return "break";
            }
            break;

        case 'PUT':
            $chaine = recupJson();
            if(!isset($params[2])){
                if($params[1]=="capteurs"){
                    api_put_capteur($chaine);
                    header("HTTP/1.1 201 CREATED");
                }elseif($params[1]=="feux"){
                    api_put_feu($chaine);
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
            if($params[1]=="capteur" || $params[1]=="capteurs"){
                api_delete_capteur($params[2], $chaine);
                header("HTTP/1.1 200 DELETED");
            }elseif($params[1]=="feu" || $params[1]=="feux"){
                api_delete_feu($params[2], $chaine);
                header("HTTP/1.1 200 DELETED");
            }else{
                header("HTTP/1.1 404 NOT FOUND");
                break;
            }
            break;
    }exit();
}elseif($action=="getApiFeu"){
    //
}else{
    carte(); 
}