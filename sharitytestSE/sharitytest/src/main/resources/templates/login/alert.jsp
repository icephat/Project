<!DOCTYPE html>
<html lang="th">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>Quixlab - Bootstrap Admin Dashboard Template by
	Themefisher.com</title>
<!-- Favicon icon -->
<link rel="icon" type="image/png" sizes="16x16"
	href="images/favicon.png">
<!-- Pignose Calender -->
<link href="./plugins/pg-calendar/css/pignose.calendar.min.css"
	rel="stylesheet">
<!-- Chartist -->
<link rel="stylesheet" href="./plugins/chartist/css/chartist.min.css">
<link rel="stylesheet"
	href="./plugins/chartist-plugin-tooltips/css/chartist-plugin-tooltip.css">
<!-- Custom Stylesheet -->
<link href="css/style.css" rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Nunito+Sans:300i,400,700&display=swap"
	rel="stylesheet" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />
<link href="https://unpkg.com/tailwindcss@^2/dist/tailwind.min.css"
	rel="stylesheet" />

<style type="text/css">
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

<body class="bg-gradient-to-r from-purple-300 to-blue-200">



	<!--**********************************
            Content body start
        ***********************************-->

	<form method="POST" modelAttribute="user" enctype="multipart/form-data"
		action="home">
		<div class="login-form-bg h-100">
			<div class="container h-100">
				<div class="row justify-content-center h-100">
					<div class="col-xl-6">
						<div class="form-input-content">

							<div class="card login-form mb-0" style="margin-top: 150px">
								<div class="card-body pt-5">
									<a class="text-center" href="index.html">
										<h4>เข้าสู่ระบบ</h4>
										<h4 style="color: red; margin-top: 10px; margin-bottom: 20px;">กรุณาตรวจสอบข้อมูลใหม่</h4>
									</a>

									<form class="mt-5 mb-5 login-input" style="margin-top: 20px">
										<div class="form-group">
											<input type="text" class="form-control" name="username"
												id="username" placeholder="Username">
										</div>
										<div class="form-group">
											<input type="password" class="form-control" name="password"
												id="password" placeholder="Password">
										</div>
										<button type="submit" class="btn login-form__btn submit w-100"
											style="color: #FFF;">login</button>


									</form>
									<p style="margin-top: 20px;">
										สมัครบัญชีผู้ใช้ <a
											href="register" class="text-primary">ลงทะเบียน</a>
									</p>

								</div>
							</div>
						</div>

					</div>

				</div>


			</div>

		</div>
	</form>

	<!-- #/ container -->

	<!--**********************************
            Content body end
        ***********************************-->


	<script type="text/javascript"
		src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-sidebar/0.2.2/js/sidebar.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/bootstrap.bundle.min.js"></script>
	<script
		src="https://kurdis.agri.kps.ku.ac.th/cassava/plugins/common/common.min.js"></script>
	<script src="https://kurdis.agri.kps.ku.ac.th/cassava/js/custom.min.js"></script>
	<script src="https://kurdis.agri.kps.ku.ac.th/cassava/js/gleek.js"></script>
	<script
		src="https://kurdis.agri.kps.ku.ac.th/cassava/js/styleSwitcher.js"></script>

	<!-- Date Picker Plugin JavaScript -->
	<script
		src="./plugins/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
	<script src="./plugins/timepicker/bootstrap-timepicker.min.js"></script>
	<script src="./plugins/bootstrap-daterangepicker/daterangepicker.js"></script>

</body>




</html>