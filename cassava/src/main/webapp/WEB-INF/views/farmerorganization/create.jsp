<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
			<!DOCTYPE html>
			<html lang="en">

			<head>
				<meta charset="utf-8">
				<meta http-equiv="X-UA-Compatible" content="IE=edge">
				<meta name="viewport" content="width=device-width,initial-scale=1">
				<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
				<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/image-uploader.min.css">
				<link
					href="https://fonts.googleapis.com/css?family=Lato:300,700|Montserrat:300,400,500,600,700|Source+Code+Pro&display=swap"
					rel="stylesheet">

				<link rel="stylesheet"
					href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
				<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
				<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
				<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

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
						<div class="row">
							<div class="col-12">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">เกษตรกรในความดูแล > เกษตรกรที่ไม่มีบัญชีผู้ใช้ >
											สร้างข้อมูลเกษตรกรใหม่</h4>
										<div class="form-validation">
											<form class="form-valide" id="farmerData"
												
												method="post" modelAttribute="farmer" enctype="multipart/form-data">
												<input type ="hidden" name="pdpastatus" value="Yes">
												<div class="form-group row">
													<label class="col-lg-4 col-form-label"
														for="farmer.user.usertitle">คำนำหน้า<span
															class="text-danger">*</span>
													</label>
													<div class="col-lg-6">
														<input type="text" class="form-control" name="user.title"
															placeholder="" required>
														<input class="inputerror" id="title" cssClass="error" disabled />
													</div>
												</div>
												<div class="form-group row">
													<label class="col-lg-4 col-form-label"
														for="user.firstName">ชื่อ<span class="text-danger">*</span>
													</label>
													<div class="col-lg-6">
														<input type="text" class="form-control" name="user.firstName"
															placeholder="" required><input class="inputerror"
															id="firstName" cssClass="error" disabled />
													</div>
												</div>
												<div class="form-group row">
													<label class="col-lg-4 col-form-label"
														for="user.lastName">นามสกุล<span class="text-danger">*</span>
													</label>
													<div class="col-lg-6">
														<input type="text" class="form-control" name="user.lastName"
															placeholder="" required><input class="inputerror"
															id="lastName" cssClass="error" disabled />
													</div>
												</div>

												<div class="form-group row">
													<label class="col-lg-4 col-form-label" for="address">ที่อยู่<span
															class="text-danger">*</span>
													</label>
													<div class="col-lg-6">
														<input type="text" class="form-control" name="address"
															placeholder="" required><input class="inputerror"
															id="address" cssClass="error" disabled />
													</div>
												</div>
												<div class="form-validation">
													<div class="form-group row">
														<label class="col-lg-4 col-form-label"
															for="val-username">จังหวัด<span class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<select name="subdistrict.district.province" id="province"
																class="form-control" required>
																<option value=""></option>
																<c:forEach items="${provinces}" var="province">
																	<option value="${province.provinceId}">
																		${province.name}</option>
																</c:forEach>
															</select>
															<input class="inputerror" id="provinces" cssClass = "error" disabled/>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-4 col-form-label" for="district">อำเภอ<span
																class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<select name="subdistrict.district.districtId" id="district"
																class="form-control" required>
																<option value=""></option>

															</select>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-4 col-form-label"
															for="subdistrict">ตำบล<span class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<select name="subdistrict.subdistrictId" id="subdistrict"
																class="form-control" required>
																<option value=""></option>

															</select>
														</div>
													</div>


												</div>
												<div class="form-group row">
													<label class="col-lg-4 col-form-label"
														for="user.phoneNo">เบอร์โทรศัพท์<span
															class="text-danger">*</span>
													</label>
													<div class="col-lg-6">
														<input type="text" class="form-control" name="user.phoneNo"
															placeholder="" required><input class="inputerror"
															id="phoneNo" cssClass="error" disabled />
													</div>
												</div>


												<div class="form-group row">
													<div class="col-lg-8 ml-auto">
														<a href="${pageContext.request.contextPath}/farmerorganization/index"
															class="btn btn-light" role="button">กลับ</a> <input
															type="submit" class="btn btn-primary" role="button"
															id="button" value="บันทึก">

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

						var isChange = false;
						$("#button").click(
							function () {
								var formData = new FormData($('#farmerData')[0]);
								$("#title").val("");
								$("#firstName").val("");
								$("#lastName").val("");
								$("#address").val("");
								$("#phoneNo").val("");
								$("#provinces").val("");
								
								$.ajax({
									url: "${pageContext.request.contextPath}/farmerorganization/save",
									type: 'POST',
									data: formData,
									dataType: 'json',
									success: function (response) {
										window.location.href = "${pageContext.request.contextPath}/farmerorganization/farmerinvalid";
									},
									error: function (response) {
										//alert(response["responseText"]);
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
						//$("#button")
						//		.click(
						//				function() {
						//					var formData = new FormData($('#farmerData')[0]);
						//					$("#title").val("");
						//					$("#firstName").val("");
						//					$("#lastName").val("");
						//					$("#address").val("");
						//					$("#phoneNo").val("");
						//					
						//					$
						//							.ajax({
						//								url : "${pageContext.request.contextPath}/farmerorganization/duplicateusername",
						//								type : 'POST',
						//								data : formData,
						//								dataType : 'json',
						//								success : function(response) {
						//									
						//									$
						//											.ajax({
						//												url : "${pageContext.request.contextPath}/farmerorganization/save",
						//												type : 'POST',
						//												data : formData,
						//												success : function(response) {
						//													window.location.href = "${pageContext.request.contextPath}/farmerorganization/farmerinvalid";
						//													alert("Save");
						//												},
						//												error : function(response) {
						//													alert("Error");
						//													//alert(response["responseText"]);
						//													const respJSON = response["responseText"];
						//													//alert(respJSON);
						//													const myObj = JSON
						//															.parse(respJSON);
						//
						//													for ( var key in myObj.body) {
						//														//if (p.hasOwnProperty(key)) {
						//														//alert(key+ " "+ $('#'+ key).val());
						//														$('#' + key).val(myObj.body[key]);
						//														//}
						//													}
						//
						//													return false;
						//												},
						//												cache : false,
						//												contentType : false,
						//												processData : false
						//											});
						//								},
						//								error : function(response) {
						//
						//									const respJSON = response["responseText"];
						//									const myObj = JSON
						//											.parse(respJSON);
						//alert(myObj.message);

						//								swal({
						//								title : "เกิดข้อผิดพลาด",
						//							text : myObj.message,
						//							icon : "warning",
						//							button : "ตกลง",
						//						});

						//						return false;
						//					},
						//					cache : false,
						//					contentType : false,
						//					processData : false
						//				});
						//		return false;
						//	});

						$("#province")
							.change(
								function () {
									//alert( "Handler for .change() called." );
									var selectedProvice = $(
										"#province option:selected").val();
									//alert(selectedProvice);
									if (selectedProvice == "") {
										$('#district').html("");
										$('#subdistrict').html("");
										return;
									}
									$
										.ajax({
											method: "GET",
											url: "${pageContext.request.contextPath}/province/"
												+ selectedProvice,
											success: function (response) {
												$('#district').html(response);
												var selectedDistrict = $(
													"#district option:selected")
													.val();
												var selectedDistrict = $(
													"#district option:selected")
													.val();
												$
													.ajax({
														method: "GET",
														url: "${pageContext.request.contextPath}/district/"
															+ selectedDistrict,
														success: function (
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
								function () {
									///alert("Handler for .change() called.");
									var selectedDistrict = $(
										"#district option:selected").val();
									$
										.ajax({
											method: "GET",
											url: "${pageContext.request.contextPath}/district/"
												+ selectedDistrict,
											success: function (response) {
												$('#subdistrict')
													.html(response);
											},

										});

								});

					});
				</script>

				<script src="${pageContext.request.contextPath}/resources/plugins/common/common.min.js"></script>
				<script src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
				<script src="${pageContext.request.contextPath}/resources/js/settings.js"></script>
				<script src="${pageContext.request.contextPath}/resources/js/gleek.js"></script>
				<script src="${pageContext.request.contextPath}/resources/js/styleSwitcher.js"></script>
				<grammarly-desktop-integration data-grammarly-shadow-root="true"></grammarly-desktop-integration>
			</body>

			</html>
