package src.simulateur.model.CamionModel;

import org.json.JSONArray;
import src.commun.Api.DialogueExterneAPI;

import org.apache.log4j.Logger;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class CamionModel {
    private List<Camion> camions;
    private DialogueExterneAPI api;
    private JSONArray json;

    private final Logger logger;

    public CamionModel() {
        camions = new ArrayList<>();
        this.api = null;
        this.json = new JSONArray();
        logger = Logger.getLogger(CamionModel.class);
    }

    // Méthode permettant d'obtenir la liste de tous les camions présent dans la BDD de l'EM
    public void obtenircamionsBDD(String urlApi) {
        this.api = new DialogueExterneAPI(urlApi);

        // Récupération de tous les camions de la base de données
        this.json = this.api.getDonnees("camions");
        logger.info("[obtenircamionsBDD()] JSON Reçu : " + json);

        // Création des objets Camion
        for (int i = 0; i < json.length(); i++) {
            camions.add(new Camion(this.json.getJSONObject(i)));
            logger.info("[obtenircamionsBDD()] Camion récupéré : \n" + camions.get(i).toString());
        }
    }

    // Méthode permettant de déplacer un camion dans le simulateur et mettre à jour les coordonnées dans la BDD de l'EM
    public void deplacerCamion(String urlApi, int idCamion, double xDest, double yDest) {
        int codeRetour = -1;

        this.api = new DialogueExterneAPI(urlApi);
        logger.info("[deplacerCamion()] - Coordonnées actuelles: [" + camions.get(idCamion).getCoord().getX() + "," + camions.get(idCamion).getCoord().getY() + "]");

        // Mise à jour des camions
        camions.get(idCamion).setX(xDest);
        camions.get(idCamion).setY(yDest);
        logger.info("[deplacerCamion()] - Nouvelle coordonnées: [" + camions.get(idCamion).getCoord().getX() + "," + camions.get(idCamion).getCoord().getY() + "]");

        // Mise à jour dans la BDD de l'EM
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(camions.get(idCamion).toJson());

        codeRetour = this.api.setDonnees("camions", jsonArray);
        if (codeRetour == HttpURLConnection.HTTP_CREATED) {
            logger.info("[deplacerCamion()] - Localisation du camion n°" + idCamion + " modifié");
        } else {
            logger.error("[deplacerCamion()] - Localisation du camion n°" + idCamion + " non modifié");
        }

    }

    public List<Camion> getcamions() {
        return camions;
    }
}
