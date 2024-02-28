<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<div class="row" id="imglist" >

	<!--	<input type="text"  id="imgSurveyTargetPoint" 
								name="imgSurveyTargetPoint" 
								value="${DTOS.imgSurveyTargetPoint}">
								 -->
	<input type="hidden" id="i" name="i" value="${id}">


	<!--	<input type="text" class="form-control" id="SelectedImgSurveyTargetPointDTO.rolecheck"
								name="SelectedImgSurveyTargetPointDTO.rolecheck" placeholder=""
								value="${checkList}">
								 -->
	<c:forEach items="${img}" var="img" varStatus="loop">
		<div class="col-2" >
			<div class="card">
				<div class="card-body" style="padding: 5px">
					<div class="text-center">
						<p class="text-muted">
						<div class="form-check mb-1">
							<label class="form-check-label"> 
						<input type="checkbox" class="check" name="check[${loop.index}]" id="check"> 
							<input
								type="hidden" name="imgSurveyTargetPoint[${loop.index}]"
								value="${img.imgSurveyTargetPointId}"></label>



						</div>
						</p>
						<img onclick="openModal();currentSlide(${loop.count})" alt="Hello"
							class=" thumbnail  mt-2" width="150px" height="150px" 
							src="data:image/jpeg;base64,${dtoList[loop.index].image}">


						<p class="text-muted">
						<h3>
						<c:choose>
						    <c:when test="${statusDate}">
						       
						      
								
								<!--<form action="${pageContext.request.contextPath}/approve/status/date/${id}/${img.imgSurveyTargetPointId}/page/${page}/value/${value}">
										<input type="hidden"  name="dateInfoStart"
												value="${formatter.format(dateInfoStart)}" >
										<input type="hidden"  name="dateInfoEnd"
												value="${formatter.format(dateInfoEnd)}" >
										 <i  type="submit" class="v" aria-hidden="true" id = "approved"></i>
										 <input type="submit" class="fa fa-check text-green" aria-hidden="true" id = "approved">-->
										 <button type="button" class="none"  onclick="editDetailDate('Approved',${img.imgSurveyTargetPointId})">
										    <i class="fa fa-check text-green" ></i>
										</button>
										 <button type="button" class="none"  onclick="editDetailDate('Reject',${img.imgSurveyTargetPointId})" >
										    <i class="fa fa-times text-warning"  ></i>
										</button>
										<button type="button" class="none"  onclick="editDetailDate('Delete',${img.imgSurveyTargetPointId})" >
										    <i class="fa fa-trash text-red"  ></i>
										</button>
										
							
						        
						    </c:when>    
						    <c:otherwise>
						    
						   
						    	
							<button type="button" class="none"  onclick="editDetail('Approved',${img.imgSurveyTargetPointId})">
								<i class="fa fa-check text-green" aria-hidden="true" id = "approved"></i>
							</button>
							<button type="button" class="none" onclick="editDetail('Reject',${img.imgSurveyTargetPointId})" >
								<i class="fa fa-times text-warning" aria-hidden="true" id = "reject"></i>
							</button>
							
							<button type="button" class="none"  onclick="editDetail('Delete',${img.imgSurveyTargetPointId})" >
								<i class="fa fa-trash text-danger" aria-hidden="true" id = "delete"></i>
							</button>
							
						    
						    </c:otherwise>
						</c:choose>

						
						
							 
						</h3>
						</p>


					</div>
				</div>
			</div>
		</div>
		<!-- The Modal -->
	</c:forEach>


	<div id="myModal" class="modal">
		<span class="exit" onclick="closeModal()">&times;</span>
		<div class="modal-content">


			<c:forEach items="${img}" var="img" varStatus="loop">

				<div class="mySlides" style="width: 800px;">

					<div class="ex1">
						<img src="data:image/jpeg;base64,${dtosOriginalSizeList[loop.index].image}"
							style="width: 100%">
					</div>

					<div class="numbertext">
						ชื่อโรค : ${img.surveytargetpoint.surveytarget.targetofsurvey.name} 
						
						<br>
						ชื่อการเพาะปลูก : ${img.surveytargetpoint.surveytarget.survey.planting.name}
						วันที่สำรวจ : ${formatterTH.format(img.surveytargetpoint.surveytarget.survey.date)}
						วันที่อัพโหลด : ${formatterTH.format(img.uploadDate)} 
						
						<br> 

						<c:choose>
							<c:when test="${img.surveytargetpoint.surveytarget.survey.userByImgPhotographer.username!=NULL}">
						         ผู้ถ่ายภาพ : ${img.surveytargetpoint.surveytarget.survey.userByImgPhotographer.firstName} ${img.surveytargetpoint.surveytarget.survey.userByImgPhotographer.lastName} 						       
							</c:when>
							<c:otherwise>
						    	ผู้ถ่ายภาพ : ${img.surveytargetpoint.surveytarget.survey.imgPhotographerOther} 
						        <br>
							</c:otherwise>
						</c:choose>
						<c:choose>
							<c:when test="${img.surveytargetpoint.surveytarget.survey.userByImgOwner.username!=NULL}">
						         เจ้าของภาพ : ${img.surveytargetpoint.surveytarget.survey.userByImgOwner.firstName} ${img.surveytargetpoint.surveytarget.survey.userByImgOwner.lastName} 						       
							</c:when>
							<c:otherwise>
						    	เจ้าของภาพ : ${img.surveytargetpoint.surveytarget.survey.imgOwnerOther} 
						        <br>
							</c:otherwise>
						</c:choose>
						

					</div>
				</div>



			</c:forEach>





		</div>
	</div>
	
<input type="hidden" id="sizenow" value="${sizenow}" />
<input type="hidden" id=firstItem value="${firstItem}" />
<input type="hidden" id="lastItem" value="${lastItem}" />
<input type="hidden" id="MaxSize" value="${MaxSize}" />

</div>
	
 <!--   <div class="col-lg-6" id="listItem">
		<span>รายการที่  ${firstitem} ถึง ${lastItem} จาก
			${MaxSize} รายการ</span>
	</div> -->	

