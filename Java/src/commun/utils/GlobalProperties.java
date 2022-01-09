package src.commun.utils;

// Classe répertoriant les adresses des API
public class GlobalProperties
{
    public static final String ADRESSE_SERVEUR_LAURINE = "192.168.1.35";
    public static final String ADRESSE_SERVEUR_LOCAL = "127.0.0.1";
    public static final String PORT_SERVEUR = "80";

    public static final String API_SIMU = "http://" + ADRESSE_SERVEUR_LAURINE + ":" + PORT_SERVEUR + "/4irc_projet_transversale/web/ServeurWebSimulation/api/";
    public static final String API_SIMU_LOCAL = "http://" + ADRESSE_SERVEUR_LOCAL + "/~atma/4IRC/projet_4IRC_transversale/web/ServeurWebSimulation/api/";
    public static final String API_EM = "http://" + ADRESSE_SERVEUR_LAURINE + ":" + PORT_SERVEUR + "/4irc_projet_transversale/web/ServeurWebEmergency/api/";
    public static final String API_EM_LOCAL = "http://" + ADRESSE_SERVEUR_LOCAL + "/~atma/4IRC/projet_4IRC_transversale/web/ServeurWebEmergency/api/";


    // Constructeur privé
    private GlobalProperties() {}
}
