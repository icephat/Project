<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/style.css">
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
							<h4 class="card-title">แจ้งเตือนการระบาด</h4>

							<div class="form-validation">
								<form class="form-valide" id="data" method="post"
									action="${pageContext.request.contextPath}/message/confirm"
									modelAttribute="messageDTO" enctype="multipart/form-data">


									<div class="form-validation">


										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="val-username">เลือกโรคที่ต้องการ<span
												class="text-danger">*</span>
											</label>
											<div class="col-lg-6">
												<select data-live-search="true" class="selectpicker" 
													name="diseases" multiple required>
													<c:forEach items="${diseases}" var="disease">
														<option value="${disease.diseaseId}">${disease.targetofsurvey.name}</option>
													</c:forEach>
												</select>
											</div>
										</div>
										<div class="form-validation">
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="val-username">จังหวัด<span
													class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<select name="province" id="province"
														class="form-control" required>
														<option value=""></option>
														<c:forEach items="${provinces}" var="province">
															
															<option value="${province.provinceId}">${province.name}</option>
														</c:forEach>
													</select>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="district">อำเภอ<span
													class="text-danger">*</span>
												</label>
												<div class="col-lg-6">
													<select name="district.districtId"
														id="district" class="form-control" required>
														<option value=""></option>

													</select>
												</div>
											</div>
											<div class="form-group row">
												<label class="col-lg-4 col-form-label" for="subdistrict">ตำบล<span
													class="text-danger">*</span>
												</label>
												<div class="col-lg-6">

													<div class="subdisrict_selectpicker">
													
														<select data-live-search="true" class="selectpicker" name="subdistrict" id="subdistricts"
														 multiple required>
														 
														<option value=""></option>

													</select>
													
													</div>
													
												</div>
											</div>


										</div>
										<div class="form-group row">
											<label class="col-lg-4 col-form-label" for="val-suggestions">ระดับความรุนแรง<span
												class="text-danger">*</span>
											</label>
											<div class="form-group"
												style="margin-top: 10px; margin-left: 15px;">
												<div class="css-control css-control-primary css-checkbox">
													<input type="radio" id="level" name="level"
														value="มาก"><label style="margin-left: 10px;">มาก</label>
													&nbsp;&nbsp;&nbsp; <input type="radio" id="level"
														name="level" value="ปานกลาง"><label
														style="margin-left: 10px;">ปานกลาง</label>
													&nbsp;&nbsp;&nbsp; <input type="radio" id="level"
														name="level" value="น้อย"><label
														style="margin-left: 10px;">น้อย</label>
												</div>
											</div>
										</div>
										<div class="form-group row">
											<div class="col-lg-8 ml-auto">
												<input type="submit" id='submit' class="btn btn-primary"
													role="button" value="ส่งแจ้งเตือน">
											</div>
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

<script>
	$(document).ready(function() {
		
		  $('.selectpicker').selectpicker();
		  $('.selectpicker').on('changed.bs.select', function(e, clickedIndex, isSelected, previousValue) {
			var id = this.id;
			console.log("12345");
			if(id ==='subdistricts'){
				console.log("sub");
				if(id==='subdistricts'){
					var subdistricts = []; // Create an empty JavaScript array to hold the naturalEnemyIds
				  <c:forEach items="${subDistricts}" var="subdistrict">
				  subdistricts.push('${subdistrict.subdistrictId}'); // Add naturalEnemyId value to the array
				  </c:forEach>
				
				if (clickedIndex === 0 && isSelected) {
				      $(this).selectpicker('selectAll');
				}else if (clickedIndex === 0 && !isSelected) {
					  $(this).selectpicker('deselectAll');
				}else if (previousValue[0] === 'เลือกทั้งหมด' && clickedIndex > 0 && !isSelected){
			    	$(this).find('option:eq(0)').prop('selected', false);
			    	
			    }else if (previousValue.length+1 === subdistricts.length && isSelected){
			    	$(this).find('option:eq(0)').prop('selected', true);
			    } 
				}
				
			}
			else{
				console.log("7654");
			}
			$(this).selectpicker('refresh');
		  });
		});
	const selectAll = document.querySelector("#select-all");
    const items = document.querySelectorAll(".form-check-input");
    
    selectAll.addEventListener("click", () => {
      items.forEach(item => {
        item.checked = selectAll.checked;
      });
    });

    items.forEach(item => {
      item.addEventListener("click", () => {
        const allChecked = document.querySelectorAll(".form-check-input:checked").length === items.length;
        selectAll.checked = allChecked;
      });
    });
	</script>
	<script>
		$(function() {

			var isChange = false;

			$("#province")
					.change(
							function() {
								//alert( "Handler for .change() called." );
								var selectedProvice = $(
										"#province option:selected").val();
								//alert(selectedProvice);
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
												
												var selectedDistrict = $(
														"#district option:selected")
														.val();
												$
														.ajax({
															method : "GET",
															url : "${pageContext.request.contextPath}/district/selectpicker/"
																	+ selectedDistrict,
															success : function(
																	response) {
																
														
																//$('#subdistrict').html(response);
																var $select = $('<div />').append(response);
																
																 $select.find('select').selectpicker();
																  
																  // Append document fragment to your DOM
																 $('.subdisrict_selectpicker').empty();
																 $('.subdisrict_selectpicker').append($select);
															},

														});
											},

										});

							});

			$("#district")
					.change(
							function() {
								///alert("Handler for .change() called.");
								var selectedDistrict = $(
										"#district option:selected").val();
								$
										.ajax({
											method : "GET",
											url : "${pageContext.request.contextPath}/district/selectpicker/"
													+ selectedDistrict,
											success : function(response) {
												
												var $select = $('<div />').append(response);
												
												 $select.find('select').selectpicker();
												  
												  // Append document fragment to your DOM
												 $('.subdisrict_selectpicker').empty();
												 $('.subdisrict_selectpicker').append($select);
												
											},

										});

							});
			/*$("#subdistrict")
			 .change(
					function() {
						///alert("Handler for .change() called.");
						var selectedSubdistrict = $(
								"#subdistrict option:selected").val();
						$
								.ajax({
									method : "GET",
									url : "${pageContext.request.contextPath}/subdistrict/"
											+ selectedSubdistrict,
									success : function(response) {
										
									},

								});

					});
 				*/
		});
	</script>
	
	<script>
	var subdistrictidFrom = '${subdistrict.subdistrictId}';
	  selectOptionsFromServerData(subdistrictidFrom, "subdistrict")
	  $('.selectpicker').selectpicker('refresh');
	  $(document).ready(function() {
		  $('.selectpicker').selectpicker();
		  $('.selectpicker').on('changed.bs.select', function(e, clickedIndex, isSelected, previousValue) {
			var id = this.id;
			if(id==='subdistrict'){
				var subdistrictIds = []; // Create an empty JavaScript array to hold the naturalEnemyIds
				  <c:forEach items="${subdistrict.subdistrictId}" var="subdistrict">
				  provinceIds.push('${subdistrict.subdistrictId}'); // Add naturalEnemyId value to the array
				  </c:forEach>
				if (clickedIndex === 0 && isSelected) {
				      $(this).selectpicker('selectAll');
				      $(this).find('option:eq(0)').prop('selected', false);
				}else if (clickedIndex === 0 && !isSelected) {
					  $(this).selectpicker('deselectAll');
				}else if (previousValue[0] === 'เลือกทั้งหมด' && clickedIndex > 0 && !isSelected){
			    	$(this).find('option:eq(0)').prop('selected', false);
			    }else if (previousValue.length+1 === provinceIds.length && isSelected){
			    	
			    } 
			}else {
				
				 if (clickedIndex === 1 && isSelected) {
					
					 $(this).selectpicker('selectAll');
				     $(this).find('option:eq(0)').prop('selected', false);
				     $(this).find('option:eq(1)').prop('selected', false);
				    } else if (clickedIndex === 1 && !isSelected) {
				    	
				      $(this).selectpicker('deselectAll');
				      $(this).find('option:eq(0)').prop('selected', true);
				    } else if (previousValue[0] === 'เลือกทั้งหมด' && clickedIndex > 1 && !isSelected){
				    	
				    	$(this).find('option:eq(1)').prop('selected', false);
				    }
				    if (!isSelected && previousValue.length === 1) {
				    	
				    	 $(this).find('option:eq(0)').prop('selected', true);
				    }
				    if(clickedIndex === 0 && isSelected){
				    	
						$(this).selectpicker('deselectAll');
						  $(this).find('option:eq(0)').prop('selected', true);
					}else if (isSelected && previousValue.length >= 1) {
				    	$(this).find('option:eq(0)').prop('selected', false);
					}
				    
			}
			$(this).selectpicker('refresh');
		  });
		  
				
	});
	  
	</script>

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


