package src.commun;

public class Coordonnees
{
    private double x;
    private double y;

    public Coordonnees() {
        this.x = 0;
        this.y = 0;
    }

    public Coordonnees(double x, double y)
    {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Coordonnees {" +
                "[" + x +
                "," + y +
                " ]}";
    }
}
