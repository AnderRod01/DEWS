<?php

function setUsuario($link, $username, $nombre, $password, $email){
    // $cadena = substr(str_shuffle("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"), 0, 16);   
    $cadena = substr(str_shuffle("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"), 1, 16);     
    $sql = "INSERT INTO usuarios (username, nombre, password, email, cadenaverificacion)"
            . " VALUES ('$username', '$nombre', '$password', '$email', '$cadena')";
    $result = mysqli_query($link, $sql);
    if(mysqli_affected_rows($link)>0){
        return $cadena;
    }
    return false;
}

function comprobarUsername($link, $username){
    $sql = "select id from usuarios where username = '$username'";
    $result = mysqli_query($link, $sql);
    if($fila = mysqli_fetch_assoc($result)){
        return true;
    }
    return false;
}

function comprobarUsuPas($link, $username, $password){
    $sql = "select id from usuarios where username = '$username' and password = '$password'";
    $result = mysqli_query($link, $sql);
    if($fila = mysqli_fetch_assoc($result)){
        return true;
    }
    return false;
}

function comprobarUsuActivo($link, $username){
    $sql = "select activo from usuarios where username = '$username'";
    $result = mysqli_query($link, $sql);
    if($fila = mysqli_fetch_assoc($result)){
        if($fila['activo']=='1'){
            return true;
        }
    }
    return false;
}

