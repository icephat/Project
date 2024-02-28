<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
	<jsp:include page="../layouts/mainLayoutHead.jsp" />
	<!-- Custom Stylesheet -->
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/image-uploader.min.css">
	<link
		href="https://fonts.googleapis.com/css?family=Lato:300,700|Montserrat:300,400,500,600,700|Source+Code+Pro&display=swap"
		rel="stylesheet">
		<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
		<style type="text/css">
		.inputerror {
			border: 0;
			color: red;
			background-color: white;
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
							<h4 class="card-title">โรคมันสำปะหลัง > ${targetofsurvey.name}</h4>
							<div class="form-validation">
								<form class="form-valide"
									id="targetofsurveyData"
									modelAttribute="targetofsurvey">
									<input type="hidden" class="form-control"  name="targetOfSurveyId" placeholder=""
												value="${targetofsurvey.targetOfSurveyId}">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="name">ชื่อโรค<span
												class="text-danger">*</span>
										</label>
										
										<div class="col-lg-6">
											<input type="text" class="form-control"  name="name" placeholder=""
												value="${targetofsurvey.name}">
											<input class="inputerror" id="name" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label"
											for="disease.code">รหัสโรคมันสำปะหลัง
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="disease.code"
												name="disease.code" placeholder=""
												value="${targetofsurvey.disease.code}">
											<input class="inputerror" id="code" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="disease.symptom">ลักษณะอาการ
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="disease.symptom"
												name="disease.symptom" placeholder=""
												value="${targetofsurvey.disease.symptom}">
											<input class="inputerror" id="symptom" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label"
											for="disease.controlDisease">การควบคุมโรค
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="disease.controlDisease"
												name="disease.controlDisease" placeholder=""
												value="${targetofsurvey.disease.controlDisease}">
											<input class="inputerror" id="controlDisease" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label"
											for="disease.controlPest">การควบคุมแมลงพาหะ
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="disease.controlPest"
												name="disease.controlPest" placeholder=""
												value="${targetofsurvey.disease.controlPest}">
												<input class="inputerror" id="controlPest" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="disease.source">ที่มาข้อมูล
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="disease.source"
												name="disease.source" placeholder=""
												value="${targetofsurvey.disease.source}">
											<input class="inputerror" id="source" cssClass="error" disabled />
										</div>
									</div>
									<input type="hidden" class="form-control" id="disease.diseaseId"
										name="disease.diseaseId" placeholder=""
										value="${targetofsurvey.disease.diseaseId}">
									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a href="${pageContext.request.contextPath }/disease/index"
												class="btn btn-light" role="button">กลับ</a> <input type="submit"
												class="btn btn-primary" id="button" role="button" value="บันทึก">
										</div>
									</div>
								</form>
							</div>
							<div class="row">
								<div class="col-12">
									<p>
										<h5>แมลงพาหะและเชื้อสาเหตุ</h5>
									</p>
									<p align="right">
										<a href="${pageContext.request.contextPath}/disease/edit/pestpathogen/${targetofsurvey.disease.diseaseId}"
											class="btn btn-primary" role="button">เเก้ไขแมลงพาหะและเชื้อสาเหตุ</a>
									</p>
								</div>
								<div class="col-6">
									<div class="table-responsive">
										<table class="table">
											<thead>
												<tr>
													<th>แมลงพาหะ</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${Pests}" var="Pests">
													<tr>
														<td>${Pests.name} (${Pests.scientificName})</td>
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
								
								<div class="col-6">
									<div class="table-responsive">
										<table class="table">
											<thead>
												<tr>
													<th>เชื้อสาเหตุ</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${Pathogens}" var="Pathogens">
													<tr>
														<td>${Pathogens.scientificName} (${Pathogens.pathogentype.name})
														</td>
													</tr>
												</c:forEach>
											</tbody>
										</table>
									</div>
								</div>
							</div>



							<div class="row">
								<div class="col-12">
									<p><h5>ภาพ</h5></p>
									<p align="right">
										<a href="${pageContext.request.contextPath}/disease/image/edit/${targetofsurvey.disease.diseaseId}"
											class="btn btn-primary" role="button">แก้ไขภาพ</a>
									</p>
								</div>
							</div>
							<div class="row">

								<c:forEach items="${imgDisease}" var="img" varStatus="loop">


									<div class="col-3">
										<div class="card">
											<div class="card-body">

												<div class="text-center">
													<img alt="" class=" thumbnail mt-4" width="150" height="150"
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
		<!--**********************************
	     Content body end
	***********************************-->
		<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"
			integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>

		<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/image-uploader.min.js">
		</script>

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
										$("#code").val("");
										$("#symptom").val("");
										$("#controlDisease").val("");
										$("#controlPest").val("");
										$("#source").val("");
										

					
	
									$
											.ajax({
												url : "${pageContext.request.contextPath}/disease/update",
												type : 'POST',
												data : formData,
												success : function(response) {
													//alert("check");
													window.location.href = "${pageContext.request.contextPath}/disease/index";
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



			$(function () {
				var isChange = false;
				$('.input-images-2')
					.bind(
						'DOMNodeInserted DOMNodeRemoved',
						function (event) {
							if (event.type == 'DOMNodeInserted' &&
								!isChange) {
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
				$(".input-images-2").mouseover(function () {
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
					preloaded: preloaded,
					imagesInputName: 'photos',
					preloadedInputName: 'old',
					maxSize: 2 * 1024 * 1024,
					maxFiles: 10
				});
				// Input and label handler
				$('input').on('focus', function () {
					$(this).parent().find('label').addClass('active')
				}).on('blur', function () {
					if ($(this).val() == '') {
						$(this).parent().find('label').removeClass('active');
					}
				});
				// Sticky menu
				let $nav = $('nav'),
					$header = $('header'),
					offset = 4 * parseFloat($(
						'body').css('font-size')),
					scrollTop = $(this).scrollTop();
				// Initial verification
				setNav();
				// Bind scroll
				$(window).on('scroll', function () {
					scrollTop = $(this).scrollTop();
					// Update nav
					setNav();
				});

				function setNav() {
					if (scrollTop > $header.outerHeight()) {
						$nav.css({
							position: 'fixed',
							'top': offset
						});
					} else {
						$nav.css({
							position: '',
							'top': ''
						});
					}
				}
			});
		</script>
</body>

</html>