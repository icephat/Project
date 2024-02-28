<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<jsp:include page="../layouts/mainLayoutHead.jsp" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/resources/css/style.css">
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
	<div class="content-body">
		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">							
							<h4 class="card-title">ศัตรูพืช > ${pest.name} > สารป้องกันกำจัดศัตรูพืช > เพิ่มข้อมูล</h4>
							<div class="form-validation">
								<form class="form-valide"  id="pestCideData"
									 modelAttribute="pestcide">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ชื่อสามัญ<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="pesticideName" placeholder="">
											<input class="inputerror" id="pesticideName" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ชื่อสามัญ(ภาษาอังกฤษ)<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="pesticideNameEng" placeholder="">
											<input class="inputerror" id="pesticideNameEng" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">%สารออกฤทธิ์
											และสูตร
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="percentApi" placeholder="">
											<input class="inputerror" id="percentApi" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">กลุ่มกลไกการออกฤทธิ์<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="pesticideMechanism" placeholder="">
											<input class="inputerror" id="pesticideMechanism" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ระดับความเป็นพิษ<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="toxicityLevel" placeholder="">
											<input class="inputerror" id="toxicityLevel" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">อัตราการใช้(กรัม/ลิตร)<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="mixingRatio" placeholder="">
											<input class="inputerror" id="mixingRatio" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">คำแนะนำ<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<select name="instruction" 
												class="form-control" required>
												<option value=""></option>
												<option value="ฉีดพ่นเฉพาะจุด">ฉีดพ่นเฉพาะจุด</option>
												<option value="แช่ท่อนพันธุ์">แช่ท่อนพันธุ์</option>
											</select>
											<input class="inputerror" id="instruction" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">รายละเอียดคำแนะนำ
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="instructionDetail" placeholder="">
											<input class="inputerror" id="instructionDetail" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">หมายเหตุ
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" 
												name="note" placeholder="">
											<input class="inputerror" id="note" cssClass="error" disabled />
										</div>
									</div>
									<input type="hidden" name="pest.pestId" value="${pest.pestId}" id = "pestId">
									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a
												href="${pageContext.request.contextPath}/pestcide/pestmanagement/create/${pest.pestId}"
												class="btn btn-light" role="button">กลับ</a> <input
												type="submit" class="btn btn-primary" role="button" id="button"
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
	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
	<script>
		$(function() {

			var isChange = false;

			$("#button")
					.click(
							function() {
								//alert("script");
								var formData = new FormData($('#pestCideData')[0]);
								//$("#pathogentype option:selected").val();
								$("#pesticideName").val("");
								//$("#pathogentype").val("");
								$("#pesticideNameEng").val("");
								//$("#percentApi").val("");
								$("#pesticideMechanism").val("");
								$("#toxicityLevel").val("");
								$("#mixingRatio").val("");
								$("#instruction").val("");
								$("#instructionDetail").val("");
								$("#note").val("");
								//$("#scientificName").val("");
								var pestId = document.getElementById("pestId").value;
								
								//alert("script");
								$
										.ajax({
											url : "${pageContext.request.contextPath}/pestcide/pestmanagement/update",
											type : 'POST',
											data : formData,
											dataType : 'json',
											success : function(response) {
												
												 window.location.href="${pageContext.request.contextPath}/pestcide/pestmanagement/create/"+pestId;
												
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

	</script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
</body>
</html>