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

public class Controller {
    private CapteurModel capteurModel;
    private FeuModel feuModel;

    // Constructeur de la classe Controller
    public Controller() {
        super();
        this.capteurModel = new CapteurModel();
        this.feuModel = new FeuModel();
    }

    // Méthode permettant d'initialiser la simulation
    public void initialiserSimulation() {
        this.capteurModel.obtenirListeCapteursBDD(GlobalProperties.API_SIMU);
        this.feuModel.genererFeu(GlobalProperties.API_SIMU, this.capteurModel.getListeCapteurs().get(2));
    }
}