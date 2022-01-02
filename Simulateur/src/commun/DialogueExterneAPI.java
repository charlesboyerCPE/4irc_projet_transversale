package commun;

import java.io.IOException;
import java.net.HttpURLConnection;

import java.net.URL;

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
            this.connection.setRequestMethod("POST");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void ajouterParametre() {

    }

    public void receptionMessage() {

    }

    public String getUrlAPI()
    {
        return urlAPI;
    }
}
