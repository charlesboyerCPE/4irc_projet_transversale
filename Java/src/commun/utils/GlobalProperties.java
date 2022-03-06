package src.commun.utils;

// Classe répertoriant les adresses des API
public class GlobalProperties
{
    /* CONNEXION API */
    public static final String ADRESSE_SERVEUR_LAURINE = "10.50.105.25";
    public static final String ADRESSE_SERVEUR_LOCAL = "127.0.0.1";
    public static final String PORT_SERVEUR = "80";

    public static final String API_SIMU = "http://" + ADRESSE_SERVEUR_LAURINE + ":" + PORT_SERVEUR + "/4irc_projet_transversale/web/ServeurWebSimulation/api/";
    public static final String API_SIMU_LOCAL = "http://" + ADRESSE_SERVEUR_LOCAL + ":" + PORT_SERVEUR + "/~atma/4IRC/projet_4IRC_transversale/web/ServeurWebSimulation/api/";
    public static final String API_EM = "http://" + ADRESSE_SERVEUR_LAURINE + ":" + PORT_SERVEUR + "/4irc_projet_transversale/web/ServeurWebEmergency/api/";
    public static final String API_EM_LOCAL = "http://" + ADRESSE_SERVEUR_LOCAL + ":" + PORT_SERVEUR + "/~atma/4IRC/projet_4IRC_transversale/web/ServeurWebEmergency/api/";

    /* CONSTANTE LOCALISATION */
    public static final double X_MIN = 45.764043;
    public static final double X_MAX = 45.796002;
    public static final double Y_MIN = 4.835659;
    public static final double Y_MAX = 4.975719;

    public static final double VITESSE_CAMION = 0.0025;

    /* CONSTANTE JEU */
    public static final int MAX_FEU = 2;
    public static final int MAX_CAMION = 5;

    // Constructeur privé
    private GlobalProperties() {}
}
