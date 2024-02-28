<!DOCTYPE html>
<html lang="th">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>การจัดการผู้ดูแลระบบ</title>
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
		<div class="nav-header" style="background-color: black;">
			<div class="brand-logo">
				<p
					style="font-size: 30px; color: whitesmoke; text-align: center; margin-top: 16px;">Admin</p>

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
                    <li>
                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-user menu-icon"></i><span class="nav-text">ผู้ดูแลระบบ</span>
                        </a>
                        <ul aria-expanded="false" class="collapse">
                            <li><a href="./admin">ผู้ดูแล</a>
                            </li>
                           

                        </ul>
                    </li>

                    <li>
                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-notebook menu-icon"></i><span class="nav-text">การจัดการผู้ใช้</span>
                        </a>
                        <ul aria-expanded="false" class="collapse">
                            <li><a href="./reportaccount">รายงานบัญชีผู้ใช้</a>
                            </li>

                            <li><a href="./reportcomment">รายงานการแสดงความคิดเห็น</a>
                            </li>

                        </ul>
                    </li>

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

		<!-- row -->
		<div class="content-body" style="min-height: 876px;">
			<div class="container-fluid">
				<div class="row justify-content-center">
					<div class="col-lg-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">ผู้ดูแลระบบ > เพิ่มผู้ดูแลระบบ</h4>
								<div class="form-validation">
								<form method="POST" modelAttribute="user"
									enctype="multipart/form-data" action="regadmin">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="username">บัญชีผู้ใช้
											<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="username"
												name="username" placeholder="">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="password">รหัสผ่าน
											<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="password" class="form-control" id="password"
												name="password" placeholder="">

										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="firstName">ชื่อ
											<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="firstName"
												name="firstName" placeholder="">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="lastName">นามสกุล
											<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="lastName"
												name="lastName" placeholder="">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="address">ที่อยู่
											<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<textarea class="form-control" id="address" name="address"
												rows="5" placeholder=""></textarea>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="skills">เกี่ยวกับตัวเอง
											<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<textarea class="form-control" id="skills" name="skills"
												rows="5" placeholder=""></textarea>
										</div>
									</div>

									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a href="./admin" type="submit" class="btn btn-outline-light">กลับ</a>
											<button  type="submit" class="btn btn-primary">ลงทะเบียน</button>
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

		<!--**********************************
            Content body end
        ***********************************-->

		<script type="text/javascript"
			src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sidebar/0.2.2/js/sidebar.js"></script>
		<script src="js/bootstrap.min.js"></script>
		<script src="js/bootstrap.bundle.min.js"></script>
		<script
			src="https://kurdis.agri.kps.ku.ac.th/cassava/plugins/common/common.min.js"></script>
		<script
			src="https://kurdis.agri.kps.ku.ac.th/cassava/js/custom.min.js"></script>
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