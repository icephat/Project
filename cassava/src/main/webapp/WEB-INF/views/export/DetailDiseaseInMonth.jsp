<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../layouts/mainLayoutHead.jsp" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<meta charset="UTF-8">
<title>ImageDetailMonth</title>
<!-- Custom Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">

</head>
<body>
	<jsp:include page="../layouts/mainLayoutBodyHeader.jsp" />


	<!--**********************************
	            Content body start
	***********************************-->
	<div class="content-body">
		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="card">

						<div class="card-body">
							<h4 class="card-title">ส่งออกภาพ > Download</h4>

							<p class="card-text">
								<label class="col-lg-4">ปีที่ตรวจสอบภาพ</label> <label
									class="col-lg-6">${year} (${month[monthint-1]})
								</label>
							</p>
							<p class="card-text">
								<label class="col-lg-4">โรค</label> <label class="col-lg-6">
									${diseaseselect.targetofsurvey.name} </label>
							</p>
							<p class="card-text">
								<label class="col-lg-4">จำนวนภาพ</label> <label class="col-lg-6">${numimg}
									ภาพ</label>
							</p>
							
							<c:if test="${size[0]!=0}">
								<form class="form-valide"
									action="${pageContext.request.contextPath}/export/img"
									method="post">
									<div class="table-responsive">
										<table class="table">
											<thead>
												<tr>
													<th>ส่วนที่</th>
													<th>จำนวนภาพ</th>
													<th>ขนาด</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${size}" var="size" varStatus="loop">
													<tr>
														<td>${loop.count}</td>
														<td>${imgid[loop.index].size()}</td>
														<td>${size} MB</td>
														<td><button name="imgid" value="${imgid[loop.index]}"
																class="btn btn-primary btn-sm">
																<i class="fa fa-download" aria-hidden="true"></i>
															</button> </td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</form>
							</c:if>
							<form class="form-valide"
								action="${pageContext.request.contextPath}/export/detail"
								method="post">
								<p align="center">
									<c:forEach items="${listdiseaseid}" var="diseaseid">
										<input type="hidden" name="listdiseaseid"
											value="${diseaseid}">
									</c:forEach>
									<input type="hidden" name="yearselect" value="${year}">
									<input type="submit" class="btn btn-primary" value="กลับ" />
								</p>
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
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
</body>
</html>