<?php
/**
 * Obtiene todas los libros de la base de datos
 */

require 'Libros.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    // Manejar petición GET
    $libros = Libros::getAll();

    if ($libros) {

        $datos["estado"] = 1;
        $datos["libros"] = $libros;

        print json_encode($datos);
    } else {
        print json_encode(array(
            "estado" => 2,
            "mensaje" => "Ha ocurrido un error"
            
        ));
        
    }
}