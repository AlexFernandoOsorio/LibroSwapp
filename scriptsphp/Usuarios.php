<?php

/**
 * Representa el la estructura de las metas
 * almacenadas en la base de datos
 */
require 'Database.php';

class Usuarios
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
        $consulta = "SELECT * FROM ls_Usuarios";
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
    public static function getByCorreoContra($correoUsuario,$contraseñaUsuario)
    {
        // Consulta de la meta
        $consulta = "SELECT us_idUsuario,
                            us_dniUsuario,
                            us_codUsuario,
			                us_tipoUsuario,
                            us_nombres,
                            us_apellidos,
                            us_estadoUsuario,
                            us_correo,
                            us_skype,
                            us_direccion,
                            us_contraseña,
                            us_edad,
                            us_telefono,
                            us_celular,
                            us_genero,
                            us_intereses
                            FROM ls_Usuarios
                            WHERE us_correo= ? and us_contraseña = ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($correoUsuario,$contraseñaUsuario));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }

    public static function getByCorreo($correoUsuario)
    {
        // Consulta de la meta
        $consulta = "SELECT us_idUsuario,
                            us_dniUsuario,
                            us_codUsuario,
                            us_tipoUsuario,
                            us_nombres,
                            us_apellidos,
                            us_estadoUsuario,
                            us_correo,
                            us_skype,
                            us_direccion,
                            us_contraseña,
                            us_edad,
                            us_telefono,
                            us_celular,
                            us_genero,
                            us_intereses
                            FROM ls_Usuarios
                            WHERE us_correo= ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($correoUsuario));
            // Capturar primera fila del resultado
            $row = $comando->fetch(PDO::FETCH_ASSOC);
            return $row;

        } catch (PDOException $e) {
            // Aquí puedes clasificar el error dependiendo de la excepción
            // para presentarlo en la respuesta Json
            return -1;
        }
    }

    public static function getByID($correoUsuario)
    {
        // Consulta de la meta
        $consulta = "SELECT us_idUsuario,
                            us_dniUsuario,
                            us_codUsuario,
                            us_tipoUsuario,
                            us_nombres,
                            us_apellidos,
                            us_estadoUsuario,
                            us_correo,
                            us_skype,
                            us_direccion,
                            us_contraseña,
                            us_edad,
                            us_telefono,
                            us_celular,
                            us_genero,
                            us_intereses
                            FROM ls_Usuarios
                            WHERE us_idUsuario= ?";

        try {
            // Preparar sentencia
            $comando = Database::getInstance()->getDb()->prepare($consulta);
            // Ejecutar sentencia preparada
            $comando->execute(array($correoUsuario));
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
     * Actualiza un registro de la bases de datos basado
     * en los nuevos valores relacionados con un identificador
     *
     * @param $idMeta      identificador
     * @param $titulo      nuevo titulo
     * @param $descripcion nueva descripcion
     * @param $fechaLim    nueva fecha limite de cumplimiento
     * @param $categoria   nueva categoria
     * @param $prioridad   nueva prioridad
     */
    public static function update(
        $idUsuario,
        $dniUsuario,
        $codUsuario,
        $nombres,
        $apellidos,
        $correo,
        $skype,
        $direccion,
        $contraseña,
        $edad,
        $telefono,
        $celular,
        $genero,
        $intereses
    )
    {
        // Creando consulta UPDATE
        $consulta = "UPDATE ls_Usuarios" .
            " SET   us_dniUsuario=?,
                    us_codUsuario=?,
		            us_tipoUsuario=?,
                    us_nombres=?,
                    us_apellidos=?,
                    us_estadoUsuario,=?,
                    us_correo=?,
                    us_skype=?,
                    us_direccion=?,
                    us_contraseña=?,
                    us_edad=?,
                    us_telefono=?,
                    us_celular=?,
                    us_genero=?,
                    us_intereses=?".
                  "WHERE us_idUsuario=?";

        // Preparar la sentencia
        $cmd = Database::getInstance()->getDb()->prepare($consulta);

        // Relacionar y ejecutar la sentencia
        $cmd->execute(array($dniUsuario,
        $codUsuario,
	    $tipoUsuario,
        $nombres,
        $apellidos,
        $estadoUsuario,
        $correo,
        $skype,
        $direccion,
        $contraseña,
        $edad,
        $telefono,
        $celular,
        $genero,
        $intereses,
        $idUsuario));

        return $cmd;
    }

    /**
     * Insertar una nueva meta
     *
     * @param $titulo      titulo del nuevo registro
     * @param $descripcion descripción del nuevo registro
     * @param $fechaLim    fecha limite del nuevo registro
     * @param $categoria   categoria del nuevo registro
     * @param $prioridad   prioridad del nuevo registro
     * @return PDOStatement
     */
    public static function insert(
        $dniUsuario,
        $codUsuario,
	    $tipoUsuario,
        $nombres,
        $apellidos,
        $estadoUsuario,
        $correo,
        $skype,
        $direccion,
        $contrasena,
        $edad,
        $telefono,
        $celular,
        $genero,
        $intereses
    )
    {
        // Sentencia INSERT
        $comando = "INSERT INTO ls_Usuarios ( " .
            "us_dniUsuario," .
            "us_codUsuario," .
	        "us_tipoUsuario," .
            "us_nombres," .
            "us_apellidos," .
            "us_estadoUsuario," .
            "us_correo," .
            "us_skype," .
            "us_direccion," .
            "us_contraseña," .
            "us_edad," .
            "us_telefono," .
            "us_celular," .
            "us_genero," .
            "us_intereses)" .
            " VALUES( ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array($dniUsuario,$codUsuario,$tipoUsuario,$nombres,$apellidos,$estadoUsuario,$correo,$skype,$direccion,$contrasena,$edad,$telefono,$celular,$genero,$intereses));
    }

    /**
     * Eliminar el registro con el identificador especificado
     *
     * @param $idMeta identificador de la meta
     * @return bool Respuesta de la eliminación
     */
    public static function delete($idUsuario)
    {
        // Sentencia DELETE
        $comando = "DELETE FROM ls_Usuarios WHERE us_idUsuario=?";

        // Preparar la sentencia
        $sentencia = Database::getInstance()->getDb()->prepare($comando);

        return $sentencia->execute(array($idUsuario));
    }
}

?>
