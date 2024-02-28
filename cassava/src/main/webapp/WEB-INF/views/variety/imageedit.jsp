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
							<h4 class="card-title">พันธุ์มันสำปะหลัง > ${variety.name} > แก้ไขภาพ</h4>
						<div class="row">
							<c:forEach items="${imgVariety}" var="img" varStatus="loop">
								<div class="col-3">
									<div class="card">
										<div class="card-body">
											<div class="text-center">
												<img alt="" class=" thumbnail mt-4"
													width="150" height="150"
													src="data:image/jpeg;base64,${dtoList[loop.index].image }">														
												<p class="text-muted">
												<h3>
													<a href="${pageContext.request.contextPath}/variety/image/delete/${img.imgVarietyId}"
														onclick="return confirm('คุณยืนยันที่จะลบหรือไม่');">
														<i class="fa fa-trash" aria-hidden="true"></i>
													</a>
												</h3>												
												
												
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
							
						<div class="form-validation">
							<form class="form-valide"
								action="${pageContext.request.contextPath }/variety/image/save"
								method="post" modelAttribute="varietyDTO"
								enctype="multipart/form-data">

								<input type="hidden" name="variety.varietyId"
									value="${variety.varietyId}">
								
								<div class="form-group row">
									<!--<label class="col-lg-4 col-form-label" for="files">เพิ่มภาพ
									</label>-->
									<div class="col-lg-6">
										<input type="file" class="form-control" id="nmfiles"
											name="files" placeholder=""style="display:none">
									</div>
								</div>
								
								<div class="input-field">
                					<label class="active">เพิ่มภาพ</label>
                					<div id="input-images-2" class="input-images-2" style="padding-top: .5rem;"></div>
            					</div>
								
								<div class="form-group row">
									<div class="col-lg-8 ml-auto">
										<a
											href="${pageContext.request.contextPath}/variety/index"
											class="btn btn-light" role="button">กลับ</a> <input
											type="submit" class="btn btn-primary" role="button"
											value="บันทึก">
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