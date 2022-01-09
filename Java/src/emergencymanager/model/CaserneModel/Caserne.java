package src.emergencymanager.model.CaserneModel;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import src.commun.Coordonnees;
import src.emergencymanager.model.PompierModel.Pompier;

import java.util.List;


public class Caserne {
    private final int id_caserne;
    private final String nom_caserne;
    private final Coordonnees coord;

    private List<Pompier> pompiers;

    private final Logger logger;

    public Caserne(int id_caserne, String nom_caserne, List<Pompier> pompiers) {
        this.id_caserne = id_caserne;
        this.pompiers = pompiers;
        this.nom_caserne = nom_caserne;
        this.coord = new Coordonnees(0,0);

        this.logger = Logger.getLogger(Caserne.class);
    }

    public Caserne(int id_caserne, List<Pompier> pompiers, String nom_caserne, double x, double y) {
        this.id_caserne = id_caserne;
        this.pompiers = pompiers;
        this.nom_caserne = nom_caserne;
        this.coord = new Coordonnees(x,y);

        this.logger = Logger.getLogger(Caserne.class);
    }

    public Caserne(JSONObject json, List<Pompier> pompiers) {

        this.pompiers = null;
        this.id_caserne = json.getInt("id_caserne");
        this.nom_caserne = json.getString("nom_caserne");
        this.coord = new Coordonnees(json.getFloat("coordonnee_x"), json.getFloat("coordonnee_y"));

        this.logger = Logger.getLogger(Caserne.class);
    }

    public int getId_caserne() {
        return id_caserne;
    }

    public int getTotal_pompier() {
        return pompiers.size();
    }

    public String getNom_caserne() {
        return nom_caserne;
    }

    public double getX() {
        return this.coord.getX();
    }

    public double getY() {
        return this.coord.getY();
    }

    @Override
    public String toString() {
        return " Caserne {" +
                "\n\tid_caserne=" + id_caserne +
                "\n\tnom_caserne='" + nom_caserne + '\'' +
                "\n\t" + coord +
                "\n\tPompier(s) présent(s):" + pompiers +
                "\n}";
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("id_caserne", this.id_caserne);
        json.put("nom_caserne", this.nom_caserne);
        json.put("coordonnee_x", this.coord.getX());
        json.put("coordonnee_y", this.coord.getY());

        if (pompiers == null) {
            json.put("total_pompier", 0);
        }else {
            json.put("total_pompier", this.pompiers.size());
        }


        return json;
    }
}
