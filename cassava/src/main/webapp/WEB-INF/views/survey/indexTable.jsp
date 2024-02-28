<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="table-responsive" id="dataTable">
	<input type="hidden" id="firstItem" value="${firstItem}" /> <input
		type="hidden" id="lastItem" value="${lastItem}" />

	<table class="table table-striped table-bordered zero-configuration">
		<thead>
			<tr>
				<th>ลำดับ</th>
				<th>วันที่สำรวจ</th>
				<th>การเพาะปลูก</th>
				<th>แปลง</th>
				<th>ที่อยู่</th>
				<th>ยืนยันข้อมูล</th>
				<th>สภาพทั่วไป</th>
				<th>ข้อมูลสำรวจ</th>
				<th>ข้อมูลสรุป</th>
				<th>ลบ</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${surveys}" var="survey" varStatus="loop">
				<tr>
					<td>${firstItem + loop.index}</td>
					<td>${formatter.format(survey.date)}</td>
					<td>${survey.planting.name}<br>(${survey.planting.code})
					</td>
					<td>${survey.planting.field.name}<br>(${survey.planting.field.code})
					</td>
					<td>${survey.planting.field.address}
						ต.${survey.planting.field.subdistrict.name} <br>อ.${survey.planting.field.subdistrict.district.name}
						จ.${survey.planting.field.subdistrict.district.province.name}
					</td>
					<td><c:choose>
							<c:when test="${survey.status == 'Editing'}">
								<div class="switch-container">
									<div class='toggle-switch'>
										<label> <input type='checkbox' value="${survey.surveyId}" style="display:none;" onchange="toggleCheckbox(this)"> <span
											class='slider'></span>
										</label>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="switch-container">
									<div class='toggle-switch'>
										<label> <input type='checkbox' value="${survey.surveyId}" style="display:none;" onchange="toggleCheckbox(this)" checked> <span
											class='slider'></span>
										</label>
									</div>
								</div>
							</c:otherwise>
						</c:choose></td>
					<td><a
						href="${pageContext.request.contextPath }/survey/edit/${survey.surveyId}"
						class="btn btn-primary" role="button"><i
							class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
					<td><a
						href="${pageContext.request.contextPath }/survey/point/${survey.surveyId}"
						class="btn btn-primary" role="button"><i
							class="fa fa-pencil-square-o" aria-hidden="true"></i></a></td>
					<td><a
						href="${pageContext.request.contextPath }/survey/summary/${survey.surveyId}"
						class="btn btn-primary" role="button"><i class="fa fa-file-o"
							aria-hidden="true"></i></a></td>
					<td><a class="btn btn-danger" role="button"
						href="${pageContext.request.contextPath }/survey/delete/${survey.surveyId}" onclick="return confirm('คุณยืนยันที่จะลบหรือไม่');"><i
							class="fa fa-trash" aria-hidden="true"> </i></a></td>
				</tr>
			</c:forEach>
	</table>
</div>
