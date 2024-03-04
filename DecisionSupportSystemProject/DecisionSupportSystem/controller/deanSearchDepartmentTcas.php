<?php

$departmentId = $_POST["departmentId"];
$tcas = $_POST["tcas"];


if ($tcas == 0) {

    ?>
    <html>

    <head>
        <title></title>
    </head>

    <body OnLoad="document.form1.submit();">
        <form action="../dean/student_tcas_search.php" method="post" name="form1">
            <input type="number" name="departmentId" value="<?php echo $departmentId; ?>" />
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
        <form action="../dean/student_tcas_search_round.php" method="post" name="form1">
            <input type="number" name="departmentId" value="<?php echo $departmentId; ?>" />
            <input type="number" name="tcas" value="<?php echo $tcas; ?>" />
            <!--<a type="button" name="std_info">
                    <span class="glyphicon glyphicon-user"></span></a>-->
            <input name="btnSubmit" type="submit" value="Submit">
        </form>
    </body>

    </html>
    <?php
}
?>