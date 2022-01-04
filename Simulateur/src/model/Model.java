/**
 * Classe : Model
 * Cette classe gère toutes les données du simulateur
 *
 * @version dev
 * @author Charles BOYER - 4IRC ~ CPE Lyon
 */

package model;

import commun.Capteur;
import commun.api.DialogueExterneAPI;
import commun.Feu;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Model
{
    private List<Capteur> listeCapteurs;
    private List<Feu> listeFeux;
    private List<Camion> listeCamions;
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
        this.listeCapteurs = new ArrayList<Capteur>();
        this.listeFeux = new ArrayList<Feu>();
        this.listeCamions = null;

        //this.capteurModel = new CapteurModel();

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
            listeCapteurs.set(i, new Capteur(random.nextInt(9), random.nextInt(9)));
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
        CapteurModel cm = new CapteurModel();
        cm.LAURINE(URL_API);
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
            listeFeux.set(i, new Feu(random.nextInt(9), random.nextInt(9), random.nextInt(9)));
        }
    }

    /**
     * Méthode permettant d'initialiser la Collection de feux
     * La localisation et l'intensité des feux est aléatoire
     * @return      Ne retourne rien
     * @param       nbFeux Le nombre de feux à instancier
     *
     * @author Charles BOYER
     */
    public void initialiserFeuxBDD(int nbFeux) {
        Random random = new Random();

        for (int i = 0; i < nbFeux; i++) {
            listeFeux.set(i, new Feu(random.nextInt(9), random.nextInt(9), random.nextInt(9)));
        }
    }

    public void identifierFeu() {

    }

    public void attribuerCamion() {

    }
}
