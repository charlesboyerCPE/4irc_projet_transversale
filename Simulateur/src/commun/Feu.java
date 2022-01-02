package commun;

public class Feu {
    private int intensite;
    private Coordonnees coord;

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
        return this.coord;
    }

    public int getIntensite() {
        return this.intensite;
    }
}
