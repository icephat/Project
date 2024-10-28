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

    <!-- theme meta -->
    <meta name="theme-name" content="rappo" />

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
    <link rel="icon" href="../images/favicon.png" type="image/x-icon">
    <link href="../plugins/tables/css/datatable/dataTables.bootstrap4.min.css" rel="stylesheet">

  </head>

  <body id="top-header">

    <!-- LOADER TEMPLATE -->
    <div id="page-loader">
      <div class="loader-icon fa fa-spin colored-border"></div>
    </div>
    <!-- /LOADER TEMPLATE -->


    <?php include './layout/header.php' ?>



    <!-- HERO
    ================================================== -->
    <!-- <div class="row mx-auto" style="margin-top:20px; margin-left:5px;">
      <div class="col-lg-3 col-sm-6">
        <div class="card " style="border-radius: 20px; background-color: #00215E; ">
          <div class="card-body">
            <h3 class="card-title text-white">พนักงานทั้งหมด</h3>
            <div class="d-inline-block">
              <h2 class="text-white">500 คน</h2>
              <p class="text-white mb-0">2567</p>
            </div>
            <span class="float-right display-5 opacity-5"><i class="fa fa-users" style="color: white;" aria-hidden="true"></i></span>
          </div>
        </div>
      </div>
      <div class="col-lg-3 col-sm-6">
        <div class="card " style="border-radius: 20px; background-color: #41C9E2; ">
          <div class="card-body">
            <h3 class="card-title text-white">รับเข้าใหม่</h3>
            <div class="d-inline-block">
              <h2 class="text-white">30 คน</h2>
              <p class="text-white mb-0">2567</p>
            </div>
            <span class="float-right display-5 opacity-5"><i class="fa fa-user-plus" style="color: white;"></i></span>
          </div>
        </div>
      </div>
      <div class="col-lg-3 col-sm-6">
        <div class="card " style="border-radius: 20px; background-color: #FF9843; ">
          <div class="card-body">
            <h3 class="card-title text-white">ลาออก</h3>
            <div class="d-inline-block">
              <h2 class="text-white">10 คน</h2>
              <p class="text-white mb-0">2567</p>
            </div>
            <span class="float-right display-5 opacity-5"><i class="fa fa-user-times" style="color: white;"></i></span>
          </div>
        </div>
      </div>
      
    </div> -->
    <section class="banner-area py-5">
      <!-- Content -->
      <div class="col-md-12 col-lg-12 text-center">
        <div class="section-heading">

          <!-- Subheading -->
          <div class="col-lg-12">
            <div class="row">
              <div class="col-lg-12">
                <div class="section px-2 bg-white shadow text-center">
                
                  <div class="container-fluid">
                    <div class="row">
                      <div class="col-lg-12">
                        <div class="card">
                          
                          <div class="card-body">
                            <h4 class="card-title">รายชื่อพนักงานทั้งหมด</h4>
                            <a href="./create.php" type="button" class="btn mb-1 btn-primary float-right">เพิ่มพนักงานใหม่</a>
                            <div class="table-responsive">
                              <table class="table table-striped table-bordered zero-configuration">
                                <thead style="font-size: small;">
                                  <tr>
                                    <th>รหัสพนักงาน</th>
                                    <th>ชื่อ (ไทย)</th>
                                    <th>นามสกุล (ไทย)</th>
                                    <th>FIRST NAME</th>
                                    <th>LAST NAME</th>
                                    <th>ตำแหน่งงาน</th>
                                    <th>เบอร์ภายใน</th>
                                    <th>แผนก</th>
                                    <th>สถานะ</th>
                                    <th>#</th>
                                  </tr>
                                </thead>
                                <tbody>
                                  <tr>
                                    <td>000</td>
                                    <td>ภัทรพร</td>
                                    <td>แซ่ตั้น</td>
                                    <td>Phattaraporn</td>
                                    <td>Saeton</td>
                                    <td>ERP</td>
                                    <td>214</td>
                                    <td>IT</td>
                                    <td>New</td>
                                    <td>
                                      <a href="./detail.php" type="button" class="btn mb-1 btn-primary"><span class="ti-pencil-alt"></span></a>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td>000</td>
                                    <td>ภัทรพร</td>
                                    <td>แซ่ตั้น</td>
                                    <td>Phattaraporn</td>
                                    <td>Saeton</td>
                                    <td>ERP</td>
                                    <td>214</td>
                                    <td>IT</td>
                                    <td>New</td>
                                    <td>
                                      <a href="./detail.php" type="button" class="btn mb-1 btn-primary"><span class="ti-pencil-alt"></span></a>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td>000</td>
                                    <td>ภัทรพร</td>
                                    <td>แซ่ตั้น</td>
                                    <td>Phattaraporn</td>
                                    <td>Saeton</td>
                                    <td>ERP</td>
                                    <td>214</td>
                                    <td>IT</td>
                                    <td>ปกติ</td>
                                    <td>
                                      <a href="./detail.php" type="button" class="btn mb-1 btn-primary"><span class="ti-pencil-alt"></span></a>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td>000</td>
                                    <td>ภัทรพร</td>
                                    <td>แซ่ตั้น</td>
                                    <td>Phattaraporn</td>
                                    <td>Saeton</td>
                                    <td>ERP</td>
                                    <td>214</td>
                                    <td>IT</td>
                                    <td>ลาออก</td>
                                    <td>
                                      <a href="./detail.php" type="button" class="btn mb-1 btn-primary"><span class="ti-pencil-alt"></span></a>
                                    </td>
                                  </tr>

                                  <tr>
                                    <td>000</td>
                                    <td>ภัทรพร</td>
                                    <td>แซ่ตั้น</td>
                                    <td>Phattaraporn</td>
                                    <td>Saeton</td>
                                    <td>ERP</td>
                                    <td>214</td>
                                    <td>IT</td>
                                    <td>ปกติ</td>
                                    <td>
                                      <a href="./detail.php" type="button" class="btn mb-1 btn-primary"><span class="ti-pencil-alt"></span></a>
                                    </td>
                                  </tr>
                                  <tr>
                                    <td>000</td>
                                    <td>ภัทรพร</td>
                                    <td>แซ่ตั้น</td>
                                    <td>Phattaraporn</td>
                                    <td>Saeton</td>
                                    <td>ERP</td>
                                    <td>214</td>
                                    <td>IT</td>
                                    <td>ปกติ</td>
                                    <td>
                                      <a href="./detail.php" type="button" class="btn mb-1 btn-primary"><span class="ti-pencil-alt"></span></a>
                                    </td>
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