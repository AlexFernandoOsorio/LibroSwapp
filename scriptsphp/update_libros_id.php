<?php
/**
 * Actualiza una meta especificada por su identificador
 */

require 'Libros.php';

if ($_SERVER['REQUEST_METHOD'] == 'PUT') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Actualizar meta
    $retorno = Libros::update(
        $body['idISBN'],
        $body['titulo'],
        $body['autor'],
        $body['edicion'],
        $body['anioPublicacion'],
        $body['editorial'],
        $body['descripcion'],
        $body['ubicacion'],
        $body['idLibro']);

    if ($retorno) {
        // Código de éxito
        print json_encode(
            array(
                'estado' => '1',
                'mensaje' => 'Actualización exitosa')
        );
    } else {
        // Código de falla
        print json_encode(
            array(
                'estado' => '2',
                'mensaje' => 'Actualización fallida')
        );
    }
}


if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    $retorno = Libros::delete($body['idLibro']);

    if ($retorno) {
        print json_encode(
            array(
                'estado' => '1',
                'mensaje' => 'Eliminación exitosa')
        );
    } else {
        print json_encode(
            array(
                'estado' => '2',
                'mensaje' => 'Eliminación fallida')
        );
    }
}