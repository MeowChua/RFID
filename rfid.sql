-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th4 20, 2022 lúc 05:41 AM
-- Phiên bản máy phục vụ: 10.4.11-MariaDB
-- Phiên bản PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `rfid`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `db_order`
--

CREATE TABLE `db_order` (
  `order_id` varchar(30) NOT NULL,
  `order_date` varchar(30) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `db_order`
--

INSERT INTO `db_order` (`order_id`, `order_date`, `status`) VALUES
('O001', '9/4/2022', 1),
('O002', '9/4/2022', 1),
('O003', '13/04/2022', 1),
('O004', '13/04/2022', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `orderdetail`
--

CREATE TABLE `orderdetail` (
  `id` int(11) NOT NULL,
  `order_id` varchar(30) NOT NULL,
  `product_id` varchar(30) NOT NULL,
  `quanity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `orderdetail`
--

INSERT INTO `orderdetail` (`id`, `order_id`, `product_id`, `quanity`) VALUES
(15, 'O001', 'P001', 6),
(16, 'O002', 'P001', 1);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `product`
--

CREATE TABLE `product` (
  `product_id` varchar(30) NOT NULL,
  `product_name` varchar(30) NOT NULL,
  `quanity` int(11) NOT NULL,
  `detail` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `product`
--

INSERT INTO `product` (`product_id`, `product_name`, `quanity`, `detail`) VALUES
('P001', 'Tra xanh 0 do', 200, 'sds'),
('P002', 'Sting', 500, 'fdfd'),
('P003', 'Nuoc tang luc Warrior', 400, 'ksdk'),
('P004', 'C2', 1000, 'dsdd'),
('P005', 'Pepsi', 1000, 'dsdd'),
('P006', 'Coca Cola', 2000, 'dsddd');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `qltk`
--

CREATE TABLE `qltk` (
  `account` varchar(30) NOT NULL,
  `pass` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `qltk`
--

INSERT INTO `qltk` (`account`, `pass`) VALUES
('bao', '123');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `tag_rfid`
--

CREATE TABLE `tag_rfid` (
  `tag_id` varchar(30) NOT NULL,
  `product_id` varchar(30) NOT NULL,
  `date` varchar(30) NOT NULL,
  `gate` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `tag_rfid`
--

INSERT INTO `tag_rfid` (`tag_id`, `product_id`, `date`, `gate`) VALUES
('300F 4F57 3AD0 01C0 8369 A230', 'P001', '2022/04/18 15:37:42', '1'),
('300F 4F57 3AD0 01C0 8369 A241', 'P001', '2022/04/18 15:37:42', '1'),
('300F 4F57 3AD0 01C0 8369 A249', 'P001', '2022/04/18 15:37:42', '1'),
('300F 4F57 3AD0 01C0 8369 A250', 'P001', '2022/04/18 15:37:42', '1'),
('300F 4F57 3AD0 01C0 8369 A255', 'P001', '2022/04/18 15:37:42', '1'),
('300F 4F57 3AD0 01C0 8369 A28F', 'P001', '2022/04/18 15:37:42', '1');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `db_order`
--
ALTER TABLE `db_order`
  ADD PRIMARY KEY (`order_id`);

--
-- Chỉ mục cho bảng `orderdetail`
--
ALTER TABLE `orderdetail`
  ADD PRIMARY KEY (`id`),
  ADD KEY `order_id` (`order_id`),
  ADD KEY `product_id` (`product_id`);

--
-- Chỉ mục cho bảng `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`product_id`);

--
-- Chỉ mục cho bảng `tag_rfid`
--
ALTER TABLE `tag_rfid`
  ADD PRIMARY KEY (`tag_id`),
  ADD KEY `product_id` (`product_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `orderdetail`
--
ALTER TABLE `orderdetail`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `orderdetail`
--
ALTER TABLE `orderdetail`
  ADD CONSTRAINT `orderdetail_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `db_order` (`order_id`),
  ADD CONSTRAINT `orderdetail_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);

--
-- Các ràng buộc cho bảng `tag_rfid`
--
ALTER TABLE `tag_rfid`
  ADD CONSTRAINT `tag_rfid_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
