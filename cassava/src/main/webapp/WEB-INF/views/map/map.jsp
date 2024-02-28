<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../layouts/mainLayoutHead.jsp" />
<!-- Custom Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">

<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">
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
<link
	href="${pageContext.request.contextPath }/resources/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
	rel="stylesheet">

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>
<style type="text/css">
.inputerror {
	border: 0;
	color: red;
	background-color: transparent;
	width: 300px;
}
</style>
</head>
<body>
	<jsp:include page="../layouts/mainLayoutBodyHeader.jsp" />

	<!--**********************************
	            Content body start
	***********************************-->
	<div class="content-body">
		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h3 class="card-title">ข้อมูลการระบาด</h3>
							<div class="form-group">
								<form id="form" method="POST">
									<div class="col">
										<label for="message-text" class="col-form-label">ช่วงเวลา
											(วันที่เริ่ม - วันสิ้นสุด)</label>
										<div class="input-daterange input-group" id="date-range">
											<input type="text" class="form-control" name="startDate"
												id="startDate" required> <span
												class="input-group-addon bg-info b-0 text-white">
												จนถึงวันที </span> <input type="text" class="form-control"
												id="endDate" name="endDate" required>
										</div>
										<div class="col-lg-7 ml-auto">
											<input class="inputerror" id="date" cssClass="error" disabled />
										</div>

									</div>
									<br>
									<div class="col-lg-3">
										<label for="message-text" class="col-form-label">สิ่งที่สำรวจ</label>
										<select class="form-control" id="targetOfSurveyId"
											name="targetOfSurveies" required>
											<option value="" hidden disabled selected>เลือกสิ่งที่สำรวจ</option>
											<c:forEach items="${targetIds}" var="targetId"
												varStatus="loop">
												<option value="${targetId}">${targetNames[loop.index]}</option>
											</c:forEach>
										</select> <input class="inputerror" id="tId" cssClass="error" disabled />
									</div>
									<br>
									<div class="col-lg-3">
										<label for="message-text" class="col-form-label">จังหวัด</label>
										<select class="form-control" id="provinceId" name="province"
											required>
											<option value="" hidden disabled selected>เลือกจังหวัด</option>
											<c:forEach items="${provinces}" var="province"
												varStatus="loop">
												<option value="${province.provinceId}">${province.name}</option>
											</c:forEach>
										</select> <input class="inputerror" id="pId" cssClass="error" disabled />
									</div>
									<br>

									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a href="${pageContext.request.contextPath}/home"
												class="btn btn-light" role="button">กลับ</a>
											<button class="btn btn-primary" type="button"
												onclick="search()">ค้นหา</button>
											<!--  <a
										class="btn btn-primary" role="button" id="submit">บันทึก</a>-->
										</div>
									</div>
								</form>
							</div>
							<div class="row">
								<!-- Bar Chart -->
								<div class="col-lg-12">
									<div class="card"
										style="background-color: #F9F9F9; color: white;">
										<div class="card-body">
											<h4 class="card-title">จำนวนการสำรวจ (ครั้ง)</h4>
											<h3 class="text-center" style="color: green" id="text">ไม่พบเจอโรคในจังหวัดที่ค้นหา
												!</h3>
											<canvas id="barChart" width="500" height="200"></canvas>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--<div id="map" style="height: 400px; width: 100%;"></div>-->
		</div>
		<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
		<script
			src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
		<!--<div id="map"></div>  -->



	</div>
	<script>
		var myChart;
		/*var map, marker;
		var markersArray = [];
		var startDate = ""
		var endDate = ""
		var id = ""

		const svgMarkerBlue = {
			path : "M-1.547 12l6.563-6.609-1.406-1.406-5.156 5.203-2.063-2.109-1.406 1.406zM0 0q2.906 0 4.945 2.039t2.039 4.945q0 1.453-0.727 3.328t-1.758 3.516-2.039 3.070-1.711 2.273l-0.75 0.797q-0.281-0.328-0.75-0.867t-1.688-2.156-2.133-3.141-1.664-3.445-0.75-3.375q0-2.906 2.039-4.945t4.945-2.039z",
			fillColor : "#10FA54",
			fillOpacity : 0.6,
			strokeWeight : 0,
			rotation : 0,
			scale : 2
		};

		const svgMarkerRed = {
			path : "M-1.547 12l6.563-6.609-1.406-1.406-5.156 5.203-2.063-2.109-1.406 1.406zM0 0q2.906 0 4.945 2.039t2.039 4.945q0 1.453-0.727 3.328t-1.758 3.516-2.039 3.070-1.711 2.273l-0.75 0.797q-0.281-0.328-0.75-0.867t-1.688-2.156-2.133-3.141-1.664-3.445-0.75-3.375q0-2.906 2.039-4.945t4.945-2.039z",
			fillColor : "red",
			fillOpacity : 0.6,
			strokeWeight : 0,
			rotation : 0,
			scale : 2
		};

		function initMap() {

			var latlng = new google.maps.LatLng(-29, 25);
			var map = new google.maps.Map(document.getElementById('map'), {
				zoom : 10,
				center : new google.maps.LatLng(13.736717, 100.923186)
			});

			clearOverlays();
			marker = new google.maps.Marker({
				map : map,
				draggable : false
			});

			/*$.ajax({
				method : "GET",
				url : "${pageContext.request.contextPath }/map/getLocation",
				success : function(response) {
					//alert(response)
					for (var i = 0; i < response.body.length; i++) {
						if (response.body[i][2] == 1) {
							marker = new google.maps.Marker({
								position : new google.maps.LatLng(
										response.body[i][0],
										response.body[i][1]),
								map : map,
								icon : svgMarkerRed,
								draggable : false
							});

						} else {
							marker = new google.maps.Marker({
								position : new google.maps.LatLng(
										response.body[i][0],
										response.body[i][1]),
								map : map,
								icon : svgMarkerBlue,
								draggable : false
							});
						}
					}
					markersArray.push(marker);
				},
			});*/

		/*}

		function clearOverlays() {
			//alert(markersArray.length);
			for (var i = 0; i < markersArray.length; i++) {
				markersArray[i].setMap(null);
			}
			markersArray = [];
		}*/

		function search() {
			/*var form = document.getElementById('form');
			var select = form.querySelector('#targetOfSurveies'), options = select.selectedOptions, values = [];

			for (var i = options.length - 1; i >= 0; i--) {
				values.push(options[i].value);
			}*/

			startDate = "?startDate="
					+ document.getElementById('startDate').value;

			endDate = "&endDate=" + document.getElementById('endDate').value;

			id = "&id=" + document.getElementById('targetOfSurveyId').value;

			provinceId = "&provinceId="
					+ document.getElementById('provinceId').value;
			$('#date').val('');
			$('#tId').val('');
			$('#pId').val('');

			if (document.getElementById('startDate').value == ""
					|| document.getElementById('endDate').value == "") {

				$('#date').val('กรุณากรอกข้อมูลให้ครบ');

			}
			if (document.getElementById('targetOfSurveyId').value == "") {
				//alert("error");
				$('#tId').val('กรุณากรอกข้อมูลให้ครบ');

			}
			if (document.getElementById('provinceId').value == "") {
				//alert("error");
				$('#pId').val('กรุณากรอกข้อมูลให้ครบ');

			}
			if (document.getElementById('startDate').value == ""
					|| document.getElementById('endDate').value == ""
					|| document.getElementById('targetOfSurveyId').value == ""
					|| document.getElementById('provinceId').value == "") {
				//alert("error");

				return false;
			}
			//alert(id);
			//alert(provinceId);
			//alert(startDate);
			//alert(endDate);
			$.ajax({
				method : "POST",
				url : "${pageContext.request.contextPath }/map/count"
						+ startDate + endDate + id + provinceId,
				success : function(response) {
					//alert(response.body.length)
					if (myChart != null) {
						myChart.destroy();
					}
					document.getElementById('text').style.display = "none";
					var distictName = [];
					var survey = [];
					var surveyDetected = [];
					for (var i = 0; i < response.body.length; i++) {
						distictName[i] = response.body[i][0];
						survey[i] = response.body[i][1];
						surveyDetected[i] = response.body[i][2];
					}
					var ctx = document.getElementById("barChart");
					ctx.height = 150;
					myChart = new Chart(ctx, {
						type : 'bar',
						data : {
							labels : distictName,
							datasets : [ {
								label : "สำรวจทั้งหมด",
								data : survey,
								borderColor : "rgba(117, 113, 249, 0.9)",
								borderWidth : "0",
								backgroundColor : "rgba(117, 113, 249, 0.5)"
							}, {
								label : "สำรวจเจอโรค",
								data : surveyDetected,
								borderColor : "rgba(217, 113, 249, 0.9)",
								borderWidth : "0",
								backgroundColor : "rgba(217, 113, 249, 0.5)"
							}

							]
						},
						options : {
							responsive : true,
							scales : {
								yAxes : [ {
									ticks : {
										beginAtZero : true
									}
								} ],
								xAxes : [ {
									// Change here
									barPercentage : 0.2,
									categoryPercentage : 1
								} ]
							}
						}
					});
				},
			});
			/*alert(startDate+endDate+id);
			$.ajax({
				method : "POST",
				url : "${pageContext.request.contextPath }/map/search"
						+ startDate + endDate + id,
				success : function(response) {
					alert(response.body[1][2] + " " + response.body[0][2]);
					var latlng = new google.maps.LatLng(-29, 25);
					var map = new google.maps.Map(document
							.getElementById('map'), {
						zoom : 10,
						center : new google.maps.LatLng(13.736717, 100.923186)
					});

					clearOverlays();
					marker = new google.maps.Marker({
						map : map,
						draggable : false
					});
					for (var i = 0; i < response.body.length; i++) {
						//alert(response.body[i][2] + " " + response.body[i][0]);
						if (response.body[i][2] == 1) {
							marker = new google.maps.Marker({
								position : new google.maps.LatLng(
										response.body[i][0],
										response.body[i][1]),
								map : map,
								icon : svgMarkerRed,
								draggable : false
							});

						} else {
							marker = new google.maps.Marker({
								position : new google.maps.LatLng(
										response.body[i][0],
										response.body[i][1]),
								map : map,
								icon : svgMarkerBlue,
								draggable : false
							});
						}
					}
					markersArray.push(marker);
				},
			});*/
		}
	</script>
	<script async defer
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBsJbltBSYWw4DC5-QzEONPgYhWlOa_78U&callback=initMap">
		
	</script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.js">
		
	</script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/moment/moment.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js">
		
	</script>
	<!-- Clock Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/clockpicker/dist/jquery-clockpicker.min.js">
		
	</script>
	<!-- Color Picker Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/jquery-asColorPicker-master/libs/jquery-asColor.js">
		
	</script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/jquery-asColorPicker-master/libs/jquery-asGradient.js">
		
	</script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/jquery-asColorPicker-master/dist/jquery-asColorPicker.min.js">
		
	</script>
	<!-- Date Picker Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.js'">
		
	</script>
	<!-- Date range Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/timepicker/bootstrap-timepicker.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.js">
		
	</script>
	<script
		src="${pageContext.request.contextPath }/resources/js/plugins-init/form-pickers-init.js"></script>

	</script>
</body>
</html>