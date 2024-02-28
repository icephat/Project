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
<!-- FormStep -->
<link href="https://fonts.googleapis.com/css?family=Raleway"
	rel="stylesheet">

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
							<h4 class="card-title">การสำรวจ > การเพาะปลูก
								${survey.planting.name} (${survey.planting.code}) - ข้อมูลสรุป</h4>
							<p align="right"></p>

							<p align="right">
								สถานะการสำรวจ
								<c:choose>
									<c:when test="${survey.status == 'Editing'}">
										<a
											href="${pageContext.request.contextPath }/survey/point/${survey.surveyId}"
											class="label label-warning">ยังไม่ยืนยันข้อมูล</a>
									</c:when>
									<c:otherwise>
										<a
											href="${pageContext.request.contextPath }/survey/point/${survey.surveyId}"
											class="label label-success">ยืนยันข้อมูล</a>
									</c:otherwise>
								</c:choose>
								<a href="${pageContext.request.contextPath }/survey/"
									class="btn btn-light" role="button">กลับ</a>
							</p>
							<input id="diseaseSize" type="hidden"
								value="${surveyDiseaseListSize }"> <input id="pestSize"
								type="hidden" value="${surveyPestPhaseListSize }"> <input
								id="natSize" type="hidden"
								value="${surveyNaturalenemyListSize }">
							<div>
								<h4 class="mb-1">การเพาะปลูก</h4>
								<div class="row">
									<div class="col-lg-12">
										<div class="row">
											<div class="col-lg-2">
												<lable>ชื่อการเพาะปลูก</lable>
											</div>
											<div class="col-lg-9">${survey.planting.name}
												(${survey.planting.code})</div>
										</div>

										<div class="row">
											<div class="col-lg-2">วันที่เพาะปลูก</div>
											<div class="col-lg-6">${formatter.format(survey.planting.primaryPlantPlantingDate)}</div>
										</div>

										<div class="row">
											<div class="col-lg-2">ชื่อแปลง</div>
											<div class="col-lg-6">${survey.planting.field.name}
												(${survey.planting.field.code})</div>
										</div>
										<div class="row">
											<div class="col-lg-2">ที่อยู่แปลง</div>
											<div class="col-lg-6">${survey.planting.field.address}
												หมู่ ${survey.planting.field.moo}
												ถนน${survey.planting.field.road}<br>ต.${survey.planting.field.subdistrict.name}
												อ.${survey.planting.field.subdistrict.district.name}
												จ.${survey.planting.field.subdistrict.district.province.name}
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-12">
										<h4 class="mb-1">การสำรวจ - สภาพทั่วไป</h4>
									</div>
								</div>
								<div class="row">
									<div class="col-lg-6">



										<div class="row">
											<div class="col-lg-4">
												<lable>วันที่สำรวจ</lable>
											</div>
											<div class="col-lg-8">${formatter.format(survey.date) }</div>
										</div>
										<div class="row">
											<div class="col-lg-4">อายุพืช</div>
											<div class="col-lg-8">${datePlanting}วัน</div>
										</div>

										<div class="row">
											<div class="col-lg-4">
												<lable>พืชแปลงข้างเคียง</lable>
											</div>
											<div class="col-lg-8">${survey.besidePlant}</div>
										</div>
										<div class="row">
											<div class="col-lg-4">
												<lable>ชนิดดิน</lable>
											</div>
											<div class="col-lg-8">${survey.soilType}</div>
										</div>
										<div class="row">
											<div class="col-lg-4">
												<lable>วัชพืชหลักที่พบ</lable>
											</div>
											<div class="col-lg-8">${survey.weed}</div>
										</div>
										<div class="row">
											<div class="col-lg-4">
												<lable>ความเสียหายจากสารกำจัดวัชพืช</lable>
											</div>
											<div class="col-lg-8">${survey.percentDamageFromHerbicide}%</div>
										</div>
									</div>
									<div class="col-lg-6">
										<div class="row">
											<div class="col-lg-4">อุณหภูมิ</div>
											<div class="col-lg-8">${survey.temperature}C</div>
										</div>
										<div class="row">
											<div class="col-lg-4">ความชื้นสัมพัทธ์</div>
											<div class="col-lg-8">${survey.humidity }%</div>
										</div>
										<div class="row">
											<div class="col-lg-4">สภาพฝน</div>
											<div class="col-lg-8">${survey.rainType}</div>
										</div>
										<div class="row">
											<div class="col-lg-4">สภาพแดด</div>
											<div class="col-lg-8">${survey.sunlightType}</div>
										</div>
										<div class="row">
											<div class="col-lg-4">สภาพน้ำค้าง</div>
											<div class="col-lg-8">${survey.dewType}</div>
										</div>
									</div>
								</div>


								<h4 class="mb-1">การสำรวจ - ข้อมูลสำรวจ</h4>




								<input type="hidden" id="stpSize"
									value="${surveyTargetPointDTOListSize}">
								<c:forEach items="${surveyTargetPointDTOList}" var="survey"
									varStatus="loop">
									<div class="card">
										<div class="card-body">
											<h3 class="card-title" align="center">
												${surveyTarget[loop.index].targetofsurvey.name}</h3>
											<p align="right">
												<c:choose>
													<c:when
														test="${surveyTarget[loop.index].status == 'Editing'}">
														<a
															href="${pageContext.request.contextPath }/survey/point/${surveyTarget[loop.index].surveyTargetId}/info"
															class="label label-warning">อยู่ระหว่างกรอกข้อมูล</a>
													</c:when>
													<c:otherwise>
														<a
															href="${pageContext.request.contextPath }/survey/point/${surveyTarget[loop.index].surveyTargetId}/info"
															class="label  label-success">กรอกข้อมูลครบแล้ว</a>
													</c:otherwise>
												</c:choose>
											</p>
											<table class="table table-striped">
												<thead>
													<tr>

													</tr>
													<tr>
														<th><h3>จุด / ต้น</h3></th>
														<c:forEach items="${itemNumber}" var="item">
															<th>${item+1}</th>
														</c:forEach>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${pointNumber}" var="point" varStatus="i">
														<tr>
															<td>จุดที่ ${point+1}</td>
															<c:forEach items="${itemNumber}" var="item" varStatus="j">
																<td>${survey.surveyValue[i.index][j.index]}<br>
																</td>
															</c:forEach>
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<div class="row">
												<div class="col-12">
													<p align="center">
														<c:choose>
															<c:when test="${loop.index < sizeDisease }">
																<span class="label gradient-2 btn-rounded">ระดับความรุนแรงเฉลี่ย
																	= ${surveyTarget[loop.index].avgDamageLevel}</span>
																<span class="label gradient-7 btn-rounded">เปอร์เซนต์การพบโรค
																	= ${surveyTarget[loop.index].percentDamage} %</span>
															</c:when>
															<c:otherwise>
																<span class="label gradient-2 btn-rounded">เปอร์เซนต์ความเสียหายเฉลี่ย
																	= ${surveyTarget[loop.index].avgDamageLevel}</span>
																<span class="label gradient-7 btn-rounded">เปอร์เซนต์การพบโรค
																	= ${surveyTarget[loop.index].percentDamage} %</span>
															</c:otherwise>
														</c:choose>
													</p>
												</div>
											</div>
											<div class="row">
												<div class="col-12">
													<form
														action="${pageContext.request.contextPath}/survey/summary/image/${survey.surveyTargetId}"
														method="POST" modelAttribute="SurveyDTO"
														class="form-valide" enctype="multipart/form-data">

														<c:if test="${loop.index < sizeDisease }">
															<c:if test="${checkedCountImage[loop.index] != 0 }">
																<p align="right">
																	เลือกจุดสำรวจ&nbsp;
																	<c:forEach items="${pointNumber}" var="point">
																		<c:if test="${countImage[loop.index][point] != 0}">
																			<input type="checkbox" class="css-control-input"
																			id="selectedImageSurveyDTO${point }_${loop.index}"
																				name="selectedImageSurveyDTOs[${point }].checked">

																			<input type="hidden" id="point"
																				name="selectedImageSurveyDTOs[${point }].point"
																				value="${point+1}">
																			<label for="point">จุดที่ ${point +1}
																				(${countImage[loop.index][point] } ภาพ)</label>&nbsp;&nbsp;&nbsp;
																		</c:if>
																	</c:forEach>
																	<input type="hidden" id="point"
																		name="selectedImageSurveyDTOs[5].point" value="6">&nbsp;&nbsp;&nbsp;<input
																		type="checkbox" class="css-control-input"
																		id="selectedImageSurveyDTO5_${loop.index}"
																		name="selectedImageSurveyDTOs[5].checked"> <label
																		for="point">จุดทั้งหมด</label>&nbsp;&nbsp;&nbsp;<input
																		type="submit" class="btn btn-primary" role="button"
																		value="ดูรูปภาพ" id="submitButton_${loop.index}">

																</p>
															</c:if>
														</c:if>
													</form>
												</div>
											</div>

										</div>
									</div>
								</c:forEach>
							</div>
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
		$(function() {
			var size = document.getElementById('stpSize').value;
			
			for (var i = 0; i < size; i++) {
				$('#submitButton_'+i).prop('disabled', true);
				$("#selectedImageSurveyDTO5_" + i).click(enable_cb);
			}
			for (var i = 0; i < size; i++) {
				for (var j = 0; j < 5; j++) {
					if(j < 5){
						$("#selectedImageSurveyDTO"+j+"_" + i).click(updateSubmitButton);
					}
					$('#selectedImageSurveyDTO' + j + '_' + i).click(
							disable_allselected);
				}
			}
		});

		function enable_cb() {
			var box = this.id.substring((this.id).length - 1);
			if (this.checked) {
				// Iterate each checkbox
				var box = this.id.substring((this.id).length - 1);
				for (var j = 0; j < 5; j++) {
					$('#selectedImageSurveyDTO' + j + '_' + box).prop(
							'checked', true);
					$('#submitButton_'+box).prop('disabled', false);
				}
			} else {
				for (var j = 0; j < 5; j++) {
					$('#selectedImageSurveyDTO' + j + '_' + box).prop(
							'checked', false);
					$('#submitButton_'+box).prop('disabled', true);
				}
			}
		}

		function disable_allselected() {
			var box = this.id.substring((this.id).length - 1);
			$('#selectedImageSurveyDTO5_' + box).prop('checked', false);
		}
		
		function updateSubmitButton() {
			var box = this.id.substring((this.id).length - 1);
			if(this.checked){
				$('#submitButton_'+box).prop('disabled', false);
			}
			else{
				$('#submitButton_'+box).prop('disabled', true);
			}
			
		  }
	</script>
	<script
		src="${pageContext.request.contextPath }/plugins/common/common.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/custom.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/settings.js"></script>
	<script src="${pageContext.request.contextPath }/js/gleek.js"></script>
	<script src="${pageContext.request.contextPath }/js/styleSwitcher.js"></script>
</html>