<?php

function getCourseById($id)
{

    require("connection_connect.php");

    $sql = "SELECT * FROM course WHERE courseId = " . $id;

    $result = $conn->query($sql);
    $course = $result->fetch_assoc();


    require("connection_close.php");


    return $course;

}

function getCoursePresentByDepartmentId($departmentId)
{

    require("connection_connect.php");

    $sql = "SELECT DISTINCT nameCourseUse,couseStartYear
    FROM course
    WHERE departmentId = departmentId AND couseStartYear IN (SELECT MAX(couseStartYear) AS couseStartYear FROM course WHERE departmentId = departmentId)";

    $result = $conn->query($sql);
    $course = $result->fetch_assoc();


    require("connection_close.php");


    return $course;

}

function getCourseByCourseName($courseName)
{

    require("connection_connect.php");

    $sql = "SELECT DISTINCT nameCourseUse,couseStartYear
    FROM course
    WHERE nameCourseUse = '$courseName'";

    $result = $conn->query($sql);
    $course = $result->fetch_assoc();


    require("connection_close.php");


    return $course;

}

function getSubjectGroupCreditCourseByNameCourseAndPlanAndStudyYearAndPart($name, $plan, $year, $part)
{

    require("connection_connect.php");

    $sql = "SELECT subjectGroup,SUM(credit) as credit
    FROM courselist
    WHERE courseName = '" . $name . "' AND coursePlan = '" . $plan . "' AND studyYear <= " . $year . " AND term <= " . $part . "
    GROUP BY subjectGroup";

    $result = $conn->query($sql);

    $courseCredits = [];

    while ($my_row = $result->fetch_assoc()) {
        $courseCredits[] = $my_row;
    }


    require("connection_close.php");


    return $courseCredits;

}

function getSubjectGroupCreditTermOneCourseByNameCourseAndPlanAndStudyYearAndPart($name, $plan, $year, $part)
{

    require("connection_connect.php");

    $yearX = $year - 1;
    $termX = $part + 1;

    $sql = "SELECT subjectGroup,SUM(credit) AS credit
    FROM (SELECT studyYear,term,subjectGroup,SUM(credit) AS credit
    FROM courselist
    WHERE courseName = '" . $name . "' AND coursePlan = '" . $plan . "' AND studyYear <= " . $yearX . " AND term <= " . $termX . "
    GROUP BY studyYear,term,subjectGroup
    UNION
    SELECT studyYear,term,subjectGroup,SUM(credit) as credit
    FROM courselist
    WHERE courseName = '" . $name . "' AND coursePlan = '" . $plan . "' AND studyYear = " . $year . " AND term = " . $part . "
    GROUP BY subjectGroup,studyYear,term) AS A
    GROUP BY subjectGroup;";

    $result = $conn->query($sql);

    $courseCredits = [];

    while ($my_row = $result->fetch_assoc()) {
        $courseCredits[] = $my_row;
    }


    require("connection_close.php");


    return $courseCredits;

}


function getCourseByDepartmentId($departmentId)
{

    require("connection_connect.php");



    $sql = "SELECT courseId,nameCourseTh,nameCourseUse,planCourse
    FROM course
    WHERE departmentId = " . $departmentId;

    $result = $conn->query($sql);

    $course = [];

    while ($my_row = $result->fetch_assoc()) {
        $course[] = $my_row;
    }


    require("connection_close.php");


    return $course;

}


function getGeneretionInCourseByCouseName($courseName)
{
    require("connection_connect.php");

    $generetions = [];

    $sql = "SELECT studyGeneretion
    FROM student NATURAL JOIN fact_student NATURAL JOIN course
    WHERE nameCourseUse = '$courseName'
    GROUP BY studyGeneretion
    ORDER BY studyGeneretion";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $generetions[] = $my_row;
    }

    require("connection_close.php");

    return $generetions;
}

function getCountStudentInCourseByCouseName($courseName)
{
    require("connection_connect.php");

    $sql = "SELECT COUNT(studentId) AS studentCount
    FROM student NATURAL JOIN fact_student NATURAL JOIN course
    WHERE nameCourseUse = '$courseName'";

    $result = $conn->query($sql);
    $countStudentInCourse = $result->fetch_assoc();

    require("connection_close.php");

    return $countStudentInCourse;
}

function getCourseNameByDepartmentId($departmentId)
{

    require("connection_connect.php");



    $sql = "SELECT DISTINCT nameCourseUse
    FROM course
    WHERE departmentId = $departmentId
    ORDER BY couseStartYear DESC"

    ;

    $result = $conn->query($sql);

    $course = [];

    while ($my_row = $result->fetch_assoc()) {
        $course[] = $my_row;
    }


    require("connection_close.php");


    return $course;

}

?>