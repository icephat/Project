<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../layouts/mainLayoutHead.jsp" />
<!-- Custom Stylesheet -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/image-uploader.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,700|Montserrat:300,400,500,600,700|Source+Code+Pro&display=swap"
	rel="stylesheet">
	<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style type="text/css">
.inputerror {
	border: 0;
	color: red;
	background-color:transparent;
	width:500px;
}
</style>
</head>
<body>
	<jsp:include page="../layouts/mainLayoutBodyHeader.jsp" />
	<!--**********************************
	            Content body start
	***********************************-->
	<div class="content-body">
		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">ศัตรูธรรมชาติ > ${targetofsurvey.name}</h4>
							<div class="form-validation">
								<form class="form-valide"
								id="targetofsurveyData"
									 modelAttribute="targetofsurvey"
									enctype="multipart/form-data">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="name">ชื่อ<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="name" placeholder=""
												value="${targetofsurvey.name}" required>
												<input class="inputerror" id="name" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="naturalenemy.commonName">ชื่อสามัญ<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="naturalenemy.commonName"
												name="naturalenemy.commonName" placeholder=""
												value="${targetofsurvey.naturalenemy.commonName}" required>
												<input class="inputerror" id="commonName" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="naturalenemy.scientificName">ชื่อวิทยาศาสตร์<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="naturalenemy.scientificName"
												name="naturalenemy.scientificName" placeholder=""
												value="${targetofsurvey.naturalenemy.scientificName}" required>
												<input class="inputerror" id="scientificName" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="naturalenemy.type">ชนิด<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<form:select path="targetofsurvey.naturalenemy.type" name="targetofsurvey.naturalenemy.type"
												class="form-control">
												<form:options items="${naturalEnemies}"></form:options>
											</form:select>

										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="naturalenemy.controlMethod">การควบคุม
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="naturalenemy.controlMethod"
												name="naturalenemy.controlMethod" placeholder=""
												value="${targetofsurvey.naturalenemy.controlMethod}" required>
												<input class="inputerror" id="controlMethod" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="naturalenemy.releaseRate">อัตราการปล่อย
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="naturalenemy.releaseRate"
												name="naturalenemy.releaseRate" placeholder=""
												value="${targetofsurvey.naturalenemy.releaseRate}" required>
												<input class="inputerror" id="releaseRate" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="naturalenemy.source">แหล่งที่มา<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="naturalenemy.source"
												name="naturalenemy.source" placeholder="" value="${targetofsurvey.naturalenemy.source}"
												required>
												<input class="inputerror" id="source" cssClass="error" disabled />
										</div>
									</div>
									<input type="hidden" name="targetOfSurveyId"
										value="${targetofsurvey.targetOfSurveyId}">
									<input type="hidden" name="naturalenemy.naturalEnemyId"
										value="${targetofsurvey.naturalenemy.naturalEnemyId}">
									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a
												href="${pageContext.request.contextPath }/naturalenemy/index"
												class="btn btn-light" role="button">กลับ</a> <input
												type="submit" class="btn btn-primary" role="button"
												id="button"
												value="บันทึก">

										</div>
									</div>
								</form>
							</div>

							<p><h5>ภาพ</h5></p>
							<p align="right"><a
								href="${pageContext.request.contextPath}/naturalenemy/image/edit/${targetofsurvey.naturalenemy.naturalEnemyId}"
								class="btn btn-primary" role="button">แก้ไขภาพ</a></p>

							<div class="row">
								<c:forEach items="${imgNaturalenemy}" var="img" varStatus="loop">
									<div class="col-3">
										<div class="card">
											<div class="card-body">
												<div class="text-center">
													<img alt="" class=" thumbnail mt-4" width="150"
														height="150"
														src="data:image/jpeg;base64,${dtoList[loop.index].image }">
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
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
	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"
		integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
		crossorigin="anonymous"></script>

	<script type="text/javascript"
		src="${pageContext.request.contextPath}/resources/js/image-uploader.min.js"></script>

	<script>
					$(function() {

var isChange = false;

$("#button")
		.click(
				function() {
					var formData = new FormData($('#targetofsurveyData')[0]);
					//$("#pathogentype option:selected").val();
					//$("#scientificName").val("");
					//$("#pathogentype").val("");
					$("#name").val("");
					$("#commonName").val("");
					$("#scientificName").val("");
					$("#controlMethod").val("");
					$("#releaseRate").val("");
					$("#source").val("");
					



				$
						.ajax({
							url : "${pageContext.request.contextPath}/naturalenemy/update",
							type : 'POST',
							data : formData,
							success : function(response) {
								window.location.href = "${pageContext.request.contextPath}/naturalenemy/index";
							},
							error : function(response) {
								//alert(response["responseText"]);
								const respJSON = response["responseText"];
								//alert(respJSON);
								const myObj = JSON
										.parse(respJSON);

								for ( var key in myObj.body) {
									//if (p.hasOwnProperty(key)) {
									//alert(key+ " "+ $('#'+ key).val());
									$('#' + key)
											.val(
													myObj.body[key]);
									//}
								}

								return false;
							},
							cache : false,
							contentType : false,
							processData : false
						});
			
return false;
});
});
		$(function() {

			var isChange = false;

			$('.input-images-2')
					.bind(
							'DOMNodeInserted DOMNodeRemoved',
							function(event) {
								if (event.type == 'DOMNodeInserted'
										&& !isChange) {
									isChange = true;
									let list = new DataTransfer();
									let fileInput = document
											.querySelector('#f');
									//fileList = fileInput.files ;

									for (var i = 0; i < fileInput.files.length; i++) {

										list.items.add(fileInput.files[i]);
										//alert(list.files[i].name);

									}
									//alert(fileList.length);
									//if(list.files.length < $('input[name^=pho]')[0].files.length)
									for (var i = 0; i < $('input[name^=pho]')[0].files.length; i++) {
										list.items
												.add($('input[name^=pho]')[0].files[i]);
									}

									//alert(list.files.length);

									fileInput.prop('files', list.files);
									//fileInput.files =$('input[name^=pho]')[0].files ;
									// fileInput.files = new FileListItems(list.files) ;
								} else {
									//isChange = true ;
									let fileInput = document
											.querySelector('#nmfiles');
									fileInput.files = $('input[name^=pho]')[0].files;
									//alert('Content removed! Current content:' + '\n\n' + this.innerHTML);
								}
							});

			$(".input-images-2").mouseover(function() {
				if (isChange) {
					let fileInput = document.querySelector('#nmfiles');
					fileInput.files = $('input[name^=pho]')[0].files;
				}
				isChange = false;

			});

			let preloaded = [
			//{id: 1, src: 'https://picsum.photos/500/500?random=1'},
			//{id: 2, src: 'https://picsum.photos/500/500?random=2'},
			//{id: 3, src: 'https://picsum.photos/500/500?random=3'},
			//{id: 4, src: 'https://picsum.photos/500/500?random=4'},
			//{id: 5, src: 'https://picsum.photos/500/500?random=5'},
			//{id: 6, src: 'https://picsum.photos/500/500?random=6'},
			];

			$('.input-images-2').imageUploader({
				preloaded : preloaded,
				imagesInputName : 'photos',
				preloadedInputName : 'old',
				maxSize : 2 * 1024 * 1024,
				maxFiles : 10
			});

			// Input and label handler
			$('input').on('focus', function() {
				$(this).parent().find('label').addClass('active')
			}).on('blur', function() {
				if ($(this).val() == '') {
					$(this).parent().find('label').removeClass('active');
				}
			});

			// Sticky menu
			let $nav = $('nav'), $header = $('header'), offset = 4 * parseFloat($(
					'body').css('font-size')), scrollTop = $(this).scrollTop();

			// Initial verification
			setNav();

			// Bind scroll
			$(window).on('scroll', function() {
				scrollTop = $(this).scrollTop();
				// Update nav
				setNav();
			});

			function setNav() {
				if (scrollTop > $header.outerHeight()) {
					$nav.css({
						position : 'fixed',
						'top' : offset
					});
				} else {
					$nav.css({
						position : '',
						'top' : ''
					});
				}
			}
		});
	</script>
</body>
</html>