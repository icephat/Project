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
			background-color:transparent;
			width:500px;
			
		}
		</style>
</head>

<body>
	<jsp:include page="../layouts/mainLayoutBodyHeader.jsp" />
	<div class="content-body">
		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">ศัตรูพืช > ${pest.name}</h4>

							<div class="form-validation">
								<form class="form-valide" id="pestData"
									 modelAttribute="pest" enctype="multipart/form-data">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="name">ชื่อ<span
												class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control"  name="name" placeholder=""
												value="${pest.name}">
												<input class="inputerror" id="name" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="scientificName">ชื่อวิทยาศาสตร์<span
												class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="scientificName" placeholder="" value="${pest.scientificName}">
												<input class="inputerror" id="scientificName" cssClass="error" disabled />
										
											</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="source">แหล่งที่มา<span
												class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control"  name="source"
												placeholder="" value="${pest.source}">
												<input class="inputerror" id="source" cssClass="error" disabled />
										
										</div>
									</div>
									<input type="hidden" name="pestId" value="${pest.pestId}">
									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a href="${pageContext.request.contextPath}/pest/"
												class="btn btn-light" role="button">กลับ</a> <input type="submit"
												id="uploadBtn" class="btn btn-primary" role="button" value="บันทึก">
										</div>
									</div>
								</form>
							</div>

							<h4>สารป้องกันกำจัดศัตรูพืช</h4>
							<input type="hidden" name="pest.pestId" value="${pest.pestId}">
							<div class="form-group row">
								<div class="col-lg-8 ml-auto">
									<p align="right">
										<a href="${pageContext.request.contextPath}/pestcide/add/edit/${pest.pestId}"
											class="btn btn-primary" role="button">เพิ่มสารป้องกันกำจัดศัตรูพืช</a>
									</p>
								</div>
							</div>
							<div class="table-responsive">
								<table class="table table-striped table-bordered zero-configuration">
									<thead>
										<tr>
											<th>ชื่อสามัญ</th>
											<th>%สารออกฤทธิ์ และสูตร</th>
											<th>กลุ่มกลไกการออกฤทธิ์</th>
											<th>ระดับความเป็นพิษ</th>
											<th>อัตราการใช้</th>
											<th>แก้ไข</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${pestcides}" var="pestcide">
											<tr>
												<td>${pestcide.pesticideName}</td>
												<td>${pestcide.percentApi}</td>
												<td>${pestcide.pesticideMechanism}</td>
												<td>${pestcide.toxicityLevel}</td>
												<td>${pestcide.mixingRatio}</td>
												<td><a href="${pageContext.request.contextPath}/pestcide/edit/${pestcide.pestManagementId}"
														class="btn btn-primary" role="button"><i class="fa fa-edit"
															aria-hidden="true"></i></a>
													<a href="${pageContext.request.contextPath}/pestcide/delete/${pestcide.pestManagementId}"
														class="btn btn-danger" role="button"><i class="fa fa-trash"
															aria-hidden="true"></i></a>
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>



							<p><h5>ภาพ</h5></p>
							<p align="right"><a href="${pageContext.request.contextPath}/pest/image/edit/${pest.pestId}"
									class="btn btn-primary" role="button">แก้ไขภาพ</a></p>





							<div class="row">
								<c:forEach items="${imgPest}" var="img" varStatus="loop">
									<div class="card">
										<div class="card-body">
											<div class="text-center">
												<img alt="" class=" thumbnail mt-4" width="150" height="150"
													src="data:image/jpeg;base64,${dtoList[loop.index].image }">
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
	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
	<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"
		integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4=" crossorigin="anonymous"></script>

	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/image-uploader.min.js"></script>

	<script>
		    	  $("#uploadBtn").click(function() {
	//		  alert( $('#myModal'));
    //		   $('#myModal').modal('show');
    		   var formData = new FormData($('#pestData')[0]);
    		   $("#name").val("");
    		   $("#scientificName").val("");
    		   $("#source").val("");
    		   
    
				$
					.ajax({
						url : "${pageContext.request.contextPath}/pest/update",
						type : 'POST',
						data : formData,
						success : function(response) {
							window.location.href = "${pageContext.request.contextPath }/pest/index";
    		   
    		   
    		   
   	        return false;
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