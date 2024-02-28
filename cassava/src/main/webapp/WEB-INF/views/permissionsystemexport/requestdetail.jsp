<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../layouts/mainLayoutHead.jsp" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<meta charset="UTF-8">
<title>Request Field</title>
<!-- Custom Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">

</head>
<body>
	<jsp:include page="../layouts/mainLayoutBodyHeader.jsp" />


	<!--**********************************
	            Content body start
	***********************************-->
	<div class="content-body">
		<div class="container-fluid">
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">รายละเอียดการขอส่งออกการสำรวจของระบบ</h4>
							<p align="right">
								<c:choose>
									<c:when test="${toPage=='index'}">
										<a
											href="${pageContext.request.contextPath}/permissionexport/system/index"
											class="btn btn-light" role="button">กลับ</a>
									</c:when>
									<c:when test="${toPage=='approveindex'}">
										<a
											href="${pageContext.request.contextPath}/permissionexport/system/approve/index"
											class="btn btn-light" role="button">กลับ</a>
									</c:when>
									<c:when test="${toPage=='history'}">
										<a
											href="${pageContext.request.contextPath}/permissionexport/system/requestApproved"
											class="btn btn-light" role="button">กลับ</a>
									</c:when>
									<c:when test="${toPage=='Approved'}">
										<form
											action="${pageContext.request.contextPath}/permissionexport/system/requestApproved"
											method="post" style="text-align: right;">
											<button type="submit" class="btn btn-light" >กลับ</button>
											<input type="hidden" name="requestId"
												value="${request.requestId}"></input> <input type="hidden"
												name="status" value="Approved"></input>
										</form>
									</c:when>
									
									<c:when test="${toPage=='Waiting'}">
										<form
											action="${pageContext.request.contextPath}/permissionexport/system/requestApproved"
											method="post" style="text-align: right;">
											<button type="submit" class="btn btn-light">กลับ</button>
											<input type="hidden" name="requestId"
												value="${request.requestId}"></input> <input type="hidden"
												name="status" value="Waiting"></input>
										</form>
									</c:when>
								</c:choose>
							</p>
							<div class="table-responsive">
								<table
									class="table table-striped table-bordered zero-configuration">


									<thead>
										<tr>
											<th>วันที่สำรวจ</th>
											<th>ชื่อแปลง</th>
											<th>อำเภอ</th>
											<th>จังหวัด</th>
											<th>สิ่งสำรวจ</th>
											<th>ความรุนแรง</th>
											<th>%การพบ</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach items="${requestdetails}" var="requestdetail">
											<tr>
												<td>${formatter.format(requestdetail.surveyTarget.survey.date)}</td>
												<td>${requestdetail.surveyTarget.survey.planting.field.name}</td>
												<td>${requestdetail.surveyTarget.survey.planting.field.subdistrict.district.name}</td>
												<td>${requestdetail.surveyTarget.survey.planting.field.subdistrict.district.province.name}</td>
												<td>${requestdetail.surveyTarget.targetofsurvey.name}<c:choose>
														<c:when
															test="${requestdetail.surveyTarget.targetofsurvey.disease != null}">
															<span class="badge badge-info">โรค</span>
														</c:when>
														<c:when
															test="${requestdetail.surveyTarget.targetofsurvey.pestphasesurvey != null}">
															<span class="badge badge-success">แมลงศัตรูพืช</span>
														</c:when>
														<c:when
															test="${requestdetail.surveyTarget.targetofsurvey.naturalenemy != null}">
															<span class="badge badge-warning">ศัตรูธรรมชาติ</span>
														</c:when>
													</c:choose>
												</td>
												<td>${requestdetail.surveyTarget.avgDamageLevel}<c:choose>
														<c:when
															test="${requestdetail.surveyTarget.targetofsurvey.disease != null}">/5</c:when>
														<c:when
															test="${requestdetail.surveyTarget.targetofsurvey.pestphasesurvey != null || requestdetail.surveyTarget.targetofsurvey.naturalenemy != null}">/100</c:when>
													</c:choose>
												</td>
												<td>${requestdetail.surveyTarget.percentDamage}%</td>



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
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
</body>
</body>
</html>