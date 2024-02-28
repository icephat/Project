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
							<h4 class="card-title">การเพาะปลูก > เพิ่มข้อมูล</h4>
							<div class="text-right">
								<a href="${pageContext.request.contextPath}/planting/"
									class="btn btn-light" role="button">กลับ</a>
							</div>
							<h4 class="card-title">เลือกแปลง</h4>
							<div class="table-responsive">
								<table
									class="table table-striped table-bordered zero-configuration">
									<thead>
										<tr>
											<th>รหัสแปลง</th>
											<th>ชื่อแปลง</th>
											<th>ที่อยู่</th>	
											<th>ตำบล</th>
											<th>อำเภอ</th>
											<th>จังหวัด</th>
											<th>จำนวนการเพาะปลูก</th>							
											<th>เพิ่มการเพาะปลูก</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${fields}" var="field">
											<tr>
												<td>${field.code}</td>
												<td>${field.name}</td>
												<td>${field.address} ถ.${field.road} หมู่ ${field.moo}</td> 
												<td>ต.${field.subdistrict.name}</td>
												<td>อ.${field.subdistrict.district.name}</td>
												<td>จ.${field.subdistrict.district.province.name}</td>
												<td>
													<a href="${pageContext.request.contextPath }/planting/plantingByFieldId/${field.fieldId}" class="btn mb-1 btn-rounded btn-outline-info 
													<c:if test="${field.plantings.size() == 0}"> disabled </c:if>	
													" >${field.plantings.size()} ครั้ง</a>	
												</td>
												<td>
													<a href="${pageContext.request.contextPath }/planting/createbyfieldId/${field.fieldId}/page/${page}" class="btn btn-primary" role="button"><i class="fa fa-plus" aria-hidden="true"></i></a>											
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

	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
</body>
</html>