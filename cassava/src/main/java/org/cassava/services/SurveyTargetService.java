package org.cassava.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import javax.persistence.Query;

import org.cassava.model.Province;
import org.cassava.model.Survey;
import org.cassava.model.SurveyTarget;
import org.cassava.model.SurveyTargetPoint;
import org.cassava.model.TargetOfSurvey;
import org.cassava.model.User;
import org.cassava.model.dto.SelectedTargetOfSurveyDTO;
import org.cassava.repository.SurveyTargetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SurveyTargetService {
	@Autowired
	private SurveyTargetRepository surveyTargetRepository;

	@Autowired
	private SurveyTargetPointService surveyTargetPointService;

	@Autowired
	private final EntityManager entityManager;

	@Autowired
	public SurveyTargetService(EntityManager entityManager) {
		this.entityManager = entityManager.getEntityManagerFactory().createEntityManager();
	}

	public SurveyTarget findById(int id) {
		return surveyTargetRepository.findById(id).orElse(null);
	}

	public List<SurveyTarget> findAll() {
		return (List<SurveyTarget>) surveyTargetRepository.findAll();
	}

	public void deleteById(int id) {
		surveyTargetRepository.deleteById(id);
	}

	public void deleteSurveyTargetByTargetOfSurveyID(int SurveyID) {
		surveyTargetRepository.deleteSurveyTargetByTargetOfSurveyID(SurveyID);
	}
	
	public SurveyTarget findByTargetOfSurveyIdAndSurveyId(int id, int surveyId) {
		return surveyTargetRepository.findByTargetOfSurveyIdAndSurveyId(id, surveyId);
	}

	public List<SurveyTarget> findBySurveyId(int id) {
		return (List<SurveyTarget>) surveyTargetRepository.findBySurveyId(id);
	}

	public List<SurveyTarget> findByDiseaseandSurvey(int id) {
		return surveyTargetRepository.findByDiseaseandSurveyId(id);
	}

	public List<SurveyTarget> findByPestPhaseandSurvey(int id) {
		return surveyTargetRepository.findByPestPhaseandSurveyId(id);
	}

	public List<SurveyTarget> findByNaturalEnemyandSurvey(int id) {
		return surveyTargetRepository.findByNaturalEnemyandSurveyId(id);
	}

	public List<SurveyTarget> findTypeDiseaseBySurveyId(int id) {
		return surveyTargetRepository.findTypeDiseaseBySurveyId(id);
	}

	public List<SurveyTarget> findTypePestPhaseBySurveyId(int id) {
		return surveyTargetRepository.findTypePestPhaseBySurveyId(id);
	}

	public List<SurveyTarget> findTypeNaturalEnemyBySurveyId(int id) {
		return surveyTargetRepository.findTypeNaturalEnemyBySurveyId(id);
	}

	public List<Object> findCheckListDiseaseBySurveyId(int id) {
		return surveyTargetRepository.findCheckListDiseaseBySurveyId(id);
	}

	public List<Object> findAllDiseaseBySurveyId(int id) {
		return (List<Object>) surveyTargetRepository.findAllDiseaseBySurveyId(id);
	}

	public List<Object> findAllNaturalEnemyBySurveyId(int id) {
		return (List<Object>) surveyTargetRepository.findAllNaturalEnemyBySurveyId(id);
	}

	public List<Object> findAllPestPhaseBySurveyId(int id) {
		return (List<Object>) surveyTargetRepository.findAllPestPhaseBySurveyId(id);
	}

	public SurveyTarget save(SurveyTarget s) {
		return surveyTargetRepository.save(s);
	}

	public SurveyTarget findById(int id, User user) {
		return surveyTargetRepository.findById(id, user);
	}

	public Integer findNumberOfFieldBySurveyTargets(List<SurveyTarget> surveyTargets) {
		return surveyTargetRepository.findNumberOfFieldBySurveyTargets(surveyTargets);
	}

	public Float findSizePlantingBySurveyTargets(List<SurveyTarget> surveyTargets) {
		return surveyTargetRepository.findSizePlantingBySurveyTargets(surveyTargets);
	}

	public Integer findNumberOfFieldDiseaseBySurveyTargets(List<SurveyTarget> surveyTargets) {
		return surveyTargetRepository.findNumberOfFieldDiseaseBySurveyTargets(surveyTargets);
	}

	public Float findSizePlantingDiseaseBySurveyTargets(List<SurveyTarget> surveyTargets) {
		return surveyTargetRepository.findSizePlantingDiseaseBySurveyTargets(surveyTargets);
	}

	public Float findPercentPlantingDiseaseBySurveyTargets(List<SurveyTarget> surveyTargets) {
		Float health = surveyTargetRepository.findSizePlantingBySurveyTargets(surveyTargets);
		Float damage = surveyTargetRepository.findSizePlantingDiseaseBySurveyTargets(surveyTargets);
		return (damage / health) * 100;
	}

	public Float findAvgDamageLevelDiseaseInALLFieldBySurveyTargets(List<SurveyTarget> surveyTargets) {
		Float health = surveyTargetRepository.findSumAvgDamageLevelDiseaseBySurveyTargets(surveyTargets);
		int number = surveyTargetRepository.findNumberOfFieldBySurveyTargets(surveyTargets);
		return (health / number);
	}

	public Float findAvgDamageLevelDiseaseInALLDiseaseFieldBySurveyTargets(List<SurveyTarget> surveyTargets) {
		Float health = surveyTargetRepository.findSumAvgDamageLevelDiseaseBySurveyTargets(surveyTargets);
		int number = surveyTargetRepository.findNumberOfFieldDiseaseBySurveyTargets(surveyTargets);
		Float sum = (float) 0;
		if (number != 0) {
			sum = (health / number);

		}
		return sum;
	}

	public float findAvgPercentDamageInAllFieldBySurveyTargets(List<SurveyTarget> surveyTargets) {
		return surveyTargetRepository.findAvgPercentDamageInAllFieldBySurveyTargets(surveyTargets);
	}

	public Float findAvgPercentDamageInAllDiseaseFieldBySurveyTargets(List<SurveyTarget> surveyTargets) {
		List<Integer> id = new ArrayList<Integer>();

		for (SurveyTarget surveyTarget : surveyTargets) {
			id.add(surveyTarget.getSurveyTargetId());

		}
		Float sum = surveyTargetRepository.findAvgPercentDamageInAllDiseaseFieldBySurveyTargets(id);
		if (sum == null) {
			sum = (float) 0;

		}
		return sum;
	}

	// **************** update damage *************************
	private float calAvgDamage(int stID) {
		return surveyTargetRepository.calAvgDamage(stID);
	}

	private float calAvgDamage(SurveyTarget st) {
		return calAvgDamage(st.getSurveyTargetId());
	}

	private float calPercentDamage(int stID) {
		return surveyTargetRepository.calPercentDamage(stID);
	}

	private float calPercentDamage(SurveyTarget st) {
		return calPercentDamage(st.getSurveyTargetId());
	}

	public SurveyTarget updateDamage(SurveyTarget st) {
		st.setAvgDamageLevel(calAvgDamage(st));
		st.setPercentDamage(calPercentDamage(st));
		return surveyTargetRepository.save(st);
	}

	public SurveyTarget updateDamage(int stId) {
		return updateDamage(findById(stId));
	}

	// ************************* cal damage for report ***************
	public float calAvgDamage(List<SurveyTarget> sts) {
		return surveyTargetRepository.calAvgDamage(sts);
	}

	public float calPercentDamage(List<SurveyTarget> sts) {
		return surveyTargetRepository.calPercentDamage(sts);
	}

	public float calPlantingArea(SurveyTarget st) {
		return st.getSurvey().getPlanting().getSize();
	}

	public float calPlantingArea(List<SurveyTarget> sts) {
		return surveyTargetRepository.calPlantingArea(sts);
	}

	public int calNumSurvey(SurveyTarget st) {
		return st.getSurveytargetpoints().size();
	}

	public int calNumSurvey(List<SurveyTarget> sts) {
		return surveyTargetRepository.calNumSurvey(sts);
	}
	// **************************************************************

	private void createSurveyTargetPoing(int id) {

//		String sql = "INSERT INTO surveytargetpoint (survetTargetID, pointNumber, itemNumber, vlue) VALUES (?,?,?,?)" ; 
//		entityManager.createNativeQuery(sql).setParameter(1, id)
//		.setParameter(2, sql)

	}

	public SurveyTarget saveSurveyTarget(TargetOfSurvey targetofsurvey, Survey survey) {
		SurveyTarget surveyTarget = new SurveyTarget();
		surveyTarget.setTargetofsurvey(targetofsurvey);
		surveyTarget.setStatus("Editing");
		surveyTarget.setAvgDamageLevel((float) 0);
		surveyTarget.setPercentDamage((float) 0);
		surveyTarget.setSurvey(survey);
		SurveyTarget st = save(surveyTarget);

		// createSurveyTargetPoing(st.getSurveyTargetId())
		List<SurveyTargetPoint> sTargetPoints = new ArrayList<SurveyTargetPoint>();
		// int index = 0;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 5; j++) {
				SurveyTargetPoint stp = new SurveyTargetPoint();
				stp.setPointNumber(j);
				stp.setItemNumber(i);
				stp.setSurveytarget(st);
				stp.setValue(0);
				sTargetPoints.add(stp);
				// surveyTargetPointService.save(stp);
			}
		}
		surveyTargetPointService.saveAll(sTargetPoints);
		// surveyTargetPointService

		return st;
	}

	public List<SurveyTarget> saveSurveyTarget(List<SelectedTargetOfSurveyDTO> selectedTargetOfSurveyDTOs,
			Survey survey) {
		List<SurveyTarget> surveyTargets = new ArrayList<>();
		for (SelectedTargetOfSurveyDTO selected : selectedTargetOfSurveyDTOs) {
			if (selected.isChecked()) {
				surveyTargets.add(saveSurveyTarget(selected.getTargetofsurvey(), survey));
			}
		}
		return surveyTargets;
	}

	public List<SurveyTarget> updateSurveyTarget(List<SelectedTargetOfSurveyDTO> selectedTargetOfSurveyDTOs,
			Survey survey) {
		List<SurveyTarget> surveyTargets = new ArrayList<>();
		for (SelectedTargetOfSurveyDTO selectedTargetOfSurveyDTO : selectedTargetOfSurveyDTOs) {
			if (selectedTargetOfSurveyDTO.isChecked()) {
				SurveyTarget surveyTarget = this.findByTargetOfSurveyIdAndSurveyId(
						selectedTargetOfSurveyDTO.getTargetofsurvey().getTargetOfSurveyId(), survey.getSurveyId());
				if (surveyTarget == null) {
					surveyTargets.add(saveSurveyTarget(selectedTargetOfSurveyDTO.getTargetofsurvey(), survey));
				} else {
					surveyTargets.add(surveyTarget);
				}
			} else {
				SurveyTarget surveyTarget = this.findByTargetOfSurveyIdAndSurveyId(
						selectedTargetOfSurveyDTO.getTargetofsurvey().getTargetOfSurveyId(), survey.getSurveyId());
				if (surveyTarget != null) {
					this.deleteById(surveyTarget.getSurveyTargetId());
				}
			}
		}
		return surveyTargets;
	}

	public float getPlantingArea(int surveyTargetId, String username) {
		return surveyTargetRepository.findPlantingArea(surveyTargetId, username);
	}

	public SurveyTarget findSurveyTargetBytargetOfSurvey(int id) {
		return surveyTargetRepository.findByTargetOfSurvey(id);
	}

	public List<Object> findSurveyTargetAndCountByDateAndTargetOfSurveyId(Date stDate, Date edDate, int tosId) {
		return surveyTargetRepository.findSurveyTargetAndCountByDateAndTargetOfSurveyId(stDate, edDate, tosId);
	}

	public List<SurveyTarget> findByTargetOfSurveysAndBetweenDateAndProvincesAndStatus(List<TargetOfSurvey> toss,
			Date stDate, Date endDate, List<Province> pv, String status) {
		return surveyTargetRepository.findByTargetOfSurveysAndBetweenDateAndProvincesAndStatus(toss, stDate, endDate,
				pv, status);
	}

	public List<SurveyTarget> findByTargetOfSurveysAndBetweenDateAndProvincesAndStatusAndFieldName(
			List<TargetOfSurvey> toss, Date stDate, Date endDate, List<Province> pv, String status, String fieldname) {
		return surveyTargetRepository.findByTargetOfSurveysAndBetweenDateAndProvincesAndStatusAndFieldName(toss, stDate,
				endDate, pv, status, fieldname);
	}

	public List<SurveyTarget> filterPostsBasedOnKeywords(String start, String end, String fieldname,
			List<Integer> disease, List<Integer> pest, List<Integer> naturalenemy, List<Integer> provinceid) {
		List<Integer> tar = new ArrayList<Integer>();
		tar.addAll(disease);
		tar.addAll(pest);
		tar.addAll(naturalenemy);
		StringBuilder sb = new StringBuilder();

		sb.append("select * FROM  surveytarget as st INNER JOIN survey as s ON st.surveyID= s.surveyID"
				+ "	INNER JOIN targetofsurvey as tos on st.targetOfSurveyID = tos.targetOfSurveyID"
				+ "	INNER JOIN permissiontargetofsurvey as ptos on ptos.targetOfSurveyID  = tos.targetOfSurveyID"
				+ "	INNER JOIN permission as p  on p.permisisonID = ptos.permissionID  "
				+ "	INNER JOIN planting pp ON pp.plantingID=s.plantingID"
				+ " inner join `field` f on f.fieldID=pp.fieldID "
				+ " inner join subdistrict sd on f.subdistrictID=sd.subdistrictID"
				+ " inner join district d on sd.districtID= d.districtID"
				+ " inner join province pv on pv.provinceID=d.provinceID where" + "(s.date between '").append(start)
				.append("' and '").append(end).append("') " + "and p.status = 'Approve' and p.dateExpire IS NOT NULL");
		if (fieldname != null && !fieldname.isEmpty()) {
			sb.append(" and f.name = '").append(fieldname).append("'");
		}
		sb.append(" and ").append("pv.provinceID IN ( ");
		for (int i = 0; i < provinceid.size(); i++) {
			sb.append(provinceid.get(i));
			if (i < provinceid.size() - 1) {
				sb.append(", ");
			}
		}
		sb.append(")");
		if (!tar.isEmpty()) {
			sb.append(" and ").append("tos.targetOfSurveyID IN (");
			for (int i = 0; i < tar.size(); i++) {
				sb.append(tar.get(i));
				if (i < tar.size() - 1) {
					sb.append(", ");
				}
			}
			sb.append(")");

		}
		// System.out.println(sb);
		Query q = entityManager.createNativeQuery(sb.toString(), SurveyTarget.class);

		return q.getResultList();
	}

	public List<SurveyTarget> findByRequestIdAndTargetOfSurvey(int id, TargetOfSurvey tos) {
		return surveyTargetRepository.findByRequestIdAndTargetOfSurvey(id, tos);
	}

	public Integer countDiseaseBySurveyId(int surveyId) {
		return surveyTargetRepository.countDiseaseBySurveyId(surveyId);
	}

	public Integer countNaturalEnemyBySurveyId(int surveyId) {
		return surveyTargetRepository.countNaturalEnemyBySurveyId(surveyId);
	}

	public Integer countPestPhaseSurveyBySurveyId(int surveyId) {
		return surveyTargetRepository.countPestPhaseSurveyBySurveyId(surveyId);
	}

	public Object countTotalAndFoundBySurveyIdAndPointNumberAndItemNumber(int surveyId, int pointNumber,
			int itemNumber) {
		return surveyTargetRepository.countTotalAndFoundBySurveyIdAndPointNumberAndItemNumber(surveyId, pointNumber,
				itemNumber);
	}

	public List<SurveyTarget> findByTargetOfSurveysAndBetweenDateAndProvincesAndStatusAndFieldNameAndOrganization(
			List<TargetOfSurvey> toss, Date stDate, Date endDate, List<Province> pv, String status, String fieldname,
			List<Integer> organizationid) {
		return surveyTargetRepository
				.findByTargetOfSurveysAndBetweenDateAndProvincesAndStatusAndFieldNameAndOrganization(toss, stDate,
						endDate, pv, status, fieldname, organizationid);
	}

	public List<TargetOfSurvey> findPestPhaseSurveyBySurveyId(int surveyId) {
		return surveyTargetRepository.findPestPhaseSurveyBySurveyId(surveyId);
	}
	
	public List<TargetOfSurvey> findDiseaseBySurveyId(int surveyId) {
		return surveyTargetRepository.findDiseaseBySurveyId(surveyId);
	}
	
	public List<TargetOfSurvey> findNaturalEnemyBySurveyId(int surveyId) {
		return surveyTargetRepository.findNaturalEnemyBySurveyId(surveyId);
	}

}
