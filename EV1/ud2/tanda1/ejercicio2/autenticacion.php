<?php
    include 'libmenu.php';
    if(isset($_POST['btnSocio'])) {
        if(strlen(trim($_POST['txtNombre']))==0 || strlen(trim($_POST['txtPwd']))==0){
            header('Location: entrada.php?error=0');
            exit();
        }else{
            $nombre = trim($_POST['txtNombre']);
            if(autentica($nombre,trim($_POST['txtPwd'])) == 1){
                session_start();
                $_SESSION['usuario'] = [$nombre, dameDcto($nombre)];
                header('Location: pedido.php');
                exit();
            }
            else {
                header('Location: entrada.php?error=1');
                exit();
            }
        }
    }elseif(isset($_POST['btnInvitado'])){
        session_start();
        
        $_SESSION['usuario'] = ['invitado', 0];
        header('Location: pedido.php');
        exit();
    }else {
        header('Location: entrada.php');
        exit();
    }
?>