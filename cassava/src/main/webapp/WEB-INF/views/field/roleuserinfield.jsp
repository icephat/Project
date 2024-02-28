<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="../layouts/mainLayoutHead.jsp" />
<!-- Custom Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/pagination.css">
<meta charset="UTF-8">
<title>AddStaffResponsibleinField Page</title>
</head>
<body>
	<jsp:include page="../layouts/mainLayoutBodyHeader.jsp" />

	<!--**********************************
	            Content body start
	***********************************-->
	<div class="content-body">
		<div class="container-fluid">
			<div class="row">
				<div class="col-6">
					<div class="card">
						<div class="card-body">
							<a
								href="${pageContext.request.contextPath }/field/${field.fieldId}/user/index"
								class="btn btn-light" role="button"> กลับ</a>
							<div class="form-validation">
								<h4 class="card-title"></h4>
								<div class="card-body">
									<div class="text-center">
										<span class="display-5"><i
											class="fa fa-users gradient-3-text"></i></span>
										<h2 class="mt-3">
											<c:choose>

												<c:when
													test="${toPage=='staffResponse' || toPage=='staffSurvey'}">
   													 เจ้าหน้าที่
													</c:when>
												<c:when test="${toPage=='farmerSurvey'}">
           											เกษตรกร
        											 </c:when>
												<c:otherwise>
           											 ไม่เจอ
        										 </c:otherwise>
											</c:choose>
										</h2>
									</div>
									<div class="media align-items-center mb-4">
										<div class="media-body">
											<h3 class="mb-0">
												<c:choose>
													<c:when test="${toPage=='staffResponse' }">
   													ผู้รับผิดชอบแปลง
													</c:when>
													<c:when test="${toPage=='staffSurvey'}">
   													ผู้สำรวจแปลง
													</c:when>
													<c:when test="${toPage=='farmerSurvey'}">
           											 เกษตรกรในแปลง
        										</c:when>
													<c:otherwise>
           										 ไม่เจอ
        										 </c:otherwise>
												</c:choose>
												<span class="float-right display-5 opacity-5"> </span>
											</h3>
											<c:forEach items="${listuserinfield}" var="userinfield"
												varStatus="loop">
												<p class="text-muted mb-0">
													${userinfield.user.title} ${userinfield.user.firstName}
													${userinfield.user.lastName} (${userinfield.user.username })

													<a href="" class="fa fa-trash text-red" aria-hidden="true"
														role="button" id="delete"
														onclick="deleteinfield(${userinfield.user.userId}, ${field.fieldId}, '${toPage}')"></a>

												</p>
											</c:forEach>
										</div>
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-6">
					<div class="card">
						<div class="card-body">
							<div class="form-validation">
								<input type="hidden" id="toPage" value="${toPage}" />
								<input type="hidden" id="fieldId" value="${field.fieldId}" />
								<div class="d-flex justify-content-center" id="empty"></div>
								<div id="card">
									<div class="row">
										<div class="col-lg-12">
											<div class="form-group row">
												<label class="col-lg-3 col-form-label" for="keyword">ค้นหา
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="keyword"
														name="keyword" placeholder="ชื่อ-นามสกุล และชื่อผู้ใช้">
												</div>
												<div class="col-lg-3">
													<input type="submit" class="btn btn-primary" id="search"
														role="button" value="ค้นหา">
												</div>
											</div>
										</div>
									</div>


									<div class="row">
										<div class="col-lg-6">
											<span>แสดงหน้าละ </span> <select name="value" id="itemSize">
												<c:forEach items="${sizePages}" var="sp">
													<option value="${sp }">${sp }</option>
												</c:forEach>
											</select> <span> รายการ</span>
										</div>
									</div>



									<div class="table-responsive" id="dataTable"></div>


									<div class="row col-lg-12">
										<input type="hidden" id="totalItem" value="${totalItem}" />
										<div class="col-lg-6" id="listItem"></div>
										<div class="col-lg-6">
											<div class="row justify-content-end">
												<div class="data-container"></div>
												<div id="pagination"></div>
											</div>
										</div>
									</div>
								</div>
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
	function deleteinfield(uid, fid, role) {
	    $.ajax({
	        method: "GET",
	        url: "${pageContext.request.contextPath}/field/" + fid + "/" + role + "/delete/" + uid,
	        success: function(response) {
	        	location.reload();
	        }
	    });
	}
	function saveinfield(uid, fid, role) {
	    $.ajax({
	        method: "GET",
	        url: "${pageContext.request.contextPath}/field/" + fid + "/" + role + "/save/" + uid,
	        success: function(response) {
	        	location.reload();
	        }
	    });
	}
		</script>

	<script
		src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/js/pagination.js"></script>

	<script>
		var pageSize = $("#itemSize option:selected").val();
		var fieldId = $("#fieldId").val();
		var toPage = $("#toPage").val();
		var totalItem;
		var keyPath = "";

		//on load
		$(function() {
			var container = $("#pagination");
			if (!container.length)
				return;
			$
					.ajax(
							{
								method : "GET",
								url : "${pageContext.request.contextPath}/field/"
										+ fieldId + "/userinfield/"+toPage+"/total",
							})
					.then(
							function(data, status, jqXhr) {
								totalItem = data;
								if (totalItem == 0) {
									$('#card').empty();
									$('#empty')
											.html(
													'<h3 class="text-secondary">ยังไม่มีข้อมูล</h3>');
								}
								container.pagination(paginateOption(pageSize,
										keyPath, totalItem));
							});
		});

		// search
		document
				.getElementById("search")
				.addEventListener(
						"click",
						function() {
							var container = $("#pagination");
							if (!container.length)
								return;
							$('pagination').empty();

							keyPath = "?key="
									+ document.getElementById("keyword").value;

							$
									.ajax(
											{
												method : "GET",
												url : "${pageContext.request.contextPath}/field/"
														+ fieldId
														+ "/userinfield/"+toPage+"/total"
														+ keyPath,
											})
									.then(
											function(data, status, jqXhr) {
												totalItem = data;
												container
														.pagination(paginateOption(
																pageSize,
																keyPath,
																totalItem));
											});

						});

		//change pageSize
		$("#itemSize").change(function() {
			var container = $("#pagination");
			if (!container.length)
				return;
			$('pagination').empty();

			pageSize = $("#itemSize option:selected").val();
			container.pagination(paginateOption(pageSize, keyPath, totalItem));
		});

		//set paginationOption
		function paginateOption(pageSize, keyPath, totalItem) {
			var options = {
				dataSource : Array.from(Array(totalItem).keys()),
				pageSize : pageSize,
				showGoInput : true,
				showGoButton : true,
				showNavigator : true,
				//formatNavigator:
				callback : function(response, pagination) {
					var pageNumber = pagination.pageNumber;
					var firstItem, lastItem;
					$
							.ajax({
								method : "GET",
								url : "${pageContext.request.contextPath}/field/"
										+ fieldId
										+ "/userinfield/"+toPage+"/page/"
										+ pageNumber
										+ "/value/"
										+ pageSize
										+ keyPath,
								success : function(response) {
									$('#dataTable').empty();
									$('#dataTable').html(response);
									firstItem = document
											.getElementById("firstItem").value;
									lastItem = document
											.getElementById("lastItem").value;

									$('#listItem').empty();
									$('#listItem').html(
											'<div class="col-lg-12" id="listItem"><span>รายการที่ '
													+ firstItem + ' ถึง '
													+ lastItem + ' จาก '
													+ totalItem
													+ ' รายการ</span></div>');
								},
							});
				},
			};
			return options;
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
