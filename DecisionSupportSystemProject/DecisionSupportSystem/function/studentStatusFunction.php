<?php

function getStudentStatusById($studentStatusId){

    require("connection_connect.php");

    $sql = "SELECT * FROM studentstatus WHERE studentStatusId = ".$studentStatusId;
    $result = $conn->query($sql);
    $studentStatus = $result->fetch_assoc();

    require("connection_close.php");

    return $studentStatus;

}

?>