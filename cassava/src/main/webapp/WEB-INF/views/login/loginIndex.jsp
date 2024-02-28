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
<!-- <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous"> -->
<link href="${pageContext.request.contextPath}/resources/css/style.css"
	rel="stylesheet">
</head>
<body class="h-100">
	<div class="login-form-bg h-100">
		<div class="container h-100">
			<div class="row justify-content-center h-100">
				<div class="col-xl-6">
					<div class="form-input-content">
						<div class="card login-form mb-0">
							<div class="card-body pt-5">
								
								<h4 align="center">เข้าสู่ระบบ</h4>
								<p></p>
								
								 <a href="${pageContext.request.contextPath}/login/oauth2/authorization/google" class="btn btn-google btn-user btn-block">
                                            <i class="fa fa-google" aria-hidden="true"></i> Sign in with <b>Google</b>
                                        </a>
								<p class="mt-5 login-form__footer">
									ไม่มีบัญชี? <a href="${pageContext.request.contextPath}/register/code" class="text-primary">ลงทะเบียนด้วยบัญชี Google</a>
								</p>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>

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