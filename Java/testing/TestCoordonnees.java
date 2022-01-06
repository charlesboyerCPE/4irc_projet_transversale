package testing;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import src.commun.Capteur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCoordonnees {
    @Test
    void Instanciation() {
        System.out.println("Test Instanciation");

        Capteur capteur = new Capteur(0,true,5,10,2,3);

        // Test des champs
        assertEquals(0, capteur.getId());
        assertTrue(capteur.isAlive());
        assertEquals(5, capteur.getIntensite());
        assertEquals(10, capteur.getPerimetre());
        assertEquals(2, capteur.getX());
        assertEquals(3, capteur.getY());

        System.out.println("Instanciation : Test réussi");
    }

    @Test
    void CapteurToJSON() {
        System.out.println("Test CapteurToJSON");

        // Création des objets
        Capteur capteur = new Capteur(0,true,5,10,2,3);
        JSONObject json = new JSONObject();

        json.put("id_capteur", 0);
        json.put("perimetre", 10);
        json.put("coordonnee_x", 2);
        json.put("intensite", 5);
        json.put("coordonnee_y", 3);

        assertEquals(json.toString(), capteur.toJson().toString());

        System.out.println("CapteurToJSON : Test réussi");
    }
}
