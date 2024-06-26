-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema wrrdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `wrrdb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `wrrdb` ;

-- -----------------------------------------------------
-- Table `wrrdb`.`movement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`movement` (
  `movement_id` INT NOT NULL AUTO_INCREMENT,
  `movement_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`movement_id`),
  UNIQUE INDEX `movement_id_UNIQUE` (`movement_id` ASC) VISIBLE,
  UNIQUE INDEX `movement_name_UNIQUE` (`movement_name` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wrrdb`.`refresh_token`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`refresh_token` (
  `refresh_token_id` BIGINT NOT NULL AUTO_INCREMENT,
  `expiration` VARCHAR(255) NULL DEFAULT NULL,
  `token` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`refresh_token_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wrrdb`.`box`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`box` (
  `box_id` INT NOT NULL AUTO_INCREMENT,
  `box_name` VARCHAR(255) NULL,
  PRIMARY KEY (`box_id`),
  UNIQUE INDEX `box_name_UNIQUE` (`box_name` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wrrdb`.`wod`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`wod` (
  `wod_id` BIGINT NOT NULL AUTO_INCREMENT,
  `box_id` INT NOT NULL,
  `wod_name` VARCHAR(255) NULL DEFAULT NULL,
  `team_size` INT NULL DEFAULT '1',
  `likes` INT NULL DEFAULT '0',
  `description` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`wod_id`),
  UNIQUE INDEX `wod_id_UNIQUE` (`wod_id` ASC) VISIBLE,
  INDEX `fk_wod_box1_idx` (`box_id` ASC) VISIBLE,
  CONSTRAINT `fk_wod_box1`
    FOREIGN KEY (`box_id`)
    REFERENCES `wrrdb`.`box` (`box_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wrrdb`.`workout`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`workout` (
  `workout_id` BIGINT NOT NULL AUTO_INCREMENT,
  `wod_id` BIGINT NOT NULL,
  `type` VARCHAR(45) NULL DEFAULT NULL,
  `seq` INT NULL DEFAULT NULL,
  `parent_workout_id` BIGINT NOT NULL,
  PRIMARY KEY (`workout_id`),
  INDEX `fk_workout_wod1_idx` (`wod_id` ASC) VISIBLE,
  INDEX `fk_workout_workout1_idx` (`parent_workout_id` ASC) VISIBLE,
  CONSTRAINT `fk_workout_wod1`
    FOREIGN KEY (`wod_id`)
    REFERENCES `wrrdb`.`wod` (`wod_id`),
  CONSTRAINT `fk_workout_workout1`
    FOREIGN KEY (`parent_workout_id`)
    REFERENCES `wrrdb`.`workout` (`workout_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wrrdb`.`type_amrap`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`type_amrap` (
  `type_amrap_id` INT NOT NULL AUTO_INCREMENT,
  `workout_id` BIGINT NOT NULL,
  `time_cap` TIME NULL DEFAULT NULL,
  PRIMARY KEY (`type_amrap_id`),
  INDEX `fk_type_amrap_workout1_idx` (`workout_id` ASC) VISIBLE,
  CONSTRAINT `fk_type_amrap_workout1`
    FOREIGN KEY (`workout_id`)
    REFERENCES `wrrdb`.`workout` (`workout_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wrrdb`.`type_emom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`type_emom` (
  `type_emom_id` INT NOT NULL AUTO_INCREMENT,
  `workout_id` BIGINT NOT NULL,
  `round` INT NULL DEFAULT NULL,
  `time_cap` TIME NULL DEFAULT NULL,
  PRIMARY KEY (`type_emom_id`),
  INDEX `fk_type_emom_workout1_idx` (`workout_id` ASC) VISIBLE,
  CONSTRAINT `fk_type_emom_workout1`
    FOREIGN KEY (`workout_id`)
    REFERENCES `wrrdb`.`workout` (`workout_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wrrdb`.`type_onoff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`type_onoff` (
  `type_onoff_id` INT NOT NULL AUTO_INCREMENT,
  `type_onoffcol` VARCHAR(45) NULL DEFAULT NULL,
  `workout_id` BIGINT NOT NULL,
  `round` INT NULL DEFAULT NULL,
  `time_cap` TIME NULL DEFAULT NULL,
  `on_time` INT NULL DEFAULT NULL,
  `off_time` INT NULL DEFAULT NULL,
  PRIMARY KEY (`type_onoff_id`),
  INDEX `fk_type_onoff_workout1_idx` (`workout_id` ASC) VISIBLE,
  CONSTRAINT `fk_type_onoff_workout1`
    FOREIGN KEY (`workout_id`)
    REFERENCES `wrrdb`.`workout` (`workout_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wrrdb`.`type_rft`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`type_rft` (
  `type_rft_id` INT NOT NULL AUTO_INCREMENT,
  `workout_id` BIGINT NOT NULL,
  `round` INT NULL DEFAULT NULL,
  `time_cap` TIME NULL DEFAULT NULL,
  PRIMARY KEY (`type_rft_id`),
  INDEX `fk_type_rft_workout1_idx` (`workout_id` ASC) VISIBLE,
  CONSTRAINT `fk_type_rft_workout1`
    FOREIGN KEY (`workout_id`)
    REFERENCES `wrrdb`.`workout` (`workout_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wrrdb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`user` (
  `created_date` DATE NULL DEFAULT NULL,
  `user_id` BIGINT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NULL DEFAULT NULL,
  `name` VARCHAR(255) NOT NULL,
  `profile_image_url` VARCHAR(255) NULL DEFAULT NULL,
  `role` VARCHAR(255) NULL DEFAULT NULL,
  `username` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `UKsb8bbouer5wak8vyiiy4pf2bx` (`username` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `wrrdb`.`workout_movement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`workout_movement` (
  `workout_type_movement_id` INT NOT NULL AUTO_INCREMENT,
  `workout_id` BIGINT NOT NULL,
  `workout_movementcol` VARCHAR(45) NULL DEFAULT NULL,
  `movement_id` INT NOT NULL,
  `reps_unit` VARCHAR(45) NULL DEFAULT NULL,
  `reps_male` INT NULL DEFAULT '0',
  `reps_female` INT NULL DEFAULT '0',
  `weight_unit` VARCHAR(45) NULL DEFAULT NULL,
  `weight_male` FLOAT NULL DEFAULT '0',
  `weight_female` FLOAT NULL DEFAULT '0',
  `synchronized` TINYINT NULL DEFAULT NULL,
  `seq` INT NULL DEFAULT NULL,
  PRIMARY KEY (`workout_type_movement_id`),
  INDEX `fk_wod_type_movement_movement1_idx` (`movement_id` ASC) VISIBLE,
  INDEX `fk_workout_type_movement_workout1_idx` (`workout_id` ASC) VISIBLE,
  CONSTRAINT `fk_wod_type_movement_movement1`
    FOREIGN KEY (`movement_id`)
    REFERENCES `wrrdb`.`movement` (`movement_id`),
  CONSTRAINT `fk_workout_type_movement_workout1`
    FOREIGN KEY (`workout_id`)
    REFERENCES `wrrdb`.`workout` (`workout_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
