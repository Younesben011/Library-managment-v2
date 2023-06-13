-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: gestbibio
-- ------------------------------------------------------
-- Server version	8.0.32

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `abonnetype`
--

DROP TABLE IF EXISTS `abonnetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `abonnetype` (
  `type_id` int NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abonnetype`
--

LOCK TABLES `abonnetype` WRITE;
/*!40000 ALTER TABLE `abonnetype` DISABLE KEYS */;
INSERT INTO `abonnetype` VALUES (1,'student'),(2,'teacher');
/*!40000 ALTER TABLE `abonnetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auteur`
--

DROP TABLE IF EXISTS `auteur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auteur` (
  `no_Aut` int NOT NULL,
  `nom_Aut` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`no_Aut`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auteur`
--

LOCK TABLES `auteur` WRITE;
/*!40000 ALTER TABLE `auteur` DISABLE KEYS */;
INSERT INTO `auteur` VALUES (1,'john michal'),(2,'pierre mark');
/*!40000 ALTER TABLE `auteur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bibiothécaire`
--

DROP TABLE IF EXISTS `bibiothécaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bibiothécaire` (
  `id_biblio` int NOT NULL,
  `nom_biblio` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `prenom_biblio` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Login` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `no_bib` int NOT NULL,
  `est_admin` int DEFAULT NULL,
  PRIMARY KEY (`id_biblio`),
  KEY `Login` (`Login`),
  KEY `no_bibio` (`no_bib`),
  CONSTRAINT `bibiothécaire_ibfk_1` FOREIGN KEY (`Login`) REFERENCES `compte` (`Login`),
  CONSTRAINT `no_bibio` FOREIGN KEY (`no_bib`) REFERENCES `bibiothéque` (`no_bib`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bibiothécaire`
--

LOCK TABLES `bibiothécaire` WRITE;
/*!40000 ALTER TABLE `bibiothécaire` DISABLE KEYS */;
INSERT INTO `bibiothécaire` VALUES (1,'jj','hiba','hiba',1,1);
/*!40000 ALTER TABLE `bibiothécaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bibiothéque`
--

DROP TABLE IF EXISTS `bibiothéque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bibiothéque` (
  `no_bib` int NOT NULL,
  `nom_bib` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`no_bib`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bibiothéque`
--

LOCK TABLES `bibiothéque` WRITE;
/*!40000 ALTER TABLE `bibiothéque` DISABLE KEYS */;
INSERT INTO `bibiothéque` VALUES (1,'Librairy EL_Manar'),(2,'Librairy El_afak'),(3,'maktaba_eljazera');
/*!40000 ALTER TABLE `bibiothéque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorie`
--

DROP TABLE IF EXISTS `categorie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorie` (
  `id_cat` int NOT NULL,
  `categorie` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_cat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorie`
--

LOCK TABLES `categorie` WRITE;
/*!40000 ALTER TABLE `categorie` DISABLE KEYS */;
INSERT INTO `categorie` VALUES (0,'null'),(1,'master'),(2,'lisence');
/*!40000 ALTER TABLE `categorie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `compte`
--

DROP TABLE IF EXISTS `compte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compte` (
  `Login` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `mot_de_passe` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `type` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`Login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compte`
--

LOCK TABLES `compte` WRITE;
/*!40000 ALTER TABLE `compte` DISABLE KEYS */;
INSERT INTO `compte` VALUES ('hiba','hiba','bibiothécaire');
/*!40000 ALTER TABLE `compte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ecrire`
--

DROP TABLE IF EXISTS `ecrire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ecrire` (
  `ISBN` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `no_Aut` int NOT NULL,
  PRIMARY KEY (`ISBN`),
  KEY `no_aut` (`no_Aut`),
  CONSTRAINT `ISBN_fk` FOREIGN KEY (`ISBN`) REFERENCES `ouvrage` (`ISBN`),
  CONSTRAINT `no_aut` FOREIGN KEY (`no_Aut`) REFERENCES `auteur` (`no_Aut`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ecrire`
--

LOCK TABLES `ecrire` WRITE;
/*!40000 ALTER TABLE `ecrire` DISABLE KEYS */;

/*!40000 ALTER TABLE `ecrire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `empreinte`
--

DROP TABLE IF EXISTS `empreinte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `empreinte` (
  `no_emp` int NOT NULL,
  `Date_Emp` date NOT NULL,
  `DateRest_prevu` date NOT NULL,
  `id_biblio` int NOT NULL,
  `No_Ab` int NOT NULL,
  `no_Ex` int NOT NULL,
  `DateRest_reel` date DEFAULT NULL,
  `notifie` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`no_emp`),
  KEY `no_Ab` (`No_Ab`),
  KEY `no_EX` (`no_Ex`),
  KEY `id_biblio_fk` (`id_biblio`),
  CONSTRAINT `id_biblio_fk` FOREIGN KEY (`id_biblio`) REFERENCES `bibiothécaire` (`id_biblio`),
  CONSTRAINT `no_Ab` FOREIGN KEY (`No_Ab`) REFERENCES `membre` (`No_Ab`),
  CONSTRAINT `no_EX` FOREIGN KEY (`no_Ex`) REFERENCES `exemplaire` (`no_Ex`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `empreinte`
--

LOCK TABLES `empreinte` WRITE;
/*!40000 ALTER TABLE `empreinte` DISABLE KEYS */;

/*!40000 ALTER TABLE `empreinte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `exemplaire`
--

DROP TABLE IF EXISTS `exemplaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `exemplaire` (
  `no_Ex` int NOT NULL,
  `etat_ex` int NOT NULL COMMENT 'etat_ex=0 pr etat_ex=1',
  `no_bib` int NOT NULL,
  `ISBN` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`no_Ex`),
  KEY `no_bib` (`no_bib`),
  KEY `ISBN` (`ISBN`),
  CONSTRAINT `ISBN` FOREIGN KEY (`ISBN`) REFERENCES `ouvrage` (`ISBN`),
  CONSTRAINT `no_bib` FOREIGN KEY (`no_bib`) REFERENCES `bibiothéque` (`no_bib`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exemplaire`
--

LOCK TABLES `exemplaire` WRITE;
/*!40000 ALTER TABLE `exemplaire` DISABLE KEYS */;
/*!40000 ALTER TABLE `exemplaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `historique`
--

DROP TABLE IF EXISTS `historique`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `historique` (
  `id_histo` int NOT NULL,
  `ISBN` varchar(12) DEFAULT NULL,
  `num_echec` int DEFAULT NULL,
  PRIMARY KEY (`id_histo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `historique`
--

LOCK TABLES `historique` WRITE;
/*!40000 ALTER TABLE `historique` DISABLE KEYS */;

/*!40000 ALTER TABLE `historique` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `membre`
--

DROP TABLE IF EXISTS `membre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `membre` (
  `No_Ab` int NOT NULL,
  `nom` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `prenom` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `Adress` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `no_bib` int NOT NULL,
  `email` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` int DEFAULT NULL,
  `id_sn` int DEFAULT NULL,
  PRIMARY KEY (`No_Ab`),
  UNIQUE KEY `email` (`email`),
  KEY `no_bi` (`no_bib`),
  KEY `type` (`type`),
  KEY `id_sn` (`id_sn`),
  CONSTRAINT `membre_ibfk_1` FOREIGN KEY (`type`) REFERENCES `abonnetype` (`type_id`),
  CONSTRAINT `membre_ibfk_2` FOREIGN KEY (`id_sn`) REFERENCES `sanction` (`id_sn`),
  CONSTRAINT `no_bi` FOREIGN KEY (`no_bib`) REFERENCES `bibiothéque` (`no_bib`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `membre`
--

LOCK TABLES `membre` WRITE;
/*!40000 ALTER TABLE `membre` DISABLE KEYS */;

/*!40000 ALTER TABLE `membre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ouvrage`
--

DROP TABLE IF EXISTS `ouvrage`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ouvrage` (
  `ISBN` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `titre` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `quantité` int DEFAULT NULL,
  `ouvrage_type` int DEFAULT NULL,
  `discript_short` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `discript_long` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `editeur` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL,
  `catégorie` int DEFAULT NULL,
  PRIMARY KEY (`ISBN`),
  KEY `ouvrage_type` (`ouvrage_type`),
  KEY `catégorie` (`catégorie`),
  CONSTRAINT `ouvrage_ibfk_1` FOREIGN KEY (`ouvrage_type`) REFERENCES `ouvragetype` (`id_type`),
  CONSTRAINT `ouvrage_ibfk_2` FOREIGN KEY (`catégorie`) REFERENCES `categorie` (`id_cat`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ouvrage`
--

LOCK TABLES `ouvrage` WRITE;
/*!40000 ALTER TABLE `ouvrage` DISABLE KEYS */;
/*!40000 ALTER TABLE `ouvrage` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ouvragetype`
--

DROP TABLE IF EXISTS `ouvragetype`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ouvragetype` (
  `id_type` int NOT NULL,
  `ouvrage_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ouvragetype`
--

LOCK TABLES `ouvragetype` WRITE;
/*!40000 ALTER TABLE `ouvragetype` DISABLE KEYS */;
INSERT INTO `ouvragetype` VALUES (1,'mémoire'),(2,'livre'),(3,'revue');
/*!40000 ALTER TABLE `ouvragetype` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sanction`
--

DROP TABLE IF EXISTS `sanction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sanction` (
  `id_sn` int NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `motif` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_sn`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sanction`
--

LOCK TABLES `sanction` WRITE;
/*!40000 ALTER TABLE `sanction` DISABLE KEYS */;
INSERT INTO `sanction` VALUES (0,'null','clear'),(1,'Avertissement','Retard frequent dans le retour des livre'),(2,'Suspension','Deterioration intentionnelle d\'un livre');
/*!40000 ALTER TABLE `sanction` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-13  8:38:55
