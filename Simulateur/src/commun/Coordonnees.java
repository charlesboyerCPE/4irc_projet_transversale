package commun;

public class Coordonnees
{
    private int x;
    private int y;

    public Coordonnees() {
        this.x = 0;
        this.y = 0;
    }

    public Coordonnees(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
