<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<jsp:include page="../layouts/mainLayoutHead.jsp" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">
</head>

<body>
	<jsp:include page="../layouts/mainLayoutBodyHeader.jsp" />

	<!--**********************************
	            Content body start
	***********************************-->
	<div class="content-body">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<form id="regForm" modelAttribute="SurveyTargetPointDTO"
								method="POST"
								action="${pageContext.request.contextPath }/survey/point/save">
								<h4 class="card-title">การสำรวจ > การเพาะปลูก ${surveyTarget.survey.planting.name}(${surveyTarget.survey.planting.code}) - ข้อมูลสำรวจ > ${surveyTarget.targetofsurvey.name}
								</h4>
								<div class="card-body">
									<div class="row">
										<div class="col-12">
											<div class="table-responsive" style="width: auto;">
												<table class="table header-border"
													style="text-align: center;">
													<thead>
														<tr>
															<th style="padding: 36px;"></th>
															<c:forEach items="${pointNumber}" var="point">
																<th>จุดที่ ${point+1}</th>
															</c:forEach>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${itemNumber}" var="item" varStatus="j">
															<tr>
																<td>ต้นที่ ${item+1}</td>
																<c:forEach items="${pointNumber}" var="point"
																	varStatus="i">
																	<td>
																		<div class="container">
																			<span style="display: inline-block;"> <input
																				type="number" class="form-control" id=""
																				name="surveyValue[${i.index}][${j.index}]" min="0"
																				max="100" style="width: 5em"
																				value="${surveyTargetPointDTO.surveyValue[i.index][j.index]}">
																			</span> <label>%</label>
																		</div> <input type="hidden"
																		name="surveyTargetPointIdList[${i.index}][${j.index}]"
																		value="${surveyTargetPointDTO.surveyTargetPointIdList[i.index][j.index]}">
																	</td>
																</c:forEach>
															</tr>
														</c:forEach>
													</tbody>
												</table>
											</div>
											<p align="center">
												<label class="inline-label" style="font-size:150%;"><input
													style="display: inline-block; opacity: 1; position: static; transform: scale(1.5); margin: 5px;"
													type="radio" name="status" value="Editing"
													<c:if
														test="${surveyTarget.status == 'Editing'}"> checked</c:if>>อยู่ระหว่างกรอกข้อมูล
												</label> <label class="inline-label" style="font-size:150%;"> <input
													style="display: inline-block; opacity: 1; position: static;  transform: scale(1.5); margin: 5px;"
													type="radio" name="status" value="Complete"
													<c:if
														test="${surveyTarget.status != 'Editing'}"> checked</c:if>>
													กรอกข้อมูลครบแล้ว
												</label>
											</p>
											<input type="hidden" name="surveyTargetId"
												value="${surveyTarget.surveyTargetId }">
											<p align="center">
												<a
													href="${pageContext.request.contextPath }/survey/point/${surveyId}"
													class="btn btn-light" role="button">กลับ</a> <input
													type="submit" class="btn btn-primary" role="button"
													value="บันทึก" />
											</p>
										</div>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
</html>