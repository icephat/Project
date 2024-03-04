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

               <?php include('../layout/dean/report.php'); ?>
                    <hr>
                    <div class="row ">
                        <div class="col-sm-4 mx-auto">
                            <a href="./student_static.php" class="card" style="border-radius: 100px;">
                                <div class="card-body text-center" style="background-color: lightgray; ">
                                    <a href="./student_static.php">
                                        <fieldset style="border:2px groove lightgray">
                                            <img src="../image/decreaseRate.png" style="width: 150px; height: 150px" ><br>
                                        </fieldset>
                                    </a>
                                </div>
                                <div class="card-footer text-center">
                                    <a  href="./student_static.php" style="color: black;  text-decoration:none; font-size: 18px;">อัตราการคงอยู่ของนิสิตในหลักสูตร</a>
                                </div>
                            </a>
                        </div>
                        <div class="col-sm-4 mx-auto">
                            <div class="card" style="border-radius: 100px;">
                                <div class="card-body text-center" style="background-color: lightgray; ">
                                    <a  href="./student_static_faculty.php">
                                        <fieldset style="border:2px groove lightgray">
                                            <img src="../image/gpa.png" style="width: 150px; height: 150px"><br>
                                        </fieldset>
                                    </a>
                                </div>
                                <div class="card-footer text-center">
                                    <a
                                        style="color: black;  text-decoration:none; font-size: 18px;">ผลการเรียนนิสิตในหลักสูตร</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4 mx-auto">
                            <div class="card" style="border-radius: 100px;">
                                <div class="card-body text-center" style="background-color: lightgray; ">
                                    <a  href="./student_static_tcas.php">
                                        <fieldset style="border:2px groove lightgray">
                                            <img src="../image/school.png" style="width: 150px; height: 150px"><br>
                                        </fieldset>
                                    </a>
                                </div>
                                <div class="card-footer text-center">
                                    <a
                                        style="color: black;  text-decoration:none; font-size: 18px;">การเข้าศึกษาของนิสิตในหลักสูตร</a>
                                </div>
                            </div>
                        </div>
                        <br><br>
                        <div class="col-sm-4 mx-auto">
                            <a href="./student_static.php" class="card" style="border-radius: 100px;">
                                <div class="card-body text-center" style="background-color: lightgray; ">
                                    <a href="./student_department.php">
                                        <fieldset style="border:2px groove lightgray">
                                            <img src="../image/decreaseRate.png" style="width: 150px; height: 150px" ><br>
                                        </fieldset>
                                    </a>
                                </div>
                                <div class="card-footer text-center">
                                    <a  href="./student_department.php" style="color: black;  text-decoration:none; font-size: 18px;">อัตราการคงอยู่ของนิสิตในภาควิชา</a>
                                </div>
                            </a>
                        </div>
                        <div class="col-sm-4 mx-auto">
                            <div class="card" style="border-radius: 100px;">
                                <div class="card-body text-center" style="background-color: lightgray; ">
                                    <a  href="./student_faculty.php">
                                        <fieldset style="border:2px groove lightgray">
                                            <img src="../image/gpa.png" style="width: 150px; height: 150px"><br>
                                        </fieldset>
                                    </a>
                                </div>
                                <div class="card-footer text-center">
                                    <a href="./student_faculty.php" 
                                        style="color: black;  text-decoration:none; font-size: 18px;">ผลการเรียนนิสิตในภาควิชา</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4 mx-auto">
                            <div class="card" style="border-radius: 100px;">
                                <div class="card-body text-center" style="background-color: lightgray; ">
                                    <a  href="./student_tcas.php">
                                        <fieldset style="border:2px groove lightgray">
                                            <img src="../image/school.png" style="width: 150px; height: 150px"><br>
                                        </fieldset>
                                    </a>
                                </div>
                                <div class="card-footer text-center">
                                    <a href="./student_tcas.php"
                                        style="color: black;  text-decoration:none; font-size: 18px;">การเข้าศึกษาของนิสิตในภาควิชา</a>
                                </div>
                            </div>
                        </div>
                        <br><br>
                        <div class="col-sm-4 " style="margin-top: 20px;">
                            <div class="card" style="border-radius: 100px;">
                                <div class="card-body text-center" style="background-color: lightgray; ">
                                    <a href="./report_advisor.php">
                                        <fieldset style="border:2px groove lightgray">
                                            <img src="../image/decreaseRate.png" style="width: 150px; height: 150px"><br>
                                        </fieldset>
                                    </a>
                                </div>
                                <div class="card-footer text-center">
                                    <a
                                        style="color: black;  text-decoration:none; font-size: 18px;">ผลการเรียนนิสิตแยกตามอาจารย์ที่ปรึกษา</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4 " style="margin-top: 20px;">
                            <div class="card" style="border-radius: 100px;">
                                <div class="card-body text-center" style="background-color: lightgray; ">
                                    <a href="./report_student_department.php">
                                        <fieldset style="border:2px groove lightgray">
                                            <img src="../image/gpa.png" style="width: 150px; height: 150px"><br>
                                        </fieldset>
                                    </a>
                                </div>
                                <div class="card-footer text-center">
                                    <a
                                        style="color: black;  text-decoration:none; font-size: 18px;">ผลการเรียนนิสิตแยกตามภาควิชา</a>
                                </div>
                            </div>
                        </div>
                        <div class="col-sm-4 mx-auto" style="margin-top: 20px;">
                            <div class="card" style="border-radius: 100px;">
                                <div class="card-body text-center" style="background-color: lightgray; ">
                                    <a  href="./student_static_department.php">
                                        <fieldset style="border:2px groove lightgray">
                                            <img src="../image/school.png" style="width: 150px; height: 150px"><br>
                                        </fieldset>
                                    </a>
                                </div>
                                <div class="card-footer text-center">
                                    <a
                                        style="color: black;  text-decoration:none; font-size: 18px;">การเข้าศึกษาของนิสิตแยกตามภาควิชา</a>
                                </div>
                            </div>
                        </div>
                    </div>




                    <!-- Content Row -------------------------------------------------------BOX----------------------->
                    <!--script-->
                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js">
                    </script>
                    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.0.1/dist/chart.umd.min.js">
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
                    <!-- Bootstrap core JavaScript-->
                    <script src="../vendor/jquery/jquery.min.js"></script>
                    <script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>



</body>

</html>