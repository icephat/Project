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
							<h4 class="card-title">แปลงเพาะปลูก</h4>
							<c:choose>
								<c:when test="${role==1}">
									<p align="right">
										<a href="${pageContext.request.contextPath}/field/fieldcreate"
											class="btn mb-1 btn-primary">เพิ่มข้อมูล</a>
									</p>
								</c:when>
							</c:choose>
							<div class="table-responsive">
								<table
									class="table table-striped table-bordered zero-configuration">
									<thead>
										<tr>
											<th>รูปแปลง</th>
											<th>ชื่อแปลง</th>
											<th>เจ้าของแปลง</th>
											<th>ที่อยู่</th>
											<th>หน่วยงาน</th>
											<th>จำนวนการเพาะปลูก</th>
											<th>รายละเอียด</th>
											<th>ผู้ใช้ในแปลง</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${ownerInFields}" var="oif" varStatus="loop">
											<tr>

												<td><c:choose>
														<c:when test="${images[loop.index].image!=''}">
															<img alt="" class=" thumbnail mt-4"
																style="width: 100px; height: 100px; object-fit: cover;"
																src="data:image/jpeg;base64,${images[loop.index].image }">
														</c:when>
														<c:otherwise>
   															 ไม่มีรูป
  														</c:otherwise>
													</c:choose></td>
												<td>${oif.field.name}<br>(${oif.field.code})
												</td>
												<td>${oif.user.title}${oif.user.firstName}
													${oif.user.lastName}</td>
												<td>${oif.field.address}
													ต.${oif.field.subdistrict.name}<br>
													อ.${oif.field.subdistrict.district.name}
													จ.${oif.field.subdistrict.district.province.name}
												</td>
												<td>${oif.field.organization.name}</td>
												<td>${oif.field.plantings.size()}ครั้ง</td>
												<td><a
													href="${pageContext.request.contextPath }/field/edit/${oif.field.fieldId}"
													class="btn btn-primary" role="button"><i
														class="fa fa-edit" aria-hidden="true"></i></a> <c:if
														test="${oif.field.plantings.size()==0}">
														<a
															href="${pageContext.request.contextPath }/field/delete/${oif.field.fieldId}"
															class="btn mb-1 btn-danger" role="button"
															onclick="return confirm('คุณยืนยันที่จะลบหรือไม่');"><i
															class="fa fa-trash" aria-hidden="true"></i></a>
													</c:if></td>
												<td><a
													href="${pageContext.request.contextPath }/field/${oif.field.fieldId}/user/index"
													class="btn btn-primary" role="button"><i
														class="fa fa-user-o" aria-hidden="true"></i></a></td>
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

	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
</body>
</html>