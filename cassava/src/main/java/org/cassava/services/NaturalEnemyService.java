package org.cassava.services;

import org.cassava.model.Disease;
import org.cassava.model.ImgNaturalEnemy;
import org.cassava.model.NaturalEnemy;
import org.cassava.repository.NaturalEnemyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@Service
@PropertySource(value = "classpath:/application.properties")
public class NaturalEnemyService {

	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private ImgNaturalEnemyService imgNaturalEnemyService;

	@Autowired
	private NaturalEnemyRepository naturalenemyrepository;

	// Save and Edit
	public NaturalEnemy save(NaturalEnemy naturalenemy) {
		return naturalenemyrepository.save(naturalenemy);
	}

	public void deleteFileByNaturalEnemyID(int id) {
		NaturalEnemy naturalenemy = findById(id);
		for (ImgNaturalEnemy img : naturalenemy.getImgnaturalenemies()) {
			File f = new File(externalPath + File.separator + "Naturalenemy" + File.separator + img.getFilePath());
			if (f.exists())
				f.delete();
			imgNaturalEnemyService.delete(img);
		}
	}

	public String writeFile(MultipartFile file) throws IOException {

		File folder = new File(externalPath + File.separator + "Naturalenemy" + File.separator);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		UUID uuid = UUID.randomUUID();

		String filename = uuid.toString();

		String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

		filename = filename + "." + type;

		String path = externalPath + File.separator + "Naturalenemy" + File.separator + filename;
		OutputStream outputStream = new FileOutputStream(path);
		outputStream.write(file.getBytes());
		outputStream.close();

		return filename;

	}

	// Read
	public NaturalEnemy findById(int id) {
		return naturalenemyrepository.findById(id).orElse(null);

	}

	public List<NaturalEnemy> findAll() {
		return (List<NaturalEnemy>) naturalenemyrepository.findAll();
	}

	public NaturalEnemy findByCommonName(String commonName) {
		return naturalenemyrepository.findByCommonName(commonName);
	}

	public List<NaturalEnemy> findByType(String type) {
		return (List<NaturalEnemy>) naturalenemyrepository.findByType(type);
	}

	public List<NaturalEnemy> findByControlMethod(String controlMethod) {
		return (List<NaturalEnemy>) naturalenemyrepository.findByControlMethod(controlMethod);
	}

	public List<NaturalEnemy> findBySurveyIDAndUserName(int id, String userName) {

		return naturalenemyrepository.findBySurveyIDAndUserName(id, userName);
	}

	// Delete
	public void deleteById(int id) {
		deleteFileByNaturalEnemyID(id);
		naturalenemyrepository.deleteById(id);
	}
	
	public NaturalEnemy findByName(String name) {
		return naturalenemyrepository.findByName(name);
	}
	
	public NaturalEnemy findByScientificName(String scientificName) {
		return naturalenemyrepository.findByScientificName(scientificName);
	}

	public Integer checkFkByNaturalEnemyId(int naturalEnemyId) {
		return naturalenemyrepository.checkFkByNaturalEnemyId(naturalEnemyId);
	}
	
}