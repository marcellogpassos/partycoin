CREATE DATABASE  IF NOT EXISTS `partycoin` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `partycoin`;
-- MySQL dump 10.13  Distrib 5.7.20, for Linux (x86_64)
--
-- Host: localhost    Database: partycoin
-- ------------------------------------------------------
-- Server version	5.7.21

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
-- Table structure for table `blockchain`
--

DROP TABLE IF EXISTS `blockchain`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `blockchain` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `src_wallet` varchar(255) NOT NULL DEFAULT '0',
  `dst_wallet` varchar(255) NOT NULL,
  `value` float NOT NULL,
  `hash` varchar(255) DEFAULT NULL,
  `previous_hash` varchar(255) NOT NULL,
  `timestamp` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `created_at_UNIQUE` (`timestamp`),
  UNIQUE KEY `previous_hash_UNIQUE` (`previous_hash`),
  UNIQUE KEY `UK_ghsswvob6im352w1a32vywgva` (`previous_hash`),
  UNIQUE KEY `UK_7rysasf1efd1ge2v8sue4mqwd` (`timestamp`),
  UNIQUE KEY `hash_UNIQUE` (`hash`),
  UNIQUE KEY `UK_7rgxsshn3xqqq4m7i7un6jcm1` (`hash`),
  KEY `fk_src_wallet_idx` (`src_wallet`),
  KEY `fk_dst_wallet_idx` (`dst_wallet`),
  CONSTRAINT `FK1nho3d141io33kdu8156elhyc` FOREIGN KEY (`dst_wallet`) REFERENCES `wallets` (`hash`),
  CONSTRAINT `FK2vcwac76ymy00l48fr03jfipr` FOREIGN KEY (`src_wallet`) REFERENCES `wallets` (`hash`),
  CONSTRAINT `fk_dst_wallet` FOREIGN KEY (`dst_wallet`) REFERENCES `wallets` (`hash`) ON UPDATE CASCADE,
  CONSTRAINT `fk_src_wallet` FOREIGN KEY (`src_wallet`) REFERENCES `wallets` (`hash`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transactions_requests`
--

DROP TABLE IF EXISTS `transactions_requests`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transactions_requests` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `src_wallet` varchar(255) NOT NULL,
  `dst_wallet` varchar(255) NOT NULL,
  `value` float NOT NULL,
  `transaction_id` bigint(20) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_transaction_request_idx` (`transaction_id`),
  CONSTRAINT `fk_transaction_request` FOREIGN KEY (`transaction_id`) REFERENCES `blockchain` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(255) DEFAULT NULL,
  `sobrenome` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `data_nascimento` date DEFAULT NULL,
  `sexo` char(1) DEFAULT NULL,
  `cpf` char(11) DEFAULT NULL,
  `celular` char(16) DEFAULT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `cpf_UNIQUE` (`cpf`),
  UNIQUE KEY `celular_UNIQUE` (`celular`),
  UNIQUE KEY `UK_801s18qq25h7jlff9piylc1tb` (`celular`),
  UNIQUE KEY `UK_7kqluf7wl0oxs7n90fpya03ss` (`cpf`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `wallets`
--

DROP TABLE IF EXISTS `wallets`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `wallets` (
  `hash` varchar(255) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`hash`),
  UNIQUE KEY `id_UNIQUE` (`hash`),
  KEY `fk_user_wallet_idx` (`user_id`),
  CONSTRAINT `FKc1foyisidw7wqqrkamafuwn4e` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `fk_user_wallet` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping events for database 'partycoin'
--

--
-- Dumping routines for database 'partycoin'
--
/*!50003 DROP FUNCTION IF EXISTS `balance` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `balance`(wallet VARCHAR(255)) RETURNS float
BEGIN

	DECLARE balance float;
    
    SELECT
		sum(
			IF(
				bloc.src_wallet = wallet,
				bloc.value * (-1),
				bloc.value
			)
		)
	INTO balance
	FROM blockchain bloc
	WHERE
		bloc.src_wallet = wallet
		OR bloc.dst_wallet = wallet;
        
	IF(balance IS NULL) THEN
        SET balance := 0;
    END IF;

	-- Saldo negativo identificado
    IF(balance < 0) THEN
        RETURN -1;
    END IF;    

RETURN balance;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `get_current_timestamp` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `get_current_timestamp`() RETURNS bigint(20)
BEGIN

RETURN round(unix_timestamp(now(4)) * 1000);
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `get_genesis_hash` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `get_genesis_hash`() RETURNS varchar(255) CHARSET utf8
BEGIN

RETURN '1e06b30966f4969ae41d990072e8d19b';
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `get_hash` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `get_hash`(id bigint) RETURNS varchar(255) CHARSET utf8
BEGIN

	DECLARE hashh varchar(255);

	SELECT
		sha2(
			concat_ws(
				':',
                bloc.id,
                bloc.src_wallet,
                bloc.dst_wallet,
                bloc.value,
                bloc.previous_hash,
                bloc.timestamp
			),
			256
		)
	INTO hashh
	FROM blockchain bloc
	WHERE bloc.id = id;

RETURN hashh;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `send` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `send`(src_wallet varchar(255), dst_wallet varchar(255), value float) RETURNS varchar(255) CHARSET utf8
BEGIN

	DECLARE previous_hash varchar(255);
    DECLARE created_at bigint DEFAULT get_current_timestamp();
    DECLARE hashh varchar(255);
    DECLARE new_id bigint;
    
    -- Valor menor que zero
    IF(value <= 0) THEN
        RETURN '-1';
    END IF;
    
    -- Saldo for insuficiente
    IF(value > balance(src_wallet)) THEN
        RETURN '-2';
    END IF;
    
    -- Carteiras de destino e origem são iguais
    IF(src_wallet = dst_wallet) THEN
		RETURN '-3';
    END IF;
    
    -- Obtém o hash anterior
    SELECT bloc.hash
	INTO previous_hash
	FROM blockchain bloc
	WHERE
		bloc.timestamp = (
			select max(baux.timestamp)
			from blockchain baux
		)
	ORDER BY bloc.id
	LIMIT 1;
	
    -- Se for a primeira transação, utiliza o hash gênesis
    IF(previous_hash IS NULL) THEN
        SET previous_hash := get_genesis_hash();
    END IF;
	
    -- Cria a nova transação
    INSERT INTO blockchain (src_wallet, dst_wallet, value, previous_hash, timestamp)
	VALUES (src_wallet, dst_wallet, value, previous_hash, created_at);
	
    -- Obtém o ID da nova transação
	SELECT bloc.id
	INTO new_id
	FROM blockchain bloc
	WHERE 
		bloc.previous_hash = previous_hash
        AND bloc.timestamp = created_at;
	
    -- Calcula o novo hash
	SET hashh := get_hash(new_id);
    
    -- Atribui o hash calculado à nova transação
    UPDATE blockchain bloc
    SET bloc.hash = hashh
    WHERE bloc.id = new_id;
         
    -- Atualiza o status da requisição para esta transação
    UPDATE transactions_requests trre
	SET trre.transaction_id = new_id, trre.updated_at = now()
	WHERE
		trre.src_wallet = src_wallet
		AND trre.dst_wallet = dst_wallet
        AND trre.value < value
        AND trre.transaction_id IS NULL;
        
RETURN hashh;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `total_received` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `total_received`(wallet VARCHAR(255)) RETURNS float
BEGIN

    DECLARE total_received float;
    
    SELECT sum(receive_transactions.value)
	INTO total_received
	FROM blockchain receive_transactions
	WHERE receive_transactions.dst_wallet = wallet;
        
    IF(total_received IS NULL) THEN
        SET total_received := 0;
    END IF;

RETURN total_received;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP FUNCTION IF EXISTS `total_sent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `total_sent`(wallet VARCHAR(255)) RETURNS float
BEGIN

    DECLARE total_sent float;
    
    SELECT sum(send_transactions.value)
	INTO total_sent
	FROM blockchain send_transactions
	WHERE send_transactions.src_wallet = wallet;
        
    IF(total_sent IS NULL) THEN
        SET total_sent := 0;
    END IF;

RETURN total_sent;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `blockchain_validation` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `blockchain_validation`(current_hash varchar(255))
BEGIN

	DECLARE current_id bigint;
    DECLARE previous_hash varchar(255);
    DECLARE src_wallet varchar(255);
    
    DECLARE error_message varchar(255);
    DECLARE success integer;
    
    SELECT
		bloc.id,
        bloc.previous_hash,
        bloc.src_wallet
	INTO
		current_id,
        previous_hash,
        src_wallet
	FROM
		blockchain bloc
	WHERE
		bloc.hash = current_hash;
            
    IF(current_id IS NULL) THEN
		SET error_message := concat('Cadeia inválida! Hash não encontrado: ', current_hash);
        SIGNAL SQLSTATE 'HY000'
		SET MESSAGE_TEXT = error_message;
    END IF;
    
    IF(current_hash != get_hash(current_id)) THEN
		SET error_message := concat('Cadeia inválida! Hash inválido: ', current_hash);
        SIGNAL SQLSTATE 'HY000'
		SET MESSAGE_TEXT = error_message;
	ELSE
		SELECT concat('Hash válido: ', current_hash) as 'Resultado preliminar';
    END IF;
    
    IF(src_wallet = '0') THEN
		IF(previous_hash = get_genesis_hash()) THEN
			SELECT 'Cadeia válida!' as 'Reultado final';
		ELSE
			SET error_message := concat('Cadeia inválida! Hash inicial inválido: ', previous_hash);
			SIGNAL SQLSTATE 'HY000'
			SET MESSAGE_TEXT = error_message;
        END IF;
	ELSE
		CALL blockchain_validation(previous_hash);
    END IF;	

END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-03-28 15:40:53
