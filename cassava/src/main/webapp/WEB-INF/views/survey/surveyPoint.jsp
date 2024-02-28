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

<style>
.toggle-switch {
	position: relative;
	width: 25px;
}

.slider {
	position: absolute;
	width: 200%;
	height: 150%;
	border-radius: 50px;
	background-color: #f29d56;
	transition: 0.3s;
}

input:checked ~ .slider {
	background-color: #28a745;
}

.slider::before {
	content: "";
	position: absolute;
	top: 0px;
	width: 25px;
	height: 25px;
	border-radius: 50%;
	box-shadow: inset 0px 100px #FFFFFF;
	background-color: #f29d56;
	transition: 0.3s;
}

input:checked ~ .slider::before {
	transform: translateX(100%);
	background-color: #FFFFFF;
	box-shadow: none;
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
			<div class="row  justify-content-center">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<div class="card-title">
								<h4>การสำรวจ > การเพาะปลูก ${plantingName}(${plantingCode})
									- ข้อมูลสำรวจ</h4>
							</div>
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
												<button type="button" class="btn btn-light"
													data-dismiss="modal">Close</button>
											</div>
										</div>
									</div>
								</div>
							</div>
							<p align="right">
								<a
									href="${pageContext.request.contextPath }/survey/edit/${survey.surveyId}"
									class="btn btn-primary" role="button">เลือกสิ่งสำรวจ</a> <a
									href="${pageContext.request.contextPath }/survey/summary/${survey.surveyId}"
									class="btn btn-secondary" role="button">ดูข้อมูลสรุป</a>
							</p>
							<div class="form-group row">
								<div class="col-lg-4">
									<div class="card">
										<div class="card-body">
											<h4 class="card-title">การสำรวจโรค</h4>
											<c:choose>
												<c:when test="${surveyDiseaseList.size() != 0}">
													<div class="table-responsive">
														<table class="table">
															<tbody>
																<c:forEach items="${surveyDiseaseList}"
																	var="surveyDisease" varStatus="loop">
																	<tr>
																		<td>
																			<h6>${surveyDisease.targetofsurvey.name}</h6>
																			<ul>

																				<c:choose>
																					<c:when test="${surveyDisease.status == 'Editing'}">
																						<h6 id="${surveyDisease.surveyTargetId }">อยู่ระหว่างกรอกข้อมูล</h6>

																					</c:when>
																					<c:otherwise>
																						<h6 id="${surveyDisease.surveyTargetId }">กรอกข้อมูลครบแล้ว</h6>

																					</c:otherwise>
																				</c:choose>

																			</ul>
																			<ul>
																				<li>ระดับความรุนแรงเฉลี่ย -
																					${surveyDisease.avgDamageLevel}</li>
																				<li>เปอร์เซนต์การพบ -
																					${surveyDisease.percentDamage}%</li>
																			</ul>
																		</td>
																		<td><div
																				class="d-flex align-items-start flex-column">
																				<div class="mb-auto p-2">
																					<c:choose>
																						<c:when
																							test="${surveyDisease.status == 'Editing'}">

																							<div class="switch-container">
																								<div class='toggle-switch'>
																									<label> <input type='checkbox'
																										value="${surveyDisease.surveyTargetId}"
																										style="display: none;"
																										onchange="toggleCheckbox(this)"> <span
																										class='slider'></span>
																									</label>
																								</div>
																							</div>

																						</c:when>
																						<c:otherwise>

																							<div class="switch-container">
																								<div class='toggle-switch'>
																									<label> <input type='checkbox'
																										value="${surveyDisease.surveyTargetId}"
																										style="display: none;"
																										onchange="toggleCheckbox(this)" checked>
																										<span class='slider'></span>
																									</label>
																								</div>
																							</div>

																						</c:otherwise>
																					</c:choose>
																				</div>
																				<div class="p-2">
																					<a
																						href="${pageContext.request.contextPath }/survey/point/${surveyDisease.surveyTargetId}/info"
																						class="btn btn-primary btn-sm" role="button"><i
																						class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
																				</div>
																			</div></td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</c:when>
												<c:otherwise>
													<p>ไม่มีสิ่งสำรวจที่เลือกไว้</p>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>

								<div class="col-lg-4">
									<div class="card">
										<div class="card-body">
											<h4 class="card-title">การสำรวจศัตรูพืช</h4>
											<c:choose>
												<c:when test="${surveyPestPhaseList.size() != 0}">
													<div class="table-responsive">
														<table class="table">
															<tbody>
																<c:forEach items="${surveyPestPhaseList}"
																	var="surveyPestPhase" varStatus="loop">
																	<tr>
																		<td>
																			<h6>${surveyPestPhase.targetofsurvey.name}</h6>
																			<ul>
																				<c:choose>
																					<c:when
																						test="${surveyPestPhase.status == 'Editing'}">
																						<h6 id="${surveyPestPhase.surveyTargetId}">อยู่ระหว่างกรอกข้อมูล</h6>
																					</c:when>
																					<c:otherwise>
																						<h6>กรอกข้อมูลครบแล้ว</h6>

																					</c:otherwise>
																				</c:choose>

																			</ul>
																			<ul>
																				<li>เปอร์เซนต์ความเสียหายเฉลี่ย -
																					${surveyPestPhase.avgDamageLevel}%</li>
																				<li>เปอร์เซนต์การพบ -
																					${surveyPestPhase.percentDamage}%</li>
																			</ul>
																		</td>
																		<td><div
																				class="d-flex align-items-start flex-column">
																				<div class="mb-auto p-2">
																					<c:choose>
																						<c:when
																							test="${surveyPestPhase.status == 'Editing'}">

																							<div class="switch-container">
																								<div class='toggle-switch'>
																									<label> <input type='checkbox'
																										value="${surveyPestPhase.surveyTargetId}"
																										style="display: none;"
																										onchange="toggleCheckbox(this)"> <span
																										class='slider'></span>
																									</label>
																								</div>
																							</div>

																						</c:when>
																						<c:otherwise>

																							<div class="switch-container">
																								<div class='toggle-switch'>
																									<label> <input type='checkbox'
																										value="${surveyPestPhase.surveyTargetId}"
																										style="display: none;"
																										onchange="toggleCheckbox(this)" checked>
																										<span class='slider'></span>
																									</label>
																								</div>
																							</div>

																						</c:otherwise>
																					</c:choose>
																				</div>
																				<div class="p-2">
																					<a
																						href="${pageContext.request.contextPath }/survey/point/${surveyPestPhase.surveyTargetId}/infopercent"
																						class="btn btn-primary btn-sm" role="button"><i
																						class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
																				</div>
																			</div></td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</c:when>
												<c:otherwise>
													<p>ไม่มีสิ่งสำรวจที่เลือกไว้</p>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
								<div class="col-lg-4">
									<div class="card">
										<div class="card-body">
											<h4 class="card-title">การสำรวจศัตรูธรรมชาติ</h4>
											<c:choose>
												<c:when test="${surveyNaturalenemyList.size() != 0}">
													<div class="table-responsive">
														<table class="table">
															<tbody>
																<c:forEach items="${surveyNaturalenemyList}"
																	var="surveyNaturalenemy" varStatus="loop">
																	<tr>
																		<td>
																			<h6>${surveyNaturalenemy.targetofsurvey.name}</h6>
																			<ul>
																				<c:choose>
																					<c:when
																						test="${surveyNaturalenemy.status == 'Editing'}">
																						<h6 id="${surveyNaturalenemy.surveyTargetId}">อยู่ระหว่างกรอกข้อมูล</h6>

																					</c:when>
																					<c:otherwise>
																						<h6>กรอกข้อมูลครบแล้ว</h6>

																					</c:otherwise>
																				</c:choose>

																			</ul>
																			<ul>
																				<li>เปอร์เซนต์ที่พบเฉลี่ย -
																					${surveyNaturalenemy.avgDamageLevel}%</li>
																				<li>เปอร์เซนต์การพบ -
																					${surveyNaturalenemy.percentDamage}%</li>
																			</ul>
																		</td>
																		<td><div
																				class="d-flex align-items-start flex-column">
																				<div class="mb-auto p-2">
																					<c:choose>
																						<c:when
																							test="${surveyNaturalenemy.status == 'Editing'}">

																							<div class="switch-container">
																								<div class='toggle-switch'>
																									<label> <input type='checkbox'
																										value="${surveyNaturalenemy.surveyTargetId}"
																										style="display: none;"
																										onchange="toggleCheckbox(this)"> <span
																										class='slider'></span>
																									</label>
																								</div>
																							</div>

																						</c:when>
																						<c:otherwise>

																							<div class="switch-container">
																								<div class='toggle-switch'>
																									<label> <input type='checkbox'
																										value="${surveyNaturalenemy.surveyTargetId}"
																										style="display: none;"
																										onchange="toggleCheckbox(this)" checked>
																										<span class='slider'></span>
																									</label>
																								</div>
																							</div>

																						</c:otherwise>
																					</c:choose>
																				</div>
																				<div class="p-2">
																					<a
																						href="${pageContext.request.contextPath }/survey/point/${surveyNaturalenemy.surveyTargetId}/infopercent"
																						class="btn btn-primary btn-sm" role="button"><i
																						class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
																				</div>
																			</div></td>
																	</tr>
																</c:forEach>
															</tbody>
														</table>
													</div>
												</c:when>
												
												<c:otherwise>
													<p>ไม่มีสิ่งสำรวจที่เลือกไว้</p>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>

							</div>
							<div class="col-lg-12">
								<form modelAttribute="Survey" method="POST"
									action="${pageContext.request.contextPath }/survey/point/update">
									<p align="center">
												<label class="inline-label" style="font-size:150%;"><input
													style="display: inline-block; opacity: 1; position: static; transform: scale(1.5); margin: 5px;"
													type="radio" name="status" value="Editing"
													<c:if
														test="${survey.status == 'Editing'}"> checked</c:if>>ยังไม่ยืนยันข้อมูล
												</label> <label class="inline-label" style="font-size:150%;"> <input
													style="display: inline-block; opacity: 1; position: static;  transform: scale(1.5); margin: 5px;"
													type="radio" name="status" value="Complete"
													<c:if
														test="${survey.status != 'Editing'}"> checked</c:if>>
													ยืนยันข้อมูล
												</label>
											</p>
									<input type="hidden" name="surveyId" value="${survey.surveyId}">
									<p align="center">
										<a href="${pageContext.request.contextPath }/survey/"
											class="btn btn-light" role="button">กลับ</a> <input
											type="submit" class="btn btn-primary" role="button"
											value="บันทึก" />
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
	<script>
		function toggleCheckbox(element) {
			var id = element.value;
			$.ajax({
				method : "GET",
				url : "${pageContext.request.contextPath}/survey/point/status/"
						+ id,
				success : function(response) {
					if (response == "Complete") {
						$('#' + id).empty();
						$('#' + id).html('<h6>กรอกข้อมูลครบแล้ว</h6>');
					} else {
						$('#' + id).empty();
						$('#' + id).html('<h6>อยู่ระหว่างกรอกข้อมูล</h6>');
					}
				}
			});
		}
	</script>
</html>