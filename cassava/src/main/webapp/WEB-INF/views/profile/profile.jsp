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
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">ข้อมูลผู้ใช้งาน</h4>

							<div class="form-validation">


								<div class="form-group row">
									<label class="col-lg-2 col-form-label" for="title">ชื่อ-สกุล</label>
									<div class="col-lg-6">
										<input type="text" class="form-control" id="user.title"
											name="user.title" placeholder=""
											value="${user.title} ${user.firstName} ${user.lastName}"
											readonly>
									</div>
								</div>

								<div class="form-group row">
									<label class="col-lg-2 col-form-label" for="username">ชื่อผู้ใช้</label>
									<div class="col-lg-6">
										<input type="text" class="form-control" id="user.username"
											name="user.username" placeholder="" value="${user.username}"
											readonly>
									</div>
								</div>
								<c:if test="${role.equals('staff')}">

									<div class="form-group row">
										<label class="col-lg-2 col-form-label" for="val-username">กำหนดสิทธิ์<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<div class="row">
												<c:forEach items="${roles}" var="role" varStatus="loop">
													<c:if test="${!role.nameTh.equals('เจ้าหน้าที่')}">
														<div class="form-group">
															<option>${role.nameTh}&nbsp;</option>
														</div>
													</c:if>
												</c:forEach>

											</div>
										</div>
									</div>
								</c:if>


							</div>
						</div>
					</div>
				</div>
				<c:if test="${role.equals('staff')}">
					<div class="col-lg-6">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">รายละเอียดผู้ใช้งาน</h4>
								<div class="basic-form">
									<form>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="position">ตำแหน่ง</label>
											<div class="col-lg-6">
												<input type="text" readonly="readonly"
													class="form-control-plaintext" id="user.staff.position"
													name="user.staff.position" placeholder=""
													value="${user.staff.position}">
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="division">ส่วนงาน</label>
											<div class="col-lg-6">
												<input type="text" readonly="readonly"
													class="form-control-plaintext" id="user.staff.division"
													name="user.staff.division" placeholder=""
													value="${user.staff.division}">
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="organization">หน่วยงาน</label>
											<div class="col-lg-6">
												<input type="text" readonly="readonly"
													class="form-control-plaintext"
													id="user.staff.organization.name"
													name="user.staff.organization.name" placeholder=""
													value="${user.staff.organization.name}">
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="phoneNo">เบอร์โทรศัพท์</label>
											<div class="col-lg-6">
												<input type="text" readonly="readonly"
													class="form-control-plaintext" id="user.phoneNo"
													name="user.phoneNo" placeholder="" value="${user.phoneNo} ">
											</div>
										</div>
									</form>
								</div>
							</div>
						</div>
					</div>
				</c:if>

				<c:if test="${role.equals('farmer')}">
					<div class="col-lg-6">
						<div class="card">
							<div class="card-body">
								<h4 class="card-title">รายละเอียดผู้ใช้งาน</h4>
								<div class="basic-form">
									<form>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="position">ตำแหน่ง</label>
											<div class="col-lg-6">
												<input type="text" readonly="readonly"
													class="form-control-plaintext" id="user.staff.position"
													name="user.staff.position" placeholder="" value="เกษตร">
											</div>
										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="division">ที่อยู่</label>
											<div class="col-lg-6">
												<input type="text" readonly="readonly"
													class="form-control-plaintext" id="farmer.address"
													name="farmer.address" placeholder=""
													value="${farmer.address}">
											</div>
										</div>

									</form>
								</div>
							</div>
						</div>
					</div>
				</c:if>

				<div class="col-lg-6">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">การใช้งานระบบ</h4>
							<div class="basic-form">
								<form>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="type">สถานะบัญชี</label>
										<div class="col-lg-6">
											<c:choose>
												<c:when test="${user.userStatus == 'active'}">
													<input type="text" readonly="readonly"
														class="form-control-plaintext" id="status" name="status"
														placeholder="" value="เปิดการใช้งาน">
												</c:when>
												<c:otherwise>
													<input type="text" readonly="readonly"
														class="form-control-plaintext" id="status" name="status"
														placeholder="" value="ปิดการใช้งาน">
												</c:otherwise>
											</c:choose>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label">วันที่ลงทะเบียน</label>
										<div class="col-lg-6">
											<input type="text" readonly="readonly"
												class="form-control-plaintext"
												value="${formatter.format(user.latestLogin)}">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label">วันที่เข้าใช้ครั้งสุดท้าย</label>
										<div class="col-lg-6">
											<input type="text" readonly="readonly"
												class="form-control-plaintext"
												value="${formatter.format(user.latestLogin)}">
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

	<script
		src="${pageContext.request.contextPath}/resources/plugins/common/common.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/settings.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/gleek.js"></script>
</body>

</html>