<!DOCTYPE html>
<html lang="th">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>ประวัติการเข้าร่วมกิจกรรม</title>
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
										<li><a href="./profile"><i class="icon-user"></i> <span>Profile</span></a></li>



										<li><a href="./login"><i class="icon-key"></i> <span>Logout</span></a></li>
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
								<li><a href="./openedActivity">ประวัติการเปิดกิจกรรม</a></li>
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
						<li class="breadcrumb-item"><a href="">ผู้ใช้</a></li>
						<li class="breadcrumb-item active"><a href="">ประวัติการเข้าร่วมกิจกรรม</a></li>
					</ol>
				</div>
			</div>
			<!-- row -->

			<div class="container-fluid">
				<div class="row">
					<div th:each="activityhistorys:${activityhistorys}"
						class="col-lg-6">
						<div class="card">
							<div class="card-body">
								<div class="card-title">
									<h3
										th:text="'ชื่อกิจกรรม : '+${activityhistorys.activity.nameActivity}" />
									<h5 style="text-align: left; float: left;"
										th:text="'รายละเอียดกิจกรรม : '+${activityhistorys.activity.detail}"></h5>
									<h6 style="text-align: right; float: right; display: inline;"
										th:text="'วันที่จัดกิจกรรม : '+${#calendars.format(activityhistorys.activity.dateActivity,'dd MMM yyyy')}">
										</h5>
								</div>

								<div class="table-responsive">
									<table class="table">
										<thead>
											<tr>
												<th>#</th>
												<th>ชื่อ - นามสกุล</th>
												<th>score</th>
												<th></th>
											</tr>
										</thead>
										<tbody th:each="activity:${listofuser}"
											th:if="${activity[0]==activityhistorys.activity.activityId}">
											<tr th:each="user,index:${activity[1]}">
												<td th:text="${index.count}" />
												<td
													th:text="${user.userinfo.firstName +' '+user.userinfo.lastName}" />
												<td th:text="${user.score}" />

												<td th:if="${user.userinfo.userId != userLog.userId}"><a
													th:href="@{./reportAccount(userId=${user.userinfo.userId})}"
													class="btn btn-outline-danger float-right"
													style="height: 35px;"><p>รายงานผู้ใช้งาน</p></a></td>

											</tr>
										</tbody>
									</table>
								</div>
								<a
									th:href="@{./estimate(activityId=${activityhistorys.activity.activityId})}"
									class="btn btn-primary">ประเมินกิจกรรม</a>
								<button type="button" class="btn btn-primary"
									data-toggle="modal"
									th:data-target="'#commentsModal-' + ${activityhistorys.activity.activityId}">
									ดูแบบประเมินกิจกรรม</button>



								<!-- Modal -->
								<div class="modal fade"
									th:id="'commentsModal-' + ${activityhistorys.activity.activityId}"
									tabindex="-1" role="dialog"
									aria-labelledby="commentsModalLabel" aria-hidden="true">
									<div class="modal-dialog modal-dialog-centered" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title" id="commentsModalLabel">แบบประเมินกิจกรรม</h5>

											</div>
											<div class="modal-body">
												<!-- Add comments here -->
												<div class="table-responsive">
													<table class="table">
														<thead>
															<tr>
																<th>#</th>
																<th>ชื่อ - นามสกุล</th>
																<th>ความคิดเห็นต่อกิจกรรม</th>

															</tr>
														</thead>
														<tbody th:each="activity:${listofuser}"
															th:if="${activity[0]==activityhistorys.activity.activityId}">
															<tr th:each="user,index:${activity[1]}">
																<td th:text="${index.count}" />
																<td
																	th:text="${user.userinfo.firstName +' '+user.userinfo.lastName}" />
																<td th:text="${user.comment}" />

															</tr>
														</tbody>
													</table>
												</div>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
													data-dismiss="modal">Close</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!-- /# column -->
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


</body>




</html>