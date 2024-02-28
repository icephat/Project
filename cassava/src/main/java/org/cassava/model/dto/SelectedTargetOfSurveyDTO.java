package org.cassava.model.dto;

import org.cassava.model.SurveyTarget;
import org.cassava.model.TargetOfSurvey;

public class SelectedTargetOfSurveyDTO {
	private boolean checked;
	
	private TargetOfSurvey targetofsurvey;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public TargetOfSurvey getTargetofsurvey() {
		return targetofsurvey;
	}

	public void setTargetofsurvey(TargetOfSurvey targetofsurvey) {
		this.targetofsurvey = targetofsurvey;
	}




}
