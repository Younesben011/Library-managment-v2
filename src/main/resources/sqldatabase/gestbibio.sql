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
-- Table structure for table `abonne`
--

DROP TABLE IF EXISTS `abonne`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `abonne` (
  `No_Ab` int NOT NULL,
  `nom` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `prenom` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `Adress` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `no_bib` int NOT NULL,
  PRIMARY KEY (`No_Ab`),
  KEY `no_bi` (`no_bib`),
  CONSTRAINT `no_bi` FOREIGN KEY (`no_bib`) REFERENCES `bibiothéque` (`no_bib`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `abonne`
--

LOCK TABLES `abonne` WRITE;
/*!40000 ALTER TABLE `abonne` DISABLE KEYS */;
/*!40000 ALTER TABLE `abonne` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `auteur`
--

DROP TABLE IF EXISTS `auteur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `auteur` (
  `no_Aut` int NOT NULL,
  `nom_Aut` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`no_Aut`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `auteur`
--

LOCK TABLES `auteur` WRITE;
/*!40000 ALTER TABLE `auteur` DISABLE KEYS */;
INSERT INTO `auteur` VALUES (1,'john michal'),(2,'pierre mark'),(3,'younes'),(4,'dd'),(5,'sda'),(6,'d'),(7,'sa3ed'),(8,'ahmed');
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
  `nom_biblio` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `prenom_biblio` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
  `Login` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  `no_bib` int NOT NULL,
  `est_admin` int DEFAULT NULL,
  PRIMARY KEY (`id_biblio`),
  KEY `Login` (`Login`),
  KEY `no_bibio` (`no_bib`),
  CONSTRAINT `Login` FOREIGN KEY (`Login`) REFERENCES `compte` (`Login`),
  CONSTRAINT `no_bibio` FOREIGN KEY (`no_bib`) REFERENCES `bibiothéque` (`no_bib`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bibiothécaire`
--

LOCK TABLES `bibiothécaire` WRITE;
/*!40000 ALTER TABLE `bibiothécaire` DISABLE KEYS */;
INSERT INTO `bibiothécaire` VALUES (1,'hiba','hibbba','hiba',1,1),(2,'ahlem','ahlemm','ahlem05',1,0),(3,'younes','benz','younes011',1,0),(4,'miloud','ll','miloud',1,0),(5,'younes','ben','youness',1,0),(6,'younes','ben','younes0111',1,0),(7,'younes','younes11','younes11',1,0),(8,'ahmed','jamal','ahmed011',1,0);
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
  `nom_bib` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
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
-- Table structure for table `compte`
--

DROP TABLE IF EXISTS `compte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `compte` (
  `Login` varchar(12) COLLATE utf8mb4_general_ci NOT NULL,
  `mot_de_passe` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`Login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `compte`
--

LOCK TABLES `compte` WRITE;
/*!40000 ALTER TABLE `compte` DISABLE KEYS */;
INSERT INTO `compte` VALUES ('ahlem05','ahlem2000'),('ahmed011','ahmed'),('hiba','hiba'),('lina22','123456'),('miloud','12345'),('younes011','* * * * '),('younes0111','younes'),('younes11','12345'),('youness','12345');
/*!40000 ALTER TABLE `compte` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ecrire`
--

DROP TABLE IF EXISTS `ecrire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ecrire` (
  `ISBN` varchar(12) COLLATE utf8mb4_general_ci NOT NULL,
  `no_Aut` int NOT NULL,
  PRIMARY KEY (`ISBN`),
  KEY `no_aut` (`no_Aut`),
  CONSTRAINT `ISBN_fk` FOREIGN KEY (`ISBN`) REFERENCES `livre` (`ISBN`),
  CONSTRAINT `no_aut` FOREIGN KEY (`no_Aut`) REFERENCES `auteur` (`no_Aut`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ecrire`
--

LOCK TABLES `ecrire` WRITE;
/*!40000 ALTER TABLE `ecrire` DISABLE KEYS */;
INSERT INTO `ecrire` VALUES ('FF',3);
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
  PRIMARY KEY (`no_emp`),
  KEY `no_Ab` (`No_Ab`),
  KEY `no_EX` (`no_Ex`),
  KEY `id_biblio_fk` (`id_biblio`),
  CONSTRAINT `id_biblio_fk` FOREIGN KEY (`id_biblio`) REFERENCES `bibiothécaire` (`id_biblio`),
  CONSTRAINT `no_Ab` FOREIGN KEY (`No_Ab`) REFERENCES `abonne` (`No_Ab`),
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
  `ISBN` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`no_Ex`),
  KEY `no_bib` (`no_bib`),
  KEY `ISBN` (`ISBN`),
  CONSTRAINT `ISBN` FOREIGN KEY (`ISBN`) REFERENCES `livre` (`ISBN`),
  CONSTRAINT `no_bib` FOREIGN KEY (`no_bib`) REFERENCES `bibiothéque` (`no_bib`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `exemplaire`
--

LOCK TABLES `exemplaire` WRITE;
/*!40000 ALTER TABLE `exemplaire` DISABLE KEYS */;
INSERT INTO `exemplaire` VALUES (1,1,1,'FF'),(2,1,1,'FF'),(3,1,1,'FF');
/*!40000 ALTER TABLE `exemplaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livre`
--

DROP TABLE IF EXISTS `livre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livre` (
  `ISBN` varchar(12) COLLATE utf8mb4_general_ci NOT NULL,
  `titre` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
  `editeur` varchar(35) COLLATE utf8mb4_general_ci NOT NULL,
  `quantité` int DEFAULT NULL,
  PRIMARY KEY (`ISBN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livre`
--

LOCK TABLES `livre` WRITE;
/*!40000 ALTER TABLE `livre` DISABLE KEYS */;
INSERT INTO `livre` VALUES ('FF','asd','sad',3);
/*!40000 ALTER TABLE `livre` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-18  7:34:06
