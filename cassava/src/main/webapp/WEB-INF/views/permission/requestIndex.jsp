<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../layouts/mainLayoutHead.jsp" />
<!-- Custom Stylesheet -->
<jsp:useBean id="now" class="java.util.Date" />

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
							<h4 class="card-title">อนุมัติสิทธิ์ส่งออกการสำรวจ</h4>
							<p align="right">
								<a href="${pageContext.request.contextPath}/permission/request/approved"
									class="btn btn-primary" role="button">คำขอสิทธิ์ที่ดำเนินการแล้ว</a>
							</p>

							<div class="table-responsive">
								<table
									class="table table-striped table-bordered zero-configuration">
									<thead>
										<tr>
											<th>วันที่ส่งคำขอสิทธิ์</th>
											<th>ชื่อผู้ขอสิทธิ์</th>
											<th>ข้อมูลช่วงวันที่</th>
											<th>จังหวัด</th>
											<th>สิ่งสำรวจ</th>
											<th>หลักฐานประกอบ</th>
											<th>อนุมัติ</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach items="${permissions}" var="permission"
											varStatus="loop1">
											<tr>
												<td>${formatter.format(permission.dateRequest)}</td>
												<td>${permission.staffByStaffId.user.firstName}
													${permission.staffByStaffId.user.lastName}<br>
													${permission.staffByStaffId.division}<br>
													${permission.staffByStaffId.organization.name}
												</td>
												<td>${formatter.format(permission.dateInfoStart)}-
													${formatter.format(permission.dateInfoEnd)}</td>
												<td><c:forEach items="${provinces[loop1.index]}"
														var="province" varStatus="loop2">
														<option>${province.name}</option>
													</c:forEach></td>
												<td><c:forEach
														items="${targetOfSurveyAndCounts[loop1.index]}"
														var="targetOfSurveyAndCount" varStatus="loop3">
														<option>${targetOfSurveyAndCount[0]}
															(${targetOfSurveyAndCount[1]})</option>
													</c:forEach></td>
												<td><c:forEach items="${permission.permissionfiles}"
														var="perfile" varStatus="loop">
														<a
															href="${pageContext.request.contextPath}/permission/request/download/${perfile.permissionFileId}"
															class="btn btn-primary btn-sm" role="button" download><i
															class="fa fa-download" aria-hidden="true"></i></a> เอกสารแนบที่
																${loop.index +1}
															<br>
													</c:forEach></td>
												<td><a
													href="${pageContext.request.contextPath}/permission/approve/${permission.permisisonId}"
													class="btn btn-success" role="button"><i
														class="fa fa-check" aria-hidden="true"></i> อนุมัติ</a> <a
													href="${pageContext.request.contextPath}/permission/reject/${permission.permisisonId}"
													class="btn btn-danger" role="button"><i
														class="fa fa-times" aria-hidden="true"></i> ไม่อนุมัติ</a></td>
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


</body>
</html>
