<!DOCTYPE html>
<html lang="en">

<?php

session_start();

require_once '../function/studentFunction.php';
require_once '../function/subjectFunction.php';

$student = getStudentByUsername($_SESSION["access-user"]);
$subjects = getAllSubject();




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


</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <?php include('../layout/student/calgpa.php'); ?>

                <hr>


                <!-- Content Row -------------------------------------------------------BOX----------------------->
                <div class="row">
                    <div class="col-sm-6 text-left">
                        <h4 style="color: black;">เกรดเฉลี่ยสะสม:
                            <?php echo round($student["gpax"], 2) ?> หน่วยกิต: <?php echo $student["credit"]?>
                        </h4>
                    </div>
                    <div class="col-sm-6 text-right">
                        <a href="./calGPAHis.php" type="button" class="btn btn-primary">ดูประวัติการคาดการณ์</a>
                    </div>
                </div>
                <hr>
                <form class="form-valide" action="../controller/calGPAController.php" method="post" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-sm-6 mx-auto">
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="text-center">
                                        <h5 style="color: black;">ชื่อวิชา<span style="color: red;">*</span></h5>
                                    </div>
                                </div>
                                <div class="col-sm-6">

                                    <div class="text-center">
                                        <h5 style="color: black;">เกรด<span style="color: red;">*</span></h5>

                                    </div>

                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="subject1">
                                            <option value="default">--กรุณาเลือกวิชา--</option>
                                            <option value="1credit">อื่นๆ [สำหรับวิชา 1 หน่วยกิต]</option>
                                            <option value="2credit">อื่นๆ [สำหรับวิชา 2 หน่วยกิต]</option>
                                            <option value="3credit">อื่นๆ [สำหรับวิชา 3 หน่วยกิต]</option>
                                            <?php
                                            foreach ($subjects as $subject) {
                                                echo "<option value=" . $subject["subjectId"] . ">" . $subject["subjectCode"] . " " . $subject["nameSubjectThai"] . " [" . $subject["credit"] . " หน่วยกิต]</option>";
                                            }

                                            ?>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">

                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="grade1">
                                            <option value="">-</option>
                                            <option value="4">A</option>
                                            <option value="3.5">B+</option>
                                            <option value="3">B</option>
                                            <option value="2.5">C+</option>
                                            <option value="2">C</option>
                                            <option value="1.5">D+</option>
                                            <option value="1">D</option>
                                            <option value="0">F</option>
                                        </select>

                                    </div>

                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="subject2">
                                            <option value="default">--กรุณาเลือกวิชา--</option>
                                            <option value="1credit">อื่นๆ [สำหรับวิชา 1 หน่วยกิต]</option>
                                            <option value="2credit">อื่นๆ [สำหรับวิชา 2 หน่วยกิต]</option>
                                            <option value="3credit">อื่นๆ [สำหรับวิชา 3 หน่วยกิต]</option>
                                            <?php
                                            foreach ($subjects as $subject) {
                                                echo "<option value=" . $subject["subjectId"] . ">" . $subject["subjectCode"] . " " . $subject["nameSubjectThai"] . " [" . $subject["credit"] . " หน่วยกิต]</option>";
                                            }

                                            ?>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">

                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="grade2">
                                            <option value="">-</option>
                                            <option value="4">A</option>
                                            <option value="3.5">B+</option>
                                            <option value="3">B</option>
                                            <option value="2.5">C+</option>
                                            <option value="2">C</option>
                                            <option value="1.5">D+</option>
                                            <option value="1">D</option>
                                            <option value="0">F</option>
                                        </select>

                                    </div>

                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="subject3">
                                            <option value="default">--กรุณาเลือกวิชา--</option>
                                            <option value="1credit">อื่นๆ [สำหรับวิชา 1 หน่วยกิต]</option>
                                            <option value="2credit">อื่นๆ [สำหรับวิชา 2 หน่วยกิต]</option>
                                            <option value="3credit">อื่นๆ [สำหรับวิชา 3 หน่วยกิต]</option>
                                            <?php
                                            foreach ($subjects as $subject) {
                                                echo "<option value=" . $subject["subjectId"] . ">" . $subject["subjectCode"] . " " . $subject["nameSubjectThai"] . " [" . $subject["credit"] . " หน่วยกิต]</option>";
                                            }

                                            ?>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">

                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="grade3">
                                            <option value="">-</option>
                                            <option value="4">A</option>
                                            <option value="3.5">B+</option>
                                            <option value="3">B</option>
                                            <option value="2.5">C+</option>
                                            <option value="2">C</option>
                                            <option value="1.5">D+</option>
                                            <option value="1">D</option>
                                            <option value="0">F</option>
                                        </select>

                                    </div>

                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="subject4">
                                            <option value="default">--กรุณาเลือกวิชา--</option>
                                            <option value="1credit">อื่นๆ [สำหรับวิชา 1 หน่วยกิต]</option>
                                            <option value="2credit">อื่นๆ [สำหรับวิชา 2 หน่วยกิต]</option>
                                            <option value="3credit">อื่นๆ [สำหรับวิชา 3 หน่วยกิต]</option>
                                            <?php
                                            foreach ($subjects as $subject) {
                                                echo "<option value=" . $subject["subjectId"] . ">" . $subject["subjectCode"] . " " . $subject["nameSubjectThai"] . " [" . $subject["credit"] . " หน่วยกิต]</option>";
                                            }

                                            ?>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">

                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="grade4">
                                            <option value="">-</option>
                                            <option value="4">A</option>
                                            <option value="3.5">B+</option>
                                            <option value="3">B</option>
                                            <option value="2.5">C+</option>
                                            <option value="2">C</option>
                                            <option value="1.5">D+</option>
                                            <option value="1">D</option>
                                            <option value="0">F</option>
                                        </select>

                                    </div>

                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="subject5">
                                            <option value="default">--กรุณาเลือกวิชา--</option>
                                            <option value="1credit">อื่นๆ [สำหรับวิชา 1 หน่วยกิต]</option>
                                            <option value="2credit">อื่นๆ [สำหรับวิชา 2 หน่วยกิต]</option>
                                            <option value="3credit">อื่นๆ [สำหรับวิชา 3 หน่วยกิต]</option>
                                            <?php
                                            foreach ($subjects as $subject) {
                                                echo "<option value=" . $subject["subjectId"] . ">" . $subject["subjectCode"] . " " . $subject["nameSubjectThai"] . " [" . $subject["credit"] . " หน่วยกิต]</option>";
                                            }

                                            ?>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">

                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="grade5">
                                            <option value="">-</option>
                                            <option value="4">A</option>
                                            <option value="3.5">B+</option>
                                            <option value="3">B</option>
                                            <option value="2.5">C+</option>
                                            <option value="2">C</option>
                                            <option value="1.5">D+</option>
                                            <option value="1">D</option>
                                            <option value="0">F</option>
                                        </select>

                                    </div>

                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="subject6">
                                            <option value="default">--กรุณาเลือกวิชา--</option>
                                            <option value="1credit">อื่นๆ [สำหรับวิชา 1 หน่วยกิต]</option>
                                            <option value="2credit">อื่นๆ [สำหรับวิชา 2 หน่วยกิต]</option>
                                            <option value="3credit">อื่นๆ [สำหรับวิชา 3 หน่วยกิต]</option>
                                            <?php
                                            foreach ($subjects as $subject) {
                                                echo "<option value=" . $subject["subjectId"] . ">" . $subject["subjectCode"] . " " . $subject["nameSubjectThai"] . " [" . $subject["credit"] . " หน่วยกิต]</option>";
                                            }

                                            ?>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">

                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="grade6">
                                            <option value="">-</option>
                                            <option value="4">A</option>
                                            <option value="3.5">B+</option>
                                            <option value="3">B</option>
                                            <option value="2.5">C+</option>
                                            <option value="2">C</option>
                                            <option value="1.5">D+</option>
                                            <option value="1">D</option>
                                            <option value="0">F</option>
                                        </select>

                                    </div>

                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="subject7">
                                            <option value="default">--กรุณาเลือกวิชา--</option>
                                            <option value="1credit">อื่นๆ [สำหรับวิชา 1 หน่วยกิต]</option>
                                            <option value="2credit">อื่นๆ [สำหรับวิชา 2 หน่วยกิต]</option>
                                            <option value="3credit">อื่นๆ [สำหรับวิชา 3 หน่วยกิต]</option>
                                            <?php
                                            foreach ($subjects as $subject) {
                                                echo "<option value=" . $subject["subjectId"] . ">" . $subject["subjectCode"] . " " . $subject["nameSubjectThai"] . " [" . $subject["credit"] . " หน่วยกิต]</option>";
                                            }

                                            ?>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">

                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="grade7">
                                            <option value="">-</option>
                                            <option value="4">A</option>
                                            <option value="3.5">B+</option>
                                            <option value="3">B</option>
                                            <option value="2.5">C+</option>
                                            <option value="2">C</option>
                                            <option value="1.5">D+</option>
                                            <option value="1">D</option>
                                            <option value="0">F</option>
                                        </select>

                                    </div>

                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="subject8">
                                            <option value="default">--กรุณาเลือกวิชา--</option>
                                            <option value="1credit">อื่นๆ [สำหรับวิชา 1 หน่วยกิต]</option>
                                            <option value="2credit">อื่นๆ [สำหรับวิชา 2 หน่วยกิต]</option>
                                            <option value="3credit">อื่นๆ [สำหรับวิชา 3 หน่วยกิต]</option>
                                            <?php
                                            foreach ($subjects as $subject) {
                                                echo "<option value=" . $subject["subjectId"] . ">" . $subject["subjectCode"] . " " . $subject["nameSubjectThai"] . " [" . $subject["credit"] . " หน่วยกิต]</option>";
                                            }

                                            ?>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">

                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="grade8">
                                            <option value="">-</option>
                                            <option value="4">A</option>
                                            <option value="3.5">B+</option>
                                            <option value="3">B</option>
                                            <option value="2.5">C+</option>
                                            <option value="2">C</option>
                                            <option value="1.5">D+</option>
                                            <option value="1">D</option>
                                            <option value="0">F</option>
                                        </select>

                                    </div>

                                </div>
                            </div>
                            <br>
                            <div class="row">
                                <div class="col-sm-6">
                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="subject9">
                                            <option value="default">--กรุณาเลือกวิชา--</option>
                                            <option value="1credit">อื่นๆ [สำหรับวิชา 1 หน่วยกิต]</option>
                                            <option value="2credit">อื่นๆ [สำหรับวิชา 2 หน่วยกิต]</option>
                                            <option value="3credit">อื่นๆ [สำหรับวิชา 3 หน่วยกิต]</option>
                                            <?php
                                            foreach ($subjects as $subject) {
                                                echo "<option value=" . $subject["subjectId"] . ">" . $subject["subjectCode"] . " " . $subject["nameSubjectThai"] . " [" . $subject["credit"] . " หน่วยกิต]</option>";
                                            }

                                            ?>
                                        </select>
                                    </div>
                                </div>
                                <div class="col-sm-6">

                                    <div class="text-center">
                                        <select class="form-control" width="500px" data-live-search="true"
                                            name="grade9">
                                            <option value="">-</option>
                                            <option value="4">A</option>
                                            <option value="3.5">B+</option>
                                            <option value="3">B</option>
                                            <option value="2.5">C+</option>
                                            <option value="2">C</option>
                                            <option value="1.5">D+</option>
                                            <option value="1">D</option>
                                            <option value="0">F</option>
                                        </select>

                                    </div>

                                </div>
                            </div>
                            <br><br>
                            <div class="col-sm-6 mx-auto" style="text-align: center;">
                                <button type="summit" class="btn btn-success"
                                    style="color: black;">ดูผลการคำนวณ</button>
                            </div>
                            <br><br>

                </form>


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

    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>



    <!-- Page level plugins -->

</body>

</html>