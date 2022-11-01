<?php
    include 'header.php';
    $_SESSION['urlanterior'] = $_SERVER['PHP_SELF']."?item=".$_GET['item'];

    $id_item = $_GET['item'];
    $item = getItem($link, $id_item);
    $nombre = $item['nombre'];
    $cantPujas = cantPujas($link, $id_item);
    if($cantPujas==0){
        $precioActual = $item['preciopartida'];
    }else{
        $precioActual = getPrecioActual($link, $item['id']);
    }
    $fechafin = date("d/M/Y h:iA", strtotime($item['fechafin']));
    $imagenes = getImagenesItem($link, $id_item);
    $desc = $item['descripcion'];
    $todasPujas = getPujas($link, $id_item);
    
    $errorPuja = "";
    if(isset($_POST['submitPuja'])){
        if($_POST['cantPuja']==""){
            $errorPuja = "Debes introducir algún valor";
        }else if(!is_numeric($_POST['cantPuja'])){
            $errorPuja = "Tiene que ser un valor numérico";
        }else if($todasPujas[0]['cantidad']>=$_POST['cantPuja']){
            $errorPuja = "Tienes que superar la puja anterior: ".$todasPujas[0]['cantidad'].DIVISA;
        }else if(cantPujasHoy($link, $_SESSION['uid'])>3){
            $errorPuja = "Tienes un máximo de 3 pujas diarias";
        }else{
            if(!setPuja($link, $id_item, $_SESSION['uid'], $_POST['cantPuja'])){
                $errorPuja = "No se ha podido introducir la puja en la base de datos";
            }else{
                $todasPujas = getPujas($link, $id_item);
            }
        }
    }
?>
<?php
    echo "<h1>$nombre</h1>";
    echo "<p><strong>Número de pujas: </strong> $cantPujas -
        <strong>Precio actual: </strong>$precioActual - 
        <strong>Fecha de cierre de pujas: $fechafin</strong></p>
    ";
    echo "<p>";
    foreach ($imagenes as $imagen) {
        $src = IMAGENES.$imagen['imagen'];
        echo "<img src='$src' alt='No se ha podido poner la imagen' width='200px'>";
    }
    echo "</p>";
    echo "<p>$desc</p>";
    
    echo "<h2>Puja por este item</h2>";
    if(!isset($_SESSION['uid'])){
        echo "<p>Para pujar debes autenticarte. <a href='login.php'>Inicair sesión</a></p>";
    }else{
        echo "<p>Añade tu puja en el siguiente formulario</p>";
        echo "<form method='POST' action='".$_SERVER['PHP_SELF']."?item=".$_GET['item']."'>";
            echo "<table>";
            echo "<tr><td><input type='text' name='cantPuja'></td>"
                . "<td><input type='submit' name='submitPuja' value='Pujar'></td>"
                . "<td style='color:red;'>$errorPuja</td></tr>";
            echo "</table>";
        echo "</form>";
        echo "<h2>Historial de pujas</h2>";
        echo "<ul>";
        foreach ($todasPujas as $puja) {
            echo "<li>".$puja['username']." - ".$puja['cantidad'].DIVISA."</li>";
        }
        echo "<li>Precio de partida = ".$item['preciopartida'].DIVISA."</li>";
        echo "</ul>";
    }
    
?>

<?php
    include 'footer.php';
?>