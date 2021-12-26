package commun;

public class Feu {
    private Coordonnees coord;
    private int intensite;

    public Feu()
    {
        this.coord = new Coordonnees(0,0);
        this.intensite = 0;
    }

    public Feu(int x, int y, int intensite)
    {
        this.coord = new Coordonnees(x, y);
        this.intensite = intensite;
    }

    public Coordonnees getCoord() {
        return coord;
    }

    public int getIntensite() {
        return intensite;
    }
}
