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

                $teacher = getTeacherByUsernameTeacher($_SESSION["access-user"]);
                $course = getCoursePresentByDepartmentId($teacher["departmentId"]);
                $semester = getSemesterPresent();


                $courses = getCourseNameByDepartmentId($teacher["departmentId"]) ;
                $semesterYears = getSemesterYear();

                ?>

                <?php include('../layout/head/report.php'); ?>

                <div>
                    <form class="form-valide" action="student_static_search.php" method="post" enctype="multipart/form-data">
                        <div class="row mx-auto">
                            <div class="column col-sm-4">

                                <div class="text-center">
                                    <h5>หลักสูตร<span style="color: red;">*</span></th>
                                </div>
                                <div class="text-center">
                                    <div>
                                        <select class="form-control" data-live-search="true" name = "courseName" >
                                            
                                           <?php
                                           foreach($courses as $cou){
                                           ?>
                                            <option value="<?php echo  $cou["nameCourseUse"]?>"><?php echo  $cou["nameCourseUse"]?>
                                            </option>
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
                                            foreach($semesterYears as $year){
                                            ?>
                                            <option value="<?php echo $year["semesterYear"]?>"><?php echo $year["semesterYear"]?></option>
                                            <?php
                                            }
                                            ?>
                                        </select>
                                    </div>
                                </div>
                            </div>
                            <div class="column col-sm-4">

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
                
                <div class="row">
                    <div class="col-sm-12">
                    
                        <div class="card shadow mb-4">
                            <div class="card-header">
                            <h5 style="color: black;">หลักสูตร <?php echo $course["nameCourseUse"]?> ปีการศึกษา <?php echo $semester["semesterYear"]?></h5>
                            </div>
                            <div class="card-body ">
                                <div class="row" style="padding: 20px;">
                                    <div class="col-sm-6">
                                    <p style="font-weight: bold; font-size: 12px;">
                                            <span style="color: rgb(0, 107, 201);">&nbsp;&nbsp;&nbsp;<span style="color:rgb(0, 107, 201); font-size:15px;">~</span> นิสิตแรกเข้า&nbsp;&nbsp;</span>
                                            
                                            <span style="color: rgb(245, 123, 57); ">
                                            &nbsp;&nbsp;&nbsp; <svg style="color: rgb(245, 123, 57);" xmlns="http://www.w3.org/2000/svg"
                                                    width="16" height="16" fill="currentColor" class="bi bi-circle-fill"
                                                    viewBox="0 0 16 16">
                                                    <circle cx="8" cy="8" r="8" />
                                                </svg> นิสิตพ้นสภาพ</span>
                                            <span style="color: #a4ebf3;">&nbsp;&nbsp;&nbsp; <svg style="color: #a4ebf3;"
                                                    xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                    fill="currentColor" class="bi bi-circle-fill" viewBox="0 0 16 16">
                                                    <circle cx="8" cy="8" r="8" />
                                                </svg> นิสิตกำลังศึกษา</span>
                                            <span style="color: #abbdee;">&nbsp;&nbsp;&nbsp;<svg style="color: #abbdee;"
                                                    xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                    fill="currentColor" class="bi bi-circle-fill" viewBox="0 0 16 16">
                                                    <circle cx="8" cy="8" r="8" />
                                                </svg> นิสิตจบการศึกษา</span>
                                            <span style="color: #ff6962;">&nbsp;&nbsp;&nbsp;<svg style="color: #ff6962;"
                                                    xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                    fill="currentColor" class="bi bi-circle-fill" viewBox="0 0 16 16">
                                                    <circle cx="8" cy="8" r="8" />
                                                </svg> นิสิตตกค้าง</span>
                                            
                                        </p>
                                        <canvas id="myChart"></canvas>
                                    </div>
                                    <div class="col-sm-6 float-right">
                                        <div class="table-responsive">
                                            <table class="table table-striped" cellspacing="0" style="color: black; ">
                                                <thead>
                                                    <tr>
                                                        <th style=" text-align: right; ">รุ่น</th>
                                                        <th style="text-align: right; width: 150px;">
                                                            <span>แรกเข้า</span>
                                                        </th>
                                                        <th style="text-align: right;"><span>พ้นการศึกษา</span></th>
                                                        <th style="text-align: right;"><span>กำลังศึกษา</span></th>
                                                        <th style="text-align: right;">จบการศึกษา</th>
                                                        <th style="text-align: right;">ตกค้าง</th>
                                                    </tr>
                                                </thead>


                                                <tbody>

                                                    <?php

                                                    $studentStatusSortGeneretions = getCountStudentStatusSortByGeneretionByNameCourseAndSemesterYear($course["nameCourseUse"],$semester["semesterYear"]);

                                                    $sumFirstEntry = 0;
                                                    $sumRetire = 0;
                                                    $sumStudy = 0;
                                                    $sumGrad = 0;
                                                    $lists = [];
                                                    $firstEntrys = [];
                                                    $retires = [];
                                                    $studys = [];
                                                    $grads = [];


                                                    foreach ($studentStatusSortGeneretions as $gen) {

                                                        $sumFirstEntry += $gen["firstEntry"];
                                                        $sumRetire += $gen["retire"];
                                                        $sumStudy += $gen["study"];
                                                        $sumGrad += $gen["grad"];
                                                        $firstEntrys[] = (int) $gen["firstEntry"];
                                                        $retires[] = (int) $gen["retire"];
                                                        $studys[] = (int) $gen["study"];
                                                        $grads[] = (int) $gen["grad"];
                                                        $lists[] = "รุ่น ".$gen["studyGeneretion"];
                                                        ?>
                                                        <tr>
                                                            <td style=" text-align: right;">
                                                                <?php echo $gen["studyGeneretion"] ?>
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $gen["firstEntry"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $gen["retire"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $gen["study"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $gen["grad"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo "123" ?> คน
                                                            </td>
                                                        </tr>
                                                        <?php



                                                    }

                                                    ?>
                                                    <!-- <tr>
                                                        <td style=" text-align: right;">66</td>
                                                        <td style=" text-align: right;">
                                                            60 คน
                                                        </td>
                                                        <td style=" text-align: right;">
                                                            0 คน
                                                        </td>
                                                        <td style=" text-align: right;">
                                                            60 คน
                                                        </td>
                                                        <td style=" text-align: right;">0 คน</td>
                                                    </tr> -->

                                                    <tr>
                                                        <th scope='row' style=" text-align: right; ">
                                                            ทุกรุ่น</th>
                                                        <td style="font-weight: bold; text-align: right;">
                                                            <?php echo $sumFirstEntry ?> คน
                                                        </td>
                                                        <td style="font-weight: bold; text-align: right;">
                                                            <?php echo $sumRetire ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumStudy ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo $sumGrad ?> คน
                                                        </td>
                                                        <td style='font-weight: bold; text-align: right;'>
                                                            <?php echo "123" ?> คน
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
                    <div class="col-sm-12">
                        <div class="card shadow mb-4">
                            <div class="card-body ">
                                <div class="row" style="padding: 20px;">
                                    <div class="col-sm-6">
                                    <p style="font-weight: bold; font-size: 12px;">
                                            <span style="color: rgb(0, 107, 201);">&nbsp;&nbsp;&nbsp;<span style="color:rgb(0, 107, 201); font-size:15px;">~</span> นิสิตแรกเข้า&nbsp;&nbsp;</span>
                                            
                                            <span style="color: rgb(245, 123, 57); ">
                                            &nbsp;&nbsp;&nbsp; <svg style="color: rgb(245, 123, 57);" xmlns="http://www.w3.org/2000/svg"
                                                    width="16" height="16" fill="currentColor" class="bi bi-circle-fill"
                                                    viewBox="0 0 16 16">
                                                    <circle cx="8" cy="8" r="8" />
                                                </svg> นิสิตพ้นสภาพ</span>
                                            <span style="color: #a4ebf3;">&nbsp;&nbsp;&nbsp; <svg style="color: #a4ebf3;"
                                                    xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                    fill="currentColor" class="bi bi-circle-fill" viewBox="0 0 16 16">
                                                    <circle cx="8" cy="8" r="8" />
                                                </svg> นิสิตกำลังศึกษา</span>
                                            <span style="color: #abbdee;">&nbsp;&nbsp;&nbsp;<svg style="color: #abbdee;"
                                                    xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                    fill="currentColor" class="bi bi-circle-fill" viewBox="0 0 16 16">
                                                    <circle cx="8" cy="8" r="8" />
                                                </svg> นิสิตจบการศึกษา</span>
                                            
                                        </p>
                                        <canvas id="myCharts"></canvas>
                                    </div>
                                    <div class="col-sm-6 float-right">
                                        <div class="table-responsive">
                                            <table class="table table-striped" cellspacing="0" style="color: black;">
                                                <thead style=" ">
                                                    <tr>
                                                        <th style=" text-align: center; ">ปีการศึกษา</th>
                                                        <th style="text-align: right; width: 150px;">
                                                            <span>รวมแรกเข้า</span>
                                                        </th>
                                                        <th style="text-align: right;"><span>พ้นการศึกษา</span>
                                                        </th>
                                                        <th style="text-align: right;"><span>กำลังศึกษา</span></th>
                                                        <th style="text-align: right;">จบการศึกษา</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php

                                                    $studentStatusByYears = getCountStudentStatusSortByYearByNameCourseAndSemesterYear($course["nameCourseUse"],$semester["semesterYear"]);
                                                    $listSem = [];
                                                    $firstEntrys2 = [];
                                                    $retires2 = [];
                                                    $studys2 = [];
                                                    $grads2 = [];
                                                    foreach ($studentStatusByYears as $studentStatusByYear) {
                                                        $listSem[] = $studentStatusByYear["semesterYear"];
                                                        $firstEntrys2[] = (int) $studentStatusByYear["firstEntry"];
                                                        $retires2[] = (int) $studentStatusByYear["retire"];
                                                        $studys2[] = (int) $studentStatusByYear["study"];
                                                        $grads2[] = (int) $studentStatusByYear["grad"];



                                                        ?>
                                                        <tr>
                                                            <td style=" text-align: center;">
                                                                <?php echo $studentStatusByYear["semesterYear"] ?>
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $studentStatusByYear["firstEntry"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $studentStatusByYear["retire"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $studentStatusByYear["study"] ?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                                <?php echo $studentStatusByYear["grad"] ?> คน
                                                            </td>
                                                        </tr>
                                                        <?php

                                                    }


                                                    ?>
                                                    <!-- <tr>
                                                        <td style=" text-align: center;">2566</td>
                                                        <td style=" text-align: center;">
                                                            60 คน
                                                        </td>
                                                        <td style=" text-align: center;">
                                                            10 คน
                                                        </td>
                                                        <td style=" text-align: center;">
                                                            110 คน
                                                        </td>
                                                        <td style=" text-align: center;">0 คน</td>
                                                    </tr> -->
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <?php 
                    $studentStudys = getCountStudentStatusTatleSortByGeneretionAndYearStudyByNameCourseIdAndStatusAndSemesterYear($course["nameCourseUse"], "กำลังศึกษา",$semester["semesterYear"]);
                    
                   
                ?>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">นิสิตคงเหลือ (กำลังศึกษา)</h6>
                                </div>
                                <div class="card-body ">
                                    <div class="row" style="padding: 20px;">
                                        <div class="col-sm-12 mx-auto float-right">
                                            <div class="table-responsive">
                                                <table class="table table-striped" cellspacing="0" style="color: black;">
                                                    <thead style=" ">
                                                        <tr>
                                                            <th rowspan="2" style=" text-align: center; width: 100px;">
                                                                รุ่น</th>
                                                            <th colspan="12" style=" text-align: center; width: 100px;">
                                                                ปีการศึกษา</th>
                                                            <th rowspan="2" style=" text-align: center;">คงเหลือ(คน)
                                                            </th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td style=" text-align: center;"></td>
                                                            <?php
                                                            $x=0;
                                                            $color="";
                                                            $year = getCoursePresentByDepartmentId($teacher["departmentId"])["couseStartYear"];
                                                            //echo $thaiDay;
                                                            for ($i = $year; $i < $year + 12; $i++) {
                                                                if($x>=0 && $x<5){
                                                                    $color="#878787";
                                                                }
                                                                elseif($x>=5 && $x<8){
                                                                    $color="#bebebe";
                                                                }
                                                                else{
                                                                    $color="#cecece";
                                                                }
                                                                ?>
                                                                
                                                                <td style=" text-align: center; background-color: <?php echo $color;?>">
                                                                    <?php echo $i ?>
                                                                </td>
                                                            <?php $x++;} ?>

                                                        </tr>
                                                        <?php
                                                        
                                                        $g1=0;
                                                        $g2=0;
                                                        $g3=0;
                                                        $g4=0;
                                                        $g5=0;
                                                        $g6=0;
                                                        $g7=0;
                                                        $g8=0;
                                                        $g9=0;
                                                        $g10=0;
                                                        $g11=0;
                                                        $g12=0;

                                                        foreach ($studentStudys as $studentStudy) {
                                                            $sum=0;
                                                            $g1+=(int)$studentStudy["one"];
                                                            $g2+=(int)$studentStudy["two"];
                                                            $g3+=(int)$studentStudy["three"];
                                                            $g4+=(int)$studentStudy["four"];
                                                            $g5+=(int)$studentStudy["five"];
                                                            $g6+=(int)$studentStudy["six"];
                                                            $g7+=(int)$studentStudy["seven"];
                                                            $g8+=(int)$studentStudy["eight"];
                                                            $g9+=(int)$studentStudy["nine"];
                                                            $g10+=(int)$studentStudy["ten"];
                                                            $g11+=(int)$studentStudy["eleven"];
                                                            $g12+=(int)$studentStudy["twelve"];
                                                            $sum=0;

                                                            ?>
                                                            <tr>
                                                                <td style=" text-align: center;">
                                                                    <?php echo $studentStudy["studyGeneretion"] ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                    
                                                                    if($studentStudy["one"] > 0){
                                                                        echo $studentStudy["one"]." คน" ;
                                                                        $sum = $studentStudy["one"] ;
                                                                    }
                                                                        ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                    if($studentStudy["two"] > 0){
                                                                        echo $studentStudy["two"]." คน";
                                                                        $sum = $studentStudy["two"] ;
                                                                    }
                                                                         ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                
                                                                    <?php 
                                                                    if($studentStudy["three"] > 0){
                                                                        echo $studentStudy["three"]." คน";
                                                                        $sum = $studentStudy["three"] ;
                                                                    }
                                                                         ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                    if($studentStudy["four"] > 0){
                                                                        echo $studentStudy["four"]." คน";
                                                                        $sum = $studentStudy["four"] ;
                                                                    }
                                                                         ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                    
                                                                    if($studentStudy["five"] > 0){
                                                                        echo $studentStudy["five"]." คน" ;
                                                                        $sum = $studentStudy["five"] ;
                                                                    }
                                                                        ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                    if($studentStudy["six"] > 0){
                                                                        echo $studentStudy["six"]." คน";
                                                                        $sum = $studentStudy["six"] ;
                                                                    }
                                                                         ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                        if($studentStudy["seven"] > 0){
                                                                            echo $studentStudy["seven"]." คน" ;
                                                                            $sum = $studentStudy["seven"] ;
                                                                        }
                                                                        ?>
                                                                    
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                <?php 
                                                                        if($studentStudy["eight"] > 0){
                                                                             echo $studentStudy["eight"]." คน";
                                                                             $sum = $studentStudy["eight"] ;
                                                                        }
                                                                        ?>
                                                                
                                                                    
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                        if($studentStudy["nine"] > 0){
                                                                            echo $studentStudy["nine"]." คน" ;
                                                                            $sum = $studentStudy["nine"] ;
                                                                        }
                                                                        ?>
                                                                    
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                <?php 
                                                                        if($studentStudy["ten"] > 0){
                                                                            echo $studentStudy["ten"]." คน" ;
                                                                            $sum = $studentStudy["ten"];
                                                                        }
                                                                        ?>
                                                                   
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                <?php 
                                                                        if($studentStudy["eleven"] > 0){
                                                                            echo $studentStudy["eleven"]." คน" ;
                                                                            $sum =$studentStudy["eleven"];
                                                                        }
                                                                        ?>
                                                                    
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                <?php 
                                                                        if($studentStudy["twelve"] > 0){
                                                                            echo $studentStudy["twelve"]." คน";
                                                                            $sum = $studentStudy["twelve"];
                                                                        }
                                                                         ?>
                                                                    
                                                                </td>
                                                                <td style=" text-align: center; font-weight: bold;"><?php echo $sum?></td>
                                                            </tr>
                                                            <?php
                                                        }
                                                        ?>
                                                    
                                                        <tr>
                                                            <th scope='row' style=" text-align: center;  ">
                                                                ทุกรุ่น</th>
                                                            <td style="font-weight: bold; text-align: center;"><?php echo $g1?> คน</td>
                                                            <td style="font-weight: bold; text-align: center;"><?php echo $g2?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g3?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g4?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g5?> คน</td>
                                                            <td style=" font-weight: bold; text-align: center;"><?php echo $g6?> คน</td>
                                                            <td style=" font-weight: bold; text-align: center;"><?php echo $g7?> คน</td>
                                                            <td style=" font-weight: bold; text-align: center;"><?php echo $g8?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g9?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g10?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g11?> คน</td>
                                                            <td style=" font-weight: bold; text-align: center;"><?php echo $g12?> คน</td>
                                                            <td style=" font-weight: bold; text-align: center;"></td>
                                                            <td style='font-weight: bold; text-align: center;'></td>
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
                <?php ?>
                
                <?php 
                   $studentStudys = getCountStudentStatusTatleSortByGeneretionAndYearStudyByNameCourseIdAndStatusAndSemesterYear($course["nameCourseUse"], "พ้นสภาพนิสิต",$semester["semesterYear"]);
                   
                ?>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">นิสิตพ้นสภาพ</h6>
                                </div>
                                <div class="card-body ">
                                    <div class="row" style="padding: 20px;">
                                        <div class="col-sm-12 mx-auto float-right">
                                            <div class="table-responsive">
                                                <table class="table table-striped" cellspacing="0" style="color: black;">
                                                    <thead style=" ">
                                                        <tr>
                                                            <th rowspan="2" style=" text-align: center; width: 100px;">
                                                                รุ่น</th>
                                                            <th colspan="12" style=" text-align: center; width: 100px;">
                                                                ปีการศึกษา</th>
                                                            <th rowspan="2" style=" text-align: center;">รวม(คน)</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td style=" text-align: center;"></td>
                                                            <?php
                                                            $x=0;
                                                            $color="";
                                                            $year = getCoursePresentByDepartmentId($teacher["departmentId"])["couseStartYear"];
                                                            //echo $thaiDay;
                                                            for ($i = $year; $i < $year + 12; $i++) {
                                                                if($x>=0 && $x<5){
                                                                    $color="#878787";
                                                                }
                                                                elseif($x>=5 && $x<8){
                                                                    $color="#bebebe";
                                                                }
                                                                else{
                                                                    $color="#cecece";
                                                                }
                                                                ?>
                                                                
                                                                <td style=" text-align: center; background-color: <?php echo $color;?>">
                                                                    <?php echo $i ?>
                                                                </td>
                                                            <?php $x++;} ?>

                                                        </tr>
                                                        <?php
                                                        
                                                        $g21=0;
                                                        $g22=0;
                                                        $g23=0;
                                                        $g24=0;
                                                        $g25=0;
                                                        $g26=0;
                                                        $g27=0;
                                                        $g28=0;
                                                        $g29=0;
                                                        $g210=0;
                                                        $g211=0;
                                                        $g212=0;
                                                        foreach ($studentStudys as $studentStudy) {
                                                            $sum=0;
                                                            $g21+=(int)$studentStudy["one"];
                                                            $g22+=(int)$studentStudy["two"];
                                                            $g23+=(int)$studentStudy["three"];
                                                            $g24+=(int)$studentStudy["four"];
                                                            $g25+=(int)$studentStudy["five"];
                                                            $g26+=(int)$studentStudy["six"];
                                                            $g27+=(int)$studentStudy["seven"];
                                                            $g28+=(int)$studentStudy["eight"];
                                                            $g29+=(int)$studentStudy["nine"];
                                                            $g210+=(int)$studentStudy["ten"];
                                                            $g211+=(int)$studentStudy["eleven"];
                                                            $g212+=(int)$studentStudy["twelve"];
                                                            $sum=0;

                                                            ?>
                                                            <tr>
                                                                <td style=" text-align: center;">
                                                                    <?php echo $studentStudy["studyGeneretion"] ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                    
                                                                    if($studentStudy["one"] > 0){
                                                                        echo $studentStudy["one"]." คน" ;
                                                                        $sum = $studentStudy["one"] ;
                                                                    }
                                                                        ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                    if($studentStudy["two"] > 0){
                                                                        echo $studentStudy["two"]." คน";
                                                                        $sum = $studentStudy["two"] ;
                                                                    }
                                                                         ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                
                                                                    <?php 
                                                                    if($studentStudy["three"] > 0){
                                                                        echo $studentStudy["three"]." คน";
                                                                        $sum = $studentStudy["three"] ;
                                                                    }
                                                                         ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                    if($studentStudy["four"] > 0){
                                                                        echo $studentStudy["four"]." คน";
                                                                        $sum = $studentStudy["four"] ;
                                                                    }
                                                                         ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                    
                                                                    if($studentStudy["five"] > 0){
                                                                        echo $studentStudy["five"]." คน" ;
                                                                        $sum = $studentStudy["five"] ;
                                                                    }
                                                                        ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                    if($studentStudy["six"] > 0){
                                                                        echo $studentStudy["six"]." คน";
                                                                        $sum = $studentStudy["six"] ;
                                                                    }
                                                                         ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                        if($studentStudy["seven"] > 0){
                                                                            echo $studentStudy["seven"]." คน" ;
                                                                            $sum = $studentStudy["seven"] ;
                                                                        }
                                                                        ?>
                                                                    
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                <?php 
                                                                        if($studentStudy["eight"] > 0){
                                                                             echo $studentStudy["eight"]." คน";
                                                                             $sum = $studentStudy["eight"] ;
                                                                        }
                                                                        ?>
                                                                
                                                                    
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                        if($studentStudy["nine"] > 0){
                                                                            echo $studentStudy["nine"]." คน" ;
                                                                            $sum = $studentStudy["nine"] ;
                                                                        }
                                                                        ?>
                                                                    
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                <?php 
                                                                        if($studentStudy["ten"] > 0){
                                                                            echo $studentStudy["ten"]." คน" ;
                                                                            $sum = $studentStudy["ten"];
                                                                        }
                                                                        ?>
                                                                   
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                <?php 
                                                                        if($studentStudy["eleven"] > 0){
                                                                            echo $studentStudy["eleven"]." คน" ;
                                                                            $sum =$studentStudy["eleven"];
                                                                        }
                                                                        ?>
                                                                    
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                <?php 
                                                                        if($studentStudy["twelve"] > 0){
                                                                            echo $studentStudy["twelve"]." คน";
                                                                            $sum = $studentStudy["twelve"];
                                                                        }
                                                                         ?>
                                                                    
                                                                </td>
                                                                <td style=" text-align: center; font-weight: bold;"><?php echo $sum?></td>
                                                            </tr>
                                                            <?php
                                                        }
                                                        ?>
                                                    
                                                        <tr>
                                                            <th scope='row' style=" text-align: center;  ">
                                                                ทุกรุ่น</th>
                                                            <td style="font-weight: bold; text-align: center;"><?php echo $g21?> คน</td>
                                                            <td style="font-weight: bold; text-align: center;"><?php echo $g22?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g23?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g24?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g25?> คน</td>
                                                            <td style=" font-weight: bold; text-align: center;"><?php echo $g26?> คน</td>
                                                            <td style=" font-weight: bold; text-align: center;"><?php echo $g27?> คน</td>
                                                            <td style=" font-weight: bold; text-align: center;"><?php echo $g28?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g29?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g210?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g211?> คน</td>
                                                            <td style=" font-weight: bold; text-align: center;"><?php echo $g212?> คน</td>
                                                            <td style=" font-weight: bold; text-align: center;"></td>
                                                            <td style='font-weight: bold; text-align: center;'></td>
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
                <?php ?>
                
                <?php 
                   $studentStudys = getCountStudentStatusTatleSortByGeneretionAndYearStudyByNameCourseIdAndStatusAndSemesterYear($course["nameCourseUse"], "จบการศึกษา",$semester["semesterYear"]);
                  
                   
                ?>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">นิสิตจบการศึกษา</h6>
                                </div>
                                <div class="card-body ">
                                    <div class="row" style="padding: 20px;">
                                        <div class="col-sm-12 mx-auto float-right">
                                            <div class="table-responsive">
                                                <table class="table table-striped" cellspacing="0" style="color: black;">
                                                    <thead style=" ">
                                                        <tr>
                                                            <th rowspan="2" style=" text-align: center; width: 100px;">
                                                                รุ่น</th>
                                                            <th colspan="12" style=" text-align: center; width: 100px;">
                                                                ปีการศึกษา</th>
                                                            <th rowspan="2" style=" text-align: center;">รวม(คน)</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <tr>
                                                            <td style=" text-align: center;"></td>
                                                            <?php
                                                            $x=0;
                                                            $color="";
                                                            $year = getCoursePresentByDepartmentId($teacher["departmentId"])["couseStartYear"];
                                                            //echo $thaiDay;
                                                            for ($i = $year; $i < $year + 12; $i++) {
                                                                if($x>=0 && $x<5){
                                                                    $color="#878787";
                                                                }
                                                                elseif($x>=5 && $x<8){
                                                                    $color="#bebebe";
                                                                }
                                                                else{
                                                                    $color="#cecece";
                                                                }
                                                                ?>
                                                                
                                                                <td style=" text-align: center; background-color: <?php echo $color;?>">
                                                                    <?php echo $i ?>
                                                                </td>
                                                            <?php $x++;} ?>

                                                        </tr>
                                                        <?php
                                                        $g31=0;
                                                        $g32=0;
                                                        $g33=0;
                                                        $g34=0;
                                                        $g35=0;
                                                        $g36=0;
                                                        $g37=0;
                                                        $g38=0;
                                                        $g39=0;
                                                        $g310=0;
                                                        $g311=0;
                                                        $g312=0;
                                                        foreach ($studentStudys as $studentStudy) {
                                                            $sum=0;
                                                            $g31+=(int)$studentStudy["one"];
                                                            $g32+=(int)$studentStudy["two"];
                                                            $g33+=(int)$studentStudy["three"];
                                                            $g34+=(int)$studentStudy["four"];
                                                            $g35+=(int)$studentStudy["five"];
                                                            $g36+=(int)$studentStudy["six"];
                                                            $g37+=(int)$studentStudy["seven"];
                                                            $g38+=(int)$studentStudy["eight"];
                                                            $g39+=(int)$studentStudy["nine"];
                                                            $g310+=(int)$studentStudy["ten"];
                                                            $g311+=(int)$studentStudy["eleven"];
                                                            $g312+=(int)$studentStudy["twelve"];
                                                            $sum=0;

                                                            ?>
                                                            <tr>
                                                                <td style=" text-align: center;">
                                                                    <?php echo $studentStudy["studyGeneretion"] ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                    
                                                                    if($studentStudy["one"] > 0){
                                                                        echo $studentStudy["one"]." คน" ;
                                                                        $sum = $studentStudy["one"] ;
                                                                    }
                                                                        ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                    if($studentStudy["two"] > 0){
                                                                        echo $studentStudy["two"]." คน";
                                                                        $sum = $studentStudy["two"] ;
                                                                    }
                                                                         ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                
                                                                    <?php 
                                                                    if($studentStudy["three"] > 0){
                                                                        echo $studentStudy["three"]." คน";
                                                                        $sum = $studentStudy["three"] ;
                                                                    }
                                                                         ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                    if($studentStudy["four"] > 0){
                                                                        echo $studentStudy["four"]." คน";
                                                                        $sum = $studentStudy["four"] ;
                                                                    }
                                                                         ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                    
                                                                    if($studentStudy["five"] > 0){
                                                                        echo $studentStudy["five"]." คน" ;
                                                                        $sum = $studentStudy["five"] ;
                                                                    }
                                                                        ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                    if($studentStudy["six"] > 0){
                                                                        echo $studentStudy["six"]." คน";
                                                                        $sum = $studentStudy["six"] ;
                                                                    }
                                                                         ?>
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                        if($studentStudy["seven"] > 0){
                                                                            echo $studentStudy["seven"]." คน" ;
                                                                            $sum = $studentStudy["seven"] ;
                                                                        }
                                                                        ?>
                                                                    
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                <?php 
                                                                        if($studentStudy["eight"] > 0){
                                                                             echo $studentStudy["eight"]." คน";
                                                                             $sum = $studentStudy["eight"] ;
                                                                        }
                                                                        ?>
                                                                
                                                                    
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                    <?php 
                                                                        if($studentStudy["nine"] > 0){
                                                                            echo $studentStudy["nine"]." คน" ;
                                                                            $sum = $studentStudy["nine"] ;
                                                                        }
                                                                        ?>
                                                                    
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                <?php 
                                                                        if($studentStudy["ten"] > 0){
                                                                            echo $studentStudy["ten"]." คน" ;
                                                                            $sum = $studentStudy["ten"];
                                                                        }
                                                                        ?>
                                                                   
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                <?php 
                                                                        if($studentStudy["eleven"] > 0){
                                                                            echo $studentStudy["eleven"]." คน" ;
                                                                            $sum =$studentStudy["eleven"];
                                                                        }
                                                                        ?>
                                                                    
                                                                </td>
                                                                <td style=" text-align: center;">
                                                                <?php 
                                                                        if($studentStudy["twelve"] > 0){
                                                                            echo $studentStudy["twelve"]." คน";
                                                                            $sum = $studentStudy["twelve"];
                                                                        }
                                                                         ?>
                                                                    
                                                                </td>
                                                                <td style=" text-align: center; font-weight: bold;"><?php echo $sum?></td>
                                                            </tr>
                                                            <?php
                                                        }
                                                        ?>
                                                
                                                        <tr>
                                                        <th scope='row' style=" text-align: center;  ">
                                                                ทุกรุ่น</th>
                                                            <td style="font-weight: bold; text-align: center;"><?php echo $g31?> คน</td>
                                                            <td style="font-weight: bold; text-align: center;"><?php echo $g32?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g33?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g34?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g35?> คน</td>
                                                            <td style=" font-weight: bold; text-align: center;"><?php echo $g36?> คน</td>
                                                            <td style=" font-weight: bold; text-align: center;"><?php echo $g37?> คน</td>
                                                            <td style=" font-weight: bold; text-align: center;"><?php echo $g38?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g39?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g310?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $g311?> คน</td>
                                                            <td style=" font-weight: bold; text-align: center;"><?php echo $g312?> คน</td>
                                                            <td style=" font-weight: bold; text-align: center;"></td>
                                                            <td style='font-weight: bold; text-align: center;'></td>
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
                <?php ?>



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

                <script src="https://cdn.jsdelivr.net/npm/chart.js@4.0.1/dist/chart.umd.min.js"></script>
                <script>

                    var lists1 = <?php echo json_encode($lists); ?>;


                    var firstEntrys1 = <?php echo json_encode($firstEntrys); ?>;

                    var retires1 = <?php echo json_encode($retires); ?>;
                    var studys1 = <?php echo json_encode($studys); ?>;
                    var grads1 = <?php echo json_encode($grads); ?>;

                    var ctx = document.getElementById("myChart").getContext('2d');
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: lists1,
                            datasets: [{

                                type: 'line',
                                label: 'นิสิตแรกเข้า',
                                backgroundColor: 'rgb(0, 107, 201)',
                                data: firstEntrys1,
                                borderColor: 'rgba(0, 107, 201,1)',
                                lineTension: 0,
                                fill: false
                            },
                            {
                                label: 'นิสิตพ้นสภาพ',
                                data: retires1,
                                backgroundColor: 'rgb(245, 123, 57)',
                           
                                borderWidth: 0
                            },
                            {
                                label: 'นิสิตกำลังศึกษา',
                                data: studys1,
                                backgroundColor: '#a4ebf3',
                             
                                borderWidth: 0
                            },
                            {
                                label: 'นิสิตจบการศึกษา',
                                data: grads1,
                                backgroundColor: '#abbdee',
                               
                                borderWidth: 0
                            },
                            {
                                label: 'นิสิตตกค้าง',
                                data: grads1,
                                backgroundColor: '#ff6962',
                               
                                borderWidth: 0
                            },


                            ]

                        },

                        options: {
                            scales: {
                                x: {
                                    stacked: true,
                                },
                                y: {
                                    stacked: true
                                }
                            },
                            plugins: {
                                legend: {
                                    display: false
                                }
                            },
                            responsive: true,
                        }
                        
                    });
                </script>

                <script>

                    var lists2 = <?php echo json_encode($listSem); ?>;


                    var firstEntrys2 = <?php echo json_encode($firstEntrys2); ?>;

                    var retires2 = <?php echo json_encode($retires2); ?>;
                    var studys2 = <?php echo json_encode($studys2); ?>;
                    var grads2 = <?php echo json_encode($grads2); ?>;

                    var ctx = document.getElementById("myCharts");
                    var myChart = new Chart(ctx, {
                        //type: 'bar',
                        //type: 'line',
                        type: 'bar',
                        data: {
                            labels: lists2,
                            datasets: [{

                                type: 'line',
                                backgroundColor: 'rgb(0, 107, 201)',
                                label: 'นิสิตแรกเข้า',
                                data: firstEntrys2,
                                borderColor: 'rgba(0, 107, 201,1)',
                                lineTension: 0,
                                fill: false
                            },
                            {
                                label: 'นิสิตพ้นสภาพ',
                                data: retires2,
                                backgroundColor: 'rgb(245, 123, 57)',
                             
                                borderWidth: 0
                            },
                            {
                                label: 'นิสิตกำลังศึกษา',
                                data: studys2,
                                backgroundColor: '#a4ebf3',
                              
                                borderWidth: 0
                            },
                            {
                                label: 'นิสิตจบการศึกษา',
                                data: grads2,
                                backgroundColor: '#abbdee',
                              
                                borderWidth: 0
                            }

                            ]

                        },

                        options: {
                            scales: {

                                x: {
                                    stacked: true,
                                },
                                y: {
                                    stacked: true
                                }
                            },
                            plugins: {
                                legend: {
                                    display: false
                                }
                            },
                            responsive: true,

                        }
                    });
                </script>


</body>

</html>