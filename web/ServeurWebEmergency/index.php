<?php
require('./controleur/controleur.php');
require('./controleur/api.php');

//$action = isset($_GET['action']) ? $_GET['action'] : NULL; // récupérer l'action navbar

$params = explode('/', htmlspecialchars(rtrim($_GET['action'],'/')));
//if(!empty($params[0])) test();
//$params[0]();

if($params[0] == "api"){  
    
    
    /*
     /api/feu/id/
     /api/feux/
    */
   insert(1);

   $request_method = $_SERVER["REQUEST_METHOD"];
   switch($request_method){
       case 'GET':
            // Capteur par id
            if($params[1]=="capteur"){
                if(isset($params[2])){
                    api_capteur($params[2]);
                }
                else{
                    header("HTTP/1.0 405 Method Not Allowed");
                    break;
                }
            }
            // Capteurs
            elseif($params[1]=="capteurs"){
                if(!isset($params[2])){
                    api_capteurs();
                }
                else{
                    header("HTTP/1.0 404 Not Found");
                    break;
                }
            }
            //FEU PAR ID
            elseif($params[1]=="feu"){
                if(isset($params[2])){
                    api_feu($params[2]);
                }
                else{
                    header("HTTP/1.0 405 Method Not Allowed");
                    break;
                }
            }
           // FEUX
            elseif($params[1]=="feux"){
                if(!isset($params[2])){
                    api_feux();
                }
                else{
                    header("HTTP/1.0 404 Not Found");
                    break;
                }
            }
           // CAMION PAR ID
            elseif($params[1]=="camion"){
                if(isset($params[2])){
                    api_camion($params[2]);
                }
                else{
                    header("HTTP/1.0 405 Method Not Allowed");
                    break;
                }
            }
           // CAMIONS
            elseif($params[1]=="camions"){
                if(!isset($params[2])){
                    api_camions();
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
            default:
            header("HTTP/1.0 405 Method Not Allowed");
            break;
   }
   exit();






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