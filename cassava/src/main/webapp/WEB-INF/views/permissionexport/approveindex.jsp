<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../layouts/mainLayoutHead.jsp" />
<!-- Custom Stylesheet -->
<jsp:useBean id="now" class="java.util.Date" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">
<link
	href="${pageContext.request.contextPath }/resources/plugins/tables/css/datatable/dataTables.bootstrap4.min.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath }/resources/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
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
				<div class="col">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">อนุมัติส่งออกการสำรวจ</h4>
							<div class="general-button" align="right">
								<div style="text-align: right;">
									<form
										action="${pageContext.request.contextPath}/permissionexport/requestApproved"
										method="post" style="display: inline;">
										<button type="submit" class="btn btn-primary">คำขอการส่งออกที่ดำเนินการแล้ว</button>
										<input type="hidden" name="requestId"
											value="${request.requestId}"> <input type="hidden"
											name="status" value="Approved">
									</form>
								</div>



							</div>
							<c:choose>
								<c:when test="${requests.size()!=0}">
									<div class="table-responsive">
										<table
											class="table table-striped table-bordered zero-configuration">
											<thead>
												<tr>
													<th>วันที่ขออนุมัติ</th>
													<th>ชื่อผู้ขอ</th>
													<th>จำนวนสิ่งสำรวจ</th>
													<th>อนุมัติ</th>
												</tr>
											</thead>
											<tbody>


												<c:forEach items="${requests}" var="request"
													varStatus="loop">
													<tr>
														<td>${formatter.format(request.dateRequest)}</td>
														<td>${request.staffByStaffId.user.firstName}
															${request.staffByStaffId.user.lastName}</td>
														<td><form
																action="${pageContext.request.contextPath}/permissionexport/requestDetail/${request.requestId}"
																method="post">
																<button type="submit"
																	class="btn mb-1 btn-rounded btn-outline-info text-primary disabled">
																	${request.requestdetails.size()} ครั้ง</button>
																<input type="hidden" name="toPage" value="approveindex"></input>
															</form></td>

														<td>
															
															<a
															href="${pageContext.request.contextPath}/permissionexport/Approve/${request.requestId}"
															class="btn btn-success" role="button"><i
																class="fa fa-check" aria-hidden="true"></i> อนุมัติ</a> <a
															href="${pageContext.request.contextPath}/permissionexport/Reject/${request.requestId}"
															class="btn btn-danger" role="button"><i
																class="fa fa-times" aria-hidden="true"></i> ไม่อนุมัติ</a>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</c:when>
								<c:otherwise>
									<div align="center" style="margin-top: 50px;">
										<h3>ไม่มีคำขออนุมัติ</h3>
									</div>
								</c:otherwise>
							</c:choose>
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
</body>
</html>