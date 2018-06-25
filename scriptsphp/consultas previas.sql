INSERT INTO `ls_Libros` (`li_idLibros`, `li_codUsuario`, `li_idISBN`, `li_titulo`, `li_autor`, `li_edicion`, `li_añoPublicacion`, `li_editorial`, `li_portada`, `li_descripcion`, `li_estadoLibro`, `li_ubicacion`) 
VALUES
(10, '090806-A','86014043210273', 'The Pragmatic Programmer', 'Andy Hunt,Dave Thomas', '1999','1999', 'Editorial desconocida', 'http://libroservice.proyectosdetesis.com/images/Libro1.jpg', 'Sin Contenido', 9, 'San sebastian'),
(11, '090806-A','978-0131872486', 'Thinking in Java', 'Bruce Eckel', '1998','1998', 'Editorial desconocido', 'http://libroservice.proyectosdetesis.com/images/Libro2.jpg', 'Sin Contenido', 10, 'Santa Rosa'),
(12, '090806-A','97856718722226', 'Python in a nutshell', 'Alex Martelli', '2003','2003', 'Editorial desconocido', 'http://libroservice.proyectosdetesis.com/images/Libro3.jpg', 'Sin Contenido', 8, 'San Jeronimo'),
(13, '090806-A','57768681241551', 'JavaScript: The Good Parts', 'Douglas Crockford', '2008','2008', 'Editorial desconocido', 'http://libroservice.proyectosdetesis.com/images/Libro4.jpg', 'Sin Contenido', 8, 'San Jeronimo'),
(14, '090806-A','32323251817362', 'Fundamentos de bases de datos', 'Abraham Silberschatz', '1998','1998', 'Editorial desconocido', 'http://libroservice.proyectosdetesis.com/images/Libro5.jpg', 'Sin Contenido', 7, 'San Jeronimo'),
(15, '090806-A','87123247182911', 'Aprende SQL', 'Alan Beaulieu', '2010','2010', 'Editorial desconocido', 'http://libroservice.proyectosdetesis.com/images/Libro6.jpg', 'Sin Contenido', 10, 'San Jeronimo');




//valores para librocategoria
INSERT INTO `ls_Librocategoria` (`lc_idLibros`, `lc_categoria1`, `lc_categoria2`) 
VALUES 
('10', '10', '11'),
('11', '10', '11'),
('12', '10', '11'),
('13', '10', '11'),
('14', '7', '11'),
('15', '7', '11');

INSERT INTO `ls_Categorias` (`cat_idCategoria`, `cat_nombreCategoria`)
VALUES
('1', 'Inteligencia artificial'),
('2', 'Arquitectura de computadoras'),
('3', 'Análisis de rendimiento de computadoras'),
('4', 'Cómputo científico'),
('5', 'Redes de computadoras'),
('6', 'Sistemas concurrentes, paralelos y distribuidos'),
('7', 'Bases de datos'),
('8', 'Informática en salud'),
('9', 'Ciencia de la información'),
('10', 'Ingeniería de software');