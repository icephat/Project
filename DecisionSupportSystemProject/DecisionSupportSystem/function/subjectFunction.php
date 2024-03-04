<?php

function getAllSubject(){

    $subjects = [];

    require("connection_connect.php");

    $sql = "SELECT * FROM subject NATURAL JOIN subjectgroup NATURAL JOIN subjectcategory ORDER BY subjectCode";
    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $subjects[] = $my_row;
    }
    require("connection_close.php");


    return $subjects;
}

function getSubjectBySubjectId($subjectId){

    require("connection_connect.php");

    $sql = "SELECT * FROM subject NATURAL JOIN subjectgroup NATURAL JOIN subjectcategory WHERE subjectId = $subjectId";
    $result = $conn->query($sql);
    $subject = $result->fetch_assoc();
    
    require("connection_close.php");


    return $subject;
}

?>