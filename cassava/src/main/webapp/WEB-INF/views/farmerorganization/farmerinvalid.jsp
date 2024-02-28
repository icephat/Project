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
							<h4 class="card-title">เกษตรกรในความดูแล >
								เกษตรกรที่ไม่มีบัญชีผู้ใช้</h4>
							<p align="right">
								<a
									href="${pageContext.request.contextPath}/farmerorganization/index"
									class="btn mb-1 btn-light">กลับ</a> <a href="create"
									class="btn mb-1 btn-primary">สร้างข้อมูลเกษตรกรใหม่</a>
							</p>
							<form class="form-valide"
								action="${pageContext.request.contextPath}/farmerorganization/save"
								method="post" modelAttribute="farmer" id="farmerData"
								enctype="multipart/form-data">


								<div class="table-responsive">
									<table
										class="table table-striped table-bordered zero-configuration">
										<thead>
											<tr>
												<th>ชื่อ สกุล</th>
												<th>ที่อยู่</th>
												<th>ตำบล</th>
												<th>อำเภอ</th>
												<th>จังหวัด</th>
												<!-- <th>สถานะบัญชี</th> -->
												<th>รายละเอียด</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${farmers}" var="farmer" varStatus="loop">
												<tr>
													<td>${farmer.user.title}${farmer.user.firstName}
														${farmer.user.lastName}</td>
													<td>${farmer.address}</td>
													<td>${farmer.subdistrict.name}</td>
													<td>${farmer.subdistrict.district.name}</td>
													<td>${farmer.subdistrict.district.province.name}</td>
													<!-- <td>${farmer.user.userStatus}</td> -->
													<td><a
														href="${pageContext.request.contextPath}/farmerorganization/editfarmerinvalid/${farmer.farmerId}"
														class="btn btn-primary" role="button"> <i
															class="fa fa-edit" aria-hidden="true"></i></a> 
													<c:if test="${hasFK[loop.index]==0}">
															<a
																href="${pageContext.request.contextPath}/farmerorganization/deletefarmerinvalid/${farmer.farmerId}"
																class="btn btn-danger" role="button"
																onclick="return confirm('คุณยืนยันที่จะลบหรือไม่');">
																<i class="fa fa-trash" aria-hidden="true"></i>
															</a></td>
													</c:if>
												</tr>
											</c:forEach>

										</tbody>

									</table>
								</div>
							</form>
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