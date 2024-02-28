<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
	<meta charset="UTF-8">
	<jsp:include page="../layouts/mainLayoutHead.jsp" />
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/bootstrap.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css">
	<link rel="stylesheet" href="${pageContext.request.contextPath }/resources/css/style.css">
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
							<h4 class="card-title">ศัตรูพืช > ${pestcide.pest.name} > แก้ไขสารป้องกันกำจัดศัตรูพืช</h4>
							<div class="form-validation">
								<form class="form-valide" id="Data" modelAttribute="pestcide">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ชื่อสามัญ<span
												class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username"
												name="pesticideName" placeholder="" value=${pestcide.pesticideName }>
											<input class="inputerror" id="pesticideName" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label"
											for="val-username">ชื่อสามัญ(ภาษาอังกฤษ)<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username"
												name="pesticideNameEng" placeholder="" value=${pestcide.pesticideNameEng}>
											<input class="inputerror" id="pesticideNameEng" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">%สารออกฤทธิ์
											และสูตร
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username" name="percentApi"
												placeholder="" value=${pestcide.percentApi }>
											<input class="inputerror" id="percentApi" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label"
											for="val-username">กลุ่มกลไกการออกฤทธิ์<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username"
												name="pesticideMechanism" placeholder=""
												value=${pestcide.pesticideMechanism }>
											<input class="inputerror" id="pesticideMechanism" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ระดับความเป็นพิษ<span
												class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username"
												name="toxicityLevel" placeholder="" value=${pestcide.toxicityLevel }>
											<input class="inputerror" id="toxicityLevel" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label"
											for="val-username">อัตราการใช้(กรัม/ลิตร)<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username" name="mixingRatio"
												placeholder="" value=${pestcide.mixingRatio }>
											<input class="inputerror" id="mixingRatio" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">คำแนะนำ<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<form:select path="pestcide.instruction" name="instruction"
												class="form-control">
												<form:options items="${pestcides}"></form:options>
											</form:select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">รายละเอียดคำแนะนำ
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username"
												name="instructionDetail" placeholder=""
												value=${pestcide.instructionDetail }>
											<input class="inputerror" id="instructionDetail" cssClass="error" disabled />
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">หมายเหตุ
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username" name="note"
												placeholder="" value=${pestcide.note }>
											<input class="inputerror" id="note" cssClass="error" disabled />
										</div>
									</div>
									<input type="hidden" name="pestManagementId" value="${pestcide.pestManagementId}">
									<input type="hidden" name="pest.pestId" value="${pestcide.pest.pestId}" id = "pestId">
									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a href="${pageContext.request.contextPath}/pest/edit/${pestcide.pest.pestId}"
												class="btn btn-light" role="button">กลับ</a> <input type="submit" id="button"
												class="btn btn-primary" role="button" value="บันทึก">
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
								//alert("scriptedit");
								var formData = new FormData($('#Data')[0]);
								//$("#pathogentype option:selected").val();
								$("#pesticideName").val("");
								//$("#pathogentype").val("");
								$("#pesticideNameEng").val("");
								$("#percentApi").val("");
								$("#pesticideMechanism").val("");
								$("#toxicityLevel").val("");
								$("#mixingRatio").val("");
								$("#instructionDetail").val("");
								$("#note").val("");
								//$("#scientificName").val("");
								var pestId = document.getElementById("pestId").value;
								
								//alert("scriptupdate");
								$
										.ajax({
											url : "${pageContext.request.contextPath}/pestcide/update",
											type : 'POST',
											data : formData,
											dataType : 'json',
											success : function(response) {
												//alert("dupsucc");
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
	<script src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js">
	</script>
	<script src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js">
	</script>
</body>

</html>