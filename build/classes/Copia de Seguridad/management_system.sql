-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 22-11-2021 a las 00:24:32
-- Versión del servidor: 10.4.21-MariaDB
-- Versión de PHP: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `management_system`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria`
--

CREATE TABLE `categoria` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `categoria`
--

INSERT INTO `categoria` (`id`, `nombre`, `descripcion`) VALUES
(1, 'moda y accesorios ', 'en esta sección se encuentran productos de uso per'),
(2, 'tecnología ', 'en esta sección se encuentran productos de uso dom'),
(3, 'electrodomésticos ', 'en esta sección se encuentran productos de uso cot'),
(4, 'dormitorio', 'en esta sección se encuentran productos que se emp'),
(5, 'farmacia  ', 'en esta sección se encuentran productos para mejor');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id` int(10) UNSIGNED NOT NULL,
  `cedula` int(11) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `telefono` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id`, `cedula`, `nombres`, `apellido`, `direccion`, `telefono`) VALUES
(1, 1005231784, 'jose', 'Valdiviezo', 'manzana 2 casa 2 altamira', '3208943'),
(2, 1003232165, 'miguel', 'lopez', 'manzana 4 casa 8 granada', '8326732'),
(3, 1032743821, 'juan', 'osorio ', 'kr 94d N. 2-26', '4325719'),
(4, 100423531, 'anderson', 'castillo', 'kr 74d N. 3-13', '9547312'),
(5, 1002542183, 'angie', 'tutistar', 'manzana 5 casa 2 atahualpa', '6432392'),
(6, 234187514, 'jhonatan', 'García', 'manzana 3 casa 6 tamasagra', '9871327'),
(7, 62457523, 'sara', 'ortiz', 'kr 87d N. 5-52', '7436917'),
(8, 98716325, 'antonio', 'yaqueno', 'kr 83d N. 7-93', '6543281'),
(9, 1234621873, 'vivana', 'ortiz', 'manzana 8 casa 7 Jerusalén', '754236578'),
(10, 54830107, 'andres', 'obando', 'kr 13d N. 3-21', '9431537');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id` int(10) UNSIGNED NOT NULL,
  `cedula` int(11) NOT NULL,
  `nombres` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `telefono` varchar(50) NOT NULL,
  `cargo` varchar(70) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `clave` varchar(50) NOT NULL,
  `rol` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `empleado`
--

INSERT INTO `empleado` (`id`, `cedula`, `nombres`, `apellido`, `direccion`, `telefono`, `cargo`, `usuario`, `clave`, `rol`) VALUES
(1, 1085330017, 'JUAN CARLOS', 'ASTAIZA ORDOÑEZ', 'Manzana C7 cs 12 /Miraflores', '7228211', 'Ingeniero de Sistemas', 'juanca', '123', 'Administrador');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrada_mercancias`
--

CREATE TABLE `entrada_mercancias` (
  `id` int(10) UNSIGNED NOT NULL,
  `fecha` varchar(50) NOT NULL,
  `hora` varchar(50) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `id_prod` int(10) UNSIGNED NOT NULL,
  `id_prov` int(10) UNSIGNED NOT NULL,
  `id_emp` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `entrega_mercancias`
--

CREATE TABLE `entrega_mercancias` (
  `id` int(10) UNSIGNED NOT NULL,
  `fecha` varchar(50) NOT NULL,
  `hora` varchar(50) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `id_prod` int(10) UNSIGNED NOT NULL,
  `id_cli` int(10) UNSIGNED NOT NULL,
  `id_emp` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `productos`
--

CREATE TABLE `productos` (
  `id` int(10) UNSIGNED NOT NULL,
  `codigo` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `existencias` varchar(50) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `fecha_vencimiento` varchar(50) DEFAULT NULL,
  `id_categoria` int(10) UNSIGNED NOT NULL,
  `id_subcategoria` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proveedor`
--

CREATE TABLE `proveedor` (
  `id` int(10) UNSIGNED NOT NULL,
  `nit` varchar(50) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `apellido` varchar(50) NOT NULL,
  `direccion` varchar(50) NOT NULL,
  `telefono` varchar(50) NOT NULL,
  `empresa` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `proveedor`
--

INSERT INTO `proveedor` (`id`, `nit`, `nombre`, `apellido`, `direccion`, `telefono`, `empresa`) VALUES
(1, '6844096331', 'luiz', 'gustin', 'kr 78g n 2-65', '9212386', 'Sudelec'),
(2, '6390901636', 'jorge', 'salazar', 'kr 32a n 6-72', '5648503', 'Challenger'),
(3, '5791170648', 'Luis ', 'Eduardo', 'manzana 4 casa 8 Bombona', '2832789', 'Haceb'),
(4, '3270268185', 'Carlos', ' Alberto', 'manzana 7 casa 1 El Churo', '5734369', 'Abba'),
(5, '2784313772', 'José ', 'Martínez', 'manzana 10 casa 5 Los Dos Puentes', '4118462', 'D&M LABORATORIOS'),
(6, '5582377008', 'Luz ', 'García', 'manzana 4 casa 10 Caracha', '9635428', 'LABORATORIOS ESKO LTDA'),
(7, '1862343776', 'jaime ', 'García', 'kr 12b n 6-50', '9913009', 'BRESCIA'),
(8, '7202499091', 'María ', 'González', 'kr 51c n 1-15', '4580064', 'Osteoequipos S.A.S'),
(9, '4130029499', 'Isabel ', 'García', 'manzana 2 casa 1 Santiago', '9565090', 'CB Medical S.A.S'),
(10, '4851985296', 'José ', 'Ramírez', 'manzana 4 casa 8 San José Obrero', '4768873', 'Distribuciones AXA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `subcategoria`
--

CREATE TABLE `subcategoria` (
  `id` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(50) DEFAULT NULL,
  `id_categoria` int(10) UNSIGNED NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `subcategoria`
--

INSERT INTO `subcategoria` (`id`, `nombre`, `descripcion`, `id_categoria`) VALUES
(1, 'ropa', 'en esta subcategoría se tiene ropa interior como r', 1),
(2, 'accesorios ', 'en esta subcategoría se tiene productos para acomp', 1),
(3, 'televisores ', 'en esta subcategoría se encuentran televisores de ', 2),
(4, 'computadores', 'en esta subcategoría se tiene diferentes tipos de ', 2),
(5, 'camara', 'en esta subcategoría se tiene diferentes tipos de ', 2),
(6, 'mundo gamer', 'en esta subcategoría se tiene productos de uso gam', 2),
(7, 'audio', 'en esta subcategoría se tiene productos para ampli', 2),
(8, 'refrigeración ', 'en esta subcategoría se encuentran productos para ', 3),
(9, 'lavado y secado', 'en esta subcategoría se tiene productos para limpi', 3),
(11, 'cocina', 'en esta subcategoría se tiene productos para uso e', 3),
(13, 'hogar', 'en esta subcategoría se tiene productos para usar ', 3),
(14, 'climatización ', 'en esta subcategoría se tiene productos para mante', 3),
(15, 'cochones', 'en esta subcategoría se tiene diferentes tipos de ', 4),
(16, 'cuartos ', 'en esta subcategoría se tiene productos para usar ', 4),
(17, 'medicamento ', 'en esta subcategoría se tiene productos para uso e', 5);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `categoria`
--
ALTER TABLE `categoria`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cedula` (`cedula`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `cedula` (`cedula`),
  ADD UNIQUE KEY `usuario` (`usuario`);

--
-- Indices de la tabla `entrada_mercancias`
--
ALTER TABLE `entrada_mercancias`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_emp` (`id_emp`),
  ADD KEY `id_prod` (`id_prod`),
  ADD KEY `id_prov` (`id_prov`);

--
-- Indices de la tabla `entrega_mercancias`
--
ALTER TABLE `entrega_mercancias`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_cli` (`id_cli`),
  ADD KEY `id_emp` (`id_emp`),
  ADD KEY `id_prod` (`id_prod`);

--
-- Indices de la tabla `productos`
--
ALTER TABLE `productos`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `codigo` (`codigo`),
  ADD KEY `id_categoria` (`id_categoria`),
  ADD KEY `id_subcategoria` (`id_subcategoria`);

--
-- Indices de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nit` (`nit`);

--
-- Indices de la tabla `subcategoria`
--
ALTER TABLE `subcategoria`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nombre` (`nombre`),
  ADD KEY `id_categoria` (`id_categoria`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria`
--
ALTER TABLE `categoria`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `empleado`
--
ALTER TABLE `empleado`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `entrada_mercancias`
--
ALTER TABLE `entrada_mercancias`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `entrega_mercancias`
--
ALTER TABLE `entrega_mercancias`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `productos`
--
ALTER TABLE `productos`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `proveedor`
--
ALTER TABLE `proveedor`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `subcategoria`
--
ALTER TABLE `subcategoria`
  MODIFY `id` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `entrada_mercancias`
--
ALTER TABLE `entrada_mercancias`
  ADD CONSTRAINT `entrada_mercancias_ibfk_1` FOREIGN KEY (`id_emp`) REFERENCES `empleado` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `entrada_mercancias_ibfk_2` FOREIGN KEY (`id_prod`) REFERENCES `productos` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `entrada_mercancias_ibfk_3` FOREIGN KEY (`id_prov`) REFERENCES `proveedor` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `entrega_mercancias`
--
ALTER TABLE `entrega_mercancias`
  ADD CONSTRAINT `entrega_mercancias_ibfk_1` FOREIGN KEY (`id_cli`) REFERENCES `clientes` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `entrega_mercancias_ibfk_2` FOREIGN KEY (`id_emp`) REFERENCES `empleado` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `entrega_mercancias_ibfk_3` FOREIGN KEY (`id_prod`) REFERENCES `productos` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `productos`
--
ALTER TABLE `productos`
  ADD CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `productos_ibfk_2` FOREIGN KEY (`id_subcategoria`) REFERENCES `subcategoria` (`id`) ON UPDATE CASCADE;

--
-- Filtros para la tabla `subcategoria`
--
ALTER TABLE `subcategoria`
  ADD CONSTRAINT `subcategoria_ibfk_1` FOREIGN KEY (`id_categoria`) REFERENCES `categoria` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
