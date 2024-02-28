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

					<li><a href="../">Home</a></li>

				</ul>
			</nav>
			<!-- .navbar -->



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
					<p>Order</p>
				</div>

				<div class="row py-5">
					<div class="col-lg-10 mx-auto">
						<div class="card rounded shadow border-0">
							<div class="card-header bg-white">
								<input id="search" type="text" class="form-control"
									placeholder="Search name ">
							</div>
							<div class="card-body p-5 bg-white rounded">
								<div class="table-responsive">
									<span class="counter pull-right"></span>
									<table class="table table-hover table-bordered results">
										<thead>
											<tr>
												<th class="col-md-0 col-0">รหัส</th>
												<th class="col-md-3 col-3">รายการอาหาร</th>
												<th class="col-md-3 col-3">จำนวนคิวที่เหลือ</th>
												<th class="col-md-2 col-2">เวลาที่จะได้รับโดยประมาณ</th>
												<th class="col-md-2 col-2">ราคารวม</th>
											</tr>
											<tr class="warning no-result">
												<td colspan="4"><i class="fa fa-warning"></i> No result</td>
											</tr>
										</thead>
										<tbody id="table">
											<tr th:each="queueOrder : ${queueOrders}">
												<td th:text="${queueOrder.orderCode}">OD003</td>
												<td>
													<div
														th:each="menuOrder : ${queueOrder.orderFood.menuInOrders}">
														<p th:text="${menuOrder.menu.name }"></p>
													</div>
												</td>
												<td th:text="${queueOrder.orderNumber}">ไม่ใส่ต้นหอม</td>
												<td th:text="${queueOrder.time}">40</td>
												<td th:text="${queueOrder.orderFood.price}"></td>
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
		$("#search").on(
				"keyup",
				function() {
					var value = $(this).val().toLowerCase();
					console.log(value);
					$("#table tr").filter(
							function() {
								$(this).toggle(
										$(this).text().toLowerCase().indexOf(
												value) > -1)
							});
				});
	</script>

</body>

</html>