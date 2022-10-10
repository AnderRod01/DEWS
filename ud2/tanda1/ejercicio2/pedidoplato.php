<?php
include 'libmenu.php';
    session_start();
    if(!isset($_SESSION['usuario']) && !isset($_GET['tipo'])){
        header('Location: entrada.php'); 
        exit();
    }


    function pintar(){
        $tipo = $_GET['tipo'];
        
        if(isset($_SESSION['platos'][$tipo]))   
            echo '<p>Va a cambiar su eleccion '.$_SESSION['platos'][$tipo].' por:</p>';
        else  
            echo '<p>Elija '.$tipo.'</p>';

        echo '<select name="selPlatos">';
        $platosTipo = damePlatos($tipo);


        foreach($platosTipo as $plato => $precio)
            echo '<option value="'.$plato.'">'.$plato.' - '.$precio.'</option>';

        echo '</select><input type="submit" name="btnElegir" value="Elegir '.$tipo.'"/>';
    }

    
    if(isset($_POST['btnElegir']) && isset($_POST['selPlatos'])){
        $_SESSION['platos'][$_GET['tipo']] = $_POST['selPlatos'];
        header('Location: pedido.php');
        exit();
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Plato</title>
</head>
<body>
    <form enctype="multipart/form-data" method="POST" action="<?php $_SERVER['PHP_SELF']?>">
        <?php pintar(); ?>
    </form>
</body>
</html>