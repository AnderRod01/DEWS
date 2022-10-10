<?php 
    if(isset($_GET['logout'])) {
        session_start();
        session_destroy();
    }
?>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Entrada</title>
</head>
<body>
    <?php 
        if(isset($_GET['error0']))
            echo '<p style="color:red;">Rellena todos los campos</p>';
        if(isset($_GET['error1']))
            echo '<p style="color:red;">Usuario o contraseña erroneo</p>';
    ?>
    <p>Si eres SOCIO introduce tu usuario y password </p>
    <form enctype="multipart/form-data" method="POST" action="./autenticacion.php">
        <div>
            <label>Usuario</label> <input type="text" name="txtNombre"/>
        </div>
        <div>
            <label>Password</label> <input type="password" name="txtPwd"/>
        </div>
        <div>
            <input name="btnSocio" type="submit" value="Acceso Socio"></input>
        </div>
        <p>Si no dispones de usuario, entra como invitado</p>
        <div>   
            <input name="btnInvitado" type="submit" value="Acceso Invitado"></input>
        </div>        
    </form>
</body>
</html>