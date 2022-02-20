<?php 
    include('template.php');
?>

<div id="corps">
    <button data-toggle="collapse" data-target="#formulaire" class="btn btn-light">Générer ou modifier un feu</button>
    <div id="formulaire" class="collapse">
        <form action="./index.php?action=getApiFeu" method="POST">
            <div class="form-group">
                <label for="intensite">Intensite</label>
                <input type="number" class="form-control" id="intensite">
            </div>
            <div class="form-group">
                <label for="frequence">Frequence</label>
                <input type="number" class="form-control" id="frequence">
            </div>
            <button id="envoyer" type="submit" class="btn btn-light">Valider</button>
        </form>
    </div>


    <div class="icones">
        <legend>Légende de la carte :</legend>
        <img src="./public/img/capteur.png">Capteur
        <img src="./public/img/feu.png">Feu
        <img src="./public/img/camion.png">Camion
    </div>
    <div id="map"></div>
</div>
<footer>
</footer>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
      
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
        afficheMap(map);
    }, 5000);
</script>

</body>
</html>

