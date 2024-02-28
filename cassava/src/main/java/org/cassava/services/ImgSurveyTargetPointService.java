package org.cassava.services;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.imageio.ImageIO;

import org.cassava.model.Disease;
import org.cassava.model.ImgNaturalEnemy;
import org.cassava.model.ImgSurveyTargetPoint;
import org.cassava.model.Survey;
import org.cassava.repository.DiseaseRepository;
import org.cassava.model.User;
import org.cassava.repository.ImgSurveyTargetPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service("ImgSurveyTargetPointService")
public class ImgSurveyTargetPointService {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private ImgSurveyTargetPointRepository imgSurveyTargetPointRepository;

	@Autowired
	private DiseaseService diseaseService;

	public ImgSurveyTargetPoint save(ImgSurveyTargetPoint imgSurveyTargetPoint) {
		return imgSurveyTargetPointRepository.save(imgSurveyTargetPoint);
	}

	public ImgSurveyTargetPoint findById(int id) {
		return imgSurveyTargetPointRepository.findById(id).orElse(null);
	}

	public void delete(ImgSurveyTargetPoint ImgSurveyTargetPoint) {
		imgSurveyTargetPointRepository.delete(ImgSurveyTargetPoint);
	}

	public List<ImgSurveyTargetPoint> findAll() {
		return (List<ImgSurveyTargetPoint>) imgSurveyTargetPointRepository.findAll();
	}

	public List<ImgSurveyTargetPoint> findImgSurveyTargetPointWaiting() {
		return (List<ImgSurveyTargetPoint>) imgSurveyTargetPointRepository.findImgSurveyTargetPointWaiting();
	}

	public List<ImgSurveyTargetPoint> findImgSurveyTargetPointApprovedByTargetOfSurveyId(int id, int page, int value) {
		page -= 1;
		Pageable pageable = PageRequest.of(page, value);
		return imgSurveyTargetPointRepository.findImgSurveyTargetPointApprovedByTargetOfSurveyId(id, pageable);
	}

	public List<ImgSurveyTargetPoint> findImgSurveyTargetPointWaitingByTargetOfSurveyId(int id, int page, int value) {
		page -= 1;
		Pageable pageable = PageRequest.of(page, value);
		return imgSurveyTargetPointRepository.findImgSurveyTargetPointWaitingByTargetOfSurveyId(id, pageable);
	}

	public List<ImgSurveyTargetPoint> findImgSurveyTargetPointRejectByTargetOfSurveyId(int id, int page, int value) {
		page -= 1;
		Pageable pageable = PageRequest.of(page, value);
		return imgSurveyTargetPointRepository.findImgSurveyTargetPointRejectByTargetOfSurveyId(id, pageable);
	}

	public List<ImgSurveyTargetPoint> findImgSurveyTargetPointApproved() {
		return (List<ImgSurveyTargetPoint>) imgSurveyTargetPointRepository.findImgSurveyTargetPointApproved();
	}

	public List<ImgSurveyTargetPoint> findImgSurveyTargetPointReject() {
		return (List<ImgSurveyTargetPoint>) imgSurveyTargetPointRepository.findImgSurveyTargetPointReject();
	}
	
	public List<ImgSurveyTargetPoint> findImgBySurveyTargetPointIdAndListStatus(int id){
		ArrayList<String> approveStatus = new ArrayList<>(Arrays.asList("Waiting", "Approved"));
		return (List<ImgSurveyTargetPoint>) imgSurveyTargetPointRepository.findImgBySurveyTargetPointIdAndListStatus(id,approveStatus);
	}

	public void deleteById(int id) {
		File f = new File(externalPath + File.separator + "SurveyTargetPoint" + File.separator
				+ imgSurveyTargetPointRepository.findById(id).get().getFilePath());
		File fS = new File(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator
				+ imgSurveyTargetPointRepository.findById(id).get().getFilePathS());
		if (f.exists()) {
			f.delete();
			fS.delete();
		}

		imgSurveyTargetPointRepository.deleteById(id);
	}

	public List<Object> findImgSurveyTargetPointApprovedByYearAndMonthAndDiseaseId(int d, int y, int m) {
		return (List<Object>) imgSurveyTargetPointRepository
				.findImgSurveyTargetPointApprovedByYearAndMonthAndDiseaseId(d, y, m);
	}

	public List<Object> findImgSurveyTargetPointApprovedByYearAndDiseaseId(int d, int y) {
		return (List<Object>) imgSurveyTargetPointRepository.findImgSurveyTargetPointApprovedByYearAndDiseaseId(d, y);
	}

	public List<Object> findAmountOnUploadYear() {
		return imgSurveyTargetPointRepository.findAmountOnUploadYear();
	}

	public int findAmountImgSurveyTargetPointBySurveyTargetPointId(int id) {
		return imgSurveyTargetPointRepository.findAmountImgSurveyTargetPointBySurveyTargetPointId(id);
	}

	public int findAmountImgSurveyTargetPointBySurveyTargetIdAndPointNumber(int id, int point) {
		return imgSurveyTargetPointRepository.findAmountImgSurveyTargetPointBySurveyTargetIdAndPointNumber(id, point);
	}
	
	public int findAmountImgSurveyTargetPointBySurveyTargetIdAndPointNumberAndListStatus (int id, int point){
		ArrayList<String> approveStatus = new ArrayList<>(Arrays.asList("Waiting", "Approved"));
		return imgSurveyTargetPointRepository.findAmountImgSurveyTargetPointBySurveyTargetIdAndPointNumberAndListStatus(id, point,approveStatus);
	}

	public List<Object> findAmountImgSurveyTargetPointEachMonthByDiseaseIdAndYear(int d, int y) {
		return imgSurveyTargetPointRepository.findAmountImgSurveyTargetPointEachMonthByDiseaseIdAndYear(d, y);
	}

	public Object findAmountImgSurveyTargetPointByDiseaseIdAndYearAndMonth(int d, int y, int m) {
		return imgSurveyTargetPointRepository.findAmountImgSurveyTargetPointByDiseaseIdAndYearAndMonth(d, y, m);
	}

	public List<String> findFilePathByDiseaseIdAndYearAndMonth(int d, int y, int m) {
		return imgSurveyTargetPointRepository.findFilePathByDiseaseIdAndYearAndMonth(d, y, m);
	}

	public int findAmountImgSurveyTargetPointByDiseaseIdAndYear(int d, int y) {
		return imgSurveyTargetPointRepository.findAmountImgSurveyTargetPointByDiseaseIdAndYear(d, y);
	}

	public List<ImgSurveyTargetPoint> findImgSurveyTargetPointWaitingBetweenDate(Date datestart, Date dateEnd) {
		return (List<ImgSurveyTargetPoint>) imgSurveyTargetPointRepository
				.findImgSurveyTargetPointWaitingBetweenDate(datestart, dateEnd);
	}

	public List<ImgSurveyTargetPoint> findByDiseaseAndStatus(Disease disease, String status) {
		return imgSurveyTargetPointRepository.findByDiseaseAndStatus(disease, status);
	}

	public List<ImgSurveyTargetPoint> findByDiseaseAndStatus(Disease disease, String status, int page, int value) {
		page -= 1;
		Pageable pageable = PageRequest.of(page, value);
		return imgSurveyTargetPointRepository.findByDiseaseAndStatus(disease, status, pageable);
	}

	public List<ImgSurveyTargetPoint> findByDiseaseAndStatusBetweenDate(int diseaseId, String status, Date datestart,
			Date dateEnd, int page, int value) {
		page -= 1;
		Pageable pageable = PageRequest.of(page, value);
		Disease disease = diseaseService.findById(diseaseId);
		return imgSurveyTargetPointRepository.findByDiseaseAndStatusBetweenDate(disease, status, datestart, dateEnd,
				pageable);
	}

	public List<ImgSurveyTargetPoint> findByDiseaseAndStatusBetweenDate(int diseaseId, String status, Date datestart,
			Date dateEnd) {
		Disease disease = diseaseService.findById(diseaseId);
		return imgSurveyTargetPointRepository.findByDiseaseAndStatusBetweenDate(disease, status, datestart, dateEnd);
	}

	public List<ImgSurveyTargetPoint> findByDiseaseAndStatusBetweenDate(Disease disease, String status, Date datestart,
			Date dateEnd, int page, int value) {
		page -= 1;
		Pageable pageable = PageRequest.of(page, value);
		return imgSurveyTargetPointRepository.findByDiseaseAndStatusBetweenDate(disease, status, datestart, dateEnd,
				pageable);
	}

	public List<Object> findImgSurveyTargetPointCountStatusByListMonthAndYear(List<Integer> months, int year) {
		String astatus = "Approved";
		List<String> rstatus = new ArrayList<String>();
		rstatus.add("Reject");
		rstatus.add("Delete");
		return imgSurveyTargetPointRepository.findImgSurveyTargetPointCountStatusByListMonthAndYear(months, year,
				astatus, rstatus);
	}

	public List<Object> findAllDiseaseNameAndCountStatus() {
		String astatus = "Approved";
		List<String> rstatus = new ArrayList<String>();
		rstatus.add("Reject");
		rstatus.add("Delete");
		return imgSurveyTargetPointRepository.findAllDiseaseNameAndCountStatus(astatus, rstatus);
	}

	public List<Object> findAllAndCountStatusByStatusAndMonthAndYear(int month, int year) {
		String astatus = "Approved";
		List<String> rstatus = new ArrayList<String>();
		rstatus.add("Reject");
		rstatus.add("Delete");
		return imgSurveyTargetPointRepository.findMonthAndCountStatusByStatusAndMonthAndYear(astatus, rstatus, month,
				year);
	}

	public List<Object> findAllDiseaseNameCountStatusByStatusAndUserId(int userId) {
		String astatus = "Approved";
		List<String> rstatus = new ArrayList<String>();
		rstatus.add("Reject");
		rstatus.add("Delete");
		return imgSurveyTargetPointRepository.findAllDiseaseNameCountStatusByStatusAndUserId(astatus, rstatus, userId);
	}

	public List<Object> findMonthAndCountStatusByStatusAndMonthAndYear(int year) {
		String astatus = "Approved";
		List<String> rstatus = new ArrayList<String>();
		rstatus.add("Reject");
		rstatus.add("Delete");
		return imgSurveyTargetPointRepository.findMonthAndCountStatusByStatusAndMonthAndYear(astatus, rstatus, year);
	}

	public List<Object> findYearAndCountStatusByStatusAndBetweenYear(int startyaer, int endyear) {
		String astatus = "Approved";
		List<String> rstatus = new ArrayList<String>();
		rstatus.add("Reject");
		rstatus.add("Delete");
		return imgSurveyTargetPointRepository.findYearAndCountStatusByStatusAndBetweenYear(astatus, rstatus, startyaer,
				endyear);
	}

	public Integer findCountImgSurveyTargetPointByPointNumberAndItemNumberAndSurvey(int point,int item,Survey survey,User user) {
		return imgSurveyTargetPointRepository.findCountImgSurveyTargetPointByPointNumberAndItemNumberAndSurvey(point,item,survey,user);
	}

	public Integer findMinYearInImgSurveyTargetPoint() {
        return imgSurveyTargetPointRepository.findMinYearInImgSurveyTargetPoint();
    }

	public List<Object> findImgSurveyTargetPointCountAllStatusByListMonthAndYear(List<Integer> months, int year) {
        String astatus = "Approved";
        List<String> rstatus = new ArrayList<String>();
        rstatus.add("Reject");
        rstatus.add("Delete");
        String bstatus = "Reject";
        String cstatus = "Delete";
        return imgSurveyTargetPointRepository.findImgSurveyTargetPointCountAllStatusByListMonthAndYear(months, year,
                astatus, rstatus,bstatus,cstatus);
    }
	// public List<ImgSurveyTargetPoint> findImageWithSurveyTargetId(int id) {
	// return imgSurveyTargetPointRepository.findImageBySurveyTargetPointId(id);
	// }
	
	public List<ImgSurveyTargetPoint> findBySurveyIdAndPointNumberAndItemNumberAndApprovedStatus (int surveyId, int pointNumber, int itemNumber){
		return imgSurveyTargetPointRepository.findBySurveyIdAndPointNumberAndItemNumberAndApprovedStatus(surveyId, pointNumber, itemNumber);
	}
	
	public Integer countImgBySurveyIdAndPointNumberAndItemNumber (int surveyId, int pointNumber, int itemNumber) {
		return imgSurveyTargetPointRepository.countImgBySurveyIdAndPointNumberAndItemNumber(surveyId, pointNumber, itemNumber);
	}

}
