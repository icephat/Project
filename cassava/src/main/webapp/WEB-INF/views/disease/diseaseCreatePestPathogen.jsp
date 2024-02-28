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
							<h4 class="card-title">โรคมันสำปะหลัง > ${nameT.name} > แมลงพาหะและเชื้อสาเหตุ </h4>


							<div class="row">
								<div class="col-6">
									
									<div class="card">
										<div class="card-body">
											<h4 class="card-title">${nameT.name} : แมลงพาหะ</h4>
											<p align="right">
												<a href="${pageContext.request.contextPath}/disease/createpest/${Disease.diseaseId}"
													class="btn mb-1 btn-primary">เพิ่ม</a>
											</p>
											<div class="table-responsive">
												<table class="table">
													<thead>
														<tr>
															<th>ชื่อ</th>
															<th>ชื่อวิทยาศาสตร์</th>											
															<th>ลบ</th>
														</tr>
													</thead>
													<tbody>
													<c:forEach items="${Pests}" var="Pests">
														<tr>											
															<td>${Pests.name}</td>
															<td>${Pests.scientificName}</td>
															<td>
																<a href="${pageContext.request.contextPath}/disease/deletepest/${Disease.diseaseId}/${Pests.pestId}"
																><i class="fa fa-trash" aria-hidden="true"></i></a>
															</td>
														</tr>
													</c:forEach>
													</tbody>
												</table>
											</div>
											
										</div>
									</div>
								</div>
								<div class="col-6">
									<div class="card">
										<div class="card-body">
											<h4 class="card-title">${nameT.name} : เชื้อสาเหตุ</h4>
											<p align="right">
												<a href="${pageContext.request.contextPath}/disease/createpathogen/${Disease.diseaseId}"
													class="btn mb-1 btn-primary">เพิ่ม</a>
											</p>
											<div class="table-responsive">
												<table class="table">
													<thead>
														<tr>											
															<th>ชื่อวิทยาศาสตร์</th>
															<th>ชนิดเชื้อโรคพืช</th>
															<th>ลบ</th>
														</tr>
													</thead>
													<tbody>
													<c:forEach items="${Pathogens}" var="Pathogens">
														<tr>
															
															<td>${Pathogens.scientificName}</td>
															<td>${Pathogens.pathogentype.name}</td>
															<td>
																<a
																href="${pageContext.request.contextPath}/disease/deletepathogen/${Disease.diseaseId}/${Pathogens.pathogenId}"><i class="fa fa-trash" aria-hidden="true"></i></a></td>
														</tr>
													</c:forEach>
													</tbody>
												</table>
											</div>
										</div>
									</div>
								</div>		
							</div>
							<p align="center"><a href="${pageContext.request.contextPath}/disease/edit/${Disease.diseaseId}" class="btn mb-1 btn-light">กลับ</a></p>
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