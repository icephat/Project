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
				<th>อุณหภูมิ (C)</th>
				<th>ความชื้นสัมพัทธ์(%)</th>
				<th>สภาพฝน</th>
				<th>สภาพแดด</th>
				<th>สภาพน้ำค้าง</th>
				<th>ผู้บันทึกข้อมูล</th>
				<th>วันที่บันทึก</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${surveys}" var="survey" varStatus="loop">
				<tr>
					<td>${firstItem + loop.index}</td>
					<td>${formatter.format(survey.date)}</td>
					<td>${survey.temperature}</td>
					<td>${survey.humidity}</td>
					<td>${survey.rainType}</td>
					<td>${survey.sunlightType}</td>
					<td>${survey.dewType}</td>
					<td>${survey.userByLastUpdateBy.title} ${survey.userByLastUpdateBy.firstName} ${survey.userByLastUpdateBy.lastName} </td>
					<td>${formatter.format(survey.lastUpdateDate)}</td>
				</tr>
			</c:forEach>
	</table>
</div>