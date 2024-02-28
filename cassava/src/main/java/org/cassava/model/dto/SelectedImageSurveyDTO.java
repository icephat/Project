package org.cassava.model.dto;

public class SelectedImageSurveyDTO {
	private boolean checked;

	private String point;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public String getPoint() {
		return point;
	}

	public void setPoint(String point) {
		this.point = point;
	}
}
