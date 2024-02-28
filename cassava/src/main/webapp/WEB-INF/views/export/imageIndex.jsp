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
<title>ImageIndex</title>
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
									method="post" onsubmit="return check()" >
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">เลือกปีที่ตรวจสอบภาพ</label>
										<div class="col-lg-8">
											<select class="form-control" id="sel1" name="yearselect">
												<c:forEach items="${year}" var="year"
													varStatus="loopCounter">
													<option value="${year}">${year}
														(${amountimg[loopCounter.index]}ภาพ)</option>
												</c:forEach>
											</select>
										</div>
									</div>

									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">เลือกโรค</label>
										<div class="col-lg-8">
											<c:forEach items="${disease}" var="disease" varStatus="i">
												<div class="form-check form-check-inline">
													<label class="form-check-label"> <input
														type="checkbox" class="form-check-input"
														value="${disease.diseaseId}" name="listdiseaseid"
														id="check${i}" />${disease.targetofsurvey.name}
													</label>
												</div>
											</c:forEach>
										</div>
									</div>
									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<input type="submit" class="btn btn-primary"
												value="แสดงรายละเอียด"  />
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

	</div>

	<!--**********************************
	     Content body end
	***********************************-->
	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
	<script>
		function check() {
			var allCheckBox = $("[id^='check']")
			var count_checked = allCheckBox.filter(":checked").length;
			if (count_checked == 0) {
				alert("กรุณาเลือกโรค") ;
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