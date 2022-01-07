
function afficheMap(listeCapteurs, listeFeux, listeCamions){

    //Création de la map
    var map = L.map('map').setView([45.764043, 4.835659], 13);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);
    
    //45.796002, 4.763202  <=> 45.796002, 4.975719
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
/*
    for (var i = 0; i < listeCamions.length; i++) {
        L.marker([listeCamions[i].coordonnee_x, listeCamions[i].coordonnee_y], {icon: camionIcon})
            .bindPopup("Camion : " + listeCamions[i].id_camion + "<br>Caserne : " + listeCamions[i].id_caserne + 
                        "<br>Type de produit  : " + listeCamions[i].type_produit + "<br>Capacité : " + listeCamions[i].capacite + 
                        "<br>Nb pompier : " + listeCamions[i].nb_pompier + "<br>Coordonnée : " + "("+listeCamions[i].coordonnee_x +" ; " + listeCamions[i].coordonnee_y + ")" +
                        "<br>Destination : " + "("+listeCamions[i].coordonnee_dest_x +" ; " + listeCamions[i].coordonnee_dest_y + ")")
            .addTo(map);
    }
*/
    for (var i = 0; i < listeCapteurs.length; i++) {
        L.marker([listeCapteurs[i].coordonnee_x, listeCapteurs[i].coordonnee_y], {icon: capteurIcon})
            .bindPopup("Capteur : " + listeCapteurs[i].id_capteur + "<br>Intensité : " + listeCapteurs[i].intensite +
                         "<br>Périmètre : " + listeCapteurs[i].perimetre + "<br>Coordonnée : " +  "("+listeCapteurs[i].coordonnee_x +" ; " + listeCapteurs[i].coordonnee_y + ")")
            .addTo(map);
        //perimetre capteur
        L.circle([listeCapteurs[i].coordonnee_x, listeCapteurs[i].coordonnee_y], {
            color: 'blue',
            fillColor: '#006FF0',
            fillOpacity: 0.5,
            radius: listeCapteurs[i].perimetre
        }).addTo(map);
    }

    for (var i = 0; i < listeFeux.length; i++) {
        L.marker([listeFeux[i].coordonnee_x, listeFeux[i].coordonnee_y], {icon: feuIcon})
            .bindPopup("Feu : " + listeFeux[i].id_feu + "<br>Capteur : " + listeFeux[i].id_capteur + "<br>Intensité : " + listeFeux[i].intensite + "<br>Fréquence : " + listeFeux[i].frequence +
                         "<br>Coordonnée : " +  "("+listeFeux[i].coordonnee_x +" ; " + listeFeux[i].coordonnee_y + ")")
            .addTo(map);
    }

}
