/**
 * Classe : Launcher
 * Cette classe permet de lancer le simulateur
 *
 * @version dev
 * @author Charles BOYER - 4IRC ~ CPE Lyon
 */

package launcher;

import controller.Controller;

public class Launcher {

    // Cette méthode permet de lancer le simulateur
    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.initialiserSimulation();
    }
}