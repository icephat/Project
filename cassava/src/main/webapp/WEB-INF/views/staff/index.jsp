<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../layouts/mainLayoutHead.jsp" />
<!-- Custom Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">
<link
	href="${pageContext.request.contextPath }/resources/plugins/tables/css/datatable/dataTables.bootstrap4.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/pagination.css">
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css"
	rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include page="../layouts/mainLayoutBodyHeader.jsp" />

	<!--**********************************
	            Content body start
	***********************************-->

	<div class="content-body">
		<div class="container-fluid">
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">เจ้าหน้าที่</h4>

							<p align="right">
								<a href="${pageContext.request.contextPath}/staff/approve"
									class="btn mb-1 btn-primary">เจ้าหน้าที่รออนุมัติ</a>
							</p>
							<div class="d-flex justify-content-center" id="empty"></div>
							<div id="card">
								<div class="row">
									<div class="col-lg-12">
										<div class="form-group row">
											<label class="col-lg-3 col-form-label" for="keyword">ค้นหา
											</label>
											<div class="col-lg-3">
												<input type="text" class="form-control" id="keyword"
													name="keyword" placeholder="ชื่อ-สกุล ตำแหน่งและสังกัด">
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
	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
	<script
		src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/js/pagination.js"></script>
	<script>
		var pageSize = $("#itemSize option:selected").val();
		var totalItem;
		var keyPath = "";

		//on load
		$(function() {
			var container = $("#pagination");
			if (!container.length)
				return;

			$
					.ajax({
						method : "GET",
						url : "${pageContext.request.contextPath}/staff/total",
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
												url : "${pageContext.request.contextPath}/staff/total"
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
								url : "${pageContext.request.contextPath}/staff/page/"
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
											'<div class="col-lg-6" id="listItem"><span>รายการที่ '
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
</body>
</html>