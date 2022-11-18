<!-- FICHERO QUE SE USARÁ COMO CABECERA POR TODA LA APLICACIÓN-->
<?php
    require("config.php");
    require("gestionBD.php");

    $con = fncCrearConexion(DB_HOST, DB_USER, DB_PASS, DB_DATABASE);

    // function crearListaCategorias (){
    //     $listaCategorias = getCategoriasCursos($con);

    // }
    
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styles.css">
    <title><?= NOMBRE_APP?></title>
</head>
<body>
    <header id="header">
        <h1><?= NOMBRE_APP?></h1>
        <nav id="menu">
            <a href="index.php">Home</a>
            <a href="anulaciones.php">Anulaciones</a>
        </nav>
    </header>
