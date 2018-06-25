<?php 
 

require 'Libros.php';
 

if ($_SERVER['REQUEST_METHOD'] == 'POST') {


 	/*
 							idLibros
                            codUsuario
                            idISBN
                            titulo
                            autor
                            edicion
                            anoPublicacio
                            editorial
                            portada
                            descripcion
                            estadoLibro
                            ubicacion
     */
    $codUsuario= $_POST['codUsuario'];
    $idISBN= $_POST['idISBN'];
    $titulo= $_POST['titulo'];
    $autor= $_POST['autor'];
    $edicion= $_POST['edicion'];
    $anoPublicacion= $_POST['anoPublicacion'];
    $editorial= $_POST['editorial'];
    //$portada= $_POST['portada'];
    $descripcion= $_POST['descripcion'];
    $estadoLibro= $_POST['estadoLibro'];
    $ubicacion= $_POST['ubicacion'];

    $ImageData = $_POST['image_data'];
 	$ImageName = $_POST['image_tag'];

    //sentencias para insertar imagen al path del servidor
    $ImagePath = "/home/pydt/libroswapp.proyectosdetesis.com/AndroidImages/UploadAppImages/$ImageName";
    $portada = "https://libroswapp.proyectosdetesis.com/AndroidImages/UploadAppImages/$ImageName";
 	$ServerURL = "https://libroservice.proyectosdetesis.com/AndroidImages/$ImagePath";
	$success=file_put_contents($ImagePath,base64_decode($ImageData));

	//sentencias para insertar el libro
    $retorno = Libros::insert(
		            $codUsuario,
		            $idISBN,
		            $titulo,
		            $autor,
		            $edicion,
		            $anoPublicacion,
		            $editorial,
		            $portada,
		            $descripcion,
		            $estadoLibro,
		            $ubicacion);

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