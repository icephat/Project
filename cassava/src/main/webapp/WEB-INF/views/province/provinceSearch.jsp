<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	

		<select name="${nameDistrict }" id="district">
			<c:forEach items="${province.districts}" var="district" >
			<option value="${district.districtId}">${district.name}</option>
			</c:forEach>
		</select>


