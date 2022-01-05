<?php 
    include('template.php');


    function afficher_tableau($tableau)
{
    $tab=array();
    // on fait une boucle qui lit les éléments du tableau
    foreach ($tableau as $cle=>$valeur) {
        if( is_array($valeur) || is_object($valeur) ) {
            echo $cle.' : <ul>';
            afficher_tableau($valeur);
            echo '</ul>';
        } else {
            echo $cle.' = '.$valeur.' <br>';
            $tab[]=array(
                $cle => $valeur,
            );
        }
    }
    return $tab;
}
//$tab = afficher_tableau($liste_capteurs);
//foreach($tab as $key => $val) echo $key;
$liste_capteurs = json_encode($liste_capteurs, true);
var_dump($liste_capteurs);
 
//afficher_tableau($liste_capteurs);

/*
    var_dump($liste_capteurs);
    foreach ($liste_capteurs as $cle=>$valeur) {
        if( is_array($valeur) || is_object($valeur) ) {
            echo $cle.' : <ul>';
            //afficher_tableau($valeur);
            echo '</ul>';
        } else {
            echo $cle.' = '.$valeur.' <br>';
        }
    }
    foreach($liste_capteurs as $key => $val) echo $liste_capteurs[$key]['id_capteur'];
    */
?>


<h2>Carte</h2>
<div id="map"></div>
<footer>
</footer>


<script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"
   integrity="sha512-XQoYMqMTK8LvdxXYG3nZ448hOEQiglfqkJs1NOQV44cWnUrBc8PkAOcXy20w0vlaXaVUearIOBhiXZ5V3ynxwA=="
   crossorigin=""></script>
   <script type="text/javascript" src="../public/js/map.js"></script>
<script>afficheMap(<?php echo $liste_capteurs ?>)</script>
<!--
<script type="module" src="../public/js/map.js">afficheMap()</script>
-->


</body>
</html>

