-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  ven. 17 déc. 2021 à 13:34
-- Version du serveur :  8.0.18
-- Version de PHP :  7.3.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `database_emergency`
--

-- --------------------------------------------------------

--
-- Structure de la table `capteur`
--

DROP TABLE IF EXISTS `capteur`;
CREATE TABLE IF NOT EXISTS `capteur` (
  `id_capteur` int(4) NOT NULL AUTO_INCREMENT,
  `intensite` int(4) NOT NULL,
  `perimetre` int(4) NOT NULL,
  `coordonnee_x` float NOT NULL,
  `coordonnee_y` float NOT NULL,
  PRIMARY KEY (`id_capteur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `caserne`
--

DROP TABLE IF EXISTS `caserne`;
CREATE TABLE IF NOT EXISTS `caserne` (
  `id_caserne` int(4) NOT NULL AUTO_INCREMENT,
  `nom_caserne` varchar(20) NOT NULL,
  `coordonnee_x` float NOT NULL,
  `coordonnee_y` float NOT NULL,
  `total_pompier` int(4) NOT NULL,
  PRIMARY KEY (`id_caserne`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Structure de la table `feu`
--

DROP TABLE IF EXISTS `feu`;
CREATE TABLE IF NOT EXISTS `feu` (
  `id_feu` int(4) NOT NULL AUTO_INCREMENT,
  `id_capteur` int(4) NOT NULL,
  `intensite` int(4) NOT NULL,
  `frequence` int(4) NOT NULL,
  `coordonnee_x` float NOT NULL,
  `coordonnee_y` float NOT NULL,
  PRIMARY KEY (`id_feu`,`id_capteur`),
  KEY `id_capteur` (`id_capteur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `feu`
--
ALTER TABLE `feu`
  ADD CONSTRAINT `feu_ibfk_1` FOREIGN KEY (`id_capteur`) REFERENCES `capteur` (`id_capteur`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;





DROP TABLE IF EXISTS `camion`;
CREATE TABLE IF NOT EXISTS `camion` (
  `id_camion` int(4) NOT NULL AUTO_INCREMENT,
  `id_caserne` int(4) NOT NULL,
  `type_produit` varchar(20) NOT NULL,
  `disponibilite` boolean NOT NULL,
  `capacite` int(4) NOT NULL,
  `nb_pompier` float(4) NOT NULL,
  PRIMARY KEY (`id_camion`),
  FOREIGN KEY `id_caserne` (`id_caserne`) REFERENCES caserne(id_caserne)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
