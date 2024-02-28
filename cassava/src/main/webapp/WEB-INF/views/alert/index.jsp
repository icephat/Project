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

			<div class="card text-center">
				<div class="card-header" style="font-size: large; color: black;">รายการแจ้งเตือน</div>
				<div class="card-body">
					<table class="table table-hover table-light text-left" style="margin-left: 20px;">
						<thead style="color: black;">
							<tr>
								<th scope="col">เรื่องการเตือน</th>
								<th scope="col">ข้อความ</th>
								<th scope="col">วันที่เตือน</th>
								<th></th>
							</tr>
						</thead>
						<tbody>

							<c:forEach items="${messageReceiverDTO}" var="messagereceiver">
								<tr>
									<th style="width:20%" scope="row">${messagereceiver.title}</th>
									<td style="width:40%">${messagereceiver.text}</td>
									<td style="width:20%">${messagereceiver.date}</td>
									<td><a
														href="${pageContext.request.contextPath}/farmerorganization/delete/${farmer.farmerId}"
														class="btn btn-danger" role="button"
														onclick="return confirm('คุณยืนยันที่จะลบหรือไม่');"><i
															class="fa fa-trash" aria-hidden="true"></i></a></td>
								</tr>
								
							</c:forEach>
							
						</tbody>
					</table>
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