<?php
/**
 * Obtiene todas los libros de la base de datos
 */

require 'Categorias.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    // Manejar petición GET
    $Cat = Categorias::getAll();

    if ($Cat) {

        $datos["estado"] = 1;
        $datos["usuario"] = $Cat;

        print json_encode($datos);
    } else {
        print json_encode(array(
            "estado" => 2,
            "mensaje" => "Ha ocurrido un error"
            
        ));
        
    }
}