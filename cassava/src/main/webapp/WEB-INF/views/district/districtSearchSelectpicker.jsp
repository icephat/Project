<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	
	<select name="subdistrict" id="subdistrict" class="selectpicker" data-actions-box="true" multiple required >
			<c:forEach items="${district.subdistricts}" var="subdistrict" >
			<option value="${subdistrict.subdistrictId}">${subdistrict.name}</option>
			</c:forEach>
	</select>




