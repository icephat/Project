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
				<th>ชื่อ-สกุล</th>
				<th>ที่อยู่</th>
				<th>สังกัดหน่วยงาน</th>

				<th>ใช้งานครั้งสุดท้าย</th>
				<th>สถานะบัญชี</th>
				<th>รายละเอียด</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${farmers}" var="farmer" varStatus="loop">
				<tr>
					<td>${firstItem + loop.index}</td>
					<td>${farmer.user.title}${farmer.user.firstName}
						${farmer.user.lastName}<br>(${farmer.user.username})
					</td>
					<td>อ.${farmer.subdistrict.district.name} <br>จ.${farmer.subdistrict.district.province.name}
					</td>
					<td><c:forEach items="${farmer.organizations}"
							var="organization">
														${organization.name}<br>
						</c:forEach></td>
					<td>${formatter.format(farmer.user.latestLogin)}</td>
					<c:if test="${farmer.user.userStatus == active}">
						<td><span class="badge badge-success px-2">เปิดการใช้งาน</span></td>
					</c:if>
					<c:if test="${farmer.user.userStatus == inactive}">
						<td><span class="badge badge-danger px-2">ปิดการใช้งาน</span></td>
					</c:if>
					<td><a
						href="${pageContext.request.contextPath}/farmer/edit/${farmer.farmerId}"
						class="btn btn-primary" role="button"><i class="fa fa-edit"
							aria-hidden="true"></i></a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>

</div>




