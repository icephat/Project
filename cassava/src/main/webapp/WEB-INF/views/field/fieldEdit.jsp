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
<meta charset="UTF-8">
<title>FieldCreate Page</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/image-uploader.min.css">
<link
	href="https://fonts.googleapis.com/css?family=Lato:300,700|Montserrat:300,400,500,600,700|Source+Code+Pro&display=swap"
	rel="stylesheet">

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<style type="text/css">
.inputerror {
	border: 0;
	color: red;
	background-color:transparent;
	width: 500px;
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

			<div class="row">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<div class="form-validation">
								<form id="fieldData" modelAttribute="field"
									enctype="multipart/form-data">
									<h4 class="card-title">แปลงเพาะปลูก > รายละเอียด</h4>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">code
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" name="code"
												value="${field.code}"> <input class="inputerror"
												id="code" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ชื่อแปลง
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" name="name"
												value="${field.name}"> <input class="inputerror"
												id="name" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">เจ้าของแปลง

										</label>
										<div class="col-lg-6">
											<select name="framownerid" id="framownerid"
												class="form-control selectpicker" data-live-search="true">
												<c:choose>
												<c:when test="${farmer.size()!=0}">
												<c:forEach items="${farmer}" var="farmer">
													<option value="${farmer.user.userId}"
														<c:if test = "${farmer.user.userId==farmerOwner.user.userId}">
    													selected
   												   </c:if>>${farmer.user.title}
														${farmer.user.firstName}
														${farmer.user.lastName}
														<c:choose>
														<c:when test="${farmer.user.username==null}">
														(ไม่มีบัญชีผู้ใช้)
														</c:when>
														<c:when test="${farmer.user.username!=null}">
														(${farmer.user.username})
														</c:when>
														</c:choose></option>
												</c:forEach>
												</c:when>
												<c:when test="${farmer.size()==0}">
														<option >ไม่มีเกษตรกร</option>
												</c:when>
												</c:choose>
											</select>					
										</div>
									</div>
									<br>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ที่อยู่<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="addressCheck"
												name="address" value="${field.address}"> <input
												class="inputerror" id="address" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">หมู่
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control"  name="moo"
												value="${field.moo}">
											<input
												class="inputerror" id="moo" cssClass="error" disabled />
										</div>
									</div>

									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ถนน
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control"  name="road"
												value="${field.road}">
											<input
												class="inputerror" id=road cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">จังหวัด<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<select name="subdistrict.district.province" id="province"
												class="form-control" required>
												<c:forEach items="${provinces}" var="province">
													<option value="${province.provinceId}"
														<c:if test = "${province.provinceId==fieldprovinces.provinceId}">
    													selected
   												   </c:if>>${province.name}</option>
												</c:forEach>
											</select> <input class="inputerror" id="provinces" cssClass="error"
												disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">อำเภอ
											<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<select name="subdistrict.district.districtId" id="district"
												class="form-control" required>
												<c:forEach items="${district}" var="district">
													<option value="${district.districtId}"
														<c:if test = "${district.districtId==fielddistrict.districtId}">
    													selected
   												   </c:if>>${district.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ตำบล<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<select name="subdistrict.subdistrictId" id="subdistrict"
												class="form-control" required>
												<c:forEach items="${subdistrict}" var="subdistrict">
													<option value="${subdistrict.subdistrictId}"
														<c:if test = "${subdistrict.subdistrictId==fieldsubdistrict.subdistrictId}">
    													selected
   												   </c:if>>${subdistrict.name}</option>
												</c:forEach>
											</select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ขนาดพื้นที่(ไร่)<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="hidden" class="form-control" name="size"
												value="${field.size}"> 
											<input type="number"
												class="form-control" id="sizeCheck" value="${field.size}" onkeypress="return (event.charCode !=8 && event.charCode ==0 || (event.charCode >= 48 && event.charCode <= 57))||event.charCode == 46">
											<input class="inputerror" id="size" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">จุดสังเกตแปลง
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username"
												name="landmark" value="${field.landmark}">
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ละติจูด
										</label>
										<div class="col-lg-6">
											<input type="number" class="form-control" name="latitude"
												value="${field.latitude}">  <input class="inputerror"
												id="latitude" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ลองจิจูด
										</label>
										<div class="col-lg-6">
											<input type="number" class="form-control" name="longitude"
												value="${field.longitude}"> <input
												class="inputerror" id="longitude" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ความสูงจากระดับน้ำทะเล<span
											class="text-danger"></span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username"
												name="metresAboveSeaLv" value="${field.metresAboveSeaLv}">
										</div>
									</div>
									<!--
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">รูป<span
											class="text-danger"></span>
										</label>
										<div class="col-lg-6">
											 <input type="file" id="imageUpload" accept="image/*" onchange="previewImage(event)"name="img" >
											 <div class="row justify-content-center">
    										<img id="preview" src="data:image/jpeg;base64,${image.image}" alt="Image Preview" style="max-width: 200px; max-height: 200px; display: none;object-fit: cover;">
    										</div>
										</div>
									</div>-->

									<input type="hidden" value="${field.fieldId}" name="fieldId">
								</form>
								<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ภาพ
										</label>
										
										
									</div>
								<p align="right" style="margin-right: 20px;">
									<a
										href="${pageContext.request.contextPath}/field/image/edit/${field.fieldId}"
										class="btn btn-primary" role="button">เพิ่ม / แก้ไขภาพ</a>
								</p>
								
								
								<c:if test="${field.imgPath!=null}">
								<div class="form-group row">
									<div class="col-lg-4 col-form-label" >
									
									</div>
									<div class="col-md-3" >
										<div class="card" >
											<div class="card-body">
												<div class="text-center">
													<img alt="" class=" thumbnail mt-4" width="150"
														height="150" src="data:image/jpeg;base64,${image.image}">
												</div>
											</div>
										</div>
									</div>
								</div>
								</c:if>
								<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a href="${pageContext.request.contextPath }/field/index"
												class="btn btn-light" role="button">กลับ</a>
											<!--   <input
												type="submit" class="btn btn-primary" role="button"
												value="Next">-->
											<button id="button" class="btn btn-primary">บันทึก</button>

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
	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
	<script type="text/javascript"
		src="https://code.jquery.com/jquery-3.2.1.min.js"
		integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
		crossorigin="anonymous"></script>
	<script>
		$(function() {
			$("#button")
					.click(
							function() {
								let address = document
										.getElementById("addressCheck").value;
								let size = document.getElementById("sizeCheck").value;
								//let longitude = document.getElementById("longitudeCheck").value;
								//let latitude = document.getElementById("latitudeCheck").value;
								var formData = new FormData($('#fieldData')[0]);
								$("#code").val("");
								$("#name").val("");
								$("#address").val("");
								$("#latitude").val("");
								$("#longitude").val("");
								$("#size").val("");
								$("#moo").val("");
								$("#road").val("");
								$("#provinces").val("");
								let error = false;
								if (size == "") {
									size = "null";
									//alert(size);
								}
								
								//alert("check");
								$
										.ajax({
											url : "${pageContext.request.contextPath}/field/update/"
													+ size
													,
											type : 'POST',
											data : formData,
											dataType : 'json',
											success : function(response) {

												window.location.href = "${pageContext.request.contextPath}/field/index";
											},
											error : function(response) {
												const respJSON = response["responseText"];
												const myObj = JSON
														.parse(respJSON);
												for ( var key in myObj.body) {
													$('#' + key).val(
															myObj.body[key]);
												}
												return false;
											},
											cache : false,
											contentType : false,
											processData : false
										});
								return false;
							});
			$("#province")
					.change(
							function() {
								var selectedProvice = $(
										"#province option:selected").val();
								if (selectedProvice == "") {
									$('#district').html("");
									$('#subdistrict').html("");
									return;
								}
								$
										.ajax({
											method : "GET",
											url : "${pageContext.request.contextPath}/province/"
													+ selectedProvice,
											success : function(response) {
												$('#district').html(response);
												console.log(response);
												var selectedDistrict = $(
														"#district option:selected")
														.val();
												$
														.ajax({
															method : "GET",
															url : "${pageContext.request.contextPath}/district/"
																	+ selectedDistrict,
															success : function(
																	response) {
																$(
																		'#subdistrict')
																		.html(
																				response);
																console
																		.log(response);
															},

														});
											},

										});

							});

			$("#district")
					.change(
							function() {
								var selectedDistrict = $(
										"#district option:selected").val();
								$
										.ajax({
											method : "GET",
											url : "${pageContext.request.contextPath}/district/"
													+ selectedDistrict,
											success : function(response) {
												$('#subdistrict')
														.html(response);
											},

										});

							});

			$("#uploadBtn")
					.click(
							function() {
								var proid = $(this).val();
								$
										.ajax({
											url : "${pageContext.request.contextPath }/field/userinField2",
											type : 'GET',
											data : data,
											success : function(data) {
												console.log("in")

											}
										})
							})

		})

		var isChange = false;
		const progressArea = document.querySelector(".progress-area"), uploadedArea = document
				.querySelector(".uploaded-area");

		$('.input-images-2')
				.bind(
						'DOMNodeInserted DOMNodeRemoved',
						function(event) {
							if (event.type == 'DOMNodeInserted' && !isChange) {

								isChange = true;
								let list = new DataTransfer();
								let fileInput = document.querySelector('#f');
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
			maxFiles : 1
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
	</script>
</body>
</html>
