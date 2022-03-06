package src.emergencymanager.model.FeuModel;

import org.apache.log4j.Logger;
import org.json.JSONArray;

import src.commun.api.DialogueExterneAPI;
import src.commun.Capteur;
import src.commun.Feu;

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
        logger.info("[obtenirFeuxBDD()] JSON Reçu : \n" + json);

        // Création des objets Feu
        for (int i = 0; i < json.length(); i++) {
            for(int j = 0; j < capteurs.size(); j++) {
                if (capteurs.get(j).getIdCapteur() == json.getJSONObject(i).getInt("id_capteur")) {
                    feux.add(new Feu(this.json.getJSONObject(i), capteurs.get(j)));
                    logger.info("[obtenirFeuxBDD()] Camion récupéré : \n" + feux.get(i).toString());
                }
            }
        }
    }

    // Méthode générant un feu aux coordonnées du capteur en paramètre et l'ajoute en base de données
    public Feu genererFeu(String urlApi, int id_capteur, int intensite, double x, double y) {
        int codeRetour = -1;
        JSONArray jsonArray = new JSONArray();
        Feu feu = null;

        // Génération d'un feu au niveau du capteur
        if (feux.size() == 0) {
            feu = new Feu(0, id_capteur,0, intensite, x, y);
        } else {
            feu = new Feu(feux.size(), id_capteur, 0, intensite, x, y);
        }

        logger.info("Feu généré : " + feu);
        feux.add(feu);

        // Ajout dans le JSON
        jsonArray.put(feu.toJson());

        // Envoi à l'API
        this.api = new DialogueExterneAPI(urlApi);
        logger.info("JSON complet : \n" + jsonArray);
        codeRetour = this.api.setDonnees("feux", jsonArray);
        if (codeRetour == 201) {
            logger.info("Feu inséré dans la base");
        } else {
            logger.error("Insertion dans la base");
        }

        return feu;
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

    public List<Feu> getFeux() {
        return feux;
    }
}
