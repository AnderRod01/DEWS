<?php
    $servidor = "mysql:dbname=NOMBRE_BASEDATOS;host=localhost";
    $user = "USUARIO";
    $pass = "CONTRASEÑA";
    try {
        $pdo = new PDO($servidor, $user, $pass, array(PDO::MYSQL_ATTR_INIT_COMMAND => "SET NAMES utf8"));
    } catch (PDOException $e) {
        echo "conexion fallida" .$e->getMessage();
    }

?>