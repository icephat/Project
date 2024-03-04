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

<?php


session_start();

require_once '../function/teacherFunction.php';
require_once '../function/semesterFunction.php';
require_once '../function/courseFunction.php';

$teacher = getTeacherByUsernameTeacher($_SESSION["access-user"]);
$semester = getSemesterPresent();

?>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <?php include('../layout/dean/home.php'); ?>

                <!--<div>
                        <form class="form-valide" action="homeSearch.php" method="post" enctype="multipart/form-data">
                            <div class="row mx-auto">
                                <div class="col-sm-3 text-center">
                                    <h5>หลักสูตร<span style="color: red;">*</span></th>
                                </div>
                                <div class="col-sm-3 text-center">
                                    <h5>ปีการศึกษา<span style="color: red;">*</span></th>
                                </div>
                                <div class="col-sm-3 text-center">
                                    <h5>ภาคการศึกษา<span style="color: red;">*</span></th>
                                </div>
                                <div class="col-sm-3 text-center">
                                    <h5><span style="color: red;"></span></th>
                                </div>

                            </div>
                            <div class="row mx-auto">
                                <div class="col-sm-3 text-center">
                                    <div>
                                        <select class="form-control" data-live-search="true" name = "courseId" >
                                            <option value="default">--กรุณาเลือกหลักสูตร--</option>

                                            <?php
                                            $courses = getCourseByDepartmentId($teacher["teacherId"]);

                                            foreach ($courses as $course) {

                                                ?>

                                            <option value="<?php echo $course["courseId"] ?>"><?php echo $course["nameCourseUse"] . " ( " . $course["planCourse"] . " )" ?>
                                            </option>
                                            
                                            <?php

                                            }
                                            ?>
                                        </select>
                                    </div>

                                </div>
                                <div class="col-sm-3 text-center">
                                    <div>
                                        <select class="form-control" data-live-search="true" name = "year">

                                            <option value="default">--กรุณาเลือกปีการศึกษา--</option>
                                            <?php
                                            $years = getSemesterYear();

                                            foreach ($years as $year) {
                                                ?>

                                            <option value="<?php echo $year["semesterYear"] ?>"><?php echo $year["semesterYear"] ?></option>
                                            <?php

                                            }
                                            ?>
                                            
                                        </select>
                                    </div>

                                </div>
                                <div class="col-sm-3 text-center">
                                    <div>
                                        <select class="form-control" data-live-search="true" name = "part">
                                            <option value="default">--ภาคการศึกษา--</option>

                                            <option value="ภาคต้น">ภาคต้น
                                            </option>
                                            <option value="ภาคปลาย">ภาคปลาย</option>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-3 text-center">
                                    <button type="submit" name="submit" id="data"
                                        class="btn btn-success btn-sm active">ดูผล</button>
                                </div>
                            </div>
                        </form>
                    </div>

                    <hr>
                    <div class="row">
                        <div class="col-10" style="color: black;">
                            <?php ?>
                            <h5>ปีการศึกษา: <?php echo $semester["semesterYear"] ?> &nbsp; ภาคการศึกษา: <?php echo $semester["semesterPart"] ?> </h5>

                        </div>

                    </div>-->

                <div class="row">
                    <div class="col-sm-12 ">
                        <div class="row">
                            <div class="col-sm-5 mx-auto">
                                <table class="table "
                                    style="margin-top: 30px; border: 1px solid black; border-collapse: collapse; ">
                                    <tr style="border: 1px solid black; border-collapse: collapse; ">
                                        <th style="border: 1px solid black; border-collapse: collapse; width: 50%; ">

                                            <?php

                                            $gpaxStatusCount = getCountStudentGPAXStatusByTeacherId($teacher["teacherId"]);
                                            
                                            ?>

                                            <div style="color: rgb(0, 9, 188);">
                                                <div class="text-center">
                                                    <a style="color: rgb(0, 9, 188);" href="#" data-toggle="modal"
                                                        data-target="#modalblue">
                                                        <h4>3.25-4.00</h4>
                                                    </a>
                                                </div>
                                                <div class="text-center">
                                                    <h1 style="font-weight: bolder; font-size: 70px; ">
                                                        <?php echo $gpaxStatusCount["blue"] ?>
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
                                                    <a style="color: rgb(0, 110, 22);" href="#" data-toggle="modal"
                                                        data-target="#modalgreen">
                                                        <h4>2.00-3.24</h4>
                                                    </a>
                                                </div>
                                                <div class="text-center">
                                                    <h1 style="font-weight: bolder; font-size: 70px;">
                                                        <?php echo $gpaxStatusCount["green"] ?>
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
                                                    <a style="color: #ff8c00;" href="#" data-toggle="modal"
                                                        data-target="#modalorange">
                                                        <h4>1.75-1.99</h4>
                                                    </a>
                                                </div>
                                                <div class="text-center">
                                                    <h1 style="font-weight: bolder; font-size: 70px;">
                                                        <?php echo $gpaxStatusCount["orange"] ?>
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
                                                    <a style="color: rgb(255, 0, 0);" href="#" data-toggle="modal"
                                                        data-target="#modalred">
                                                        <h4>0.00-1.74</h4>
                                                    </a>
                                                </div>
                                                <div class="text-center">
                                                    <h1 style="font-weight: bolder; font-size: 70px;">
                                                        <?php echo $gpaxStatusCount["red"] ?>
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
                                <table class="table "
                                    style="margin-top: 30px; border: 1px solid black; border-collapse: collapse;">
                                    <tr style="border: 1px solid black; border-collapse: collapse;">
                                        <th style="border: 1px solid black; border-collapse: collapse; width: 50%;">
                                            <?php

                                            $planingCount = getCountStudentByPlaningByTeacherId($teacher["teacherId"]);



                                            ?>

                                            <div style="color: rgb(100, 197, 215);">
                                                <div class="text-center">
                                                    <a style="color:  rgb(100, 197, 215);" href="#" data-toggle="modal"
                                                        data-target="#modalblue2">
                                                        <h4>ตามแผน</h4>
                                                    </a>
                                                </div>
                                                <div class="text-center">
                                                    <h1 style="font-weight: bolder; font-size: 70px; ">
                                                        <?php echo $planingCount["plan"] ?>
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
                                                    <a style="color: rgb(	118, 188, 22);" href="#" data-toggle="modal"
                                                        data-target="#modalgreen2">
                                                        <h4>ไม่ตามแผน</h4>
                                                    </a>
                                                </div>
                                                <div class="text-center">
                                                    <h1 style="font-weight: bolder; font-size: 70px;">
                                                        <?php echo $planingCount["notPlan"] ?>
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
                                                    <a style="color: rgb(	245, 123, 57);" href="#" data-toggle="modal"
                                                        data-target="#modalorange2">
                                                        <h4>พ้นสภาพ</h4>
                                                    </a>
                                                </div>
                                                <div class="text-center">
                                                    <h1 style="font-weight: bolder; font-size: 70px;">
                                                        <?php echo $planingCount["retire"] ?>
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
                                                    <a style="color: rgb(255, 105, 98);" href="#" data-toggle="modal"
                                                        data-target="#modalred2">
                                                        <h4>จบการศึกษา</h4>
                                                    </a>
                                                </div>
                                                <div class="text-center">
                                                    <h1 style="font-weight: bolder; font-size: 70px;">
                                                        <?php echo $planingCount["grad"] ?>
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
                <br>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">จำนวนนิสิตแยกตามรุ่น (คน)</h6>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-6">

                                        <canvas id="learnyear"></canvas>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="table-responsive">
                                            <table class="table table-striped" width="100%" cellspacing="0"
                                                style="color: black;">
                                                <thead>
                                                    <tr>
                                                        <th style=" text-align: right; ">รุ่น</th>
                                                        <th style="text-align: right; ">
                                                            <span>ตามแผน</span>
                                                        </th>
                                                        <th style="text-align: right; ">
                                                            <span>ไม่ตามแผน</span>
                                                        </th>
                                                        <th style="text-align: right; ">พ้นสภาพ</th>
                                                        <th style="text-align: right; ">จบการศึกษา</th>
                                                        <th style="text-align: center; ">รายละเอียด</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php

                                                    $gens = getCountStudentPlanStatusBystudyGeneretionByTeacherId($teacher["teacherId"]);
                                                    $listgensh = [];
                                                    $sumPlan = 0;
                                                    $sumNotPlan = 0;
                                                    $sumRetire = 0;
                                                    $sumGrad = 0;
                                                    $sPLanh = [];
                                                    $sNotPlanh = [];
                                                    $sRetireh = [];
                                                    $sGradh = [];
                                                    $idtermplan = 0;
                                                    foreach ($gens as $gen) { ?>

                                                        <tr>
                                                            <td style=" text-align: right;">
                                                                <?php echo $gen["studyGeneretion"] ?>
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $gen["planCount"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $gen["notPlanCount"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $gen["retire"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $gen["grad"] ?> คน
                                                            </td>
                                                            <td class="text-center">
                                                                <a data-toggle="modal"
                                                                    data-target="#modalPlan<?php echo $idtermplan ?>">
                                                                    <i class="fas fa-search fa-sm"></i>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <?php
                                                        $idtermplan++;
                                                        $listgensh[] = "รุ่น " . (string) $gen["studyGeneretion"];
                                                        $sumPlan += $gen["planCount"];
                                                        $sPLanh[] = (int) $sumPlan;
                                                        $sumNotPlan += $gen["notPlanCount"];
                                                        $sNotPlanh[] = (int) $sumNotPlan;
                                                        $sumRetire = $gen["retire"];
                                                        $sRetireh[] = (int) $sumRetire;
                                                        $sumGrad = $gen["grad"];
                                                        $sGradh[] = (int) $sumGrad;

                                                        //รายชื่อนิสิต ตามแผน ไม่ตามแผน พ้นสภาพนิสิต จบการศึกษา
                                                        //$gen["studentPlans"]
                                                        //$gen["studentNotPlans"]
                                                        //$gen["studentRetires"]
                                                        //$gen["studentGrads"]
                                                    }

                                                    ?>
                                                    <!-- <tr>
                                                            <td style=" text-align: right;">61</td>
                                                            <td style=" text-align: right;">
                                                                0 คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                1 คน
                                                            </td>
                                                            <td style=" text-align: right;">0 คน</td>
                                                            <td style=" text-align: right;">0 คน</td>
                                                        </tr>
                                                        <tr>
                                                            <td style=" text-align: right;">62</td>
                                                            <td style=" text-align: right;">
                                                                0 คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                2 คน
                                                            </td>
                                                            <td style=" text-align: right;">0 คน</td>
                                                            <td style=" text-align: right;">0 คน</td>
                                                        </tr>
                                                        <tr>
                                                            <td style=" text-align: right;">63</td>
                                                            <td style=" text-align: right;">
                                                                9 คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                1 คน
                                                            </td>
                                                            <td style=" text-align: right;">0 คน</td>
                                                            <td style=" text-align: right;">0 คน</td>
                                                        </tr>
                                                        <tr>
                                                            <td style=" text-align: right;">64</td>
                                                            <td style=" text-align: right;">
                                                                5 คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                5 คน
                                                            </td>
                                                            <td style=" text-align: right;">0 คน</td>
                                                            <td style=" text-align: right;">0 คน</td>
                                                        </tr>
                                                        <tr>
                                                            <td style=" text-align: right;">65</td>
                                                            <td style=" text-align: right;">
                                                                9 คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                1 คน
                                                            </td>
                                                            <td style=" text-align: right;">0 คน</td>
                                                            <td style=" text-align: right;">0 คน</td>
                                                        </tr>
                                                        <tr>
                                                            <td style=" text-align: right;">66</td>
                                                            <td style=" text-align: right;">
                                                                10 คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                0 คน
                                                            </td>
                                                            <td style=" text-align: right;">0 คน</td>
                                                            <td style=" text-align: right;">0 คน</td>
                                                        </tr> -->

                                                    <tr>
                                                        <th scope='row' style=" text-align: right;">รวม (คน)</th>
                                                        <td style="font-weight: bold; text-align: right;">
                                                            <?php echo $sumPlan ?>
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumNotPlan ?>
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumRetire ?>
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumGrad ?>
                                                        </td>
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

                <div class="row">
                    <div class="col-sm-4">
                        <div class="card">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">ช่วงเกรดนิสิตปีที่ 1</h6>
                            </div>
                            <?php

                            $day = date("Y");
                            $thaiDay = 543 + $day;
                            //echo substr($thaiDay-4, -2);
                            $y = substr($thaiDay - 4, -2);
                            $yNow = substr($thaiDay, -2);
                            $rangeGradeStudyYearOnes = getCountGradeRangeByTeacherIdAndStudyYear($teacher["teacherId"], 1);
                            $pee1gen = [];
                            $pee1blues = [];
                            $pee1greens = [];
                            $pee1oranges = [];
                            $pee1reds = [];

                            //for($y; $y<$yNow; $y++){
                            
                            foreach ($rangeGradeStudyYearOnes as $range) {
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
                            <div class="card-body">
                                <canvas id="pee1"></canvas>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="card">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">ช่วงเกรดนิสิตปีที่ 2</h6>
                            </div>
                            <?php
                            $day = date("Y");
                            $thaiDay = 543 + $day;
                            //echo substr($thaiDay-4, -2);
                            $y = substr($thaiDay - 4, -2);
                            $yNow = substr($thaiDay, -2);
                            $rangeGradeStudyYearOnes = getCountGradeRangeByTeacherIdAndStudyYear($teacher["teacherId"], 2);
                            $pee2genh = [];
                            $pee2bluesh = [];
                            $pee2greensh = [];
                            $pee2orangesh = [];
                            $pee2redsh = [];
                            //for($y; $y<$yNow; $y++){
                            foreach ($rangeGradeStudyYearOnes as $range) {
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

                            // }
                            ?>
                            <div class="card-body">
                                <canvas id="pee2"></canvas>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-4">
                        <div class="card">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">ช่วงเกรดนิสิตปีที่ 3</h6>
                            </div>
                            <?php
                            $day = date("Y");
                            $thaiDay = 543 + $day;
                            //echo substr($thaiDay-4, -2);
                            $y = substr($thaiDay - 4, -2);
                            $yNow = substr($thaiDay, -2);
                            $rangeGradeStudyYearOnes = getCountGradeRangeByTeacherIdAndStudyYear($teacher["teacherId"], 3);
                            $pee3gen = [];
                            $pee3blues = [];
                            $pee3greens = [];
                            $pee3oranges = [];
                            $pee3reds = [];

                            //for($y; $y<$yNow; $y++){
                            foreach ($rangeGradeStudyYearOnes as $range) {
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
                            <div class="card-body">
                                <canvas id="pee3"></canvas>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4" style="margin-top: 25px;">
                        <div class="card">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">ช่วงเกรดนิสิตปีที่ 4</h6>
                            </div>
                            <?php
                            $day = date("Y");
                            $thaiDay = 543 + $day;
                            //echo substr($thaiDay-4, -2);
                            $y = substr($thaiDay - 4, -2);
                            $yNow = substr($thaiDay, -2);
                            $rangeGradeStudyYearOnes = getCountGradeRangeByTeacherIdAndStudyYear($teacher["teacherId"], 4);
                            $pee4gen = [];
                            $pee4blues = [];
                            $pee4greens = [];
                            $pee4oranges = [];
                            $pee4reds = [];
                            //for($y; $y<$yNow; $y++){
                            
                            foreach ($rangeGradeStudyYearOnes as $range) {
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
                            <div class="card-body">
                                <canvas id="pee4"></canvas>
                            </div>
                        </div>
                    </div>

                    <div class="col-sm-4" style="margin-top: 25px;">
                        <div class="card">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">ช่วงเกรดนิสิตปีที่ 4+</h6>
                            </div>
                            <?php
                            $day = date("Y");
                            $thaiDay = 543 + $day;
                            //echo substr($thaiDay-4, -2);
                            $y = substr($thaiDay - 4, -2);
                            $yNow = substr($thaiDay, -2);
                            $rangeGradeStudyYearOnes = getCountGradeRangeByTeacherIdAndStudyYear($teacher["teacherId"], 4);
                            $pee4plusgen = [];
                            $pee4plusblues = [];
                            $pee4plusgreens = [];
                            $pee4plusoranges = [];
                            $pee4plusreds = [];
                            //for($y; $y<$yNow; $y++){
                            
                            foreach ($rangeGradeStudyYearOnes as $range) {
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
                            <div class="card-body">
                                <canvas id="pee4plus"></canvas>
                            </div>
                        </div>
                    </div>

                </div>
                <br><br>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">จำนวนนิสิตแยกตามปีการศึกษา (คน)
                                </h6>
                            </div>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-6">

                                        <canvas id="learnnew"></canvas>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="table-responsive">
                                            <table class="table table-striped" width="100%" cellspacing="0"
                                                style="color: black;">
                                                <thead>
                                                    <tr>
                                                        <th style=" text-align: right; ">ปีการศึกษา</th>
                                                        <th style=" text-align: right; ">เทอม</th>
                                                        <th style="text-align: right; ">
                                                            <span>ตามหลักสูตร</span>
                                                        </th>
                                                        <th style="text-align: right; ">
                                                            <span>ไม่ตามหลักสูตร</span>
                                                        </th>
                                                        <th style="text-align: right; ">พ้นสภาพ</th>
                                                        <th style="text-align: center; ">รายละเอียด</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php

                                                    $countStudySemesters = getCountStudySemesterYearPartByTeacherID($teacher["teacherId"]);
                                                    $sortYears = [];
                                                    $plans = [];
                                                    $notPlan = [];
                                                    $resignPlan = [];
                                                    $idterm = 0;

                                                    foreach ($countStudySemesters as $countStudySemester) {

                                                        $sortYears[] = (string) $countStudySemester["semesterYear"] . " " . (string) $countStudySemester["semesterPart"];
                                                        $plans[] = (int) $countStudySemester["planStatus"];
                                                        $notPlan[] = (int) $countStudySemester["notPlanStatus"];
                                                        $resignPlan[] = (int) $countStudySemester["resign"];


                                                        ?>
                                                        <tr>
                                                            <td style=" text-align: right;">
                                                                <?php echo $countStudySemester["semesterYear"] ?>
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $countStudySemester["semesterPart"] ?>
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $countStudySemester["planStatus"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $countStudySemester["notPlanStatus"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $countStudySemester["resign"] ?> คน
                                                            </td>
                                                            <td class="text-center">
                                                                <a data-toggle="modal"
                                                                    data-target="#modal<?php echo $idterm ?>">
                                                                    <i class="fas fa-search fa-sm"></i>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <?php
                                                        $idterm++;
                                                        //กลับมา echo ดู
                                                        //echo print_r(countStudySemester["studentPlans"]); รายชื่อนิสิตที่ตามแผน
                                                        //echo print_r(countStudySemester["studentNotPlans"]); รายชื่อนิสิตที่ไม่ตามแผน
                                                        //echo print_r(countStudySemester["studentResign"]); รายชื่อนิสิตที่ลาออก
                                                    
                                                    }






                                                    ?>

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
                    <div class="col-sm-6">
                        <div class="card">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">สถานภาพนิสิต ณ ปัจจุบัน</h6>
                            </div>
                            <?php
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
                            $studyGeneretionGPAXs = getGPAXStatusGerenetionByTeacherId($teacher["teacherId"]);
                            for ($y; $y < $yNow; $y++) {
                                foreach ($studyGeneretionGPAXs as $grade) {
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
                            }


                            ?>
                            <div class="card-body">
                                <canvas id="learn"></canvas>
                            </div>
                        </div>
                    </div>
                    <div class="col-sm-6">
                        <div class="card">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">สถานภาพนิสิตจบการศึกษา </h6>
                            </div>
                            <?php

                            $endgen = [];
                            $BEG = [];
                            $GEG = [];
                            $OEG = [];
                            $REG = [];
                            $studyGraduateGeneretionGPAXs = getGPAXStatusGerenetionGraduateByTeacherId($teacher["teacherId"]);
                            foreach ($studyGraduateGeneretionGPAXs as $gradeEnd) {
                                $endgen[] = "รุ่น " . (string) $gradeEnd["studyGeneretion"];
                                $BEG[] = (int) $gradeEnd["blue"];
                                $GEG[] = (int) $gradeEnd["green"];
                                $OEG[] = (int) $gradeEnd["orange"];
                                $REG[] = (int) $gradeEnd["red"];
                            }
                            ?>
                            <div class="card-body">
                                <canvas id="learn2"></canvas>
                            </div>
                        </div>
                    </div>

                </div>
                <br><br>

                <!-- <div class="row">
                        <div class="col-sm-12">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">รายชื่อนิสิต
                                    </h6>
                                </div>
                                <div class="table-responsive">
                                    <table class="table table-striped" id="dataTable" cellspacing="0"
                                        style="color: black;  ">
                                        <thead>
                                            <tr>

                                                <th>รหัสนิสิต</th>
                                                <th>ชื่อ-นามสกุล</th>
                                                <th>GPA</th>
                                                <th>สถานะ</th>

                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>6320500603</td>
                                                <td>ภัทรพร ปัญญาอุดมพร</td>
                                                <td>3.38</td>
                                                <td style="color: rgb(0, 110, 22); font-weight: bold;">ปกติ</td>
                                            </tr>
                                            <tr>
                                                <td>6320500611</td>
                                                <td>ภานุวัฒน์ จั่นจินดา</td>
                                                <td>3.65</td>
                                                <td style="color: rgb(0, 9, 188); font-weight: bold;">เกียรตินิยม</td>
                                            </tr>
                                        </tbody>

                                    </table>
                                </div>

                            </div>

                        </div>

                    </div> -->

                <!-- modalblue -->
               
                <div id="modalblue" class="modal fade" style="color: black;">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header" style="height: 90px;">
                                <h5>รายชื่อนิสิต ช่วงเกรด 3.25-4.00 </h5>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <br>

                            </div>
                            <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต <?php echo $gpaxStatusCount["blue"]?> คน</h5>
                            <?php
                                if((int)$gpaxStatusCount["blue"] > 0){
                                        
                            ?>
                            <div class="modal-body" id="std_detail">
                                <table class="table">

                                    <thead>
                                        <tr>
                                            <th class="text-center">รหัสนิสิต</th>
                                            <th>ชื่อ-นามสกุล</th>
                                            <th class="text-center">GPAX</th>
                                            <th class="text-center">รายละเอียด</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <?php
                                            foreach($gpaxStatusCount["blues"] as $student){
                                               
                                        ?>
                                        <tr>
                                            <th class="text-center"><?php echo $student["studentId"]?></th>
                                            <th><?php echo $student["fisrtNameTh"]." ".$student["lastNameTh"]?></th>
                                            <th class="text-center"><?php echo number_format($student["gpaAll"], 2, '.', '');?></th>
                                            <th class="text-center">
                                                <form action="./student_info.php" method = "post">
                                                    <input type="hidden" name="studentId" value="<?php echo $student["studentId"]; ?>" />
                                                    <!--<a type="button" name="std_info">
                                <span class="glyphicon glyphicon-user"></span></a>-->
                                                    <button type="submit" name="submit"
                                                        class="btn btn-info btn-md"><span
                                                            class="glyphicon glyphicon-user"></span> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                    </svg></button>
                                                </form>
                                            </th>
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
                        <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต <?php echo $gpaxStatusCount["green"]?> คน</h5>
                        <?php
                                if((int)$gpaxStatusCount["green"] > 0){
                                        
                            ?>
                        <div class="modal-body" id="std_detail">
                            <table class="table">

                                <thead>
                                    <tr>
                                        <th class="text-center">รหัสนิสิต</th>
                                        <th>ชื่อ-นามสกุล</th>
                                        <th class="text-center">GPAX</th>
                                        <th class="text-center">รายละเอียด</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                        foreach($gpaxStatusCount["greens"] as $student){
                                    ?>
                                        <tr>
                                            <th class="text-center"><?php echo $student["studentId"]?></th>
                                            <th><?php echo $student["fisrtNameTh"]." ".$student["lastNameTh"]?></th>
                                            <th class="text-center"><?php echo number_format($student["gpaAll"], 2, '.', '');?></th>
                                            <th class="text-center">
                                                <form action="./student_info.php" method = "post">
                                                    <input type="hidden" name="studentId" value="<?php echo $student["studentId"]; ?>" />
                                                    <!--<a type="button" name="std_info">
                                <span class="glyphicon glyphicon-user"></span></a>-->
                                                    <button type="submit" name="submit"
                                                        class="btn btn-info btn-md"><span
                                                            class="glyphicon glyphicon-user"></span> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                    </svg></button>
                                                </form>
                                            </th>
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
                    <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต <?php echo $gpaxStatusCount["orange"]?> คน</h5>
                    <?php
                        if((int)$gpaxStatusCount["orange"] > 0){
                                    
                    ?>
                    <div class="modal-body" id="std_detail">
                        <table class="table">

                            <thead>
                                <tr>
                                    <th class="text-center">รหัสนิสิต</th>
                                    <th>ชื่อ-นามสกุล</th>
                                    <th class="text-center">GPAX</th>
                                    <th class="text-center">รายละเอียด</th>
                                </tr>
                            </thead>
                            <tbody>
                                <?php
                                    foreach($gpaxStatusCount["oranges"] as $student){
                                ?>
                                    <tr>
                                        <th class="text-center"><?php echo $student["studentId"]?></th>
                                        <th><?php echo $student["fisrtNameTh"]." ".$student["lastNameTh"]?></th>
                                        <th class="text-center"><?php echo number_format($student["gpaAll"], 2, '.', '');?></th>
                                        <th class="text-center">
                                                <form action="./student_info.php" method = "post">
                                                    <input type="hidden" name="studentId" value="<?php echo $student["studentId"]; ?>" />
                                                    <!--<a type="button" name="std_info">
                                <span class="glyphicon glyphicon-user"></span></a>-->
                                                    <button type="submit" name="submit"
                                                        class="btn btn-info btn-md"><span
                                                            class="glyphicon glyphicon-user"></span> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                    </svg></button>
                                                </form>
                                            </th>
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
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต <?php echo $gpaxStatusCount["red"]?> คน</h5>
                <?php
                    if((int)$gpaxStatusCount["red"] > 0){
                                    
                ?>
                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th class="text-center">รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th class="text-center">GPAX</th>
                                <th class="text-center">รายละเอียด</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                                foreach($gpaxStatusCount["reds"] as $student){
                            ?>
                                <tr>
                                    <th class="text-center"><?php echo $student["studentId"]?></th>
                                    <th><?php echo $student["fisrtNameTh"]." ".$student["lastNameTh"]?></th>
                                    <th class="text-center"><?php echo number_format($student["gpaAll"], 2, '.', '');?></th>
                                    <th class="text-center">
                                                <form action="./student_info.php" method = "post">
                                                    <input type="hidden" name="studentId" value="<?php echo $student["studentId"]; ?>" />
                                                    <!--<a type="button" name="std_info">
                                <span class="glyphicon glyphicon-user"></span></a>-->
                                                    <button type="submit" name="submit"
                                                        class="btn btn-info btn-md"><span
                                                            class="glyphicon glyphicon-user"></span> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                    </svg></button>
                                                </form>
                                            </th>
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
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต <?php echo $planingCount["plan"]?> คน</h5>
                <?php
                    if((int)$planingCount["plan"] > 0){
                                    
                ?>
                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th class="text-center">รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th class="text-center">GPAX</th>
                                <th class="text-center">รายละเอียด</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                                foreach($planingCount["plans"] as $student){
                            ?>
                                <tr>
                                    <th class="text-center"><?php echo $student["studentId"]?></th>
                                    <th><?php echo $student["fisrtNameTh"]." ".$student["lastNameTh"]?></th>
                                    <th class="text-center"><?php echo number_format($student["gpaAll"], 2, '.', '');?></th>
                                    <th class="text-center">
                                                <form action="./student_info.php" method = "post">
                                                    <input type="hidden" name="studentId" value="<?php echo $student["studentId"]; ?>" />
                                                    <!--<a type="button" name="std_info">
                                <span class="glyphicon glyphicon-user"></span></a>-->
                                                    <button type="submit" name="submit"
                                                        class="btn btn-info btn-md"><span
                                                            class="glyphicon glyphicon-user"></span> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                    </svg></button>
                                                </form>
                                            </th>
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

    <!-- modalgreen -->
    <div id="modalgreen2" class="modal fade" style="color: black;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header" style="height: 90px;">
                    <h5>รายชื่อนิสิต ไม่ตามแผน </h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <br>

                </div>
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต <?php echo $planingCount["notPlan"]?> คน</h5>
                <?php
                    if((int)$planingCount["notPlan"] > 0){
                                    
                ?>
                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th class="text-center">รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th class="text-center">GPAX</th>
                                <th class="text-center">รายละเอียด</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                                foreach($planingCount["notPlans"] as $student){
                            ?>
                                <tr>
                                    <th class="text-center"><?php echo $student["studentId"]?></th>
                                    <th><?php echo $student["fisrtNameTh"]." ".$student["lastNameTh"]?></th>
                                    <th class="text-center"><?php echo number_format($student["gpaAll"], 2, '.', '');?></th>
                                    <th class="text-center">
                                                <form action="./student_info.php" method = "post">
                                                    <input type="hidden" name="studentId" value="<?php echo $student["studentId"]; ?>" />
                                                    <!--<a type="button" name="std_info">
                                <span class="glyphicon glyphicon-user"></span></a>-->
                                                    <button type="submit" name="submit"
                                                        class="btn btn-info btn-md"><span
                                                            class="glyphicon glyphicon-user"></span> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                    </svg></button>
                                                </form>
                                            </th>
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
    <div id="modalorange2" class="modal fade" style="color: black;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header" style="height: 90px;">
                    <h5>รายชื่อนิสิต พ้นสภาพ </h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <br>

                </div>
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต <?php echo $planingCount["retire"]?> คน</h5>
                <?php
                    if((int)$planingCount["retire"] > 0){
                                    
                ?>
                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th class="text-center">รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th class="text-center">GPAX</th>
                                <th class="text-center">รายละเอียด</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                                foreach($planingCount["retires"] as $student){
                            ?>
                                <tr>
                                    <th class="text-center"><?php echo $student["studentId"]?></th>
                                    <th><?php echo $student["fisrtNameTh"]." ".$student["lastNameTh"]?></th>
                                    <th class="text-center"><?php echo number_format($student["gpaAll"], 2, '.', '');?></th>
                                    <th class="text-center">
                                                <form action="./student_info.php" method = "post">
                                                    <input type="hidden" name="studentId" value="<?php echo $student["studentId"]; ?>" />
                                                    <!--<a type="button" name="std_info">
                                <span class="glyphicon glyphicon-user"></span></a>-->
                                                    <button type="submit" name="submit"
                                                        class="btn btn-info btn-md"><span
                                                            class="glyphicon glyphicon-user"></span> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                    </svg></button>
                                                </form>
                                            </th>
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
    <div id="modalred2" class="modal fade" style="color: black;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header" style="height: 90px;">
                    <h5>รายชื่อนิสิต จบการศึกษา </h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <br>

                </div>
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต <?php echo $planingCount["grad"]?> คน</h5>
                <?php
                    if((int)$planingCount["grad"] > 0){
                                    
                ?>
                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th class="text-center">รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th class="text-center">GPAX</th>
                                <th class="text-center">รายละเอียด</th>
                            </tr>
                        </thead>
                        <tbody>
                            <?php
                                foreach($planingCount["grads"] as $student){
                            ?>
                                <tr>
                                    <th class="text-center"><?php echo $student["studentId"]?></th>
                                    <th><?php echo $student["fisrtNameTh"]." ".$student["lastNameTh"]?></th>
                                    <th class="text-center"><?php echo number_format($student["gpaAll"], 2, '.', '');?></th>
                                    <th class="text-center">
                                                <form action="./student_info.php" method = "post">
                                                    <input type="hidden" name="studentId" value="<?php echo $student["studentId"]; ?>" />
                                                    <!--<a type="button" name="std_info">
                                <span class="glyphicon glyphicon-user"></span></a>-->
                                                    <button type="submit" name="submit"
                                                        class="btn btn-info btn-md"><span
                                                            class="glyphicon glyphicon-user"></span> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                    </svg></button>
                                                </form>
                                            </th>
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



    <!--modalPlan-->
    <?php
    $termPlan = 0;
    foreach ($gens as $gen) {

        ?>
        <div id="modalPlan<?php echo $termPlan ?>" class="modal fade" style="color: black;">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header" style="height: 90px;">
                        <h5>รุ่น
                            <?php echo $gen["studyGeneretion"] ?>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <br>

                    </div>
                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตตามแผน
                        <?php echo sizeof($gen["studentPlans"]) ?> คน
                    </h5>
                    <?php
                    if (sizeof($gen["studentPlans"]) > 0) {

                        ?>
                        <div class="modal-body" id="std_detail">
                            <table class="table">

                                <thead>
                                    <tr>
                                        <th class="text-center">รหัสนิสิต</th>
                                        <th>ชื่อ-นามสกุล</th>
                                        <th class="text-center">GPAX</th>
                                        <th class="text-center">รายละเอียด</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                    foreach ($gen["studentPlans"] as $gPlan) {
                                        ?>
                                        <tr>
                                            <th class="text-center">
                                                <?php echo $gPlan["studentId"] ?>
                                            </th>
                                            <th>นาย
                                                <?php echo $gPlan["fisrtNameTh"] . " " . $gPlan["lastNameTh"] ?>
                                            </th>
                                            <th class="text-center">
                                                <?php echo number_format($gPlan["gpaAll"], 2, '.', '');?>
                                            </th>
                                            <th class="text-center">
                                                <form action="./student_info.php" method = "post">
                                                    <input type="hidden" name="studentId" value="<?php echo $gPlan["studentId"]; ?>" />
                                                    <!--<a type="button" name="std_info">
                                <span class="glyphicon glyphicon-user"></span></a>-->
                                                    <button type="submit" name="submit"
                                                        class="btn btn-info btn-md"><span
                                                            class="glyphicon glyphicon-user"></span> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                    </svg></button>
                                                </form>
                                            </th>
                                        </tr>
                                        <?php
                                    }
                                    ?>

                                </tbody>
                            </table>

                        </div>
                    <?php } ?>
                    <hr>
                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตไม่ตามแผน
                        <?php echo sizeof($gen["studentNotPlans"]) ?> คน
                    </h5>
                    <?php
                    if (sizeof($gen["studentNotPlans"]) > 0) {

                        ?>
                        <div class="modal-body" id="std_detail">
                            <table class="table">

                                <thead>
                                    <tr>
                                        <th class="text-center">รหัสนิสิต</th>
                                        <th>ชื่อ-นามสกุล</th>
                                        <th class="text-center">GPAX</th>
                                        <th class="text-center">รายละเอียด</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                    foreach ($gen["studentNotPlans"] as $gNotPlan) {
                                        ?>
                                        <tr>
                                            <th class="text-center">
                                                <?php echo $gNotPlan["studentId"] ?>
                                            </th>
                                            <th>นาย
                                                <?php echo $gNotPlan["fisrtNameTh"] . " " . $gNotPlan["lastNameTh"] ?>
                                            </th>
                                            <th class="text-center">
                                                
                                                <?php echo number_format($gNotPlan["gpaAll"], 2, '.', '');?>
                                            </th>
                                            <th class="text-center">
                                                <form action="./student_info.php" method = "post">
                                                    <input type="hidden" name="studentId" value="<?php echo $gNotPlan["studentId"]; ?>" />
                                                    <!--<a type="button" name="std_info">
                                <span class="glyphicon glyphicon-user"></span></a>-->
                                                    <button type="submit" name="submit"
                                                        class="btn btn-info btn-md"><span
                                                            class="glyphicon glyphicon-user"></span> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                    </svg></button>
                                                </form>
                                            </th>
                                        </tr>
                                        <?php
                                    }
                                    ?>


                                </tbody>
                            </table>

                        </div>
                    <?php } ?>
                    <hr>
                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตพ้นสภาพ
                        <?php echo $sumRetire ?> คน
                    </h5>
                    <?php
                    if ((int) $sumRetire > 0) {

                        ?>
                        <div class="modal-body" id="std_detail">
                            <table class="table">

                                <thead>
                                    <tr>
                                        <th class="text-center">รหัสนิสิต</th>
                                        <th>ชื่อ-นามสกุล</th>
                                        <th class="text-center">GPAX</th>
                                        <th class="text-center">รายละเอียด</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                    foreach ($gen["studentResign"] as $gRePlan) {
                                        ?>
                                        <tr>
                                            <th class="text-center">
                                                <?php echo $gRePlan["studentId"] ?>
                                            </th>
                                            <th>นาย
                                                <?php echo $gRePlan["fisrtNameTh"] . " " . $gRePlan["lastNameTh"] ?>
                                            </th>
                                            <th class="text-center">
                                                <?php echo number_format($gRePlan["gpaAll"], 2, '.', '');?>
                                            </th>
                                            <th class="text-center">
                                                <form action="./student_info.php" method = "post">
                                                    <input type="hidden" name="studentId" value="<?php echo $gRePlan["studentId"]; ?>" />
                                                    <!--<a type="button" name="std_info">
                                <span class="glyphicon glyphicon-user"></span></a>-->
                                                    <button type="submit" name="submit"
                                                        class="btn btn-info btn-md"><span
                                                            class="glyphicon glyphicon-user"></span> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                    </svg></button>
                                                </form>
                                            </th>
                                        </tr>
                                        <?php
                                    }
                                    ?>


                                </tbody>
                            </table>

                        </div>
                    <?php } ?>
                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตจบการศึกษา
                        <?php echo $sumGrad ?> คน
                    </h5>

                    <?php
                    //print_r($countStudySemester);
                    if ((int) $sumGrad > 0) {

                        ?>
                        <div class="modal-body" id="std_detail">
                            <table class="table">

                                <thead>
                                    <tr>
                                        <th class="text-center">รหัสนิสิต</th>
                                        <th>ชื่อ-นามสกุล</th>
                                        <th class="text-center">GPAX</th>
                                        <th class="text-center">รายละเอียด</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                    foreach ($gen["studentGrads"] as $gGrad) {
                                        ?>
                                        <tr>
                                            <th class="text-center">
                                                <?php echo $gGrad["studentId"] ?>
                                            </th>
                                            <th>นาย
                                                <?php echo $gGrad["fisrtNameTh"] . " " . $gGrad["lastNameTh"] ?>
                                            </th>
                                            <th class="text-center">
                                                <?php echo number_format($gGrad["gpaAll"], 2, '.', '');?>
                                            </th>
                                            <th class="text-center">
                                                <form action="./student_info.php" method = "post">
                                                    <input type="hidden" name="studentId" value="<?php echo $gGrad["studentId"]; ?>" />
                                                    <!--<a type="button" name="std_info">
                                <span class="glyphicon glyphicon-user"></span></a>-->
                                                    <button type="submit" name="submit"
                                                        class="btn btn-info btn-md"><span
                                                            class="glyphicon glyphicon-user"></span> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                    </svg></button>
                                                </form>
                                            </th>
                                        </tr>
                                        <?php
                                    }
                                    ?>


                                </tbody>
                            </table>

                        </div>
                    <?php } ?>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"
                            style="font-size: 18px;">ปิดหน้าต่าง</button>
                    </div>
                </div>
            </div>
        </div>
        <?php
        $termPlan++;
    }
    ?>


    <!--modal-->
    <?php
    $term = 0;
    foreach ($countStudySemesters as $countStudySemester) {

        ?>
        <div id="modal<?php echo $term ?>" class="modal fade" style="color: black;">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header" style="height: 90px;">
                        <h5>ปีการศึกษา
                            <?php echo $countStudySemester["semesterYear"] ?> ภาคการศึกษา
                            <?php echo $countStudySemester["semesterPart"] ?>
                        </h5>
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <br>

                    </div>
                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตตามหลักสูตร
                        <?php echo $countStudySemester["planStatus"] ?> คน
                    </h5>
                    <?php
                    if ((int) $countStudySemester["planStatus"] > 0) {

                        ?>
                        <div class="modal-body" id="std_detail">
                            <table class="table">

                                <thead>
                                    <tr>
                                        <th class="text-center">รหัสนิสิต</th>
                                        <th>ชื่อ-นามสกุล</th>
                                        <th class="text-center">GPAX</th>
                                        <th class="text-center">รายละเอียด</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                    foreach ($countStudySemester["studentPlans"] as $sPlan) {
                                        ?>
                                        <tr>
                                            <th class="text-center">
                                                <?php echo $sPlan["studentId"] ?>
                                            </th>
                                            <th>นาย
                                                <?php echo $sPlan["fisrtNameTh"] . " " . $sPlan["lastNameTh"] ?>
                                            </th>
                                            <th class="text-center">
                                                <?php echo number_format($sPlan["gpaAll"], 2, '.', '');?>
                                            </th>
                                            <th class="text-center">
                                                <form action="./student_info.php" method = "post">
                                                    <input type="hidden" name="studentId" value="<?php echo $sPlan["studentId"]; ?>" />
                                                    <!--<a type="button" name="std_info">
                                <span class="glyphicon glyphicon-user"></span></a>-->
                                                    <button type="submit" name="submit"
                                                        class="btn btn-info btn-md"><span
                                                            class="glyphicon glyphicon-user"></span> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                    </svg></button>
                                                </form>
                                            </th>
                                        </tr>
                                        <?php
                                    }
                                    ?>

                                </tbody>
                            </table>

                        </div>
                    <?php } ?>
                    <hr>
                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตไม่ตามหลักสูตร
                        <?php echo $countStudySemester["notPlanStatus"] ?> คน
                    </h5>
                    <?php
                    if ((int) $countStudySemester["studentNotPlans"] > 0) {

                        ?>
                        <div class="modal-body" id="std_detail">
                            <table class="table">

                                <thead>
                                    <tr>
                                        <th class="text-center">รหัสนิสิต</th>
                                        <th>ชื่อ-นามสกุล</th>
                                        <th class="text-center">GPAX</th>
                                        <th class="text-center">รายละเอียด</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                    foreach ($countStudySemester["studentNotPlans"] as $sNotPlan) {
                                        ?>
                                        <tr>
                                            <th class="text-center">
                                                <?php echo $sNotPlan["studentId"] ?>
                                            </th>
                                            <th>นาย
                                                <?php echo $sNotPlan["fisrtNameTh"] . " " . $sNotPlan["lastNameTh"] ?>
                                            </th>
                                            <th class="text-center">
                                                <?php echo number_format($sNotPlan["gpaAll"], 2, '.', '');?>
                                            </th>
                                            <th class="text-center">
                                                <form action="./student_info.php" method = "post">
                                                    <input type="hidden" name="studentId" value="<?php echo $sNotPlan["studentId"]; ?>" />
                                                    <!--<a type="button" name="std_info">
                                <span class="glyphicon glyphicon-user"></span></a>-->
                                                    <button type="submit" name="submit"
                                                        class="btn btn-info btn-md"><span
                                                            class="glyphicon glyphicon-user"></span> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                    </svg></button>
                                                </form>
                                            </th>
                                        </tr>
                                        <?php
                                    }
                                    ?>


                                </tbody>
                            </table>

                        </div>
                    <?php } ?>
                    <hr>
                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตพ้นสภาพ
                        <?php echo $countStudySemester["resign"] ?> คน
                    </h5>
                    <?php
                    if ((int) $countStudySemester["studentResign"] > 0) {

                        ?>
                        <div class="modal-body" id="std_detail">
                            <table class="table">

                                <thead>
                                    <tr>
                                        <th class="text-center">รหัสนิสิต</th>
                                        <th>ชื่อ-นามสกุล</th>
                                        <th class="text-center">GPAX</th>
                                        <th class="text-center">รายละเอียด</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <?php
                                    foreach ($countStudySemester["studentResign"] as $sRePlan) {
                                        ?>
                                        <tr>
                                            <th class="text-center">
                                                <?php echo $sRePlan["studentId"] ?>
                                            </th>
                                            <th>นาย
                                                <?php echo $sRePlan["fisrtNameTh"] . " " . $sRePlan["lastNameTh"] ?>
                                            </th>
                                            <th class="text-center">
                                                <?php echo number_format($sRePlan["gpaAll"], 2, '.', '');?>
                                            </th>
                                            <th class="text-center">
                                                <form action="./student_info.php" method = "post">
                                                    <input type="hidden" name="studentId" value="<?php echo $sRePlan["studentId"]; ?>" />
                                                    <!--<a type="button" name="std_info">
                                <span class="glyphicon glyphicon-user"></span></a>-->
                                                    <button type="submit" name="submit"
                                                        class="btn btn-info btn-md"><span
                                                            class="glyphicon glyphicon-user"></span> <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-search" viewBox="0 0 16 16">
                                                            <path d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"/>
                                                    </svg></button>
                                                </form>
                                            </th>
                                        </tr>
                                        <?php
                                    }
                                    ?>


                                </tbody>
                            </table>

                        </div>
                    <?php } ?>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal"
                            style="font-size: 18px;">ปิดหน้าต่าง</button>
                    </div>
                </div>
            </div>
        </div>
        <?php
        $term++;
    }
    ?>




    <!-- Content Row -------------------------------------------------------BOX----------------------->
    <!--script-->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.0.1/dist/chart.umd.min.js">
    </script>
    <link href="../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <!-- Page level plugins -->
    <script src="../vendor/datatables/jquery.dataTables.min.js"></script>
    <script src="../vendor/datatables/dataTables.bootstrap4.min.js"></script>

    <!-- Page level custom scripts -->
    <script src="../js/demo/datatables-demo.js"></script>

    <!-- Bootstrap core JavaScript-->
    <script src="../vendor/jquery/jquery.min.js"></script>
    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

    <script>

        var gensh = <?php echo json_encode($listgensh); ?>;


        var sPLansh = <?php echo json_encode($sPLanh); ?>;

        var sNotPlansh = <?php echo json_encode($sNotPlanh); ?>;
        var sRetiresh = <?php echo json_encode($sRetireh); ?>;
        var sGradsh = <?php echo json_encode($sGradh); ?>;

        var ctx = document.getElementById("learnyear");
        var myChart = new Chart(ctx, {
            //type: 'bar',
            //type: 'line',
            type: 'bar',
            data: {
                labels: gensh,
                datasets: [
                    {
                        label: 'ตามหลักสูตร',
                        data: sPLansh,
                        backgroundColor: "rgba(100, 197, 215,0.7)",
                        borderWidth: 0
                    },
                    {
                        label: ['ไม่ตามหลักสูตร'],
                        data: sNotPlansh,
                        backgroundColor: "rgba(118, 188, 22,0.7)",
                        borderWidth: 0
                    },
                    {
                        label: ['พ้นสภาพ'],
                        data: sRetiresh,
                        backgroundColor: 'rgba(245, 123, 57,0.7)',
                        borderWidth: 0
                    },
                    {
                        label: ['จบการศึกษา'],
                        data: sGradsh,
                        backgroundColor: 'rgba(255, 105, 98,0.7)',
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
                        stacked: true,
                        beginAtZero: true // เพิ่มค่านี้เพื่อให้แกน y เริ่มต้นที่ 0
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
                    data: p4blue,
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


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js">
    </script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.0.1/dist/chart.umd.min.js">
    </script>
    <script>

        var years = <?php echo json_encode($sortYears); ?>;
        console.log(years);

        var plans = <?php echo json_encode($plans); ?>;
        console.log(plans);
        var notPlans = <?php echo json_encode($notPlan); ?>;
        console.log(notPlans);
        var resignPlans = <?php echo json_encode($resignPlan); ?>;
        console.log(resignPlans);

        var ctx = document.getElementById("learnnew");
        var myChart = new Chart(ctx, {
            //type: 'bar',
            //type: 'line',
            type: 'bar',
            data: {
                labels: years,
                datasets: [{
                    label: 'ตามหลักสูตร',
                    data: plans,
                    backgroundColor: "rgba(100, 197, 215,0.7)",
                    borderWidth: 0
                },
                {
                    label: ['ไม่ตามหลักสุตร'],
                    data: notPlans,
                    backgroundColor: "rgba(118, 188, 22,0.7)",
                    borderWidth: 0
                },
                {
                    label: ['พ้นสภาพ'],
                    data: resignPlans,
                    backgroundColor: 'rgba(255, 105, 98,0.7)',
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
        console.log(gennow);

        var bluegen = <?php echo json_encode($BNG); ?>;
        console.log(bluegen);
        var greengen = <?php echo json_encode($GNG); ?>;
        console.log(greengen);
        var orangegen = <?php echo json_encode($ONG); ?>;
        console.log(orangegen);
        var redgen = <?php echo json_encode($RNG); ?>;
        console.log(redgen);

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
        console.log(genend);

        var bluegenend = <?php echo json_encode($BEG); ?>;
        console.log(bluegenend);
        var greengenend = <?php echo json_encode($GEG); ?>;
        console.log(greengenend);
        var orangegenend = <?php echo json_encode($OEG); ?>;
        console.log(orangegenend);
        var redgenend = <?php echo json_encode($REG); ?>;
        console.log(redgenend);

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



</body>

</html>