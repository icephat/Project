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
				<th>วันที่เพาะปลูก</th>
				<th>การเพาะปลูก</th>
				<th>แปลง</th>
				<th>ที่อยู่</th>
				<th>จำนวนการสำรวจ</th>
				<th>เพิ่มการสำรวจ</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${plantings}" var="planting" varStatus="loop">
				<tr>
					<td>${firstItem + loop.index}</td>
					<td>${formatter.format(planting.primaryPlantPlantingDate)}</td>
					<td>${planting.name}<br>(${planting.code})
					</td>
					<td>${planting.field.name}<br>(${planting.field.code})
					</td>
					<td>${planting.field.address}
						ต.${planting.field.subdistrict.name}<br>อ.${planting.field.subdistrict.district.name}
						จ.${planting.field.subdistrict.district.province.name}
					</td>
					<td><c:choose>
							<c:when test="${planting.surveys.size() == 0}">
								<a
									href="${pageContext.request.contextPath}/survey/plantingId/${planting.plantingId}"
									class="btn mb-1 btn-rounded btn-outline-primary disabled" >${planting.surveys.size()}
									ครั้ง</a>
							</c:when>
							<c:otherwise>
								<a
									href="${pageContext.request.contextPath}/survey/plantingId/${planting.plantingId}"
									class="btn mb-1 btn-rounded btn-outline-primary">${planting.surveys.size()}
									ครั้ง</a>
							</c:otherwise>
						</c:choose></td>
					<td><a
						href="${pageContext.request.contextPath}/survey/create/${planting.plantingId}"
						class="btn btn-primary" role="button"><i class="fa fa-plus"
							aria-hidden="true"></i></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>