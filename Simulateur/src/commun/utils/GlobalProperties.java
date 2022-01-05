package commun.utils;

// Classe répertoriant les adresses des API
public class GlobalProperties
{
    public static final String ADRESSE_SERVEUR = "192.168.3.197";
    public static final String PORT_SERVEUR = "80";
    public static final String API_SIMU = "http://" + ADRESSE_SERVEUR + ":" + PORT_SERVEUR + "/4irc_projet_transversale/web/ServeurWebEmergency/api/";
    public static final String API_EM = "";



    // Constructeur privé
    private GlobalProperties() {}
}
