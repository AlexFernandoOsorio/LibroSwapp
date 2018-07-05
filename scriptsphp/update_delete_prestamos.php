<?php

require 'Prestamos.php';

if ($_SERVER['REQUEST_METHOD'] == 'POST') {

    $a=$_POST['idPrestamo'];
    $b=$_POST['tipoPrestamo'];
    $retorno = Prestamos::updateTipoPrestamo($a,$b);

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
if ($_SERVER['REQUEST_METHOD'] == 'PUT') {

    $retorno = Prestamos::delete($_POST['idPrestamo']);

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