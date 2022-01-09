package src.emergencymanager.model.PompierModel;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import src.emergencymanager.model.CaserneModel.Caserne;

import java.util.List;

public class Pompier {
    private final int id_pompier;
    private final String nom;
    private int fatigue;

    private Caserne caserne;

    private final Logger logger;

    public Pompier(int id_pompier, int fatigue, String nom) {
        this.id_pompier = id_pompier;
        this.fatigue = fatigue;
        this.nom = nom;
        this.caserne = null;

        this.logger = Logger.getLogger(Pompier.class);
    }

    // Constructeur permettant d'instancier un objet Pompier à partir d'un JSON
    public Pompier(JSONObject json, List<Caserne> casernes) {
        this.id_pompier = json.getInt("id_pompier");
        this.fatigue = json.getInt("fatigue");
        this.nom = json.getString("nom");
        this.caserne = casernes.get(json.getInt("id_caserne"));

        this.logger = Logger.getLogger(Pompier.class);
    }

    public int getId_pompier() {
        return id_pompier;
    }

    public int getFatigue() {
        return fatigue;
    }

    public int getIdCaserne() {
        return this.caserne.getId_caserne();
    }

    public void setCaserne(Caserne caserne) {
        this.caserne = caserne;
    }

    public String getNom() {
        return nom;
    }

    @Override
    public String toString() {
        return "\nPompier {" +
                "\n\tid_pompier=" + id_pompier +
                "\n\tfatigue=" + fatigue +
                "\n\tnom='" + nom + '\'' +
                "\n\tcaserne=" + caserne +
                "\n}";
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("id_pompier", this.id_pompier);
        json.put("fatigue", this.fatigue);
        json.put("nom", this.nom);
        json.put("id_caserne", this.caserne.getId_caserne());

        return json;
    }
}
