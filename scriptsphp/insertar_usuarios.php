<?php
/**
 * Insertar un nuevo libro en la base de datos
 */

require 'Usuarios.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"),true);
    
    $retorno = Usuarios::insert(
        $body['dniUsuario'],
        $body['codUsuario'],    
        $body['tipoUsuario'],
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