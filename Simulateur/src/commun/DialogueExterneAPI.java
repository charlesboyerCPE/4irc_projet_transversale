package commun;

import java.net.HttpURLConnection;

public class DialogueExterneAPI
{
    private String urlAPI;
    private HttpURLConnection requeteHttp;

    public DialogueExterneAPI() {
        this.urlAPI = "";
        this.requeteHttp = null;
    }

    public DialogueExterneAPI(String urlAPI)
    {
        this.urlAPI = urlAPI;
        this.requeteHttp = null;
    }

    public void envoyerMessage(String message) {

    }

    public void receptionMessage() {

    }

    public String getUrlAPI()
    {
        return urlAPI;
    }
}
