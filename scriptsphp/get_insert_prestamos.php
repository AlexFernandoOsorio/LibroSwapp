<?php
/**
 * Obtiene todas los libros de la base de datos
 */

require 'Prestamos.php';

if ($_SERVER['REQUEST_METHOD'] == 'GET') {

    if (isset($_GET['idPrestamo'])) {
       
        // Obtener parámetro idMeta
        $Prestamo = $_GET['idPrestamo'];
        // Tratar retorno
        $retorno = Prestamos::getByIDPrestamo($Prestamo);


        if ($retorno) {

            $datos["estado"] = "1";
            $datos["Prestamo"] = $retorno;
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
        if (isset($_GET['tipoPrestamo'])) {
       
        // Obtener parámetro idMeta
        $Prestamo = $_GET['tipoPrestamo'];
        // Tratar retorno
        $retorno = Prestamos::getByTipoPrestamo($Prestamo);


        if ($retorno) {

            $datos["estado"] = "1";
            $datos["Prestamo"] = $retorno;
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
    }
}

if ($_SERVER['REQUEST_METHOD'] == 'POST') {
    
    $idLibro = $_POST['pr_idLibro'];
    $idCodUsuarioSolicitante = $_POST['pr_idCodUsuarioSolicitante'];
    $idCodUsuarioSolicitado = $_POST['pr_idCodUsuarioSolicitado'];
    $tipoPrestamo = $_POST['pr_tipoPrestamo'];

    $retorno = Prestamos::insert($idLibro,$idCodUsuarioSolicitante,$idCodUsuarioSolicitado,$tipoPrestamo);

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