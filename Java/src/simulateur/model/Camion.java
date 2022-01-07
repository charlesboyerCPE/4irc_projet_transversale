package src.simulateur.model;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import src.commun.Coordonnees;

public class Camion {
    private int id_camion;
    private final String type;
    private Coordonnees coord;
    private Coordonnees coordDestination;

    private final Logger logger;

    public Camion(int id_camion, String type, int x, int y, int xDest, int yDest) {
        this.id_camion = id_camion;
        this.type = type;
        this.coord = new Coordonnees(x, y);
        this.coordDestination = new Coordonnees(xDest, yDest);
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

    public float getX() {
        return this.coord.getX();
    }

    public float getY() {
        return this.coord.getY();
    }

    public void setX(float x) {
        this.coord.setX(x);
    }

    public void setY(float y) {
        this.coord.setY(y);
    }

    public float getDestX() {
        return this.coordDestination.getX();
    }

    public float getDestY() {
        return this.coordDestination.getY();
    }

    public void setDestX(float xDest) {
        this.coordDestination.setX(xDest);
    }

    public void setDestY(float yDest) {
        this.coordDestination.setY(yDest);
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

