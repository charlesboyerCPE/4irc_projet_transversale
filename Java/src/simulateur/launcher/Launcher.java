/**
 * Classe : Launcher
 * Cette classe permet de lancer le simulateur
 *
 * @version dev
 * @author Charles BOYER - 4IRC ~ CPE Lyon
 */

package src.simulateur.launcher;

import org.apache.log4j.PropertyConfigurator;
import src.simulateur.controller.Controller;

public class Launcher {

    // Cette méthode permet de lancer le simulateur
    public static void main(String[] args) {
        PropertyConfigurator.configure("conf/log4j.properties");

        Controller controller = new Controller();
        controller.initialiserSimulation();
    }
}