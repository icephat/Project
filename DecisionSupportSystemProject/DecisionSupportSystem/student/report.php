<!DOCTYPE html>
<html lang="en">

<?php
session_start();

require '../function/studentFunction.php';
//require '../function/semesterFunction.php';

$student = getStudentByUsername($_SESSION["access-user"]);
//$semester = getSemesterPresent();




?>

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
    <!-- Custom styles for this template -->
    <link href="../css/sb-admin-2.min.css" rel="stylesheet">

    <link href="../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <?php include('../layout/student/gpa.php'); ?>

                <hr>


                <?php include('./infoStudent.php') ?>
                <br>

                <br><br>
                <div style="justify-content: center; align-items: center;">
                    <div class="col-sm-12 mx-auto">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">ผลการเรียนวิชาที่ไม่ผ่านตามแผน</h6>
                            </div>

                            <div class="card-body ">
                                <div class="table-responsive">
                                    <table class="table table-striped" cellspacing="0" style="color: black;  ">
                                        <thead style="background-color: #86d3f7;">
                                            <tr>
                                                <th style=" text-align: center;">ปีการศึกษา</th>
                                                <th style=" text-align: center;">ภาคการเรียน</th>
                                                <th style=" text-align: center;">หมวดวิชา</th>
                                                <th style=" text-align: center;">รหัสวิชา</th>
                                                <th style="text-align: left;">ชื่อรายวิชา<span>ยังไม่ผ่าน</span>
                                                </th>
                                                <th style="text-align: center;">หน่วยกิต</th>
                                                <th style="text-align: center;">สถานะ</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <?php

                                            $subjectFs = getListSubjectForFAndWByStudentId($student["studentId"]);
                                            $sumcreditF = 0;

                                            foreach ($subjectFs as $subjectF) {
                                                $sumcreditF += $subjectF["credit"];
                                                echo "
                                                    
                                                    
                                                    <tr>
                                                        <td style=\" text-align: center;\">" . $subjectF["semesterYear"] . "</td>
                                                        <td style=\" text-align: center;\">" . $subjectF["semesterPart"] . "</td>
                                                        <td style=\" text-align: center;\">" . $subjectF["subjectGroup"] . "</td>
                                                        <td style=\" text-align: center;\">" . $subjectF["subjectCode"] . "</td>
                                                        <td style=\" text-align: left;\">
                                                        " . $subjectF["nameSubjectThai"] . "
                                                        </td>
                                                        <td style=\" text-align: center;\">" . $subjectF["credit"] . "</td>
                                                        <td style=\" text-align: center;\">" . $subjectF["gradeCharacter"] . "</td>

                                                    </tr>
                                                    
                                                    
                                                    
                                                    
                                                    ";
                                            }

                                            ?>
                                            <?php
                                            
                                            
                                            $courseNotLearns = getSubjectNotLearnInCoureseList($student["studentId"],$student["course"]["nameCourseUse"],$student["course"]["planCourse"],$student["studyYear"],$student["studyTerm"]);
                                            

                                            foreach($courseNotLearns as $courseNotLearn){
                                                $sumcreditF += $courseNotLearn["credit"];
                                            ?>
                                            <tr>
                                                    <td style=" text-align: center;"></td>
                                                    <td style=" text-align: center;"></td>
                                                    <td style=" text-align: center;"><?php echo $courseNotLearn["subjectGroup"]?></td>
                                                    <td style=" text-align: center;"><?php echo $courseNotLearn["subjectCode"]?></td>
                                                    <td style=" text-align: left;">
                                                        
                                                    </td>
                                                    <td style=" text-align: center;"><?php echo $courseNotLearn["credit"]?></td>
                                                    <td style=" text-align: center;"></td>

                                                </tr>
                                            <?php
                                            }
                                            ?>

                                            

                                                <!-- <tr>
                                                    <td style=" text-align: center;">2</td>
                                                    <td style=" text-align: center;">ภาคปลาย</td>
                                                    <td style=" text-align: center;">หมวดวิชาเฉพาะบังคับ</td>
                                                    <td style=" text-align: center;">01417168</td>
                                                    <td style=" text-align: left;">
                                                        Engineering Mathematics II
                                                    </td>
                                                    <td style="text-align: center;">
                                                        3
                                                    </td>
                                                    <td style=" text-align: center;">W</td>

                                                </tr> -->

                                            <tr>
                                                <td style="background-color: #86d3f7; font-weight: bold; color: black; text-align: center;"
                                                    colspan="4">
                                                    รวม</td>
                                                <td style=" text-align: center;">
                                                    <?php 
                                                    $s = count($subjectFs) + count($courseNotLearns);
                                                    echo $s ?>
                                                </td>
                                                <td style="text-align: center;">
                                                    <?php echo $sumcreditF ?>
                                                </td>
                                                <td style=" text-align: center;"></td>

                                            </tr>

                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>
                    </div>
                    <br><br>
                    <div class="col-sm-12 mx-auto">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">ผลการเรียนรายวิชาตกค้างที่ผ่านแล้ว
                                </h6>
                            </div>
                            <div class="card-body ">
                                <div class="table-responsive">
                                    <table class="table table-striped" cellspacing="0" style="color: black;  ">
                                        <thead style="background-color: #86d3f7;">
                                            <tr>
                                                <th style=" text-align: center;">ปีการศึกษา</th>
                                                <th style=" text-align: center;">ภาคการเรียน</th>
                                                <th style=" text-align: center;">หมวดวิชา</th>
                                                <th style=" text-align: center;">รหัสวิชา</th>
                                                <th style="text-align: left;">ชื่อรายวิชา<span>ผ่านแล้ว </span></th>
                                                <th style="text-align: center;">หน่วยกิต</th>
                                                <th style="text-align: center;">สถานะ</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <?php
                                            $subjectPassAndNots = getRegisPassAndNotPassByStudentId($student["studentId"]);

                                            $sumcreditF = 0;
                                            foreach ($subjectPassAndNots as $subjectPassAndNot) {
                                                $sumcreditF += $subjectF["credit"];

                                                ?>

                                                <tr>
                                                    <td style=" text-align: center;">
                                                        <?php echo $subjectPassAndNot["semesterYear"] ?>
                                                    </td>
                                                    <td style=" text-align: center;">
                                                        <?php echo $subjectPassAndNot["semesterPart"] ?>
                                                    </td>
                                                    <td style=" text-align: center;">
                                                        <?php echo $subjectPassAndNot["subjectGroup"] ?>
                                                    </td>
                                                    <td style=" text-align: center;">
                                                        <?php echo $subjectPassAndNot["subjectCode"] ?>
                                                    </td>
                                                    <td style=" text-align: left;">
                                                        <?php echo $subjectPassAndNot["nameSubjectThai"] ?>
                                                    </td>
                                                    <td style=" text-align: center;">
                                                        <?php echo $subjectPassAndNot["credit"] ?>
                                                    </td>
                                                    <td style=" text-align: center;">
                                                        <?php echo $subjectPassAndNot["gradeCharacter"] ?>
                                                    </td>

                                                </tr>

                                                <?php
                                            }

                                            ?>
                                            <tr>
                                                <td style="background-color: #86d3f7; font-weight: bold; color: black; text-align: center;"
                                                    colspan="4">
                                                    รวม</td>
                                                <td style=" text-align: center;">
                                                    <?php echo count($subjectPassAndNots) ?>
                                                </td>
                                                <td style="text-align: center;">
                                                    <?php echo $sumcreditF ?>
                                                </td>
                                                <td style=" text-align: center;"></td>

                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <br>

                    <div class="col-sm-12 mx-auto">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">วิชาเรียนที่เรียนเกินหลักสูตร
                                </h6>
                            </div>
                            <div class="card-body ">
                                <div class="table-responsive">
                                    <table class="table table-striped" cellspacing="0" style="color: black;  ">
                                        <thead style="background-color: #86d3f7;">
                                            <tr>
                                                <th style=" text-align: center;">ปีการศึกษา</th>
                                                <th style=" text-align: center;">ภาคการเรียน</th>
                                                <th style=" text-align: center;">หมวดวิชา</th>
                                                <th style=" text-align: center;">รหัสวิชา</th>
                                                <th style="text-align: left;">ชื่อรายวิชา</th>
                                                <th style="text-align: center;">หน่วยกิต</th>
                                                <th style="text-align: center;">สถานะ</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <?php
                                            $subjectGroups = getListSubjectGroupPassInRegisByStudentId($student["studentId"]);


                                            $overSubjects = $subjectGroups["over"]["list"];
                                            
                                            $sumOver = 0;

                                            foreach ($overSubjects as $over) {



                                                ?>

                                                <tr>
                                                    <td style=" text-align: center;">
                                                        <?php echo $over["semesterYear"] ?>
                                                    </td>
                                                    <td style=" text-align: center;">
                                                        <?php echo $over["semesterPart"] ?>
                                                    </td>
                                                    <td style=" text-align: center;">
                                                        <?php echo $over["subjectGroup"] ?>
                                                    </td>
                                                    <td style=" text-align: center;">
                                                        <?php echo $over["subjectCode"] ?>
                                                    </td>
                                                    <td style=" text-align: left;">
                                                        <?php echo $over["nameSubjectThai"] ?>
                                                    </td>
                                                    <td style=" text-align: center;">
                                                        <?php echo $over["credit"] ?>
                                                    </td>
                                                    <td style=" text-align: center;">
                                                        <?php echo $over["gradeCharacter"] ?>
                                                    </td>

                                                </tr>

                                                <?php
                                            }

                                            ?>
                                            <tr>
                                                <td style="background-color: #86d3f7; font-weight: bold; color: black; text-align: center;"
                                                    colspan="4">
                                                    รวม</td>
                                                <td style=" text-align: center;">
                                                    <?php echo count($overSubjects) ?>
                                                </td>
                                                <td style="text-align: center;">
                                                    <?php echo $sumOver ?>
                                                </td>
                                                <td style=" text-align: center;"></td>

                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>





                    <br><br>
                    <div>
                        <div class="col-sm-12">
                            <div class="card">

                                <ul class="nav nav-tabs" id="myTab" role="tablist">
                                    <?php

                                    $i = 1;


                                    $unlink = "nav-link active";
                                    $tab = "true";

                                    foreach ($subjectGroups as $gorup) {
                                        if ($gorup["name"] != "over") {
                                            echo "
                                            
                                        <li class=\"nav-item\">
                                            <a class=\"" . $unlink . "\" id=\"tab" . $i . "-tab\" data-toggle=\"tab\" href=\"#tab" .$i. "\" role=\"tab\" aria-controls=\"tab" . $i . "\" aria-selected=" . $tab . ">" . $gorup["name"] . "</a>
                                        </li>
                                            
                                            
                                            
                                            
                                            ";
                                            $tab = "false";
                                            $i++;
                                            $unlink = "nav-link";
                                        }

                                    }

                                    ?>

                                </ul>
                                <div class="tab-content">


                                    <?php

                                    $i = 1;
                                    $tabpane = "tab-pane fade show active";

                                    foreach ($subjectGroups as $gorup) {
                                        echo "                              
                                                    <div class=\"" . $tabpane . "\" id=\"tab" . $i . "\" role=\"tabpanel\" aria-labelledby=\"tab" . $i . "-tab\">
                                                    ";

                                        $tabpane = "tab-pane fade ";
                                        $i++;

                                        echo "
                                                

                                            <div class=\"table-responsive\">
                                                <table class=\"table table-striped\" id=\"dataTable".$i."\" cellspacing=\"0\"
                                                    style=\"color: black;  \">
                                                    <thead>
                                                        <tr>
                                                            <th>ปีการศึกษา</th>
                                                            <th>ภาคการศึกษา</th>
                                                            <th>รหัสวิชา</th>
                                                            <th>ชื่อวิชา</th>
                                                            <th>หมวดรายวิชา</th>

                                                            <th>ผลการเรียน</th>
                                                            <th>หน่วยกิต</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                
                                                ";

                                        foreach ($gorup["list"] as $regis) {
 
                                    
                                            echo "
                                                    
                                                    <tr>
                                                            <td class=\"text-left\">" . $regis["semesterYear"] . "</td>
                                                            <td class=\"text-left\">" . $regis["semesterPart"] . "</td>
                                                            <td class=\"text-left\">" . $regis["subjectCode"] . "</td>
                                                            <td class=\"text-left\">" . $regis["nameSubjectThai"] . "</td>
                                                            <td class=\"text-left\">" . $regis["subjectGroup"] . "</td>
                                                            <td class=\"text-left\">" . $regis["gradeCharacter"] . "</td>
                                                            <td class=\"text-left\">" . $regis["credit"] . "</td>
                                                        </tr>
                                                    
                                                    ";

                                        }

                                        echo "
                                                    </tbody>
                                                </table>
                                            </div>";



                                        echo "</div>";
                                    }


                                    ?>




                                </div>
                            </div>
                        </div>



                    </div>


                </div>






            </div>
            <!-- /.container-fluid --------------------------------------------------------------------------------------------->

        </div>
        <!-- End of Main Content -->


    </div>
    <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Logout Modal-->
    <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
        aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                    <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                <div class="modal-footer">
                    <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
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
    <script src="../js/demo/datatables-demo3.js"></script>

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


</body>

</html>