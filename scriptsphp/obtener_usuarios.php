<?php
/**
 * Obtiene todas los libros de la base de datos
 */

require 'Usuarios.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    // Manejar petición GET
    $usuario = Usuarios::getAll();

    if ($usuario) {

        $datos["estado"] = 1;
        $datos["usuario"] = $usuario;

        print json_encode($datos);
    } else {
        print json_encode(array(
            "estado" => 2,
            "mensaje" => "Ha ocurrido un error"
            
        ));
        
    }
}