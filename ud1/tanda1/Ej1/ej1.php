<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <?php
        echo date("d\\t\h F Y, l")."<br>";

        $arrPalabras= array("Hola", "que", "tal");
        $txt = implode(" ", $arrPalabras);
        echo $txt."<br>";

        echo str_replace("ñ", "gn", "ñoñeria")."<br>";

        function generadorNum($n, $limite1, $limite2){
            for ($i=0; $i < $n; $i++) { 
                echo rand($limite1, $limite2)." -- ";
            }
        }

        generadorNum(5, 4, 40);
        echo "<br>"

        // queda 2 y ultimo

    ?>
</body>
</html>
