<!DOCTYPE html>
<html lang="th">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Quixlab - Bootstrap Admin Dashboard Template by
	Themefisher.com</title>
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

.box-widget {
	border: none;
	position: relative;
}

.box {
	position: relative;
	border-radius: 3px;
	background: #ffffff;
	border-top: 3px solid #d2d6de;
	margin-bottom: 20px;
	width: 100%;
	box-shadow: 0 1px 1px rgba(0, 0, 0, 0.1);
}

.box-header.with-border {
	border-bottom: 1px solid #f4f4f4;
}

.box-header {
	color: #444;
	display: block;
	padding: 10px;
	position: relative;
}

.user-block img {
	width: 40px;
	height: 40px;
	float: left;
}

.user-block .username {
	font-size: 16px;
	font-weight: 600;
}

.user-block .description {
	color: #999;
	font-size: 13px;
}

.user-block .username, .user-block .description, .user-block .comment {
	display: block;
	margin-left: 50px;
}

.box-header>.box-tools {
	position: absolute;
	right: 10px;
	top: 5px;
}

.btn-box-tool {
	padding: 5px;
	font-size: 12px;
	background: transparent;
	color: #97a0b3;
}

.box-body {
	border-top-left-radius: 0;
	border-top-right-radius: 0;
	border-bottom-right-radius: 3px;
	border-bottom-left-radius: 3px;
	padding: 10px;
}

.pad {
	padding: 10px;
}

.box .btn-default {
	background-color: #f4f4f4;
	color: #444;
	border-color: #ddd;
}

.box-comments {
	background: #f7f7f7 !important;
}

.box-footer {
	border-top-left-radius: 0;
	border-top-right-radius: 0;
	border-bottom-right-radius: 3px;
	border-bottom-left-radius: 3px;
	border-top: 1px solid #f4f4f4;
	padding: 10px;
	background-color: #fff;
}

.box-comments .box-comment:first-of-type {
	padding-top: 0;
}

.box-comments .box-comment {
	padding: 8px 0;
	border-bottom: 1px solid #eee;
}

.img-sm, .box-comments .box-comment img, .user-block.user-block-sm img {
	width: 30px !important;
	height: 30px !important;
}

.img-sm, .img-md, .img-lg, .box-comments .box-comment img, .user-block.user-block-sm img
	{
	float: left;
}

.box-comments .comment-text {
	margin-left: 0px;
	color: #555;
}

.box-comments .username {
	color: #444;
	display: block;
	font-weight: 600;
}

.box-comments .text-muted {
	font-weight: 400;
	font-size: 12px;
}

.img-sm+.img-push {
	margin-left: 40px;
}

.box .form-control {
	border-radius: 0;
	box-shadow: none;
	border-color: #d2d6de;
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
		<div class="content-body">

			<link
				href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"
				rel="stylesheet">
			<div class="container bootstrap snippets bootdey">
				<div class="col-md-11" style="margin-left: 70px; margin-top: 25px;">

					<div class="card" style="padding: 30px;">
						<div class="box-header with-border">
							<div class="user-block">
								<img class="img-circle"
									src="https://bootdey.com/img/Content/avatar/avatar1.png"
									alt="User Image"> <span class="username"><a
									href="#" th:text=${activity.user.username}></a></span>

								<p style="color: gray; margin-left: 50px;"
									th:text="${'วันที่เปิดกิจกรรม : ' + activity.dateOpen}"></p>






							</div>
							<div class="row" th:each="image : ${activity.imageActivities}">
								<img class="img-fluid"
									th:src="@{${'images/uploads/'+ image.imagePath}}"
									alt="First slide">
							</div>
						</div>

						<div class="box-body" style="display: block;">
							<div class="card">
								<div class="card-body">
									<div class="row">
										<p th:text="${'ชื่อกิจกรรม : ' + activity.nameActivity}"
											class="fw-bold"></p>
									</div>
									<div class="row">
										<p class="fw-bold"
											th:text="${'สถานที่จัดกิจกรรม : '+activity.place}"></p>
									</div>

									<div class="row">
										<p
											th:text="${'วันที่เริ่มกิจกรรม : ' + activity.dateActivity}"
											class="fw-bold"></p>
									</div>
									<div class="row">
										<p th:text="${'รายละเอียดกิจกรรม : ' + activity.detail}"
											class="fw-bold"></p>
									</div>
									<div class="row" style="justify-content: 'center';">
										<p class="fw-bold">ประเภทของกิจกรรม :</p>
										<div th:each="type : ${activity.typeOfActivities}">
											<p class="btn btn-outline-info float-right"
												style="margin-right: 10px;"
												th:text=${type.typeActivity.typeName}></p>
										</div>
									</div>
									<div class="row">
										<p class="fw-bold">เงื่อนไขการเข้าร่วมกิจกรรม :</p>
										<div th:each="condition : ${activity.conditionOfActivities}">
											<p class="btn btn-outline-success float-right"
												style="margin-right: 10px;"
												th:text=${condition.conditionActivity.conditionDetail}></p>
										</div>
									</div>


								</div>
							</div>

							<span
								th:text="${'จำนวนคนที่สามารถเข้าร่วม : ' + joinAmount + '/'+activity.numberPeople + ' คน' }"
								class="text-muted pull-right"></span>


						</div>
						<div style="margin-bottom: 10px;">
							<div class="bootstrap-modal">
								<!-- Button trigger modal -->
								<button type="button" class="btn btn-primary"
									th:if="${activity.user.userId != user.userId}"
									data-toggle="modal" data-target="#basicModal">ส่งคำขอเข้าร่วมกิจกรรม</button>
								<!-- Modal -->
								<div class="modal fade" id="basicModal" style="display: none;"
									aria-hidden="true">
									<div class="modal-dialog modal-lg" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title">ส่งคำขอเข้าร่วมกิจกรรม</h5>
												<button type="button" class="close" data-dismiss="modal">
													<span></span>
												</button>
											</div>
											<div class="modal-body">

												<p style="color: gray; margin-left: 50px;"
													th:text="${activity.activityId}"></p>
												<form action="joinactivity" method="POST"
													modelAttribute="joinActivityDTO"
													enctype="multipart/form-data">
													<div class="form-group row">

														<label class="col-lg-4 col-form-label" for="detail">เงื่อนไขการเข้าร่วมกิจกรรม
															<span class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<select data-live-search="true" class="selectpicker"
																name="conditionActivity">
																<option
																	th:each="condition : ${activity.conditionOfActivities}"
																	th:value="${condition.conditionActivity.conditionId}"
																	th:text="${condition.conditionActivity.conditionDetail}">
															</select>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-4 col-form-label" for="nameActivity">รายละเอียดเพิ่มเติม
															<span class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<input type="text" class="form-control"
																id="detailRequest" name="detailRequest"
																placeholder="ระบุรายละเอียดเพิ่มเติม" required>
														</div>
													</div>
													<input type="hidden" id="activity" name="activity"
														th:value="${activity.activityId}" />
													<div class="modal-footer">
														<button type="button" class="btn btn-secondary"
															data-dismiss="modal">ปิด</button>
														<button th:if="${joinAmount != activity.numberPeople}"
															type="submit" class="btn btn-primary">ส่งคำขอ</button>
														<button th:if="${joinAmount == activity.numberPeople}"
															type="button" class="btn btn-danger" data-dismiss="modal">กิจกรรมนี้มีผู้เข้าร่วมครบแล้ว</button>
													</div>
												</form>
											</div>

										</div>
									</div>
								</div>
							</div>
						</div>
						<div class="box-footer box-comments" style="display: block;">
							<div th:each="comment : ${comments}" class="box-comment">
								<div class="comment-text">
									<span class="username"
										th:text="${comment.user.firstName + ' ' + comment.user.lastName}">
									</span>
									<p th:text="${comment.detail}"></p>
								</div>
								<a th:href="@{./commentreport(commentId=${comment.commentId})}"
									class="btn btn-outline-danger float-right"
									style="height: 35px;"><p>รายงานความคิดเห็น</p></a>
							</div>
						</div>
						<div class="box-footer" style="display: block;">
							<form action="postcomment" method="POST"
								modelAttribute="commentDTO" enctype="multipart/form-data">
								<div class="img-push">
									<span class="username"
										th:text="${user.firstName + ' ' + user.lastName}"
										style="font-weight: bold; margin-right: 5px;"> </span> <input
										type="text" class="form-control"
										placeholder="Press enter to post comment" id="detail"
										name="detail" required onblur="this.value=this.value.trim()"> <input type="hidden" id="activity"
										name="activity" th:value="${activity.activityId}" />
									<button type="submit" class="btn btn-primary">Send</button>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>

		</div>
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
</body>




</html>