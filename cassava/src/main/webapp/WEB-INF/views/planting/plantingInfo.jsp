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
<style type="text/css">
			
			.inputerror {
				border: 0;
				color:red;
				background-color:transparent;
				width: 300px;
			}
		
		
		
		</style>
</head>
<body>
	<jsp:include page="../layouts/mainLayoutBodyHeader.jsp" />
	<div class="content-body">
		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">การเพาะปลูก > เพิ่มข้อมูล >
								แปลง${field.name}</h4>
							<div class="form-validation">
								<form class="form-valide"
									id="plantingDTOData"
									 modelAttribute="plantingDTO">
									<input type="hidden" class="form-control" id="plantingId"
										name="planting.plantingId" value="${planting.plantingId}">
									<input type="hidden" class="form-control" id="fieldId"
										name="planting.field.fieldId" value="${field.fieldId}">



									<div class="form-group row">
										<label class="col-lg-3 col-form-label" for="val-username">รหัสการเพาะปลูก<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="planting.code" value="${planting.code}" placeholder=""
												required>
											<input class="inputerror" id="code" cssClass = "error" disabled/>
										</div>
									</div>
									<div class="form-group row">

										<label class="col-lg-3 col-form-label" for="val-username">ชื่อการเพาะปลูก<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="planting.name" value="${planting.name}" placeholder=""
												required>
											<input class="inputerror" id="name" cssClass = "error" disabled/>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-lg-4">
											<div class="card">
												<div class="card-body">
													<p align="center">สภาพแวดล้อม</p>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="val-username">พื้นที่ปลูก
															(ไร่)<span class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<input type="number" class="form-control" id="sizeCheck"
																name="planting.size" value="${planting.size}"
																placeholder="" required min="0" onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))||event.charCode == 46">
															<input class="inputerror" id="size" cssClass = "error" disabled/>
														</div>
													</div>
													<div class="form-group row">

														<label class="col-lg-6 col-form-label" for="val-username">พืชที่ปลูกก่อนหน้า<span
															class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<form:select path="plantingDto.planting.previousPlant"
																class="form-control"
																onchange='CheckpreviousPlantOther(this.value);'>
																<form:options items="${previousPlants}"></form:options>
															</form:select>
															<c:set var="check" value="${planting.previousPlant}" />
															<c:choose>
																<c:when test="${check == 'อื่นๆ'}">
																	<input class="form-control" type="text"
																		name="planting.previousPlantOther"
																		id="previousPlantOther"
																		value="${planting.previousPlantOther}"
																		style='display: block;' />
																</c:when>
																<c:otherwise>
																	<input class="form-control" type="text"
																		name="planting.previousPlantOther"
																		id="previousPlantOther"
																		value="${planting.previousPlantOther}"
																		style='display: none;' />
																</c:otherwise>
															</c:choose>
															<input class="inputerror" id="previousPlant" cssClass = "error" disabled/>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="val-username">ชนิดพืชแปลงข้างเคียง<span
															class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<form:select path="plantingDto.planting.besidePlant"
																class="form-control"
																onchange='CheckbesidePlantOther(this.value);'>
																<form:options items="${besidePlants}"></form:options>
															</form:select>
															<c:set var="check" value="${planting.besidePlant}" />
															<c:choose>
																<c:when test="${check == 'อื่นๆ'}">
																	<input class="form-control" type="text"
																		name="planting.besidePlantOther"
																		id="besidePlantOther"
																		value="${planting.besidePlantOther}"
																		style='display: block;' />
																</c:when>
																<c:otherwise>
																	<input class="form-control" type="text"
																		name="planting.besidePlantOther"
																		id="besidePlantOther"
																		value="${planting.besidePlantOther}"
																		style='display: none;' />
																</c:otherwise>
															</c:choose>
															<input class="inputerror" id="besidePlant" cssClass = "error" disabled/>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-6 col-form-label" for="val-username">สารเคมีกำจัดแมลงสำหรับแช่ท่อนพันธุ์ก่อนปลูก<span
															class="text-danger">*</span>
														</label>
														<div class="col-lg-6">
															<form:select
																path="plantingDto.planting.soakingStemChemical"
																class="form-control">
																<form:options items="${soakingStemChemicals}"></form:options>
															</form:select>
															<input class="inputerror" id="soakingStemChemical" cssClass = "error" disabled/>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-lg-4">
											<div class="card">
												<div class="card-body">
													<p align="center">พืชหลัก</p>
													<div class="form-group row">
														<label class="col-lg-4 col-form-label" for="val-username">ชนิดพืชหลัก<span
															class="text-danger"></span>
														</label>
														<div class="col-lg-8">
																	<p class="form-control">${plants[0].name}</p>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-4 col-form-label" for="val-username">ชนิดพันธุ์หลัก<span
															class="text-danger">*</span>
														</label>
														<div class="col-lg-8">
															<select data-live-search="true" class="selectpicker"
																name="varieties" id="select" multiple required>
																<c:forEach items="${varietyList}" var="variety"
																	varStatus="loop">
																	<option value="${variety.varietyId}">${variety.name}</option>
																</c:forEach>
															</select>
															<input class="inputerror" id="varieties" cssClass = "error" disabled/>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-4 col-form-label" for="val-username">วันที่ปลูก<span
															class="text-danger">*</span>
														</label>
														<div class="col-lg-8">
															<input type="date" class="form-control"
																id="plantingDatePrimaryPlantPlanting"
																name="plantingDatePrimaryPlantPlanting"
																value="${planting.primaryPlantPlantingDate}"
																placeholder="" required
																onchange='plantingDate();'>
															<input class="inputerror" id="primaryPlantPlantingDate" cssClass = "error" disabled/>
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-4 col-form-label" for="val-username">วันที่เก็บเกี่ยว
														</label>
														<div class="col-lg-8">
															<input type="date" class="form-control"
																id="primaryPlantHarvestDate"
																name="plantingDatePrimaryPlantHarvest"
																value="${planting.primaryPlantHarvestDate}"
																placeholder=""
																min="${planting.primaryPlantPlantingDate}"
																>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="col-lg-4">
											<div class="card">
												<div class="card-body">
													<p align="center">พืชรอง</p>
													<div class="form-group row">
														<label class="col-lg-4 col-form-label" for="val-username">ชนิดพืชรอง<span
															class="text-danger"></span>
														</label>
														<div class="col-lg-8">
															<input type="text" class="form-control"
																id="secondaryPlantType"
																name="planting.secondaryPlantType"
																value="${planting.secondaryPlantType}" placeholder="">
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-4 col-form-label" for="val-username">ชนิดพันธุ์รอง<span
															class="text-danger"></span>
														</label>
														<div class="col-lg-8">
															<input type="text" class="form-control"
																id="secondaryPlantVariety"
																name="planting.secondaryPlantVariety"
																value="${planting.secondaryPlantVariety}" placeholder="">
														</div>
													</div>

													<div class="form-group row">
														<label class="col-lg-4 col-form-label" for="val-username">วันที่ปลูก</label>
														<div class="col-lg-8">
															<input type="date" class="form-control"
																id="secondaryPlantPlantingDate"
																name="plantingDateSecondaryPlantPlanting"
																value="${planting.secondaryPlantPlantingDate}"
																placeholder="">
														</div>
													</div>
													<div class="form-group row">
														<label class="col-lg-4 col-form-label" for="val-username">วันที่เก็บเกี่ยว</label>
														<div class="col-lg-8">
															<input type="date" class="form-control"
																id="secondaryPlantHarvestDate"
																name="plantingDateSecondaryPlantHarvest"
																value="${planting.secondaryPlantHarvestDate}"
																placeholder="" min="${planting.secondaryPlantPlantingDate}">
														</div>
													</div>
												</div>
											</div>
										</div>
									</div>
							</div>




							<div class="form-group row">
								<div class="col-lg-4">
									<div class="card">
										<div class="card-body">
											<p align="center">รายละเอียดการปลูก</p>
											<div class="form-group row">
												<label class="col-lg-6 col-form-label" for="val-username">ที่มาของท่อนพันธุ์<span
													class="text-danger">*</span>
												</label>
												<c:set var="check" value="${planting.stemSource}" />
												<c:choose>
													<c:when test="${check == 'ซื้อ'}">
														<span class="col-lg-2 col-form-label"> <input
															type="radio" name="planting.stemSource" id="stemSource"
															value="ซื้อ" checked>ซื้อ
														</span>
														<span class="col-lg-4 col-form-label"><input
															type="radio" name="planting.stemSource" id="stemSource"
															value="เก็บท่อนพันธุ์เอง">เก็บท่อนพันธุ์เอง</span>
													</c:when>
													<c:otherwise>
														<span class="col-lg-2 col-form-label"> <input
															type="radio" name="planting.stemSource" id="stemSource"
															value="ซื้อ">ซื้อ
														</span>
														<span class="col-lg-4 col-form-label"><input
															type="radio" name="planting.stemSource" id="stemSource"
															value="เก็บท่อนพันธุ์เอง" checked>เก็บท่อนพันธุ์เอง</span>
													</c:otherwise>
												</c:choose>
												<input class="inputerror" id="stemSource" cssClass = "error" disabled/>	
											</div>
											<div class="form-group row">
												<label class="col-lg-6 col-form-label" for="val-username">จำนวนครั้งการไถเตรียมแปลง<span
													class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<form:select path="plantingDto.planting.numTillage"
														class="form-control">
														<form:options items="${numTillages}"></form:options>
													</form:select>
													<input class="inputerror" id="numTillage" cssClass = "error" disabled/>
												</div>
											</div>

											<div class="form-group row">
												<label class="col-lg-6 col-form-label" for="val-username">การใช้วัสดุปรับปรุงดิน<span
													class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<form:select path="plantingDto.planting.soilAmendments"
														class="form-control"
														onchange='ChecksoilAmendmentsOther(this.value);'>
														<form:options items="${soilAmendmentss}"></form:options>
													</form:select>
													<c:set var="check" value="${planting.soilAmendments}" />
													<c:choose>
														<c:when test="${check == 'อื่นๆ'}">
															<input class="form-control" type="text"
																name="planting.soilAmendmentsOther"
																id="soilAmendmentsOther"
																value="${planting.soilAmendmentsOther}"
																style='display: block;' />
														</c:when>
														<c:otherwise>
															<input class="form-control" type="text"
																name="planting.soilAmendmentsOther"
																id="soilAmendmentsOther"
																value="${planting.soilAmendmentsOther}"
																style='display: none;' />
														</c:otherwise>
													</c:choose>
												<input class="inputerror" id="soilAmendments" cssClass = "error" disabled/>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-6 col-form-label" for="val-username">วิธีการจัดการโรค<span
													class="text-danger"></span>
												</label>
												<div class="col-lg-6">
													<form:select path="plantingDto.planting.diseaseManagement"
														class="form-control">
														<option value="" selected>เลือก</option>
														<form:options items="${diseaseManagements}"></form:options>
													</form:select>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-6 col-form-label" for="val-username">วิธีการจัดการแมลงศัตรูพืช<span
													class="text-danger"></span>
												</label>
												<div class="col-lg-6">
													
													<form:select path="plantingDto.planting.pestManagement"
														class="form-control">
														<option value="" selected>เลือก</option>
														<form:options items="${pestManagements}"></form:options>
													</form:select>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-8">
									<div class="card">
										<div class="card-body">
											<p align="center">การใส่ปุ๋ย</p>
											<div class="form-group row">
												<label class="col-lg-3 col-form-label" for="val-username">วันที่ใส่ปุ๋ยครั้งที่
													1</label>
												<div class="col-lg-3">
													<input type="date" class="form-control"
														id="fertilizerDate1" name="plantingDateFertilizerDate1"
														value="${planting.fertilizerDate1}" placeholder="">
												</div>
												<label class="col-lg-3 col-form-label" for="val-username">ปุ๋ยเกรดที่ใช้<span
													class="text-danger"></span>
												</label>
												<div class="col-lg-3">
													<input type="text" class="form-control"
														id="fertilizerFormular1"
														name="planting.fertilizerFormular1"
														value="${planting.fertilizerFormular1}" placeholder="">
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-3 col-form-label" for="val-username">วันที่ใส่ปุ๋ยครั้งที่
													2<span class="text-danger"></span>
												</label>
												<div class="col-lg-3">
													<input type="date" class="form-control"
														id="fertilizerDate2" name="plantingDateFertilizerDate2"
														value="${planting.fertilizerDate2}" placeholder="">
												</div>
												<label class="col-lg-3 col-form-label" for="val-username">ปุ๋ยเกรดที่ใช้<span
													class="text-danger"></span>
												</label>
												<div class="col-lg-3">
													<input type="text" class="form-control"
														id="fertilizerFormular2"
														name="planting.fertilizerFormular2"
														value="${planting.fertilizerFormular2}" placeholder="">
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-3 col-form-label" for="val-username">วันที่ใส่ปุ๋ยครั้งที่
													3<span class="text-danger"></span>
												</label>
												<div class="col-lg-3">
													<input type="date" class="form-control"
														id="fertilizerDate3" name="plantingDateFertilizerDate3"
														value="${planting.fertilizerDate3}" placeholder="">
												</div>
												<label class="col-lg-3 col-form-label" for="val-username">ปุ๋ยเกรดที่ใช้<span
													class="text-danger"></span>
												</label>
												<div class="col-lg-3">
													<input type="text" class="form-control"
														id="fertilizerFormular3"
														name="planting.fertilizerFormular3"
														value="${planting.fertilizerFormular3}" placeholder="">
												</div>
											</div>
											<hr noshade="noshade" align="center" width="100%" size="6">
											<p align="center">การกำจัดวัชพืช</p>
											<div class="form-group row">
												<label class="col-lg-3 col-form-label" for="val-username">เดือนที่กำจัดวัชพืชครั้งที่
													1<span class="text-danger"></span>
												</label>
												<div class="col-lg-3">
													<form:select path="plantingDto.planting.WeedingMonth1"
														class="form-control">
														<option value="">เลือก</option>
														<form:options items="${weedingMonth1s}"></form:options>
													</form:select>
												</div>
												<label class="col-lg-3 col-form-label" for="val-username">สารเคมีที่ใช้<span
													class="text-danger"></span>
												</label>
												<div class="col-lg-3">
													<form:select
														path="plantingDto.planting.herbicideByWeedingChemical1.herbicideId"
														class="form-control"
														onchange='CheckweedingChemicalOther1(this.value);'>
														<option value="">เลือก</option>
														<form:options items="${herbicides}"
															itemValue="herbicideId" itemLabel="name" />
													</form:select>
													<c:set var="check"
														value="${planting.herbicideByWeedingChemical1.herbicideId}" />
													<c:choose>
														<c:when test="${check == 9}">
															<input class="form-control" type="text"
																name="planting.weedingChemicalOther1"
																id="weedingChemicalOther1"
																value="${planting.weedingChemicalOther1}"
																style='display: block;' />
														</c:when>
														<c:otherwise>
															<input class="form-control" type="text"
																name="planting.weedingChemicalOther1"
																id="weedingChemicalOther1"
																value="${planting.weedingChemicalOther1}"
																style='display: none;' />
														</c:otherwise>
													</c:choose>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-3 col-form-label" for="val-username">เดือนที่กำจัดวัชพืชครั้งที่
													2<span class="text-danger"></span>
												</label>
												<div class="col-lg-3">
													<form:select path="plantingDto.planting.WeedingMonth2"
														class="form-control">
														<option value="">เลือก</option>
														<form:options items="${weedingMonth2s}"></form:options>
													</form:select>
												</div>
												<label class="col-lg-3 col-form-label" for="val-username">สารเคมีที่ใช้<span
													class="text-danger"></span>
												</label>
												<div class="col-lg-3">
													<form:select
														path="plantingDto.planting.herbicideByWeedingChemical2.herbicideId"
														class="form-control"
														onchange='CheckweedingChemicalOther2(this.value);'>
														<option value="">เลือก</option>
														<form:options items="${herbicides}"
															itemValue="herbicideId" itemLabel="name" />
													</form:select>
													<c:set var="check"
														value="${planting.herbicideByWeedingChemical2.herbicideId}" />
													<c:choose>
														<c:when test="${check == 9}">
															<input class="form-control" type="text"
																name="planting.weedingChemicalOther2"
																id="weedingChemicalOther2"
																value="${planting.weedingChemicalOther2}"
																style='display: block;' />
														</c:when>
														<c:otherwise>
															<input class="form-control" type="text"
																name="planting.weedingChemicalOther2"
																id="weedingChemicalOther2"
																value="${planting.weedingChemicalOther2}"
																style='display: none;' />
														</c:otherwise>
													</c:choose>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-3 col-form-label" for="val-username">เดือนที่กำจัดวัชพืชครั้งที่
													3<span class="text-danger"></span>
												</label>
												<div class="col-lg-3">
													<form:select path="plantingDto.planting.WeedingMonth3"
														class="form-control">
														<option value="">เลือก</option>
														<form:options items="${weedingMonth3s}"></form:options>
													</form:select>
												</div>
												<label class="col-lg-3 col-form-label" for="val-username">สารเคมีที่ใช้<span
													class="text-danger"></span>
												</label>
												<div class="col-lg-3">
													<form:select
														path="plantingDto.planting.herbicideByWeedingChemical3.herbicideId"
														class="form-control"
														onchange='CheckweedingChemicalOther3(this.value);'>
														<option value="">เลือก</option>
														<form:options items="${herbicides}"
															itemValue="herbicideId" itemLabel="name" />
													</form:select>
													<c:set var="check"
														value="${planting.herbicideByWeedingChemical3.herbicideId}" />
													<c:choose>
														<c:when test="${check == 9}">
															<input class="form-control" type="text"
																name="planting.weedingChemicalOther3"
																id="weedingChemicalOther3"
																value="${planting.weedingChemicalOther3}"
																style='display: block;' />
														</c:when>
														<c:otherwise>
															<input class="form-control" type="text"
																name="planting.weedingChemicalOther3"
																id="weedingChemicalOther3"
																value="${planting.weedingChemicalOther3}"
																style='display: none;' />
														</c:otherwise>
													</c:choose>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<div class="form-group row">
								<label class="col-lg-3 col-form-label" for="val-username">หมายเหตุ</label>
								<div class="col-lg-9">
									<input type="text" class="form-control" id="note"
										name="planting.note" value="${planting.note}" placeholder="">
								</div>
							</div>

							<div class="form-group row">
								<div class="col-lg-8 ml-auto">
									<a
										href="${pageContext.request.contextPath}/planting/"
										class="btn btn-light" role="button">กลับ</a><input
										type="submit" class="btn btn-primary" role="button"
										value="บันทึก" id="button">
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
	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />

	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
	<script>
		$(function () {
		
			$("#button").click(
					
				function () {
				
					var formData = new FormData($('#plantingDTOData')[0]);
					
					$("#code").val("");
					$("#name").val("");
					$("#size").val("");
					$("#previousPlant").val("");
					//$("#previousPlantOther").val("");
					//$("#stemSource").val("");
					$("#soakingStemChemical").val("");
					$("#primaryPlantPlantingDate").val("");
					$("#numTillage").val("");
					$("#soilAmendments").val("");
					$("#besidePlant").val("");
					$("#varieties").val("");
					//let x = document.getElementById("sizeCheck").value;
					//let y = document.getElementById("plantingDatePrimaryPlantPlanting").value;
					
					
					$.ajax({
						url: "${pageContext.request.contextPath}/planting/update",
						type: 'POST',
						data: formData,
						dataType: 'json',
						success: function (response) {
							window.location.href = "${pageContext.request.contextPath}/planting/";
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
		function plantingDate(val) {
			//alert("check");
			var element = document.getElementById('plantingDatePrimaryPlantPlanting');
			//alert(element.value);
			document.getElementById("primaryPlantHarvestDate").min = element.value;
			
			
		}
	</script>
	<script type="text/javascript">
		function CheckpreviousPlantOther(val) {
			
			var element = document.getElementById('previousPlantOther');
			if (val == "อื่นๆ") {
				element.style.display = 'block';
			}
			else
			{
				element.style.display = 'none';
				element.value="";
			}
		}
	</script>
	<script type="text/javascript">
		function CheckbesidePlantOther(val) {
			var element = document.getElementById('besidePlantOther');

			if (val == "อื่นๆ") {
				element.style.display = 'block';
			}
			else
			{
				element.style.display = 'none';
				element.value="";
			}
		}
	</script>
	<script type="text/javascript">
		function ChecksoilAmendmentsOther(val) {
			var element = document.getElementById('soilAmendmentsOther');

			if (val == "อื่นๆ") {
				element.style.display = 'block';
			}
			else
			{
				element.style.display = 'none';
				element.value="";
			}
		}
	</script>
	<script type="text/javascript">
		function CheckweedingChemicalOther1(val) {
			var element = document.getElementById('weedingChemicalOther1');

			if (val == 9) {
				element.style.display = 'block';
			}
			else
			{
				element.style.display = 'none';
				element.value="";
			}
		}
	</script>
	<script type="text/javascript">
		function CheckweedingChemicalOther2(val) {
			var element = document.getElementById('weedingChemicalOther2');

			if (val == 9) {
				element.style.display = 'block';
			}
			else
			{
				element.style.display = 'none';
				element.value="";
			}
		}
	</script>
	<script type="text/javascript">
		function CheckweedingChemicalOther3(val) {
			var element = document.getElementById('weedingChemicalOther3');

			if (val == 9) {
				element.style.display = 'block';
			}
			else
			{
				element.style.display = 'none';
				element.value="";
			}
		}
	</script>
	<script type="text/javascript">
		var x = ${varietyIdList};
		$('#select').selectpicker('val', x);
		$('#select').selectpicker('refresh');
	</script>
</body>
</html>
