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

            ?>

            <?php include('../layout/head/report.php'); ?>

                    <div>
                        <form class="form-valide" action="../controller/headSearchFacultyTcas.php" method="post" enctype="multipart/form-data">
                            <div class="row mx-auto">
                                <!-- <div class="column mx-auto col-sm-2">
                                    <div class="text-center">
                                        <h5>ภาควิชา<span style="color: red;">*</span></th>
                                    </div>
                                    <div class="text-center">
                                        <div>
                                            <select class="form-control" data-live-search="true" name="departmentId">

                                                <option value="0">ทุกภาค</option>
                                                        <?php
                                                        foreach ($departments as $department) {
                                                            ?>

                                                            <option value="<?php echo $department["departmentId"] ?>">
                                                                <?php echo $department["departmentName"] ?>
                                                            </option>
                                                            <?php
                                                        }
                                                        ?>
                                            </select>
                                        </div>
                                    </div>
                                </div> -->
                                <div class="column mx-auto col-sm-4">
                                    <div class="text-center">
                                        <h5>รอบ TCAS<span style="color: red;">*</span></th>
                                    </div>
                                    <div class="text-center">
                                        <div>
                                            <select class="form-control" data-live-search="true" name = "tcas">
                                                <option value="0">ทุกรอบ</option>

                                                <option value="1">รอบที่ 1
                                                </option>
                                                <option value="2">รอบที่ 2</option>
                                                <option value="3">รอบที่ 3
                                                </option>
                                                <option value="4">รอบที่ 4</option>
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
                                            <select class="form-control" data-live-search="true" name = "generetion">
                                                <option value="0">ทุกรุ่น</option>

                                                <?php
                                                foreach ($generetions as $generetion) {
                                                    ?>
                                                    <option value="<?php echo $generetion["studyGeneretion"] ?>">
                                                        <?php echo $generetion["studyGeneretion"] ?>
                                                    </option>
                                                    <?php
                                                }
                                                ?>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <!-- <div class="column mx-auto col-sm-2">
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
                                </div> -->
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
                    <h5 style="color:black;">คณะวิศวกรรมศาสตร์ กำแพงแสน </h5>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">จำนวนนิสิตตาม Tcas (คน)</h6>
                                </div>
                                <div class="card-body ">
                                    <?php
                                    
                                    $countStudentSortByDepartments = getCountStudentTcasSortByDepartmentBySemesterYear($semester["semesterYear"]);
                                    ?>
                                    <div class="row" style="padding: 20px;">
                                        <div class="col-sm-6">

                                            <canvas id="myChart"></canvas>
                                        </div>
                                        <div class="col-sm-6 float-right">
                                            <div class="table-responsive">
                                                <table class="table table-striped" cellspacing="0"
                                                    style="color: black;">
                                                    <thead style=" ">
                                                        <tr>
                                                            <th >ภาควิชา</th>
                                                            <th style="text-align: center; "><span>รอบที่ 1</span>
                                                            </th>
                                                            <th style="text-align: center;"><span>รอบที่ 2</span></th>
                                                            <th style="text-align: center;">รอบที่ 3</th>
                                                            <th style="text-align: center;">รอบที่ 4</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <?php
                                                        $studyGeneretion=[];
                                                        $TCAS1=[];
                                                        $TCAS2=[];
                                                        $TCAS3=[];
                                                        $TCAS4=[];
                                                        
                                                        $sumTcas1 = 0;
                                                        $sumTcas2 = 0;
                                                        $sumTcas3 = 0;
                                                        $sumTcas4 = 0;

                                                        foreach($countStudentSortByDepartments as $countStudentSortByDepartment){

                                                            $sumTcas1+=$countStudentSortByDepartment["TCAS1"];
                                                            $sumTcas2+=$countStudentSortByDepartment["TCAS2"];
                                                            $sumTcas3+=$countStudentSortByDepartment["TCAS3"]; 
                                                            $sumTcas4+=$countStudentSortByDepartment["TCAS4"]; 

                                                            $studyGeneretion[]=$countStudentSortByDepartment["departmentInitials"] ;
                                                            $TCAS1[]=(int)$countStudentSortByDepartment["TCAS1"];
                                                            $TCAS2[]=(int)$countStudentSortByDepartment["TCAS2"];
                                                            $TCAS3[]=(int)$countStudentSortByDepartment["TCAS3"];
                                                            $TCAS4[]=(int)$countStudentSortByDepartment["TCAS4"];
                                                        
                                                        ?>
                                                        <tr>
                                                            <td><?php echo $countStudentSortByDepartment["departmentInitials"] ?></td>
                                                            <td style=" text-align: center;">
                                                            <?php echo $countStudentSortByDepartment["TCAS1"] ?> คน
                                                            </td>
                                                            <td style=" text-align: center;">
                                                            <?php echo $countStudentSortByDepartment["TCAS2"] ?> คน
                                                            </td>
                                                            <td style=" text-align: center;"><?php echo $countStudentSortByDepartment["TCAS3"] ?> คน</td>
                                                            <td style=" text-align: center;"><?php echo $countStudentSortByDepartment["TCAS4"] ?> คน</td>
                                                        </tr>
                                                        <?php
                                                        }
                                                        ?>

                                                        
                                                        
                                                        <tr>
                                                            <th scope='row' style=" ">ทุกภาค</th>
                                                            <td style="font-weight: bold; text-align: center;"><?php echo $sumTcas1?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $sumTcas2?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $sumTcas3?> คน</td>
                                                            <td style='font-weight: bold; text-align: center;'><?php echo $sumTcas4?> คน</td>
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
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">ผลการเรียนนิสิต</h6>
                                </div>
                                <?php $departmentMMAs = getMaxMinAVGGPAXSortByDepartmentInFacultyBySemesterYear($semester["semesterYear"]);?>
                                <div class="card-body ">
                                    <div class="row" style="padding: 20px;">
                                        <div class="col-sm-6">

                                            <div id="grade"></div>
                                        </div>
                                        <div class="col-sm-6 mx-auto">
                                            <div class="table-responsive">
                                                <table class="table table-striped" cellspacing="0"
                                                    style="color: black;">
                                                    <thead style=" ">
                                                        <tr>
                                                            <th ><span>ภาควิชา</span></th>
                                                            <th style="text-align: center; "><span>ค่าสูงสุด</span>
                                                            </th>
                                                            <th style="text-align: center;"><span>ค่าต่ำสุด</span></th>
                                                            <th style="text-align: center;"><span>ค่าเฉลี่ย</span></th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <?php
                                                        $studyGeneretionGrade=[];
                                                        $maxGPAX=[];
                                                        $minGPAX=[];
                                                        $avgGPAX=[];

                                                        foreach($departmentMMAs as $departmentMMA){
                                                            $studyGeneretionGrade[]="รุ่น ".(string)$departmentMMA["departmentInitials"];
                                                            $maxGPAX[]=(float)$departmentMMA["maxGPAX"];
                                                            $minGPAX[]=(float)$departmentMMA["minGPAX"];
                                                            $avgGPAX[]=(float)$departmentMMA["avgGPAX"];
                                                        ?>
                                                        <tr style="font-weight: normal;">
                                                            <td ><?php echo $departmentMMA["departmentInitials"] ?></td>
                                                            <td style=" text-align: center;">
                                                            <?php echo $departmentMMA["maxGPAX"] ?>
                                                            </td>
                                                            <td style=" text-align: center;">
                                                            <?php echo $departmentMMA["minGPAX"] ?>
                                                            </td>
                                                            <td style=" text-align: center;"><?php echo $departmentMMA["avgGPAX"] ?> </td>
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
                    <br><br>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">อัตราคงอยู่ </h6>
                                </div>
                                <?php
                                $percentageDepartments = getPercentageStudySortByDepartmentInFacultyBySemesterYear($semester["semesterYear"]);
                                
                                ?>
                                <div class="card-body ">
                                    
                                    <div class="row" style="padding: 20px;">
                                        <div class="col-sm-6">

                                            <canvas id="percent"></canvas>
                                        </div>
                                        <div class="col-sm-6 float-right">
                                            <div class="table-responsive">
                                                <table class="table table-striped" cellspacing="0"
                                                    style="color: black;">
                                                    <thead style=" ">
                                                        <tr>
                                                            <th style=" text-align: center; ">ภาควิชา</th>
                                                            <th style="text-align: center; "><span>จำนวนรับเข้า</span>
                                                            </th>
                                                            <th style="text-align: center;"><span>จำนวนคงอยู่</span></th>
                                                            <th style="text-align: center;">ร้อยละ</th>

                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <?php
                                                        $studyGeneretionPercent=[];
                                                        $entry=[];
                                                        $study=[];
                                                        
                                                        foreach($percentageDepartments as $percentageDepartment){

                                                            $studyGeneretionPercent[]= $percentageDepartment["departmentInitials"];
                                                            $study[]=(int)$percentageDepartment["study"];
                                                            $entry[]=(int)$percentageDepartment["entry"];
                                                        ?>
                                                        <tr>
                                                            <th style=" text-align: center;  "><?php echo $percentageDepartment["departmentInitials"]  ?></th>
                                                            <td style=" text-align: center;">
                                                            <?php echo $percentageDepartment["entry"]  ?> คน
                                                            </td>
                                                            <td style=" text-align: center;">
                                                            <?php echo $percentageDepartment["study"]  ?> คน
                                                            </td>
                                                            <td style=" text-align: center;"><?php echo $percentageDepartment["percentage"]  ?></td>

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
                    <br><br>
                    <div class="row">
                        <div class="col-sm-12">
                            <div class="card shadow mb-4">
                                <div class="card-header py-3">
                                    <h6 class="m-0 font-weight-bold text-primary">อัตราพ้นสภาพ </h6>
                                </div>
                                <?php
                                $percentageRetireDepartments = getPercentageStudyAndRetireSortByDepartmentInFacultyBySemesterYear($semester["semesterYear"]);
                                
                                ?>
                                <div class="card-body ">
                                    <div class="row" style="padding: 20px;">
                                        <div class="col-sm-6">

                                            <canvas id="percent2"></canvas>
                                        </div>
                                        <div class="col-sm-6 float-right">
                                       
                                            <div class="table-responsive">
                                                <table class="table table-striped" cellspacing="0"
                                                    style="color: black;">
                                                    <thead style=" ">
                                                        <tr>
                                                            <th style=" text-align: center; ">ภาควิชา</th>
                                                            <th style="text-align: center; "><span>จำนวนคงเหลือ</span></th>
                                                            <th style="text-align: center;"><span>จำนวนพ้นสภาพ</span></th>
                                                            <th style="text-align: center;">ร้อยละ</th>
                                                            
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                        <?php
                                                        $studyGeneretionPercent2=[];
                                                        $study2=[];
                                                        $retire2=[];
                                                        $percentage2=[];
                                                        foreach($percentageRetireDepartments as $percentageRetireDepartment){
                                                            $studyGeneretionPercent2[]=$percentageRetireDepartment["departmentInitials"];
                                                            $study2[]=(int)$percentageRetireDepartment["study"];
                                                            $retire2[]=(int)$percentageRetireDepartment["retire"];
                                                            $percentage2[]=(int)$percentageRetireDepartment["percentage"];
                                                        ?>
                                                        <tr>
                                                            
                                                            <td style=" text-align: center;">
                                                                <?php echo $percentageRetireDepartment["departmentInitials"] ?>
                                                            </td>
                                                            <td style=" text-align: center;"><?php echo $percentageRetireDepartment["study"] ?> คน</td>
                                                            <td style=" text-align: center;"><?php echo $percentageRetireDepartment["retire"] ?> คน</td>
                                                            <?php if((string)$percentageRetireDepartment["departmentInitials"] !=null){?>
                                                                <td style=" text-align: center;"><?php echo ((int)$percentageRetireDepartment["retire"]/(int)$percentageRetireDepartment["study"])*100 ?></td>
                                                            <?php }else{?>
                                                                <td style=" text-align: center;"></td>
                                                            <?php }?>
                                                            
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

                    <script>

                        var studyGeneretions = <?php echo json_encode($studyGeneretion); ?>;
                        
                        var tcas1 = <?php echo json_encode($TCAS1); ?>;
                        var tcas2 = <?php echo json_encode($TCAS2); ?>;
                        var tcas3 = <?php echo json_encode($TCAS3); ?>;
                        var tcas4 = <?php echo json_encode($TCAS4); ?>;

                        var ctx = document.getElementById("myChart");
                        var myChart = new Chart(ctx, {
                            //type: 'bar',
                            //type: 'line',
                            type: 'bar',
                            data: {
                                labels: studyGeneretions,
                                datasets: [{
                                    label: 'รอบที่ 1',
                                    data: tcas1,
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
                                    label: 'รอบที่ 2',
                                    data: tcas2,
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
                                    label: 'รอบที่ 3',
                                    data: tcas3,
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
                                },
                                {
                                    label: 'รอบที่ 4',
                                    data: tcas4,
                                    backgroundColor: '#f8c769',
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


                                ]

                            },

                            options: {
                                scales: {
                                    yAxes: [{
                                        ticks: {
                                            min: 0
                                        }
                                    }]
                                },
                                responsive: true,

                            }
                        });
                    </script>
                    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.4.0/Chart.js"></script>
                    <script src='https://cdn.plot.ly/plotly-2.27.0.min.js'></script>
                    <script>
                        var studyGeneretionGrade = <?php echo json_encode($studyGeneretionGrade); ?>;
                        
                        var maxGPAX = <?php echo json_encode($maxGPAX); ?>;
                        var minGPAX = <?php echo json_encode($minGPAX); ?>;
                        var avgGPAX = <?php echo json_encode($avgGPAX); ?>;
                        
    
                        var ctx = document.getElementById("grade");
                        var data = [];
    
                        for (var i = 0; i < studyGeneretionGrade.length; i++) {
                            var generationData = {
                                y: [maxGPAX[i], avgGPAX[i], minGPAX[i]],
                                type: 'box',
                                name: studyGeneretionGrade[i]
                            };
                            data.push(generationData);
                        }
    
                        Plotly.newPlot('grade', data);
                        
                    </script>

                    <script>

                    var studyGeneretionPercent = <?php echo json_encode($studyGeneretionPercent); ?>;
                    var entry = <?php echo json_encode($entry); ?>;
                    var study = <?php echo json_encode($study); ?>;

                        var ctx = document.getElementById("percent");
                        var myChart = new Chart(ctx, {
                            //type: 'bar',
                            //type: 'line',
                            type: 'bar',
                            data: {
                                labels: studyGeneretionPercent,
                                datasets: [{
                                    label: 'จำนวนรับเข้า',
                                    data: entry,
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
                                    label: 'จำนวนคงเหลือ',
                                    data: study,
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

                                ]

                            },

                            options: {
                                scales: {
                                    yAxes: [{
                                        ticks: {
                                            beginAtZero: true,
                                            min: 0
                                        }
                                    }]
                                },
                                responsive: true,

                            }
                        });
                    </script>

                    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.1/jquery.min.js">
                    </script>
                    <script src="https://cdn.jsdelivr.net/npm/chart.js@4.0.1/dist/chart.umd.min.js">
                    </script>
                    <script>
                        var studyGeneretionPercent2 = <?php echo json_encode($studyGeneretionPercent2); ?>;
                        var study2 = <?php echo json_encode($study2); ?>;
                        var retire2 = <?php echo json_encode($retire2); ?>;
                        var percentage2 = <?php echo json_encode($percentage2); ?>;

                        var ctx = document.getElementById("percent2");
                        var myChart = new Chart(ctx, {
                            //type: 'bar',
                            //type: 'line',
                            type: 'bar',
                            data: {
                                labels: studyGeneretionPercent2,
                                datasets: [{
                                    label: 'จำนวนคงอยู่',
                                    data: study2,
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
                                    label: 'จำนวนพ้นสภาพ',
                                    data: retire2,
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
                                },
                                yAxes: [{
                                    ticks: {
                                        beginAtZero: true,
                                        max: 100,
                                        min: 0
                                    }
                                }]
            
                            }
                        });
                    </script>



</body>

</html>