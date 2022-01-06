/**
 * Classe : Capteurs
 * Classe représentant un capteur dans la simulation
 *
 * @version dev
 * @author Charles BOYER - 4IRC ~ CPE Lyon
 */

package src.commun;

import org.json.JSONObject;

import java.util.logging.Logger;

public class Capteur {
    private int id_capteur;
    private int perimetre;
    private int intensite;
    private boolean isAlive;
    private Coordonnees coord;

    private final Logger logger;

    public Capteur()
    {
        this.id_capteur = 0;
        this.isAlive = true;
        this.intensite = 0;
        this.perimetre = 10;
        this.coord = new Coordonnees(0,0);
        this.logger = Logger.getLogger(String.valueOf(Capteur.class));
    }

    public Capteur(int x, int y) {
        this.id_capteur = 0;
        this.isAlive = true;
        this.intensite = 0;
        this.perimetre = 10;
        this.coord = new Coordonnees(x, y);
        this.logger = Logger.getLogger(String.valueOf(Capteur.class));
    }

    public Capteur(int id_capteur, boolean isAlive, int intensite, int perimetre, int x, int y) {
        this.id_capteur = id_capteur;
        this.isAlive = true;
        this.intensite = intensite;
        this.perimetre = perimetre;
        this.coord = new Coordonnees(x, y);

        this.logger = Logger.getLogger(String.valueOf(Capteur.class));
    }

    public Capteur(JSONObject json) {
        this.id_capteur = json.getInt("id_capteur_capteur");
        this.isAlive = true;
        this.intensite = json.getInt("intensite");
        this.perimetre = json.getInt("perimetre");
        this.coord = new Coordonnees(json.getFloat("coordonnee_x"), json.getFloat("coordonnee_y"));
        this.logger = Logger.getLogger(String.valueOf(Capteur.class));
    }

    // Méthode permettant de créer un JSON à partir d'un objet Capteur
    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("id_capteur", this.getIdCapteur());
        json.put("perimetre", this.getPerimetre());
        json.put("coordonnee_x", this.getX());
        json.put("intensite", this.getIntensite());
        json.put("coordonnee_y", this.getY());

        logger.info("JSON créer : " + json);

        return json;
    }


    public boolean isAlive() {
        return isAlive;
    }

    public int getPerimetre() {
        return perimetre;
    }

    public int getIntensite() {
        return intensite;
    }

    public int getIdCapteur() {
        return id_capteur;
    }

    public float getX() {
        return coord.getX();
    }

    public float getY() {
        return coord.getY();
    }

    @Override
    public String toString() {
        return "Capteur {\n" +
                "\tid_capteur=" + id_capteur +
                "\n\tisAlive=" + isAlive +
                "\n\tperimetre=" + perimetre +
                "\n\tintensite=" + intensite +
                "\n\tcoord=" + coord +
                " \n}";
    }
}
