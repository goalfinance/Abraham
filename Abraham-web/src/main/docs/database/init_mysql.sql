CREATE DATABASE  IF NOT EXISTS `mas` /*!40100 DEFAULT CHARACTER SET gbk */;
USE `mas`;
-- MySQL dump 10.13  Distrib 5.7.17, for macos10.12 (x86_64)
--
-- Host: 127.0.0.1    Database: mas
-- ------------------------------------------------------
-- Server version	5.7.19

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
-- Table structure for table `t_ca_keypair`
--

DROP TABLE IF EXISTS `t_ca_keypair`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ca_keypair` (
  `key_sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `key_name` varchar(255) DEFAULT NULL,
  `key_password` varchar(255) DEFAULT NULL,
  `key_size` int(11) DEFAULT NULL,
  `key_type` varchar(255) DEFAULT NULL,
  `key_use` int(11) DEFAULT NULL,
  PRIMARY KEY (`key_sid`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_ca_keypair`
--

LOCK TABLES `t_ca_keypair` WRITE;
/*!40000 ALTER TABLE `t_ca_keypair` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_ca_keypair` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_ca_kp_dsa_extinfo`
--

DROP TABLE IF EXISTS `t_ca_kp_dsa_extinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ca_kp_dsa_extinfo` (
  `kpdei_sid` bigint(20) NOT NULL,
  `kpdei_g` varchar(1000) DEFAULT NULL,
  `kpdei_p` varchar(1000) DEFAULT NULL,
  `kpdei_q` varchar(1000) DEFAULT NULL,
  `kpdei_x` varchar(1000) DEFAULT NULL,
  `kpdei_y` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`kpdei_sid`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_ca_kp_dsa_extinfo`
--

LOCK TABLES `t_ca_kp_dsa_extinfo` WRITE;
/*!40000 ALTER TABLE `t_ca_kp_dsa_extinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_ca_kp_dsa_extinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_ca_kp_rsa_extinfo`
--

DROP TABLE IF EXISTS `t_ca_kp_rsa_extinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_ca_kp_rsa_extinfo` (
  `kpei_sid` bigint(20) NOT NULL,
  `kpei_private_exponent` varchar(1000) DEFAULT NULL,
  `kpei_private_modulus` varchar(1000) DEFAULT NULL,
  `kpei_public_exponent` varchar(1000) DEFAULT NULL,
  `kpei_public_modulus` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`kpei_sid`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_ca_kp_rsa_extinfo`
--

LOCK TABLES `t_ca_kp_rsa_extinfo` WRITE;
/*!40000 ALTER TABLE `t_ca_kp_rsa_extinfo` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_ca_kp_rsa_extinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_security_resource`
--

DROP TABLE IF EXISTS `t_security_resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_security_resource` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `group_sid` bigint(20) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sort_index` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_security_resource`
--

LOCK TABLES `t_security_resource` WRITE;
/*!40000 ALTER TABLE `t_security_resource` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_security_resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_security_resource_group`
--

DROP TABLE IF EXISTS `t_security_resource_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_security_resource_group` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `sort_idx` int(11) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_security_resource_group`
--

LOCK TABLES `t_security_resource_group` WRITE;
/*!40000 ALTER TABLE `t_security_resource_group` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_security_resource_group` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_security_role`
--

DROP TABLE IF EXISTS `t_security_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_security_role` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_security_role`
--

LOCK TABLES `t_security_role` WRITE;
/*!40000 ALTER TABLE `t_security_role` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_security_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_security_role_permission`
--

DROP TABLE IF EXISTS `t_security_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_security_role_permission` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `permission_string` varchar(255) DEFAULT NULL,
  `resource_group_sid` bigint(20) DEFAULT NULL,
  `resource_sid` bigint(20) DEFAULT NULL,
  `role_sid` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_security_role_permission`
--

LOCK TABLES `t_security_role_permission` WRITE;
/*!40000 ALTER TABLE `t_security_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_security_role_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_security_user`
--

DROP TABLE IF EXISTS `t_security_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_security_user` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `nick_name` varchar(255) DEFAULT NULL,
  `passwd` varchar(255) DEFAULT NULL,
  `pwd_change_time` datetime DEFAULT NULL,
  `roles` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_security_user`
--

LOCK TABLES `t_security_user` WRITE;
/*!40000 ALTER TABLE `t_security_user` DISABLE KEYS */;
INSERT INTO `t_security_user` VALUES (1,'2017-10-13 11:41:45','panqingrong','panpan','7695171fd207619221fe4622afd5c257e80e1f21',NULL,NULL,'284d80bd00e0351c','A',NULL,'panpan'),(2,'2017-10-16 13:40:05','Yolanda Yung','Yan Yan','87e8ea16bcb1ac99f9e465fb7a6c5a4b8aa48049',NULL,NULL,'c441c2e32fc145b8','A',NULL,'Yolanda');
/*!40000 ALTER TABLE `t_security_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_security_user_permission`
--

DROP TABLE IF EXISTS `t_security_user_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_security_user_permission` (
  `sid` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL,
  `permission_string` varchar(255) DEFAULT NULL,
  `resource_group_sid` bigint(20) DEFAULT NULL,
  `resource_sid` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `user_sid` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_security_user_permission`
--

LOCK TABLES `t_security_user_permission` WRITE;
/*!40000 ALTER TABLE `t_security_user_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `t_security_user_permission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `v_dsakey_info`
--

DROP TABLE IF EXISTS `v_dsakey_info`;
/*!50001 DROP VIEW IF EXISTS `v_dsakey_info`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_dsakey_info` AS SELECT
                                          1 AS `key_sid`,
                                          1 AS `key_name`,
                                          1 AS `key_type`,
                                          1 AS `key_size`,
                                          1 AS `key_password`,
                                          1 AS `kpdei_g`,
                                          1 AS `kpdei_p`,
                                          1 AS `kpdei_q`,
                                          1 AS `kpdei_x`,
                                          1 AS `kpdei_y`*/;
SET character_set_client = @saved_cs_client;

--
-- Temporary view structure for view `v_rsakey_info`
--

DROP TABLE IF EXISTS `v_rsakey_info`;
/*!50001 DROP VIEW IF EXISTS `v_rsakey_info`*/;
SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE VIEW `v_rsakey_info` AS SELECT
                                          1 AS `key_sid`,
                                          1 AS `key_name`,
                                          1 AS `key_type`,
                                          1 AS `key_size`,
                                          1 AS `key_password`,
                                          1 AS `kpei_private_exponent`,
                                          1 AS `kpei_private_modulus`,
                                          1 AS `kpei_public_exponent`,
                                          1 AS `kpei_public_modulus`*/;
SET character_set_client = @saved_cs_client;

--
-- Final view structure for view `v_dsakey_info`
--

/*!50001 DROP VIEW IF EXISTS `v_dsakey_info`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
  /*!50013 DEFINER=`mas`@`localhost` SQL SECURITY DEFINER */
  /*!50001 VIEW `v_dsakey_info` AS select `t_ca_keypair`.`key_sid` AS `key_sid`,`t_ca_keypair`.`key_name` AS `key_name`,`t_ca_keypair`.`key_type` AS `key_type`,`t_ca_keypair`.`key_size` AS `key_size`,`t_ca_keypair`.`key_password` AS `key_password`,`t_ca_kp_dsa_extinfo`.`kpdei_g` AS `kpdei_g`,`t_ca_kp_dsa_extinfo`.`kpdei_p` AS `kpdei_p`,`t_ca_kp_dsa_extinfo`.`kpdei_q` AS `kpdei_q`,`t_ca_kp_dsa_extinfo`.`kpdei_x` AS `kpdei_x`,`t_ca_kp_dsa_extinfo`.`kpdei_y` AS `kpdei_y` from (`t_ca_kp_dsa_extinfo` join `t_ca_keypair`) where (`t_ca_kp_dsa_extinfo`.`kpdei_sid` = `t_ca_keypair`.`key_sid`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;

--
-- Final view structure for view `v_rsakey_info`
--

/*!50001 DROP VIEW IF EXISTS `v_rsakey_info`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8 */;
/*!50001 SET character_set_results     = utf8 */;
/*!50001 SET collation_connection      = utf8_general_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
  /*!50013 DEFINER=`mas`@`localhost` SQL SECURITY DEFINER */
  /*!50001 VIEW `v_rsakey_info` AS select `t_ca_keypair`.`key_sid` AS `key_sid`,`t_ca_keypair`.`key_name` AS `key_name`,`t_ca_keypair`.`key_type` AS `key_type`,`t_ca_keypair`.`key_size` AS `key_size`,`t_ca_keypair`.`key_password` AS `key_password`,`t_ca_kp_rsa_extinfo`.`kpei_private_exponent` AS `kpei_private_exponent`,`t_ca_kp_rsa_extinfo`.`kpei_private_modulus` AS `kpei_private_modulus`,`t_ca_kp_rsa_extinfo`.`kpei_public_exponent` AS `kpei_public_exponent`,`t_ca_kp_rsa_extinfo`.`kpei_public_modulus` AS `kpei_public_modulus` from (`t_ca_keypair` join `t_ca_kp_rsa_extinfo`) where (`t_ca_keypair`.`key_sid` = `t_ca_kp_rsa_extinfo`.`kpei_sid`) */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-16 13:55:38
