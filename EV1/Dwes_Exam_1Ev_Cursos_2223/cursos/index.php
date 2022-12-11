<!-- INDEX DE LA APP, DONDE SE GESTIONARAN LOS CAMBIOS DE PRECIO DE LA APLICACIÓN -->
<?php
    // incluimos la cabecera
    require("header.php");

    // funcion para mostrar la lista de radios de categorias
    function mostrarRadiosCategorias ($con){
        $listaCategorias = getCategoriasCursos($con);
        echo '<form action="index.php" method="POST">';
        foreach ($listaCategorias as $categoria){
            $cat = $categoria['categoria'];
            if(isset($_POST['radioCategorias']) && $_POST['radioCategorias'] == $cat){
                echo  "<input type='radio' name='radioCategorias' id='$cat' value='$cat' checked>
                <label for='$cat'>$cat</label><br>";
            }else{
                echo  "<input type='radio' name='radioCategorias' id='$cat' value='$cat'>
                <label for='$cat'>$cat</label><br>";
            }
        }

        
        if(isset($_POST['radioCategorias']) && $_POST['radioCategorias'] == 'todas'){
            echo "<input type='radio' name='radioCategorias' id='todas' value='todas' checked>
            <label for='todas'>TODAS LAS CATEGORIAS</label><br>";
        }else{
            echo "<input type='radio' name='radioCategorias' id='todas' value='todas'>
            <label for='todas'>TODAS LAS CATEGORIAS</label><br>";
        }
       

        echo "<input type='submit' name='verCursos' value='Ver cursos'></form><hr>";
    }

    // Funcion que muestra la tabla por categorias y los botones de subir y bajar precio
    function mostrarTablaCursosPorCategoria($con, $categoria){


        $listaTemas = getCursosPorCategoria($con, $categoria);
        echo "<form action='index.php' method='GET'><table><tr class='head'>
                <td>SELECCIONAR</td>
                <td>TEMA</td>
                <td>CANTIDAD</td>
            </tr>";



        foreach ($listaTemas as $tema){
            $nombreTema = $tema['TEMA'];
            $idTema = $tema['ID_TEMA'];
            $cantidadCursos = getCantidadCursosPorTema($con, $idTema);
        
            echo "<tr class='body'>
                <td><input type='checkbox' name='seleccionarTema[]' id='seleccionarTema' value='$idTema'></td>
                <td>$nombreTema</td>
                <td>$cantidadCursos</td>
                </tr>";

        }
        echo "</table>";
        $txtOptions ="";
        for ($precio = 5; $precio <=50; $precio +=5){
            $txtOptions .= "<option value='$precio'>$precio%</option>";
        }

        echo '<input type="submit" value="BAJAR PRECIO" name="bajarPrecio">
        <select name="porcentaje">'.$txtOptions.'</select>
        <input type="submit" value="SUBIR PRECIO" name="subirPrecio"></form>';
    }
    
    //Funcion que cambia el precio de los en base de datos
    function cambiarPrecio($con, $idTema, $porcentaje){
        if (isset($_GET['subirPrecio'])){
            subirPrecio($con, $idTema, $porcentaje);
        }else{
            bajarPrecio($con, $idTema, $porcentaje);
        }
    }

?>

<div id="main">
    <h1>Elija la categoria de cursos:</h1>
    <?php 
        mostrarRadiosCategorias($con);

        if (isset($_POST['verCursos'])){
            if(!isset($_POST['radioCategorias'])){
                echo "<div class='msg-rojo'>Debes seleccionar una categoria primero</div>";
            }else{
                mostrarTablaCursosPorCategoria($con, $_POST['radioCategorias']);
            }
        }else{
            if (isset($_GET['subirPrecio']) || isset($_GET['bajarPrecio'])){
                $totalCursosSeleccionados = 0;
                $porcentaje = $_GET['porcentaje'];
                
                foreach ($_GET['seleccionarTema'] as $idTema) {
                    cambiarPrecio($con, $idTema, $porcentaje);
                    $totalCursosSeleccionados += getCantidadCursosPorTema($con, $idTema);
                }

                echo "Se ha cambiado el precio de <strong>$totalCursosSeleccionados</strong> cursos";
            }
        }
    ?>
</div>
<!--  incluimos el pie de página  -->
<?= require("footer.php")?>