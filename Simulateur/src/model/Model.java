/**
 * Classe : Model
 * Cette classe gère toutes les données du simulateur
 *
 * @version dev
 * @author Charles BOYER - 4IRC ~ CPE Lyon
 */

package model;

import commun.Capteurs;
import commun.DialogueExterneAPI;
import commun.Feu;

import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Model
{
    private List<Capteurs> collectionCapteurs;
    private List<Feu> collectionFeu;
    private List<Camion> collectionCamion;
    private DialogueExterneAPI api;

    /**
     * Constructeur par défaut de la classe Model
     *
     * @author Charles BOYER
     */
    public Model() {
        collectionCapteurs = null;
        collectionFeu = null;
        collectionCamion = null;
        api = null;
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
     * @param       nbCapteurs Le nombre de capteurs à instancier
     *
     * @author Charles BOYER
     * TODO : Faire la méthode
     */
    public void initialiserCapteursBDD(int nbCapteurs) {
        for (int i = 0; i < nbCapteurs; i++) {
            collectionCapteurs.set(i, new Capteurs());
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
