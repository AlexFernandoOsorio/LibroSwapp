<?php

/**
 * Representa el la estructura de las metas
 * almacenadas en la base de datos
 */
require 'Database.php';

class Libros
{
    function __construct()
    {
    }

    /**
     * Retorna en la fila especificada de la tabla 'Libro'
     *
     * @param $idMeta Identificador del registro
     * @return array Datos del registro
     */
    public static function getAll()
    {
        $consulta = "SELECT li_idLibros,
                            li_codUsuario,
                             li_idISBN,
                             li_titulo,
                             li_autor,
                             li_edicion,
                             li_añoPublicacion,
                             li_editorial,
                             li_portada,
                             li_descripcion,
                             li_estadoLibro,
                             li_ubicacion,
                             li_categoriaLibro
                             FROM ls_Libros";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute();

            return $comando->fetchAll(PDO::FETCH_ASSOC);

        } catch (PDOException $e) {
            return false;
        }
    }

    /**
     * Obtiene los campos de una meta con un identificador
     * determinado
     *
     * @param $idMeta Identificador de la meta
     * @return mixed
     */
    public static function getById($idlibro)
    {
        // Consulta de la meta
        $consulta = "SELECT li_idLibros,
                            li_codUsuario,
                            li_idISBN,
                            li_titulo,
                            li_autor,
                            li_edicion,
                            li_añoPublicacion,
                            li_editorial,
                            li_portada,
                            li_descripcion,
                            li_estadoLibro,
                            li_ubicacion,
                            li_categoriaLibro
                             FROM ls_Libros
                             WHERE li_idLibros = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($idlibro));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }

    public static function getByUsuario($codUsuario)
    {
        // Consulta de la meta
        $consulta = "SELECT li_idLibros,
                            li_codUsuario,
                            li_idISBN,
                            li_titulo,
                            li_autor,
                            li_edicion,
                            li_añoPublicacion,
                            li_editorial,
                            li_portada,
                            li_descripcion,
                            li_estadoLibro,
                            li_ubicacion,
                            li_categoriaLibro
                             FROM ls_Libros
                             WHERE li_codUsuario = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($codUsuario));
            // Capturar primera fila del resultado
            $row = $comando->fetchall(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
    public static function getByCategoria($Catlibro)
    {
        // Consulta de la meta
        $consulta = "SELECT li_idLibros,
                            li_codUsuario,
                            li_idISBN,
                            li_titulo,
                            li_autor,
                            li_edicion,
                            li_añoPublicacion,
                            li_editorial,
                            li_portada,
                            li_descripcion,
                            li_estadoLibro,
                            li_ubicacion,
                            li_categoriaLibro
                            FROM ls_Libros
                            WHERE li_categoriaLibro = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($Catlibro));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }

    public static function getByTitulo($Titulo)
    {
        // Consulta de la meta
        $consulta = "SELECT li_idLibros,
                            li_codUsuario,
                            li_idISBN,
                            li_titulo,
                            li_autor,
                            li_edicion,
                            li_añoPublicacion,
                            li_editorial,
                            li_portada,
                            li_descripcion,
                            li_estadoLibro,
                            li_ubicacion,
                            li_categoriaLibro
                            FROM ls_Libros
                            WHERE li_titulo like ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($Titulo));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }

    public static function getByAutor($Autor)
    {
        // Consulta de la meta
        $consulta = "SELECT li_idLibros,
                            li_codUsuario,
                            li_idISBN,
                            li_titulo,
                            li_autor,
                            li_edicion,
                            li_añoPublicacion,
                            li_editorial,
                            li_portada,
                            li_descripcion,
                            li_estadoLibro,
                            li_ubicacion,
                            li_categoriaLibro
                            FROM ls_Libros
                            WHERE li_autor like ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($Autor));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }

    public static function insert(
            $codUsuario,
            $idLSBM,
            $titulo,
            $autor,
            $edicion,
            $añoPublica,
            $editorial,
            $portada,
            $descripcion,
            $estadoLibro,
            $ubicacion,
            $categoria
    )
    {
        // Sentencia INSERT
        $comando = "INSERT INTO ls_Libros ( " .
            "li_codUsuario," .
            "li_idISBN," .
            "li_titulo," .
            "li_autor," .
            "li_edicion," .
            "li_añoPublicacion," .
            "li_editorial," .
            "li_portada," .
            "li_descripcion," .
            "li_estadoLibro," .
            "li_ubicacion," .
            "li_categoriaLibro)" .
            " VALUES( ?,?,?,?,?,?,?,?,?,?,?,?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array
            (
            $codUsuario,
            $idLSBM,
            $titulo,
            $autor,
            $edicion,
            $añoPublica,
            $editorial,
            $portada,
            $descripcion,
            $estadoLibro,
            $ubicacion,
            $categoria));
    }

    //update libros
    public static function update(
        $idISBN,
        $titulo,
        $autor,
        $edicion,
        $anioPublicacion,
        $editorial,
        $descripcion,
        $ubicacion,
        $idLibro
    )
    {
        // Creando consulta UPDATE
        $consulta = "UPDATE ls_Libros" .
            " SET li_idISBN=?, li_titulo=?, li_autor=?, li_edicion=?, li_añoPublicacion=?, li_editorial=?, li_descripcion=?, li_ubicacion=? " .
            "WHERE li_idLibros=?";

        // Preparar la sentencia
        $cmd = Database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($idISBN, $titulo, $autor, $edicion, $anioPublicacion, $editorial,
            $descripcion,$ubicacion,$idLibro));

        return $cmd;
    }

    /**
     * Eliminar el registro con el identificador especificado
     *
     * @param $idMeta identificador de la meta
     * @return bool Respuesta de la eliminación
     */
    public static function delete($idlibro)
    {
        // Sentencia DELETE
        $comando = "DELETE FROM ls_Libros WHERE li_idLibros=?";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array($idlibro));
    }
}
?>
