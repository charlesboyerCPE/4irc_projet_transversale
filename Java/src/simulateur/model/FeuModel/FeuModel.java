package src.simulateur.model.FeuModel;

import src.commun.Capteur;
import src.commun.Feu;
import src.commun.api.DialogueExterneAPI;
import src.simulateur.model.Model;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

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

        // Génération d'un feu au niveau du capteur
        Feu feu = new Feu(listeFeux.size() + 1, 0, 1, capteur.getX(), capteur.getY(), capteur);
        logger.info("Feu généré : " + feu);
        listeFeux.add(feu);

        // Ajout dans le JSON
        jsonArray.put(feu.toJson());

        // Envoi à l'API
        this.api = new DialogueExterneAPI(urlApi);
        codeRetour = this.api.setDonnees("feu/" + feu.getIdFeu(), jsonArray);
        if (codeRetour == 201) {
            logger.info("Feu inséré dans la base");
        } else {
            logger.info("Erreur lors de l'insertion dans la base");
        }
    }

    public List<Feu> getListeFeux() {
        return listeFeux;
    }
}
