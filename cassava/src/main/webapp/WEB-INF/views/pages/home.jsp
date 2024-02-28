<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../layouts/mainLayoutHead.jsp" />

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>


<!-- Custom Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">
<link
	href="${pageContext.request.contextPath }/resources/plugins/tables/css/datatable/dataTables.bootstrap4.min.css"
	rel="stylesheet">

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

<script type="text/javascript" src="multiple-dropdown.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>



<style>
#selectYear, #selectYear2 {
	display: none;
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
							<h3>ยินดีต้อนรับเข้าสู่ระบบสำรวจโรคมันสำปะหลัง</h3>
							<div align="right">

								<sec:authorize access="hasAnyRole('ROLE_systemAdmin')">
									<a type="button" class="btn mb-1 btn-primary"
										<c:if test="${role == 'systemAdmin'}">
									style="background-color:#7698ff;"
								</c:if>
										href="${pageContext.request.contextPath}/home/systemadmin">ข้อมูลระบบ</a>

								</sec:authorize>

								<sec:authorize access="hasAnyRole('ROLE_fieldRegistrar')">
									<a type="button" class="btn mb-1 btn-primary"
										<c:if test="${role == 'fieldRegistrar'}">
									style="background-color:#7698ff;"
								</c:if>
										href="${pageContext.request.contextPath}/home/fieldregistrar">ข้อมูลหน่วยงาน</a>
								</sec:authorize>

								<sec:authorize
									access="hasAnyRole('ROLE_systemAdmin','ROLE_fieldRegistrar')">
									<a type="button" class="btn mb-1 btn-primary"
										<c:if test="${role == 'staff'}">
									style="background-color:#7698ff;"
								</c:if>
										href="${pageContext.request.contextPath}/home/staff">ข้อมูลส่วนตัว</a>
								</sec:authorize>
							</div>
						</div>

					</div>
				</div>

				<!-- body -->
				<input type="hidden" id="regions" value="${regions}"> <input
					type="hidden" id="amountFields" value="${sumFields}"> <input
					type="hidden" id="years" value="${yearsLineChart1}"> <input
					type="hidden" id="amounts" value="${amountsLineChart1}"> <input
					type="hidden" id="years2" value="${yearsLineChart2}"> <input
					type="hidden" id="amounts2" value="${amountsLineChart2}"> <input
					type="hidden" id="yearsNaturalEnemy" value="${yearsLineChart3}">
				<input type="hidden" id="amountsNaturalEnemy"
					value="${amountsLineChart3}"> <input type="hidden"
					id="yearsPest" value="${yearsLineChart4}"> <input
					type="hidden" id="amountsPest" value="${amountsLineChart4}">

				<input type="hidden" id="yearsDiseaseC5"
					value="${yearsDiseaseLineChart5}"> <input type="hidden"
					id="amountsDiseaseC5" value="${amountsDiseaseLineChart5}">

				<input type="hidden" id="yearsNaturalEnemyC5"
					value="${yearsNaturalEnemyLineChart5}"> <input
					type="hidden" id="amountsNaturalEnemyC5"
					value="${amountsNaturalEnemyLineChart5}"> <input
					type="hidden" id="yearsPestC5" value="${yearsPestLineChart5}">
				<input type="hidden" id="amountsPestC5"
					value="${amountsPestLineChart5}"> <input type="hidden"
					id="plantingRegionChartHome" value="${plantingRegionPieRegion2}">
				<input type="hidden" id="plantingRegionChartAmountHome"
					value="${sumPieRegion2}"> <input type="hidden"
					id="varietyChartHome" value="${pieNameVarietyCharts}"> <input
					type="hidden" id="varietyChartAmountHome"
					value="${sumPieVarietyCharts}"> <input type="hidden"
					id="plantingMonthAreaYears" value="${plantingMonthArea.years}">
				<input type="hidden" id="plantingMonthAreaNums"
					value="${plantingMonthArea.nums}"> <input type="hidden"
					id="plantingMonthAreaYears2" value="${plantingMonthArea2.years}">
				<input type="hidden" id="plantingMonthAreaNums2"
					value="${plantingMonthArea2.nums}"> <input type="hidden"
					id="plantingMonthAreaYears3" value="${plantingMonthArea3.years}">
				<input type="hidden" id="plantingMonthAreaNums3"
					value="${plantingMonthArea3.nums}">

				<c:if test="${role == 'systemAdmin'}">
					<div class="col-lg-3 col-sm-6">
						<div class="card gradient-1">
							<div class="card-body">
								<div class="d-inline-block">
									<h2 class="text-white">${amountOrg}</h2>
									<p class="text-white mb-0">หน่วยงาน</p>
								</div>
								<span class="float-right display-5 opacity-5"><i
									class="icon-home"></i></span>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-sm-6">
						<div class="card gradient-2">
							<div class="card-body">
								<div class="d-inline-block">
									<h2 class="text-white">${amountStaff}</h2>
									<p class="text-white mb-0">เจ้าหน้าที่</p>
								</div>
								<span class="float-right display-5 opacity-5"><i
									class="fa fa-user"></i></span>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-sm-6">
						<div class="card gradient-3">
							<div class="card-body">
								<div class="d-inline-block">
									<h2 class="text-white">${amountFarmer}</h2>
									<p class="text-white mb-0">เกษตรกร</p>
								</div>
								<span class="float-right display-5 opacity-5"><i
									class="fa fa-users"></i></span>
							</div>
						</div>
					</div>
					<div class="col-lg-3 col-sm-6">
						<div class="card  gradient-9">
							<div class="card-body">
								<div class="d-inline-block">
									<h2 class="text-white">${amountField}</h2>
									<p class="text-white mb-0">แปลงเพาะปลูก</p>
								</div>
								<span class="float-right display-5 opacity-5"><i
									class="fa fa-map"></i></span>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${role == 'fieldRegistrar'}">
					<div class="col-12">
						<div class="card gradient-1">
							<div class="card-body">
								<div class="d-inline-block">
									<p class="text-white">${nameOrg}</p>
									<p class="text-white mb-0">หน่วยงาน</p>
								</div>
								<span class="float-right display-5 opacity-5"><i
									class="icon-home"></i></span>
							</div>
						</div>
					</div>
					<div class="col-lg-4 col-sm-6">
						<div class="card gradient-2">
							<div class="card-body">
								<div class="d-inline-block">
									<h2 class="text-white">${amountStaff}</h2>
									<p class="text-white mb-0">เจ้าหน้าที่</p>
								</div>
								<span class="float-right display-5 opacity-5"><i
									class="fa fa-user"></i></span>
							</div>
						</div>
					</div>
					<div class="col-lg-4 col-sm-6">
						<div class="card gradient-3">
							<div class="card-body">
								<div class="d-inline-block">
									<h2 class="text-white">${amountFarmer}</h2>
									<p class="text-white mb-0">เกษตรกร</p>
								</div>
								<span class="float-right display-5 opacity-5"><i
									class="fa fa-users"></i></span>
							</div>
						</div>
					</div>
					<div class="col-lg-4 col-sm-6">
						<div class="card  gradient-9">
							<div class="card-body">
								<div class="d-inline-block">
									<h2 class="text-white">${amountField}</h2>
									<p class="text-white mb-0">แปลงเพาะปลูก</p>
								</div>
								<span class="float-right display-5 opacity-5"><i
									class="fa fa-map"></i></span>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${role == 'farmer'}">
					<div class="col-lg-12 col-xl-12">
						<div class="card  gradient-9">
							<div class="card-body">
								<div class="d-inline-block">
									<h2 class="text-white">${amountField}</h2>
									<p class="text-white mb-0">แปลงเพาะปลูก</p>
								</div>
								<span class="float-right display-5 opacity-5"><i
									class="fa fa-map"></i></span>
							</div>
						</div>
					</div>
				</c:if>
				<c:if test="${role == 'staff'}">
					<div class="col-lg-12 col-xl-12">
						<div class="card  gradient-9">
							<div class="card-body">
								<div class="d-inline-block">
									<h2 class="text-white">${amountField}</h2>
									<p class="text-white mb-0">แปลงเพาะปลูก</p>
								</div>
								<span class="float-right display-5 opacity-5"><i
									class="fa fa-map"></i></span>
							</div>
						</div>
					</div>
				</c:if>
			</div>


			<div class="row">
				<div class="col-12">


					<h3>ข้อมูลทั่วไป</h3>
					<div class="row">

						<!-- Pie Chart -->
						<div class="col-lg-4">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">พื้นที่แปลงเพาะปลูก รวม ${sum} ไร่</h4>
									<canvas id="pieChart1" width="400" height="400"></canvas>
								</div>
							</div>
						</div>
						<!-- Line Chart -->
						<div class="col-lg-8">
							<div class="row">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">พื้นที่การเพาะปลูก (ไร่)</h4>
										<canvas id="lineChart1" width="500" height="100"></canvas>

									</div>
								</div>
							</div>
							<div class="row">
								<div class="card">
									<div class="card-body">
										<h4 class="card-title">พื้นที่การสำรวจ (ไร่)</h4>
										<canvas id="lineChart2" width="500" height="100"></canvas>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<!-- Bar Chart -->
						<div class="col-lg-12">
							<div class="card">
								<div class="card-body">
									<h4 class="card-title">จำนวนการสำรวจ (ครั้ง)</h4>
									<canvas id="barChart" width="500" height="200"></canvas>
								</div>
							</div>
						</div>


					</div>
				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<div class="card-body">
						<span class="pull-right">
							<div class="basic-form">
								<div class="form-inline">
									<div class="form-group mx-sm-3 mb-2">
										<label for="month">เลือกเดือน : </label> <select
											class="form-control" name="month" id="month">
											<option value="0">ทั้งหมด</option>
											<option value="1">มกราคม</option>
											<option value="2">กุมภาพันธ์</option>
											<option value="3">มีนาคม</option>
											<option value="4">เมษายน</option>
											<option value="5">พฤษภาคม</option>
											<option value="6">มิถุนายน</option>
											<option value="7">กรกฏาคม</option>
											<option value="8">สิงหาคม</option>
											<option value="9">กันยายน</option>
											<option value="10">ตุลาคม</option>
											<option value="11">พฤศจิกายน</option>
											<option value="12">ธันวาคม</option>

										</select>
									</div>
									<div class="form-group mx-sm-3 mb-2">
										<label for="month">เลือกปี : </label>
										<div id="selectYear">
											<select class="form-control" name="year" id="year">
												<c:forEach items="${years}" var="year">
													<option value="${year.yearEn}">${year.yearTh}</option>
												</c:forEach>

											</select>
										</div>
										<div id="selectYearAll">
											<select class="form-control" name="yearAll" id="yearAll">
												<option value="0">ทั้งหมด</option>
												<c:forEach items="${years}" var="year">
													<option value="${year.yearEn}">${year.yearTh}</option>
												</c:forEach>

											</select>
										</div>
									</div>



									<div class="col">
										<button id="button" type="submit" class="btn btn-light mb-2">แสดง</button>
									</div>
								</div>
							</div>
						</span>
						<h3>การเพาะปลูก</h3>
						<span>ข้อมูลการเพาะปลูก</span>
						<div id="dataPlanting">
							<div class="row">
								<div class="col-6">
									<div class="card card-widget">
										<div class="card-body gradient-9">
											<div class="media">
												<span class="card-widget__icon"><i class="fa fa-th"
													aria-hidden="true"></i></span>
												<div class="media-body">
													<h5 class="card-widget__subtitle">พื้นที่การเพาะปลูก</h5>
													<h2 class="card-widget__title">${countPlantingYearMonth}&nbsp;ไร่</h2>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="col-6">
									<div class="card card-widget">
										<div class="card-body gradient-7">
											<div class="media">
												<span class="card-widget__icon"><i
													class="fa fa-map-signs" aria-hidden="true"></i></span>
												<div class="media-body">

													<h5 class="card-widget__subtitle">จำนวนการเพาะปลูก</h5>
													<h2 class="card-widget__title">${countPlanting}&nbsp;ครั้ง</h2>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>


							<div class="row">
								<!-- Pie Chart -->
								<div class="col-lg-6">
									<div class="card">
										<div class="card-body">
											<h4 class="card-title">พื้นที่เพาะปลูกจำแนกตามภูมิภาค(ไร่)</h4>
											<c:if test="${checkDataRegionChart == 0}">

												<span style="margin-top: 50px;">ไม่พบข้อมูล</span>

											</c:if>
											<c:if test="${checkDataRegionChart == 1}">
												<canvas id="pieChart2" width="500" height="250"></canvas>

											</c:if>
										</div>
									</div>
								</div>
								<div class="col-lg-6">
									<div class="card">
										<div class="card-body">

											<h4>พื้นที่เพาะปลูกจำแนกตามพันธุ์(ไร่)</h4>
											<c:if test="${checkDataVarietyChart == 0}">

												<span style="margin-top: 50px;">ไม่พบข้อมูล</span>

											</c:if>
											<c:if test="${checkDataVarietyChart == 1}">
												<canvas id="pieChart3" width="500" height="250"></canvas>

											</c:if>
										</div>
									</div>
								</div>
							</div>

							<div class="row">
								<!-- Line Chart -->
								<div class="col-lg-12">
									<div class="card">
										<div class="card-body">
											<h4 class="card-title">พื้นที่เพาะปลูกรายเดือน (ไร่)</h4>
											<canvas id="lineChart3" width="500" height="250"></canvas>
										</div>
									</div>
								</div>
							</div>
						</div>

					</div>

				</div>
			</div>
			<div class="row">
				<div class="col-12">
					<span class="pull-right">
						<div class="basic-form">
							<div class="form-inline">
								<div class="form-group mx-sm-3 mb-2">
									<label for="month">เลือกเดือน : </label> <select
										class="form-control" name="month2" id="month2">
										<option value="0">ทั้งหมด</option>
										<option value="1">มกราคม</option>
										<option value="2">กุมภาพันธ์</option>
										<option value="3">มีนาคม</option>
										<option value="4">เมษายน</option>
										<option value="5">พฤษภาคม</option>
										<option value="6">มิถุนายน</option>
										<option value="7">กรกฏาคม</option>
										<option value="8">สิงหาคม</option>
										<option value="9">กันยายน</option>
										<option value="10">ตุลาคม</option>
										<option value="11">พฤศจิกายน</option>
										<option value="12">ธันวาคม</option>
									</select>

								</div>
								<div class="form-group mx-sm-3 mb-2">
									<label for="month">เลือกปี : </label>
									<div id="selectYear2">
										<select class="form-control" name="year2" id="year2">
											<c:forEach items="${years}" var="year">
												<option value="${year.yearEn}">${year.yearTh}</option>
											</c:forEach>

										</select>
									</div>
									<div id="selectYearAll2">
										<select class="form-control" name="yearAll2" id="yearAll2">
											<option value="0">ทั้งหมด</option>
											<c:forEach items="${years}" var="year">
												<option value="${year.yearEn}">${year.yearTh}</option>
											</c:forEach>
										</select>
									</div>
								</div>

								<div class="col">
									<button id="button2" type="submit" class="btn btn-light mb-2">แสดง</button>
								</div>

							</div>
						</div>
					</span>
					<h3>การสำรวจแปลง</h3>
					<span>ข้อมูลการสำรวจแปลง</span>

					<div id="dataField">
						<div class="row">
							<div class="col-4">
								<div class="row">
									<div class="col-12">
										<div class="card card-widget">
											<div class="card-body gradient-9">
												<div class="media">
													<span class="card-widget__icon"><i
														class="fa fa-leaf" aria-hidden="true"></i></span>
													<div class="media-body">
														<h2 class="card-widget__title">${countDiseaseYearMonth}&nbsp;ครั้ง</h2>
														<h5 class="card-widget__subtitle">โรคมันสำปะหลัง</h5>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row">
									<div class="col-12">
										<div class="table-responsive">
											<table class="table table-xs mb-0">
												<c:if test="${checkDisease > 0}">
													<thead>
														<tr>
															<th></th>
															<th>ระดับความรุนแรง</th>
															<th>% การระบาด</th>
														</tr>
													</thead>
													<tbody>

														<c:forEach items="${avgPercentsDiseases}"
															var="avgPercentsDisease">
															<tr>

																<td>${avgPercentsDisease.nameOfAvgs}</td>
																<td>
																	<h6 class="m-t-10">
																		<span class="pull-right">${avgPercentsDisease.avgPercents}</span>
																	</h6> <br>
																	<div class="progress mb-3" style="height: 7px">
																		<div class="progress-bar bg-danger"
																			style="width: ${avgPercentsDisease.avgPercents*20}%;"
																			role="progressbar">
																			<span class="sr-only">${avgPercentsDisease.avgPercents}
																				Order</span>
																		</div>
																	</div>
																</td>
																<td>
																	<h6 class="m-t-10">
																		<span class="pull-right">${avgPercentsDisease.damageLevels}%</span>
																	</h6> <br>
																	<div class="progress mb-3" style="height: 7px">
																		<div class="progress-bar bg-danger"
																			style="width: ${avgPercentsDisease.damageLevels}%;"
																			role="progressbar">
																			<span class="sr-only">${avgPercentsDisease.damageLevels}
																				Order</span>
																		</div>
																	</div>
																</td>

															</tr>
														</c:forEach>

													</tbody>
												</c:if>
												<c:if test="${checkDisease == 0}">
													<span class="text-center">ไม่พบข้อมูล</span>
												</c:if>
											</table>
										</div>
									</div>
								</div>
							</div>
							<div class="col-4">
								<div class="row">
									<div class="col-12">
										<div class="card card-widget">
											<div class="card-body gradient-3">
												<div class="media">
													<span class="card-widget__icon"><i class="fa fa-bug"
														aria-hidden="true"></i></span>
													<div class="media-body">
														<h2 class="card-widget__title">${countPestYearMonth}&nbsp;ครั้ง</h2>
														<h5 class="card-widget__subtitle">ศัตรูพืช</h5>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row lg-12">
									<div class="col-12">
										<div class="table-responsive">
											<table class="table table-xs mb-0">
												<c:if test="${checkPest > 0}">
													<thead>
														<tr>
															<th></th>
															<th>%ความรุนแรง</th>
															<th>%การระบาด</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${avgPercentsPests}"
															var="avgPercentsPest">
															<tr>
																<td>${avgPercentsPest.nameOfAvgs}</td>
																<td>
																	<h6 class="m-t-10">
																		<span class="pull-right">${avgPercentsPest.avgPercents}%</span>
																	</h6> <br>
																	<div class="progress mb-3" style="height: 7px">
																		<div class="progress-bar bg-warning"
																			style="width: ${avgPercentsPest.avgPercents }%;"
																			role="progressbar">
																			<span class="sr-only">${avgPercentsPest.avgPercents}
																				Order</span>
																		</div>
																	</div>
																</td>
																<td>
																	<h6 class="m-t-10">
																		<span class="pull-right">${avgPercentsPest.damageLevels}%</span>
																	</h6> <br>
																	<div class="progress mb-3" style="height: 7px">
																		<div class="progress-bar bg-warning"
																			style="width: ${avgPercentsPest.damageLevels}%;"
																			role="progressbar">
																			<span class="sr-only">${avgPercentsPest.damageLevels}
																				Order</span>
																		</div>
																	</div>
																</td>
															</tr>

														</c:forEach>
													</tbody>
												</c:if>
												<c:if test="${checkPest == 0}">
													<span class="text-center">ไม่พบข้อมูล</span>
												</c:if>

											</table>
										</div>
									</div>
								</div>
							</div>
							<div class="col-4">
								<div class="row">
									<div class="col-12">
										<div class="card card-widget">
											<div class="card-body gradient-7">
												<div class="media">
													<span class="card-widget__icon"><i class="fa fa-paw"
														aria-hidden="true"></i></span>
													<div class="media-body">
														<h2 class="card-widget__title">${countNaturalEnemyYearMonth}&nbsp;ครั้ง</h2>
														<h5 class="card-widget__subtitle">ศัตรูธรรมชาติ</h5>
													</div>
												</div>
											</div>
										</div>
									</div>
								</div>
								<div class="row lg-12">
									<div class="col-12">
										<div class="table-responsive">
											<table class="table table-xs mb-0">
												<c:if test="${checkNaturalEnemy > 0}">
													<thead>
														<tr>
															<th></th>
															<th>% ความรุนแรง</th>
															<th>% การพบ</th>
														</tr>
													</thead>
													<tbody>
														<c:forEach items="${avgPercentsNaturalEnemys}"
															var="avgPercentsNaturalEnemy">
															<tr>
																<td>${avgPercentsNaturalEnemy.nameOfAvgs}</td>
																<td>
																	<h6 class="m-t-10">
																		<span class="pull-right">${avgPercentsNaturalEnemy.avgPercents}%</span>
																	</h6> <br>
																	<div class="progress mb-3" style="height: 7px">
																		<div class="progress-bar bg-success"
																			style="width: ${avgPercentsNaturalEnemy.avgPercents}%;"
																			role="progressbar">
																			<span class="sr-only">${avgPercentsNaturalEnemy.avgPercents}
																				Order</span>
																		</div>
																	</div>
																</td>
																<td>
																	<h6 class="m-t-10">
																		<span class="pull-right">${avgPercentsNaturalEnemy.damageLevels}%</span>
																	</h6> <br>
																	<div class="progress mb-3" style="height: 7px">
																		<div class="progress-bar bg-success"
																			style="width: ${avgPercentsNaturalEnemy.damageLevels}%;"
																			role="progressbar">
																			<span class="sr-only">${avgPercentsNaturalEnemy.damageLevels}
																				Order</span>
																		</div>
																	</div>
																</td>
															</tr>
														</c:forEach>

													</tbody>
												</c:if>
												<c:if test="${checkNaturalEnemy == 0}">
													<span class="text-center">ไม่พบข้อมูล</span>
												</c:if>
											</table>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>


			</div>
		</div>
		<!-- #/ container -->
	</div>
	<!--**********************************
            Content body end
        ***********************************-->

	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />

	<!-- Custom Script -->
	<script
		src="${pageContext.request.contextPath}/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>

	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/moment/moment.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"></script>

	<!-- DataTablejs -->
	<script
		src="https://kurdis.agri.kps.ku.ac.th/cassava/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="https://kurdis.agri.kps.ku.ac.th/cassava/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="https://kurdis.agri.kps.ku.ac.th/cassava/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>

	<!-- Chart -->
	<script>
		var regions = document.getElementById("regions").value;
		var amountFields = document.getElementById("amountFields").value;

		regions = regions.replace("[", "")
		regions = regions.replace("]", "")

		amountFields = amountFields.replace("[", "")
		amountFields = amountFields.replace("]", "")

		var regionNames = regions.split(",");

		var countFields = amountFields.split(",");

		//pie chart
		var ctx = document.getElementById("pieChart1");
		ctx.height = 500;
		new Chart("pieChart1", {
			type : 'pie',
			data : {
				datasets : [ {
					data : countFields,
					backgroundColor : [ "rgba(117, 113, 249,1)",
							"rgba(117, 113, 249,0.9)",
							"rgba(117, 113, 249,0.7)",
							"rgba(117, 113, 249,0.5)",
							"rgba(117, 113, 249,0.3)",
							"rgba(117, 113, 249,0.2)" ],
					hoverBackgroundColor : [ "rgba(117, 113, 249,0.9)",
							"rgba(117, 113, 249,0.8)",
							"rgba(117, 113, 249,0.6)",
							"rgba(117, 113, 249,0.4)",
							"rgba(117, 113, 249,0.2)",
							"rgba(117, 113, 249,0.1)" ]
				} ],
				labels : regionNames
			},
			options : {
				responsive : true
			}
		});
	</script>

	<script>
		var years = document.getElementById("years").value;
		var amounts = document.getElementById("amounts").value;

		years = years.replace("[", "")
		years = years.replace("]", "")

		amounts = amounts.replace("[", "")
		amounts = amounts.replace("]", "")

		var yearsNames = years.split(",");

		var countPlanting = amounts.split(",");

		var ctx = document.getElementById("lineChart1");
		const xValues = yearsNames;
		const yValues = countPlanting;

		new Chart("lineChart1", {
			type : "line",
			data : {
				labels : xValues,
				datasets : [ {
					label : "พื้นที่การเพาะปลูก",
					fill : true,
					lineTension : 0,
					backgroundColor : "rgba(0,0,255,0.3)",
					borderColor : "rgba(0,0,255,0.1)",
					data : yValues
				} ]
			},
			options : {
				legend : {
					display : false
				},

			}
		});
	</script>

	<script>
		var years2 = document.getElementById("years2").value;
		var amounts2 = document.getElementById("amounts2").value;

		var yearsNaturalEnemy = document.getElementById("yearsNaturalEnemy").value;
		var amountsNaturalEnemy = document
				.getElementById("amountsNaturalEnemy").value;

		var yearsPest = document.getElementById("yearsPest").value;
		var amountsPest = document.getElementById("amountsPest").value;

		years2 = years2.replace("[", "")
		years2 = years2.replace("]", "")

		amounts2 = amounts2.replace("[", "")
		amounts2 = amounts2.replace("]", "")

		yearsNaturalEnemy = yearsNaturalEnemy.replace("[", "")
		yearsNaturalEnemy = yearsNaturalEnemy.replace("]", "")

		amountsNaturalEnemy = amountsNaturalEnemy.replace("[", "")
		amountsNaturalEnemy = amountsNaturalEnemy.replace("]", "")

		yearsPest = yearsPest.replace("[", "")
		yearsPest = yearsPest.replace("]", "")

		amountsPest = amountsPest.replace("[", "")
		amountsPest = amountsPest.replace("]", "")

		var yearsDisease2 = years2.split(",");
		var countDisease2 = amounts2.split(",");

		var yearsNaturalEnemy2 = yearsNaturalEnemy.split(",");
		var amountsNaturalEnemy2 = amountsNaturalEnemy.split(",");

		var yearsPest2 = yearsPest.split(",");
		var amountsPest2 = amountsPest.split(",");

		var ctx = document.getElementById("lineChart2");

		new Chart("lineChart2", {
			type : "line",
			data : {
				labels : yearsDisease2,
				datasets : [ {
					label : "โรคมันสำปะหลัง",
					borderColor : "rgba(117, 113, 249,0.9)",
					borderWidth : "1",
					backgroundColor : "rgba(117, 113, 249,0.3)",
					data : countDisease2
				}, {
					label : "ศัตรูพืช",
					borderColor : "rgba(217, 113, 249, 0.9)",
					borderWidth : "1",
					backgroundColor : "rgba(217, 113, 249, 0.3)",
					data : amountsPest2
				}, {
					label : "ศัตรูรธรรมชาติ",
					borderColor : "rgba(117, 249,113,  0.9)",
					borderWidth : "1",
					backgroundColor : "rgba(117, 249,113,  0.3)",
					data : amountsNaturalEnemy2
				} ]
			},
			options : {
				legend : {
					display : false
				},

			}
		});
	</script>

	<script>
		var plantingRegionChartsHome = document
				.getElementById("plantingRegionChartHome").value;
		var plantingRegionChartAmountsHome = document
				.getElementById("plantingRegionChartAmountHome").value;

		plantingRegionChartsHome = plantingRegionChartsHome.replace("[", "")
		plantingRegionChartsHome = plantingRegionChartsHome.replace("]", "")

		plantingRegionChartAmountsHome = plantingRegionChartAmountsHome
				.replace("[", "")
		plantingRegionChartAmountsHome = plantingRegionChartAmountsHome
				.replace("]", "")

		var plantingRegionChartNamesHome = plantingRegionChartsHome.split(",");

		var plantingRegionChartcountsHome = plantingRegionChartAmountsHome
				.split(",");

		//pie chart
		var ctx = document.getElementById("pieChart2");
		ctx.height = 300;
		new Chart("pieChart2", {
			type : 'pie',
			data : {
				datasets : [ {
					data : plantingRegionChartcountsHome,
					backgroundColor : [ "rgba(117, 113, 249,1)",
							"rgba(117, 113, 249,0.9)",
							"rgba(117, 113, 249,0.7)",
							"rgba(117, 113, 249,0.5)",
							"rgba(117, 113, 249,0.3)",
							"rgba(117, 113, 249,0.2)" ],
					hoverBackgroundColor : [ "rgba(117, 113, 249,0.9)",
							"rgba(117, 113, 249,0.8)",
							"rgba(117, 113, 249,0.6)",
							"rgba(117, 113, 249,0.4)",
							"rgba(117, 113, 249,0.2)",
							"rgba(117, 113, 249,0.1)" ]
				} ],
				labels : plantingRegionChartNamesHome
			},
			options : {
				responsive : true
			}
		});
	</script>

	<script>
		//pie chart

		var varietyChartsHome = document.getElementById("varietyChartHome").value;
		var varietyChartAmountsHome = document
				.getElementById("varietyChartAmountHome").value;

		varietyChartsHome = varietyChartsHome.replace("[", "")
		varietyChartsHome = varietyChartsHome.replace("]", "")

		varietyChartAmountsHome = varietyChartAmountsHome.replace("[", "")
		varietyChartAmountsHome = varietyChartAmountsHome.replace("]", "")

		var varietyChartNamesHome = varietyChartsHome.split(",");

		var varietyChartcountsHome = varietyChartAmountsHome.split(",");

		var ctx = document.getElementById("pieChart3");
		ctx.height = 300;
		new Chart("pieChart3", {
			type : 'pie',
			data : {
				datasets : [ {
					data : varietyChartcountsHome,
					backgroundColor : [ "rgba(117, 113, 249,1)",
							"rgba(117, 113, 249,0.9)",
							"rgba(117, 113, 249,0.7)",
							"rgba(117, 113, 249,0.5)",
							"rgba(117, 113, 249,0.3)",
							"rgba(117, 113, 249,0.2)" ],
					hoverBackgroundColor : [ "rgba(117, 113, 249,0.9)",
							"rgba(117, 113, 249,0.8)",
							"rgba(117, 113, 249,0.6)",
							"rgba(117, 113, 249,0.4)",
							"rgba(117, 113, 249,0.2)",
							"rgba(117, 113, 249,0.1)" ]
				} ],
				labels : varietyChartNamesHome
			},
			options : {
				responsive : true
			}
		});
	</script>

	<script>
		//line chart

		var plantingMonthAreaYears = document
				.getElementById("plantingMonthAreaYears").value;
		var plantingMonthAreaNums = document
				.getElementById("plantingMonthAreaNums").value;
		var plantingMonthAreaYears2 = document
				.getElementById("plantingMonthAreaYears2").value;
		var plantingMonthAreaNums2 = document
				.getElementById("plantingMonthAreaNums2").value;
		var plantingMonthAreaYears3 = document
				.getElementById("plantingMonthAreaYears3").value;
		var plantingMonthAreaNums3 = document
				.getElementById("plantingMonthAreaNums3").value;

		plantingMonthAreaYears = plantingMonthAreaYears.replace("[", "")
		plantingMonthAreaYears = plantingMonthAreaYears.replace("]", "")
		plantingMonthAreaNums = plantingMonthAreaNums.replace("[", "")
		plantingMonthAreaNums = plantingMonthAreaNums.replace("]", "")

		plantingMonthAreaYears2 = plantingMonthAreaYears2.replace("[", "")
		plantingMonthAreaYears2 = plantingMonthAreaYears2.replace("]", "")
		plantingMonthAreaNums2 = plantingMonthAreaNums2.replace("[", "")
		plantingMonthAreaNums2 = plantingMonthAreaNums2.replace("]", "")

		plantingMonthAreaYears3 = plantingMonthAreaYears3.replace("[", "")
		plantingMonthAreaYears3 = plantingMonthAreaYears3.replace("]", "")
		plantingMonthAreaNums3 = plantingMonthAreaNums3.replace("[", "")
		plantingMonthAreaNums3 = plantingMonthAreaNums3.replace("]", "")

		var plantingMonthAreaYear1 = plantingMonthAreaYears.split(",");
		var plantingMonthAreaNum1 = plantingMonthAreaNums.split(",");

		var plantingMonthAreaYear2 = plantingMonthAreaYears2.split(",");
		var plantingMonthAreaNum2 = plantingMonthAreaNums2.split(",");

		var plantingMonthAreaYear3 = plantingMonthAreaYears3.split(",");
		var plantingMonthAreaNum3 = plantingMonthAreaNums3.split(",");

		var ctx = document.getElementById("lineChart3");
		ctx.height = 120;
		//line chart
		var myChart = new Chart(ctx, {
			type : 'line',
			data : {
				labels : [ "ม.ค.", "ก.พ.", "มี.ค.", "เม.ย.", "พ.ค.", "มิ.ย.",
						"ก.ค.", "ส.ค.", "ก.ย.", "ต.ค.", "พ.ย.", "ธ.ค." ],
				datasets : [ {
					label : plantingMonthAreaYear3,
					borderColor : "rgba(144,	104,	190,.9)",
					borderWidth : "1",
					backgroundColor : "rgba(144,	104,	190,.3)",
					data : plantingMonthAreaNum3
				}, {
					label : plantingMonthAreaYear2,
					borderColor : "rgba(217, 113, 249, 0.9)",
					borderWidth : "1",
					backgroundColor : "rgba(217, 113, 249, 0.3)",
					data : plantingMonthAreaNum2
				}, {
					label : plantingMonthAreaYear1,
					borderColor : "rgba(117, 249,113,  0.9)",
					borderWidth : "1",
					backgroundColor : "rgba(117, 249,113,  0.3)",
					data : plantingMonthAreaNum1
				} ]
			},
			options : {
				legend : {
					display : false
				},
			}
		});
	</script>

	<script>
		//bar chart
		//Dis
		var years = document.getElementById("yearsDiseaseC5").value;
		var amounts = document.getElementById("amountsDiseaseC5").value;

		years = years.replace("[", "")
		years = years.replace("]", "")

		amounts = amounts.replace("[", "")
		amounts = amounts.replace("]", "")

		var yearsNames = years.split(",");

		var countPlanting = amounts.split(",");

		//NaturalEnemy
		var yearsNaturalEnemy = document.getElementById("yearsNaturalEnemyC5").value;
		var amountsNaturalEnemy = document
				.getElementById("amountsNaturalEnemyC5").value;
		console.log(yearsNaturalEnemy)
		console.log(amountsNaturalEnemy)

		yearsNaturalEnemy = yearsNaturalEnemy.replace("[", "")
		yearsNaturalEnemy = yearsNaturalEnemy.replace("]", "")

		amountsNaturalEnemy = amountsNaturalEnemy.replace("[", "")
		amountsNaturalEnemy = amountsNaturalEnemy.replace("]", "")

		var yearsNaturalEnemy = yearsNaturalEnemy.split(",");

		var countNaturalEnemy = amountsNaturalEnemy.split(",");

		//Pest
		var yearsPest = document.getElementById("yearsPestC5").value;
		var amountsPest = document.getElementById("amountsPestC5").value;

		yearsPest = yearsPest.replace("[", "")
		yearsPest = yearsPest.replace("]", "")

		amountsPest = amountsPest.replace("[", "")
		amountsPest = amountsPest.replace("]", "")

		var yearsPest = yearsPest.split(",");

		var countPest = amountsPest.split(",");

		var ctx = document.getElementById("barChart");
		ctx.height = 150;
		var myChart = new Chart(ctx, {
			type : 'bar',
			data : {
				labels : yearsNames,
				datasets : [ {
					label : "โรคมันสำปะหลัง",
					data : countPlanting,
					borderColor : "rgba(117, 113, 249, 0.9)",
					borderWidth : "0",
					backgroundColor : "rgba(117, 113, 249, 0.5)"
				}, {
					label : "ศัตรูพืช",
					data : countPest,
					borderColor : "rgba(217, 113, 249, 0.9)",
					borderWidth : "0",
					backgroundColor : "rgba(217, 113, 249, 0.5)"
				}, {
					label : "ศัตรูธรรมชาติ",
					data : countNaturalEnemy,
					borderColor : "rgba(117, 249,113,  0.9)",
					borderWidth : "0",
					backgroundColor : "rgba(117, 249,113,  0.5)"
				}

				]
			},
			options : {
				legend : {
					display : false
				},
				scales : {

					xAxes : [ {
						// Change here
						barPercentage : 0.2
					} ]
				}
			}
		});
	</script>

	<script>
		$("#button").click(
				function() {
					var year = document.getElementById("year").value;
					var month = document.getElementById("month").value;
					var yearAll = document.getElementById("yearAll").value;
					$.ajax({
						url : "${pageContext.request.contextPath}/planting/"
								+ month + "/" + year + "/" + yearAll,
						type : 'GET',
						success : function(response) {
							//alert(response);
							$('#dataPlanting').empty();
							$('#dataPlanting').html(response);

						},

					});
				});
	</script>

	<script>
		$("#button2").click(

				function() {
					var year2 = document.getElementById("year2").value;
					var month2 = document.getElementById("month2").value;
					var yearAll2 = document.getElementById("yearAll2").value;
					//alert(year2 + " - " + month2 + " - " + yearAll2)
					$.ajax({
						url : "${pageContext.request.contextPath}/fieldchart/"
								+ month2 + "/" + year2 + "/" + yearAll2,
						type : 'GET',
						success : function(response) {
							//alert(response);
							$('#dataField').empty();
							$('#dataField').html(response);

						},

					});
				});
	</script>


	<script>
		$(document)
				.ready(
						function() {

							$("#month")
									.change(
											function(e) {
												hideAll();
												$(e.target.options)
														.removeClass();
												var $selectedOption = $(e.target.options[e.target.options.selectedIndex]);
												$selectedOption
														.addClass('selected');

												if ($selectedOption.val() == 0) {

													console.log($selectedOption
															.val());
													$('#selectYearAll').show();
												} else {
													console.log($selectedOption
															.val());
													$('#selectYear').show();
												}

											});
						});

		function hideAll() {
			$("#selectYear").hide();
			$("#selectYearAll").hide();
		}
	</script>

	<script>
		$(document)
				.ready(
						function() {

							$("#month2")
									.change(
											function(e) {
												hideAll2();
												$(e.target.options)
														.removeClass();
												var $selectedOption = $(e.target.options[e.target.options.selectedIndex]);
												$selectedOption
														.addClass('selected');

												if ($selectedOption.val() == 0) {

													console.log($selectedOption
															.val());
													$('#selectYearAll2').show();
												} else {
													console.log($selectedOption
															.val());
													$('#selectYear2').show();
												}

											});
						});

		function hideAll2() {
			$("#selectYear2").hide();
			$("#selectYearAll2").hide();

		}
	</script>



</body>
</html>

