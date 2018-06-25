<?php
/**
 * Actualiza una meta especificada por su identificador
 */

require 'Usuarios.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Actualizar meta
    $retorno = Usuarios::update(
        $body['idUsuario'],
        $body['dniUsuario'],
        $body['codUsuario'],
        $body['nombres'],
        $body['apellidos'],
        $body['correo'],
        $body['skype'],
        $body['direccion'],
        $body['contrasena'],
        $body['edad'],
        $body['telefono'],
        $body['celular'],
        $body['genero'],
        $body['intereses']);

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