package src.simulateur.model.CamionModel;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import src.commun.Coordonnees;

public class Camion {
    private int id_camion;
    private final String type;
    private Coordonnees coord;
    private Coordonnees coordDestination;

    private final Logger logger;

    public Camion(int id_camion, String type, int x, int y) {
        this.id_camion = id_camion;
        this.type = type;
        this.coord = new Coordonnees(x, y);
        this.coordDestination = null;
        logger = Logger.getLogger(Camion.class);
    }

    public Camion(JSONObject json) {
        this.id_camion = json.getInt("id_camion");
        this.type = json.getString("type_produit");
        this.coord = new Coordonnees(json.getFloat("coordonnee_x"), json.getFloat("coordonnee_y"));
        this.coordDestination = new Coordonnees(json.getFloat("coordonnee_dest_x"), json.getFloat("coordonnee_dest_y"));
        logger = Logger.getLogger(Camion.class);
    }

    public int getId_camion() {
        return id_camion;
    }

    public String getType() {
        return this.type;
    }

    public double getX() {
        return this.coord.getX();
    }

    public double getY() {
        return this.coord.getY();
    }

    public void setX(float x) {
        this.coord.setX(x);
    }

    public void setY(float y) {
        this.coord.setY(y);
    }

    public double getDestX() {
        return this.coordDestination.getX();
    }

    public double getDestY() {
        return this.coordDestination.getY();
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("id_camion", this.getId_camion());
        json.put("type_produit", this.getType());
        json.put("coordonnee_x", this.getX());
        json.put("coordonnee_y", this.getY());

        logger.info("JSON créer : " + json);

        return json;
    }

    @Override
    public String toString() {
        return "\nCamion {" +
                "\n\tid_camion=" + id_camion +
                "\n\ttype='" + type + '\'' +
                "\n\tcoord=" + coord +
                "\n\tcoordDestination=" + coordDestination +
                "\n}";
    }
}

