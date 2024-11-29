CREATE DATABASE IF NOT EXISTS `interview_system` DEFAULT CHARACTER SET utf8;

USE `interview_system`;

DROP TABLE IF EXISTS `interviewer`;

CREATE TABLE `interviewer` (
  `uid` int NOT NULL AUTO_INCREMENT,
  `uname` varchar(30) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `interviewee`;

CREATE TABLE `interviewee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `workerID` varchar(12) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(30) NOT NULL,
  `address` varchar(50) NOT NULL,
  `image` varchar(255),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;