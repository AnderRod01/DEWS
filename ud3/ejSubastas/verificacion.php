<?php
        include 'header.php';
        $cadena = $_GET['cadena'];
        $email = $_GET['email'];
        if(activarUsuario($link, $email, $cadena)){            
            echo "<p>Se ha verificado la cuenta. Puedes entrar pinchando <a href='login.php'>log in</a></p>";
        }else{
            echo "<p>No se ha podido verificar la cuenta <a href='index.php'>Ir al inicio</a></p>";
        }
        ?>
    </body>
</html>
