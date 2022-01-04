//TODO A TESTER PUT et en-tête Content-Length

package commun.api;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
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

    public JSONObject getDonnees(String url) {
        int codeRetour = -1;
        JSONObject json = null;
        StringBuilder reponse = null;
        String ligne = "";

        try {
            // Création requête
            creerRequete("GET", url);
            codeRetour = this.connection.getResponseCode();
            logger.info("Code Retour HTTP: " + codeRetour);

            if(codeRetour == HttpURLConnection.HTTP_OK) {

                // Lecture réponse
                InputStream is = this.connection.getInputStream();
                BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                reponse = new StringBuilder();

                // Affectation de la réponse dans une String
                while ((ligne = rd.readLine()) != null) {
                    reponse.append(ligne);
                }

                // Parser la réponse
                json = new JSONObject(reponse.toString());

            } else {
                logger.info("ERREUR Code Retour HTTP: " + codeRetour);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return json;
    }

    public int setDonnees(String url, JSONObject contenu) {
        int codeRetour = -1;
        StringBuilder reponse = null;
        String ligne = "";

        try {
            // Création requête
            creerRequete("PUT", url);
            this.connection.setDoOutput(true);
            this.connection.setRequestProperty("Accept", "application/json");

            codeRetour = this.connection.getResponseCode();
            logger.info("Code Retour HTTP: " + codeRetour);

            if(codeRetour == HttpURLConnection.HTTP_OK) {

                // Récupération du JSON
                byte[] out = contenu.toString().getBytes(StandardCharsets.UTF_8);

                // Récupération du flux et écriture
                OutputStream os = this.connection.getOutputStream();
                os.write(out);

                // Récupération de la réponse de l'API
                codeRetour = this.connection.getResponseCode();
                if(codeRetour == HttpURLConnection.HTTP_CREATED) {
                    logger.info("PUT Effectué: " + codeRetour);
                } else {
                    logger.info("ERREUR Put : " + codeRetour);
                }
            } else {
                logger.info("ERREUR Code Retour HTTP: " + codeRetour);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return codeRetour;
    }


    public int deleteDonnees(String url) {
        // Création requête
        creerRequete("DELETE", url);
    }

    public String getUrlAPI()
    {
        return urlAPI;
    }
}