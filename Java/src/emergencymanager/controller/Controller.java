package src.emergencymanager.controller;

import org.apache.log4j.Logger;

import src.commun.utils.GlobalProperties;

import src.emergencymanager.model.CamionModel.CamionModel;
import src.emergencymanager.model.CaserneModel.CaserneModel;
import src.emergencymanager.model.OperationModel.Operation;
import src.emergencymanager.model.PompierModel.PompierModel;

import src.simulateur.model.CapteurModel.CapteurModel;

import java.util.List;

public class Controller {
    private final PompierModel pompierModel;
    private final CapteurModel capteurModel;
    private final CamionModel camionModel;
    private final CaserneModel caserneModel;

    private List<Operation> listeOperation;

    private final Logger logger;

    public Controller() {
        this.capteurModel = new CapteurModel();
        this.pompierModel = new PompierModel();
        this.camionModel  = new CamionModel();
        this.caserneModel = new CaserneModel();

        logger = Logger.getLogger(Controller.class);
    }

    public void initialiserEM() {
        capteurModel.obtenircapteursBDD(GlobalProperties.API_EM);

        caserneModel.initialiserCasernes(GlobalProperties.API_EM, 2, null);
        //camionModel.obtenirCamionBDD(GlobalProperties.API_EM, caserneModel.getCasernes());
        camionModel.initialiserCamions(GlobalProperties.API_EM, caserneModel.getCasernes().get(0));
        camionModel.definirDestination(GlobalProperties.API_EM, camionModel.getCamions().get(0).getId_camion(), 45.9, 5);
        camionModel.supprimerCamion(GlobalProperties.API_EM, 0);
    }
}
