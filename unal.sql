-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 04-08-2022 a las 04:27:36
-- Versión del servidor: 10.4.11-MariaDB
-- Versión de PHP: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `unal`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `cli_id` int(11) NOT NULL,
  `cli_nombre` varchar(50) NOT NULL,
  `cli_apellido` varchar(50) NOT NULL,
  `cli_nacionalidad` varchar(50) NOT NULL,
  `cli_correo` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `cliente`
--

INSERT INTO `cliente` (`cli_id`, `cli_nombre`, `cli_apellido`, `cli_nacionalidad`, `cli_correo`) VALUES
(0, 'Cliente no registrado', 'Cliente no registrado', 'null', 'null'),
(1, 'juan', 'perez', 'colombiana', 'ASDjperez@micorreo.com'),
(2, 'pedro ', 'martinez', 'colombiana', 'pmartinez@micorreo.com'),
(3, 'maria', 'lara', 'colombiana', 'mlara@micorreo.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `factura`
--

CREATE TABLE `factura` (
  `fac_id` int(11) NOT NULL,
  `fac_nombre_empresa` varchar(50) NOT NULL,
  `fac_fecha` date NOT NULL,
  `fac_ciudad` varchar(50) NOT NULL,
  `cli_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `factura`
--

INSERT INTO `factura` (`fac_id`, `fac_nombre_empresa`, `fac_fecha`, `fac_ciudad`, `cli_id`) VALUES
(1, 'Tienda unaleña', '2022-08-03', 'Bogota', 1),
(2, 'Tienda unaleña', '2022-08-03', 'Bogota', 2),
(3, 'Tienda unaleña', '2022-08-03', 'Bogota', 3),
(4, 'Tienda unaleña', '2022-08-03', 'Bogota', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `pro_id` int(11) NOT NULL,
  `pro_nombre` varchar(50) NOT NULL,
  `pro_precio` int(11) NOT NULL,
  `pro_descripcion` varchar(50) NOT NULL,
  `pro_fecha_vencimiento` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`pro_id`, `pro_nombre`, `pro_precio`, `pro_descripcion`, `pro_fecha_vencimiento`) VALUES
(1, 'galletas unaleñas', 1200, 'galletas unaleñas de chocolate', '2021-10-25'),
(2, 'agua unaleña', 2500, 'agua manantial unaleña', '2021-11-25'),
(3, 'gaseosa unaleña', 3500, 'gaseosa unaleña de manzana', '2021-08-20'),
(4, 'leche', 3000, 'Leche entera unaleña', '2022-11-23'),
(6, 'harina', 2400, 'arina para arepas', '2023-11-20'),
(7, 'bombom', 200, 'bobom sabor frutos rojo', '2023-11-20');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto_factura`
--

CREATE TABLE `producto_factura` (
  `profac_id` int(11) NOT NULL,
  `profac_cantidad` int(11) NOT NULL,
  `fac_id` int(11) NOT NULL,
  `pro_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `producto_factura`
--

INSERT INTO `producto_factura` (`profac_id`, `profac_cantidad`, `fac_id`, `pro_id`) VALUES
(1, 5, 1, 1),
(2, 10, 1, 2),
(3, 15, 1, 3),
(4, 22, 2, 1),
(5, 25, 2, 2),
(6, 31, 2, 3),
(7, 35, 3, 1),
(8, 44, 3, 2),
(9, 1, 3, 3),
(10, 13, 4, 1),
(12, 5, 4, 3);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`cli_id`);

--
-- Indices de la tabla `factura`
--
ALTER TABLE `factura`
  ADD PRIMARY KEY (`fac_id`),
  ADD KEY `cli_id` (`cli_id`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`pro_id`);

--
-- Indices de la tabla `producto_factura`
--
ALTER TABLE `producto_factura`
  ADD PRIMARY KEY (`profac_id`),
  ADD KEY `fac_id` (`fac_id`),
  ADD KEY `pro_id` (`pro_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `producto_factura`
--
ALTER TABLE `producto_factura`
  MODIFY `profac_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `factura`
--
ALTER TABLE `factura`
  ADD CONSTRAINT `factura_ibfk_1` FOREIGN KEY (`cli_id`) REFERENCES `cliente` (`cli_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `producto_factura`
--
ALTER TABLE `producto_factura`
  ADD CONSTRAINT `producto_factura_ibfk_1` FOREIGN KEY (`fac_id`) REFERENCES `factura` (`fac_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `producto_factura_ibfk_2` FOREIGN KEY (`pro_id`) REFERENCES `producto` (`pro_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
