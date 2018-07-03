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

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    $nombreCat= $_POST['nombreCat   '];

    $retorno = Categorias::insert($nombreCat);

    if ($retorno) {
        // Código de éxito
        print json_encode(
            array(
                'estado' => '1',
                'mensaje' => 'Creación exitosa')
        );
    } else {
        // Código de falla
        print json_encode(
            array(
                'estado' => '2',
                'mensaje' => 'Creación fallida')
        );
    }
}