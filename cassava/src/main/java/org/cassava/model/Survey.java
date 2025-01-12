package org.cassava.model;
// Generated Nov 30, 2022, 10:33:28 PM by Hibernate Tools 5.5.9.Final

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Survey generated by hbm2java
 */
public class Survey implements java.io.Serializable {

	private Integer surveyId;
	@JsonIgnore
	private Planting planting;
	@JsonIgnore
	private User userByLastUpdateBy;
	@JsonIgnore
	private User userByCreateBy;
	@JsonIgnore
	private User userByImgOwner;
	@JsonIgnore
	private User userByImgPhotographer;
	@NotNull (message = "ห้ามไม่มีค่า")
	private Date date;
	private String besidePlant;
	private String weed;
	@NotNull (message = "ห้ามไม่มีค่า")
	private float temperature;
	@NotNull (message = "ห้ามไม่มีค่า")
	private float humidity;
	@NotNull (message = "ห้ามไม่มีค่า")
	private String rainType;
	@NotNull (message = "ห้ามไม่มีค่า")
	private String sunlightType;
	@NotNull (message = "ห้ามไม่มีค่า")
	private String dewType;
	@NotNull (message = "ห้ามไม่มีค่า")
	private String soilType;
	@NotNull (message = "ห้ามไม่มีค่า")
	private String percentDamageFromHerbicide;
	private String surveyPatternDisease;
	private String surveyPatternPest;
	private String surveyPatternNaturalEnemy;
	private int numPointSurvey;
	private int numPlantInPoint;
	private String imgOwnerOther;
	private String imgPhotographerOther;
	@NotNull (message = "ห้ามไม่มีค่า")
	private String note;
	private Date createDate;
	private Date lastUpdateDate;
	private String status;
	@JsonIgnore
	private List<ImgHerbicideDamage> imgherbicidedamages = new ArrayList<ImgHerbicideDamage>();
	@JsonIgnore
	private List<RequestDetail> requestdetails = new ArrayList<RequestDetail>();
	@JsonIgnore
	private List<SurveyTarget> surveytargets = new ArrayList<SurveyTarget>();
	@JsonIgnore
	private List<SurveyPoint> surveyPoints = new ArrayList<SurveyPoint>();
	
	public Survey() {
	}
	
	public void clone(Survey otherSurvey) {
		this.date = otherSurvey.date;
		this.besidePlant = otherSurvey.besidePlant;
		this.weed = otherSurvey.weed;
		this.temperature = otherSurvey.temperature;
		this.humidity = otherSurvey.humidity;
		this.rainType = otherSurvey.rainType;
		this.sunlightType = otherSurvey.sunlightType;
		this.dewType = otherSurvey.dewType;
		this.soilType = otherSurvey.soilType;
		this.percentDamageFromHerbicide = otherSurvey.percentDamageFromHerbicide;
		this.surveyPatternDisease = otherSurvey.surveyPatternDisease;
		this.surveyPatternPest = otherSurvey.surveyPatternPest;
		this.surveyPatternNaturalEnemy = otherSurvey.surveyPatternNaturalEnemy;
		this.numPointSurvey = otherSurvey.numPointSurvey;
		this.numPlantInPoint = otherSurvey.numPlantInPoint;
		this.imgOwnerOther = otherSurvey.imgOwnerOther;
		this.imgPhotographerOther = otherSurvey.imgPhotographerOther;
		this.note = otherSurvey.note;
		this.createDate = otherSurvey.createDate;
		this.lastUpdateDate = otherSurvey.lastUpdateDate;
	}
	
	public Survey(Planting planting, Date date, float temperature, float humidity, String rainType, String sunlightType,
			String dewType, String soilType, String surveyPatternDisease, String surveyPatternPest,
			String surveyPatternNaturalEnemy, int numPointSurvey, int numPlantInPoint, String note, Date createDate) {
		this.planting = planting;
		this.date = date;
		this.temperature = temperature;
		this.humidity = humidity;
		this.rainType = rainType;
		this.sunlightType = sunlightType;
		this.dewType = dewType;
		this.soilType = soilType;
		this.surveyPatternDisease = surveyPatternDisease;
		this.surveyPatternPest = surveyPatternPest;
		this.surveyPatternNaturalEnemy = surveyPatternNaturalEnemy;
		this.numPointSurvey = numPointSurvey;
		this.numPlantInPoint = numPlantInPoint;
		this.note = note;
		this.createDate = createDate;
	}

	public Survey(Planting planting, User userByLastUpdateBy, User userByCreateBy, User userByImgOwner,
			User userByImgPhotographer, Date date, String besidePlant, String weed, float temperature, float humidity,
			String rainType, String sunlightType, String dewType, String soilType, String percentDamageFromHerbicide,
			String surveyPatternDisease, String surveyPatternPest, String surveyPatternNaturalEnemy, int numPointSurvey,
			int numPlantInPoint, String imgOwnerOther, String imgPhotographerOther, String note, Date createDate,
			Date lastUpdateDate, String status, List imgherbicidedamages, List requestdetails, List surveytargets ,List surveyPoints) {
		this.planting = planting;
		this.userByLastUpdateBy = userByLastUpdateBy;
		this.userByCreateBy = userByCreateBy;
		this.userByImgOwner = userByImgOwner;
		this.userByImgPhotographer = userByImgPhotographer;
		this.date = date;
		this.besidePlant = besidePlant;
		this.weed = weed;
		this.temperature = temperature;
		this.humidity = humidity;
		this.rainType = rainType;
		this.sunlightType = sunlightType;
		this.dewType = dewType;
		this.soilType = soilType;
		this.percentDamageFromHerbicide = percentDamageFromHerbicide;
		this.surveyPatternDisease = surveyPatternDisease;
		this.surveyPatternPest = surveyPatternPest;
		this.surveyPatternNaturalEnemy = surveyPatternNaturalEnemy;
		this.numPointSurvey = numPointSurvey;
		this.numPlantInPoint = numPlantInPoint;
		this.imgOwnerOther = imgOwnerOther;
		this.imgPhotographerOther = imgPhotographerOther;
		this.note = note;
		this.createDate = createDate;
		this.lastUpdateDate = lastUpdateDate;
		this.status = status;
		this.imgherbicidedamages = imgherbicidedamages;
		this.requestdetails = requestdetails;
		this.surveytargets = surveytargets;
		this.surveyPoints = surveyPoints;
	}

	public Integer getSurveyId() {
		return this.surveyId;
	}

	public void setSurveyId(Integer surveyId) {
		this.surveyId = surveyId;
	}

	public Planting getPlanting() {
		return this.planting;
	}

	public void setPlanting(Planting planting) {
		this.planting = planting;
	}

	public User getUserByLastUpdateBy() {
		return this.userByLastUpdateBy;
	}

	public void setUserByLastUpdateBy(User userByLastUpdateBy) {
		this.userByLastUpdateBy = userByLastUpdateBy;
	}

	public User getUserByCreateBy() {
		return this.userByCreateBy;
	}

	public void setUserByCreateBy(User userByCreateBy) {
		this.userByCreateBy = userByCreateBy;
	}

	public User getUserByImgOwner() {
		return this.userByImgOwner;
	}

	public void setUserByImgOwner(User userByImgOwner) {
		this.userByImgOwner = userByImgOwner;
	}

	public User getUserByImgPhotographer() {
		return this.userByImgPhotographer;
	}

	public void setUserByImgPhotographer(User userByImgPhotographer) {
		this.userByImgPhotographer = userByImgPhotographer;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBesidePlant() {
		return this.besidePlant;
	}

	public void setBesidePlant(String besidePlant) {
		this.besidePlant = besidePlant;
	}

	public String getWeed() {
		return this.weed;
	}

	public void setWeed(String weed) {
		this.weed = weed;
	}

	public float getTemperature() {
		return this.temperature;
	}

	public void setTemperature(float temperature) {
		this.temperature = temperature;
	}

	public float getHumidity() {
		return this.humidity;
	}

	public void setHumidity(float humidity) {
		this.humidity = humidity;
	}

	public String getRainType() {
		return this.rainType;
	}

	public void setRainType(String rainType) {
		this.rainType = rainType;
	}

	public String getSunlightType() {
		return this.sunlightType;
	}

	public void setSunlightType(String sunlightType) {
		this.sunlightType = sunlightType;
	}

	public String getDewType() {
		return this.dewType;
	}

	public void setDewType(String dewType) {
		this.dewType = dewType;
	}

	public String getSoilType() {
		return this.soilType;
	}

	public void setSoilType(String soilType) {
		this.soilType = soilType;
	}

	public String getPercentDamageFromHerbicide() {
		return this.percentDamageFromHerbicide;
	}

	public void setPercentDamageFromHerbicide(String percentDamageFromHerbicide) {
		this.percentDamageFromHerbicide = percentDamageFromHerbicide;
	}

	public String getSurveyPatternDisease() {
		return this.surveyPatternDisease;
	}

	public void setSurveyPatternDisease(String surveyPatternDisease) {
		this.surveyPatternDisease = surveyPatternDisease;
	}

	public String getSurveyPatternPest() {
		return this.surveyPatternPest;
	}

	public void setSurveyPatternPest(String surveyPatternPest) {
		this.surveyPatternPest = surveyPatternPest;
	}

	public String getSurveyPatternNaturalEnemy() {
		return this.surveyPatternNaturalEnemy;
	}

	public void setSurveyPatternNaturalEnemy(String surveyPatternNaturalEnemy) {
		this.surveyPatternNaturalEnemy = surveyPatternNaturalEnemy;
	}

	public int getNumPointSurvey() {
		return this.numPointSurvey;
	}

	public void setNumPointSurvey(int numPointSurvey) {
		this.numPointSurvey = numPointSurvey;
	}

	public int getNumPlantInPoint() {
		return this.numPlantInPoint;
	}

	public void setNumPlantInPoint(int numPlantInPoint) {
		this.numPlantInPoint = numPlantInPoint;
	}

	public String getImgOwnerOther() {
		return this.imgOwnerOther;
	}

	public void setImgOwnerOther(String imgOwnerOther) {
		this.imgOwnerOther = imgOwnerOther;
	}

	public String getImgPhotographerOther() {
		return this.imgPhotographerOther;
	}

	public void setImgPhotographerOther(String imgPhotographerOther) {
		this.imgPhotographerOther = imgPhotographerOther;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getLastUpdateDate() {
		return this.lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public List<ImgHerbicideDamage> getImgherbicidedamages() {
		return this.imgherbicidedamages;
	}

	public void setImgherbicidedamages(List imgherbicidedamages) {
		this.imgherbicidedamages = imgherbicidedamages;
	}

	public List<RequestDetail> getRequestdetails() {
		return this.requestdetails;
	}

	public void setRequestdetails(List requestdetails) {
		this.requestdetails = requestdetails;
	}

	public List<SurveyTarget> getSurveytargets() {
		return this.surveytargets;
	}

	public void setSurveytargets(List surveytargets) {
		this.surveytargets = surveytargets;
	}

	public List<SurveyPoint> getSurveyPoints() {
		return surveyPoints;
	}

	public void setSurveyPoints(List<SurveyPoint> surveyPoints) {
		this.surveyPoints = surveyPoints;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
