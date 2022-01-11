package src.emergencymanager.model.IncidentModel;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import src.commun.Feu;
import src.emergencymanager.model.CamionModel.Camion;


    public class Incident {
    private final Feu feu;
    private final Camion camion;

    private final Logger logger;

    public Incident(Feu feu) {
        this.feu = feu;
        this.camion = null;
        logger = Logger.getLogger(Incident.class);
    }

    public Incident(Feu feu, Camion camion) {

        this.feu = feu;
        this.camion = camion;

        logger = Logger.getLogger(Incident.class);
    }

    public Incident(JSONObject json, Feu feu, Camion camion) {
        this.feu = feu;
        this.camion = camion;

        logger = Logger.getLogger(Incident.class);

    }

    public Feu getFeu() {
        return feu;
    }

    public Camion getCamion() {
        return camion;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("id_feu", this.feu.getIdFeu());
        if (this.camion != null) {
            json.put("id_camion", this.camion.getId_camion());
        }
        json.put("id_equipe", 0);

        return json;
    }

    @Override
    public String toString() {
        return "\nOperation {" +
                "\n\tid_feu=" + feu+
                "\n\tid_camion=" + camion +
                "\n}";
    }
}
