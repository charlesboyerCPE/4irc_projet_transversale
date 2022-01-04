package model;

import commun.Capteur;
import commun.api.DialogueExterneAPI;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class CapteurModel extends Model {

    private List<Capteur> listeCapteurs;
    private final Logger logger;


    public CapteurModel() {
        listeCapteurs = new ArrayList<Capteur>();
        logger = Logger.getLogger(String.valueOf(CapteurModel.class));
    }

    // Méthode permettant de mettre à jour un capteur dans la BDD
    public void majCapteur(String url, int id) {

    }

    public void LAURINE(String url) {
        DialogueExterneAPI api = new DialogueExterneAPI(url);
        String test = "[{\"id_capteur\":\"4\",\"perimetre\":\"7\",\"coordonnee_x\":\"5\",\"intensite\":\"5\",\"coordonnee_y\":\"5\"}]";
        JSONArray tabJson = new JSONArray(test);

        api.setDonnees("capteurs", tabJson);
    }

    public void supprimerCapteurs(int id) {

    }

    // Méthode permettant d'obtenir tous les capteurs présent dans la BDD de simulation
    public void obtenirListeCapteurs(String urlApi) {
        JSONArray json;

        DialogueExterneAPI api = new DialogueExterneAPI(urlApi);

        // Récupération de tous les capteurs de la base de données
        json = api.getDonnees("capteurs");
        logger.info("JSON Reçu : " + json);

        // Création des objets Capteurs
        for (int i = 0; i < json.length(); i++) {
            listeCapteurs.add(new Capteur(json.getJSONObject(i)));
            logger.info("Capteurs récupéré : \n" + listeCapteurs.get(i).toString());
        }
    }

    public void creerCapteurs() {
    }
}
