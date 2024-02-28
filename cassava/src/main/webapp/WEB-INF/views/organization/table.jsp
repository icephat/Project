<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="table-responsive">
	<table class="table table-striped table-bordered zero-configuration">
		<thead>
			<tr>
				<th>รหัสหน่วยงาน</th>
				<th>หน่วยงาน</th>
				<th>เบอร์โทรศัพท์</th>
				<th>การเข้าถึงข้อมูล</th>
				<th>แก้ไข</th>
				<th>ผู้จัดการข้อมูลแปลง</th>
				<th>รายละเอียด</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${organizations}" var="organization"
				varStatus="loop">

				<tr>
					<td>${organization.code}</td>
					<td>${organization.name}</td>
					<td>${organization.phone}</td>
					<td>${organization.accessInfoLevel}</td>
					<td><a
						href="${pageContext.request.contextPath}/organization/edit/${organization.organizationId}"
						class="btn btn-primary" role="button"><i class="fa fa-edit"
							aria-hidden="true"></i></a> <!--  <input type="text" value="${organization.organizationId}  ${checkedFK[loop.index]}">-->
						<c:if test="${(empty checkedFK[loop.index])}">
							<a
								href="${pageContext.request.contextPath}/organization/delete/${organization.organizationId}"
								class="btn btn-danger" role="button"
								onclick="return confirm('คุณยืนยันที่จะลบหรือไม่');"><i
								class="fa fa-trash" aria-hidden="true"></i></a>
						</c:if></td>

					<td>${countStaff[loop.index] }คน</td>
					<td><a
						href="${pageContext.request.contextPath}/organization/info/${organization.organizationId}"
						class="btn btn-primary" role="button"><i class="fa fa-edit"
							aria-hidden="true"></i></a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="col-lg-6">
		<select name="page" id="page">
			<c:forEach items="${pages}" var="p">
				<option id="${p}" value="${p}">${p}</option>
			</c:forEach>
		</select>
	</div>
</div>

<script>
		$(function() {
			$("#value")
					.change(
							function() {
								//alert( "Handler for .change() called." );
								var selectedValue = $("#value option:selected")
										.val();
								var selectedPage = $("#page option:selected")
										.val();
								$
										.ajax({
											method : "GET",
											url : "${pageContext.request.contextPath }/organization/index/page/"
													+ selectedPage
													+ "/value/"
													+ selectedValue,
											success : function(response) {
												$('#staffs').empty();
												$('#staffs').html(
														response);
											},
										});
							});
		});
		$(function() {
			$("#page")
					.change(
							function() {
								//alert( "Handler for .change() called." );
								var selectedValue = $("#value option:selected")
										.val();
								var selectedPage = $("#page option:selected")
										.val();
								$
										.ajax({
											method : "GET",
											url : "${pageContext.request.contextPath }/organization/index/page/"
													+ selectedPage
													+ "/value/"
													+ selectedValue,
											success : function(response) {
												alert(${pages});
												$('#staffs').empty();
												$('#staffs').html(
														response);
												document.getElementById(${currentPage+1}).selected = "true";
											},
										});
							});
		});
	</script>