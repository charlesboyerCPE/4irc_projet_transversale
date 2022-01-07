package src.simulateur.model.CamionModel;

import org.json.JSONArray;
import src.commun.Capteur;
import src.commun.api.DialogueExterneAPI;
import src.simulateur.model.Camion;
import src.simulateur.model.Model;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class CamionModel extends Model {
    private List<Camion> listeCamions;
    private final Logger logger;

    public CamionModel() {
        listeCamions = new ArrayList<>();
        logger = Logger.getLogger(CamionModel.class);
    }

    // Méthode permettant d'obtenir la liste de tous les camions présent dans la BDD de l'EM
    public void obtenirListeCamionsBDD(String urlApi) {
        this.api = new DialogueExterneAPI(urlApi);

        // Récupération de tous les camions de la base de données
        this.json = this.api.getDonnees("camions");
        logger.info("[obtenirListeCamionsBDD()] JSON Reçu : " + json);

        // Création des objets Camion
        for (int i = 0; i < json.length(); i++) {
            listeCamions.add(new Camion(this.json.getJSONObject(i)));
            logger.info("[obtenirListeCamionsBDD()] Camion récupéré : \n" + listeCamions.get(i).toString());
        }
    }

    // Méthode permettant de déplacer un camion dans le simulateur et mettre à jour les coordonnées dans la BDD de l'EM
    public void deplacerCamion(String urlApi, int idCamion, float xDest, float yDest) {

        this.api = new DialogueExterneAPI(urlApi);
        logger.info("[deplacerCamion()] Coordonnées actuelles: [" + listeCamions.get(idCamion).getX() + "," + listeCamions.get(idCamion).getY() + "]");

        // Mise à jour des camions
        listeCamions.get(idCamion).setX(xDest);
        listeCamions.get(idCamion).setY(yDest);
        logger.info("[deplacerCamion()] Nouvelle coordonnées: [" + listeCamions.get(idCamion).getX() + "," + listeCamions.get(idCamion).getY() + "]");

        // Mise à jour dans la BDD de l'EM
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(listeCamions.get(idCamion).toJson());

        this.api.setDonnees("updateCamions", jsonArray);
        logger.info("[deplacerCamion()] Localisation du camion n°" + idCamion + " ajouté dans la base de données");
    }



    public List<Camion> getListeCamions() {
        return listeCamions;
    }
}
