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
<link
	href="${pageContext.request.contextPath }/resources/plugins/tables/css/datatable/dataTables.bootstrap4.min.css"
	rel="stylesheet">
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
							<h4 class="card-title">การสำรวจ > เพิ่มข้อมูล > การเพาะปลูก
								${plantingName} (${plantingCode})</h4>
							<div class="bootstrap-modal">
								<p align="right">
									<!-- Button trigger modal -->
									<button type="button" class="btn btn-warning"
										data-toggle="modal" data-target="#exampleModalCenter">
										<i class="fa fa-info-circle" aria-hidden="true"></i>
										ข้อมูลเพิ่มเติม
									</button>
								</p>
								<!-- Modal -->
								<div class="modal fade" id="exampleModalCenter">
									<div class="modal-dialog modal-dialog-centered" role="document">
										<div class="modal-content">
											<div class="modal-header">
												<h5 class="modal-title">วิธีการสำรวจ</h5>
												<button type="button" class="close" data-dismiss="modal">
													<span>&times;</span>
											</div>
											<div class="modal-body">
												<p>
													การสำรวจแบ่งเป็น 5 จุด จุดละ 20 ต้น <br> การสำรวจโรค -
													สำรวจระดับความรุนแรงของโรค ตั้งแต่ระดับ 0-5 <br>
													การสำรวจศัตรูพืช - สำรวจเปอร์เซนต์ความเสียหายของศัตรูพืช
													0-100 % <br> การสำรวจศัตรูธรรมชาติ -
													สำรวจเปอร์เซนต์ที่พบศัตรูธรรมชาติ 0-100 %
												</p>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-light"
													data-dismiss="modal">Close</button>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="form-validation">
								<form class="form-valide"
								id="surveyDTOData"
									modelAttribute="surveyDTO">
									<!-- One "tab" for each step in the form: -->

									<h4 align="center">สภาพทั่วไป</h4>
									<input type="hidden" class="form-control" id="plantingId"
										name="survey.planting.plantingId" value="${plantingId}">
									<div class="form-group row">
										<div class="col-lg-4">
											<div class="card">
												<div class="card-body">
													<h4 class="card-title">สภาพแวดล้อม</h4>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="date">วันที่สำรวจ<span
															class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<input type="date" class="form-control" 
																name="surveyDate" min=${plantingDate}>
															<input class="inputerror" id="date" cssClass = "error" disabled/>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="besidePlant">พืชแปลงข้างเคียง
														</label>
														<div class="col-lg-6">
															<input type="text" class="form-control" id="besidePlant"
																name="survey.besidePlant">
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="soilType">ชนิดดิน
														</label>
														<div class="col-lg-6">
															<input type="text" class="form-control" id="soilType"
																name="survey.soilType">
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="weed">วัชพืชหลักที่พบ<span
															class="text-danger"></span>
														</label>
														<div class="col-lg-6">
															<input type="text" class="form-control" id="weed"
																name="survey.weed">
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label"
															for="percentDamageFromHerbicide">ความเสียหายจากสารกำจัดวัชพืช(%)<span
															class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<select name="survey.percentDamageFromHerbicide"
																 class="form-control"
																>
																<option value="" hidden disabled selected>เลือก</option>
																<c:forEach items="${percentDamageFromHerbicide}"
																	var="percentDamageFromHerbicide">
																	<option value="${percentDamageFromHerbicide}">${percentDamageFromHerbicide}</option>
																</c:forEach>
															</select>
															<input class="inputerror" id="percentDamageFromHerbicide" cssClass = "error" disabled/>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-lg-4">
											<div class="card">
												<div class="card-body">
													<h4 class="card-title">สภาพอากาศ</h4>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="val-username">อุณหภูมิ
															(C)<span class="text-danger">*</span>
														</label>
														<input type="hidden" class="form-control" 
																name="survey.temperature" value="0"  >
														<div class="col-lg-6">
															
															<input type="number" class="form-control" id="temperatureCheck"
																name="temperature" value="">
															<input class="inputerror" id="temperature" cssClass = "error" disabled/>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="val-username">ความชื้นสัมพัทธ์(%)<span
															class="text-danger">*</span>
														</label>
														<input type="hidden" class="form-control" 
																name="survey.humidity" value="0" id="humidityCheck">
														<div class="col-lg-6">
															
															<input type="number" class="form-control" 
																name="humidity" value="">
															<input class="inputerror" id="humidity" cssClass = "error" disabled/>
														</div>
													</div>

													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="rainType">สภาพฝน<span
															class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<select name="survey.rainType"
																class="form-control" >
																<option value="" hidden disabled selected>เลือก</option>
																<c:forEach items="${rainType}" var="rainType">
																	<option value="${rainType}">${rainType}</option>
																</c:forEach>
															</select>
														<input class="inputerror" id="rainType" cssClass = "error" disabled/>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="sunlightType">สภาพแดด<span
															class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<select name="survey.sunlightType" 
																class="form-control" >
																<option value="" hidden disabled selected>เลือก</option>
																<c:forEach items="${sunlightType}" var="sunlightType">
																	<option value="${sunlightType}">${sunlightType}</option>
																</c:forEach>
															</select>
															<input class="inputerror" id="sunlightType" cssClass = "error" disabled/>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="dewType">สภาพน้ำค้าง<span
															class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<select name="survey.dewType" 
																class="form-control" >
																<option value="" hidden disabled selected>เลือก</option>
																<c:forEach items="${dewType}" var="dewType">
																	<option value="${dewType}">${dewType}</option>
																</c:forEach>
															</select>
														<input class="inputerror" id="dewType" cssClass = "error" disabled/>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-lg-4">
											<div class="card">
												<div class="card-body">
													<h4 class="card-title">เจ้าของภาพ</h4>
													<div class="form-group row">
														<label class="col-lg-4 col-form-label"
															for="userByImgOwner">เจ้าของภาพ<span
															class="text-danger">*</span></label>
														<div class="col-lg-6">
															<select name="survey.userByImgOwner.userId"
																class="form-control"
																onchange='CheckimgOwnerOther(this.value);' >
																<option value="" hidden disabled selected>เลือก</option>
																<c:forEach items="${userInFields}" var="userInField">
																	<option value="${userInField.user.userId}">${userInField.user.firstName}
																		${userInField.user.lastName}</option>
																</c:forEach>
																<option value="0">อื่นๆ</option>
															</select>
														<input class="inputerror" id="userByImgOwner" cssClass = "error" disabled/>
														</div>
													</div>
													<div class="form-group row" style='display: none;'
														id="imgOwner">

														<div class="col-lg-12 row">
															<label class="col-lg-6 col-form-label"
																for="imgOwnerOther">เจ้าของภาพอื่นๆ </label> <input
																type="text" name="survey.imgOwnerOther"
																id="imgOwnerOther" class="form-control col-lg-6">
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-4 col-form-label"
															for="userByImgPhotographer">ผู้ถ่ายภาพ <span
															class="text-danger">*</span></label>
														<div class="col-lg-6">
															<select name="survey.userByImgPhotographer.userId"
																 class="form-control"
																onchange='CheckimgPhotographerOther(this.value);'
																>
																<option value="" hidden disabled selected>เลือก</option>
																<c:forEach items="${userInFields}" var="userInField">
																	<option value="${userInField.user.userId}">${userInField.user.firstName}
																		${userInField.user.lastName}</option>
																</c:forEach>
																<option value="0">อื่นๆ</option>
															</select>
														<input class="inputerror" id="userByImgPhotographer" cssClass = "error" disabled/>
														</div>
													</div>
													<div class="form-group row" style='display: none;'
														id="imgPhotographer">

														<div class="col-lg-12 row">
															<label class="col-lg-6 col-form-label"
																for="imgPhotographerOther">ผู้ถ่ายภาพอื่นๆ </label> <input
																type="text" name="survey.imgPhotographerOther"
																id="imgPhotographerOther" class="form-control col-lg-6">
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-lg-3 col-form-label" for="note">รายละเอียดเพิ่มเติม</label>
										<div class="col-lg-8">
											<input name="survey.note" id="note" class="form-control">
										</div>
									</div>
									<h4 align="center">เลือกสิ่งสำรวจ</h4>
									<div class="form-group row">
										<div class="col-lg-4">
											<div class="card">
												<div class="card-body">
													<div class="row col-12 ">
														<div class="col-6">
															<h4 class="card-title">โรคพืช</h4>
														</div>
													</div>
													<div class="form-group">
														<div class="form-check mb-3">
															<label class="form-check-label" for="targetOfSurveyId">
																<input type="checkbox" class="form-check-input"
																id="selectedAllDisease"> เลือกทั้งหมด
															</label>
														</div>
													</div>
													<div class="form-group">
														<c:forEach items="${diseaseIdList}" var="diseaseId"
															varStatus="loop">
															<div class="form-check mb-3">
																<label class="form-check-label" for="targetOfSurveyId">
																	<input type="checkbox" class="form-check-input"
																	id="surveytargetDiseases${loop.index}"
																	name="surveytargetDiseases[${loop.index}].checked">
																	<input type="hidden"
																	id="targetOfSurvey.targetOfSurveyId"
																	name="surveytargetDiseases[${loop.index}].targetofsurvey.targetOfSurveyId"
																	value="${diseaseId}">
																	${diseaseNameList[loop.index]}
																</label>
															</div>
														</c:forEach>
													</div>
												</div>
											</div>
										</div>
										<div class="col-lg-4">
											<div class="card">
												<div class="card-body">
													<div class="row col-12 ">
														<div class="col-6">
															<h4 class="card-title">ศัตรูพืช</h4>
														</div>
													</div>
													<div class="form-group">
														<div class="form-check mb-3">
															<label class="form-check-label" for="targetOfSurveyId">
																<input type="checkbox" class="form-check-input"
																id="selectedAllPest"> เลือกทั้งหมด
															</label>
														</div>
													</div>
													<div class="form-group">
														<c:forEach items="${pestIdList}" var="pestId"
															varStatus="loop">
															<div class="form-check mb-3">
																<label class="form-check-label" for="targetOfSurveyId">
																	<input type="checkbox" class="form-check-input"
																	id="surveytargetPestPhases${loop.index}"
																	name="surveytargetPestPhases[${loop.index}].checked">
																	<input type="hidden"
																	id="targetOfSurvey.targetOfSurveyId"
																	name="surveytargetPestPhases[${loop.index}].targetofsurvey.targetOfSurveyId"
																	value="${pestId}"> ${pestNameList[loop.index]}
																</label>
															</div>
														</c:forEach>
													</div>
												</div>
											</div>
										</div>
										<div class="col-lg-4">
											<div class="card">
												<div class="card-body">
													<div class="row col-12 ">
														<div class="col-6">
															<h4 class="card-title">ศัตรูธรรมชาติ</h4>
														</div>
													</div>
													<div class="form-group">
														<div class="form-check mb-3">
															<label class="form-check-label" for="targetOfSurveyId">
																<input type="checkbox" class="form-check-input"
																id="selectedAllNat"> เลือกทั้งหมด
															</label>
														</div>
													</div>
													<div class="form-group">													
														<c:forEach items="${naturalEnemyList}"
															var="naturalEnemy" varStatus="loop">
															<div class="form-check mb-3">

																<label class="form-check-label" for="targetOfSurveyId">
																	<input type="checkbox" class="form-check-input"
																	id="surveytargetNaturalEnemies${loop.index}"
																	name="surveytargetNaturalEnemies[${loop.index}].checked">
																	<input type="hidden"
																	id="targetOfSurvey.targetOfSurveyId"
																	name="surveytargetNaturalEnemies[${loop.index}].targetofsurvey.targetOfSurveyId"
																	value="${naturalEnemyList[loop.index][0]}">
																	${naturalEnemyList[loop.index][1]}
																</label>
															</div>
														</c:forEach>
													</div>
												</div>
											</div>
										</div>
									</div>


									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a href="${pageContext.request.contextPath}/survey/planting"
												class="btn btn-light" role="button">กลับ</a> <input
												type="submit" class="btn btn-primary" role="button" id="button"
												value="บันทึก">
										</div>
									</div>

								</form>
							</div>
						</div>
					</div>
					<span id="result"></span>
					<input type="hidden" id="diseaseSize" value="${diseaseListSize}">
					<input type="hidden" id="pestSize" value="${pestListSize }">
					<input type="hidden" id="natSize" value="${naturalEnemyList.size()}">
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
	<script
		src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
	<script>
		$(function () {
		
			$("#button").click(
				
				
				function () {
				$("#date").val("");
				$("#percentDamageFromHerbicide").val("");
				$("#temperature").val("");
				$("#humidity").val("");
				$("#rainType").val("");
				$("#sunlightType").val("");
				$("#dewType").val("");
				$("#userByImgOwner").val("");
				$("#userByImgPhotographer").val("");
					
					
					
					var formData = new FormData($('#surveyDTOData')[0]);
					$.ajax({
						url: "${pageContext.request.contextPath}/survey/check",
						type: 'POST',
						data: formData,
						dataType: 'json',
						success: function (response) {
							$.ajax({
								url: "${pageContext.request.contextPath}/survey/save",
								type: 'POST',
								data: formData,
								dataType: 'json',
								success: function (response) {
									////alert(response["responseText"]);
									const id = response["body"];
									window.location.href = "${pageContext.request.contextPath}/survey/point/"+id;
								},
						error: function (response) {
						
							//alert(response["responseText"]);
							
							
						},
						cache: false,
						contentType: false,
						processData: false
					});
							//window.location.href = "${pageContext.request.contextPath}/survey/point/${id}";
						},
						error: function (response) {
						
							//alert(response["responseText"]);
							
							const respJSON = response["responseText"];
							
							const myObj = JSON.parse(respJSON);
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
	<script type="text/javascript">
		function CheckimgPhotographerOther(val) {
			var element = document.getElementById('imgPhotographer');
			var element2 = document.getElementById('imgPhotographerOther');			
			if (val == '0') {
				element.style.display = 'block';
			} else {
				element.style.display = 'none';
				element2.value = "";
			}
		}
		function CheckimgOwnerOther(val) {
			var element = document.getElementById('imgOwner');
			var element2 = document.getElementById('imgOwnerOther');
			if (val == '0') {
				element.style.display = 'block';
			} else {
				element.style.display = 'none';
				element2.value = "";
			}
		}
	</script>
	<script>
		$(function() {
			var diseaseSize = document.getElementById('diseaseSize').value;
			var pestSize = document.getElementById('pestSize').value;
			var natSize = document.getElementById('natSize').value;

			//disease
			$("#selectedAllDisease").click(enable_cb_disease);
			for (var i = 0; i < diseaseSize; i++) {
				$('#surveytargetDiseases' + i).click(
						disable_allselected_disease);
			}

			//pestPhase
			$("#selectedAllPest").click(enable_cb_pest);
			for (var i = 0; i < pestSize; i++) {
				$('#surveytargetPestPhases' + i)
						.click(disable_allselected_pest);
			}

			//naturalenemy
			$("#selectedAllNat").click(enable_cb_nat);
			for (var i = 0; i < natSize; i++) {
				$('#surveytargetNaturalEnemies' + i).click(
						disable_allselected_nat);
			}

		});

		function enable_cb_disease() {
			var diseaseSize = document.getElementById('diseaseSize').value;
			if (this.checked) {
				for (var j = 0; j < diseaseSize; j++) {
					$('#surveytargetDiseases' + j).prop('checked', true);
				}

			} else {
				for (var j = 0; j < diseaseSize; j++) {
					$('#surveytargetDiseases' + j).prop('checked', false);
				}
			}
		}

		function disable_allselected_disease() {
			$('#selectedAllDisease').prop('checked', false);
		}

		function enable_cb_pest() {
			var pestSize = document.getElementById('pestSize').value;
			if (this.checked) {
				for (var j = 0; j < pestSize; j++) {
					$('#surveytargetPestPhases' + j).prop('checked', true);
				}

			} else {
				for (var j = 0; j < pestSize; j++) {
					$('#surveytargetPestPhases' + j).prop('checked', false);
				}
			}
		}

		function disable_allselected_pest() {
			$('#selectedAllPest').prop('checked', false);
		}

		function enable_cb_nat() {
			var natSize = document.getElementById('natSize').value;
			if (this.checked) {
				for (var j = 0; j < natSize; j++) {
					$('#surveytargetNaturalEnemies' + j).prop('checked', true);
				}

			} else {
				for (var j = 0; j < natSize; j++) {
					$('#surveytargetNaturalEnemies' + j).prop('checked', false);
				}
			}
		}

		function disable_allselected_nat() {
			$('#selectedAllNat').prop('checked', false);
		}
	</script>
</body>
</html>