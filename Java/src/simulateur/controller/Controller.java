package src.simulateur.controller;


import src.commun.Capteur;
import src.commun.Coordonnees;
import src.commun.Feu;
import src.commun.Utils.GlobalProperties;

import src.simulateur.model.CamionModel.Camion;
import src.simulateur.model.CamionModel.CamionModel;
import src.simulateur.model.CapteurModel.CapteurModel;
import src.simulateur.model.FeuModel.FeuModel;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

public class Controller extends TimerTask {
    private final CapteurModel capteurModel;
    private final FeuModel feuModel;
    private final CamionModel camionModel;

    private final Logger logger;

    // Constructeur de la classe Controller
    public Controller() {
        this.capteurModel = new CapteurModel();
        this.feuModel = new FeuModel();
        this.camionModel = new CamionModel();

        this.logger = Logger.getLogger(Controller.class);
    }

    @Override
    public void run() {
        initialiserSimulation();
    }

    // Méthode permettant d'initialiser la simulation
    public void initialiserSimulation() {
        double xDest;
        double yDest;
        double distance;

        // Récupération de toutes les données
        getAllDonneesBDD();

        List<Camion> camions = camionModel.getcamions();
        List<Feu> feux = feuModel.getFeux();
        List<Capteur> capteurs = capteurModel.getCapteurs();

        // Génération d'un feu sur un capteur de manière aléatoire
        generationFeuSurCapteur();

        // Modification intensité feu et du capteur associé
        verifierIntensite();

        // Déplacement des camions
        for (int i = 0; i < camions.size(); i++) {
            xDest = camions.get(i).getCoordDest().getX();
            yDest = camions.get(i).getCoordDest().getY();

            distance = distanceEntreCoord(camions.get(i).getCoord(), camions.get(i).getCoordDest());

            if (distance < GlobalProperties.VITESSE_CAMION) {
                camionModel.deplacerCamion(GlobalProperties.API_EM_LOCAL, camions.get(i).getId_camion(), xDest, yDest);
            } else {
                double distX = camions.get(i).getCoord().getX() - camions.get(i).getCoordDest().getX();
                double distY = camions.get(i).getCoord().getY() - camions.get(i).getCoordDest().getY();
                double movX = distX * GlobalProperties.VITESSE_CAMION / distance;
                double movY = distY * GlobalProperties.VITESSE_CAMION / distance;
                camionModel.deplacerCamion(GlobalProperties.API_EM_LOCAL, camions.get(i).getId_camion(), distX, distY);
            }
        }
    }

    // Méthode permettant d'obtenir toutes les données
    private void getAllDonneesBDD() {
        capteurModel.obtenircapteursBDD(GlobalProperties.API_SIMU_LOCAL);
        feuModel.obtenirFeuxBDD(GlobalProperties.API_SIMU_LOCAL, capteurModel.getCapteurs());
        camionModel.obtenircamionsBDD(GlobalProperties.API_EM_LOCAL);
    }

    // Méthode permettant de générer un feu sur un capteur aléatoire
    private void generationFeuSurCapteur() {
        double nbAlea;
        Random random = new Random();
        List<Feu> feux = feuModel.getFeux();
        List<Capteur> capteurs = capteurModel.getCapteurs();
        List<Capteur> capteursTemp = new ArrayList<>();
        List<Camion> camions = camionModel.getcamions();


        if(feux.size() < GlobalProperties.MAX_FEU) {
            // Parcours des capteurs et suppression de ceux qui ont déjà un feu
            for (int i = 0; i < capteurs.size(); i++) {
                for (int j = 0; j < feux.size(); j++) {
                    if(capteurs.get(i).getIdCapteur() == feux.get(i).getId_capteur()) {
                        capteursTemp.add(capteurs.get(i));
                    }
                }
            }
            capteurs.removeAll(capteursTemp);

            // Génération d'un feu sur un capteur aléatoirement
            nbAlea = getRandom(0, capteurs.size());
            double intensiteAlea = getRandom(0, 10);
            Capteur capteur = capteurs.get((int) nbAlea);
            feuModel.genererFeu(GlobalProperties.API_SIMU_LOCAL,
                    capteur.getIdCapteur(),
                    (int) intensiteAlea,
                    capteur.getCoord().getX(),
                    capteur.getCoord().getY()
            );
        }
    }

    // Méthode permettant de vérifier la location des camions et augmentation/diminution intensité feu
    private boolean camionProcheFeu(Camion camion, Feu feu) {
        boolean isProche = false;

        if(camion.getCoord().getX() == feu.getCoord().getX() && camion.getCoord().getY() == feu.getCoord().getY()) {
            isProche = true;
        }

        return isProche;
    }

    // Méthode parcourant les feux et les camions et modifiant leurs intensitées
    private void verifierIntensite() {
        List<Feu> feux = feuModel.getFeux();
        List<Camion> camions = camionModel.getcamions();

        for (int i = 0; i < camions.size(); i++) {
            for(int k = 0; k < feux.size(); k++) {
                if (camionProcheFeu(camions.get(i), feux.get(k))) {
                    // Diminution
                    feuModel.modificationIntensite(2, GlobalProperties.API_SIMU_LOCAL, feux.get(k).getIdFeu());
                    capteurModel.modificationIntensite(2, GlobalProperties.API_SIMU_LOCAL, feux.get(k).getId_capteur());
                } else {
                    // Augmentation
                    feuModel.modificationIntensite(1, GlobalProperties.API_SIMU_LOCAL, feux.get(k).getIdFeu());
                    capteurModel.modificationIntensite(1, GlobalProperties.API_SIMU_LOCAL, feux.get(k).getId_capteur());
                }
            }
        }
    }

    private double getRandom(double nbMin, double nbMax) {
        return nbMin + Math.random() * (nbMax - nbMin) ;
    }

    private double distanceEntreCoord(Coordonnees init, Coordonnees dest) {
        double xInit = init.getX();
        double yInit = init.getY();
        double xDest = dest.getX();
        double yDest = dest.getY();

        return Math.sqrt((yDest-yInit) * (yDest-yInit) + (xDest-xInit) * (xDest-xInit));
    }
}