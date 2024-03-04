<!DOCTYPE html>
<html lang="en">

<head>

    <style>
        .t1:hover {
            background-color: #ececec;
            transition: all 0.5s linear;
        }
    </style>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>ระบบสนับสนุนการตัดสินใจ</title>

    <!-- Custom fonts for this template-->
    <link href="../vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="../css/sb-admin-2.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">
                <?php


                session_start();

                require_once '../function/teacherFunction.php';
                require_once '../function/semesterFunction.php';
                require_once '../function/courseFunction.php';
                require_once '../function/headDeptFunction.php';
                require_once '../function/departmentFunction.php';

                $teacher = getTeacherByUsernameTeacher($_SESSION["access-user"]);
                $semester = getSemesterPresent();


                $course = getCoursePresentByDepartmentId($teacher["departmentId"]);

                $departments = getAllDepartment();
                $semesterYears = getSemesterYear();

                $departmentId = $_POST["departmentId"];
                $semesterYear = $_POST["year"];


                $department = getDepartmentById($departmentId);

                ?>

                <?php include('../layout/head/report.php'); ?>

                <div>
                    <form class="form-valide" action="student_faculty_search.php" method="post" enctype="multipart/form-data">
                        <div class="row mx-auto">
                            <div class="column col-sm-4">

                                <div class="text-center">
                                    <h5>ภาควิชา<span style="color: red;">*</span></th>
                                </div>
                                <div class="text-center">
                                    <div>
                                        <select class="form-control" data-live-search="true" name = "departmentId">
                                            
                                            <?php
                                            foreach($departments as $dpm){
                                            ?>
    
                                                <option value="<?php echo $dpm["departmentId"] ?>"><?php echo $dpm["departmentName"] ?></option>
                                            <?php
                                            }
                                            ?>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <div class="column col-sm-4">
                                <div class="text-center">
                                    <h5>ปีที่สืบค้น<span style="color: red;">*</span></th>
                                </div>
                                <div class="text-center">
                                    <div>
                                        <select class="form-control" data-live-search="true" name = "year">
                                            
                                            <?php
                                            foreach($semesterYears as $y){
                                            ?>
                                            <option value="<?php echo $y["semesterYear"]?>"><?php echo $y["semesterYear"]?></option>
                                            <?php
                                            }
                                            ?>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="column col-sm-3">
                                <div class="text-center">
                                    <br>
                                </div>
                                <div class="text-center">
                                    <button type="submit" name="submit" id="data"
                                        class="btn btn-success btn-m active">ดูผล</button>
                                </div>
                            </div>

                        </div>

                    </form>
                </div>

                <hr>
                <div class="row" style="color: black;">
                    <?php

                    $generetions = getGeneretionInCourseByDepartmentId($departmentId);
                    $countStudentInCourse = getCountStudentInDepartmentByDepartmentId($departmentId);

                    ?>
                    <h5>ภาควิชา
                        <?php echo $department["departmentName"] . " ปีการศึกษา ".$semesterYear ?>
                        
                    </h5>
                </div>
                <div class="row">
                    <div class="col-sm-12 ">
                        <div class="row">
                            <div class="col-sm-5 mx-auto">
                                <table class="table table-hover"
                                    style="margin-top: 30px; border: 1px solid black; border-collapse: collapse; ">
                                    <tr style="border: 1px solid black; border-collapse: collapse; ">
                                        <th style="border: 1px solid black; border-collapse: collapse; width: 50%; ">

                                            <?php
                                            $countRangeGrade = getCountStudentGradeRangeByDepartmrntIdAndSemesterYear($departmentId, $semesterYear)

                                                ?>

                                            <div style="color: rgb(0, 9, 188);">
                                                <div class="text-center">
                                                    <a style="color: rgb(0, 9, 188);" href="#" data-toggle="modal" data-target="#modalblue">
                                                        <h4>3.25-4.00</h4>
                                                    </a>
                                                </div>
                                                <div class="text-center">
                                                    <h1 style="font-weight: bolder; font-size: 70px; ">
                                                        <?php echo $countRangeGrade["blue"] ?>
                                                    </h1>
                                                </div>
                                                <div class="text-right">
                                                    <p>คน</p>
                                                </div>
                                            </div>


                                        </th>
                                        <th style="border: 1px solid black; border-collapse: collapse; ">
                                            <div style="color: rgb(0, 110, 22);">
                                                <div class="text-center">
                                                    <a style="color: rgb(0, 110, 22);" href="#" data-toggle="modal" data-target="#modalgreen">
                                                        <h4>2.00-3.24</h4>
                                                    </a>
                                                </div>
                                                <div class="text-center">
                                                    <h1 style="font-weight: bolder; font-size: 70px;">
                                                        <?php echo $countRangeGrade["green"] ?>
                                                    </h1>
                                                </div>
                                                <div class="text-right">
                                                    <p>คน</p>
                                                </div>
                                            </div>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th style="border: 1px solid black; border-collapse: collapse; ">

                                            <div style="color: #ff8c00;">
                                                <div class="text-center">
                                                    <a style="color: #ff8c00;" href="#" data-toggle="modal" data-target="#modalorange">
                                                        <h4>1.75-1.99</h4>
                                                    </a>
                                                </div>
                                                <div class="text-center">
                                                    <h1 style="font-weight: bolder; font-size: 70px;">
                                                        <?php echo $countRangeGrade["orange"] ?>
                                                    </h1>
                                                </div>
                                                <div class="text-right">
                                                    <p>คน</p>
                                                </div>
                                            </div>
                                        </th>
                                        <th style="border: 1px solid black; border-collapse: collapse;">
                                            <div style="color: rgb(255, 0, 0);">
                                                <div class="text-center">
                                                    <a style="color: rgb(255, 0, 0);" href="#" data-toggle="modal" data-target="#modalred">
                                                        <h4>0.00-1.74</h4>
                                                    </a>
                                                </div>
                                                <div class="text-center">
                                                    <h1 style="font-weight: bolder; font-size: 70px;">
                                                        <?php echo $countRangeGrade["red"] ?>
                                                    </h1>
                                                </div>
                                                <div class="text-right">
                                                    <p>คน</p>
                                                </div>
                                            </div>
                                        </th>
                                    </tr>
                                </table>
                            </div>
                            <div class="col-sm-5 mx-auto">
                                <table class="table table-hover"
                                    style="margin-top: 30px; border: 1px solid black; border-collapse: collapse;">
                                    <tr style="border: 1px solid black; border-collapse: collapse;">
                                        <th style="border: 1px solid black; border-collapse: collapse; width: 50%;">
                                            <?php
                                            $countPlanStatus = getCountStudentPlanStatusByDepartmrntIdAndSemesterYear($departmentId, $semesterYear);
                                                ?>

                                            <div style="color: rgb(100, 197, 215);">
                                                <div class="text-center">
                                                    <a style="color: rgb(100, 197, 215);" href="#" data-toggle="modal" data-target="#modalblue2">
                                                        <h4>ตามแผน</h4>
                                                    </a>
                                                </div>
                                                <div class="text-center">
                                                    <h1 style="font-weight: bolder; font-size: 70px; ">
                                                        <?php echo $countPlanStatus["plan"] ?>
                                                    </h1>
                                                </div>
                                                <div class="text-right">
                                                    <p>คน</p>
                                                </div>
                                            </div>


                                        </th>
                                        <th style="border: 1px solid black; border-collapse: collapse; ">
                                            <div style="color: rgb(	118, 188, 22);">
                                                <div class="text-center">
                                                    <a style="color: rgb(	118, 188, 22);" href="#" data-toggle="modal" data-target="#modalgreen2">
                                                        <h4>ไม่ตามแผน</h4>
                                                    </a>
                                                </div>
                                                <div class="text-center">
                                                    <h1 style="font-weight: bolder; font-size: 70px;">
                                                        <?php echo $countPlanStatus["notPlan"] ?>
                                                    </h1>
                                                </div>
                                                <div class="text-right">
                                                    <p>คน</p>
                                                </div>
                                            </div>
                                        </th>
                                    </tr>
                                    <tr>
                                        <th style="border: 1px solid black; border-collapse: collapse;">

                                            <div style="color: rgb(	245, 123, 57);">
                                                <div class="text-center">
                                                    <a style="color: rgb(	245, 123, 57);" href="#" data-toggle="modal" data-target="#modalorange2">
                                                        <h4>พ้นสภาพ</h4>
                                                    </a>
                                                </div>
                                                <div class="text-center">
                                                    <h1 style="font-weight: bolder; font-size: 70px;">
                                                        <?php echo $countPlanStatus["retire"] ?>
                                                    </h1>
                                                </div>
                                                <div class="text-right">
                                                    <p>คน</p>
                                                </div>
                                            </div>
                                        </th>
                                        <th style="border: 1px solid black; border-collapse: collapse;">
                                            <div style="color: rgb(255, 105, 98);">
                                                <div class="text-center">
                                                    <a style="color:  rgb(255, 105, 98);" href="#" data-toggle="modal" data-target="#modalred2">
                                                        <h4>จบการศึกษา</h4>
                                                    </a>
                                                </div>
                                                <div class="text-center">
                                                    <h1 style="font-weight: bolder; font-size: 70px;">
                                                        <?php echo $countPlanStatus["grad"] ?>
                                                    </h1>
                                                </div>
                                                <div class="text-right">
                                                    <p>คน</p>
                                                </div>
                                            </div>
                                        </th>
                                    </tr>
                                </table>
                            </div>
                        </div>
                    </div>

                </div>

                <div class="row">
                    <div class="col-sm-4">
                        <div class="card">
                            <?php
                            $studentGeneretionGradeRangeOnes = getCountStudentGradeRangeSortByGeneretionByDepartmentIdAndSemesterYearAndStudyYear($departmentId, $semesterYear, 1);
                            $day = date("Y");
                            $thaiDay = 543 + $day;
                            //echo substr($thaiDay-4, -2);
                            $y = substr($thaiDay - 4, -2);
                            $yNow = substr($thaiDay, -2);
                            $pee1gen = [];
                            $pee1blues = [];
                            $pee1greens = [];
                            $pee1oranges = [];
                            $pee1reds = [];

                            //for($y; $y<$yNow; $y++){
                            
                            foreach ($studentGeneretionGradeRangeOnes as $range) {
                                if ((int) $range["studyGeneretion"] == $y) {
                                    $pee1gen[] = "รุ่น " . (string) $range["studyGeneretion"];
                                    $pee1blues[] = $range["blue"];
                                    $pee1greens[] = $range["green"];
                                    $pee1oranges[] = $range["orange"];
                                    $pee1reds[] = $range["red"];
                                } else {
                                    $pee1gen[] = "รุ่น " . (string) $y;
                                    $pee1blues[] = "0";
                                    $pee1greens[] = "0";
                                    $pee1oranges[] = "0";
                                    $pee1reds[] = "0";
                                }
                            }

                            //}
                            ?>
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">ช่วงเกรดนิสิตปีที่ 1</h6>
                            </div>
                            <div class="card-body">
                                <canvas id="pee1"></canvas>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="card">
                            <?php
                            $studentGeneretionGradeRangeTwos = getCountStudentGradeRangeSortByGeneretionByDepartmentIdAndSemesterYearAndStudyYear($departmentId, $semesterYear, 2);
                            $day = date("Y");
                            $thaiDay = 543 + $day;
                            //echo substr($thaiDay-4, -2);
                            $y = substr($thaiDay - 4, -2);
                            $yNow = substr($thaiDay, -2);
                            $pee2genh = [];
                            $pee2bluesh = [];
                            $pee2greensh = [];
                            $pee2orangesh = [];
                            $pee2redsh = [];
                            //for($y; $y<$yNow; $y++){
                            foreach ($studentGeneretionGradeRangeTwos as $range) {
                                if ((int) $range["studyGeneretion"] == $y) {

                                    $pee2genh[] = "รุ่น " . (string) $range["studyGeneretion"];
                                    $pee2bluesh[] = $range["blue"];
                                    $pee2greensh[] = $range["green"];
                                    $pee2orangesh[] = $range["orange"];
                                    $pee2redsh[] = $range["red"];
                                } else {
                                    $pee2genh[] = "รุ่น " . (string) $y;
                                    $pee2bluesh[] = "0";
                                    $pee2greensh[] = "0";
                                    $pee2orangesh[] = "0";
                                    $pee2redsh[] = "0";
                                }
                            }

                            //}
                            ?>
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">ช่วงเกรดนิสิตปีที่ 2</h6>
                            </div>
                            <div class="card-body">
                                <canvas id="pee2"></canvas>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="card">
                            <?php
                            $studentGeneretionGradeRangeThrees = getCountStudentGradeRangeSortByGeneretionByDepartmentIdAndSemesterYearAndStudyYear($departmentId, $semesterYear, 3);
                            $day = date("Y");
                            $thaiDay = 543 + $day;
                            //echo substr($thaiDay-4, -2);
                            $y = substr($thaiDay - 4, -2);
                            $yNow = substr($thaiDay, -2);
                            $pee3gen = [];
                            $pee3blues = [];
                            $pee3greens = [];
                            $pee3oranges = [];
                            $pee3reds = [];
                            //for($y; $y<$yNow; $y++){
                            foreach ($studentGeneretionGradeRangeThrees as $range) {
                                if ((int) $range["studyGeneretion"] == $y) {
                                    $pee3gen[] = "รุ่น " . (string) $range["studyGeneretion"];
                                    $pee3blues[] = $range["blue"];
                                    $pee3greens[] = $range["green"];
                                    $pee3oranges[] = $range["orange"];
                                    $pee3reds[] = $range["red"];
                                } else {
                                    $pee3gen[] = "รุ่น " . (string) $y;
                                    $pee3blues[] = "0";
                                    $pee3greens[] = "0";
                                    $pee3oranges[] = "0";
                                    $pee3reds[] = "0";
                                }
                            }
                            //}
                            ?>
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">ช่วงเกรดนิสิตปีที่ 3</h6>
                            </div>
                            <div class="card-body">
                                <canvas id="pee3"></canvas>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4" style="margin-top: 25px;">
                        <div class="card">
                            <?php
                            $studentGeneretionGradeRangeFours = getCountStudentGradeRangeSortByGeneretionByDepartmentIdAndSemesterYearAndStudyYear($departmentId, $semesterYear, 4);
                            $day = date("Y");
                            $thaiDay = 543 + $day;
                            //echo substr($thaiDay-4, -2);
                            $y = substr($thaiDay - 4, -2);
                            $yNow = substr($thaiDay, -2);
                            $pee4gen = [];
                            $pee4blues = [];
                            $pee4greens = [];
                            $pee4oranges = [];
                            $pee4reds = [];
                            //for($y; $y<$yNow; $y++){
                            
                            foreach ($studentGeneretionGradeRangeFours as $range) {
                                if ((int) $range["studyGeneretion"] == $y) {
                                    $pee4gen[] = "รุ่น " . (string) $range["studyGeneretion"];
                                    $pee4blues[] = $range["blue"];
                                    $pee4greens[] = $range["green"];
                                    $pee4oranges[] = $range["orange"];
                                    $pee4reds[] = $range["red"];
                                } else {
                                    $pee4gen[] = "รุ่น " . (string) $y;
                                    $pee4blues[] = "0";
                                    $pee4greens[] = "0";
                                    $pee4oranges[] = "0";
                                    $pee4reds[] = "0";
                                }
                            }
                            //}
                            ?>
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">ช่วงเกรดนิสิตปีที่ 4</h6>
                            </div>
                            <div class="card-body">
                                <canvas id="pee4"></canvas>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4" style="margin-top: 25px;">
                        <div class="card">
                            <?php
                            $studentGeneretionGradeRangeFours = getCountStudentGradeRangeSortByGeneretionByDepartmentIdAndSemesterYearAndStudyYear($departmentId, $semesterYear, 4);
                            $day = date("Y");
                            $thaiDay = 543 + $day;
                            //echo substr($thaiDay-4, -2);
                            $y = substr($thaiDay - 4, -2);
                            $yNow = substr($thaiDay, -2);
                            $pee4plusgen = [];
                            $pee4plusblues = [];
                            $pee4plusgreens = [];
                            $pee4plusoranges = [];
                            $pee4plusreds = [];
                            //for($y; $y<$yNow; $y++){
                            
                            foreach ($studentGeneretionGradeRangeFours as $range) {
                                if ((int) $range["studyGeneretion"] == $y) {
                                    $pee4plusgen[] = "รุ่น " . (string) $range["studyGeneretion"];
                                    $pee4plusblues[] = $range["blue"];
                                    $pee4plusgreens[] = $range["green"];
                                    $pee4plusoranges[] = $range["orange"];
                                    $pee4plusreds[] = $range["red"];
                                } else {
                                    $pee4plusgen[] = "รุ่น " . (string) $y;
                                    $pee4plusblues[] = "0";
                                    $pee4plusgreens[] = "0";
                                    $pee4plusoranges[] = "0";
                                    $pee4plusreds[] = "0";
                                }
                            }
                            //}
                            ?>
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">ช่วงเกรดนิสิตปีที่ 4+</h6>
                            </div>
                            <div class="card-body">
                                <canvas id="pee4plus"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
                <br><br>

                <div class="row">
                    <div class="col-sm-6">
                        <div class="card">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">สถานภาพนิสิต ณ ปัจจุบัน</h6>
                            </div>
                            <?php

                            $countStudentStudyingRangeGradeSortByGeneretions = getCountStudentGradeRangeSortByGeneretionByDepartmentIdAndSemesterYearAndStudyYearAndStatus($departmentId, $semesterYear, "กำลังศึกษา");
                            $day = date("Y");
                            $thaiDay = 543 + $day;
                            //echo substr($thaiDay-4, -2);
                            $y = substr($thaiDay - 4, -2);
                            $yNow = substr($thaiDay, -2);
                            $nowgen = [];
                            $BNG = [];
                            $GNG = [];
                            $ONG = [];
                            $RNG = [];
                            //for($y; $y<$yNow; $y++){
                            foreach ($countStudentStudyingRangeGradeSortByGeneretions as $grade) {
                                if ((int) $range["studyGeneretion"] == $y) {
                                    $nowgen[] = "รุ่น " . (string) $grade["studyGeneretion"];
                                    $BNG[] = (int) $grade["blue"];
                                    $GNG[] = (int) $grade["green"];
                                    $ONG[] = (int) $grade["orange"];
                                    $RNG[] = (int) $grade["red"];
                                } else {
                                    $nowgen[] = "รุ่น " . (string) $y;
                                    $BNG[] = "0";
                                    $GNG[] = "0";
                                    $ONG[] = "0";
                                    $RNG[] = "0";
                                }
                            }
                            //}
                            ?>
                            <div class="card-body">
                                <canvas id="learn"></canvas>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="card">
                            <?php

                            $countStudentGraduateRangeGradeSortByGeneretions = getCountStudentGradeRangeSortByGeneretionByDepartmentIdAndSemesterYearAndStudyYearAndStatus($departmentId, $semesterYear, "จบการศึกษา");
                            $endgen = [];
                            $BEG = [];
                            $GEG = [];
                            $OEG = [];
                            $REG = [];
                            foreach ($countStudentGraduateRangeGradeSortByGeneretions as $gradeEnd) {
                                $endgen[] = "รุ่น " . (string) $gradeEnd["studyGeneretion"];
                                $BEG[] = (int) $gradeEnd["blue"];
                                $GEG[] = (int) $gradeEnd["green"];
                                $OEG[] = (int) $gradeEnd["orange"];
                                $REG[] = (int) $gradeEnd["red"];
                            }
                            ?>
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">สถานภาพนิสิตจบการศึกษา </h6>
                            </div>
                            <div class="card-body">
                                <canvas id="learn2"></canvas>
                            </div>
                        </div>
                    </div>
                </div>
                <br><br>


                <div class="row">
                    <div class="col-sm-12">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">จำนวนนิสิต (คน)</h6>
                            </div>
                            <?php

                            $studentStatusGeneretions = getCountStudentStatusSortByGeneretionByDepartmentIdAndSemesterYearAndStudyYear($departmentId, $semesterYear);
                            // print_r($studentStatusGeneretions);
                            $studyGeneretion = [];
                            $firstEntry = [];
                            $study = [];
                            $grad = [];
                            foreach ($studentStatusGeneretions as $statusGeneretions) {
                                $studyGeneretion[] = "รุ่น " . (string) $statusGeneretions["studyGeneretion"];
                                $firstEntry[] = (int) $statusGeneretions["firstEntry"];
                                $study[] = (int) $statusGeneretions["study"];
                                $grad[] = (int) $statusGeneretions["grad"];
                            }
                            ?>
                            <div class="card-body ">
                                <div class="row" style="padding: 20px;">
                                    <div class="col-sm-6">

                                        <canvas id="myChart"></canvas>
                                    </div>
                                    <div class="col-sm-6 float-right">
                                        <div class="table-responsive">
                                            <table class="table table-striped" cellspacing="0" style="color: black;">
                                                <thead>
                                                    <tr>
                                                        <th style=" text-align: right; ">รุ่น</th>
                                                        <th style="text-align: right; ">
                                                            <span>แรกเข้า</span>
                                                        </th>
                                                        <th style="text-align: right;"><span>กำลังศึกษา</span></th>
                                                        <th style="text-align: right;">จบการศึกษา</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <?php
                                                    $sumFisrtEntry = 0;
                                                    $sumStudy = 0;
                                                    $sumGrad = 0;
                                                    foreach($studentStatusGeneretions as $generetionCountStatus){
                                                        $sumFisrtEntry += $generetionCountStatus["firstEntry"];
                                                        $sumStudy += $generetionCountStatus["study"];
                                                        $sumGrad += $generetionCountStatus["grad"];
                                                    ?>
                                                    <tr>
                                                        <td style=" text-align: right;"><?php echo $generetionCountStatus["studyGeneretion"]?></td>
                                                        <td style=" text-align: right;">
                                                        <?php echo $generetionCountStatus["firstEntry"]?> คน
                                                        </td>
                                                        <td style=" text-align: right;">
                                                        <?php echo $generetionCountStatus["study"]?> คน
                                                        </td>
                                                        <td style=" text-align: right;"><?php echo $generetionCountStatus["grad"]?> คน</td>
                                                    </tr>
                                                    <?php
                                                    }
                                                    ?>

                                                    


                                                    <tr>
                                                        <th scope='row' style=" text-align: right;  ">
                                                            ทุกรุ่น</th>
                                                        <td style="font-weight: bold; text-align: right;"><?php echo $sumFisrtEntry ?> คน</td>
                                                        <td style='font-weight: bold; text-align: right;'><?php echo $sumStudy ?> คน</td>
                                                        <td style='font-weight: bold; text-align: right;'><?php echo $sumGrad ?> คน</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br><br>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">จำนวนนิสิตแยกตามหลักสูตร (คน)</h6>
                            </div>
                            <?php
                                $countPlanStatusSortBySemesterYears = getCountStudentPlanStatusSortBySemesterYearByDepartmentIdAndSemesterYear($departmentId, $semesterYear);
                                //print_r($countPlanStatusSortBySemesterYears);
                                $semesterLearncos=[];
                                $planLearncos=[];
                                $notPlanLearncos=[];
                                $retireLearncos=[];
                                foreach($countPlanStatusSortBySemesterYears as $planStatus){
                                    $semesterLearncos[] = (string)$planStatus["semesterYear"];
                                    $planLearncos[] = (int)$planStatus["plan"];
                                    $notPlanLearncos[] = (int)$planStatus["notPlan"];
                                    $retireLearncos[] = (int)$planStatus["retire"];
                                }
                            ?>
                            <div class="card-body ">
                                <div class="row" style="padding: 20px;">
                                    <div class="col-sm-6">

                                        <canvas id="learncos"></canvas>
                                    </div>
                                    <div class="col-sm-6 float-right">
                                        <div class="table-responsive">
                                            <table class="table table-striped" cellspacing="0" style="color: black;">
                                                <thead style=" ">
                                                    <tr>
                                                        <th style=" text-align: right; ">ปีการศึกษา</th>
                                                        <th style="text-align: right;">
                                                            <span>ตามหลักสูตร</span>
                                                        </th>
                                                        <th style="text-align: right;">
                                                            <span>ไม่ตามหลักสูตร</span>
                                                        </th>
                                                        <th style="text-align: right; ">พ้นสภาพ</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <?php
                                                    $sumPlan = 0;
                                                    $sumNotPlan = 0;
                                                    $sumRetire = 0;
                                                    foreach($countPlanStatusSortBySemesterYears as $countPlanStatusSortBySemesterYear){
                                                        $sumPlan += $countPlanStatusSortBySemesterYear["plan"];
                                                        $sumNotPlan += $countPlanStatusSortBySemesterYear["notPlan"];
                                                        $sumRetire += $countPlanStatusSortBySemesterYear["retire"];
                                                    ?>
                                                    <tr>
                                                        <td style=" text-align: right;"><?php echo $countPlanStatusSortBySemesterYear["semesterYear"]?></td>
                                                        <td style=" text-align: right;">
                                                        <?php echo $countPlanStatusSortBySemesterYear["plan"]?> คน
                                                        </td>
                                                        <td style=" text-align: right;">
                                                        <?php echo $countPlanStatusSortBySemesterYear["notPlan"]?> คน
                                                        </td>
                                                        <td style=" text-align: right;"><?php echo $countPlanStatusSortBySemesterYear["retire"]?> คน</td>
                                                    </tr>
                                                    <?php
                                                    }
                                                    ?>


                                                    <tr>
                                                        <th scope='row' style=" text-align: right;">ทุกปีการศึกษา</th>
                                                        <td style="font-weight: bold; text-align: right;"><?php echo $sumPlan ?> คน</td>
                                                        <td style='font-weight: bold; text-align: right;'><?php echo $sumNotPlan ?> คน</td>
                                                        <td style='font-weight: bold; text-align: right;'><?php echo $sumRetire ?> คน</td>
                                                    </tr>


                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <br><br>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">จำนวนนิสิตแยกตามรุ่น (คน)</h6>
                            </div>
                            <?php
                                $countPlanStatusSortByGeneretions = getCountStudentPlanStatusSortByStudyGeneretionByDepartmentAndSemesterYear($departmentId, $semesterYear);
                                //print_r($countPlanStatusSortByGeneretions);
                                $semesterGen=[];
                                $planGen=[];
                                $notPlanGen=[];
                                $retireGen=[];
                                foreach($countPlanStatusSortByGeneretions as $planStatusGen){
                                    $semesterGen[] = "รุ่น ".(string)$planStatusGen["studyGeneretion"];
                                    $planGen[] = (int)$planStatusGen["plan"];
                                    $notPlanGen[] = (int)$planStatusGen["notPlan"];
                                    $retireGen[] = (int)$planStatusGen["retire"];
                                }
                                                    
                            ?>
                            <div class="card-body ">
                                <div class="row" style="padding: 20px;">
                                    <div class="col-sm-6">

                                        <canvas id="learnyear"></canvas>
                                    </div>
                                    <div class="col-sm-6 float-right">
                                        <div class="table-responsive">
                                            <table class="table table-striped" cellspacing="0" style="color: black;">
                                                <thead style=" ">
                                                    <tr>
                                                        <th style=" text-align: right; ">รุ่น</th>
                                                        <th style="text-align: right; "><span>ตามแผน</span>
                                                        </th>
                                                        <th style="text-align: right; "><span>ไม่ตามแผน</span></th>
                                                        <th style="text-align: right; ">พ้นสภาพ</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                <?php
                                                    
                                                    $sumPlanGen = 0;
                                                    $sumNotPlanGen = 0;
                                                    $sumRetireGen = 0;
                                                    foreach($countPlanStatusSortByGeneretions as $countPlanStatusSortByGeneretion){
                                                    $sumPlanGen += $countPlanStatusSortByGeneretion["plan"];
                                                    $sumNotPlanGen += $countPlanStatusSortByGeneretion["notPlan"];
                                                    $sumRetireGen += $countPlanStatusSortByGeneretion["retire"];
                                                    
                                                    ?>
                                                    <tr>
                                                        <td style=" text-align: right;"><?php echo $countPlanStatusSortByGeneretion["studyGeneretion"] ?></td>
                                                        <td style=" text-align: right;">
                                                        <?php echo $countPlanStatusSortByGeneretion["plan"] ?> คน</td> 
                                                        </td>
                                                        <td style=" text-align: right;">
                                                        <?php echo $countPlanStatusSortByGeneretion["notPlan"] ?> คน</td> 
                                                        </td>
                                                        <td style=" text-align: right;"><?php echo $countPlanStatusSortByGeneretion["retire"] ?> คน </td>
                                                    </tr>
                                                    <?php
                                                    }
                                                    ?>


                                                    <tr>
                                                        <th scope='row' style=" text-align: right;">ทุกรุ่น</th>
                                                        <td style="font-weight: bold; text-align: right;"><?php echo $sumPlanGen ?> คน</td>
                                                        <td style='font-weight: bold; text-align: right;'><?php echo $sumNotPlanGen ?> คน</td>
                                                        <td style='font-weight: bold; text-align: right;'><?php echo $sumRetireGen ?> คน</td>
                                                    </tr>

                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- modalblue -->
                <div id="modalblue" class="modal fade" style="color: black;">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header" style="height: 90px;">
                                        <h5>รายชื่อนิสิต ช่วงเกรด 3.25-4.00 </h5>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <br>

                                    </div>
                                    <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต <?php echo sizeof($countRangeGrade["blues"])?> คน</h5>
                                    <?php
                                        if(sizeof($countRangeGrade["blues"]) > 0){
                                        
                                    ?>
                                        <div class="modal-body" id="std_detail">
                                            <table class="table">

                                                <thead>
                                                    <tr>
                                                        <th class="text-center">รหัสนิสิต</th>
                                                        <th>ชื่อ-นามสกุล</th>
                                                        <th class="text-center">GPAX</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                        foreach($countRangeGrade["blues"] as $student){
                                                    ?>
                                                    <tr>
                                                            <th class="text-center"><?php echo $student["studentId"]?></th>
                                                            <th><?php echo $student["fisrtNameTh"]." ".$student["lastNameTh"]?></th>
                                                            <th class="text-center"><?php echo $student["gpaAll"]?></th>
                                                    </tr>
                                                    <?php
                                                    }?>

                                                </tbody>
                                            </table>

                                        </div>
                                    <?php }?>
                                    
                                    <hr>
                                    
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal"
                                            style="font-size: 18px;">ปิดหน้าต่าง</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- modalgreen -->
                    <div id="modalgreen" class="modal fade" style="color: black;">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header" style="height: 90px;">
                                        <h5>รายชื่อนิสิต ช่วงเกรด 2.00-3.24 </h5>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <br>

                                    </div>
                                    <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต <?php echo sizeof($countRangeGrade["greens"])?> คน</h5>
                                    <?php
                                        if(sizeof($countRangeGrade["greens"]) > 0){
                                        
                                    ?>
                                        <div class="modal-body" id="std_detail">
                                            <table class="table">

                                                <thead>
                                                    <tr>
                                                        <th class="text-center">รหัสนิสิต</th>
                                                        <th>ชื่อ-นามสกุล</th>
                                                        <th class="text-center">GPAX</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                        foreach($countRangeGrade["greens"] as $student){
                                                    ?>
                                                        <tr>
                                                            <th class="text-center"><?php echo $student["studentId"]?></th>
                                                            <th><?php echo $student["fisrtNameTh"]." ".$student["lastNameTh"]?></th>
                                                            <th class="text-center"><?php echo $student["gpaAll"]?></th>
                                                        </tr>
                                                    <?php }?>

                                                </tbody>
                                            </table>

                                        </div>
                                    <?php }?>
                                    <hr>
                                    
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal"
                                            style="font-size: 18px;">ปิดหน้าต่าง</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- modalorange -->
                    <div id="modalorange" class="modal fade" style="color: black;">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header" style="height: 90px;">
                                        <h5>รายชื่อนิสิต ช่วงเกรด 1.75-1.99 </h5>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <br>

                                    </div>
                                    <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต <?php echo sizeof($countRangeGrade["oranges"])?> คน</h5>
                                    <?php
                                        if(sizeof($countRangeGrade["oranges"]) > 0){
                                        
                                    ?>
                                        <div class="modal-body" id="std_detail">
                                            <table class="table">

                                                <thead>
                                                    <tr>
                                                        <th class="text-center">รหัสนิสิต</th>
                                                        <th>ชื่อ-นามสกุล</th>
                                                        <th class="text-center">GPAX</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                        foreach($countRangeGrade["greens"] as $student){
                                                    ?>
                                                        <tr>
                                                            <th class="text-center"><?php echo $student["studentId"]?></th>
                                                            <th><?php echo $student["fisrtNameTh"]." ".$student["lastNameTh"]?></th>
                                                            <th class="text-center"><?php echo $student["gpaAll"]?></th>
                                                        </tr>
                                                    <?php }?>

                                                </tbody>
                                            </table>

                                        </div>
                                    <?php }?>
                                    <hr>
                                    
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal"
                                            style="font-size: 18px;">ปิดหน้าต่าง</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- modalred -->
                    <div id="modalred" class="modal fade" style="color: black;">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header" style="height: 90px;">
                                        <h5>รายชื่อนิสิต ช่วงเกรด 0.00-1.74 </h5>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <br>

                                    </div>
                                    <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต <?php echo sizeof($countRangeGrade["reds"])?> คน</h5>
                                    <?php
                                        if(sizeof($countRangeGrade["reds"]) > 0){
                                        
                                    ?>
                                        <div class="modal-body" id="std_detail">
                                            <table class="table">

                                                <thead>
                                                    <tr>
                                                        <th class="text-center">รหัสนิสิต</th>
                                                        <th>ชื่อ-นามสกุล</th>
                                                        <th class="text-center">GPAX</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                        foreach($countRangeGrade["reds"] as $student){
                                                    ?>
                                                        <tr>
                                                            <th class="text-center"><?php echo $student["studentId"]?></th>
                                                            <th><?php echo $student["fisrtNameTh"]." ".$student["lastNameTh"]?></th>
                                                            <th class="text-center"><?php echo $student["gpaAll"]?></th>
                                                        </tr>
                                                    <?php }?>
                                                    

                                                </tbody>
                                            </table>

                                        </div>
                                    <?php }?>
                                    <hr>
                                    
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal"
                                            style="font-size: 18px;">ปิดหน้าต่าง</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- modalblue2 -->
                    <div id="modalblue2" class="modal fade" style="color: black;">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header" style="height: 90px;">
                                        <h5>รายชื่อนิสิต ตามแผน </h5>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <br>

                                    </div>
                                    <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต <?php echo sizeof($countPlanStatus["plans"])?> คน</h5>
                                    <?php
                                        if(sizeof($countPlanStatus["plans"]) > 0){
                                        
                                    ?>
                                        <div class="modal-body" id="std_detail">
                                            <table class="table">

                                                <thead>
                                                    <tr>
                                                        <th class="text-center">รหัสนิสิต</th>
                                                        <th>ชื่อ-นามสกุล</th>
                                                        <th class="text-center">GPAX</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                        foreach($countPlanStatus["plans"] as $student){
                                                    ?>
                                                        <tr>
                                                            <th class="text-center"><?php echo $student["studentId"]?></th>
                                                            <th><?php echo $student["fisrtNameTh"]." ".$student["lastNameTh"]?></th>
                                                            <th class="text-center"><?php echo $student["gpaAll"]?></th>
                                                        </tr>
                                                    <?php }?>
                                                    

                                                </tbody>
                                            </table>

                                        </div>
                                    <?php }?>
                                    <hr>
                                    
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal"
                                            style="font-size: 18px;">ปิดหน้าต่าง</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- modalgreen2 -->
                    <div id="modalgreen2" class="modal fade" style="color: black;">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header" style="height: 90px;">
                                        <h5>รายชื่อนิสิต ไม่ตามแผน </h5>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <br>

                                    </div>
                                    <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต <?php echo sizeof($countPlanStatus["notPlans"])?> คน</h5>
                                    <?php
                                        if(sizeof($countPlanStatus["notPlans"]) > 0){
                                        
                                    ?>
                                        <div class="modal-body" id="std_detail">
                                            <table class="table">

                                                <thead>
                                                    <tr>
                                                        <th class="text-center">รหัสนิสิต</th>
                                                        <th>ชื่อ-นามสกุล</th>
                                                        <th class="text-center">GPAX</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                        foreach($countPlanStatus["notPlans"] as $student){
                                                    ?>
                                                        <tr>
                                                            <th class="text-center"><?php echo $student["studentId"]?></th>
                                                            <th><?php echo $student["fisrtNameTh"]." ".$student["lastNameTh"]?></th>
                                                            <th class="text-center"><?php echo $student["gpaAll"]?></th>
                                                        </tr>
                                                    <?php }?>

                                                </tbody>
                                            </table>

                                        </div>
                                    <?php }?>
                                    <hr>
                                    
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal"
                                            style="font-size: 18px;">ปิดหน้าต่าง</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- modalorange2 -->
                    <div id="modalorange2" class="modal fade" style="color: black;">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header" style="height: 90px;">
                                        <h5>รายชื่อนิสิต พ้นสภาพ </h5>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <br>

                                    </div>
                                    <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต <?php echo sizeof($countPlanStatus["retires"])?> คน</h5>
                                    <?php
                                        if(sizeof($countPlanStatus["retires"]) > 0){
                                        
                                    ?>
                                        <div class="modal-body" id="std_detail">
                                            <table class="table">

                                                <thead>
                                                    <tr>
                                                        <th class="text-center">รหัสนิสิต</th>
                                                        <th>ชื่อ-นามสกุล</th>
                                                        <th class="text-center">GPAX</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                        foreach($countPlanStatus["retires"] as $student){
                                                    ?>
                                                        <tr>
                                                            <th class="text-center"><?php echo $student["studentId"]?></th>
                                                            <th><?php echo $student["fisrtNameTh"]." ".$student["lastNameTh"]?></th>
                                                            <th class="text-center"><?php echo $student["gpaAll"]?></th>
                                                        </tr>
                                                    <?php }?>
                                                    

                                                </tbody>
                                            </table>

                                        </div>
                                    <?php }?>
                                    <hr>
                                    
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal"
                                            style="font-size: 18px;">ปิดหน้าต่าง</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- modalred2 -->
                    <div id="modalred2" class="modal fade" style="color: black;">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header" style="height: 90px;">
                                        <h5>รายชื่อนิสิต จบการศึกษา </h5>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <br>

                                    </div>
                                    <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต <?php echo sizeof($countPlanStatus["grads"])?> คน</h5>
                                    <?php
                                        if(sizeof($countPlanStatus["grads"]) > 0){
                                        
                                    ?>
                                        <div class="modal-body" id="std_detail">
                                            <table class="table">

                                                <thead>
                                                    <tr>
                                                        <th class="text-center">รหัสนิสิต</th>
                                                        <th>ชื่อ-นามสกุล</th>
                                                        <th class="text-center">GPAX</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                        foreach($countPlanStatus["grads"] as $student){
                                                    ?>
                                                        <tr>
                                                            <th class="text-center"><?php echo $student["studentId"]?></th>
                                                            <th><?php echo $student["fisrtNameTh"]." ".$student["lastNameTh"]?></th>
                                                            <th class="text-center"><?php echo $student["gpaAll"]?></th>
                                                        </tr>
                                                    <?php }?>
                                                    

                                                </tbody>
                                            </table>

                                        </div>
                                    <?php }?>
                                    <hr>
                                    
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal"
                                            style="font-size: 18px;">ปิดหน้าต่าง</button>
                                    </div>
                                </div>
                            </div>
                        </div>




                <!-- Bootstrap core JavaScript-->
                <script src="../vendor/jquery/jquery.min.js"></script>
                <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

                <!-- Core plugin JavaScript-->
                <script src="../vendor/jquery/jquery.min.js"></script>
                <script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

                <!-- Page level plugins -->
                <script src="../vendor/datatables/jquery.dataTables.min.js"></script>
                <script src="../vendor/datatables/dataTables.bootstrap4.min.js"></script>

                <!-- Page level custom scripts -->
                <script src="../js/demo/datatables-demo.js"></script>

                <!-- Custom scripts for all pages-->
                <script src="../js/sb-admin-2.min.js"></script>
                <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.js"></script>

                <!-- Bootstrap core JavaScript-->
                <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

                <!-- Core plugin JavaScript-->
                <script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

                <!-- Custom scripts for all pages-->
                <script src="../js/sb-admin-2.min.js"></script>

                <!-- Page level plugins -->
                <script src="../vendor/chart.js/Chart.min.js"></script>

                <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.js"></script>

                <script>
                    var studyGeneretions = <?php echo json_encode($studyGeneretion); ?>;

                    var firstEntrys = <?php echo json_encode($firstEntry); ?>;
                    var studys = <?php echo json_encode($study); ?>;
                    var grads = <?php echo json_encode($grad); ?>;
                    var ctx = document.getElementById("myChart");
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: studyGeneretions,
                            datasets: [{
                                label: 'นิสิตแรกเข้า',
                                data: firstEntrys,
                                backgroundColor: '#bfd575',
                                borderColor: [
                                    'rgba(150,186,169, 1)', //1
                                    'rgba(108,158,134, 1)',
                                    'rgba(66,130,100, 1)',
                                    'rgba(45,117,83, 1)',
                                    'rgba(27,70,49, 1)', //5
                                    'rgba(0, 51, 18, 1)'
                                ],
                                borderWidth: 0
                            },
                            {
                                label: 'นิสิตกำลังศึกษา',
                                data: studys,
                                backgroundColor: '#a4ebf3',
                                borderColor: [
                                    'rgba(150,186,169, 1)', //1
                                    'rgba(108,158,134, 1)',
                                    'rgba(66,130,100, 1)',
                                    'rgba(45,117,83, 1)',
                                    'rgba(27,70,49, 1)', //5
                                    'rgba(0, 51, 18, 1)'
                                ],
                                borderWidth: 0
                            },
                            {
                                label: 'นิสิตจบการศึกษา',
                                data: grads,
                                backgroundColor: '#abbdee',
                                borderColor: [
                                    'rgba(150,186,169, 1)', //1
                                    'rgba(108,158,134, 1)',
                                    'rgba(66,130,100, 1)',
                                    'rgba(45,117,83, 1)',
                                    'rgba(27,70,49, 1)', //5
                                    'rgba(0, 51, 18, 1)'
                                ],
                                borderWidth: 0
                            }

                            ]

                        },

                        options: {
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: true,
                                        min: 0
                                    }
                                }]
                            },
                            responsive: true,

                        }
                    });
                </script>

                <script>
                    var ctx = document.getElementById("myCharts");
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: ['2565', '2566'],
                            datasets: [{
                                label: 'นิสิตแรกเข้า',
                                data: [60, 60],
                                backgroundColor: '#bfd575',
                                borderColor: [
                                    'rgba(150,186,169, 1)', //1
                                    'rgba(108,158,134, 1)',
                                    'rgba(66,130,100, 1)',
                                    'rgba(45,117,83, 1)',
                                    'rgba(27,70,49, 1)', //5
                                    'rgba(0, 51, 18, 1)'
                                ],
                                borderWidth: 0
                            },
                            {
                                label: 'นิสิตพ้นสภาพ',
                                data: [0, 10],
                                backgroundColor: '#ff6962',
                                borderColor: [
                                    'rgba(150,186,169, 1)', //1
                                    'rgba(108,158,134, 1)',
                                    'rgba(66,130,100, 1)',
                                    'rgba(45,117,83, 1)',
                                    'rgba(27,70,49, 1)', //5
                                    'rgba(0, 51, 18, 1)'
                                ],
                                borderWidth: 0
                            },
                            {
                                label: 'นิสิตกำลังศึกษา',
                                data: [50, 110],
                                backgroundColor: '#a4ebf3',
                                borderColor: [
                                    'rgba(150,186,169, 1)', //1
                                    'rgba(108,158,134, 1)',
                                    'rgba(66,130,100, 1)',
                                    'rgba(45,117,83, 1)',
                                    'rgba(27,70,49, 1)', //5
                                    'rgba(0, 51, 18, 1)'
                                ],
                                borderWidth: 0
                            },
                            {
                                label: 'นิสิตจบการศึกษา',
                                data: [0, 0],
                                backgroundColor: '#abbdee',
                                borderColor: [
                                    'rgba(150,186,169, 1)', //1
                                    'rgba(108,158,134, 1)',
                                    'rgba(66,130,100, 1)',
                                    'rgba(45,117,83, 1)',
                                    'rgba(27,70,49, 1)', //5
                                    'rgba(0, 51, 18, 1)'
                                ],
                                borderWidth: 0
                            }

                            ]

                        },

                        options: {
                            scales: {
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: true,
                                        max: 150,
                                        min: 0
                                    }
                                }]
                            },
                            responsive: true,

                        }
                    });
                </script>

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js"></script>
                <script src="https://cdn.jsdelivr.net/npm/chart.js@4.0.1/dist/chart.umd.min.js"></script>

                <script>
                    var p1gen = <?php echo json_encode($pee1gen); ?>;

                    var p1blue = <?php echo json_encode($pee1blues); ?>;
                    var p1green = <?php echo json_encode($pee1greens); ?>;
                    var p1orange = <?php echo json_encode($pee1oranges); ?>;
                    var p1red = <?php echo json_encode($pee1reds); ?>;

                    var ctx = document.getElementById("pee1");
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: p1gen,
                            datasets: [{
                                label: '3.25-4.00',
                                data: p1blue,
                                backgroundColor: "rgba(0, 9, 188,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '2.00-3.24',
                                data: p1green,
                                backgroundColor: "rgba(0, 110, 22,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '1.75-1.99',
                                data: p1orange,
                                backgroundColor: 'rgba(255,128,0,0.7)',
                                borderWidth: 0
                            },
                            {
                                label: '0.00-1.74',
                                data: p1red,
                                backgroundColor: 'rgba(255, 0, 0,0.7)',
                                borderWidth: 0
                            }
                            ]
                        },
                        options: {
                            responsive: true,
                            scales: {
                                x: {
                                    stacked: true,
                                },
                                y: {
                                    stacked: true
                                }
                            }

                        }
                    });
                </script>

                <script>
                    var p2genh = <?php echo json_encode($pee2genh); ?>;

                    var p2blueh = <?php echo json_encode($pee2bluesh); ?>;
                    var p2greenh = <?php echo json_encode($pee2greensh); ?>;
                    var p2orangeh = <?php echo json_encode($pee2orangesh); ?>;
                    var p2redh = <?php echo json_encode($pee2redsh); ?>;
                    var ctx = document.getElementById("pee2");
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: p2genh,
                            datasets: [{
                                label: '3.25-4.00',
                                data: p2blueh,
                                backgroundColor: "rgba(0, 9, 188,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '2.00-3.24',
                                data: p2greenh,
                                backgroundColor: "rgba(0, 110, 22,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '1.75-1.99',
                                data: p2orangeh,
                                backgroundColor: 'rgba(255,128,0,0.7)',
                                borderWidth: 0
                            },
                            {
                                label: '0.00-1.74',
                                data: p2redh,
                                backgroundColor: 'rgba(255, 0, 0,0.7)',
                                borderWidth: 0
                            }
                            ]
                        },
                        options: {
                            responsive: true,
                            scales: {
                                x: {
                                    stacked: true,
                                },
                                y: {
                                    stacked: true
                                }
                            }

                        }
                    });
                </script>
                <script>
                    var p3gen = <?php echo json_encode($pee3gen); ?>;

                    var p3blue = <?php echo json_encode($pee3blues); ?>;
                    var p3green = <?php echo json_encode($pee3greens); ?>;
                    var p3orange = <?php echo json_encode($pee3oranges); ?>;
                    var p3red = <?php echo json_encode($pee3reds); ?>;
                    var ctx = document.getElementById("pee3");
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: p3gen,
                            datasets: [{
                                label: '3.25-4.00',
                                data: p3blue,
                                backgroundColor: "rgba(0, 9, 188,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '2.00-3.24',
                                data: p3green,
                                backgroundColor: "rgba(0, 110, 22,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '1.75-1.99',
                                data: p3orange,
                                backgroundColor: 'rgba(255,128,0,0.7)',
                                borderWidth: 0
                            },
                            {
                                label: '0.00-1.74',
                                data: p3red,
                                backgroundColor: 'rgba(255, 0, 0,0.7)',
                                borderWidth: 0
                            }
                            ]
                        },
                        options: {
                            responsive: true,
                            scales: {
                                x: {
                                    stacked: true,
                                },
                                y: {
                                    stacked: true
                                }
                            }

                        }
                    });
                </script>
                <script>
                    var p4gen = <?php echo json_encode($pee4gen); ?>;

                    var p4blue = <?php echo json_encode($pee4blues); ?>;
                    var p4green = <?php echo json_encode($pee4greens); ?>;
                    var p4orange = <?php echo json_encode($pee4oranges); ?>;
                    var p4red = <?php echo json_encode($pee4reds); ?>;
                    var ctx = document.getElementById("pee4");
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: p4gen,
                            datasets: [{
                                label: '3.25-4.00',
                                data: p4blue,
                                backgroundColor: "rgba(0, 9, 188,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '2.00-3.24',
                                data: p4green,
                                backgroundColor: "rgba(0, 110, 22,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '1.75-1.99',
                                data: p4orange,
                                backgroundColor: 'rgba(255,128,0,0.7)',
                                borderWidth: 0
                            },
                            {
                                label: '0.00-1.74',
                                data: p4red,
                                backgroundColor: 'rgba(255, 0, 0,0.7)',
                                borderWidth: 0
                            }
                            ]
                        },
                        options: {
                            responsive: true,
                            scales: {
                                x: {
                                    stacked: true,
                                },
                                y: {
                                    stacked: true
                                }
                            }

                        }
                    });
                </script>

                <script>
                    var p4plusgen = <?php echo json_encode($pee4plusgen); ?>;

                    var p4plusblue = <?php echo json_encode($pee4plusblues); ?>;
                    var p4plusgreen = <?php echo json_encode($pee4plusgreens); ?>;
                    var p4plusorange = <?php echo json_encode($pee4plusoranges); ?>;
                    var p4plusred = <?php echo json_encode($pee4plusreds); ?>;
                    var ctx = document.getElementById("pee4plus");
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: p4plusgen,
                            datasets: [{
                                label: '3.25-4.00',
                                data: p4plusblue,
                                backgroundColor: "rgba(0, 9, 188,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '2.00-3.24',
                                data: p4plusgreen,
                                backgroundColor: "rgba(0, 110, 22,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '1.75-1.99',
                                data: p4plusorange,
                                backgroundColor: 'rgba(255,128,0,0.7)',
                                borderWidth: 0
                            },
                            {
                                label: '0.00-1.74',
                                data: p4plusred,
                                backgroundColor: 'rgba(255, 0, 0,0.7)',
                                borderWidth: 0
                            }
                            ]
                        },
                        options: {
                            responsive: true,
                            scales: {
                                x: {
                                    stacked: true,
                                },
                                y: {
                                    stacked: true
                                }
                            }

                        }
                    });
                </script>
                <script>
                    var gennow = <?php echo json_encode($nowgen); ?>;

                    var bluegen = <?php echo json_encode($BNG); ?>;
                    var greengen = <?php echo json_encode($GNG); ?>;
                    var orangegen = <?php echo json_encode($ONG); ?>;
                    var redgen = <?php echo json_encode($RNG); ?>;


                    var ctx = document.getElementById("learn");
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: gennow,
                            datasets: [{
                                label: '3.25-4.00',
                                data: bluegen,
                                backgroundColor: "rgba(0, 9, 188,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '2.00-3.24',
                                data: greengen,
                                backgroundColor: "rgba(0, 110, 22,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '1.75-1.99',
                                data: orangegen,
                                backgroundColor: 'rgba(255,128,0,0.7)',
                                borderWidth: 0
                            },
                            {
                                label: '0.00-1.74',
                                data: redgen,
                                backgroundColor: 'rgba(255, 0, 0,0.7)',
                                borderWidth: 0
                            }
                            ]
                        },
                        options: {
                            responsive: true,
                            scales: {
                                x: {
                                    stacked: true,
                                },
                                y: {
                                    stacked: true
                                }
                            }

                        }
                    });
                </script>

                <script>
                    var genend = <?php echo json_encode($endgen); ?>;

                    var bluegenend = <?php echo json_encode($BEG); ?>;
                    var greengenend = <?php echo json_encode($GEG); ?>;
                    var orangegenend = <?php echo json_encode($OEG); ?>;
                    var redgenend = <?php echo json_encode($REG); ?>;


                    var ctx = document.getElementById("learn2");
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: genend,
                            datasets: [{
                                label: '3.25-4.00',
                                data: bluegenend,
                                backgroundColor: "rgba(0, 9, 188,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '2.00-3.24',
                                data: greengenend,
                                backgroundColor: "rgba(0, 110, 22,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '1.75-1.99',
                                data: orangegenend,
                                backgroundColor: 'rgba(255,128,0,0.7)',
                                borderWidth: 0
                            },
                            {
                                label: '0.00-1.74',
                                data: redgenend,
                                backgroundColor: 'rgba(255, 0, 0,0.7)',
                                borderWidth: 0
                            }
                            ]
                        },
                        options: {
                            responsive: true,
                            scales: {
                                x: {
                                    stacked: true,
                                },
                                y: {
                                    stacked: true
                                }
                            }

                        }
                    });
                </script>
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js">
                </script>
                <script src="https://cdn.jsdelivr.net/npm/chart.js@4.0.1/dist/chart.umd.min.js">
                </script>
                <script>
                    
                    var semesterLearncos = <?php echo json_encode($semesterLearncos); ?>;

                    var planLearncos = <?php echo json_encode($planLearncos); ?>;
                    var notPlanLearncos = <?php echo json_encode($notPlanLearncos); ?>;
                    var retireLearncos = <?php echo json_encode($retireLearncos); ?>;

                    var ctx = document.getElementById("learncos");
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: semesterLearncos,
                            datasets: [{
                                label: 'ตามหลักสูตร',
                                data: planLearncos,
                                backgroundColor: "rgba(100, 197, 215,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: ['ไม่ตามหลักสุตร'],
                                data: notPlanLearncos,
                                backgroundColor: "rgba(118, 188, 22,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: ['พ้นสภาพ'],
                                data: retireLearncos,
                                backgroundColor: 'rgba(245, 123, 57,0.7)',
                                borderWidth: 0
                            }
                            ]
                        },
                        options: {
                            responsive: true,
                            scales: {
                                x: {
                                    stacked: true,
                                },
                                y: {
                                    stacked: true
                                }
                            }

                        }
                    });
                </script>

                <!-- ปิด script กราฟด้านบน -->
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js">
                </script>
                <script src="https://cdn.jsdelivr.net/npm/chart.js@4.0.1/dist/chart.umd.min.js">
                </script>

                <script>
                    var semesterGen = <?php echo json_encode($semesterGen); ?>;

                    var planGen = <?php echo json_encode($planGen); ?>;
                    var notPlanGen = <?php echo json_encode($notPlanGen); ?>;
                    var retireGen = <?php echo json_encode($retireGen); ?>;

                    var ctx = document.getElementById("learnyear");
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: semesterGen,
                            datasets: [
                                {
                                    label: 'ตามหลักสูตร',
                                    data: planGen,
                                    backgroundColor: "rgba(100, 197, 215,0.7)",
                                    borderWidth: 0
                                },
                                {
                                    label: ['ไม่ตามหลักสุตร'],
                                    data: notPlanGen,
                                    backgroundColor: "rgba(118, 188, 22,0.7)",
                                    borderWidth: 0
                                },
                                {
                                    label: ['พ้นสภาพ'],
                                    data: retireGen,
                                    backgroundColor: 'rgba(245, 123, 57,0.7)',
                                    borderWidth: 0
                                }
                            ]
                        },
                        options: {
                            responsive: true,
                            scales: {
                                x: {
                                    stacked: true,
                                },
                                y: {
                                    stacked: true
                                }
                            }

                        }
                    });
                </script>


</body>

</html>