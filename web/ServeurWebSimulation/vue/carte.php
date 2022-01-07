<?php 
    include('template.php');

    $liste_capteurs = json_encode($liste_capteurs, true);
    $liste_feux = json_encode($liste_feux, true);
    $liste_camion = array();
?>
<legend>Générer ou modifier un feu</legend>


  

  <form>
                <div class="form-group">
                    <label for="intensite">Intensite</label>
                    <input type="intensite" class="form-control" id="intensite">
                </div>
                <div class="form-group">
                    <label for="frequence">Frequence</label>
                    <input type="frequence" class="form-control" id="frequence">
                </div>
                <button type="submit" class="btn btn-primary">Submit</button>
        </form>

<legend>Légende de la carte :</legend>
<div class="icones">
    <img src="../public/img/capteur.png">Capteur
    <img src="../public/img/feu.png">Feu
    <img src="../public/img/camion.png">Camion
</div>
<div id="map"></div>
<footer>
</footer>


<script src="https://unpkg.com/leaflet@1.7.1/dist/leaflet.js"
   integrity="sha512-XQoYMqMTK8LvdxXYG3nZ448hOEQiglfqkJs1NOQV44cWnUrBc8PkAOcXy20w0vlaXaVUearIOBhiXZ5V3ynxwA=="
   crossorigin="">
</script>
<script type="text/javascript" src="../public/js/map.js"></script>
<script>afficheMap(<?php echo $liste_capteurs ?>, <?php echo $liste_feux ?>, <?php echo $liste_camions ?>)</script>

</body>
</html>

