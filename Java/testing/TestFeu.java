package testing;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import src.commun.Capteur;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestFeu
{
    @Test
    void Instanciation() {
        System.out.println("Test Instanciation");

        Feu feu = new Feu(0,0,0,0,0,0);

        // Test des champs
        assertEquals(0, feu.getIdFeu());
        assertEquals(0, feu.getX());
        assertEquals(0, feu.getY());
        assertEquals(0, feu.getFrequence());
        assertEquals(0, feu.getCapteurs());
        assertEquals(0, feu.getIntensite());

        System.out.println("Instanciation : Test réussi");
    }

    @Test
    void FeuToJSON() {
        System.out.println("Test FeuToJSON");

        // Création des objets
        Feu feu = new Feu(0,0,0,0,0,0);
        JSONObject json = new JSONObject();

        json.put("id_feu", 0);
        json.put("coordonnee_x", 0);
        json.put("coordonnee_y", 0);
        json.put("frequence", 0);
        json.put("capteurs", 0);
		json.put("intensite", 0);

        assertEquals(json.toString(), feu.toJson().toString());

        System.out.println("CapteurToJSON : Test réussi");
    }
}
