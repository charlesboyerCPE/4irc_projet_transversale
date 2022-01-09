package src.emergencymanager.model.PompierModel;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

import src.commun.api.DialogueExterneAPI;
import src.emergencymanager.model.CaserneModel.Caserne;


public class PompierModel {
    private final List<Pompier> pompiers;
    private DialogueExterneAPI api;
    private JSONArray json;

    private final Logger logger;

    public PompierModel() {
        this.pompiers = new ArrayList<Pompier>();
        this.api = null;
        this.json = null;

        logger = Logger.getLogger(PompierModel.class);
    }

    // Méthode permettant de créer nbPompier pompiers. Les pompiers ne seront affectés à aucune casernes
    public void creerPompier(String urlApi, int nbPompier, Caserne caserne) {
        int codeRetour = -1;

        this.api = new DialogueExterneAPI(urlApi);

        // Création des pompiers
        for(int i = 0; i < nbPompier; i++) {
            this.pompiers.add(new Pompier(i, 0, "Jacquie"));
            logger.info("[creerPompier()] - Pompier créer:" + this.pompiers.get(i));
            this.json.put(this.pompiers.get(i).toJson());
        }

        // MaJ de la base de données
        codeRetour = this.api.setDonnees("pompiers", this.json);
        if(codeRetour == HttpURLConnection.HTTP_CREATED) {
            logger.info("[creerPompier()] - Pompiers ajouter dans la base");
        } else {
            logger.error("[creerPompier()] - Pompiers non ajouter");
        }
    }

    // Méthode permettant de récupéré les pompiers présent dans la base de données
    public void obtenirPompierBDD(String urlApi, List<Caserne> casernes) {
        this.api = new DialogueExterneAPI(urlApi);

        // Récupération de tous les pompiers de la base de données
        this.json = this.api.getDonnees("pompiers");
        logger.info("[obtenirPompierBDD()] - JSON Reçu : " + json);

        // Création des objets Pompiers
        for (int i = 0; i < json.length(); i++) {
            pompiers.add(new Pompier(this.json.getJSONObject(i), casernes));
            logger.info("[obtenirPompierBDD()] - Pompier récupéré : \n" + pompiers.get(i).toString());
        }
    }

    // Méthode permettant de tuer un pompier
    public void tuerPompier(String urlApi, int id_pompier) {
        int codeRetour = -1;

        this.api = new DialogueExterneAPI(urlApi);

        codeRetour = this.api.deleteDonnees("pompier/" + id_pompier);
        if(codeRetour == 200) {
            logger.info("[tuerPompier()] - Pompier n°" + id_pompier + " supprimé");
        } else {
            logger.error("[tuerPompier()] - Pompier n°" + id_pompier + " non supprimé");
        }
    }
}
