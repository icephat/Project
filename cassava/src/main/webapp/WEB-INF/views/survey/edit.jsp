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
							<h4 class="card-title">การสำรวจ > การเพาะปลูก
								${surveyDTO1.survey.planting.name}(${surveyDTO1.survey.planting.code})
								- สภาพทั่วไป</h4>
							<div class="bootstrap-modal">
								<p align="right">
									<!-- Button trigger modal -->
									<button type="button" class="btn btn-warning"
										data-toggle="modal" data-target="#exampleModalCenter">
										<i class="fa fa-info-circle" aria-hidden="true"></i>
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
													0-100 %<br> การสำรวจศัตรูธรรมชาติ -
													สำรวจเปอร์เซนต์ที่พบศัตรูธรรมชาติ 0-100 %
												</p>
											</div>
											<div class="modal-footer">
												<button type="button" class="btn btn-secondary"
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
															<input type="date" class="form-control" id="date"
																name="surveyDate" value="${surveyDTO1.survey.date }"
																required min=${plantingDate}>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="besidePlant">พืชแปลงข้างเคียง
														</label>
														<div class="col-lg-6">
															<input type="text" class="form-control" id="besidePlant"
																name="survey.besidePlant"
																value="${surveyDTO1.survey.besidePlant}">
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="soilType">ชนิดดิน
														</label>
														<div class="col-lg-6">
															<input type="text" class="form-control" id="soilType"
																name="survey.soilType"
																value="${surveyDTO1.survey.soilType }">
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="weed">วัชพืชหลักที่พบ<span
															class="text-danger"></span>
														</label>
														<div class="col-lg-6">
															<input type="text" class="form-control" id="weed"
																name="survey.weed" value="${surveyDTO1.survey.weed }">
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label"
															for="percentDamageFromHerbicide">ความเสียหายจากสารกำจัดวัชพืช(%)<span
															class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<form:select
																path="surveyDTO1.survey.percentDamageFromHerbicide"
																name="survey.percentDamageFromHerbicide"
																class="form-control">
																<form:options items="${percentDamageFromHerbicide}" />
															</form:select>
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
														<label class="col-lg-6 col-form-label" for="temperature">อุณหภูมิ
															(C)<span class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<input type="hidden" class="form-control" 
																name="survey.temperature"
																value="${surveyDTO1.survey.temperature}" required>
															<input type="number" class="form-control" id="temperatureCheck"
																name="temperature" value="${surveyDTO1.survey.temperature}">
															<input class="inputerror" id="temperature" cssClass = "error" disabled/>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="humidity">ความชื้นสัมพัทธ์(%)<span
															class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<input type="hidden" class="form-control" 
																name="survey.humidity"
																value="${surveyDTO1.survey.humidity}" required>
															<input type="number" class="form-control" 
																name="humidity" value="${surveyDTO1.survey.humidity}">
															<input class="inputerror" id="humidity" cssClass = "error" disabled/>
														</div>
													</div>

													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="rainType">สภาพฝน<span
															class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<form:select path="surveyDTO1.survey.rainType"
																name="survey.rainType" class="form-control">
																<form:options items="${rainType}" />
															</form:select>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="sunlightType">สภาพแดด<span
															class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<form:select path="surveyDTO1.survey.sunlightType"
																name="survey.sunlightType" class="form-control">
																<form:options items="${sunlightType}" />
															</form:select>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="dewType">สภาพน้ำค้าง<span
															class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<form:select path="surveyDTO1.survey.dewType"
																name="survey.dewType" class="form-control">
																<form:options items="${dewType}" />
															</form:select>
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
															<form:select id="userByImgOwner"
																name="survey.userByImgOwner.userId"
																path="surveyDTO1.survey.userByImgOwner.userId"
																class="form-control"
																onchange='CheckimgOwnerOther(this.value);'>
																<c:forEach var="userInField" items="${userInFields}">
																	<form:option value="${userInField.user.userId}"
																		class="form-control">
																		<c:out
																			value="${userInField.user.firstName} ${userInField.user.lastName}" />
																	</form:option>
																</c:forEach>
																<form:option value="0">อื่นๆ</form:option>
															</form:select>
														</div>
													</div>
													<div class="form-group row" style='display: none;'
														id="imgOwner">

														<div class="col-lg-12 row">
															<label class="col-lg-6 col-form-label"
																for="imgOwnerOther">เจ้าของภาพอื่นๆ </label> <input
																type="text" name="survey.imgOwnerOther"
																id="imgOwnerOther" class="form-control col-lg-6"
																value="${surveyDTO1.survey.imgOwnerOther }">
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-4 col-form-label"
															for="userByImgPhotographer">ผู้ถ่ายภาพ<span
															class="text-danger">*</span></label>
														<div class="col-lg-6">
															<form:select id="userByImgPhotographer"
																name="survey.userByImgPhotographer.userId"
																path="surveyDTO1.survey.userByImgPhotographer.userId"
																class="form-control"
																onchange='CheckimgPhotographerOther(this.value);'>
																<c:forEach var="userInField" items="${userInFields}">
																	<form:option value="${userInField.user.userId}">
																		${userInField.user.firstName} ${userInField.user.lastName}
																	</form:option>
																</c:forEach>
																<form:option value="0">อื่นๆ</form:option>
															</form:select>
														</div>
													</div>
													<div class="form-group row" style='display: none;'
														id="imgPhotographer">

														<div class="col-lg-12 row">
															<label class="col-lg-6 col-form-label"
																for="imgPhotographerOther">ผู้ถ่ายภาพอื่นๆ </label> <input
																type="text" name="survey.imgPhotographerOther"
																id="imgPhotographerOther" class="form-control col-lg-6"
																value="${surveyDTO1.survey.imgPhotographerOther }">
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-lg-3 col-form-label" for="note">รายละเอียดเพิ่มเติม</label>
										<div class="col-lg-8">
											<input name="survey.note" id="note" class="form-control"
												value="${surveyDTO1.survey.note}">
										</div>
									</div>
									<h4 align="center">เลือกสิ่งสำรวจ</h4>
									<div class="form-group row">
										<div class="col-lg-4">
											<div class="card">
												<div class="card-body">
													<div class="column col-12 ">
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
														<c:forEach items="${surveyDiseaseList}" var="disease"
															varStatus="loop">
															<div class="form-check mb-3">
																<label class="form-check-label" for="targetOfSurveyId">
																	<c:set var="check" value="${disease[2]}" /> <c:choose>
																		<c:when test="${check == 1}">
																			<input type="checkbox" class="form-check-input"
																				id="surveytargetDiseases${loop.index}_checked"
																				name="surveytargetDiseases[${loop.index}].checked"
																				checked>
																		</c:when>
																		<c:otherwise>
																			<input type="checkbox" class="form-check-input"
																				id="surveytargetDiseases${loop.index}"
																				name="surveytargetDiseases[${loop.index}].checked">
																		</c:otherwise>
																	</c:choose> <input type="hidden"
																	id="targetOfSurvey.targetOfSurveyId"
																	name="surveytargetDiseases[${loop.index}].targetofsurvey.targetOfSurveyId"
																	value="${disease[0]}"> ${disease[1]}
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
														<c:forEach items="${surveyPestPhaseList}" var="pestPhase"
															varStatus="loop">
															<div class="form-check mb-3">
																<label class="form-check-label" for="targetOfSurveyId">
																	<c:set var="check" value="${pestPhase[2]}" /> <c:choose>
																		<c:when test="${check == 1}">
																			<input type="checkbox" class="form-check-input"
																				id="surveytargetPestPhases${loop.index}_checked"
																				name="surveytargetPestPhases[${loop.index}].checked"
																				checked>
																		</c:when>
																		<c:otherwise>
																			<input type="checkbox" class="form-check-input"
																				id="surveytargetPestPhases${loop.index}"
																				name="surveytargetPestPhases[${loop.index}].checked">
																		</c:otherwise>
																	</c:choose> <input type="hidden"
																	id="targetOfSurvey.targetOfSurveyId"
																	name="surveytargetPestPhases[${loop.index}].targetofsurvey.targetOfSurveyId"
																	value="${pestPhase[0]}"> ${pestPhase[1]}
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
														<c:forEach items="${surveyNaturalenemyList}"
															var="naturalenemy" varStatus="loop">
															<div class="form-check mb-3">
																<label class="form-check-label" for="targetOfSurveyId">
																	<c:set var="check" value="${naturalenemy[2]}" /> <c:choose>
																		<c:when test="${check == 1}">
																			<input type="checkbox" class="form-check-input"
																				id="surveytargetNaturalEnemies${loop.index}_checked"
																				name="surveytargetNaturalEnemies[${loop.index}].checked"
																				checked>
																		</c:when>
																		<c:otherwise>
																			<input type="checkbox" class="form-check-input"
																				id="surveytargetNaturalEnemies${loop.index}"
																				name="surveytargetNaturalEnemies[${loop.index}].checked">
																		</c:otherwise>
																	</c:choose> <input type="hidden"
																	id="targetOfSurvey.targetOfSurveyId"
																	name="surveytargetNaturalEnemies[${loop.index}].targetofsurvey.targetOfSurveyId"
																	value="${naturalenemy[0]}"> ${naturalenemy[1]}
																</label>
															</div>
														</c:forEach>
													</div>
												</div>
											</div>
										</div>

										<input type="hidden" name="survey.surveyId"
											value="${surveyDTO1.survey.surveyId}"> <input
											type="hidden" id="checkImgOwnerOther"
											value="${checkImgOwnerOther}"> <input type="hidden"
											id="checkImgPhotographerOther"
											value="${checkImgPhotographerOther}">

									</div>

									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a href="${pageContext.request.contextPath}/survey/"
												class="btn btn-light" role="button">กลับ</a> <input
												type="submit" class="btn btn-primary" role="button" id="button"
												value="บันทึก">
										</div>
									</div>
								</form>
							</div>
							<input type="hidden" id="diseaseSize" value="${surveyDiseaseList.size()}"> 
							<input type="hidden" id="pestSize" value="${surveyPestPhaseList.size()}"> 
							<input type="hidden" id="natSize" value="${surveyNaturalenemyList.size()}">
						</div>
					</div>
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
				//$("#date").val("");
				//$("#percentDamageFromHerbicide").val("");
				$("#temperature").val("");
				$("#humidity").val("");
				//$("#rainType").val("");
				//$("#sunlightType").val("");
				//$("#dewType").val("");
				//$("#userByImgOwner").val("");
				//$("#userByImgPhotographer").val("");
					
					
					//alert("update");
					var formData = new FormData($('#surveyDTOData')[0]);
					$.ajax({
						url: "${pageContext.request.contextPath}/survey/update",
						type: 'POST',
						data: formData,
						dataType: 'json',
						success: function (response) {
							
							window.location.href = "${pageContext.request.contextPath}/survey/";
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
			if (val == "0") {
				element.style.display = 'block';
			} else {
				element.style.display = 'none';
				element.value = "";
			}
		}
		function CheckimgOwnerOther(val) {
			var element = document.getElementById('imgOwner');
			if (val == "0") {
				element.style.display = 'block';
			} else {
				element.style.display = 'none';
				element.value = "";
			}
		}
		$(document)
				.ready(
						function() {
							if (document.getElementById('checkImgOwnerOther').value == 0) {
								document.getElementById('userByImgOwner').value = 0;
								document.getElementById('imgOwner').style.display = 'block';
							}
							if (document
									.getElementById('checkImgPhotographerOther').value == 0) {
								document
										.getElementById('userByImgPhotographer').value = 0;
								document.getElementById('imgPhotographer').style.display = 'block';
							}
						});
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
					$('#surveytargetDiseases' + j + '_checked').prop('checked',
							true);
				}

			} else {
				for (var j = 0; j < diseaseSize; j++) {
					$('#surveytargetDiseases' + j).prop('checked', false);
					$('#surveytargetDiseases' + j + '_checked').prop('checked',
							false);
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
					$('#surveytargetPestPhases' + j + '_checked').prop(
							'checked', true);
				}

			} else {
				for (var j = 0; j < pestSize; j++) {
					$('#surveytargetPestPhases' + j).prop('checked', false);
					$('#surveytargetPestPhases' + j + '_checked').prop(
							'checked', false);
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
					$('#surveytargetNaturalEnemies' + j + '_checked').prop(
							'checked', true);
				}

			} else {
				for (var j = 0; j < natSize; j++) {
					$('#surveytargetNaturalEnemies' + j).prop('checked', false);
					$('#surveytargetNaturalEnemies' + j + '_checked').prop(
							'checked', false);
				}
			}
		}

		function disable_allselected_nat() {
			$('#selectedAllNat').prop('checked', false);
		}
	</script>
</body>
</html>
