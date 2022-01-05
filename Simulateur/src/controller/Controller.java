/**
 * Classe : Controller
 * Cette classe gère le simulateur
 *
 * @version dev
 * @author Charles BOYER - 4IRC ~ CPE Lyon
 */

package controller;


import commun.utils.GlobalProperties;
import model.CapteurModel.CapteurModel;

public class Controller {
    private CapteurModel capteurModel;

    // Constructeur de la classe Controller
    public Controller() {
        super();
        this.capteurModel = new CapteurModel();
    }

    // Méthode permettant d'initialiser la simulation
    public void initialiserSimulation() {
        capteurModel.creerCapteurs(GlobalProperties.API_SIMU, 10);
    }
}