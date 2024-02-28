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
						<li><a href="#about">รายการสั่งซื้อ</a></li>
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
		<section id="about" class="contact">
			<div class="container" data-aos="fade-up">

				<div class="section-header">
					<p th:text="${'ร้าน '+ store.name}"></p>
					<p>Menu</p>
				</div>

				<div class="row gy-4">
					<div class="col-md-6" th:each="menu : ${menus}">
						<a th:href="@{'/'+${menu.menuId}+'/detail'}">
							<div class="info-item  d-flex align-items-center">
								<i class="icon fa-solid fa-bowl-food flex-shrink-0"></i>
								<div>
									<h3 th:text="${menu.name}"></h3>
									<p th:text="${menu.price + ' บาท'}"></p>
								</div>
							</div>
						</a>
					</div>
					<!-- End Info Item -->
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