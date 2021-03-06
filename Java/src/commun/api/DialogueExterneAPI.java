//TODO en-tête Content-Length (nombre d'octet)

package src.commun.api;

import org.json.JSONArray;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class DialogueExterneAPI
{
    private String urlAPI;
    private HttpURLConnection connection;

    private final Logger logger;

    public DialogueExterneAPI() {
        this.urlAPI = "";
        this.connection = null;
        logger = Logger.getLogger(String.valueOf(DialogueExterneAPI.class));
    }

    public DialogueExterneAPI(String urlAPI)
    {
        this.urlAPI = urlAPI;
        this.connection = null;
        logger = Logger.getLogger(String.valueOf(DialogueExterneAPI.class));
    }

    private void creerRequete(String methode, String urlFin) {
        try {
            URL urlComplete = new URL(urlAPI + urlFin);
            this.connection = (HttpURLConnection) urlComplete.openConnection();
            this.connection.setRequestMethod(methode);
            this.connection.setRequestProperty("Content-Type", "application/json; utf-8");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JSONArray getDonnees(String url) {
        int codeRetour = -1;
        String ligne = "";

        JSONArray json = null;
        StringBuilder reponse = null;
        InputStream is = null;
        BufferedReader rd = null;

        try {
            // Création requête
            creerRequete("GET", url);
            codeRetour = this.connection.getResponseCode();

            if(codeRetour == HttpURLConnection.HTTP_OK) {

                // Lecture réponse
                is = this.connection.getInputStream();
                rd = new BufferedReader(new InputStreamReader(is));
                reponse = new StringBuilder();

                // Affectation de la réponse dans une String
                while ((ligne = rd.readLine()) != null) {
                    reponse.append(ligne);
                }

                // Parser la réponse
                json = new JSONArray(reponse.toString());

                // Fermeture de la connexion
                is.close();


            } else {
                logger.info("ERREUR Code Retour HTTP: " + codeRetour + " " + this.connection.getResponseMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    public int setDonnees(String url, JSONArray contenu) {
        int codeRetour = -1;

        try {
            // Création requête
            creerRequete("PUT", url);
            this.connection.setDoOutput(true);
            this.connection.setRequestProperty("Accept", "application/json");

            // Récupération du flux et écriture à l'intérieur
            OutputStream os = this.connection.getOutputStream();
            os.write(jsonToByteArray(contenu));

            // Récupération de la réponse de l'API
            codeRetour = this.connection.getResponseCode();
            if(codeRetour != HttpURLConnection.HTTP_CREATED) {
                logger.info("ERREUR MaJ Données : " + codeRetour + " " + this.connection.getResponseMessage());
            }

            // Fermeture de la connexion
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return codeRetour;
    }


    public int deleteDonnees(String url) {
        int codeRetour = -1;

        try {
            // Création requête
            creerRequete("DELETE", url);
            codeRetour = this.connection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return codeRetour;
    }

    private byte[] jsonToByteArray(JSONArray contenu) {

        // Conversion du JSONArray en String
        List<String> contenuToString = new ArrayList<>();
        for(int i=0; i < contenu.length(); i++) {
            contenuToString.add(contenu.get(i).toString());
        }
        String contenuString = contenuToString.toString();

        // Conversion du String Array en byte[]
        return contenuString.getBytes(StandardCharsets.UTF_8);
    }

    public String getUrlAPI()
    {
        return urlAPI;
    }
}
