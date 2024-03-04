<?php

$studentId = $_POST["studentId"];

require_once '../function/connection_connect.php';
$queryCheck = "SELECT studentId FROM student WHERE studentId = '$studentId'";
$check = mysqli_query($conn, $queryCheck);
require_once '../function/connection_close.php';

if ($check->num_rows > 0) {

    ?>
    <html>

    <head>
        <title></title>
    </head>

    <body OnLoad="document.form1.submit();">
        <form action="../head/student_info.php" method="post" name="form1">
            <input type="text" name="studentId" value="<?php echo $studentId; ?>" />
            <!--<a type="button" name="std_info">
                            <span class="glyphicon glyphicon-user"></span></a>-->
            <input name="btnSubmit" type="submit" value="Submit">
        </form>
    </body>

    </html>


    <?php
} else {
    header('Location: ' . '../head/noFindNisit.php');
    exit();

}


?>