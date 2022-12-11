<?php
/* FICHERO DE GESTION
 */
// CONEXION - metodo que crea conexión de BD y la devuelve
    function fncCrearConexion($host, $user, $pass, $db){

        $con = new mysqli($host,$user, $pass);   
        if(!$con->select_db($db)) die ($con->error);  

        return $con;
    }

    // consulta que devuelve las distintas categorias
    function getCategoriasCursos ($con){
        $sql = "SELECT DISTINCT categoria FROM temas";
        $resultado = $con->query($sql);  
        
        if($con->errno) {
            die($con->error);
        }else{
            return $resultado;
        }; 
    }

    // consulta que devuelve la cantidad de cursos por tema
    function getCantidadCursosPorTema ($con, $tema){
        $sql = "SELECT count(*) as cuenta from cursos where ID_TEMA = '$tema'";

        $result = mysqli_query($con, $sql);
        while($cursos_row = mysqli_fetch_assoc($result)){
            $cuenta = $cursos_row['cuenta'];
        }
        return $cuenta;
    }

    // consulta que devuelve el tema segun la categoria seleccionada
    function getCursosPorCategoria ($con, $categoria){
        $sql = "SELECT ID_TEMA, TEMA from temas where CATEGORIA = '$categoria'";
        
        if ($categoria == "todas"){
            $sql = "SELECT ID_TEMA, TEMA from temas";
        }

        $resultado = $con->query($sql);  
        
        if($con->errno) {
            die($con->error);
        }else{
            return $resultado;
        }; 
    }

    // update que sube el precio el porcentaje que se le pase por parametro
    function subirPrecio ($con, $idTema, $porcentaje){
        $sql = "UPDATE cursos set cursos.PRECIO=(cursos.PRECIO *(1+($porcentaje/100))) WHERE cursos.ID_CURSO in (select ID_CURSO from (select ID_CURSO from cursos, temas where cursos.ID_TEMA=temas.ID_TEMA and temas.ID_TEMA='$idTema') as cursos)";

        mysqli_query($con, $sql);
    }

    // update que baja el precio el porcentaje que se le pase por parametro
    function bajarPrecio ($con, $idTema, $porcentaje){
        $sql = "UPDATE cursos set cursos.PRECIO=(cursos.PRECIO *(1-($porcentaje/100))) WHERE cursos.ID_CURSO in (select ID_CURSO from (select ID_CURSO from cursos, temas where cursos.ID_TEMA=temas.ID_TEMA and temas.ID_TEMA='$idTema') as cursos)";

        mysqli_query($con, $sql);
    }


    // consulta que devuelve informacion sobre los cursos que 
    // se han realizado el dia en que se hace la consulta
    function getCursosHoy ($con){
        $sql = 	'SELECT cursos.Id_curso as curso ,edificios.nombre as edificio,aulas.ID_AULA as aula,temas.TEMA as tema , cursos.asistentes as asistentes 
        from cursos, temas, aulas, edificios where cursos.id_tema=temas.ID_TEMA 
        and cursos.ID_AULA=aulas.ID_AULA and aulas.id_edificio=edificios.id_edificio
        and DATE_FORMAT(dia, "%Y-%m-%d") = DATE_FORMAT(sysdate(), "%Y-%m-%d")
        order by tema desc';

        $resultado = $con->query($sql);  
        
        if($con->errno) {
            die($con->error);
        }else{
            return $resultado;
        }; 
    }
 
    // funcion que actualiza la cantidad de asistentes en el curso en el que se le especifica
    function updateAsistentes ($con, $idCurso, $asistentes){
        $sql = "UPDATE cursos set cursos.ASISTENTES = '$asistentes' where cursos.ID_CURSO = '$idCurso'";
        mysqli_query($con, $sql);
    }
?>