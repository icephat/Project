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

<script type="text/javascript" src="multiple-dropdown.js"></script>


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
							<h4 class="card-title">รายงานการตรวจสอบ -> สถิติการทำงานของผู้ตรวจสอบ</h4>

							<div class="form-validation">
								<form class="form-valide"
									action="${pageContext.request.contextPath}/export/imagesumary/workstatistics"
									method="post">

									<div class="form-validation">

										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="val-username">เลือกเดือนที่ต้องการ<span
												class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<select class="selectpicker" name="months" multiple>
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
											<label class="col-lg-4 col-form-label" for="val-username">เลือกปีที่ต้องการ<span
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
												<a
													href="${pageContext.request.contextPath }/export/imagesumary"
													class="btn btn-light" role="button">กลับ</a> <input
													type="submit" id='submit' class="btn btn-primary"
													role="button" value="ยืนยัน">
											</div>
										</div>
									</div>
								</form>
							</div>

							<div class="table-responsive">
								<table
									class="table table-striped table-bordered zero-configuration">
									<thead>
										<tr>
											<th>ชื่อผู้ใช้</th>
											<th>ชื่อ</th>
											<th>นามสกุล</th>
											<th>จำนวนรูปที่อนุมัติ</th>
											<th>จำนวนรูปที่ปฏิเสธ</th>
											<th>หมายเหตุ</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${imgSumaryDTOs}" var="imgSumaryDTO"
											varStatus="loop">
											<tr>
												<td>${imgSumaryDTO.staff.user.username}</td>
												<td>${imgSumaryDTO.staff.user.firstName}</td>
												<td>${imgSumaryDTO.staff.user.lastName}</td>
												<td>${imgSumaryDTO.approved}</td>
												<td>${imgSumaryDTO.rejected}</td>
												<td>ปฏิเสธ : ${imgSumaryDTO.reject} รูป , ลบ :
													${imgSumaryDTO.delete} รูป</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

						</div>
					</div>






				</div>
			</div>


		</div>
	</div>



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
