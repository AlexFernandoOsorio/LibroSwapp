<?php

/**
 * Representa el la estructura de las metas
 * almacenadas en la base de datos
 */
require 'Database.php';

class Prestamos
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
        $consulta = "SELECT pr_idPrestamo,
                            pr_idLibro,
                            pr_idCodUsuarioSolicitante,
                            pr_idCodUsuarioSolicitado,
                            pr_tipoPrestamo
                            FROM ls_Prestamo";
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

    public static function getByIDPrestamo($idPrestamo)
    {
        $consulta = "SELECT pr_idPrestamo,
                            pr_idLibro,
                            pr_idCodUsuarioSolicitante,
                            pr_idCodUsuarioSolicitado,
                            pr_tipoPrestamo
                            FROM ls_Prestamo
                            WHERE pr_idPrestamo = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($idPrestamo));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }

    public static function getByIDLibro($idLibro)
    {
        $consulta = "SELECT pr_idPrestamo,
                            pr_idLibro,
                            pr_idCodUsuarioSolicitante,
                            pr_idCodUsuarioSolicitado,
                            pr_tipoPrestamo
                            FROM ls_Prestamo
                            WHERE pr_idLibro = ?";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($idLibro));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
    public static function getByTipoPrestamo($idTipo)
    {
        $consulta = "SELECT pr_idPrestamo,
                            pr_idLibro,
                            pr_idCodUsuarioSolicitante,
                            pr_idCodUsuarioSolicitado,
                            pr_tipoPrestamo
                            FROM ls_Prestamo
                            WHERE pr_tipoPrestamo = ?";
        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($idTipo));
            // Capturar primera fila del resultado
            $row = $comando->fetchAll(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }
    public static function updateTipoPrestamo(
        $idPrestamo,
        $tipoPrestamo
    )
    {
        // Creando consulta UPDATE
        $consulta = "UPDATE ls_Prestamo" .
            " SET pr_tipoPrestamo=? " .
            "WHERE pr_idPrestamo=?";

        // Preparar la sentencia
        $cmd = Database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($tipoPrestamo, $idPrestamo));

        return $cmd;
    }
    public static function insert(
            $pr_idLibro,
            $pr_idCodUsuarioSolicitante,
            $pr_idCodUsuarioSolicitado,
            $pr_tipoPrestamo
    )
    {
        // Sentencia INSERT
        $comando = "INSERT INTO ls_Prestamo ( " .
            "pr_idLibro," .
            "pr_idCodUsuarioSolicitante," .
            "pr_idCodUsuarioSolicitado," .
            "pr_tipoPrestamo)" .
            " VALUES( ?,?,?,?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array
            (
            $pr_idLibro,
            $pr_idCodUsuarioSolicitante,
            $pr_idCodUsuarioSolicitado,
            $pr_tipoPrestamo));
    }

    public static function delete($idPrestamo)
    {
        // Sentencia DELETE
        $comando = "DELETE FROM ls_Prestamo WHERE pr_idPrestamo=?";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array($idPrestamo));
    }
}
?>
