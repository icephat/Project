<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
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
							<h4 class="card-title">เจ้าหน้าที่ > ${staff.user.firstName} ${staff.user.lastName}</h4>
							<div class="form-validation">
								<form class="form-valide"
									 modelAttribute="staff" id="staffData"
									enctype="multipart/form-data">

									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="username">ชื่อผู้ใช้<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												id="user.username" name="user.username" placeholder=""
												value="${staff.user.username}" readonly>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="title">คำนำหน้า<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.title"
												name="user.title" placeholder="" value="${staff.user.title}">
											<input class="inputerror" id="title" cssClass="error" disabled />
										</div>
									</div>


									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="firstName">ชื่อ<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												id="user.firstName" name="user.firstName" placeholder=""
												value="${staff.user.firstName}">
											<input class="inputerror" id="firstName" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="lastName">นามสกุล<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												id="user.lastName" name="user.lastName" placeholder=""
												value="${staff.user.lastName}">
											<input class="inputerror" id="lastName" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="position">ตำแหน่ง
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="position"
												name="position" placeholder="" value="${staff.position}">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="division">ส่วนงาน
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="division"
												name="division" placeholder="" value="${staff.division}">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="organization">หน่วยงาน<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="organization.name"
												name="organization.name" placeholder=""
												value="${staff.organization.name}" readonly>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="phoneNo">เบอร์โทรศัพท์<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												id="user.phoneNo" name="user.phoneNo" placeholder=""
												value="${staff.user.phoneNo}" onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))||event.charCode == 45">
											<input class="inputerror" id="phoneNo" cssClass="error"
																disabled />
										</div>
									</div>
									<div class="form-group row">
													<label class="col-lg-4 col-form-label" for="type">สถานะบัญชี<span class="text-danger">*</span>
													</label>
													<div class="col-lg-6">
														<input type="text" class="form-control" value="รออนุมัติ"
															readonly>
													</div>
												</div>
									<input type="hidden" name="staffId" value="${staff.staffId}">
									
									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a href="${pageContext.request.contextPath}/staff/"
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
		<script>
						$(function () {
							$("#button").click(
								function () {
									var formData = new FormData($('#staffData')[0]);
									$("#title").val("");
									$("#firstName").val("");
									$("#lastName").val("");
									$.ajax({
										url: "${pageContext.request.contextPath}/staff/approve/update",
										type: 'POST',
										data: formData,
										dataType: 'json',
										success: function (response) {
											window.location.href = "${pageContext.request.contextPath}/staff/approve";
										},
										error: function (response) {
											const respJSON = response["responseText"];
											const myObj = JSON
												.parse(respJSON);
											for (var key in myObj.body) {
												$('#' + key).val(myObj.body[key]);
											}
											return false;
										},
										cache: false,
										contentType: false,
										processData: false
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
</body>
</html>

