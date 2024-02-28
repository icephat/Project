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
</head>
<body>
	<jsp:include page="../layouts/mainLayoutBodyHeader.jsp" />
	<!--**********************************
	            Content body start
	***********************************-->
	<div class="content-body">
		<div class="container-fluid">
			<form class="form-valide" method="post" modelAttribute="farmer"
				enctype="multipart/form-data">
				<div class="row">
					<div class="col-12">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">เกษตรกรในหน่วยงาน > รายละเอียด</h4>
								<div class="form-validation">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="user.username">บัญชีผู้ใช้</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.username"
												name="user.username" placeholder=""
												value="${farmer.user.username}" disabled="disabled">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="user.title">คำนำหน้า</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.title"
												name="user.title" placeholder=""
												value="${farmer.user.title}" disabled="disabled">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="user.firstName">ชื่อ</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.firstName"
												name="user.firstName" placeholder=""
												value="${farmer.user.firstName}" disabled="disabled">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="user.lastName">นามสกุล</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.lastName"
												name="user.lastName" placeholder=""
												value="${farmer.user.lastName}" disabled="disabled">
										</div>
									</div>

									<div class="form-validation">
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="adderss">ที่อยู่</label>
											<div class="col-lg-6">
												<input type="text" class="form-control" id="address"
													name="address" placeholder="" value="${farmer.address}"
													disabled="disabled">
											</div>
										</div>


										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="subdistrict">ตำบล</label>
											<div class="col-lg-6">
	
												<input type="text" class="form-control" id="subdistrict"
													name="subdistrict" placeholder=""
													value="${farmer.subdistrict.name}" disabled="disabled">
											</div>
	
										</div>
										

										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="district">อำเภอ</label>
											<div class="col-lg-6">
												<input type="text" class="form-control" id="district"
													name="district" placeholder=""
													value="${farmer.subdistrict.district.name}"
													disabled="disabled">
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="val-username">จังหวัด</label>
											<div class="col-lg-6">
												<input type="text" class="form-control" id="adderss"
													name="province" placeholder=""
													value="${farmer.subdistrict.district.province.name}"
													disabled="disabled">
											</div>

										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="user.phoneNo">เบอร์โทรศัพท์</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.phoneNo"
												name="user.phoneNo" placeholder=""
												value="${farmer.user.phoneNo}" disabled="disabled">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="type">สถานะบัญชี</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="status"
												name="status" placeholder="" value="${status}"
												disabled="disabled">
										</div>
									</div>
	
									<input type="hidden" name="farmerId" value="${farmer.farmerId}">
	
									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a href="${pageContext.request.contextPath}/farmerorganization/index"
												class="btn btn-light" role="button">กลับ</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
	</div>

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

