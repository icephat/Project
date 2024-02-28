package org.cassava.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.cassava.model.Disease;
import org.cassava.model.ImgDisease;
import org.cassava.repository.DiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service("DiseaseService")
@PropertySource(value = "classpath:/application.properties")
public class DiseaseService {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private ImgDiseaseService imgDiseaseService;

	@Autowired
	private DiseaseRepository diseaseRespository;

	public void deleteFileByDiseaseID(int id) {
		Disease disease = findById(id);

		for (ImgDisease img : disease.getImgdiseases()) {

			File f = new File(externalPath + File.separator + "Disease" + File.separator + img.getFilePath());

			if (f.exists())
				f.delete();
			imgDiseaseService.delete(img);
		}

	}

	public String writeFile(MultipartFile file) throws IOException {

		File folder = new File(externalPath + File.separator + "Disease" + File.separator);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		UUID uuid = UUID.randomUUID();

		String filename = uuid.toString();

		String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

		filename = filename + "." + type;

		String path = externalPath + File.separator + "Disease" + File.separator + filename;
		
		OutputStream outputStream = new FileOutputStream(path);
		outputStream.write(file.getBytes());
		outputStream.close();

		return filename;

	}

	public List<Disease> findAll() {
		List<Disease> diseases = (List<Disease>) diseaseRespository.findAll();
		return diseases;
	}

	public Disease findBySymptom(String symptom) {
		Disease disease = diseaseRespository.findBySymptom(symptom);
		return disease;
	}

	public Disease findById(int id) {
		Disease Disease = diseaseRespository.findById(id).orElse(null);
		return Disease;
	}

	public List<Disease> findByKey(String symptom, String controlDisease, String controlPest) {
		List<Disease> Diseases = diseaseRespository.findByKey(symptom, controlDisease, controlPest);
		return Diseases;
	}

	public Disease save(Disease k) {
		return diseaseRespository.save(k);
	}

	public void deleteById(int id) {
		diseaseRespository.deleteById(id);
	}

	public List<Disease> findByTargetofsurveyName(String name) {

		return diseaseRespository.findByTargetofsurveyName(name);
	}

	public List<Disease> findBySurveyIDAndUserName(int id, String userName) {

		return diseaseRespository.findBySurveyIDAndUserName(id, userName);
	}
	
	public Disease findByName(String name) {
		return diseaseRespository.findByName(name);
	}

	public Integer checkFkBydiseaseId(int diseaseId) {
		return diseaseRespository.checkFkBydiseaseId(diseaseId);
	}
}
