<?php

$semesterYear = $_POST["year"];
$generetion = $_POST["generetion"];


if ($generetion == 0) {
    ?>

    <html>

    <head>
        <title></title>
    </head>

    <body OnLoad="document.form1.submit();">
        <form action="../dean/report_advisor_search.php" method="post" name="form1">
            <input type="number" name="semesterYear" value="<?php echo $semesterYear; ?>" />
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
        <form action="../dean/report_advisor_search_generetion.php" method="post" name="form1">
            <input type="number" name="semesterYear" value="<?php echo $semesterYear; ?>" />
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