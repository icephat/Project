<?php

require_once 'studentFunction.php';

function getTermSummaryListByStudentId($studentId)
{


    require("connection_connect.php");

    $sql = "SELECT * FROM fact_term_summary NATURAL JOIN semester WHERE studentId = '$studentId'";
    $result = $conn->query($sql);
    
    $terms = [];

    while ($my_row = $result->fetch_assoc()) {
        $semesterId = $my_row["semesterId"];
        $my_row["regisList"] = getListRegisByStudentIdAndSemesterId($studentId,$semesterId);
        $terms[] = $my_row;


    }


    require("connection_close.php");

    return $terms;
}


?>