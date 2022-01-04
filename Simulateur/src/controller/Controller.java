/**
 * Classe : Controller
 * Cette classe gère le simulateur
 *
 * @version dev
 * @author Charles BOYER - 4IRC ~ CPE Lyon
 */

package controller;

import model.Model;

public class Controller {
    private Model model;

    // Constructeur de la classe Controller
    public Controller() {
        super();
        this.model = new Model();
    }

    // Méthode permettant d'initialiser la simulation
    public void initialiserSimulation() {
        model.initialiserCapteursBDD();
    }
}