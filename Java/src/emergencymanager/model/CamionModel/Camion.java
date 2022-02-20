package src.emergencymanager.model.CamionModel;

import org.apache.log4j.Logger;
import org.json.JSONObject;

import src.commun.Coordonnees;
import src.emergencymanager.model.CaserneModel.Caserne;
import src.emergencymanager.model.PompierModel.Pompier;

import java.util.List;

public class Camion {
    private final int id_camion;
    private final int disponibilite;
    private final int capacite;
    private final String type_produit;
    private boolean en_intervention;

    private final Caserne caserne;
    private List<Pompier> pompiers;
    private Coordonnees coord;
    private Coordonnees coordDest;

    private final Logger logger;

    public Camion(int id_camion, int disponibilite, int capacite, String type_produit, Caserne caserne) {
        this.id_camion = id_camion;
        this.disponibilite = disponibilite;
        this.capacite = capacite;
        this.en_intervention = false;
        this.type_produit = type_produit;
        this.coord = new Coordonnees(caserne.getX(), caserne.getY());
        this.coordDest = new Coordonnees(0, 0);
        this.caserne = caserne;
        this.pompiers = null;

        logger = Logger.getLogger(Camion.class);
    }

    public Camion(JSONObject json, Caserne caserne) {

        this.id_camion = json.getInt("id_camion");
        this.disponibilite = json.getInt("disponibilite");
        this.capacite = json.getInt("capacite");
        this.type_produit = json.getString("type_produit");
        this.coord = new Coordonnees(json.getFloat("coordonnee_x"), json.getFloat("coordonnee_y"));
        this.coordDest = new Coordonnees(json.getFloat("coordonnee_dest_x"), json.getFloat("coordonnee_dest_y"));
        this.en_intervention = false;
        this.caserne = caserne;
        this.pompiers = null;

        logger = Logger.getLogger(Camion.class);
    }

    public int getId_camion() {
        return id_camion;
    }

    public int getDisponibilite() {
        return disponibilite;
    }

    public int getCapacite() {
        return capacite;
    }

    public String getType_produit() {
        return type_produit;
    }

    public int getNb_pompier() {
        return this.pompiers.size();
    }

    public int getId_caserne() {
        return this.caserne.getId_caserne();
    }

    public Coordonnees getCoord() {
        return this.coord;
    }

    public Coordonnees getCoordDest() {
        return this.coordDest;
    }

    public void setDestX(double xDest) {
        if(this.coordDest == null) {
            this.coordDest = new Coordonnees(xDest, 0);
        } else {
            this.coordDest.setX(xDest);
        }
    }

    public void setDestY(double yDest) {
        if(this.coordDest == null) {
            this.coordDest = new Coordonnees(0, yDest);
        } else {
            this.coordDest.setY(yDest);
        }
    }

    public boolean isEn_intervention() {
        return en_intervention;
    }

    public void setEn_intervention(boolean en_intervention) {
        this.en_intervention = en_intervention;
    }

    public void setPompiers(List<Pompier> pompiers) {
        this.pompiers = pompiers;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("id_camion", this.id_camion);
        json.put("id_caserne", this.caserne.getId_caserne());
        json.put("type_produit", this.type_produit);
        json.put("disponibilite", this.disponibilite);
        json.put("capacite", this.capacite);

        if (this.pompiers == null) {
            json.put("nb_pompier", 0);
        } else {
            json.put("nb_pompier", this.pompiers.size());
        }

        json.put("coordonnee_x", this.coord.getX());
        json.put("coordonnee_y", this.coord.getY());

        if (this.coordDest == null) {
            json.put("coordonnee_dest_x", 0);
            json.put("coordonnee_dest_y", 0);
        } else {
            json.put("coordonnee_dest_x", this.coordDest.getX());
            json.put("coordonnee_dest_y", this.coordDest.getY());
        }

        return json;
    }

    @Override
    public String toString() {
        return "\nCamion {" +
                "\n\tid_camion=" + id_camion +
                "\n\tdisponibilite=" + disponibilite +
                "\n\tcapacite=" + capacite +
                "\n\ttype_produit='" + type_produit + '\'' +
                "\n\tCoord" + coord + '\'' +
                "\n\tCoordDest" + coordDest + '\'' +
                "\n\tpompiers=" + pompiers +
                "\n\tcaserne=" + caserne +
                "\n}";
    }
}
