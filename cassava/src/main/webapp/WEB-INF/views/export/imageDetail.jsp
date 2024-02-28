<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../layouts/mainLayoutHead.jsp" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<meta charset="UTF-8">
<title>ImageDetails</title>
<!-- Custom Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">

</head>
<body>
	<jsp:include page="../layouts/mainLayoutBodyHeader.jsp" />


	<!--**********************************
	            Content body start
	***********************************-->
	<jsp:useBean id="idd" class="java.util.ArrayList" scope="session">
		<%
		idd.add(1);
		%>
	</jsp:useBean>
	<div class="content-body">
		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">ส่งออกภาพ</h4>
							<div class="form-validation">
								<form class="form-valide"
									action="${pageContext.request.contextPath}/export/detail"
									method="post" onsubmit="return check()">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">เลือกปีที่ตรวจสอบภาพ</label>
										<div class="col-lg-8">
											<select class="form-control" id="sel1" name="yearselect">
												<c:forEach items="${year}" var="year"
													varStatus="loopCounter">
													<option value="${year}"
														<c:if test = "${year==yearselect}">
    													selected
   												   </c:if>>${year}
														(${yearamount[loopCounter.count-1]}ภาพ)</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">เลือกโรค</label>
										<div class="col-lg-8">
											<c:forEach items="${disease}" var="disease" varStatus="loop">
												<div class="form-check form-check-inline">
													<label class="form-check-label"> <input
														type="checkbox" class="form-check-input" id="check${loop}"
														name="listdiseaseid" value="${disease.diseaseId}"
														<c:forEach items="${listdiseaseid}" var="diseaseid">
														<c:if test="${disease.diseaseId==diseaseid}"> 
														checked 
   														</c:if>
														</c:forEach>>
														${disease.targetofsurvey.name}
													</label>
												</div>
											</c:forEach>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<input type="submit" class="btn btn-primary"
												value="แสดงรายละเอียด" />
										</div>
									</div>
								</form>
							</div>
							<form class="form-valide"
								action="${pageContext.request.contextPath}/export/allimginmonth"
								method="post">
								<div>
									<table class="table">
										<thead>
											<tr>
												<th>โรค</th>
												<th>ม.ค.</th>
												<th>ก.พ.</th>
												<th>มี.ค.</th>
												<th>เม.ย.</th>
												<th>พ.ค.</th>
												<th>มิ.ย.</th>
												<th>ก.ค.</th>
												<th>ส.ค.</th>
												<th>ก.ย.</th>
												<th>ต.ค.</th>
												<th>พ.ย.</th>
												<th>ธ.ค.</th>
											</tr>
										</thead>

										<tbody>
											<c:forEach items="${diseaseselect}" var="diseaseselect"
												varStatus="loop">
												<tr>
													<td>${diseaseselect.targetofsurvey.name}</td>
													<c:forEach begin="1" end="12" var="i">
														<td><c:choose>
																<c:when test="${num[loop.index][i]==0}">
																	${num[loop.index][i]}
																</c:when>
																<c:otherwise>
																	<a
																		href="${pageContext.request.contextPath}/export/imginmonth?id=${num[loop.index][0]}&month=${i}&year=${yearselect}&listid=${diseaseselectst}">
																		${num[loop.index][i]}</a>
																</c:otherwise>
															</c:choose></td>
													</c:forEach>
												</tr>
											</c:forEach>

											<tr>
												<td>download</td>

												<c:forEach begin="1" end="12" var="i">
													<td><c:choose>
															<c:when test="${checkmoy[i]!=0}">
																<button type="submit" class="btn btn-primary btn-sm"
																	name="month" value="${i}">
																	<i class="fa fa-download"></i>
																</button>
															</c:when>
															<c:otherwise>
																	-
																</c:otherwise>
														</c:choose></td>
												</c:forEach>
											</tr>

										</tbody>
									</table>
								</div>
								<input type="hidden" name="year" value="${yearselect}">
								<c:forEach items="${listdiseaseid}" var="diseaseid">
									<input type="hidden" name="listdiseaseid"
										value="${diseaseid}">

								</c:forEach>
							</form>
							<c:if test="${checkmoy[0]==1}">

								<form class="form-valide"
									action="${pageContext.request.contextPath}/export/imginyear"
									method="post">
									<input type="hidden" name="year" value="${yearselect}">

									<c:forEach items="${listdiseaseid}" var="diseaseid">
										<input type="hidden" name="listdiseaseid"
											value="${diseaseid}">

									</c:forEach>

									<p align="center">
										<button type="submit" class="btn btn-primary btn-sm">
											<i class="fa fa-download" aria-hidden="true"></i> Download
											ทั้งหมด
										</button>
									</p>
								</form>
							</c:if>
						</div>

					</div>
				</div>
			</div>
		</div>

	</div>

	<!--**********************************
	     Content body end ?id=${check[loop.index][0]}&month=${i}&year=${yearselect}&did=${diseaseselectst}
	***********************************-->
	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
	<script>
		function check() {
			var allCheckBox = $("[id^='check']")
			var count_checked = allCheckBox.filter(":checked").length;
			if (count_checked == 0) {
				alert("กรุณาเลือกโรค");
				return false;
			} else {
				return true;
			}
		}
	</script>

	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
</body>
</html>