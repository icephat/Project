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
	href="${pageContext.request.contextPath}/resources/css/image-uploader.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,700|Montserrat:300,400,500,600,700|Source+Code+Pro&display=swap"
	rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>	
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
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<!--  <h4 class="card-title">เกษตรกรในความดูแล > เพิ่มเกษตรกร >
								สร้างข้อมูลเกษตรกรใหม่</h4> -->
								
							<h4 class="card-title">เกษตรกร > ${farmer.user.firstName} ${farmer.user.lastName}</h4>
							<div class="form-validation">
								<form class="form-valide"
									id="famerData" modelAttribute="farmer"
									enctype="multipart/form-data">

									<input type="hidden" name="farmerId" value="${farmer.farmerId}">
									<input type="hidden" name="pdpastatus" value="${farmer.pdpastatus}">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="user.username">บัญชีผู้ใช้<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.username"
												name="user.username" placeholder=""
												value="${farmer.user.username}" readonly>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="user.title">คำนำหน้า
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.title"
												name="user.title" placeholder=""
												value="${farmer.user.title}">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="user.firstName">ชื่อ
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.firstName"
												name="user.firstName" placeholder=""
												value="${farmer.user.firstName}">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="user.lastName">นามสกุล
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.lastName"
												name="user.lastName" placeholder=""
												value="${farmer.user.lastName}">
										</div>
									</div>
									<!--  
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="idcard">รหัสบัตรประชาชน<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="idcard"
												name="idcard" placeholder="" value="${farmer.idcard}">
										</div>
									</div>
									-->
									<div class="form-validation">
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="address">ที่อยู่<span
												class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<input type="text" class="form-control" 
													name="address" placeholder="" value="${farmer.address}">
												<input class="inputerror" id="address" cssClass="error" disabled />
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="val-username">จังหวัด<span
												class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<select name="subdistrict.district.province" id="province"
													class="form-control" required>
													<c:forEach items="${provinces}" var="province">
														<option value="${province.provinceId}"
															<c:if test = "${province.provinceId==provinceFarmer.provinceId}">
		    													selected
		   													</c:if>>${province.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="val-username">อำเภอ
												<span class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<select name="subdistrict.district.districtId" id="district"
													class="form-control" required>
													<c:forEach items="${district}" var="district">
														<option value="${district.districtId}"
															<c:if test = "${district.districtId==districtFarmer.districtId}">
    													selected
   												   </c:if>>${district.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="val-username">ตำบล<span
												class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<select name="subdistrict.subdistrictId" id="subdistrict"
													class="form-control" required>
													<c:forEach items="${subdistrict}" var="subdistrict">
														<option value="${subdistrict.subdistrictId}"
															<c:if test = "${subdistrict.subdistrictId==subdistrictFarmer.subdistrictId}">
    													selected
   												   </c:if>>${subdistrict.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="user.phoneNo">เบอร์โทรศัพท์
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.phoneNo"
												name="user.phoneNo" placeholder=""
												value="${farmer.user.phoneNo}">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="type">สถานะบัญชี<span class="text-danger">*</span></label>										
										<div class="col-lg-6">
											<form:select path="farmer.user.userStatus" class="form-control">
											    <form:options items="${statusMap}" />
											</form:select>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a href="${pageContext.request.contextPath}/farmer/"
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
									var formData = new FormData($('#famerData')[0]);
									$("#address").val("");
									$.ajax({
										url: "${pageContext.request.contextPath}/farmer/update",
										type: 'POST',
										data: formData,
										dataType: 'json',
										success: function (response) {
											window.location.href = "${pageContext.request.contextPath}/farmer/";
										},
										error: function (response) {
											alert(response["responseText"]);
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
	<script>
		$(function() {
			$("#province").change(
							function() {
								//alert( "Handler for .change() called." );
								var selectedProvice = $("#province option:selected").val();								
								//alert(selectedProvice);								
								if (selectedProvice == "") {
									$('#district').html("");
									$('#subdistrict').html("");
									return;
								}
								$
										.ajax({
											method : "GET",
											url : "${pageContext.request.contextPath}/province/"
													+ selectedProvice,
											success : function(response) {
												$('#district').html(response);
												var selectedDistrict = $(
														"#district option:selected")
														.val();
												var selectedDistrict = $(
														"#district option:selected")
														.val();
												$
														.ajax({
															method : "GET",
															url : "${pageContext.request.contextPath}/district/"
																	+ selectedDistrict,
															success : function(
																	response) {
																$(
																		'#subdistrict')
																		.html(
																				response);
															},

														});
											},

										});

							});

			$("#district")
					.change(
							function() {
								///alert("Handler for .change() called.");
								var selectedDistrict = $(
										"#district option:selected").val();
								$
										.ajax({
											method : "GET",
											url : "${pageContext.request.contextPath}/district/"
													+ selectedDistrict,
											success : function(response) {
												$('#subdistrict')
														.html(response);
											},

										});

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

