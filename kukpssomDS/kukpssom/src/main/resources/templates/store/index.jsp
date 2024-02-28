<!DOCTYPE html>
<html lang="en">

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
							style="text-align: center; border-radius: 100%; background-color: rgb(255, 0, 0); width: 20px; color: rgb(255, 255, 255); font-size: small;">0</span></a></li>
					<li><a href="">เมนูทั้งหมด</a></li>
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

				<div class="row py-5">
					<div class="col-lg-10 mx-auto">
						<div class="card rounded shadow border-0">
							<div class="card-header bg-white">
								<a href="./addmenu" type="button"
									class="btn btn-primary float-end">เพิ่มเมนูอาหาร</a>
							</div>
							<div class="card-body p-5 bg-white rounded">
								<div class="table-responsive">
									<div class="form-group pull-right">
										<input type="text" class="search form-control"
											placeholder="ค้นหารายการอาหาร">
									</div>
									<span class="counter pull-right"></span>
									<table class="table table-hover table-bordered results">
										<thead>
											<tr>
												<th class="col-md-5 col-5">ชื่อเมนูอาหาร</th>
												<th class="col-md-4 col-4">ราคา (บาท)</th>
												<th class="col-md-3 col-3">เพิ่มเติม</th>
											</tr>
											<tr class="warning no-result">
												<td colspan="4"><i class="fa fa-warning"></i> No result</td>
											</tr>
										</thead>
										<tbody>
											<tr th:each="menu : ${menus}">
												<td th:text="${menu.name}">ผัดไทย</td>
												<td th:text="${menu.price}">40</td>
												<td><a th:href="@{'./editmenu/'+${menu.menuId}}"
													type="button" class="btn btn-warning"><svg
															xmlns="http://www.w3.org/2000/svg" height="1em"
															viewBox="0 0 576 512">
                              <path style="font-size: 13px;"
																d="M402.6 83.2l90.2 90.2c3.8 3.8 3.8 10 0 13.8L274.4 405.6l-92.8 10.3c-12.4 1.4-22.9-9.1-21.5-21.5l10.3-92.8L388.8 83.2c3.8-3.8 10-3.8 13.8 0zm162-22.9l-48.8-48.8c-15.2-15.2-39.9-15.2-55.2 0l-35.4 35.4c-3.8 3.8-3.8 10 0 13.8l90.2 90.2c3.8 3.8 10 3.8 13.8 0l35.4-35.4c15.2-15.3 15.2-40 0-55.2zM384 346.2V448H64V128h229.8c3.2 0 6.2-1.3 8.5-3.5l40-40c7.6-7.6 2.2-20.5-8.5-20.5H48C21.5 64 0 85.5 0 112v352c0 26.5 21.5 48 48 48h352c26.5 0 48-21.5 48-48V306.2c0-10.7-12.9-16-20.5-8.5l-40 40c-2.2 2.3-3.5 5.3-3.5 8.5z" />
                            </svg></a> <a
													th:href="@{'./deletemenu/'+${menu.menuId}}" type="button"
													class="btn btn-danger"><svg
															xmlns="http://www.w3.org/2000/svg" height="1em"
															viewBox="0 0 448 512">
															<path
																d="M135.2 17.7L128 32H32C14.3 32 0 46.3 0 64S14.3 96 32 96H416c17.7 0 32-14.3 32-32s-14.3-32-32-32H320l-7.2-14.3C307.4 6.8 296.3 0 284.2 0H163.8c-12.1 0-23.2 6.8-28.6 17.7zM416 128H32L53.2 467c1.6 25.3 22.6 45 47.9 45H346.9c25.3 0 46.3-19.7 47.9-45L416 128z" />
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