<?php
    session_start();
    include 'libmenu.php';
    function dibujar(){

        if(isset($_SESSION['platos'])){

            $total = 0;
            $txtHtml = "<p>SU ELECCION:</p>";

            if(isset($_SESSION['platos']['Primero'])){

                $precio = damePrecio($_SESSION['platos']['Primero']);
                $total += $precio;
                $txtHtml .= '<p>Primer plato: '.$_SESSION['platos']['Primero'].' ----- '.$precio."</p>";

            }
            if(isset($_SESSION['platos']['Segundo'])){

                $precio = damePrecio($_SESSION['platos']['Segundo']);
                $total += $precio;
                $txtHtml .= '<p>Segundo plato: '.$_SESSION['platos']['Segundo'].' ----- '.$precio."</p>";

            }
            if(isset($_SESSION['platos']['Postre'])){

                $precio = damePrecio($_SESSION['platos']['Postre']);
                $total += $precio;
                $txtHtml .= '<p>Postre: '.$_SESSION['platos']['Postre'].' ----- '.$precio."</p>";

            }
            if(isset($_SESSION['platos']['Bebida'])){

                $precio = damePrecio($_SESSION['platos']['Bebida']);
                $total += $precio;
                $txtHtml .= '<p>Bebida: '.$_SESSION['platos']['Bebida'].' ----- '.$precio."</p>";

            }
            
            $descuento = $_SESSION['usuario'][1];
            $total -= ($total*($descuento/100));
            $txtHtml .= '<p>TOTAL: ----- '.$total."</p>";
            echo $txtHtml;
        }
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>FinPedido</title>
</head>
<body>
    <?php dibujar();?><br/>
    <a href="entrada.php?logout=true">Otro pedido</a>
</body>
</html>