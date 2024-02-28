<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
							<h4 class="card-title">การสำรวจ > ภาพ</h4>		
							<div class="row justify-content-end">
								<a href="${pageContext.request.contextPath}/survey/summary/${id}"
										class="btn btn-light" role="button">กลับ</a>
							</div>												
							<div class="row">
								<c:forEach items="${imgSurveyTargetPoint}" var="img"
									varStatus="loop">
									<div class="col-2">
										<div class="card">
											<div class="card-body">
												<div class="text-center">
													<img alt="" class=" thumbnail mt-4" width="100%"
														height="100%"
														src="data:image/jpeg;base64,${dtoList[loop.index].image }">
													<p class="text-muted">
													<p>${name[loop.index]}</p>
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
