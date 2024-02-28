<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
	
	<jsp:include page="../layouts/mainLayoutHead.jsp" />
	<!-- Custom Stylesheet -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">

	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/image-uploader.min.css">
	<link
		href="https://fonts.googleapis.com/css?family=Lato:300,700|Montserrat:300,400,500,600,700|Source+Code+Pro&display=swap"
		rel="stylesheet">

	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
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
							<h4 class="card-title">ตรวจสอบภาพ</h4>
							<form action="${pageContext.request.contextPath}/approve/index/find">
								<div class="form-group row ">
									<label class="col-lg-1 " for="symptom">
									</label>
									<div class="col-lg-8 margin-right">
									</div>
									<div class="col-lg-2 margin-right">
										<input type="search" class="form-control" id="name" name="name"
											aria-label="Search" placeholder="ค้นหา">
									</div>
									<input type="submit" value="ค้นหา" class="btn mb-1 btn-primary ">
								</div>
							</form>

							<div class="row">
								<c:forEach items="${diseases}" var="disease" varStatus="loop">
									<div class="col-3">
										<div class="card">
											<div class="card-body">
												<div class="text-center">
													<img alt="" class=" thumbnail rounded-circle mt-4" width="150"
														height="150"
														src="data:image/jpeg;base64,${images[loop.index].image }">
													<h4 class="card-widget__title text-dark mt-3">
														${disease.targetofsurvey.name}</h4>
													<p class="text-muted">${approve[loop.index]}/${all[loop.index]} ภาพ</p>
													<div class="mt-4">
														<c:choose>
															<c:when test="${all[loop.index]!=0}">
																<h6>
																	<span class="pull-right">${percent[loop.index]} %</span>
																</h6>																
																<br>
																<div class="progress mb-3" style="height: 7px">
																	<div class="progress-bar gradient-1"
																		style="width: ${percent[loop.index]}%;"
																		role="progressbar"><span class="sr-only">%
																			Complete</span>
																	</div>
																</div>
																
																<p class="text-muted">
																	<h3>																		
																		<a href="${pageContext.request.contextPath}/approve/detail/${disease.diseaseId}">
																			<i class="fa fa-edit" aria-hidden="true"></i>
																		</a>
																	</h3>
																</p>																																
															</c:when>
															<c:otherwise>
																<h6>
																	<span class="pull-center">ไมมีภาพสำหรับตรวจสอบ</span>
																</h6>																		
																<br><br><br>
															</c:otherwise>
														</c:choose>
													</div>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
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

	<!-- Custom Script -->
	<script src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js">
	</script>
	<script src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js">
	</script>
</body>

</html>