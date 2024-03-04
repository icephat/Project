<?php

$generetion = $_POST["generetion"];
$tcas = $_POST["tcas"];

if ($tcas == 0 && $generetion == 0) {
    header('Location: ' . '../dean/student_static_department.php');
    exit();
} else if ($tcas == 0) {

    ?>
        <html>

        <head>
            <title></title>
        </head>

        <body OnLoad="document.form1.submit();">
            <form action="../dean/student_static_department_search_genereton.php" method="post" name="form1">
                <input type="number" name="generetion" value="<?php echo $generetion; ?>" />
                <!--<a type="button" name="std_info">
                            <span class="glyphicon glyphicon-user"></span></a>-->
                <input name="btnSubmit" type="submit" value="Submit">
            </form>
        </body>

        </html>
    <?php
} else if ($generetion == 0) {
    ?>
            <html>

            <head>
                <title></title>
            </head>

            <body OnLoad="document.form1.submit();">
                <form action="../dean/student_static_department_search_round.php" method="post" name="form1">
                    <input type="number" name="tcas" value="<?php echo $tcas; ?>" />
                    <!--<a type="button" name="std_info">
                    <span class="glyphicon glyphicon-user"></span></a>-->
                    <input name="btnSubmit" type="submit" value="Submit">
                </form>
            </body>

            </html>
    <?php
} else {
    ?>
            <html>

            <head>
                <title></title>
            </head>

            <body OnLoad="document.form1.submit();">
                <form action="../dean/student_static_department_search_round_generetion.php" method="post" name="form1">
                    <input type="number" name="tcas" value="<?php echo $tcas; ?>" />
                    <input type="number" name="generetion" value="<?php echo $generetion; ?>" />
                    <!--<a type="button" name="std_info">
                <span class="glyphicon glyphicon-user"></span></a>-->
                    <input name="btnSubmit" type="submit" value="Submit">
                </form>
            </body>

            </html>

    <?php
}
?>