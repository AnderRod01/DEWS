<?php

    session_start();
    if (isset($_GET['logout'])){
        session_destroy();
        session_start();
    }
    if (!isset($_SESSION['lstNombres'])){
        $_SESSION['lstNombres'] = [];
    }
    if (isset($_POST['Aniadir']) && $_POST['nombre']!= "" && validarNom() && !in_array($_POST['nombre'], $_SESSION['lstNombres'])){     
        $_SESSION['lstNombres'][] = $_POST['nombre'];
        
    }

    function mostrarNombres (){
        if (!count($_SESSION['lstNombres']) == 0){ 
            $txtHtml = "<p>Datos introducidos</p><ul>";
            foreach ($_SESSION['lstNombres'] as $nombre) {
                $txtHtml .= "<li>".$nombre."</li>";
            }
            $txtHtml.= "</ul>";
            echo $txtHtml;
        }else{
            echo "<p>Todavía no se han introducido nombres</p>";
        }
    }

    function validarNom(){
        if (isset($_POST['nombre'])){
            for ($i = 0; $i<strlen($_POST['nombre']); $i++){
                $codChr = ord(strtoupper($_POST['nombre'][$i]));
                if ($codChr < 65 || $codChr > 90 && $codChr != 32){
                    return false;
                }
            }
        }
        return true;
    }

    if (isset($_POST['Borrar'])){
        if (in_array($_POST['nombre'], $_SESSION['lstNombres'])){
            $i = array_search($_POST['nombre'], $_SESSION['lstNombres']);
            unset($_SESSION['lstNombres'][$i]);
        }
    }

    
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ejercicio1</title>
</head>
<style>
    input[type='submit']{
        margin-top: 15px;
        margin-right: 10px;
    }
    .error{
        color: red;
    }
</style>
<body>
    <?php
        if (!validarNom()){
            echo "<p class ='error'>No has escrito unicamente con letras y espacios</p>";
        }
    ?>
    <form enctype="multipart/form-data" action="<?php echo $_SERVER['PHP_SELF'];?>" method="post">
        <label for="nombre"><strong>Escriba algun nombre:</strong></label>
        <input type="text" name="nombre" autofocus>
        <div>
            <input type="submit" name="Aniadir" value="Añadir">
            <input type="submit" name="Borrar" value="Borrar">
        </div>
    </form>
    <?php
        mostrarNombres();

        echo "<a href='?logout'>Cerrar sesion (se perderan los datos guardados)</a>"
    ?>
</body>
</html>