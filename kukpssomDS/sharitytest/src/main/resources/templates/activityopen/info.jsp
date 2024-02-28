<!-- <!DOCTYPE html>
<html lang="th">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Quixlab - Bootstrap Admin Dashboard Template by Themefisher.com</title>
    <!-- Favicon icon -->
	<link rel="icon" type="image/png" sizes="16x16"
	href="images/favicon.png">
<!-- Pignose Calender -->
<link href="./plugins/pg-calendar/css/pignose.calendar.min.css"
	rel="stylesheet">
<!-- Chartist -->
<link rel="stylesheet" href="./plugins/chartist/css/chartist.min.css">
<link rel="stylesheet"
	href="./plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css">
<!-- Custom Stylesheet -->
<link href="css/style.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">

<style type="text/css">
/* Chart.js */
@
-webkit-keyframes chartjs-render-animation {from { opacity:0.99
	
}

to {
	opacity: 1
}

}
@
keyframes chartjs-render-animation {from { opacity:0.99
	
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

.datamap path:not(.datamaps-arc), .datamap circle, .datamap line {
	stroke: #FFFFFF;
	vector-effect: non-scaling-stroke;
	stroke-width: 1px;
}

.datamaps-legend dt, .datamaps-legend dd {
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
				<a href="home.html"> <!--**********************************
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
			<div class="slimScrollDiv"
				style="position: relative; overflow: hidden; width: auto; height: 100%;">
				<div class="nk-nav-scroll"
					style="overflow: hidden; width: auto; height: 100%;">
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
						<li class="breadcrumb-item"><a href="">กิจกรรม</a></li>
						<li class="breadcrumb-item active"><a href="">เปิดกิจกรรม</a></li>
					</ol>
				</div>
			</div>
			<!-- row -->

			<div class="container-fluid">
				<div class="row justify-content-center">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-body">
								<a
									th:href="@{./request(activityId=${joinActivity.activity.activityId})}"
									type="button" class="btn btn-link"
									style="text-align: left; margin-bottom: 20px; margin-left: -20px; margin-top: -10px;">
									<span class="bi bi-arrow-return-left"></span>
								</a>
								<div class="form-validation">
									<form class="form-valide" action="#" method="post"
										novalidate="novalidate">
										<div class="form-group row">

											<label class="col-lg-4 col-form-label" for="firstName">ชื่อ<span
												class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input style="background-color: white" type="text"
													class="form-control" id="firstName" name="firstName"
													th:placeholder="${user.firstName}" disabled />
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="val-suggestions">นามสกุล
												<span class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input style="background-color: white" type="text"
													class="form-control" id="lastName" name="lastName"
													th:placeholder="${user.lastName}" disabled />
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="val-username">ที่อยู่
												<span class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input style="background-color: white" type="text"
													class="form-control" id="address" name="address"
													th:placeholder="${user.address}" disabled />
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="val-currency">ความสามารถ
												<span class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input style="background-color: white" type="text"
													class="form-control" id="skills" name="skills"
													th:placeholder="${user.skills}" disabled />
											</div>
										</div>


										<div class="form-group row">
											<div class="col-lg-8 ml-auto">

												<a
													th:href="@{./approve(joinActivityId=${joinActivity.joinActivityId})}"
													type="button" class="btn mb-1 btn-success"
													style="height: 38px; margin-top: 3px;">อนุมัติ
												</a> <a
													th:href="@{./remove(joinActivityId=${joinActivity.joinActivityId})}"
													class="btn btn-danger" role="button" style="height: 38px;"><svg
														xmlns="http://www.w3.org/2000/svg" width="16" height="19"
														fill="currentColor" class="bi bi-box-arrow-right"
														viewBox="0 0 16 16">
                                                                            <path
															fill-rule="evenodd"
															d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z" />
                                                                            <path
															fill-rule="evenodd"
															d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z" />
                                                                          </svg></a>

											</div>
										</div>

									</form>
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
	<script
		src="https://kurdis.agri.kps.ku.ac.th/cassava/plugins/common/common.min.js"></script>
	<script src="https://kurdis.agri.kps.ku.ac.th/cassava/js/custom.min.js"></script>
	<script src="https://kurdis.agri.kps.ku.ac.th/cassava/js/gleek.js"></script>
	<script
		src="https://kurdis.agri.kps.ku.ac.th/cassava/js/styleSwitcher.js"></script>

	<!-- Date Picker Plugin JavaScript -->
	<script
		src="./plugins/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
	<script src="./plugins/timepicker/bootstrap-timepicker.min.js"></script>
	<script src="./plugins/bootstrap-daterangepicker/daterangepicker.js"></script>

</body>




</html>
