-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 04, 2023 at 08:06 PM
-- Server version: 10.4.25-MariaDB
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `boardgame`
--

-- --------------------------------------------------------

--
-- Table structure for table `boardgame`
--

CREATE TABLE `boardgame` (
  `boardgameId` int(11) NOT NULL,
  `description` text COLLATE utf8_unicode_ci NOT NULL,
  `playtime` int(11) NOT NULL,
  `age` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `type` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `price` float NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `imgPath` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `boardgamebought`
--

CREATE TABLE `boardgamebought` (
  `boardgameboughtId` int(11) NOT NULL,
  `boardgameId` int(11) NOT NULL,
  `buyboardgameId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `boardgamerental`
--

CREATE TABLE `boardgamerental` (
  `boardgamerentalId` int(11) NOT NULL,
  `datestart` date NOT NULL,
  `dateend` date NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `tel` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `boardgamerented`
--

CREATE TABLE `boardgamerented` (
  `boardgamerentedId` int(11) NOT NULL,
  `boardgameId` int(11) NOT NULL,
  `boardgamerentalId` int(11) NOT NULL,
  `rent` float NOT NULL,
  `deposit` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `bookingId` int(11) NOT NULL,
  `numberbooking` int(11) NOT NULL,
  `date` date NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `note` text COLLATE utf8_unicode_ci NOT NULL,
  `tel` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `tableId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`bookingId`, `numberbooking`, `date`, `name`, `note`, `tel`, `tableId`) VALUES
(1, 4, '2023-03-18', '', '', '0887598985', 1),
(2, 6, '2023-03-18', '', '', '0887598985', 2),
(3, 2, '2023-03-31', '', '', '0887598985', 2);

-- --------------------------------------------------------

--
-- Table structure for table `buyboardgame`
--

CREATE TABLE `buyboardgame` (
  `buyboardgameId` int(11) NOT NULL,
  `date` date NOT NULL,
  `address` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `type` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `tel` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `transferslip` varchar(150) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `manubooking`
--

CREATE TABLE `manubooking` (
  `manubookingId` int(11) NOT NULL,
  `menuId` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `tel` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `menu`
--

CREATE TABLE `menu` (
  `menuId` int(11) NOT NULL,
  `name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `price` float NOT NULL,
  `type` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `imgPath` varchar(150) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reservedgameboard`
--

CREATE TABLE `reservedgameboard` (
  `reservedgameboardId` int(11) NOT NULL,
  `bookingId` int(11) NOT NULL,
  `boardgameId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `table`
--

CREATE TABLE `table` (
  `tableId` int(11) NOT NULL,
  `status` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `table`
--

INSERT INTO `table` (`tableId`, `status`, `number`) VALUES
(1, 'available', 4),
(2, 'available', 4),
(3, 'available', 6),
(4, 'available', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `boardgame`
--
ALTER TABLE `boardgame`
  ADD PRIMARY KEY (`boardgameId`);

--
-- Indexes for table `boardgamebought`
--
ALTER TABLE `boardgamebought`
  ADD PRIMARY KEY (`boardgameboughtId`),
  ADD KEY `boardgameId` (`boardgameId`) USING BTREE,
  ADD KEY `buyboardgameId` (`buyboardgameId`);

--
-- Indexes for table `boardgamerental`
--
ALTER TABLE `boardgamerental`
  ADD PRIMARY KEY (`boardgamerentalId`);

--
-- Indexes for table `boardgamerented`
--
ALTER TABLE `boardgamerented`
  ADD PRIMARY KEY (`boardgamerentedId`),
  ADD KEY `boardgameId` (`boardgameId`) USING BTREE,
  ADD KEY `boardgamerentalId` (`boardgamerentalId`);

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD PRIMARY KEY (`bookingId`),
  ADD KEY `tableId` (`tableId`);

--
-- Indexes for table `buyboardgame`
--
ALTER TABLE `buyboardgame`
  ADD PRIMARY KEY (`buyboardgameId`);

--
-- Indexes for table `manubooking`
--
ALTER TABLE `manubooking`
  ADD PRIMARY KEY (`manubookingId`),
  ADD KEY `menuId` (`menuId`);

--
-- Indexes for table `menu`
--
ALTER TABLE `menu`
  ADD PRIMARY KEY (`menuId`);

--
-- Indexes for table `reservedgameboard`
--
ALTER TABLE `reservedgameboard`
  ADD PRIMARY KEY (`reservedgameboardId`),
  ADD KEY `bookingId` (`bookingId`),
  ADD KEY `boardgameId` (`boardgameId`);

--
-- Indexes for table `table`
--
ALTER TABLE `table`
  ADD PRIMARY KEY (`tableId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `boardgame`
--
ALTER TABLE `boardgame`
  MODIFY `boardgameId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `boardgamebought`
--
ALTER TABLE `boardgamebought`
  MODIFY `boardgameboughtId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `boardgamerental`
--
ALTER TABLE `boardgamerental`
  MODIFY `boardgamerentalId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `boardgamerented`
--
ALTER TABLE `boardgamerented`
  MODIFY `boardgamerentedId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `booking`
--
ALTER TABLE `booking`
  MODIFY `bookingId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `buyboardgame`
--
ALTER TABLE `buyboardgame`
  MODIFY `buyboardgameId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `manubooking`
--
ALTER TABLE `manubooking`
  MODIFY `manubookingId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `menu`
--
ALTER TABLE `menu`
  MODIFY `menuId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reservedgameboard`
--
ALTER TABLE `reservedgameboard`
  MODIFY `reservedgameboardId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `table`
--
ALTER TABLE `table`
  MODIFY `tableId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `boardgamebought`
--
ALTER TABLE `boardgamebought`
  ADD CONSTRAINT `boardgamebought_ibfk_1` FOREIGN KEY (`boardgameId`) REFERENCES `boardgame` (`boardgameId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `boardgamebought_ibfk_2` FOREIGN KEY (`buyboardgameId`) REFERENCES `buyboardgame` (`buyboardgameId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `boardgamerented`
--
ALTER TABLE `boardgamerented`
  ADD CONSTRAINT `boardgamerented_ibfk_1` FOREIGN KEY (`boardgameId`) REFERENCES `boardgame` (`boardgameId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `boardgamerented_ibfk_2` FOREIGN KEY (`boardgamerentalId`) REFERENCES `boardgamerental` (`boardgamerentalId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_2` FOREIGN KEY (`tableId`) REFERENCES `table` (`tableId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `manubooking`
--
ALTER TABLE `manubooking`
  ADD CONSTRAINT `manubooking_ibfk_2` FOREIGN KEY (`menuId`) REFERENCES `menu` (`menuId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reservedgameboard`
--
ALTER TABLE `reservedgameboard`
  ADD CONSTRAINT `reservedgameboard_ibfk_1` FOREIGN KEY (`bookingId`) REFERENCES `booking` (`bookingId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reservedgameboard_ibfk_2` FOREIGN KEY (`boardgameId`) REFERENCES `boardgame` (`boardgameId`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
