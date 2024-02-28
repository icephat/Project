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
<link
	href="${pageContext.request.contextPath }/resources/plugins/tables/css/datatable/dataTables.bootstrap4.min.css"
	rel="stylesheet">

<link
	href="${pageContext.request.contextPath }/resources/plugins/bootstrap-material-datetimepicker/css/bootstrap-material-datetimepicker.css"
	rel="stylesheet">

</head>
<body>
	<jsp:include page="../layouts/mainLayoutBodyHeader.jsp" />


	<!--**********************************
	            Content body start
	***********************************-->
	<div class="content-body">
		<div class="container-fluid">
			<div class="row">
				<div class="col-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">ส่งออกการสำรวจ > ขอส่งออกการสำรวจ >
								เลือกการสำรวจ</h4>
							<p align="right">
								<a
									href="${pageContext.request.contextPath}/permissionexport/permissions"
									class="btn btn-light" role="button">กลับ</a>
							</p>
							<div class="card">
								<div class="card-body">
									<form
										action="${pageContext.request.contextPath }/permissionexport/permission/${permissionId}/search"
										method="post">
										<div class="form-group">
											<label for="message-text" class="col-form-label">ช่วงเวลาข้อมูล
												(วันที่เริ่ม - วันสิ้นสุด)</label>
											<div class="input-daterange input-group" id="date-range">
												<input type="date" class="form-control" name="dateStart"
													value="${datestartselect}" min="${datestart}" max="${dateend}"
													required> <span
													class="input-group-addon bg-info b-0 text-white">
													จนถึงวันที </span> <input type="date" class="form-control"
													name="dateEnd" value="${dateendselect}" min="${datestart}"
													max="${dateend}" required>
											</div>
										</div>
										<div class="row ">
											<div class="col-md-3">
												<label for="val-username" class="form-label">ชื่อแปลง</label>
												<input type="text" class="form-control" name="fieldname" value="${fieldname}">
											</div>
											<div class="col-md-3">
												<p>โรค</p>
												<select  data-live-search="true" name="disease" id="disease" class="form-control selectpicker"
													multiple required data-live-search="true">
													<option value="0">ไม่เลือก</option>
													<c:if test="${listdisease.size()!=0}">
														<option value="">เลือกทั้งหมด</option>
														<c:forEach items="${listdisease}" var="disease">
															<option value="${disease.targetOfSurveyId}">${disease.name}</option>
														</c:forEach>
													</c:if>
												</select>
											</div>
											<div class="col-md-3">
												<p>แมลงศัตรูพืช</p>
												<select name="pest" id="pest" class="form-control selectpicker" multiple
													required data-live-search="true">
													<option value="0">ไม่เลือก</option>
													<c:if test="${listpest.size()!=0}">
														<option value="">เลือกทั้งหมด</option>
														<c:forEach items="${listpest}" var="pest">
															<option value="${pest.targetOfSurveyId}">${pest.name}</option>
														</c:forEach>
													</c:if>
												</select>
											</div>
											<div class="col-md-3">
												<p>ศัตรูธรรมชาติ</p>
												<select name="naturalenemy" id="naturalenemy"
													class="form-control selectpicker" multiple required data-live-search="true">
													<option value="0">ไม่เลือก</option>
													<c:if test="${listnaturalenemy.size()!=0}">
														<option value="">เลือกทั้งหมด</option>
														<c:forEach items="${listnaturalenemy}" var="naturalenemy">
															<option value="${naturalenemy.targetOfSurveyId}">${naturalenemy.name}</option>
														</c:forEach>
													</c:if>
												</select>
											</div>
											<div class="col-md-3">
												<p>จังหวัด</p>
												<select name="province" id="province" class="form-control selectpicker"
													multiple required data-live-search="true">
													<option value="">เลือกทั้งหมด</option>
													<c:forEach items="${listprovince}" var="province">
														<option value="${province.provinceId}">${province.name}</option>
														<!--<input type="hidden" value="province" name="listprovince">-->
													</c:forEach>
												</select>
											</div>

										</div>
										<div class=" ">
											<div>
												<p align="center">
													<button type="submit" class="btn btn-info">ค้นหา</button>
												</p>

											</div>
										</div>

									</form>
								</div>
							</div>
							<div class="card">
								<div class="card-body">
									<label><input type="checkbox" id="select-all">
										เลือกทั้งหมด</label>
									<form
										action="${pageContext.request.contextPath }/permissionexport/requestSave"
										modelAttribute="requestdetail" method="post">
										<div class="table-responsive">
											<table
												class="table table-striped table-bordered zero-configuration">
												<thead>
													<tr>
														<th>เลือก</th>
														<th>วันที่สำรวจ</th>
														<th>ชื่อแปลง</th>
														<th>อำเภอ</th>
														<th>จังหวัด</th>
														<th>สิ่งสำรวจ</th>
														<th>ความรุนแรง</th>
														<th>%การพบ</th>
													</tr>
												</thead>
												<tbody>
													<c:forEach items="${listsurveytarget}" var="st">
														<tr>
															<td>
																<p align="center">
																	<input type="checkbox" class="form-check-input"
																		 value="${st.surveyTargetId}"  onclick="check(this)">
																</p>
															</td>
															<td>${formatter.format(st.survey.date)}</td>
															<td>${st.survey.planting.field.name}</td>
															<td>
																${st.survey.planting.field.subdistrict.district.name}</td>
															<td>
																${st.survey.planting.field.subdistrict.district.province.name}
															</td>
															<td>${st.targetofsurvey.name}<c:choose>
																	<c:when test="${st.targetofsurvey.disease != null}">
																		<span class="badge badge-info">โรค</span>
																	</c:when>
																	<c:when
																		test="${st.targetofsurvey.pestphasesurvey != null}">
																		<span class="badge badge-success">แมลงศัตรูพืช</span>
																	</c:when>
																	<c:when
																		test="${st.targetofsurvey.naturalenemy != null}">
																		<span class="badge badge-warning">ศัตรูธรรมชาติ</span>
																	</c:when>
																</c:choose>
															</td>
															<td>${st.avgDamageLevel}<c:choose>
																	<c:when test="${st.targetofsurvey.disease != null}">/5</c:when>
																	<c:when
																		test="${st.targetofsurvey.pestphasesurvey != null || st.targetofsurvey.naturalenemy != null}">/100</c:when>
																</c:choose>
															</td>
															<td>${st.percentDamage}%</td>
															
												
														</tr>
													</c:forEach>
												</tbody>
											</table>
											<div class="table-responsive">
												<p align="center">
												

													<button type="submit" class="btn mb-1 btn-primary">ขอส่งออกข้อมูล</button>

												</p>
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
	</div>
	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
	<script>
	function selectOptionsFromServerData(serverData, dropdownId) {
		console.log(serverData) ;
	    var dropdown = document.getElementById(dropdownId);
	    if (!serverData || (serverData.length === 3 && serverData[1] === "0")) {
	    	if(dropdownId=="disease"||dropdownId=="pest"||dropdownId=="naturalenemy"){
	    		$("#" + dropdownId).find('option:eq(0)').prop('selected', true);
	    	    }
	      
	    } else {
	      for (var i = 0; i < dropdown.options.length; i++) {
	        var optionValue = dropdown.options[i].value;
	        if (serverData.includes(optionValue)) {
	          dropdown.options[i].selected = true;
	        }
	      }
	      $("#" + dropdownId).find('option:eq(0)').prop('selected', false);
	      if(dropdownId=="disease"||dropdownId=="pest"||dropdownId=="naturalenemy"){
	    $("#" + dropdownId).find('option:eq(1)').prop('selected', false);
	    }
	    }
	   
	  }
	  
	 var diseaseidFromServer = '${diseaseid}';
	  selectOptionsFromServerData(diseaseidFromServer, "disease");

	  var pestidFromServer = '${pestid}';
	  selectOptionsFromServerData(pestidFromServer, "pest");
	  
	  var naturalenemyidFromServer = '${naturalenemyid}';
	  selectOptionsFromServerData(naturalenemyidFromServer, "naturalenemy");
	  
	  var provinceidFromServer = '${provinceid}';
	  selectOptionsFromServerData(provinceidFromServer, "province")
	  
	  
	  $('.selectpicker').selectpicker('refresh');
	$(document).ready(function() {
		  $('.selectpicker').selectpicker();
		  $('.selectpicker').on('changed.bs.select', function(e, clickedIndex, isSelected, previousValue) {
			var id = this.id;
			if(id==='province'){
				var provinceIds = []; // Create an empty JavaScript array to hold the naturalEnemyIds
				  <c:forEach items="${listprovince}" var="province">
				  provinceIds.push('${province.provinceId}'); // Add naturalEnemyId value to the array
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
	
	var selectedCheckboxes = [];
	var status  ; 
		
			  
	$("#select-all").click(function() {
		var all = [] ;
		 <c:forEach items="${listsurveytarget}" var="surveytarget">
		 all.push('${surveytarget.surveyTargetId}'); // Add naturalEnemyId value to the array
		  </c:forEach>
	
			        var checkboxes = $("table.table-bordered tbody input[type='checkbox']");
			        checkboxes.prop("checked", $(this).is(":checked"));
			        if ($(this).is(":checked")) {
			        	selectedCheckboxes = all ; 

			        } else {
			            
			        	selectedCheckboxes = []; 
			        }
			        
			    });
				
				$("form").submit(function() {
				    if (selectedCheckboxes.length > 0) {
				       
				        $("<input>")
				            .attr("type", "hidden")
				            .attr("name", "listsurveyid")
				            .val(selectedCheckboxes.join(","))
				            .appendTo($(this));
				    }
				});
				
				$("table.table-striped").on("draw.dt", function() {
					 var checkboxes = $("table.table-striped tbody input[type='checkbox']");
					  checkboxes.each(function() {
					    var value = $(this).val();
					    if (selectedCheckboxes.includes(value)) {
					      $(this).prop("checked", true);
					    }else{
					    	 $(this).prop("checked", false);
					    }
					  });
					});

				
			
	function check(checkbox) {
	    var value = checkbox.value;
		var all = [] ;
			 <c:forEach items="${listsurveytarget}" var="surveytarget">
			 all.push('${surveytarget.surveyTargetId}'); // Add naturalEnemyId value to the array
			  </c:forEach>
	

	    if (checkbox.checked) {
	    	
	        
	        selectedCheckboxes.push(value);
	        if(all.length === selectedCheckboxes.length){
	        	$("#select-all").prop("checked", true);
	        	status = false ;
	        }
	    } else {
	      
	        var index = selectedCheckboxes.indexOf(value);
	        if (index !== -1) {
	            selectedCheckboxes.splice(index, 1);
	        }

	        if(all.length-1 === selectedCheckboxes.length){
	        	 $("#select-all").prop("checked", false);
	        	 status = true ;
	        }
	    }
	}
		
	

	</script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>

</body>
</html>