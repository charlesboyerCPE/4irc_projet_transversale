<?php
require('./controleur/controleur.php');
require('./controleur/api.php');

$params = explode('/', htmlspecialchars(rtrim($_GET['action'],'/')));

$ess = '[{"id_capteur":"4","perimetre":"6","coordonnee_x":"5","intensite":"5","coordonnee_y":"5"}]';
//api_put_capteurs($ess);

if($params[0] == "api"){  
    
   $request_method = $_SERVER["REQUEST_METHOD"];
   switch($request_method){
       case 'GET':
        //Capteur
            if($params[1]=="capteur"){
                if(isset($params[2])){
                    api_get_capteur($params[2]);
                }
                else{
                    header("HTTP/1.0 405 Method Not Allowed");
                    break;
                }
            }
            elseif($params[1]=="capteurs"){
                if(!isset($params[2])){
                    api_get_capteurs();
                }
                else{
                    header("HTTP/1.0 404 Not Found");
                    break;
                }
            }
        //Feu
            elseif($params[1]=="feu"){
                if(isset($params[2])){
                    api_get_feu($params[2]);
                }
                else{
                    header("HTTP/1.0 405 Method Not Allowed");
                    break;
                }
            }
            elseif($params[1]=="feux"){
                if(!isset($params[2])){
                    api_get_feux();
                }
                else{
                    header("HTTP/1.0 404 Not Found");
                    break;
                }
            }
            else{
                header("HTTP/1.0 404 Not Found");
                break;
            }
            break;
        
            
            
            case 'PUT':
                //Capteur
                    if($params[1]=="capteurs"){
                        if(!isset($params[2])){
                        /* Les données PUT arrivent du flux */
                            $chaine ='';
                            $putdata = fopen("php://input", "r");
                        /* Lecture des données, 1 Ko à la fois et écriture dans le fichier */
                            while ($data = fread($putdata, 1024)){
                                $chaine.= $data;
                            }
                            error_log('chaine : '.$chaine);
                            api_put_capteurs($chaine);
                            header("HTTP/1.0 201 CREATED");
                        }
                        else{
                            header("HTTP/1.0 404 Not Found");
                            break;
                        }
                    }
                    else{
                        header("HTTP/1.0 404 Not Found");
                        break;
                    }
                    break;
   }
   exit();
}
elseif($params[0] == "getCarte"){
    carte();
}
elseif($params[0] == "getDashboard"){
    dashboard();
}
else{
    carte();
}