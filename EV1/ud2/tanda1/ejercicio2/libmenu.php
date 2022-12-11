<?php

    function autentica($user,$pwd){
        $f = fopen('utils/socios.txt','r');
        while (!feof($f)) {
            $linea = fgets($f); 
            $linea = explode(';',$linea);
            if($user==trim($linea[0]) && $pwd==trim($linea[1])){
                fclose($f);
                return 1;
            }
        }
        fclose($f);
        return 0;
    }

    function dameDcto($user) {
        $f = fopen('utils/socios.txt','r');
        while (!feof($f)) {
            $linea = fgets($f); 
            $linea = explode(';',$linea);
            if($user==trim($linea[0])){
                fclose($f);
                return trim($linea[2]);
            }
        }
        fclose($f);
        return 0;
    }

    function damePlatos($tipo)  {
        $platos = [];
        $f = fopen('utils/platos.txt','r');
        while (!feof($f)) {
            $linea = fgets($f); 
            $linea = explode(';',$linea);
            if($tipo==trim($linea[1]))           
                $platos[trim($linea[0])] = trim($linea[2]);            
        }
        fclose($f);
        return $platos;
    }

    function damePrecio($plato) {
        $f = fopen('utils/platos.txt','r');
        while (!feof($f)) {
            $linea = fgets($f); 
            $linea = explode(';',$linea);
            if($plato==trim($linea[0])){
                fclose($f);
                return trim($linea[2]);
            }
        }
        fclose($f);
        return -1;
    }
?>