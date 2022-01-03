<?php

/*
function getFeuById($id){
    $stmt = $dbh->prepare("SELECT id_feu FROM feu WHERE id_feu = '".$id."' ");
    $stmt->setFetchMode(PDO::FETCH_CLASS, 'Feu'); 
    $stmt->execute();
    $obj = $stmt->fetch()
    $stmt->closeCursor();
    sendJSON($obj);
}
*/

function test(){
    echo 'api';
}


?>