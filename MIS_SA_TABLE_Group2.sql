-- MySQL dump 10.13  Distrib 8.0.17, for Win64 (x86_64)
--
-- Host: localhost    Database: sa_group6
-- ------------------------------------------------------
-- Server version	8.0.17

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
-- Table structure for table `tbapplying`
--

DROP TABLE IF EXISTS `tbapplying`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbapplying` (
  `idtbApplying` int(11) NOT NULL AUTO_INCREMENT,
  `id_applying_member` int(11) NOT NULL,
  `tbDelegator_back_idtbDelegator_back` int(11) NOT NULL,
  PRIMARY KEY (`idtbApplying`),
  KEY `fk_tbApplying_tbDelegator_back1_idx` (`tbDelegator_back_idtbDelegator_back`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_tbApplying_tbDelegator_back1` FOREIGN KEY (`tbDelegator_back_idtbDelegator_back`) REFERENCES `tbdelegator_back` (`idtbDelegator_back`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbapplying`
--

LOCK TABLES `tbapplying` WRITE;
/*!40000 ALTER TABLE `tbapplying` DISABLE KEYS */;
INSERT INTO `tbapplying` VALUES (10,7,28),(13,8,21);
/*!40000 ALTER TABLE `tbapplying` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbcommission_list`
--

DROP TABLE IF EXISTS `tbcommission_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbcommission_list` (
  `idtbCommission_list` int(11) NOT NULL AUTO_INCREMENT,
  `Id_delegator` int(11) NOT NULL,
  `brief_description` varchar(50) NOT NULL,
  `detail_description` varchar(200) NOT NULL,
  `star_level` int(11) NOT NULL,
  `exp` int(11) NOT NULL,
  `tbDelegator_back_idtbDelegator_back` int(11) NOT NULL,
  PRIMARY KEY (`idtbCommission_list`),
  KEY `fk_tbCommission_list_tbDelegator_back1_idx` (`tbDelegator_back_idtbDelegator_back`),
  CONSTRAINT `fk_tbCommission_list_tbDelegator_back1` FOREIGN KEY (`tbDelegator_back_idtbDelegator_back`) REFERENCES `tbdelegator_back` (`idtbDelegator_back`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbcommission_list`
--

LOCK TABLES `tbcommission_list` WRITE;
/*!40000 ALTER TABLE `tbcommission_list` DISABLE KEYS */;
INSERT INTO `tbcommission_list` VALUES (12,7,' 這是志剛的第2個委託',' 幫我寫後端，SA後端都別人寫的',2,200,20),(13,7,' 這是志剛的第3個委託',' 前端好難，SA前端都別人寫的',3,300,21),(14,8,' 這是國杰的第1個委託',' 天樂一直在幹話，幫我讓她閉嘴',1,1000,26),(16,8,' 這是國杰的第3個委託',' 天樂希望可以讓馬寬閉嘴',3,3000,28);
/*!40000 ALTER TABLE `tbcommission_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbdelegator_back`
--

DROP TABLE IF EXISTS `tbdelegator_back`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbdelegator_back` (
  `idtbDelegator_back` int(11) NOT NULL AUTO_INCREMENT,
  `brief_description` varchar(50) NOT NULL,
  `detail_description` varchar(200) NOT NULL,
  `star_level` int(11) NOT NULL,
  `submit_status` int(11) NOT NULL,
  `exp` int(11) NOT NULL,
  `tbMember_idtbMember` int(11) NOT NULL,
  PRIMARY KEY (`idtbDelegator_back`),
  KEY `fk_tbDelegator_back_tbMember1_idx` (`tbMember_idtbMember`),
  CONSTRAINT `fk_tbDelegator_back_tbMember1` FOREIGN KEY (`tbMember_idtbMember`) REFERENCES `tbmember` (`idtbMember`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbdelegator_back`
--

LOCK TABLES `tbdelegator_back` WRITE;
/*!40000 ALTER TABLE `tbdelegator_back` DISABLE KEYS */;
INSERT INTO `tbdelegator_back` VALUES (20,'這是志剛的第2個委託','幫我寫後端，SA後端都別人寫的',2,1,200,7),(21,'這是志剛的第3個委託','前端好難，SA前端都別人寫的',3,1,300,7),(24,'這是志剛的第5個委託','我發現SA可以一個人寫一個系統',5,2,500,7),(25,'這是志剛的第6個委託','我想不到可以寫甚麼了，幫我畢業',1,0,0,7),(26,'這是國杰的第1個委託','天樂一直在幹話，幫我讓她閉嘴',1,1,1000,8),(28,'這是國杰的第3個委託','天樂希望可以讓馬寬閉嘴',3,1,3000,8),(29,'這是國杰的第4個委託','馬寬希望老師能讓我們SA PASS!!!',4,2,4000,8),(30,'這是國杰的第5個委託','大家都希望SA可以 PASS!!!!!!!!!!!!!!!!!!!!!!!',5,0,0,8);
/*!40000 ALTER TABLE `tbdelegator_back` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbguild`
--

DROP TABLE IF EXISTS `tbguild`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbguild` (
  `idtbGuild` int(11) NOT NULL AUTO_INCREMENT,
  `id_delegator_person` int(11) NOT NULL,
  `brief_description` varchar(50) NOT NULL,
  `detail_description` varchar(200) NOT NULL,
  `star_level` int(11) NOT NULL,
  `tbDelegator_back_idtbDelegator_back` int(11) NOT NULL,
  PRIMARY KEY (`idtbGuild`),
  KEY `fk_tbGuild_tbDelegator_back1_idx` (`tbDelegator_back_idtbDelegator_back`),
  CONSTRAINT `fk_tbGuild_tbDelegator_back1` FOREIGN KEY (`tbDelegator_back_idtbDelegator_back`) REFERENCES `tbdelegator_back` (`idtbDelegator_back`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbguild`
--

LOCK TABLES `tbguild` WRITE;
/*!40000 ALTER TABLE `tbguild` DISABLE KEYS */;
INSERT INTO `tbguild` VALUES (22,7,'這是志剛的第6個委託','我想不到可以寫甚麼了，幫我畢業',1,25),(27,8,'這是國杰的第5個委託','大家都希望SA可以 PASS!!!!!!!!!!!!!!!!!!!!!!!',5,30);
/*!40000 ALTER TABLE `tbguild` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbhistory_record`
--

DROP TABLE IF EXISTS `tbhistory_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbhistory_record` (
  `idtbHistory_record` int(11) NOT NULL AUTO_INCREMENT,
  `id_delegator_member` int(11) NOT NULL,
  `id_trustee_member` int(11) NOT NULL,
  `brief_description` varchar(50) NOT NULL,
  `detail_description` varchar(200) NOT NULL,
  `star_level` int(11) NOT NULL,
  `exp` int(11) NOT NULL,
  `delegator_eval` varchar(200) NOT NULL,
  `trustee_eval` varchar(200) NOT NULL,
  `tbMember_idtbMember` int(11) NOT NULL,
  PRIMARY KEY (`idtbHistory_record`),
  KEY `fk_tbHistory_record_tbMember1_idx` (`tbMember_idtbMember`),
  CONSTRAINT `fk_tbHistory_record_tbMember1` FOREIGN KEY (`tbMember_idtbMember`) REFERENCES `tbmember` (`idtbMember`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbhistory_record`
--

LOCK TABLES `tbhistory_record` WRITE;
/*!40000 ALTER TABLE `tbhistory_record` DISABLE KEYS */;
INSERT INTO `tbhistory_record` VALUES (8,7,8,'這是志剛的第一個委託','幫我寫程式我不會寫，SA好難，8分鐘Demo不完',1,100,'你好棒棒','你也好棒棒',7),(9,7,8,'這是志剛的第一個委託','幫我寫程式我不會寫，SA好難，8分鐘Demo不完',1,100,'你好棒棒','你也好棒棒',8),(10,8,7,'這是國杰的第2個委託','馬寬也一直在幹話',2,2000,'你真的好棒棒','你也真的好棒棒',8),(11,8,7,'這是國杰的第2個委託','馬寬也一直在幹話',2,2000,'你真的好棒棒','你也真的好棒棒',7),(12,7,8,'這是志剛的第4個委託','資料庫好難，SA資料庫都不是我寫的，幫我寫',4,400,'你應該很棒吧','',7),(13,7,8,'這是志剛的第4個委託','資料庫好難，SA資料庫都不是我寫的，幫我寫',4,400,'你應該很棒吧','',8);
/*!40000 ALTER TABLE `tbhistory_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbmember`
--

DROP TABLE IF EXISTS `tbmember`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbmember` (
  `idtbMember` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `permission` int(11) NOT NULL,
  `level` int(11) NOT NULL,
  `member_exp` int(11) NOT NULL,
  `img` varchar(45) NOT NULL DEFAULT 'img.jpg',
  PRIMARY KEY (`idtbMember`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbmember`
--

LOCK TABLES `tbmember` WRITE;
/*!40000 ALTER TABLE `tbmember` DISABLE KEYS */;
INSERT INTO `tbmember` VALUES (7,'陳志剛','shaui940123@gmail.com','a123456789',0,0,0,'img2.jpg'),(8,'蕭國杰','ben093612@gmail.com','bb123456',0,0,0,'img2.jpg'),(9,'manager','anthonyma@gmail.com','cc123456',1,0,0,'img2.jpg');
/*!40000 ALTER TABLE `tbmember` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tbtrustee_back`
--

DROP TABLE IF EXISTS `tbtrustee_back`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tbtrustee_back` (
  `idtbTrustee_back` int(11) NOT NULL AUTO_INCREMENT,
  `id_member` int(11) NOT NULL,
  `brief_description` varchar(50) NOT NULL,
  `detail_description` varchar(200) NOT NULL,
  `star_level` int(11) NOT NULL,
  `accept_status` int(11) NOT NULL,
  `exp` int(11) NOT NULL,
  `tbMember_idtbMember` int(11) NOT NULL,
  `id_commission` int(11) NOT NULL,
  PRIMARY KEY (`idtbTrustee_back`),
  KEY `fk_tbTrustee_back_tbMember1_idx` (`tbMember_idtbMember`),
  CONSTRAINT `fk_tbTrustee_back_tbMember1` FOREIGN KEY (`tbMember_idtbMember`) REFERENCES `tbmember` (`idtbMember`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tbtrustee_back`
--

LOCK TABLES `tbtrustee_back` WRITE;
/*!40000 ALTER TABLE `tbtrustee_back` DISABLE KEYS */;
INSERT INTO `tbtrustee_back` VALUES (10,7,' 這是志剛的第5個委託',' 我發現SA可以一個人寫一個系統',5,1,500,8,24),(11,8,' 這是國杰的第3個委託',' 天樂希望可以讓馬寬閉嘴',3,0,3000,7,28),(13,8,' 這是國杰的第4個委託',' 馬寬希望老師能讓我們SA PASS!!!',4,1,4000,7,29),(14,7,' 這是志剛的第3個委託',' 前端好難，SA前端都別人寫的',3,0,300,8,21);
/*!40000 ALTER TABLE `tbtrustee_back` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'sa_group6'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-01-03 19:43:14
