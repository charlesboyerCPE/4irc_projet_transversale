package src.emergencymanager.launcher;

import org.apache.log4j.PropertyConfigurator;
import src.emergencymanager.controller.Controller;

public class Launcher {
    public static void main(String[] args) {
        PropertyConfigurator.configure("conf/log4j.properties");

        Controller controller = new Controller();
        controller.initialiserEM();
    }
}
