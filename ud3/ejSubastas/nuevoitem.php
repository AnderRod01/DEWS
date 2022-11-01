<?php
    include 'header.php';
    $_SESSION['urlanterior'] = $_SERVER['PHP_SELF'];
    
    if(!isset($_SESSION['uid'])){
        header("Location:login.php");
    }
    
    $nombre = "";
    $desc = "";
    $error = "";
    if(isset($_POST['submitAniadir'])){
        if($_POST['nombre']==""){
            $error .= "Introduce un nombre para el articulo. ";
        }else{
            $nombre = $_POST['nombre'];
        }
        if($_POST['descripcion']=""){
            $error .= "Introduce una decriopcion para el articulo. ";
        }else{
            $desc = $_POST['descripcion'];
        }
        
        $f = $_POST['ano']."-".$_POST['mes']."-".$_POST['dia']." ".$_POST['hora'].":".$_POST['minutos'];
        $fecha = date("Y-m-d H:i", strtotime($f));
        if(strtotime($fecha) < time()){
            $error .= "Introduce una fecha valida. ";
        }
        if($_POST['precio']=="" || !is_numeric($_POST['precio'])){
            $error .= "Introduce un precio adecuado para el articulo. ";
        }
        
        if($error==""){
            $id = insertItem($link, $_POST['categoria'], $_POST['nombre'], $_POST['descripcion'], $fecha, $_POST['precio'], $_SESSION['uid']);
            if($id==false){
                $error = "Algo ha ido mal a la hora de introducir el item en la bsae de datos, revise el formulario."; 
            }else{
                header("Location:editaritem.php?idArt=$id&idUser=".$_SESSION['uid']);
            }
        }
    }
    
    echo "<h3 style='color:red;'>$error</h3>";
    echo "<table>";
        echo '<form method="POST" action="'.$_SERVER['PHP_SELF'].'">';
            echo "<tr><th>Categoría</th><td><select name='categoria'>";
                foreach ($categorias as $categoria) {
                    echo "<option value='".$categoria['id']."'>".$categoria['categoria']."</option>";
                }
            echo "</select></td></tr>"
                ?>
                <tr><th>Nombre</th><td><input type='text' name='nombre' value='<?php echo $nombre?>'></td></tr>
                <tr><th>Descripcion</th><td><textarea name='descripcion' rows='4' cols='50' placeholder='<?php echo $desc?>'></textarea></td></tr>
                <tr>
                    <th>Fecha de fin de puja</th>
                    <td>
                        <table>
                            <tr><th>Día</th><th>Mes</th><th>Año</th><th>Hora</th><th>Minutos</th></tr>
                            <tr>
                            <td><select name='dia'>
                            <?php
                                for ($i = 1; $i <= 31; $i++) {
                                   echo "<option value='$i'>$i</option>";
                                }
                                echo "</td><td><select name='mes'>";
                                for ($i = 1; $i <= 12; $i++) {
                                   echo "<option value='$i'>$i</option>";
                                }
                                echo "</td><td><select name='ano'>";
                                $a = date("Y");
                                for ($i = date("Y"); $i <= (date("Y")+5); $i++) {
                                   echo "<option value='$i'>$i</option>";
                                }
                                echo "</td><td><select name='hora'>";
                                for ($i = 0; $i <= 23; $i++) {
                                   echo "<option value='$i'>$i</option>";
                                }
                                echo "</td><td><select name='minutos'>";
                                for ($i = 0; $i <= 59; $i++) {
                                   echo "<option value='$i'>$i</option>";
                                }
                                ?>
                            </tr>
                        </table>
                    </td>
                </tr>
            <tr><th>Precio</th><td><input type="text" name="precio">€</td></tr>
            <tr><td colspan="2"><input type="submit" name="submitAniadir" value="AÑADIR"></td></tr>
        </form>
    </table>
<?php
include 'footer.php';
?>