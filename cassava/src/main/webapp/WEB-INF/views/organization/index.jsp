<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<jsp:include page="../layouts/mainLayoutHead.jsp" />
	<!-- Custom Stylesheet -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">
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
							<h4 class="card-title">หน่วยงาน</h4>
							<p align="right">
								<a href="${pageContext.request.contextPath}/organization/create"
									class="btn mb-1 btn-primary">เพิ่มข้อมูล</a>
							</p>
							<div class="table-responsive">
								<table class="table table-striped table-bordered zero-configuration">
									<thead>
										<tr>
											<th>รหัสหน่วยงาน</th>
											<th>หน่วยงาน</th>
											<th>เบอร์โทรศัพท์</th>
											<!-- <th>การเข้าถึงข้อมูล</th> -->
											<th>แก้ไข</th>
											<th>ผู้จัดการข้อมูลแปลง</th>
											<th>เจ้าหน้าที่</th>
											<th>เกษตรกร</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${organizations}" var="organization" varStatus="loop">

											<tr>
												<td>${organization.code}</td>
												<td>${organization.name}</td>
												<td>${organization.phone}</td>
												<!-- <td>${organization.accessInfoLevel}</td> -->
												<td><a href="${pageContext.request.contextPath}/organization/edit/${organization.organizationId}"
														class="btn btn-primary" role="button"><i class="fa fa-edit"
															aria-hidden="true"></i></a>
													<c:if test="${hasFK[loop.index]==0}">
														<a href="${pageContext.request.contextPath}/organization/delete/${organization.organizationId}"
															class="btn btn-danger" role="button"
															onclick="return confirm('คุณยืนยันที่จะลบหรือไม่');">
															<i class="fa fa-trash" aria-hidden="true"></i></a>
													</c:if>
												</td>

												<td>${countStaffFieldRegistrar[loop.index]} คน <a
														href="${pageContext.request.contextPath}/organization/info/${organization.organizationId}"
														class="btn btn-primary  btn-sm" role="button"><i
															class="fa fa-edit" aria-hidden="true"></i></a></td>
												<td>
													${countStaff[loop.index]} คน
												</td>
												<td>
													${countFarmer[loop.index]} คน
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

	<script src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js">
	</script>
</body>

</html>