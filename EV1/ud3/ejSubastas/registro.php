<?php
include 'header.php';

$_SESSION['urlanterior'] = $_SERVER['PHP_SELF'];


$error = "";
if(isset($_POST['submitRegistro'])){
    if(!comprobarUsername($conn, $_POST['username'])){
        $cadena = setUsuario($conn, $_POST['username'], $_POST['nombre'], $_POST['password'], $_POST['email']);
        if($cadena!=false){
            $cuerpo = "<a href='".RUTA."verificacion.php?cadena=".$cadena."&email=".$_POST['email']."'>Pincha en este enlace para verificar tu cuenta</a>";
            $email = $_POST['email'];
            $nombre = $_POST['nombre'];
            $asunto = "Verificacion de usuario SUBASTAS DW2";

        }
    }
}
?>
<h1>Registro</h1>
<p>Para registrarse en SUBASTAS DW2, rellena el siguiente formulario</p>
<p style="color:red"><?php echo $error?></p>
<table>
    <form method="POST" action="<?php echo $_SERVER['PHP_SELF']?>">
        <tr><td>Nombre de usuario</td><td><input type="text" name="username" required></td></tr>
        <tr><td>Nombre y apellidos</td><td><input type="text" name="nombre" required></td></tr>
        <tr><td>Contraseña</td><td><input type="password" name="password" required></td></tr>
        <tr><td>Email</td><td><input type="email" name="email" required></td></tr>
        <tr><td colspan="2"><input type="submit" name="submitRegistro" value="REGISTRARSE"></td></tr>
    </form>
</table>
<p>Recibira un correo de verificación</p>

<?php   
    include 'footer.php';
?>