window.addEventListener("load", init);





function init(){
    var map = L.map('map').setView([45.764043, 4.835659], 13);

    L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
        attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
    }).addTo(map);
/*
    L.marker([45.764043,  4.835659]).addTo(map)
        .bindPopup('Lyon')
        .openPopup();
    */
}
