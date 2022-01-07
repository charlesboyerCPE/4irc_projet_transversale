/**
 * Classe : Controller
 * Cette classe gère le simulateur
 *
 * @version dev
 * @author Charles BOYER - 4IRC ~ CPE Lyon
 */

package src.simulateur.controller;


import src.commun.utils.GlobalProperties;
import src.simulateur.model.CapteurModel.CapteurModel;
import src.simulateur.model.FeuModel.FeuModel;

import org.apache.log4j.Logger;

public class Controller {
    private CapteurModel capteurModel;
    private FeuModel feuModel;

    // Constructeur de la classe Controller
    public Controller() {
        this.capteurModel = new CapteurModel();
        this.feuModel = new FeuModel();
        Logger logger = Logger.getLogger(Controller.class);
    }

    // Méthode permettant d'initialiser la simulation
    public void initialiserSimulation() {
        this.capteurModel.obtenirListeCapteursBDD(GlobalProperties.API_SIMU);
        /*for(int i = 0; i < capteurModel.getListeCapteurs().size(); i++) {
            capteurModel.supprimerCapteur(GlobalProperties.API_EM, i);
        }*/
        capteurModel.creerCapteurs(GlobalProperties.API_SIMU, 4);

        this.feuModel.genererFeu(GlobalProperties.API_SIMU, this.capteurModel.getListeCapteurs().get(2));
        this.feuModel.modificationIntensite(1, GlobalProperties.API_SIMU, 0);
    }
}