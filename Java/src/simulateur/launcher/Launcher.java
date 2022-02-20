package src.simulateur.launcher;

import org.apache.log4j.PropertyConfigurator;
import src.simulateur.controller.Controller;


public class Launcher {
    public static void main(String[] args) {
        PropertyConfigurator.configure("conf/log4j.properties");

        long delta = 50000; // 5sec

        //Timer timer = new Timer();
        Controller controller = new Controller();

        //timer.schedule(controller, 0, delta);
        controller.initialiserSimulation();
    }
}