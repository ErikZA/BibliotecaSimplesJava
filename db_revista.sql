-- MySQL dump 10.13  Distrib 5.7.18, for Win64 (x86_64)
--
-- Host: localhost    Database: bancorevista
-- ------------------------------------------------------
-- Server version	5.7.18-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `artigo`
--

DROP TABLE IF EXISTS `artigo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `artigo` (
  `ID_Artigo` int(11) NOT NULL,
  `ID_Edicao` int(11) NOT NULL,
  `Escritor_Artigo` varchar(145) NOT NULL,
  PRIMARY KEY (`ID_Artigo`,`ID_Edicao`),
  UNIQUE KEY `ID_Artigo_UNIQUE` (`ID_Artigo`),
  KEY `fk_Artigo_Edicao1_idx` (`ID_Edicao`),
  CONSTRAINT `fk_Artigo_Edicao1` FOREIGN KEY (`ID_Edicao`) REFERENCES `edicao` (`ID_Edicao`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `artigo`
--

LOCK TABLES `artigo` WRITE;
/*!40000 ALTER TABLE `artigo` DISABLE KEYS */;
INSERT INTO `artigo` VALUES (1,1,'1'),(43,12,'erik mais'),(88,12,'erikhenrqur'),(12123,12,'aparevi');
/*!40000 ALTER TABLE `artigo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `edicao`
--

DROP TABLE IF EXISTS `edicao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `edicao` (
  `ID_Edicao` int(11) NOT NULL,
  `Nome_Revista` varchar(145) NOT NULL,
  `Lancamento_Edicao` varchar(45) NOT NULL,
  `Num_Edicao` int(11) NOT NULL,
  PRIMARY KEY (`ID_Edicao`,`Nome_Revista`),
  UNIQUE KEY `ID_Edicao_UNIQUE` (`ID_Edicao`),
  KEY `fk_edicao_revista1_idx` (`Nome_Revista`),
  CONSTRAINT `fk_edicao_revista1` FOREIGN KEY (`Nome_Revista`) REFERENCES `revista` (`Nome_Revista`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `edicao`
--

LOCK TABLES `edicao` WRITE;
/*!40000 ALTER TABLE `edicao` DISABLE KEYS */;
INSERT INTO `edicao` VALUES (1,'erik','1',1),(2,'erik','2',2),(12,'mateus','fsd3',3),(15,'erik','julho',4),(12349,'ERIK','março',2);
/*!40000 ALTER TABLE `edicao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revista`
--

DROP TABLE IF EXISTS `revista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `revista` (
  `ID_Revista` int(11) NOT NULL,
  `Nome_Revista` varchar(145) NOT NULL,
  `Site_Revista` varchar(145) NOT NULL,
  `Editora_Revista` varchar(145) NOT NULL,
  PRIMARY KEY (`ID_Revista`,`Nome_Revista`),
  UNIQUE KEY `ID_Revista_UNIQUE` (`ID_Revista`),
  UNIQUE KEY `Nome_Revista_UNIQUE` (`Nome_Revista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revista`
--

LOCK TABLES `revista` WRITE;
/*!40000 ALTER TABLE `revista` DISABLE KEYS */;
INSERT INTO `revista` VALUES (1,'1','1','1'),(2,'mateus','mateus','mateus'),(20,'ERIK','erik@1234','abril');
/*!40000 ALTER TABLE `revista` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-14 19:33:51
