package org.cassava.services;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.awt.*;
import java.awt.image.Raster;
import java.awt.image.DataBufferByte;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.cassava.model.ImgSurveyTargetPoint;
import org.cassava.model.Survey;
import org.cassava.model.SurveyTarget;
import org.cassava.model.SurveyTargetPoint;
import org.cassava.model.User;
import org.cassava.model.dto.SurveyTargetPointDTO;
import org.cassava.repository.SurveyTargetPointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@PropertySource(value = "classpath:/application.properties")
public class SurveyTargetPointService {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private ImgSurveyTargetPointService imgSurveyTargetPointService;

	@Autowired
	private SurveyTargetService surveyTargetService;

	@Autowired
	private SurveyTargetPointRepository surveyTargetPointRepository;

	public void deleteFileByNaturalEnemyID(int id) {
		SurveyTargetPoint surveyTargetPoint = findById(id);
		for (ImgSurveyTargetPoint img : surveyTargetPoint.getImgsurveytargetpoints()) {
			File f = new File(externalPath + File.separator + "SurveyTargetPoint" + File.separator + img.getFilePath());
			if (f.exists())
				f.delete();
			imgSurveyTargetPointService.delete(img);
		}
	}

	public String writeFile(MultipartFile file, String fileName) throws IOException {

		File folder = new File(externalPath + File.separator + "SurveyTargetPoint" + File.separator);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		File s_folder = new File(externalPath + File.separator + "s_SurveyTargetPoint" + File.separator);
		if (!s_folder.exists()) {
			s_folder.mkdirs();
		}

		SecureRandom rand = new SecureRandom();
		// Setting the upper bound to generate
		// the random numbers between the specific range
		int upperbound = 1000;
		// Generating random values from 0 - 999
		// using nextInt()
		int int_random1 = rand.nextInt(upperbound);

		String filename = fileName;

		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyy");
		Date date = new Date();

		String now = formatter.format(date);

		String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

		filename = filename + "_" + now + "_" + int_random1 + "." + type;

		String path = externalPath + File.separator + "SurveyTargetPoint" + File.separator + filename;
		OutputStream outputStream = new FileOutputStream(path);
		outputStream.write(file.getBytes());
		outputStream.close();

		return filename;

	}

	public List<SurveyTargetPoint> getBySurveyTargetIdAndPointNumber(int id, int pointNumber) {
		return surveyTargetPointRepository.findBySurveyTargetIdAndPointNumber(id, pointNumber);
	}

/*	public List<SurveyTargetPoint> getBySurveyTargetId(int id) {
		return surveyTargetPointRepository.findBySurveyTargetId(id);
	}
*/
	public SurveyTargetPoint findBySurveyTargetIdAndPointNumberAndItemNumber(int id, int pointNumber, int itemNumber) {
		return surveyTargetPointRepository.findBySurveyTargetIdAndPointNumberAndItemNumber(id, pointNumber, itemNumber);
	}

	public SurveyTargetPoint save(SurveyTargetPoint stp) {
		return surveyTargetPointRepository.save(stp);
	}
	
	public void saveAll(List<SurveyTargetPoint> stp) {
		 surveyTargetPointRepository.saveAll(stp);
	}

	public SurveyTargetPoint findById(int id) {
		return surveyTargetPointRepository.findById(id).orElse(null);
	}

	public void deleteById(int id) {
		surveyTargetPointRepository.deleteById(id);
	}

	public SurveyTargetPoint findByUserAndSurveyTargetPointId(User user,int id) {
		return surveyTargetPointRepository.findByUserAndSurveyTargetPointId(id, user);
	}

//	public void updateSurvetTargetPoint(SurveyTargetPointDTO surveyTargetPointDTO ) {
//		SurveyTarget surveyTarget = surveyTargetService.findById(surveyTargetPointDTO.getSurveyTargetId());
//		
//		int surveyTargetId = surveyTarget.getSurveyTargetId();
//		int[][] surveyTargetPointId = surveyTargetPointDTO.getSurveyTargetPointIdList();
//		int[][] value = surveyTargetPointDTO.getSurveyValue();
//		for(int i=0;i<20;i++) {
//			for(int j=0;j<5;j++) {
//				SurveyTargetPoint surveyTargetPoint = findById(surveyTargetPointId[i][j]);
//				surveyTargetPoint.setValue(value[i][j]);
//				save(surveyTargetPoint);
//			}
//		}
//		surveyTarget.setStatus(surveyTargetPointDTO.getStatus());
//		float averageDamage = surveyTargetPointRepository.calculationAverageDamage(surveyTargetId);
//		float percentDamage = surveyTargetPointRepository.calculationPercentDamage(surveyTargetId);
//		surveyTarget.setAvgDamageLevel(averageDamage);
//		surveyTarget.setPercentDamage(percentDamage);
//		surveyTargetService.save(surveyTarget);
//	}
//	

	public SurveyTargetPoint updateSurveyTargetPoint(SurveyTargetPoint surveyTargetPoint, int value) {
		surveyTargetPoint.setValue(value);
		SurveyTargetPoint stp = this.save(surveyTargetPoint);
		surveyTargetService.updateDamage(stp.getSurveytarget());
		return this.save(surveyTargetPoint);
	}
	
	public SurveyTarget updateSurveyTargetPoint(int[][] stpId,int[][] stpValue,	int surveyTargetId) {
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 5; j++) {
				SurveyTargetPoint stp = findById(stpId[j][i]);
				stp.setValue(stpValue[j][i]);
				this.save(stp);				
			}
		}	
		return surveyTargetService.updateDamage(surveyTargetId);
	}

//	public SurveyTargetPoint getSurveyTargetpointByPosition(int surveyTargetId,int pointNumber,int itemNumber) {
//		return surveyTargetPointRepository.getSurveyTargetpointByPosition(surveyTargetId, pointNumber, itemNumber);
//	}
	
	public List<SurveyTargetPoint> findBySurveyTargetIdAndPointNumber(int id, int pointNumber) {
		return surveyTargetPointRepository.findBySurveyTargetIdAndPointNumber(id, pointNumber);
	}

}
