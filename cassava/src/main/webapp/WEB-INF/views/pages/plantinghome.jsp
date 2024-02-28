<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<input type="hidden" id="plantingRegionChart" value="${plantingRegionPieRegion2}">
<input type="hidden" id="plantingRegionChartAmount" value="${sumPieRegion2}">

<input type="hidden" id="varietyChart" value="${pieNameVarietyCharts}">
<input type="hidden" id="varietyChartAmount" value="${sumPieVarietyCharts}">
	

<input type="hidden" id="plantingMonthAreaYearshome" value="${plantingMonthArea.years}">
<input type="hidden" id="plantingMonthAreaNumshome" value="${plantingMonthArea.nums}">
				
<input type="hidden" id="plantingMonthAreaYearshome2" value="${plantingMonthArea2.years}">
<input type="hidden" id="plantingMonthAreaNumshome2" value="${plantingMonthArea2.nums}">
				
<input type="hidden" id="plantingMonthAreaYearshome3" value="${plantingMonthArea3.years}">
<input type="hidden" id="plantingMonthAreaNumshome3" value="${plantingMonthArea3.nums}">

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
						<span class="card-widget__icon"><i class="fa fa-map-signs"
							aria-hidden="true"></i></span>
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
					<h4 class="card-title">พื้นที่เพาะปลูกจำแนกตามภูมิภาค (ไร่)</h4>
					<c:if test="${checkDataRegionChart == 0}">

						<span >ไม่พบข้อมูล</span>

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

					<h4 class="card-title">พื้นที่เพาะปลูกจำแนกตามพันธุ์ (ไร่)</h4>
					<c:if test="${checkDataVarietyChart == 0}">
						<span >ไม่พบข้อมูล</span>

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

<!-- Chart -->
<script>
	var plantingRegionCharts = document.getElementById("plantingRegionChart").value;
	var plantingRegionChartAmounts = document
			.getElementById("plantingRegionChartAmount").value;
	console.log(plantingRegionCharts)
	console.log(plantingRegionChartAmounts)

	plantingRegionCharts = plantingRegionCharts.replace("[", "")
	plantingRegionCharts = plantingRegionCharts.replace("]", "")

	plantingRegionChartAmounts = plantingRegionChartAmounts.replace("[", "")
	plantingRegionChartAmounts = plantingRegionChartAmounts.replace("]", "")

	var plantingRegionChartNames = plantingRegionCharts.split(",");

	var plantingRegionChartcounts = plantingRegionChartAmounts.split(",");

	//pie chart
	var ctx = document.getElementById("pieChart2");
	ctx.height = 300;
	new Chart("pieChart2", {
		type : 'pie',
		data : {
			datasets : [ {
				data : plantingRegionChartcounts,
				backgroundColor : [ "rgba(117, 113, 249,1)",
						"rgba(117, 113, 249,0.9)", "rgba(117, 113, 249,0.7)",
						"rgba(117, 113, 249,0.5)", "rgba(117, 113, 249,0.3)",
						"rgba(117, 113, 249,0.2)" ],
				hoverBackgroundColor : [ "rgba(117, 113, 249,0.9)",
						"rgba(117, 113, 249,0.8)", "rgba(117, 113, 249,0.6)",
						"rgba(117, 113, 249,0.4)", "rgba(117, 113, 249,0.2)",
						"rgba(117, 113, 249,0.1)" ]
			} ],
			labels : plantingRegionChartNames
		},
		options : {
			responsive : true
		}
	});
</script>

<script>
	//pie chart

	var varietyCharts = document.getElementById("varietyChart").value;
	var varietyChartAmounts = document.getElementById("varietyChartAmount").value;
	console.log(varietyCharts)
	console.log(varietyChartAmounts)

	varietyCharts = varietyCharts.replace("[", "")
	varietyCharts = varietyCharts.replace("]", "")

	varietyChartAmounts = varietyChartAmounts.replace("[", "")
	varietyChartAmounts = varietyChartAmounts.replace("]", "")

	var varietyChartNames = varietyCharts.split(",");

	var varietyChartcounts = varietyChartAmounts.split(",");

	var ctx = document.getElementById("pieChart3");
	ctx.height = 300;
	new Chart("pieChart3", {
		type : 'pie',
		data : {
			datasets : [ {
				data : varietyChartcounts,
				backgroundColor : [ "rgba(117, 113, 249,1)",
						"rgba(117, 113, 249,0.9)", "rgba(117, 113, 249,0.7)",
						"rgba(117, 113, 249,0.5)", "rgba(117, 113, 249,0.3)",
						"rgba(117, 113, 249,0.2)" ],
				hoverBackgroundColor : [ "rgba(117, 113, 249,0.9)",
						"rgba(117, 113, 249,0.8)", "rgba(117, 113, 249,0.6)",
						"rgba(117, 113, 249,0.4)", "rgba(117, 113, 249,0.2)",
						"rgba(117, 113, 249,0.1)" ]
			} ],
			labels : varietyChartNames
		},
		options : {
			responsive : true
		}
	});
</script>

<script>
		//line chart

		var plantingMonthAreaYearshome = document.getElementById("plantingMonthAreaYearshome").value;
		var plantingMonthAreaNumshome = document.getElementById("plantingMonthAreaNumshome").value;
		var plantingMonthAreaYearshome2 = document.getElementById("plantingMonthAreaYearshome2").value;
		var plantingMonthAreaNumshome2 = document.getElementById("plantingMonthAreaNumshome2").value;
		var plantingMonthAreaYearshome3 = document.getElementById("plantingMonthAreaYearshome3").value;
		var plantingMonthAreaNumshome3 = document.getElementById("plantingMonthAreaNumshome3").value;
		
		
		plantingMonthAreaYearshome = plantingMonthAreaYearshome.replace("[", "")
		plantingMonthAreaYearshome = plantingMonthAreaYearshome.replace("]", "")
		plantingMonthAreaNumshome = plantingMonthAreaNumshome.replace("[", "")
		plantingMonthAreaNumshome = plantingMonthAreaNumshome.replace("]", "")
		
		plantingMonthAreaYearshome2 = plantingMonthAreaYearshome2.replace("[", "")
		plantingMonthAreaYearshome2 = plantingMonthAreaYearshome2.replace("]", "")
		plantingMonthAreaNumshome2 = plantingMonthAreaNumshome2.replace("[", "")
		plantingMonthAreaNumshome2 = plantingMonthAreaNumshome2.replace("]", "")
		
		plantingMonthAreaYearshome3 = plantingMonthAreaYearshome3.replace("[", "")
		plantingMonthAreaYearshome3 = plantingMonthAreaYearshome3.replace("]", "")
		plantingMonthAreaNumshome3 = plantingMonthAreaNumshome3.replace("[", "")
		plantingMonthAreaNumshome3 = plantingMonthAreaNumshome3.replace("]", "")

		
		var plantingMonthAreaYearhome1 = plantingMonthAreaYearshome.split(",");
		var plantingMonthAreaNumhome1 = plantingMonthAreaNumshome.split(",");
		
		var plantingMonthAreaYearhome2 = plantingMonthAreaYearshome2.split(",");
		var plantingMonthAreaNumhome2 = plantingMonthAreaNumshome2.split(",");
		
		var plantingMonthAreaYearhome3 = plantingMonthAreaYearshome3.split(",");
		var plantingMonthAreaNumhome3 = plantingMonthAreaNumshome3.split(",");
		
		var ctx = document.getElementById("lineChart3");
		ctx.height = 120;
		//line chart
		var myChart = new Chart(ctx, {
			type : 'line',
			data : {
				labels : [ "ม.ค.", "ก.พ.", "มี.ค.", "เม.ย.", "พ.ค.", "มิ.ย.",
						"ก.ค.", "ส.ค.", "ก.ย.", "ต.ค.", "พ.ย.", "ธ.ค." ],
				datasets : [
						{
							label :plantingMonthAreaYearhome3,
							borderColor : "rgba(144,	104,	190,.9)",
							borderWidth : "1",
							backgroundColor : "rgba(144,	104,	190,.3)",
							data : plantingMonthAreaNumhome3
						},
						{
							label : plantingMonthAreaYearhome2,
							borderColor : "rgba(217, 113, 249, 0.9)",
							borderWidth : "1",
							backgroundColor : "rgba(217, 113, 249, 0.3)",
							data : plantingMonthAreaNumhome2
						},
						{
							label : plantingMonthAreaYearhome1,
							borderColor : "rgba(117, 249,113,  0.9)",
							borderWidth : "1",
							backgroundColor : "rgba(117, 249,113,  0.3)",
							data : plantingMonthAreaNumhome1
						} ]
			},
			options : {
				responsive : true,
				
			}
		});
	</script>


