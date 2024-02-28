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
							<h4 class="card-title">เกษตรกรในหน่วยงาน</h4>


							<p align="right">
								<!--  <a href="search" class="btn mb-1 btn-primary">เพิ่มเกษตรกร</a>		-->					
								<a href="farmerinvalid" class="btn mb-1 btn-primary">เกษตรกร - ไม่มีบัญชีผู้ใช้</a>
							</p>



							<div class="table-responsive">
								<table
									class="table table-striped table-bordered zero-configuration">
									<thead>
										<tr>
											<th>ชื่อ สกุล</th>
											<th>ตำบล</th>
											<th>อำเภอ</th>
											<th>จังหวัด</th>
											<th>สถานะบัญชี</th>
											<th>รายละเอียด</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${farmers}" var="farmer">
											<c:if test="${farmer.organizations.size()>0}">
												<tr>
													<td>${farmer.user.firstName} ${farmer.user.lastName}<br>
														${farmer.user.username}
													</td>
													<td>${farmer.subdistrict.name}</td>
													<td>${farmer.subdistrict.district.name}</td>
													<td>${farmer.subdistrict.district.province.name}</td>
													<td>${farmer.user.userStatus}</td>
													<td><a
														href="${pageContext.request.contextPath}/farmerorganization/edit/${farmer.farmerId}"
														class="btn btn-primary" role="button"> <i
															class="fa fa-search" aria-hidden="true"></i></a><!--   <a
														href="${pageContext.request.contextPath}/farmerorganization/delete/${farmer.farmerId}"
														class="btn btn-danger" role="button"
														onclick="return confirm('คุณยืนยันที่จะลบหรือไม่');"><i
															class="fa fa-trash" aria-hidden="true"></i></a>-->
												</tr>
											</c:if>

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
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
</body>
</html>