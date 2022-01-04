<?php
require('./controleur/controleur.php');
require('./controleur/api.php');

//$action = isset($_GET['action']) ? $_GET['action'] : NULL; // récupérer l'action navbar

$params = explode('/', htmlspecialchars(rtrim($_GET['action'],'/')));
//if(!empty($params[0])) test();
//$params[0]();

$ess = '[{"id_capteur":"4","perimetre":"6","coordonnee_x":"5","intensite":"5","coordonnee_y":"5"}]';

//api_put_capteurs($ess);

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
                    header("HTTP/1.0 405 Method Not Allowed");
                    break;
                }
            }
            elseif($params[1]=="camions"){
                if(!isset($params[2])){
                    api_get_camions();
                }
                else{
                    header("HTTP/1.0 404 Not Found");
                    break;
                }
            }
        //Capteur
            elseif($params[1]=="capteur"){
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
        //Caserne
            elseif($params[1]=="caserne"){
                if(isset($params[2])){
                    api_get_caserne($params[2]);
                }
                else{
                    header("HTTP/1.0 405 Method Not Allowed");
                    break;
                }
            }
            elseif($params[1]=="casernes"){
                if(!isset($params[2])){
                    api_get_casernes();
                }
                else{
                    header("HTTP/1.0 404 Not Found");
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
                    header("HTTP/1.0 405 Method Not Allowed");
                    break;
                }
            }
            elseif($params[1]=="equipes"){
                if(!isset($params[2])){
                    api_get_equipes();
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
        //Operation
            elseif($params[1]=="operation"){
                if(isset($params[2])){
                    api_get_operation($params[2]);
                }
                else{
                    header("HTTP/1.0 405 Method Not Allowed");
                    break;
                }
            }
            elseif($params[1]=="operations"){
                if(!isset($params[2])){
                    api_get_operations();
                }
                else{
                    header("HTTP/1.0 404 Not Found");
                    break;
                }
            }
        //Pompier
            elseif($params[1]=="pompier"){
                if(isset($params[2])){
                    api_get_pompier($params[2]);
                }
                else{
                    header("HTTP/1.0 405 Method Not Allowed");
                    break;
                }
            }
            elseif($params[1]=="pompiers"){
                if(!isset($params[2])){
                    api_get_pompiers();
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
/*
                //Camion
                    if($params[1]=="camion"){
                        if(isset($params[2])){
                            api_put_camion($params[2]);
                        }
                        else{
                            header("HTTP/1.0 405 Method Not Allowed");
                            break;
                        }
                    }
                    elseif($params[1]=="camions"){
                        if(!isset($params[2])){





                            //api_put_camions();
                        }
                        else{
                            header("HTTP/1.0 404 Not Found");
                            break;
                        }
                    }
*/
                //Capteur
                    //else

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

/*
                //Caserne
                    elseif($params[1]=="caserne"){
                        if(isset($params[2])){
                            api_put_caserne($params[2]);
                        }
                        else{
                            header("HTTP/1.0 405 Method Not Allowed");
                            break;
                        }
                    }
                    elseif($params[1]=="casernes"){
                        if(!isset($params[2])){
                            api_put_casernes();
                        }
                        else{
                            header("HTTP/1.0 404 Not Found");
                            break;
                        }
                    }
                //Constitution ?
                //Equipe
                    elseif($params[1]=="equipe"){
                        if(isset($params[2])){
                            api_put_equipe($params[2]);
                        }
                        else{
                            header("HTTP/1.0 405 Method Not Allowed");
                            break;
                        }
                    }
                    elseif($params[1]=="equipes"){
                        if(!isset($params[2])){
                            api_put_equipes();
                        }
                        else{
                            header("HTTP/1.0 404 Not Found");
                            break;
                        }
                    }
                //Feu
                    elseif($params[1]=="feu"){
                        if(isset($params[2])){
                            api_put_feu($params[2]);
                        }
                        else{
                            header("HTTP/1.0 405 Method Not Allowed");
                            break;
                        }
                    }
                    elseif($params[1]=="feux"){
                        if(!isset($params[2])){
                            api_put_feux();
                        }
                        else{
                            header("HTTP/1.0 404 Not Found");
                            break;
                        }
                    }
                //Operation
                    elseif($params[1]=="operation"){
                        if(isset($params[2])){
                            api_put_operation($params[2]);
                        }
                        else{
                            header("HTTP/1.0 405 Method Not Allowed");
                            break;
                        }
                    }
                    elseif($params[1]=="operations"){
                        if(!isset($params[2])){
                            api_put_operations();
                        }
                        else{
                            header("HTTP/1.0 404 Not Found");
                            break;
                        }
                    }
                //Pompier
                    elseif($params[1]=="pompier"){
                        if(isset($params[2])){
                            api_put_pompier($params[2]);
                        }
                        else{
                            header("HTTP/1.0 405 Method Not Allowed");
                            break;
                        }
                    }
                    elseif($params[1]=="pompiers"){
                        if(!isset($params[2])){
                            api_put_pompiers();
                        }
                        else{
                            header("HTTP/1.0 404 Not Found");
                            break;
                        }
                    }
    */
                    else{
                        header("HTTP/1.0 404 Not Found");
                        break;
                    }
                    break;


    //case 'DELETE':
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



//methode 1
    if ($_SERVER['REQUEST_METHOD'] == 'PUT')
{
  $_PUT = array();
  parse_str(file_get_contents("php://input"), $_PUT);
  foreach ($_PUT as $key => $value)
  {
    echo $key . " : " . $value;
  }
}

//methode 2

$decoded_input = json_decode(file_get_contents("php://input"), true);
//Here you have usual php array stored in $decoded_input. Do some stuff with it.
header('Content-type: application/json');
echo json_encode($decoded_input);

}


insertion ou update 
INSERT INTO TABLE COLONNE1, COLONNE5, COLONNE8 VALUES (valeurs1, valeurs5, valeurs8)
ON DUPLICATE KEY UPDATE COLONNE5 = valeurs5, COLONNE8 = valeurs8

*/