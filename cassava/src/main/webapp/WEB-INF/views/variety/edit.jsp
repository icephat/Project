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
							<h4 class="card-title">พันธุ์มันสำปะหลัง > ${variety.name}</h4>
							<div class="form-validation">
								<form class="form-valide"
									id="varietyData"
									method="post" modelAttribute="varietyDTO"
									enctype="multipart/form-data">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="name">ชื่อพันธุ์<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="variety.name" placeholder="" value="${variety.name}">
										<input class="inputerror" id="name" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="trichome">ขนที่ยอดอ่อน
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="variety.trichome" placeholder=""
												value="${variety.trichome }">
										<input class="inputerror" id="trichome" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="apicalLeavesColor">สียอดอ่อน
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												name="variety.apicalLeavesColor"
												placeholder="" value="${variety.apicalLeavesColor }">
										<input class="inputerror" id="apicalLeavesColor" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="youngLeavesColor">สีใบอ่อน
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="variety.youngLeavesColor" placeholder=""
												value="${variety.youngLeavesColor}">
										<input class="inputerror" id="youngLeavesColor" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="petioleColor">สีก้านใบ
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="variety.petioleColor" placeholder=""
												value="${variety.petioleColor}">
										<input class="inputerror" id="petioleColor" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label"
											for="centralLeafletShape">ลักษณะแฉกกลางใบ
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												 name="variety.centralLeafletShape"
												placeholder="" value="${variety.centralLeafletShape}">
										<input class="inputerror" id="centralLeafletShape" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="branchingHabi">การแตกกิ่ง
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="variety.branchingHabit" placeholder=""
												value="${variety.branchingHabit}">
											<input class="inputerror" id="branchingHabit" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label"
											for="heightToFirstBranching">ความสูงของการแตกกิ่งแรก(ซม.)
										</label>
										<div class="col-lg-6">
											<input type="number" class="form-control"
												
												name="variety.heightToFirstBranching" placeholder=""
												value="${variety.heightToFirstBranching}" onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))||event.charCode == 46">
											<input class="inputerror" id="heightToFirstBranching" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="plantHeight">ความสูงของลำต้น(ซม.)
										</label>
										<div class="col-lg-6">
											<input type="number" class="form-control" 
												name="variety.plantHeight" placeholder=""
												value="${variety.plantHeight}" onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))||event.charCode == 46">
											<input class="inputerror" id="plantHeight" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="stemColor">สีลำต้น
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="variety.stemColor" placeholder=""
												value="${variety.stemColor}">
											<input class="inputerror" id="stemColor" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label"
											for="starchContentRainySeason">เปอร์เซนต์แป้ง(ฤดูฝน)
										</label>
										<div class="col-lg-6">
											<input type="number" class="form-control"
												
												name="variety.starchContentRainySeason" placeholder=""
												value="${variety.starchContentRainySeason}" onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))||event.charCode == 46">
											<input class="inputerror" id="starchContentRainySeason" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label"
											for="starchContentDrySeason">เปอร์เซนต์แป้ง(ฤดูแล้ง)
										</label>
										<div class="col-lg-6">
											<input type="number" class="form-control"
												
												name="variety.starchContentDrySeason" placeholder=""
												value="${variety.starchContentDrySeason}" onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))||event.charCode == 46">
											<input class="inputerror" id="starchContentDrySeason" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="rootYield">ผลผลิต(กก.)
										</label>
										<div class="col-lg-6">
											<input type="number" class="form-control" 
												name="variety.rootYield" placeholder=""
												value="${variety.rootYield}" onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))||event.charCode == 46">
											<input class="inputerror" id="rootYield" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="mainCharacter">ลักษณะเด่น
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="variety.mainCharacter" placeholder=""
												value="${variety.mainCharacter}">
											<input class="inputerror" id="mainCharacter" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="limitationNote">ข้อจำกัด
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="variety.limitationNote" placeholder=""
												value="${variety.limitationNote}">
											<input class="inputerror" id="limitationNote" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="source">ที่มาข้อมูล
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control"
												name="variety.source" placeholder=""
												value="${variety.source}">
											<input class="inputerror" id="source" cssClass="error" disabled />
										</div>
									</div>

									<input type="hidden" name="variety.varietyId"
										value="${variety.varietyId}">
									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a
												href="${pageContext.request.contextPath}/variety/index"
												class="btn btn-light" role="button">กลับ</a> <input
												type="submit" class="btn btn-primary" role="button"
												id="uploadBtn" value="บันทึก">
										</div>
									</div>
								</form>
							</div>

							<p>ภาพ</p>
							<p align="right">
									<a
									href="${pageContext.request.contextPath}/variety/image/edit/${variety.varietyId}"
									class="btn btn-primary" role="button">เพิ่ม / แก้ไขภาพ</a>
								</p>

							<div class="row">

								
							</div>
							<div class="row">
								<c:forEach items="${imgVariety}" var="img" varStatus="loop">
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
			$("#uploadBtn").click(function() {
	//		  alert( $('#myModal'));
    //		   $('#myModal').modal('show');
    		   var formData = new FormData($('#varietyData')[0]);
			   $("#name").val("");
    		   $("#trichome").val("");
    		   $("#apicalLeavesColor").val("");
    		   $("#youngLeavesColor").val("");
    		   $("#petioleColor").val("");
    		   $("#centralLeafletShape").val("");
    		   $("#branchingHabit").val("");
    		   $("#heightToFirstBranching").val("");
    		   $("#plantHeight").val("");
    		   $("#stemColor").val("");
    		   $("#starchContentRainySeason").val("");
    		   $("#starchContentDrySeason").val("");
    		   $("#rootYield").val("");
    		   $("#mainCharacter").val("");
    		   $("#limitationNote").val("");
    		   $("#source").val("");

    		 
				$
					.ajax({
						url : "${pageContext.request.contextPath}/variety/update",
						type : 'POST',
						data : formData,
						success : function(response) {

   	            			
   	            		$('#result').html(response);
   	            		
   	            		
   	            			window.location.href = "${pageContext.request.contextPath }/variety/index";
   	            		
   	            		//alert(files[0].name);
   	            		//alert(files.length);
   	            		$('#myModal').modal('show');
   	            		
   	            		
   	           


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