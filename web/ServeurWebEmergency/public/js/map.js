//window.addEventListener("load", init);





function init(){ document.write('ok')
    //afficheMap();
/*
    L.marker([45.764043,  4.835659]).addTo(map)
        .bindPopup('Lyon')
        .openPopup();
    */

        
}


function afficheMap(listeCapteurs, listeFeux, listeCamions, listePompiers){

    //listeCapteurs.forEach(element => console.log(element.id_capteur));
    //console.log(listeCapteurs);

    //Création de la map
    var map = L.map('map').setView([45.764043, 4.835659], 13);
    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

    //Création des icones
    var capteurIcon = L.icon({
        iconUrl: './public/img/capteur.png',
       shadowUrl: '',
    
        iconSize:     [50, 50], // size of the icon
        shadowSize:   [50, 64], // size of the shadow
        iconAnchor:   [22, 94], // point of the icon which will correspond to marker's location
        shadowAnchor: [4, 62],  // the same for the shadow
        popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
    });

    var feuIcon = L.icon({
        iconUrl: './public/img/feu.png',
       shadowUrl: '',
    
        iconSize:     [50, 50], // size of the icon
        shadowSize:   [50, 64], // size of the shadow
        iconAnchor:   [22, 94], // point of the icon which will correspond to marker's location
        shadowAnchor: [4, 62],  // the same for the shadow
        popupAnchor:  [-3, -76] // point from which the popup should open relative to the iconAnchor
    });
    //L.marker([45.764043, 4.835659], {icon: feuIcon}).bindPopup("Feu").addTo(map);
/*
    for (var i = 0; i < listeCapteurs.length; i++) {
        L.marker([listeFeux[i].coordonnee_x, listeFeux[i].coordonnee_y], {icon: capteurIcon}).bindPopup("Capteur").addTo(map);
    }
*/
    for (var i = 0; i < listeFeux.length; i++) {
        L.marker([45.764043, 4.835659], {icon: feuIcon}).bindPopup("Feu").addTo(map);
    }

    //Capteurs sous forme de cercles
    for (var i = 0; i < listeCapteurs.length; i++) {
        //console.log("listeCapteurs" + listeCapteurs[i].id_capteur);
         L.circle([45.764043, 4.835659], {
            color: 'red',
            fillColor: '#f03',
            fillOpacity: 0.5,
            radius: 200//500
        }).addTo(map);
    }
  /*  

    for (var i = 0; i < listeFeux.length; i++) {
         L.circle([listeFeux[i].coordonnee_x, listeFeux[i].coordonnee_y], {
            color: 'blue',
            fillColor: 'blue',
            fillOpacity: 0.5,
            radius: 100
        }).addTo(map);
    }
*/
    
}

   
