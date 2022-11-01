<?php

session_start();
include 'config.php';
include 'bbdd.php';
$conn = conexion();

mysqli_set_charset($conn, "utf8");

?>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title><?php echo NOMBREFORO ?></title>
        <link rel="stylesheet" href="css/style.css"/>
    </head>
    <style>
        img{
            width: 150px;
            object-fit:cover;
        }
    </style>
    <body>
        <div id="header">
            <h1><?php echo NOMBREFORO ?></h1>
        </div>
        <div id="menu">
            <a href="index.php">Home </a>
            
            <?php
            if(isset($_SESSION['uid'])){
                echo '<a href="logout.php">Logout </a>';
                echo '<a href="nuevoitem.php">Nuevo item </a>';
                if($_SESSION['username']=="admin"){
                    echo '<a href="vencidas.php">Subastas vencidas </a>';
                    echo '<a href="publi.php">Anunciantes </a>';
                }
            }else{
                echo '<a href="login.php">Login</a>';
            }            
            ?>
            
            
        </div>
        <div id="container">
            <div id="bar">
                <?php   require 'bar.php';    ?>
            </div>
            <div id="main">