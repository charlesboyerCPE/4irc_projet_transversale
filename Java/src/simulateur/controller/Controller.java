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

        /* TEST */
        int xDest = 0;
        int yDest = 0;
        this.capteurModel.obtenirListeCapteursBDD(GlobalProperties.API_SIMU);
        this.feuModel.genererFeu(GlobalProperties.API_SIMU, this.capteurModel.getListeCapteurs().get(0));

        this.camionModel.obtenirListeCamionsBDD(GlobalProperties.API_EM);

        // MEP coord dest
        this.camionModel.getListeCamions().get(0).setDestX(47);
        this.camionModel.getListeCamions().get(0).setDestY(7);

        try {
            Thread.sleep(1000);
            /*
                    this.camionModel.deplacerCamion(
                    GlobalProperties.API_EM,
                    camionModel.getListeCamions().get(0).getId_camion(),
                    feuModel.getListeFeux().get(0).getX() += xDest,
                    feuModel.getListeFeux().get(0).getY() += yDest
                    );
            */
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}