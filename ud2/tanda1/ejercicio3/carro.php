<?php
    define('LSTPRODUCTOS', ['prod1'=>5, 'prod2'=>10, 'prod3'=>3, 'prod4'=>6]);
    session_start();

    if (!isset($_SESSION['lstPedidos'])){
        $_SESSION['lstPedidos'] = ['prod1' => 0, 'prod2' => 0, 'prod3' => 0, 'prod4' => 0];
    }

    function dibujarTabla (){
        echo "<tr>
        <th>PRODUCTO</th>
        <th>PRECIO</th>
        <th>ELIJA CANTIDAD</th>
        <th>PEDIDO ACTUAL</th>
        </tr>";

        foreach (LSTPRODUCTOS as $key => $value) {
            echo "<tr>";
            
            echo "<td><input type='checkbox' name='".$key."'>".$key."</td>";
            echo "<td>".$value." €</td>";
            
            echo "<td><select name='sel".$key."'>";
            for ($i=0; $i<=10;$i++){
                echo "<option value='".$i."'>".$i."</option>";
            }
            echo "</select></td>";

            echo "<td>" .$_SESSION['lstPedidos'][$key]." uds pedidas</td>";

            echo "</tr>";
        }
    }

    function pintarErrores(){
        if (isset($_POST['aniadir'])){
            $cont = 0;
            foreach (LSTPRODUCTOS as $key => $value) {
                if (!isset($_POST[$key])){
                    $cont++;
                }elseif ($_POST['sel'.$key] == 0){
                    echo "<p>Debes seleccionar una cantidad del producto</p>";
                }
            }
            if ($cont == count(LSTPRODUCTOS)){
                echo "<p>Debes seleccionar algun producto</p>";
            }
        }
    }
    
    function resumen (){
        if (isset($_POST['compra'])){
            echo "<h2>Tu pedido</h2><ul>";
            $total = 0;
            foreach($_SESSION['lstPedidos'] as $key => $value){
                if ($value!=0){
                    echo "<li>".$key." - ".$value." unidades a ".LSTPRODUCTOS[$key]."€</li>";
                    $total += (LSTPRODUCTOS[$key] * $value);
                }
            }

            echo "<li>TOTAL ".$total." EUROS</li></ul>";
            unset($_SESSION['lstPedidos']);
            $_SESSION['lstPedidos'] = ['prod1' => 0, 'prod2' => 0, 'prod3' => 0, 'prod4' => 0];

        }
    }



    if(isset($_POST['aniadir'])){
        foreach (LSTPRODUCTOS as $key => $value) {
            if (isset($_POST[$key]) && $_POST['sel'.$key] != 0){
                $_SESSION['lstPedidos'][$key] += $_POST['sel'.$key];
            }
        }
    }

    if (isset($_POST['vaciar'])){
        unset($_SESSION['lstPedidos']);
        $_SESSION['lstPedidos'] = ['prod1' => 0, 'prod2' => 0, 'prod3' => 0, 'prod4' => 0];
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Carro</title>
</head>
<body>
    <form enctype="multipart/form-data" action="<?php echo $_SERVER['PHP_SELF'];?>" method="post">  

        <?php
            pintarErrores();
            resumen();
        ?>
        <table>
            <?php
                dibujarTabla();
            ?>
        </table>

        <input type="submit" name="aniadir" value="AÑADIR UNIDADES">
        <input type="submit" name="compra" value="FORMALIZAR COMPRA">
        <input type="submit" name="vaciar" value="VACIAR CARRO">
    </form>
</body>
</html>