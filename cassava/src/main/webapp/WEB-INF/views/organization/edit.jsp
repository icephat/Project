<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<!-- Custom Stylesheet -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">
	<link
		href="${pageContext.request.contextPath }/resources/plugins/tables/css/datatable/dataTables.bootstrap4.min.css"
		rel="stylesheet">
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>


	<style type="text/css">
		.inputerror {
			border: 0;
			color: red;
			background-color:transparent;
		}
	</style>



	<jsp:include page="../layouts/mainLayoutHead.jsp" />
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
							<h4 class="card-title">หน่วยงาน > เพิ่มข้อมูล</h4>
							<div class="form-validation">
								<form class="form-valide" id="organizationData" modelAttribute="organization">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="code">รหัสหน่วยงาน<span
												class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" name="code"
												value="${organization.code}" placeholder="">
											<input type="hidden" class="form-control" name="organizationId"
												value="${organization.organizationId}" placeholder="">
											<input class="inputerror" id="code" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="name">ชื่อหน่วยงาน<span
												class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" name="name"
												value="${organization.name}" placeholder="" required="required">
											<input class="inputerror" id="name" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="phone">เบอร์โทรศัพท์<span
												class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" name="phone"
												value="${organization.phone}" placeholder="" onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))||event.charCode == 45">
											<input class="inputerror" id="phone" cssClass="error" disabled />

										</div>
									</div>
									<!-- 
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="accessInfoLevel">ระดับการเข้าถึงข้อมูล</label>
										<div class="col-lg-6">
											<select class="form-control" name="accessInfoLevel"
												id="accessInfoLevel">
												<c:forEach items="${organizationTypes}" var="organizationType">
													<option value="${organizationType}">${organizationType}</option>
												</c:forEach>
											</select>
										</div>
									</div> -->
									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a href="${pageContext.request.contextPath}/organization/"
												class="btn btn-light" role="button">กลับ</a> <input type="submit"
												class="btn btn-primary" role="button" id="button" value="บันทึก">
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
	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
	<script>
		$(function () {
			$("#button").click(
				function () {
					var formData = new FormData($('#organizationData')[0]);
					$("#code").val("");
					$("#name").val("");
					$("#phone").val("");
					$.ajax({
						url: "${pageContext.request.contextPath}/organization/update",
						type: 'POST',
						data: formData,
						dataType: 'json',
						success: function (response) {
							window.location.href = "${pageContext.request.contextPath}/organization/";
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
</body>
</html>