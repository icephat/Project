package org.cassava.model.dto;
import java.util.List;

import org.cassava.model.ImgSurveyTargetPoint;
import org.cassava.model.SurveyTargetPoint;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
public class SelectedImgSurveyTargetPointDTO {

		private List<Integer> imgSurveyTargetPoint;

		private List<Boolean> check;
		
		private int i;
		
		private Boolean status;
		
		public int getI() {
			return i;
		}

		public void setI(int i) {
			this.i = i;
		}

		public List<Integer> getImgSurveyTargetPoint() {
			return imgSurveyTargetPoint;
		}

		public void setImgSurveyTargetPoint(List<Integer> imgSurveyTargetPoint) {
			this.imgSurveyTargetPoint = imgSurveyTargetPoint;
		}

		public List<Boolean> getCheck() {
			return check;
		}

		public void setCheck(List<Boolean> check) {
			this.check = check;
		}

		public Boolean getStatus() {
			return status;
		}

		public void setStatus(Boolean status) {
			this.status = status;
		}




		

		
		

	}

	

	

