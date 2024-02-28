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
							<h4 class="card-title">อนุมัติส่งออกการสำรวจของระบบ</h4>
							<div style="text-align: right;">
								<c:choose>
									<c:when
										test="${staff.getOrganization().getName().equals('กรมส่งเสริมการเกษตร')||staff.getOrganization().getName().equals('กรมวิชาการเกษตร')||staff.getOrganization().getName().equals('ภาควิชาโรคพืช มก. กพส.')||toPage.equals('Approved')}">
										<a
											href="${pageContext.request.contextPath}/permissionexport/system/approve/index"
											class="btn btn-light" role="button">กลับ</a>
									</c:when>
									<c:otherwise>
										<form
											action="${pageContext.request.contextPath}/permissionexport/system/requestApproved"
											method="post" style="display: inline;">
											<button type="submit" class="btn btn-primary">คำขอการส่งออกที่ดำเนินการแล้ว</button>
											<input type="hidden" name="requestId"
												value="${request.requestId}"> <input type="hidden"
												name="status" value="Approved">
										</form>
									</c:otherwise>
								</c:choose>
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
													<th>หน่วยงานที่1</th>
													<th>หน่วยงานที่2</th>
													<th>หน่วยงานที่3</th>
													<th>สถานะ</th>
													<th>ช่วงวันส่งออก</th>


												</tr>
											</thead>
											<tbody>
												<c:forEach items="${requests}" var="request"
													varStatus="loop">
													<tr>
														<td>${formatter.format(request.dateRequest)}</td>
														<td>${request.staffByStaffId.user.firstName}
															${request.staffByStaffId.user.lastName}</td>
														<td>
															<form
																action="${pageContext.request.contextPath}/permissionexport/system/requestDetail/${request.requestId}"
																method="post">
																<button type="submit"
																	class="btn mb-1 btn-rounded btn-outline-info text-primary disabled">
																	${request.requestdetails.size()} ครั้ง</button>
																<input type="hidden" name="toPage" value="${toPage}"></input>
															</form>
														</td>

														<td><c:choose>
																<c:when test="${request.statusDae.equals('Waiting')}">
																	<span class="badge badge-warning">รออนุมัติ</span>
																</c:when>
																<c:when test="${request.statusDae.equals('Approve')}">
																	<span class="badge badge-success">อนุมัติ</span>
																</c:when>
																<c:when test="${request.statusDae.equals('Reject')}">
																	<span class="badge badge-danger">ไม่อนุมัติ</span>
																</c:when>
															</c:choose></td>
														<td><c:choose>
																<c:when test="${request.statusDa.equals('Waiting')}">
																	<span class="badge badge-warning">รออนุมัติ</span>
																</c:when>
																<c:when test="${request.statusDa.equals('Approve')}">
																	<span class="badge badge-success">อนุมัติ</span>
																</c:when>
																<c:when test="${request.statusDa.equals('Reject')}">
																	<span class="badge badge-danger">ไม่อนุมัติ</span>
																</c:when>
															</c:choose></td>
														<td><c:choose>
																<c:when
																	test="${request.statusDppatho.equals('Waiting')}">
																	<span class="badge badge-warning">รออนุมัติ</span>
																</c:when>
																<c:when
																	test="${request.statusDppatho.equals('Approve')}">
																	<span class="badge badge-success">อนุมัติ</span>
																</c:when>
																<c:when test="${request.statusDppatho.equals('Reject')}">
																	<span class="badge badge-danger">ไม่อนุมัติ</span>
																</c:when>
															</c:choose></td>
														<td><c:choose>
																<c:when test="${request.status.equals('Waiting')}">
																	<span class="badge badge-warning">รออนุมัติ</span>
																</c:when>
																<c:when test="${request.status.equals('Approve')}">
																	<span class="badge badge-success">อนุมัติ</span>
																</c:when>
																<c:when test="${request.status.equals('Reject')}">
																	<span class="badge badge-danger">ไม่อนุมัติ</span>
																</c:when>
															</c:choose></td>
														<td><c:if test="${request.status.equals('Approve')}">
																<c:if test="${now > request.dateExpire}">
																	<span class="badge badge-danger">
																		${formatter.format(request.dateApprove)}
																		-${formatter.format(request.dateExpire)}</span>
																</c:if>

																<c:if test="${now <= request.dateExpire}">
																	<span class="badge badge-success">
																		${formatter.format(request.dateApprove)}
																		-${formatter.format(request.dateExpire)}</span>
																</c:if>
															</c:if></td>

													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</c:when>
								<c:otherwise>
									<div align="center" style="margin-top: 50px;">
										<h3>ไม่มีข้อมูล</h3>
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