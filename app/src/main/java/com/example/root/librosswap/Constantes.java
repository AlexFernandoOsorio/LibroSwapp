package com.example.root.librosswap;

public class Constantes {


    static final String ServerPath="https://libroswapp.proyectosdetesis.com/scriptsphp/";
    static final String ServerPathImages="https://libroswapp.proyectosdetesis.com/AndroidImages/UploadAppImages";

    //Direcciones de lso servicios Rest para usuarios
    public static final String GetUsuarios = ServerPath+"obtener_usuarios.php";
    public static final String GetUsuarioID = ServerPath+"obtener_usuarios_id.php";
    public static final String insertUsuario = ServerPath+"insertar_usuarios.php";
    public static final String updatetUsuario = ServerPath+"actualizar_usuarios.php";

    //Direcciones de lso servicios Rest para libros
    public static final String GetLibrosall = ServerPath+"obtener_libros.php";
    public static final String GetLibrosCat = ServerPath+"obtener_libros_id.php?libroCat=";
    public static final String GetLibrosTitulo = ServerPath+"obtener_libros_id.php?libroTitulo=";
    public static final String GetLibrosAutor = ServerPath+"obtener_libros_id.php?libroAutor=";
    public static final String GetLibrosID = ServerPath+"obtener_libros_id.php?idLibro=";
    public static final String GetLibrosUsuario =ServerPath+"obtener_libros_id.php?codUsuario=";

    public static final String InsertLibros = ServerPath+"insertar_libros.php";


    //Direcciones de lso servicios Rest para categorias
    public static final String Get_Insert_Categorias = ServerPath+"get_insert_categorias.php";
    public static final String Update_Delete_Categorias = ServerPath+"update_delete_categorias.php";
    //Direcciones de lso servicios Rest para prestamos
    public static final String Get_Insert_Prestamos = ServerPath+"get_insert_categorias.php";
    public static final String Update_Delete_Prestamos = ServerPath+"update_delete_categorias.php";

}
