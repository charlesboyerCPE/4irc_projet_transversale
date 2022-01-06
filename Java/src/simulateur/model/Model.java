/**
 * Classe : Model
 * Cette classe gère toutes les données du simulateur
 *
 * @version dev
 * @author Charles BOYER - 4IRC ~ CPE Lyon
 */

package src.simulateur.model;

import src.commun.api.DialogueExterneAPI;

import src.commun.utils.GlobalProperties;
import org.json.JSONArray;

import java.util.List;
import java.util.logging.Logger;

public class Model
{

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
        this.listeCamions = null;

        this.api = null;
        this.logger = Logger.getLogger(String.valueOf(Model.class));
    }


    public void genererFeu() {

    }

    public void attribuerCamion() {

    }
}
