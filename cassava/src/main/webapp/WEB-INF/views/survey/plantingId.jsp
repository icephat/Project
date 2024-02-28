<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@
taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<link rel="stylesheet"
	href="http://www.jqueryscript.net/css/jquerysctipttop.css"
	type="text/css" />
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
							<h4 class="card-title">การสำรวจ > เพิ่มข้อมูล >
								การเพาะปลูก-${planting.name}(${planting.code})</h4>
							<p align="right">
								<a href="${pageContext.request.contextPath}/survey/planting"
									class="btn mb-1 btn-light">กลับ</a> <a
									href="${pageContext.request.contextPath}/survey/create/${planting.plantingId}"
									class="btn mb-1 btn-primary">เพิ่มข้อมูล</a> <input
									type="hidden" id="id" value="${planting.plantingId }" />
							</p>
							<h5>ข้อมูลการเพาะปลูก</h5>
							<h5></h5>
							<div class="row">
								<div class="col-lg-2">
									<lable>การเพาะปลูก</lable>
								</div>
								<div class="col-lg-9">${planting.name}(${planting.code})</div>
							</div>
							<div class="row">
								<div class="col-lg-2">วันที่เพาะปลูก</div>
								<div class="col-lg-6">
									${formatter.format(planting.primaryPlantPlantingDate)}</div>
							</div>
							<div class="row">
								<div class="col-lg-2">แปลง</div>
								<div class="col-lg-6">${planting.field.name}
									(${planting.field.code})</div>
							</div>
							<div class="row">
								<div class="col-lg-2">ที่อยู่แปลง</div>
								<div class="col-lg-6">${planting.field.address}
									ต.${planting.field.subdistrict.name}
									อ.${planting.field.subdistrict.district.name}
									จ.${planting.field.subdistrict.district.province.name}</div>
							</div>
							<p></p>
							<h5>ข้อมูลการสำรวจ</h5>
							<div class="d-flex justify-content-center" id="empty"></div>
							<div id="card">
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
	<script
		src="${pageContext.request.contextPath }/resources/js/jquery-3.6.0.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/js/pagination.js"></script>

	<script>
		var pageSize = $("#itemSize option:selected").val();
		var totalItem;
		var id = document.getElementById("id").value;

		//on load
		$(function() {
			var container = $("#pagination");
			if (!container.length)
				return;

			$
					.ajax(
							{
								method : "GET",
								url : "${pageContext.request.contextPath}/survey/plantingId/"
										+ id + "/total",
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
			container.pagination(paginateOption(pageSize, totalItem));
		});

		//set paginationOption
		function paginateOption(pageSize, totalItem) {
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
								url : "${pageContext.request.contextPath}/survey/plantingId/"
										+ id
										+ "/page/"
										+ pageNumber
										+ "/value/" + pageSize,
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
	<script
		src="${pageContext.request.contextPath }/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/plugins/tables/js/datatable/dataTables.bootstrap4.min.js">
		
	</script>
	<script
		src="${pageContext.request.contextPath }/plugins/tables/js/datatable-init/datatable-basic.min.js">
		
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
