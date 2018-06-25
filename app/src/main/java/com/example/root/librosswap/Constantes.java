package com.example.root.librosswap;

public class Constantes {


    static final String ServerPath="https://libroswapp.proyectosdetesis.com/scriptsphp/";
    static final String ServerPathImages="https://libroswapp.proyectosdetesis.com/AndroidImages/UploadAppImages";

    //Direcciones de lso servicios Rest para usuarios
    public static final String GetUsuario = ServerPath+"obtener_usuarios.php";
    public static final String GetUsuarioID = ServerPath+"obtener_usuarios_id.php";
    public static final String insertUsuario = ServerPath+"insertar_usuarios.php";
    public static final String updatetUsuario = ServerPath+"actualizar_usuarios.php";

    //Direcciones de lso servicios Rest para libros
    public static final String GetLibrosall = ServerPath+"obtener_libros.php";
    public static final String GetLibrosCat = ServerPath+"obtener_libros_id.php?libroCat=";
    public static final String GetLibrosTitulo = ServerPath+"obtener_libros_id.php?libroTitulo=";
    public static final String GetLibrosAutor = ServerPath+"obtener_libros_id.php?libroAutor=";
    public static final String GetLibrosID = ServerPath+"obtener_libros_id.php?idLibro=";

    //Direcciones de lso servicios Rest para categorias
    public static final String GetCatall = ServerPath+"obtener_categorias.php";




}
