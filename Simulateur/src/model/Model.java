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

public class Model
{
    private Collection<Capteurs> collectionCapteurs;
    private Collection<Feu> collectionFeu;
    private Collection<Camion> collectionCamion;
    private DialogueExterneAPI api;

    /**
     * Constructeur par défaut de la classe Model
     *
     * @author Charles BOYER
     */
    public Model() {
        super();
    }

    /**
     * Méthode permettant d'initialiser la Collection de capteurs
     *
     * @return      Ne retourne rien
     * @param       nbCapteurs Le nombre de capteurs à instancier
     *
     * @author Charles BOYER
     */
    public void initialiserCapteurs(int nbCapteurs) {

    }

    /**
     * Méthode permettant d'initialiser la Collection de feux
     *
     * @return      Ne retourne rien
     * @param       nbFeux Le nombre de feux à instancier
     *
     * @author Charles BOYER
     */
    public void initialiserFeu(int nbFeux) {

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
