CREATE DATABASE  IF NOT EXISTS `employee` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `employee`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: employee
-- ------------------------------------------------------
-- Server version	8.0.36

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
-- Table structure for table `attend_record_feb`
--

DROP TABLE IF EXISTS `attend_record_feb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attend_record_feb` (
  `ID` int NOT NULL,
  `total_absent` int DEFAULT NULL,
  `late` int DEFAULT NULL,
  `early_checkout` int DEFAULT NULL,
  `in_time` int DEFAULT NULL,
  `overtime` int DEFAULT NULL,
  `approval` int DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  KEY `emp_index` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attend_record_jan`
--

DROP TABLE IF EXISTS `attend_record_jan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attend_record_jan` (
  `ID` int NOT NULL,
  `total_absent` int DEFAULT NULL,
  `late` int DEFAULT NULL,
  `early_checkout` int DEFAULT NULL,
  `in_time` int DEFAULT NULL,
  `overtime` int DEFAULT NULL,
  `approval` int DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  KEY `emp_index` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attendancefeb`
--

DROP TABLE IF EXISTS `attendancefeb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendancefeb` (
  `ID` int DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `CHECK IN` time DEFAULT NULL,
  `CHECK OUT` time DEFAULT NULL,
  `OVERTIME` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attendancejan`
--

DROP TABLE IF EXISTS `attendancejan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendancejan` (
  `ID` int DEFAULT NULL,
  `DATE` date DEFAULT NULL,
  `CHECK_IN` time DEFAULT NULL,
  `CHECK_OUT` time DEFAULT NULL,
  `OVERTIME` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attendancetracking_feb`
--

DROP TABLE IF EXISTS `attendancetracking_feb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendancetracking_feb` (
  `ID` int DEFAULT NULL,
  `Checkin_DateTime` timestamp NULL DEFAULT NULL,
  `Checkout_DateTime` timestamp NULL DEFAULT NULL,
  `hour_ovt` int DEFAULT NULL,
  `ear_check_out` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `attendancetracking_jan`
--

DROP TABLE IF EXISTS `attendancetracking_jan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `attendancetracking_jan` (
  `ID` int DEFAULT NULL,
  `Checkin_DateTime` timestamp NULL DEFAULT NULL,
  `Checkout_DateTime` timestamp NULL DEFAULT NULL,
  `hour_ovt` int DEFAULT NULL,
  `ear_check_min` int DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `information`
--

DROP TABLE IF EXISTS `information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `information` (
  `ID` int NOT NULL,
  `Name` text,
  `Date _of_Birth` date DEFAULT NULL,
  `Sex` text,
  `Department` text,
  `Position` text,
  `Base_Salary` int DEFAULT NULL,
  `AbsentApproval_Jan` int DEFAULT NULL,
  `AbsentApproval_Feb` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `idx_user_ID` (`ID`),
  CONSTRAINT `idx_user_ID` FOREIGN KEY (`ID`) REFERENCES `logininfo` (`User_id`),
  CONSTRAINT `user_ID` FOREIGN KEY (`ID`) REFERENCES `logininfo` (`User_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `logininfo`
--

DROP TABLE IF EXISTS `logininfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `logininfo` (
  `User_id` int NOT NULL,
  `Password` int DEFAULT NULL,
  `Position` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`User_id`),
  KEY `idx_user_ID` (`User_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sal_perform_feb`
--

DROP TABLE IF EXISTS `sal_perform_feb`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sal_perform_feb` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Absent` int DEFAULT NULL,
  `Penalty` int DEFAULT NULL,
  `Bonus` int DEFAULT NULL,
  `Income` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `emp_index` (`ID`),
  KEY `sal_perform_feb_index` (`ID`),
  CONSTRAINT `fk_sal_perform_feb_attend_record_feb` FOREIGN KEY (`ID`) REFERENCES `attend_record_feb` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sal_perform_jan`
--

DROP TABLE IF EXISTS `sal_perform_jan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sal_perform_jan` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `Absent` int DEFAULT NULL,
  `Penalty` int DEFAULT NULL,
  `Bonus` int DEFAULT NULL,
  `Income` int DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `emp_index` (`ID`),
  CONSTRAINT `emp_index` FOREIGN KEY (`ID`) REFERENCES `attend_record_jan` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'employee'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-04-03 19:02:54
