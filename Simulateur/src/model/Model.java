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

import commun.utils.GlobalProperties;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

public class Model
{
    private List<Feu> listeFeux;
    private List<Camion> listeCamions;

    protected DialogueExterneAPI api;
    protected JSONArray json;

    private GlobalProperties globalProperties;

    private final Logger logger;

    /**
     * Constructeur par défaut de la classe Model
     *
     * @author Charles BOYER
     */
    public Model() {
        this.listeFeux = new ArrayList<Feu>();
        this.listeCamions = null;

        this.api = null;
        this.logger = Logger.getLogger(String.valueOf(Model.class));
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
