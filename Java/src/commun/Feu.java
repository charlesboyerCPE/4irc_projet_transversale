package src.commun;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Feu {

    private int id_feu;
    private int frequence;
    private int intensite;

    private Coordonnees coord;
    private List<Capteur> capteurs;

    private final Logger logger;

    public Feu()
    {
        this.id_feu = 0;
        this.frequence = 0;
        this.intensite = 0;
        this.coord = new Coordonnees(0,0);
        this.capteurs = new ArrayList<Capteur>();
        this.logger = Logger.getLogger(String.valueOf(Feu.class));
    }

    public Feu(int id_feu, int frequence, int intensite, float x, float y, Capteur capteur)
    {
        this.id_feu = id_feu;
        this.frequence = frequence;
        this.intensite = intensite;
        this.coord = new Coordonnees(x, y);
        this.capteurs = new ArrayList<Capteur>();
        this.capteurs.add(capteur);
        this.logger = Logger.getLogger(String.valueOf(Feu.class));
    }

    public int getIdFeu() {
        return id_feu;
    }

    public float getX() {
        return coord.getX();
    }

    public float getY() {
        return coord.getY();
    }

    public int getFrequence() {
        return frequence;
    }

    public List<Capteur> getCapteurs() {
        return capteurs;
    }

    public int getIntensite() {
        return this.intensite;
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();

        json.put("id_feu", this.getIdFeu());
        json.put("id_capteur", this.getCapteurs().get(0).getIdCapteur());
        json.put("intensite", this.getIntensite());
        json.put("frequence", this.getFrequence());
        json.put("coordonnee_x", this.getX());
        json.put("coordonnee_y", this.getY());

        logger.info("JSON créer : " + json);

        return json;
    }

    @Override
    public String toString() {
        return "\nFeu {" +
                "\n\tid_feu=" + id_feu +
                "\n\tfrequence=" + frequence +
                "\n\tintensite=" + intensite +
                "\n\tcoord=" + coord +
                "\n\tcapteurs=" + capteurs +
                "\n}";
    }
}
