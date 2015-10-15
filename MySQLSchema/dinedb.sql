-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Oct 15, 2015 at 04:01 AM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `dinedb`
--

-- --------------------------------------------------------

--
-- Table structure for table `places`
--

CREATE TABLE IF NOT EXISTS `places` (
  `CID` int(11) NOT NULL AUTO_INCREMENT,
  `City_Name` varchar(20) NOT NULL,
  PRIMARY KEY (`CID`),
  UNIQUE KEY `CID` (`CID`),
  UNIQUE KEY `City_Name` (`City_Name`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `places`
--

INSERT INTO `places` (`CID`, `City_Name`) VALUES
(3, 'Bangalore'),
(1, 'Lucknow'),
(2, 'Mumbai');

-- --------------------------------------------------------

--
-- Table structure for table `rest`
--

CREATE TABLE IF NOT EXISTS `rest` (
  `RID` int(11) NOT NULL AUTO_INCREMENT,
  `CID` int(11) NOT NULL,
  `Rest_Name` varchar(40) NOT NULL,
  `URL` varchar(100) NOT NULL,
  PRIMARY KEY (`RID`),
  KEY `CID` (`CID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `rest`
--

INSERT INTO `rest` (`RID`, `CID`, `Rest_Name`, `URL`) VALUES
(1, 1, 'BBQ Nation', 'http://10.0.2.2/Dineapp/RestImages/BBQNation.jpg'),
(2, 3, 'California Pizza Kitchen', 'http://10.0.2.2/Dineapp/RestImages/California.jpg'),
(3, 3, 'Hanoi', 'http://10.0.2.2/Dineapp/RestImages/Hanoi.jpg'),
(4, 3, 'Hard Rock Cafe', 'http://10.0.2.2/Dineapp/RestImages/HRCBe.jpg'),
(5, 2, 'Hard Rock Cafe', 'http://10.0.2.2/Dineapp/RestImages/HRCMum.jpg'),
(6, 3, 'Little Italy', 'http://10.0.2.2/Dineapp/RestImages/LittleItaly.jpg'),
(7, 2, 'Mc Donald''s', 'http://10.0.2.2/Dineapp/RestImages/McDMum.jpg'),
(8, 3, 'Monkey Bar', 'http://10.0.2.2/Dineapp/RestImages/MonkeyBar.jpg'),
(9, 3, 'Purple Haze', 'http://10.0.2.2/Dineapp/RestImages/PurpleHaze.jpg'),
(10, 1, 'Royal Cafe', 'http://10.0.2.2/Dineapp/RestImages/RoyalCafe.jpg'),
(11, 3, 'Truffles', 'http://10.0.2.2/Dineapp/RestImages/Truffles.jpg');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `rest`
--
ALTER TABLE `rest`
  ADD CONSTRAINT `FK_REST_CID_CID` FOREIGN KEY (`CID`) REFERENCES `places` (`CID`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
