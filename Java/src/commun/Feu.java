package src.commun;

import org.json.JSONObject;
import org.apache.log4j.Logger;

public class Feu {

    private int id_feu;
    private int id_capteur;
    private int frequence;
    private int intensite;

    private Coordonnees coord;

    private final Logger logger;

    public Feu()
    {
        this.id_feu = 0;
        this.frequence = 0;
        this.intensite = 0;
        this.coord = new Coordonnees(0,0);
        this.logger = Logger.getLogger(Feu.class);
    }

    public Feu(int id_feu, int id_capteur, int frequence, int intensite, double x, double y)
    {
        this.id_feu = id_feu;
        this.id_capteur = id_capteur;
        this.frequence = frequence;
        this.intensite = intensite;
        this.coord = new Coordonnees(x, y);
        this.logger = Logger.getLogger(String.valueOf(Feu.class));
    }

    public Feu(JSONObject json, Capteur capteur) {
        this.id_feu = json.getInt("id_feu");
        this.frequence = json.getInt("frequence");
        this.intensite = json.getInt("intensite");
        this.id_capteur = json.getInt("id_capteur");
        this.coord = new Coordonnees(json.getDouble("coordonnee_x"), json.getDouble("coordonnee_y"));

        logger = Logger.getLogger(Feu.class);
    }

    public int getIdFeu() {
        return id_feu;
    }

    public int getId_capteur() {
        return id_capteur;
    }

    public Coordonnees getCoord() {
        return this.coord;
    }

    public int getFrequence() {
        return frequence;
    }

    public int getIntensite() {
        return this.intensite;
    }

    public void setIntensite(int intensite) {
        this.intensite = intensite;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("id_feu", this.getIdFeu());
        json.put("id_capteur", this.getId_capteur());
        json.put("intensite", this.getIntensite());
        json.put("frequence", this.getFrequence());
        json.put("coordonnee_x", this.getCoord().getX());
        json.put("coordonnee_y", this.getCoord().getY());

        return json;
    }

    @Override
    public String toString() {
        return "\nFeu {" +
                "\n\tid_feu=" + id_feu +
                "\n\tid_capteur=" + id_capteur +
                "\n\tfrequence=" + frequence +
                "\n\tintensite=" + intensite +
                "\n\tcoord=" + coord +
                "\n}";
    }
}
