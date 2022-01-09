/**
 * Classe : Controller
 * Cette classe gère le simulateur
 *
 * @version dev
 * @author Charles BOYER - 4IRC ~ CPE Lyon
 */

package src.simulateur.controller;


import src.commun.utils.GlobalProperties;
import src.simulateur.model.CamionModel.CamionModel;
import src.simulateur.model.CapteurModel.CapteurModel;
import src.simulateur.model.FeuModel.FeuModel;

import org.apache.log4j.Logger;

public class Controller {
    private CapteurModel capteurModel;
    private FeuModel feuModel;
    private CamionModel camionModel;

    // Constructeur de la classe Controller
    public Controller() {
        this.capteurModel = new CapteurModel();
        this.feuModel = new FeuModel();
        this.camionModel = new CamionModel();

        Logger logger = Logger.getLogger(Controller.class);
    }

    // Méthode permettant d'initialiser la simulation
    public void initialiserSimulation() {
        capteurModel.obtenircapteursBDD(GlobalProperties.API_SIMU_LOCAL);
        feuModel.genererFeu(GlobalProperties.API_SIMU_LOCAL, capteurModel.getcapteurs().get(0));
    }
}