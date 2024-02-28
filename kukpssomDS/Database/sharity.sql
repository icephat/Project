-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 01, 2023 at 09:47 AM
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
-- Database: `sharity`
--

-- --------------------------------------------------------

--
-- Table structure for table `activity`
--

CREATE TABLE `activity` (
  `activityId` int(11) NOT NULL,
  `nameActivity` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `userId` int(11) NOT NULL,
  `detail` text COLLATE utf8_unicode_ci NOT NULL,
  `place` text COLLATE utf8_unicode_ci NOT NULL,
  `numberPeople` int(11) NOT NULL,
  `dateOpen` date NOT NULL,
  `dateActivity` date NOT NULL,
  `status` enum('open','close') COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `activity`
--

INSERT INTO `activity` (`activityId`, `nameActivity`, `userId`, `detail`, `place`, `numberPeople`, `dateOpen`, `dateActivity`, `status`) VALUES
(1, 'ปิ้งย่างร้านไหนมันไม่เปิดเพลงเ', 3, 'ขอร้านที่ไม่เปิดเพลงเศร้าค้าบบบบบบบ', 'ร้านหมูทะหมูใจใยๆ', 10, '2023-03-05', '2023-03-10', NULL),
(4, 'ทริปเศร้าคนอกหัก', 1, 'อกหักจัดเลยดิ สมหน้าาาาาาา ไปเที่ยวเถอะนะ', 'น้ำตกไทรโยคน้อย', 2, '2023-03-05', '2023-03-10', NULL),
(6, 'ทริปบอร์ดเกม', 3, 'หาคนไปเล่นบอร์ดเกมครับ', 'TAO KA DEN', 5, '2023-03-05', '2023-03-10', NULL),
(7, 'after score', 2, 'สงสัยเขาคงไม่กลับมา', 'บ้าน', 2, '2023-04-01', '2023-04-20', NULL),
(8, 'after typeName', 2, 'สงสัยเขาคงไม่กลับมา', 'บ้าน5', 2, '2023-04-01', '2023-04-21', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `commentId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `activityId` int(11) NOT NULL,
  `detail` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `conditionactivity`
--

CREATE TABLE `conditionactivity` (
  `conditionId` int(11) NOT NULL,
  `conditionDetail` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `conditionactivity`
--

INSERT INTO `conditionactivity` (`conditionId`, `conditionDetail`) VALUES
(1, 'มีรถ'),
(2, 'มีเตาไฟฟ้า'),
(3, 'มีเต้นท์');

-- --------------------------------------------------------

--
-- Table structure for table `conditionofactivity`
--

CREATE TABLE `conditionofactivity` (
  `conditionOfActivityId` int(11) NOT NULL,
  `conditionId` int(11) NOT NULL,
  `activityId` int(11) NOT NULL,
  `numberPeopleInCondition` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `conditionofactivity`
--

INSERT INTO `conditionofactivity` (`conditionOfActivityId`, `conditionId`, `activityId`, `numberPeopleInCondition`) VALUES
(1, 1, 1, 1),
(2, 2, 1, 1),
(3, 1, 8, 0),
(4, 2, 8, 0);

-- --------------------------------------------------------

--
-- Table structure for table `imageactivity`
--

CREATE TABLE `imageactivity` (
  `imageId` int(11) NOT NULL,
  `imagePath` varchar(150) COLLATE utf8_unicode_ci NOT NULL,
  `activityId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `joinactivity`
--

CREATE TABLE `joinactivity` (
  `joinActivityId` int(11) NOT NULL,
  `activityId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `conditionId` int(11) DEFAULT NULL,
  `status` enum('apporve','reject','waiting') COLLATE utf8_unicode_ci NOT NULL,
  `score` int(11) DEFAULT NULL,
  `detailRequest` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `comment` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `dateRequest` date DEFAULT NULL,
  `dateApprove` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `joinactivity`
--

INSERT INTO `joinactivity` (`joinActivityId`, `activityId`, `userId`, `conditionId`, `status`, `score`, `detailRequest`, `comment`, `dateRequest`, `dateApprove`) VALUES
(1, 6, 1, 1, 'apporve', 4, NULL, NULL, NULL, '2023-03-17');

-- --------------------------------------------------------

--
-- Table structure for table `reportcomment`
--

CREATE TABLE `reportcomment` (
  `reportCommentId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `commentId` int(11) NOT NULL,
  `detail` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `typeactivity`
--

CREATE TABLE `typeactivity` (
  `typeId` int(11) NOT NULL,
  `typeName` varchar(30) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `typeactivity`
--

INSERT INTO `typeactivity` (`typeId`, `typeName`) VALUES
(1, 'โสด');

-- --------------------------------------------------------

--
-- Table structure for table `typeofactivity`
--

CREATE TABLE `typeofactivity` (
  `typeOfActivityId` int(11) NOT NULL,
  `typeId` int(11) NOT NULL,
  `activityId` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `typeofactivity`
--

INSERT INTO `typeofactivity` (`typeOfActivityId`, `typeId`, `activityId`) VALUES
(1, 1, 8);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userId` int(11) NOT NULL,
  `username` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `firstName` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `lastName` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `status` enum('active','nonactivie') COLLATE utf8_unicode_ci NOT NULL,
  `role` enum('user','admin') COLLATE utf8_unicode_ci NOT NULL,
  `address` text COLLATE utf8_unicode_ci NOT NULL,
  `skills` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userId`, `username`, `password`, `firstName`, `lastName`, `status`, `role`, `address`, `skills`) VALUES
(1, 'panuwat', '123456', 'Panuwat', 'Janjinda', 'active', 'user', 'กาญจนบุรี', 'สุดหล่อ'),
(2, 'Omegamon', '123456', 'โอเมกามอน', 'เมอซิฟูลโหมด', 'active', 'user', 'ดิจิตอลเวิร์ล', 'โอเมก้าอินฟอช'),
(3, 'phat', '2496', 'ภัทรพร', 'ปัญญาอุดมพร', 'active', 'user', '36/2', 'น่ารัก'),
(4, 'admin', '123456', 'admin2', 'admin', 'active', 'admin', '555', 'ดูแลระบบ'),
(5, 'adminnn', '789456', 'สตอเบอรี่', 'วันศุกร์', 'active', 'admin', '99/9', 'นอน');

-- --------------------------------------------------------

--
-- Table structure for table `userreport`
--

CREATE TABLE `userreport` (
  `reportId` int(11) NOT NULL,
  `userIdReport` int(11) NOT NULL,
  `userIdInReport` int(11) NOT NULL,
  `detail` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `activity`
--
ALTER TABLE `activity`
  ADD PRIMARY KEY (`activityId`),
  ADD KEY `userId` (`userId`) USING BTREE;

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`commentId`),
  ADD KEY `userId` (`userId`) USING BTREE,
  ADD KEY `activityId` (`activityId`) USING BTREE;

--
-- Indexes for table `conditionactivity`
--
ALTER TABLE `conditionactivity`
  ADD PRIMARY KEY (`conditionId`);

--
-- Indexes for table `conditionofactivity`
--
ALTER TABLE `conditionofactivity`
  ADD PRIMARY KEY (`conditionOfActivityId`),
  ADD KEY `conditionId` (`conditionId`) USING BTREE,
  ADD KEY `activityId` (`activityId`) USING BTREE;

--
-- Indexes for table `imageactivity`
--
ALTER TABLE `imageactivity`
  ADD PRIMARY KEY (`imageId`),
  ADD KEY `activityId` (`activityId`) USING BTREE;

--
-- Indexes for table `joinactivity`
--
ALTER TABLE `joinactivity`
  ADD PRIMARY KEY (`joinActivityId`),
  ADD KEY `activityId` (`activityId`) USING BTREE,
  ADD KEY `userId` (`userId`) USING BTREE,
  ADD KEY `conditionId` (`conditionId`);

--
-- Indexes for table `reportcomment`
--
ALTER TABLE `reportcomment`
  ADD PRIMARY KEY (`reportCommentId`),
  ADD KEY `userId` (`userId`) USING BTREE,
  ADD KEY `commentId` (`commentId`) USING BTREE;

--
-- Indexes for table `typeactivity`
--
ALTER TABLE `typeactivity`
  ADD PRIMARY KEY (`typeId`);

--
-- Indexes for table `typeofactivity`
--
ALTER TABLE `typeofactivity`
  ADD PRIMARY KEY (`typeOfActivityId`),
  ADD KEY `typeId` (`typeId`) USING BTREE,
  ADD KEY `activityId` (`activityId`) USING BTREE;

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userId`);

--
-- Indexes for table `userreport`
--
ALTER TABLE `userreport`
  ADD PRIMARY KEY (`reportId`),
  ADD KEY `userIdInReport` (`userIdInReport`),
  ADD KEY `userIdReport` (`userIdReport`,`userIdInReport`) USING BTREE;

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `activity`
--
ALTER TABLE `activity`
  MODIFY `activityId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `commentId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `conditionactivity`
--
ALTER TABLE `conditionactivity`
  MODIFY `conditionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `conditionofactivity`
--
ALTER TABLE `conditionofactivity`
  MODIFY `conditionOfActivityId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `imageactivity`
--
ALTER TABLE `imageactivity`
  MODIFY `imageId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `joinactivity`
--
ALTER TABLE `joinactivity`
  MODIFY `joinActivityId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `reportcomment`
--
ALTER TABLE `reportcomment`
  MODIFY `reportCommentId` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `typeactivity`
--
ALTER TABLE `typeactivity`
  MODIFY `typeId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `typeofactivity`
--
ALTER TABLE `typeofactivity`
  MODIFY `typeOfActivityId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `userreport`
--
ALTER TABLE `userreport`
  MODIFY `reportId` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `activity`
--
ALTER TABLE `activity`
  ADD CONSTRAINT `activity_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`activityId`) REFERENCES `activity` (`activityId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `conditionofactivity`
--
ALTER TABLE `conditionofactivity`
  ADD CONSTRAINT `conditionofactivity_ibfk_1` FOREIGN KEY (`conditionId`) REFERENCES `conditionactivity` (`conditionId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `conditionofactivity_ibfk_2` FOREIGN KEY (`activityId`) REFERENCES `activity` (`activityId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `imageactivity`
--
ALTER TABLE `imageactivity`
  ADD CONSTRAINT `imageactivity_ibfk_1` FOREIGN KEY (`activityId`) REFERENCES `activity` (`activityId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `joinactivity`
--
ALTER TABLE `joinactivity`
  ADD CONSTRAINT `joinactivity_ibfk_1` FOREIGN KEY (`activityId`) REFERENCES `activity` (`activityId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `joinactivity_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `joinactivity_ibfk_3` FOREIGN KEY (`conditionId`) REFERENCES `conditionactivity` (`conditionId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `reportcomment`
--
ALTER TABLE `reportcomment`
  ADD CONSTRAINT `reportcomment_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `reportcomment_ibfk_2` FOREIGN KEY (`commentId`) REFERENCES `comment` (`commentId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `typeofactivity`
--
ALTER TABLE `typeofactivity`
  ADD CONSTRAINT `typeofactivity_ibfk_1` FOREIGN KEY (`typeId`) REFERENCES `typeactivity` (`typeId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `typeofactivity_ibfk_2` FOREIGN KEY (`activityId`) REFERENCES `activity` (`activityId`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `userreport`
--
ALTER TABLE `userreport`
  ADD CONSTRAINT `userreport_ibfk_1` FOREIGN KEY (`userIdReport`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `userreport_ibfk_2` FOREIGN KEY (`userIdInReport`) REFERENCES `user` (`userId`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
