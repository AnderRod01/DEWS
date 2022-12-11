<?php

    if(isset($_POST['submitLogin'])){
        include 'config.php';
        
        $link = conexion();
        
        if($_POST['usuario']=="" || $_POST['password']==""){
            $errorLogin = "Debes introducir usuario y contraseña";
        }else{
            if(!comprobarUsuPas($link, $_POST['usuario'], $_POST['password'])){
                $errorLogin = "Datos incorrectos";
            }else{
                if(!comprobarUsuActivo($link, $_POST['usuario'])){
                    $errorLogin = "Esta cuenta no está verificada. Verifiquela desde el email que le hemos enviado.";
                }else{
                    $_SESSION['username'] = $_POST['usuario'];
                    $_SESSION['uid'] = getUid($link, $_POST['usuario']);
                    
                }
            }
        }

        if($errorLogin!=""){
            header("Location:".$_SERVER['PHP_SELF']."?error=$errorLogin");
        }else{
            header("Location:".$_SESSION['urlanterior']);
        }
        

    }else{
        include 'header.php';
        $errorLogin = "";
        if(isset($_GET['error'])){
           $errorLogin = $_GET['error'];
        }
?>

<h1>LOGIN<h4 style="color: red;"><?php echo $errorLogin?></h4></h1>
<table>
    <form method="POST" action="<?php echo $_SERVER['PHP_SELF']?>">
        <tr>
            <th>Usuario</th>
            <td><input type="text" name="usuario" value=""></td>
        </tr>
        <tr>
            <th>Contraseña</th>
            <td><input type="password" name="password" value=""></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" name="submitLogin" value="Login!"></td>
        </tr>
    </form>
</table>
<p>No tienes una cuenta? <a href="registro.php">Registrate!</a></p>

<?php
    include 'footer.php';
}
?>