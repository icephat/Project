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
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
	<style type="text/css">
		.inputerror {
			border: 0;
			color: red;
			background-color:transparent;
			width:500px;
		}
	</style>
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
							<h4 class="card-title">เชื้อโรคพืช > ${pathogen.scientificName }</h4>
							<div class="form-validation">
								<form class="form-valide"
									id="Data"
									method="post" modelAttribute="pathogen">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ชื่อวิทยาศาสตร์<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username"
												name="scientificName" placeholder=""
												value="${pathogen.scientificName }" required>
										<input class="inputerror" id="scientificName" cssClass="error" disabled />
										</div>
									</div>
									
									
									
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ประเภทเชื้อ<span
											class="text-danger">*</span>
										</label>
										
										
										<div class="col-lg-6">
											<form:select path="pathogen.pathogentype.pathogenTypeId"
												name="pathogentype" class="form-control">
												<form:options items="${pType}"  itemValue="pathogenTypeId" itemLabel="name"></form:options>
											</form:select>
										</div>
									</div>
									<input type="hidden" name="pathogenId" value="${id}">
									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a
												href="${pageContext.request.contextPath}/pathogen/"
												class="btn btn-light" role="button">กลับ</a> <input
												type="submit" class="btn btn-primary" role="button" id="button"

												value="บันทึก">
										</div>
									</div>
								</form>
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

	<!-- Custom Script -->
	<script>
		$(function() {

			var isChange = false;

			$("#button")
					.click(
							function() {
								var formData = new FormData($('#Data')[0]);
								//$("#pathogentype option:selected").val();
								$("#scientificName").val("");
								//$("#pathogentype").val("");
								
								

								
												$
														.ajax({
															url : "${pageContext.request.contextPath}/pathogen/update",
															type : 'POST',
															data : formData,
															success : function(response) {
																window.location.href = "${pageContext.request.contextPath}/pathogen/index";
															},
															error : function(response) {
																//alert(response["responseText"]);
																const respJSON = response["responseText"];
																//alert(respJSON);
																const myObj = JSON
																		.parse(respJSON);

																for ( var key in myObj.body) {
																	//if (p.hasOwnProperty(key)) {
																	//alert(key+ " "+ $('#'+ key).val());
																	$('#' + key)
																			.val(
																					myObj.body[key]);
																	//}
																}

																return false;
															},
															cache : false,
															contentType : false,
															processData : false
														});
											
								return false;
							});
		});

	</script>
	<script src="${pageContext.request.contextPath }/resources/plugins/validation/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/plugins/validation/jquery.validate-init.js"></script>
	
	
</body>
</html>