<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../layouts/mainLayoutHead.jsp" />
<!-- Custom Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/plugins/tables/css/datatable/dataTables.bootstrap4.min.css">
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
							<h4 class="card-title">เชื้อโรคพืช</h4>
							<p align="right">
								<a
									href="${pageContext.request.contextPath}/pathogen/create"
									class="btn mb-1 btn-primary">เพิ่มข้อมูล</a>
							</p>
							<div class="table-responsive">
								<table
									class="table table-striped table-bordered zero-configuration">
									<thead>
										<tr>
											<th>ชื่อวิทยาศาสตร์</th>
											<th>ประเภทเชื้อ</th>
											<th>แก้ไข</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pathogens}" var="pathogen" varStatus="loop">
											<tr>
												<td>${pathogen.scientificName}</td>
												<td>${pathogen.pathogentype.name}</td>
												<td>
												<a href="${pageContext.request.contextPath}/pathogen/edit/${pathogen.pathogenId}" class="btn btn-primary" role="button"><i class="fa fa-edit" aria-hidden="true"></i></a> 
												<c:if test="${hasFK[loop.index]==0}">
												<a href="${pageContext.request.contextPath}/pathogen/delete/${pathogen.pathogenId}"	class="btn btn-danger" role="button"
												onclick="return confirm('คุณยืนยันที่จะลบหรือไม่');">
												<i class="fa fa-trash" aria-hidden="true">
												</i></a>	
												</c:if>											
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
	<!--**********************************
	     Content body end
	***********************************-->

	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />

	<!-- Custom Script -->
	<script src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
</body>
</html>

