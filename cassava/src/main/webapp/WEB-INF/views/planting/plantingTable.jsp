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
				<th>รหัสการเพาะปลูก</th>
				<th>ชื่อการเพาะปลูก</th>
				<th>ชื่อแปลง</th>
				<th>ที่อยู่</th>
				<th>จำนวนการสำรวจ</th>
				<th>รายละเอียด</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${plantings}" var="planting" varStatus="loop">
				<tr>
					<td>${firstItem + loop.index}</td>
					<td>${formatter.format(planting.primaryPlantPlantingDate)}</td>
					<td>${planting.code}</td>
					<td>${planting.name}</td>
					<td>${planting.field.name}<br>(${planting.field.code})
					</td>
					<td>${planting.field.address}
						ต.${planting.field.subdistrict.name}<br>
						อ.${planting.field.subdistrict.district.name}
						จ.${planting.field.subdistrict.district.province.name}
					</td>

					<td><a
						class="btn mb-1 btn-rounded btn-outline-info text-primary disabled">${planting.surveys.size()}
							ครั้ง</a></td>

					<td><c:set var="check" value="${planting.surveys.size()}" />
						<c:choose>
							<c:when test="${check == 0}">
								<a
									href="${pageContext.request.contextPath}/planting/edit/${planting.plantingId}"
									class="btn btn-primary" role="button"><i class="fa fa-edit"
									aria-hidden="true"></i></a>
								<a
									href="${pageContext.request.contextPath}/planting/delete/${planting.plantingId}"
									class="btn btn-danger" role="button" onclick="return confirm('คุณยืนยันที่จะลบหรือไม่');" ><i class="fa fa-trash"
									aria-hidden="true"></i></a>
							</c:when>
							<c:otherwise>
								<a
									href="${pageContext.request.contextPath}/planting/edit/${planting.plantingId}"
									class="btn btn-primary" role="button"><i class="fa fa-edit"
									aria-hidden="true"></i></a>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</div>
