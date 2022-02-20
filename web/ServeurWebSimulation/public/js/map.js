function requeteApi(url, methode){
    let requestURL = url;
    let request = new XMLHttpRequest();
    request.open(methode, requestURL);
    request.responseType = 'json';   
    return request;
}


function afficheMap(map){

    //Remove icone
    map.eachLayer(function (layer) {
        if(layer.options && (layer.options.pane === "markerPane" || layer.options.pane === "overlayPane")) {
            map.removeLayer(layer);
        }
    });

    //45.796002, 4.763202  <=>  45.796002, 4.975719
    //45.796002, 4.975719  <=>  45.715516, 4.975719
    var bounds = [[45.715516, 4.975719], [45.816106, 4.726810]];
    L.rectangle(bounds, {color: "#ff7800", weight: 1}).addTo(map);
    map.fitBounds(bounds);

    //Création des icones

    var camionIcon = L.icon({
        iconUrl: './public/img/camion.png',
        shadowUrl: '',
        iconSize:     [30, 30], // size of the icon
        shadowSize:   [50, 64], // size of the shadow
        iconAnchor:   [15, 15], // point of the icon which will correspond to marker's location
        shadowAnchor: [4, 62],  // the same for the shadow
        popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
    });

    var capteurIcon = L.icon({
        iconUrl: './public/img/capteur.png',
        shadowUrl: '',
        iconSize:     [30, 30], // size of the icon
        shadowSize:   [50, 64], // size of the shadow
        iconAnchor:   [15, 15], // point of the icon which will correspond to marker's location
        shadowAnchor: [4, 62],  // the same for the shadow
        popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
    });

    var feuIcon = L.icon({
        iconUrl: './public/img/feu.png',
        shadowUrl: '',
        iconSize:     [30, 30], // size of the icon
        shadowSize:   [50, 64], // size of the shadow
        iconAnchor:   [15, 15], // point of the icon which will correspond to marker's location
        shadowAnchor: [4, 62],  // the same for the shadow
        popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
    });

    var layerGroup = L.layerGroup();

    //Appel API

    var listeCamions;
    var listeCapteurs;
    var listeFeux;

    /*
    let requestCamions = requeteApi('http://192.168.1.35:80/4irc_projet_transversale/web/ServeurWebSimulation/api/camions', 'GET');
    requestCamions.onload = function() {
        listeCamions = requestCamions.response;   
        for(var i in listeCamions){
            marker = new L.marker([listeCamions[i].coordonnee_x, listeCamions[i].coordonnee_y], {icon: camionIcon})
                            .bindPopup("Camion : " + listeCamions[i].id_camion + "<br>Caserne : " + listeCamions[i].id_caserne + 
                                "<br>Type de produit  : " + listeCamions[i].type_produit + "<br>Capacité : " + listeCamions[i].capacite + 
                                "<br>Nb pompier : " + listeCamions[i].nb_pompier + "<br>Coordonnée : " + "("+listeCamions[i].coordonnee_x +" ; " + listeCamions[i].coordonnee_y + ")" +
                                "<br>Destination : " + "("+listeCamions[i].coordonnee_dest_x +" ; " + listeCamions[i].coordonnee_dest_y + ")")
            layerGroup.addLayer(marker);  
        }
        layerGroup.addTo(map);
    }
    requestCamions.send();
    */

    let requestCapteurs = requeteApi('http://websimulateur/api/capteurs', 'GET');
    requestCapteurs.onload = function() {
        listeCapteurs = requestCapteurs.response;   
        for(var i in listeCapteurs){
            marker = new  L.marker([listeCapteurs[i].coordonnee_x, listeCapteurs[i].coordonnee_y], {icon: capteurIcon})
                            .bindPopup("Capteur : " + listeCapteurs[i].id_capteur + "<br>Intensité : " + listeCapteurs[i].intensite +
                                "<br>Périmètre : " + listeCapteurs[i].perimetre + "<br>Coordonnée : " +  "("+listeCapteurs[i].coordonnee_x +" ; " + listeCapteurs[i].coordonnee_y + ")")
            layerGroup.addLayer(marker);  
        }
        layerGroup.addTo(map);
    }
    requestCapteurs.send();

    let requestFeux = requeteApi('http://websimulateur/api/feux', 'GET');
    requestFeux.onload = function() {
        listeFeux = requestFeux.response;   
        for(var i in listeFeux){
            marker = new  L.marker([listeFeux[i].coordonnee_x, listeFeux[i].coordonnee_y], {icon: feuIcon})
                            .bindPopup("Feu : " + listeFeux[i].id_feu + "<br>Capteur : " + listeFeux[i].id_capteur + "<br>Intensité : " + listeFeux[i].intensite + "<br>Fréquence : " + listeFeux[i].frequence +
                                 "<br>Coordonnée : " +  "("+listeFeux[i].coordonnee_x +" ; " + listeFeux[i].coordonnee_y + ")")
            layerGroup.addLayer(marker);  
        }
        layerGroup.addTo(map);
    }
    requestFeux.send();

}
