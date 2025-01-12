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
							<h4 class="card-title">ผู้ใช้ทั่วไป > รายละเอียด</h4>
							<div class="form-validation">
								<form class="form-valide"
									action="${pageContext.request.contextPath}/admin/update"
									method="post" modelAttribute="roleDTO"
									enctype="multipart/form-data">

									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="username">ชื่อผู้ใช้<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.username"
												name="user.username" placeholder=""
												value="${roleDTO1.user.username}">
										</div>
									</div>

									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="title">คำนำหน้า<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.title"
												name="user.title" placeholder="" value="${roleDTO1.user.title}">
										</div>
									</div>


									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="firstName">ชื่อ<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.firstName"
												name="user.firstName" placeholder=""
												value="${roleDTO1.user.firstName}">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="lastName">นามสกุล<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.lastName"
												name="user.lastName" placeholder=""
												value="${roleDTO1.user.lastName}">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="position">ตำแหน่ง<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.staff.position"
												name="user.staff.position" placeholder="" value="${roleDTO1.user.staff.position}">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="division">ส่วนงาน<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.staff.division"
												name="user.staff.division" placeholder="" value="${roleDTO1.user.staff.division}">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="organization">หน่วยงาน<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												id="user.staff.organization.name" name="user.staff.organization.name"
												placeholder="" value="${roleDTO1.user.staff.organization.name}">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="phoneNo">เบอร์โทรศัพท์<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="user.phoneNo"
												name="user.phoneNo" placeholder=""
												value="${roleDTO1.user.phoneNo}">
										</div>
									</div>

									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ประเภทผู้ใช้<span
											class="text-danger">*</span>
										</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										
										&nbsp;&nbsp;<div class="row">
											<c:forEach items="${roleList}" var="roleList"
													varStatus="loop">
													<div class="form-group">
														<div class="css-control css-control-primary css-checkbox">
															<c:set var="check"
																value="${roleList[2]}" />
															<c:choose>
																<c:when test="${check == 1}">
																	<input type="checkbox" class="css-control-input"
																		name="rolecheck[${loop.index}].checked"
																		checked>
																</c:when>
																<c:otherwise>
																	<input type="checkbox" class="css-control-input"
																		name="rolecheck[${loop.index}].checked">
																</c:otherwise>
															</c:choose>
															<input type="hidden" id="role.roleId"
																name="rolecheck[${loop.index}].role.roleId"
																value="${roleList[0]}"> <label
																for="roleId">${roleList[1]}</label>&nbsp;&nbsp;&nbsp;
														</div>
													</div>
												</c:forEach>
											
										</div>
										
										
										
									</div>

									<input type="hidden" name="role.roleId"
											value="${roleDTO1.role.roleId}">

									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="type">สถานะบัญชี<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">

											<select name="status" id="status" class="form-control">
												<c:forEach items="${statusList}" var="statusList">
													<option
														<c:if test = "${statusList==status}">
    														selected
   												   		</c:if>>${statusList}</option>
												</c:forEach>
											</select>

										</div>
									</div>
									
									<div class="form-group row">
										<label class="col-lg-4 col-form-label">วันที่ลงทะเบียน</label>
										<div class="col-lg-6">
											<input type="text" readonly="readonly"
												class="form-control-plaintext"
												value="${roleDTO1.user.latestLogin}">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label">วันที่ใช้ครั้งสุดท้าย</label>
										<div class="col-lg-6">
											<input type="text" readonly="readonly"
												class="form-control-plaintext"
												value="${roleDTO1.user.latestLogin}">
										</div>
									</div>
									<input type="hidden" name="user.userId" value="${roleDTO1.user.userId}">

									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a
												href="${pageContext.request.contextPath}/admin/approve"
												class="btn btn-light" role="button">กลับ</a> <input
												type="submit" class="btn btn-primary" role="button"
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

	<script
		src="${pageContext.request.contextPath}/resources/plugins/common/common.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/settings.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/gleek.js"></script>
</body>
</html>

