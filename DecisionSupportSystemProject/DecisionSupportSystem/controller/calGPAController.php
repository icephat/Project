<?php

session_start();



require_once '../function/regisFunction.php';
require_once '../function/studentFunction.php';
require_once '../function/subjectFunction.php';




$calGPA["studentId"] = $_SESSION["studentId"];
$student = getStudentByStudentId($_SESSION["studentId"]);


// $subject1 = $_POST["subject1"];
// $grade1 = $_POST["grade1"];

// $subject2 = $_POST["subject2"];
// $grade2 = $_POST["grade2"];

// $subject3 = $_POST["subject3"];
// $grade3 = $_POST["grade3"];

// $subject4 = $_POST["subject4"];
// $grade4 = $_POST["grade4"];

// $subject5 = $_POST["subject5"];
// $grade5 = $_POST["grade5"];

// $subject6 = $_POST["subject6"];
// $grade6 = $_POST["grade6"];

// $subject7 = $_POST["subject7"];
// $grade7 = $_POST["grade7"];

// $subject8 = $_POST["subject8"];
// $grade8 = $_POST["grade8"];

// $subject9 = $_POST["subject9"];
// $grade9 = $_POST["grade9"];

$calGPA["gpaPresent"] = round($student["gpax"], 2);


$sumCreditGradeNewTerm = 0;
$sumCreditNewTerm = 0;
$count = 1;

for ($i = 1; $i < 10; $i++) {
    if ($_POST["subject$i"] != null and $_POST["grade$i"] != "") {
        $calGPA["subject$count"] = $_POST["subject$i"];
        $calGPA["grade$count"] = $_POST["grade$i"];

        $list = array("1credit", "2credit", "3credit");

        if (!in_array($_POST["subject$i"], $list)) {
            
            $subject = getSubjectBySubjectId($_POST["subject$i"]);
            $calGPA["subjectCode$count"] = $subject["subjectCode"];
            $calGPA["subjectName$count"] = $subject["nameSubjectEng"];
            $calGPA["credit$count"]= $subject["credit"];


            $sumCreditNewTerm += $subject["credit"];
            $sumCreditGradeNewTerm += $subject["credit"] * $_POST["grade$i"];
        } else {
            if ($_POST["subject$i"] ==  "1credit") {
                
                $sumCreditNewTerm += 1;
                $sumCreditGradeNewTerm += 1 * $_POST["grade$i"];
                $calGPA["subjectName$count"] = "1 Credit";
                $calGPA["credit$count"]= 1;

            } else if ($_POST["subject$i"] ==  "2credit") {
                $sumCreditNewTerm += 2;
                $sumCreditGradeNewTerm += 2 * $_POST["grade$i"];
                $calGPA["subjectName$count"] = "2 Credit";
                $calGPA["credit$count"]= 2;

            } else if ($_POST["subject$i"] ==  "3credit") {
                $sumCreditNewTerm += 3;
                $sumCreditGradeNewTerm += 3 * $_POST["grade$i"];
                $calGPA["subjectName$count"] = "3 Credit";
                $calGPA["credit$count"]= 3;

            }
            $calGPA["subjectCode$count"] = "XXXXXXXX";

        }

        if ($_POST["grade$i"] == 4) {
            $calGPA["grade$count"] = "A";
        } else if ($_POST["grade$i"] == 3.5) {
            $calGPA["grade$count"] = "B+";
        } else if ($_POST["grade$i"] == 3) {
            $calGPA["grade$count"] = "B";
        } else if ($_POST["grade$i"] == 2.5) {
            $calGPA["grade$count"] = "C+";
        } else if ($_POST["grade$i"] == 2) {
            $calGPA["grade$count"] = "C";
        } else if ($_POST["grade$i"] == 1.5) {
            $calGPA["grade$count"] = "D+";
        } else if ($_POST["grade$i"] == 1) {
            $calGPA["grade$count"] = "D";
        } else if ($_POST["grade$i"] == 0) {
            $calGPA["grade$count"] = "F";
        }

        //echo "subject : ".$_POST["subject$i"]." grade : ".$_POST["grade$i"]."<br>";
        $count++;
    }

}

$calGPA["gpaNew"] = round($sumCreditGradeNewTerm/$sumCreditNewTerm,2);
$calGPA["creditPresent"] = $student["credit"];
$calGPA["creditNew"] = $sumCreditNewTerm;




$newCreditGrade = 0;

$regisList = getListRegisByStudentId($student["studentId"]);

$sumCreditOld = 0;

foreach($regisList as $regis){
    $sumCreditOld += $regis["credit"];
    $newCreditGrade += $regis["credit"] * $regis["gradeNumber"];
}

$calGPA["gpaxNew"] = round(($newCreditGrade+$sumCreditGradeNewTerm)/($sumCreditNewTerm+$sumCreditOld),2);
$calGPA["count"] = $count;

echo print_r($calGPA);

$path = "../student/calGPA/" . $calGPA["studentId"];
$toJson = json_encode($calGPA);
file_put_contents($path . ".json", $toJson);

header('Location: ' . '../student/calGPAHis.php');
exit();



?>