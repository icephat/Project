<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

			<!DOCTYPE html>
			<html>

			<head>



				<style type="text/css">
					button.none {
						border-style: none;
						border: 0;
						padding: 0px;
						margin-top: 0px;
						margin-bottom: 0px;
						margin-right: 0px;
						margin-left: 0px;
						background: none;
					}

					.check {
						position: relative;
						width: 20px;
						height: 20px;
						border-radius: 2px;
						appearance: none;
						background-color: #bbb;
						transition: all .3s;
						margin-left: 120Px;
					}

					.check::before {
						content: '';
						position: absolute;
						border: solid #fff;
						display: block;
						width: .3em;
						height: .6em;
						border-width: 0 .2em .2em 0;
						z-index: 1;
						opacity: 0;
						right: calc(50% - .3em);
						top: calc(50% - .6em);
						transform: rotate(0deg);
						transition: all .3s;
						transform-origin: center center;
						margin-left: 120Px;
					}

					.check:checked {
						animation: a .3s ease-in forwards;
						background-color: rgb(120, 190, 120);
					}

					.check:checked::before {
						opacity: 1;
						transform: rotate(405deg);
					}




					input[type=text] {
						padding: 20px 20px;
					}

					.text {
						font-size: 20px;
						margin-top: 5px;
					}

					.numbertext {
						color: #000000;
						font-size: 30px;
						padding: 8px 12px;
						top: 0;
						text-align: center;
						width: 1500px;
						hight: 150px;
						margin-left: auto;
						margin-top: 500px;
						margin-right: auto;
						position: absolute;
						background-color: aqua;
						background-color: rgba(0, 0, 0, .25);
						color: white;
						padding: 20px;
					}

					.center {
						text-align: center;
					}

					.form {
						padding: 8px 30px;
					}

					div.ex1 {
						background-color: lightblue;
						width: 1500px;
						height: 700px;
						overflow: scroll;
					}

					body {
						font-family: Arial, Helvetica, sans-serif;
					}

					#myImg {
						border-radius: 5px;
						cursor: pointer;
						transition: 0.3s;
					}

					#myImg:hover {
						opacity: 0.7;
					}

					/* The Modal (background) */
					.modal {
						display: none;
						/* Hidden by default */
						position: fixed;
						/* Stay in place */
						z-index: 1;
						/* Sit on top */
						padding-top: 50px;
						/* Location of the box */
						left: 0;
						top: 0;
						width: 100%;
						/* Full width */
						height: 100%;
						/* Full height */
						overflow: auto;
						/* Enable scroll if needed */
						background-color: rgb(0, 0, 0);
						/* Fallback color */
						background-color: rgba(0, 0, 0, 0.9);
						/* Black w/ opacity */
					}

					/* Modal Content (image) */
					.modal-content {
						margin: auto;
						display: block;
						width: 1500px;
						max-width: 1500px;
					}

					/* Caption of Modal Image */
					#caption {
						margin: auto;
						display: block;
						width: 40%;
						max-width: 100px;
						text-align: center;
						color: #000000;
						padding: 10px 0;
						height: 10px;
					}

					/* Add Animation */
					.modal-content,
					#caption {
						-webkit-animation-name: zoom;
						-webkit-animation-duration: 0.6s;
						animation-name: zoom;
						animation-duration: 0.6s;
						width: 1500px;
					}



					/* The Close Button */
					.close {
						position: absolute;
						top: 15px;
						right: 35px;
						color: #f1f1f1;
						font-size: 40px;
						font-weight: bold;
						transition: 0.3s;
					}

					.close:hover,
					.close:focus {
						color: #bbb;
						text-decoration: none;
						cursor: pointer;
					}

					/* 100% Image Width on Smaller Screens */
					@media only screen and (max-width: 700px) {
						.modal-content {
							width: 100%;
						}
					}

					.exit {
						z-index: 2;
						position: absolute;
						font-size: 70px;
						right: 3%;
					}

					.close {
						display: none;
					}

					* {
						margin: 0;
						padding: 0;
						box-sizing: border-box;
						font-family: "Poppins", sans-serif;
					}

					::selection {
						color: #fff;
						background: #6990F2;
					}

					.wrapper {
						width: 430px;
						background: #fff;
						border-radius: 5px;
						padding: 30px;
						box-shadow: 7px 7px 12px rgba(0, 0, 0, 0.05);
					}

					.wrapper header {
						color: #6990F2;
						font-size: 27px;
						font-weight: 600;
						text-align: center;
					}

					.wrapper form {
						height: 167px;
						display: flex;
						cursor: pointer;
						margin: 30px 0;
						align-items: center;
						justify-content: center;
						flex-direction: column;
						border-radius: 5px;
						border: 2px dashed #6990F2;
					}



					.pagination a.active {
						background-color: #7c7f7c;
						color: white;
					}

					.pagination a:hover:not(.active) {
						background-color: #ddd;
					}
				</style>

				<jsp:include page="../layouts/mainLayoutHead.jsp" />
				<!-- Custom Stylesheet -->
				<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
				<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">

				<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/image-uploader.min.css">
				<link
					href="https://fonts.googleapis.com/css?family=Lato:300,700|Montserrat:300,400,500,600,700|Source+Code+Pro&display=swap"
					rel="stylesheet">

				<link rel="stylesheet"
					href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
				<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
				<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
				<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>






				<link
					href="${pageContext.request.contextPath }/resources/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
					rel="stylesheet">
				<!-- Page plugins css -->
				<link
					href="${pageContext.request.contextPath }/resources/plugins/clockpicker/dist/jquery-clockpicker.min.css"
					rel="stylesheet">
				<!-- Color picker plugins css 
    <link href="./plugins/jquery-asColorPicker-master/css/asColorPicker.css" rel="stylesheet">-->
				<!-- Date picker plugins css -->
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
				<!-- newwww -->
				<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/pagination.css">
				<link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css" />
				<script type="text/javascript" src="multiple-dropdown.js"></script>
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
										<h4 class="card-title">ตรวจสอบภาพ > ${nameDisease}</h4>
										<p align="right">
											<a href="${pageContext.request.contextPath}/approve/index"
												class="btn mb-1 btn-light">เลือกโรคใหม่ <span
													class="btn-icon-right"><i class="fa fa-repeat"></i></span></a>
											<a href="${pageContext.request.contextPath}/approve/detail/${id}"
												class="btn mb-1 btn-secondary">ภาพที่รอตรวจสอบ<span
													class="btn-icon-right"><i
														class="fa fa-hourglass-half"></i></span></a>
											<a href="${pageContext.request.contextPath}/approve/success/${id}"
												class="btn mb-1 btn-success">ภาพที่อนุมัติแล้ว <span
													class="btn-icon-right"><i class="fa fa-check"></i></span></a>
											<a href="${pageContext.request.contextPath}/approve/reject/${id}"
												class="btn mb-1 btn-warning">ภาพที่ไม่อนุมัติ <span
													class="btn-icon-right"><i class="fa fa-close"></i></span></a>
										</p>
										<c:if test="${MaxSize==0}">
											<br><br><br><br><br>
											<br><br><br><br><br>
											<h1 align="center">ไม่มีภาพที่อนุมัติแล้ว</h1>
											<br><br><br><br><br>
											<br><br><br><br><br>
										</c:if>
										<c:if test="${MaxSize!=0}">
											<h3 align="left">ภาพที่อนุมัติแล้ว</h3>
											<br>
											<div class="row">
												<div class="col-lg-12">
													<div class="form-validation">
														<form
															action="${pageContext.request.contextPath}/approve/find/success/${id}/page/${page}/value/${value}"
															class="form-inline float-sm-right" method="POST">
															<div class="form-group mx-sm-3 mb-2">
																<div class="input-daterange input-group"
																	id="date-range">
																	<span class="p mt-2"> วันที่เริ่ม </span>
																	<input type="text"
																		class="form-control form-control-sm"
																		name="dateInfoStart"
																		value="${formatter.format(dateInfoStart)}"
																		id="dateInfoStart" required>
																	<span class="p mt-2"> จนถึงวันที่ </span>
																	<input type="text"
																		class="form-control form-control-sm"
																		name="dateInfoEnd"
																		value="${formatter.format(dateInfoEnd)}"
																		id="dateInfoEnd" required>
																</div>
															</div>
															<input type="submit" value="ค้นหา"
																class="btn mb-1 btn-primary">
															<a class="btn btn-light"
																href="${pageContext.request.contextPath}/approve/success/${id}">ทั้งหมด</a>
														</form>
													</div>
												</div>
											</div>

											<div class="row">
												<div class="col-lg-6">
													<span>แสดงหน้าละ </span>
													<select name="value" id="itemSize">
														<c:forEach items="${sizePages}" var="sp">
															<c:choose>
																<c:when test="${sp==value}">
																	<option value="${sp}" selected>${sp}</option>
																</c:when>
																<c:otherwise>
																	<option value="${sp}">${sp}</option>
																</c:otherwise>
															</c:choose>
														</c:forEach>
													</select>
													<span>รายการ</span> <input type="hidden" id="pageNum"
														value="${page}" />
												</div>
											</div>

											<div class="row">
												<div class="col-12">
													<div class="form-validation">
														<div class="basic-form">
															<c:choose>
																<c:when test="${statusDate}">
																	<form class="form-valide"
																		action="${pageContext.request.contextPath}/approve/success/edit/date/${id}/page/${page}/value/${value}"
																		method="POST" modelAttribute="DTO"
																		enctype="multipart/form-data" id="statusbutton">
																		
																		<div class="row">
																			<div class="col-12">
																				<div class="card">
																					<div class="card-body">
																						<p align="right">
																							<input type="button"
																								id="all" onclick="All()"
																								class="btn btn-light"
																								role="button"
																								value="เลือกทั้งหมด">
																							<input type="button"
																								id="all"
																								onclick="deselectAll()"
																								class="btn btn-light"
																								role="button"
																								value="ไม่เลือกทั้งหมด">
																						</p>
																						<input type="hidden"
																							name="dateInfoStart"
																							value="${formatter.format(dateInfoStart)}">
																						<input type="hidden"
																							name="dateInfoEnd"
																							value="${formatter.format(dateInfoEnd)}">
																						<div class="form-group"
																							id="imglist"></div>
																						<p align="center"
																							style="font-size: 20px;">

																							<input
																								onclick="return confirm('คุณยืนยันที่จะที่ไม่อนุมัติหรือไม่');"
																								type="submit"
																								class="btn btn-warning"
																								role="button"
																								name="status0"
																								value="ไม่อนุมัติ">
																							<input
																								onclick="return confirm('คุณยืนยันที่จะลบหรือไม่');"
																								type="submit"
																								class="btn btn-danger"
																								role="button"
																								name="status0"
																								value="ลบรูป">

																						</p>
																					</div>
																				</div>
																			</div>

																		</div>

																	</form>
																</c:when>
																<c:otherwise>
																	<form class="form-valide"
																		action="${pageContext.request.contextPath}/approve/success/edit/${id}/page/${page}/value/${value}"
																		method="POST"
																		modelAttribute="SelectedImgSurveyTargetPointDTO"
																		enctype="multipart/form-data" id="buttonn">
																		<div class="row">
																			<div class="col-12">
																				<div class="card">
																					<div class="card-body">
																						<p align="right">
																							<input type="button"
																								id="all" onclick="All()"
																								class="btn btn-light"
																								role="button"
																								value="เลือกทั้งหมด">
																							<input type="button"
																								id="all"
																								onclick="deselectAll()"
																								class="btn btn-light"
																								role="button"
																								value="ไม่เลือกทั้งหมด">
																						</p>
																						<div class="form-group"
																							id="imglist"></div>
																						<p align="center"
																							style="font-size: 20px;">

																							<input
																								onclick="return confirm('คุณยืนยันที่จะที่ไม่อนุมัติหรือไม่');"
																								type="submit"
																								class="btn btn-warning"
																								role="button"
																								name="status0"
																								value="ไม่อนุมัติ">
																							<input
																								onclick="return confirm('คุณยืนยันที่จะลบหรือไม่');"
																								type="submit"
																								class="btn btn-danger"
																								role="button"
																								name="status0"
																								value="ลบรูป">

																						</p>
																					</div>
																				</div>
																			</div>

																		</div>

																	</form>
																</c:otherwise>
															</c:choose>


															<div class="col-lg-6" id="listItem2">
															</div>

															<div class="w3-center">
																<div class="w3-bar">
																	<div class="bootstrap-pagination">
																		<nav></nav>
																	</div>
																</div>
															</div>

															<div class="row col-lg-12">
																<input type="hidden" id="totalItem"
																	value="${totalItem}" />
																<div class="col-lg-6" id="listItem">
																</div>
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

												<input type="hidden" id="pageNum" value="${page}" />
											</div>
										</c:if>
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
				<script src="http://code.jquery.com/jquery-3.6.1.js"></script>
				<script src="${pageContext.request.contextPath }/resources/js/pagination.js"></script>

				<!-- Custom Script -->
				<script>
					function All() {

						checkboxes = document.getElementsByClassName("check")
						var pageSizeSelected = $("#itemSize option:selected").val();
						for (let i = 0; i < pageSizeSelected; i++)
							checkboxes[i].checked = true;

					}
					function deselectAll() {

						checkboxes = document.getElementsByClassName("check")
						var pageSizeSelected = $("#itemSize option:selected").val();
						for (let i = 0; i < pageSizeSelected; i++)
							checkboxes[i].checked = false;

					}
				</script>
				<script>
					function editSuccessDate(type, a) {
						let textReject = "คุณยืนยันที่จะที่ไม่อนุมัติหรือไม่";
						let textDelete = "คุณยืนยันที่จะลบหรือไม่";
						if(type==="Reject"){
							 if (confirm(textReject) == false) {
								    return true;
								  }
						}else if(type==="Delete") {
							if (confirm(textDelete) == false) {
							    return true;
							  }
						}
						var pageSizeSelected = $("#itemSize option:selected").val();


						var pageNumber = document.getElementById("pageNum").value;

						$
							.ajax({
								type: 'POST',
								url: "${pageContext.request.contextPath }/approve/success/edit/date/${id}/" + a + "/page/"
									+ pageNumber
									+ "/value/" + pageSizeSelected,
								data: {
									dateInfoStart: $('#dateInfoStart').val(),
									dateInfoEnd: $('#dateInfoEnd').val(),
									type: type
								},
								success: function (response) {
									$('#imglist').empty();
									$('#imglist')
										.html(response);
									$('#statusbutton').attr("action", "${pageContext.request.contextPath}/approve/success/edit/date/${id}/page/${page}/value/" + pageSizeSelected);
									$('#pageNum').attr("value", pageNumber);
									var size = document
										.getElementById("MaxSize").value;
									if(size==0){
										 window.location.href="${pageContext.request.contextPath}/approve/success/${id}";
									}
									firstItem = document.getElementById("firstItem").value;
									lastItem = document.getElementById("lastItem").value;
									$('#pageNum').attr("value", pageNumber);
									$('#listItem').empty();
									$('#listItem').html(
										'<div class="col-lg-6" id="listItem"><span>รายการที่ ' + firstItem +
										' ถึง ' + lastItem + ' จาก ' + size + ' รายการ</span></div>');
									var container = $("#pagination");
									if (!container.length)
										return;
									function paginateOption(pageSize, keyPath, totalItem) {
										var options = {
											dataSource: Array.from(Array(totalItem).keys()),
											pageSize: pageSize,
											showGoInput: true,
											showGoButton: true,
											showNavigator: true,
											//formatNavigator:
											callback: function (response, pagination) {
												var pageNumber = pagination.pageNumber;
												var firstItem, lastItem;

												$.ajax({
													method: "GET",
													url: "${pageContext.request.contextPath}/success/index/page/date/${id}/" + pageNumber + "/value/" + pageSize + keyPath,
													success: function (response) {
														// $('#imglist').empty();
														// $('#imglist').html(response);
														firstItem = document.getElementById("firstItem").value;
														lastItem = document.getElementById("lastItem").value;
														$('#pageNum').attr("value", pageNumber);
														$('#listItem').empty();
														$('#listItem').html(
															'<div class="col-lg-6" id="listItem"><span>รายการที่ ' + firstItem +
															' ถึง ' + lastItem + ' จาก ' + totalItem + ' รายการ</span></div>');
													},
												});
											},
										};
										return options;
									}
									paginateOption(pageSize, keyPath, size);
									if (size % pageSizeSelected == 0) {
										$('pagination').empty();
										var container = $("#pagination");
										if (!container.length)
											return;
										var sources = (function () {
											var result = [];
											for (var i = 1; i <= size; i++) {
												result.push(i);

											}

											return result;
										})();

										var pageNumber;
										var options = {
											dataSource: sources,
											pageSize: pageSizeSelected,
											showGoInput: true,
											showGoButton: true,
											showNavigator: true,
											callback: function (
												response,
												pagination) {

												pageNumber = pagination.pageNumber;
												$
													.ajax({
														method: "POST",
														url: "${pageContext.request.contextPath }/approve/success/date/list/${id}/page/"
															+ pageNumber
															+ "/value/"
															+ pageSizeSelected,
														data: {
															dateInfoStart: $('#dateInfoStart').val(),
															dateInfoEnd: $('#dateInfoEnd').val(),
														},
														success: function (
															response) {
																$('#imglist')
																.empty();
															$('#imglist')
																.html(
																	response);
															$('#buttonn').attr("action",
																"${pageContext.request.contextPath}/approve/edit/${id}/page/${page}/value/" +
																pageSizeSelected);
															$('#pageNum').attr("value", pageNumber);
															firstItem = document.getElementById("firstItem").value;
															lastItem = document.getElementById("lastItem").value;
															$('#pageNum').attr("value", pageNumber);
															$('#listItem').empty();
															$('#listItem').html(
																'<div class="col-lg-6" id="listItem"><span>รายการที่ ' + firstItem +
																' ถึง ' + lastItem + ' จาก ' + size + ' รายการ</span></div>');


														},
													});
											},
										};


										container.pagination(options);

									}

								},
							});
					}
				</script>
				<script>
					function editSuccess(type, a) {
						let textReject = "คุณยืนยันที่จะที่ไม่อนุมัติหรือไม่";
						let textDelete = "คุณยืนยันที่จะลบหรือไม่";
						if(type==="Reject"){
							 if (confirm(textReject) == false) {
								    return true;
								  }
						}else if(type==="Delete") {
							if (confirm(textDelete) == false) {
							    return true;
							  }
						}
						var pageSizeSelected = $("#itemSize option:selected").val();


						var pageNumber = document.getElementById("pageNum").value;


						$
							.ajax({
								type: 'POST',
								url: "${pageContext.request.contextPath }/approve/success/edit/${id}/" + a + "/page/"
									+ pageNumber
									+ "/value/" + pageSizeSelected,
								data: {

									type: type
								},
								success: function (response) {
									$('#imglist').empty();
									$('#imglist')
										.html(response);
									$('#statusbutton').attr("action", "${pageContext.request.contextPath}/approve/success/edit/${id}/page/${page}/value/" + pageSizeSelected);
									$('#pageNum').attr("value", pageNumber);
									// var size = document
									// 	.getElementById("sizenow").value;
									// var first = document
									// 	.getElementById("firstitem").value;
									// var last = document
									// 	.getElementById("lastItem").value;
									var size = document
										.getElementById("MaxSize").value;
									if(size==0){
										 window.location.href="${pageContext.request.contextPath}/approve/success/${id}";
									}
									firstItem = document.getElementById("firstItem").value;
									lastItem = document.getElementById("lastItem").value;
									$('#pageNum').attr("value", pageNumber);
									$('#listItem').empty();

									$('#listItem').html(
										'<div class="col-lg-6" id="listItem"><span>รายการที่ ' + firstItem +
										' ถึง ' + lastItem + ' จาก ' + size + ' รายการ</span></div>');
									//container.pagination(paginateOption(pageSizeSelected,size));
									var container = $("#pagination");
									if (!container.length)
										return;
									function paginateOption(pageSize, keyPath, totalItem) {
										var options = {
											dataSource: Array.from(Array(totalItem).keys()),
											pageSize: pageSize,
											showGoInput: true,
											showGoButton: true,
											showNavigator: true,
											//formatNavigator:
											callback: function (response, pagination) {
												var pageNumber = pagination.pageNumber;
												var firstItem, lastItem;

												$.ajax({
													method: "GET",
													url: "${pageContext.request.contextPath}/success/index/page/${id}/" + pageNumber + "/value/" + pageSize + keyPath,
													success: function (response) {
														// $('#imglist').empty();
														// $('#imglist').html(response);
														firstItem = document.getElementById("firstItem").value;
														lastItem = document.getElementById("lastItem").value;
														$('#pageNum').attr("value", pageNumber);
														$('#listItem').empty();
														$('#listItem').html(
															'<div class="col-lg-6" id="listItem"><span>รายการที่ ' + firstItem +
															' ถึง ' + lastItem + ' จาก ' + totalItem + ' รายการ</span></div>');
													},
												});
											},
										};
										return options;
									}
									paginateOption(pageSize, keyPath, size);
									if (size % pageSizeSelected == 0) {
										$('pagination').empty();
										var container = $("#pagination");
										if (!container.length)
											return;


										var sources = (function () {
											var result = [];
											for (var i = 1; i <= size; i++) {
												result.push(i);

											}

											return result;
										})();

										var pageNumber;
										var options = {
											dataSource: sources,
											pageSize: pageSizeSelected,
											showGoInput: true,
											showGoButton: true,
											showNavigator: true,
											callback: function (
												response,
												pagination) {
												pageNumber = pagination.pageNumber;
												$
													.ajax({
														method: "POST",
														url: "${pageContext.request.contextPath }/approve/success/list/${id}/page/" +
															pageNumber +
															"/value/" +
															pageSizeSelected,
														success: function (
															response) {
																$('#imglist')
																.empty();
															$('#imglist')
																.html(
																	response);
															$('#buttonn').attr("action",
																"${pageContext.request.contextPath}/approve/edit/${id}/page/${page}/value/" +
																pageSizeSelected);
															$('#pageNum').attr("value", pageNumber);
															firstItem = document.getElementById("firstItem").value;
															lastItem = document.getElementById("lastItem").value;
															$('#pageNum').attr("value", pageNumber);
															$('#listItem').empty();
															$('#listItem').html(
																'<div class="col-lg-6" id="listItem"><span>รายการที่ ' + firstItem +
																' ถึง ' + lastItem + ' จาก ' + size + ' รายการ</span></div>');



														},
													});
											},
										};


										container.pagination(options);

									}

								},
							});
					}
				</script>
				<script>
					var pageSize = $("#itemSize option:selected").val();
					var totalItem;
					var keyPath = "";

					let dateInfoStart = new Date(document.getElementById("dateInfoStart").value);
					let dateInfoEnd = new Date(document.getElementById("dateInfoEnd").value);
					function dateIsValid(date) {
						return date instanceof Date && !isNaN(date);
					}


					if (!dateIsValid(dateInfoStart) || !dateIsValid(dateInfoEnd)) {
						$(function () {
							var container = $("#pagination");
							if (!container.length)
								return;

							$.ajax({
								method: "GET",
								url: "${pageContext.request.contextPath}/success/index/total/${id}",

							}).then(function (data, status, jqXhr) {
								totalItem = data;
								container.pagination(paginateOption(pageSize, keyPath, totalItem));
							});
						});

						$("#itemSize").change(

							function () {
								var container = $("#pagination");
								if (!container.length)
									return;
								$('pagination').empty();

								pageSize = $("#itemSize option:selected").val();
								container.pagination(paginateOption(pageSize, keyPath, totalItem));
							});
						function paginateOption(pageSize, keyPath, totalItem) {
							var options = {
								dataSource: Array.from(Array(totalItem).keys()),
								pageSize: pageSize,
								showGoInput: true,
								showGoButton: true,
								showNavigator: true,
								//formatNavigator:
								callback: function (response, pagination) {
									var pageNumber = pagination.pageNumber;
									var firstItem, lastItem;

									$.ajax({
										method: "GET",
										url: "${pageContext.request.contextPath}/success/index/page/${id}/" + pageNumber + "/value/" + pageSize + keyPath,
										success: function (response) {
											$('#imglist').empty();
											$('#imglist').html(response);
											firstItem = document.getElementById("firstItem").value;
											lastItem = document.getElementById("lastItem").value;
											var size = document.getElementById("MaxSize").value;
											$('#pageNum').attr("value", pageNumber);
											$('#listItem').empty();
											$('#listItem').html(
												'<div class="col-lg-6" id="listItem"><span>รายการที่ ' + firstItem +
												' ถึง ' + lastItem + ' จาก ' + size + ' รายการ</span></div>');
										},
									});
								},
							};
							return options;
						}
					} else if (dateIsValid(dateInfoStart) && dateIsValid(dateInfoEnd)) {
						$(function () {
							var container = $("#pagination");
							if (!container.length)
								return;

							$.ajax({
								method: "GET",
								url: "${pageContext.request.contextPath}/success/index/total/date/${id}",
								data: {
									dateInfoStart: $('#dateInfoStart').val(),
									dateInfoEnd: $('#dateInfoEnd').val(),
								}
							}).then(function (data, status, jqXhr) {
								totalItem = data;
								container.pagination(paginateOption(pageSize, keyPath, totalItem));
							});
						});

						$("#itemSize").change(

							function () {
								var container = $("#pagination");
								if (!container.length)
									return;
								$('pagination').empty();

								pageSize = $("#itemSize option:selected").val();
								container.pagination(paginateOption(pageSize, keyPath, totalItem));
							});
						function paginateOption(pageSize, keyPath, totalItem) {
							var options = {
								dataSource: Array.from(Array(totalItem).keys()),
								pageSize: pageSize,
								showGoInput: true,
								showGoButton: true,
								showNavigator: true,
								//formatNavigator:
								callback: function (response, pagination) {
									var pageNumber = pagination.pageNumber;
									var firstItem, lastItem;

									$.ajax({
										method: "GET",
										url: "${pageContext.request.contextPath}/success/index/page/date/${id}/" + pageNumber + "/value/" + pageSize + keyPath,
										data: {
											dateInfoStart: $('#dateInfoStart').val(),
											dateInfoEnd: $('#dateInfoEnd').val(),
										},
										success: function (response) {
											$('#imglist').empty();
											$('#imglist').html(response);
											firstItem = document.getElementById("firstItem").value;
											lastItem = document.getElementById("lastItem").value;
											var size = document.getElementById("MaxSize").value;
											$('#pageNum').attr("value", pageNumber);
											$('#listItem').empty();
											$('#listItem').html(
												'<div class="col-lg-6" id="listItem"><span>รายการที่ ' + firstItem +
												' ถึง ' + lastItem + ' จาก ' + size + ' รายการ</span></div>');
										},
									});
								},
							};
							return options;
						}





					}
				</script>
				<!-- <script>
					let dateInfoStart = new Date(document.getElementById("dateInfoStart").value);
					let dateInfoEnd = new Date(document.getElementById("dateInfoEnd").value);
					function dateIsValid(date) {
						return date instanceof Date && !isNaN(date);
					}
					if (!dateIsValid(dateInfoStart) || !dateIsValid(dateInfoEnd)) {
						var pageSizeSelected = $("#itemSize option:selected").val();
						$(function () {
							(function (name) {
								var container = $("#pagination-" + name);
								if (!container.length)
									return;
								var size = document.getElementById("size").value;
								var sources = (function () {
									var result = [];
									for (var i = 1; i <= size; i++) {
										result.push(i);
									}

									return result;
								})();

								var pageNumber;

								var options = {
									dataSource: sources,
									pageSize: pageSizeSelected,
									showGoInput: true,
									showGoButton: true,
									showNavigator: true,
									callback: function (response, pagination) {

										pageNumber = pagination.pageNumber;
										$
											.ajax({
												method: "POST",
												url: "${pageContext.request.contextPath }/approve/success/list/${id}/page/"
													+ pageNumber
													+ "/value/" + pageSizeSelected,
												success: function (response) {
													$('#imglist').empty();
													$('#imglist')
														.html(response);
													$('#pageNum').attr("value", pageNumber);
													$('#listItem2').empty();
													var first = document
														.getElementById("firstitem").value;
													var last = document
														.getElementById("lastItem").value;
													var size = document
														.getElementById("MaxSize").value;
													$('#listItem2')
														.html(
															'<div class="col-lg-6" id="listItem"><span>รายการที่ '
															+ first
															+ ' ถึง '
															+ last
															+ ' จาก '
															+ size
															+ ' รายการ</span></div>');
												},
											});
									},
								};

								container.pagination(options);
							})("demo2");
						});

						$(function () {
							$("#itemSize")
								.change(
									function () {
										var pageNumber = document
											.getElementById("pageNum").value;
										pageSizeSelected = $(
											"#itemSize option:selected").val();
										$
											.ajax({
												method: "POST",
												url: "${pageContext.request.contextPath }/approve/success/list/${id}/page/"
													+ pageNumber
													+ "/value/"
													+ pageSizeSelected,
												success: function (response) {
													$('#imglist').empty();
													$('#imglist')
														.html(response);

													$('pagination').empty();
													var container = $("#pagination");
													if (!container.length)
														return;

													var size = document
														.getElementById("size").value;
													var sources = (function () {
														var result = [];
														for (var i = 1; i <= size; i++) {
															result.push(i);
														}

														return result;
													})();

													var pageNumber;
													var options = {
														dataSource: sources,
														pageSize: pageSizeSelected,
														showGoInput: true,
														showGoButton: true,
														showNavigator: true,
														callback: function (
															response,
															pagination) {

															pageNumber = pagination.pageNumber;
															$
																.ajax({
																	method: "POST",
																	url: "${pageContext.request.contextPath }/approve/success/list/${id}/page/"
																		+ pageNumber
																		+ "/value/"
																		+ pageSizeSelected,
																	success: function (
																		response) {
																		$('#imglist').empty();
																		$('#imglist')
																			.html(response);
																		$('#pageNum').attr("value", pageNumber);
																		$('#listItem2').empty();
																		var first = document
																			.getElementById("firstitem").value;
																		var last = document
																			.getElementById("lastItem").value;
																		var size = document
																			.getElementById("MaxSize").value;
																		$('#listItem2')
																			.html(
																				'<div class="col-lg-6" id="listItem"><span>รายการที่ '
																				+ first
																				+ ' ถึง '
																				+ last
																				+ ' จาก '
																				+ size
																				+ ' รายการ</span></div>');

																	},
																});
														},
													};


													container.pagination(options);
												},
											});
									});
						});
					} else if (dateIsValid(dateInfoStart) && dateIsValid(dateInfoEnd)) {
						var pageSizeSelected = $("#itemSize option:selected").val();
						$(function () {
							(function (name) {
								var container = $("#pagination-" + name);
								if (!container.length)
									return;
								var size = document.getElementById("size").value;
								var sources = (function () {
									var result = [];
									for (var i = 1; i <= size; i++) {
										result.push(i);
									}

									return result;
								})();

								var pageNumber;

								formData = new FormData();
								var options = {
									dataSource: sources,
									pageSize: pageSizeSelected,
									showGoInput: true,
									showGoButton: true,
									showNavigator: true,

									callback: function (response, pagination) {

										pageNumber = pagination.pageNumber;
										pageSize = pagination.pageSize;
										$
											.ajax({
												type: 'POST',
												url: "${pageContext.request.contextPath }/approve/success/date/list/${id}/page/"
													+ pageNumber
													+ "/value/" + pageSizeSelected,
												data: {
													dateInfoStart: $('#dateInfoStart').val(),
													dateInfoEnd: $('#dateInfoEnd').val(),
												},
												success: function (response) {
													$('#imglist').empty();
													$('#imglist')
														.html(response);
													$('#statusbutton').attr("action", "${pageContext.request.contextPath}/approve/success/edit/date/${id}/page/${page}/value/" + pageSizeSelected);
													$('#pageNum').attr("value", pageNumber);
													$('#listItem2').empty();
													var first = document
														.getElementById("firstitem").value;
													var last = document
														.getElementById("lastItem").value;
													var size = document
														.getElementById("MaxSize").value;
													$('#listItem2')
														.html(
															'<div class="col-lg-6" id="listItem"><span>รายการที่ '
															+ first
															+ ' ถึง '
															+ last
															+ ' จาก '
															+ size
															+ ' รายการ</span></div>');
												},
											});
									},
								};

								container.pagination(options);
							})("demo2");
						});

						$(function () {
							$("#itemSize")
								.change(
									function () {
										var pageNumber = document
											.getElementById("pageNum").value;

										pageSizeSelected = $("#itemSize option:selected").val();
										$
											.ajax({
												method: "POST",
												url: "${pageContext.request.contextPath }/approve/success/date/list/${id}/page/"
													+ pageNumber
													+ "/value/"
													+ pageSizeSelected,
												data: {
													dateInfoStart: $('#dateInfoStart').val(),
													dateInfoEnd: $('#dateInfoEnd').val(),
												},
												success: function (response) {
													$('#imglist').empty();
													$('#imglist')
														.html(response);

													$('pagination').empty();
													$('#statusbutton').attr("action", "${pageContext.request.contextPath}/approve/success/edit/date/${id}/page/${page}/value/" + pageSizeSelected);
													var container = $("#pagination");
													$('#pageNum').attr("value", pageNumber);
													if (!container.length)
														return;

													var size = document
														.getElementById("size").value;
													var sources = (function () {
														var result = [];
														for (var i = 1; i <= size; i++) {
															result.push(i);
														}

														return result;
													})();

													var pageNumber;
													var options = {
														dataSource: sources,
														pageSize: pageSizeSelected,
														showGoInput: true,
														showGoButton: true,
														showNavigator: true,
														callback: function (
															response,
															pagination) {

															pageNumber = pagination.pageNumber;
															$
																.ajax({
																	method: "POST",
																	url: "${pageContext.request.contextPath }/approve/success/date/list/${id}/page/"
																		+ pageNumber
																		+ "/value/"
																		+ pageSizeSelected,
																	data: {
																		dateInfoStart: $('#dateInfoStart').val(),
																		dateInfoEnd: $('#dateInfoEnd').val(),
																	},
																	success: function (
																		response) {
																		$('#imglist').empty();
																		$('#imglist')
																			.html(response);
																		$('#statusbutton').attr("action", "${pageContext.request.contextPath}/approve/success/edit/date/${id}/page/${page}/value/" + pageSizeSelected);
																		$('#pageNum').attr("value", pageNumber);
																		$('#listItem2').empty();
																		var first = document
																			.getElementById("firstitem").value;
																		var last = document
																			.getElementById("lastItem").value;
																		var size = document
																			.getElementById("MaxSize").value;
																		$('#listItem2')
																			.html(
																				'<div class="col-lg-6" id="listItem"><span>รายการที่ '
																				+ first
																				+ ' ถึง '
																				+ last
																				+ ' จาก '
																				+ size
																				+ ' รายการ</span></div>');
																	},
																});
														},
													};


													container.pagination(options);
												},
											});

									});
						});
					}






				</script> -->




				<script>
					function openModal() {
						document.getElementById("myModal").style.display = "block";
					}

					function closeModal() {
						document.getElementById("myModal").style.display = "none";
					}

					var slideIndex = 1;
					showSlides(slideIndex);

					function plusSlides(n) {
						showSlides(slideIndex += n);
					}

					function currentSlide(n) {
						showSlides(slideIndex = n);
					}

					function showSlides(n) {
						var i;
						var slides = document.getElementsByClassName("mySlides");
						var dots = document.getElementsByClassName("demo");
						var captionText = document.getElementById("caption");
						if (n > slides.length) { slideIndex = 1 }
						if (n < 1) { slideIndex = slides.length }
						for (i = 0; i < slides.length; i++) {
							slides[i].style.display = "none";
						}
						for (i = 0; i < dots.length; i++) {
							dots[i].className = dots[i].className.replace(" active", "");
						}
						slides[slideIndex - 1].style.display = "block";
						dots[slideIndex - 1].className += " active";
						captionText.innerHTML = dots[slideIndex - 1].alt;
					}
				</script>

				<script
					src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js">
					</script>
				<script
					src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js">
					</script>
				<script
					src="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.js">
					</script>
				<script
					src="${pageContext.request.contextPath }/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.js">
					</script>
				<script src="${pageContext.request.contextPath }/resources/plugins/moment/moment.js"></script>
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
					src="${pageContext.request.contextPath }/resources/plugins/timepicker/bootstrap-timepicker.min.js">
					</script>
				<script
					src="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.js">
					</script>
				<script src="${pageContext.request.contextPath }/resources/js/plugins-init/form-pickers-init.js">
				</script>
			</body>

			</html>
