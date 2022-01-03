package commun.api;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DialogueExterneAPI
{
    private String urlAPI;
    private HttpURLConnection connection;

    public DialogueExterneAPI() {
        this.urlAPI = "";
        this.connection = null;
    }

    public DialogueExterneAPI(String urlAPI)
    {
        this.urlAPI = urlAPI;
        this.connection = null;
    }

    private void creerRequete() {
        try {
            URL url = new URL(this.urlAPI);
            this.connection = (HttpURLConnection) url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ajouterParametres() {
        try {
            this.connection.setRequestMethod("POST");
            this.connection.setRequestProperty("Content-Type", "application/json; utf-8");
        } catch (ProtocolException e) {
            e.printStackTrace();
        }
    }

    private void ajouterFichier(String fichier) {
        try {
            OutputStream os = this.connection.getOutputStream();

            // Récupération du fichier sous forme de tableau de bytes
            byte[] input = fichier.getBytes(StandardCharsets.UTF_8);

            // Ecriture dans le flux
            os.write(input, 0, input.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getDonnees(JSONObject message) {
        this.connection.setDoOutput(true);
    }

    public void setDonnees(JSON message) {
        this.connection.setDoOutput(true);
    }

    public void updateDonnees(JSON message) {
        this.connection.setDoOutput(true);
    }
    public void deleteDonnees(JSON message) {
        this.connection.setDoOutput(true);
    }



    public String getUrlAPI()
    {
        return urlAPI;
    }
}
