package org.cassava.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.cassava.model.Disease;
import org.cassava.model.NaturalEnemy;
import org.cassava.model.Organization;
import org.cassava.model.PestPhaseSurvey;
import org.cassava.model.Province;
import org.cassava.model.SurveyTarget;
import org.cassava.model.TargetOfSurvey;
import org.cassava.model.dto.SelectedTargetOfSurveyDTO;
import org.cassava.repository.TargetofsurveyReposirtory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class TargetOfSurveyService {
	@Autowired
	private TargetofsurveyReposirtory targetofsurveyReposirtory;

	public List<SelectedTargetOfSurveyDTO> findAll(int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		List<TargetOfSurvey> targetOfSurveys = targetofsurveyReposirtory.findAll(pageable);
		List<SelectedTargetOfSurveyDTO> selectedTargetOfSurveyDTOs = new ArrayList<>();
		for (int i = 0; i < targetOfSurveys.size(); i++) {
			SelectedTargetOfSurveyDTO selectedTargetOfSurveyDTO = new SelectedTargetOfSurveyDTO();
			selectedTargetOfSurveyDTO.setChecked(false);
			selectedTargetOfSurveyDTO.setTargetofsurvey(targetOfSurveys.get(i));
			selectedTargetOfSurveyDTOs.add(selectedTargetOfSurveyDTO);
		}
		return selectedTargetOfSurveyDTOs;
	}

	public List<Object> findNaturalEnemy() {
		return targetofsurveyReposirtory.findNatauralEnemy();
	}

	public List<Object> findPest() {
		return targetofsurveyReposirtory.findPest();
	}

	public List<Object> findDisease() {
		return targetofsurveyReposirtory.findDisease();
	}

	public TargetOfSurvey save(TargetOfSurvey targetofsurvey) {
		return targetofsurveyReposirtory.save(targetofsurvey);
	}

	public TargetOfSurvey findByName(String name) {
		return targetofsurveyReposirtory.findByName(name);
	}

	public void deleteById(int id) {
		targetofsurveyReposirtory.deleteById(id);
	}

	public List<Disease> findDiseaseByUserNameAndTargetOfSurveyId(String username, int tid, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return targetofsurveyReposirtory.findDiseaseByUserNameAndTargetOfSurveyId(username, tid, pageable);
	}

	public List<PestPhaseSurvey> findPestPhaseSurveyByUserNameAndTargetOfSurveyId(String username, int tid, int page,
			int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return targetofsurveyReposirtory.findPestByUserNameAndTargetOfSurveyId(username, tid, pageable);
	}

	public List<NaturalEnemy> findNaturalEnemyByUserNameAndTargetOfSurveyId(String username, int tid, int page,
			int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return targetofsurveyReposirtory.findNatauralEnemyByUserNameAndTargetOfSurveyId(username, tid, pageable);
	}

	public TargetOfSurvey findById(int id) {
		return targetofsurveyReposirtory.findById(id).orElse(null);
	}

	public List<TargetOfSurvey> findAllPestPhaseSurvey() {
		return targetofsurveyReposirtory.findAllPestPhaseSurvey();
	}

	public List<TargetOfSurvey> findAllDisease() {
		return targetofsurveyReposirtory.findAllDisease();
	}

	public List<TargetOfSurvey> findAllNaturalEnemy() {
		return targetofsurveyReposirtory.findAllNaturalEnemy();
	}

	public List<TargetOfSurvey> findAllDiseaseBySurveyId(int surveyId) {
		return targetofsurveyReposirtory.findAllDiseaseBySurveyId(surveyId);
	}

	public List<TargetOfSurvey> findAllPestPhaseBySurveyId(int surveyId) {
		return targetofsurveyReposirtory.findAllPestPhaseBySurveyId(surveyId);
	}

	public Object findCountSurveyAndTargetOfSurveyIdByTargetOfSurveyIdAndDateAndProvince(int tosId, Date frmDate,
			Date enDate, List<Integer> pvId) {
		return targetofsurveyReposirtory.findCountSurveyAndTargetOfSurveyIdByTargetOfSurveyIdAndDateAndProvince(tosId,
				frmDate, enDate, pvId);
	}

	public List<Object> findCountSurveyAndTargetOfSurveyByTargetOfSurveyIdAndDateAndProvinceAndStatus(List<Integer> tos,
			Date frmDate, Date enDate, List<Integer> pv, String status) {
		return targetofsurveyReposirtory.findCountSurveyAndTargetOfSurveyByTargetOfSurveyIdAndDateAndProvinceAndStatus(
				tos, frmDate, enDate, pv, status);
	}

	public List<Object> findCountSurveyAndTargetOfSurveyByTargetOfSurveyIdAndDateAndProvinceAndStatusByListOrganization(List<Integer> tos,
			Date frmDate, Date enDate, List<Integer> pv, String status,List<Integer> organization) {
		return targetofsurveyReposirtory.findCountSurveyAndTargetOfSurveyByTargetOfSurveyIdAndDateAndProvinceAndStatusByListOrganization(
				tos, frmDate, enDate, pv, status, organization);
	}
	
	public List<TargetOfSurvey> findAllNaturalEnemyBySurveyId(int surveyId) {
		return targetofsurveyReposirtory.findAllNaturalEnemyBySurveyId(surveyId);
	}

	public List<TargetOfSurvey> findDiseaseInSurveyAndOrganization(Organization organization) {
		return targetofsurveyReposirtory.findDiseaseInSurveyAndOrganization(organization);
	}

	public List<TargetOfSurvey> findPestInSurveyAndOrganization(Organization organization) {
		return targetofsurveyReposirtory.findPestInSurveyAndOrganization(organization);
	}

	public List<TargetOfSurvey> findNaturalEnemyInSurveyAndOrganization(Organization organization) {
		return targetofsurveyReposirtory.findNaturalEnemyInSurveyAndOrganization(organization);
	}
	
	public List<TargetOfSurvey> findTypeDiseaseBySurveyTarget(List<SurveyTarget> sts) {
		return targetofsurveyReposirtory.findTypeDiseaseBySurveyTarget(sts);
	}
	
	public List<TargetOfSurvey> findTypePestPhaseBySurveyTarget(List<SurveyTarget> sts) {
		return targetofsurveyReposirtory.findTypePestPhaseBySurveyTarget(sts);
	}
	
	public List<TargetOfSurvey> findTypeNaturalEnemyBySurveyTarget(List<SurveyTarget> sts) {
		return targetofsurveyReposirtory.findTypeNaturalEnemyBySurveyTarget(sts);
	}	
	
	public List<TargetOfSurvey> findByListTargetOfSurveyId(List<Integer> targetOfSurveyId) {
		return targetofsurveyReposirtory.findByListTargetOfSurveyId(targetOfSurveyId);
	}
}
