<?php

function getCountStudentInFaculty()
{
    require("connection_connect.php");



    $sql = "SELECT COUNT(studentId) AS countStudent FROM student;";

    $result = $conn->query($sql);

    $countStudent = $result->fetch_assoc();


    require("connection_close.php");

    return $countStudent;


}

function geStudyGeneretionStudentInFaculty()
{
    require("connection_connect.php");


    $generetions = [];

    $sql = "SELECT DISTINCT studyGeneretion FROM fact_student ORDER BY studyGeneretion;";

    $result = $conn->query($sql);



    while ($my_row = $result->fetch_assoc()) {
        $generetions[] = $my_row;
    }


    require("connection_close.php");

    return $generetions;


}

function getCountStudentGradeRangeInFacultyฺSemesterYearBySemesterYear($semesterYear)
{

    require("connection_connect.php");

    $sql = "SELECT COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE semesterYear <= $semesterYear     
    GROUP BY studentId);";

    $result = $conn->query($sql);
    $countRangeGrade = $result->fetch_assoc();


    require("connection_close.php");

    return $countRangeGrade;

}

function getCountStudentGradeRangeInFacultyฺSemesterYearBySemesterYearAndGeneretion($semesterYear,$generetion)
{

    require("connection_connect.php");

    $sql = "SELECT COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE semesterYear <= $semesterYear AND studyGeneretion = $generetion     
    GROUP BY studentId);";

    $result = $conn->query($sql);
    $countRangeGrade = $result->fetch_assoc();


    require("connection_close.php");

    return $countRangeGrade;

}

function getCountStudentPlanStatusInFacultyBySemesterYear($semesterYear)
{

    require("connection_connect.php");

    $sql = "SELECT COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN studentId END) AS grad
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE semesterYear <= $semesterYear 
    GROUP BY studentId);";

    $result = $conn->query($sql);
    $countPlanStatus = $result->fetch_assoc();


    require("connection_close.php");

    return $countPlanStatus;

}

function getCountStudentPlanStatusInFacultyBySemesterYearAndGeneretion($semesterYear,$generetion)
{

    require("connection_connect.php");

    $sql = "SELECT COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN studentId END) AS grad
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId);";

    $result = $conn->query($sql);
    $countPlanStatus = $result->fetch_assoc();


    require("connection_close.php");

    return $countPlanStatus;

}

function getGradeRangeSortByDepartmentInFacultyBySemesterYear($semesterYear)
{

    require("connection_connect.php");

    $gradeRangeSortByDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN department
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student 
    WHERE semesterYear <= $semesterYear
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {


        $gradeRangeSortByDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $gradeRangeSortByDepartments;

}

function getGradeRangeSortByDepartmentInFacultyBySemesterYearAnd($semesterYear,$generetion)
{

    require("connection_connect.php");

    $gradeRangeSortByDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN department
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student 
    WHERE semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {


        $gradeRangeSortByDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $gradeRangeSortByDepartments;

}

function getplanStatusSortByDepartmentInFacultyBySemesterYear($semesterYear)
{

    require("connection_connect.php");

    $planStatusSortByDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN studentId END) AS grad
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN department
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE semesterYear <= $semesterYear 
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {


        $planStatusSortByDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $planStatusSortByDepartments;

}

function getplanStatusSortByDepartmentInFacultyBySemesterYearAndGeneretion($semesterYear,$generetion)
{

    require("connection_connect.php");

    $planStatusSortByDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN studentId END) AS grad
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN department
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {


        $planStatusSortByDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $planStatusSortByDepartments;

}


function getMaxMinAVGGPAXSortByDepartmentInFacultyBySemesterYear($semesterYear)
{


    require("connection_connect.php");

    $departmentMMAs = [];

    $sql = "SELECT departmentName,departmentInitials,ROUND(MAX(gpaAll),2) AS maxGPAX,ROUND(AVG(gpaAll),2) AS avgGPAX,ROUND(MIN(gpaAll),2) AS minGPAX
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN department
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE semesterYear <= $semesterYear 
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {


        $departmentMMAs[] = $my_row;
    }


    require("connection_close.php");

    return $departmentMMAs;

}

function getMaxMinAVGGPAXSortByDepartmentInFacultyBySemesterYearAndRound($semesterYear,$round)
{


    require("connection_connect.php");

    $departmentMMAs = [];

    $sql = "SELECT departmentName,departmentInitials,ROUND(MAX(gpaAll),2) AS maxGPAX,ROUND(AVG(gpaAll),2) AS avgGPAX,ROUND(MIN(gpaAll),2) AS minGPAX
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN department
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE semesterYear <= $semesterYear AND tcasRound = $round
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {


        $departmentMMAs[] = $my_row;
    }


    require("connection_close.php");

    return $departmentMMAs;

}

function getMaxMinAVGGPAXSortByDepartmentInFacultyBySemesterYearAndGeneretion($semesterYear,$generetion)
{


    require("connection_connect.php");

    $departmentMMAs = [];

    $sql = "SELECT departmentName,departmentInitials,ROUND(MAX(gpaAll),2) AS maxGPAX,ROUND(AVG(gpaAll),2) AS avgGPAX,ROUND(MIN(gpaAll),2) AS minGPAX
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN department
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {


        $departmentMMAs[] = $my_row;
    }


    require("connection_close.php");

    return $departmentMMAs;

}

function getMaxMinAVGGPAXSortByDepartmentInFacultyBySemesterYearAndGeneretionAndRound($semesterYear,$generetion,$round)
{


    require("connection_connect.php");

    $departmentMMAs = [];

    $sql = "SELECT departmentName,departmentInitials,ROUND(MAX(gpaAll),2) AS maxGPAX,ROUND(AVG(gpaAll),2) AS avgGPAX,ROUND(MIN(gpaAll),2) AS minGPAX
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN department
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE semesterYear <= $semesterYear AND studyGeneretion = $generetion AND tcasRound = $round
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {


        $departmentMMAs[] = $my_row;
    }


    require("connection_close.php");

    return $departmentMMAs;

}


function getCountStudentRemainingGradeRangeInFacultyBySemesterYear($semesterYear)
{

    require("connection_connect.php");

    $sql = "SELECT COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE planStatus != 'จบการศึกษา' AND planStatus != 'พ้นสภาพนิสิต' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student    
    WHERE studyYear > 4 AND semesterYear <= $semesterYear 
    GROUP BY studentId);";

    $result = $conn->query($sql);
    $countRangeGrade = $result->fetch_assoc();


    require("connection_close.php");

    return $countRangeGrade;

}

function getCountStudentRemainingGradeRangeInFacultyBySemesterYearAndGeneretion($semesterYear,$generetion)
{

    require("connection_connect.php");

    $sql = "SELECT COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE planStatus != 'จบการศึกษา' AND planStatus != 'พ้นสภาพนิสิต' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student    
    WHERE studyYear > 4 AND semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId);";

    $result = $conn->query($sql);
    $countRangeGrade = $result->fetch_assoc();


    require("connection_close.php");

    return $countRangeGrade;

}

function getCountStudentRemainingPlanStatusInFacultyBySemesterYear($semesterYear)
{

    require("connection_connect.php");

    $sql = "SELECT COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN studentId END) AS grad
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE planStatus != 'จบการศึกษา' AND planStatus != 'พ้นสภาพนิสิต' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student 
    WHERE studyYear > 4 AND semesterYear <= $semesterYear
    GROUP BY studentId);";

    $result = $conn->query($sql);
    $countPlanStatus = $result->fetch_assoc();


    require("connection_close.php");

    return $countPlanStatus;

}

function getCountStudentRemainingPlanStatusInFacultyBySemesterYearAndGeneretion($semesterYear,$generetion)
{

    require("connection_connect.php");

    $sql = "SELECT COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN studentId END) AS grad
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE planStatus != 'จบการศึกษา' AND planStatus != 'พ้นสภาพนิสิต' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student 
    WHERE studyYear > 4 AND semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId);";

    $result = $conn->query($sql);
    $countPlanStatus = $result->fetch_assoc();


    require("connection_close.php");

    return $countPlanStatus;

}

function getGradeRangeRemainingSortByDepartmentInFacultyBySemesterYear($semesterYear)
{

    require("connection_connect.php");

    $gradeRangeSortByDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN department
    WHERE planStatus != 'จบการศึกษา' AND planStatus != 'พ้นสภาพนิสิต' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student 
    WHERE studyYear > 4 AND semesterYear <= $semesterYear
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {


        $gradeRangeSortByDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $gradeRangeSortByDepartments;

}

function getGradeRangeRemainingSortByDepartmentInFacultyBySemesterYearAndGeneretion($semesterYear,$generetion)
{

    require("connection_connect.php");

    $gradeRangeSortByDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN department
    WHERE planStatus != 'จบการศึกษา' AND planStatus != 'พ้นสภาพนิสิต' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student 
    WHERE studyYear > 4 AND semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {


        $gradeRangeSortByDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $gradeRangeSortByDepartments;

}

function getplanStatusRemainingSortByDepartmentInFacultyBySemesterYear($semesterYear)
{

    require("connection_connect.php");

    $planStatusSortByDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN studentId END) AS grad
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN department
    WHERE planStatus != 'จบการศึกษา' AND planStatus != 'พ้นสภาพนิสิต' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student 
    WHERE studyYear > 4 AND semesterYear <= $semesterYear
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {


        $planStatusSortByDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $planStatusSortByDepartments;

}

function getplanStatusRemainingSortByDepartmentInFacultyBySemesterYearAndGeneretion($semesterYear,$generetion)
{

    require("connection_connect.php");

    $planStatusSortByDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN studentId END) AS grad
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN department
    WHERE planStatus != 'จบการศึกษา' AND planStatus != 'พ้นสภาพนิสิต' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student 
    WHERE studyYear > 4 AND semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {


        $planStatusSortByDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $planStatusSortByDepartments;

}

function getCountStudentTcasSortByDepartmentBySemesterYear($semesterYear)
{

    require("connection_connect.php");

    $countStudentSortByDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(CASE WHEN tcasName = 'TCAS1' THEN studentId END) AS TCAS1,COUNT(CASE WHEN tcasName = 'TCAS2' THEN studentId END) AS TCAS2,COUNT(CASE WHEN tcasName = 'TCAS3' THEN studentId END) AS TCAS3,COUNT(CASE WHEN tcasName = 'TCAS4' THEN studentId END) AS TCAS4
    FROM department NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE tcasYear <= $semesterYear
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $countStudentSortByDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $countStudentSortByDepartments;

}

function getCountStudentTcasSortByDepartmentBySemesterYearAndRound($semesterYear,$round)
{

    require("connection_connect.php");

    $countStudentSortByDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(CASE WHEN tcasName = 'TCAS1' THEN studentId END) AS TCAS1,COUNT(CASE WHEN tcasName = 'TCAS2' THEN studentId END) AS TCAS2,COUNT(CASE WHEN tcasName = 'TCAS3' THEN studentId END) AS TCAS3,COUNT(CASE WHEN tcasName = 'TCAS4' THEN studentId END) AS TCAS4
    FROM department NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE tcasYear <= $semesterYear AND tcasRound = $round
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $countStudentSortByDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $countStudentSortByDepartments;

}

function getCountStudentTcasSortByDepartmentBySemesterYearAndGeneretion($semesterYear,$generetion)
{

    require("connection_connect.php");

    $countStudentSortByDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(CASE WHEN tcasName = 'TCAS1' THEN studentId END) AS TCAS1,COUNT(CASE WHEN tcasName = 'TCAS2' THEN studentId END) AS TCAS2,COUNT(CASE WHEN tcasName = 'TCAS3' THEN studentId END) AS TCAS3,COUNT(CASE WHEN tcasName = 'TCAS4' THEN studentId END) AS TCAS4
    FROM department NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE tcasYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $countStudentSortByDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $countStudentSortByDepartments;

}
function getCountStudentTcasSortByDepartmentBySemesterYearAndGeneretionAndRound($semesterYear,$generetion,$round)
{

    require("connection_connect.php");

    $countStudentSortByDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(CASE WHEN tcasName = 'TCAS1' THEN studentId END) AS TCAS1,COUNT(CASE WHEN tcasName = 'TCAS2' THEN studentId END) AS TCAS2,COUNT(CASE WHEN tcasName = 'TCAS3' THEN studentId END) AS TCAS3,COUNT(CASE WHEN tcasName = 'TCAS4' THEN studentId END) AS TCAS4
    FROM department NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE tcasYear <= $semesterYear AND studyGeneretion = $generetion AND tcasRound = $round
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $countStudentSortByDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $countStudentSortByDepartments;

}

function getPercentageStudySortByDepartmentInFacultyBySemesterYear($semesterYear)
{

    require("connection_connect.php");

    $percentageDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(studentId) AS entry,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END) AS study,ROUND(COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END)*100/COUNT(studentId),2) AS percentage
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student NATURAL JOIN studentstatus  NATURAL JOIN department
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE semesterYear <= $semesterYear
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $percentageDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $percentageDepartments;

}

function getPercentageStudySortByDepartmentInFacultyBySemesterYearAndRound($semesterYear,$round)
{

    require("connection_connect.php");

    $percentageDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(studentId) AS entry,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END) AS study,ROUND(COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END)*100/COUNT(studentId),2) AS percentage
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student NATURAL JOIN studentstatus  NATURAL JOIN department
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE semesterYear <= $semesterYear AND tcasRound = $round
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $percentageDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $percentageDepartments;

}

function getPercentageStudySortByDepartmentInFacultyBySemesterYearAndGeneretion($semesterYear,$generetion)
{

    require("connection_connect.php");

    $percentageDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(studentId) AS entry,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END) AS study,ROUND(COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END)*100/COUNT(studentId),2) AS percentage
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student NATURAL JOIN studentstatus  NATURAL JOIN department
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $percentageDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $percentageDepartments;

}

function getPercentageStudySortByDepartmentInFacultyBySemesterYearAndRoundAndGeneretion($semesterYear,$round,$generetion)
{

    require("connection_connect.php");

    $percentageDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(studentId) AS entry,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END) AS study,ROUND(COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END)*100/COUNT(studentId),2) AS percentage
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student NATURAL JOIN studentstatus  NATURAL JOIN department
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE semesterYear <= $semesterYear AND tcasRound = $round AND studyGeneretion = $generetion
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $percentageDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $percentageDepartments;

}

function getPercentageStudyAndRetireSortByDepartmentInFacultyBySemesterYear($semesterYear)
{

    require("connection_connect.php");

    $percentageRetireDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END) AS study,COUNT(CASE WHEN status = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,ROUND(COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END)*100/COUNT(studentId),2) AS percentage
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student NATURAL JOIN studentstatus  NATURAL JOIN department
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE semesterYear <= $semesterYear
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $percentageRetireDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $percentageRetireDepartments;

}

function getPercentageStudyAndRetireSortByDepartmentInFacultyBySemesterYearAndRound($semesterYear,$round)
{

    require("connection_connect.php");

    $percentageRetireDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END) AS study,COUNT(CASE WHEN status = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,ROUND(COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END)*100/COUNT(studentId),2) AS percentage
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student NATURAL JOIN studentstatus  NATURAL JOIN department
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE semesterYear <= $semesterYear AND tcasRound = $round
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $percentageRetireDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $percentageRetireDepartments;

}

function getPercentageStudyAndRetireSortByDepartmentInFacultyBySemesterYearAndGeneretion($semesterYear,$generetion)
{

    require("connection_connect.php");

    $percentageRetireDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END) AS study,COUNT(CASE WHEN status = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,ROUND(COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END)*100/COUNT(studentId),2) AS percentage
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student NATURAL JOIN studentstatus  NATURAL JOIN department
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE semesterYear <= $semesterYear AND studyGeneretion = $generetion
    GROUP BY studentId)
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $percentageRetireDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $percentageRetireDepartments;

}

function getPercentageStudyAndRetireSortByDepartmentInFacultyBySemesterYearAndRoundAndGeneretion($semesterYear,$round,$generetion)
{

    require("connection_connect.php");

    $percentageRetireDepartments = [];

    $sql = "SELECT departmentName,departmentInitials,COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END) AS study,COUNT(CASE WHEN status = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,ROUND(COUNT(CASE WHEN status = 'กำลังศึกษา' THEN studentId END)*100/COUNT(studentId),2) AS percentage
    FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN tcas NATURAL JOIN fact_student NATURAL JOIN studentstatus  NATURAL JOIN department
    WHERE termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN tcas
    WHERE semesterYear <= $semesterYear AND tcasRound = $round AND studyGeneretion = $generetion
    GROUP BY studentId) 
    GROUP BY departmentId;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $percentageRetireDepartments[] = $my_row;
    }


    require("connection_close.php");

    return $percentageRetireDepartments;

}



?>