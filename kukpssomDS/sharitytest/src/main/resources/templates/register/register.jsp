<!DOCTYPE html>
<html lang="th">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<title>ลงทะเบียน</title>
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

<style type=type="text/css">
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

<body class="bg-gradient-to-r from-blue-300 to-indigo-200">



	<!--**********************************
            Content body start
        ***********************************-->

	<div class="content-body" style="margin-top: 50px; margin-left: auto;">



		<div class="container-fluid" style="width: 50%;">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<div class="form-validation">
								<form method="POST" modelAttribute="user"
									enctype="multipart/form-data" action="login">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="username">บัญชีผู้ใช้
											<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="username"
												name="username" placeholder="" required>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="password">รหัสผ่าน
											<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="password" class="form-control" id="password"
												name="password" placeholder="" required>

										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="firstName">ชื่อ
											<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="firstName"
												name="firstName" placeholder="" required>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="lastName">นามสกุล
											<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="lastName"
												name="lastName" placeholder="" required>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="address">ที่อยู่
											<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<textarea class="form-control" id="address" name="address"
												rows="5" placeholder="" required></textarea>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="skills">เกี่ยวกับตัวเอง
											<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<textarea class="form-control" id="skills" name="skills"
												rows="5" placeholder="" required></textarea>
										</div>
									</div>

									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a href="login" type="submit" class="btn btn-outline-light">กลับ</a>
											<button  type="submit" class="btn btn-primary">ลงทะเบียน</button>
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