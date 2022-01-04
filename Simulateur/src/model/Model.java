/**
 * Classe : Model
 * Cette classe gère toutes les données du simulateur
 *
 * @version dev
 * @author Charles BOYER - 4IRC ~ CPE Lyon
 */

package model;

import commun.Capteurs;
import commun.Coordonnees;
import commun.api.DialogueExterneAPI;
import commun.Feu;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Model
{
    private List<Capteurs> collectionCapteurs;
    private List<Feu> collectionFeu;
    private List<Camion> collectionCamion;
    private DialogueExterneAPI api;
    private JSONArray json;

    private final String ADRESSE_SERVEUR = "192.168.31.9";
    private final String URL_API = "http://" + ADRESSE_SERVEUR + ":80/4irc_projet_transversale/web/ServeurWebEmergency/api/";
    private final Logger logger;

    /**
     * Constructeur par défaut de la classe Model
     *
     * @author Charles BOYER
     */
    public Model() {
        this.collectionCapteurs = new ArrayList<Capteurs>();
        this.collectionFeu = null;
        this.collectionCamion = null;
        this.api = null;
        this.json = new JSONArray();
        this.logger = Logger.getLogger(String.valueOf(Model.class));
    }

    /**
     * Méthode permettant d'initialiser la Collection de capteurs
     * Les capteurs auront des coordonnées aléatoires avec cette méthode
     *
     * @return      Ne retourne rien
     * @param       nbCapteurs Le nombre de capteurs à instancier
     *
     * @author Charles BOYER
     */
    public void initialiserCapteurs(int nbCapteurs) {
        Random random = new Random();

        for (int i = 0; i < nbCapteurs; i++) {
            collectionCapteurs.set(i, new Capteurs(random.nextInt(9), random.nextInt(9)));
        }
    }

    /**
     * Méthode permettant d'initialiser la Collection de capteurs
     * Les capteurs sont récupérés de la base de données de simulation
     *
     * @return      Ne retourne rien
     *
     * @author Charles BOYER
     * TODO : Faire la méthode
     */
    public void initialiserCapteursBDD() {
        int i = 0;
        int codeRetour = -1;

        this.api = new DialogueExterneAPI(URL_API);
/*
        // Récupération de tous les capteurs de la base de données
        this.json = this.api.getDonnees("capteurs");
        logger.info("JSON Reçu : " + this.json);

        // Création des objets Capteurs
        for (i = 0; i < this.json.length(); i++) {
            collectionCapteurs.add(new Capteurs(this.json.getJSONObject(i)));
            logger.info("Capteurs récupéré : \n" + collectionCapteurs.get(i).toString());
        }
*/
        // TEST PUT
        DialogueExterneAPI api = new DialogueExterneAPI(URL_API);
        String test = "[{\"id_capteur\":\"4\",\"perimetre\":\"7\",\"coordonnee_x\":\"5\",\"intensite\":\"5\",\"coordonnee_y\":\"5\"}]";
        JSONArray tabJson = new JSONArray(test);

        api.setDonnees("capteurs", tabJson);
    }

    /**
     * Méthode permettant d'initialiser la Collection de feux
     * La localisation et l'intensité des feux est aléatoire
     * @return      Ne retourne rien
     * @param       nbFeux Le nombre de feux à instancier
     *
     * @author Charles BOYER
     */
    public void initialiserFeu(int nbFeux) {
        Random random = new Random();

        for (int i = 0; i < nbFeux; i++) {
            collectionFeu.set(i, new Feu(random.nextInt(9), random.nextInt(9), random.nextInt(9)));
        }
    }

    /**
     * Méthode permettant d'initialiser la Collection de camions
     *
     * @return      Ne retourne rien
     * @param       nbCamions Le nombre de camions à instancier
     *
     * @author Charles BOYER
     */
    public void initialiserCamion(int nbCamions) {

    }

    public void identifierFeu() {

    }

    public void attribuerCamion() {

    }
}
