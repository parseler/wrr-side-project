-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema wrrdb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema wrrdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `wrrdb` DEFAULT CHARACTER SET utf8mb4 ;
USE `wrrdb` ;

-- -----------------------------------------------------
-- Table `wrrdb`.`wod`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`wod` (
  `wod_id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `wod_name` VARCHAR(255) NULL,
  `team_size` INT NULL DEFAULT 1,
  `likes` INT NULL DEFAULT 0,
  `description` VARCHAR(255) NULL,
  PRIMARY KEY (`wod_id`),
  INDEX `fk_wod_user_idx` (`user_id` ASC) VISIBLE,
  UNIQUE INDEX `wod_id_UNIQUE` (`wod_id` ASC) VISIBLE,
  CONSTRAINT `fk_wod_user`
    FOREIGN KEY (`user_id`)
    REFERENCES `wrrdb`.`user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wrrdb`.`movement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`movement` (
  `movement_id` INT NOT NULL AUTO_INCREMENT,
  `movement_name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`movement_id`),
  UNIQUE INDEX `movement_id_UNIQUE` (`movement_id` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wrrdb`.`workout`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`workout` (
  `workout_id` BIGINT NOT NULL AUTO_INCREMENT,
  `wod_id` BIGINT NOT NULL,
  `type` VARCHAR(45) NULL,
  `seq` INT NULL,
  `parent_workout_id` BIGINT NOT NULL,
  PRIMARY KEY (`workout_id`),
  INDEX `fk_workout_wod1_idx` (`wod_id` ASC) VISIBLE,
  INDEX `fk_workout_workout1_idx` (`parent_workout_id` ASC) VISIBLE,
  CONSTRAINT `fk_workout_wod1`
    FOREIGN KEY (`wod_id`)
    REFERENCES `wrrdb`.`wod` (`wod_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_workout_workout1`
    FOREIGN KEY (`parent_workout_id`)
    REFERENCES `wrrdb`.`workout` (`workout_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wrrdb`.`workout_movement`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`workout_movement` (
  `workout_type_movement_id` INT NOT NULL AUTO_INCREMENT,
  `workout_id` BIGINT NOT NULL,
  `workout_movementcol` VARCHAR(45) NULL,
  `movement_id` INT NOT NULL,
  `reps_unit` VARCHAR(45) NULL,
  `reps_male` INT NULL DEFAULT 0,
  `reps_female` INT NULL DEFAULT 0,
  `weight_unit` VARCHAR(45) NULL,
  `weight_male` FLOAT NULL DEFAULT 0,
  `weight_female` FLOAT NULL DEFAULT 0,
  `synchronized` TINYINT NULL,
  `seq` INT NULL,
  PRIMARY KEY (`workout_type_movement_id`),
  INDEX `fk_wod_type_movement_movement1_idx` (`movement_id` ASC) VISIBLE,
  INDEX `fk_workout_type_movement_workout1_idx` (`workout_id` ASC) VISIBLE,
  CONSTRAINT `fk_wod_type_movement_movement1`
    FOREIGN KEY (`movement_id`)
    REFERENCES `wrrdb`.`movement` (`movement_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_workout_type_movement_workout1`
    FOREIGN KEY (`workout_id`)
    REFERENCES `wrrdb`.`workout` (`workout_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wrrdb`.`type_rft`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`type_rft` (
  `type_rft_id` INT NOT NULL AUTO_INCREMENT,
  `workout_id` BIGINT NOT NULL,
  `round` INT NULL,
  `time_cap` TIME NULL,
  PRIMARY KEY (`type_rft_id`),
  INDEX `fk_type_rft_workout1_idx` (`workout_id` ASC) VISIBLE,
  CONSTRAINT `fk_type_rft_workout1`
    FOREIGN KEY (`workout_id`)
    REFERENCES `wrrdb`.`workout` (`workout_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wrrdb`.`type_amrap`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`type_amrap` (
  `type_amrap_id` INT NOT NULL AUTO_INCREMENT,
  `workout_id` BIGINT NOT NULL,
  `time_cap` TIME NULL,
  PRIMARY KEY (`type_amrap_id`),
  INDEX `fk_type_amrap_workout1_idx` (`workout_id` ASC) VISIBLE,
  CONSTRAINT `fk_type_amrap_workout1`
    FOREIGN KEY (`workout_id`)
    REFERENCES `wrrdb`.`workout` (`workout_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wrrdb`.`type_onoff`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`type_onoff` (
  `type_onoff_id` INT NOT NULL AUTO_INCREMENT,
  `type_onoffcol` VARCHAR(45) NULL,
  `workout_id` BIGINT NOT NULL,
  `round` INT NULL,
  `time_cap` TIME NULL,
  `on_time` INT NULL,
  `off_time` INT NULL,
  PRIMARY KEY (`type_onoff_id`),
  INDEX `fk_type_onoff_workout1_idx` (`workout_id` ASC) VISIBLE,
  CONSTRAINT `fk_type_onoff_workout1`
    FOREIGN KEY (`workout_id`)
    REFERENCES `wrrdb`.`workout` (`workout_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `wrrdb`.`type_emom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `wrrdb`.`type_emom` (
  `type_emom_id` INT NOT NULL AUTO_INCREMENT,
  `workout_id` BIGINT NOT NULL,
  `round` INT NULL,
  `time_cap` TIME NULL,
  PRIMARY KEY (`type_emom_id`),
  INDEX `fk_type_emom_workout1_idx` (`workout_id` ASC) VISIBLE,
  CONSTRAINT `fk_type_emom_workout1`
    FOREIGN KEY (`workout_id`)
    REFERENCES `wrrdb`.`workout` (`workout_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
