package org.cassava.services;

import java.util.List;

import org.cassava.model.Survey;
import org.cassava.model.SurveyPoint;
import org.cassava.repository.SurveyPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyPointService {
	
	@Autowired
	private  SurveyPointRepository surveyPointRepository;
	
	public List<SurveyPoint> findAll(){
		return (List<SurveyPoint>) surveyPointRepository.findAll();
	}
	
	public SurveyPoint findById(int id) {
		return surveyPointRepository.findById(id).get();
	}

	public SurveyPoint save(SurveyPoint surveyPoint) {
		return surveyPointRepository.save(surveyPoint);
	}

	public void deletebyid(int id) {
		surveyPointRepository.deleteById(id);
	}

	public void saveSurveyPoint(Survey survey) {
		for(int i = 0 ;i < 5; i++) {
			SurveyPoint sp = new SurveyPoint(survey, i, "Editing");
			this.save(sp);
		}
	}
	
	public SurveyPoint findBySurveyIdAndPointNumber(int surveyId, int pointNumber) {
		return surveyPointRepository.findBySurveyIdAndPointNumber(surveyId, pointNumber);
	}
	
	public List<SurveyPoint> findBySurveyId (int surveyId){
		return surveyPointRepository.findBySurveyId(surveyId);
	}
}
