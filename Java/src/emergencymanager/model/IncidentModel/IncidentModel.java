package src.emergencymanager.model.IncidentModel;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import src.commun.Api.DialogueExterneAPI;
import src.commun.Feu;
import src.emergencymanager.model.CamionModel.Camion;
import src.emergencymanager.model.FeuModel.FeuModel;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;

public class IncidentModel {
    private List<Incident> incidents;

    private DialogueExterneAPI api;
    private JSONArray json;

    private final Logger logger;

    public IncidentModel() {
        this.incidents = new ArrayList<>();
        this.api = null;
        this.json = new JSONArray();
        logger = Logger.getLogger(IncidentModel.class);
    }

    // Méthode permettant d'obtenir toutes les opréations présentes dans la base de données
    public void obtenirOperationBDD(String urlApi, List<Feu> feux, List<Camion> camions) {
        this.api = new DialogueExterneAPI(urlApi);

        // Récupération de toutes les opérations présente dans la base de données
        this.json = this.api.getDonnees("operation");
        logger.info("Opérations reçu");

        // Création des objets Opération
        for (int i = 0; i < json.length(); i++) {
            for (Camion camion : camions) {
                for (Feu feu : feux) {
                    if (json.getJSONObject(i).getInt("id_feu") == feu.getIdFeu()
                            && json.getJSONObject(i).getInt("id_camion") == camion.getId_camion()) {
                        incidents.add(new Incident(this.json.getJSONObject(i), feu, camion));
                        logger.info("[obtenirOperationBDD()] - Opération récupérée: " + incidents.get(i));
                    }
                }
            }
        }
    }

    // Méthode permettant de créer un opération
    public void creerIncident(String urlApi, Feu feu, Camion camion) {
        int codeRetour = -1;
        Incident incident = null;

        this.api = new DialogueExterneAPI(urlApi);

        // Création de l'opération
        incident = new Incident(feu, camion);
        incidents.add(incident);
        this.json.put(incident.toJson());
        logger.info("[creerOperation()] - Opération creer : " + incident);
        logger.info("[creerOperation()] - JSON creer : \n" + this.json);

        // MaJ de la base de données
        codeRetour = this.api.setDonnees("operations", this.json);
        if (codeRetour == HttpURLConnection.HTTP_CREATED) {
            logger.info("[creerOperation()] - Opération inséré en base");
        } else {
            incidents.remove(incident);
            logger.info("[creerOperation()] - Opération non creer");
        }
    }

    // Méthode permettant de supprimer une opération
    public void supprimerIncident(String urlApi) {

    }

    public List<Incident> getIncidents() {
        return incidents;
    }
}
