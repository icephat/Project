<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
			<!DOCTYPE html>
			<html lang="en">

			<head>
				<jsp:include page="../layouts/mainLayoutHead.jsp" />
				<!-- Custom Stylesheet -->
				<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
				<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">
				<link
					href="${pageContext.request.contextPath }/resources/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
					rel="stylesheet">
				<!-- Page plugins css -->
				<link
					href="${pageContext.request.contextPath }/resources/plugins/clockpicker/dist/jquery-clockpicker.min.css"
					rel="stylesheet">
				<!-- Color picker plugins css 
    <link href="./plugins/jquery-asColorPicker-master/css/asColorPicker.css" rel="stylesheet">-->
				<!-- Date picker plugins css -->
				<link
					href="${pageContext.request.contextPath }/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.css"
					rel="stylesheet">
				<!-- Daterange picker plugins css -->
				<link
					href="${pageContext.request.contextPath }/resources/plugins/timepicker/bootstrap-timepicker.min.css"
					rel="stylesheet">
				<link
					href="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.css"
					rel="stylesheet">
				<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>


			<style type="text/css">
		.inputerror {
			border: 0;
			color: red;
			background-color:transparent;
			width:300px;
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
							<div class="col">
								<div class="card gradient-1">
									<div class="card-body">
										<h3 class="card-title text-white">ออกรหัสลงทะเบียนสำหรับ</h3>
										<h2 class="text-white">เกษตรกร</h2>
										<span class="float-left ">
											<div class="bootstrap-modal">
												<button type="button" class="btn mb-1 btn-light opacity-5"
													data-toggle="modal" data-target="#exampleModal"
													data-whatever="@mdo">ออกรหัสลงทะเบียน</button>
												<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog"
													aria-labelledby="exampleModalLabel" aria-hidden="true">
													<div class="modal-dialog" role="document">
														<div class="modal-content">
															<div class="modal-header">
																<h5 class="modal-title" id="exampleModalLabel">
																	รหัสลงทะเบียนสำหรับเกษตรกร</h5>
																<button type="button" class="close" data-dismiss="modal"
																	aria-label="Close">
																	<span aria-hidden="true">&times;</span>
																</button>
															</div>
															<div class="modal-body text-gray">
																<form class="form-valide" id="registercodeData"
																	name="registercodeData"
																	modelAttribute="registercode">
																	<div class="form-group">
																		<c:choose>
																			<c:when test="${organizations.size()==1}">
																				<label for="recipient-name"
																					class="col-form-label ">หน่วยงาน</label>
																				<input type="text" class="form-control"
																					value="123 ${organizations[0].name}"
																					readonly>
																				<input type="hidden"
																					class="form-control"
																					id="organization.organizationId"
																					name="organization.organizationId"
																					value=${organizations[0].organizationId}>
																			</c:when>
																			<c:otherwise>
																				<label for="recipient-name"
																					class="col-form-label ">เลือกหน่วยงาน</label>
																				<select
																					name="organization.organizationId"
																					id="registerCodeId"
																					class="form-control" required>
																					<c:forEach items="${organizations}"
																						var="organization">
																						<option
																							value="${organization.organizationId}">
																							${organization.name}
																						</option>
																					</c:forEach>
																				</select>
																			</c:otherwise>
																		</c:choose>
																	</div>
																	<div class="form-group">
																		<label for="recipient-name"
																			class="col-form-label ">จำกัดจำนวนคน
																			(1-100)</label>
																		<input type="text" class="form-control" id="numUserPermit"
																			name="numUserPermit">

																		<!--  <p id="numUserPermit" class="inputerror"></p>-->
																		<input class="inputerror" id="numUserPermitCheck" cssClass="error" disabled />
																	</div>
																	<div class="form-group">
																		<label for="message-text"
																			class="col-form-label">ช่วงเวลาการใช้รหัส
																			(วันที่เริ่ม - วันหมดอายุ)</label>
																		<div class="input-daterange input-group"
																			id="date-range" name="date">
																			<input type="text" class="form-control"
																				name="startDate" id="startDate"
																				required="required">
																			<span
																				class="input-group-addon bg-info b-0 text-white">
																				จนถึงวันที </span>
																			<input type="text" class="form-control"
																				name="expireDate" id="expireDate"
																				required="required">
																		</div>
																		<!--  <p id="date" class="inputerror"></p>-->
																		<input class="inputerror" id="date" cssClass="error" disabled />
																	</div>
																	<input type="hidden" name="userType" value="Farmer">
																</form>
																<div class="modal-footer">
																	<button type="button" class="btn btn-secondary"
																		data-dismiss="modal">ปิด</button>
																	<input type="submit" class="btn btn-primary"
																		data-toggle="modal" data-target="#exampleCode"
																		data-whatever="@mdo" role="button" id="button"
																		value="ออกรหัส">
																</div>
															</div>
														</div>
													</div>
												</div>

											</div>
										</span>
									</div>

								</div>
							</div>
						</div>
						<div class="row">
							<c:forEach items="${registcodes}" var="registcode" varStatus="loop">
								<div class="col-3">
									<div class="card card-widget">
										<div class="card-body">
											<p align="center"><img alt="" class=" thumbnail rounded-circle mt-4"
													width="150" height="150"
													src="data:image/jpeg;base64,${codes[loop.index] }">
											</p>
											<h5 align="center">${registcode.code }</h5>

											<span>${formatter.format(registcode.startDate) } -
												${formatter.format(registcode.expireDate) } </span>
											<div class="mt-4">
												<h6>
													ใช้แล้ว (${registcode.numUseRegist }/${registcode.numUserPermit
													})<span class="pull-right">${percentList[loop.index]}%</span>
												</h6>
												<div class="progress mb-3" style="height: 7px">
													<div class="progress-bar gradient-1"
														style="width: ${percentList[loop.index]}%;" role="progressbar">
														<span class="sr-only">${percentList[loop.index]}%
															Complete</span>
													</div>
												</div>
											</div>
											<div class="mt-4">
												<h6 class="m-t-10 text-muted">
													ออกโดย : ${registcode.user.firstName}
													${registcode.user.lastName}<span class="pull-right" <c:choose>
														<c:when test="${statusList[loop.index].equals('กำลังใช้งาน')}">
															style="color:green"
														</c:when>
														<c:when
															test="${statusList[loop.index].equals('ยังไม่เริ่มใช้งาน')}">
															style="color:orange"
														</c:when>
														<c:otherwise>
															style="color:red"
														</c:otherwise>
														</c:choose>
														>${statusList[loop.index]}
													</span>
												</h6>
											</div>
											<div class="mt-4">
												<h6 class="m-t-10 text-muted">
													หน่วยงาน : ${registcode.organization.name }<span
														class="pull-right text-red">
														<a class="pull-right text-red"
															href="${pageContext.request.contextPath}/registfarmercode/delete/${registcode.registerCodeId }"
															onclick="return confirm('คุณยืนยันที่จะลบหรือไม่');">
															<i class="fa fa-trash" aria-hidden="true"></i>
														</a></span>
												</h6>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
				<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
				<script>
					$(function () {

						var isChange = false;

						$("#button")
							.click(
								function () {
									$('#numUserPermitCheck').val('');
									$('#date').val('');
									let x = document.getElementById("numUserPermit").value;
									let y = new Date(document.getElementById("startDate").value);
									let z = new Date(document.getElementById("expireDate").value);


									function dateIsValid(date) {
										return date instanceof Date && !isNaN(date);
									}

									
									function isInt(value) {
										  return !isNaN(value) && (function(x) { return (x | 0) === x; })(parseFloat(value))
										}
									
									if (!isInt(x)||x == "" || !(dateIsValid(y) && dateIsValid(z))) {
										if (x == "") {
											$('#numUserPermitCheck').val('กรุณากรอกจำนวนให้ครบ');
										}else if(!isInt(x)){
											$('#numUserPermitCheck').val('กรุณากรอกจำนวนเป็นจำนวนเต็ม');
										}
										if ( !(dateIsValid(y) && dateIsValid(z))) {
											$('#date').val('กรุณากรอกข้อมูลวันที่ให้ครบ');
										} 
										return false;
									}
									var formData = new FormData($('#registercodeData')[0]);

									$.ajax({
										url: "${pageContext.request.contextPath}/registfarmercode/create",
										type: 'POST',
										data: formData,
										success: function (response) {
											window.location.href = "${pageContext.request.contextPath}/registfarmercode/";
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
					});
				</script>
				<script
					src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
				<script
					src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js">
					</script>
				<script
					src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js">
					</script>
				<script
					src="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.js">
					</script>
				<script
					src="${pageContext.request.contextPath }/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.js">
					</script>
				<script src="${pageContext.request.contextPath }/resources/plugins/moment/moment.js"></script>
				<script
					src="${pageContext.request.contextPath }/resources/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js">
					</script>
				<!-- Clock Plugin JavaScript -->
				<script
					src="${pageContext.request.contextPath }/resources/plugins/clockpicker/dist/jquery-clockpicker.min.js">
					</script>
				<!-- Color Picker Plugin JavaScript -->
				<script
					src="${pageContext.request.contextPath }/resources/plugins/jquery-asColorPicker-master/libs/jquery-asColor.js">
					</script>
				<script
					src="${pageContext.request.contextPath }/resources/plugins/jquery-asColorPicker-master/libs/jquery-asGradient.js">
					</script>
				<script
					src="${pageContext.request.contextPath }/resources/plugins/jquery-asColorPicker-master/dist/jquery-asColorPicker.min.js">
					</script>
				<!-- Date Picker Plugin JavaScript -->
				<script
					src="${pageContext.request.contextPath }/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.js'">
					</script>
				<!-- Date range Plugin JavaScript -->
				<script
					src="${pageContext.request.contextPath }/resources/plugins/timepicker/bootstrap-timepicker.min.js"></script>
				<script
					src="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.js">
					</script>
				<script
					src="${pageContext.request.contextPath }/resources/js/plugins-init/form-pickers-init.js"></script>
			</body>

			</html>