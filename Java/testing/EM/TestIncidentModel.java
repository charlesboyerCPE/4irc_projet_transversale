//package testing.EM;
//
//import org.junit.jupiter.api.Test;
//import src.commun.Utils.GlobalProperties;
//import src.emergencymanager.model.CamionModel.CamionModel;
//import src.emergencymanager.model.CapteurModel.CapteurModel;
//import src.emergencymanager.model.CaserneModel.CaserneModel;
//import src.emergencymanager.model.FeuModel.FeuModel;
//import src.emergencymanager.model.IncidentModel.IncidentModel;
//
//public class TestIncidentModel {
//    private IncidentModel incidentModel;
//    private FeuModel feuModel;
//    private CamionModel camionModel;
//    private CapteurModel capteurModel;
//    private CaserneModel caserneModel;
//
//
//    @Test
//    void recupBDD() {
//        this.incidentModel = new IncidentModel();
//        this.camionModel = new CamionModel();
//        this.feuModel = new FeuModel();
//        this.capteurModel = new CapteurModel();
//        this.caserneModel = new CaserneModel();
//
//        capteurModel.obtenircapteursBDD(GlobalProperties.API_EM_LOCAL);
//        feuModel.obtenirFeuxBDD(GlobalProperties.API_EM_LOCAL,capteurModel.getCapteurs());
//        caserneModel.obtenirCasernesBDD(GlobalProperties.API_EM_LOCAL, null);
//        camionModel.obtenirCamionBDD(GlobalProperties.API_EM_LOCAL, caserneModel.getCasernes());
//        incidentModel.obtenirOperationBDD(GlobalProperties.API_EM_LOCAL, feuModel.getFeux(), camionModel.getCamions());
//
//        System.out.println(incidentModel.getOperations());
//    }
//
//    @Test
//    void creationOperation() {
//        recupBDD();
//
//        incidentModel.creerOperation(GlobalProperties.API_EM_LOCAL, feuModel.getFeux().get(0), camionModel.getCamions().get(0));
//        System.out.println(incidentModel.getOperations());
//    }
//}
