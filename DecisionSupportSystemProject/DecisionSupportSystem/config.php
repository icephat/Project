<?php

session_start();


include_once 'function/teacherFunction.php';
//require_once 'function/studentFunction.php';

$_SESSION["system"] = "DecisionSupport";
$_SESSION["access-user"] = "fengtps";//fengtps
$username = $_SESSION["access-user"];





if (!is_null(getTeacherByUsernameTeacher($username))) {
    

    $teacher = getTeacherByUsernameTeacher($username);
    if ($teacher["roleNameEng"] == "teacher") {
        header('Location: ' . './teacher/home.php');
        exit();
    }

} else {
    echo "เข้า";
    header('Location: ' . './student/home.php');
    exit();
}










?>