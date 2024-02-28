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
							<h4 class="card-title">โรคมันสำปะหลัง > ${nameT.name} > แมลงพาหะและเชื้อสาเหตุ > เพิ่มเชื้อสาเหตุ</h4>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>ชื่อวิทยาศาสตร์</th>
											<th>ประเภทเชื้อ</th>
											<th>เพิ่ม</th>
										</tr>
									</thead>
									<tbody>
									<c:forEach items="${pathogensum}" var="pathogensum">
									<c:if test="${not empty pathogensum.pathogenId}" >
          								<tr>
											<td>${pathogensum.scientificName}</td>
											<td>${pathogensum.pathogentype.name}</td>
											<td>
												<a href="${pageContext.request.contextPath}/disease/addpathogen/${Disease.diseaseId}/${pathogensum.pathogenId}" class="btn btn-primary" role="button"><i class="fa fa-plus" aria-hidden="true"></i></a>
											</td>
										</tr>
									</c:if>
									</c:forEach>
									</tbody>
								</table>
							</div>
							<p align="center">
								<a href="${pageContext.request.contextPath}/disease/edit/pestpathogen/${Disease.diseaseId}"
									class="btn mb-1 btn-light">กลับ</a>
							</p>
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
</html>