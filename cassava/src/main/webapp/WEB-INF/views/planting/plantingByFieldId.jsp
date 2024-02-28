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
	<div class="content-body">
		<div class="container-fluid">
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">ชื่อแปลงเพาะปลูก : ${plantings[0].field.name}(${plantings[0].field.code}) >การเพาะปลูกทั้งหมดที่สำรวจ</h4>
							<div class="text-right">
							<a href="${pageContext.request.contextPath }/planting/createbyfieldId/${plantings[0].field.fieldId}/page/${page}"
									class="btn mb-1 btn-primary">เพิ่มข้อมูล</a>
								<a href="${pageContext.request.contextPath}/planting/choose"
									class="btn btn-light" role="button">กลับ</a>
							</div>
							<div class="table-responsive">
								<table
									class="table table-striped table-bordered zero-configuration">
									<thead>
										<tr>
											<th>วันที่เพาะปลูก</th>
											<th>รหัสการเพาะปลูก</th>
											<th>ชื่อการเพาะปลูก</th>
											<th>ที่อยู่</th>
											<th>จำนวนการสำรวจ</th>
											<th>รายละเอียด</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${plantings}" var="planting">
											<tr>
												<td>${formatter.format(planting.primaryPlantPlantingDate)}</td>
												<td>${planting.code}</td>
												<td>${planting.name}</td>
												<td>${planting.field.address}
													ต.${planting.field.subdistrict.name}<br>
													อ.${planting.field.subdistrict.district.name}
													จ.${planting.field.subdistrict.district.province.name}
												</td>

												<td><a class="btn mb-1 btn-rounded btn-outline-primary">${planting.surveys.size()}
														ครั้ง</a></td>

												<td><c:set var="check" value="${planting.surveys.size()}" /><c:choose>
														<c:when test="${check == 0}">
															<a
																href="${pageContext.request.contextPath}/planting/edit/${planting.plantingId}"
																class="btn btn-primary" role="button"><i
																class="fa fa-edit" aria-hidden="true"></i></a>
															<a
																href="${pageContext.request.contextPath}/planting/delete/${planting.plantingId}"
																class="btn btn-danger" role="button"><i
																class="fa fa-trash" aria-hidden="true"></i></a>
														</c:when>
														<c:otherwise>
															<a
																href="${pageContext.request.contextPath}/planting/edit/${planting.plantingId}"
																class="btn btn-primary" role="button"><i
																class="fa fa-edit" aria-hidden="true"></i></a>
														</c:otherwise>
													</c:choose></td>
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
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
</body>
</html>