<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

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

</head>

<body>

	<!-- ======= Header ======= -->
	<header id="header" class="header fixed-top d-flex align-items-center">
		<div
			class="container d-flex align-items-center justify-content-between">

			<a href="index.html"
				class="logo d-flex align-items-center me-auto me-lg-0"> <!-- Uncomment the line below if you also wish to use an image logo -->
				<!-- <img src="./img/logo.png" alt=""> -->
				<h1>
					KU KPS SOM<span>.</span>
				</h1>
			</a>
		</div>
	</header>
	<!-- End Header -->

	<!-- ======= Hero Section ======= -->


	<main id="main">

		<!-- ======= Book A Table Section ======= -->
		<section id="book-a-table" class="book-a-table "
			style="margin-top: 50px;">
			<div class="container " data-aos="fade-up" style="width: 70%;">

				<div class="row g-0 "
					style="justify-content: center; align-items: center; padding: 10px; border-radius: 20px; box-shadow: 0px 5px 10px 0px rgba(0, 0, 0, 0.5);">

					<div class="col-md-6 reservation-img"
						style="background-image: url(./img/reservation.jpg);"
						data-aos="zoom-out" data-aos-delay="200"></div>

					<div class="col-lg-4 d-flex align-items-center ">
						<form method="POST" modelAttribute="loginDTO"
							enctype="multipart/form-data" th:action="@{/home}" class="login">
							<div class="row g-0">
								<div class="" style="margin-bottom: 15px;">
									<input type="text" name="username" class="form-control"
										id="name" placeholder="Username" data-rule="minlen:4"
										data-msg="Please enter at least 4 chars">
									<div class="validate"></div>
								</div>
								<div class="">
									<input type="password" class="form-control" name="password"
										id="password" placeholder="password" data-rule="password"
										data-msg="Please enter a valid email">
									<div class="validate"></div>
								</div>
							</div>
							<div class="mb-3">
								<h3
									style="font-size: xx-small; margin-top: 10px; color: red; font-family: sans-serif;">
									** username หรือ password ผิดพลาด กรุณาทำงานเข้าสู่ระบบอีกครั้ง
									**</h3>
							</div>
							<div class="text-center">
								<button type="submit">เข้าสู่ระบบ</button>
							</div>
						</form>
					</div>
					<!-- End Reservation Form -->

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

</body>

</html>