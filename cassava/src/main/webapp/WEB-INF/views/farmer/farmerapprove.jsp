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
							<h4 class="card-title">เกษตรกร > เกษตรกรรออนุมัติ</h4>
							<p align="right">
								<a
									href="${pageContext.request.contextPath}/farmer/"
									class="btn mb-1 btn-light">กลับ</a>
							</p>
							<div class="table-responsive">
								<table
									class="table table-striped table-bordered zero-configuration">
									<thead>
										<tr>
											<th>ผู้ใช้</th>
											<th>ที่อยู่</th>
											<th>สังกัดหน่วยงาน</th>
											<th>อนุมัติ</th>
											<th>รายละเอียด</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${farmers}" var="farmer">
											<tr>
												<td>${farmer.user.firstName}  ${farmer.user.lastName}<br>${farmer.user.username}</td>
												<td>อ.${farmer.subdistrict.district.name} <br>จ.${farmer.subdistrict.district.province.name}</td>
												<td>
													<c:forEach items="${farmer.organizations}" var="organization">
														${organization.name}<br>
													</c:forEach>
												</td>
												
												<td><a
													href="${pageContext.request.contextPath}/farmer/${farmer.farmerId}/approve/save"
													class="btn btn-primary" role="button"><i class="fa fa-check" aria-hidden="true"></i> อนุมัติ</a>
													<a
															href="${pageContext.request.contextPath}/farmer/delete/${farmer.farmerId}"
															class="btn btn-danger" role="button"
													onclick="return confirm('คุณยืนยันที่จะลบหรือไม่');"><i class="fa fa-trash" aria-hidden="true"></i></a>
													
												<td><a
													href="${pageContext.request.contextPath}/farmer/${farmer.farmerId}/detail"
													class="btn btn-primary" role="button"><i class="fa fa-search" aria-hidden="true"></i></a>
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
		src="${pageContext.request.contextPath }/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>

</body>
</html>