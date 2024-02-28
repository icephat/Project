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
					<li><a href="./home">ร้านค้าทั้งหมด</a></li>


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


				<div class="row py-5">
					<div class="col-lg-10 mx-auto">
						<div class="card rounded shadow border-0">
							<div class="card-header bg-white">
								<a href="./addStore" type="button"
									class="btn btn-primary float-end">เพิ่มร้านค้า</a>
							</div>
							<div class="card-body p-5 bg-white rounded">
								<div class="table-responsive">
									<div class="form-group pull-right">
										<input type="text" class="search form-control"
											placeholder="ค้นหาร้านค้า">
									</div>
									<span class="counter pull-right"></span>
									<table class="table table-hover table-bordered results">
										<thead>
											<tr>
												<th class="col-md-3 col-3">เจ้าของร้าน</th>
												<th class="col-md-3 col-3">ชื่อร้านค้า</th>

												<th class="col-md-3 col-3">ประเภทอาหาร</th>
												<th class="col-md-3 col-3 text-center">เพิ่มเติม</th>
											</tr>
											<tr class="warning no-result">
												<td colspan="4"><i class="fa fa-warning"></i> No result</td>
											</tr>
										</thead>
										<tbody>
											<tr th:each="store : ${stores}">
												<td th:text="${store.user.username}" />
												<td th:text="${store.name}" />
												<td th:text="${store.type}" />
												<td><a th:href="@{./delete(storeId=${store.storeId})}"
													class="btn btn-danger" role="button"><svg
															xmlns="http://www.w3.org/2000/svg" width="16" height="16"
															fill="currentColor" class="bi bi-box-arrow-right"
															viewBox="0 0 16 16">
																			<path fill-rule="evenodd"
																d="M10 12.5a.5.5 0 0 1-.5.5h-8a.5.5 0 0 1-.5-.5v-9a.5.5 0 0 1 .5-.5h8a.5.5 0 0 1 .5.5v2a.5.5 0 0 0 1 0v-2A1.5 1.5 0 0 0 9.5 2h-8A1.5 1.5 0 0 0 0 3.5v9A1.5 1.5 0 0 0 1.5 14h8a1.5 1.5 0 0 0 1.5-1.5v-2a.5.5 0 0 0-1 0v2z" />
																			<path fill-rule="evenodd"
																d="M15.854 8.354a.5.5 0 0 0 0-.708l-3-3a.5.5 0 0 0-.708.708L14.293 7.5H5.5a.5.5 0 0 0 0 1h8.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3z" />
																		</svg></a></td>
											</tr>


										</tbody>
									</table>
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