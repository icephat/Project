<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div id="dataSurvey">
	<div class="row">
		<div class="col-4">
			<div class="row">
				<div class="col-12">
					<div class="card card-widget">
						<div class="card-body gradient-9">
							<div class="media">
								<span class="card-widget__icon"><i class="fa fa-leaf"
									aria-hidden="true"></i></span>
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
			<div class="row">
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
									<c:forEach items="${avgPercentsPests}" var="avgPercentsPest">
										<tr>
											<td>${avgPercentsPest.nameOfAvgs}</td>
											<td>
												<h6 class="m-t-10">
													<span class="pull-right">${avgPercentsPest.avgPercents}%</span>
												</h6> <br>
												<div class="progress mb-3" style="height: 7px">
													<div class="progress-bar bg-warning"
														style="width: ${avgPercentsPest.avgPercents}%;"
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
														<span class="sr-only">${avgPercentsNaturalEnemy.avgPercents}%
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
														<span class="sr-only">${avgPercentsNaturalEnemy.damageLevels}%
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

<!-- Chart -->


