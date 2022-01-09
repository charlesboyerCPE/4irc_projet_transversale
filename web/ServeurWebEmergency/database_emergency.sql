-- phpMyAdmin SQL Dump
-- version 4.9.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le :  jeu. 06 jan. 2022 à 13:52
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
-- Structure de la table `operation`
--

DROP TABLE IF EXISTS `operation`;
CREATE TABLE IF NOT EXISTS `operation` (
  `id_feu` int(4) NOT NULL,
  `id_camion` int(4) NOT NULL,
  `id_equipe` int(4) NOT NULL,
  `debut` timestamp NOT NULL,
  `fin` timestamp NOT NULL,
  PRIMARY KEY (`id_feu`,`id_camion`),
  KEY `id_camion` (`id_camion`),
  KEY `id_equipe` (`id_equipe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
-- --------------------------------------------------------

--
-- Structure de la table `feu`
--

DROP TABLE IF EXISTS `feu`;
CREATE TABLE IF NOT EXISTS `feu` (
  `id_feu` int(4) NOT NULL,
  `id_capteur` int(4) NOT NULL,
  `intensite` int(4) NOT NULL,
  `frequence` int(4) NOT NULL,
  `coordonnee_x` double NOT NULL,
  `coordonnee_y` double NOT NULL,
  PRIMARY KEY (`id_feu`,`id_capteur`),
  KEY `id_capteur` (`id_capteur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `feu`
--

INSERT INTO `feu` (`id_feu`, `id_capteur`, `intensite`, `frequence`, `coordonnee_x`, `coordonnee_y`) VALUES
(20, 10, 3, 2, 45.7719, 4.83566);

-- --------------------------------------------------------
--
-- Structure de la table `capteur`
--

DROP TABLE IF EXISTS `capteur`;
CREATE TABLE IF NOT EXISTS `capteur` (
  `id_capteur` int(4) NOT NULL,
  `intensite` int(4) NOT NULL,
  `perimetre` int(4) NOT NULL,
  `coordonnee_x` double NOT NULL,
  `coordonnee_y` double NOT NULL,
  PRIMARY KEY (`id_capteur`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Déchargement des données de la table `capteur`
--

INSERT INTO `capteur` (`id_capteur`, `intensite`, `perimetre`, `coordonnee_x`, `coordonnee_y`) VALUES
(0, 0, 10, 6, 0),
(1, 0, 10, 8, 5),
(2, 0, 10, 4, 1),
(3, 0, 10, 7, 1),
(4, 0, 10, 0, 2),
(10, 3, 3, 45.764, 4.83566);

-- --------------------------------------------------------

--
-- Structure de la table `camion`
--

DROP TABLE IF EXISTS `camion`;
CREATE TABLE IF NOT EXISTS `camion` (
  `id_camion` int(4) NOT NULL,
  `id_caserne` int(4) NOT NULL,
  `type_produit` varchar(20) NOT NULL,
  `disponibilite` tinyint(1) NOT NULL,
  `capacite` int(4) NOT NULL,
  `nb_pompier` int(4) NOT NULL,
  `coordonnee_x` double NOT NULL,
  `coordonnee_y` double NOT NULL,
  `coordonnee_dest_x` double NOT NULL,
  `coordonnee_dest_y` double NOT NULL,
  PRIMARY KEY (`id_camion`),
  KEY `id_caserne` (`id_caserne`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------
--
-- Structure de la table `constitution`
--

DROP TABLE IF EXISTS `constitution`;
CREATE TABLE IF NOT EXISTS `constitution` (
  `id_equipe` int(4) NOT NULL,
  `id_pompier` int(4) NOT NULL,
  PRIMARY KEY (`id_equipe`,`id_pompier`),
  KEY `id_pompier` (`id_pompier`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- --------------------------------------------------------




--
-- Structure de la table `pompier`
--

DROP TABLE IF EXISTS `pompier`;
CREATE TABLE IF NOT EXISTS `pompier` (
  `id_pompier` int(4) NOT NULL,
  `id_caserne` int(4) NOT NULL,
  `nom` varchar(20) NOT NULL,
  `fatigue` tinyint(1) NOT NULL,
  PRIMARY KEY (`id_pompier`),
  KEY `id_caserne` (`id_caserne`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------
--
-- Structure de la table `caserne`
--

DROP TABLE IF EXISTS `caserne`;
CREATE TABLE IF NOT EXISTS `caserne` (
  `id_caserne` int(4) NOT NULL,
  `nom_caserne` varchar(20) NOT NULL,
  `coordonnee_x` double NOT NULL,
  `coordonnee_y` double NOT NULL,
  `total_pompier` int(4) NOT NULL,
  PRIMARY KEY (`id_caserne`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------


--
-- Structure de la table `equipe`
--

DROP TABLE IF EXISTS `equipe`;
CREATE TABLE IF NOT EXISTS `equipe` (
  `id_equipe` int(4) NOT NULL,
  `date_constitution` date NOT NULL,
  PRIMARY KEY (`id_equipe`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;






--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `camion`
--
ALTER TABLE `camion`
  ADD CONSTRAINT `camion_ibfk_1` FOREIGN KEY (`id_caserne`) REFERENCES `caserne` (`id_caserne`) ON DELETE CASCADE;

--
-- Contraintes pour la table `constitution`
--
ALTER TABLE `constitution`
  ADD CONSTRAINT `constitution_ibfk_1` FOREIGN KEY (`id_equipe`) REFERENCES `equipe` (`id_equipe`) ON DELETE CASCADE,
  ADD CONSTRAINT `constitution_ibfk_2` FOREIGN KEY (`id_pompier`) REFERENCES `pompier` (`id_pompier`) ON DELETE CASCADE;

--
-- Contraintes pour la table `feu`
--
ALTER TABLE `feu`
  ADD CONSTRAINT `feu_ibfk_1` FOREIGN KEY (`id_capteur`) REFERENCES `capteur` (`id_capteur`) ON DELETE CASCADE;

--
-- Contraintes pour la table `operation`
--
ALTER TABLE `operation`
  ADD CONSTRAINT `operation_ibfk_1` FOREIGN KEY (`id_feu`) REFERENCES `feu` (`id_feu`) ON DELETE CASCADE,
  ADD CONSTRAINT `operation_ibfk_2` FOREIGN KEY (`id_camion`) REFERENCES `camion` (`id_camion`) ON DELETE CASCADE,
  ADD CONSTRAINT `operation_ibfk_3` FOREIGN KEY (`id_equipe`) REFERENCES `equipe` (`id_equipe`) ON DELETE CASCADE;

--
-- Contraintes pour la table `pompier`
--
ALTER TABLE `pompier`
  ADD CONSTRAINT `pompier_ibfk_1` FOREIGN KEY (`id_caserne`) REFERENCES `caserne` (`id_caserne`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
