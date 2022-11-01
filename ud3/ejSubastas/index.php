<?php
    include_once 'header.php';

    $_SESSION['paginaActual'] = "index";

    $conn = mysqli_connect(DB_HOST, DB_USER, DB_PASS);
    mysqli_select_db($conn, DB_DATABASE);


    

    function cargarItems (){
        if (!isset($_GET['id'])){
            $query = "SELECT imagenes.imagen, items.nombre, pujas.cantidad, items.preciopartida, items.fechafin
                        FROM items
                        left join pujas
                        on  pujas.id_item = items.id
                        left join imagenes 
                        on imagenes.id_item = items.id
                        group by items.id;";
    
    
        }else{
            $query = "SELECT imagenes.imagen, items.nombre, pujas.cantidad, items.preciopartida, items.fechafin, items.id
                        FROM items
                        left join pujas
                        on  pujas.id_item = items.id
                        left join imagenes 
                        on imagenes.id_item = items.id
                        where items.id_cat =". $_GET['id']."
                        group by items.id;";
        }

        $resultset = mysqli_query($GLOBALS["conn"], $query);
        echo "<table><h1>Items disponibles</h1>";
        echo "<tr>
                <th>Imagen</th>
                <th>Item</th>
                <th>Pujas</th>
                <th>Precio</th>
                <th>Pujas Hasta</th>
            </tr>";
        
        while ($row = mysqli_fetch_assoc($resultset)){
            if($row['imagen']== null)
                echo "<tr><td>NO IMAGEN</td>";
            else
                echo "<tr><td><img src='./imagenes/".$row['imagen'].".jpg'></td>";  //poner imagenes
    
            echo "<td><a href='itemdetalles.php?'".$row['id']."'>".$row['nombre']."</a></td>"; //nombre, sera un enlace
            
            if ($row['cantidad'] == null)
                echo "<td>0</td>";
            else
                echo "<td>".$row['cantidad']."</td>"; //cantidad
            
            echo "<td>".$row['preciopartida']."</td>"; //precio
    
            echo "<td>".$row['fechafin']."</td></tr>"; //fechafin
        }
        echo "</table>";
    }
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <title><?php echo NOMBREFORO ?></title>
</head>

<?php cargarItems()?>