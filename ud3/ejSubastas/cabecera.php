<?php
    include_once 'config.php';
    session_start();

    $conn = mysqli_connect(DB_HOST, DB_USER, DB_PASS);
    mysqli_select_db($conn, DB_DATABASE);

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
<body>
    <div id="header">
        <h1><?php echo NOMBREFORO ?></h1>
    </div>
    <div id="menu">
        <a href="cabecera.php">Home</a>
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
            <?php require_once("index2.php");?>
        </div>
    </div>
    <div>

    </div>
</body>
</html>