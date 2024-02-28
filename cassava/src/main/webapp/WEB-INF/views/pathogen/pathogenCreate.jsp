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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

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
							<h4 class="card-title">เชื้อโรคพืช > เพิ่มข้อมูล</h4>
							<div class="form-validation">
								<form class="form-valide" id="Data" modelAttribute="pathogen">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" 
											for="val-username">ชื่อวิทยาศาสตร์<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="scientificName" placeholder="" required>
											<input class="inputerror" id="scientificName" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ประเภทเชื้อ<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<select name="pathogen.pathogentype" id="pathogentype"
												class="form-control" >
												
												<c:forEach items="${pathogentype}" var="pathogentype">
													<option value="${pathogentype.pathogenTypeId}">${pathogentype.name}</option>
												</c:forEach>
												
											</select>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a
												href="${pageContext.request.contextPath}/pathogen/index"
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
															url : "${pageContext.request.contextPath}/pathogen/save",
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
	<script
		src="${pageContext.request.contextPath}/resources/plugins/common/common.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/settings.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/gleek.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/styleSwitcher.js"></script>
	<script src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
		
	<script src="${pageContext.request.contextPath }/resources/plugins/validation/jquery.validate.min.js"></script>
	<script src="${pageContext.request.contextPath }/resources/plugins/validation/jquery.validate-init.js"></script>
	

	
	
	
	
</body>
</html>