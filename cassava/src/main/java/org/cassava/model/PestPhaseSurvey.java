package org.cassava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

// Generated Nov 30, 2022, 10:33:28 PM by Hibernate Tools 5.5.9.Final

/**
 * Pestphasesurvey generated by hbm2java
 */
public class PestPhaseSurvey implements java.io.Serializable {

	private int pestPhaseSurveyId;
	@JsonIgnore
	private Pest pest;
	@JsonIgnore
	private PestPhase pestphase;
	@JsonIgnore
	private TargetOfSurvey targetofsurvey;

	public PestPhaseSurvey() {
	}

	public PestPhaseSurvey(Pest pest, PestPhase pestphase, TargetOfSurvey targetofsurvey) {
		this.pest = pest;
		this.pestphase = pestphase;
		this.targetofsurvey = targetofsurvey;
	}

	public int getPestPhaseSurveyId() {
		return this.pestPhaseSurveyId;
	}

	public void setPestPhaseSurveyId(int pestPhaseSurveyId) {
		this.pestPhaseSurveyId = pestPhaseSurveyId;
	}

	public Pest getPest() {
		return this.pest;
	}

	public void setPest(Pest pest) {
		this.pest = pest;
	}

	public PestPhase getPestphase() {
		return this.pestphase;
	}

	public void setPestphase(PestPhase pestphase) {
		this.pestphase = pestphase;
	}

	public TargetOfSurvey getTargetofsurvey() {
		return this.targetofsurvey;
	}

	public void setTargetofsurvey(TargetOfSurvey targetofsurvey) {
		this.targetofsurvey = targetofsurvey;
	}

}