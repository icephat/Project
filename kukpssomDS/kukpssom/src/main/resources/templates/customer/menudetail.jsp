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

<!-- Template Main CSS File -->
<link href="../css/main.css" rel="stylesheet">
<link href="../css/checkBox.css" rel="stylesheet">

<!-- =======================================================
  * Template Name: Yummy
  * Updated: Sep 18 2023 with Bootstrap v5.3.2
  * Template URL: https://bootstrapmade.com/yummy-bootstrap-restaurant-website-template/
  * Author: BootstrapMade.com
  * License: https://bootstrapmade.com/license/
  ======================================================== -->
</head>

<body>

	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">

		<div
			class="container d-flex align-items-right justify-content-between">

			<a href="#" class="logo d-flex align-items-center me-auto me-lg-0">
				<!-- Uncomment the line below if you also wish to use an image logo -->
				<!-- <img src="assets/img/logo.png" alt=""> -->
				<h1>KU KPS SOM.</h1>
			</a>

			<div class="justify-content-end">
				<nav id="navbar" class="navbar">
					<ul>
						<li><a th:href="@{'/customer/'}">ร้านค้า</a></li>
						<li><a href="../customer/order">รายการสั่งซื้อ</a></li>
					</ul>
				</nav>
				<!-- .navbar -->

			</div>


			<a class="btn-book-a-table" href="../logout"
				style="background-color: rgba(255, 78, 2, 0.8); font-size: 11px;">Logout</a>
			<i class="mobile-nav-toggle mobile-nav-show bi bi-list"></i> <i
				class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

		</div>
	</header>
	<!-- End Header -->

	<main id="main">
		<section id="about" class="contact-menu">
			<div class="container-menu" data-aos="fade-up">

				<div class="section-header">
					<p th:text="${'ร้าน '+ store.name}"></p>
					<p>Menu</p>
				</div>

				<div class="container-menu">
					<div class="col-lg-10 mx-auto">
						<div class="card rounded shadow border-0">
							<div
								style="display: flex; justify-content: center; align-items: center;">
								<div class="card-body" style="text-align: center;">
									<form class="form-valide" method="POST"
										modelAttribute="menuInOrder" enctype="multipart/form-data"
										th:action="@{'/customer/createOrder'}">
										<h2 class="card-title"
											th:text="${'เมนู : '+ menu.name}"></h2>
										<input type="hidden" name="menuId" th:value="${menu.menuId}" />
										<input type="hidden" name="price" th:value="${menu.price}" />

										<fieldset class="checkbox-group">
											<legend class="checkbox-group-legend">เพิ่มไข่</legend>
											<div class="checkbox">
												<label class="checkbox-wrapper"> <input type="radio"
													class="checkbox-input" name="addOn" th:value="none" checked="checked"/> <span
													class="checkbox-tile"> <span class="checkbox-label">ไม่เพิ่ม</span>
												</span>
												</label>
											</div>
											<div class="checkbox">
												<label class="checkbox-wrapper"> <input type="radio"
													class="checkbox-input" name="addOn" th:value="omelet" /> <span
													class="checkbox-tile"> <span class="checkbox-label">ไข่เจียว</span>
												</span>
												</label>
											</div>
											<div class="checkbox">
												<label class="checkbox-wrapper"> <input type="radio"
													class="checkbox-input" name="addOn" th:value="friedegg" />
													<span class="checkbox-tile"> <span
														class="checkbox-label">ไข่ดาว</span>
												</span>
												</label>
											</div>
										</fieldset>
										<br>
										<fieldset class="checkbox-group">
											<legend class="checkbox-group-legend">ระดับ</legend>
											<div class="checkbox">
												<label class="checkbox-wrapper"> <input type="radio"
													class="checkbox-input" name="level" th:value="normal" checked="checked"/> <span
													class="checkbox-tile"> <span class="checkbox-label">ปกติ</span>
												</span>
												</label>
											</div>
											<div class="checkbox">
												<label class="checkbox-wrapper"> <input type="radio"
													class="checkbox-input" name="level" th:value="extra" /> <span
													class="checkbox-tile"> <span class="checkbox-label">พิเศษ</span>
												</span>
												</label>
											</div>
										</fieldset>
										<br>

										<h3 class="card-text" style="font-size: 25px;">Note</h3>
										<div>
											<textarea class=" col-lg-8" name="note"></textarea>

										</div>
										<br>
										<button type="submit" class="btn btn-outline-success">ยืนยันคำสั่งซื้อ</button>
									</form>
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

	<!-- ======= Footer ======= -->


	<a href="#"
		class="scroll-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<div id="preloader"></div>

	<!-- Vendor JS Files -->
	<script src="../vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="../vendor/aos/aos.js"></script>
	<script src="../vendor/glightbox/js/glightbox.min.js"></script>
	<script src="../vendor/purecounter/purecounter_vanilla.js"></script>
	<script src="../vendor/swiper/swiper-bundle.min.js"></script>
	<script src="../vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="../js/main.js"></script>

	<!--Icon-->
	<script src="https://kit.fontawesome.com/74746c9f39.js"
		crossorigin="anonymous"></script>

</body>

</html>