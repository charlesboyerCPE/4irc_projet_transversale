package src.emergencymanager.model.CaserneModel;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import src.commun.api.DialogueExterneAPI;
import src.emergencymanager.model.PompierModel.Pompier;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class CaserneModel {
    private final List<Caserne> casernes;
    private DialogueExterneAPI api;
    private JSONArray json;

    private final Logger logger;

    public CaserneModel() {
        this.casernes = new ArrayList<Caserne>();
        this.api = null;
        this.json = new JSONArray();

        this.logger = Logger.getLogger(CaserneModel.class);
    }

    // Méthode permettant d'initialiser la liste des casernes
    public void initialiserCasernes(String urlApi, int nbCasernes, List<Pompier> pompiers) {
        int codeRetour = -1;

        this.api = new DialogueExterneAPI(urlApi);

        if (casernes.size() == 0) {
            // Création des casernes
            for(int i = 0; i < nbCasernes; i++) {
                this.casernes.add(new Caserne(i, pompiers, "CPE Lyon", 45.3,4.8));
                logger.info("[initialiserCasernes()] - Caserne créer:" + this.casernes.get(i));
                this.json.put(this.casernes.get(i).toJson());
            }

            logger.info("[initialiserCasernes()] - JSON créer:\n" + this.json);

            // MaJ de la base de données
            codeRetour = this.api.setDonnees("casernes", this.json);
            if(codeRetour == HttpURLConnection.HTTP_CREATED) {
                logger.info("[initialiserCasernes()] - Casernes ajouter dans la base");
            } else {
                casernes.clear();
                logger.error("[initialiserCasernes()] - Liste vidé");
            }
        } else {
            logger.info("[initialiserCasernes()] - Casernes déjà initialisées");
        }
    }

    // Méthode permettant de récupérer les casernes présentes en base de données
    public void obtenirCasernesBDD(String urlApi, List<Pompier> pompiers) {
        this.api = new DialogueExterneAPI(urlApi);

        // Récupération de toutes les casernes de la base de données
        this.json = this.api.getDonnees("casernes");
        logger.info("[obtenirCasernesBDD()] - JSON Reçu : " + json);

        // Création des objets Pompiers
        for (int i = 0; i < json.length(); i++) {
            casernes.add(new Caserne(this.json.getJSONObject(i), pompiers));
            logger.info("[obtenirCasernesBDD()] - Caserne récupéré : \n" + casernes.get(i).toString());
        }
    }

    // Méthode permettant de supprimer une caserne
    public void supprimerCaserne(String urlApi, int id_caserne) {
        int codeRetour = -1;

        this.api = new DialogueExterneAPI(urlApi);

        // Suppression de la caserne
        for (int i = 0; i < casernes.size(); i++) {
            if (casernes.get(i).getId_caserne() == id_caserne) {
                casernes.remove(i);
            }
        }

        // MaJ de la base de données
        codeRetour = this.api.deleteDonnees("caserne/" + id_caserne);
        if(codeRetour == 200) {
            logger.info("[supprimerCaserne()] - Caserne n°" + id_caserne + " supprimé");
        } else {
            logger.error("[supprimerCaserne()] - Caserne n°" + id_caserne + " non supprimé");
        }
    }

    // Méthode permettant d'ajouter un pompier dans la caserne
    public void ajouterPompier(String urlApi, Pompier pompier) {
        this.api = new DialogueExterneAPI(urlApi);

        // Récupération de pompiers

    }

    public List<Caserne> getCasernes() {
        return casernes;
    }
}
