<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">

	<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/image-uploader.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,700|Montserrat:300,400,500,600,700|Source+Code+Pro&display=swap"
	rel="stylesheet">

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
				color:red;
				background-color:transparent;
				width: 300px;
			}
		
		
		
		</style>
</head>
<body>

	<!--**********************************
	            Content body start
	***********************************-->
	<div class="login-form-bg h-100">
		<div class="container h-100">
			<div class="row justify-content-center h-100">
				<div class="col-xl-6">
					<div class="form-input-content">
						<div class="card login-form mb-0">
							<div class="card-body pt-5">
								<a class="text-center">
									<h4> ลงทะเบียนสำหรับเจ้าหน้าที่<c:if test="${id != null}">
										หน่วยงาน ${organization}
									</c:if></h4>
								</a>
								<div class="form-validation">
									<form class="form-valide"
										 id="staffData"
										 modelAttribute="staffDTO"
										enctype="multipart/form-data">

										<input type="hidden" class="form-control"
											id="registercode.code" name="registercode.registerCodeId"
											value="${id}" placeholder="">

										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="username">ชื่อผู้ใช้<span
												class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input type="text" class="form-control" id="username"
													name="staff.user.username" placeholder="" value = "${userName}" readonly>
												
											</div>
										</div>

										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="usertitle">คำนำหน้า<span
												class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input type="text" class="form-control" id="usertitle"
													name="staff.user.title" placeholder="">
												<input class="inputerror" id="title" cssClass = "error" disabled/>
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="firstName">ชื่อ<span
												class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input type="text" class="form-control" 
													name="staff.user.firstName" placeholder="">
												<input class="inputerror" id="firstName" cssClass = "error" disabled/>
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="lastName">นามสกุล<span
												class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input type="text" class="form-control" 
													name="staff.user.lastName" placeholder="">
												<input class="inputerror" id="lastName" cssClass = "error" disabled/>
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="phoneNo">เบอร์โทรศัพท์<span
												class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input type="number" class="form-control" 
													name="staff.user.phoneNo" placeholder="">
												<input class="inputerror" id="phoneNo" cssClass = "error" disabled/>
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="position">ตำแหน่ง
											</label>
											<div class="col-lg-6">
												<input type="text" class="form-control" id="position"
													name="staff.position" placeholder="">
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="division">ส่วนงาน
											</label>
											<div class="col-lg-6">
												<input type="text" class="form-control" id="division"
													name="staff.division" placeholder="">
											</div>
										</div>
										
										<c:if test="${id == null}">
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="organization">หน่วยงาน<span
													class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<select name="staff.organization.organizationId"
														id="organization" class="form-control" required disable>
														<c:forEach items="${organizations}" var="organization">
															<option value="${organization.organizationId}">${organization.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
										</c:if>
									</form>
										<div class="form-group row">
											<div class="col-lg-8 ml-auto">
												<a href="${pageContext.request.contextPath}/"
													class="btn btn-light" role="button">ยกเลิก</a> <input
													type="submit" class="btn btn-primary" role="button" id="buttons"
													value="ลงทะเบียน">
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
<script>
	$("#buttons")
	.click(function() {
				var formData = new FormData($('#staffData')[0]);
				$("#title").val("");
				$("#firstName").val("");
				$("#lastName").val("");
				$("#address").val("");
				$("#phoneNo").val("");
				
				
				
			
								$
										.ajax({
											url : "${pageContext.request.contextPath}/register/staff/check",
											type : 'POST',
											data : formData,
											success : function(response) {
												//alert("fin");
												$
										.ajax({
											url : "${pageContext.request.contextPath}/register/staff/save",
											type : 'POST',
											data : formData,
											success : function(response) {
												window.location.href = "${pageContext.request.contextPath}/register/registerStatus";
							
											},
											error : function(response) {
												window.location.href = "${pageContext.request.contextPath}/register/status";
												
											},
											cache : false,
											contentType : false,
											processData : false
										});




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
													$('#' + key).val(myObj.body[key]);
													//}
													//alert(key)
												}

												return false;
											},
											cache : false,
											contentType : false,
											processData : false
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
	<grammarly-desktop-integration data-grammarly-shadow-root="true"></grammarly-desktop-integration>
</body>
</html>