package src.emergencymanager.model.CamionModel;

import org.json.JSONArray;
import src.commun.Api.DialogueExterneAPI;

import org.apache.log4j.Logger;
import src.emergencymanager.model.CaserneModel.Caserne;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class CamionModel {
    private final List<Camion> camions;
    private DialogueExterneAPI api;
    private JSONArray json;

    private final Logger logger;

    public CamionModel() {
        this.camions = new ArrayList<Camion>();
        this.api = null;
        this.json = new JSONArray();

        this.logger = Logger.getLogger(CamionModel.class);
    }

    // Méthode permettant d'obtenir tous les camions de la base de données
    public void obtenirCamionBDD(String urlApi, List<Caserne> casernes) {
        this.api = new DialogueExterneAPI(urlApi);

        // Récupération de tous les camions de la base de données
        this.json = this.api.getDonnees("camions");

        // Création des objets Camion
        for (int i = 0; i < json.length(); i++) {
            for (Caserne caserne : casernes) {
                // Vérification de l'id de la caserne
                if (caserne.getId_caserne() == json.getJSONObject(i).getInt("id_caserne")) {
                    camions.add(new Camion(this.json.getJSONObject(i), caserne));
                    logger.info("[obtenirCamionBDD()] Camion récupéré : \n" + camions.get(i).toString());
                }
            }
        }
    }

    // Méthode permettant de créer des camions
    public void creerCamions(String urlApi, Caserne caserne) {
        int codeRetour = -1;
        Camion camion;

        this.api = new DialogueExterneAPI(urlApi);

        if (camions.size() == 0) {
            camion = new Camion(0, 1, 4, "Camion", caserne);
        } else {
            camion = new Camion(camions.size(), 1, 4, "Camion", caserne);
        }

        camions.add(camion);
        this.json.put(camion.toJson());

        // MaJ de la base
        codeRetour = this.api.setDonnees("camions", this.json);
        if (codeRetour == HttpURLConnection.HTTP_CREATED) {
            logger.info("[creerCamions()] - Camion créer : " + camion);
        } else {
            logger.error("[creerCamions()] - Camion non inséré");
            camions.remove(camion);
        }
    }

    // Méthode permettant de supprimer un camion
    public void supprimerCamion(String urlApi, int id_camion) {
        int codeRetour = -1;

        this.api = new DialogueExterneAPI(urlApi);

        // Suppression du camion
        for (int i = 0; i < camions.size(); i++) {
            if (camions.get(i).getId_camion() == id_camion) {
                camions.remove(i);
            }
        }

        // MaJ de la base de données
        codeRetour = this.api.deleteDonnees("camion/" + id_camion);
        if(codeRetour == 200) {
            logger.info("[supprimerCamion()] - Camion n°" + id_camion + " supprimé");
        } else {
            logger.error("[supprimerCamion()] - Camion n°" + id_camion + " non supprimé");
        }
    }

    // Méthode permettant de saisir la destination d'un camion
    public void definirDestination(String urlApi, int id_camion, double xDest, double yDest) {
        int codeRetour = -1;

        this.api = new DialogueExterneAPI(urlApi);

        // Mise à jour destination
        for (Camion camion : camions) {
            if (camion.getId_camion() == id_camion) {

                // Si coordDest pas initialisé
                logger.info("[definirDestination()] - Destination initiale : [" + camion.getCoordDest().getX() + ";" + camion.getCoordDest().getY() + "]");
                camion.setDestX(xDest);
                camion.setDestY(yDest);
                logger.info("[definirDestination()] - Nouvelle destination : [" + camion.getCoordDest().getX() + ";" + camion.getCoordDest().getY() + "]");
                json.put(camion.toJson());
            }
        }

        // MaJ de la base de données
        codeRetour = this.api.setDonnees("camions", json);
        if(codeRetour == HttpURLConnection.HTTP_CREATED) {
            logger.info("[definirDestination()] - Localisation Camion n°" + id_camion + " mise à jour en base");
        } else {
            logger.error("[definirDestination()] - Localisation Camion n°" + id_camion + " non mise à jour en base");
        }
    }

    public List<Camion> getCamions() {
        return camions;
    }
}
