<!-- Topbar -->
<nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow" style="height: max-content;">

    <!-- Sidebar Toggle (Topbar) -->

    <a href="#"><img src="../image/newLogoUniversity1.png" border="0"></a>
    </a>


    <!-- Topbar Navbar -->
    <ul class="navbar-nav ml-auto">


        <!-- Nav Item - User Information -->
        <li class="nav-item dropdown no-arrow">
            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown"
                aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small"><?php echo $student["fisrtNameTh"]." ",$student["lastNameTh"] ?></span>
                <img class="img-profile rounded-circle" src="../img/undraw_profile_3.svg">
            </a>
            <!-- Dropdown - User Information -->
            <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">

                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                    <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                    Logout
                </a>
            </div>
        </li>

    </ul>

</nav>
<!-- End of Topbar -->

<!-- Begin Page Content -------------------------------------------------------------------------------------------->
<div class="container-fluid">

    <!-- Content Row -------------------------------------------------------BOX----------------------->
    <div class="row">

        <!-- Earnings (Monthly) Card Example -->

        <a href="./home.php" class="col-xl-3 col-md-6 mb-4 "style="text-decoration: none;">
            <div class="t1 card border-left-primary shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-primary text-uppercase mb-1">
                                HOME</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">หน้าหลัก</div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-home fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </a>

        <!-- Earnings (Monthly) Card Example -->
        <a href="./info.php" class="col-xl-3 col-md-6 mb-4" style="text-decoration: none;">
            <div class="t1 card border-left-success shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-success text-uppercase mb-1">
                                INFORMATION</div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">ข้อมูลส่วนตัว</div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-user fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </a>

        <!-- Earnings (Monthly) Card Example -->
        <a href="./report.php" class="col-xl-3 col-md-6 mb-4" style="text-decoration: none;">
            <div class="t1 card border-left-info shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-info text-uppercase mb-1">ACADEMIC
                                RESULTS
                            </div>
                            <div class="row no-gutters align-items-center">
                                <div class="col-auto">
                                    <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800">ผลการเรียน
                                    </div>
                                </div>
                                <!--div class="col">
                                <div class="progress progress-sm mr-2">
                                    <div class="progress-bar bg-info" role="progressbar"
                                        style="width: 50%" aria-valuenow="50" aria-valuemin="0"
                                        aria-valuemax="100"></div>
                                </div>
                            </div-->
                            </div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-clipboard-list fa-2x text-gray-300"></i>
                        </div>
                    </div>
                </div>
            </div>
        </a>

        <!-- Pending Requests Card Example -->
        <a href="formGPA.php" class="col-xl-3 col-md-6 mb-4" style="text-decoration: none;">
            <div class="t1 card border-left-warning shadow h-100 py-2">
                <div class="card-body">
                    <div class="row no-gutters align-items-center">
                        <div class="col mr-2">
                            <div class="text-xs font-weight-bold text-warning text-uppercase mb-1">
                                CALCULATE ACADEMIC RESULTS </div>
                            <div class="h5 mb-0 font-weight-bold text-gray-800">คำนวณผลการเรียน</div>
                        </div>
                        <div class="col-auto">
                            <i class="fas fa-user-tie fa-2x text-warning"></i>
                        </div>
                    </div>
                </div>
            </div>
        </a>
    </div>