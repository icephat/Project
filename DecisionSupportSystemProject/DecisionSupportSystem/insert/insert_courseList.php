<?php


// Open uploaded CSV file with read-only mode
$csvFile = fopen("D:\CPEKU\Project66\course\cpe\course_cpe.csv", 'r');

// Skip the first line
fgetcsv($csvFile);


require("../function/connection_connect.php");

while (($getData = fgetcsv($csvFile, 1000000, ",")) !== FALSE){

    $courseName = $getData[0];
    $coursePlan = $getData[1];
    $studyYear = $getData[2];
    $term = $getData[3];
    $subjectCode = $getData[4];
    $credit = $getData[5];
    $subjectGroup = $getData[6];


    $sql = "insert into courselist(courseName,coursePlan,studyYear,term,subjectCode,credit,subjectGroup) values ('$courseName','$coursePlan',$studyYear,$term,'$subjectCode',$credit,'$subjectGroup')";
    mysqli_query($conn, $sql);
}








require("../function/connection_close.php");



?>