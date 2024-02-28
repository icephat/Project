<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../layouts/mainLayoutHead.jsp" />
<!-- Custom Stylesheet -->
<jsp:useBean id="now" class="java.util.Date" />
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
							<h4 class="card-title">ส่งออกการสำรวจ > ขอส่งออกการสำรวจ</h4>
							<p align="right">
								<a
									href="${pageContext.request.contextPath}/permissionexport/index"
									class="btn btn-light" role="button">กลับ</a>
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

											<th>สถานะ</th>
											<th>ช่วงวันที่ใช้สิทธิ์</th>
											<th>เลือกการสำรวจ</th>

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
															<c:if test="${targetOfSurveyAndCount[1]!=0}">
															 <c:set var="i" value="true" />
															</c:if>
													</c:forEach></td>

												<td><c:if test="${permission.status.equals('Waiting')}">
														<span class="badge badge-warning">รออนุมัติ</span>
													</c:if> <c:if test="${permission.status.equals('Reject')}">
														<span class="badge badge-danger">ไม่อนุมัติ</span>
													</c:if> <c:if test="${permission.status.equals('Approve')}">
														<span class="badge badge-success">อนุมัติ</span>
													</c:if></td>
												<td><c:if test="${permission.status.equals('Approve')}">
														<c:if test="${now > permission.dateExpire}">
															<span class="badge badge-danger">
																${formatter.format(permission.dateApprove)}
																-${formatter.format(permission.dateExpire)}</span>
														</c:if>

														<c:if test="${now <= permission.dateExpire}">
															<span class="badge badge-success">
																${formatter.format(permission.dateApprove)}
																-${formatter.format(permission.dateExpire)}</span>
														</c:if>
													</c:if></td>

												<td><c:if test="${permission.status.equals('Approve')}">
														<c:choose>
														<c:when test="${!i}">
																<p  style="text-align: center;">ไม่มีการสำรวจ</p>
															</c:when>
															<c:when test="${now <= permission.dateExpire}">
																<form
																	action="${pageContext.request.contextPath}/permissionexport/permission/${permission.permisisonId}"
																	method="post">

																	<button type="submit" value="" class="btn btn-primary">
																		<i class=" fa fa-arrow-right" aria-hidden="true"></i>
																	</button>
																</form>
															</c:when>
															
															<c:otherwise>
																<span style="margin-left: 1rem;"> <i
																	class="fa fa-arrow-right" aria-hidden="true"></i>
																</span>
															</c:otherwise>
														</c:choose>
													</c:if>
													<c:set var="i" value="false" />
													</td>
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

