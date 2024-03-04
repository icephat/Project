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
    <link href="../vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<?php

session_start();

require_once '../function/teacherFunction.php';



$teacher = getTeacherByUsernameTeacher($_SESSION["access-user"]);


$studentId = $_POST["studentId"];
$student = getStudentByStudentId($studentId);



?>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <?php include('../layout/teacher/nisit.php'); ?>

                <hr>


                <!-- Content Row -------------------------------------------------------BOX----------------------->
                <div class="row">
                    <div class="col-sm-6 text-left">
                        <h5 style="color: black;">
                            <?php echo $student["studentId"] ?> <span style="color: blue;">
                                <?php echo $student["fisrtNameTh"] . " " . $student["lastNameTh"] ?>
                            </span>
                        </h5>
                    </div>
                    <div class="col-sm-6 text-right">
                        <?php

                        if (file_exists("../student/calGPA/" . $student["studentId"] . ".json")) {



                            ?>
                        <a href="./calGPAHis.php?studentId=<?php echo $studentId ?>" type="button"
                            class="btn btn-primary">ดูประวัติการคาดการณ์</a>
                        <?php



                        }

                        ?>
                    </div>
                </div>
                <hr>
                <!-- Content Row -------------------------------------------------------BOX----------------------->
                <div class="row">
                    <div class="col-sm-6">
                        <div class="row" style="margin-left: 20px; padding: auto; ">
                            <div class="col-sm-6">
                                <p style="color: blue;">ชื่อ-นามสกุล (ภาษาอังกฤษ) : </p>
                            </div>
                            <div class="col-sm-6">
                                <p style="color: gray;">
                                    <?php echo $student["fisrtNameEng"] . " " . $student["lastNameEng"] ?>
                                </p>
                            </div>

                        </div>
                        <div class="row" style="margin-left: 20px; padding: auto; ">
                            <div class="col-sm-6">
                                <p style="color: blue;">เบอร์โทรศัพท์ : </p>
                            </div>
                            <div class="col-sm-6">
                                <p style="color: gray;">
                                    <?php echo $student["tell"] ?>
                                </p>
                            </div>

                        </div>
                        <div class="row" style="margin-left: 20px; padding: auto; ">
                            <div class="col-sm-6">
                                <p style="color: blue;">e-Mail :</p>
                            </div>
                            <div class="col-sm-6">
                                <p style="color: gray;">
                                    <?php echo $student["email"] ?>
                                </p>
                            </div>

                        </div>
                        <div class="row" style="margin-left: 20px; padding: auto; ">
                            <div class="col-sm-6">
                                <p style="color: blue;">สาขาวิชา :</p>
                            </div>
                            <div class="col-sm-6">
                                <p style="color: gray;">
                                    <?php echo $student["department"]["departmentName"] ?>
                                </p>
                            </div>

                        </div>
                        <div class="row" style="margin-left: 20px; padding: auto; ">
                            <div class="col-sm-6">
                                <p style="color: blue;">การศึกษาระดับมัธยม :</p>
                            </div>
                            <div class="col-sm-6">
                                <p style="color: gray;">
                                    <?php echo $student["school"]["schoolName"] ?>
                                </p>
                            </div>

                        </div>
                        <div class="row" style="margin-left: 20px; padding: auto; ">
                            <div class="col-sm-6">
                                <p style="color: blue;">ช่องทางรับเข้า :</p>
                            </div>
                            <div class="col-sm-6">
                                <p style="color: gray;">TCAS
                                    <?php echo $student["tcasId"] ?>
                                </p>
                            </div>

                        </div>
                        <!-- <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: blue;">สิทธิ์ฝึกงาน :</p>
                                </div>
                                <div class="col-sm-6">
                                    <p style="color: gray;">มีสิทธิ์/ <span style="color: green;">ผ่าน</span></p>
                                </div>
                            </div>
                            <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: blue;">สิทธิ์โครงงาน :</p>
                                </div>
                                <div class="col-sm-6">
                                    <p style="color: gray;">มีสิทธิ์/ <span style="color: red;">ไม่ผ่าน</span></p>
                                </div>
                            </div> -->

                    </div>

                    <div class="col-sm-6">
                        <!--<div class="row" style="margin-left: 20px; padding: auto; ">
                            <div class="col-sm-6">
                                <p style="color: blue;">รหัสประจำตัวประชาชน : </p>
                            </div>
                            <div class="col-sm-6">
                                <p style="color: gray;">
                                    <?php echo $student["personId"] ?>
                                </p>
                            </div>

                        </div>-->
                        <div class="row" style="margin-left: 20px; padding: auto; ">
                            <div class="col-sm-6">
                                <p style="color: blue;">เบอร์โทรศัพท์ผู้ปกครอง : </p>
                            </div>
                            <div class="col-sm-6">
                                <p style="color: gray;">
                                    <?php echo $student["parentTell"] ?>
                                </p>
                            </div>

                        </div>
                        <div class="row" style="margin-left: 20px; padding: auto; ">
                            <div class="col-sm-6">
                                <p style="color: blue;">อาจารย์ที่ปรึกษา :</p>
                            </div>
                            <div class="col-sm-6">
                                <p style="color: gray;">
                                    <?php echo $student["teacher"]["titleTecherTh"] . $student["teacher"]["fisrtNameTh"] . " " . $student["teacher"]["lastNameTh"]; ?>
                                </p>
                            </div>

                        </div>
                        <div class="row" style="margin-left: 20px; padding: auto; ">
                            <div class="col-sm-6">
                                <p style="color: blue;">ประเภทหลักสูตร :</p>
                            </div>
                            <div class="col-sm-6">
                                <p style="color: gray;">
                                    <?php echo $student["course"]["nameCourseUse"] . " (" . $student["course"]["planCourse"] . ")" ?>
                                </p>
                            </div>

                        </div>
                        <div class="row" style="margin-left: 20px; padding: auto; ">
                            <div class="col-sm-6">
                                <p style="color: blue;">ที่อยู่โรงเรียน :</p>
                            </div>
                            <div class="col-sm-6">
                                <p style="color: gray;">จังหวัด
                                    <?php echo $student["school"]["provinceName"] ?>
                                </p>
                            </div>

                        </div>
                        <div class="row" style="margin-left: 20px; padding: auto; ">
                            <div class="col-sm-6">
                                <p style="color: blue;">สถานะ :</p>
                            </div>
                            <div class="col-sm-6">
                                <p style="color: gray;">
                                    <?php echo $student["status"] ?>
                                </p>
                            </div>

                        </div>
                        <!-- <div class="row" style="margin-left: 20px; padding: auto; ">
                                <div class="col-sm-6">
                                    <p style="color: blue;">สิทธิ์สหกิจ :</p>
                                </div>
                                <div class="col-sm-6">
                                    <p style="color: red;">ไม่มีสิทธิ์</p>
                                </div>
                            </div> -->
                        <div class="row" style="margin-left: 20px; padding: auto; ">
                            <div class="col-sm-6">
                                <p style="color: blue;">note (หมายเหตุ):</p>
                            </div>
                            <div class="col-sm-6">
                                <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#modalnote">ดู note</button>
                                
                                <?php
                                if (file_exists("./note/" . $studentId . ".json")) {
                                    $path = "./note/" . $studentId . ".json";
                                    $jsonString = file_get_contents($path);
                                    $remark = json_decode($jsonString, true);


                                    ?>
                                    <p style="color: gray;">
                                        <?php echo $remark ?>
                                    </p>
                                    <?php

                                }
                                ?>
                            </div>
                        </div>

                    </div>
                </div>

                <br>
                <div class="row">
                    <div class="col-sm-12 mx-auto" style="margin-left: 20px;">
                        <form action="../controller/addNote.php" method="post">
                            <p><label for="note" style="color:#0552d8;">เพิ่ม note:</label></p>
                            <textarea style=" width: 100%;" id="note" name="note"></textarea>
                            <input type="hidden" name="studentId" value="<?php echo $studentId ?>" />
                            <br>
                            <div class="text-center">
                                <button type="submit" class="btn btn-success center-block">บันทึก</button>
                            </div>

                        </form>

                    </div>
                </div>
                <br>


                <div class="row">
                    <div class="col-sm-12">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">รายงานผลการเรียนแต่ละภาคการศึกษา</h6>
                            </div>
                            <div class="card-body ">
                                <div class="row" style="padding: 20px;">
                                    <div class="col-sm-6">
                                        <p style="font-weight: bold; font-size: 12px;"><span style="color: red;">
                                                <svg style="color: #ff6962;" xmlns="http://www.w3.org/2000/svg"
                                                    width="16" height="16" fill="currentColor" class="bi bi-circle-fill"
                                                    viewBox="0 0 16 16">
                                                    <circle cx="8" cy="8" r="8" />
                                                </svg> เกรด(0-1.74)</span>
                                            <span style="color: orange;">&nbsp;&nbsp;&nbsp; <svg style="color: #f57b39;"
                                                    xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                    fill="currentColor" class="bi bi-circle-fill" viewBox="0 0 16 16">
                                                    <circle cx="8" cy="8" r="8" />
                                                </svg> เกรด(1.75-1.99)</span>
                                            <span style="color: green;">&nbsp;&nbsp;&nbsp;<svg style="color: #99cc99;"
                                                    xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                    fill="currentColor" class="bi bi-circle-fill" viewBox="0 0 16 16">
                                                    <circle cx="8" cy="8" r="8" />
                                                </svg> เกรด(2.0-3.24)</span>
                                            <span style="color: blue;">&nbsp;&nbsp;&nbsp;<svg style="color: #86d3f7;"
                                                    xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                    fill="currentColor" class="bi bi-circle-fill" viewBox="0 0 16 16">
                                                    <circle cx="8" cy="8" r="8" />
                                                </svg> เกรด(3.25-4.00)</span>
                                            <span style="color: rgb(0, 107, 201);">
                                                ~ GPAX
                                            </span>
                                        </p>
                                        <canvas id="myChart"></canvas>
                                    </div>
                                    <div class="col-sm-6 float-right">
                                        <div class="table-responsive">
                                            <table class="table table-striped" cellspacing="0"
                                                style="color: black; font-size: small;">
                                                <thead>
                                                    <tr>
                                                        <th>ปีการศึกษา</th>
                                                        <th>ภาคการศึกษา</th>
                                                        <th class="text-center">หน่วยกิต</th>
                                                        <th class="text-center">GPA</th>
                                                        <th class="text-center">GPAX</th>
                                                        <th class="text-right">+-GPAX</th>
                                                        <th>รายละเอียด</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                    $i = 0;
                                                    $gpa = 0;
                                                    $gpaNew = 0.00;
                                                    $idterm = 0;
                                                    $check = 0;
                                                    foreach ($student["terms"] as $term) {

                                                        if ($i == 0) {
                                                            $gpa = number_format($term["gpaAll"], 2, '.', '');
                                                            $i++;
                                                        } else {
                                                            $gpaNew = number_format($term["gpaAll"], 2, '.', '') - $gpa;
                                                            $gpa = number_format($term["gpaAll"], 2, '.', '');
                                                        }

                                                        $ch = " ";

                                                        echo "
                                                            <tr>
                                                                <th scope=\"row\">" . $term["semesterYear"] . "</th>
                                                                <td class=\"text-center\">" . $term["semesterPart"] . "</td>
                                                                <td class=\"text-center\">" . $term["creditTerm"] . "</td>
                                                                <td>" . number_format($term["gpaTerm"], 2, '.', '') . "</td>
                                                                <td>" . number_format($term["gpaAll"], 2, '.', '') . "</td>";

                                                        if (number_format($gpaNew, 2) > 0.00) {
                                                            $color = "color:green";
                                                            $ch = "+";
                                                        } elseif (number_format($gpaNew, 2) < 0.00) {
                                                            $color = "color:red";
                                                            $ch = "";
                                                        } else {
                                                            $color = "color:green";
                                                            $ch = "";
                                                        }

                                                        // $idterms[] = $idterm;
                                                        echo "<td class=\"text-right\"><span style=\"" . $color . "\"> " . $ch . number_format($gpaNew, 2) . " </span></td>
                                                                <td class=\"text-center\">
                                                                    <a data-toggle=\"modal\" data-target=\"#modal" . $idterm . "\" >
                                                                        <i class=\"fas fa-search fa-sm\"></i>
                                                                    </a>

                                                                </td>
                                                                
                                                            </tr>";
                                                        $idterm++;
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

                <div class="row">
                    <div class="col-sm-12">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">ผลการเรียนในแต่ละหมวดวิชา</h6>
                            </div>
                            <?php

                            $academics = getAcademicInSubjectCategoryByStudentId($student["studentId"]);

                            ?>
                            <div class="card-body">
                                <div class="row">
                                    <div class="col-sm-6">
                                        <p style="font-weight: bold; font-size: 12px;"><span style="color: red;">
                                                <svg style="color: #ff6962;" xmlns="http://www.w3.org/2000/svg"
                                                    width="16" height="16" fill="currentColor" class="bi bi-circle-fill"
                                                    viewBox="0 0 16 16">
                                                    <circle cx="8" cy="8" r="8" />
                                                </svg> เกรด(0-1.74)</span>
                                            <span style="color: orange;">&nbsp;&nbsp;&nbsp; <svg style="color: #f57b39;"
                                                    xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                    fill="currentColor" class="bi bi-circle-fill" viewBox="0 0 16 16">
                                                    <circle cx="8" cy="8" r="8" />
                                                </svg> เกรด(1.75-1.99)</span>
                                            <span style="color: green;">&nbsp;&nbsp;&nbsp;<svg style="color: #99cc99;"
                                                    xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                    fill="currentColor" class="bi bi-circle-fill" viewBox="0 0 16 16">
                                                    <circle cx="8" cy="8" r="8" />
                                                </svg> เกรด(2.0-3.24)</span>
                                            <span style="color: blue;">&nbsp;&nbsp;&nbsp;<svg style="color: #86d3f7;"
                                                    xmlns="http://www.w3.org/2000/svg" width="16" height="16"
                                                    fill="currentColor" class="bi bi-circle-fill" viewBox="0 0 16 16">
                                                    <circle cx="8" cy="8" r="8" />
                                                </svg> เกรด(3.25-4.00)</span>
                                        </p>
                                        <canvas id="myChartSub"></canvas>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="table-responsive">
                                            <table class="table table-striped" width="100%" cellspacing="0"
                                                style="color: black; font-size: small;">
                                                <thead>
                                                    <tr>
                                                        <th>หมวดวิชา</th>
                                                        <th style="text-align: right;">เกรดเฉลี่ย</th>
                                                        <th style="text-align: right; ">หน่วยกิตทั้งหมด
                                                        </th>
                                                        <th style="text-align: right;">
                                                            จำนวนหน่วยกิตที่<span
                                                                style="color:#428f3e;">เรียนไปแล้ว</span>
                                                        </th>
                                                        <th style="text-align: right; ">
                                                            จำนวนหน่วยกิตที่<span style="color:red;">ยังไม่เรียน</span>
                                                        </th>


                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <?php
                                                    $credit = 0;
                                                    $creditYet = 0;
                                                    $creditCheck=0;
                                                    foreach ($academics as $academic) {
                                                        echo "
                                                            
                                                    <tr>
                                                        <td>" . $academic["name"] . "</td>
                                                        <td style=\"font-weight: bold; text-align: right;\">
                                                        " . number_format($academic["grade"], 2, '.', '') . "
                                                        </td>
                                                        <td style=\"font-weight: bold; text-align: right;\">" . $academic["creditAll"] . "</td>
                                                        <td style=\"font-weight: bold; color: green; text-align: right;\">
                                                        " . $academic["credit"] . "
                                                        </td>";
                                                        if($academic["creditYet"] < 0){
                                                            $creditCheck = 0;
                                                        }
                                                        else{
                                                            $creditCheck = $academic["creditYet"];
                                                        }
                                                        echo
                                                         "<td style=\"font-weight: bold; color: red; text-align: right;\">" . $creditCheck . "</td>
                                                        
                                                        
                                                    </tr>";
                                                        $credit += $academic["credit"];
                                                        $creditYet += $academic["creditYet"];

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
                    <div class="col-12">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">
                                    ร้อยละของหน่วยกิตที่ลงทะเบียนแบ่งตามหมวดวิชา (%)</h6>
                            </div>
                            <div class="card-body">
                                <div class="row " style=" justify-content: center; align-items: center;">
                                    <?php
                                    $percentAll = 0;
                                    $percentCreditYetAll = 0;
                                    $percentCreditAll = number_format( ($credit * 100) / $student["course"]["totalCredit"], 2, '.', '');
                                    $percentCreditYetAll = number_format(($creditYet * 100) / $student["course"]["totalCredit"], 2, '.', '');
                                    
                                    ?>
                                    <div class="col-sm-2" style="text-decoration: none;">
                                        <div class="t1 card">
                                            <p style="padding: 10px;">หน่วยกิตการเรียน &nbsp;<br><span
                                                    style="color:#304f69;">ทั้งหมด</span></p>
                                            <div style="text-align: center; position: relative;\">
                                                <canvas id="donutChart0"></canvas>
                                                <div id="centerText"
                                                    style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); font-size: 20px; color: #333;">
                                                    <?php echo $percentCreditAll ?>%
                                                    <?php echo number_format($student["gpax"], 2, '.', '') ?>
                                                </div>
                                            </div>
                                        </div>

                                    </div>

                                    <?php

                                    $i = 1;

                                    foreach ($academics as $academic) {
                                        $percent = 0;

                                        $percent = round((float) ($academic["credit"] * 100) / $academic["creditAll"], 2);
                                        ?>




                                        <a href="./student_info.php#tab<?php echo $i ?>" class="col-sm-2"
                                            style="text-decoration: none;">
                                            <div class="t1 card">
                                                <p style="padding: 10px;">หน่วยกิตการเรียน &nbsp;<br><span
                                                        style="color:#304f69;">
                                                        <?php echo $academic["name"] ?>
                                                    </span></p>
                                                <div style="text-align: center; position: relative;\">
                                                    <canvas id="donutChart<?php echo $i ?>"></canvas>
                                                    <div id="centerText"
                                                        style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); font-size: 20px; color: #333;">
                                                        <?php echo $percent ?>%<br>
                                                        <?php echo number_format($academic["grade"], 2, '.', '') ?>
                                                    </div>
                                                </div>
                                            </div>

                                        </a>

                                        <?php

                                        $i++;
                                    }


                                    ?>

                                </div>
                            </div>
                        </div>

                    </div>

                </div>

                <br><br>
                <div style="justify-content: center; align-items: center;">

                    <div class="col-sm-12 mx-auto">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">ผลการเรียนวิชาที่ไม่ผ่านตามแผน</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-striped" cellspacing="0" style="color: black;  ">
                                        <thead style="background-color: #86d3f7;">
                                            <tr>
                                                <th style=" text-align: center;">ชั้นปี</th>
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


                                            $courseNotLearns = getSubjectNotLearnInCoureseList($student["studentId"], $student["course"]["nameCourseUse"], $student["course"]["planCourse"], $student["studyYear"], $student["studyTerm"]);


                                            foreach ($courseNotLearns as $courseNotLearn) {
                                                $sumcreditF += $courseNotLearn["credit"];
                                                ?>
                                                <tr>
                                                    <td style=" text-align: center;"></td>
                                                    <td style=" text-align: center;"></td>
                                                    <td style=" text-align: center;">
                                                        <?php echo $courseNotLearn["subjectGroup"] ?>
                                                    </td>
                                                    <td style=" text-align: center;">
                                                        <?php echo $courseNotLearn["subjectCode"] ?>
                                                    </td>
                                                    <td style=" text-align: left;">

                                                    </td>
                                                    <td style=" text-align: center;">
                                                        <?php echo $courseNotLearn["credit"] ?>
                                                    </td>
                                                    <td style=" text-align: center;"></td>

                                                </tr>
                                                <?php
                                            }
                                            ?>
                                            <tr>
                                                <td style="background-color: #86d3f7; font-weight: bold; color: black; text-align: center;"
                                                    colspan="4">
                                                    รวม</td>
                                                <td style=" text-align: center;">
                                                    <?php 
                                                    $s = count($subjectFs)+count($courseNotLearns);
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
                        <br>

                    </div>
                    <br><br>

                    <div class="col-sm-12 mx-auto">
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">ผลการเรียนวิชาที่วิชาตกค้างที่ผ่านแล้ว
                                </h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-striped" cellspacing="0" style="color: black;  ">
                                        <thead style="background-color: #86d3f7;">
                                            <tr>
                                                <th style=" text-align: center;">ชั้นปี</th>
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
                                                $sumcreditF += $subjectPassAndNot["credit"];

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
                        <br>

                    </div>
                    <br><br>

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
                                            <a class=\"" . $unlink . "\" id=\"tab" . $i . "-tab\" data-toggle=\"tab\" href=\"#tab" . $i . "\" role=\"tab\" aria-controls=\"tab" . $i . "\" aria-selected=" . $tab . ">" . $gorup["name"] . "</a>
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
                                                <table class=\"table table-striped\" id=\"dataTable" . $i . "\" cellspacing=\"0\"
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
                    <a class="btn btn-primary" href="login.php">Logout</a>
                </div>
            </div>
        </div>
    </div>

    <!--modal-->
    <?php
    $i = 0;
    foreach ($student["terms"] as $term) {

        echo "
                        <div id=modal" . $i . " class=\"modal\" style=\"color: black;\">
                            <div class=\"modal-dialog modal-lg\">
                                <div class=\"modal-content\">";

        echo "<div class=\"modal-header\" style=\"height: 90px;\">
                        <table class=\"modal-dialog modal-lg\" style=\"border:none; width: 85%;\">
                            <th style=\" text-align: left; \">
                                        <h5 style=\"font-weight: bold;\">GPA" . number_format($term["gpaTerm"], 2, '.', '') . "</h5>
                            </th>
                            <th style=\" text-align: right;\">
                                <h5 style=\"font-weight: bold;\">GPAX " . number_format($term["gpaAll"], 2, '.', '') . "</h5>
                            </th>
                        </table>
                        <button type=\"button\" class=\"close\" data-dismiss=\"modal\">&times;</button>
                        <br>



                        </div>
                        <h4 class=\"modal-title\" style=\"margin-left: 10px;\">ผลการเรียนของนิสิตในปีการศึกษา " . $term["semesterYear"] . " " . $term["semesterPart"] . "</h4>
                        <div class=\"modal-body\" id=\"std_detail\">
                            <table class=\"table\">

                                <thead>
                                    <tr>
                                        
                                        <th>GPA</th>
                                        <th>จำนวนหน่วยกิต</th>
                                        <th>รายชื่อวิชา</th>
                                    </tr>
                                </thead>
                                <tbody>";
        foreach ($term["regisList"] as $regis) {
            echo " 
                                        <tr>
                                            
                                            <th>" . $regis["gradeCharacter"] . "</th>
                                            <th>" . $regis["credit"] . "</th>
                                            <th>" . $regis["nameSubjectEng"] . "</th>
                                        </tr>
                                    ";
        }


        echo "</tbody>
                            </table>

                                </div>
                                <div class=\"modal-footer\">
                                    <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\"
                                        style=\"font-size: 18px;\">ปิดหน้าต่าง</button>
                                </div>
                            </div>
                        </div>
                    </div>";


        $i++;
    }


    ?>



    <!-- modalnote -->
               
    <div id="modalnote" class="modal fade" style="color: black;">
                    <div class="modal-dialog modal-lg">
                        <div class="modal-content">
                            <div class="modal-header" style="height: 90px;">
                                <h5>Note </h5>
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <br>

                            </div>
                            
                            <div class="modal-body" id="std_detail">
                                <table class="table">

                                    <thead>
                                        <tr>
                                            <th>อาจารย์</th>
                                            <th>รายการบันทึก</th>
                                            <th class="text-center">รายละเอียด</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <tr>
                                            <th class="text-center">ฐิติพงษ์</th>
                                            <th>ซึมเศร้า</th>
                                            <th class="text-center">
                                                <button type="button" class="btn btn-danger">ลบ</button>
                                            </th>
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
    <script src="../js/demo/datatables-demo2.js"></script>

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

        <?php
        $gp = [];
        $gpAll = [];
        $label = [];
        foreach ($student["terms"] as $term) {

            $floatvar = (float) number_format($term["gpaTerm"], 2, '.', '');
            $gp[] = $floatvar;

            $floatvarAll = (float) number_format($term["gpaAll"], 2, '.', '');
            $gpAll[] = $floatvarAll;

            $label[] = $term["semesterPart"] . " " . $term["semesterYear"];
        }
        ?>
        var gs = <?php echo json_encode($gp); ?>;
        console.log(gs);

        var gsAll = <?php echo json_encode($gpAll); ?>;
        console.log(gsAll);

        const GPA2563 = gs;
        const GPAAll = gsAll;
        const labeljs = <?php echo json_encode($label); ?>;

        const GPAcolorbar = [];
        let GPAsize = GPA2563.length;
        let GPAcolorLoop;

        for (let i = 0; i < GPAsize; i++) {
            if (GPA2563[i] >= 0.0000 && GPA2563[i] <= 1.7499) {
                GPAcolorLoop = 'rgba(255, 105, 98,0.7)';
            }
            else if (GPA2563[i] >= 1.7500 && GPA2563[i] <= 1.9999) {
                GPAcolorLoop = 'rgba(245, 123, 57,0.7)';
            }
            else if (GPA2563[i] >= 2.0000 && GPA2563[i] <= 3.2499) {
                GPAcolorLoop = 'rgba(153, 204, 153,0.7)';
            }
            else if (GPA2563[i] >= 3.2500) {
                GPAcolorLoop = 'rgba(134, 211, 247,0.7)';
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
                labels: labeljs,
                datasets: [{
                    type: 'line',
                    data: GPAAll,
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

        <?php
        $academicGrades = [];
        $academicLabel = [];
        foreach ($academics as $academic) {
            $academicGrades[] = (float) $academic["grade"];
            $academicLabel[] = $academic["name"];

        }

        ?>

        var grades = <?php echo json_encode($academicGrades) ?>;
        console.log(grades);
        var academicLabels = <?php echo json_encode($academicLabel) ?>;
        console.log(academicLabels);

        var ctx = document.getElementById("myChartSub");
        const GPASub = grades;
        const GPASubcolorbar = [];
        let GPASubsize = GPASub.length;
        let GPASubcolorLoop;
        for (let i = 0; i < GPASubsize; i++) {
            if (GPASub[i] >= 0.0000 && GPASub[i] <= 1.7499) {
                GPASubcolorLoop = 'rgba(255, 105, 98,0.7)';
            }
            else if (GPASub[i] >= 1.7500 && GPASub[i] <= 1.9999) {
                GPASubcolorLoop = 'rgba(245, 123, 57,0.7)';
            }
            else if (GPASub[i] >= 2.0000 && GPASub[i] <= 3.2499) {
                GPASubcolorLoop = 'rgba(153, 204, 153,0.7)';
            }
            else if (GPASub[i] >= 3.2500) {
                GPASubcolorLoop = 'rgba(134, 211, 247,0.7)';
            }
            GPASubcolorbar[i] = GPASubcolorLoop;
        }
        var myChart = new Chart(ctx, {
            //type: 'bar',
            //type: 'line',

            type: 'bar',
            data: {
                labels: academicLabels,
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

        <?php

        $dataLists = [];
        $dataPerLists = [];
        $dataGrades = [];
        foreach ($academics as $academic) {

            //$dataPerLists[] = (float)100-($academic["credit"]*($academic["creditYet"]/100));
            $dataPerLists[] = (float) ($academic["credit"] * 100) / $academic["creditAll"];
            if((float) ($academic["creditYet"] * 100) / $academic["creditAll"] >= 0){
                $dataLists[] = (float) ($academic["creditYet"] * 100) / $academic["creditAll"];
            }
            else if((float) ($academic["creditYet"] * 100) / $academic["creditAll"] < 0){
                $dataLists[] = 0;
            }
            
            $dataGrades[] = (float) $academic["grade"];

        }

        ?>
        var dataGrades = <?php echo json_encode($dataGrades) ?>;
        console.log(dataGrades);
        var perLists = <?php echo json_encode($dataPerLists) ?>;
        console.log(perLists);
        var datalists = <?php echo json_encode($dataLists) ?>;

        var dataCreditYet = <?php echo $percentCreditYetAll; ?>;
        
        var dataCredit = <?php echo $percentCreditAll ?>;
       
        let GPAPiesize = dataGrades.length;
        const GPAcolorPie = [];
        let GPAPiecolorLoop;
        for (let i = 0; i < GPAPiesize; i++) {
            if (dataGrades[i] >= 0.0000 && dataGrades[i] <= 1.7499) {
                GPAPiecolorLoop = 'rgba(255, 105, 98,0.7)';
            }
            else if (dataGrades[i] >= 1.7500 && dataGrades[i] <= 1.9999) {
                GPAPiecolorLoop = 'rgba(245, 123, 57,0.7)';
            }
            else if (dataGrades[i] >= 2.0000 && dataGrades[i] <= 3.2499) {
                GPAPiecolorLoop = 'rgba(153, 204, 153,0.7)';
            }
            else if (dataGrades[i] >= 3.2500) {
                GPAPiecolorLoop = 'rgba(134, 211, 247,0.7)';
            }
            GPAcolorPie[i] = GPAPiecolorLoop;
        }

        let colorAll;
        if(dataCredit <= 25){
            colorAll = 'rgba(255, 105, 98,0.7)';
        }
        else if(dataCredit > 25 && dataCredit <= 50){
            colorAll = 'rgba(245, 123, 57,0.7)';
        }
        else if(dataCredit > 50 && dataCredit <=75){
            colorAll = 'rgba(153, 204, 153,0.7)';
        }
        else if(dataCredit > 75){
            colorAll = 'rgba(134, 211, 247,0.7)';
        }
        let x = 0;
        datalists.splice(0, 0, dataCreditYet);
        perLists.splice(0, 0, dataCredit);
        GPAcolorPie.splice(0, 0, colorAll);


        const labels = ["donutChart0", "donutChart1", "donutChart2", "donutChart3", "donutChart4", "donutChart5", "donutChart6"];
        for (var name of labels) {
            var data = {
                datasets: [{
                    data: [datalists[x], perLists[x]],
                    backgroundColor: ['rgba(211,211,211,0.8)', GPAcolorPie[x]]
                }]
            };

            var ctx = document.getElementById(name);
            var name = new Chart(ctx, {
                type: "doughnut",
                data: data,
                options: {
                    cutoutPercentage: perLists[x],  // กำหนดค่านี้เพื่อสร้าง Donut Chart
                    responsive: true,
                    plugins: {
                        legend: {
                            display: false
                        }
                    }
                }
            });




            x++;
        }


    </script>

</body>

</html>