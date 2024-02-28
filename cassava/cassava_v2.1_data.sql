-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 16, 2022 at 09:46 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cassava`
--
CREATE DATABASE IF NOT EXISTS `cassava` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `cassava`;

-- --------------------------------------------------------

--
-- Table structure for table `api`
--

CREATE TABLE `api` (
  `apiID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `apidetail`
--

CREATE TABLE `apidetail` (
  `apiDetailID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `apiID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `apistaff`
--

CREATE TABLE `apistaff` (
  `apiStaffID` int(11) NOT NULL,
  `apiID` int(11) NOT NULL,
  `staffID` int(11) NOT NULL,
  `expireDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `disease`
--

CREATE TABLE `disease` (
  `diseaseID` int(11) NOT NULL,
  `code` varchar(10) DEFAULT NULL,
  `symptom` varchar(150) DEFAULT NULL,
  `controlDisease` text DEFAULT NULL,
  `controlPest` text DEFAULT NULL,
  `plantID` int(11) NOT NULL,
  `source` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='โรคพืช';

--
-- Dumping data for table `disease`
--

INSERT INTO `disease` (`diseaseID`, `code`, `symptom`, `controlDisease`, `controlPest`, `plantID`, `source`) VALUES
(1, 'AN', 'อาการ', 'การป้องกันกำจัดโรคแอนแทรคโนสนั้น นิยมใช้การเปลี่ยนพืชปลูกเพื่อลดแหล่งสะสมของโรค (Lozano et al., 1981) ใช้ท่อนพันธุ์มันสำปะหลังที่ปลอดโรค (Ikotun and Hanh, 1991) หรือการใช้เชื้อแบคทีเรีย Pseudomonas aeruginosa เพื่อควบคุมโรคแอนแทรคโนส (Goswami et al., 2015) รวมถึงการใช้มันสำปะหลังต้านทานโรคแอนแทรคโนส (Friend, 1981; Fukunang et al., 1999) เช่นมันสำปะหลังพันธุ์ห้วยบง 60', '-', 1, 'ที่มา กรมส่งเสริมการเกษตร. การจำแนกพันธุ์มันสำปะหลัง'),
(3, 'BD', 'ใบด่าง', 'การป้องกันกำจัดสามารถทำได้โดย ปลูกมันสำปะหลังพันธุ์ทนทานต่อโรค ได้แก่เกษตรศาสตร์ 50 และระยอง 72 ใช้ท่อนพันธุ์ที่ปลอดโรค หากพบโรคควรรีบทำลายต้นที่เป็นโรคออกนอกแปลงปลูกเพื่อลดแหล่งสะสมของเชื้อไวรัส และกำจัดแมลงหวี่ขาวยาสูบที่เป็นพาหะนำโรค ', 'พ่นด้วยสารเคมีกำจัดแมลงโอเมโทเอต 50% SL อัตรา 40 มล./น้ำ 20ลิตร โดยพ่นเมื่อพบแมลงหวี่ขาวมีความหนาแน่นทั้งต้นประมาณ 30% พ่นใต้ใบ 1-2 ครั้ง ห่างกัน 7-14 วัน (สุภราดา สุคนธาภิรมย์ ณ พัทลุง และคณะ, 2564)', 1, 'ที่มา กรมส่งเสริมการเกษตร. การจำแนกพันธุ์มันสำปะหลัง'),
(4, 'PJ', 'โรคพุ่มแจ้', 'แนวทางการป้องกันกำจัดโรคพุ่มแจ้คือการเลือกใช้ท่อนพันธุ์มันสำปะหลังปลอดโรค หากพบโรคควรทำลายต้นที่เป็นโรคทิ้งออกนอกแปลงปลูก รวมถึงการกำจัดวัชพืช', '-', 1, 'ที่มา กรมส่งเสริมการเกษตร. การจำแนกพันธุ์มันสำปะหลัง'),
(8, '', 'โรคใบจุดสีน้ำตาล', 'การป้องกันกำจัดสามารถทำได้โดย พ่นด้วยสารเคมีกำจัดโรคพืช Benomyl (Benlate 50% WP) อัตราการใช้ 15 กรัมต่อน้ำ 20 ลิตร โดยพ่น 10 วันต่อครั้ง ตั้งแต่มันสำปะหลังอายุ 5 เดือนขึ้นไป (อาจพ่น 3-4 ครั้ง ทั้งนี้ขึ้นกับความรุนแรงของโรค) (กองโรคพืชและจุลชีววิทยา กรมวิชาการเกษตร, 2535)', '', 1, 'ที่มา กรมส่งเสริมการเกษตร. การจำแนกพันธุ์มันสำปะหลัง'),
(23, '1', '1', '1', '1', 1, '1');

-- --------------------------------------------------------

--
-- Table structure for table `diseasepathogen`
--

CREATE TABLE `diseasepathogen` (
  `diseaseID` int(11) NOT NULL,
  `pathogenID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='เชื้อก่อโรค';

--
-- Dumping data for table `diseasepathogen`
--

INSERT INTO `diseasepathogen` (`diseaseID`, `pathogenID`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(3, 1),
(4, 1),
(4, 2),
(4, 3),
(4, 4),
(8, 2),
(23, 1);

-- --------------------------------------------------------

--
-- Table structure for table `diseasepest`
--

CREATE TABLE `diseasepest` (
  `diseaseID` int(11) NOT NULL,
  `pestID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='แมลงก่อโรค';

--
-- Dumping data for table `diseasepest`
--

INSERT INTO `diseasepest` (`diseaseID`, `pestID`) VALUES
(1, 1),
(4, 1),
(8, 1),
(23, 1);

-- --------------------------------------------------------

--
-- Table structure for table `district`
--

CREATE TABLE `district` (
  `districtID` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `code` varchar(10) DEFAULT NULL,
  `provinceID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='อำเภอ';

--
-- Dumping data for table `district`
--

INSERT INTO `district` (`districtID`, `name`, `code`, `provinceID`) VALUES
(1, 'ตลิ่งชัน', 'TC', 1),
(2, 'บางรัก', 'BR', 1),
(3, 'บ้านบึง', 'BB', 4),
(4, 'ศรีราชา', 'SC', 4),
(5, 'บางเลน', 'BL', 3),
(6, 'กำแพงแสน', 'KS', 3),
(7, 'แกลง', 'KL', 2),
(8, 'ปลวกแดง', 'PD', 2);

-- --------------------------------------------------------

--
-- Table structure for table `farmer`
--

CREATE TABLE `farmer` (
  `farmerID` int(11) NOT NULL,
  `IDcard` varchar(13) DEFAULT NULL,
  `address` varchar(100) NOT NULL,
  `subdistrictID` int(11) DEFAULT NULL,
  `PDPAStatus` enum('Yes','No') NOT NULL DEFAULT 'No',
  `PDPAValidDate` date DEFAULT NULL,
  `PDPAFilePath` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `farmer`
--

INSERT INTO `farmer` (`farmerID`, `IDcard`, `address`, `subdistrictID`, `PDPAStatus`, `PDPAValidDate`, `PDPAFilePath`) VALUES
(3, NULL, 'aa', 1, 'No', NULL, NULL),
(4, NULL, 'aa', NULL, 'No', NULL, NULL),
(5, NULL, 'aa', 1, 'No', NULL, NULL),
(6, NULL, 'aa', NULL, 'No', NULL, NULL),
(7, NULL, 'aa', NULL, 'No', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `farmerorganization`
--

CREATE TABLE `farmerorganization` (
  `farmerID` int(11) NOT NULL,
  `organizationID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='หน่วยงานเกษตรกร';

--
-- Dumping data for table `farmerorganization`
--

INSERT INTO `farmerorganization` (`farmerID`, `organizationID`) VALUES
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1);

-- --------------------------------------------------------

--
-- Table structure for table `field`
--

CREATE TABLE `field` (
  `fieldID` int(11) NOT NULL,
  `organizationID` int(11) NOT NULL,
  `code` varchar(50) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `address` varchar(100) NOT NULL,
  `road` varchar(30) DEFAULT NULL,
  `moo` varchar(10) DEFAULT NULL,
  `subdistrictID` int(11) NOT NULL,
  `landmark` varchar(100) DEFAULT NULL,
  `latitude` float NOT NULL,
  `longitude` float NOT NULL,
  `metresAboveSeaLv` float DEFAULT NULL,
  `size` float NOT NULL,
  `imgPath` varchar(150) DEFAULT NULL,
  `status` enum('ใช้งาน','ยกเลิก') NOT NULL DEFAULT 'ใช้งาน',
  `createBy` int(11) NOT NULL,
  `createDate` datetime NOT NULL DEFAULT current_timestamp(),
  `lastUpdateBy` int(11) DEFAULT NULL,
  `lastUpdateDate` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='แปลงปลูก';

--
-- Dumping data for table `field`
--

INSERT INTO `field` (`fieldID`, `organizationID`, `code`, `name`, `address`, `road`, `moo`, `subdistrictID`, `landmark`, `latitude`, `longitude`, `metresAboveSeaLv`, `size`, `imgPath`, `status`, `createBy`, `createDate`, `lastUpdateBy`, `lastUpdateDate`) VALUES
(1, 1, 'a', '1', '1', '1', '1', 11, '1', 1, 1, 1, 1, NULL, 'ใช้งาน', 1, '2022-12-16 15:40:44', NULL, NULL),
(2, 1, '2', '2', '2', '2', '2', 10, '2', 2, 2, 2, 2, NULL, 'ใช้งาน', 1, '2022-12-16 15:41:00', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `herbicide`
--

CREATE TABLE `herbicide` (
  `herbicideID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='สารเคมีกำจัดวัชพืช';

--
-- Dumping data for table `herbicide`
--

INSERT INTO `herbicide` (`herbicideID`, `name`) VALUES
(1, 'อะเซโทคลอร์'),
(2, 'อะลาคลอร์'),
(3, 'ฟลูมิออกซาซิน'),
(4, 'เอส-เมโทลาคลอร์'),
(5, 'ไกลโฟเสท'),
(6, 'กลูโฟซิเนตแอมโมเนียม'),
(7, 'ไดยูรอน'),
(8, 'ใช้แรงงาน'),
(9, 'อื่นๆ');

-- --------------------------------------------------------

--
-- Table structure for table `imgdisease`
--

CREATE TABLE `imgdisease` (
  `imgDiseaseID` int(11) NOT NULL,
  `diseaseID` int(11) NOT NULL,
  `filePath` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ภาพโรคพืช';

--
-- Dumping data for table `imgdisease`
--

INSERT INTO `imgdisease` (`imgDiseaseID`, `diseaseID`, `filePath`) VALUES
(1, 1, '8c344079-b447-4d8d-907e-6a9127b6d64d.jpg'),
(2, 4, '1e5e6d8f-4a5e-410c-bb56-6812359c82ef.jpg'),
(3, 1, 'e2e02336-a8c8-4e60-9118-5a49a5157fc0.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `imgherbicidedamage`
--

CREATE TABLE `imgherbicidedamage` (
  `imgHerbicideDamageID` int(11) NOT NULL,
  `surveyID` int(11) NOT NULL,
  `filePath` varchar(150) NOT NULL,
  `filePathS` varchar(150) NOT NULL,
  `uploadBy` int(11) DEFAULT NULL,
  `uploadDate` datetime NOT NULL DEFAULT current_timestamp(),
  `approveStatus` enum('Waiting','Approved','Reject') NOT NULL DEFAULT 'Waiting',
  `approveBy` int(11) DEFAULT NULL,
  `approveDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `imgnaturalenemy`
--

CREATE TABLE `imgnaturalenemy` (
  `imgNaturalEnemyID` int(11) NOT NULL,
  `naturalEnemyID` int(11) NOT NULL,
  `filePath` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ภาพศัตรูธรรมชาติ';

--
-- Dumping data for table `imgnaturalenemy`
--

INSERT INTO `imgnaturalenemy` (`imgNaturalEnemyID`, `naturalEnemyID`, `filePath`) VALUES
(1, 15, '4cd8a0d7-c104-45a2-ac4c-9c68f10e8ae2.jpg'),
(2, 15, 'e005b3c8-be0d-4622-b2a5-b1dcead7f1cc.jpg'),
(3, 16, '2438cbb6-14ce-46d3-89b6-b554415872b9.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `imgpest`
--

CREATE TABLE `imgpest` (
  `imgPestID` int(11) NOT NULL,
  `pestID` int(11) NOT NULL,
  `filePath` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ภาพแมลงศัตรูพืช';

--
-- Dumping data for table `imgpest`
--

INSERT INTO `imgpest` (`imgPestID`, `pestID`, `filePath`) VALUES
(2, 1, 'b1e3988b-078c-49a5-9fec-b66fdc1b0f5c.jpg'),
(4, 1, '87336c4e-7a5c-4d78-b0b8-fd37ab9239d9.jpg'),
(5, 3, '2e602553-6c73-4686-9b99-cbcd014343e9.jpg'),
(6, 3, '10373b27-9d89-404b-88b9-18867daef259.jpg'),
(7, 2, '52099b7f-ba51-464a-819a-8f0ba848baea.jpg'),
(8, 2, 'b27113e3-f791-478e-9b7c-1c2a0e5955bd.jpg'),
(9, 2, '52f70756-8e80-4781-a3b3-68e2706be28f.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `imgsurveytargetpoint`
--

CREATE TABLE `imgsurveytargetpoint` (
  `imgSurveyTargetPointID` int(11) NOT NULL,
  `surveyTargetPointID` int(11) NOT NULL,
  `filePath` varchar(150) NOT NULL,
  `filePathS` varchar(150) NOT NULL,
  `uploadBy` int(11) DEFAULT NULL,
  `uploadDate` datetime NOT NULL DEFAULT current_timestamp(),
  `approveStatus` enum('Waiting','Approved','Reject') NOT NULL DEFAULT 'Waiting',
  `approveBy` int(11) DEFAULT NULL,
  `approveDate` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ภาพสิ่งสำรวจตามจุดสำรวจ';

-- --------------------------------------------------------

--
-- Table structure for table `imgvariety`
--

CREATE TABLE `imgvariety` (
  `imgVarietyID` int(11) NOT NULL,
  `varietyID` int(11) NOT NULL,
  `filePath` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ภาพพันธุ์พืช';

--
-- Dumping data for table `imgvariety`
--

INSERT INTO `imgvariety` (`imgVarietyID`, `varietyID`, `filePath`) VALUES
(5, 1, '96b4b45a-aa54-44e1-86e2-2f2093535931.png'),
(7, 1, 'a6479add-d88d-45c1-90c8-7daa3359cc37.png'),
(8, 1, 'e5b0a382-fc3c-4995-b616-8ac5214931a4.png'),
(19, 15, 'f81a0afe-c966-467d-adc5-73b7aadcae1c.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `messageID` int(11) NOT NULL,
  `sender` int(11) DEFAULT NULL,
  `sendDate` datetime NOT NULL DEFAULT current_timestamp(),
  `title` varchar(100) DEFAULT NULL,
  `text` text DEFAULT NULL,
  `type` enum('Message','Warning','Alert') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ข้อความ';

-- --------------------------------------------------------

--
-- Table structure for table `messagereceiver`
--

CREATE TABLE `messagereceiver` (
  `messageID` int(11) NOT NULL,
  `receiverID` int(11) NOT NULL,
  `readStatus` enum('Y','N') NOT NULL DEFAULT 'N'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ผู้รับข้อคาม';

-- --------------------------------------------------------

--
-- Table structure for table `naturalenemy`
--

CREATE TABLE `naturalenemy` (
  `naturalEnemyID` int(11) NOT NULL,
  `commonName` varchar(50) NOT NULL,
  `scientificName` varchar(70) NOT NULL,
  `type` enum('ตัวห้ำ','ตัวเบียน') DEFAULT NULL,
  `controlMethod` varchar(255) DEFAULT NULL,
  `releaseRate` varchar(255) DEFAULT NULL,
  `source` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ศัตรูธรรมชาติ';

--
-- Dumping data for table `naturalenemy`
--

INSERT INTO `naturalenemy` (`naturalEnemyID`, `commonName`, `scientificName`, `type`, `controlMethod`, `releaseRate`, `source`) VALUES
(15, 'Anagyrus', 'Anagyrus lopezi', 'ตัวเบียน', 'เพลี้ยแป้งมันสำปะหลังสีชมพู ', 'อัตราปล่อย 50-100 คู่ต่อไร่ หากพบเพลี้ยแป้งมันสำปะหลังสีชมพูระบาดรุนแรงให้ปล่อย 200 คู่ต่อไร่', ''),
(16, 'green lacewing', 'Plesiochrysa ramburi (Schneider)', 'ตัวห้ำ', 'ตัวอ่อนเป็นตัวห้ำที่สามารถกินศัตรูพืชได้เช่น เพลี้ยแป้ง ตัวอ่อนเพลี้ยหอย ไรแดง ', '1.ไม่พบการระบาดของเพลี้ยแป้ง ปล่อยตัวเต็มวัย 100-200 ตัว/ไร่ 2. พบการระบาดของเพลี้ยแป้ง <20 ตัวต่อต้น ปล่อยตัวเต็มวัย 300-500 ตัวต่อไร่ 3. พบการระบาดของเพลี้ยแป้ง >20 ตัวต่อต้น ปล่อยตัวเต็มวัย 500-1000 ตัวต่อไร่', 'https://www.doa.go.th/share/attachment.php?aid=2675');

-- --------------------------------------------------------

--
-- Table structure for table `organization`
--

CREATE TABLE `organization` (
  `organizationID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `code` varchar(10) NOT NULL,
  `accessInfoLevel` enum('ภายในหน่วยงาน','ทั้งหมด') NOT NULL DEFAULT 'ภายในหน่วยงาน' COMMENT 'ระดับการเข้าถึงข้อมูล'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='หน่วยงาน';

--
-- Dumping data for table `organization`
--

INSERT INTO `organization` (`organizationID`, `name`, `phone`, `code`, `accessInfoLevel`) VALUES
(1, 'ภาควิชาโรคพืช ม.เกษตรศาสตร์ กำแพงแสน', '456', 'agri', 'ภายในหน่วยงาน'),
(2, 'ศูนย์วิจัยพืชไร่ จ.ระยอง', '123456', 'rayong', 'ภายในหน่วยงาน'),
(3, 'ภาควิชาวิศวกรรมคอมพิวเตอร์ คณะวิศวกรรมศาสตร์ ', '123', 'cpe', 'ภายในหน่วยงาน');

-- --------------------------------------------------------

--
-- Table structure for table `pathogen`
--

CREATE TABLE `pathogen` (
  `pathogenID` int(11) NOT NULL,
  `scientificName` varchar(70) NOT NULL,
  `pathogenTypeID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='เชื้อโรคพืช';

--
-- Dumping data for table `pathogen`
--

INSERT INTO `pathogen` (`pathogenID`, `scientificName`, `pathogenTypeID`) VALUES
(1, 'Sri Lankan cassava mosaic virus', 2),
(2, 'Candidatus Phytoplasma', 3),
(3, 'Xanthomnas axonopodis pv. manihotis', 5),
(4, 'Colletotrichum gloeosporioides f.sp. manihotis', 1);

-- --------------------------------------------------------

--
-- Table structure for table `pathogentype`
--

CREATE TABLE `pathogentype` (
  `pathogenTypeID` int(11) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ชนิดเชื้อโรคพืช';

--
-- Dumping data for table `pathogentype`
--

INSERT INTO `pathogentype` (`pathogenTypeID`, `name`) VALUES
(1, 'เชื้อรา'),
(2, 'เชื้อไวรัส'),
(3, 'เชื้อไฟโตพลาสมา'),
(4, 'ไส้เดือนฝอย'),
(5, 'แบคทีเรีย');

-- --------------------------------------------------------

--
-- Table structure for table `permission`
--

CREATE TABLE `permission` (
  `permisisonID` int(11) NOT NULL,
  `type` enum('inOrganize','allOrganize') NOT NULL,
  `staffID` int(11) NOT NULL,
  `dateInfoStart` date NOT NULL,
  `dateInfoEnd` date NOT NULL,
  `dateRequest` datetime NOT NULL,
  `dateApprove` datetime DEFAULT NULL,
  `dateExpire` datetime DEFAULT NULL,
  `approveBy` int(11) DEFAULT NULL,
  `status` enum('Waiting','Approve','Reject') NOT NULL DEFAULT 'Waiting'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `permissiondisease`
--

CREATE TABLE `permissiondisease` (
  `permissionDiseaseID` int(11) NOT NULL,
  `permissionID` int(11) NOT NULL,
  `diseaaseID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `permissionfile`
--

CREATE TABLE `permissionfile` (
  `permissionFileID` int(11) NOT NULL,
  `filePath` varchar(150) NOT NULL,
  `permissionID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `permissionprovince`
--

CREATE TABLE `permissionprovince` (
  `permisisonProvinceID` int(11) NOT NULL,
  `permissionID` int(11) NOT NULL,
  `provinceID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `pest`
--

CREATE TABLE `pest` (
  `pestID` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `scientificName` varchar(70) NOT NULL,
  `source` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='แมลงศัตรูพืช';

--
-- Dumping data for table `pest`
--

INSERT INTO `pest` (`pestID`, `name`, `scientificName`, `source`) VALUES
(1, 'ไรแดงหม่อน', 'Tetranychus truncatus', 'กรมส่งเสริมการเกษตร. การจำแนกพันธุ์มันสำปะหลัง. บริษัท ซัน แพคเกจจิ้ง (2014) จำกัด, กรมส่งเสริมการเกษตร, 2559. 96 หน้า.'),
(2, 'แมลงหวี่ขาวยาสูบ', 'Bemesia tabaci', 'ที่มา กรมส่งเสริมการเกษตร. การจำแนกพันธุ์มันสำปะหลัง. บริษัท ซัน แพคเกจจิ้ง (2014) จำกัด, กรมส่งเสริมการเกษตร, 2559. 96 หน้า.'),
(3, 'เพลี้ยหอยขาว', 'Aonidomytilus albus', 'กรมส่งเสริมการเกษตร. การจำแนกพันธุ์มันสำปะหลัง. บริษัท ซัน แพคเกจจิ้ง (2014) จำกัด, กรมส่งเสริมการเกษตร, 2559. 96 หน้า.'),
(6, 'a', 'a', 'a'),
(7, 's', 's', 's'),
(8, 'e', 'e', 'e');

-- --------------------------------------------------------

--
-- Table structure for table `pestmanagement`
--

CREATE TABLE `pestmanagement` (
  `pestManagementID` int(11) NOT NULL,
  `pestID` int(11) NOT NULL,
  `pesticideName` varchar(30) NOT NULL,
  `pesticideNameEng` varchar(30) NOT NULL,
  `percentAPI` varchar(20) NOT NULL,
  `pesticideMechanism` varchar(20) NOT NULL,
  `toxicityLevel` varchar(30) NOT NULL,
  `mixingRatio` varchar(30) NOT NULL,
  `instruction` enum('ฉีดพ่นเฉพาะจุด','แช่ท่อนพันธุ์') NOT NULL,
  `instructionDetail` text NOT NULL,
  `note` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='การกำจัดแมลงศัตรูพืช';

--
-- Dumping data for table `pestmanagement`
--

INSERT INTO `pestmanagement` (`pestManagementID`, `pestID`, `pesticideName`, `pesticideNameEng`, `percentAPI`, `pesticideMechanism`, `toxicityLevel`, `mixingRatio`, `instruction`, `instructionDetail`, `note`) VALUES
(1, 1, 'เฮกซีไทอะซอกซ์', 'hexythiazox', '1.8% EC', '10A', 'ไม่มีพิษเฉียบพลัน', '100มล./น้ำ20 ลิตร', 'ฉีดพ่นเฉพาะจุด', 'พ่นเมื่อพบไรแดงทำลายบริเวณใบส่วนยอด และใบส่วนล่างเริ่มแสดงอาการเหี่ยวโดยเฉพาะพืชยังเล็กพ่นให้ทั่วทั้งต้น ใต้ใบและบนใบจำนวน 1-2 ครั้ง ห่างกัน 10 วัน', ''),
(2, 1, 'ทีบูเฟนไพแรด', 'tebufenpyrad', '36%', '21A', 'ปานกลาง', '5-10มล./น้ำ20', 'ฉีดพ่นเฉพาะจุด', 'พ่นเมื่อพบไรแดงทำลายบริเวณใบส่วนยอด', 'ไรแดงและแมลงหวี่ขาว'),
(5, 6, '', '', '', '', '', '', 'ฉีดพ่นเฉพาะจุด', '', ''),
(6, 6, '', '', '', '', '', '', 'ฉีดพ่นเฉพาะจุด', '', '');

-- --------------------------------------------------------

--
-- Table structure for table `pestphase`
--

CREATE TABLE `pestphase` (
  `pestPhaseID` int(11) NOT NULL,
  `name` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ระยะแมลงศัตรูพืช';

--
-- Dumping data for table `pestphase`
--

INSERT INTO `pestphase` (`pestPhaseID`, `name`) VALUES
(1, 'ไข่'),
(2, 'ตัวอ่อน'),
(3, 'ตัวเต็มวัย');

-- --------------------------------------------------------

--
-- Table structure for table `pestphasesurvey`
--

CREATE TABLE `pestphasesurvey` (
  `pestPhaseSurveyID` int(11) NOT NULL,
  `pestID` int(11) NOT NULL,
  `pestPhaseID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ระยะแมลงศัตรูพืชในการสำรวจ';

--
-- Dumping data for table `pestphasesurvey`
--

INSERT INTO `pestphasesurvey` (`pestPhaseSurveyID`, `pestID`, `pestPhaseID`) VALUES
(5, 1, 1),
(6, 1, 2),
(7, 1, 3),
(9, 2, 1),
(10, 2, 2),
(11, 2, 3),
(12, 3, 1),
(13, 3, 2),
(14, 3, 3),
(24, 6, 1),
(25, 6, 2),
(26, 6, 3),
(27, 7, 1),
(28, 7, 2),
(29, 7, 3),
(30, 8, 1),
(31, 8, 2),
(32, 8, 3);

-- --------------------------------------------------------

--
-- Table structure for table `plant`
--

CREATE TABLE `plant` (
  `plantID` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `harvestDayStart` int(11) NOT NULL,
  `harvestDayEnd` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='พันธุ์พืช';

--
-- Dumping data for table `plant`
--

INSERT INTO `plant` (`plantID`, `name`, `harvestDayStart`, `harvestDayEnd`) VALUES
(1, 'มันสำปะหลัง', 241, 360);

-- --------------------------------------------------------

--
-- Table structure for table `planting`
--

CREATE TABLE `planting` (
  `plantingID` int(11) NOT NULL,
  `fieldID` int(11) NOT NULL,
  `code` varchar(25) DEFAULT NULL,
  `name` varchar(60) DEFAULT NULL,
  `size` float DEFAULT NULL,
  `previousPlant` enum('มันสำปะหลัง','สับปะรด','อ้อย','อื่นๆ') NOT NULL,
  `previousPlantOther` varchar(30) DEFAULT NULL,
  `besidePlant` enum('มันสำปะหลัง','ยางพารา','สับปะรด','อ้อย','อื่นๆ') NOT NULL,
  `besidePlantOther` varchar(30) DEFAULT NULL,
  `primaryVarietyOther` varchar(30) DEFAULT NULL,
  `primaryPlantPlantingDate` date NOT NULL,
  `primaryPlantHarvestDate` date NOT NULL,
  `secondaryPlantType` varchar(20) DEFAULT NULL,
  `secondaryPlantVariety` varchar(30) DEFAULT NULL,
  `secondaryPlantPlantingDate` date DEFAULT NULL,
  `secondaryPlantHarvestDate` date DEFAULT NULL,
  `stemSource` enum('ซื้อ','เก็บท่อนพันธุ์เอง') NOT NULL,
  `soakingStemChemical` enum('ไทอะมีโทแซม','อิมิดราคลอพริด','มาลาไทออน','ไม่แช่') NOT NULL COMMENT 'สารเคมีกำจัดแมลงสำหรับแช่ท่อนพันธุ์ก่อนปลูก',
  `numTillage` enum('1','2','3','4') NOT NULL COMMENT 'จำนวนครั้งการไถเตรียมแปลง',
  `soilAmendments` enum('กากมันสำปะหลัง','มูลไก่แกลบ','ปูนขาว','อื่นๆ') NOT NULL COMMENT 'การใช้วัสดุปรับปรุงดิน',
  `soilAmendmentsOther` varchar(50) DEFAULT NULL,
  `fertilizerDate1` date DEFAULT NULL,
  `fertilizerFormular1` varchar(20) DEFAULT NULL,
  `fertilizerDate2` date DEFAULT NULL,
  `fertilizerFormular2` varchar(20) DEFAULT NULL,
  `fertilizerDate3` date DEFAULT NULL,
  `fertilizerFormular3` varchar(20) DEFAULT NULL,
  `diseaseManagement` enum('ใช้สารเคมี','ชีววิธี','วิธีกล','ไม่จัดการ') DEFAULT NULL,
  `pestManagement` enum('ใช้สารเคมี','ชีววิธี','วิธีกล','ไม่จัดการ') DEFAULT NULL,
  `weedingMonth1` int(11) NOT NULL COMMENT 'เดือนกำจัดวัชพืช1',
  `weedingChemical1` int(11) DEFAULT NULL,
  `weedingChemicalOther1` varchar(30) DEFAULT NULL,
  `weedingMonth2` int(11) DEFAULT NULL,
  `weedingChemical2` int(11) DEFAULT NULL,
  `weedingChemicalOther2` varchar(30) DEFAULT NULL,
  `weedingMonth3` int(11) DEFAULT NULL,
  `weedingChemical3` int(11) DEFAULT NULL,
  `weedingChemicalOther3` varchar(30) DEFAULT NULL,
  `note` text NOT NULL,
  `createBy` int(11) NOT NULL,
  `createDate` datetime NOT NULL DEFAULT current_timestamp(),
  `lastUpdateBy` int(11) DEFAULT NULL,
  `lastUpdateDate` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='การเพาะปลูก';

-- --------------------------------------------------------

--
-- Table structure for table `plantingcassavavariety`
--

CREATE TABLE `plantingcassavavariety` (
  `plantingID` int(11) NOT NULL,
  `varietyID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='พันธุ์มันสำปะหลังในการเพาะปลูก';

-- --------------------------------------------------------

--
-- Table structure for table `plantphase`
--

CREATE TABLE `plantphase` (
  `plantPhaseID` int(11) NOT NULL,
  `plantID` int(11) NOT NULL,
  `order` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `dayStart` int(11) NOT NULL,
  `dayEnd` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ระยะการเติบโตของพืช';

--
-- Dumping data for table `plantphase`
--

INSERT INTO `plantphase` (`plantPhaseID`, `plantID`, `order`, `name`, `dayStart`, `dayEnd`) VALUES
(1, 1, 1, 'น้อยกว่า 3 เดือน', 1, 90),
(2, 1, 2, '3-5 เดือน', 91, 150),
(3, 1, 3, '6-8 เดือน', 151, 240),
(4, 1, 4, 'มากกว่า 8 เดือน', 241, 360);

-- --------------------------------------------------------

--
-- Table structure for table `province`
--

CREATE TABLE `province` (
  `provinceID` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `code` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='จังหวัด';

--
-- Dumping data for table `province`
--

INSERT INTO `province` (`provinceID`, `name`, `code`) VALUES
(1, 'กรุงเทพ', 'ฺฺBKK'),
(2, 'ระยอง', 'RY'),
(3, 'นครปฐม', 'ฺฺNP'),
(4, 'ชลบุรี', 'CB');

-- --------------------------------------------------------

--
-- Table structure for table `registercode`
--

CREATE TABLE `registercode` (
  `registerCodeID` int(11) NOT NULL,
  `code` varchar(20) NOT NULL,
  `userType` enum('Staff','Farmer') NOT NULL,
  `organizationID` int(11) NOT NULL,
  `numUserPermit` int(11) NOT NULL DEFAULT 1,
  `numUseRegist` int(11) NOT NULL,
  `startDate` datetime NOT NULL,
  `expireDate` datetime NOT NULL,
  `createBy` int(11) DEFAULT NULL,
  `createDate` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='รหัสลงทะเบียน';

-- --------------------------------------------------------

--
-- Table structure for table `request`
--

CREATE TABLE `request` (
  `requestID` int(11) NOT NULL,
  `type` enum('inOrganization','allOrganization') NOT NULL,
  `staffID` int(11) NOT NULL,
  `dateRequest` datetime NOT NULL DEFAULT current_timestamp(),
  `requestNote` varchar(255) DEFAULT NULL,
  `status` enum('Waiting','Approve','Reject') NOT NULL DEFAULT 'Waiting',
  `approveBy` int(11) DEFAULT NULL,
  `dateApprove` datetime DEFAULT NULL,
  `dateExpire` date DEFAULT NULL,
  `statusDAE` enum('Waiting','Approve','Reject') DEFAULT 'Waiting' COMMENT 'กรมส่งเสริมการเกษตร',
  `approveByDAE` int(11) DEFAULT NULL,
  `statusDA` enum('Waiting','Approve','Reject') DEFAULT 'Waiting' COMMENT 'กรมวิชาการเกษตร',
  `approveByDA` int(11) DEFAULT NULL,
  `statusDPPatho` enum('Waiting','Approve','Reject') DEFAULT 'Waiting' COMMENT 'ภาควิชาโรคพืช มก.',
  `approveByDPPhato` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='การขอข้อมูล';

-- --------------------------------------------------------

--
-- Table structure for table `requestdetail`
--

CREATE TABLE `requestdetail` (
  `requestDetailID` int(11) NOT NULL,
  `requestID` int(11) NOT NULL,
  `surveyID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='รายละเอียดการขอข้อมูล';

-- --------------------------------------------------------

--
-- Table structure for table `requestfile`
--

CREATE TABLE `requestfile` (
  `requestFileID` int(11) NOT NULL,
  `filePath` varchar(150) NOT NULL,
  `requestID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `roleID` int(11) NOT NULL,
  `nameEng` varchar(30) NOT NULL,
  `nameTH` varchar(30) NOT NULL,
  `userDefine` enum('Y','N') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='หน้าที่';

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`roleID`, `nameEng`, `nameTH`, `userDefine`) VALUES
(1, 'waitingApprove', 'ผู้ใข้รออนุมัติ', 'N'),
(2, 'staff', 'เจ้าหน้าที่', 'N'),
(3, 'farmer', 'เกษตรกร', 'N'),
(4, 'systemAdmin', 'ผู้ดูแลระบบ', 'Y'),
(5, 'infoAdmin', 'ผู้ดูแลข้อมูล', 'Y'),
(6, 'fieldRegistrar', 'ผู้จัดการข้อมูลแปลง', 'N'),
(7, 'fieldResponsible', 'ผู้รับผิดชอบแปลง', 'N'),
(8, 'fieldSurvey', 'ผู้สำรวจแปลง', 'N'),
(9, 'farmerOwner', 'เกษตรกรเจ้าของแปลง', 'N'),
(10, 'farmerSurveyor', 'เกษตรกรประจำแปลง', 'N'),
(11, 'imageExaminer', 'ผู้ตรวจสอบภาพ', 'Y'),
(12, 'imageExporter', 'ผู้ส่งออกภาพ', 'Y'),
(13, 'infoExporter', 'ผู้ส่งออกข้อมูล', 'Y');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

CREATE TABLE `staff` (
  `staffID` int(11) NOT NULL,
  `position` varchar(30) NOT NULL,
  `division` varchar(50) NOT NULL,
  `organizationID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`staffID`, `position`, `division`, `organizationID`) VALUES
(1, 'นักวิชาการ', 'cpe', 1),
(2, 'a', 'a', 1),
(8, 'a', 'a', 1),
(9, 'a', 'a', 1),
(10, 'a', 'a', 1),
(11, 'a', 'a', 1),
(12, 'a', 'a', 1);

-- --------------------------------------------------------

--
-- Table structure for table `subdistrict`
--

CREATE TABLE `subdistrict` (
  `subdistrictID` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `districtID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ตำบล';

--
-- Dumping data for table `subdistrict`
--

INSERT INTO `subdistrict` (`subdistrictID`, `name`, `districtID`) VALUES
(1, 'กำแพงแสน', 6),
(2, 'ทุ่งบัว', 6),
(3, 'สี่พระยา', 2),
(4, 'สีลม', 2),
(5, 'บางเลน', 5),
(6, 'บางปลา', 5),
(7, 'บ่อวิน', 4),
(8, 'เมืองศรีราชา', 4),
(9, 'บ้านบึง', 3),
(10, 'ตลิ่งชัน', 1),
(11, 'ปลวกแดง', 8),
(12, 'แกลง', 7);

-- --------------------------------------------------------

--
-- Table structure for table `survey`
--

CREATE TABLE `survey` (
  `surveyID` int(11) NOT NULL,
  `date` date NOT NULL,
  `plantingID` int(11) NOT NULL,
  `besidePlant` varchar(50) DEFAULT NULL,
  `weed` varchar(30) DEFAULT NULL,
  `temperature` float NOT NULL,
  `humidity` float NOT NULL,
  `rainType` enum('ไม่มีฝน','ฝนทิ้งช่วง','ฝนปรอย','ฝนตกชุก') NOT NULL,
  `sunlightType` enum('แดดจัด','แดดน้อยฟ้าครึ้ม') NOT NULL,
  `dewType` enum('ไม่มีน้ำค้าง','น้ำค้างเล็กน้อย','น้ำค้างแรง') NOT NULL,
  `soilType` varchar(50) NOT NULL,
  `percentDamageFromHerbicide` enum('0','10','20','30','40','50','60','70','80','90','100') DEFAULT NULL,
  `surveyPatternDisease` set('นับจำนวนต้น','นับจำนวนสิ่งสำรวจ','พบ/ไม่พบ','ระดับ 0-5') NOT NULL DEFAULT 'ระดับ 0-5',
  `surveyPatternPest` enum('นับจำนวนต้น','นับจำนวนสิ่งสำรวจ','พบ/ไม่พบ','ระดับ 0-5','เปอร์เซ็นต์') NOT NULL DEFAULT 'เปอร์เซ็นต์',
  `surveyPatternNaturalEnemy` enum('นับจำนวนต้น','นับจำนวนสิ่งสำรวจ','พบ/ไม่พบ','เปอร์เซ็นต์') NOT NULL DEFAULT 'เปอร์เซ็นต์',
  `numPointSurvey` int(11) NOT NULL DEFAULT 5,
  `numPlantInPoint` int(11) NOT NULL DEFAULT 20,
  `imgOwner` int(11) DEFAULT NULL,
  `imgOwnerOther` varchar(100) DEFAULT NULL,
  `imgPhotographer` int(11) DEFAULT NULL,
  `imgPhotographerOther` varchar(100) DEFAULT NULL,
  `note` varchar(255) NOT NULL,
  `createBy` int(11) DEFAULT NULL,
  `createDate` datetime NOT NULL DEFAULT current_timestamp(),
  `lastUpdateBy` int(11) DEFAULT NULL,
  `lastUpdateDate` timestamp NULL DEFAULT NULL ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='การสำรวจแปลง';

-- --------------------------------------------------------

--
-- Table structure for table `surveytarget`
--

CREATE TABLE `surveytarget` (
  `surveyTargetID` int(11) NOT NULL,
  `surveyID` int(11) NOT NULL,
  `targetOfSurveyID` int(11) NOT NULL,
  `avgDamageLevel` float DEFAULT NULL,
  `percentDamage` float DEFAULT NULL,
  `status` enum('Editing','Complete') NOT NULL DEFAULT 'Editing'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='สื่งททำการสำรวจ';

-- --------------------------------------------------------

--
-- Table structure for table `surveytargetpoint`
--

CREATE TABLE `surveytargetpoint` (
  `surveyTargetPointID` int(11) NOT NULL,
  `surveyTargetID` int(11) NOT NULL,
  `pointNumber` int(11) NOT NULL,
  `itemNumber` int(11) NOT NULL,
  `value` int(11) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='สิ่งที่ทำการสำรวจในจุดสำรวจ';

-- --------------------------------------------------------

--
-- Table structure for table `targetofsurvey`
--

CREATE TABLE `targetofsurvey` (
  `targetOfSurveyID` int(11) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='สิ่งสำรวจ';

--
-- Dumping data for table `targetofsurvey`
--

INSERT INTO `targetofsurvey` (`targetOfSurveyID`, `name`) VALUES
(1, 'โรคแอนแทรคโนส'),
(3, 'โรคใบด่าง'),
(4, 'โรคพุ่มแจ้'),
(5, 'ไรแดงหม่อน(ไข่)'),
(6, 'ไรแดงหม่อน(ตัวอ่อน)'),
(7, 'ไรแดงหม่อน(ตัวเต็มวัย)'),
(8, 'โรคใบจุดสีน้ำตาล'),
(9, 'แมลงหวี่ขาวยาสูบ(ไข่)'),
(10, 'แมลงหวี่ขาวยาสูบ(ตัวอ่อน)'),
(11, 'แมลงหวี่ขาวยาสูบ(ตัวเต็มวัย)'),
(12, 'เพลี้ยหอยขาว(ไข่)'),
(13, 'เพลี้ยหอยขาว(ตัวอ่อน)'),
(14, 'เพลี้ยหอยขาว(ตัวเต็มวัย)'),
(15, 'แตนเบียน'),
(16, 'แมลงช้างปีกใส'),
(17, 'ฟ(ไข่)'),
(18, 'ฟ(ตัวอ่อน)'),
(19, 'ฟ(ตัวเต็มวัย)'),
(20, '1(ไข่)'),
(21, '1(ตัวอ่อน)'),
(22, '1(ตัวเต็มวัย)'),
(23, '1'),
(24, 'a(ไข่)'),
(25, 'a(ตัวอ่อน)'),
(26, 'a(ตัวเต็มวัย)'),
(27, 's(ไข่)'),
(28, 's(ตัวอ่อน)'),
(29, 's(ตัวเต็มวัย)'),
(30, 'e(ไข่)'),
(31, 'e(ตัวอ่อน)'),
(32, 'e(ตัวเต็มวัย)');

-- --------------------------------------------------------

--
-- Table structure for table `token`
--

CREATE TABLE `token` (
  `tokenID` int(11) NOT NULL,
  `value` int(11) NOT NULL,
  `expireDate` int(11) NOT NULL,
  `staffID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `username` varchar(35) DEFAULT NULL,
  `title` varchar(10) DEFAULT NULL,
  `firstName` varchar(20) NOT NULL,
  `lastName` varchar(30) NOT NULL,
  `phoneNo` varchar(20) NOT NULL,
  `userStatus` enum('invalid','waiting','active','inactive') NOT NULL,
  `latestLogin` datetime DEFAULT NULL,
  `requestInfoStatus` enum('No','Waiting','Yes') NOT NULL DEFAULT 'No'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ผู้ใช้ระบบ';

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`userID`, `username`, `title`, `firstName`, `lastName`, `phoneNo`, `userStatus`, `latestLogin`, `requestInfoStatus`) VALUES
(1, 'duangpenj@gmail.com', NULL, 'dpj', 'jetpipat', '0123456789', 'active', '2022-12-13 17:13:50', 'No'),
(2, 'sidol.sat@gmail.com', 'นาย', 'ศิวดล', '', '5556666', 'active', '2022-12-13 17:20:25', 'No'),
(3, 'Farmer1@gmail.com', 'นาย', 'เกษตรกร1', 'รักไทย', '023456789', 'active', '2022-12-16 09:27:42', 'No'),
(4, 'Farmer2@gmail.com', 'นาย', 'เกษตรกร2', 'รักไทย', '023456789', 'active', '2022-12-16 09:27:42', 'No'),
(5, 'Farmer3@gmail.com', 'นาย', 'เกษตรกร3', 'รักไทย', '023456789', 'active', '2022-12-16 09:27:42', 'No'),
(6, 'Farmer4@gmail.com', 'นาย', 'เกษตรกร4', 'รักไทย', '023456789', 'active', '2022-12-16 09:27:42', 'No'),
(7, 'Farmer5@gmail.com', 'นาย', 'เกษตรกร5', 'รักไทย', '023456789', 'active', '2022-12-16 09:27:42', 'No'),
(8, 'staff1@gmail.com', 'นาย', 'จนท1', 'รักไทย', '023456789', 'active', '2022-12-16 09:27:42', 'No'),
(9, 'staff2@gmail.com', 'นาย', 'จนท2', 'รักไทย', '023456789', 'active', '2022-12-16 09:27:42', 'No'),
(10, 'staff3@gmail.com', 'นาย', 'จนท3', 'รักไทย', '023456789', 'active', '2022-12-16 09:27:42', 'No'),
(11, 'staff4@gmail.com', 'นาย', 'จนท4', 'รักไทย', '023456789', 'active', '2022-12-16 09:27:42', 'No'),
(12, 'staff5@gmail.com', 'นาย', 'จนท5', 'รักไทย', '023456789', 'active', '2022-12-16 09:27:42', 'No');

-- --------------------------------------------------------

--
-- Table structure for table `userinfield`
--

CREATE TABLE `userinfield` (
  `userID` int(11) NOT NULL,
  `fieldID` int(11) NOT NULL,
  `role` enum('staffCreator','staffResponse','staffSurvey','farmerOwner','farmerSurvey') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='ผู้ใช้ประจำแปลง';

--
-- Dumping data for table `userinfield`
--

INSERT INTO `userinfield` (`userID`, `fieldID`, `role`) VALUES
(3, 1, 'farmerOwner'),
(3, 2, 'farmerOwner');

-- --------------------------------------------------------

--
-- Table structure for table `userrole`
--

CREATE TABLE `userrole` (
  `userID` int(11) NOT NULL,
  `roleID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='หน้าที่ผู้ใช้ระบบ';

-- --------------------------------------------------------

--
-- Table structure for table `variety`
--

CREATE TABLE `variety` (
  `varietyID` int(11) NOT NULL,
  `name` varchar(30) NOT NULL,
  `plantID` int(11) NOT NULL,
  `trichome` varchar(20) DEFAULT NULL,
  `apicalLeavesColor` varchar(30) DEFAULT NULL COMMENT 'สียอดอ่อน',
  `youngLeavesColor` varchar(30) DEFAULT NULL COMMENT 'สีใบอ่อน',
  `petioleColor` varchar(30) DEFAULT NULL COMMENT 'สีก้านใบ',
  `centralLeafletShape` varchar(30) DEFAULT NULL COMMENT 'ลักษณะแฉกกลางใบ',
  `branchingHabit` varchar(20) DEFAULT NULL COMMENT 'การแตกกิ่ง',
  `heightToFirstBranching` varchar(20) DEFAULT NULL COMMENT 'ความสูงของการแตกกิ่งแรก(ซม.)',
  `plantHeight` varchar(20) DEFAULT NULL COMMENT 'ความสูงของลำต้น(ซม.)',
  `stemColor` varchar(30) DEFAULT NULL COMMENT 'สีลำต้น',
  `starchContentRainySeason` varchar(20) DEFAULT NULL COMMENT 'เปอร์เซ็นต์แป้งฤดูฝน',
  `starchContentDrySeason` varchar(20) DEFAULT NULL COMMENT 'เปอร์เซ็นต์แป้งฤดูแล้ง',
  `rootYield` float DEFAULT NULL COMMENT 'ผลผลิต(กก.)',
  `mainCharacter` varchar(255) DEFAULT NULL COMMENT 'ลักษณะเด่น',
  `limitationNote` varchar(255) DEFAULT NULL COMMENT 'ข้อจำกัด',
  `source` varchar(255) DEFAULT NULL COMMENT 'แหล่งที่มา'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='พันธุ์พืช';

--
-- Dumping data for table `variety`
--

INSERT INTO `variety` (`varietyID`, `name`, `plantID`, `trichome`, `apicalLeavesColor`, `youngLeavesColor`, `petioleColor`, `centralLeafletShape`, `branchingHabit`, `heightToFirstBranching`, `plantHeight`, `stemColor`, `starchContentRainySeason`, `starchContentDrySeason`, `rootYield`, `mainCharacter`, `limitationNote`, `source`) VALUES
(1, 'ระยอง 9', 1, 'เขียวอ่อน', 'เขียวอ่อน', 'เขียวอ่อน', 'เขียวอ่อน', 'เขียวอ่อน', 'เขียวอ่อน', '1', '1', 'เขียวเงิน', '18.3', '18.3', 3.22, 'ปรับตัวเข้ากับสภาพแวดล้อมได้ดี', 'ปริมาณแป้งต่ำ', 'ที่มา กรมส่งเสริมการเกษตร. การจำแนกพันธุ์มันสำปะหลัง. บริษัท ซัน แพคเกจจิ้ง (2014) จำกัด, กรมส่งเสริมการเกษตร, 2559. 96 หน้า.'),
(15, 'เกษตรศาสตร์ 50', 1, 'เขียวอ่อน', 'เขียวอ่อน', 'เขียวอ่อน', 'เขียวอ่อน', 'เขียวอ่อน', 'เขียวอ่อน', '11', '11', 'เขียวเงิน', '23', '28', 4.4, 'ปรับตัวเข้ากับสภาพแวดล้อมได้ดี มีความงอกดี เก็บรักษาได้นาน ปริมาณแป้งสูง ทนทานโรคใบด่าง', 'อ่อนแอต่อโรคพุ่มแจ้', 'ที่มา กรมส่งเสริมการเกษตร. การจำแนกพันธุ์มันสำปะหลัง. บริษัท ซัน แพคเกจจิ้ง (2014) จำกัด, กรมส่งเสริมการเกษตร, 2559. 96 หน้า.');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `api`
--
ALTER TABLE `api`
  ADD PRIMARY KEY (`apiID`);

--
-- Indexes for table `apidetail`
--
ALTER TABLE `apidetail`
  ADD PRIMARY KEY (`apiDetailID`),
  ADD KEY `apiGroupID` (`apiID`);

--
-- Indexes for table `apistaff`
--
ALTER TABLE `apistaff`
  ADD PRIMARY KEY (`apiStaffID`),
  ADD KEY `apiID` (`apiID`),
  ADD KEY `staffID` (`staffID`);

--
-- Indexes for table `disease`
--
ALTER TABLE `disease`
  ADD PRIMARY KEY (`diseaseID`),
  ADD KEY `plantID` (`plantID`);

--
-- Indexes for table `diseasepathogen`
--
ALTER TABLE `diseasepathogen`
  ADD PRIMARY KEY (`diseaseID`,`pathogenID`),
  ADD KEY `diseaseID` (`diseaseID`),
  ADD KEY `pathogenID` (`pathogenID`);

--
-- Indexes for table `diseasepest`
--
ALTER TABLE `diseasepest`
  ADD PRIMARY KEY (`diseaseID`,`pestID`),
  ADD KEY `diseaseID` (`diseaseID`),
  ADD KEY `pestID` (`pestID`);

--
-- Indexes for table `district`
--
ALTER TABLE `district`
  ADD PRIMARY KEY (`districtID`),
  ADD KEY `provinceID` (`provinceID`);

--
-- Indexes for table `farmer`
--
ALTER TABLE `farmer`
  ADD PRIMARY KEY (`farmerID`),
  ADD UNIQUE KEY `IDcard` (`IDcard`),
  ADD KEY `subdistrictID` (`subdistrictID`);

--
-- Indexes for table `farmerorganization`
--
ALTER TABLE `farmerorganization`
  ADD PRIMARY KEY (`farmerID`,`organizationID`),
  ADD KEY `farmerID` (`farmerID`),
  ADD KEY `organizationID` (`organizationID`);

--
-- Indexes for table `field`
--
ALTER TABLE `field`
  ADD PRIMARY KEY (`fieldID`),
  ADD KEY `subdistrictID` (`subdistrictID`),
  ADD KEY `organizationID` (`organizationID`),
  ADD KEY `createBy` (`createBy`),
  ADD KEY `lastUpdateBy` (`lastUpdateBy`);

--
-- Indexes for table `herbicide`
--
ALTER TABLE `herbicide`
  ADD PRIMARY KEY (`herbicideID`);

--
-- Indexes for table `imgdisease`
--
ALTER TABLE `imgdisease`
  ADD PRIMARY KEY (`imgDiseaseID`),
  ADD KEY `diseaseID` (`diseaseID`);

--
-- Indexes for table `imgherbicidedamage`
--
ALTER TABLE `imgherbicidedamage`
  ADD PRIMARY KEY (`imgHerbicideDamageID`),
  ADD KEY `surveyID` (`surveyID`),
  ADD KEY `uploadBy` (`uploadBy`),
  ADD KEY `approveBy` (`approveBy`);

--
-- Indexes for table `imgnaturalenemy`
--
ALTER TABLE `imgnaturalenemy`
  ADD PRIMARY KEY (`imgNaturalEnemyID`),
  ADD KEY `naturalEnemyID` (`naturalEnemyID`);

--
-- Indexes for table `imgpest`
--
ALTER TABLE `imgpest`
  ADD PRIMARY KEY (`imgPestID`),
  ADD KEY `pestID` (`pestID`);

--
-- Indexes for table `imgsurveytargetpoint`
--
ALTER TABLE `imgsurveytargetpoint`
  ADD PRIMARY KEY (`imgSurveyTargetPointID`),
  ADD KEY `surveyTargetPointID` (`surveyTargetPointID`),
  ADD KEY `approveBy` (`approveBy`),
  ADD KEY `uploadBy` (`uploadBy`);

--
-- Indexes for table `imgvariety`
--
ALTER TABLE `imgvariety`
  ADD PRIMARY KEY (`imgVarietyID`),
  ADD KEY `varietyID` (`varietyID`);

--
-- Indexes for table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`messageID`),
  ADD KEY `sender` (`sender`);

--
-- Indexes for table `messagereceiver`
--
ALTER TABLE `messagereceiver`
  ADD PRIMARY KEY (`messageID`,`receiverID`),
  ADD KEY `messageID` (`messageID`),
  ADD KEY `receiverID` (`receiverID`);

--
-- Indexes for table `naturalenemy`
--
ALTER TABLE `naturalenemy`
  ADD PRIMARY KEY (`naturalEnemyID`);

--
-- Indexes for table `organization`
--
ALTER TABLE `organization`
  ADD PRIMARY KEY (`organizationID`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `pathogen`
--
ALTER TABLE `pathogen`
  ADD PRIMARY KEY (`pathogenID`),
  ADD KEY `pathogenTypeID` (`pathogenTypeID`);

--
-- Indexes for table `pathogentype`
--
ALTER TABLE `pathogentype`
  ADD PRIMARY KEY (`pathogenTypeID`);

--
-- Indexes for table `permission`
--
ALTER TABLE `permission`
  ADD PRIMARY KEY (`permisisonID`),
  ADD KEY `staffID` (`staffID`),
  ADD KEY `approveBy` (`approveBy`);

--
-- Indexes for table `permissiondisease`
--
ALTER TABLE `permissiondisease`
  ADD PRIMARY KEY (`permissionDiseaseID`),
  ADD KEY `permissionID` (`permissionID`),
  ADD KEY `diseaaseID` (`diseaaseID`);

--
-- Indexes for table `permissionfile`
--
ALTER TABLE `permissionfile`
  ADD PRIMARY KEY (`permissionFileID`),
  ADD KEY `permissionID` (`permissionID`);

--
-- Indexes for table `permissionprovince`
--
ALTER TABLE `permissionprovince`
  ADD PRIMARY KEY (`permisisonProvinceID`),
  ADD KEY `permissionID` (`permissionID`),
  ADD KEY `provinceID` (`provinceID`);

--
-- Indexes for table `pest`
--
ALTER TABLE `pest`
  ADD PRIMARY KEY (`pestID`);

--
-- Indexes for table `pestmanagement`
--
ALTER TABLE `pestmanagement`
  ADD PRIMARY KEY (`pestManagementID`),
  ADD KEY `pestID` (`pestID`);

--
-- Indexes for table `pestphase`
--
ALTER TABLE `pestphase`
  ADD PRIMARY KEY (`pestPhaseID`);

--
-- Indexes for table `pestphasesurvey`
--
ALTER TABLE `pestphasesurvey`
  ADD PRIMARY KEY (`pestPhaseSurveyID`),
  ADD UNIQUE KEY `pestID_2` (`pestID`,`pestPhaseID`),
  ADD KEY `pestID` (`pestID`),
  ADD KEY `pestPhaseID` (`pestPhaseID`);

--
-- Indexes for table `plant`
--
ALTER TABLE `plant`
  ADD PRIMARY KEY (`plantID`);

--
-- Indexes for table `planting`
--
ALTER TABLE `planting`
  ADD PRIMARY KEY (`plantingID`),
  ADD KEY `fieldID` (`fieldID`),
  ADD KEY `createBy` (`createBy`),
  ADD KEY `lastUpdateBy` (`lastUpdateBy`),
  ADD KEY `weedingChemical1` (`weedingChemical1`),
  ADD KEY `weedingChemical2` (`weedingChemical2`),
  ADD KEY `weedingChemical3` (`weedingChemical3`);

--
-- Indexes for table `plantingcassavavariety`
--
ALTER TABLE `plantingcassavavariety`
  ADD UNIQUE KEY `varietyID` (`varietyID`),
  ADD UNIQUE KEY `plantingID_2` (`plantingID`,`varietyID`),
  ADD KEY `plantingID` (`plantingID`);

--
-- Indexes for table `plantphase`
--
ALTER TABLE `plantphase`
  ADD PRIMARY KEY (`plantPhaseID`),
  ADD KEY `plantID` (`plantID`);

--
-- Indexes for table `province`
--
ALTER TABLE `province`
  ADD PRIMARY KEY (`provinceID`);

--
-- Indexes for table `registercode`
--
ALTER TABLE `registercode`
  ADD PRIMARY KEY (`registerCodeID`),
  ADD UNIQUE KEY `code` (`code`),
  ADD KEY `createBy` (`createBy`),
  ADD KEY `organizationID` (`organizationID`);

--
-- Indexes for table `request`
--
ALTER TABLE `request`
  ADD PRIMARY KEY (`requestID`),
  ADD KEY `requestBy` (`staffID`),
  ADD KEY `approveBy` (`approveBy`),
  ADD KEY `approveByDAE` (`approveByDAE`),
  ADD KEY `approveByDA` (`approveByDA`),
  ADD KEY `approveByDPPhato` (`approveByDPPhato`);

--
-- Indexes for table `requestdetail`
--
ALTER TABLE `requestdetail`
  ADD PRIMARY KEY (`requestDetailID`),
  ADD KEY `requestID` (`requestID`),
  ADD KEY `surveyID` (`surveyID`);

--
-- Indexes for table `requestfile`
--
ALTER TABLE `requestfile`
  ADD PRIMARY KEY (`requestFileID`),
  ADD KEY `requestID` (`requestID`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`roleID`);

--
-- Indexes for table `staff`
--
ALTER TABLE `staff`
  ADD PRIMARY KEY (`staffID`),
  ADD KEY `organizationID` (`organizationID`);

--
-- Indexes for table `subdistrict`
--
ALTER TABLE `subdistrict`
  ADD PRIMARY KEY (`subdistrictID`),
  ADD KEY `districtID` (`districtID`);

--
-- Indexes for table `survey`
--
ALTER TABLE `survey`
  ADD PRIMARY KEY (`surveyID`),
  ADD KEY `lastUpdateBy` (`lastUpdateBy`),
  ADD KEY `plantingID` (`plantingID`),
  ADD KEY `createBy` (`createBy`),
  ADD KEY `imgOwner` (`imgOwner`),
  ADD KEY `imgPhotographer` (`imgPhotographer`);

--
-- Indexes for table `surveytarget`
--
ALTER TABLE `surveytarget`
  ADD PRIMARY KEY (`surveyTargetID`),
  ADD UNIQUE KEY `surveyID_2` (`surveyID`,`targetOfSurveyID`),
  ADD KEY `surveyID` (`surveyID`),
  ADD KEY `targetOfSurveyID` (`targetOfSurveyID`);

--
-- Indexes for table `surveytargetpoint`
--
ALTER TABLE `surveytargetpoint`
  ADD PRIMARY KEY (`surveyTargetPointID`),
  ADD KEY `surveyTargetID` (`surveyTargetID`);

--
-- Indexes for table `targetofsurvey`
--
ALTER TABLE `targetofsurvey`
  ADD PRIMARY KEY (`targetOfSurveyID`);

--
-- Indexes for table `token`
--
ALTER TABLE `token`
  ADD PRIMARY KEY (`tokenID`),
  ADD KEY `staffID` (`staffID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`),
  ADD UNIQUE KEY `name` (`username`);

--
-- Indexes for table `userinfield`
--
ALTER TABLE `userinfield`
  ADD KEY `userID` (`userID`),
  ADD KEY `fieldID` (`fieldID`);

--
-- Indexes for table `userrole`
--
ALTER TABLE `userrole`
  ADD PRIMARY KEY (`userID`,`roleID`),
  ADD KEY `roleID` (`roleID`),
  ADD KEY `userID` (`userID`);

--
-- Indexes for table `variety`
--
ALTER TABLE `variety`
  ADD PRIMARY KEY (`varietyID`),
  ADD KEY `plantID` (`plantID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `api`
--
ALTER TABLE `api`
  MODIFY `apiID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `apidetail`
--
ALTER TABLE `apidetail`
  MODIFY `apiDetailID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `apistaff`
--
ALTER TABLE `apistaff`
  MODIFY `apiStaffID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `district`
--
ALTER TABLE `district`
  MODIFY `districtID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `field`
--
ALTER TABLE `field`
  MODIFY `fieldID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `herbicide`
--
ALTER TABLE `herbicide`
  MODIFY `herbicideID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `imgdisease`
--
ALTER TABLE `imgdisease`
  MODIFY `imgDiseaseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `imgherbicidedamage`
--
ALTER TABLE `imgherbicidedamage`
  MODIFY `imgHerbicideDamageID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `imgnaturalenemy`
--
ALTER TABLE `imgnaturalenemy`
  MODIFY `imgNaturalEnemyID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `imgpest`
--
ALTER TABLE `imgpest`
  MODIFY `imgPestID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `imgsurveytargetpoint`
--
ALTER TABLE `imgsurveytargetpoint`
  MODIFY `imgSurveyTargetPointID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `imgvariety`
--
ALTER TABLE `imgvariety`
  MODIFY `imgVarietyID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT for table `message`
--
ALTER TABLE `message`
  MODIFY `messageID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `organization`
--
ALTER TABLE `organization`
  MODIFY `organizationID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `pathogen`
--
ALTER TABLE `pathogen`
  MODIFY `pathogenID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `pathogentype`
--
ALTER TABLE `pathogentype`
  MODIFY `pathogenTypeID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `permission`
--
ALTER TABLE `permission`
  MODIFY `permisisonID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `permissiondisease`
--
ALTER TABLE `permissiondisease`
  MODIFY `permissionDiseaseID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `permissionfile`
--
ALTER TABLE `permissionfile`
  MODIFY `permissionFileID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `permissionprovince`
--
ALTER TABLE `permissionprovince`
  MODIFY `permisisonProvinceID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `pest`
--
ALTER TABLE `pest`
  MODIFY `pestID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `pestmanagement`
--
ALTER TABLE `pestmanagement`
  MODIFY `pestManagementID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `pestphase`
--
ALTER TABLE `pestphase`
  MODIFY `pestPhaseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `plant`
--
ALTER TABLE `plant`
  MODIFY `plantID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `planting`
--
ALTER TABLE `planting`
  MODIFY `plantingID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `plantphase`
--
ALTER TABLE `plantphase`
  MODIFY `plantPhaseID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `province`
--
ALTER TABLE `province`
  MODIFY `provinceID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `registercode`
--
ALTER TABLE `registercode`
  MODIFY `registerCodeID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `request`
--
ALTER TABLE `request`
  MODIFY `requestID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `requestdetail`
--
ALTER TABLE `requestdetail`
  MODIFY `requestDetailID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `requestfile`
--
ALTER TABLE `requestfile`
  MODIFY `requestFileID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `roleID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT for table `subdistrict`
--
ALTER TABLE `subdistrict`
  MODIFY `subdistrictID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `survey`
--
ALTER TABLE `survey`
  MODIFY `surveyID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `surveytarget`
--
ALTER TABLE `surveytarget`
  MODIFY `surveyTargetID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `surveytargetpoint`
--
ALTER TABLE `surveytargetpoint`
  MODIFY `surveyTargetPointID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `targetofsurvey`
--
ALTER TABLE `targetofsurvey`
  MODIFY `targetOfSurveyID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- AUTO_INCREMENT for table `token`
--
ALTER TABLE `token`
  MODIFY `tokenID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `variety`
--
ALTER TABLE `variety`
  MODIFY `varietyID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `apidetail`
--
ALTER TABLE `apidetail`
  ADD CONSTRAINT `apidetail_ibfk_1` FOREIGN KEY (`apiID`) REFERENCES `api` (`apiID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `apistaff`
--
ALTER TABLE `apistaff`
  ADD CONSTRAINT `apistaff_ibfk_1` FOREIGN KEY (`apiID`) REFERENCES `api` (`apiID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `apistaff_ibfk_2` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `disease`
--
ALTER TABLE `disease`
  ADD CONSTRAINT `disease_ibfk_2` FOREIGN KEY (`plantID`) REFERENCES `plant` (`plantID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `disease_ibfk_3` FOREIGN KEY (`diseaseID`) REFERENCES `targetofsurvey` (`targetOfSurveyID`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `diseasepathogen`
--
ALTER TABLE `diseasepathogen`
  ADD CONSTRAINT `diseaseCause_ibfk_2` FOREIGN KEY (`diseaseID`) REFERENCES `disease` (`diseaseID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `diseaseCause_ibfk_3` FOREIGN KEY (`pathogenID`) REFERENCES `pathogen` (`pathogenID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `diseasepest`
--
ALTER TABLE `diseasepest`
  ADD CONSTRAINT `diseasepest_ibfk_1` FOREIGN KEY (`diseaseID`) REFERENCES `disease` (`diseaseID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `diseasepest_ibfk_2` FOREIGN KEY (`pestID`) REFERENCES `pest` (`pestID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `district`
--
ALTER TABLE `district`
  ADD CONSTRAINT `district_ibfk_1` FOREIGN KEY (`provinceID`) REFERENCES `province` (`provinceID`) ON UPDATE CASCADE;

--
-- Constraints for table `farmer`
--
ALTER TABLE `farmer`
  ADD CONSTRAINT `farmer_ibfk_1` FOREIGN KEY (`farmerID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `farmer_ibfk_2` FOREIGN KEY (`subdistrictID`) REFERENCES `subdistrict` (`subdistrictID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `farmerorganization`
--
ALTER TABLE `farmerorganization`
  ADD CONSTRAINT `farmerorganization_ibfk_2` FOREIGN KEY (`organizationID`) REFERENCES `organization` (`organizationID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `farmerorganization_ibfk_3` FOREIGN KEY (`farmerID`) REFERENCES `farmer` (`farmerID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `field`
--
ALTER TABLE `field`
  ADD CONSTRAINT `field_ibfk_2` FOREIGN KEY (`subdistrictID`) REFERENCES `subdistrict` (`subdistrictID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `field_ibfk_3` FOREIGN KEY (`organizationID`) REFERENCES `organization` (`organizationID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `field_ibfk_4` FOREIGN KEY (`createBy`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `field_ibfk_5` FOREIGN KEY (`lastUpdateBy`) REFERENCES `user` (`userID`) ON UPDATE CASCADE;

--
-- Constraints for table `imgdisease`
--
ALTER TABLE `imgdisease`
  ADD CONSTRAINT `imgdisease_ibfk_1` FOREIGN KEY (`diseaseID`) REFERENCES `disease` (`diseaseID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `imgherbicidedamage`
--
ALTER TABLE `imgherbicidedamage`
  ADD CONSTRAINT `imgherbicidedamage_ibfk_1` FOREIGN KEY (`surveyID`) REFERENCES `survey` (`surveyID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `imgherbicidedamage_ibfk_2` FOREIGN KEY (`uploadBy`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `imgherbicidedamage_ibfk_3` FOREIGN KEY (`approveBy`) REFERENCES `user` (`userID`) ON UPDATE CASCADE;

--
-- Constraints for table `imgnaturalenemy`
--
ALTER TABLE `imgnaturalenemy`
  ADD CONSTRAINT `imgnaturalenemy_ibfk_1` FOREIGN KEY (`naturalEnemyID`) REFERENCES `naturalenemy` (`naturalEnemyID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `imgpest`
--
ALTER TABLE `imgpest`
  ADD CONSTRAINT `imgpest_ibfk_1` FOREIGN KEY (`pestID`) REFERENCES `pest` (`pestID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `imgsurveytargetpoint`
--
ALTER TABLE `imgsurveytargetpoint`
  ADD CONSTRAINT `imgsurveytargetpoint_ibfk_1` FOREIGN KEY (`surveyTargetPointID`) REFERENCES `surveytargetpoint` (`surveyTargetPointID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `imgsurveytargetpoint_ibfk_2` FOREIGN KEY (`approveBy`) REFERENCES `user` (`userID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `imgsurveytargetpoint_ibfk_3` FOREIGN KEY (`uploadBy`) REFERENCES `user` (`userID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `imgvariety`
--
ALTER TABLE `imgvariety`
  ADD CONSTRAINT `imgvariety_ibfk_1` FOREIGN KEY (`varietyID`) REFERENCES `variety` (`varietyID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `message`
--
ALTER TABLE `message`
  ADD CONSTRAINT `message_ibfk_1` FOREIGN KEY (`sender`) REFERENCES `user` (`userID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `messagereceiver`
--
ALTER TABLE `messagereceiver`
  ADD CONSTRAINT `messagereceiver_ibfk_1` FOREIGN KEY (`messageID`) REFERENCES `message` (`messageID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `messagereceiver_ibfk_2` FOREIGN KEY (`receiverID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `naturalenemy`
--
ALTER TABLE `naturalenemy`
  ADD CONSTRAINT `naturalenemy_ibfk_1` FOREIGN KEY (`naturalEnemyID`) REFERENCES `targetofsurvey` (`targetOfSurveyID`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `pathogen`
--
ALTER TABLE `pathogen`
  ADD CONSTRAINT `pathogen_ibfk_1` FOREIGN KEY (`pathogenTypeID`) REFERENCES `pathogentype` (`pathogenTypeID`) ON UPDATE CASCADE;

--
-- Constraints for table `permission`
--
ALTER TABLE `permission`
  ADD CONSTRAINT `permission_ibfk_1` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `permission_ibfk_2` FOREIGN KEY (`approveBy`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `permissiondisease`
--
ALTER TABLE `permissiondisease`
  ADD CONSTRAINT `permissiondisease_ibfk_1` FOREIGN KEY (`permissionID`) REFERENCES `permission` (`permisisonID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `permissiondisease_ibfk_2` FOREIGN KEY (`diseaaseID`) REFERENCES `disease` (`diseaseID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `permissionfile`
--
ALTER TABLE `permissionfile`
  ADD CONSTRAINT `permissionfile_ibfk_1` FOREIGN KEY (`permissionID`) REFERENCES `permission` (`permisisonID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `permissionprovince`
--
ALTER TABLE `permissionprovince`
  ADD CONSTRAINT `permissionprovince_ibfk_1` FOREIGN KEY (`permissionID`) REFERENCES `permission` (`permisisonID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `permissionprovince_ibfk_2` FOREIGN KEY (`provinceID`) REFERENCES `province` (`provinceID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pestmanagement`
--
ALTER TABLE `pestmanagement`
  ADD CONSTRAINT `pestmanagement_ibfk_1` FOREIGN KEY (`pestID`) REFERENCES `pest` (`pestID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `pestphasesurvey`
--
ALTER TABLE `pestphasesurvey`
  ADD CONSTRAINT `pestphasesurvey_ibfk_1` FOREIGN KEY (`pestID`) REFERENCES `pest` (`pestID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `pestphasesurvey_ibfk_2` FOREIGN KEY (`pestPhaseID`) REFERENCES `pestphase` (`pestPhaseID`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `pestphasesurvey_ibfk_3` FOREIGN KEY (`pestPhaseSurveyID`) REFERENCES `targetofsurvey` (`targetOfSurveyID`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `planting`
--
ALTER TABLE `planting`
  ADD CONSTRAINT `planting_ibfk_1` FOREIGN KEY (`fieldID`) REFERENCES `field` (`fieldID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `planting_ibfk_10` FOREIGN KEY (`lastUpdateBy`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `planting_ibfk_6` FOREIGN KEY (`createBy`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `planting_ibfk_7` FOREIGN KEY (`weedingChemical1`) REFERENCES `herbicide` (`herbicideID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `planting_ibfk_8` FOREIGN KEY (`weedingChemical2`) REFERENCES `herbicide` (`herbicideID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `planting_ibfk_9` FOREIGN KEY (`weedingChemical3`) REFERENCES `herbicide` (`herbicideID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `plantingcassavavariety`
--
ALTER TABLE `plantingcassavavariety`
  ADD CONSTRAINT `plantingcassavavariety_ibfk_1` FOREIGN KEY (`plantingID`) REFERENCES `planting` (`plantingID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `plantingcassavavariety_ibfk_2` FOREIGN KEY (`varietyID`) REFERENCES `variety` (`varietyID`) ON UPDATE CASCADE;

--
-- Constraints for table `plantphase`
--
ALTER TABLE `plantphase`
  ADD CONSTRAINT `plantphase_ibfk_1` FOREIGN KEY (`plantID`) REFERENCES `plant` (`plantID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `registercode`
--
ALTER TABLE `registercode`
  ADD CONSTRAINT `registercode_ibfk_1` FOREIGN KEY (`createBy`) REFERENCES `user` (`userID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `registercode_ibfk_2` FOREIGN KEY (`organizationID`) REFERENCES `organization` (`organizationID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `request`
--
ALTER TABLE `request`
  ADD CONSTRAINT `request_ibfk_3` FOREIGN KEY (`approveByDAE`) REFERENCES `staff` (`staffID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `request_ibfk_4` FOREIGN KEY (`approveByDA`) REFERENCES `staff` (`staffID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `request_ibfk_5` FOREIGN KEY (`approveByDPPhato`) REFERENCES `staff` (`staffID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `request_ibfk_6` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `request_ibfk_7` FOREIGN KEY (`approveBy`) REFERENCES `staff` (`staffID`) ON DELETE SET NULL ON UPDATE CASCADE;

--
-- Constraints for table `requestdetail`
--
ALTER TABLE `requestdetail`
  ADD CONSTRAINT `requestdetail_ibfk_1` FOREIGN KEY (`requestID`) REFERENCES `request` (`requestID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `requestdetail_ibfk_2` FOREIGN KEY (`surveyID`) REFERENCES `survey` (`surveyID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `requestfile`
--
ALTER TABLE `requestfile`
  ADD CONSTRAINT `requestfile_ibfk_1` FOREIGN KEY (`requestID`) REFERENCES `request` (`requestID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `staff`
--
ALTER TABLE `staff`
  ADD CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`staffID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `staff_ibfk_2` FOREIGN KEY (`organizationID`) REFERENCES `organization` (`organizationID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `subdistrict`
--
ALTER TABLE `subdistrict`
  ADD CONSTRAINT `subdistrict_ibfk_1` FOREIGN KEY (`districtID`) REFERENCES `district` (`districtID`) ON UPDATE CASCADE;

--
-- Constraints for table `survey`
--
ALTER TABLE `survey`
  ADD CONSTRAINT `survey_ibfk_10` FOREIGN KEY (`imgOwner`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `survey_ibfk_11` FOREIGN KEY (`imgPhotographer`) REFERENCES `user` (`userID`) ON UPDATE CASCADE,
  ADD CONSTRAINT `survey_ibfk_7` FOREIGN KEY (`createBy`) REFERENCES `user` (`userID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `survey_ibfk_8` FOREIGN KEY (`lastUpdateBy`) REFERENCES `user` (`userID`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `survey_ibfk_9` FOREIGN KEY (`plantingID`) REFERENCES `planting` (`plantingID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `surveytarget`
--
ALTER TABLE `surveytarget`
  ADD CONSTRAINT `surveytarget_ibfk_1` FOREIGN KEY (`surveyID`) REFERENCES `survey` (`surveyID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `surveytarget_ibfk_2` FOREIGN KEY (`targetOfSurveyID`) REFERENCES `targetofsurvey` (`targetOfSurveyID`) ON UPDATE CASCADE;

--
-- Constraints for table `surveytargetpoint`
--
ALTER TABLE `surveytargetpoint`
  ADD CONSTRAINT `surveytargetpoint_ibfk_1` FOREIGN KEY (`surveyTargetID`) REFERENCES `surveytarget` (`surveyTargetID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `token`
--
ALTER TABLE `token`
  ADD CONSTRAINT `token_ibfk_1` FOREIGN KEY (`staffID`) REFERENCES `staff` (`staffID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `userinfield`
--
ALTER TABLE `userinfield`
  ADD CONSTRAINT `userinfield_ibfk_2` FOREIGN KEY (`fieldID`) REFERENCES `field` (`fieldID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `userinfield_ibfk_3` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `userrole`
--
ALTER TABLE `userrole`
  ADD CONSTRAINT `userRole_ibfk_1` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `userRole_ibfk_2` FOREIGN KEY (`roleID`) REFERENCES `role` (`roleID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `variety`
--
ALTER TABLE `variety`
  ADD CONSTRAINT `variety_ibfk_1` FOREIGN KEY (`plantID`) REFERENCES `plant` (`plantID`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
