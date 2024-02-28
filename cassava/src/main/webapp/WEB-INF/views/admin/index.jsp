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
							<h4 class="card-title">ผู้จัดการระบบ</h4>
							<p align="right">
								<a href="${pageContext.request.contextPath}/admin/approve"
									class="btn mb-1 btn-primary">เพิ่มข้อมูล</a>
							</p>
							<div class="table-responsive">
								<table
									class="table table-striped table-bordered zero-configuration">
									<thead>
										<tr>
											<th>ชื่อ สกุล</th>
											<th>หน่วยงาน</th>
											<c:forEach items="${roles}" var="role" varStatus="i">
												<th>${role.nameTh}</th>
											</c:forEach>
											<th>แก้ไข</th>
										</tr>
									</thead>

									<c:forEach items="${users}" var="user" varStatus="i">
										<tr>
											<td>${user.title}${user.firstName}${user.lastName}<br>
												${user.username}
											</td>
											<td>${user.staff.division}<br>${user.staff.organization.name}</td>
											<c:forEach items="${roles}" var="role" varStatus="j">
												<td><c:if test="${tableRole[i.index][j.index] == 1}">
														<i class="fa fa-check" aria-hidden="true"></i>
													</c:if></td>
											</c:forEach>
											<td><a
												href="${pageContext.request.contextPath}/admin/approve/${user.staff.staffId}/create"
												class="btn btn-primary" role="button"><i
													class="fa fa-edit" aria-hidden="true"></i></a>
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
	<script>
		var role = document.getElementById("role").value;
		if (role == "System Admin") {
			document.getElementById("system").innerHTML = <i class="fa fa-check" aria-hidden="true"></i>;
		} else if (role == "Info Admin") {
			document.getElementById("info").innerHTML = <i class="fa fa-check" aria-hidden="true"></i>;
		} else if (role == "Image examiner") {
			document.getElementById("image").innerHTML = <i class="fa fa-check" aria-hidden="true"></i>;
		}
	</script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
</body>
</html>