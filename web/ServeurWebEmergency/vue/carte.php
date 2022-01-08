<?php 
    include('template.php');

    $liste_capteurs = json_encode($liste_capteurs, true);
    $liste_feux = json_encode($liste_feux, true);
    $liste_camions = json_encode($liste_camions, true);
    $liste_pompiers = json_encode($liste_pompiers, true);
    $liste_casernes = json_encode($liste_casernes, true);
?>

<legend>Légende de la carte :</legend>
<div class="icones">
    <img src="../public/img/capteur.png">Capteur
    <img src="../public/img/feu.png">Feu
    <img src="../public/img/camion.png">Camion
    <img src="../public/img/caserne.png">Caserne
</div>
<div id="map"></div>
<footer>
</footer>

<script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"
   integrity="sha512-XQoYMqMTK8LvdxXYG3nZ448hOEQiglfqkJs1NOQV44cWnUrBc8PkAOcXy20w0vlaXaVUearIOBhiXZ5V3ynxwA=="
   crossorigin="">
</script>
<script type="text/javascript" src="../public/js/map.js"></script>
<script>
   
   var map = L.map('map').setView([45.764043, 4.835659], 13);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    setInterval(() => {
        afficheMap(map, <?php echo $liste_capteurs ?>, <?php echo $liste_feux ?>, <?php echo $liste_camions ?>, <?php echo $liste_casernes ?>);
    }, 5000);

</script>
</body>
</html>

