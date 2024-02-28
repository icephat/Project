<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<title>KU KPS SOM</title>
<meta content="" name="description">
<meta content="" name="keywords">

<!-- Favicons -->
<link href="./img/favicon.png" rel="icon">
<link href="./img/apple-touch-icon.png" rel="apple-touch-icon">

<!-- Google Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,600;1,700&family=Amatic+SC:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&family=Inter:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
	rel="stylesheet">

<!-- Vendor CSS Files -->
<link href="./vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link href="./vendor/bootstrap-icons/bootstrap-icons.css"
	rel="stylesheet">
<link href="./vendor/aos/aos.css" rel="stylesheet">
<link href="./vendor/glightbox/css/glightbox.min.css" rel="stylesheet">
<link href="./vendor/swiper/swiper-bundle.min.css" rel="stylesheet">

<!-- Template Main CSS File -->
<link href="./css/main.css" rel="stylesheet">

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

			<a href="index.html"
				class="logo d-flex align-items-center me-auto me-lg-0"> <!-- Uncomment the line below if you also wish to use an image logo -->
				<!-- <img src="./img/logo.png" alt=""> -->
				<h1>KU KPS SOM.</h1>
			</a>

			<div class="justify-content-end">
				<nav id="navbar" class="navbar">
					<ul>
						<li><a href="#hero">Home</a></li>
						<li><a href="#about">Store</a></li>
					</ul>
				</nav>
				<!-- .navbar -->

			</div>


			<a class="btn-book-a-table" href="./login"
				style="background-color: rgba(255, 78, 2, 0.8);">Login</a> <i
				class="mobile-nav-toggle mobile-nav-show bi bi-list"></i> <i
				class="mobile-nav-toggle mobile-nav-hide d-none bi bi-x"></i>

		</div>
	</header>
	<!-- End Header -->

	<!-- ======= Hero Section ======= -->
	<section id="hero" class="hero d-flex align-items-center section-bg">
		<div class="container">
			<div class="row justify-content-between gy-5">
				<div
					class="col-lg-5 order-2 order-lg-1 d-flex flex-column justify-content-center align-items-center align-items-lg-start text-center text-lg-start">
					<h2 data-aos="fade-up">
						สั่งส้มออนไลน์<br>
					</h2>
					<p data-aos="fade-up" data-aos-delay="100">
						ระบบสั่งอาหารโรงอาหารกลาง1 (โรงส้ม) <br> <span
							style="color: red;">*ลูกค้าต้องเดินทางไปรับอาหารที่สั่งรวมถึงจ่ายเงินที่หน้าร้าน</span>
					</p>
					<div class="d-flex" data-aos="fade-up" data-aos-delay="200">
						<a href="#book-a-table" class="btn-book-a-table"
							style="background-color: #ff4e02cc;">Login</a>
					</div>
				</div>
				<div class="col-lg-5 order-1 order-lg-2 text-center text-lg-start">
					<img src="./img/hero-img.png" class="img-fluid" alt=""
						data-aos="zoom-out" data-aos-delay="300">
				</div>
			</div>
		</div>
	</section>
	<!-- End Hero Section -->

	<main id="main">

		<!-- ======= About Section ======= -->
		<section id="about" class="contact">
			<div class="container" data-aos="fade-up">

				<div class="section-header">
					<h2>ร้านค้าที่เข้าร่วม</h2>
					<p>Store</p>
				</div>

				<div class="row gy-4">

					<div th:each="store : ${stores }" class="col-md-6">
						<div class="info-item  d-flex align-items-center">
							<i class="icon bi bi-shop flex-shrink-0"></i>
							<div>
								<h3 th:text="${store.name }">ร้านกระทะร้อน</h3>
								<p>
									ประเภท : <span th:text="${store.type}">อาหารตามสั่ง</span>
								</p>
							</div>
							<div style="width: 30px;"></div>
							<div>

								<a th:href="@{'./queue/'+${store.storeId }}" type="button"
									class="btn btn-primary btn-sm">ดูคิวในร้าน</a>
							</div>
						</div>

					</div>
					<!-- End Info Item -->

				</div>

			</div>
		</section>
		<!-- End About Section -->

		<!-- ======= Book A Table Section ======= -->
		<section id="book-a-table" class="book-a-table">
			<div class="container" data-aos="fade-up">

				<div class="section-header">
					<h2>KU KPS SOM.</h2>
					<p>
						โปรด <span>เข้าสู่ระบบ</span>
					</p>
				</div>
			</div>
		</section>
		<!-- End Book A Table Section -->



	</main>
	<!-- End #main -->


	<a href="#"
		class="scroll-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>

	<div id="preloader"></div>

	<!-- Vendor JS Files -->
	<script src="./vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	<script src="./vendor/aos/aos.js"></script>
	<script src="./vendor/glightbox/js/glightbox.min.js"></script>
	<script src="./vendor/purecounter/purecounter_vanilla.js"></script>
	<script src="./vendor/swiper/swiper-bundle.min.js"></script>
	<script src="./vendor/php-email-form/validate.js"></script>

	<!-- Template Main JS File -->
	<script src="./js/main.js"></script>
	<script>
    const loginText = document.querySelector(".title-text .login");
    const loginForm = document.querySelector("form.login");
    const loginBtn = document.querySelector("label.login");
    const signupBtn = document.querySelector("label.signup");
    const signupLink = document.querySelector("form .signup-link a");
    signupBtn.onclick = (() => {
      loginForm.style.marginLeft = "-50%";
      loginText.style.marginLeft = "-50%";
    });
    loginBtn.onclick = (() => {
      loginForm.style.marginLeft = "0%";
      loginText.style.marginLeft = "0%";
    });
    signupLink.onclick = (() => {
      signupBtn.click();
      return false;
    });
  </script>

</body>

</html>