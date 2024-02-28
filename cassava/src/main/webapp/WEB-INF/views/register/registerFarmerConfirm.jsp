<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">

</head>
<body>
	<!--*******************
        Preloader start
    ********************-->
	<div id="preloader">
		<div class="loader">
			<svg class="circular" viewBox="25 25 50 50">
                <circle class="path" cx="50" cy="50" r="20" fill="none"
					stroke-width="3" stroke-miterlimit="10" />
            </svg>
		</div>
	</div>
	<!--*******************
        Preloader end
    ********************-->

	<!--**********************************
	            Content body start
	***********************************-->
	<div class="login-form-bg h-100">
		<div class="container h-100">
			<div class="row justify-content-center h-100">
				<div class="col-xl-10">
					<div class="form-input-content">
						<div class="card login-form mb-0">
							<div class="card-body pt-5">
								<a class="text-center">
									<h4>ลงทะเบียนด้วยบัญชี Google</h4>
									<h5 class="text-primary">${userName}</h5>
								</a>

								<div class="card mb-5">
									<div class="card-body">
										<h4>การจดลิขสิทธิ์</h4>
										<p>ผู้นำส่งภาพมาให้คลังข้อมูลภาพถ่ายโรคสำคัญของมันสำปะหลังจำเป็นต้องถ่ายโอนลิขสิทธิ์หรือระบุว่าข้อมูลเป็นสาธารณสมบัติ
											กรุณาเลือกหนึ่งในข้อความจากข้างล่าง

											ข้อมูลและภาพที่ท่านส่งมาให้นั้นจะถูกนำไปใช้ในกลุ่มนักวิชาการโรคพืชและนักวิชาการเกษตรผ่านคลังข้อมูลภาพถ่ายโรคสำคัญของมันสำปะหลัง
											ทางคลังข้อมูลภาพถ่ายโรคสำคัญของมันสำปะหลังไม่แนะนำให้ผู้ส่งข้อมูลและภาพใช้งานข้อมูลและภาพภายใต้จุดประสงค์อื่นที่ไม่ตรงกับข้อตกลงการใช้งานของคลังข้อมูลภาพถ่ายโรคสำคัญของมันสำปะหลัง
											ข้อตกลงนี้จะเปลี่ยนไปตามนโยบายของคลังข้อมูลภาพถ่ายโรคสำคัญของมันสำปะหลัง
										</p>
										<br>
										<h4>การถ่ายโอนลิขสิทธิ์</h4>
										<p>ข้าพเจ้าขอยืนยันว่าข้าพเจ้าเป็นเจ้าของลิขสิทธิ์ข้อมูลและภาพถ่ายและด้วยเหตุนี้ข้าพเจ้าขอถ่ายโอนลิขสิทธิ์ทั้งหมดของข้อมูลและภาพให้แก่คลังข้อมูลภาพถ่ายโรคสำคัญของมันสำปะหลัง
											การถ่ายโอนครั้งนี้จะทำให้คลังข้อมูลภาพถ่ายโรคสำคัญของมันสำปะหลังมีอำนาจเด็ดขาดในการให้ลิขสิทธิ์ของข้อมูลและภาพให้แก่ผู้อื่น
											ข้าพเจ้าในฐานะเจ้าของลิขสิทธิ์ยังสามารถใช้งานภาพนี้สำหรับงานตีพิมพ์ของข้าพเจ้าได้โดยที่ไม่ต้องขออนุญาตจากคลังข้อมูลภาพถ่ายโรคสำคัญของมันสำปะหลัง
											ข้าพเจ้ายังขอยืนยันอีกว่างานที่ตีพิมพ์จากข้อมูลและภาพของคลังข้อมูลภาพถ่ายโรคสำคัญของมันสำปะหลังจะไม่ละเมิดลิขสิทธิ์หรือสิทธิของผู้อื่น
											ข้าพเจ้าทราบดีว่าคลังข้อมูลภาพถ่ายโรคสำคัญของมันสำปะหลังจะใช้คำพูดข้างต้นนี้ในการอ้างลิขสิทธิ์ของผลงาน
										</p>
										<br>
										<h4>ประกาศสาธารณสมบัติ</h4>
										<p>ข้าพเจ้าขอยืนยันว่าข้อมูลและภาพที่ได้เห็นจากข้างในระบบทำขึ้นโดยคลังข้อมูลภาพถ่ายโรคสำคัญของมันสำปะหลังหรือมีเหตุผลอื่นที่ทำให้ไม่สามารถจดลิขสิทธิ์ได้และตกเป็นสาธารณสมบัติ
											ด้วยเหตุนี้ผลงานจึงสามารถตีพิมพ์ได้อย่างอิสระ
											ข้าพเจ้าทราบดีว่าคลังข้อมูลภาพถ่ายโรคสำคัญของมันสำปะหลังจะใช้คำพูดข้างต้นเพื่อพิจารณาว่าผลงานไร้ลิขสิทธิ์
										</p>

									</div>
								</div>



								<form class="mt-5 mb-5 login-input"
									action="/cassava/register/farmer/confirm" method="post">
									<div class="form-group" >
										<label class="form-check-label" style="margin-left: 30px;"> <input
											type="checkbox" class="form-check-input" value="1"
											name="check">เลือกข้อนี้เพื่อยอมรับกับการถ่ายโอนลิขสิทธิ์ข้างต้น
											<br> <input type="checkbox" class="form-check-input"
											value="1" name="check2">เลือกข้อนี้เพื่อยอมรับกับประกาศสาธารณสมบัติข้างต้น
										</label>
										<p>
											<span class="text-danger"></span>
										</p>

										<c:if test="${check == 0}">
											<span style="color: red; margin-left: 10px;">กรุณากดยอมรับเงื่อนไขทั้ง 2 ข้อ</span>
										</c:if>
									</div>
									<p align="center" >
										<a href="${pageContext.request.contextPath}/register/code"
											class="btn mb-1 btn-light">กลับ</a>
										<button class="btn btn-primary">ยืนยัน</button>
									</p>
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



	<script
		src="${pageContext.request.contextPath}/resources/plugins/common/common.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/custom.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/settings.js"></script>
	<script src="${pageContext.request.contextPath}/resources/js/gleek.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/js/styleSwitcher.js"></script>
	<grammarly-desktop-integration data-grammarly-shadow-root="true"></grammarly-desktop-integration>
</body>
</html>