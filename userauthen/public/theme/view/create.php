<!DOCTYPE php>

<!--
 // WEBSITE: https://themefisher.com
 // TWITTER: https://twitter.com/themefisher
 // FACEBOOK: https://www.facebook.com/themefisher
 // GITHUB: https://github.com/themefisher/
-->

<php lang="en">
<head>

  <!-- Basic Page Needs
  ================================================== -->
  <meta charset="utf-8">
  <title>I+</title>

  <!-- Mobile Specific Metas
  ================================================== -->
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="Corporate php5 Template">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=5.0">
  <meta name="author" content="Themefisher">
  <meta name="generator" content="Themefisher Rappo php Template v1.0">

  <!-- bootstrap -->
  <link rel="stylesheet" href="../plugins/bootstrap/bootstrap.min.css">
  <!-- Animate -->
  <link rel="stylesheet" href="../plugins/animate-css/animate.css">
  <!-- Icon Font css -->
  <link rel="stylesheet" href="../plugins/fontawesome/css/all.css">
  <link rel="stylesheet" href="../plugins/fonts/Pe-icon-7-stroke.css">
  <!-- Themify icon Css -->
  <link rel="stylesheet" href="../plugins/themify/css/themify-icons.css">
  <!-- Slick Carousel CSS -->
  <link rel="stylesheet" href="../plugins/slick-carousel/slick/slick.css">
  <link rel="stylesheet" href="../plugins/slick-carousel/slick/slick-theme.css">

  <!-- Main Stylesheet -->
  <link rel="stylesheet" href="../css/style2.css">
  <link href="../css/font-awesome.css" rel="stylesheet">
  
  <!--Favicon-->
  <link rel="icon" href="{{ url('./theme/images/LOGO2.png') }}" type="image/x-icon">
  <link href="../plugins/tables/css/datatable/dataTables.bootstrap4.min.css" rel="stylesheet">

  <style>
    .section-bottom {
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh; /* ครอบคลุมความสูงของหน้าจอทั้งหมด */
        }
</style>
  

</head>
<body id="top-header">



<?php include './layout/header.php'?>

    <section class="section" id="section-testimonial" style="margin-top: 20px;">

        <div class="container-fluid">
            <div class="row justify-content-center">
                <div class="col-lg-10">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-validation">
                                <form class="form-valide" action="./home.php" method="post" novalidate="novalidate">
                                    <div class="form-group row">
                                        <label class="col-lg-4 col-form-label" for="idusername">รหัสพนักงาน 
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="idusername" name="idusername" value="" >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-4 col-form-label" for="nameth">ชื่อ (ไทย) 
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="nameth" name="nameth" value="" >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-4 col-form-label" for="lnameth">นามสกุล (ไทย) 
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="lnameth" name="lnameth" value="" >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-4 col-form-label" for="fnameEn">FIRST NAME 
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="fnameEn" name="fnameEn" value="" >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-4 col-form-label" for="lnameEn">LAST NAME 
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="lnameEn" name="lnameEn" value="" >
                                        </div>
                                    </div>
                                   
                                    <div class="form-group row">
                                        <label class="col-lg-4 col-form-label" for="NickName">ชื่อเล่น 
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="NickName" name="NickName" value="" >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-4 col-form-label" for="position">ตำแหน่งงาน 
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="position" name="position" value="" >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-4 col-form-label" for="phone">เบอร์โทร 
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="phone" name="phone" value="" >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-4 col-form-label" for="Email">Email ใน 
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="Email" name="Email" value="" >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-4 col-form-label" for="Email2">Email นอก 
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="Email2" name="Email2" value="" >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-4 col-form-label" for="call">เบอร์ภายใน
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="call" name="call" value="" >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-4 col-form-label" for="location">Location 
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="location" name="location" value="" >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-4 col-form-label" for="val-number">ฝ่าย
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="val-number" name="val-number" value="" >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-4 col-form-label" for="val-range">แผนก
                                        </label>
                                        <div class="col-lg-6">
                                            <input type="text" class="form-control" id="val-range" name="val-range" value="" >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-lg-4 col-form-label" for="val-number">สถานะ
                                        </label>
                                        <div class="col-lg-6">
                                            <select class="form-control" id="val-skill" name="val-skill">
                                                <option value="">Please select</option>
                                                <option value="new" >New</option>
                                                <option value="ปกติ">ปกติ</option>
                                                <option value="ลาออก">ลาออก</option>
                                                <option value="Update data">Update data</option>
                                            </select>
                                        </div>
                                    </div>
                                
                                   
                                    <div class="form-group row">
                                        <div class="col-lg-8 ml-auto">
                                            <a href="./home.php" type="button" class="btn mb-1 btn-outline-light" style="color: black;">ย้อนกลับ</a>
                                            <button type="submit" class="btn btn-primary">บันทึก</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
 

    <?php include './layout/footer.php'; ?>


  <!-- 
  Essential Scripts
  =====================================-->
  <!-- jQuery -->
  <script src="../plugins/jquery/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="../plugins/bootstrap/bootstrap.min.js"></script>
  <!-- Slick Slider -->
  <script src="../plugins/slick-carousel/slick/slick.min.js"></script>

  <script src="../plugins/google-map/gmap.js"></script>

  <script src="../js/script.js"></script>
  <script src="../plugins/tables/js/jquery.dataTables.min.js"></script>
    <script src="../plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
    <script src="../plugins/tables/js/datatable-init/datatable-basic.min.js"></script>

</body>
</php>
   