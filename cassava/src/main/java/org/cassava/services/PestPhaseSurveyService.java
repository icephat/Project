package org.cassava.services;

import java.util.List;

import org.cassava.model.Pest;
import org.cassava.model.PestPhase;
import org.cassava.model.PestPhaseSurvey;
import org.cassava.repository.PestPhaseSurveyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Service
public class PestPhaseSurveyService {
	
	@Autowired
	private PestPhaseSurveyRepository pestphasesurveyRepository;
	
	public PestPhaseSurvey findById(int id) {
		return pestphasesurveyRepository.findById(id).get();
	}
	
	
	public PestPhaseSurvey save(PestPhaseSurvey pp) {
		return pestphasesurveyRepository.save(pp);
	}
	
	public void deleteById(int id) {
		pestphasesurveyRepository.deleteById(id);
	}
	
	public List<PestPhaseSurvey> findByPestId(int id) {
		return pestphasesurveyRepository.findByPestId(id);
	}
	
	public List<PestPhaseSurvey> findAll(){
		return (List<PestPhaseSurvey>) pestphasesurveyRepository.findAll();
	}
	
	public List<PestPhaseSurvey> findBySurveyIDAndUserName(int id,String userName){
		return pestphasesurveyRepository.findBySurveyIDAndUserName(id,userName);
	}
	
	public  PestPhaseSurvey findByName(String name) {
		return pestphasesurveyRepository.findByName(name);
	}
	
}
