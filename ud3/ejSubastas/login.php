<?php

    if (isset($_POST['login'])){
        $query = "SELECT COUNT(*) FROM USUARIOS WHERE USERNAME = ? AND PASSWORD = ?";

        if ($stmt = mysqli_prepare($conn, $query)){
            mysqli_stmt_bind_param($stmt, "ss", $_POST['txtUsu'], $_POST['txtPwd']);

            $rs = mysqli_stmt_execute($stmt);
            if($rs == 1){
                var_dump($rs);
            }else{
                var_dump($query);
            }

        }
        mysqli_stmt_close($stmt);
    }


    if ($_SESSION['paginaActual'] == "login"){

        echo "<script>document.getElementById('main').remove()</script>";
        $txtHTML = '<form enctype="multipart/form-data" action="'.$_SERVER["PHP_SELF"].'" method="post">
        <h1>Login</h1>
        <table>
            <tr>
                <td><label for="txtUsu">Usuario</label></td>
                <td><input type="text" name="txtUsu" id="txtUsu"></td>
            </tr>
            <tr>
                <td><label for="txtPwd">Password</label></td>
                <td><input type="password" name="txtPwd" id="txtPwd"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" name="login" value="Login!"></td>
            </tr>
        </table>
    </form>
    <p>No tienes una cuenta? <a href="registro.php">Registrate!</a></p>';

    echo $txtHTML;
    }

    
?>




