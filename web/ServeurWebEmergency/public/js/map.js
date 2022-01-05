//window.addEventListener("load", init);





function init(){ document.write('ok')
    //afficheMap();
/*
    L.marker([45.764043,  4.835659]).addTo(map)
        .bindPopup('Lyon')
        .openPopup();
    */

        
}


function afficheMap(liste){

    liste.forEach(element => console.log(element.id_capteur));
    //console.log(liste);

    var map = L.map('map').setView([45.764043, 4.835659], 13);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);

/*
    var circle = L.circle([45.764043, 4.835659], {
        color: 'red',
        fillColor: '#f03',
        fillOpacity: 0.5,
        radius: 500
    }).addTo(map);
*/
    for (var i = 0; i < liste.length; i++) {
        console.log("liste" + liste[i].id_capteur);

         L.circle([liste[i].coordonnee_x, liste[i].coordonnee_y], {
            color: 'red',
            fillColor: '#f03',
            fillOpacity: 0.5,
            radius: 500
        }).addTo(map);
    }
    
}
/*

function afficheCapteur($capteur){
    

}
liste.forEach(element => element.coordonnee_x), liste.forEach(element => element.coordonnee_y)
*/


   
