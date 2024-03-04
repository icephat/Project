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
                require_once '../function/facultyFunction.php';

                $teacher = getTeacherByUsernameTeacher($_SESSION["access-user"]);
                $semester = getSemesterPresent();


                $course = getCoursePresentByDepartmentId($teacher["departmentId"]);

                $departments = getAllDepartment();
                $semesterYears = getSemesterYear();

                $generetions = geStudyGeneretionStudentInFaculty();

                $semesterYear = $_POST["semesterYear"];
                $generetion = $_POST["generetion"];



                    ?>

                <?php include('../layout/head/report.php'); ?>

                <div>
                    <form class="form-valide" action="../controller/reportStudentDepartmentearch.php" method="post"
                        enctype="multipart/form-data">
                        <div class="row mx-auto">
                            

                            <div class="column mx-auto col-sm-4">
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
                            <div class="column mx-auto col-sm-4">
                                <div class="text-center">
                                    <h5>รุ่น<span style="color: red;">*</span></th>
                                </div>
                                <div class="text-center">
                                    <div>
                                        <select class="form-control" data-live-search="true" name="generetion">
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
                            <div class="column mx-auto col-sm-3">
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


                    $countStudentInCourse = getCountStudentInFaculty();

                    ?>
                    <h5>คณะวิศวกรรมศาสตร์ กำแพงแสน ปีการศึกษา  <?php echo $semesterYear ?> รุ่นที่ <?php echo $generetion ?>
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
                                            $countRangeGrade = getCountStudentGradeRangeInFacultyฺSemesterYearBySemesterYearAndGeneretion($semesterYear,$generetion);

                                                ?>

                                            <div style="color: rgb(0, 9, 188);">
                                                <div class="text-center">
                                                    <a style="color: rgb(0, 9, 188);"><!-- href="#" data-toggle="modal" data-target="#modalblue"-->
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
                                                    <a style="color: rgb(0, 110, 22);" ><!--href="#" data-toggle="modal"
                                                        data-target="#modalgreen"-->
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
                                                    <a style="color: #ff8c00;" ><!--href="#" data-toggle="modal"
                                                        data-target="#modalorange"-->
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
                                                    <a style="color: rgb(255, 0, 0);" ><!--href="#" data-toggle="modal"
                                                        data-target="#modalred"-->
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
                                            $countPlanStatus = getCountStudentPlanStatusInFacultyBySemesterYearAndGeneretion($semesterYear,$generetion)

                                                ?>

                                            <div style="color: rgb(100, 197, 215);">
                                                <div class="text-center">
                                                    <a style="color: rgb(100, 197, 215);"><!-- href="#" data-toggle="modal"
                                                        data-target="#modalblue2"-->
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
                                                    <a style="color: rgb(	118, 188, 22);" ><!--href="#" data-toggle="modal"
                                                        data-target="#modalgreen2"-->
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
                                                    <a style="color: rgb(	245, 123, 57);" ><!--href="#" data-toggle="modal"
                                                        data-target="#modalorange2"-->
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
                                                    <a style="color:  rgb(255, 105, 98);" ><!--href="#" data-toggle="modal"
                                                        data-target="#modalred2"-->
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
                            <!--<div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">จำนวนนิสิต (คน)</h6>
                                </div>-->
                            <?php
                            $gradeRangeSortByDepartments = getGradeRangeSortByDepartmentInFacultyBySemesterYearAnd($semesterYear,$generetion);

                            ?>
                            <div class="card-body ">
                                <div class="row" style="padding: 20px;">
                                    <div class="col-sm-6">

                                        <canvas id="learn"></canvas>
                                    </div>
                                    <div class="col-sm-6 float-right">
                                        <div class="table-responsive">
                                            <table class="table table-striped" cellspacing="0" style="color: black;">
                                                <thead style=" ">
                                                    <tr>
                                                        <th style=" text-align: left; ">ภาควิชา</th>
                                                        <th style="text-align: right; ">3.25-4.00
                                                        </th>
                                                        <th style="text-align: right;">2.00-3.24</th>
                                                        <th style="text-align: right;">1.75-1.99</th>
                                                        <th style="text-align: right;">0.00-1.74</th>

                                                        <!--<th style="text-align: right;">รายละเอียด</th>-->
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                    $sumBlue = 0;
                                                    $sumGreen = 0;
                                                    $sumOrange = 0;
                                                    $sumRed = 0;

                                                    $learnLabels = [];
                                                    $learnBlues = [];
                                                    $learnGreens = [];
                                                    $learnOranges = [];
                                                    $learnReds = [];
                                                    $id=0;
                                                    foreach ($gradeRangeSortByDepartments as $dept) {
                                                        $sumBlue += $dept["blue"];
                                                        $sumGreen += $dept["green"];
                                                        $sumOrange += $dept["orange"];
                                                        $sumRed += $dept["red"];

                                                        $learnLabels[] = $dept["departmentInitials"];
                                                        $learnBlues[] = (int) $dept["blue"];
                                                        $learnGreens[] = (int) $dept["green"];
                                                        $learnOranges[] = (int) $dept["orange"];
                                                        $learnReds[] = (int) $dept["red"];
                                                        ?>
                                                        <tr>
                                                            <td style=" text-align: left;">
                                                                <?php echo $dept["departmentInitials"] ?>
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $dept["blue"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $dept["green"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $dept["orange"] ?> คน
                                                            </td>

                                                            <td style=" text-align: right;">
                                                                <?php echo $dept["red"] ?> คน
                                                            </td>
                                                            <!--<td class="text-center">
                                                                <a data-toggle="modal" data-target="#dataModal<?php echo $id?>" >
                                                                    <i class="fas fa-search fa-sm"></i>
                                                                </a>
                                                            </td>-->
                                                        </tr>
                                                        <?php
                                                        $id++;
                                                    }
                                                    ?>



                                                    <tr>
                                                        <th scope='row' style=" text-align: left;  ">
                                                            ทุกภาค</th>
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
                                                        <!--<td></td>-->
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
                            <?php
                            $planStatusSortByDepartments = getplanStatusSortByDepartmentInFacultyBySemesterYearAndGeneretion($semesterYear,$generetion);

                            ?>
                            <div class="card-body ">
                                <div class="row" style="padding: 20px;">
                                    <div class="col-sm-6">

                                        <canvas id="learn2"></canvas>
                                    </div>
                                    <div class="col-sm-6 float-right">
                                        <div class="table-responsive">
                                            <table class="table table-striped" cellspacing="0" style="color: black;">
                                                <thead style=" ">
                                                    <tr>
                                                        <th style=" text-align: left; ">ภาควิชา</th>
                                                        <th style="text-align: right; ">ตามแผน
                                                        </th>
                                                        <th style="text-align: right;">ไม่ตามแผน</th>
                                                        <th style="text-align: right;">พ้นสภาพ</th>
                                                        <th style="text-align: right;">จบการศึกษา</th>

                                                        <!--<th style="text-align: right;">รายละเอียด</th>-->
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                    $sumPlan = 0;
                                                    $sumNotPlan = 0;
                                                    $sumRetire = 0;
                                                    $sumGrad = 0;

                                                    $learn2Labels = [];
                                                    $learn2Plans = [];
                                                    $learn2NotPlans = [];
                                                    $learn2Retires = [];
                                                    $learn2Grads = [];
                                                    $id=0;
                                                    foreach ($planStatusSortByDepartments as $planStatusSortByDepartment) {
                                                        $sumPlan += $planStatusSortByDepartment["plan"];
                                                        $sumNotPlan += $planStatusSortByDepartment["notPlan"];
                                                        $sumRetire += $planStatusSortByDepartment["retire"];
                                                        $sumGrad += $planStatusSortByDepartment["grad"];

                                                        $learn2Labels[] = $planStatusSortByDepartment["departmentInitials"];
                                                        $learn2Plans[] = (int) $planStatusSortByDepartment["plan"];
                                                        $learn2NotPlans[] = (int) $planStatusSortByDepartment["notPlan"];
                                                        $learn2Retires[] = (int) $planStatusSortByDepartment["retire"];
                                                        $learn2Grads[] = (int) $planStatusSortByDepartment["grad"];

                                                        ?>
                                                        <tr>
                                                            <td style=" text-align: left;">
                                                                <?php echo $planStatusSortByDepartment["departmentInitials"] ?>
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $planStatusSortByDepartment["plan"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $planStatusSortByDepartment["notPlan"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $planStatusSortByDepartment["retire"] ?> คน
                                                            </td>

                                                            <td style=" text-align: right;">
                                                                <?php echo $planStatusSortByDepartment["grad"] ?> คน
                                                            </td>
                                                            <!--<td class="text-center">
                                                                <a data-toggle="modal" data-target="#dataModal2<?php echo $id?>" >
                                                                    <i class="fas fa-search fa-sm"></i>
                                                                </a>
                                                            </td>-->
                                                        </tr>
                                                        <?php
                                                        $id++;
                                                    }
                                                    ?>




                                                    <tr>
                                                        <th scope='row' style=" text-align: left;  ">
                                                            ทุกภาค</th>
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
                                                        <!--<td></td>-->
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
                            <?php
                            $departmentMMAs = getMaxMinAVGGPAXSortByDepartmentInFacultyBySemesterYearAndGeneretion($semesterYear,$generetion);

                            ?>
                            <div class="card-body ">
                                <div class="row" style="padding: 20px;">
                                    <div class="col-sm-6">

                                        <div id="grade"></div>
                                    </div>
                                    <div class="col-sm-6 mx-auto">
                                        <div class="table-responsive">
                                            <table class="table table-striped" cellspacing="0" style="color: black;">
                                                <thead style=" ">
                                                    <tr>
                                                        <th style=" text-align: left; "><span>ภาควิชา</span></th>
                                                        <th style="text-align: center; "><span>ค่าสูงสุด</span>
                                                        </th>
                                                        <th style="text-align: center;"><span>ค่าต่ำสุด</span></th>
                                                        <th style="text-align: center;"><span>ค่าเฉลี่ย</span></th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                    $departmentInitials = [];
                                                    $maxGPAX = [];
                                                    $minGPAX = [];
                                                    $avgGPAX = [];

                                                    foreach ($departmentMMAs as $departmentMMA) {
                                                        $departmentInitials[] = $departmentMMA["departmentInitials"];
                                                        $maxGPAX[] = (float) $departmentMMA["maxGPAX"];
                                                        $minGPAX[] = (float) $departmentMMA["minGPAX"];
                                                        $avgGPAX[] = (float) $departmentMMA["avgGPAX"]
                                                            ?>
                                                        <tr style="font-weight: normal;">
                                                            <td style=" text-align: left;">
                                                                <?php echo $departmentMMA["departmentInitials"] ?>
                                                            </td>
                                                            <td style=" text-align: center;">
                                                                <?php echo $departmentMMA["maxGPAX"] ?>
                                                            </td>
                                                            <td style=" text-align: center;">
                                                                <?php echo $departmentMMA["minGPAX"] ?>
                                                            </td>
                                                            <td style=" text-align: center;">
                                                                <?php echo $departmentMMA["avgGPAX"] ?>
                                                            </td>
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
                <div class="row">
                    <div class="col-sm-12 ">
                        <div class="row">
                            <div class="col-sm-5 mx-auto">
                                <table class="table table-hover"
                                    style="margin-top: 30px; border: 1px solid black; border-collapse: collapse; ">
                                    <tr style="border: 1px solid black; border-collapse: collapse; ">
                                        <th style="border: 1px solid black; border-collapse: collapse; width: 50%; ">

                                            <?php
                                            $countRangeGrade = getCountStudentRemainingGradeRangeInFacultyBySemesterYearAndGeneretion($semesterYear,$generetion)

                                                ?>

                                            <div style="color: rgb(0, 9, 188);">
                                                <div class="text-center">
                                                    <a style="color: rgb(0, 9, 188);" ><!--href="#" data-toggle="modal"
                                                        data-target="#modalblue3"-->
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
                                                    <a style="color: rgb(0, 110, 22);"><!-- href="#" data-toggle="modal"
                                                        data-target="#modalgreen3"-->
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
                                                    <a style="color: #ff8c00;" ><!--href="#" data-toggle="modal"
                                                        data-target="#modalorange3"-->
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
                                                    <a style="color: rgb(255, 0, 0);"><!-- href="#" data-toggle="modal"
                                                        data-target="#modalred3"-->
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
                                            $countPlanStatus = getCountStudentRemainingPlanStatusInFacultyBySemesterYearAndGeneretion($semesterYear,$generetion)

                                                ?>

                                            <div style="color: rgb(100, 197, 215);">
                                                <div class="text-center">
                                                    <a style="color: rgb(100, 197, 215);" ><!--href="#" data-toggle="modal"
                                                        data-target="#modalblue4"-->
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
                                                    <a style="color: rgb(	118, 188, 22);" ><!--href="#" data-toggle="modal"
                                                        data-target="#modalgreen4"-->
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
                                                    <a style="color: rgb(	245, 123, 57);" ><!--href="#" data-toggle="modal"
                                                        data-target="#modalorange4"-->
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
                                                    <a style="color: rgb(255, 105, 98);" ><!--href="#" data-toggle="modal"
                                                        data-target="#modalred4"-->
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
                            <!--<div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">จำนวนนิสิต (คน)</h6>
                                </div>-->
                            <?php
                            $gradeRangeRemainingSortByDepartments = getGradeRangeRemainingSortByDepartmentInFacultyBySemesterYearAndGeneretion($semesterYear,$generetion);

                            ?>
                            <div class="card-body ">
                                <div class="row" style="padding: 20px;">
                                    <div class="col-sm-6">

                                        <canvas id="learn21"></canvas>
                                    </div>
                                    <div class="col-sm-6 float-right">
                                        <div class="table-responsive">
                                            <table class="table table-striped" cellspacing="0" style="color: black;">
                                                <thead style=" ">
                                                    <tr>
                                                        <th style=" text-align: left; ">ภาควิชา</th>
                                                        <th style="text-align: right; ">3.25-4.00
                                                        </th>
                                                        <th style="text-align: right;">2.00-3.24</th>
                                                        <th style="text-align: right;">1.75-1.99</th>
                                                        <th style="text-align: right;">0.00-1.74</th>

                                                        <!--<th style="text-align: right;">รายละเอียด</th>-->
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                    $sumBlue2 = 0;
                                                    $sumGreen2 = 0;
                                                    $sumOrange2 = 0;
                                                    $sumRed2 = 0;

                                                    $learnLabels2 = [];
                                                    $learnBlues2 = [];
                                                    $learnGreens2 = [];
                                                    $learnOranges2 = [];
                                                    $learnReds2 = [];
                                                    $idmodal=0;
                                                    foreach ($gradeRangeRemainingSortByDepartments as $deptR) {
                                                        $sumBlue2 += $deptR["blue"];
                                                        $sumGreen2 += $deptR["green"];
                                                        $sumOrange2 += $deptR["orange"];
                                                        $sumRed2 += $deptR["red"];

                                                        $learnLabels2[] = $deptR["departmentInitials"];
                                                        $learnBlues2[] = (int) $deptR["blue"];
                                                        $learnGreens2[] = (int) $deptR["green"];
                                                        $learnOranges2[] = (int) $deptR["orange"];
                                                        $learnReds2[] = (int) $deptR["red"];

                                                        ?>
                                                        <tr>
                                                            <td style=" text-align: left;">
                                                                <?php echo $deptR["departmentInitials"] ?>
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $deptR["blue"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $deptR["green"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $deptR["orange"] ?> คน
                                                            </td>

                                                            <td style=" text-align: right;">
                                                                <?php echo $deptR["red"] ?> คน
                                                            </td>
                                                            <!--<td class="text-center">
                                                                <a data-toggle="modal" data-target="#dataModal3<?php echo $idmodal?>" >
                                                                    <i class="fas fa-search fa-sm"></i>
                                                                </a>
                                                            </td>-->
                                                        </tr>
                                                        <?php
                                                        $idmodal++;
                                                    }
                                                    ?>


                                                    <tr>
                                                        <th scope='row' style=" text-align: left;  ">
                                                            ทุกภาค</th>
                                                        <td style="font-weight: bold; text-align: right;">
                                                            <?php echo $sumBlue2 ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumGreen2 ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumOrange2 ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumRed2 ?> คน
                                                        </td>
                                                        <!--<td></td>-->
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
                            <?php
                            $planStatusRemainingByDepartments = getplanStatusRemainingSortByDepartmentInFacultyBySemesterYearAndGeneretion($semesterYear,$generetion);

                            ?>
                            <div class="card-body ">
                                <div class="row" style="padding: 20px;">
                                    <div class="col-sm-6">

                                        <canvas id="learn22"></canvas>
                                    </div>
                                    <div class="col-sm-6 float-right">
                                        <div class="table-responsive">
                                            <table class="table table-striped" cellspacing="0" style="color: black;">
                                                <thead style=" ">
                                                    <tr>
                                                        <th style=" text-align: left; ">ภาควิชา</th>
                                                        <th style="text-align: right; ">ตามแผน
                                                        </th>
                                                        <th style="text-align: right;">ไม่ตามแผน</th>
                                                        <th style="text-align: right;">พ้นสภาพ</th>
                                                        <th style="text-align: right;">จบการศึกษา</th>

                                                        <!--<th style="text-align: right;">รายละเอียด</th>-->
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                    $sumPlan2 = 0;
                                                    $sumNotPlan2 = 0;
                                                    $sumRetire2 = 0;
                                                    $sumGrad2 = 0;

                                                    $learn2Labels2 = [];
                                                    $learn2Plans2 = [];
                                                    $learn2NotPlans2 = [];
                                                    $learn2Retires2 = [];
                                                    $learn2Grads2 = [];
                                                    $idmodal=0;
                                                    foreach ($planStatusRemainingByDepartments as $planStatusRemainingByDepartment) {
                                                        $sumPlan2 += $planStatusRemainingByDepartment["plan"];
                                                        $sumNotPlan2 += $planStatusRemainingByDepartment["notPlan"];
                                                        $sumRetire2 += $planStatusRemainingByDepartment["retire"];
                                                        $sumGrad2 += $planStatusRemainingByDepartment["grad"];

                                                        $learn2Labels2[] = $planStatusRemainingByDepartment["departmentInitials"];
                                                        $learn2Plans2[] = (int) $planStatusRemainingByDepartment["plan"];
                                                        $learn2NotPlans2[] = (int) $planStatusRemainingByDepartment["notPlan"];
                                                        $learn2Retires2[] = (int) $planStatusRemainingByDepartment["retire"];
                                                        $learn2Grads2[] = (int) $planStatusRemainingByDepartment["grad"];

                                                        ?>
                                                        <tr>
                                                            <td style=" text-align: left;">
                                                                <?php echo $planStatusRemainingByDepartment["departmentInitials"] ?>
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $planStatusRemainingByDepartment["plan"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $planStatusRemainingByDepartment["notPlan"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $planStatusRemainingByDepartment["retire"] ?> คน
                                                            </td>

                                                            <td style=" text-align: right;">
                                                                <?php echo $planStatusRemainingByDepartment["grad"] ?> คน
                                                            </td>
                                                            <!--<td class="text-center">
                                                                <a data-toggle="modal" data-target="#dataModal4<?php echo $idmodal?>" >
                                                                    <i class="fas fa-search fa-sm"></i>
                                                                </a>
                                                            </td>-->
                                                        </tr>
                                                        <?php
                                                        $idmodal++;
                                                    }
                                                    ?>


                                                    <tr>
                                                        <th scope='row' style=" text-align: left;  ">
                                                            ทุกภาค</th>
                                                        <td style="font-weight: bold; text-align: right;">
                                                            <?php echo $sumPlan2 ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumNotPlan2 ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumRetire2 ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumGrad2 ?> คน
                                                        </td>
                                                        <!--<td></td>-->
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
                <!--<div style="justify-content: center; align-items: center;">
                        <h5 style="margin-left: 20px;  color: black;">ผลการเรียนวิชาที่ไม่ผ่านตามแผน</h5>
                        <div class="col-10 mx-auto">
                            <div class="table-responsive">
                                <table class="table table-striped" cellspacing="0" style="color: black;  ">
                                    <thead style=" ">
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
                        </div>-->

                <!-- modalblue -->
                <div id="modalblue" class="modal fade" style="color: black;">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header" style="height: 90px;">
                                <h5>รายชื่อนิสิต ช่วงเกรด 3.25-4.00 </h5>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <br>

                            </div>
                            <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต 0 คน</h5>

                            <div class="modal-body" id="std_detail">
                                <table class="table">

                                    <thead>
                                        <tr>
                                            <th>รหัสนิสิต</th>
                                            <th>ชื่อ-นามสกุล</th>
                                            <th>GPAX</th>
                                        </tr>
                                    </thead>
                                    <tbody>

                                        <tr>
                                            <th>63202651</th>
                                            <th>xxx xxxx</th>
                                            <th>3.33</th>
                                        </tr>

                                    </tbody>
                                </table>

                            </div>


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
                        <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต 0 คน</h5>

                        <div class="modal-body" id="std_detail">
                            <table class="table">

                                <thead>
                                    <tr>
                                        <th>รหัสนิสิต</th>
                                        <th>ชื่อ-นามสกุล</th>
                                        <th>GPAX</th>
                                    </tr>
                                </thead>
                                <tbody>

                                    <tr>
                                        <th>63202651</th>
                                        <th>xxx xxxx</th>
                                        <th>3.33</th>
                                    </tr>

                                </tbody>
                            </table>

                        </div>

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
                    <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต 0 คน</h5>
                    < <div class="modal-body" id="std_detail">
                        <table class="table">

                            <thead>
                                <tr>
                                    <th>รหัสนิสิต</th>
                                    <th>ชื่อ-นามสกุล</th>
                                    <th>GPAX</th>
                                </tr>
                            </thead>
                            <tbody>

                                <tr>
                                    <th>63202651</th>
                                    <th>xxx xxxx</th>
                                    <th>3.33</th>
                                </tr>

                            </tbody>
                        </table>

                </div>

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
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต 0 คน</h5>

                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th>รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th>GPAX</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <th>63202651</th>
                                <th>xxx xxxx</th>
                                <th>3.33</th>
                            </tr>


                        </tbody>
                    </table>

                </div>

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
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต 0 คน</h5>

                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th>รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th>GPAX</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <th>63202651</th>
                                <th>xxx xxxx</th>
                                <th>3.33</th>
                            </tr>


                        </tbody>
                    </table>

                </div>

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
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต 0 คน</h5>

                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th>รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th>GPAX</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <th>63202651</th>
                                <th>xxx xxxx</th>
                                <th>3.33</th>
                            </tr>

                        </tbody>
                    </table>

                </div>

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
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต 0 คน</h5>

                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th>รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th>GPAX</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <th>63202651</th>
                                <th>xxx xxxx</th>
                                <th>3.33</th>
                            </tr>


                        </tbody>
                    </table>

                </div>


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
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต 0 คน</h5>

                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th>รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th>GPAX</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <th>63202651</th>
                                <th>xxx xxxx</th>
                                <th>3.33</th>
                            </tr>



                        </tbody>
                    </table>

                </div>

                <hr>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                        style="font-size: 18px;">ปิดหน้าต่าง</button>
                </div>
            </div>
        </div>
    </div>
    </div>

    <!-- modalblue3 -->
    <div id="modalblue3" class="modal fade" style="color: black;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header" style="height: 90px;">
                    <h5>รายชื่อนิสิต ช่วงเกรด 3.25-4.00 </h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <br>

                </div>
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต 0 คน</h5>

                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th>รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th>GPAX</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <th>63202651</th>
                                <th>xxx xxxx</th>
                                <th>3.33</th>
                            </tr>

                        </tbody>
                    </table>

                </div>


                <hr>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                        style="font-size: 18px;">ปิดหน้าต่าง</button>
                </div>
            </div>
        </div>
    </div>

    <!-- modalgreen3 -->
    <div id="modalgreen3" class="modal fade" style="color: black;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header" style="height: 90px;">
                    <h5>รายชื่อนิสิต ช่วงเกรด 2.00-3.24 </h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <br>

                </div>
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต 0 คน</h5>

                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th>รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th>GPAX</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <th>63202651</th>
                                <th>xxx xxxx</th>
                                <th>3.33</th>
                            </tr>

                        </tbody>
                    </table>

                </div>

                <hr>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                        style="font-size: 18px;">ปิดหน้าต่าง</button>
                </div>
            </div>
        </div>
    </div>
    </div>

    <!-- modalorange3 -->
    <div id="modalorange3" class="modal fade" style="color: black;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header" style="height: 90px;">
                    <h5>รายชื่อนิสิต ช่วงเกรด 1.75-1.99 </h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <br>

                </div>
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต 0 คน</h5>
                < <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th>รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th>GPAX</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <th>63202651</th>
                                <th>xxx xxxx</th>
                                <th>3.33</th>
                            </tr>

                        </tbody>
                    </table>

            </div>

            <hr>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"
                    style="font-size: 18px;">ปิดหน้าต่าง</button>
            </div>
        </div>
    </div>
    </div>
    </div>

    <!-- modalred3 -->
    <div id="modalred3" class="modal fade" style="color: black;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header" style="height: 90px;">
                    <h5>รายชื่อนิสิต ช่วงเกรด 0.00-1.74 </h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <br>

                </div>
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต 0 คน</h5>

                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th>รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th>GPAX</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <th>63202651</th>
                                <th>xxx xxxx</th>
                                <th>3.33</th>
                            </tr>


                        </tbody>
                    </table>

                </div>

                <hr>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                        style="font-size: 18px;">ปิดหน้าต่าง</button>
                </div>
            </div>
        </div>
    </div>
    </div>

    <!-- modalblue4 -->
    <div id="modalblue4" class="modal fade" style="color: black;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header" style="height: 90px;">
                    <h5>รายชื่อนิสิต ตามแผน </h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <br>

                </div>
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต 0 คน</h5>

                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th>รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th>GPAX</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <th>63202651</th>
                                <th>xxx xxxx</th>
                                <th>3.33</th>
                            </tr>


                        </tbody>
                    </table>

                </div>

                <hr>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                        style="font-size: 18px;">ปิดหน้าต่าง</button>
                </div>
            </div>
        </div>
    </div>
    </div>

    <!-- modalgreen4 -->
    <div id="modalgreen4" class="modal fade" style="color: black;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header" style="height: 90px;">
                    <h5>รายชื่อนิสิต ไม่ตามแผน </h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <br>

                </div>
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต 0 คน</h5>

                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th>รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th>GPAX</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <th>63202651</th>
                                <th>xxx xxxx</th>
                                <th>3.33</th>
                            </tr>

                        </tbody>
                    </table>

                </div>

                <hr>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                        style="font-size: 18px;">ปิดหน้าต่าง</button>
                </div>
            </div>
        </div>
    </div>
    </div>

    <!-- modalorange4 -->
    <div id="modalorange4" class="modal fade" style="color: black;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header" style="height: 90px;">
                    <h5>รายชื่อนิสิต พ้นสภาพ </h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <br>

                </div>
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต 0 คน</h5>

                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th>รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th>GPAX</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <th>63202651</th>
                                <th>xxx xxxx</th>
                                <th>3.33</th>
                            </tr>


                        </tbody>
                    </table>

                </div>


                <hr>

                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal"
                        style="font-size: 18px;">ปิดหน้าต่าง</button>
                </div>
            </div>
        </div>
    </div>
    </div>

    <!-- modalred4 -->
    <div id="modalred4" class="modal fade" style="color: black;">
        <div class="modal-dialog modal-lg">
            <div class="modal-content">
                <div class="modal-header" style="height: 90px;">
                    <h5>รายชื่อนิสิต จบการศึกษา </h5>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <br>

                </div>
                <h5 class="modal-title" style="margin-left: 10px;">จำนวนนิสิต 0 คน</h5>

                <div class="modal-body" id="std_detail">
                    <table class="table">

                        <thead>
                            <tr>
                                <th>รหัสนิสิต</th>
                                <th>ชื่อ-นามสกุล</th>
                                <th>GPAX</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <th>63202651</th>
                                <th>xxx xxxx</th>
                                <th>3.33</th>
                            </tr>



                        </tbody>
                    </table>

                </div>

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
                        $id=0;
                        
                        foreach ($gradeRangeSortByDepartments as $dept) {
                            
                    ?>

                        <div id="dataModal<?php echo $id?>" class="modal fade" style="color: black;">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header" style="height: 90px;">
                                        <h5>วศ.คอมพิวเตอร์ </h5>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <br>



                                    </div>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิต3.25-4.00 0 คน</h5>
                                    <div class="modal-body" id="std_detail">
                                        <table class="table table-striped">

                                            <thead>
                                                <tr>
                                                    <th>รหัสนิสิต</th>
                                                    <th>ชื่อ-นามสกุล</th>
                                                    <th>เกรดเฉลี่ย</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>3.38</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>3.45</th>
                                                </tr>


                                            </tbody>
                                        </table>

                                    </div>
                                    <hr>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิต2.00-3.24 100 คน</h5>
                                    <div class="modal-body" id="std_detail">
                                        <table class="table table-striped" id="grade" cellspacing="0">

                                            <thead>
                                                <tr>
                                                    <th>รหัสนิสิต</th>
                                                    <th>ชื่อ-นามสกุล</th>
                                                    <th>เกรดเฉลี่ย</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>2.85</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>2.59</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>3.01</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>3.05</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>3.10</th>
                                                </tr>


                                            </tbody>
                                        </table>

                                    </div>
                                    <hr>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิต1.75-1.99 6 คน</h5>
                                    <div class="modal-body" id="std_detail">
                                        <table class="table table-striped">

                                            <thead>
                                                <tr>
                                                    <th>รหัสนิสิต</th>
                                                    <th>ชื่อ-นามสกุล</th>
                                                    <th>เกรดเฉลี่ย</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>1.45</th>
                                                </tr>

                                            </tbody>
                                        </table>

                                    </div>
                                    <hr>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิต0.00-1.74 0 คน</h5>
                                    <hr>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal"
                                            style="font-size: 18px;">ปิดหน้าต่าง</button>
                                    </div>
                                </div>
                            </div>


                        </div>
                    <?php 
                        $id++;
                    }?>

                    <?php
                        $id=0;
                        
                        foreach ($planStatusSortByDepartments as $planStatusSortByDepartment) {
                            
                    ?>

                        <div id="dataModal2<?php echo $id?>" class="modal fade" style="color: black;">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header" style="height: 90px;">
                                        <h5>วศ.คอมพิวเตอร์</h5>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <br>



                                    </div>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตตามแผน 225 คน</h5>
                                    <div class="modal-body" id="std_detail">
                                        <table class="table table-striped">

                                            <thead>
                                                <tr>
                                                    <th>รหัสนิสิต</th>
                                                    <th>ชื่อ-นามสกุล</th>
                                                    <th>เกรดเฉลี่ย</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>3.38</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>3.45</th>
                                                </tr>


                                            </tbody>
                                        </table>

                                    </div>
                                    <hr>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตไม่ตามแผน 636 คน</h5>
                                    <div class="modal-body" id="std_detail">
                                        <table class="table table-striped">

                                            <thead>
                                                <tr>
                                                    <th>รหัสนิสิต</th>
                                                    <th>ชื่อ-นามสกุล</th>
                                                    <th>เกรดเฉลี่ย</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>2.85</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>2.59</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>3.01</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>3.05</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>3.10</th>
                                                </tr>


                                            </tbody>
                                        </table>

                                    </div>
                                    <hr>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตพ้นสภาพ 700 คน</h5>
                                    <div class="modal-body" id="std_detail">
                                        <table class="table table-striped">

                                            <thead>
                                                <tr>
                                                    <th>รหัสนิสิต</th>
                                                    <th>ชื่อ-นามสกุล</th>
                                                    <th>เกรดเฉลี่ย</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>1.45</th>
                                                </tr>

                                            </tbody>
                                        </table>

                                    </div>
                                    <hr>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตจบการศึกษา 4 คน</h5>
                                    <div class="modal-body" id="std_detail">
                                        <table class="table table-striped">

                                            <thead>
                                                <tr>
                                                    <th>รหัสนิสิต</th>
                                                    <th>ชื่อ-นามสกุล</th>
                                                    <th>เกรดเฉลี่ย</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>1.45</th>
                                                </tr>

                                            </tbody>
                                        </table>

                                    </div>
                                    <hr>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal"
                                            style="font-size: 18px;">ปิดหน้าต่าง</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <?php $id++;}?>

                    <?php
                        $id=0;
                        
                        foreach ($gradeRangeRemainingSortByDepartments as $deptR) {
                            
                    ?>

                        <div id="dataModal3<?php echo $id?>" class="modal fade" style="color: black;">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header" style="height: 90px;">
                                        <h5>วศ.คอมพิวเตอร์ </h5>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <br>



                                    </div>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิต3.25-4.00 0 คน</h5>

                                    <hr>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิต2.00-3.24 5 คน</h5>
                                    <div class="modal-body" id="std_detail">
                                        <table class="table table-striped" id="grade" cellspacing="0">

                                            <thead>
                                                <tr>
                                                    <th>รหัสนิสิต</th>
                                                    <th>ชื่อ-นามสกุล</th>
                                                    <th>เกรดเฉลี่ย</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>2.85</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>2.59</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>3.01</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>3.05</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>3.10</th>
                                                </tr>


                                            </tbody>
                                        </table>

                                    </div>
                                    <hr>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิต1.75-1.99 0 คน</h5>
                                    <hr>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิต0.00-1.74 0 คน</h5>
                                    <hr>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal"
                                            style="font-size: 18px;">ปิดหน้าต่าง</button>
                                    </div>
                                </div>
                            </div>


                        </div>
                    <?php $id++; }?>

                    <?php
                        $id=0;
                        
                        foreach ($planStatusRemainingByDepartments as $planStatusRemainingByDepartment) {
                            
                    ?>
                        <div id="dataModal4<?php echo $id?>" class="modal fade" style="color: black;">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header" style="height: 90px;">
                                        <h5>วศ.คอมพิวเตอร์</h5>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <br>



                                    </div>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตตามแผน 0 คน</h5>
                                    <hr>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตไม่ตามแผน 5 คน</h5>
                                    <div class="modal-body" id="std_detail">
                                        <table class="table table-striped">

                                            <thead>
                                                <tr>
                                                    <th>รหัสนิสิต</th>
                                                    <th>ชื่อ-นามสกุล</th>
                                                    <th>เกรดเฉลี่ย</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>2.85</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>2.59</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>3.01</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>3.05</th>
                                                </tr>
                                                <tr>
                                                    <th>632xxxxxxx</th>
                                                    <th>นายxxxxxx xxxxxx</th>
                                                    <th>3.10</th>
                                                </tr>


                                            </tbody>
                                        </table>

                                    </div>
                                    <hr>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตพ้นสภาพ 0 คน</h5>
                                    <hr>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตจบการศึกษา 0 คน</h5>
                                    <hr>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default" data-dismiss="modal"
                                            style="font-size: 18px;">ปิดหน้าต่าง</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    <?php $id++; }?>




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
                    label: '0.00-1.99',
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

        var learn2Plans = <?php echo json_encode($learn2Plans); ?>;
        var learn2NotPlans = <?php echo json_encode($learn2NotPlans); ?>;
        var learn2Retires = <?php echo json_encode($learn2Retires); ?>;
        var learn2Grads = <?php echo json_encode($learn2Grads); ?>;

        var ctx = document.getElementById("learn2");
        var myChart = new Chart(ctx, {
            //type: 'bar',
            //type: 'line',
            type: 'bar',
            data: {
                labels: learn2Labels,
                datasets: [{
                    label: 'ตามแผน',
                    data: learn2Plans,
                    backgroundColor: "rgba(134, 211, 247,0.7)",
                    borderWidth: 0
                },
                {
                    label: 'ไม่ตามแผน',
                    data: learn2NotPlans,
                    backgroundColor: "rgba(153, 204, 153,0.7)",
                    borderWidth: 0
                },
                {
                    label: 'พ้นสภาพ',
                    data: learn2Retires,
                    backgroundColor: 'rgba(245, 123, 57,0.7)',
                    borderWidth: 0
                },
                {
                    label: 'จบการศึกษา',
                    data: learn2Grads,
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

        var departmentInitials = <?php echo json_encode($departmentInitials); ?>;

        var maxGPAX = <?php echo json_encode($maxGPAX); ?>;
        var minGPAX = <?php echo json_encode($minGPAX); ?>;
        var avgGPAX = <?php echo json_encode($avgGPAX); ?>;


        var ctx = document.getElementById("grade");
        var data = [];

        for (var i = 0; i < departmentInitials.length; i++) {
            var generationData = {
                y: [maxGPAX[i], avgGPAX[i], minGPAX[i]],
                type: 'box',
                name: departmentInitials[i]
            };
            data.push(generationData);
        }

        Plotly.newPlot('grade', data);
    </script>

    <script>

        var learnLabels2 = <?php echo json_encode($learnLabels2); ?>;

        var learnBlues2 = <?php echo json_encode($learnBlues2); ?>;
        var learnGreens2 = <?php echo json_encode($learnGreens2); ?>;
        var learnOranges2 = <?php echo json_encode($learnOranges2); ?>;
        var learnReds2 = <?php echo json_encode($learnReds2); ?>;
        var ctx = document.getElementById("learn21");
        var myChart = new Chart(ctx, {
            //type: 'bar',
            //type: 'line',
            type: 'bar',
            data: {
                labels: learnLabels2,
                datasets: [{
                    label: '3.25-4.00',
                    data: learnBlues2,
                    backgroundColor: "rgba(0, 9, 188,0.7)",
                    borderWidth: 0
                },
                {
                    label: '2.00-3.24',
                    data: learnGreens2,
                    backgroundColor: "rgba(0, 110, 22,0.7)",
                    borderWidth: 0
                },
                {
                    label: '1.75-1.99',
                    data: learnOranges2,
                    backgroundColor: 'rgba(255,128,0,0.7)',
                    borderWidth: 0
                },
                {
                    label: '0.00-1.74',
                    data: learnReds2,
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

        var learn2Labels2 = <?php echo json_encode($learn2Labels2); ?>;

        var learn2Plans2 = <?php echo json_encode($learn2Plans2); ?>;
        var learn2NotPlans2 = <?php echo json_encode($learn2NotPlans2); ?>;
        var learn2Retires2 = <?php echo json_encode($learn2Retires2); ?>;
        var learn2Grads2 = <?php echo json_encode($learn2Grads2); ?>;

        var ctx = document.getElementById("learn22");
        var myChart = new Chart(ctx, {
            //type: 'bar',
            //type: 'line',
            type: 'bar',
            data: {
                labels: learn2Labels2,
                datasets: [{
                    label: 'ตามแผน',
                    data: learn2Plans2,
                    backgroundColor: "rgba(134, 211, 247,0.7)",
                    borderWidth: 0
                },
                {
                    label: 'ไม่ตามแผน',
                    data: learn2NotPlans2,
                    backgroundColor: "rgba(153, 204, 153,0.7)",
                    borderWidth: 0
                },
                {
                    label: 'พ้นสภาพ',
                    data: learn2Retires2,
                    backgroundColor: 'rgba(245, 123, 57,0.7)',
                    borderWidth: 0
                },
                {
                    label: 'จบการศึกษา',
                    data: learn2Grads2,
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






</body>

</html>

<div id="dataModal" class="modal fade" style="color: black;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="height: 90px;">
                <h5>วศ.คอมพิวเตอร์ </h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <br>



            </div>
            <h5 class="modal-title" style="margin-left: 10px;">นิสิต3.25-4.00 50 คน</h5>
            <div class="modal-body" id="std_detail">
                <table class="table table-striped">

                    <thead>
                        <tr>
                            <th>รหัสนิสิต</th>
                            <th>ชื่อ-นามสกุล</th>
                            <th>เกรดเฉลี่ย</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>3.38</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>3.45</th>
                        </tr>


                    </tbody>
                </table>

            </div>
            <hr>
            <h5 class="modal-title" style="margin-left: 10px;">นิสิต2.00-3.24 100 คน</h5>
            <div class="modal-body" id="std_detail">
                <table class="table table-striped" id="grade" cellspacing="0">

                    <thead>
                        <tr>
                            <th>รหัสนิสิต</th>
                            <th>ชื่อ-นามสกุล</th>
                            <th>เกรดเฉลี่ย</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>2.85</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>2.59</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>3.01</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>3.05</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>3.10</th>
                        </tr>


                    </tbody>
                </table>

            </div>
            <hr>
            <h5 class="modal-title" style="margin-left: 10px;">นิสิต1.75-1.99 6 คน</h5>
            <div class="modal-body" id="std_detail">
                <table class="table table-striped">

                    <thead>
                        <tr>
                            <th>รหัสนิสิต</th>
                            <th>ชื่อ-นามสกุล</th>
                            <th>เกรดเฉลี่ย</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>1.45</th>
                        </tr>

                    </tbody>
                </table>

            </div>
            <hr>
            <h5 class="modal-title" style="margin-left: 10px;">นิสิต0.00-1.74 0 คน</h5>
            <hr>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"
                    style="font-size: 18px;">ปิดหน้าต่าง</button>
            </div>
        </div>
    </div>


</div>

<div id="dataModal2" class="modal fade" style="color: black;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="height: 90px;">
                <h5>วศ.คอมพิวเตอร์</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <br>



            </div>
            <h5 class="modal-title" style="margin-left: 10px;">นิสิตตามแผน 225 คน</h5>
            <div class="modal-body" id="std_detail">
                <table class="table table-striped">

                    <thead>
                        <tr>
                            <th>รหัสนิสิต</th>
                            <th>ชื่อ-นามสกุล</th>
                            <th>เกรดเฉลี่ย</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>3.38</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>3.45</th>
                        </tr>


                    </tbody>
                </table>

            </div>
            <hr>
            <h5 class="modal-title" style="margin-left: 10px;">นิสิตไม่ตามแผน 636 คน</h5>
            <div class="modal-body" id="std_detail">
                <table class="table table-striped">

                    <thead>
                        <tr>
                            <th>รหัสนิสิต</th>
                            <th>ชื่อ-นามสกุล</th>
                            <th>เกรดเฉลี่ย</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>2.85</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>2.59</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>3.01</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>3.05</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>3.10</th>
                        </tr>


                    </tbody>
                </table>

            </div>
            <hr>
            <h5 class="modal-title" style="margin-left: 10px;">นิสิตพ้นสภาพ 700 คน</h5>
            <div class="modal-body" id="std_detail">
                <table class="table table-striped">

                    <thead>
                        <tr>
                            <th>รหัสนิสิต</th>
                            <th>ชื่อ-นามสกุล</th>
                            <th>เกรดเฉลี่ย</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>1.45</th>
                        </tr>

                    </tbody>
                </table>

            </div>
            <hr>
            <h5 class="modal-title" style="margin-left: 10px;">นิสิตจบการศึกษา 4 คน</h5>
            <div class="modal-body" id="std_detail">
                <table class="table table-striped">

                    <thead>
                        <tr>
                            <th>รหัสนิสิต</th>
                            <th>ชื่อ-นามสกุล</th>
                            <th>เกรดเฉลี่ย</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>1.45</th>
                        </tr>

                    </tbody>
                </table>

            </div>
            <hr>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"
                    style="font-size: 18px;">ปิดหน้าต่าง</button>
            </div>
        </div>
    </div>
</div>

<div id="dataModal21" class="modal fade" style="color: black;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="height: 90px;">
                <h5>วศ.คอมพิวเตอร์ </h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <br>



            </div>
            <h5 class="modal-title" style="margin-left: 10px;">นิสิต3.25-4.00 0 คน</h5>

            <hr>
            <h5 class="modal-title" style="margin-left: 10px;">นิสิต2.00-3.24 5 คน</h5>
            <div class="modal-body" id="std_detail">
                <table class="table table-striped" id="grade" cellspacing="0">

                    <thead>
                        <tr>
                            <th>รหัสนิสิต</th>
                            <th>ชื่อ-นามสกุล</th>
                            <th>เกรดเฉลี่ย</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>2.85</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>2.59</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>3.01</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>3.05</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>3.10</th>
                        </tr>


                    </tbody>
                </table>

            </div>
            <hr>
            <h5 class="modal-title" style="margin-left: 10px;">นิสิต1.75-1.99 0 คน</h5>
            <hr>
            <h5 class="modal-title" style="margin-left: 10px;">นิสิต0.00-1.74 0 คน</h5>
            <hr>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"
                    style="font-size: 18px;">ปิดหน้าต่าง</button>
            </div>
        </div>
    </div>


</div>

<div id="dataModal22" class="modal fade" style="color: black;">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="height: 90px;">
                <h5>วศ.คอมพิวเตอร์</h5>
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <br>



            </div>
            <h5 class="modal-title" style="margin-left: 10px;">นิสิตตามแผน 0 คน</h5>
            <hr>
            <h5 class="modal-title" style="margin-left: 10px;">นิสิตไม่ตามแผน 5 คน</h5>
            <div class="modal-body" id="std_detail">
                <table class="table table-striped">

                    <thead>
                        <tr>
                            <th>รหัสนิสิต</th>
                            <th>ชื่อ-นามสกุล</th>
                            <th>เกรดเฉลี่ย</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>2.85</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>2.59</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>3.01</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>3.05</th>
                        </tr>
                        <tr>
                            <th>632xxxxxxx</th>
                            <th>นายxxxxxx xxxxxx</th>
                            <th>3.10</th>
                        </tr>


                    </tbody>
                </table>

            </div>
            <hr>
            <h5 class="modal-title" style="margin-left: 10px;">นิสิตพ้นสภาพ 0 คน</h5>
            <hr>
            <h5 class="modal-title" style="margin-left: 10px;">นิสิตจบการศึกษา 0 คน</h5>
            <hr>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"
                    style="font-size: 18px;">ปิดหน้าต่าง</button>
            </div>
        </div>
    </div>
</div>