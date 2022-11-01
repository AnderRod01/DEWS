<?php
    include 'header.php';
    if(!isset($_SESSION['uid']) || !isset($_GET['idUser']) || !isset($_GET['idArt']) || $_SESSION['uid']!=$_GET['idUser']){
        header("Location:index.php");
    }
    
    $item = getItem($link, $_GET['idArt']);
    
    if(isset($_POST['submitBajarP'])){
        if(isset($_POST['cant'])){
            $precio = $item['preciopartida']-$_POST['cant'];
            updatePrecioSalidaItem($link, $precio, $item['id']);
        }
    }
    if(isset($_POST['submitSubirP'])){
        if(isset($_POST['cant'])){
            $precio = $item['preciopartida']+$_POST['cant'];
            if($precio>0){
                updatePrecioSalidaItem($link, $precio, $item['id']);
            }            
        }
    }
    if(isset($_POST["submitPH"])){
        updateFechaFinItem($link, $item['fechafin'], $item['id'], "hora");
    }
    if(isset($_POST["submitPD"])){
        updateFechaFinItem($link, $item['fechafin'], $item['id'], "dia");
    }
    if(isset($_GET['borrar'])){
        $id_imagen = $_GET['borrar'];
        $src = getImagen($link, $id_imagen);
        unlink(IMAGENES.$src);
        borrarImagen($link, $id_imagen);
    }
    if(isset($_POST['submitSubirI'])){
        if(is_uploaded_file($_FILES['img']['tmp_name'])){
            $origen = $_FILES['img']['tmp_name'];
            $imagen = $_FILES['img']['name'];
            $destino = IMAGENES.$imagen;
            $cont = 2;
            while(file_exists($destino)){
                $nombre = explode(".", $imagen)[0];
                $tipo = explode(".", $imagen)[1];
                
                if(count(explode("_", $nombre))>=2){
                    $nombre .= "_".$cont;
                }else{
                    $nombre[strlen($nombre)-1] = $cont;
                }
                
                $destino = IMAGENES.$nombre.".".$tipo;
                $cont ++;
            }
            
            move_uploaded_file($origen, $destino);
            $img = explode("/", $destino)[1];
            insertImagen($link, $item['id'], $img);
        }
    }
    
    $item = getItem($link, $_GET['idArt']);
    $imagenes = getImagenesItem($link, $_GET['idArt']);
    $enlace = $_SERVER['PHP_SELF']."?idArt=".$item['id']."&idUser=".$_SESSION['uid'];
    
    
    
    
    echo "<h1>".$item['nombre']."</h1>";
    echo "<table>";
        echo "<tr>";
            echo "<td><strong>Precio de salida: </strong>".$item['preciopartida']."</td>";
            echo "<td>";
            if(count(getPujas($link, $item['id']))==0){
                
                echo "<form method='POST' action='$enlace'>"
                        . "<input type='text' name='cant'>"
                        . "<input type='submit' name='submitBajarP' value='BAJAR'>"
                        . "<input type='submit' name='submitSubirP' value='SUBIR'>"
                    . "</form>";
            }
            echo "</td>";
        echo "</tr>";
        echo "<tr>";
            echo "<td><strong>Fecha fin para pujar: </strong>".$item['fechafin']."</td>";
            echo "<td><form method='POST' action='$enlace'>"
                    . "<input type='submit' name='submitPH' value='Postponer 1 hora'>"
                    . "<input type='submit' name='submitPD' value='Postponer 1 día'></form></td>";
        echo "</tr>";
    echo "</table>";
    
    echo "<h1>Imagenes actuales</h1>";
    if(count($imagenes)==0){
        echo "<p>No hay imagenes</p>";
    }else{
        echo "<table>";
        foreach ($imagenes as $imagen) {
            echo "<tr>";
                $src = IMAGENES.$imagen['imagen'];
                echo "<td><img src='$src' alt='No se ha podido insertar la imagen' width='400px'></td>";
                echo "<td><a href='".$enlace."&borrar=".$imagen['id']."'>[BORRAR]</a></td>";
            echo "</tr>";
        }
        echo "</table>";
    }    
?>
<table>
    <form method="POST" action="<?php echo $enlace;?>" enctype="multipart/form-data">
        <tr><th>Imagen a subir</th><td><input type="file" name="img"></td></tr>
        <tr><td colspan="2"><input type="submit" name="submitSubirI" value="SUBIR"></td></tr>
    </form>
</table>
<?php
    include 'footer.php';
?>