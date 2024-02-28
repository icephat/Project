package se.springboot.sharitytest.model.dto;

import java.sql.Date;
import java.util.List;

import se.springboot.sharitytest.model.Activity;
import se.springboot.sharitytest.model.ConditionActivity;
import se.springboot.sharitytest.model.TypeActivity;
import se.springboot.sharitytest.model.User;

public class ActivityDTO {

	private String nameActivity;
	private String detail;
	private String place;
	private int numberPeople;
	private Date dateOpen;
	private Date dateActivity;
	private List<ConditionActivity> conditionActivities;
	private List<TypeActivity> typeActivities;

	public List<TypeActivity> getTypeActivities() {
		return typeActivities;
	}

	public void setTypeActivities(List<TypeActivity> typeActivities) {
		this.typeActivities = typeActivities;
	}

	public List<ConditionActivity> getConditionActivities() {
		return conditionActivities;
	}

	public void setConditionActivities(List<ConditionActivity> conditionActivities) {
		this.conditionActivities = conditionActivities;
	}

	public String getNameActivity() {
		return nameActivity;
	}

	public void setNameActivity(String nameActivity) {
		this.nameActivity = nameActivity;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public Date getDateOpen() {
		return dateOpen;
	}

	public void setDateOpen(Date dateOpen) {
		this.dateOpen = dateOpen;
	}

	public int getNumberPeople() {
		return numberPeople;
	}

	public void setNumberPeople(int numberPeople) {
		this.numberPeople = numberPeople;
	}

	public Date getDateActivity() {
		return dateActivity;
	}

	public void setDateActivity(Date dateActivity) {
		this.dateActivity = dateActivity;
	}
}
