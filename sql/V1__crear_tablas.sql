CREATE TABLE `sala` (
  `id` int NOT NULL AUTO_INCREMENT,
  `capacidad` int DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `estado` bit(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB;


CREATE TABLE `reserva` (
  `id` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `hora_fin` time NOT NULL,
  `hora_inicio` time NOT NULL,
  `usuario` varchar(255) NOT NULL,
  `sala_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrb7m5fot7vm8u2d83p0im7ekw` (`sala_id`),
  CONSTRAINT `FKrb7m5fot7vm8u2d83p0im7ekw` FOREIGN KEY (`sala_id`) REFERENCES `sala` (`id`)
) ENGINE=InnoDB;


CREATE TABLE `usuario` (
  `usuario_id` int NOT NULL AUTO_INCREMENT,
  `apellidos` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `nom_completo` varchar(255) DEFAULT NULL,
  `nombres` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `rol` tinyint DEFAULT NULL,
  PRIMARY KEY (`usuario_id`),
  CONSTRAINT `usuario_chk_1` CHECK ((`rol` between 0 and 1))
) ENGINE=InnoDB;
