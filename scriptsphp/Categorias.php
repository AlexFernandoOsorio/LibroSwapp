<?php

/**
 * Representa el la estructura de las metas
 * almacenadas en la base de datos
 */
require 'Database.php';

class Categorias
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
        $consulta = "SELECT * FROM ls_Categorias";
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
    public static function getById($idCategoria)
    {
        // Consulta de la meta
        $consulta = "SELECT cat_idCategoria,
                            cat_nombreCategoria
                            FROM ls_Categorias
                            WHERE cat_idCategoria = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($idCategoria));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }

    /**
     * Insertar una nueva categoria
     */
    public static function insert(
        $cat_nombreCategoria
    )
    {
        // Sentencia INSERT
        $comando = "INSERT INTO ls_Categorias ( " .
            "cat_nombreCategoria)" .
            " VALUES(?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array($cat_nombreCategoria));
    }

    /**
     * Eliminar el registro con el identificador especificado
     *
     * @param $idMeta identificador de la meta
     * @return bool Respuesta de la eliminación
     */
    public static function delete($idCategoria)
    {
        // Sentencia DELETE
        $comando = "DELETE FROM ls_Categorias WHERE cat_idCategoria=?";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array($idCategoria));
    }
}

?>
