<?php
/**
 * Obtiene el detalle de una meta especificada por
 * su identificador "idMeta"
 */

require 'Usuarios.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    if (isset($_GET['correoUsuario']) && isset($_GET['contraseñaUsuario']) ) {
       
        // Obtener parámetro idMeta
        $parametro = $_GET['correoUsuario'];
        $parametro2= $_GET['contraseñaUsuario'];

        // Tratar retorno
        $retorno = Usuarios::getByCorreoContra($parametro,$parametro2);


        if ($retorno) {

            $datos["estado"] = "1";
            $datos["usuarios"] = $retorno;
            // Enviar objeto json de nlos datos
            print json_encode($datos);
        } else {
            // Enviar respuesta de error general
            print json_encode(
                array(
                    'estado' => '2',
                    'mensaje' => 'No se obtuvo el registro'
                )
            );
        }

    } 
    else
     {
        if (isset($_GET['correoUsuario'])) {
       
        // Obtener parámetro idMeta
        $parametro = $_GET['correoUsuario'];
        // Tratar retorno
        $retorno = Usuarios::getByCorreo($parametro);


        if ($retorno) {

            $datos["estado"] = "1";
            $datos["usuarios"] = $retorno;
            // Enviar objeto json de nlos datos
            print json_encode($datos);
        } else {
            // Enviar respuesta de error general
            print json_encode(
                array(
                    'estado' => '2',
                    'mensaje' => 'No se obtuvo el registro'
                )
            );
         }

        }
        else{
                if (isset($_GET['codUsuario'])) {
               
                // Obtener parámetro idMeta
                $parametro = $_GET['codUsuario'];
                // Tratar retorno
                $retorno = Usuarios::getBycodUser($parametro);


                if ($retorno) {

                    $datos["estado"] = "1";
                    $datos["usuarios"] = $retorno;
                    // Enviar objeto json de nlos datos
                    print json_encode($datos);
                } else {
                    // Enviar respuesta de error general
                    print json_encode(
                        array(
                            'estado' => '2',
                            'mensaje' => 'No se obtuvo el registro'
                        )
                    );
                 }

                }
        }
    }
    
}


if ($_SERVER['REQUEST_METHOD'] == 'PUT') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Actualizar meta
    $retorno = Libros::update(
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