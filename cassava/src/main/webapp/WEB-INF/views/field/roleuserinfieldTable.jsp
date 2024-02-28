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
				<th>ชื่อเจ้าหน้าที่</th>
				<th>เพิ่มเจ้าหน้าที่</th>

			</tr>
		</thead>
		<tbody>
			<c:forEach items="${userList}" var="u" varStatus="loop">
				<tr>
					<td><p class="text-muted mb-0">${stauff.user.title}
							${u.user.firstName} ${u.user.lastName}
							<c:choose>
								<c:when test="${u.user.username==null}">
														(ไม่มีบัญชีผู้ใช้)
														</c:when>
								<c:when test="${u.user.username!=null}">
														 (${u.user.username })
														</c:when>
							</c:choose>
						</p></td>
					<td><a
						href="${pageContext.request.contextPath}/field/${field.fieldId}/${toPage}/save/${u.user.userId}">
							<button class="btn btn-primary" aria-hidden="true">
								<i class="fa fa-plus" aria-hidden="true"></i>
							</button>
					</a></td>
				</tr>
			</c:forEach>
		</tbody>

	</table>
</div>
