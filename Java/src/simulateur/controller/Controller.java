package src.simulateur.controller;

import src.commun.Capteur;
import src.commun.Coordonnees;
import src.commun.Feu;
import src.commun.utils.GlobalProperties;

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

        ArrayList<Camion> camions = new ArrayList<>(camionModel.getcamions());
        ArrayList<Feu> feux = new ArrayList<Feu>(feuModel.getFeux());
        ArrayList<Capteur> capteurs = new ArrayList<Capteur>(capteurModel.getCapteurs());

        // Génération d'un feu sur un capteur de manière aléatoire
        generationFeuSurCapteur(capteurs, feux);

        // Modification intensité feu et du capteur associé si un camion est proche d'un feu
        verifierIntensite(feux, camions);

        // Déplacement des camions
        for (Camion camion : camions) {
            xDest = camion.getCoordDest().getX();
            yDest = camion.getCoordDest().getY();

            distance = distanceEntreCoord(camion.getCoord(), camion.getCoordDest());

            if (distance < GlobalProperties.VITESSE_CAMION) {
                camionModel.deplacerCamion(GlobalProperties.API_EM_LOCAL, camion.getId_camion(), xDest, yDest);
            } else {
                double distX = camion.getCoord().getX() - camion.getCoordDest().getX();
                double distY = camion.getCoord().getY() - camion.getCoordDest().getY();
                double movX = distX * GlobalProperties.VITESSE_CAMION / distance;
                double movY = distY * GlobalProperties.VITESSE_CAMION / distance;
                camionModel.deplacerCamion(GlobalProperties.API_EM_LOCAL, camion.getId_camion(), camion.getCoord().getX() + movX, camion.getCoord().getY() + movY);
            }
        }
    }

    // Méthode permettant d'obtenir toutes les données
    private void getAllDonneesBDD() {
        capteurModel.obtenirCapteursBDD(GlobalProperties.API_SIMU_LOCAL);
        if (capteurModel.getCapteurs().size() != 0) {
            feuModel.obtenirFeuxBDD(GlobalProperties.API_SIMU_LOCAL, capteurModel.getCapteurs());
        }
        camionModel.obtenirCamionsBDD(GlobalProperties.API_EM_LOCAL);
    }

    // Méthode permettant de générer un feu sur un capteur aléatoire
    private void generationFeuSurCapteur(List<Capteur> capteurs, List<Feu> feux) {
        double nbAlea;
        Random random = new Random();
        List<Capteur> capteursTemp = new ArrayList<>();

        if(feux.size() < GlobalProperties.MAX_FEU) {
            // Parcours des capteurs et suppression de ceux qui ont déjà un feu
            for (int i = 0; i < capteurs.size(); i++) {
                for (int j = 0; j < feux.size(); j++) {
                    if(capteurs.get(i).getIdCapteur() == feux.get(j).getId_capteur()) {
                        capteursTemp.add(capteurs.get(i));
                    }
                }
            }
            capteurs.removeAll(capteursTemp);

            if (capteurs.size() != 0) {
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
            } else {
                logger.info("Tous les capteurs sont en feux");
            }

        } else {
            logger.info("Nombre MAX de feu atteint");
        }
    }

    // Méthode permettant de vérifier la location des camions et augmentation/diminution intensité feu
    private boolean camionProcheFeu(Camion camion, Feu feu) {
        boolean isProche = false;

        if(camion.getCoord().getX() == feu.getCoord().getX() && camion.getCoord().getY() == feu.getCoord().getY()) {
            logger.info("Camion n°" + camion.getId_camion() + " est proche du feu n°" + feu.getIdFeu());
            isProche = true;
        } else {
            logger.info("Camion n°" + camion.getId_camion() + " n'est pas proche du feu n°" + feu.getIdFeu());
        }

        return isProche;
    }

    // Méthode parcourant les feux et les camions et modifiant leurs intensitées
    private void verifierIntensite(List<Feu> feux, List<Camion> camions) {
        for (Camion camion : camions) {
            for (Feu feu : feux) {
                if (camionProcheFeu(camion, feu)) {
                    // Diminution
                    logger.info("Diminution de l'intensité");
                    feuModel.modificationIntensite(2, GlobalProperties.API_SIMU_LOCAL, feu.getIdFeu());
                    capteurModel.modificationIntensite(2, GlobalProperties.API_SIMU_LOCAL, feu.getId_capteur());
                } else {
                    // Augmentation
                    logger.info("Augmentation de l'intensité");
                    feuModel.modificationIntensite(1, GlobalProperties.API_SIMU_LOCAL, feu.getIdFeu());
                    capteurModel.modificationIntensite(1, GlobalProperties.API_SIMU_LOCAL, feu.getId_capteur());
                }
            }
        }
    }

    // Méthode permettant de générer un nombre aléatoire
    private double getRandom(double nbMin, double nbMax) {
        return nbMin + Math.random() * (nbMax - nbMin) ;
    }

    // Méthode permettant de determiner la distance entre deux coordonnées
    private double distanceEntreCoord(Coordonnees init, Coordonnees dest) {
        double xInit = init.getX();
        double yInit = init.getY();
        double xDest = dest.getX();
        double yDest = dest.getY();

        return Math.sqrt((yDest-yInit) * (yDest-yInit) + (xDest-xInit) * (xDest-xInit));
    }
}