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

<style type="text/css">
.container {
	padding: 10%;
	text-align: center;
}

#custom-search-input {
	margin: 0;
	margin-top: 10px;
	padding: 0;
}

#custom-search-input .search-query {
	width: 80%;
	padding-right: 3px;
	padding-left: 15px;
	/* IE7-8 doesn't have border-radius, so don't indent the padding */
	margin-bottom: 0;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 0;
}

#custom-search-input button {
	border: 0;
	background: none;
	/** belows styles are working good */
	padding: 2px 5px;
	margin-top: 2px;
	position: absolute;
	right: 0;
	/* IE7-8 doesn't have border-radius, so don't indent the padding */
	margin-bottom: 0;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;
	border-radius: 3px;
	color: #D9230F;
	cursor: unset;
	z-index: 2;
}

.search-query:focus {
	z-index: 0;
}

.text-9xl {
	font-size: 14rem;
}

@media ( max-width : 645px) {
	.text-9xl {
		font-size: 5.75rem;
	}
	.text-6xl {
		font-size: 1.75rem;
	}
	.text-2xl {
		font-size: 1rem;
		line-height: 1.2rem;
	}
	.py-8 {
		padding-top: 1rem;
		padding-bottom: 1rem;
	}
	.px-6 {
		padding-left: 1.2rem;
		padding-right: 1.2rem;
	}
	.mr-6 {
		margin-right: 0.5rem;
	}
	.px-12 {
		padding-left: 1rem;
		padding-right: 1rem;
	}
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
			<div class="row justify-content">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">เกษตรกรในหน่วยงาน > เพิ่มเกษตรกร</h4>
							<div class="text-right">
								<a
									href="${pageContext.request.contextPath}/farmerorganization/index"
									class="btn btn-light" role="button">กลับ</a>
							</div>
							<h2>ค้นหาเกษตรกร</h2>
							<form class="form-valide"
								action="${pageContext.request.contextPath}/farmerorganization/find"
								method="post" id="farmerData" modelAttribute="farmer"
								enctype="multipart/form-data">
								<div class="col-12">
									<div id="search-input"
										style="width: 800px; margin-top: 60px; margin-bottom: 100px; margin-left: auto; margin-right: auto;">
										<div class="input-group">
											<input type="text" class="search-query form-control"
												id="user.username" placeholder="โปรดระบุอีเมล์ผู้ใช้"
												name="user.username" /> <input type="submit"
												class="btn btn-primary" role="button" id="button"
												value="ค้นหา">
										</div>
									</div>
								</div>
							</form>
							<div class="text-center">
								<h3 style="margin-bottom: 50px; align-self: center;">${message}</h3>
							</div>
							<c:if test="${check > 0}">
								<div class="text-center">
									<h4>ข้อมูลผู้ใช้</h4>
								</div>
								<div class="row justify-content-center">
									<div class="col-auto">
										<table class="table table-responsive">
											<tr>
												<td>ชื่อ-สกุล</td>
												<td>${farmer.user.title}${farmer.user.firstName}
													${farmer.user.lastName}</td>
											</tr>
											<tr>
												<td>บัญชีผู้ใช้</td>
												<td>${farmer.user.username}</td>
											</tr>
											<tr>
												<td>ที่อยู่</td>
												<td>${farmer.address}</td>
											</tr>
											<tr>
												<td></td>
												<td>ต.${farmer.subdistrict.name}
													อ.${farmer.subdistrict.district.name}
													จ.${farmer.subdistrict.district.province.name}</td>
											</tr>
											<tr>
												<td>สถานะบัญชี</td>
												<td>${status}</td>
											</tr>
										</table>
									</div>
								</div>


								<div class="text-center">
									<c:if test="${check == 2}">
										<div class="text-center">
											<a
												href="${pageContext.request.contextPath}/farmerorganization/index"
												class="btn btn-light" role="button">กลับ</a> <a
												href="${pageContext.request.contextPath}/farmer/farmerapprove"
												class="btn btn-primary" role="button">อนุมัติเกษตรกร</a>
										</div>
									</c:if>
									<c:if test="${check == 3}">
										<div class="col-lg-12 ml-auto">
											<!--<a
												href="${pageContext.request.contextPath}/farmerorganization/index"
												class="btn btn-light" role="button">กลับ</a> 
												
											<a
												href="${pageContext.request.contextPath}/farmerorganization/add/${farmer.farmerId}"
												class="btn btn-success" role="button">เพิ่มเกษตรกร</a> -->
											<form action="${pageContext.request.contextPath}/farmerorganization/approve/${farmer.farmerId}"
												method="post">
												<a
												href="${pageContext.request.contextPath}/farmerorganization/index"
												class="btn btn-light" role="button">กลับ</a> 
												<button class="btn btn-success" role="button">เพิ่มเกษตรกร</button>
												</form>
										</div>
									</c:if>
									<c:if test="${check == 4}">
										<div class="col-lg-12 ml-auto">
											<!--<a
												href="${pageContext.request.contextPath}/farmerorganization/index"
												class="btn btn-light" role="button">กลับ</a>
												
											<a
												href="${pageContext.request.contextPath}/farmerorganization/approve/${farmer.farmerId}"
													class="btn btn-success">อนุมัติการใช้งานและเพิ่มเกษตรกร</a> -->
											<form action="${pageContext.request.contextPath}/farmerorganization/approve/${farmer.farmerId}"
												method="post">
												<a
												href="${pageContext.request.contextPath}/farmerorganization/index"
												class="btn btn-light" role="button">กลับ</a>
												<button class="btn btn-success">อนุมัติการใช้งานและเพิ่มเกษตรกร</button>
											</form>
										</div>
									</c:if>
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

	<!-- Custom Script -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
</body>
</html>