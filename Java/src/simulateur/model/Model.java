/**
 * Classe : Model
 * Cette classe gère toutes les données du simulateur
 *
 * @version dev
 * @author Charles BOYER - 4IRC ~ CPE Lyon
 */

package src.simulateur.model;

import src.commun.api.DialogueExterneAPI;
import org.json.JSONArray;
import java.util.logging.Logger;

public class Model
{
    protected DialogueExterneAPI api;
    protected JSONArray json;
    private final Logger logger;

    public Model() {

        this.api = null;
        this.logger = Logger.getLogger(String.valueOf(Model.class));
    }


    public void genererFeu() {

    }

    public void attribuerCamion() {

    }
}
