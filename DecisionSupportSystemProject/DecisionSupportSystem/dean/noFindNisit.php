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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>


    <!-- Custom styles for this template-->
    <link href="../css/sb-admin-2.min.css" rel="stylesheet">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <!-- Custom styles for this template -->
    <link href="../css/sb-admin-2.min.css" rel="stylesheet">

    <link href="../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">


</head>

<?php

session_start();

require_once '../function/teacherFunction.php';
require_once '../function/departmentFunction.php';



$teacher = getTeacherByUsernameTeacher($_SESSION["access-user"]);


$students = getStudentInAdviserBtTeacherId($teacher["teacherId"]);

$deptStudents = getStudentByDepartmentId($teacher["departmentId"]);



?>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <?php include('../layout/dean/nisit.php'); ?>
                <hr>
                <div class="row">
                    <div class="col-12 mx-auto">
                        <h4 style="color: green;">รายชื่อนิสิต คณะวิศวกรรมศาสตร์ กำแพงแสน</h4>
                        <div class="card">
                            <ul class="nav nav-tabs" id="myTabs" role="tablist">
                                <li class="nav-item" role="presentation">
                                    <a class="nav-link " id="tab1-tab" data-bs-toggle="tab" href="#tab1"
                                        role="tab" aria-controls="tab1" aria-selected="true">ในที่ปรึกษา</a>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <a class="nav-link" id="tab2-tab" data-bs-toggle="tab" href="#tab2" role="tab"
                                        aria-controls="tab2" aria-selected="false">ในภาควิชา</a>
                                </li>
                                <li class="nav-item" role="presentation">
                                    <a class="nav-link active" id="tab3-tab" data-bs-toggle="tab" href="#tab3" role="tab"
                                        aria-controls="tab3" aria-selected="false">ในคณะ</a>
                                </li>
                            </ul>

                            <div class="col12 tab-content" id="myTabsContent">
                                <div class="tab-pane fade" id="tab1" role="tabpanel"
                                    aria-labelledby="tab1-tab">
                                    <br>
                                    <div class="table-responsive">
                                        <table class="table table-striped" id="dataTable" cellspacing="0"
                                            style="color: black;  ">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">รหัสนิสิต</th>
                                                    <th>ชื่อ-นามสกุล</th>
                                                    <th>ประเภทหลักสูตร</th>
                                                    <th class="text-center">หน่วยกิตที่ลงทะเบียน<br>
                                                        (ทั้งหมด/ผ่าน/ไม่ผ่าน)
                                                    </th>
                                                    <th class="text-center">ผลการเรียน</th>
                                                    <th class="text-center">รายละเอียด</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <?php



                                                foreach ($students as $student) {



                                                    ?>
                                                    <tr>
                                                        <td>
                                                            <?php echo $student["studentId"] ?>
                                                        </td>
                                                        <td>
                                                            <?php echo $student["fisrtNameTh"] . " " . $student["lastNameTh"] ?>
                                                        </td>
                                                        <td >
                                                            <?php echo $student["course"]["nameCourseUse"] . " (" . $student["course"]["planCourse"] . ")" ?>
                                                        </td>

                                                        <td class="text-center"><span style='color:green;'>
                                                                <?php echo $student["course"]["totalCredit"] ?>
                                                            </span>
                                                            <span style='color:green;'>/
                                                                <?php echo $student["creditThree"]["creditPass"] ?>
                                                            </span>
                                                            <?php $notPass = $student["course"]["totalCredit"] - $student["creditThree"]["creditPass"] ?>
                                                            <span style='color:red;'>/
                                                                <?php echo $notPass ?>
                                                            </span>
                                                        </td>
                                                        <td class="text-center"><span style='color:green;'>
                                                                <?php echo round($student["gpax"], 2) ?>
                                                            </span> <br>
                                                            <span></span>
                                                        </td>

                                                        <td class="text-center">
                                                            <form action="./student_info.php" method="post">
                                                                <input type="hidden" name="studentId"
                                                                    value="<?php echo $student["studentId"]; ?>" />
                                                                <!--<a type="button" name="std_info">
                                <span class="glyphicon glyphicon-user"></span></a>-->
                                                                <button type="submit" name="submit"
                                                                    class="btn btn-info btn-md"><span
                                                                        class="glyphicon glyphicon-user"></span>
                                                                    รายละเอียด</button>
                                                            </form>
                                                        </td>
                                                    </tr>
                                                    <?php
                                                }
                                                ?>
                                            </tbody>

                                        </table>
                                    </div>
                                </div>

                                <div class="tab-pane fade" id="tab2" role="tabpanel" aria-labelledby="tab2-tab">
                                    <br>
                                    <div class="table-responsive">
                                        <table class="table table-striped" id="dataTable2" cellspacing="0"
                                            style="color: black;  ">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">รหัสนิสิต</th>
                                                    <th>ชื่อ-นามสกุล</th>
                                                    <th>ประเภทหลักสูตร</th>
                                                    <th>ที่ปรึกษา</th>

                                                    <th class="text-center">หน่วยกิตที่ลงทะเบียน<br>
                                                        (ทั้งหมด/ผ่าน/ไม่ผ่าน)</th>
                                                    <th class="text-center">ผลการเรียน</th>
                                                    <th class="text-center">รายละเอียด</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <?php



                                                foreach ($deptStudents as $student) {



                                                    ?>
                                                    <tr>
                                                        <td class="text-center">
                                                            <?php echo $student["studentId"] ?>
                                                        </td>
                                                        <td>
                                                            <?php echo $student["fisrtNameTh"] . " " . $student["lastNameTh"] ?>
                                                        </td>
                                                        <td >
                                                            <?php echo $student["course"]["nameCourseUse"] . " (" . $student["course"]["planCourse"] . ")" ?>
                                                        </td>

                                                        <td >
                                                            <?php echo $student["teacher"]["fisrtNameTh"] . " (" . $student["teacher"]["lastNameTh"] . ")" ?>
                                                        </td>

                                                        <td class="text-center"><span style='color:green;'>
                                                                <?php echo $student["course"]["totalCredit"] ?>
                                                            </span>
                                                            <span style='color:green;'>/
                                                                <?php echo $student["creditThree"]["creditPass"] ?>
                                                            </span>
                                                            <?php $notPass = $student["course"]["totalCredit"] - $student["creditThree"]["creditPass"] ?>
                                                            <span style='color:red;'>/
                                                                <?php echo $notPass ?>
                                                            </span>
                                                        </td>
                                                        <td class="text-center"><span style='color:green;'>
                                                                <?php echo round($student["gpax"], 2) ?>
                                                            </span> <br>
                                                            <span></span>
                                                        </td>

                                                        <td class="text-center">
                                                            <form action="./student_info.php" method="post">
                                                                <input type="hidden" name="studentId"
                                                                    value="<?php echo $student["studentId"]; ?>" />
                                                                <!--<a type="button" name="std_info">
<span class="glyphicon glyphicon-user"></span></a>-->
                                                                <button type="submit" name="submit"
                                                                    class="btn btn-info btn-md"><span
                                                                        class="glyphicon glyphicon-user"></span>
                                                                    รายละเอียด</button>
                                                            </form>
                                                        </td>
                                                    </tr>
                                                    <?php
                                                }
                                                ?>
                                            </tbody>

                                        </table>
                                    </div>
                                </div>
                                <div class="tab-pane fade show active" id="tab3" role="tabpanel" aria-labelledby="tab3-tab">
                                    <br>
                                    <div class="col-12 mx-auto">
                                        <form action="../controller/deanStudentSearch.php" method = "POST" >
                                            <div class=" text-center">
                                                <h5 style="margin-left: 20px;">โปรดระบุรหัสนิสิต</h3>
                                                    <br>
                                                    <input type="text" class="form-control" name = "studentId"  
                                                        placeholder="รหัสนิสิต" required>
                                                    <br>

                                                    <button class="btn btn-primary"
                                                        style="font-size: 20px;">Search</button>

                                            </div>
                                        </form>

                                        <br><br><br>
                                        <div class="text-center">
                                            <h5 style="color:red"> ไม่พบรหัสนิสิตที่ค้นหา</h5>
                                        </div>

                                    </div>
                                    <br>
                                </div>
                            </div>

                        </div>



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