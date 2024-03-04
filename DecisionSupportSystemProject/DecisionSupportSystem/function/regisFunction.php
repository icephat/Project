<?php


function getListRegisByStudentIdAndSemesterId($studentId, $semesterId)
{

    require("connection_connect.php");

    $sql = "SELECT * FROM fact_regis NATURAL JOIN subject WHERE studentId = '" . $studentId . "' AND semesterId = " . $semesterId;
    $result = $conn->query($sql);
    //$regisList = $result->fetch_assoc();


    while ($my_row = $result->fetch_assoc()) {
        $regisList[] = $my_row;
    }


    require("connection_close.php");

    return $regisList;
}

function getListRegisByStudentId($studentId)
{

    require("connection_connect.php");

    $sql = "SELECT * FROM fact_regis NATURAL JOIN subject WHERE studentId = '" . $studentId . "'";
    $result = $conn->query($sql);
    //$regisList = $result->fetch_assoc();


    while ($my_row = $result->fetch_assoc()) {
        $regisList[] = $my_row;
    }


    require("connection_close.php");

    return $regisList;
}

function getListRegisNotFAndWByStudentId($studentId)
{

    require("connection_connect.php");

    $sql = "SELECT * FROM fact_regis NATURAL JOIN subject WHERE studentId = '" . $studentId . "' AND gradeCharacter != 'F' AND gradeCharacter != 'W'";
    $result = $conn->query($sql);
    //$regisList = $result->fetch_assoc();


    while ($my_row = $result->fetch_assoc()) {
        $regisList[] = $my_row;
    }


    require("connection_close.php");

    return $regisList;
}

function getListSubjectPassInRegisByStudentIdAndSubjectCategory($studentId, $subjectCategoryName)
{

    require("connection_connect.php");

    $sql = "SELECT * FROM semester NATURAL JOIN fact_regis NATURAL JOIN subject NATURAL JOIN subjectgroup NATURAL JOIN subjectcategory WHERE studentId = '" . $studentId . "' AND subjectCategoryName = '" . $subjectCategoryName . "' AND (gradeCharacter != 'F' AND gradeCharacter != 'W' AND gradeCharacter != 'P')";
    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $subjects[] = $my_row;
    }


    require("connection_close.php");

    return $subjects;

}

function getListSubjectPassInRegisByStudentIdAndSubjectGroup($studentId, $subjectGroup)
{
    require("connection_connect.php");
    $subjects = [];
    $sql = "SELECT * FROM semester NATURAL JOIN fact_regis NATURAL JOIN subject NATURAL JOIN subjectgroup NATURAL JOIN subjectcategory WHERE studentId = '" . $studentId . "' AND subjectGroup = '" . $subjectGroup . "' AND (gradeCharacter != 'F' AND gradeCharacter != 'W' AND gradeCharacter != 'P')";
    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $subjects[] = $my_row;
    }


    require("connection_close.php");

    return $subjects;

}




?>