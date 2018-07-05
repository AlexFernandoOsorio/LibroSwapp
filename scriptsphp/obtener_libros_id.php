<?php
/**
 * Obtiene el detalle de una meta especificada por
 * su identificador "idMeta"
 */

require 'Libros.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    if (isset($_GET['libroCat'])) {
       
        // Obtener parámetro idMeta
        $parametro = $_GET['libroCat'];
        // Tratar retorno
        $retorno = Libros::getByCategoria($parametro);


        if ($retorno) {

            $datos["estado"] = "1";
            $datos["Libros"] = $retorno;
            // Enviar objeto json de nlos datos
            print json_encode($datos);
        } else {
            // Enviar respuesta de error general
            print json_encode(
                array(
                    'estado' => '2',
                    'mensaje' => 'No se obtuvieron registros'
                )
            );
        }
    }
    else
    {
        if (isset($_GET['libroTitulo'])) {
       
        // Obtener parámetro idMeta
        $parametro = $_GET['libroTitulo'];
        // Tratar retorno
        $retorno = Libros::getByTitulo($parametro);


        if ($retorno) {

            $datos["estado"] = "1";
            $datos["Libros"] = $retorno;
            // Enviar objeto json de nlos datos
            print json_encode($datos);
        } else {
            // Enviar respuesta de error general
            print json_encode(
                array(
                    'estado' => '2',
                    'mensaje' => 'No se obtuvieron registros'
                )
            );
        }
        }
        else{
            if (isset($_GET['libroAutor'])) {
       
            // Obtener parámetro idMeta
            $parametro = $_GET['libroAutor'];
            // Tratar retorno
            $retorno = Libros::getByAutor($parametro);

            if ($retorno) {
                $datos["estado"] = "1";
                $datos["Libros"] = $retorno;
                // Enviar objeto json de nlos datos
                print json_encode($datos);
            } else {
                // Enviar respuesta de error general
                print json_encode(
                    array(
                        'estado' => '2',
                        'mensaje' => 'No se obtuvieron registros'
                    )
                );
                    }
            }
            else
            {
                 if (isset($_GET['idLibro'])) {
                  
                 $parametro = $_GET['idLibro'];
                 // Tratar retorno
                 $retorno = Libros::getById($parametro);
                 if ($retorno) {
                     $datos["estado"] = "1";
                     $datos["Libros"] = $retorno;
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
                        
                       $parametro = $_GET['codUsuario'];
                       // Tratar retorno
                       $retorno = Libros::getByUsuario($parametro);
                       if ($retorno) {
                           $datos["estado"] = "1";
                           $datos["Libros"] = $retorno;
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
    }

}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    // Decodificando formato Json
    $body = json_decode(file_get_contents("php://input"), true);

    // Insertar meta
    $retorno = Libros::insert(
        $body['codUsuario'],
        $body['idLSBM'],
        $body['titulo'],
        $body['autor'],
        $body['edicion'],
        $body['anioPublicacion'],
        $body['editorial'],
        $body['portada'],
        $body['descripcion'],
        $body['estadoLibro'],
        $body['ubicacion']);
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

