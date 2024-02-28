<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<jsp:include page="../layouts/mainLayoutHead.jsp" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">
	<link rel="stylesheet"
		href="${pageContext.request.contextPath }/resources/plugins/tables/css/datatable/dataTables.bootstrap4.min.css">
</head>

<body>
	<jsp:include page="../layouts/mainLayoutBodyHeader.jsp" />
	<div class="content-body">
		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">ศัตรูพืช > ${pest.name} > สารป้องกันกำจัดศัตรูพืช</h4>
							<input type="hidden" name="pest.pestId" value="${pest.pestId}">
							<div class="form-group row">
								<div class="col-lg-8 ml-auto">
									<p align="right">
										<a href="${pageContext.request.contextPath}/pestcide/add/${pest.pestId}"
											class="btn btn-primary" role="button">เพิ่มสารป้องกันกำจัดศัตรูพืช</a>
									</p>
								</div>
							</div>
							<div class="table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th>ชื่อสามัญ</th>
											<th>%สารออกฤทธิ์ และสูตร</th>
											<th>กลุ่มกลไกการออกฤทธิ์</th>
											<th>ระดับความเป็นพิษ</th>
											<th>อัตราการใช้</th>
											<th>รายละเอียด</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pestcides}" var="pestcide">
											<tr>
												<td>${pestcide.pesticideName}</td>
												<td>${pestcide.percentApi}</td>
												<td>${pestcide.pesticideMechanism}</td>
												<td>${pestcide.toxicityLevel}</td>
												<td>${pestcide.mixingRatio}</td>
												<td><a
														href="${pageContext.request.contextPath}/pestcide/pestmanagement/edit/${pestcide.pestManagementId}" class="btn btn-primary" role="button"><i
															class="fa fa-edit" aria-hidden="true"></i></a>
													<a
														href="${pageContext.request.contextPath}/pestcide/pestmanagement/delete/${pestcide.pestManagementId}" class="btn btn-danger" role="button"><i
															class="fa fa-trash" aria-hidden="true"></i></a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
							<div class="text-center">
								<a href="${pageContext.request.contextPath}/pest/edit/${pest.pestId}" class="btn btn-light"
									role="button">กลับ</a>
							</div>

						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
	<script src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js">
	</script>
	<script src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js">
	</script>
</body>

</html>