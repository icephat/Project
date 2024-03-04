<?php
include_once '../function/semesterFunction.php';
include_once '../function/studentFunction.php';
include_once '../function/courseFunction.php';

function getCountStudentStatusSortByGeneretionByNameCourseAndSemesterYear($course, $year)
{

    require("connection_connect.php");

    $generetionStatus = [];


    $sql = "SELECT studyGeneretion,COUNT(studentId) AS firstEntry,COUNT(CASE WHEN status = 'พ้นสภาพนิสิต' THEN status END) AS retire,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN status END) AS study,COUNT(CASE WHEN status = 'จบการศึกษา' THEN status END) AS grad
    FROM studentstatus NATURAL JOIN  fact_term_summary NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$course' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId WHERE nameCourseUse = '$course'  AND semesterYear <= $year
    GROUP BY studentId)
    GROUP BY studyGeneretion;";


    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $generetionStatus[] = $my_row;
    }



    require("connection_close.php");

    return $generetionStatus;

}

function getCountStudentStatusSortByYearByNameCourseAndSemesterYear($course, $year)
{

    require("connection_connect.php");

    $yearStatus = [];



    $sql = "SELECT semesterYear,COUNT(CASE WHEN tcasYear = semesterYear THEN studentId END) AS firstEntry,COUNT(CASE WHEN status = 'พ้นสภาพนิสิต' THEN status END) AS retire,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN status END) AS study,COUNT(CASE WHEN status = 'จบการศึกษา' THEN status END) AS grad
    FROM studentstatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$course' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId WHERE nameCourseUse = '$course' AND semesterYear <= $year
    GROUP BY studentId,studyYear)
    GROUP BY semesterYear;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $yearStatus[] = $my_row;
    }



    require("connection_close.php");

    return $yearStatus;

}

function getCountStudentStatusTatleSortByGeneretionAndYearStudyByNameCourseIdAndStatusAndSemesterYear($course, $status, $year)
{

    require("connection_connect.php");

    $yearStatus = [];

    //$course = getCoursePresentByDepartmentId($departmentId)["nameCourseUse"];

    $sql = "SELECT studyGeneretion,COUNT(CASE WHEN semesterYear = couseStartYear THEN studentId END) AS one,COUNT(CASE WHEN semesterYear = couseStartYear+1 THEN studentId END) AS two,COUNT(CASE WHEN semesterYear = couseStartYear+2 THEN studentId END) AS three,COUNT(CASE WHEN semesterYear = couseStartYear+3 THEN studentId END) AS four,COUNT(CASE WHEN semesterYear = couseStartYear+4 THEN studentId END) AS five,COUNT(CASE WHEN semesterYear = couseStartYear+5 THEN studentId END) AS six,COUNT(CASE WHEN semesterYear = couseStartYear+6 THEN studentId END) AS seven,COUNT(CASE WHEN semesterYear = couseStartYear+7 THEN studentId END) AS eight,COUNT(CASE WHEN semesterYear = couseStartYear+8 THEN studentId END) AS nine,COUNT(CASE WHEN semesterYear = couseStartYear+9 THEN studentId END) AS ten,COUNT(CASE WHEN semesterYear = couseStartYear+10 THEN studentId END) AS eleven,COUNT(CASE WHEN semesterYear = couseStartYear+11 THEN studentId END) AS twelve
    FROM studentstatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$course' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId 
    WHERE nameCourseUse = '$course' AND semesterYear <= $year
    GROUP BY studentId,studyYear) AND status = '$status'
    GROUP BY studyGeneretion";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $yearStatus[] = $my_row;
    }



    require("connection_close.php");

    return $yearStatus;

}

function getCountStudentGradeRangeByCourseNameAndSemesterYear($courseName, $semesterYear)
{

    require("connection_connect.php");

    $sql = "SELECT COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$courseName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId WHERE nameCourseUse = '$courseName' AND semesterYear <= $semesterYear   GROUP BY studentId);";

    $result = $conn->query($sql);
    $countRangeGrade = $result->fetch_assoc();

    $countRangeGrade["blues"] = geStudentListInGradeRangeByCourseNameAndSemesterYearAndGradeRange($courseName, $semesterYear, "blue");
    $countRangeGrade["greens"] = geStudentListInGradeRangeByCourseNameAndSemesterYearAndGradeRange($courseName, $semesterYear, "green");
    $countRangeGrade["oranges"] = geStudentListInGradeRangeByCourseNameAndSemesterYearAndGradeRange($courseName, $semesterYear, "orange");
    $countRangeGrade["reds"] = geStudentListInGradeRangeByCourseNameAndSemesterYearAndGradeRange($courseName, $semesterYear, "red");


    require("connection_close.php");

    return $countRangeGrade;

}

function geStudentListInGradeRangeByCourseNameAndSemesterYearAndGradeRange($courseName, $semesterYear, $gradeRange)
{

    require("connection_connect.php");

    $students = [];
    $sql = "SELECT studentId,fisrtNameTh,lastNameTh,round(gpaTerm,2) AS gpaTerm,round(gpaAll,2) AS gpaAll
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN student INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$courseName' AND gpaStatusName = '$gradeRange' AND termSummaryId IN (SELECT MAX(termSummaryId) AS  termSummaryId FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId WHERE nameCourseUse = '$courseName' AND semesterYear <= $semesterYear
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {

        $students[] = $my_row;
    }


    require("connection_close.php");

    return $students;

}

function getCountStudentPlanStatusByCourseNameAndSemesterYear($courseName, $semesterYear)
{

    require("connection_connect.php");

    $sql = "SELECT COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN studentId END) AS grad
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$courseName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId WHERE nameCourseUse = '$courseName' AND semesterYear <= $semesterYear   GROUP BY studentId);";

    $result = $conn->query($sql);
    $countPlanStatus = $result->fetch_assoc();

    $countPlanStatus["plans"] = geStudentListInPlanStatusByCourseNameAndSemesterYearAndGradeRange($courseName, $semesterYear, "ตามแผน");
    $countPlanStatus["notPlans"] = geStudentListInPlanStatusByCourseNameAndSemesterYearAndGradeRange($courseName, $semesterYear, "ไม่ตามแผน");
    $countPlanStatus["retires"] = geStudentListInPlanStatusByCourseNameAndSemesterYearAndGradeRange($courseName, $semesterYear, "พ้นสภาพนิสิต");
    $countPlanStatus["grads"] = geStudentListInPlanStatusByCourseNameAndSemesterYearAndGradeRange($courseName, $semesterYear, "จบการศึกษา");


    require("connection_close.php");

    return $countPlanStatus;

}

function geStudentListInPlanStatusByCourseNameAndSemesterYearAndGradeRange($courseName, $semesterYear, $planStatus)
{

    require("connection_connect.php");

    $students = [];
    $sql = "SELECT studentId,fisrtNameTh,lastNameTh,round(gpaTerm,2) AS gpaTerm,round(gpaAll,2) AS gpaAll
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN student  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$courseName' AND planStatus = '$planStatus' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId WHERE nameCourseUse = '$courseName' AND semesterYear <= $semesterYear   
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $students[] = $my_row;
    }


    require("connection_close.php");

    return $students;

}

function getCountStudentGradeRangeSortByGeneretionByCourseNameAndSemesterYearAndStudyYear($courseName, $semesterYear, $studyYear)
{

    require("connection_connect.php");

    $studentGeneretionGradeRanges = [];

    $sql = "SELECT studyGeneretion, COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$courseName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId WHERE nameCourseUse = '$courseName' AND semesterYear <= $semesterYear AND studyYear = $studyYear GROUP BY studentId)
    GROUP BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $studentGeneretionGradeRanges[] = $my_row;
    }


    require("connection_close.php");

    return $studentGeneretionGradeRanges;

}

function getCountStudentGradeRangeSortByGeneretionByCourseNameAndSemesterYearAndStudyYearAndStatus($courseName, $semesterYear, $status)
{

    require("connection_connect.php");

    $studentGeneretionGradeRangeByStatus = [];

    $sql = "SELECT studyGeneretion, COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN  fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$courseName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM studentStatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId WHERE nameCourseUse = '$courseName' AND semesterYear <= $semesterYear AND status = '$status' GROUP BY studentId)
    GROUP BY studyGeneretion
    ORDER BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $studentGeneretionGradeRangeByStatus[] = $my_row;
    }


    require("connection_close.php");

    return $studentGeneretionGradeRangeByStatus;

}

function getCountStudentStatusSortByGeneretionByCourseNameAndSemesterYearAndStudyYear($courseName, $semesterYear)
{

    require("connection_connect.php");

    $studentStatusGeneretions = [];

    $sql = "SELECT studyGeneretion,COUNT(CASE WHEN tcasYear = 2500+studyGeneretion THEN studentId END) AS firstEntry,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN status END) AS study,COUNT(CASE WHEN status = 'จบการศึกษา' THEN status END) AS grad
    FROM studentStatus NATURAL JOIN  fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$courseName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM studentStatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId WHERE nameCourseUse = '$courseName' AND semesterYear <= $semesterYear 
    GROUP BY studentId)
    ORDER BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $studentStatusGeneretions[] = $my_row;
    }


    require("connection_close.php");

    return $studentStatusGeneretions;

}

function getCountStudentPlanStatusSortBySemesterYearByCourseNameAndSemesterYear($courseName, $semesterYear)
{

    require("connection_connect.php");

    $countPlanStatusSortBySemesterYear = [];

    $sql = "SELECT semesterYear, COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$courseName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId WHERE nameCourseUse = '$courseName' AND semesterYear <= $semesterYear   GROUP BY studentId,semesterYear)
    GROUP BY semesterYear
    ORDER BY semesterYear;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $countPlanStatusSortBySemesterYear[] = $my_row;
    }


    require("connection_close.php");

    return $countPlanStatusSortBySemesterYear;

}

function getCountStudentPlanStatusSortByStudyGeneretionByCourseNameAndSemesterYear($courseName, $semesterYear)
{

    require("connection_connect.php");

    $countPlanStatusSortByGeneretion = [];

    $sql = "SELECT studyGeneretion, COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$courseName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId WHERE nameCourseUse = '$courseName' AND semesterYear <= $semesterYear   GROUP BY studentId)
    GROUP BY studyGeneretion
    ORDER BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $countPlanStatusSortByGeneretion[] = $my_row;
    }


    require("connection_close.php");

    return $countPlanStatusSortByGeneretion;

}

function getCountStudentTcasSortByStudyGeneretionByCourseName($courseName)
{

    require("connection_connect.php");

    $countStudentSortByGeneretion = [];

    $sql = "SELECT studyGeneretion,COUNT(CASE WHEN tcasName = 'TCAS1' THEN studentId END) AS TCAS1,COUNT(CASE WHEN tcasName = 'TCAS2' THEN studentId END) AS TCAS2,COUNT(CASE WHEN tcasName = 'TCAS3' THEN studentId END) AS TCAS3,COUNT(CASE WHEN tcasName = 'TCAS4' THEN studentId END) AS TCAS4
    FROM course NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE nameCourseUse = '$courseName'
    GROUP BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $countStudentSortByGeneretion[] = $my_row;
    }


    require("connection_close.php");

    return $countStudentSortByGeneretion;

}

function getCountStudentTcasSortByStudyGeneretionByCourseNameAndTcasRound($courseName,$round)
{

    require("connection_connect.php");

    $countStudentSortByGeneretion = [];

    $sql = "SELECT studyGeneretion,COUNT(studentId) AS tcasCount
    FROM course NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE nameCourseUse = '$courseName' AND tcasRound = $round
    GROUP BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $countStudentSortByGeneretion[] = $my_row;
    }


    require("connection_close.php");

    return $countStudentSortByGeneretion;

}

function getMaxMinAvgGPAXByCourseName($courseName)
{

    require("connection_connect.php");

    $gpaxMMA = [];

    $sql = "SELECT studyGeneretion,ROUND(MAX(gpaAll),2) AS maxGPAX,ROUND(AVG(gpaAll),2) AS avgGPAX,ROUND(MIN(gpaAll),2) AS minGPAX
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$courseName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId WHERE nameCourseUse = '$courseName' GROUP BY studentId)
    GROUP BY studyGeneretion
    ORDER BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $gpaxMMA[] = $my_row;
    }


    require("connection_close.php");

    return $gpaxMMA;

}

function getMaxMinAvgGPAXByCourseNameAndRound($courseName,$round)
{

    require("connection_connect.php");

    $gpaxMMA = [];

    $sql = "SELECT studyGeneretion,ROUND(MAX(gpaAll),2) AS maxGPAX,ROUND(AVG(gpaAll),2) AS avgGPAX,ROUND(MIN(gpaAll),2) AS minGPAX
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$courseName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN tcas   INNER JOIN course ON fact_student.courseId = course.courseId 
    WHERE nameCourseUse = '$courseName' AND tcasRound = $round
    GROUP BY studentId)
    GROUP BY studyGeneretion
    ORDER BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $gpaxMMA[] = $my_row;
    }


    require("connection_close.php");

    return $gpaxMMA;

}

function getPercentageStudySortByGeneretionByCourseName($courseName)
{

    require("connection_connect.php");

    $percentageGeneretions = [];

    $sql = "SELECT studyGeneretion,COUNT(studentId) AS entry,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END) AS study,ROUND(COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END)*100/COUNT(studentId),2) AS percentage
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student NATURAL JOIN studentstatus  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$courseName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId WHERE nameCourseUse = '$courseName' GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $percentageGeneretions[] = $my_row;
    }


    require("connection_close.php");

    return $percentageGeneretions;

}

function getPercentageStudySortByGeneretionByCourseNameAndRound($courseName,$round)
{

    require("connection_connect.php");

    $percentageGeneretions = [];

    $sql = "SELECT studyGeneretion,COUNT(studentId) AS entry,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END) AS study,ROUND(COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END)*100/COUNT(studentId),2) AS percentage
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student NATURAL JOIN studentstatus  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$courseName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN tcas INNER JOIN course ON fact_student.courseId = course.courseId 
    WHERE nameCourseUse = '$courseName' AND tcasRound = $round
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $percentageGeneretions[] = $my_row;
    }


    require("connection_close.php");

    return $percentageGeneretions;

}

function getPercentageStudyAndRetireSortByGeneretionByCourseName($courseName)
{

    require("connection_connect.php");

    $percentageGeneretions = [];

    $sql = "SELECT studyGeneretion,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END) AS study,COUNT(CASE WHEN status = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,ROUND(COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END)*100/COUNT(studentId),2) AS percentage
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student NATURAL JOIN studentstatus  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$courseName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  INNER JOIN course ON fact_student.courseId = course.courseId WHERE nameCourseUse = '$courseName' GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $percentageGeneretions[] = $my_row;
    }


    require("connection_close.php");

    return $percentageGeneretions;

}

function getPercentageStudyAndRetireSortByGeneretionByCourseNameAndRound($courseName,$round)
{

    require("connection_connect.php");

    $percentageGeneretions = [];

    $sql = "SELECT studyGeneretion,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END) AS study,COUNT(CASE WHEN status = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,ROUND(COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END)*100/COUNT(studentId),2) AS percentage
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student NATURAL JOIN studentstatus  INNER JOIN course ON fact_student.courseId = course.courseId
    WHERE nameCourseUse = '$courseName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN tcas  INNER JOIN course ON fact_student.courseId = course.courseId 
    WHERE nameCourseUse = '$courseName' AND tcasRound = $round
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $percentageGeneretions[] = $my_row;
    }


    require("connection_close.php");

    return $percentageGeneretions;

}

function getCountStudentStatusSortByGeneretionByDepartmentIdAndSemesterYear($departmentId,$semesterYear)
{

    require("connection_connect.php");

    $generetionStatus = [];


    $sql = "SELECT studyGeneretion,COUNT(studentId) AS firstEntry,COUNT(CASE WHEN status = 'พ้นสภาพนิสิต' THEN status END) AS retire,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN status END) AS study,COUNT(CASE WHEN status = 'จบการศึกษา' THEN status END) AS grad
    FROM studentstatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student WHERE departmentId = $departmentId AND semesterYear <= $semesterYear  GROUP BY studentId)
    GROUP BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $generetionStatus[] = $my_row;
    }



    require("connection_close.php");

    return $generetionStatus;

}

function getCountStudentStatusSortByYearByDepartmrntIdAndSemesterYear($departmentId,$semesterYear)
{

    require("connection_connect.php");

    $yearStatus = [];



    $sql = "SELECT semesterYear,COUNT(CASE WHEN tcasYear = semesterYear THEN studentId END) AS firstEntry,COUNT(CASE WHEN status = 'พ้นสภาพนิสิต' THEN status END) AS retire,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN status END) AS study,COUNT(CASE WHEN status = 'จบการศึกษา' THEN status END) AS grad
    FROM studentstatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  WHERE departmentId = $departmentId AND semesterYear <= $semesterYear  GROUP BY studentId,studyYear)
    GROUP BY semesterYear;";


    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $yearStatus[] = $my_row;
    }



    require("connection_close.php");

    return $yearStatus;

}

function getCountStudentStatusTatleSortByGeneretionAndYearStudyByDepartmentIdIdAndStatusAndSemesterYear($departmentId, $status, $semesterYear)
{

    require("connection_connect.php");

    $yearStatus = [];

    //$course = getCoursePresentByDepartmentId($departmentId)["nameCourseUse"];

    $sql = "SELECT studyGeneretion,COUNT(CASE WHEN semesterYear = $semesterYear-4 THEN studentId END) AS one,COUNT(CASE WHEN semesterYear = $semesterYear-3 THEN studentId END) AS two,COUNT(CASE WHEN semesterYear = $semesterYear-2 THEN studentId END) AS three,COUNT(CASE WHEN semesterYear = $semesterYear-1 THEN studentId END) AS four,COUNT(CASE WHEN semesterYear = $semesterYear THEN studentId END) AS five
    FROM studentstatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND semesterYear <= $semesterYear
    GROUP BY studentId,studyYear) AND status = '$status' AND studyGeneretion >= $semesterYear-2504 AND studyGeneretion <= $semesterYear-2500
    GROUP BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $yearStatus[] = $my_row;
    }



    require("connection_close.php");

    return $yearStatus;

}


function getCountStudentGradeRangeByDepartmrntIdAndSemesterYear($departmentId, $semesterYear)
{

    require("connection_connect.php");

    $sql = "SELECT COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student   WHERE departmentId = $departmentId AND semesterYear <= $semesterYear   GROUP BY studentId);";

    $result = $conn->query($sql);
    $countRangeGrade = $result->fetch_assoc();
    $countRangeGrade["blues"] = geStudentListInGradeRangeByDepartmentIdAndSemesterYearAndGradeRange($departmentId, $semesterYear, "blue");
    $countRangeGrade["greens"] = geStudentListInGradeRangeByDepartmentIdAndSemesterYearAndGradeRange($departmentId, $semesterYear, "green");
    $countRangeGrade["oranges"] = geStudentListInGradeRangeByDepartmentIdAndSemesterYearAndGradeRange($departmentId, $semesterYear, "orange");
    $countRangeGrade["reds"] = geStudentListInGradeRangeByDepartmentIdAndSemesterYearAndGradeRange($departmentId, $semesterYear, "red");




    require("connection_close.php");

    return $countRangeGrade;

}

function getCountStudentGradeRangeByDepartmrntIdAndSemesterYearAndGeneretion($departmentId, $semesterYear,$generetion)
{

    require("connection_connect.php");

    $sql = "SELECT COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND semesterYear <= $semesterYear AND studyGeneretion = $generetion   GROUP BY studentId);";

    $result = $conn->query($sql);
    $countRangeGrade = $result->fetch_assoc();
    $countRangeGrade["blues"] = geStudentListInGradeRangeByDepartmentIdAndSemesterYearAndGradeRangeAndGeneretion($departmentId, $semesterYear, "blue",$generetion);
    $countRangeGrade["greens"] = geStudentListInGradeRangeByDepartmentIdAndSemesterYearAndGradeRangeAndGeneretion($departmentId, $semesterYear, "green",$generetion);
    $countRangeGrade["oranges"] = geStudentListInGradeRangeByDepartmentIdAndSemesterYearAndGradeRangeAndGeneretion($departmentId, $semesterYear, "orange",$generetion);
    $countRangeGrade["reds"] = geStudentListInGradeRangeByDepartmentIdAndSemesterYearAndGradeRangeAndGeneretion($departmentId, $semesterYear, "red",$generetion);




    require("connection_close.php");

    return $countRangeGrade;

}

function geStudentListInGradeRangeByDepartmentIdAndSemesterYearAndGradeRange($departmentId, $semesterYear, $gradeRange)
{

    require("connection_connect.php");

    $students = [];
    $sql = "SELECT studentId,fisrtNameTh,lastNameTh,round(gpaTerm,2) AS gpaTerm,round(gpaAll,2) AS gpaAll
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN student
    WHERE departmentId = $departmentId AND gpaStatusName = '$gradeRange' AND termSummaryId IN (SELECT MAX(termSummaryId) AS  termSummaryId FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student 
    WHERE departmentId = $departmentId AND semesterYear <= $semesterYear
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {

        $students[] = $my_row;
    }


    require("connection_close.php");

    return $students;

}

function geStudentListInGradeRangeByDepartmentIdAndSemesterYearAndGradeRangeAndGeneretion($departmentId, $semesterYear, $gradeRange,$generetion)
{

    require("connection_connect.php");

    $students = [];
    $sql = "SELECT studentId,fisrtNameTh,lastNameTh,round(gpaTerm,2) AS gpaTerm,round(gpaAll,2) AS gpaAll
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN student
    WHERE departmentId = $departmentId AND gpaStatusName = '$gradeRange' AND termSummaryId IN (SELECT MAX(termSummaryId) AS  termSummaryId FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student 
    WHERE departmentId = $departmentId AND semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {

        $students[] = $my_row;
    }


    require("connection_close.php");

    return $students;

}

function getCountStudentPlanStatusByDepartmrntIdAndSemesterYear($departmentId, $semesterYear)
{

    require("connection_connect.php");

    $sql = "SELECT COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN studentId END) AS grad
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student 
    WHERE departmentId = $departmentId AND semesterYear <= $semesterYear   GROUP BY studentId);";

    $result = $conn->query($sql);
    $countPlanStatus = $result->fetch_assoc();

    $countPlanStatus["plans"] = geStudentListInPlanStatusByDepartmentIdAndSemesterYearAndGradeRange($departmentId, $semesterYear, "ตามแผน");
    $countPlanStatus["notPlans"] = geStudentListInPlanStatusByDepartmentIdAndSemesterYearAndGradeRange($departmentId, $semesterYear, "ไม่ตามแผน");
    $countPlanStatus["retires"] = geStudentListInPlanStatusByDepartmentIdAndSemesterYearAndGradeRange($departmentId, $semesterYear, "พ้นสภาพนิสิต");
    $countPlanStatus["grads"] = geStudentListInPlanStatusByDepartmentIdAndSemesterYearAndGradeRange($departmentId, $semesterYear, "จบการศึกษา");




    require("connection_close.php");

    return $countPlanStatus;

}

function getCountStudentPlanStatusByDepartmrntIdAndSemesterYearAndGeneretion($departmentId, $semesterYear,$generetion)
{

    require("connection_connect.php");

    $sql = "SELECT COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN studentId END) AS grad
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student 
    WHERE departmentId = $departmentId AND semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId);";

    $result = $conn->query($sql);
    $countPlanStatus = $result->fetch_assoc();

    $countPlanStatus["plans"] = geStudentListInPlanStatusByDepartmentIdAndSemesterYearAndGradeRangeAndGeneretion($departmentId, $semesterYear, "ตามแผน",$generetion);
    $countPlanStatus["notPlans"] = geStudentListInPlanStatusByDepartmentIdAndSemesterYearAndGradeRangeAndGeneretion($departmentId, $semesterYear, "ไม่ตามแผน",$generetion);
    $countPlanStatus["retires"] = geStudentListInPlanStatusByDepartmentIdAndSemesterYearAndGradeRangeAndGeneretion($departmentId, $semesterYear, "พ้นสภาพนิสิต",$generetion);
    $countPlanStatus["grads"] = geStudentListInPlanStatusByDepartmentIdAndSemesterYearAndGradeRangeAndGeneretion($departmentId, $semesterYear, "จบการศึกษา",$generetion);




    require("connection_close.php");

    return $countPlanStatus;

}

function geStudentListInPlanStatusByDepartmentIdAndSemesterYearAndGradeRange($departmentId, $semesterYear, $planStatus)
{

    require("connection_connect.php");

    $students = [];
    $sql = "SELECT studentId,fisrtNameTh,lastNameTh,round(gpaTerm,2) AS gpaTerm,round(gpaAll,2) AS gpaAll
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN student
    WHERE departmentId = $departmentId AND planStatus = '$planStatus' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND semesterYear <= $semesterYear 
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $students[] = $my_row;
    }


    require("connection_close.php");

    return $students;

}

function geStudentListInPlanStatusByDepartmentIdAndSemesterYearAndGradeRangeAndGeneretion($departmentId, $semesterYear, $planStatus,$generetion)
{

    require("connection_connect.php");

    $students = [];
    $sql = "SELECT studentId,fisrtNameTh,lastNameTh,round(gpaTerm,2) AS gpaTerm,round(gpaAll,2) AS gpaAll
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN student
    WHERE departmentId = $departmentId AND planStatus = '$planStatus' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $students[] = $my_row;
    }


    require("connection_close.php");

    return $students;

}

function getCountStudentGradeRangeSortByGeneretionByDepartmentIdAndSemesterYearAndStudyYear($departmentId, $semesterYear, $studyYear)
{

    require("connection_connect.php");

    $studentGeneretionGradeRanges = [];

    $sql = "SELECT studyGeneretion, COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND semesterYear <= $semesterYear AND studyYear = $studyYear GROUP BY studentId)
    GROUP BY studyGeneretion
    ORDER BY studyGeneretion";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $studentGeneretionGradeRanges[] = $my_row;
    }


    require("connection_close.php");

    return $studentGeneretionGradeRanges;

}

function getCountStudentGradeRangeSortByGeneretionByDepartmentIdAndSemesterYearAndStudyYearAndStatus($departmentId, $semesterYear, $status)
{

    require("connection_connect.php");

    $studentGeneretionGradeRangeByStatus = [];

    $sql = "SELECT studyGeneretion, COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN  fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM studentStatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  
    WHERE departmentId = $departmentId AND semesterYear <= $semesterYear AND status = '$status' GROUP BY studentId)
    GROUP BY studyGeneretion
    ORDER BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $studentGeneretionGradeRangeByStatus[] = $my_row;
    }


    require("connection_close.php");

    return $studentGeneretionGradeRangeByStatus;

}

function getCountStudentStatusSortByGeneretionByDepartmentIdAndSemesterYearAndStudyYear($departmentId, $semesterYear)
{

    require("connection_connect.php");

    $studentStatusGeneretions = [];

    $sql = "SELECT studyGeneretion,COUNT(CASE WHEN tcasYear = 2500+studyGeneretion THEN studentId END) AS firstEntry,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN status END) AS study,COUNT(CASE WHEN status = 'จบการศึกษา' THEN status END) AS grad
    FROM studentStatus NATURAL JOIN  fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM studentStatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student WHERE departmentId = $departmentId AND semesterYear <= $semesterYear 
    GROUP BY studentId)
    ORDER BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $studentStatusGeneretions[] = $my_row;
    }


    require("connection_close.php");

    return $studentStatusGeneretions;

}

function getCountStudentPlanStatusSortBySemesterYearByDepartmentIdAndSemesterYear($departmentId, $semesterYear)
{

    require("connection_connect.php");

    $countPlanStatusSortBySemesterYear = [];

    $sql = "SELECT semesterYear, COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  WHERE departmentId = $departmentId AND semesterYear <= $semesterYear   GROUP BY studentId,semesterYear)
    GROUP BY semesterYear
    ORDER BY semesterYear;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $countPlanStatusSortBySemesterYear[] = $my_row;
    }


    require("connection_close.php");

    return $countPlanStatusSortBySemesterYear;

}

function getCountStudentPlanStatusSortByStudyGeneretionByDepartmentAndSemesterYear($departmentId, $semesterYear)
{

    require("connection_connect.php");

    $countPlanStatusSortByGeneretion = [];

    $sql = "SELECT studyGeneretion, COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student   WHERE departmentId = $departmentId AND semesterYear <= $semesterYear   GROUP BY studentId)
    GROUP BY studyGeneretion
    ORDER BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $countPlanStatusSortByGeneretion[] = $my_row;
    }


    require("connection_close.php");

    return $countPlanStatusSortByGeneretion;

}

function getCountStudentTcasSortByStudyGeneretionByDepartmentId($departmentId)
{

    require("connection_connect.php");

    $countStudentSortByGeneretion = [];

    $sql = "SELECT studyGeneretion,COUNT(CASE WHEN tcasName = 'TCAS1' THEN studentId END) AS TCAS1,COUNT(CASE WHEN tcasName = 'TCAS2' THEN studentId END) AS TCAS2,COUNT(CASE WHEN tcasName = 'TCAS3' THEN studentId END) AS TCAS3,COUNT(CASE WHEN tcasName = 'TCAS4' THEN studentId END) AS TCAS4
    FROM department NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE departmentId = $departmentId
    GROUP BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $countStudentSortByGeneretion[] = $my_row;
    }


    require("connection_close.php");

    return $countStudentSortByGeneretion;

}

function getCountStudentTcasSortByStudyGeneretionByDepartmentIdAndRound($departmentId,$round)
{

    require("connection_connect.php");

    $countStudentSortByGeneretion = [];

    $sql = "SELECT studyGeneretion,COUNT(studentId) AS tcasCount
    FROM department NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE departmentId = $departmentId AND tcasRound = $round
    GROUP BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $countStudentSortByGeneretion[] = $my_row;
    }


    require("connection_close.php");

    return $countStudentSortByGeneretion;

}

function getMaxMinAvgGPAXByDepartmentId($departmentId)
{

    require("connection_connect.php");

    $gpaxMMA = [];

    $sql = "SELECT studyGeneretion,ROUND(MAX(gpaAll),2) AS maxGPAX,ROUND(AVG(gpaAll),2) AS avgGPAX,ROUND(MIN(gpaAll),2) AS minGPAX
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student WHERE departmentId = $departmentId GROUP BY studentId)
    GROUP BY studyGeneretion
    ORDER BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $gpaxMMA[] = $my_row;
    }


    require("connection_close.php");

    return $gpaxMMA;

}

function getMaxMinAvgGPAXByDepartmentIdAndRound($departmentId,$round)
{

    require("connection_connect.php");

    $gpaxMMA = [];

    $sql = "SELECT studyGeneretion,ROUND(MAX(gpaAll),2) AS maxGPAX,ROUND(AVG(gpaAll),2) AS avgGPAX,ROUND(MIN(gpaAll),2) AS minGPAX
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE departmentId = $departmentId AND tcasRound = $round
    GROUP BY studentId)
    GROUP BY studyGeneretion
    ORDER BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $gpaxMMA[] = $my_row;
    }


    require("connection_close.php");

    return $gpaxMMA;

}

function getPercentageStudySortByGeneretionByDepartmentId($departmentId)
{

    require("connection_connect.php");

    $percentageGeneretions = [];

    $sql = "SELECT studyGeneretion,COUNT(studentId) AS entry,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END) AS study,ROUND(COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END)*100/COUNT(studentId),2) AS percentage
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student NATURAL JOIN studentstatus  
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student 
    WHERE departmentId = $departmentId
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $percentageGeneretions[] = $my_row;
    }


    require("connection_close.php");

    return $percentageGeneretions;

}

function getPercentageStudySortByGeneretionByDepartmentIdAndRound($departmentId,$round)
{

    require("connection_connect.php");

    $percentageGeneretions = [];

    $sql = "SELECT studyGeneretion,COUNT(studentId) AS entry,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END) AS study,ROUND(COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END)*100/COUNT(studentId),2) AS percentage
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student NATURAL JOIN studentstatus  
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE departmentId = $departmentId AND tcasRound = $round
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $percentageGeneretions[] = $my_row;
    }


    require("connection_close.php");

    return $percentageGeneretions;

}

function getPercentageStudyAndRetireSortByGeneretionByDepartmentId($departmentId)
{

    require("connection_connect.php");

    $percentageGeneretions = [];

    $sql = "SELECT studyGeneretion,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END) AS study,COUNT(CASE WHEN status = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,ROUND(COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END)*100/COUNT(studentId),2) AS percentage
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student NATURAL JOIN studentstatus  
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student   
    WHERE departmentId = $departmentId 
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $percentageGeneretions[] = $my_row;
    }


    require("connection_close.php");

    return $percentageGeneretions;

}

function getPercentageStudyAndRetireSortByGeneretionByDepartmentIdAndRound($departmentId,$round)
{

    require("connection_connect.php");

    $percentageGeneretions = [];

    $sql = "SELECT studyGeneretion,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END) AS study,COUNT(CASE WHEN status = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,ROUND(COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END)*100/COUNT(studentId),2) AS percentage
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student NATURAL JOIN studentstatus  
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student   NATURAL JOIN tcas
    WHERE departmentId = $departmentId  AND tcasRound = $round
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $percentageGeneretions[] = $my_row;
    }


    require("connection_close.php");

    return $percentageGeneretions;

}

function getGradeRangeSortByAdviserByDepartmentIdAndSemesterYear($departmentId,$semesterYear)
{

    require("connection_connect.php");

    $gradeRangeSortByAdvisers = [];

    $sql = "SELECT teacherId,titleTecherTh,fisrtNameTh,lastNameTh,COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE departmentId = $departmentId AND semesterYear <= $semesterYear   GROUP BY studentId)
    GROUP BY teacherId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {

        $my_row["blues"] = getListStudentByTeacherIdAndGPAStatusNameAndSemesterYear($my_row["teacherId"], "blue",$semesterYear);
        $my_row["greens"] = getListStudentByTeacherIdAndGPAStatusNameAndSemesterYear($my_row["teacherId"], "green",$semesterYear);
        $my_row["oranges"] = getListStudentByTeacherIdAndGPAStatusNameAndSemesterYear($my_row["teacherId"], "orange",$semesterYear);
        $my_row["reds"] = getListStudentByTeacherIdAndGPAStatusNameAndSemesterYear($my_row["teacherId"], "red",$semesterYear);
        $gradeRangeSortByAdvisers[] = $my_row;
    }


    require("connection_close.php");

    return $gradeRangeSortByAdvisers;

}

function getGradeRangeSortByAdviserByDepartmentIdAndSemesterYearAndGeneretion($departmentId,$semesterYear,$generetion)
{

    require("connection_connect.php");

    $gradeRangeSortByAdvisers = [];

    $sql = "SELECT teacherId,titleTecherTh,fisrtNameTh,lastNameTh,COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE departmentId = $departmentId AND semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId)
    GROUP BY teacherId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {

        $my_row["blues"] = getListStudentByTeacherIdAndGPAStatusNameAndSemesterYearAndGeneretion($my_row["teacherId"], "blue",$semesterYear,$generetion);
        $my_row["greens"] = getListStudentByTeacherIdAndGPAStatusNameAndSemesterYearAndGeneretion($my_row["teacherId"], "green",$semesterYear,$generetion);
        $my_row["oranges"] = getListStudentByTeacherIdAndGPAStatusNameAndSemesterYearAndGeneretion($my_row["teacherId"], "orange",$semesterYear,$generetion);
        $my_row["reds"] = getListStudentByTeacherIdAndGPAStatusNameAndSemesterYearAndGeneretion($my_row["teacherId"], "red",$semesterYear,$generetion);
        $gradeRangeSortByAdvisers[] = $my_row;
    }


    require("connection_close.php");

    return $gradeRangeSortByAdvisers;

}

function getListStudentByTeacherIdAndGPAStatusNameAndSemesterYear($teacherId, $gpaStatusName,$semesterYear)
{

    require("connection_connect.php");

    $students = [];

    $sql = "SELECT *
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher INNER JOIN student ON fact_student.studentId = student.studentId
    WHERE teacherId = $teacherId AND gpaStatusName = '$gpaStatusName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE teacherId = $teacherId AND semesterYear <= $semesterYear
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $students[] = $my_row;
    }


    require("connection_close.php");

    return $students;

}

function getListStudentByTeacherIdAndGPAStatusNameAndSemesterYearAndGeneretion($teacherId, $gpaStatusName,$semesterYear,$generetion)
{

    require("connection_connect.php");

    $students = [];

    $sql = "SELECT *
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher INNER JOIN student ON fact_student.studentId = student.studentId
    WHERE teacherId = $teacherId AND gpaStatusName = '$gpaStatusName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE teacherId = $teacherId AND semesterYear <= $semesterYear  AND studyGeneretion = $generetion
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $students[] = $my_row;
    }


    require("connection_close.php");

    return $students;

}

function getPlanStatusSortByAdviserByDepartmentIdAndSemesterYear($departmentId,$semesterYear)
{

    require("connection_connect.php");

    $planStatusSortByAdvisers = [];

    $sql = "SELECT teacherId,titleTecherTh,fisrtNameTh,lastNameTh,COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire ,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN studentId END) AS grad
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE departmentId = $departmentId AND semesterYear <= $semesterYear
    GROUP BY studentId)
    GROUP BY teacherId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {

        $my_row["plans"] = getListStudentByTeacherIdAndPlanStatusAndSemesterYear($my_row["teacherId"], "ตามแผน",$semesterYear);
        $my_row["notPlans"] = getListStudentByTeacherIdAndPlanStatusAndSemesterYear($my_row["teacherId"], "ไม่ตามแผน",$semesterYear);
        $my_row["retires"] = getListStudentByTeacherIdAndPlanStatusAndSemesterYear($my_row["teacherId"], "พ้นสภาพนิสิต",$semesterYear);
        $my_row["grads"] = getListStudentByTeacherIdAndPlanStatusAndSemesterYear($my_row["teacherId"], "จบการศึกษา",$semesterYear);
        $planStatusSortByAdvisers[] = $my_row;
    }


    require("connection_close.php");

    return $planStatusSortByAdvisers;

}

function getPlanStatusSortByAdviserByDepartmentIdAndSemesterYearAndGeneretion($departmentId,$semesterYear,$generetion)
{

    require("connection_connect.php");

    $planStatusSortByAdvisers = [];

    $sql = "SELECT teacherId,titleTecherTh,fisrtNameTh,lastNameTh,COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire ,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN studentId END) AS grad
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE departmentId = $departmentId AND semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId)
    GROUP BY teacherId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {

        $my_row["plans"] = getListStudentByTeacherIdAndPlanStatusAndSemesterYearAndGeneretion($my_row["teacherId"], "ตามแผน",$semesterYear,$generetion);
        $my_row["notPlans"] = getListStudentByTeacherIdAndPlanStatusAndSemesterYearAndGeneretion($my_row["teacherId"], "ไม่ตามแผน",$semesterYear,$generetion);
        $my_row["retires"] = getListStudentByTeacherIdAndPlanStatusAndSemesterYearAndGeneretion($my_row["teacherId"], "พ้นสภาพนิสิต",$semesterYear,$generetion);
        $my_row["grads"] = getListStudentByTeacherIdAndPlanStatusAndSemesterYearAndGeneretion($my_row["teacherId"], "จบการศึกษา",$semesterYear,$generetion);
        $planStatusSortByAdvisers[] = $my_row;
    }


    require("connection_close.php");

    return $planStatusSortByAdvisers;

}

function getListStudentByTeacherIdAndPlanStatusAndSemesterYear($teacherId, $planStatus,$semesterYear)
{

    require("connection_connect.php");

    $students = [];

    $sql = "SELECT *
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher INNER JOIN student ON fact_student.studentId = student.studentId
    WHERE teacherId = $teacherId AND planStatus = '$planStatus' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE teacherId = $teacherId AND semesterYear <= $semesterYear
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $students[] = $my_row;
    }


    require("connection_close.php");

    return $students;

}

function getListStudentByTeacherIdAndPlanStatusAndSemesterYearAndGeneretion($teacherId, $planStatus,$semesterYear,$generetion)
{

    require("connection_connect.php");

    $students = [];

    $sql = "SELECT *
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher INNER JOIN student ON fact_student.studentId = student.studentId
    WHERE teacherId = $teacherId AND planStatus = '$planStatus' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE teacherId = $teacherId AND semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $students[] = $my_row;
    }


    require("connection_close.php");

    return $students;

}

function getMaxMinAVGGPAXSortByAdviserByDepartmentIdAndSemesterYear($departmentId,$semesterYear)
{


    require("connection_connect.php");

    $advisorMMAs = [];

    $sql = "SELECT teacherId,titleTecherTh,fisrtNameTh,lastNameTh,ROUND(MAX(gpaAll),2) AS maxGPAX,ROUND(AVG(gpaAll),2) AS avgGPAX,ROUND(MIN(gpaAll),2) AS minGPAX
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE departmentId = $departmentId AND semesterYear <= $semesterYear GROUP BY studentId)
    GROUP BY teacherId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {


        $advisorMMAs[] = $my_row;
    }


    require("connection_close.php");

    return $advisorMMAs;

}

function getMaxMinAVGGPAXSortByAdviserByDepartmentIdAndSemesterYearAndGeneretion($departmentId,$semesterYear,$generetion)
{


    require("connection_connect.php");

    $advisorMMAs = [];

    $sql = "SELECT teacherId,titleTecherTh,fisrtNameTh,lastNameTh,ROUND(MAX(gpaAll),2) AS maxGPAX,ROUND(AVG(gpaAll),2) AS avgGPAX,ROUND(MIN(gpaAll),2) AS minGPAX
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE departmentId = $departmentId AND semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId)
    GROUP BY teacherId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {


        $advisorMMAs[] = $my_row;
    }


    require("connection_close.php");

    return $advisorMMAs;

}


function getRemainingGradeRangeSortByAdviserByDepartmentIdAnd($departmentId,$semesterYear)
{

    require("connection_connect.php");

    $gradeRangeSortByAdvisers = [];

    $sql = "SELECT teacherId,titleTecherTh,fisrtNameTh,lastNameTh,COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE departmentId = $departmentId And studyYear > 4 AND semesterYear <= $semesterYear GROUP BY studentId)
    GROUP BY teacherId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {

        $my_row["blues"] = getRemainingListStudentByTeacherIdAndGPAStatusNameAndSemesterYear($my_row["teacherId"], "blue",$semesterYear);
        $my_row["greens"] = getRemainingListStudentByTeacherIdAndGPAStatusNameAndSemesterYear($my_row["teacherId"], "green",$semesterYear);
        $my_row["oranges"] = getRemainingListStudentByTeacherIdAndGPAStatusNameAndSemesterYear($my_row["teacherId"], "orange",$semesterYear);
        $my_row["reds"] = getRemainingListStudentByTeacherIdAndGPAStatusNameAndSemesterYear($my_row["teacherId"], "red",$semesterYear);
        $gradeRangeSortByAdvisers[] = $my_row;
    }


    require("connection_close.php");

    return $gradeRangeSortByAdvisers;

}

function getRemainingGradeRangeSortByAdviserByDepartmentIdAndGeneretion($departmentId,$semesterYear,$generetion)
{

    require("connection_connect.php");

    $gradeRangeSortByAdvisers = [];

    $sql = "SELECT teacherId,titleTecherTh,fisrtNameTh,lastNameTh,COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE departmentId = $departmentId And studyYear > 4 AND semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId)
    GROUP BY teacherId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {

        $my_row["blues"] = getRemainingListStudentByTeacherIdAndGPAStatusNameAndSemesterYearAndGeneretion($my_row["teacherId"], "blue",$semesterYear,$generetion);
        $my_row["greens"] = getRemainingListStudentByTeacherIdAndGPAStatusNameAndSemesterYearAndGeneretion($my_row["teacherId"], "green",$semesterYear,$generetion);
        $my_row["oranges"] = getRemainingListStudentByTeacherIdAndGPAStatusNameAndSemesterYearAndGeneretion($my_row["teacherId"], "orange",$semesterYear,$generetion);
        $my_row["reds"] = getRemainingListStudentByTeacherIdAndGPAStatusNameAndSemesterYearAndGeneretion($my_row["teacherId"], "red",$semesterYear,$generetion);
        $gradeRangeSortByAdvisers[] = $my_row;
    }


    require("connection_close.php");

    return $gradeRangeSortByAdvisers;

}

function getRemainingListStudentByTeacherIdAndGPAStatusNameAndSemesterYear($teacherId, $gpaStatusName,$semesterYear)
{

    require("connection_connect.php");

    $students = [];

    $sql = "SELECT *
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher INNER JOIN student ON fact_student.studentId = student.studentId
    WHERE teacherId = $teacherId AND gpaStatusName = '$gpaStatusName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE teacherId = $teacherId  And studyYear > 4 AND semesterYear <= $semesterYear  GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $students[] = $my_row;
    }


    require("connection_close.php");

    return $students;

}

function getRemainingListStudentByTeacherIdAndGPAStatusNameAndSemesterYearAndGeneretion($teacherId, $gpaStatusName,$semesterYear,$generetion)
{

    require("connection_connect.php");

    $students = [];

    $sql = "SELECT *
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher INNER JOIN student ON fact_student.studentId = student.studentId
    WHERE teacherId = $teacherId AND gpaStatusName = '$gpaStatusName' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE teacherId = $teacherId  And studyYear > 4 AND semesterYear <= $semesterYear  AND studyGeneretion = $generetion
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $students[] = $my_row;
    }


    require("connection_close.php");

    return $students;

}

function getRemainingPlanStatusSortByAdviserByDepartmentIdAndSemesterYear($departmentId,$semesterYear)
{

    require("connection_connect.php");

    $planStatusSortByAdvisers = [];

    $sql = "SELECT teacherId,titleTecherTh,fisrtNameTh,lastNameTh,COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN studentId END) AS grad
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE departmentId = $departmentId And studyYear > 4 AND semesterYear <= $semesterYear GROUP BY studentId)
    GROUP BY teacherId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {

        $my_row["plans"] = getListRemainingStudentByTeacherIdAndPlanStatusAndSemesterYear($my_row["teacherId"], "ตามแผน",$semesterYear);
        $my_row["notPlans"] = getListRemainingStudentByTeacherIdAndPlanStatusAndSemesterYear($my_row["teacherId"], "ไม่ตามแผน",$semesterYear);
        $my_row["retires"] = getListRemainingStudentByTeacherIdAndPlanStatusAndSemesterYear($my_row["teacherId"], "พ้นสภาพนิสิต",$semesterYear);
        $my_row["grads"] = getListRemainingStudentByTeacherIdAndPlanStatusAndSemesterYear($my_row["teacherId"], "จบการศึกษา",$semesterYear);
        $planStatusSortByAdvisers[] = $my_row;
    }


    require("connection_close.php");

    return $planStatusSortByAdvisers;

}

function getRemainingPlanStatusSortByAdviserByDepartmentIdAndSemesterYearAndGeneretion($departmentId,$semesterYear,$generetion)
{

    require("connection_connect.php");

    $planStatusSortByAdvisers = [];

    $sql = "SELECT teacherId,titleTecherTh,fisrtNameTh,lastNameTh,COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN studentId END) AS grad
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher
    WHERE departmentId = $departmentId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE departmentId = $departmentId And studyYear > 4 AND semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId)
    GROUP BY teacherId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {

        $my_row["plans"] = getListRemainingStudentByTeacherIdAndPlanStatusAndSemesterYearAndGeneretion($my_row["teacherId"], "ตามแผน",$semesterYear,$generetion);
        $my_row["notPlans"] = getListRemainingStudentByTeacherIdAndPlanStatusAndSemesterYearAndGeneretion($my_row["teacherId"], "ไม่ตามแผน",$semesterYear,$generetion);
        $my_row["retires"] = getListRemainingStudentByTeacherIdAndPlanStatusAndSemesterYearAndGeneretion($my_row["teacherId"], "พ้นสภาพนิสิต",$semesterYear,$generetion);
        $my_row["grads"] = getListRemainingStudentByTeacherIdAndPlanStatusAndSemesterYearAndGeneretion($my_row["teacherId"], "จบการศึกษา",$semesterYear,$generetion);
        $planStatusSortByAdvisers[] = $my_row;
    }


    require("connection_close.php");

    return $planStatusSortByAdvisers;

}

function getListRemainingStudentByTeacherIdAndPlanStatusAndSemesterYear($teacherId, $planStatus,$semesterYear)
{

    require("connection_connect.php");

    $students = [];

    $sql = "SELECT *
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher INNER JOIN student ON fact_student.studentId = student.studentId
    WHERE teacherId = $teacherId AND planStatus = '$planStatus' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE teacherId = $teacherId  And studyYear > 4 AND semesterYear <= $semesterYear GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $students[] = $my_row;
    }


    require("connection_close.php");

    return $students;

}

function getListRemainingStudentByTeacherIdAndPlanStatusAndSemesterYearAndGeneretion($teacherId, $planStatus,$semesterYear,$generetion)
{

    require("connection_connect.php");

    $students = [];

    $sql = "SELECT *
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN teacher INNER JOIN student ON fact_student.studentId = student.studentId
    WHERE teacherId = $teacherId AND planStatus = '$planStatus' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN teacher
    WHERE teacherId = $teacherId  And studyYear > 4 AND semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $students[] = $my_row;
    }


    require("connection_close.php");

    return $students;

}







?>