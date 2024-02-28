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
							<div class="form-validation">
								<form class="form-valide"
									action="${pageContext.request.contextPath }/field/adduserinField/${field.fieldId}"
									id="adduserresponsible" modelAttribute="adduserresponsible"
									enctype="multipart/form-data">
									<h4 class="card-title"></h4>
									<div class="card-body">
										<div class="text-center">
											<span class="display-5"><i
												class=""></i></span>
											<h2 class="mt-3">Pest</h2>
										</div>
										<div class="media align-items-center mb-4">
											<div class="media-body">
												<h3 class="mb-0">
													PestID<span class="float-right display-5 opacity-5">
														<a class="fa fa-user-plus" href="${pageContext.request.contextPath}/disease/diseaseCreateP/${Disease.diseaseId}" role="button"
														id="adduser"></a>
													</span>
												</h3>
												<!--  showuserinfield -->
											</div>
										</div>
										
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<div class="col-6">
					<div class="card">
						<div class="card-body">
							<div class="form-validation">
								<form class="form-valide"
									action="${pageContext.request.contextPath }/disease/diseaseCreatePest"
									id="userinfield" modelAttribute="userinfield">
									<input type="hidden" id="fieldID" name="fieldID"
										value="${field.fieldId}">
									<h4 class="card-title"></h4>
										<div class="card-body">
											<div class="text-center">
												<span class="display-5"><i
													class=""></i></span>
												<h2 class="mt-3">Pathogen</h2>
											</div>
											<div class="media align-items-center mb-4">
											<div class="media-body">
												<h3 class="mb-0">
													PathogenID<span class="float-right display-5 opacity-5">
														<a class="fa fa-user-plus" href="" role="button"
														id="adduser"></a>
													</span>
												</h3>
												<!--  showuserinfield -->
											</div>
										</div>
											
										</div>
								</form>
							</div>
						</div>
					</div>
				</div>
				<a href="${pageContext.request.contextPath}/disease/edit/${Disease.diseaseId}"
									class="btn mb-1 btn-primary">ย้อนกลับ</a>
			</div>
		</div>
	</div>
	<!--**********************************
	     Content body end
	***********************************-->
	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
	<script>
	$(function(){
		$("#userID").click(function() {
			var formData = new FormData($('#userinfield')[0]);
			$.ajax({
				type : 'POST',
				data : formData,
				url : "${pageContext.request.contextPath }/field/addResponseinfield/"+${field.fieldId},
				success : function(response) {
					alert( "yes");
				},

			});
		});
	})
	</script>

	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
</body>
</html>