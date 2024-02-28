<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html class="h-100" >
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
                <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10" />
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
				<div class="col-xl-6">
					<div class="form-input-content">
						<div class="card login-form mb-0">
							<div class="card-body pt-5">
								<a class="text-center">
									<h4>ลงทะเบียนด้วยบัญชี Google</h4>
									<h5 class="text-primary">${userName}</h5>
								</a>
																							
								<form class="mt-5 mb-5 login-input"
									action="${pageContext.request.contextPath}/register/confirm"
									method="GET">
									<h4>กรุณาใส่รหัสลงทะเบียน</h4>
									<div class="form-group">
										<input type="text" class="form-control" name="code"
											placeholder="รหัสลงทะเบียน" required>
											<p><span class="text-danger">${errMsg}</span></p>
									</div>																		
									<p align="center">
										<a href="${pageContext.request.contextPath}/"
													class="btn mb-1 btn-light">กลับ</a>
										<input type="submit" class="btn btn-primary" role="button"
										value="ตรวจสอบ"></p>
								</form>


								<div class="bootstrap-modal">
                                    <!-- Button trigger modal -->
                                    <p align="right">
                                    <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#exampleModalCenter">ไม่มีรหัสลงทะเบียน</button>
                                    </p>
                                    <!-- Modal -->
                                    <div class="modal fade bd-example-modal-lg" id="exampleModalCenter">
                                        <div class="modal-dialog modal-dialog-centered" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title">การลงทะเบียนแบบไม่มีรหัสลงทะเบียน</h5>
                                                    <button type="button" class="close" data-dismiss="modal"><span>&times;</span>
                                                    </button>
                                                </div>
                                                <div class="modal-body">
                                                    <p>เมื่อลงทะเบียนเรียบร้อยแล้ว ผู้ใช้จะต้องรออนุมัติจากเจ้าหน้าที่ก่อนจึงจะสามารถเข้าใช้ระบบได้</p>
                                                    <p align="center"><button type="button" class="btn btn-light" data-dismiss="modal">กลับ</button> </p>
                                                </div>
                                                <div class="modal-footer"> 
                                                	<p align="center">
	                                                    <a href="${pageContext.request.contextPath}/register/farmer/confirm"
														class="btn mb-1 btn-primary">ขอลงทะเบียนสำหรับเกษตรกร</a> 
														<a href="${pageContext.request.contextPath}/register/staff/confirm"
														class="btn mb-1 btn-primary">ขอลงทะเบียนสำหรับเจ้าหน้าที่</a>										
                                                   </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

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