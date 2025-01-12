<!DOCTYPE html>
<html lang="en">

<?php

session_start();

require '../function/studentFunction.php';


$student = getStudentByUsername($_SESSION["access-user"]);

$courses = getCoursePlanByCourseName($student["course"]["nameCourseUse"]);




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
                <form class="form-valide" action="../controller/updateTell.php" method="post"
                    enctype="multipart/form-data">

                    <?php include('../layout/student/info.php'); ?>
                    <hr>


                    <!-- Content Row -------------------------------------------------------BOX----------------------->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="col-sm-12 text-left">
                                <h5 style="color: green;">ข้อมูลส่วนตัว</h5>
                            </div>
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">รหัสประจำตัวนิสิต </p>
                                </div>
                                <div class="col-sm-6">
                                    <p style="color: gray;">
                                        <?php echo $student["studentId"] ?>
                                    </p>
                                </div>

                            </div>
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">ชื่อ-นามสกุล (ไทย) </p>
                                </div>
                                <div class="col-sm-6 ">
                                    <p style="color: gray;">
                                        <?php echo $student["fisrtNameTh"] . " ", $student["lastNameTh"] ?>
                                    </p>
                                </div>
                            </div>
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">ชื่อ-นามสกุล (อังกฤษ)</p>
                                </div>
                                <div class="col-sm-6 ">
                                    <p style="color: gray;">
                                        <?php echo $student["fisrtNameEng"] . " ", $student["lastNameEng"] ?>
                                    </p>
                                </div>
                            </div>
                            <!--<div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">รหัสประจำตัวประชาชน</p>
                                </div>
                                <div class="col-sm-6 ">
                                    <p style="color: gray;"><?php echo $student["personId"] ?></p>
                                </div>
                            </div>-->
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">เพศ</p>
                                </div>
                                <div class="col-sm-6 ">
                                    <p style="color: gray;">
                                        <?php echo $student["genderTh"] ?>
                                    </p>
                                </div>
                            </div>

                        </div>

                        <div class="col-sm-6 text-left">
                            <div class="col-sm-6 text-left">
                                <h5 style="color: green;">ช่องทางการติดต่อ</h5>
                            </div>
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">เบอร์โทรศัพท์</p>
                                </div>
                                <div class="col-sm-6 ">
                                    <input class='form-control' name="tell" value="<?php echo $student["tell"] ?> "
                                        required />
                                </div>
                            </div>
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">e-Mail</p>
                                </div>
                                <div class="col-sm-6 ">
                                    <p style="color: gray;">
                                        <?php echo $student["email"] ?>
                                    </p>
                                </div>
                            </div>
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">เบอร์โทรศัพท์ผู้ปกครอง</p>
                                </div>
                                <div class="col-sm-6 ">
                                    <input class='form-control' name="pTell"
                                        value="<?php echo $student["parentTell"] ?>" required />
                                </div>
                            </div>
                        </div>
                    </div>

                    <br>
                    <hr>

                    <!-- Content Row -------------------------------------------------------BOX----------------------->
                    <div class="row">
                        <div class="col-sm-6">
                            <div class="col-sm-12 text-left">
                                <h5 style="color: green;">การศึกษาปัจจุบัน</h5>
                            </div>
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">อาจารย์ที่ปรึกษา </p>
                                </div>
                                <div class="col-sm-6">
                                    <p style="color: gray;">
                                        <?php echo $student["teacher"]["titleTecherTh"] . $student["teacher"]["fisrtNameTh"] . " " . $student["teacher"]["lastNameTh"]; ?>
                                    </p>
                                </div>

                            </div>
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">วิทยาเขต </p>
                                </div>
                                <div class="col-sm-6 ">
                                    <p style="color: gray;">
                                        <?php echo $student["department"]["campus"] ?>
                                    </p>
                                </div>
                            </div>
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">คณะ</p>
                                </div>
                                <div class="col-sm-6 ">
                                    <p style="color: gray;">
                                        <?php echo $student["department"]["faculty"] ?>
                                    </p>
                                </div>
                            </div>
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">สาขาวิชา</p>
                                </div>
                                <div class="col-sm-6 ">
                                    <p style="color: gray;">
                                        <?php echo $student["department"]["departmentName"] ?>
                                    </p>
                                </div>
                            </div>
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">ประเภทหลักสูตร</p>
                                </div>
                                <div class="col-sm-6 ">
                                    <!-- <p style="color: gray;">
                                        <?php echo $student["course"]["nameCourseUse"] . " (" . $student["course"]["planCourse"] . ")" ?>
                                    </p> -->
                                    <select class="form-control" width="500px" data-live-search="true" name="courseId">

                                        <?php
                                        foreach ($courses as $course) {
                                            echo "<option value=" . $course["courseId"] ." "  ;
                                            if($student["course"]["planCourse"] == $course["planCourse"]){
                                                echo " selected = 'selected'";
                                            }
                                            echo ">" . $course["nameCourseUse"] . " (" . $course["planCourse"] . ") </option>";
                                        }

                                        ?>
                                    </select>

                                    <!--<p style="color: gray;"><?php echo $student["course"]["nameCourseUse"] ?><input class='form-control' name="pTell" value="<?php echo $student["course"]["planCourse"] ?>" /></p>-->

                                </div>
                            </div>
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">สถานภาพนิสิต</p>
                                </div>
                                <div class="col-sm-6 ">
                                    <p style="color: gray;">
                                        <?php echo $student["status"] ?>
                                    </p>
                                </div>
                            </div>
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">เกรดเฉลี่ยสะสม</p>
                                </div>
                                <div class="col-sm-6 ">
                                    <p style="color: gray;">
                                        <?php echo round($student["gpax"], 2) ?>
                                    </p>
                                </div>
                            </div>

                        </div>

                        <div class="col-sm-6 text-left">
                            <div class="col-sm-6 text-left">
                                <h5 style="color: green;">การศึกษาระดับมัธยม</h5>
                            </div>
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">ชื่อโรงเรียน</p>
                                </div>
                                <div class="col-sm-6 ">
                                    <p style="color: gray;">
                                        <?php echo $student["school"]["schoolName"] ?>
                                    </p>
                                </div>
                            </div>
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: black;">ที่อยู่โรงเรียน</p>
                                </div>
                                <div class="col-sm-6 ">
                                    <p style="color: gray;">จังหวัด
                                        <?php echo $student["school"]["provinceName"] ?>
                                    </p>
                                </div>
                            </div>

                        </div>
                    </div>



                    <br><br>
                    <div style="text-align: center;">
                        <a type="button" class="btn btn-danger" href="./info.php" style="color: black;">ยกเลิก</a>
                        <button type="summit" class="btn btn-success" style="color: black;">บันทึก</button>
                        <!-- <a type="button" class="btn btn-success" href="./info.php" style="color: black;">บันทึก</a> -->
                    </div>
                    <br><br><br>
                </form>

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
    <script>
        const GPA2563 = [2.13, 3.34, 2.63, 3.33];
        const GPAcolorbar = [];
        let GPAsize = GPA2563.length;
        let GPAcolorLoop;

        for (let i = 0; i < GPAsize; i++) {
            if (GPA2563[i] >= 0.0000 && GPA2563[i] <= 1.7499) {
                GPAcolorLoop = '#ff6962';
            }
            else if (GPA2563[i] >= 1.7500 && GPA2563[i] <= 1.9999) {
                GPAcolorLoop = '#f57b39';
            }
            else if (GPA2563[i] >= 2.0000 && GPA2563[i] <= 3.2499) {
                GPAcolorLoop = '#99cc99';
            }
            else if (GPA2563[i] >= 3.2500) {
                GPAcolorLoop = '#86d3f7';
            }
            GPAcolorbar[i] = GPAcolorLoop;
        }
        console.log(GPAcolorbar);


        var ctx = document.getElementById("myChart");

        var myChart = new Chart(ctx, {
            //type: 'bar',
            //type: 'line',
            type: 'bar',
            data: {
                labels: ['ภาคต้น 2563', 'ภาคปลาย 2563', 'ภาคต้น 2564', 'ภาคปลาย 2564'],
                datasets: [{
                    type: 'line',
                    label: ['ภาคต้น 2563', 'ภาคปลาย 2563', 'ภาคต้น 2564', 'ภาคปลาย 2564'],
                    data: [2.13, 2.63, 2.62, 2.78],
                    borderColor: 'rgb(0, 107, 201)',
                    lineTension: 0,
                    fill: false
                },
                {
                    data: GPA2563,
                    backgroundColor: GPAcolorbar,
                    borderWidth: 0
                },

                ]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true,
                            max: 4,
                            min: 0
                        }
                    }]
                },
                legend: {
                    display: false
                },
                responsive: true,

            }
        });
    </script>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.js"></script>
    <script>
        var ctx = document.getElementById("myChartSub");
        const GPASub = [3.13, 3.23, 3.33, 3.38, 3.40];
        const GPASubcolorbar = [];
        let GPASubsize = GPASub.length;
        let GPASubcolorLoop;
        for (let i = 0; i < GPASubsize; i++) {
            if (GPASub[i] >= 0.0000 && GPASub[i] <= 1.7499) {
                GPASubcolorLoop = 'rgba(255, 105, 98,0.8)';
            }
            else if (GPASub[i] >= 1.7500 && GPASub[i] <= 1.9999) {
                GPASubcolorLoop = 'rgba(245, 123, 57,0.8)';
            }
            else if (GPASub[i] >= 2.0000 && GPASub[i] <= 3.2499) {
                GPASubcolorLoop = 'rgba(153, 204, 153,0.8)';
            }
            else if (GPASub[i] >= 3.2500) {
                GPASubcolorLoop = 'rgba(134, 188, 247,0.8)';
            }
            GPASubcolorbar[i] = GPASubcolorLoop;
        }
        var myChart = new Chart(ctx, {
            //type: 'bar',
            //type: 'line',

            type: 'bar',
            data: {
                labels: ['หมวดวิชาแกน', 'หมวดวิชาศึกษาทั่วไป', 'หมวดวิชาเฉพาะบังคับ', 'หมวดวิชาเฉพาะเลือก', 'หมวดวิชาเสรี'],
                datasets: [{
                    data: GPASub,
                    backgroundColor: GPASubcolorbar,
                    borderWidth: 0,

                }]
            },
            options: {
                scales: {
                    yAxes: [{
                        ticks: {
                            beginAtZero: true,
                            max: 4,
                            min: 0
                        }
                    }]
                },
                legend: {
                    display: false
                },
                responsive: true,

            }


        });
    </script>

    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <script>
        const GPAC = 3.13;
        let GPACcolorbar = '';
        let GPACcolor = 'rgba(211,211,211,0.8)';
        if (GPAC >= 0.0000 && GPAC <= 1.7499) {
            GPACcolorbar = 'rgba(255, 105, 98,0.8)';
        }
        else if (GPAC >= 1.7500 && GPAC <= 1.9999) {
            GPACcolorbar = 'rgba(245, 123, 57,0.8)';
        }
        else if (GPAC >= 2.0000 && GPAC <= 3.2499) {
            GPACcolorbar = 'rgba(153, 204, 153,0.8)';

        }
        else if (GPAC >= 3.2500) {
            GPACcolorbar = 'rgba(134, 188, 247,0.8)';
        }

        // ข้อมูลสำหรับ Donut Chart
        var data = {
            datasets: [{
                data: [100, 100 - 100],
                backgroundColor: [GPACcolorbar, GPACcolor]
            }]
        };
        var data2 = {
            datasets: [{
                data: [80, 100 - 80],
                backgroundColor: [GPACcolorbar, GPACcolor]
            }]
        };
        var data3 = {
            datasets: [{
                data: [90, 100 - 90],
                backgroundColor: [GPACcolorbar, GPACcolor]
            }]
        };
        var data4 = {
            datasets: [{
                data: [90, 100 - 90],
                backgroundColor: [GPACcolorbar, GPACcolor]
            }]
        };
        var data5 = {
            datasets: [{
                data: [100, 100 - 100],
                backgroundColor: [GPACcolorbar, GPACcolor]
            }]
        };

        // สร้าง Donut Chart
        var ctx = document.getElementById("donutChart");
        var donutChart = new Chart(ctx, {
            type: "doughnut",
            data: data,
            options: {
                cutoutPercentage: 70,  // กำหนดค่านี้เพื่อสร้าง Donut Chart
                responsive: true,
                plugins: {
                    legend: {
                        display: false
                    }
                }
            }
        });
        var ctx = document.getElementById("donutChart2");
        var donutChart2 = new Chart(ctx, {
            type: "doughnut",
            data: data2,
            options: {
                cutoutPercentage: 70,  // กำหนดค่านี้เพื่อสร้าง Donut Chart
                responsive: true,
                plugins: {
                    legend: {
                        display: false
                    }
                }
            }
        });
        var ctx = document.getElementById("donutChart3");
        var donutChart3 = new Chart(ctx, {
            type: "doughnut",
            data: data3,
            options: {
                cutoutPercentage: 70,  // กำหนดค่านี้เพื่อสร้าง Donut Chart
                responsive: true,
                plugins: {
                    legend: {
                        display: false
                    }
                }
            }
        });
        var ctx = document.getElementById("donutChart4");
        var donutChart4 = new Chart(ctx, {
            type: "doughnut",
            data: data4,
            options: {
                cutoutPercentage: 70,  // กำหนดค่านี้เพื่อสร้าง Donut Chart
                responsive: true,
                plugins: {
                    legend: {
                        display: false
                    }
                }
            }
        });
        var ctx = document.getElementById("donutChart5");
        var donutChart5 = new Chart(ctx, {
            type: "doughnut",
            data: data5,
            options: {
                cutoutPercentage: 70,  // กำหนดค่านี้เพื่อสร้าง Donut Chart
                responsive: true,
                plugins: {
                    legend: {
                        display: false
                    }
                }
            }
        });

    </script>

</body>

</html>

<div id="dataModal" class="modal fade">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header" style="height: 90px;">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <table class="modal-dialog modal-lg" style="border:none; width: 85%;">
                    <th style=" text-align: left; ">
                        <h4 style="font-weight: bold;">เกรด 3.23</h4>
                    </th>
                    <th style=" text-align: right;">
                        <h4 style="font-weight: bold;">GPA 3.45</h4>
                    </th>
                </table>


            </div>
            <h4 class="modal-title" style="margin-left: 10px;">ผลการเรียนของนิสิตในปีการศึกษา 2563 ภาคต้น</h4>
            <div class="modal-body" id="std_detail">
                <table class="table">

                    <thead>
                        <tr>
                            <th>รายชื่อวิชา</th>
                            <th>เกรดที่ได้</th>
                            <th>จำนวนหน่วยกิต</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <th>General Physics I</th>
                            <th>B</th>
                            <th>3</th>
                        </tr>
                        <tr>
                            <th>Math I</th>
                            <th>B</th>
                            <th>3</th>
                        </tr>
                    </tbody>
                </table>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"
                    style="font-size: 18px;">ปิดหน้าต่าง</button>
            </div>
        </div>
    </div>
</div>