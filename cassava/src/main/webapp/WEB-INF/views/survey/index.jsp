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
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/plugins/tables/css/datatable/dataTables.bootstrap4.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/pagination.css">
<link
	href="${pageContext.request.contextPath }/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.css"
	rel="stylesheet">
<!-- Daterange picker plugins css -->
<link
	href="${pageContext.request.contextPath }/resources/plugins/timepicker/bootstrap-timepicker.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/resources/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
	rel="stylesheet">
<style>
.toggle-switch {
	position: relative;
	width: 25px;
}

.slider {
	position: absolute;
	width: 200%;
	height: 150%;
	border-radius: 50px;
	background-color: #f29d56;
	transition: 0.3s;
}

input:checked ~ .slider {
	background-color: #28a745;
}

.slider::before {
	content: "";
	position: absolute;
	top: 0px;
	width: 25px;
	height: 25px;
	border-radius: 50%;
	box-shadow: inset 0px 100px #FFFFFF;
	background-color: #f29d56;
	transition: 0.3s;
}

input:checked ~ .slider::before {
	transform: translateX(100%);
	background-color: #FFFFFF;
	box-shadow: none;
}

.inputerror {
	border: 0;
	color: red;
	background-color: white;
	width: 300px;
	margin: auto;
}
</style>

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
							<h4 class="card-title">การสำรวจ</h4>
							<p align="right">
								<a href="${pageContext.request.contextPath }/survey/planting"
									class="btn mb-1 btn-primary">เพิ่มข้อมูล</a>
							</p>
							<div class="d-flex justify-content-center" id="empty">
							</div>
							<div id="card">
								

								<div class="row">
									<div class="col-lg-12">
										<div class="form-group row">
											<label class="col-lg-3 col-form-label" for="keyword">ค้นหา
											</label>
											<div class="col-lg-3">
												<div class="input-daterange input-group" id="date-range">
													<input type="text" class="form-control" name="startDate"
														id="startDate" required> <span
														class="input-group-addon bg-info b-0 text-white">
														จนถึงวันที </span> <input type="text" class="form-control"
														id="endDate" name="endDate" required>
												</div>
											</div>
											<div class="col-lg-3">
												<input type="text" class="form-control" id="keyword"
													name="keyword"
													placeholder="รหัสการเพาะปลูก ชื่อการเพาะปลูก">
											</div>
											<div class="col-lg-3">
												<input type="submit" class="btn btn-primary" id="search"
													role="button" value="ค้นหา">
												</div>
											<input class="inputerror" id="searchCheck" cssClass="error" disabled />
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
		var startDatePath = "";
		var endDatePath = "";

		//on load
		$(function() {
			var container = $("#pagination");
			if (!container.length)
				return;

			$
					.ajax(
							{
								method : "GET",
								url : "${pageContext.request.contextPath}/survey/index/total",
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
							$('#searchCheck').val('');
								
								function dateIsValid(date) {
									return date instanceof Date && !isNaN(date);
								}

								
							
							let startDate = new Date(document.getElementById("startDate").value);
							let endDate = new Date(document.getElementById("endDate").value);
							var keyword = document.getElementById("keyword").value;
							
							if(!(dateIsValid(startDate) && dateIsValid(endDate))){
								
								
								$('#searchCheck').val('กรุณาระบุวันที');
								//alert("กรุณาเลือก โรค หรือ ศัตรูพืช หรือ ศัตรูธรรมชาติ อย่างน้อย 1 อย่าง");
								return false;
							}
							var container = $("#pagination");
							if (!container.length)
								return;
							$('pagination').empty();

							keyPath = "?key="
									+ document.getElementById("keyword").value;
							startDatePath = "&startDate="
									+ document.getElementById("startDate").value;
							endDatePath = "&endDate="
									+ document.getElementById("endDate").value;
							$
									.ajax(
											{
												method : "GET",
												url : "${pageContext.request.contextPath}/survey/index/total"
														+ keyPath
														+ startDatePath
														+ endDatePath,
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
								url : "${pageContext.request.contextPath}/survey/index/page/"
										+ pageNumber
										+ "/value/"
										+ pageSize
										+ keyPath + startDatePath + endDatePath,
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

		function toggleCheckbox(element) {
			var id = element.value;
			$.ajax({
				method : "GET",
				url : "${pageContext.request.contextPath}/survey/status/" + id,
				success : function(response) {

				}
			});
		}
	</script>


	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js">
		
	</script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js">
		
	</script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.js">
		
	</script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/moment/moment.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js">
		
	</script>
	<!-- Clock Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/clockpicker/dist/jquery-clockpicker.min.js">
		
	</script>
	<!-- Color Picker Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/jquery-asColorPicker-master/libs/jquery-asColor.js">
		
	</script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/jquery-asColorPicker-master/libs/jquery-asGradient.js">
		
	</script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/jquery-asColorPicker-master/dist/jquery-asColorPicker.min.js">
		
	</script>
	<!-- Date Picker Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.js'">
		
	</script>
	<!-- Date range Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/timepicker/bootstrap-timepicker.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.js">
		
	</script>
	<script
		src="${pageContext.request.contextPath }/resources/js/plugins-init/form-pickers-init.js"></script>
</body>

</html>