/**
 * Classe : Capteurs
 * Classe représentant un capteur dans la simulation
 *
 * @version dev
 * @author Charles BOYER - 4IRC ~ CPE Lyon
 */

package commun;

import org.json.JSONArray;
import org.json.JSONObject;

public class Capteurs {
    private int id;
    private boolean isAlive;
    private int perimetre;
    private int intensite;

    private Coordonnees coord;

    public Capteurs()
    {
        this.id = 0;
        this.isAlive = true;
        this.intensite = 0;
        this.perimetre = 10;
        this.coord = new Coordonnees(0,0);
    }

    public Capteurs(int x, int y) {
        this.id = 0;
        this.isAlive = true;
        this.intensite = 0;
        this.perimetre = 10;
        this.coord = new Coordonnees(x, y);
    }

    public Capteurs(int id, boolean isAlive, int intensite, int perimetre, int x, int y) {
        this.id = 0;
        this.isAlive = true;
        this.intensite = 0;
        this.perimetre = 10;
        this.coord = new Coordonnees(x, y);
    }

    public Capteurs(JSONObject json) {
        this.id = json.getInt("id_capteur");
        this.isAlive = true;
        this.intensite = json.getInt("intensite");
        this.perimetre = json.getInt("perimetre");
        this.coord = new Coordonnees(json.getFloat("coordonnee_x"), json.getFloat("coordonnee_y"));
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

    @Override
    public String toString() {
        return "Capteurs {\n" +
                "\tid=" + id +
                "\n\tisAlive=" + isAlive +
                "\n\tperimetre=" + perimetre +
                "\n\tintensite=" + intensite +
                "\n\tcoord=" + coord +
                " \n}";
    }
}
