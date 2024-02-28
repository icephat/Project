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
				<th>ตำแหน่ง</th>
				<th>สังกัดหน่วยงาน</th>
				<th>ใช้งานครั้งสุดท้าย</th>
				<th>สถานะบัญชี</th>
				<th>รายละเอียด</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${staffs}" var="staff" varStatus="loop">
				<tr>
					<td>${firstItem + loop.index}</td>
					<td>${staff.user.title}${staff.user.firstName}
						${staff.user.lastName}<br>(${staff.user.username})
					</td>
					<td>${staff.position}</td>
					<td>${staff.division}<br>${staff.organization.name}</td>


					<td>${formatter.format(staff.user.latestLogin)}</td>

					<c:if test="${staff.user.userStatus == active}">
						<td><span class="badge badge-success px-2">เปิดการใช้งาน</span></td>
					</c:if>
					<c:if test="${staff.user.userStatus == inactive}">
						<td><span class="badge badge-danger px-2">ปิดการใช้งาน</span></td>
					</c:if>

					<td><a
						href="${pageContext.request.contextPath}/staff/edit/${staff.staffId}"
						class="btn btn-primary" role="button"><i class="fa fa-edit"
							aria-hidden="true"></i></a>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</div>