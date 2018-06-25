<?php
 
include 'mysql_login.php';
 
// Create connection
$conn = new mysqli($servidor, $usuario, $contrasena, $basededatos) or die ("No se ha podido conectar al servidor de Base de datos");

if($_SERVER['REQUEST_METHOD'] == 'POST')
 {
 $DefaultId = 0;
 
 $ImageData = $_POST['image_data'];
 
 $ImageName = $_POST['image_tag'];
 
 $ImagePath = "/home/pydt/libroservice.proyectosdetesis.com/AndroidImages/UploadImages/$ImageName.jpg";
 
 $ServerURL = "https://libroservice.proyectosdetesis.com/AndroidImages/$ImagePath";
 
 $success=file_put_contents($ImagePath,base64_decode($ImageData));

	/*if(!$success){
	print json_encode(array(‘success’ => ‘bien’));
	}
	else{
	print json_encode(array(‘success’ => ‘mal’));
	}*/
}
?>