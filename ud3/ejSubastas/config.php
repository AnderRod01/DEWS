<?php
    define( "DB_HOST", "localhost");
    define ("DB_USER", "root");
    define ("DB_PASS", "");
    define ("DB_DATABASE", "ud03");
    
    define ("MONEDA", "€");
    define ("NOMBREFORO", "SUBASTAS GASTA AQUI");
    define ("RUTA", "http://".$_SERVER["SERVER_NAME"]."/DEWS/ud3/ejSubastas/");

    function conexion(){
        $conn = mysqli_connect(DB_HOST, DB_USER, DB_PASS, DB_DATABASE);
        if(mysqli_errno($conn)>0){
            return false;
        }
        return $conn;
    }
    

?>