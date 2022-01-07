package src.simulateur.model.FeuModel;

import src.commun.Capteur;
import src.commun.Feu;
import src.commun.api.DialogueExterneAPI;
import src.simulateur.model.Model;

import org.json.JSONArray;
import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.List;


public class FeuModel extends Model {
    private List<Feu> listeFeux;
    private final Logger logger;

    public FeuModel() {
        listeFeux = new ArrayList<>();
        logger = Logger.getLogger(String.valueOf(FeuModel.class));
    }

    // Méthode générant un feu aux coordonnées du capteur en paramètre et l'ajoute en base de donnée
    public void genererFeu(String urlApi, Capteur capteur) {
        int codeRetour = -1;
        JSONArray jsonArray = new JSONArray();
        Feu feu = null;

        // Génération d'un feu au niveau du capteur
        if (listeFeux.size() == 0) {
            feu = new Feu(listeFeux.size(), 0, 1, capteur.getX(), capteur.getY(), capteur);
        } else {
            feu = new Feu(listeFeux.size() + 1, 0, 1, capteur.getX(), capteur.getY(), capteur);
        }

        logger.info("Feu généré : " + feu);
        listeFeux.add(feu);

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
    }

    // Méthode permettant de supprimer un feu de la simulation
    public void supprimerFeu(String urlApi, int idFeu) {
        int codeRetour = -1;

        this.api = new DialogueExterneAPI(urlApi);

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
                listeFeux.get(idFeu).setIntensite(listeFeux.get(idFeu).getIntensite() + 1);
                logger.info("Intensité du Feu n°" + idFeu + " augmenté");
            }
            case 2 -> { // Diminution
                listeFeux.get(idFeu).setIntensite(listeFeux.get(idFeu).getIntensite() - 1);
                logger.info("Intensité du Feu n°" + idFeu + " baissé");
            }
        }

        // Ajout dans le JSON
        jsonArray.put(listeFeux.get(idFeu).toJson());

        // Envoi à la base de données
        this.api = new DialogueExterneAPI(urlApi);
        codeRetour = this.api.setDonnees("feux", jsonArray);
        if(codeRetour == 201) {
            logger.info("Intensité du Feu n°" + idFeu + " modifié");
        } else {
            logger.error("Inténsité du Feu n°" + idFeu + " non modifié");
        }
    }

    public List<Feu> getListeFeux() {
        return listeFeux;
    }
}
