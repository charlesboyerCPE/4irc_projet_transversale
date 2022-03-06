package src.simulateur.model.CapteurModel;

import src.commun.Capteur;
import src.commun.api.DialogueExterneAPI;

import org.json.JSONArray;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
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

        logger = Logger.getLogger(String.valueOf(CapteurModel.class));
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
    public void obtenirCapteursBDD(String urlApi) {
        this.api = new DialogueExterneAPI(urlApi);

        // Récupération de tous les capteurs de la base de données
        this.json = this.api.getDonnees("capteurs");

        // Création des objets Capteurs
        for (int i = 0; i < json.length(); i++) {
            capteurs.add(new Capteur(this.json.getJSONObject(i)));
        }
        logger.info(capteurs.size() + " capteurs recupérés");
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
            logger.info("Capteurs créer : \n" + capteur);
        }

        // Envoi à la base de données
        logger.info("JSON complet : \n" + jsonArray);
        this.api = new DialogueExterneAPI(urlApi);
        codeRetour = this.api.setDonnees("capteurs", jsonArray);
        if (codeRetour == 201) {
            logger.info("Liste de capteurs inséré dans la base");
        } else {
            logger.info("Erreur lors de l'insertion dans la base");
        }
    }

    // Méthode permettant de modifier l'intensité d'un capteur
    public void modificationIntensite(int mode, String urlApi, int id_capteur) {
        int codeRetour = -1;
        JSONArray jsonArray = new JSONArray();

        switch (mode) {
            case 1 -> { // Augmentation
                capteurs.get(id_capteur).setIntensite(capteurs.get(id_capteur).getIntensite() + 1);
                logger.info("Intensité du Capteur n°" + id_capteur + " augmenté : " + capteurs.get(id_capteur).getIntensite());
            }
            case 2 -> { // Diminution
                capteurs.get(id_capteur).setIntensite(capteurs.get(id_capteur).getIntensite() - 1);
                logger.info("Intensité du Capteur n°" + id_capteur + " baissé: " + capteurs.get(id_capteur).getIntensite());
            }
        }

        // Ajout dans le JSON
        jsonArray.put(capteurs.get(id_capteur).toJson());

        // Envoi à la base de données
        this.api = new DialogueExterneAPI(urlApi);
        codeRetour = this.api.setDonnees("capteurs", jsonArray);
        if(codeRetour == 201) {
            logger.info("Intensité du Capteur n°" + id_capteur + " modifié en base");
        } else {
            logger.error("Inténsité du Capteur n°" + id_capteur + " non modifié en base");
        }
    }

    public List<Capteur> getCapteurs() {
        return capteurs;
    }
}
