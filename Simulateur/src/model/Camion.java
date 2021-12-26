package model;

import commun.Coordonnees;

public class Camion {
    private Coordonnees coord;
    private String type;

    public Camion()
    {}

    public Camion(String type)
    {
        this.type = type;
    }
}

