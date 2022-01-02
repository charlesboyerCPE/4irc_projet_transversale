package model;

import commun.Coordonnees;
import commun.Feu;

public class Camion {
    private Coordonnees coord;
    private String type;

    private Feu feu;

    public Camion() {
        this.coord = new Coordonnees(0,0);
        this.type = "";
        this.feu = null;
    }

    public Camion(String type, int x, int y) {
        this.coord = new Coordonnees(x,y);
        this.type = type;
        this.feu = null;
    }

    public String get_type() {
        return this.type;
    }

    public Coordonnees get_coord() {
        return this.coord;
    }

    public void attribuerFeu(Feu feu) {

    }
}

