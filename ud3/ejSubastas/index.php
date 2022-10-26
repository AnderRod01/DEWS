<?php
    include_once 'config.php';
    session_start();

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
            $query = "SELECT imagenes.imagen, items.nombre, pujas.cantidad, items.preciopartida, items.fechafin
                        FROM items
                        left join pujas
                        on  pujas.id_item = items.id
                        left join imagenes 
                        on imagenes.id_item = items.id
                        where items.id_cat =". $_GET['id']."
                        group by items.id;";
        }
    
    
    
        $resultset = mysqli_query($conn, $query);
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
    
            echo "<td>".$row['nombre']."</td>"; //nombre, sera un enlace
            
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
<style>
    img{
        width: 150px;
        object-fit:cover;
    }
</style>
<body>
    <div id="header">
        <h1><?php echo NOMBREFORO ?></h1>
    </div>
    <div id="menu">
        <a href="index.php">Home</a>
        <?php
            if(isset($_SESSION['USERNAME']) == TRUE) {
                echo "<a href='logout.php'>Logout</a>";
            }
            else {
                echo "<a href='login.php'>Login</a>";
            }
        ?>
        <a href="newitem.php">New Item</a>
    </div>
    <div id="container">
        <div id="bar">
            <?php require_once("bar.php"); ?>
        </div>
        <div id="main">  
            <?php
                cargarItems();
            ?>
        </div>
    </div>
    <div>
        
    </div>
</body>
</html>