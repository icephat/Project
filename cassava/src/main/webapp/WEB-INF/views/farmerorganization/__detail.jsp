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
							<h4 class="card-title">เกษตรกรในความดูแล > เพิ่มเกษตรกร >
								สร้างข้อมูลเกษตรกรใหม่</h4>
							<div class="form-validation">


								<div class="form-group row">
									<label class="col-lg-4 col-form-label"
										for="farmer.user.usertitle">คำนำหน้า<span
										class="text-danger">*</span>
									</label>
									<div class="col-lg-6">
										<input type="text" class="form-control" id="user.title"
											name="user.title" placeholder="" value="${farmer.user.title}"
											disabled="disabled">
									</div>
								</div>
								<div class="form-group row">
									<label class="col-lg-4 col-form-label" for="user.name">ชื่อ<span
										class="text-danger">*</span>
									</label>
									<div class="col-lg-6">
										<input type="text" class="form-control" id="user.name"
											name="user.firstName" placeholder=""
											value="${farmer.user.firstName}" disabled="disabled">
									</div>
								</div>
								<div class="form-group row">
									<label class="col-lg-4 col-form-label" for="user.surname">นามสกุล<span
										class="text-danger">*</span>
									</label>
									<div class="col-lg-6">
										<input type="text" class="form-control" id="user.surname"
											name="user.lastName" placeholder=""
											value="${farmer.user.lastName}" disabled="disabled">
									</div>
								</div>

								<div class="form-validation">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="address">ที่อยู่<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="address"
												name="address" placeholder="" value="${farmer.address}"
												disabled="disabled">
										</div>
									</div>

									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">จังหวัด<span
											class="province.name">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="adderss"
												name="province" placeholder=""
												value="${farmer.subdistrict.district.province.name}"
												disabled="disabled">
										</div>

									</div>

									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="district">อำเภอ<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="district"
												name="district" placeholder=""
												value="${farmer.subdistrict.district.name}"
												disabled="disabled">
										</div>
									</div>
								</div>

								<div class="form-group row">
									<label class="col-lg-4 col-form-label" for="subdistrict">ตำบล<span
										class="text-danger">*</span>
									</label>
									<div class="col-lg-6">

										<input type="text" class="form-control" id="subdistrict"
											name="subdistrict" placeholder=""
											value="${farmer.subdistrict.name}" disabled="disabled">
									</div>

								</div>
							</div>



							<div class="form-group row">
								<label class="col-lg-4 col-form-label" for="user.phoneNo">เบอร์โทรศัพท์<span
									class="text-danger">*</span>
								</label>
								<div class="col-lg-6">
									<input type="text" class="form-control" id="user.phoneNo"
										name="user.phoneNo" placeholder=""
										value="${farmer.user.phoneNo}" disabled="disabled">
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-4 col-form-label" for="type">สถานะบัญชี<span
									class="text-danger">*</span>
								</label>
								<div class="col-lg-6">
									<input type="text" class="form-control" id="status"
										name="status" placeholder="" value="${status}"
										disabled="disabled">
								</div>
							</div>

							<div class="form-group row">
								<label class="col-lg-4 col-form-label" for="user.username">กำหนดบัญชีผู้ใช้<span
									class="text-danger">*</span>
								</label>
								<div class="col-lg-6">
									<input type="text" class="form-control" id="user.username"
										name="user.username" placeholder=""
										value="${farmer.user.username}" disabled="disabled">
								</div>
							</div>


							<div class="form-group row">
								<div class="col-lg-8 ml-auto">
									<a
										href="${pageContext.request.contextPath}/farmerorganization/search"
										class="btn btn-light" role="button">กลับ</a> <a
										href="${pageContext.request.contextPath}/farmerorganization/addorganization/${farmer.farmerId}"
										class="btn btn-success" role="button">เพิ่ม</a>
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

