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
<meta charset="UTF-8">
<title>AddUserinField Page</title>
</head>
<body>
	<jsp:include page="../layouts/mainLayoutBodyHeader.jsp" />

	<!--**********************************
	            Content body start
	***********************************-->
	<div class="content-body">
		<div class="container-fluid">
			<div class="row">
				<div class="col-6">
					<div class="card">
						<div class="card-body">
							<a href="${pageContext.request.contextPath }/field/index"
								class="btn btn-light" role="button"> กลับ</a>
							<div class="form-validation">
								<h4 class="card-title"></h4>
								<div class="card-body">
									<div class="text-center">
										<span class="display-5"><i
											class="fa fa-users gradient-3-text"></i></span>
										<h2 class="mt-3">เจ้าหน้าที่</h2>
									</div>
									<div class="media align-items-center mb-4">
										<div class="media-body">
											<h3 class="mb-0">
												ผู้รับผิดชอบแปลง<span
													class="float-right display-5 opacity-5"> <a
													class="fa fa-user-plus"
													href="${pageContext.request.contextPath }/field/${field.fieldId}/userinfield/staffResponse"
													
													role="button"></a>
												</span>
											</h3>
											<c:forEach items="${listresponsibleinfield}"
												var="responsibleinfield" varStatus="loop">
												<p class="text-muted mb-0">
													${responsibleinfield.user.title}
													${responsibleinfield.user.firstName}
													${responsibleinfield.user.lastName}
													(${responsibleinfield.user.username })
													<a href="" class="fa fa-trash text-red" aria-hidden="true"
														role="button" id="delete"
														onclick="deleteinfield(${responsibleinfield.user.userId}, ${field.fieldId}, 'staffResponse')"></a>
												</p>
											</c:forEach>
										</div>
									</div>
									<div class="media align-items-center mb-4">
										<div class="media-body">
											<h3 class="mb-0">
												ผู้สำรวจแปลง<span class="float-right display-5 opacity-5">
													<a class="fa fa-user-plus"
													href="${pageContext.request.contextPath }/field/${field.fieldId}/userinfield/staffSurvey"
													role="button"></a>
												</span>
											</h3>
											<c:forEach items="${listsurveyinfield}"
												var="surveyinfield" varStatus="loop">
												<p class="text-muted mb-0">
													${surveyinfield.user.title}
													${surveyinfield.user.firstName}
													${surveyinfield.user.lastName}
													(${surveyinfield.user.username }) 
													<a href="" class="fa fa-trash text-red" aria-hidden="true"
														role="button" id="delete"
														onclick="deleteinfield(${surveyinfield.user.userId}, ${field.fieldId}, 'staffSurvey')"></a>
													
												</p>
											</c:forEach>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-6">
					<div class="card">
						<div class="card-body">
							<br>
							<div class="form-validation">

								<input type="hidden" id="fieldID" name="fieldID"
									value="${field.fieldId}">
								<h4 class="card-title"></h4>
								<div class="card-body">
									<div class="text-center">
										<span class="display-5"><i
											class="fa fa-sun-o gradient-3-text"></i></span>
										<h2 class="mt-3">เกษตรกร</h2>
									</div>
									<div class="media align-items-center mb-4">
										<div class="media-body">
											<h3 class="mb-0">
												เจ้าของแปลง<span class="float-right display-5 opacity-5">

												</span>
											</h3>
											<c:forEach items="${listownerinfield}" var="listownerinfield"
												varStatus="loop">
												<p class="text-muted mb-0">
													${listownerinfield.user.title}
													${listownerinfield.user.firstName}
													${listownerinfield.user.lastName}
													(${listownerinfield.user.username })</p>
											</c:forEach>
										</div>
									</div>
									<div class="media align-items-center mb-4">
										<div class="media-body">

											<h3 class="mb-0">
												สมาชิก<span class="float-right display-5 opacity-5">
													<a class="fa fa-user-plus"
													href="${pageContext.request.contextPath }/field/${field.fieldId}/userinfield/farmerSurvey"
													role="button"></a>
												</span>
											</h3>
											<c:forEach items="${listsurveyorinfield}"
												var="surveyorinfield" varStatus="loop">
												<p class="text-muted mb-0">
													${surveyorinfield.user.title}
													${surveyorinfield.user.firstName}
													${surveyorinfield.user.lastName}
													(${surveyorinfield.user.username })
													 <a href="" class="fa fa-trash text-red" aria-hidden="true"
														role="button" id="delete"
														onclick="deleteinfield(${surveyorinfield.user.userId}, ${field.fieldId}, 'farmerSurvey')"></a>
												</p>
											</c:forEach>
										</div>
									</div>
								</div>
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
	<script>
		function deleteinfield(uid, fid, role) {
			$.ajax({
				method : "GET",
				url : "${pageContext.request.contextPath}/field/" + fid + "/"
						+ role + "/delete/" + uid,
				success : function(response) {
					location.reload();
				}
			});
		}
		function saveinfield(uid, fid, role) {
			$.ajax({
				method : "GET",
				url : "${pageContext.request.contextPath}/field/" + fid + "/"
						+ role + "/save/" + uid,
				success : function(response) {
					location.reload();
				}
			});
		}
	</script>

	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
</body>
</html>