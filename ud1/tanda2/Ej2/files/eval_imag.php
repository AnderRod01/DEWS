<?php
    function rutasImg (){
        $arrImg = [];
        $arrext=["jpg","png","tiff","jpeg","gif"];
                $files=scandir('../images/');
                foreach($files as $f){
                    $ext = pathinfo($f, PATHINFO_EXTENSION);
                    if(in_array($ext, $arrext))
                    {
                        array_push($arrImg, $f);
                    }
                    
                }
        return $arrImg;
    }
?>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Evaluacion</title>
</head>
<body>
    <form enctype="multipart/form-data" action="<?php echo $_SERVER['PHP_SELF'];?>" method="post">
        <table>
            <?php
                $arrImg = rutasImg();
                shuffle($arrImg);

                for ($cont = 1; $cont<= $_POST['selCantidad']; $cont++){
                    $txtHtml = "<tr><td>";
                    $txtHtml .= "<img width = '300' height ='200'  src='../images/".$arrImg[$cont-1]."'/></td>";

                    $txtHtml .= "<td>
                                <input type='checkbox' id='img".$cont."' name='img".$cont."' value='".$cont."'>
                                <label for='img".$cont."'>Me gusta</label>
                            </td>";
                    echo $txtHtml."</tr>";
                }
                


            ?>
        </table>
    </form>
</body>
</html>