package src.emergencymanager.model.CapteurModel;
import src.commun.Capteur;

import org.json.JSONArray;
import org.apache.log4j.Logger;
import src.commun.api.DialogueExterneAPI;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class CapteurModel {
    private List<Capteur> capteurs;

    private DialogueExterneAPI api;
    private JSONArray json;
    private final Logger logger;

    public CapteurModel() {
        capteurs = new ArrayList<Capteur>();

        this.api = null;
        this.json = new JSONArray();

        logger = Logger.getLogger(String.valueOf(src.simulateur.model.CapteurModel.CapteurModel.class));
    }

    // Méthode permettant de mettre à jour un capteur dans la BDD
    public void majCapteur(String urlApi, int id_capteur) {
        this.api = new DialogueExterneAPI(urlApi);

        // Parcours de la liste de capteurs
        for (Capteur entree : capteurs) {
            if (entree.getIdCapteur() == id_capteur) {
                logger.info("Capteur n°" + id_capteur + " présent");

                // Remplissage du JSON
                JSONArray jsonArray = new JSONArray();
                jsonArray.put(entree.toJson());

                // Envoi des données
                api.setDonnees("capteur/" + id_capteur, jsonArray);
                logger.info("Capteur n°" + id_capteur + " ajouté dans la base de données");
            }
        }
    }

    // Méthode permettant de supprimer un capteur
    public void supprimerCapteur(String urlApi, int id_capteur) {
        int codeRetour = -1;

        this.api = new DialogueExterneAPI(urlApi);

        // Suppression du capteur
        for (int i = 0; i < capteurs.size(); i++) {
            if (capteurs.get(i).getIdCapteur() == id_capteur) {
                capteurs.remove(i);
            }
        }

        // MaJ de la base de données
        codeRetour = this.api.deleteDonnees("capteur/" + id_capteur);
        if(codeRetour == 200) {
            logger.info("Capteur n°" + id_capteur + " supprimé");
        } else {
            logger.info("Erreur : Capteur n°" + id_capteur + " non supprimé");
        }
    }

    // Méthode permettant d'obtenir tous les capteurs présent dans la BDD de simulation
    public void obtenircapteursBDD(String urlApi) {
        int i = 0;
        this.api = new DialogueExterneAPI(urlApi);

        // Récupération de tous les capteurs de la base de données
        this.json = this.api.getDonnees("capteurs");
        logger.info("JSON Reçu : " + json);

        // Création des objets Capteurs
        for (i = 0; i < json.length(); i++) {
            capteurs.add(new Capteur(this.json.getJSONObject(i)));
        }
        logger.info(i + " capteurs récupérés");
    }

    // Méthode permettant de créer nbCapteur capteurs, de les ajouter à la liste de capteurs et de l'ajouter en BDD
    public void creerCapteurs(String urlApi, int nbCapteurs) {
        int codeRetour = -1;
        int i = 0;

        Random random = new Random();
        JSONArray jsonArray = new JSONArray();

        for (i = 0; i < nbCapteurs; i++) {
            // Ajout dans la liste
            logger.info("i= " + i);
            Capteur capteur = new Capteur (
                    i,
                    true,
                    0,
                    10,
                    random.nextInt(9),
                    random.nextInt(9)
            );
            capteurs.add(capteur);
            jsonArray.put(capteur.toJson());
            logger.info("Capteurs créer");
        }

        // Envoi à la base de données
        this.api = new DialogueExterneAPI(urlApi);
        codeRetour = this.api.setDonnees("capteurs", jsonArray);
        if (codeRetour == 201) {
            logger.info("Liste de capteurs inséré dans la base");
        } else {
            logger.info("ERREUR - Liste non inséré dans la base");
            capteurs.clear();
        }
    }

    public List<Capteur> getCapteurs() {
        return capteurs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CapteurModel that = (CapteurModel) o;
        return Objects.equals(capteurs, that.capteurs) && Objects.equals(api, that.api) && Objects.equals(json, that.json) && Objects.equals(logger, that.logger);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capteurs, api, json, logger);
    }
}

