<!-- PAGINA QUE GESTIONA LAS ANULACIONES DE ASISTENCIA -->

<?php
    require("header.php");
    session_start();

    $fechaHoy = date('Y-m-d');

    // CARGO LOS DATOS EN LA SESION LA PRIMERA VEZ QUE ENTRAMOS EN LA PAGINA 
    if(!isset($_SESSION['cursos'])){
        $_SESSION['cursos'] = [];
        $cursosHoy = getCursosHoy($con);
        foreach ($cursosHoy as $filaCurso) {
            $curso = $filaCurso['curso'];
            $edificio = $filaCurso['edificio'];
            $aula = $filaCurso['aula'];
            $tema = $filaCurso['tema'];
            $asistentes = $filaCurso['asistentes'];
            array_push($_SESSION['cursos'], ["curso" => $curso, "edificio" => $edificio, "aula" => $aula, "tema" => $tema, "asistentes" => $asistentes]);
        }
        
    }
    // CANCELO 1 ASISTENCIA EN LA SESIONs
    if (isset($_GET['cursoACambiar'])){


        $i = 0;
        foreach ($_SESSION['cursos'] as $filaCurso) {
            if ($filaCurso['curso'] == $_GET['cursoACambiar']){
                $_SESSION['cursos'][$i]['asistentes'] -= 1;
            }
            $i++;
        }
    }



    function mostrarTablaAnulaciones (){
        foreach ($_SESSION['cursos'] as $filaCurso) {
            $curso = $filaCurso['curso'];
            $edificio = $filaCurso['edificio'];
            $aula = $filaCurso['aula'];
            $tema = $filaCurso['tema'];
            $asistentes = $filaCurso['asistentes'];

            echo "<tr class='body'>
                <td>Curso $curso ($edificio $aula, $tema)</td>
                <td>$asistentes asistentes</td>";
            
            
            if ($asistentes == 0){
                echo "</tr>";
            }else{
                echo "<td><a href='anulaciones.php?cursoACambiar=$curso&asisentes=$asistentes'>Cancelar 1 asistencia</a></td></tr>";
            }
                
        }
    }

?>
<div id="main">
    <h1>CURSOS DE HOY (<?= $fechaHoy?>)</h1>
    <?php
         // GUARDO LOS DATOS DE ASISTENCIA EN BASE DE DATOS Y MUESTRO UN MENSAJE DE CONFIRMACION
        if(isset($_GET['guardado'])){
            echo "<p>Las anulaciones han sido guardadas correctamente</p>";

            foreach ($_SESSION['cursos'] as $filaCurso) {
                $curso = $filaCurso['curso'];
                $asistentes = $filaCurso['asistentes'];
    
                updateAsistentes($con, $curso, $asistentes);
            }
        }
    ?>
    <table>
        <?=mostrarTablaAnulaciones(); ?>
    </table>
    <a href="anulaciones.php?guardado=true">Guardar anulaciones</a>
</div>

<?=require("footer.php");?>
