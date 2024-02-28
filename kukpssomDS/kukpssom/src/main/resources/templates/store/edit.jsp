<!DOCTYPE html>
<html lang="th">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>KU KPS SOM</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="../img/favicon.png" rel="icon">
<link href="../img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,600;1,700&family=Amatic+SC:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&family=Inter:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="../vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="../vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="../vendor/aos/aos.css" rel="stylesheet">
<link href="../vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
<link href="../vendor/swiper/swiper-bundle.min.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://cdn.datatables.net/1.13.6/css/jquery.dataTables.css" />
<link
	href="https://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css"
	rel="stylesheet">

<!-- Template Main CSS File -->
<link href="../css/main.css" rel="stylesheet">

<!-- =======================================================
  * Template Name: Yummy
  * Updated: Sep 18 2023 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/yummy-bootstrap-restaurant-website-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->

<style>
.results tr[visible='false'], .no-result {
	display: none;
}

.results tr[visible='true'] {
	display: table-row;
}

.counter {
	padding: 8px;
	color: #ccc;
}
</style>

</head>

<body>

	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">
		<div
			class="container d-flex align-items-right justify-content-between">

			<a href="index.html"
				class="logo d-flex align-items-center me-auto me-lg-0"> <!-- Uncomment the line below if you also wish to use an image logo -->
				<!-- <img src="assets/img/logo.png" alt=""> -->
				<h1>KU KPS SOM.</h1>
			</a>

			<nav id="navbar" class="navbar">
				<ul>
					<li><a href="./orderlist">ออเดอร์<span th:text="${q}"
							style="text-align: center; border-radius: 100%; background-color: rgb(255, 0, 0); width: 20px; color: rgb(255, 255, 255); font-size: small;">1</span></a></li>
					<li><a href="./home">เมนูทั้งหมด</a></li>
					<!--  <li><a href="./sell">ยอดขาย</a></li>-->
					<li><a href="./infomation">ข้อมูลร้าน</a></li>

				</ul>
			</nav>
			<!-- .navbar -->

			<a class="btn-book-a-table" href="../logout"
				style="background-color: rgba(255, 78, 2, 0.8);">Logout</a> <i
				class="mobile-nav-toggle mobile-nav-show bi bi-list"></i> <i
				class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

		</div>
	</header>
	<!-- End Header -->

	<br>
	<br>

	<main id="menu">
		<!-- ======= About Section ======= -->
		<section id="menu" class="contact">
			<div class="container" data-aos="fade-up">

				<div class="section-header">
					<h2 th:text="${store.name}">ไม่สั่งไม่ทำ</h2>
					<p>Store</p>
				</div>

				<div class="container-fluid">
					<div class="row justify-content-center">
						<div class="col-lg-12">
							<div class="card">
								<div class="card-body">
									<div class="form-validation">
										<form class="form-valide" method="POST" modelAttribute="store"
											enctype="multipart/form-data" th:action="@{/store/update}"
											novalidate="novalidate">

											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="val-email">ชื่อร้าน
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="text" class="form-control" id="val-email"
														name="name" th:value="${store.name}">
												</div>
											</div>
											<br>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="val-email">เวลาเปิด
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="time" class="form-control" id="val-email"
														name="timeOpen" th:value="${store.timeOpen}">
												</div>
											</div>
											<br>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="val-password">เวลาปิด
													<span class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<input type="time" class="form-control" id="val-password"
														name="timeClose" th:value="${store.timeClose}">
												</div>
											</div>
											<br>


											<div class="form-group row"
												style="margin-left: 50%; margin-top: 10px;">
												<div class="col-lg-8 ml-auto">
													<button type="submit" class="btn btn-warning">บันทึก</button>
												</div>
											</div>
										</form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>


			</div>

			</div>
		</section>
		<!-- End About Section -->


	</main>
	<!-- End #main -->


	<a href="#"
		class="scroll-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>


	<!-- Vendor JS Files -->
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="../vendor/aos/aos.js"></script>
	<script src="../vendor/glightbox/js/glightbox.min.js"></script>
	<script src="../vendor/purecounter/purecounter_vanilla.js"></script>
	<script src="../vendor/swiper/swiper-bundle.min.js"></script>
	<script src="../vendor/php-email-form/validate.js"></script>
	<script src="https://cdn.datatables.net/1.13.6/js/jquery.dataTables.js"></script>
	<script
		src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

	<!-- Template Main JS File -->
	<script src="../js/main.js"></script>

	<script>
		$(function() {
			$(document)
					.ready(
							function() {
								$(".search")
										.keyup(
												function() {
													var searchTerm = $(
															".search").val();
													var listItem = $(
															'.results tbody')
															.children('tr');
													var searchSplit = searchTerm
															.replace(/ /g,
																	"'):containsi('")

													$
															.extend(
																	$.expr[':'],
																	{
																		'containsi' : function(
																				elem,
																				i,
																				match,
																				array) {
																			return (elem.textContent
																					|| elem.innerText || '')
																					.toLowerCase()
																					.indexOf(
																							(match[3] || "")
																									.toLowerCase()) >= 0;
																		}
																	});

													$(".results tbody tr")
															.not(
																	":containsi('"
																			+ searchSplit
																			+ "')")
															.each(
																	function(e) {
																		$(this)
																				.attr(
																						'visible',
																						'false');
																	});

													$(
															".results tbody tr:containsi('"
																	+ searchSplit
																	+ "')")
															.each(
																	function(e) {
																		$(this)
																				.attr(
																						'visible',
																						'true');
																	});

													var jobCount = $('.results tbody tr[visible="true"]').length;
													$('.counter')
															.text(
																	jobCount
																			+ ' รายการ');

													if (jobCount == '0') {
														$('.no-result').show();
													} else {
														$('.no-result').hide();
													}
												});
							});
		});
	</script>

</body>

</html>