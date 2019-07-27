-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 27, 2019 at 05:14 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `timetable_gs`
--

-- --------------------------------------------------------

--
-- Table structure for table `buildings`
--

CREATE TABLE `buildings` (
  `id` int(4) NOT NULL,
  `building_name` varchar(255) DEFAULT NULL,
  `building_char` char(5) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `buildings`
--

INSERT INTO `buildings` (`id`, `building_name`, `building_char`) VALUES
(1, 'IT', 'I'),
(2, 'Ka', 'K');

-- --------------------------------------------------------

--
-- Table structure for table `courses`
--

CREATE TABLE `courses` (
  `id` int(4) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `type` tinyint(4) DEFAULT NULL COMMENT '0 - practical / 1 - theoretical',
  `major_id` int(11) DEFAULT NULL,
  `level_number` int(1) DEFAULT NULL,
  `semester` tinyint(1) DEFAULT NULL,
  `course_number` varchar(255) DEFAULT NULL,
  `credit_hours` int(2) DEFAULT NULL,
  `actual_hours` int(2) DEFAULT NULL,
  `can_devide` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `courses`
--

INSERT INTO `courses` (`id`, `name`, `type`, `major_id`, `level_number`, `semester`, `course_number`, `credit_hours`, `actual_hours`, `can_devide`) VALUES
(1, 'برمجة 2', 1, 1, 1, 2, 'CSCI 1309', 3, 3, 1),
(2, 'برمجة 3', 1, 2, 2, 2, 'CSCI 2308', 3, 3, 1),
(3, 'تفاضل وتكامل (أ)', 1, 1, 1, 1, 'MATHA 1301', 3, 3, 1),
(4, 'مقدمة في الحوسبة - عملي', 0, 1, 1, 1, 'CSCI 1103', 1, 2, 0),
(5, 'برمجة (1)', 1, 1, 1, 1, 'CSCI 1304', 3, 3, 1),
(6, 'برمجة (1) - عملي', 0, 1, 1, 1, 'CSCI 1104', 1, 2, 0),
(7, 'مقدمة في الحوسبة', 1, 1, 1, 1, 'CSCI 1303', 3, 3, 1),
(8, 'برمجة (2) - عملي', 0, 1, 1, 2, 'CSCI 1106', 1, 2, 0),
(9, 'رياضيات منفصلة', 1, 1, 1, 2, 'CSCI 2303', 3, 3, 1),
(10, 'تصميم منطقي', 1, 1, 1, 2, 'CSCI 2301', 3, 3, 1),
(11, 'اللغة الإنجليزية (تكنولوجيا المعلومات)', 1, 1, 1, 2, 'ENGL 1307', 3, 3, 1),
(12, 'الرياضيات والحوسبة', 1, 2, 2, 1, 'CSCI 3310', 3, 3, 1),
(13, 'بنية البيانات والخوارزميات', 1, 2, 2, 1, 'CSCI 2309', 3, 3, 1),
(14, 'برمجة ويب (1)', 1, 2, 2, 1, 'SICT 2307', 3, 3, 1),
(15, 'برمجة ويب (1) عملي', 0, 2, 2, 1, 'SICT 2107', 1, 2, 0),
(16, 'هندسة البرمجيات (1)', 1, 2, 2, 1, 'SDEV 2302', 3, 3, 1),
(17, 'بنية البيانات والخوارزميات (عملي)', 0, 2, 2, 1, 'CSCI 2109', 1, 2, 0),
(18, 'نظم إدارة قواعد البيانات (1)', 1, 2, 2, 1, 'SICT 2305', 3, 3, 1),
(19, 'نظم إدارة قواعد البيانات (1) - عملي', 0, 2, 2, 1, 'SICT 2105', 1, 2, 0),
(20, 'برمجة ويب (2)', 1, 2, 2, 2, 'SICT 2308', 3, 3, 1),
(21, 'برمجة ويب (2) عملي', 0, 2, 2, 2, 'SICT 2108', 1, 2, 0),
(22, 'عمارة الكمبيوتر ولغة التجميع', 1, 2, 2, 2, 'CSCI 2310', 3, 3, 1),
(23, 'عمارة الكمبيوتر ولغة التجميع - عملي', 0, 2, 2, 2, 'CSCI 2110', 1, 2, 0),
(24, 'برمجة (3) - عملي', 0, 2, 2, 2, 'CSCI 2108', 1, 2, 0),
(25, 'تحليل وتصميم الخوارزميات', 0, 2, 3, 1, 'CSCI 3314', 3, 3, 1),
(26, 'نظم التشغيل', 1, 2, 3, 1, 'CSCI 3306', 3, 3, 1),
(27, 'نظم التشغيل - عملي', 0, 2, 3, 1, 'CSCI 3106', 1, 2, 0),
(28, 'مبادئ لغة البرمجة', 1, 2, 3, 1, 'CSCI 3303', 3, 3, 1),
(29, 'نظم إدارة قواعد البيانات (2)', 1, 2, 3, 1, 'SICT 3308', 3, 3, 1),
(30, 'نظم إدارة قواعد البيانات (2) - عمل', 0, 2, 3, 1, 'SICT 3108', 1, 2, 0),
(31, 'هندسة البرمجيات (2)', 1, 2, 3, 1, 'SDEV 2304', 3, 3, 1),
(32, 'متطلب اختياري (1)', NULL, 2, 3, 2, 'OPTI 3301', 3, 3, 1),
(33, 'نظرية الأوتوماتا', NULL, 2, 3, 2, 'CSCI 3304', 3, 3, 1),
(34, 'شبكات الكمبيوتر', NULL, 2, 3, 2, 'SICT 3309', 3, 3, 1),
(35, 'شبكات الكمبيوتر - عملي', NULL, 2, 3, 2, 'SICT 3109', 1, 2, 0),
(36, 'تنقيب البيانات', NULL, 2, 3, 2, 'SDEV 3304', 3, 3, 1),
(37, 'متطلب اختياري (2)', NULL, 2, 4, 1, 'OPTI 4302', 3, 3, 1),
(38, 'بحث تخرج', NULL, 2, 4, 1, 'CSCI 4108', 1, 1, 0),
(39, 'رسومات الحاسب', NULL, 2, 4, 1, 'CSCI 4302', 3, 3, 1),
(40, 'أنظمة موزعة وسحابية', NULL, 2, 4, 1, 'SICT 4401', 4, 3, 1),
(41, 'سلامة وأمن المعلومات', NULL, 2, 4, 1, 'SICT 4309', 3, 3, 1),
(42, 'سلامة وأمن المعلومات (عملي)', NULL, 2, 4, 1, 'SICT 43090', 0, 2, 0),
(43, '(عملي) رسومات الحاسب', NULL, 2, 4, 1, 'CSCI 43020', 0, 2, 0),
(44, '(عملي) أنظمة موزعة وسحابية', NULL, 2, 4, 1, 'SICT 44010', 0, 2, 0),
(45, 'مهارات الاتصال وريادة الأعمال', NULL, 2, 4, 2, 'CSCI 4328', 3, 3, 1),
(46, 'الذكاء الاصطناعي', NULL, 2, 4, 1, 'CSCI 4304', 3, 3, 1),
(47, 'الذكاء الاصطناعي (عملي)', NULL, 2, 4, 2, 'CSCI 43040', 0, 2, 0),
(48, 'مشروع تخرج', NULL, 2, 4, 2, 'CSCI 4208', 2, 0, 0),
(49, 'أخلاقيات المهنة', NULL, 2, 4, 2, 'SICT 2202', 2, 2, 1),
(50, 'متطلب اختياري (3)', NULL, 2, 4, 2, 'OPTI 4303', 3, 3, 1),
(51, 'الوسائط المتعددة', NULL, NULL, 2, 0, 'SICT 2306', 3, 3, 1),
(52, 'الوسائط المتعددة (عملي)', NULL, NULL, 2, 0, 'SICT 2106', 1, 2, 0),
(53, 'إدارة الأعمال والتجارة الإلكترونية', NULL, NULL, 3, 0, 'SICT 4419', 4, 3, 1),
(54, 'إدارة الأعمال والتجارة الإلكترونية (عملي)', NULL, NULL, 3, 0, 'SICT 44190', 0, 2, 0),
(55, 'أنظمة المعلومات', NULL, NULL, 3, 0, 'SICT 3306', 3, 3, 1),
(56, 'حوسبة الهواتف المتنقلة', NULL, NULL, 4, 0, 'SICT 4312', 3, 3, 1),
(57, 'مبادئ هندسة البرمجيات', NULL, NULL, 2, 0, 'SDEV 2301', 3, 3, 1),
(58, 'هندسة المتطلبات', NULL, NULL, 3, 0, 'SDEV 2303', 3, 3, 1),
(59, 'تصميم وعمارة البرمجيات', NULL, NULL, 3, 0, 'SDEV 3306', 3, 3, 1),
(60, 'تصميم وعمارة البرمجيات - عملي', NULL, NULL, 3, 0, 'SDEV 3106', 1, 2, 0),
(61, 'فحص البرمجيات وتأكيد الجودة', NULL, NULL, 4, 0, 'SDEV 3305', 3, 3, 1),
(62, 'إدارة مشاريع البرمجيات', NULL, NULL, 4, 0, 'SDEV 3303', 3, 3, 1),
(63, 'أمن البرمجيات', NULL, NULL, 4, 0, 'SDEV 4303', 3, 3, 1),
(64, 'هندسة البرمجيات لخدمة الويب', NULL, NULL, 4, 0, 'SDEV 4314', 3, 3, 1),
(65, 'هندسة برمجيات متقدمة', NULL, NULL, 4, 0, 'SDEV 4304', 3, 3, 1),
(66, 'رسم هندسي', NULL, NULL, 1, 1, 'WDMM 1204', 2, 2, 1),
(67, 'مقدمة للحاسوب والانترنت', NULL, NULL, 1, 1, 'WDMM 1401', 3, 3, 1),
(68, 'مقدمة للحاسوب والانترنت  - عملي', NULL, NULL, 1, 1, 'WDMM 14010', 1, 2, 0),
(69, 'برمجة الوسائط (1)', NULL, NULL, 1, 1, 'WDMM 1402', 3, 3, 1),
(70, 'برمجة الوسائط (1) - عملي', NULL, NULL, 1, 1, 'WDMM 14020', 1, 2, 0),
(71, 'تكنولوجيا الوسائط المتعددة وتأليفها', NULL, NULL, 1, 1, 'WDMM 1403', 3, 3, 1),
(72, 'تكنولوجيا الوسائط المتعددة وتأليفها - عملي', NULL, NULL, 1, 1, 'WDMM 14030', 1, 2, 0),
(73, 'برمجة الوسائط (2)', NULL, NULL, 1, 2, 'WDMM 1405', 3, 3, 1),
(74, 'برمجة الوسائط (2) - عملي', NULL, NULL, 1, 2, 'WDMM 14050', 1, 2, 0),
(75, 'أساسيات التصميم في الويب', NULL, NULL, 1, 2, 'WDMM 1406', 3, 3, 1),
(76, 'أساسيات التصميم في الويب - علمي', NULL, NULL, 1, 2, 'WDMM 14060', 1, 2, 0),
(77, 'تحرير الصوت والصورة الرقمية', NULL, NULL, 1, 2, 'WDMM 1304', 3, 3, 1),
(78, 'الرسم الحر', NULL, NULL, 1, 2, 'WDMM 1201', 2, 2, 1),
(79, 'تطوير الويب (1)', NULL, NULL, 2, 1, 'WDMM 2401', 3, 3, 1),
(80, 'تطوير الويب (1) - عملي', NULL, NULL, 2, 1, 'WDMM 24010', 1, 2, 0),
(81, 'الرياضيات للوسائط المتعددة', NULL, NULL, 2, 1, 'WDMM 2302', 3, 3, 1),
(82, 'قواعد بيانات الوسائط المتعددة والويب (1)', NULL, NULL, 2, 1, 'WDMM 2406', 3, 3, 1),
(83, 'قواعد بيانات الوسائط المتعددة والويب (1) - عملي', NULL, NULL, 2, 1, 'WDMM 24060', 1, 2, 0),
(84, 'تقنيات إنتاج الوسائط الرقمية', NULL, NULL, 2, 1, 'WDMM 2408', 3, 3, 1),
(85, 'تقنيات إنتاج الوسائط الرقمية - عملي', NULL, NULL, 2, 1, 'WDMM 24080', 1, 2, 0),
(86, 'رسومات الويب', NULL, NULL, 2, 2, 'WDMM 2407', 3, 3, 1),
(87, 'رسومات الويب', NULL, NULL, 2, 2, 'WDMM 24070', 1, 2, 0),
(88, 'تطوير الويب (2)', NULL, NULL, 2, 2, 'WDMM 2403', 3, 3, 1),
(89, 'تطوير الويب (2) - عملي', NULL, NULL, 2, 2, 'WDMM 24030', 1, 2, 0),
(90, 'قواعد بيانات الوسائط المتعددة والويب (2)', NULL, NULL, 2, 2, 'WDMM 2409', 3, 3, 1),
(91, 'قواعد بيانات الوسائط المتعددة والويب (2) - عملي', NULL, NULL, 2, 2, 'WDMM 24090', 1, 2, 0),
(92, 'هندسة الوسائط والويب', NULL, NULL, 2, 2, 'WDMM 2303', 3, 3, 1),
(93, 'الحركات في الحاسوب', NULL, NULL, 3, 1, 'WDMM 3403', 3, 3, 1),
(94, 'الحركات في الحاسوب - عملي', NULL, NULL, 3, 1, 'WDMM 34030', 1, 2, 0),
(95, 'أمن الويب', NULL, NULL, 3, 1, 'WDMM 3410', 3, 3, 1),
(96, 'أمن الويب - ملي', NULL, NULL, 3, 1, 'WDMM 34100', 1, 2, 0),
(97, 'إدارة مشاريع الوسائط المتعددة', NULL, NULL, 3, 1, 'WDMM 3405', 3, 3, 1),
(98, 'إدارة مشاريع الوسائط المتعددة - عملي', NULL, NULL, 3, 1, 'WDMM 34050', 1, 2, 0),
(99, 'تجسيد النماذج ثلاثية الأبعاد', NULL, NULL, 3, 1, 'WDMM 3407', 3, 3, 1),
(100, 'تجسيد النماذج ثلاثية الأبعاد - عملي', NULL, NULL, 3, 1, 'WDMM 34070', 1, 2, 0),
(101, 'تحريك المجسمات ثلاثية الأبعاد', NULL, NULL, 3, 2, 'WDMM 3406', 3, 3, 1),
(102, 'تحريك المجسمات ثلاثية الأبعاد - عملي', NULL, NULL, 3, 2, 'WDMM 34060', 1, 2, 0),
(109, 'برمجة الألعاب الإلكترونية', NULL, NULL, 3, 2, 'WDMM 3308', 3, 3, 1),
(110, 'تطبيقات الهواتف الذكية', NULL, NULL, 4, 1, 'WDMM 4401', 3, 3, 1),
(111, 'تطبيقات الهواتف الذكية - عملي', NULL, NULL, 4, 1, 'WDMM 44010', 1, 2, 0),
(112, 'مشروع التخرج', NULL, NULL, 4, 2, 'WDMM 4207', 2, 0, 0),
(113, 'تجربة المستخدم للأجهزة الذكية', NULL, NULL, 1, 2, 'MOBC 1301', 3, 3, 1),
(114, 'برمجة تطبيقات الأجهزة الذكية (1)', NULL, NULL, 2, 1, 'MOBC 2301', 3, 3, 1),
(115, 'برمجة تطبيقات الأجهزة الذكية (1) - عملي', NULL, NULL, 2, 1, 'MOBC 2101', 1, 2, 0),
(116, 'تطوير الويب للأجهزة الذكية', NULL, NULL, 2, 2, 'MOBC 2302', 3, 3, 1),
(117, 'تطوير الويب للأجهزة الذكية - عملي', NULL, NULL, 2, 2, 'MOBC 2102', 1, 2, 0),
(118, 'برمجة تطبيقات الأجهزة الذكية (2)', NULL, NULL, 2, 2, 'MOBC 2303', 3, 3, 1),
(119, 'برمجة تطبيقات الأجهزة الذكية (2) - عملي', NULL, NULL, 2, 2, 'MOBC 2103', 1, 2, 0),
(120, 'قواعد بيانات الأجهزة الذكية والويب', NULL, NULL, 2, 2, 'MOBC 2304', 3, 3, 1),
(121, 'قواعد بيانات الأجهزة الذكية والويب - عملي', NULL, NULL, 2, 2, 'MOBC 2104', 1, 2, 0),
(122, 'أنماط التصميم لتطبيقات الأجهزة الذكية', NULL, NULL, 2, 2, 'MOBC 2305', 3, 3, 1),
(123, 'برمجة تطبيقات الأجهزة الذكية (3)', NULL, NULL, 3, 1, 'MOBC 3301', 3, 3, 1),
(124, 'برمجة تطبيقات الأجهزة الذكية (3) -عملي', NULL, NULL, 3, 1, 'MOBC 3101', 1, 2, 0),
(125, 'الحوسبة المنتشرة', NULL, NULL, 3, 1, 'MOBC 3302', 3, 3, 1),
(126, 'الحوسبة المنتشرة - عملي', NULL, NULL, 3, 1, 'MOBC 3102', 1, 2, 0),
(127, 'التطبيقات الشبكية للأجهزة الذكية', NULL, NULL, 3, 1, 'MOBC 3303', 3, 3, 1),
(128, 'التطبيقات الشبكية للأجهزة الذكية - عملي', NULL, NULL, 3, 1, 'MOBC 3103', 1, 2, 0),
(129, 'الحوسبة السحابية وتطبيقاتها', NULL, NULL, 3, 2, 'MOBC 3304', 3, 3, 1),
(130, 'الحوسبة السحابية وتطبيقاتها - عملي', NULL, NULL, 3, 2, 'MOBC 3104', 1, 2, 0),
(131, 'تطبيقات الوسائط المتعددة للأجهزة الذكية', NULL, NULL, 3, 2, 'MOBC 3305', 3, 3, 1),
(132, 'تطبيقات الوسائط المتعددة للأجهزة الذكية - عملي', NULL, NULL, 3, 2, 'MOBC 3105', 1, 2, 0),
(133, 'تدريب ميداني', NULL, NULL, 3, 2, 'MOBC 3006', 2, 0, 0),
(134, 'فحص برمجيات الأجهزة الذكية', NULL, NULL, 4, 1, 'MOBC 4301', 3, 3, 1),
(135, 'فحص برمجيات الأجهزة الذكية - عملي', NULL, NULL, 4, 1, 'MOBC 4101', 1, 2, 0),
(136, 'العمل الحر والتربح من الانترنت', NULL, NULL, 4, 1, 'MOBC 4302', 3, 3, 1),
(137, 'العمل الحر والتربح من الانترنت - عملي', NULL, NULL, 4, 1, 'MOBC 4102', 1, 2, 0),
(138, 'برمجة الألعاب للأجهزة الذكية', NULL, NULL, 4, 2, 'MOBC 4303', 3, 3, 1),
(139, 'برمجة الألعاب للأجهزة الذكية - عملي', NULL, NULL, 4, 2, 'MOBC 4103', 1, 2, 0),
(140, 'مشروع التخرج', NULL, NULL, 4, 2, 'MOBC 4204', 2, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `courses_majors`
--

CREATE TABLE `courses_majors` (
  `id` int(11) NOT NULL,
  `course_id` int(11) DEFAULT NULL,
  `major_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `courses_majors`
--

INSERT INTO `courses_majors` (`id`, `course_id`, `major_id`) VALUES
(3, 2, 3),
(4, 2, 4),
(13, 14, 3),
(14, 14, 4),
(16, 15, 3),
(17, 15, 4),
(19, 13, 3),
(20, 13, 4),
(22, 17, 3),
(23, 17, 4),
(25, 12, 3),
(26, 12, 4),
(30, 18, 3),
(31, 18, 4),
(33, 19, 3),
(34, 19, 4),
(35, 26, 3),
(36, 26, 4),
(37, 25, 3),
(38, 25, 4),
(39, 31, 4),
(40, 29, 3),
(41, 29, 4),
(42, 30, 3),
(43, 30, 4);

-- --------------------------------------------------------

--
-- Table structure for table `days`
--

CREATE TABLE `days` (
  `id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `weekend` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `days`
--

INSERT INTO `days` (`id`, `name`, `weekend`) VALUES
(1, 'السبت', 0),
(2, 'الأحد', 0),
(3, 'الإثنين', 0),
(4, 'الثلاثاء', 0),
(5, 'الأربعاء', 0),
(6, 'الخميس', 1),
(7, 'الجمعة', 1),
(8, 'non', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `final_table`
--

CREATE TABLE `final_table` (
  `id` int(4) NOT NULL,
  `section_id` int(4) DEFAULT NULL,
  `room_id` int(4) DEFAULT NULL,
  `timeslots_day_id` int(4) DEFAULT NULL,
  `start` float DEFAULT NULL,
  `end` float DEFAULT NULL,
  `teacher_id` int(11) DEFAULT NULL,
  `major_id` int(11) DEFAULT NULL,
  `level_number` int(11) DEFAULT NULL,
  `semester` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `final_table`
--

INSERT INTO `final_table` (`id`, `section_id`, `room_id`, `timeslots_day_id`, `start`, `end`, `teacher_id`, `major_id`, `level_number`, `semester`) VALUES
(334, 3, NULL, 1, 10, 11, 1, 1, 1, 1),
(335, 2, NULL, 1, 8, 9, 1, 1, 1, 1),
(336, 4, NULL, 1, 9, 10, 4, 1, 1, 1),
(337, 5, NULL, 2, 8, 9.5, 4, 1, 1, 1),
(338, 1, NULL, 2, 9.5, 11, 2, 1, 1, 1),
(339, 6, NULL, 9, 14, 16, 7, 1, 1, 1),
(340, 8, NULL, 8, 14, 16, 7, 1, 1, 1),
(341, 7, NULL, 9, 12, 14, 7, 1, 1, 1),
(342, 10, NULL, 10, 12, 14, 8, 1, 1, 1),
(343, 9, NULL, 10, 14, 16, 8, 1, 1, 1),
(344, 11, NULL, 6, 13, 15, 8, 1, 1, 1),
(345, 13, NULL, 1, 13, 14, 1, 1, 1, 1),
(346, 14, NULL, 2, 8, 9.5, 4, 1, 1, 1),
(347, 12, NULL, 2, 13.5, 15, 2, 1, 1, 1),
(348, 16, NULL, 10, 10, 12, 8, 1, 1, 1),
(349, 15, NULL, 7, 10, 12, 7, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `hours`
--

CREATE TABLE `hours` (
  `id` int(4) NOT NULL,
  `hour_start` time NOT NULL,
  `hour_end` time NOT NULL,
  `break` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `hours`
--

INSERT INTO `hours` (`id`, `hour_start`, `hour_end`, `break`) VALUES
(1, '08:00:00', '16:00:00', '11:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `levels`
--

CREATE TABLE `levels` (
  `id` int(11) NOT NULL,
  `nickName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `levels`
--

INSERT INTO `levels` (`id`, `nickName`) VALUES
(1, 'المستوى الاول'),
(2, 'المستوى الثاني'),
(3, 'المستوى الثالث'),
(4, 'المستوى الرابع');

-- --------------------------------------------------------

--
-- Table structure for table `majors`
--

CREATE TABLE `majors` (
  `id` int(4) NOT NULL,
  `name` varchar(255) NOT NULL,
  `nickName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `majors`
--

INSERT INTO `majors` (`id`, `name`, `nickName`) VALUES
(1, 'IT_general', 'القسم العام'),
(2, 'CS', 'علم الحاسوب'),
(3, 'SDEV', 'تطوير البرمجيات'),
(4, 'SICT', 'نظم تكنولوجيا المعلومات'),
(5, 'WDMM', 'تكنولوجيا الوسائط المتعددة و تطوير الويب'),
(6, 'MOBC', 'الحوسبة المتنقلة وتطبيقات الأجهزة الذكية');

-- --------------------------------------------------------

--
-- Table structure for table `rooms`
--

CREATE TABLE `rooms` (
  `id` int(4) NOT NULL,
  `building_id` int(4) DEFAULT NULL,
  `floor_number` int(2) DEFAULT NULL,
  `room_number` int(4) DEFAULT NULL,
  `capacity` int(4) DEFAULT NULL,
  `room_type_id` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rooms`
--

INSERT INTO `rooms` (`id`, `building_id`, `floor_number`, `room_number`, `capacity`, `room_type_id`) VALUES
(1, 1, 3, 18, 25, 1),
(2, 2, 2, 3, 100, 2);

-- --------------------------------------------------------

--
-- Table structure for table `rooms_ specialization`
--

CREATE TABLE `rooms_ specialization` (
  `id` int(4) NOT NULL,
  `specialization` varchar(255) NOT NULL,
  `tools` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rooms_ specialization`
--

INSERT INTO `rooms_ specialization` (`id`, `specialization`, `tools`) VALUES
(1, 'Dell', 'Dell Laptops '),
(2, 'مكاتب', 'LCD,computer');

-- --------------------------------------------------------

--
-- Table structure for table `rooms_types`
--

CREATE TABLE `rooms_types` (
  `id` int(4) NOT NULL,
  `studing_type` varchar(255) NOT NULL,
  `gender_type` tinyint(1) NOT NULL,
  `specialization_id` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `rooms_types`
--

INSERT INTO `rooms_types` (`id`, `studing_type`, `gender_type`, `specialization_id`) VALUES
(1, 'عملي', 3, 1),
(2, 'نظري', 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `sections`
--

CREATE TABLE `sections` (
  `id` int(5) NOT NULL,
  `course_id` int(4) DEFAULT NULL,
  `major_id` int(4) DEFAULT NULL,
  `teacher_id` int(4) DEFAULT NULL,
  `gender_type` char(1) DEFAULT NULL,
  `section_number` int(4) DEFAULT NULL,
  `size` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sections`
--

INSERT INTO `sections` (`id`, `course_id`, `major_id`, `teacher_id`, `gender_type`, `section_number`, `size`) VALUES
(1, 3, 1, 2, '1', 101, 70),
(2, 5, 1, 1, '1', 101, 40),
(3, 5, 1, 1, '1', 102, 30),
(4, 7, 1, 4, '1', 101, 40),
(5, 7, 1, 4, '1', 102, 30),
(6, 6, 1, 7, '1', 101, 25),
(7, 6, 1, 7, '1', 102, 25),
(8, 6, 1, 7, '1', 103, 20),
(9, 4, 1, 8, '1', 101, 25),
(10, 4, 1, 8, '1', 102, 25),
(11, 4, 1, 8, '1', 103, 20),
(12, 3, 1, 2, '2', 201, 30),
(13, 5, 1, 1, '2', 201, 30),
(14, 7, 1, 4, '2', 201, 30),
(15, 4, 1, 7, '2', 201, 30),
(16, 6, 1, 8, '2', 201, 30);

-- --------------------------------------------------------

--
-- Table structure for table `studytimes`
--

CREATE TABLE `studytimes` (
  `id` int(3) NOT NULL,
  `day_id` int(11) DEFAULT NULL,
  `start` double NOT NULL,
  `end` double NOT NULL,
  `timeslot_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `studytimes`
--

INSERT INTO `studytimes` (`id`, `day_id`, `start`, `end`, `timeslot_id`) VALUES
(1, 1, 8, 9, 1),
(2, 1, 9, 10, 1),
(3, 1, 10, 11, 1),
(4, 1, 12, 13, 1),
(5, 1, 13, 14, 1),
(6, 1, 14, 15, 1),
(7, 1, 15, 16, 1),
(8, 1, 8, 10, 3),
(9, 1, 9, 11, 3),
(10, 1, 10, 12, 3),
(11, 1, 12, 14, 3),
(12, 1, 14, 16, 3),
(13, 2, 8, 9.5, 2),
(14, 2, 9.5, 11, 2),
(15, 2, 12.5, 14, 2),
(16, 2, 13.5, 15, 2),
(17, 2, 8, 9, 1),
(18, 2, 9, 10, 1),
(19, 2, 10, 11, 1),
(20, 2, 12, 13, 1),
(21, 2, 13, 14, 1),
(22, 2, 14, 15, 1),
(23, 2, 15, 16, 1),
(24, 2, 8, 10, 3),
(26, 2, 10, 12, 3),
(29, 2, 14, 16, 3),
(30, 3, 8, 9, 1),
(31, 3, 9, 10, 1),
(32, 3, 10, 11, 1),
(33, 3, 12, 13, 1),
(34, 3, 13, 14, 1),
(35, 3, 14, 15, 1),
(36, 3, 15, 16, 1),
(37, 3, 8, 10, 3),
(38, 3, 9, 11, 3),
(39, 3, 10, 12, 3),
(40, 3, 12, 14, 3),
(41, 3, 14, 16, 3),
(42, 5, 8, 9, 1),
(43, 5, 9, 10, 1),
(44, 5, 10, 11, 1),
(45, 5, 12, 13, 1),
(46, 5, 13, 14, 1),
(47, 5, 14, 15, 1),
(48, 5, 15, 16, 1),
(49, 5, 8, 10, 3),
(50, 5, 9, 11, 3),
(51, 5, 10, 12, 3),
(52, 5, 12, 14, 3),
(53, 5, 14, 16, 3),
(54, 4, 8, 9, 1),
(55, 4, 9, 10, 1),
(56, 4, 10, 11, 1),
(57, 4, 12, 13, 1),
(58, 4, 13, 14, 1),
(59, 4, 14, 15, 1),
(60, 4, 15, 16, 1),
(61, 4, 8, 10, 3),
(63, 4, 10, 12, 3),
(65, 4, 14, 16, 3),
(66, 4, 8, 9.5, 2),
(67, 4, 9.5, 11, 2),
(68, 4, 12.5, 14, 2),
(69, 4, 13.5, 15, 2),
(71, 1, 13, 15, 3),
(76, 5, 13, 15, 3),
(77, 3, 13, 15, 3),
(78, 2, 12, 14, 3),
(79, 4, 12, 14, 3);

-- --------------------------------------------------------

--
-- Table structure for table `teachers`
--

CREATE TABLE `teachers` (
  `id` int(4) NOT NULL,
  `name` varchar(255) NOT NULL,
  `houres` int(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `teachers`
--

INSERT INTO `teachers` (`id`, `name`, `houres`) VALUES
(1, 'باسم عمر العجلة', NULL),
(2, 'أشرف المغاري', NULL),
(3, 'عبد الكريم الأشقر', NULL),
(4, 'وائل السراج', NULL),
(5, 'كريم مهدي', NULL),
(6, 'معتز سعد', NULL),
(7, 'محمد الكرش', NULL),
(8, 'محمد الأغا', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `timeslots`
--

CREATE TABLE `timeslots` (
  `id` int(4) NOT NULL,
  `period` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `timeslots`
--

INSERT INTO `timeslots` (`id`, `period`) VALUES
(1, 1),
(2, 1.5),
(3, 2),
(4, 0);

-- --------------------------------------------------------

--
-- Table structure for table `timeslots_days`
--

CREATE TABLE `timeslots_days` (
  `id` int(4) NOT NULL,
  `timeslot_id` int(4) DEFAULT NULL,
  `day_id_o` int(4) DEFAULT NULL,
  `day_id_t` int(4) DEFAULT NULL,
  `day_id_th` int(4) DEFAULT NULL,
  `day_id_f` int(4) DEFAULT NULL,
  `day_id_fi` int(4) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `timeslots_days`
--

INSERT INTO `timeslots_days` (`id`, `timeslot_id`, `day_id_o`, `day_id_t`, `day_id_th`, `day_id_f`, `day_id_fi`) VALUES
(1, 1, 1, 3, 5, 8, 8),
(2, 2, 2, 4, 8, 8, 8),
(3, 1, 1, 3, 8, 8, 8),
(4, 1, 1, 5, 8, 8, 8),
(5, 1, 3, 5, 8, 8, 8),
(6, 3, 1, 8, 8, 8, 8),
(7, 3, 2, 8, 8, 8, 8),
(8, 3, 3, 8, 8, 8, 8),
(9, 3, 4, 8, 8, 8, 8),
(10, 3, 5, 8, 8, 8, 8),
(12, 1, 1, 8, 8, 8, 8),
(13, 1, 2, 8, 8, 8, 8),
(14, 1, 3, 8, 8, 8, 8),
(15, 1, 4, 8, 8, 8, 8),
(16, 1, 5, 8, 8, 8, 8),
(17, 4, 8, 8, 8, 8, 8);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `buildings`
--
ALTER TABLE `buildings`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `courses`
--
ALTER TABLE `courses`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `course_number` (`course_number`),
  ADD KEY `major_id` (`major_id`);

--
-- Indexes for table `courses_majors`
--
ALTER TABLE `courses_majors`
  ADD PRIMARY KEY (`id`),
  ADD KEY `course_id` (`course_id`),
  ADD KEY `major_id` (`major_id`);

--
-- Indexes for table `days`
--
ALTER TABLE `days`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `final_table`
--
ALTER TABLE `final_table`
  ADD PRIMARY KEY (`id`),
  ADD KEY `final_table_ibfk_1` (`section_id`),
  ADD KEY `final_table_ibfk_2` (`room_id`),
  ADD KEY `final_table_ibfk_3` (`timeslots_day_id`),
  ADD KEY `teacher_id` (`teacher_id`);

--
-- Indexes for table `hours`
--
ALTER TABLE `hours`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `levels`
--
ALTER TABLE `levels`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `majors`
--
ALTER TABLE `majors`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`id`),
  ADD KEY `building_id` (`building_id`),
  ADD KEY `room_type_id` (`room_type_id`);

--
-- Indexes for table `rooms_ specialization`
--
ALTER TABLE `rooms_ specialization`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rooms_types`
--
ALTER TABLE `rooms_types`
  ADD PRIMARY KEY (`id`),
  ADD KEY `specialization_id` (`specialization_id`);

--
-- Indexes for table `sections`
--
ALTER TABLE `sections`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sections_ibfk_1` (`course_id`),
  ADD KEY `sections_ibfk_2` (`teacher_id`),
  ADD KEY `sections_ibfk_3` (`major_id`);

--
-- Indexes for table `studytimes`
--
ALTER TABLE `studytimes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `day_id` (`day_id`),
  ADD KEY `timeslot_id` (`timeslot_id`);

--
-- Indexes for table `teachers`
--
ALTER TABLE `teachers`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `timeslots`
--
ALTER TABLE `timeslots`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `timeslots_days`
--
ALTER TABLE `timeslots_days`
  ADD PRIMARY KEY (`id`),
  ADD KEY `timeslot_id` (`timeslot_id`),
  ADD KEY `timeslots_days_ibfk_2` (`day_id_o`),
  ADD KEY `day_id_t` (`day_id_t`),
  ADD KEY `day_id_th` (`day_id_th`),
  ADD KEY `day_id_fi` (`day_id_fi`),
  ADD KEY `day_id_f` (`day_id_f`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `buildings`
--
ALTER TABLE `buildings`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `courses`
--
ALTER TABLE `courses`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=141;

--
-- AUTO_INCREMENT for table `courses_majors`
--
ALTER TABLE `courses_majors`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;

--
-- AUTO_INCREMENT for table `days`
--
ALTER TABLE `days`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `final_table`
--
ALTER TABLE `final_table`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=350;

--
-- AUTO_INCREMENT for table `hours`
--
ALTER TABLE `hours`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `majors`
--
ALTER TABLE `majors`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT for table `rooms`
--
ALTER TABLE `rooms`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `rooms_ specialization`
--
ALTER TABLE `rooms_ specialization`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `rooms_types`
--
ALTER TABLE `rooms_types`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `sections`
--
ALTER TABLE `sections`
  MODIFY `id` int(5) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `studytimes`
--
ALTER TABLE `studytimes`
  MODIFY `id` int(3) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=80;

--
-- AUTO_INCREMENT for table `teachers`
--
ALTER TABLE `teachers`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `timeslots`
--
ALTER TABLE `timeslots`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `timeslots_days`
--
ALTER TABLE `timeslots_days`
  MODIFY `id` int(4) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `courses`
--
ALTER TABLE `courses`
  ADD CONSTRAINT `courses_ibfk_1` FOREIGN KEY (`major_id`) REFERENCES `majors` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `courses_majors`
--
ALTER TABLE `courses_majors`
  ADD CONSTRAINT `courses_majors_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`),
  ADD CONSTRAINT `courses_majors_ibfk_2` FOREIGN KEY (`major_id`) REFERENCES `majors` (`id`);

--
-- Constraints for table `final_table`
--
ALTER TABLE `final_table`
  ADD CONSTRAINT `final_table_ibfk_1` FOREIGN KEY (`section_id`) REFERENCES `sections` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `final_table_ibfk_2` FOREIGN KEY (`room_id`) REFERENCES `rooms` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `final_table_ibfk_3` FOREIGN KEY (`timeslots_day_id`) REFERENCES `timeslots_days` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `final_table_ibfk_4` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`);

--
-- Constraints for table `rooms`
--
ALTER TABLE `rooms`
  ADD CONSTRAINT `rooms_ibfk_1` FOREIGN KEY (`building_id`) REFERENCES `buildings` (`id`),
  ADD CONSTRAINT `rooms_ibfk_2` FOREIGN KEY (`room_type_id`) REFERENCES `rooms_types` (`id`);

--
-- Constraints for table `rooms_types`
--
ALTER TABLE `rooms_types`
  ADD CONSTRAINT `rooms_types_ibfk_1` FOREIGN KEY (`specialization_id`) REFERENCES `rooms_ specialization` (`id`);

--
-- Constraints for table `sections`
--
ALTER TABLE `sections`
  ADD CONSTRAINT `sections_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `courses` (`id`) ON DELETE NO ACTION,
  ADD CONSTRAINT `sections_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teachers` (`id`) ON DELETE NO ACTION,
  ADD CONSTRAINT `sections_ibfk_3` FOREIGN KEY (`major_id`) REFERENCES `majors` (`id`) ON DELETE NO ACTION;

--
-- Constraints for table `studytimes`
--
ALTER TABLE `studytimes`
  ADD CONSTRAINT `studytimes_ibfk_1` FOREIGN KEY (`day_id`) REFERENCES `days` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `studytimes_ibfk_2` FOREIGN KEY (`timeslot_id`) REFERENCES `timeslots` (`id`) ON DELETE SET NULL ON UPDATE SET NULL;

--
-- Constraints for table `timeslots_days`
--
ALTER TABLE `timeslots_days`
  ADD CONSTRAINT `timeslots_days_ibfk_1` FOREIGN KEY (`timeslot_id`) REFERENCES `timeslots` (`id`),
  ADD CONSTRAINT `timeslots_days_ibfk_2` FOREIGN KEY (`day_id_o`) REFERENCES `days` (`id`),
  ADD CONSTRAINT `timeslots_days_ibfk_3` FOREIGN KEY (`day_id_t`) REFERENCES `days` (`id`),
  ADD CONSTRAINT `timeslots_days_ibfk_4` FOREIGN KEY (`day_id_th`) REFERENCES `days` (`id`),
  ADD CONSTRAINT `timeslots_days_ibfk_5` FOREIGN KEY (`day_id_fi`) REFERENCES `days` (`id`),
  ADD CONSTRAINT `timeslots_days_ibfk_6` FOREIGN KEY (`day_id_f`) REFERENCES `days` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
