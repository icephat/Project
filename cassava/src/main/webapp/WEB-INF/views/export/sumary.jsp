<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../layouts/mainLayoutHead.jsp" />

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>


<!-- Custom Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">
<link
	href="${pageContext.request.contextPath }/resources/plugins/tables/css/datatable/dataTables.bootstrap4.min.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath }/resources/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
	rel="stylesheet">
<!-- Page plugins css -->
<link
	href="${pageContext.request.contextPath }/resources/plugins/clockpicker/dist/jquery-clockpicker.min.css"
	rel="stylesheet">
<!-- Color picker plugins css 
    <link href="./plugins/jquery-asColorPicker-master/css/asColorPicker.css" rel="stylesheet">-->
<!-- Date picker plugins css -->
<link
	href="${pageContext.request.contextPath }/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.css"
	rel="stylesheet">
<!-- Daterange picker plugins css -->
<link
	href="${pageContext.request.contextPath }/resources/plugins/timepicker/bootstrap-timepicker.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">

<script type="text/javascript" src="multiple-dropdown.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
</head>
<body>
	<jsp:include page="../layouts/mainLayoutBodyHeader.jsp" />

	<!--**********************************
            Content body start
        ***********************************-->
	<div class="content-body">
		<div class="container-fluid">



			<input type="hidden" id="labels" value="${labels}"> <input
				type="hidden" id="approves" value="${approves}"> <input
				type="hidden" id="rejects" value="${rejects}"> <input
				type="hidden" id="maxSize" value="${maxSize}"> <input
				type="hidden" id="diseasesDairy"
				value="${diseaseSumaryDTODairy.diseases}"> <input
				type="hidden" id="approvesDairy"
				value="${diseaseSumaryDTODairy.approves}"> <input
				type="hidden" id="rejectsDairy"
				value="${diseaseSumaryDTODairy.rejects}"> <input
				type="hidden" id="diseasesMonthly"
				value="${diseaseSumaryDTOMonthly.diseases}"> <input
				type="hidden" id="approvesMonthly"
				value="${diseaseSumaryDTOMonthly.approves}"> <input
				type="hidden" id="rejectsMonthly"
				value="${diseaseSumaryDTOMonthly.rejects}">

			<div class="row">
				<div class="card">



					<div class="card-body">

						
						<h4>สถิติการตรวจสอบแบบต่างๆ</h4>
						<div class="row" align="right">

							<p align="right">
							<form
								action="${pageContext.request.contextPath}/export/imagesumary/workstatistics"
								method="post">
								<input type="hidden" value="${month}" name="months"> <input
									type="hidden" value="${year}" name="year"> <input
									type="submit" value="สถิติการทำงานของผู้ตรวจสอบ"
									class="btn btn-primary" /> <a class="btn btn-primary"
									href="${pageContext.request.contextPath}/export/imagesumary/all"
									role="button">สถิติการตรวจสอบภาพทั้งหมด</a>
							</form>


							</p>
						</div>

						<div class="card-body">
							<h4 class="card-title">รูปแบบการแสดงของกราฟ</h4>
							<p class="text-muted">
								<code></code>
							</p>
							<div id="accordion-three" class="accordion">
								<div class="card">
									<div class="card-header">
										<h5 class="mb-0" data-toggle="collapse"
											data-target="#collapseOne4" aria-expanded="true"
											aria-controls="collapseOne4">
											<i class="fa" aria-hidden="true"></i> สถิติการทำงานแบบรายวัน
										</h5>
									</div>
									<div id="collapseOne4" class="collapse"
										data-parent="#accordion-three" style="">
										<div class="card-body">

											<div class="form-group row">
												<div class="col-lg-6">
													<div class="form-validation">
														<form class="form-valide"
															action="${pageContext.request.contextPath}/export/imagesumary/daily"
															method="post">

															<div class="form-validation">

																<div class="form-group row">
																	<label class="col-lg-4 col-form-label"
																		for="val-username">เลือกเดือนที่ต้องการ<span
																		class="text-danger">*</span>
																	</label>
																	<div class="col-lg-6">

																		<select class="selectpicker" name="month">
																			<option value="1">มกราคม</option>
																			<option value="2">กุมภาพันธ์</option>
																			<option value="3">มีนาคม</option>
																			<option value="4">เมษายน</option>
																			<option value="5">พฤษพาคม</option>
																			<option value="6">มิถุนายน</option>
																			<option value="7">กรกฏาคม</option>
																			<option value="8">สิงหาคม</option>
																			<option value="9">กันยายน</option>
																			<option value="10">ตุลาคม</option>
																			<option value="11">พฤศจิกายน</option>
																			<option value="12">ธันวาคม</option>

																		</select>
																	</div>
																</div>
																<div class="form-group row">
																	<label class="col-lg-4 col-form-label"
																		for="val-username">เลือกปีที่ต้องการ<span
																		class="text-danger">*</span>
																	</label>
																	<div class="col-lg-6">
																		<select class="selectpicker" name="year">
																			<c:forEach items="${years}" var="year">
																				<option value="${year}">พ.ศ. ${year+543}</option>
																			</c:forEach>
																		</select>
																	</div>
																</div>

																<div class="form-group row">
																	<div class="col-lg-8 ml-auto">
																		<input type="submit" id='submit'
																			class="btn btn-primary" role="button" value="ยืนยัน">
																	</div>
																</div>
															</div>
														</form>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card">
									<div class="card-header">
										<h5 class="mb-0 collapsed" data-toggle="collapse"
											data-target="#collapseTwo5" aria-expanded="false"
											aria-controls="collapseTwo5">
											<i class="fa" aria-hidden="true"></i>
											สถิติการทำงานแบบรายเดือน
										</h5>
									</div>
									<div id="collapseTwo5" class="collapse"
										data-parent="#accordion-three" style="">
										<div class="card-body">

											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="val-username">เลือกปีที่ต้องการ<span
													class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<form class="form-valide"
														action="${pageContext.request.contextPath}/export/imagesumary/monthly"
														method="post">
														<select class="selectpicker" name="year">
															<c:forEach items="${years}" var="year">
																<option value="${year}">พ.ศ. ${year+543}</option>
															</c:forEach>
														</select> <input type="submit" id='submit' class="btn btn-primary"
															role="button" value="ยืนยัน">
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="card">
									<div class="card-header">
										<h5 class="mb-0 collapsed" data-toggle="collapse"
											data-target="#collapseThree6" aria-expanded="false"
											aria-controls="collapseThree6">
											<i class="fa" aria-hidden="true"></i> สถิติการทำงานแบบรายปี
										</h5>
									</div>
									<div id="collapseThree6" class="collapse"
										data-parent="#accordion-three">
										<div class="card-body">
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="val-username">เลือกช่วงปีที่ต้องการ<span
													class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<form class="form-valide"
														action="${pageContext.request.contextPath}/export/imagesumary/yearly"
														method="post">
														<select class="selectpicker" name="year">
															<option value="2017">ช่วงปี 2560 - 2569</option>
														</select> <input type="submit" id='submit' class="btn btn-primary"
															role="button" value="ยืนยัน">
													</form>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<br>
							<h5 class="card-title">${title}</h5>
							<canvas id="barChart" class="chartjs-render-monitor"></canvas>
						</div>
					</div>
				</div>
			</div>


		</div>
	</div>
	<!--**********************************
            Content body end
        ***********************************-->



	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />


	<script
		src="${pageContext.request.contextPath}/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>

	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/moment/moment.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"></script>

	<!-- DataTablejs -->
	<script
		src="https://kurdis.agri.kps.ku.ac.th/cassava/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="https://kurdis.agri.kps.ku.ac.th/cassava/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="https://kurdis.agri.kps.ku.ac.th/cassava/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>

	<!-- Chart -->

	<script>
		var labels = document.getElementById("labels").value;
		var approves = document.getElementById("approves").value;
		var rejects = document.getElementById("rejects").value;

		var maxSize = parseInt(document.getElementById("maxSize").value);

		labels = labels.replace("[", "")
		labels = labels.replace("]", "")

		approves = approves.replace("[", "")
		approves = approves.replace("]", "")

		rejects = rejects.replace("[", "")
		rejects = rejects.replace("]", "")

		var labelsName = labels.split(",");

		var approveValues = approves.split(",");

		var rejectValues = rejects.split(",");

		//bar chart
		var ctx = document.getElementById("barChart");
		ctx.height = 150;
		var myChart = new Chart(ctx, {
			type : 'horizontalBar',
			data : {
				labels : labelsName,
				datasets : [ {
					label : "รูปที่ถูกอนุมัติ",
					data : approveValues,
					borderColor : "#87FEBC",
					borderWidth : "0",
					backgroundColor : "#87FEBC"
				}, {
					label : "รูปที่ไม่ถูกอนุมัติ",
					data : rejectValues,
					borderColor : "#FF7B7B",
					borderWidth : "0",
					backgroundColor : "#FF7B7B"
				}

				]
			},
			options : {
				scales : {
					xAxes : [ {
						ticks : {
							min : 0,
							max : maxSize
						}
					} ]
				}
			}
		});
	</script>
</body>
</html>
