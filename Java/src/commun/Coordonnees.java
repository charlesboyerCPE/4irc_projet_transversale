package src.commun;

public class Coordonnees
{
    private float x;
    private float y;

    public Coordonnees() {
        this.x = 0;
        this.y = 0;
    }

    public Coordonnees(float x, float y)
    {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Coordonnees {" +
                "x= " + x +
                ", y=" + y +
                " }";
    }
}
