<?php

function getSchoolById($schoolId){

    require("connection_connect.php");

    $sql = "SELECT * FROM school NATURAL JOIN province NATURAL JOIN region WHERE schoolId = ".$schoolId;
    $result = $conn->query($sql);
    $school = $result->fetch_assoc();

    require("connection_close.php");

    return $school;

}

?>