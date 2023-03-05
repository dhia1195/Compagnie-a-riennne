-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : dim. 05 mars 2023 à 13:07
-- Version du serveur : 10.4.27-MariaDB
-- Version de PHP : 8.0.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `compagnie_aerienne`
--

-- --------------------------------------------------------

--
-- Structure de la table `avion`
--

CREATE TABLE `avion` (
  `ID` int(50) NOT NULL,
  `modele` varchar(50) NOT NULL,
  `capacite` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `avion`
--

INSERT INTO `avion` (`ID`, `modele`, `capacite`) VALUES
(6666, 'Boeing 737', 200),
(7894, 'Boeing 737', 200),
(7895, 'Boeing 737', 210),
(45021, 'Boeing 737', 200),
(58025, 'Airbus A320', 350),
(59078, 'Airbus A320', 350),
(60065, 'Airbus A320', 350),
(61065, 'Boeing 737', 100),
(75841, 'Beechcraft ', 120),
(75842, 'Beechcraft ', 130),
(580251, 'Boeing 767', 300),
(590781, 'Boeing 767', 350),
(600651, 'Boeing 767', 350),
(610652, 'Beechcraft ', 100);

-- --------------------------------------------------------

--
-- Structure de la table `siege`
--

CREATE TABLE `siege` (
  `Numero` int(50) NOT NULL,
  `typesiege` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `siege`
--

INSERT INTO `siege` (`Numero`, `typesiege`) VALUES
(3111, 'Economic class'),
(3112, 'Economic class'),
(3520, 'First class'),
(3521, 'First class'),
(3522, 'First class'),
(3523, 'First class'),
(3524, 'First class'),
(3566, 'Economic class'),
(3567, 'Economic class');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `avion`
--
ALTER TABLE `avion`
  ADD PRIMARY KEY (`ID`);

--
-- Index pour la table `siege`
--
ALTER TABLE `siege`
  ADD PRIMARY KEY (`Numero`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
