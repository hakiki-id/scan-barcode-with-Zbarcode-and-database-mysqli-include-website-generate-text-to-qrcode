-- phpMyAdmin SQL Dump
-- version 4.4.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 06, 2016 at 08:09 AM
-- Server version: 5.6.26
-- PHP Version: 5.6.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbbuku`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_buku`
--

CREATE TABLE IF NOT EXISTS `tb_buku` (
  `idbarcode` varchar(50) NOT NULL,
  `judul` varchar(100) NOT NULL,
  `penerbit` varchar(50) NOT NULL,
  `tahun` varchar(10) NOT NULL,
  `image` varchar(500) NOT NULL,
  `sinopsis` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_buku`
--

INSERT INTO `tb_buku` (`idbarcode`, `judul`, `penerbit`, `tahun`, `image`, `sinopsis`) VALUES
('18789237489', 'Ketika Cinta Tiada', 'Ahmad Books', '2018', 'kakbah.jpg', 'Ketika cinta mempertemukan 2 hati menjadi satu cinta yang bergelora, ahmad mujahit menjadi lupa akan kehidupannya sebagai seorang mahasiswa. dia selalu mendambakan cinta yang sejati, cinta yang hakiki denga pacarnya. \r\ntp ketika cinta tiada, ahmad menjadi gila, bagaimanakah kisah pertualangan ahmad dalam menemukan cintanya kembali'),
('3485304232', 'Kreatif Sampai Mati', 'Araya Publisher', '2012', 'Lomba Redesain Buku KSM.jpg', 'Seni adalah kehidupan, kata orang seni ada jalan ketiga menuju surga. seni adalah karya sastra tanpa batas. yang mengespresikan diri seorang seniman'),
('89237459238', 'Model Pendidikan Berkarakter', 'Kementrian Pendidikan RI', '2013', 'COVER-PENDIDIKAN-BERKARAKTER.jpg', 'dalam pendidikan anak usia remaja, terdapat metode metode yang dapat dijadikan acuan untuk menjadikan pendidikan yang berkarakter. mampu membentuk kepribadian anak.'),
('923497997', 'APBD 2012', 'Kementrian RI', '2013', 'cover-deskripsi-3-kecil.jpg', 'buku ini membahas tentang tatanan apbd kenegaraan republik indonesia, dalam perancangan keuangan yang terfalidasi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_buku`
--
ALTER TABLE `tb_buku`
  ADD PRIMARY KEY (`idbarcode`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
