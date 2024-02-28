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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<script type="text/javascript" src="multiple-dropdown.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.all.min.js"></script>
<link rel='stylesheet'
	href='https://cdn.jsdelivr.net/npm/sweetalert2@10.10.1/dist/sweetalert2.min.css'>



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
							<h4 class="card-title">แจ้งเตือนการระบาด -> ยืนยันการส่ง</h4>

							<div class="form-validation">
								<form class="form-valide" method="POST"
									action="${pageContext.request.contextPath}/message/send"
									modelAttribute="messageDTO" enctype="multipart/form-data">

									<div class="col-lg-12"></div>
									<div class="card border-secondary" style="margin-top: 70px;">
										<div class="card-body justify-content-center">
											<p style="color: black;">
												ผู้รับ  <span style="color: gray; margin-left: 10px;">
													เกษตรกร จังหวัด${province.name} อำเภอ${district.name}
													ตำบล<c:forEach items="${subdistricts}" var="subdistrict">
													${subdistrict.name}&nbsp;&nbsp;
													</c:forEach> (${amountfarmer} คน) </span>
													
											</p>

											<p style="color: black;">
												หัวข้อ <span style="color: gray; margin-left: 10px;">แจ้งเตือนการระบาดของโรค

													<c:forEach items="${diseases}" var="disease">
														${disease.targetofsurvey.name}&nbsp;&nbsp;
													</c:forEach>

												</span>

											</p>
											<p style="color: black;">วันที่ ${date}</p>
											<p style="color: black;">ข้อความ 
												พบการระบาดของโรค

												<c:forEach items="${diseases}" var="disease">
														${disease.targetofsurvey.name}&nbsp;&nbsp;
												</c:forEach>
												โดยมีระดับความรุนแรง
												<c:if test="${level =='มาก'}">
													<span style="color: red;">${level}</span>
												</c:if>
												<c:if test="${level =='ปานกลาง'}">
													<span style="color: orange;">${level}</span>
												</c:if>
												<c:if test="${level =='น้อย'}">
													<span style="color: green;">${level}</span>
												</c:if>
												ในพื้นที่ของท่านโปรดตรวจสอบแปลง และศึกษาข้อมูลเพื่อป้องกัน
											</p>
											
										</div>
										
											<input type="hidden" value="${text}" name="text" />
											<input type="hidden" value="${title}" name="title" />
											
											<input type="hidden" value="${province.provinceId}" name="provinceId" />
											
											<input type="hidden" value="${district.districtId}" name="districtId" />
											
											<select name="subdistrict" multiple="multiple" hidden="hidden">
												<c:forEach items="${subdistricts}" var="subdistrict">
													<option value="${subdistrict.subdistrictId}" selected="selected"></option>
												</c:forEach>
											</select>
											
											<select name="diseases" multiple="multiple" hidden="hidden">
												<c:forEach items="${diseases}" var="disease">
													<option value="${disease.diseaseId}" selected="selected"></option>
												</c:forEach>
											</select>
											
											<input type="hidden" value="${level}" name="level" />
										
										<div class="form-group row">
											<div class="col-lg-8 ml-auto">
												<a href="${pageContext.request.contextPath}/message/cancel"
													class="btn btn-danger" role="button">ยกเลิก</a> 
													
												<c:if test="${amountfarmer > 0}"> <input
													type="submit" class="btn btn-success"
													onclick="fireSweetAlert()" value="ยืนยันการส่ง"></c:if>	

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
	</div>



	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />

	<script>
		function fireSweetAlert() {

			alert("ส่งสำเร็จ")

		}
	</script>

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
	<!-- Clock Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/clockpicker/dist/jquery-clockpicker.min.js"></script>
	<!-- Color Picker Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/jquery-asColorPicker-master/libs/jquery-asColor.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/jquery-asColorPicker-master/libs/jquery-asGradient.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/jquery-asColorPicker-master/dist/jquery-asColorPicker.min.js"></script>
	<!-- Date Picker Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.js'"></script>
	<!-- Date range Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/timepicker/bootstrap-timepicker.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/js/plugins-init/form-pickers-init.js"></script>

</body>
</html>
