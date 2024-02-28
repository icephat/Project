package org.cassava.model;
// Generated Nov 30, 2022, 10:33:28 PM by Hibernate Tools 5.5.9.Final

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Surveytargetpoint generated by hbm2java
 */
public class SurveyTargetPoint implements java.io.Serializable {

	
	private Integer surveyTargetPointId;
	@JsonIgnore
	private SurveyTarget surveytarget;
	@NotNull (message = "ห้ามไม่มีค่า")
	private int pointNumber;
	@NotNull (message = "ห้ามไม่มีค่า")
	private int itemNumber;
	@NotNull (message = "ห้ามไม่มีค่า")
	private int value;
	@JsonIgnore
	private List<ImgSurveyTargetPoint> imgsurveytargetpoints = new ArrayList<ImgSurveyTargetPoint>();

	public SurveyTargetPoint() {
	}
	
	public void clone(SurveyTargetPoint otherSurveyTargetPoint) {
		this.pointNumber = otherSurveyTargetPoint.pointNumber;
		this.itemNumber = otherSurveyTargetPoint.itemNumber;
		this.value = otherSurveyTargetPoint.value;
	}

	public SurveyTargetPoint(SurveyTarget surveytarget, int pointNumber, int itemNumber, int value) {
		this.surveytarget = surveytarget;
		this.pointNumber = pointNumber;
		this.itemNumber = itemNumber;
		this.value = value;
	}

	public SurveyTargetPoint(SurveyTarget surveytarget, int pointNumber, int itemNumber, int value,
			List imgsurveytargetpoints) {
		this.surveytarget = surveytarget;
		this.pointNumber = pointNumber;
		this.itemNumber = itemNumber;
		this.value = value;
		this.imgsurveytargetpoints = imgsurveytargetpoints;
	}

	public Integer getSurveyTargetPointId() {
		return this.surveyTargetPointId;
	}

	public void setSurveyTargetPointId(Integer surveyTargetPointId) {
		this.surveyTargetPointId = surveyTargetPointId;
	}

	public SurveyTarget getSurveytarget() {
		return this.surveytarget;
	}

	public void setSurveytarget(SurveyTarget surveytarget) {
		this.surveytarget = surveytarget;
	}

	public int getPointNumber() {
		return this.pointNumber;
	}

	public void setPointNumber(int pointNumber) {
		this.pointNumber = pointNumber;
	}

	public int getItemNumber() {
		return this.itemNumber;
	}

	public void setItemNumber(int itemNumber) {
		this.itemNumber = itemNumber;
	}

	public int getValue() {
		return this.value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public List<ImgSurveyTargetPoint> getImgsurveytargetpoints() {
		return this.imgsurveytargetpoints;
	}

	public void setImgsurveytargetpoints(List imgsurveytargetpoints) {
		this.imgsurveytargetpoints = imgsurveytargetpoints;
	}

}