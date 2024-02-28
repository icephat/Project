<!DOCTYPE html>
<html lang="th">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>ข้อมูลผู้ใช้</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="images/favicon.png">
    <!-- Pignose Calender -->
    <link href="./plugins/pg-calendar/css/pignose.calendar.min.css" rel="stylesheet">
    <!-- Chartist -->
    <link rel="stylesheet" href="./plugins/chartist/css/chartist.min.css">
    <link rel="stylesheet" href="./plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css">
    <!-- Custom Stylesheet -->
    <link href="css/style.css" rel="stylesheet">

    <style type="text/css">
        /* Chart.js */
        @-webkit-keyframes chartjs-render-animation {
            from {
                opacity: 0.99
            }

            to {
                opacity: 1
            }
        }

        @keyframes chartjs-render-animation {
            from {
                opacity: 0.99
            }

            to {
                opacity: 1
            }
        }

        .chartjs-render-monitor {
            -webkit-animation: chartjs-render-animation 0.001s;
            animation: chartjs-render-animation 0.001s;
        }
    </style>
    <style class="datamaps-style-block">
        .datamap path.datamaps-graticule {
            fill: none;
            stroke: #777;
            stroke-width: 0.5px;
            stroke-opacity: .5;
            pointer-events: none;
        }

        .datamap .labels {
            pointer-events: none;
        }

        .datamap path:not(.datamaps-arc),
        .datamap circle,
        .datamap line {
            stroke: #FFFFFF;
            vector-effect: non-scaling-stroke;
            stroke-width: 1px;
        }

        .datamaps-legend dt,
        .datamaps-legend dd {
            float: left;
            margin: 0 3px 0 0;
        }

        .datamaps-legend dd {
            width: 20px;
            margin-right: 6px;
            border-radius: 3px;
        }

        .datamaps-legend {
            padding-bottom: 20px;
            z-index: 1001;
            position: absolute;
            left: 4px;
            font-size: 12px;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        }

        .datamaps-hoverover {
            display: none;
            font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
        }

        .hoverinfo {
            padding: 4px;
            border-radius: 1px;
            background-color: #FFF;
            box-shadow: 1px 1px 5px #CCC;
            font-size: 12px;
            border: 1px solid #CCC;
        }

        .hoverinfo hr {
            border: 1px dotted #CCC;
        }
    </style>

</head>

<body>

    <div id="main-wrapper" class="show">

        <!--**********************************
            Nav header start
        ***********************************-->
        <div class="nav-header" style="background-color: #afd7f6;">
            <div class="brand-logo">
                <a href="home.html">

                    <!--**********************************
                    <b class="logo-abbr"><img src="https://kurdis.agri.kps.ku.ac.th/cassava/images/logo.png" alt=""> </b>
                    <span class="logo-compact"><img src="https://kurdis.agri.kps.ku.ac.th/cassava/images/logo-compact.png" alt=""></span>
                    <span class="brand-title">
                        <img src="https://kurdis.agri.kps.ku.ac.th/cassava/images/logo-text.png" alt="">
                    </span>
***********************************-->

                </a>
            </div>
        </div>
        <!--**********************************
            Nav header end
        ***********************************-->

        <!--**********************************
            Header start
        ***********************************-->
        <div class="header">
            <div class="header-content clearfix">
                <div class="nav-control">
                    <div class="hamburger">
                        <span class="toggle-icon"><i class="icon-menu"></i></span>
                    </div>
                </div>
                
                <div class="header-left">
					<div class="input-group icons">
						<div class="input-group-prepend">
							<span
								class="input-group-text bg-transparent border-0 pr-2 pr-sm-3"
								id="basic-addon1"><i class="mdi mdi-magnify"></i></span>
						</div>
						<input type="search" class="form-control"
							placeholder="Search Dashboard" aria-label="Search Dashboard">
						<div class="drop-down animated flipInX d-md-none">
							<form action="#">
								<input type="text" class="form-control" placeholder="Search">
							</form>
						</div>
					</div>
				</div>
					<div class="header-right">
					<ul class="clearfix">
						
						<li class="icons dropdown">
							<div class="user-img c-pointer position-relative"
								data-toggle="dropdown">
								<span class="activity active"></span> <img
									src="https://kurdis.agri.kps.ku.ac.th/cassava/images/user/1.png"
									height="40" width="40" alt="">
							</div>
							<div
								class="drop-down dropdown-profile animated fadeIn dropdown-menu">
								<div class="dropdown-content-body">
									<ul>
										<li><a href="./profile"><i class="icon-user"></i>
												<span>Profile</span></a></li>
										

										
										<li><a
											href="./login"><i
												class="icon-key"></i> <span>Logout</span></a></li>
									</ul>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!--**********************************
            Header end ti-comment-alt
        ***********************************-->

        <!--**********************************
            Sidebar start
        ***********************************-->
        <div class="nk-sidebar">
            <div class="slimScrollDiv" style="position: relative; overflow: hidden; width: auto; height: 100%;">
                <div class="nk-nav-scroll" style="overflow: hidden; width: auto; height: 100%;">
                    <ul class="metismenu" id="menu">
						<li><a class="has-arrow" href="javascript:void()"
							aria-expanded="false"> <i class="icon-user menu-icon"></i><span
								class="nav-text">ผู้ใช้ระบบ</span>
						</a>
							<ul aria-expanded="false" class="collapse">
								<li><a href="./profile">ข้อมูลผู้ใช้</a></li>
								<li><a href="./activityopen">กิจกรรมที่คุณกำลังเปิด</a></li>

								<li><a href="./activityhistory">ประวัติการเข้าร่วมกิจกรรม</a></li>
								<li><a href="./openedActivity">ประวัติการเปิดกิจกรรม</a>
								</li>
								<li><a href="./requesthistory">ประวัติคำขอเข้าร่วม</a></li>

							</ul></li>

						<li><a class="has-arrow" href="javascript:void()"
							aria-expanded="false"> <i class="icon-notebook menu-icon"></i><span
								class="nav-text">กิจกรรม</span>
						</a>
							<ul aria-expanded="false" class="collapse">
								<li><a href="./activity">กิจกรรมที่เปิด</a></li>

								<li><a href="./openactivity">เปิดกิจกรรม</a></li>

							</ul></li>

					</ul>
                </div>
                <div class="slimScrollBar"
                    style="background: transparent; width: 5px; position: absolute; top: 0px; opacity: 0.4; display: block; border-radius: 7px; z-index: 99; right: 1px; height: 30px;">
                </div>
                <div class="slimScrollRail"
                    style="width: 5px; height: 100%; position: absolute; top: 0px; display: none; border-radius: 7px; background: rgb(51, 51, 51); opacity: 0.2; z-index: 90; right: 1px;">
                </div>
            </div>
        </div>
        <!--**********************************
            Sidebar end
        ***********************************-->

        <!--**********************************
            Content body start
        ***********************************-->
        <div class="content-body" style="min-height: 876px;">

            <div class="row page-titles mx-0">
                <div class="col p-md-0">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="">ผู้ใช้ระบบ</a></li>
                        <li class="breadcrumb-item active"><a href="">ข้อมูลผู้ใช้</a></li>
                    </ol>
                </div>
            </div>
            <!-- row -->

            <div class="container-fluid">
                <div class="row">
                    <div class="col-lg-12 col-xxl-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="media align-items-center mb-4">
                                    <img class="mr-3" src="images/avatar/11.png" width="80" height="80" alt="">
                                    <div class="media-body">
                                        <h3 class="mb-0" th:text="${user.username}"></h3>
                                        <p class="text-muted mb-0" th:text="${user.firstName}"></p>
                                    </div>
                                </div>

                                <div class="row mb-5">
                                    <div class="col">
                                        <div class="card card-profile text-center">
                                            <span class="mb-1 text-primary"><i class="icon-people"></i></span>
                                            <h3 class="mb-0" th:text="${join}"></h3>
                                            <p class="text-muted px-4">จำนวนเข้าร่วมกิจกรรม</p>
                                        </div>
                                    </div>
                                    <div class="col">
                                        <div class="card card-profile text-center">
                                            <span class="mb-1 text-warning"><i class="icon-user-follow"></i></span>
                                            <h3 class="mb-0" th:text="${act}"></h3>
                                            <p class="text-muted">จำนวนกิจกรรมที่เปิด</p>
                                        </div>
                                    </div>
                                    <div class="col-12 text-center">
                                        <a th:href="@{./activityopen}" type="button" class="btn btn-success px-5">คำขอเข้าร่วมกิจกรรม</a>
                                        <a th:href="@{./activity}" type="button" class="btn btn-danger px-5">กิจกรรมที่กำลังเปิด</a>
                                    </div>
                                </div>

                                <h4>About Me</h4>
                                <p class="text-muted" th:text="${user.skills}"></p>
                                <ul class="card-profile__info">
                                    <li class="mb-1"><strong class="text-dark mr-4">Address</strong>
                                        <span th:text="${user.address}"></span></li>
                                    
                                </ul>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <!-- #/ container -->
    </div>
    <!-- #/ container -->
    </div>
    <!--**********************************
            Content body end
        ***********************************-->


    <!--**********************************
            Footer start
        ***********************************-->
    <div class="footer">
        <!--**********************************
            <div class="copyright">
                <p>Copyright &copy; Designed & Developed by <a href="https://themeforest.net/user/quixlab">Quixlab</a> 2018</p>
            </div>
			***********************************-->
    </div>
    <!--**********************************
            Footer end
        ***********************************-->
    </div>


    <script type="text/javascript"
        src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sidebar/0.2.2/js/sidebar.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/bootstrap.bundle.min.js"></script>
    <script src="https://kurdis.agri.kps.ku.ac.th/cassava/plugins/common/common.min.js"></script>
    <script src="https://kurdis.agri.kps.ku.ac.th/cassava/js/custom.min.js"></script>
    <script src="https://kurdis.agri.kps.ku.ac.th/cassava/js/gleek.js"></script>
    <script src="https://kurdis.agri.kps.ku.ac.th/cassava/js/styleSwitcher.js"></script>


</body>




</html>