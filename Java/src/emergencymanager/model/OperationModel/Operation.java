package src.emergencymanager.model.OperationModel;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import src.commun.Feu;
import src.emergencymanager.model.CamionModel.Camion;

import java.sql.Timestamp;


public class Operation {
    private final long dateDebut;
    private final long dateFin;

    private final Feu feu;
    private final Camion camion;

    private final Logger logger;

    public Operation(Feu feu, Camion camion) {
        Timestamp time = null;

        this.dateDebut = time.getTime();
        this.dateFin = 0;
        this.feu = feu;
        this.camion = camion;

        logger = Logger.getLogger(Operation.class);
    }

    public long getDateDebut() {
        return dateDebut;
    }

    public long getDateFin() {
        return dateFin;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("id_feu", this.feu.getIdFeu());
        json.put("id_camion", this.camion.getId_camion());
        json.put("id_equipe", 0);
        json.put("debut", this.dateDebut);
        json.put("fin", this.dateFin);

        return json;
    }

    @Override
    public String toString() {
        return "\nOperation {" +
                "\n\tdateDebut=" + dateDebut +
                "\n\tdateFin=" + dateFin +
                "\n\tfeu=" + feu +
                "\n\tcamion=" + camion +
                "\n}";
    }
}
