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
                    <div class="error-content">
                        <div class="card mb-0">
                            <div class="card-body text-center pt-5">
                                <h1 class="error-text text-primary">403</h1>
                                <h4 class="mt-4"><i class="fa fa-thumbs-down text-danger"></i> พบข้อผิดพลาด</h4>
                                <p>ขออภัย คุณไม่ได้รับอนุญาตเข้าใช้งาน</p>
                                <form class="mt-5 mb-5">
                                    
                                    <div class="text-center mb-4 mt-4"><a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Go to Homepage</a>
                                    </div>
                                </form>
                                
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

</body>
</html>
