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
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css" >
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
							<h4 class="card-title">โรคมันสำปะหลัง</h4>
							<p align="right">
								<a href="${pageContext.request.contextPath }/disease/create"
									class="btn mb-1 btn-primary">เพิ่มข้อมูล</a>
							</p>
							<div class="row">
							
							<c:forEach items="${diseases}" var="disease" varStatus="loop" >
								<div class="col-3">
									<div class="card">
										<div class="card-body">
											<div class="text-center">
												<img alt="" class=" thumbnail rounded-circle mt-4"
													width="150" height="150"
													src="data:image/jpeg;base64,${images[loop.index].image }">
												<h4 class="card-widget__title text-dark mt-3">${disease.targetofsurvey.name}</h4>
												<p class="text-muted">
												<h3>
													<a href="${pageContext.request.contextPath}/disease/edit/${disease.diseaseId}"> <i
														class="fa fa-edit" aria-hidden="true"></i>
													</a> 
													<c:if test="${hasFK[loop.index]==0}">
													<a href="${pageContext.request.contextPath}/disease/delete/${disease.diseaseId}" onclick="return confirm('Are you sure you want to delete this item?');"><i
														class="fa fa-trash" aria-hidden="true"> </i>
													</a>
													</c:if>
												</h3>
												
											</div>
										</div>
									</div>
								</div>
								</c:forEach>
								
										</div>
									</div>
								</div>

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

	<!-- Custom Script -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
</body>
</html>
