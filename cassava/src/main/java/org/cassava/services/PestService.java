package org.cassava.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.cassava.model.ImgPest;
import org.cassava.model.Pest;
import org.cassava.repository.PestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@PropertySource(value = "classpath:/application.properties")
public class PestService {

	@Value("${external.resoures.path}")
	private String externalPath;
	
	@Autowired
	private PestRepository pestRepository;
	
	@Autowired
	private ImgPestService imgpestService;
	
	public void deleteFileByPestID(int id) {
		Pest pest = findById(id);

		for (ImgPest img : pest.getImgpests()) {

			File f = new File(externalPath + File.separator+"Pest"+File.separator + img.getFilePath());

			if (f.exists())
				f.delete();
			imgpestService.delete(img);
		}

	}

	public String writeFile(MultipartFile file) throws IOException {

		File folder = new File(externalPath+File.separator+"Pest"+File.separator);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		UUID uuid = UUID.randomUUID();

		String filename = uuid.toString();

		String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

		filename = filename + "." + type;

		String path = externalPath + File.separator+"Pest"+File.separator + filename;
		System.out.println(externalPath);
		System.out.println(File.separator);
		System.out.println(filename);
		OutputStream outputStream = new FileOutputStream(path);
		outputStream.write(file.getBytes());
		outputStream.close();

		return filename;

	}

	
	public List<Pest> findAll(){
		List<Pest> pest = (List<Pest>) pestRepository.findAll();
		
		return pest;
	}
	
	public Pest findByName(String name) {
		Pest pest = pestRepository.findByName(name);
		return pest;
	}
	
	public Pest findById(int id) {
		return pestRepository.findById(id).orElse(null);
	}
	
	public Pest findBySciName(String sciName) {
		Pest pest = pestRepository.findBySciName(sciName);
		return pest;
	}
	
	public Pest save (Pest p) {
		return pestRepository.save(p);
	}
	
	public Integer checkFkByPestId(int pestId) {
		return pestRepository.checkFkByPestId(pestId);
	}
	public void deleteById (int id) {
		deleteFileByPestID(id);
		pestRepository.deleteById(id);
	}
	public List<Pest> findByDiseaseNotIn(int diseaseId){
	return pestRepository.findByDiseaseNotIn(diseaseId);
}

}