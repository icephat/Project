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


		<input type="hidden" id="labels" value="${labels}"> <input
			type="hidden" id="approves" value="${approves}"> <input
			type="hidden" id="rejects" value="${rejects}"> <input
			type="hidden" id="maxSize" value="${maxSize}">
		<div class="container-fluid">
			<div class="row">
				<div class="card">
					<div class="card-body">
						<h5 class="card-title">รายงานการตรวจสอบ -> สถิติการตรวจสอบภาพทั้งหมด</h5>
						<p align="right">
							<a href="${pageContext.request.contextPath }/export/imagesumary"
								class="btn btn-light" role="button">กลับ</a>
						</p>
						<div class="col">
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
