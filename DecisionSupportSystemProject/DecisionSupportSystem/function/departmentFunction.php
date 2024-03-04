<?php


function getDepartmentById($departmentId)
{

    require("connection_connect.php");

    $sql = "SELECT * FROM department WHERE departmentId = " . $departmentId;
    $result = $conn->query($sql);
    $department = $result->fetch_assoc();

    require("connection_close.php");

    return $department;

}

function getAllDepartment()
{

    require("connection_connect.php");

    $sql = "SELECT * FROM department";
    $result = $conn->query($sql);
    $departments = [];

    while ($my_row = $result->fetch_assoc()) {
        $departments[] = $my_row;
    }

    require("connection_close.php");

    return $departments;

}

function getCountStudentInDepartmentByDepartmentId($departmentId)
{
    require("connection_connect.php");

    $sql = "SELECT COUNT(studentId) AS studentCount
    FROM fact_student
    WHERE departmentId = $departmentId";

    $result = $conn->query($sql);
    $countStudentInCourse = $result->fetch_assoc();

    require("connection_close.php");

    return $countStudentInCourse;
}

function getGeneretionInCourseByDepartmentId($departmentId)
{
    require("connection_connect.php");

    $generetions = [];

    $sql = "SELECT studyGeneretion
    FROM student NATURAL JOIN fact_student
    WHERE departmentId = $departmentId
    GROUP BY studyGeneretion
    ORDER BY studyGeneretion";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $generetions[] = $my_row;
    }

    require("connection_close.php");

    return $generetions;
}

function getStudentByDepartmentId($departmentId)
{

    require("connection_connect.php");


    $students = [];

    $sql = "SELECT studentId FROM fact_student WHERE departmentId = " . $departmentId;

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {



        $student = getStudentByStudentId($my_row["studentId"]);
        //echo $my_row["studentId"];

        $students[] = $student;



    }



    require("connection_close.php");

    return $students;

}


?>