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
</head>
<body>
	<jsp:include page="../layouts/mainLayoutBodyHeader.jsp" />
	<div class="content-body">
		<div class="container-fluid">
			<div class="row justify-content-center">
				<div class="col-lg-12">
					<div class="card">
						<div class="card-body">
							<h4 class="card-title">ศัตรูพืช > ${pest.name} >สารป้องกันกำจัดศัตรูพืช > เพิ่มข้อมูล</h4>							
							<div class="form-validation">
								<form class="form-valide"
									action="${pageContext.request.contextPath }/pestcide/save"
									method="post" modelAttribute="pestcide">
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ชื่อสามัญ<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username"
												name="pesticideName" placeholder=""
												value=${pestcide.pesticideName }>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ชื่อสามัญ(ภาษาอังกฤษ)<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username"
												name="pesticideNameEng" placeholder=""
												value=${pestcide.pesticideNameEng }>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">%สารออกฤทธิ์
											และสูตร<span class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username"
												name="percentApi" placeholder=""
												value=${pestcide.percentApi }>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">กลุ่มกลไกการออกฤทธิ์<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username"
												name="pesticideMechanism" placeholder=""
												value=${pestcide.pesticideMechanism }>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">ระดับความเป็นพิษ<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username"
												name="toxicityLevel" placeholder=""
												value=${pestcide.toxicityLevel }>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">อัตราการใช้(กรัม/ลิตร)<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username"
												name="mixingRatio" placeholder=""
												value=${pestcide.mixingRatio }>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">คำแนะนำ<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<select name="instruction" id="instruction"
												class="form-control" " required>
												<option value="ฉีดพ่นเฉพาะจุด">ฉีดพ่นเฉพาะจุด</option>
												<option value="แช่ท่อนพันธุ์">แช่ท่อนพันธุ์</option>
											</select>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">รายละเอียดคำแนะนำ<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username"
												name="instructionDetail" placeholder=""
												value=${pestcide.instructionDetail }>
										</div>
									</div>
									<div class="form-group row">
										<label class="col-lg-4 col-form-label" for="val-username">สมุดจด<span
											class="text-danger">*</span>
										</label>
										<div class="col-lg-6">
											<input type="text" class="form-control" id="val-username"
												name="note" placeholder="" value=${pestcide.note }>
										</div>
									</div>
									<input type="hidden" name="pestManagementId"
										value="${pestcide.pestManagementId}"> <input
										type="hidden" name="pest.pestId"
										value="${pestcide.pest.pestId}">
									<div class="form-group row">
										<div class="col-lg-8 ml-auto">
											<a
												href="${pageContext.request.contextPath}/pest/edit/${pestcide.pest.pestId}"
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

	<jsp:include page="../layouts/mainLayoutBodyFooter.jsp" />
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/jquery.dataTables.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable/dataTables.bootstrap4.min.js"></script>
	<script
		src="${pageContext.request.contextPath }/resources/plugins/tables/js/datatable-init/datatable-basic.min.js"></script>
</body>
</html>