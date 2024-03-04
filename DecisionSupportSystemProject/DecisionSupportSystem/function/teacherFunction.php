<?php

include_once '../function/semesterFunction.php';
include_once '../function/studentFunction.php';

function getTeacherById($teacherId)
{

    require("connection_connect.php");

    $sql = "SELECT * FROM teacher WHERE teacherId = " . $teacherId;
    $result = $conn->query($sql);
    $teacher = $result->fetch_assoc();

    require("connection_close.php");

    return $teacher;

}

function getTeacherByUsernameTeacher($usernameTeacher)
{

    require("connection_connect.php");
    echo $usernameTeacher . "<br>";

    $sql = "SELECT * FROM department NATURAL JOIN teacher NATURAL JOIN teacherrole NATURAL JOIN role WHERE username = '" . $usernameTeacher . "'";
    $result = $conn->query($sql);
    $teacher = $result->fetch_assoc();

    require("connection_close.php");


    return $teacher;

}

function getCountStudentByPlaningByTeacherId($teacherId)
{


    require("connection_connect.php");

    $sql = "SELECT COUNT(CASE WHEN planStatus = 'ตามแผน' THEN studentId END) AS plan,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN studentId END) AS notPlan,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN studentId END) AS retire,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN studentId END) AS grad
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE teacherId = $teacherId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student 
    WHERE teacherId = $teacherId 
    GROUP BY studentId);";

    $result = $conn->query($sql);
    $countPlanStatus = $result->fetch_assoc();

    $countPlanStatus["plans"] = geStudentListInPlanStatusByTeacherIdAnPlanStatus($teacherId, "ตามแผน");
    $countPlanStatus["notPlans"] = geStudentListInPlanStatusByTeacherIdAnPlanStatus($teacherId, "ไม่ตามแผน");
    $countPlanStatus["retires"] = geStudentListInPlanStatusByTeacherIdAnPlanStatus($teacherId, "พ้นสภาพนิสิต");
    $countPlanStatus["grads"] = geStudentListInPlanStatusByTeacherIdAnPlanStatus($teacherId, "จบการศึกษา");




    require("connection_close.php");

    return $countPlanStatus;

}

function geStudentListInPlanStatusByTeacherIdAnPlanStatus($teacherId, $planStatus)
{

    require("connection_connect.php");

    $students = [];
    $sql = "SELECT studentId,fisrtNameTh,lastNameTh,round(gpaTerm,2) AS gpaTerm,round(gpaAll,2) AS gpaAll
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student  NATURAL JOIN student
    WHERE teacherId = $teacherId AND planStatus = '$planStatus' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE teacherId = $teacherId
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $students[] = $my_row;
    }


    require("connection_close.php");

    return $students;

}

function getCountStudentGPAXStatusByTeacherId($teacherId)
{


    require("connection_connect.php");


    $semester = getSemesterPresent();


    $sql = "SELECT COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE teacherId = $teacherId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE teacherId = $teacherId GROUP BY studentId);";

    $result = $conn->query($sql);

    $countGPAXs = $result->fetch_assoc();
    $countGPAXs["blues"] = getstudentListByTeacherIdAndStatus($teacherId,"blue");
    $countGPAXs["greens"] = getstudentListByTeacherIdAndStatus($teacherId,"green");
    $countGPAXs["oranges"] = getstudentListByTeacherIdAndStatus($teacherId,"orange");
    $countGPAXs["reds"] = getstudentListByTeacherIdAndStatus($teacherId,"red");


    require("connection_close.php");

    

    return $countGPAXs;
    ;

}

function getstudentListByTeacherIdAndStatus($teacherId,$gradeRange)
{


    require("connection_connect.php");


    $semester = getSemesterPresent();

    $countGPAXs = [];


    $sql = "SELECT studentId,fisrtNameTh,lastNameTh,round(gpaTerm,2) AS gpaTerm,round(gpaAll,2) AS gpaAll
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student NATURAL JOIN student
    WHERE teacherId = $teacherId AND gpaStatusName = '$gradeRange' AND termSummaryId IN (SELECT MAX(termSummaryId) AS  termSummaryId FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student 
    WHERE teacherId = $teacherId
    GROUP BY studentId);";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $countGPAXs[] = $my_row;
    }


    require("connection_close.php");

    

    return $countGPAXs;
    ;

}

function getCountStudentInTeacherByStudentStatusByTeacherIdAndStatus($teacherId, $status)
{

    require("connection_connect.php");

    $sql = "SELECT COUNT(studentId) AS count
        FROM fact_student NATURAL JOIN studentstatus
        WHERE teacherId = " . $teacherId . " AND status = '" . $status . "'
        GROUP BY studentStatusId";

    $result = $conn->query($sql);
    $studentStatus = $result->fetch_assoc();


    if (is_null($studentStatus)) {
        $s["count"] = 0;
        $studentStatus = $s;
    }




    require("connection_close.php");

    return $studentStatus;

}

function getCountStudentInTeacherByPlaningByTeacherIdAndPlaningStatus($teacherId, $status)
{

    require("connection_connect.php");

    $semester = getSemesterPresent();

    $sql = "SELECT planStatus,COUNT(studentId) AS count
        FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN fact_student NATURAL JOIN gpaxstatus
        WHERE teacherId = " . $teacherId . " AND semesterYear = " . $semester["semesterYear"] . " AND semesterPart = '" . $semester["semesterPart"] . "' AND studentStatusId = 1 AND planStatus = '" . $status . "';";

    $result = $conn->query($sql);
    $plan = $result->fetch_assoc();

    if (is_null($plan)) {
        $s["count"] = 0;
        $plan = $s;
    }

    require("connection_close.php");

    return $plan;

}

function getCountStudentPlanStatusBystudyGeneretionByTeacherId($teacherId)
{

    require("connection_connect.php");

    $semester = getSemesterPresent();

    $sql = "SELECT studyGeneretion,COUNT(CASE WHEN planStatus = 'ตามแผน' THEN planStatus END) AS planCount,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN planStatus END) AS notPlanCount,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN planStatus END) AS retire,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN planStatus END) AS grad
    FROM fact_term_summary NATURAL JOIN student NATURAL JOIN fact_student
    WHERE teacherId = $teacherId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary 
    WHERE teacherId = $teacherId
    GROUP BY studentId)
    GROUP BY studyGeneretion";

    $generetions = [];
    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {

        $my_row["studentPlans"] = getListStudentPlanStatusBystudyGeneretionByTeacherIdAndGeneretionAndPlanStatus($teacherId, $my_row["studyGeneretion"], 'ตามแผน');
        $my_row["studentNotPlans"] = getListStudentPlanStatusBystudyGeneretionByTeacherIdAndGeneretionAndPlanStatus($teacherId, $my_row["studyGeneretion"], 'ไม่ตามแผน');
        $my_row["studentRetires"] = getListStudentPlanStatusBystudyGeneretionByTeacherIdAndGeneretionAndPlanStatus($teacherId, $my_row["studyGeneretion"], 'พ้นสภาพนิสิต');
        $my_row["studentGrads"] = getListStudentPlanStatusBystudyGeneretionByTeacherIdAndGeneretionAndPlanStatus($teacherId, $my_row["studyGeneretion"], 'จบการศึกษา');

        $generetions[] = $my_row;

    }

    require("connection_close.php");

    return $generetions;

}

function getListStudentPlanStatusBystudyGeneretionByTeacherIdAndGeneretionAndPlanStatus($teacherId, $generetion, $planStatus)
{

    require("connection_connect.php");

    $semester = getSemesterPresent();

    $sql = "SELECT *
    FROM fact_term_summary NATURAL JOIN student NATURAL JOIN fact_student
    WHERE teacherId = $teacherId AND planStatus = '$planStatus' AND studyGeneretion = $generetion AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary GROUP BY studentId)";

    $students = [];
    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $students[] = $my_row;
    }

    require("connection_close.php");

    return $students;

}

function getCountStudentStatusByGeneretionAndTeacherId($generetion, $teacherId)
{
    require("connection_connect.php");

    $sql = "SELECT A.status ,IFNULL(count,0) AS count
        FROM (SELECT status
        FROM studentstatus
        WHERE status = 'พ้นสภาพนิสิต' OR status = 'จบการศึกษา') AS A LEFT JOIN (SELECT studyGeneretion,status,COUNT(studentId) AS count
        FROM fact_student NATURAL JOIN studentstatus
        WHERE teacherId = " . $teacherId . " AND (status = 'พ้นสภาพนิสิต' OR status = 'จบการศึกษา' ) AND studyGeneretion = " . $generetion . "
        GROUP BY studentStatusId) AS B
        ON A.status = B.status";

    $result = $conn->query($sql);

    $studentStatus = [];

    while ($my_row = $result->fetch_assoc()) {
        $studentStatus[] = $my_row;
    }


    require("connection_close.php");

    return $studentStatus;
}

function getCountPlanStatusByGeneretionAndTeacherId($generetion, $teacherId)
{


    require("connection_connect.php");

    $semester = getSemesterPresent();

    $sql = "SELECT planStatus,COUNT(studentId) AS count
        FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN fact_student NATURAL JOIN gpaxstatus
        WHERE teacherId = " . $teacherId . " AND semesterYear = " . $semester["semesterYear"] . " AND semesterPart = '" . $semester["semesterPart"] . "' AND studentStatusId = 1 AND studyGeneretion = " . $generetion
        . " GROUP BY planStatus";


    // $sql = "SELECT planStatus,COUNT(studentId) as count
    // FROM (SELECT studentId,studyGeneretion,MAX(studyYear) AS studyYear,studyTerm,gpaxStatusId,planStatus,gpaxStatusName
    // FROM fact_student NATURAL JOIN fact_term_summary NATURAL JOIN gpaxstatus
    // WHERE teacherId = ".$teacherId." AND studyTerm != 3 AND studyGeneretion = ".$generetion."
    // GROUP BY studentId) AS A
    // GROUP BY planStatus";

    $result = $conn->query($sql);

    $studentStatus = [];

    while ($my_row = $result->fetch_assoc()) {
        $studentStatus[] = $my_row;
    }


    require("connection_close.php");

    return $studentStatus;
}

function getCountGradeRangeByTeacherIdAndStudyYear($teacherId, $studyYear)
{


    require("connection_connect.php");

    $studentGeneretionGradeRanges = [];

    $sql = "SELECT studyGeneretion, COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE teacherId = $teacherId AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student 
    WHERE teacherId = $teacherId AND studyYear = $studyYear 
    GROUP BY studentId)
    GROUP BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $studentGeneretionGradeRanges[] = $my_row;
    }


    require("connection_close.php");

    return $studentGeneretionGradeRanges;

}


function getCountGradeRangeByTeacherIdAndStudyGeneretionAndStudyYear($teacherId, $generetion, $studyYear)
{

    require("connection_connect.php");

    $sql = "SELECT gpaStatusName,IFNULL(count,0) AS count
        FROM gpastatus LEFT JOIN 
        (SELECT gpaStatusId,COUNT(*) as count
        FROM fact_term_summary NATURAL JOIN fact_student
        WHERE teacherId = " . $teacherId . " AND studyYear = " . $studyYear . " AND studyGeneretion = " . $generetion . " AND studyTerm IN 
        (SELECT MAX(studyTerm) AS studyTerm FROM fact_term_summary NATURAL JOIN fact_student WHERE teacherId = " . $teacherId . " AND studyYear = " . $studyYear . " AND studyGeneretion = " . $generetion . " AND studyTerm != 3)
        GROUP BY gpaStatusId) AS A
        ON gpastatus.gpaStatusId = A.gpaStatusId";

    $rangeGrades = [];

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $rangeGrades[] = $my_row;
    }
    require("connection_close.php");

    return $rangeGrades;



}

function getCountStudySemesterYearPartByTeacherID($teacherId)
{
    require("connection_connect.php");

    $sql = "SELECT semesterYear,semesterPart,COUNT(CASE WHEN planStatus = 'ตามแผน' THEN planStatus END) AS planStatus,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN planStatus END) AS notPlanStatus,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN planStatus END) AS resign
        FROM fact_student NATURAL JOIN fact_term_summary NATURAL JOIN semester
        WHERE teacherId = " . $teacherId . " AND semesterPart != 'ภาคฤดูร้อน'
        GROUP BY semesterYear,semesterPart";

    $countStudySemesters = [];

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {

        $my_row["studentPlans"] = getListStudentByTeacherIDAndPlanStatusAndSemesterYearAndSemesterYear($teacherId, 'ตามแผน', $my_row["semesterYear"], $my_row["semesterPart"]);
        $my_row["studentNotPlans"] = getListStudentByTeacherIDAndPlanStatusAndSemesterYearAndSemesterYear($teacherId, 'ไม่ตามแผน', $my_row["semesterYear"], $my_row["semesterPart"]);
        $my_row["studentResign"] = getListStudentByTeacherIDAndPlanStatusAndSemesterYearAndSemesterYear($teacherId, 'พ้นสภาพนิสิต', $my_row["semesterYear"], $my_row["semesterPart"]);
        $countStudySemesters[] = $my_row;

    }



    require("connection_close.php");

    return $countStudySemesters;
}

function getListStudentByTeacherIDAndPlanStatusAndSemesterYearAndSemesterYear($teacherId, $planStatus, $semesterYear, $semesterPart)
{

    require("connection_connect.php");

    $students = [];

    $semesterId = getSemesterIdByYearAndPart($semesterYear, $semesterPart);

    $sql = "SELECT studentId,fisrtNameTh,lastNameTh,gpaAll,planStatus
        FROM student NATURAL JOIN fact_term_summary NATURAL JOIN fact_student
        WHERE teacherId = " . $teacherId . " AND semesterId = " . $semesterId . " AND planStatus = '" . $planStatus . "'";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $students[] = $my_row;
    }


    require("connection_close.php");


    return $students;

}

function getGPAXStatusGerenetionByTeacherId($teacherId)
{

    $studyGeneretionGPAXs = [];

    $semester = getSemesterPresent();
    require("connection_connect.php");


    $sql = "SELECT studyGeneretion,COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN studentstatus NATURAL JOIN fact_student
    WHERE teacherId = $teacherId AND status = 'กำลังศึกษา' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE teacherId = $teacherId GROUP BY studentId)
    GROUP BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $studyGeneretionGPAXs[] = $my_row;
    }


    require("connection_close.php");

    return $studyGeneretionGPAXs;



}

function getGPAXStatusGerenetionGraduateByTeacherId($teacherId)
{

    $studyGeneretionGPAXs = [];

    $semester = getSemesterPresent();
    require("connection_connect.php");


    $sql = "SELECT studyGeneretion,COUNT(CASE WHEN gpaStatusName = 'blue' THEN studentId END) AS blue,COUNT(CASE WHEN gpaStatusName = 'green' THEN studentId END) AS green,COUNT(CASE WHEN gpaStatusName = 'orange' THEN studentId END) AS orange,COUNT(CASE WHEN gpaStatusName = 'red' THEN studentId END) AS red
    FROM gpastatus NATURAL JOIN fact_term_summary NATURAL JOIN studentstatus NATURAL JOIN fact_student
    WHERE teacherId = $teacherId AND status = 'จบการศึกษา' AND termSummaryId IN (SELECT MAX(termSummaryId) AS termSummaryId FROM fact_term_summary NATURAL JOIN semester NATURAL JOIN fact_student
    WHERE teacherId = $teacherId GROUP BY studentId)
    GROUP BY studyGeneretion;";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $studyGeneretionGPAXs[] = $my_row;
    }


    require("connection_close.php");

    return $studyGeneretionGPAXs;



}

function getCountStudentGPAXStatusByTeacherIdAndSemesterYearAndSemesterPartAndCourseId($teacherId, $semesterYear, $semesterPart, $courseId)
{

    require("connection_connect.php");

    $countGPAXs = [];

    $semester = getSemesterPresent();


    $sql = "SELECT gpaxstatus.gpaxStatusName,IFNULL(count,0) AS count
        FROM gpaxstatus LEFT JOIN
        (SELECT gpaxStatusId,gpaxStatusName,COUNT(studentId) AS count
        FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN fact_student NATURAL JOIN gpaxstatus
        WHERE teacherId = 1 AND courseId = " . $courseId . " AND semesterYear = " . $semesterYear . " AND semesterPart = '" . $semesterPart . "'
        GROUP BY gpaxStatusId) A
        ON gpaxstatus.gpaxStatusId = A.gpaxStatusId";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $countGPAXs[] = $my_row;
    }

    require("connection_close.php");

    foreach ($countGPAXs as $gpax) {


        if ($gpax["gpaxStatusName"] == "เกียรตินิยม") {
            $gpaxStatusCount["blue"] = $gpax["count"];
        } else if ($gpax["gpaxStatusName"] == "ปกติ") {
            $gpaxStatusCount["green"] = $gpax["count"];
        } else if ($gpax["gpaxStatusName"] == "รอพินิจ") {
            $gpaxStatusCount["orange"] = $gpax["count"];
        } else if ($gpax["gpaxStatusName"] == "โปรต่ำ") {
            $gpaxStatusCount["red"] = $gpax["count"];
        }



    }

    return $gpaxStatusCount;

}

function getCountStudentByPlaningByTeacherIdAndCourseIdAndSemesterYearAndSemesterPart($teacherId, $courseId, $year, $part)
{


    $planingStatus["plan"] = getCountStudentInTeacherByPlaningByTeacherIdAndPlaningStatusAndCouresId($teacherId, "ตามแผน", $courseId, $year, $part);
    $planingStatus["notPlan"] = getCountStudentInTeacherByPlaningByTeacherIdAndPlaningStatusAndCouresId($teacherId, "ไม่ตามแผน", $courseId, $year, $part);
    $planingStatus["retire"] = getCountStudentInTeacherByStudentStatusByTeacherIdAndStatus($teacherId, "พ้นสภาพ");
    $planingStatus["grad"] = getCountStudentInTeacherByStudentStatusByTeacherIdAndStatus($teacherId, "จบการศึกษา");

    return $planingStatus;

}

function getCountStudentInTeacherByPlaningByTeacherIdAndPlaningStatusAndCouresId($teacherId, $status, $courseId, $semesterYear, $semesterPart)
{

    require("connection_connect.php");

    $semester = getSemesterPresent();

    $sql = "SELECT planStatus,COUNT(studentId) AS count
        FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN fact_student NATURAL JOIN gpaxstatus
        WHERE teacherId = " . $teacherId . " AND courseId = " . $courseId . " AND semesterYear = " . $semesterYear . " AND semesterPart = '" . $semesterPart . "' AND studentStatusId = 1 AND planStatus = '" . $status . "';";

    $result = $conn->query($sql);
    $plan = $result->fetch_assoc();

    if (is_null($plan)) {
        $s["count"] = 0;
        $plan = $s;
    }

    require("connection_close.php");

    return $plan;

}

function getCountStudentPlanStatusBystudyGeneretionByTeacherIdAndSemesterYearAndSemesterPartAndCourseId($teacherId, $year, $part, $courseId)
{

    require("connection_connect.php");

    $semester = getSemesterPresent();

    $sql = "SELECT studyGeneretion,COUNT(CASE WHEN planStatus = 'ตามแผน' THEN planStatus END) AS planCount,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN planStatus END) AS notPlanCount,COUNT(CASE WHEN planStatus = 'พ้นสภาพนิสิต' THEN planStatus END) AS retire,COUNT(CASE WHEN planStatus = 'จบการศึกษา' THEN planStatus END) AS grad
    FROM fact_student NATURAL JOIN fact_term_summary NATURAL JOIN semester
    WHERE fact_term_summary.teacherId = $teacherId AND semesterYear = " . $year . " AND semesterPart = '" . $part . "' AND courseId = " . $courseId . "
    GROUP BY studyGeneretion";

    $generetions = [];
    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $generetions[] = $my_row;
    }

    require("connection_close.php");

    return $generetions;

}


function getCountGradeRangeByTeacherIdAndStudyYearAndSemesterYearAndSemesterPartAndCourseId($teacherId, $studyYear, $year, $part, $courseId)
{


    require("connection_connect.php");


    $sYear = $year - $studyYear;

    $sqlGeneretion = "SELECT DISTINCT studyGeneretion
        FROM fact_student NATURAL JOIN fact_term_summary NATURAL JOIN semester
        WHERE teacherId = " . $teacherId . " AND studyYear = " . $studyYear . " AND semesterPart = '" . $part . "' AND semesterYear <= $year
        ORDER BY studyGeneretion";

    $generetions = [];

    $result = $conn->query($sqlGeneretion);

    while ($my_row = $result->fetch_assoc()) {
        $generetions[] = $my_row["studyGeneretion"];
    }

    $rangeGradeStudyYears = [];

    foreach ($generetions as $generetion) {
        $rangeGradeStudy["studyGeneretion"] = $generetion;
        $rangeGrades = getCountGradeRangeByTeacherIdAndStudyGeneretionAndStudyYearAndSemesterYearAndSemesterPartAndCourseId($teacherId, $generetion, $studyYear, $year, $part, $courseId);

        foreach ($rangeGrades as $rangeGrade) {
            $gpaStatusName = $rangeGrade["gpaStatusName"];
            $rangeGradeStudy["$gpaStatusName"] = $rangeGrade["count"];
        }
        $rangeGradeStudyYears[] = $rangeGradeStudy;


    }

    require("connection_close.php");

    return $rangeGradeStudyYears;

}

function getCountGradeRangeByTeacherIdAndStudyGeneretionAndStudyYearAndSemesterYearAndSemesterPartAndCourseId($teacherId, $generetion, $studyYear, $year, $part, $courseId)
{

    require("connection_connect.php");

    if ($part == "ภาคต้น") {
        $term = 1;
    } else if ($part == "ภาคปลาย") {
        $term = 2;
    }

    $sql = "SELECT gpaStatusName,IFNULL(count,0) AS count
        FROM gpastatus LEFT JOIN 
        (SELECT gpaStatusId,COUNT(*) as count
        FROM semester NATURAL JOIN fact_term_summary NATURAL JOIN fact_student
        WHERE fact_term_summary.teacherId = " . $teacherId . " AND studyYear = " . $studyYear . " AND studyGeneretion = " . $generetion . " AND studyTerm =" . $term . "
        GROUP BY gpaStatusId) AS A
        ON gpastatus.gpaStatusId = A.gpaStatusId";

    $rangeGrades = [];

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $rangeGrades[] = $my_row;
    }
    require("connection_close.php");

    return $rangeGrades;



}

function getCountStudySemesterYearPartByTeacherIDAndSemesterYearAndSemesterPartAndCourseId($teacherId, $year, $part, $courseId)
{
    require("connection_connect.php");

    $sql = "SELECT semesterYear,semesterPart,COUNT(CASE WHEN planStatus = 'ตามแผน' THEN planStatus END) AS planStatus,COUNT(CASE WHEN planStatus = 'ไม่ตามแผน' THEN planStatus END) AS notPlanStatus,COUNT(CASE WHEN planStatus = 'ลาออก' THEN planStatus END) AS resign
        FROM fact_student NATURAL JOIN fact_term_summary NATURAL JOIN semester
        WHERE fact_term_summary.teacherId = " . $teacherId . " AND semesterPart = '" . $part . "' AND semesterYear = " . $year . " AND courseId = " . $courseId . "
        GROUP BY semesterYear,semesterPart";

    $countStudySemesters = [];

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {

        $my_row["studentPlans"] = getListStudentByTeacherIDAndPlanStatusAndSemesterYearAndSemesterYear($teacherId, 'ตามแผน', $my_row["semesterYear"], $my_row["semesterPart"]);
        $my_row["studentNotPlans"] = getListStudentByTeacherIDAndPlanStatusAndSemesterYearAndSemesterYear($teacherId, 'ไม่ตามแผน', $my_row["semesterYear"], $my_row["semesterPart"]);
        $my_row["studentResign"] = getListStudentByTeacherIDAndPlanStatusAndSemesterYearAndSemesterYear($teacherId, 'ลาออก', $my_row["semesterYear"], $my_row["semesterPart"]);
        $countStudySemesters[] = $my_row;

    }



    require("connection_close.php");

    return $countStudySemesters;
}

function getStudentInAdviserBtTeacherId($teacherId)
{

    require("connection_connect.php");


    $students = [];

    $sql = "SELECT studentId FROM fact_student WHERE teacherId = " . $teacherId;

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {



        $student = getStudentByStudentId($my_row["studentId"]);
        //echo $my_row["studentId"];

        $students[] = $student;



    }



    require("connection_close.php");

    return $students;

}


function getGPAXStatusGerenetionByTeacherIdAndSemesterYearAndSemesterPartAndCourseId($teacherId, $year, $part, $courseId)
{

    $studyGeneretionGPAXs = [];

    $semester = getSemesterIdByYearAndPart($year, $part);
    require("connection_connect.php");


    $sql = "SELECT studyGeneretion,COUNT(CASE WHEN gpaxStatusId = 1 THEN gpaxStatusId END) AS blue,COUNT(CASE WHEN gpaxStatusId = 2 THEN gpaxStatusId END) AS green,COUNT(CASE WHEN gpaxStatusId = 3 THEN gpaxStatusId END) AS orange,COUNT(CASE WHEN gpaxStatusId = 4 THEN gpaxStatusId END) AS red
    FROM fact_student NATURAL JOIN fact_term_summary
    WHERE teacherId = " . $teacherId . " AND studentStatusId = 1 AND  semesterId = " . $semester . " AND courseId = " . $courseId . "
    GROUP BY studyGeneretion";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $studyGeneretionGPAXs[] = $my_row;
    }


    require("connection_close.php");

    return $studyGeneretionGPAXs;



}

function getGPAXStatusGerenetionGraduateByTeacherIdAndSemesterYearAndSemesterPartAndCourseId($teacherId, $year, $part, $courseId)
{

    $studyGeneretionGPAXs = [];


    $semester = getSemesterIdByYearAndPart($year, $part);
    require("connection_connect.php");


    $sql = "SELECT studyGeneretion,COUNT(CASE WHEN gpaxStatusId = 1 THEN gpaxStatusId END) AS blue,COUNT(CASE WHEN gpaxStatusId = 2 THEN gpaxStatusId END) AS green,COUNT(CASE WHEN gpaxStatusId = 3 THEN gpaxStatusId END) AS orange,COUNT(CASE WHEN gpaxStatusId = 4 THEN gpaxStatusId END) AS red
    FROM fact_student NATURAL JOIN fact_term_summary
    WHERE teacherId = " . $teacherId . " AND fact_term_summary.studentStatusId = 2 AND semesterId = " . $semester . " AND courseId = $courseId
    GROUP BY studyGeneretion";

    $result = $conn->query($sql);

    while ($my_row = $result->fetch_assoc()) {
        $studyGeneretionGPAXs[] = $my_row;
    }


    require("connection_close.php");

    return $studyGeneretionGPAXs;



}




?>