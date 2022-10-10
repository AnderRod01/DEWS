<?php
    session_start();
    if(!isset($_SESSION['usuario'])){
        header('Location: entrada.php'); 
        exit();
    }
    
    function mostrarPedido(){
        if(isset($_SESSION['platos'])){
            $txtHtml = "<h2>SU ELECCION:</h2><ul>";
            if(isset($_SESSION['platos']['Primero']))
                $txtHtml = $txtHtml.'<li>Primer plato: '.$_SESSION['platos']['Primero'].'</li>';
            if(isset($_SESSION['platos']['Segundo']))
                $txtHtml = $txtHtml.'<li>Segundo plato: '.$_SESSION['platos']['Segundo'].'</li>';
            if(isset($_SESSION['platos']['Postre']))
                $txtHtml = $txtHtml.'<li>Postre: '.$_SESSION['platos']['Postre'].'</li>';
            if(isset($_SESSION['platos']['Bebida']))
                $txtHtml = $txtHtml.'<li>Bebida: '.$_SESSION['platos']['Bebida'].'</li>';
            echo $txtHtml.'</ul>';
        }
    }

    
    if(isset($_POST['btnPedir'])){
        if(count($_SESSION['platos'])>0) {
            header('Location: finpedido.php');
            exit();
        }
        else
            echo '<p style="color:red;">Elige al menos un plato</p>';
    }

?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pedido</title>
</head>
<body>
    <p><a href="pedidoplato.php?tipo=Primero">Primero</a></p>
    <p><a href="pedidoplato.php?tipo=Segundo">Segundo</a></p>
    <p><a href="pedidoplato.php?tipo=Postre">Postre</a></p>
    <p><a href="pedidoplato.php?tipo=Bebida">Bebida</a></p>
    <?php 
        mostrarPedido() 
    ?>
    <form enctype="multipart/form-data" method="POST" action="<?php $_SERVER['PHP_SELF']?>">
        <button style="padding:0px; border:0px;" type="submit" name="btnPedir">
            <img src="images/pedido.png" alt="Hacer Pedido"/>
        </button>
    </form>
</body>
</html>