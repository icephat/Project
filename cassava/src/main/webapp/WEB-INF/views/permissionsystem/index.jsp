<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<jsp:include page="../layouts/mainLayoutHead.jsp" />
<!-- Custom Stylesheet -->
<jsp:useBean id="now" class="java.util.Date" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">
<link
	href="${pageContext.request.contextPath }/resources/plugins/tables/css/datatable/dataTables.bootstrap4.min.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath }/resources/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
	rel="stylesheet">
<!-- Page plugins css -->
<link
	href="${pageContext.request.contextPath }/resources/plugins/clockpicker/dist/jquery-clockpicker.min.css"
	rel="stylesheet">
<!-- Color picker plugins css 
    <link href="./plugins/jquery-asColorPicker-master/css/asColorPicker.css" rel="stylesheet">-->
<!-- Date picker plugins css -->
<link
	href="${pageContext.request.contextPath }/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.css"
	rel="stylesheet">
<!-- Daterange picker plugins css -->
<link
	href="${pageContext.request.contextPath }/resources/plugins/timepicker/bootstrap-timepicker.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.css"
	rel="stylesheet">

<script type="text/javascript" src="multiple-dropdown.js"></script>
<style type="text/css">
.inputerror {
	border: 0;
	color: red;
	background-color:transparent;
	width: 300px;
}

.inputerror2 {
	border: 0;
	color: red;
	background-color:transparent;
	width: 500px;
	margin: auto;
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
							<h4 class="card-title">ขอสิทธิ์ส่งออกการสำรวจของระบบ</h4>

							<div class="form-validation">
								<form class="form-valide" id="permissionDTOData" method="post"
									modelAttribute="permissionDTO" enctype="multipart/form-data">
									<div class="form-validation">
										<div class="form-group row">
											<div class="col-lg-4">
												<div class="form-group row">
													<label class="col-lg-3 col-form-label" for="val-username">เลือกหน่วยงาน<span
														class="text-danger">*</span>
													</label>
													<div class="col-lg-9">
														<select data-live-search="true" class="selectpicker"
															name="organizations" id="organizations" multiple
															required="required">
															<option value="0">เลือกทั้งหมด</option>
															<c:forEach items="${organizations}" var="organization">
																<option value="${organization.organizationId}">${organization.name}</option>
															</c:forEach>
														</select>
														<input class="inputerror" id="organizationsCheck" cssClass="error" disabled />
													</div>
												</div>
											</div>
										</div>

										<div class="form-group row">
											<div class="col-lg-4">
												<div class="form-group row">
													<label class="col-lg-3 col-form-label" for="val-username">เลือกโรค<span
														class="text-danger">*</span>
													</label>
													<div class="col-lg-9">
														<select data-live-search="true" class="selectpicker"
															name="diseases" id="diseases" multiple
															required="required">
															<option value="0" selected>ไม่เลือก</option>
															<option value="-1">เลือกทั้งหมด</option>
															<c:forEach items="${diseases}" var="disease">
																<option value="${disease.diseaseId}">${disease.targetofsurvey.name}</option>
															</c:forEach>
														</select>
													</div>

												</div>
											</div>
											<div class="col-lg-4">
												<div class="form-group row">
													<label class="col-lg-3 col-form-label" for="val-username">เลือกศัตรูพืช<span
														class="text-danger">*</span>
													</label>
													<div class="col-lg-9">
														<select data-live-search="true" class="selectpicker"
															name="pests" id="pests" multiple required="required">
															<option value="0" selected>ไม่เลือก</option>
															<option value="-1">เลือกทั้งหมด</option>
															<c:forEach items="${pestPhaseSurveys}"
																var="pestPhaseSurvey">
																<option value="${pestPhaseSurvey.pestPhaseSurveyId}">${pestPhaseSurvey.targetofsurvey.name}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<div class="col-lg-4">
												<div class="form-group row">
													<label class="col-lg-3 col-form-label" for="val-username">เลือกศัตรูธรรมชาติ<span
														class="text-danger">*</span>
													</label>
													<div class="col-lg-9">
														<select data-live-search="true" class="selectpicker"
															name="naturalEnemies" id="naturalEnemies" multiple
															required="required">
															<option value="0" selected>ไม่เลือก</option>
															<option value="-1">เลือกทั้งหมด</option>
															<c:forEach items="${naturalEnemies}" var="naturalEnemy">
																<option value="${naturalEnemy.naturalEnemyId}">${naturalEnemy.targetofsurvey.name}</option>
															</c:forEach>
														</select>

													</div>
												</div>
											</div>
											<input class="inputerror2" id="All" cssClass="error" disabled />
										</div>
										<div class="form-group row">
											<div class="col-lg-4">
												<div class="form-group row">
													<label class="col-lg-3 col-form-label" for="val-username">เลือกจังหวัด<span
														class="text-danger">*</span>
													</label>
													<div class="col-lg-9">
														<select data-live-search="true" class="selectpicker"
															id="provinces" name="provinces" multiple
															required="required">
															<option value="selectAll">เลือกทั้งหมด</option>
															<c:forEach items="${provinces}" var="province">
																<option value="${province.name}">${province.name}</option>

															</c:forEach>
														</select> <input class="inputerror" id="provincesCheck"
															cssClass="error" disabled />
													</div>
												</div>
											</div>
											<div class="col-lg-4">
												<div class="form-group row">
													<label for="message-text" class="col-lg-3 col-form-label">ช่วงเวลาสำรวจข้อมูล<span
														class="text-danger">*</span></label>
													<div class="col-lg-9">
														<div class="input-daterange input-group" id="date-range">
															<input type="text" class="form-control"
																id="dateInfoStart" name="dateInfoStart"
																required="required"> <span
																class="input-group-addon bg-primary b-0 text-white">
																จนถึงวันที </span> <input type="text" class="form-control"
																id="dateInfoEnd" name="dateInfoEnd" required="required">

														</div>
														<input class="inputerror" id="date" cssClass="error"
															disabled />
													</div>
												</div>
											</div>
											<div class="col-lg-4">
												<div class="form-group row">
													<label class="col-lg-3 col-form-label" for="val-username">หลักฐานประกอบคำขอ<span
														class="text-danger">*</span>
													</label>
													<div class="col-lg-9">
														<input type="file" class="file" name="files" multiple
															id="FileCheck"> <input class="inputerror"
															id="File" cssClass="error" disabled />
													</div>
												</div>
											</div>
										</div>
										<div class="form-group row">
											<div class="col-lg-8 ml-auto">
												<input type="submit" class="btn btn-primary" role="button"
													value="ขอสิทธิ์ในการส่งออกข้อมูล" id="button">
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>

					<div class="card">
						<div class="card-body">
							<h4 class="card-title">รายการสิทธิ์ส่งออกการสำรวจของระบบ</h4>

							<div class="table-responsive">
								<table
									class="table table-striped table-bordered zero-configuration">
									<thead>
										<tr>
											<th>ข้อมูลช่วงวันที่</th>
											<th>หน่วยงาน</th>
											<th>จังหวัด</th>
											<th>สิ่งสำรวจ</th>
											<th>วันที่ส่งคำขอสิทธิ์</th>
											<th>สถานะ</th>
											<th>ช่วงวันที่ใช้สิทธิ์</th>

										</tr>
									</thead>
									<tbody>
										<c:forEach items="${permissionList}" var="permission"
											varStatus="loop1">
											<tr>
												<td>${formatter.format(permission.dateInfoStart)}-
													${formatter.format(permission.dateInfoEnd)}</td>

												<td><c:forEach
														items="${permission.permissionOrganizations}" var="perorganization"
														varStatus="loop2">
														<option>${perorganization.organization.name}</option>
													</c:forEach></td>
												<td><c:forEach
														items="${permission.permissionprovinces}" var="provinces"
														varStatus="loop2">
														<option>${provinces.province.name}</option>
													</c:forEach></td>

												<td><c:forEach items="${targetOfSurveys[loop1.index]}"
														var="targetOfSurvey" varStatus="loop">
														<option>${targetOfSurvey[0]}
															(${targetOfSurvey[1]})</option>
													</c:forEach></td>

												<td>${formatter.format(permission.dateRequest)}</td>

												<td><c:if test="${permission.status.equals('Waiting')}">
														<span class="badge badge-warning">รออนุมัติ</span>
													</c:if> <c:if test="${permission.status.equals('Reject')}">
														<span class="badge badge-danger">ไม่อนุมัติ</span>
													</c:if> <c:if test="${permission.status.equals('Approve')}">
														<span class="badge badge-success">อนุมัติ</span>
													</c:if></td>

												<td><c:if test="${permission.status.equals('Waiting')}">
														<a class="btn btn-danger " role="button"
															href="${pageContext.request.contextPath}/permissionsystem/request/delete/${permission.permisisonId}"
															onclick="return confirm('คุณยืนยันที่จะลบคำขอหรือไม่');">
															<i class="fa fa-trash" aria-hidden="true"></i>
														</a>
													</c:if> <c:if test="${permiss.permission.status.equals('Reject')}">
														<a class="btn btn-danger " role="button"
															href="${pageContext.request.contextPath}/permissionsystem/request/delete/${permission.permisisonId}"
															onclick="return confirm('คุณยืนยันที่จะลบคำขอหรือไม่');">
															<i class="fa fa-trash" aria-hidden="true"></i>
														</a>
													</c:if> <c:if test="${permission.status.equals('Approve')}">
														<c:if test="${now > permission.dateExpire}">
															<span class="badge badge-danger">
																${formatter.format(permission.dateApprove)}
																-${formatter.format(permission.dateExpire)}</span>
														</c:if>

														<c:if test="${now <= permission.dateExpire}">
															<span class="badge badge-success">
																${formatter.format(permission.dateApprove)}
																-${formatter.format(permission.dateExpire)}</span>
														</c:if>
													</c:if></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>

						</div>
					</div>






				</div>
			</div>


		</div>
	</div>



	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />


	<!-- Select All -->
	<script>
		$(function () {
		
			$("#button").click(
					
				function () {
					$('#date').val('');
					$('#provincesCheck').val('');
					$('#All').val('');
					$('#File').val('');
					$('#organizationsCheck').val('');
					//alert("check");
					let y = new Date(document.getElementById("dateInfoStart").value);
					let z = new Date(document.getElementById("dateInfoEnd").value);
					let FileCheck = document.getElementById("FileCheck").value;
					
					function dateIsValid(date) {
						return date instanceof Date && !isNaN(date);
					}

					
					function isInt(value) {
						  return !isNaN(value) && (function(x) { return (x | 0) === x; })(parseFloat(value))
						}
					
					if (!(dateIsValid(y) && dateIsValid(z))) {
						
						if ( !(dateIsValid(y) && dateIsValid(z))) {
							$('#date').val('กรุณากรอกข้อมูลวันที่ให้ครบ');
						} 
						
					}
					var formData = new FormData($('#permissionDTOData')[0]);
					
					
				var naturalEnemies = document.getElementById("naturalEnemies").value;
				var pests = document.getElementById("pests").value;
				var diseases = document.getElementById("diseases").value;
				var provinces = document.getElementById("provinces").value;
				var organizations = document.getElementById("organizations").value;
				//alert(provinces);
				if(naturalEnemies === "0" && pests === "0" && diseases === "0" ){
					$('#All').val('กรุณาเลือก โรค หรือ ศัตรูพืช หรือ ศัตรูธรรมชาติ อย่างน้อย 1 อย่าง');
					
				}
				if(organizations == ""){
					
					$('#organizationsCheck').val('กรุณาเลือกองค์กรอย่างน้อย 1 หน่วยงาน');
					
				}
				if(provinces == ""){
					
					$('#provincesCheck').val('กรุณาเลือกจังหวัดอย่างน้อย 1 จังหวัด');
					
				}
				if(FileCheck == ""){
					
					$('#File').val('กรุณาเลือกไฟล์');
					
				}
				if (FileCheck == ""||provinces == ""||(!(dateIsValid(y) && dateIsValid(z)))||(naturalEnemies === "0" && pests === "0" && diseases === "0" )) {
					return false;
				}
					$.ajax({
						url: "${pageContext.request.contextPath}/permissionsystem/create",
						type: 'POST',
						data: formData,
						dataType: 'json',
						success: function (response) {
							window.location.href = "${pageContext.request.contextPath}/permissionsystem/index";
						},
						error: function (response) {
							//alert(response["responseText"]);
							const respJSON = response["responseText"];
							
							const myObj = JSON.parse(respJSON);
							for (var key in myObj.body) {
								
								$('#' + key).val(myObj.body[key]);
							}
							return false;
						},
						cache: false,
						contentType: false,
						processData: false
					});
					return false;
				});
		});
	</script>
	<script>
	$(document).ready(function() {
		  $('.selectpicker').selectpicker();
		  $('.selectpicker').on('changed.bs.select', function(e, clickedIndex, isSelected, previousValue) {
			var id = this.id;
			if(id==='provinces'||id==='organizations'){
				
				if(id==='provinces'){
					var provinces = []; // Create an empty JavaScript array to hold the naturalEnemyIds
				  <c:forEach items="${provinces}" var="province">
				  provinces.push('${province.provinceId}'); // Add naturalEnemyId value to the array
				  </c:forEach>
				if (clickedIndex === 0 && isSelected) {
				      $(this).selectpicker('selectAll');
				}else if (clickedIndex === 0 && !isSelected) {
					  $(this).selectpicker('deselectAll');
				}else if (previousValue[0] === 'เลือกทั้งหมด' && clickedIndex > 0 && !isSelected){
			    	$(this).find('option:eq(0)').prop('selected', false);
			    	
			    }else if (previousValue.length+1 === provinces.length && isSelected){
			    	$(this).find('option:eq(0)').prop('selected', true);
			    } 
				}
				else{
					var organizations = []; // Create an empty JavaScript array to hold the naturalEnemyIds
					  <c:forEach items="${organizations}" var="organization">
					  organizations.push('${organization.organizationId}'); // Add naturalEnemyId value to the array
					  console.log(organizations)
					  </c:forEach>;
					if (clickedIndex === 0 && isSelected) {
					      $(this).selectpicker('selectAll');
					      console.log(organizations)
					}else if (clickedIndex === 0 && !isSelected) {
						  $(this).selectpicker('deselectAll');
						  console.log('1')
						  console.log(organizations)
					}else if (previousValue[0] === 'เลือกทั้งหมด' && clickedIndex > 0 && !isSelected){
				    	$(this).find('option:eq(0)').prop('selected', false);
				    	console.log('2')
				    	console.log(organizations)
				    	
				    }else if (previousValue.length+1 === organizations.length && isSelected){
				    	$(this).find('option:eq(0)').prop('selected', true);
				    	console.log('3')
				    	console.log(organizations)
				    } 
				}
				
			}
			else {
				

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
	function myFunction() {
		var naturalEnemies = document.getElementById("naturalEnemies").value;
		var pests = document.getElementById("pests").value;
		var diseases = document.getElementById("diseases").value;
		
		
		if(naturalEnemies === "0" && pests === "0" && diseases === "0" ){
			alert("กรุณาเลือก โรค หรือ ศัตรูพืช หรือ ศัตรูธรรมชาติ อย่างน้อย 1 อย่าง");
			return false;
		}
	}
	</script>






	<!-- END -->


	<script
		src="${pageContext.request.contextPath}/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>

	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/moment/moment.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-material-datetimepicker/js/bootstrap-material-datetimepicker.js"></script>
	<!-- Clock Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/clockpicker/dist/jquery-clockpicker.min.js"></script>
	<!-- Color Picker Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/jquery-asColorPicker-master/libs/jquery-asColor.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/jquery-asColorPicker-master/libs/jquery-asGradient.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/jquery-asColorPicker-master/dist/jquery-asColorPicker.min.js"></script>
	<!-- Date Picker Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-datepicker/bootstrap-datepicker.min.js'"></script>
	<!-- Date range Plugin JavaScript -->
	<script
		src="${pageContext.request.contextPath }/resources/plugins/timepicker/bootstrap-timepicker.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/bootstrap-daterangepicker/daterangepicker.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/js/plugins-init/form-pickers-init.js"></script>

</body>
</html>