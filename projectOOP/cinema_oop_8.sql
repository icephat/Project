-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 06, 2022 at 07:34 AM
-- Server version: 10.4.24-MariaDB
-- PHP Version: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cinema_oop`
--
CREATE DATABASE IF NOT EXISTS `cinema_oop` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `cinema_oop`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin` (
  `admin_id` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `admin_user` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `admin_psw` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `admin_user`, `admin_psw`) VALUES
('A01', 'admin.cinema@gmail.com', '123456789');

-- --------------------------------------------------------

--
-- Table structure for table `cycle`
--

DROP TABLE IF EXISTS `cycle`;
CREATE TABLE `cycle` (
  `cycle_id` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `cycle_time_start_end` varchar(11) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cycle`
--

INSERT INTO `cycle` (`cycle_id`, `cycle_time_start_end`) VALUES
('C01', '09:00-11:00'),
('C02', '12:00-15:00'),
('C03', '15:00-18:00');

-- --------------------------------------------------------

--
-- Table structure for table `cycle_composit`
--

DROP TABLE IF EXISTS `cycle_composit`;
CREATE TABLE `cycle_composit` (
  `cycle_com_id` int(5) NOT NULL,
  `cycle_id` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `cycle_date` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `room_id` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `movie_id` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `cycle_composit`
--

INSERT INTO `cycle_composit` (`cycle_com_id`, `cycle_id`, `cycle_date`, `room_id`, `movie_id`) VALUES
(7, 'C01', '2022-10-16', 'R01', 'M02'),
(8, 'C01', '2022-10-21', 'R02', 'M02'),
(9, 'C03', '2022-10-21', 'R02', 'M02'),
(20, 'C02', '2022-11-05', 'R01', 'M03'),
(21, 'C02', '2022-11-05', 'R02', 'M04');

-- --------------------------------------------------------

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
CREATE TABLE `member` (
  `member_id` int(11) NOT NULL,
  `user` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `fname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `lname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(10) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `member`
--

INSERT INTO `member` (`member_id`, `user`, `password`, `fname`, `lname`, `phone`) VALUES
(1, 'user.01@gmail.com', '987654321', 'user01', 'luser01', '0987654321'),
(2, 'user.02@gmail.com', '000000000', 'user02', 'Iuser02', '0996325896'),
(3, 'user03@mail.com', '111111111', 'pop', 'human', '0896325964'),
(4, 'user04@gmail.com', '147258369', 'saii', 'ami', '095963879');

-- --------------------------------------------------------

--
-- Table structure for table `movie`
--

DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `movie_id` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `movie_name` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `description` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `time` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `movie_pic` varchar(150) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `movie`
--

INSERT INTO `movie` (`movie_id`, `movie_name`, `description`, `time`, `movie_pic`) VALUES
('M02', 'movietest', 'Description', '120', 'D:\\cassava_image\\Variety\\3ca6a7ab-618e-4662-b24e-f873646c308b.png'),
('M03', 'The Croods', 'animation', '152', 'D:\\imagemovie\\The Croods.jpg'),
('M04', 'Despicable Me 3', 'comady', '150', 'D:\\imagemovie\\ima.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `room_id` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `room_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`room_id`, `room_name`) VALUES
('R01', 'room1'),
('R02', 'room2');

-- --------------------------------------------------------

--
-- Table structure for table `seat`
--

DROP TABLE IF EXISTS `seat`;
CREATE TABLE `seat` (
  `seat_id` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `seatPrice` int(11) NOT NULL,
  `ticket_id` int(11) NOT NULL,
  `member_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `ticket`
--

DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `ticket_id` int(11) NOT NULL,
  `cycle_id` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `movie_id` varchar(3) COLLATE utf8_unicode_ci DEFAULT NULL,
  `member_id` int(11) DEFAULT NULL,
  `ticket_seat` varchar(3) COLLATE utf8_unicode_ci NOT NULL,
  `ticket_price` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `ticket`
--

INSERT INTO `ticket` (`ticket_id`, `cycle_id`, `movie_id`, `member_id`, `ticket_seat`, `ticket_price`) VALUES
(83, 'C03', 'M02', 4, 'D5', 200),
(84, 'C03', 'M02', 4, 'D6', 200);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`admin_id`);

--
-- Indexes for table `cycle`
--
ALTER TABLE `cycle`
  ADD PRIMARY KEY (`cycle_id`);

--
-- Indexes for table `cycle_composit`
--
ALTER TABLE `cycle_composit`
  ADD PRIMARY KEY (`cycle_com_id`),
  ADD KEY `cycle_id` (`cycle_id`),
  ADD KEY `room_id` (`room_id`),
  ADD KEY `movie_id` (`movie_id`);

--
-- Indexes for table `member`
--
ALTER TABLE `member`
  ADD PRIMARY KEY (`member_id`);

--
-- Indexes for table `movie`
--
ALTER TABLE `movie`
  ADD PRIMARY KEY (`movie_id`);

--
-- Indexes for table `room`
--
ALTER TABLE `room`
  ADD PRIMARY KEY (`room_id`);

--
-- Indexes for table `seat`
--
ALTER TABLE `seat`
  ADD PRIMARY KEY (`seat_id`),
  ADD KEY `ticket_id` (`ticket_id`),
  ADD KEY `member_id` (`member_id`);

--
-- Indexes for table `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`ticket_id`),
  ADD KEY `cycle_id` (`cycle_id`),
  ADD KEY `movie_id` (`movie_id`),
  ADD KEY `member_id` (`member_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `cycle_composit`
--
ALTER TABLE `cycle_composit`
  MODIFY `cycle_com_id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `member`
--
ALTER TABLE `member`
  MODIFY `member_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `ticket`
--
ALTER TABLE `ticket`
  MODIFY `ticket_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=85;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `cycle_composit`
--
ALTER TABLE `cycle_composit`
  ADD CONSTRAINT `cycle_composit_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cycle_composit_ibfk_2` FOREIGN KEY (`cycle_id`) REFERENCES `cycle` (`cycle_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `cycle_composit_ibfk_3` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `seat`
--
ALTER TABLE `seat`
  ADD CONSTRAINT `seat_ibfk_1` FOREIGN KEY (`ticket_id`) REFERENCES `ticket` (`ticket_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `seat_ibfk_2` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`movie_id`) REFERENCES `movie` (`movie_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ticket_ibfk_2` FOREIGN KEY (`cycle_id`) REFERENCES `cycle` (`cycle_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `ticket_ibfk_3` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
