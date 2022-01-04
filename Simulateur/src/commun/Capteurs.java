/**
 * Classe : Capteurs
 * Classe représentant un capteur dans la simulation
 *
 * @version dev
 * @author Charles BOYER - 4IRC ~ CPE Lyon
 */

package commun;

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
        this.isAlive = true;
        this.intensite = 0;
        this.perimetre = 10;
        this.coord = new Coordonnees(x, y);
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
}
