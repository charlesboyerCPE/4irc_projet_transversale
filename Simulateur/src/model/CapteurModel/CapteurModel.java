package model.CapteurModel;

import commun.Capteur;
import commun.api.DialogueExterneAPI;
import model.Model;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class CapteurModel extends Model {

    private List<Capteur> listeCapteurs;
    private final Logger logger;

    public CapteurModel() {
        listeCapteurs = new ArrayList<Capteur>();
        logger = Logger.getLogger(String.valueOf(CapteurModel.class));
    }

    // Méthode permettant de mettre à jour un capteur dans la BDD
    public void majCapteur(String urlApi, int id) {
        this.api = new DialogueExterneAPI(urlApi);

        // Parcours de la liste de capteurs
        for (Capteur entree : listeCapteurs) {
            if (entree.getId() == id) {
                logger.info("Capteur n°" + id + " présent");

                // Remplissage du JSON
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(entree.toJson());

                // Envoi des données
                api.setDonnees("capteur/" + id, jsonArray);
                logger.info("Capteur n°" + id + " ajouté dans la base de données");
            }
        }
    }

    public void LAURINE(String url) {
        DialogueExterneAPI api = new DialogueExterneAPI(url);
        String test = "[{\"id_capteur\":\"4\",\"perimetre\":\"7\",\"coordonnee_x\":\"5\",\"intensite\":\"5\",\"coordonnee_y\":\"5\"}]";
        JSONArray tabJson = new JSONArray(test);

        api.setDonnees(url, tabJson);
    }

    public void supprimerCapteur(String urlApi, int id) {
        int codeRetour = -1;

        this.api = new DialogueExterneAPI(urlApi);

        codeRetour = this.api.deleteDonnees("capteur/" + id);
        if(codeRetour == 200) {
            logger.info("Capteur n°" + id + " supprimé");
        } else {
            logger.info("Erreur : Capteur n°" + id + " non supprimé");
        }
    }

    // Méthode permettant d'obtenir tous les capteurs présent dans la BDD de simulation
    public void obtenirListeCapteursBDD(String urlApi) {
        this.api = new DialogueExterneAPI(urlApi);

        // Récupération de tous les capteurs de la base de données
        this.json = this.api.getDonnees("capteurs");
        logger.info("JSON Reçu : " + json);

        // Création des objets Capteurs
        for (int i = 0; i < json.length(); i++) {
            listeCapteurs.add(new Capteur(this.json.getJSONObject(i)));
            logger.info("Capteurs récupéré : \n" + listeCapteurs.get(i).toString());
        }
    }

    // Méthode permettant de créer nbCapteur capteurs, de les ajouter à la liste de capteurs et de l'ajouter en BDD
    public void creerCapteurs(String urlApi, int nbCapteurs) {
        Random random = new Random();
        JSONArray jsonArray = new JSONArray();

        for (int i = 0; i < nbCapteurs; i++) {
            // Ajout dans la liste
            Capteur capteur = new Capteur (
                    i,
                    true,
                    0,
                    10,
                    random.nextInt(9),
                    random.nextInt(9)
            );
            listeCapteurs.add(capteur);

            // Envoi à la base de données
            jsonArray.put(capteur.toJson());
            this.api = new DialogueExterneAPI(urlApi);
            this.api.setDonnees("capteurs", jsonArray);
        }
    }


    public List<Capteur> getListeCapteurs() {
        return listeCapteurs;
    }
}
