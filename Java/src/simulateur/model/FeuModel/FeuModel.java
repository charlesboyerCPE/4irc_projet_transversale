package src.simulateur.model.FeuModel;

import src.commun.Capteur;
import src.commun.Feu;
import src.commun.api.DialogueExterneAPI;

import org.json.JSONArray;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;


public class FeuModel {
    private List<Feu> feux;

    private DialogueExterneAPI api;
    private JSONArray json;

    private final Logger logger;

    public FeuModel() {
        feux = new ArrayList<>();
        this.api = null;
        this.json = new JSONArray();

        logger = Logger.getLogger(String.valueOf(FeuModel.class));
    }

    // Méthode permettant d'obtenir les feux de la base de données
    public void obtenirFeuxBDD(String urlApi, List<Capteur> capteurs) {
        this.api = new DialogueExterneAPI(urlApi);

        // Récupération de tous les feux de la base de données
        this.json = this.api.getDonnees("feux");

        // Création des objets Feu
        for (int i = 0; i < json.length(); i++) {
            for(int j = 0; j < capteurs.size(); j++) {
                if (capteurs.get(j).getIdCapteur() == json.getJSONObject(i).getInt("id_capteur")) {
                    feux.add(new Feu(this.json.getJSONObject(i), capteurs.get(j)));
                }
            }
        }

        logger.info(feux.size() + " feu récupérés");
    }

    // Méthode générant un feu aux coordonnées du capteur en paramètre et l'ajoute en base de données
    public void genererFeu(String urlApi, int id_capteur, int intensite, double x, double y) {
        int codeRetour = -1;
        JSONArray jsonArray = new JSONArray();
        Feu feu = null;

        // Génération d'un feu au niveau du capteur
        if (feux.size() == 0) {
            feu = new Feu(0, id_capteur,0, intensite, x, y);
        } else {
            feu = new Feu(feux.size(), id_capteur, 0, intensite, x, y);
        }

        feux.add(feu);
        logger.info("Feu généré au capteur id n°: " + feu.getId_capteur());

        // Ajout dans le JSON
        jsonArray.put(feu.toJson());

        // Envoi à l'API
        this.api = new DialogueExterneAPI(urlApi);
        codeRetour = api.setDonnees("feux", jsonArray);
        if (codeRetour == 201) {
            logger.info("Feu inséré dans la base");
        } else {
            logger.error("Insertion dans la base");
        }
    }

    // Méthode permettant de supprimer un feu de la simulation
    public void supprimerFeu(String urlApi, int idFeu) {
        int codeRetour = -1;

        this.api = new DialogueExterneAPI(urlApi);
        
        for(int i = 0; i < feux.size(); i++) {
            if (feux.get(i).getIdFeu() == idFeu) {
                feux.remove(i);
            }
        }

        codeRetour = this.api.deleteDonnees("feu/" + idFeu);
        if(codeRetour == 200) {
            logger.info("Feu n°" + idFeu + " supprimé");
        } else {
            logger.error("Feu n°" + idFeu + " non supprimé");
        }
    }

    // Méthode permettant de baisser/augmenter l'intensité d'un feu de une unité
    public void modificationIntensite(int mode, String urlApi, int idFeu) {
        int codeRetour = -1;
        JSONArray jsonArray = new JSONArray();

        // Modification intensité
        switch (mode) {
            case 1 -> { // Augmentation
                feux.get(idFeu).setIntensite(feux.get(idFeu).getIntensite() + 1);
                logger.info("Intensité du Feu n°" + idFeu + " augmenté : " + feux.get(idFeu).getIntensite());
            }
            case 2 -> { // Diminution
                feux.get(idFeu).setIntensite(feux.get(idFeu).getIntensite() - 1);
                logger.info("Intensité du Feu n°" + idFeu + " baissé : " + feux.get(idFeu).getIntensite());
            }
        }

        // Ajout dans le JSON
        jsonArray.put(feux.get(idFeu).toJson());

        // Envoi à la base de données
        this.api = new DialogueExterneAPI(urlApi);
        codeRetour = this.api.setDonnees("feux", jsonArray);
        if(codeRetour == 201) {
            logger.info("Intensité du Feu n°" + idFeu + " modifié dans la base");
        } else {
            logger.error("Inténsité du Feu n°" + idFeu + " non modifié dans la base");
        }
    }

    public List<Feu> getFeux() {
        return feux;
    }
}
