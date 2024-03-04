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
    <link href="../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

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

                $generetions = getGeneretionInCourseByDepartmentId($teacher["departmentId"]);

                $semesterYears = getSemesterYear();

                $semesterYear = $_POST["semesterYear"];
                $generetion = $_POST["generetion"];




                ?>

                <?php include('../layout/head/report.php'); ?>
                <div>
                    <form class="form-valide" action="../controller/headAdvisorSearch.php" method="post" enctype="multipart/form-data">
                        <div class="row mx-auto">
                            <div class="column col-sm-4">

                                <div class="text-center">
                                    <h5>ปีการศึกษา<span style="color: red;">*</span></th>
                                </div>
                                <div class="text-center">
                                    <div>
                                        <select class="form-control" data-live-search="true" name="year">

                                            <?php
                                            foreach ($semesterYears as $year) {
                                                ?>
                                                <option value="<?php echo $year["semesterYear"] ?>">
                                                    <?php echo $year["semesterYear"] ?>
                                                </option>
                                                <?php
                                            }
                                            ?>
                                        </select>
                                    </div>
                                </div>
                            </div>

                            <!-- <div class="column col-sm-3">
                                <div class="text-center">
                                    <h5>ภาคการศึกษา<span style="color: red;">*</span></th>
                                </div>
                                <div class="text-center">
                                    <div>
                                        <select class="form-control" data-live-search="true">
                                            <option value="default">--กรุณาเลือกภาคการศึกษา--</option>
                                            <option value="2561">ต้น
                                            </option>
                                            <option value="2562">ปลาย</option>
                                        </select>
                                    </div>
                                </div>
                            </div> -->
                            <div class="column col-sm-4">
                                <div class="text-center">
                                    <h5>รุ่นที่สืบค้น<span style="color: red;">*</span></th>
                                </div>
                                <div class="text-center">
                                    <div>
                                        <select class="form-control" data-live-search="true" name = "generetion">
                                            
                                            <option value="0">ทุกรุ่น</option>
                                            <?php
                                            foreach ($generetions as $gen) {
                                                ?>
                                                <option value="<?php echo $gen["studyGeneretion"] ?>">
                                                    <?php echo $gen["studyGeneretion"] ?>
                                                </option>
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

                    
                    $countStudentInCourse = getCountStudentInDepartmentByDepartmentId($teacher["departmentId"]);

                    ?>
                    <h5>ภาควิชา<?php echo $teacher["departmentName"] . " ปีการศึกษา ".$semesterYear ?> รุ่นที่ <?php echo $generetion ?>
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
                                            $countRangeGrade = getCountStudentGradeRangeByDepartmrntIdAndSemesterYearAndGeneretion($teacher["departmentId"], $semesterYear,$generetion)

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
                                            $countPlanStatus = getCountStudentPlanStatusByDepartmrntIdAndSemesterYearAndGeneretion($teacher["departmentId"], $semesterYear,$generetion)

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
                                                    <a style="color: rgb(255, 105, 98);" href="#" data-toggle="modal" data-target="#modalred2">
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
                <br><br>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="card shadow mb-4">
                            <!-- <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">จำนวนนิสิต (คน)</h6>
                                </div> -->
                            <div class="card-body ">
                                <div class="row" style="padding: 20px;">
                                    <?php

                                    $gradeRangeSortByAdvisers = getGradeRangeSortByAdviserByDepartmentIdAndSemesterYearAndGeneretion($teacher["departmentId"],$semesterYear,$generetion);

                                    ?>
                                    <div class="col-sm-6">

                                        <canvas id="learn"></canvas>
                                    </div>
                                    <div class="col-sm-6 float-right">
                                        <div class="table-responsive">
                                            <table class="table table-striped" cellspacing="0" style="color: black;">
                                                <thead>
                                                    <tr>
                                                        <th style=" text-align: left; ">ชื่ออาจารย์</th>
                                                        <th style="text-align: right; ">3.25-4.00
                                                        </th>
                                                        <th style="text-align: right;">2.00-3.24</th>
                                                        <th style="text-align: right;">1.75-1.99</th>
                                                        <th style="text-align: right;">0.00-1.74</th>

                                                        <th style="text-align: right;">รายละเอียด</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php

                                                    $sumBlue = 0;
                                                    $sumGreen = 0;
                                                    $sumOrange = 0;
                                                    $sumRed = 0;

                                                    $learnLabels=[];
                                                    $learnBlues=[];
                                                    $learnGreens=[];
                                                    $learnOranges=[];
                                                    $learnReds=[];
                                                    $idLearn=0;

                                                    foreach ($gradeRangeSortByAdvisers as $adviser) {

                                                        $sumBlue+=$adviser["blue"];
                                                        $sumGreen+=$adviser["green"];
                                                        $sumOrange+=$adviser["orange"];
                                                        $sumRed+=$adviser["red"];

                                                        $learnLabels[]=$adviser["titleTecherTh"] . "" . $adviser["fisrtNameTh"];
                                                        $learnBlues[]=$adviser["blue"];
                                                        $learnGreens[]=$adviser["green"];
                                                        $learnOranges[]=$adviser["orange"];
                                                        $learnReds[]=$adviser["red"];
                                                        
                                                        ?>
                                                        <tr>
                                                            <td style=" text-align: left;">
                                                                <?php echo $adviser["titleTecherTh"] . "" . $adviser["fisrtNameTh"] . " " . $adviser["lastNameTh"] ?>
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $adviser["blue"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $adviser["green"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $adviser["orange"] ?> คน
                                                            </td>

                                                            <td style=" text-align: right;">
                                                                <?php echo $adviser["red"] ?> คน
                                                            </td>
                                                            <td class="text-center">
                                                            <a data-toggle="modal" data-target="#modalLearn<?php echo $idLearn?>" >
                                                                    <i class="fas fa-search fa-sm"></i>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <?php
                                                        $idLearn++;
                                                    }
                                                    ?>




                                                    <tr>
                                                        <th scope='row' style=" text-align: left;  ">
                                                            ทุกท่าน</th>
                                                        <td style="font-weight: bold; text-align: right;">
                                                            <?php echo $sumBlue ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumGreen ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumOrange ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumRed ?> คน
                                                        </td>
                                                        <td></td>
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
                            <!--<div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">จำนวนนิสิต (คน)</h6>
                                </div>-->
                            <div class="card-body ">
                                <div class="row" style="padding: 20px;">
                                    <?php

                                    $planStatusSortByAdvisers = getPlanStatusSortByAdviserByDepartmentIdAndSemesterYearAndGeneretion($teacher["departmentId"],$semesterYear,$generetion);

                                    ?>
                                    <div class="col-sm-6">

                                        <canvas id="learn2"></canvas>
                                    </div>
                                    <div class="col-sm-6 float-right">
                                        <div class="table-responsive">
                                            <table class="table table-striped" cellspacing="0" style="color: black; ">
                                                <thead style=" ">
                                                    <tr>
                                                        <th style=" text-align: left; ">ชื่ออาจารย์</th>
                                                        <th style="text-align: right; ">ตามแผน
                                                        </th>
                                                        <th style="text-align: right;">ไม่ตามแผน</th>
                                                        <th style="text-align: right;">พ้นสภาพ</th>
                                                        <th style="text-align: right;">จบการศึกษา</th>

                                                        <th style="text-align: right;">รายละเอียด</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php

                                                    $sumPlan = 0;
                                                    $sumNotPlan = 0;
                                                    $sumRetire = 0;
                                                    $sumGrad = 0;

                                                    $learn2Labels=[];
                                                    $learn2Blues=[];
                                                    $learn2Greens=[];
                                                    $learn2Oranges=[];
                                                    $learn2Reds=[];
                                                    $idLearn2=0;

                                                    foreach ($planStatusSortByAdvisers as $advi) {
                                                        $sumPlan+=$advi["plan"];
                                                        $sumNotPlan+=$advi["notPlan"];
                                                        $sumRetire+=$advi["retire"];
                                                        $sumGrad+=$advi["grad"];

                                                        $learn2Labels[]=$advi["titleTecherTh"] . "" . $advi["fisrtNameTh"];
                                                        $learn2Blues[]=$advi["plan"];
                                                        $learn2Greens[]=$advi["notPlan"];
                                                        $learn2Oranges[]=$advi["retire"];
                                                        $learn2Reds[]=$advi["grad"];

                                                        ?>
                                                        <tr>
                                                            <td style=" text-align: left;">
                                                                <?php echo $advi["titleTecherTh"] . "" . $advi["fisrtNameTh"] . " " . $advi["lastNameTh"] ?>
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $advi["plan"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $advi["notPlan"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $advi["retire"] ?> คน
                                                            </td>

                                                            <td style=" text-align: right;">
                                                                <?php echo $advi["grad"] ?> คน
                                                            </td>
                                                            <td class="text-center">
                                                                <a data-toggle="modal" data-target="#modal<?php echo $idLearn2?>" >
                                                                    <i class="fas fa-search fa-sm"></i>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <?php
                                                        $idLearn2++;
                                                    }
                                                    ?>




                                                    <tr>
                                                        <th scope='row' style=" text-align: left;  ">
                                                            ทุกท่าน</th>
                                                        <td style="font-weight: bold; text-align: right;">
                                                            <?php echo $sumPlan ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumNotPlan ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumRetire ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumGrad ?> คน
                                                        </td>
                                                        <td></td>
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
                            <!--<div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">ผลการเรียนนิสิต</h6>
                                </div>-->
                            <div class="card-body ">
                                <?php
                                $adviserMMAs = getMaxMinAVGGPAXSortByAdviserByDepartmentIdAndSemesterYearAndGeneretion($teacher["departmentId"],$semesterYear,$generetion)
                                ?>
                                <div class="row" style="padding: 20px;">
                                    <div class="col-sm-6">
                                        

                                        <div id="grade"></div>
                                    </div>
                                    <div class="col-sm-6 mx-auto">
                                        <div class="table-responsive">
                                            <table class="table table-striped" cellspacing="0" style="color: black; " >
                                                <thead style=" ">
                                                    <tr>
                                                        <th style=" text-align: left; "><span>อาจารย์</span></th>
                                                        <th style="text-align: center; "><span>ค่าสูงสุด</span>
                                                        </th>
                                                        <th style="text-align: center;"><span>ค่าต่ำสุด</span></th>
                                                        <th style="text-align: center;"><span>ค่าเฉลี่ย</span></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                    
                                                    foreach($adviserMMAs as $mmf){
                                                        $advisorGeneretionGrade = [];
                                                        $maxGPAX = [];
                                                        $minGPAX = [];
                                                        $avgGPAX = [];

                                                        $advisorGeneretionGrade[]=$mmf["titleTecherTh"] . "" . $mmf["fisrtNameTh"];
                                                        $maxGPAX[]=$mmf["maxGPAX"];
                                                        $minGPAX[]=$mmf["minGPAX"];
                                                        $avgGPAX[]=$mmf["avgGPAX"];
                                                    ?>
                                                    <tr style="font-weight: normal;">
                                                        <td style=" text-align: left;"><?php echo $mmf["titleTecherTh"] . "" . $mmf["fisrtNameTh"] . " " . $mmf["lastNameTh"] ?></td>
                                                        <td style=" text-align: center;">
                                                        <?php echo $mmf["maxGPAX"]?>
                                                        </td>
                                                        <td style=" text-align: center;">
                                                        <?php echo $mmf["minGPAX"]?>
                                                        </td>
                                                        <td style=" text-align: center;"><?php echo $mmf["avgGPAX"]?> </td>
                                                    </tr>
                                                    <?php
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
                <br>
                <div class="row">
                    <h5 style="color: black;">นิสิตตกค้าง</h5>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-12">
                        <div class="card shadow mb-4">
                            <!--<div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">จำนวนนิสิต (คน)</h6>
                                </div>-->
                            <div class="card-body ">
                                <div class="row" style="padding: 20px;">
                                    <?php

                                    $remainingGradeRangeSortByAdvisers = getRemainingGradeRangeSortByAdviserByDepartmentIdAndGeneretion($teacher["departmentId"],$semesterYear,$generetion);

                                    ?>
                                    <div class="col-sm-6">

                                        <canvas id="learn21"></canvas>
                                    </div>
                                    <div class="col-sm-6 float-right">
                                        <div class="table-responsive">
                                            <table class="table table-striped" cellspacing="0" style="color: black;">
                                                <thead>
                                                    <tr>
                                                        <th style=" text-align: left; ">ชื่ออาจารย์</th>
                                                        <th style="text-align: right; ">3.25-4.00
                                                        </th>
                                                        <th style="text-align: right;">2.00-3.24</th>
                                                        <th style="text-align: right;">1.75-1.99</th>
                                                        <th style="text-align: right;">0.00-1.74</th>

                                                        <th style="text-align: right;">รายละเอียด</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php

                                                    $sumBlue = 0;
                                                    $sumGreen = 0;
                                                    $sumOrange = 0;
                                                    $sumRed = 0;

                                                    $learn21Labels=[];
                                                    $learn21Blues=[];
                                                    $learn21Greens=[];
                                                    $learn21Orangs=[];
                                                    $learn21Reds=[];
                                                    $idLearn3=0;
                                                    //print_r($remainingGradeRangeSortByAdvisers);
                                                    foreach ($remainingGradeRangeSortByAdvisers as $adviser) {
                                                        
                                                        $sumBlue+=$adviser["blue"];
                                                        $sumGreen+= $adviser["green"];
                                                        $sumOrange+=$adviser["orange"];
                                                        $sumRed+=$adviser["red"];

                                                        $learn21Labels[]=$adviser["titleTecherTh"] . "" . $adviser["fisrtNameTh"];
                                                        $learn21Blues[]=$adviser["blue"];
                                                        $learn21Greens[]=$adviser["green"];
                                                        $learn21Orangs[]=$adviser["orange"];
                                                        $learn21Reds[]=$adviser["red"];


                                                        ?>
                                                        <tr>
                                                            <td style=" text-align: left;">
                                                                <?php echo $adviser["titleTecherTh"] . "" . $adviser["fisrtNameTh"] . " " . $adviser["lastNameTh"] ?>
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $adviser["blue"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $adviser["green"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $adviser["orange"] ?> คน
                                                            </td>

                                                            <td style=" text-align: right;">
                                                                <?php echo $adviser["red"] ?> คน
                                                            </td>
                                                            <td class="text-center">
                                                                <a data-toggle="modal" data-target="#modalLearn3<?php echo $idLearn3?>" >
                                                                    <i class="fas fa-search fa-sm"></i>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <?php
                                                        $idLearn3++;
                                                    }
                                                    ?>




                                                    <tr>
                                                        <th scope='row' style=" text-align: left;  ">
                                                            ทุกท่าน</th>
                                                        <td style="font-weight: bold; text-align: right;">
                                                            <?php echo $sumBlue ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumGreen ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumOrange ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumRed ?> คน
                                                        </td>
                                                        <td></td>
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
                            <!--<div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">จำนวนนิสิต (คน)</h6>
                                </div>-->
                            <div class="card-body ">
                                <div class="row" style="padding: 20px;">
                                    <?php

                                    $remainingPlanStatusSortByAdvisers = getRemainingPlanStatusSortByAdviserByDepartmentIdAndSemesterYearAndGeneretion($teacher["departmentId"],$semesterYear,$generetion);

                                    ?>
                                    <div class="col-sm-6">

                                        <canvas id="learns2"></canvas>
                                    </div>
                                    <div class="col-sm-6 float-right">
                                        <div class="table-responsive">
                                            <table class="table table-striped" cellspacing="0" style="color: black;">
                                                <thead style=" ">
                                                    <tr>
                                                        <th style=" text-align: left; ">ชื่ออาจารย์</th>
                                                        <th style="text-align: right; ">ตามแผน
                                                        </th>
                                                        <th style="text-align: right;">ไม่ตามแผน</th>
                                                        <th style="text-align: right;">พ้นสภาพ</th>
                                                        <th style="text-align: right;">จบการศึกษา</th>

                                                        <th style="text-align: right;">รายละเอียด</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php

                                                    $sumPlan = 0;
                                                    $sumNotPlan = 0;
                                                    $sumRetire = 0;
                                                    $sumGrad = 0;
                                                    $learn22Labels=[];
                                                    $learn22Plan=[];
                                                    $learn22NotPlan=[];
                                                    $learn22Retire=[];
                                                    $learn22Grads=[];
                                                    $idLearn4=0;
                                                    foreach ($remainingPlanStatusSortByAdvisers as $advi) {


                                                        $sumPlan+=$advi["plan"];
                                                        $sumNotPlan+= $advi["notPlan"];
                                                        $sumRetire+=$advi["retire"];
                                                        $sumGrad+=$advi["grad"];

                                                        $learn22Labels[]=$adviser["titleTecherTh"] . "" . $adviser["fisrtNameTh"];
                                                        $learn22Plan[]=$adviser["plan"];
                                                        $learn22NotPlan[]=$adviser["notPlan"];
                                                        $learn22Retire[]=$adviser["retire"];
                                                        $learn22Grads[]=$adviser["grad"];
                                                        ?>
                                                        <tr>
                                                            <td style=" text-align: left;">
                                                                <?php echo $advi["titleTecherTh"] . "" . $advi["fisrtNameTh"] . " " . $advi["lastNameTh"] ?>
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $advi["plan"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $advi["notPlan"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $advi["retire"] ?> คน
                                                            </td>

                                                            <td style=" text-align: right;">
                                                                <?php echo $advi["grad"] ?> คน
                                                            </td>
                                                            <td class="text-center">
                                                                <a data-toggle="modal" data-target="#modalLearn4<?php echo $idLearn4?>" >
                                                                    <i class="fas fa-search fa-sm"></i>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <?php
                                                        $idLearn4++;
                                                    }
                                                    ?>




                                                    <tr>
                                                        <th scope='row' style=" text-align: left;  ">
                                                            ทุกท่าน</th>
                                                        <td style="font-weight: bold; text-align: right;">
                                                            <?php echo $sumPlan ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumNotPlan ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumRetire ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumGrad ?> คน
                                                        </td>
                                                        <td></td>
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
                                            <th style=" text-align: left;">อาจารย์ที่ปรึกษา</th>
                                            <th style=" text-align: center;">รหัสนิสิต</th>
                                            <th style=" text-align: left;">ชื่อนิสิต</th>
                                            <th style=" text-align: right;">ทั้งหมด</th>
                                            <th style="text-align: right;">เรียน</th>
                                            <th style="text-align: right;">คงเหลือ</th>
                                            <th style="text-align: center;">GPA</th>
                                            <th style="text-align: center;">รายละเอียด</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <td style=" text-align: left;">ฐิติพงษ์ สถิรเมธีกุล</td>
                                            <td style=" text-align: center;">632xxxxxxx</td>
                                            <td style=" text-align: left;">นางสาวxxxxx xxxxx</td>
                                            <td style=" text-align: right;">140</td>
                                            <td style=" text-align: right;">128</td>
                                            <td style=" text-align: right;">12</td>
                                            <td style=" text-align: center;">3.38</td>
                                            <td class="text-center">
                                                <a data-toggle="modal" data-target="#dataModal5">
                                                    <i class="fas fa-search fa-sm"></i>
                                                </a>
                                            </td>

                                        </tr>

                                        <tr>
                                            <td style=" text-align: left;">วรัญญา อรรถเสนา</td>
                                            <td style=" text-align: center;">642xxxxxxx</td>
                                            <td style=" text-align: left;">นางสาวxxxxx xxxxx</td>
                                            <td style=" text-align: right;">138</td>
                                            <td style=" text-align: right;">93</td>
                                            <td style=" text-align: right;">45</td>
                                            <td style=" text-align: center;">3.02</td>
                                            <td class="text-center">
                                                <a data-toggle="modal" data-target="#dataModal5">
                                                    <i class="fas fa-search fa-sm"></i>
                                                </a>
                                            </td>
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

                <!-- madalLearn -->
                
                <?php
                        $idLearns=0;
                        foreach ($gradeRangeSortByAdvisers as $adviser) { 
                            

                ?>
                <div id="modalLearn<?php echo $idLearns?>" class="modal fade" style="color: black;">
                    <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header" style="height: 90px;">
                                    <h5>ฐิติพงษ์ สถิรเมธีกุล </h5>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                    <br>

                                </div>
                                <h5 class="modal-title" style="margin-left: 10px;">นิสิต3.25-4.00 <?php echo sizeof($adviser["blues"])?> คน</h5>
                                <?php
                                    if(sizeof($adviser["blues"])> 0){
                                        
                                ?>
                                <div class="modal-body" id="std_detail">
                                    <table class="table table-striped">

                                        <thead>
                                            <tr>
                                                <th class="text-center">รหัสนิสิต</th>
                                                <th>ชื่อ-นามสกุล</th>
                                                <th class="text-center">GPAX</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <?php
                                                foreach($adviser["blues"] as $advi){
                                            ?>
                                            <tr>
                                                <th class="text-center"><?php echo $advi["studentId"]?></th>
                                                <th><?php echo $advi["fisrtNameTh"]." ".$advi["lastNameTh"]?></th>
                                                <th class="text-center"><?php echo number_format($advi["gpaTerm"], 2, '.', '');?></th>
                                            </tr>
                                            <?php
                                                }
                                            ?>


                                        </tbody>
                                    </table>

                                </div>
                                <?php }?>
                                <hr>
                                <h5 class="modal-title" style="margin-left: 10px;">นิสิต2.00-3.24 <?php echo sizeof($adviser["greens"])?> คน</h5>
                                <?php
                                    if(sizeof($adviser["greens"])> 0){
                                        
                                ?>
                                <div class="modal-body" id="std_detail">
                                    <table class="table table-striped">

                                        <thead>
                                            <tr>
                                                <th class="text-center">รหัสนิสิต</th>
                                                <th>ชื่อ-นามสกุล</th>
                                                <th class="text-center">GPAX</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <?php
                                                foreach($adviser["greens"] as $advi){
                                            ?>
                                            <tr>
                                                <th class="text-center"><?php echo $advi["studentId"]?></th>
                                                <th><?php echo $advi["fisrtNameTh"]." ".$advi["lastNameTh"]?></th>
                                                <th class="text-center"><?php echo number_format($advi["gpaTerm"], 2, '.', '');?></th>
                                            </tr>
                                            <?php
                                                }
                                            ?>


                                        </tbody>
                                    </table>

                                </div>
                                <?php }?>
                                <hr>
                                <h5 class="modal-title" style="margin-left: 10px;">นิสิต1.75-1.99 <?php echo sizeof($adviser["oranges"])?> คน</h5>
                                <?php
                                    if(sizeof($adviser["oranges"])> 0){
                                        
                                ?>
                                <div class="modal-body" id="std_detail">
                                    <table class="table table-striped">

                                        <thead>
                                            <tr>
                                                <th class="text-center">รหัสนิสิต</th>
                                                <th>ชื่อ-นามสกุล</th>
                                                <th class="text-center">GPAX</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <?php
                                                foreach($adviser["oranges"] as $advi){
                                            ?>
                                            <tr>
                                                <th class="text-center"><?php echo $advi["studentId"]?></th>
                                                <th><?php echo $advi["fisrtNameTh"]." ".$advi["lastNameTh"]?></th>
                                                <th class="text-center"><?php echo number_format($advi["gpaTerm"], 2, '.', '');?></th>
                                            </tr>
                                            <?php
                                                }
                                            ?>


                                        </tbody>
                                    </table>

                                </div>
                                <?php }?>
                                <hr>
                                <h5 class="modal-title" style="margin-left: 10px;">นิสิต0.00-1.74 <?php echo sizeof($adviser["reds"])?> คน</h5>
                                <?php
                                    if(sizeof($adviser["reds"])> 0){
                                        
                                ?>
                                <div class="modal-body" id="std_detail">
                                    <table class="table table-striped">

                                        <thead>
                                            <tr>
                                                <th class="text-center">รหัสนิสิต</th>
                                                <th>ชื่อ-นามสกุล</th>
                                                <th class="text-center">GPAX</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <?php
                                                foreach($adviser["reds"] as $advi){
                                            ?>
                                            <tr>
                                                <th class="text-center"><?php echo $advi["studentId"]?></th>
                                                <th><?php echo $advi["fisrtNameTh"]." ".$advi["lastNameTh"]?></th>
                                                <th class="text-center"><?php echo number_format($advi["gpaTerm"], 2, '.', '');?></th>
                                            </tr>
                                            <?php
                                                }
                                            ?>

                                        </tbody>
                                    </table>

                                </div>
                                <?php } ?>
                                <hr>
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal"
                                        style="font-size: 18px;">ปิดหน้าต่าง</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <?php
                    $idLearns++;
                    }
                ?>

                <!-- modalLearn2 -->
                <?php
                        $idLearns2=0;
                        
                        foreach ($planStatusSortByAdvisers as $adviser) { 
                            

                ?>
                <div id="modal<?php echo $idLearns2?>" class="modal fade" style="color: black;">
                
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header" style="height: 90px;">
                            <h5><?php echo $adviser["titleTecherTh"].$adviser["fisrtNameTh"]." ".$adviser["lastNameTh"]?></h5>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <br>



                            </div>
                            <h5 class="modal-title" style="margin-left: 10px;">นิสิตตามแผน <?php echo sizeof($adviser["plans"])?> คน</h5>
                            <?php
                                if(sizeof($adviser["plans"])> 0){
                                        
                            ?>
                            <div class="modal-body" id="std_detail">
                                <table class="table table-striped">

                                    <thead>
                                        <tr>
                                            <th class="text-center">รหัสนิสิต</th>
                                            <th>ชื่อ-นามสกุล</th>
                                            <th class="text-center">GPAX</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <?php
                                        foreach($adviser["plans"] as $advi){
                                        ?>
                                        <tr>
                                            <th class="text-center"><?php echo $advi["studentId"]?></th>
                                            <th><?php echo $advi["fisrtNameTh"]." ".$advi["lastNameTh"]?></th>
                                            <th class="text-center"><?php echo number_format($advi["gpaTerm"], 2, '.', '');?></th>
                                        </tr>
                                        <?php
                                            }
                                        ?>


                                    </tbody>
                                </table>

                            </div>
                            <?php }?>
                            <hr>
                            <h5 class="modal-title" style="margin-left: 10px;">นิสิตไม่ตามแผน <?php echo sizeof($adviser["notPlans"])?> คน</h5>
                            <?php
                                if(sizeof($adviser["notPlans"])> 0){
                                        
                            ?>
                            <div class="modal-body" id="std_detail">
                                <table class="table table-striped">

                                    <thead>
                                        <tr>
                                            <th class="text-center">รหัสนิสิต</th>
                                            <th>ชื่อ-นามสกุล</th>
                                            <th class="text-center">GPAX</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <?php 
                                            foreach($adviser["notPlans"] as $advi){
                                        ?>
                                        <tr>
                                            <th class="text-center"><?php echo $advi["studentId"]?></th>
                                            <th><?php echo $advi["fisrtNameTh"]." ".$advi["lastNameTh"]?></th>
                                            <th class="text-center"><?php echo number_format($advi["gpaTerm"], 2, '.', '');?></th>
                                        </tr>
                                        <?php
                                            }
                                        ?>


                                    </tbody>
                                </table>

                            </div>
                            <?php }?>
                            <hr>
                            <h5 class="modal-title" style="margin-left: 10px;">นิสิตพ้นสภาพ <?php echo sizeof($adviser["retires"])?> คน</h5>
                            <?php
                                if(sizeof($adviser["retires"])> 0){
                                        
                            ?>
                            <div class="modal-body" id="std_detail">
                                <table class="table table-striped">

                                    <thead>
                                        <tr>
                                            <th class="text-center">รหัสนิสิต</th>
                                            <th>ชื่อ-นามสกุล</th>
                                            <th class="text-center">GPAX</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <?php 
                                            foreach($adviser["retires"] as $advi){
                                        ?>
                                        <tr>
                                            <th class="text-center"><?php echo $advi["studentId"]?></th>
                                            <th><?php echo $advi["fisrtNameTh"]." ".$advi["lastNameTh"]?></th>
                                            <th class="text-center"><?php echo number_format($advi["gpaTerm"], 2, '.', '');?></th>
                                        </tr>
                                        <?php
                                            }
                                        ?>


                                    </tbody>
                                </table>

                            </div>
                            <?php }?>
                            <hr>
                            <h5 class="modal-title" style="margin-left: 10px;">นิสิตจบการศึกษา <?php echo sizeof($adviser["grads"])?> คน</h5>
                            <?php
                                if(sizeof($adviser["grads"])> 0){
                                        
                            ?>
                            <div class="modal-body" id="std_detail">
                                <table class="table table-striped">

                                    <thead>
                                        <tr>
                                            <th class="text-center">รหัสนิสิต</th>
                                            <th>ชื่อ-นามสกุล</th>
                                            <th class="text-center">GPAX</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <?php 
                                            foreach($adviser["grads"] as $advi){
                                        ?>
                                        <tr>
                                            <th class="text-center"><?php echo $advi["studentId"]?></th>
                                            <th><?php echo $advi["fisrtNameTh"]." ".$advi["lastNameTh"]?></th>
                                            <th class="text-center"><?php echo number_format($advi["gpaTerm"], 2, '.', '');?></th>
                                        </tr>
                                        <?php
                                            }
                                        ?>


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
                <?php
                    $idLearns2++;
                    }
                ?>

                <!-- modal3-->
                <?php
                        $idLearns3=0;
                        foreach ($remainingGradeRangeSortByAdvisers as $adviser) { 
                            

                ?>
                <div id="modalLearn3<?php echo $idLearns3?>" class="modal fade" style="color: black;">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header" style="height: 90px;">
                            <h5><?php echo $adviser["titleTecherTh"].$adviser["fisrtNameTh"]." ".$adviser["lastNameTh"]?></h5>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <br>

                            </div>
                            <h5 class="modal-title" style="margin-left: 10px;">นิสิต3.25-4.00 <?php echo sizeof($adviser["blues"])?> คน</h5>
                                <?php
                                    if(sizeof($adviser["blues"])> 0){
                                        
                                ?>
                                <div class="modal-body" id="std_detail">
                                    <table class="table table-striped">

                                        <thead>
                                            <tr>
                                                <th class="text-center">รหัสนิสิต</th>
                                                <th>ชื่อ-นามสกุล</th>
                                                <th class="text-center">GPAX</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <?php
                                                foreach($adviser["blues"] as $advi){
                                            ?>
                                            <tr>
                                                <th class="text-center"><?php echo $advi["studentId"]?></th>
                                                <th><?php echo $advi["fisrtNameTh"]." ".$advi["lastNameTh"]?></th>
                                                <th class="text-center"><?php echo number_format($advi["gpaTerm"], 2, '.', '');?></th>
                                            </tr>
                                            <?php
                                                }
                                            ?>


                                        </tbody>
                                    </table>

                                </div>
                                <?php }?>
                                <hr>
                                <h5 class="modal-title" style="margin-left: 10px;">นิสิต2.00-3.24 <?php echo sizeof($adviser["greens"])?> คน</h5>
                                <?php
                                    if(sizeof($adviser["greens"])> 0){
                                        
                                ?>
                                <div class="modal-body" id="std_detail">
                                    <table class="table table-striped">

                                        <thead>
                                            <tr>
                                                <th class="text-center">รหัสนิสิต</th>
                                                <th>ชื่อ-นามสกุล</th>
                                                <th class="text-center">GPAX</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <?php
                                                foreach($adviser["greens"] as $advi){
                                            ?>
                                            <tr>
                                                <th class="text-center"><?php echo $advi["studentId"]?></th>
                                                <th><?php echo $advi["fisrtNameTh"]." ".$advi["lastNameTh"]?></th>
                                                <th class="text-center"><?php echo number_format($advi["gpaTerm"], 2, '.', '');?></th>
                                            </tr>
                                            <?php
                                                }
                                            ?>


                                        </tbody>
                                    </table>

                                </div>
                                <?php }?>
                                <hr>
                                <h5 class="modal-title" style="margin-left: 10px;">นิสิต1.75-1.99 <?php echo sizeof($adviser["oranges"])?> คน</h5>
                                <?php
                                    if(sizeof($adviser["oranges"])> 0){
                                        
                                ?>
                                <div class="modal-body" id="std_detail">
                                    <table class="table table-striped">

                                        <thead>
                                            <tr>
                                                <th class="text-center">รหัสนิสิต</th>
                                                <th>ชื่อ-นามสกุล</th>
                                                <th class="text-center">GPAX</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <?php
                                                foreach($adviser["oranges"] as $advi){
                                            ?>
                                            <tr>
                                                <th class="text-center"><?php echo $advi["studentId"]?></th>
                                                <th><?php echo $advi["fisrtNameTh"]." ".$advi["lastNameTh"]?></th>
                                                <th class="text-center"><?php echo number_format($advi["gpaTerm"], 2, '.', '');?></th>
                                            </tr>
                                            <?php
                                                }
                                            ?>


                                        </tbody>
                                    </table>

                                </div>
                                <?php }?>
                                <hr>
                                <h5 class="modal-title" style="margin-left: 10px;">นิสิต0.00-1.74 <?php echo sizeof($adviser["reds"])?> คน</h5>
                                <?php
                                    if(sizeof($adviser["reds"])> 0){
                                        
                                ?>
                                <div class="modal-body" id="std_detail">
                                    <table class="table table-striped">

                                        <thead>
                                            <tr>
                                                <th class="text-center">รหัสนิสิต</th>
                                                <th>ชื่อ-นามสกุล</th>
                                                <th class="text-center">GPAX</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <?php
                                                foreach($adviser["reds"] as $advi){
                                            ?>
                                            <tr>
                                                <th class="text-center"><?php echo $advi["studentId"]?></th>
                                                <th><?php echo $advi["fisrtNameTh"]." ".$advi["lastNameTh"]?></th>
                                                <th class="text-center"><?php echo number_format($advi["gpaTerm"], 2, '.', '');?></th>
                                            </tr>
                                            <?php
                                                }
                                            ?>

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
                <?php
                    $idLearns3++;
                    }
                ?>

                <!--modal4-->
                <?php
                        $idLearns4=0;
                        foreach ($remainingPlanStatusSortByAdvisers as $adviser) { 
                            

                ?>
                <div id="modalLearn4<?php echo $idLearns4?>" class="modal fade" style="color: black;">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header" style="height: 90px;">
                                <h5><?php echo $adviser["titleTecherTh"].$adviser["fisrtNameTh"]." ".$adviser["lastNameTh"]?></h5>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <br>



                            </div>
                            <h5 class="modal-title" style="margin-left: 10px;">นิสิตตามแผน <?php echo sizeof($adviser["plans"])?> คน</h5>
                            <?php
                                if(sizeof($adviser["plans"])> 0){
                                        
                            ?>
                            <div class="modal-body" id="std_detail">
                                <table class="table table-striped">

                                    <thead>
                                        <tr>
                                            <th class="text-center">รหัสนิสิต</th>
                                            <th>ชื่อ-นามสกุล</th>
                                            <th class="text-center">GPAX</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <?php
                                        foreach($adviser["plans"] as $advi){
                                        ?>
                                        <tr>
                                            <th class="text-center"><?php echo $advi["studentId"]?></th>
                                            <th><?php echo $advi["fisrtNameTh"]." ".$advi["lastNameTh"]?></th>
                                            <th class="text-center"><?php echo number_format($advi["gpaTerm"], 2, '.', '');?></th>
                                        </tr>
                                        <?php
                                            }
                                        ?>


                                    </tbody>
                                </table>

                            </div>
                            <?php }?>
                            <hr>
                            <h5 class="modal-title" style="margin-left: 10px;">นิสิตไม่ตามแผน <?php echo sizeof($adviser["notPlans"])?> คน</h5>
                            <?php
                                if(sizeof($adviser["notPlans"])> 0){
                                        
                            ?>
                            <div class="modal-body" id="std_detail">
                                <table class="table table-striped">

                                    <thead>
                                        <tr>
                                            <th class="text-center">รหัสนิสิต</th>
                                            <th>ชื่อ-นามสกุล</th>
                                            <th class="text-center">GPAX</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <?php 
                                            foreach($adviser["notPlans"] as $advi){
                                        ?>
                                        <tr>
                                            <th class="text-center"><?php echo $advi["studentId"]?></th>
                                            <th><?php echo $advi["fisrtNameTh"]." ".$advi["lastNameTh"]?></th>
                                            <th class="text-center"><?php echo number_format($advi["gpaTerm"], 2, '.', '');?></th>
                                        </tr>
                                        <?php
                                            }
                                        ?>


                                    </tbody>
                                </table>

                            </div>
                            <?php }?>
                            <hr>
                            <h5 class="modal-title" style="margin-left: 10px;">นิสิตพ้นสภาพ <?php echo sizeof($adviser["retires"])?> คน</h5>
                            <?php
                                if(sizeof($adviser["retires"])> 0){
                                        
                            ?>
                            <div class="modal-body" id="std_detail">
                                <table class="table table-striped">

                                    <thead>
                                        <tr>
                                            <th class="text-center">รหัสนิสิต</th>
                                            <th>ชื่อ-นามสกุล</th>
                                            <th class="text-center">GPAX</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <?php 
                                            foreach($adviser["retires"] as $advi){
                                        ?>
                                        <tr>
                                            <th class="text-center"><?php echo $advi["studentId"]?></th>
                                            <th><?php echo $advi["fisrtNameTh"]." ".$advi["lastNameTh"]?></th>
                                            <th class="text-center"><?php echo number_format($advi["gpaTerm"], 2, '.', '');?></th>
                                        </tr>
                                        <?php
                                            }
                                        ?>


                                    </tbody>
                                </table>

                            </div>
                            <?php }?>
                            <hr>
                            <h5 class="modal-title" style="margin-left: 10px;">นิสิตจบการศึกษา <?php echo sizeof($adviser["grads"])?> คน</h5>
                            <?php
                                if(sizeof($adviser["grads"])> 0){
                                        
                            ?>
                            <div class="modal-body" id="std_detail">
                                <table class="table table-striped">

                                    <thead>
                                        <tr>
                                            <th class="text-center">รหัสนิสิต</th>
                                            <th>ชื่อ-นามสกุล</th>
                                            <th class="text-center">GPAX</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <?php 
                                            foreach($adviser["grads"] as $advi){
                                        ?>
                                        <tr>
                                            <th class="text-center"><?php echo $advi["studentId"]?></th>
                                            <th><?php echo $advi["fisrtNameTh"]." ".$advi["lastNameTh"]?></th>
                                            <th class="text-center"><?php echo number_format($advi["gpaTerm"], 2, '.', '');?></th>
                                        </tr>
                                        <?php
                                            }
                                        ?>


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
                <?php
                    $idLearns4++;
                    }
                ?>

                


                <!-- Page level plugins -->
                <script src="../vendor/chart.js/Chart.min.js"></script>

                <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.js"></script>

                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js">
                </script>
                <script src="https://cdn.jsdelivr.net/npm/chart.js@4.0.1/dist/chart.umd.min.js">
                </script>


                


                <script>

                    var learnLabels = <?php echo json_encode($learnLabels); ?>;
                    var learnBlues = <?php echo json_encode($learnBlues); ?>;
                    var learnGreens = <?php echo json_encode($learnGreens); ?>;
                    var learnOranges = <?php echo json_encode($learnOranges); ?>;
                    var learnReds = <?php echo json_encode($learnReds); ?>;

                    var ctx = document.getElementById("learn");
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: learnLabels,
                            datasets: [{
                                label: '3.25-4.00',
                                data: learnBlues,
                                backgroundColor: "rgba(0, 9, 188,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '2.00-3.24',
                                data: learnGreens,
                                backgroundColor: "rgba(0, 110, 22,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '1.75-1.99',
                                data: learnOranges,
                                backgroundColor: 'rgba(255,128,0,0.7)',
                                borderWidth: 0
                            },
                            {
                                label: '0.00-1.74',
                                data: learnReds,
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
                    var learn2Labels = <?php echo json_encode($learn2Labels); ?>;
                    var learn2Blues = <?php echo json_encode($learn2Blues); ?>;
                    var learn2Greens = <?php echo json_encode($learn2Greens); ?>;
                    var learn2Oranges = <?php echo json_encode($learn2Oranges); ?>;
                    var learn2Reds = <?php echo json_encode($learn2Reds); ?>;

                    var ctx = document.getElementById("learn2");
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: learn2Labels,
                            datasets: [{
                                label: 'ตามแผน',
                                data: learn2Blues,
                                backgroundColor: "rgba(100, 197, 215,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: 'ไม่ตามแผน',
                                data: learn2Greens,
                                backgroundColor: "rgba(118, 188, 22,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: 'พ้นสภาพ',
                                data: learn2Oranges,
                                backgroundColor: 'rgba(	245, 123, 57,0.7)',
                                borderWidth: 0
                            },
                            {
                                label: 'จบการศึกษา',
                                data: learn2Reds,
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
                    var learn21Labels = <?php echo json_encode($learn21Labels); ?>;
                    var learn21Blues = <?php echo json_encode($learn21Blues); ?>;
                    var learn21Greens = <?php echo json_encode($learn21Greens); ?>;
                    var learn21Orangs = <?php echo json_encode($learn21Orangs); ?>;
                    var learn21Reds = <?php echo json_encode($learn21Reds); ?>;
                    
                    var ctx = document.getElementById("learn21");
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: learn21Labels,
                            datasets: [{
                                label: '3.25-4.00',
                                data: learn21Blues,
                                backgroundColor: "rgba(0, 9, 188,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '2.00-3.24',
                                data: learn21Greens,
                                backgroundColor: "rgba(0, 110, 22,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: '1.75-1.99',
                                data: learn21Orangs,
                                backgroundColor: 'rgba(255,128,0,0.7)',
                                borderWidth: 0
                            },
                            {
                                label: '0.00-1.74',
                                data: learn21Reds,
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

                    var learn22Labels = <?php echo json_encode($learn22Labels); ?>;
                    var learn22Plan = <?php echo json_encode($learn22Plan); ?>;
                    var learn22NotPlan = <?php echo json_encode($learn22NotPlan); ?>;
                    var learn22Retire = <?php echo json_encode($learn22Retire); ?>;
                    var learn22Grads = <?php echo json_encode($learn22Grads); ?>;
                    
                    var ctx = document.getElementById("learns2");
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: learn22Labels,
                            datasets: [{
                                label: 'ตามแผน',
                                data: learn22Plan,
                                backgroundColor: "rgba(100, 197, 215,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: 'ไม่ตามแผน',
                                data: learn22NotPlan,
                                backgroundColor: "rgba(118, 188, 22,0.7)",
                                borderWidth: 0
                            },
                            {
                                label: 'พ้นสภาพ',
                                data: learn22Retire,
                                backgroundColor: 'rgba(	245, 123, 57,0.7)',
                                borderWidth: 0
                            },
                            {
                                label: 'จบการศึกษา',
                                data: learn22Grads,
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

                <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.js"></script>
                <script src='https://cdn.plot.ly/plotly-2.27.0.min.js'></script>
                <script>


                    var advisorGeneretionGrade = <?php echo json_encode($advisorGeneretionGrade); ?>;
                        
                    var maxGPAX = <?php echo json_encode($maxGPAX); ?>;
                    var minGPAX = <?php echo json_encode($minGPAX); ?>;
                    var avgGPAX = <?php echo json_encode($avgGPAX); ?>;

                    var ctx = document.getElementById("grade");
                    var data = [];
    
                    for (var i = 0; i < advisorGeneretionGrade.length; i++) {
                        var generationData = {
                            y: [maxGPAX[i], avgGPAX[i], minGPAX[i]],
                            type: 'box',
                            name: advisorGeneretionGrade[i]
                        };
                        data.push(generationData);
                    }
                    Plotly.newPlot('grade', data);
                    /*var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        
                        type: 'bar',
                        data: {
                            labels: ['รุ่น 60', 'รุ่น 61', 'รุ่น 62', 'รุ่น 63', 'รุ่น 64'],
                            datasets: [{
                                label: 'max',
                                data: [3.40, 3.50, 3.43, 3.53, 3.44],
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
                                label: 'min',
                                data: [2.00, 1.50, 1.43, 1.53, 1.44],
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
                                label: 'avg',
                                data: [2.70, 2.50, 2.43, 2.53, 2.44],
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
        
                            responsive: true,
        
                        }
                    });*/
                </script>



                <!-- Bootstrap core JavaScript-->
                <script src="../vendor/jquery/jquery.min.js"></script>
                <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

                <!-- Core plugin JavaScript-->
                <script src="../vendor/jquery-easing/jquery.easing.min.js"></script>

                <!-- Custom scripts for all pages-->
                <script src="../js/sb-admin-2.min.js"></script>

                <!-- Page level plugins -->
                <script src="../vendor/datatables/jquery.dataTables.min.js"></script>
                <script src="../vendor/datatables/dataTables.bootstrap4.min.js"></script>

                <!-- Page level custom scripts -->
                <script src="../js/demo/datatables-demo.js"></script>



</body>

</html>




