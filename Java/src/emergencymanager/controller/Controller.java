package src.emergencymanager.controller;

import org.apache.log4j.Logger;

import src.commun.Capteur;
import src.commun.Coordonnees;
import src.commun.Feu;
import src.commun.utils.GlobalProperties;

import src.emergencymanager.model.CamionModel.Camion;
import src.emergencymanager.model.CamionModel.CamionModel;
import src.emergencymanager.model.CapteurModel.CapteurModel;
import src.emergencymanager.model.CaserneModel.Caserne;
import src.emergencymanager.model.CaserneModel.CaserneModel;
import src.emergencymanager.model.FeuModel.FeuModel;
import src.emergencymanager.model.IncidentModel.Incident;
import src.emergencymanager.model.IncidentModel.IncidentModel;
import src.emergencymanager.model.PompierModel.PompierModel;


import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

public class Controller extends TimerTask {
    private final PompierModel pompierModel;
    private final CapteurModel capteurModel;
    private final CamionModel camionModel;
    private final CaserneModel caserneModel;
    private final IncidentModel incidentModel;
    private final FeuModel feuModel;

    private final Logger logger;

    public Controller() {
        this.capteurModel = new CapteurModel();
        this.pompierModel = new PompierModel();
        this.camionModel  = new CamionModel();
        this.caserneModel = new CaserneModel();
        this.feuModel = new FeuModel();
        this.incidentModel = new IncidentModel();

        logger = Logger.getLogger(Controller.class);
    }

    @Override
    public void run() {
        initialiserEM();
    }

    public void initialiserEM() {
        // Récupération des données de la base de données
        getAllDonneesBDD();

        List<Camion> camions = camionModel.getCamions();
        List<Caserne> casernes = caserneModel.getCasernes();
        List<Capteur> capteurs = capteurModel.getCapteurs();
        List<Incident> incidents = incidentModel.getIncidents();

        // Création des camions si BDD vide
        if (camions.size() == 0) {
            initCamion(GlobalProperties.MAX_CAMION, casernes.get(0));
        }

        // Vérification des feux en cours
        /*if(incidents.size() == 0) {
            for (int i = 0; i < capteurs.size(); i++) {
                if(capteurs.get(i).getIntensite() > 0) {
                    Camion camion;
                    incidentModel.creerIncident(
                            GlobalProperties.API_EM_LOCAL,
                            feuModel.genererFeu(GlobalProperties.API_EM_LOCAL,
                                    capteurs.get(i).getIdCapteur(),
                                    capteurs.get(i).getIntensite(),
                                    capteurs.get(i).getCoord().getX(),
                                    capteurs.get(i).getCoord().getY()
                            ),
                            camion = getCamionPlusProche(camions, capteurs.get(i))
                    );
                }
            }
        } else {
            identifierIncident(capteurs, camions, incidents);
        }*/
    }

    // Méthode permettant d'obtenir toutes les données de la base
    private void getAllDonneesBDD() {
        capteurModel.obtenircapteursBDD(GlobalProperties.API_EM_LOCAL);
        caserneModel.obtenirCasernesBDD(GlobalProperties.API_EM_LOCAL, null);
        camionModel.obtenirCamionBDD(GlobalProperties.API_EM_LOCAL, caserneModel.getCasernes());
        feuModel.obtenirFeuxBDD(GlobalProperties.API_EM_LOCAL, capteurModel.getCapteurs());
        incidentModel.obtenirOperationBDD(GlobalProperties.API_EM_LOCAL, feuModel.getFeux(), camionModel.getCamions());
    }

    // Méthode permettant de créer des camions si il n'y en a pas dans la BDD
    private void initCamion(int nbCamions, Caserne caserne) {
        for (int i = 0; i < nbCamions; i++) {
            camionModel.creerCamions(GlobalProperties.API_EM_LOCAL, caserne);
        }
    }

    // Méthode permettant de vérifier si un capteur à détecter un feu et de créer un incident
    private void identifierIncident(List<Capteur> capteurs, List<Camion> camions, List<Incident> incidents) {
        Coordonnees coord = new Coordonnees();

        // Suppression des capteurs où il y a déjà un incident
        for(int i = 0; i < incidents.size(); i++) {
            List<Capteur> capteurTemp = new ArrayList<>();

            // Suppression des capteurs où il  y a un incident
            for(int j = 0; j < capteurs.size(); j++) {
                if(capteurs.get(j).getIdCapteur() == incidents.get(i).getFeu().getId_capteur()) {
                    capteurTemp.add(capteurs.get(j));

                    if(capteurs.get(j).getIntensite() == 0) {
                        //incidentModel.supprimerIncident(GlobalProperties.API_EM_LOCAL);
                    } else {
                        incidents.get(i).getFeu().setIntensite(capteurs.get(j).getIntensite());
                    }
                }
            }
            capteurs.removeAll(capteurTemp);

            // On traite les capteurs non traité (On aura toujours assez de camions), on génère l'incident
            for (int j = 0; j < capteurs.size(); j++) {

                if(capteurs.get(j).getIntensite() != 0) {
                    Camion camion;
                    Feu feu;

                    incidentModel.creerIncident(
                            GlobalProperties.API_EM_LOCAL,
                            feuModel.genererFeu(GlobalProperties.API_EM_LOCAL,
                                    capteurs.get(j).getIdCapteur(),
                                    capteurs.get(j).getIntensite(),
                                    capteurs.get(j).getCoord().getX(),
                                    capteurs.get(j).getCoord().getY()
                            ),
                            camion = getCamionPlusProche(camions, capteurs.get(j))
                    );
                    camionModel.definirDestination(
                            GlobalProperties.API_EM_LOCAL,
                            camion.getId_camion(),
                            capteurs.get(j).getCoord().getX(),
                            capteurs.get(j).getCoord().getY()
                    );
                }
            }
        }
    }

    // Méthode retournant le camion le plus proche
    private Camion getCamionPlusProche(List<Camion> camion, Capteur capteur) {
        double distance;
        double distanceMin = Double.MAX_VALUE;
        int j = 0;

        for(int i = 0; i < camion.size(); i++) {
            distance = distanceEntreDeuxPoints(camion.get(i).getCoord(), capteur.getCoord());
            if (distance < distanceMin) {
                distanceMin = distance;
                j = camion.indexOf(camion.get(i));
            }
        }

        return camion.get(j);
    }

    // Méthode retournant la distance
    private double distanceEntreDeuxPoints(Coordonnees c1, Coordonnees c2) {
        double x1 = c1.getX();
        double y1 = c1.getY();
        double x2 = c2.getX();
        double y2 = c2.getY();

        return Math.sqrt((y2-y1) * (y2-y1) + (x2-x1) * (x2-x1));
    }

}
