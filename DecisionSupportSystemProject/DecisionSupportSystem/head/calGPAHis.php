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

                <?php include('../layout/head/nisit.php'); ?>

                    <hr>


                    <!-- Content Row -------------------------------------------------------BOX----------------------->
                    <div class="row">
                        <div class="col-6 text-left">
                            <h4 style="color: black;">เกรดเฉลี่ยสะสม: 3.38 หน่วยกิต: 132</h4>
                        </div>
                        
                    </div>
                    <hr>

                    <div class="row">
                        <div class="col-12 mx-auto">
                            <div class="card">
                                <div class="table-responsive">
                                    <table class="table table-striped" cellspacing="0" style="color: black;">
                                        <thead>
                                            <tr>
                                                <th>
                                                    <p>ผลการเรียนเทอมปัจจุบัน</p>
                                                </th>
                                                <th>
                                                    <p>ผลการเรียนของเทอมที่คำนวณ</p>
                                                </th>
                                                <th>
                                                    <p>ผลการเรียนที่คาดว่าจะได้</p>
                                                </th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <td>
                                                    <p style="color: black; font-weight: bold;">GPAX : <span
                                                            style="font-weight: normal;">3.38</span></p>

                                                </td>
                                                <td>
                                                    <p style="color: black; font-weight: bold;">GPA : <span
                                                            style="font-weight: normal;"> 3.40</span></p>

                                                </td>
                                                <td>
                                                    <p style="color: black; font-weight: bold;">GPAX : <span
                                                            style="font-weight: normal;"> 3.39 <span
                                                                style="color: green;">[+0.01]</span></span></p>

                                                </td>
                                            </tr>
                                            <tr>
                                                <td>
                                                    <p style="color: black; font-weight: bold;">หน่วยกิต : <span
                                                            style="font-weight: normal;">132</span></p>

                                                </td>
                                                <td>
                                                    <p style="color: black; font-weight: bold;">หน่วยกิต :<span
                                                            style="font-weight: normal;"> 5</span></p>

                                                </td>
                                                <td>
                                                    <p style="color: black; font-weight: bold;">หน่วยกิต : <span
                                                            style="font-weight: normal;"> 137 <span
                                                                style="color: green;">[+5]</span></span></p>

                                                </td>
                                            </tr>

                                        </tbody>
                                    </table>
                                </div>
                            </div>

                        </div>

                    </div>
                    <hr>
                    <div class="row">
                        <div class="col-12 mx-auto">
                            <div class="card">
                                <div class="card-header">
                                    <h5 style="color: blue;">คำนวณผลการเรียนล่วงหน้า</h5>

                                </div>
                                <div class="card-body">
                                    <div class="table-responsive">
                                        <table class="table table-striped" cellspacing="0" style="color: black;">
                                            <thead>
                                                <tr>
                                                    <th class="text-center">รหัสวิชา</th>
                                                    <th>ชื่อวิชา</th>

                                                    <th class="text-center">ผลการเรียน</th>
                                                    <th class="text-center">หน่วยกิต</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <tr>
                                                    <td class="text-center">01417167</td>
                                                    <td>Engineering Mathematics I</td>
                                                    <td class="text-center">C+</td>
                                                    <td class="text-center">3</td>
                                                </tr>
                                                <tr>
                                                    <td class="text-center">01999111</td>
                                                    <td>Knowledge of the Land</td>
                                                    <td class="text-center">A</td>
                                                    <td class="text-center">2</td>
                                                </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                                <p style="color: red; padding: 10px;">*วิชาที่สามารถ Regrade
                                    ได้ต้องเป็นรายวิชาที่ได้แต้มคะแนนต่ำกว่า 2.00 (ได้เกรดต่ำกว่า C)
                                </p>
                            </div>
                            <br><br>
                            <div style="text-align: center;">
                                <a href="./student_info.php" type="button" class="btn btn-primary">ย้อนกลับ</a>
                            </div>
                            <br><br>
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