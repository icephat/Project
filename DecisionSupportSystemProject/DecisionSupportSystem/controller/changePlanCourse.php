<?php


session_start();

require '../function/studentFunction.php';

$studentId = $_SESSION["studentId"];

$student = getStudentByStudentId($studentId);


?>