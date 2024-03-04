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



        $courseId = $_POST["courseId"];
        $yearSerach = $_POST["year"];
        $partSearch = $_POST["part"];

        $teacher = getTeacherByUsernameTeacher($_SESSION["access-user"]);
        $semester = getSemesterPresent();
        $course = getCourseById($courseId);

?>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <?php include('../layout/teacher/home.php'); ?>

                    <div>
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
                                        <select class="form-control" data-live-search="true" name = "courseId">
                                            <option value="default">--กรุณาเลือกหลักสูตร--</option>

                                            <?php
                                                $courses = getCourseByDepartmentId($teacher["teacherId"]);

                                                foreach($courses as $course){

                                            ?>

                                            <option value="<?php echo $course["courseId"]?>"><?php echo $course["nameCourseUse"]." ( ".$course["planCourse"]." )"?>
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

                                            foreach($years as $year){
                                            ?>

                                            <option value="<?php echo  $year["semesterYear"]?>"><?php echo  $year["semesterYear"]?></option>
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
                            <h5>หลักสูตร: <?php echo $course["nameCourseUse"]." (".$course["planCourse"].") "?> ปีการศึกษา: <?php echo $yearSerach?> &nbsp; ภาคการศึกษา: <?php echo $partSearch?> </h5>

                        </div>

                    </div>
                    <div class="row">
                        <div class="col-sm-12 ">
                            <div class="row">
                                <div class="col-sm-5 mx-auto">
                                    <table class="table table-hover"
                                        style="margin-top: 30px; border: 1px solid black; border-collapse: collapse; ">
                                        <tr style="border: 1px solid black; border-collapse: collapse; ">
                                            <th
                                                style="border: 1px solid black; border-collapse: collapse; width: 50%; ">

                                                <?php
                                                
                                                $gpaxStatusCount = getCountStudentGPAXStatusByTeacherIdAndSemesterYearAndSemesterPartAndCourseId($teacher["teacherId"],$yearSerach,$partSearch,$courseId);
                                                ?>

                                                <div style="color: rgb(0, 9, 188);">
                                                    <div class="text-center">
                                                        <a style="color: rgb(0, 9, 188);"
                                                            href="../report_teacher/grade/honor.php">
                                                            <h4>เกียรตินิยม</h4>
                                                        </a>
                                                    </div>
                                                    <div class="text-center">
                                                        <h1 style="font-weight: bolder; font-size: 70px; "><?php echo $gpaxStatusCount["blue"]?></h1>
                                                    </div>
                                                    <div class="text-right">
                                                        <p>คน</p>
                                                    </div>
                                                </div>


                                            </th>
                                            <th style="border: 1px solid black; border-collapse: collapse; ">
                                                <div style="color: rgb(0, 110, 22);">
                                                    <div class="text-center">
                                                        <a style="color: rgb(0, 110, 22);"
                                                            href="../report_teacher/grade/normal.php">
                                                            <h4>ปกติ</h4>
                                                        </a>
                                                    </div>
                                                    <div class="text-center">
                                                        <h1 style="font-weight: bolder; font-size: 70px;"><?php echo $gpaxStatusCount["green"]?></h1>
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
                                                        <a style="color: #ff8c00;"
                                                            href="../report_teacher/grade/prohigh.php">
                                                            <h4>รอพินิจ</h4>
                                                        </a>
                                                    </div>
                                                    <div class="text-center">
                                                        <h1 style="font-weight: bolder; font-size: 70px;"><?php echo $gpaxStatusCount["orange"]?></h1>
                                                    </div>
                                                    <div class="text-right">
                                                        <p>คน</p>
                                                    </div>
                                                </div>
                                            </th>
                                            <th style="border: 1px solid black; border-collapse: collapse;">
                                                <div style="color: rgb(255, 0, 0);">
                                                    <div class="text-center">
                                                        <a style="color: rgb(255, 0, 0);"
                                                            href="../report_teacher/grade/prodown.php">
                                                            <h4>โปรต่ำ</h4>
                                                        </a>
                                                    </div>
                                                    <div class="text-center">
                                                        <h1 style="font-weight: bolder; font-size: 70px;"><?php echo $gpaxStatusCount["red"]?></h1>
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
                                            
                                            $planingCount = getCountStudentByPlaningByTeacherId($teacher["teacherId"]);
                                            
                                            
                                            
                                            ?>

                                                <div style="color: rgb(0, 9, 188);">
                                                    <div class="text-center">
                                                        <a style="color: rgb(0, 9, 188);"
                                                            href="../report_teacher/status/plan.php">
                                                            <h4>ตามแผน</h4>
                                                        </a>
                                                    </div>
                                                    <div class="text-center">
                                                        <h1 style="font-weight: bolder; font-size: 70px; "><?php echo $planingCount["plan"]["count"] ?></h1>
                                                    </div>
                                                    <div class="text-right">
                                                        <p>คน</p>
                                                    </div>
                                                </div>


                                            </th>
                                            <th style="border: 1px solid black; border-collapse: collapse; ">
                                                <div style="color: rgb(0, 110, 22);">
                                                    <div class="text-center">
                                                        <a style="color: rgb(0, 110, 22);"
                                                            href="../report_teacher/status/noplan.php">
                                                            <h4>ไม่ตามแผน</h4>
                                                        </a>
                                                    </div>
                                                    <div class="text-center">
                                                        <h1 style="font-weight: bolder; font-size: 70px;"><?php echo $planingCount["notPlan"]["count"] ?></h1>
                                                    </div>
                                                    <div class="text-right">
                                                        <p>คน</p>
                                                    </div>
                                                </div>
                                            </th>
                                        </tr>
                                        <tr>
                                            <th style="border: 1px solid black; border-collapse: collapse;">

                                                <div style="color: #ff8c00;">
                                                    <div class="text-center">
                                                        <a style="color: #ff8c00;"
                                                            href="../report_teacher/status/retry.php">
                                                            <h4>พ้นสภาพ</h4>
                                                        </a>
                                                    </div>
                                                    <div class="text-center">
                                                        <h1 style="font-weight: bolder; font-size: 70px;"><?php echo $planingCount["retire"]["count"] ?></h1>
                                                    </div>
                                                    <div class="text-right">
                                                        <p>คน</p>
                                                    </div>
                                                </div>
                                            </th>
                                            <th style="border: 1px solid black; border-collapse: collapse;">
                                                <div style="color: rgb(255, 0, 0);">
                                                    <div class="text-center">
                                                        <a style="color: rgb(255, 0, 0);"
                                                            href="../report_teacher/status/finish.php">
                                                            <h4>จบการศึกษา</h4>
                                                        </a>
                                                    </div>
                                                    <div class="text-center">
                                                        <h1 style="font-weight: bolder; font-size: 70px;"><?php echo $planingCount["grad"]["count"] ?></h1>
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
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <?php
                                                        
                                                        $gens = getCountStudentPlanStatusBystudyGeneretionByTeacherIdAndSemesterYearAndSemesterPartAndCourseId($teacher["teacherId"],$yearSerach,$partSearch,$courseId);

                                                        $listgens2=[];
                                                        $sumPlan = 0;
                                                        $sumNotPlan = 0;
                                                        $sumRetire = 0;
                                                        $sumGrad = 0;
                                                        $sPLan2=[];
                                                        $sNotPlan2=[];
                                                        $sRetire2=[];
                                                        $sGrad2=[];

                                                        foreach($gens as $gen){
                                                            echo "
                                                            <tr>
                                                                <td style=\" text-align: right;\">".$gen["studyGeneretion"]."</td>
                                                                <td style=\" text-align: right;\">
                                                                ".$gen["planCount"]." คน
                                                                </td>
                                                                <td style=\" text-align: right;\">
                                                                ".$gen["notPlanCount"]." คน
                                                                </td>
                                                                <td style=\" text-align: right;\">".$gen["retire"]." คน</td>
                                                                <td style=\" text-align: right;\">".$gen["grad"]." คน</td>
                                                            </tr>
                                                            
                                                            
                                                            ";
                                                            $listgens2[]="รุ่น ".(string)$gen["studyGeneretion"];
                                                            $sumPlan += $gen["planCount"];
                                                            $sPLan2[]=(int)$sumPlan;
                                                            $sumNotPlan += $gen["notPlanCount"];
                                                            $sNotPlan2[]=(int)$sumNotPlan;
                                                            $sumRetire = $gen["retire"];
                                                            $sRetire2[] =(int) $sumRetire;
                                                            $sumGrad = $gen["grad"];
                                                            $sGrad2[] =(int) $sumGrad;
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
                                                            <td style="font-weight: bold; text-align: right;"><?php echo $sumPlan?> </td>
                                                            <td style='font-weight: bold; text-align: right;'><?php echo $sumNotPlan?></td>
                                                            <td style='font-weight: bold; text-align: right;'><?php echo $sumRetire?></td>
                                                            <td style='font-weight: bold; text-align: right;'><?php echo $sumGrad?></td>
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
                                    $y=substr($thaiDay-4, -2);
                                    $yNow=substr($thaiDay, -2);
                                    $rangeGradeStudyYearOnes = getCountGradeRangeByTeacherIdAndStudyYearAndSemesterYearAndSemesterPartAndCourseId($teacher["teacherId"],1,$yearSerach,$partSearch,$courseId);
                                    $pee1gen=[];
                                    $pee1blues=[];
                                    $pee1greens=[];
                                    $pee1oranges=[];
                                    $pee1reds=[];
                                    for($y; $y<$yNow; $y++){
                                        
                                        foreach($rangeGradeStudyYearOnes as $range){
                                            if((int)$range["studyGeneretion"] == $y){
                                                $pee1gen[]="รุ่น ".(string)$range["studyGeneretion"];
                                                $pee1blues[]=$range["blue"];
                                                $pee1greens[]=$range["green"];
                                                $pee1oranges[]=$range["orange"];
                                                $pee1reds[]=$range["red"];
                                            }
                                            else{
                                                $pee1gen[]="รุ่น ".(string)$y;
                                                $pee1blues[]="0";
                                                $pee1greens[]="0";
                                                $pee1oranges[]="0";
                                                $pee1reds[]="0";
                                            }
                                        }
                                    
                                    }
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
                                    $y=substr($thaiDay-4, -2);
                                    $yNow=substr($thaiDay, -2);
                                    $rangeGradeStudyYearOnes = getCountGradeRangeByTeacherIdAndStudyYearAndSemesterYearAndSemesterPartAndCourseId($teacher["teacherId"],2,$yearSerach,$partSearch,$courseId);
                                    $pee2gen=[];
                                    $pee2blues=[];
                                    $pee2greens=[];
                                    $pee2oranges=[];
                                    $pee2reds=[];
                                    for($y; $y<$yNow; $y++){
                                        foreach($rangeGradeStudyYearOnes as $range){
                                            if((int)$range["studyGeneretion"] == $y){
                                            
                                                $pee2gen[]="รุ่น ".(string)$range["studyGeneretion"];
                                                $pee2blues[]=$range["blue"];
                                                $pee2greens[]=$range["green"];
                                                $pee2oranges[]=$range["orange"];
                                                $pee2reds[]=$range["red"];    
                                            }
                                            else{
                                                $pee2gen[]="รุ่น ".(string)$y;
                                                $pee2blues[]="0";
                                                $pee2greens[]="0";
                                                $pee2oranges[]="0";
                                                $pee2reds[]="0";
                                            }
                                        }

                                    }
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
                                    $y=substr($thaiDay-4, -2);
                                    $yNow=substr($thaiDay, -2);
                                    $rangeGradeStudyYearOnes = getCountGradeRangeByTeacherIdAndStudyYearAndSemesterYearAndSemesterPartAndCourseId($teacher["teacherId"],3,$yearSerach,$partSearch,$courseId);
                                    $pee3gen=[];
                                    $pee3blues=[];
                                    $pee3greens=[];
                                    $pee3oranges=[];
                                    $pee3reds=[];
                                    for($y; $y<$yNow; $y++){
                                        foreach($rangeGradeStudyYearOnes as $range){
                                            if((int)$range["studyGeneretion"] == $y){
                                                $pee3gen[]="รุ่น ".(string)$range["studyGeneretion"];
                                                $pee3blues[]=$range["blue"];
                                                $pee3greens[]=$range["green"];
                                                $pee3oranges[]=$range["orange"];
                                                $pee3reds[]=$range["red"];
                                            } 
                                            else{
                                                $pee3gen[]="รุ่น ".(string)$y;
                                                $pee3blues[]="0";
                                                $pee3greens[]="0";
                                                $pee3oranges[]="0";
                                                $pee3reds[]="0";
                                            }
                                        }
                                    }
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
                                    $y=substr($thaiDay-4, -2);
                                    $yNow=substr($thaiDay, -2);
                                    $rangeGradeStudyYearOnes = getCountGradeRangeByTeacherIdAndStudyYearAndSemesterYearAndSemesterPartAndCourseId($teacher["teacherId"],4,$yearSerach,$partSearch,$courseId);
                                    
                                    $pee4gen=[];
                                    $pee4blues=[];
                                    $pee4greens=[];
                                    $pee4oranges=[];
                                    $pee4reds=[];
                                    for($y; $y<$yNow; $y++){

                                        foreach($rangeGradeStudyYearOnes as $range){
                                            if((int)$range["studyGeneretion"] == $y){
                                                $pee4gen[]="รุ่น ".(string)$range["studyGeneretion"];
                                                $pee4blues[]=$range["blue"];
                                                $pee4greens[]=$range["green"];
                                                $pee4oranges[]=$range["orange"];
                                                $pee4reds[]=$range["red"];
                                            }
                                            else{
                                                $pee4gen[]="รุ่น ".(string)$y;
                                                $pee4blues[]="0";
                                                $pee4greens[]="0";
                                                $pee4oranges[]="0";
                                                $pee4reds[]="0";
                                            }
                                        }
                                    }
                                ?>
                                <div class="card-body">
                                    <canvas id="pee4"></canvas>
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
                                                            <th style="text-align: right; ">ลาออก</th>
                                                            <th style="text-align: center; ">รายละเอียด</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <?php
                                                        
                                                        $countStudySemesters = getCountStudySemesterYearPartByTeacherIDAndSemesterYearAndSemesterPartAndCourseId($teacher["teacherId"],$yearSerach,$partSearch,$courseId);
                                                        
                                                        $sortYears=[];
                                                        $plans=[];
                                                        $notPlan=[];
                                                        $resignPlan=[];
                                                        $idterm = 0;

                                                        foreach($countStudySemesters as $countStudySemester){
                                                            
                                                            $sortYears[] = (string)$countStudySemester["semesterYear"]." ".(string)$countStudySemester["semesterPart"];
                                                            $plans[] = (int)$countStudySemester["planStatus"];
                                                            $notPlan[] = (int)$countStudySemester["notPlanStatus"];
                                                            $resignPlan[] = (int)$countStudySemester["resign"];

                                                        
                                                        ?>
                                                        <tr>
                                                            <td style=" text-align: right;"><?php echo $countStudySemester["semesterYear"]?></td>
                                                            <td style=" text-align: right;"><?php echo $countStudySemester["semesterPart"]?></td>
                                                            <td style=" text-align: right;">
                                                            <?php echo $countStudySemester["planStatus"]?> คน
                                                            </td>
                                                            <td style=" text-align: right;">
                                                            <?php echo $countStudySemester["notPlanStatus"]?> คน
                                                            </td>
                                                            <td style=" text-align: right;"><?php echo $countStudySemester["resign"]?> คน</td>
                                                            <td class="text-center">
                                                                <a data-toggle="modal" data-target="#modal<?php echo $idterm?>" >
                                                                    <i class="fas fa-search fa-sm"></i>
                                                                </a>
                                                            </td>
                                                        </tr>
                                                        <?php
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
                                    $y=substr($thaiDay-4, -2);
                                    $yNow=substr($thaiDay, -2);
                                    $studyGeneretionGPAXs = getGPAXStatusGerenetionByTeacherIdAndSemesterYearAndSemesterPartAndCourseId($teacher["teacherId"],$yearSerach,$partSearch,$courseId);
                                    $nowgen=[];
                                    $BNG=[];
                                    $GNG=[];
                                    $ONG=[];
                                    $RNG=[];
                                    for($y; $y<$yNow; $y++){
                                        foreach($studyGeneretionGPAXs as $grade){
                                            if((int)$range["studyGeneretion"] == $y){
                                                $nowgen[] = "รุ่น ".(string)$grade["studyGeneretion"];
                                                $BNG[] = (int)$grade["blue"];
                                                $GNG[] = (int)$grade["green"];
                                                $ONG[] = (int)$grade["orange"];
                                                $RNG[] = (int)$grade["red"];
                                            }
                                            else{
                                                $nowgen[]="รุ่น ".(string)$y;
                                                $BNG[]="0";
                                                $GNG[]="0";
                                                $ONG[]="0";
                                                $RNG[]="0";
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
                                
                                
                                    $endgen=[];
                                    $BEG=[];
                                    $GEG=[];
                                    $OEG=[];
                                    $REG=[];
                                    $studyGraduateGeneretionGPAXs = getGPAXStatusGerenetionGraduateByTeacherIdAndSemesterYearAndSemesterPartAndCourseId($teacher["teacherId"],$yearSerach,$partSearch,$courseId);
                                    foreach($studyGraduateGeneretionGPAXs as $gradeEnd){
                                        $endgen[] = "รุ่น ".(string)$gradeEnd["studyGeneretion"];
                                        $BEG[] = (int)$gradeEnd["blue"];
                                        $GEG[] = (int)$gradeEnd["green"];
                                        $OEG[] = (int)$gradeEnd["orange"];
                                        $REG[] = (int)$gradeEnd["red"];
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

                    <!--modal-->
                    <?php
                        $term =0;
                        foreach($countStudySemesters as $countStudySemester){

                    ?>
                        <div id="modal<?php echo $term?>" class="modal fade" style="color: black;">
                            <div class="modal-dialog modal-lg">
                                <div class="modal-content">
                                    <div class="modal-header" style="height: 90px;">
                                        <h5>ปีการศึกษา <?php echo $countStudySemester["semesterYear"]?> ภาคการศึกษา <?php echo $countStudySemester["semesterPart"]?></h5>
                                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                                        <br>

                                    </div>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตตามหลักสูตร <?php echo $countStudySemester["planStatus"]?> คน</h5>
                                    <?php
                                        if((int)$countStudySemester["planStatus"] > 0){
                                        
                                    ?>
                                        <div class="modal-body" id="std_detail">
                                            <table class="table">

                                                <thead>
                                                    <tr>
                                                        <th>รหัสนิสิต</th>
                                                        <th>ชื่อ-นามสกุล</th>
                                                        <th>เกรดเฉลี่ย</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                        foreach($countStudySemester["studentPlans"] as $sPlan){
                                                    ?>
                                                        <tr>
                                                            <th><?php echo $sPlan["studentId"]?></th>
                                                            <th>นาย<?php echo $sPlan["fisrtNameTh"]." ".$sPlan["lastNameTh"]?></th>
                                                            <th><?php echo $sPlan["gpaAll"]?></th>
                                                        </tr>
                                                    <?php
                                                        }
                                                    ?>

                                                </tbody>
                                            </table>

                                        </div>
                                    <?php } ?>
                                    <hr>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตไม่ตามหลักสูตร <?php echo $countStudySemester["notPlanStatus"]?> คน</h5>
                                    <?php
                                        if((int)$countStudySemester["studentNotPlans"] > 0){
                                            
                                    ?>
                                    <div class="modal-body" id="std_detail">
                                        <table class="table">

                                            <thead>
                                                <tr>
                                                    <th>รหัสนิสิต</th>
                                                    <th>ชื่อ-นามสกุล</th>
                                                    <th>เกรดเฉลี่ย</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <?php
                                                    foreach($countStudySemester["studentNotPlans"] as $sNotPlan){
                                                ?>
                                                    <tr>
                                                        <th><?php echo $sNotPlan["studentId"]?></th>
                                                        <th>นาย<?php echo $sNotPlan["fisrtNameTh"]." ".$sNotPlan["lastNameTh"]?></th>
                                                        <th><?php echo $sNotPlan["gpaAll"]?></th>
                                                    </tr>
                                                <?php
                                                    }
                                                ?>


                                            </tbody>
                                        </table>

                                    </div>
                                    <?php } ?>
                                    <hr>
                                    <h5 class="modal-title" style="margin-left: 10px;">นิสิตตามหลักสูตร <?php echo $countStudySemester["resign"]?> คน</h5>
                                    <?php
                                        if((int)$countStudySemester["studentResign"] > 0){
                                            
                                    ?>
                                        <div class="modal-body" id="std_detail">
                                            <table class="table">

                                                <thead>
                                                    <tr>
                                                        <th>รหัสนิสิต</th>
                                                        <th>ชื่อ-นามสกุล</th>
                                                        <th>เกรดเฉลี่ย</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                        foreach($countStudySemester["studentResign"] as $sRePlan){
                                                    ?>
                                                        <tr>
                                                            <th><?php echo $sRePlan["studentId"]?></th>
                                                            <th>นาย<?php echo $sRePlan["fisrtNameTh"]." ".$sRePlan["lastNameTh"]?></th>
                                                            <th><?php echo $sRePlan["gpaAll"]?></th>
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
                        
                        var gens2 = <?php echo json_encode($listgens2); ?>;
                        console.log(gens2);

                        var sPLans2 = <?php echo json_encode($sPLan2); ?>;
                        console.log(sPLans2);
                        var sNotPlans2 = <?php echo json_encode($sNotPlan2); ?>;
                        console.log(sNotPlans2);
                        var sRetires2 = <?php echo json_encode($sRetire2); ?>;
                        console.log(sRetires2);
                        var sGrads2 = <?php echo json_encode($sGrad2); ?>;
                        console.log(sGrads2);

                        var ctx = document.getElementById("learnyear");
                        var myChart = new Chart(ctx, {
                            //type: 'bar',
                            //type: 'line',
                            type: 'bar',
                            data: {
                                labels: gens2,
                                datasets: [
                                    {
                                        label: 'ตามหลักสูตร',
                                        data: sPLans2,
                                        backgroundColor: "rgba(0, 9, 188,0.7)",
                                        borderWidth: 0
                                    },
                                    {
                                        label: ['ไม่ตามหลักสูตร'],
                                        data: sNotPlans2,
                                        backgroundColor: "rgba(0, 110, 22,0.7)",
                                        borderWidth: 0
                                    },
                                    {
                                        label: ['พ้นสภาพ'],
                                        data: sRetires2,
                                        backgroundColor: 'rgba(255,128,0,0.7)',
                                        borderWidth: 0
                                    },
                                    {
                                        label: ['จบการศึกษา'],
                                        data: sGrads2,
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
                        var p2gen = <?php echo json_encode($pee2gen); ?>;

                        var p2blue = <?php echo json_encode($pee2blues); ?>;
                        var p2green = <?php echo json_encode($pee2greens); ?>;
                        var p2orange = <?php echo json_encode($pee2oranges); ?>;
                        var p2red = <?php echo json_encode($pee2reds); ?>;

                        var ctx = document.getElementById("pee2");
                        var myChart = new Chart(ctx, {
                            //type: 'bar',
                            //type: 'line',
                            type: 'bar',
                            data: {
                                labels: p2gen,
                                datasets: [{
                                    label: '3.25-4.00',
                                    data: p2blue,
                                    backgroundColor: "rgba(0, 9, 188,0.7)",
                                    borderWidth: 0
                                },
                                {
                                    label: '2.00-3.24',
                                    data: p2green,
                                    backgroundColor: "rgba(0, 110, 22,0.7)",
                                    borderWidth: 0
                                },
                                {
                                    label: '1.75-1.99',
                                    data: p2orange,
                                    backgroundColor: 'rgba(255,128,0,0.7)',
                                    borderWidth: 0
                                },
                                {
                                    label: '0.00-1.74',
                                    data: p2red,
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
                                    backgroundColor: "#89cfef",
                                    borderWidth: 0
                                },
                                {
                                    label: ['ไม่ตามหลักสุตร'],
                                    data: notPlans,
                                    backgroundColor: "#ffab76",
                                    borderWidth: 0
                                },
                                {
                                    label: ['ลาออก'],
                                    data:resignPlans,
                                    backgroundColor: '#ff6962',
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
                                    label: 'เกียรตินิยม',
                                    data: bluegen,
                                    backgroundColor: "rgba(0, 9, 188,0.7)",
                                    borderWidth: 0
                                },
                                {
                                    label: 'ปกติ',
                                    data: greengen,
                                    backgroundColor: "rgba(0, 110, 22,0.7)",
                                    borderWidth: 0
                                },
                                {
                                    label: 'โปรสูง',
                                    data: orangegen,
                                    backgroundColor: 'rgba(255,128,0,0.7)',
                                    borderWidth: 0
                                },
                                {
                                    label: 'โปรต่ำ',
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
                                    label: 'เกียรตินิยม',
                                    data: bluegenend,
                                    backgroundColor: "rgba(0, 9, 188,0.7)",
                                    borderWidth: 0
                                },
                                {
                                    label: 'ปกติ',
                                    data: greengenend,
                                    backgroundColor: "rgba(0, 110, 22,0.7)",
                                    borderWidth: 0
                                },
                                {
                                    label: 'โปรสูง',
                                    data: orangegenend,
                                    backgroundColor: 'rgba(255,128,0,0.7)',
                                    borderWidth: 0
                                },
                                {
                                    label: 'โปรต่ำ',
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

