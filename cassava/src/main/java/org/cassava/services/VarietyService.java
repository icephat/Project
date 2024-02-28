package org.cassava.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.cassava.model.ImgVariety;
import org.cassava.model.Variety;
import org.cassava.repository.VarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@PropertySource(value = "classpath:/application.properties")
public class VarietyService {
	
	@Value("${external.resoures.path}")
	private String externalPath;
	
	@Autowired
	private VarietyRepository varietyrepository;
	
	@Autowired
	private ImgVarietyService imgVarietyService;
	
	public void deleteFileByVarietyID(int id) {
		Variety variety = findById(id);

		for (ImgVariety img : variety.getImgvarieties()) {

			File f = new File(externalPath + File.separator+"Variety"+File.separator + img.getFilePath());

			if (f.exists())
				f.delete();
			imgVarietyService.delete(img);
		}

	}

	public String writeFile(MultipartFile file) throws IOException {

		File folder = new File(externalPath+File.separator+"Variety"+File.separator);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		UUID uuid = UUID.randomUUID();

		String filename = uuid.toString();

		String type = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);

		filename = filename + "." + type;

		String path = externalPath + File.separator+"Variety"+File.separator + filename;
		OutputStream outputStream = new FileOutputStream(path);
		outputStream.write(file.getBytes());
		outputStream.close();

		return filename;

	}
	
	// Insert & Edit
	public Variety save(Variety v) {
		return varietyrepository.save(v);
	}
	
	// Read
	public Variety findById(int id) {
		return varietyrepository.findById(id).orElse(null);
	}
		
	public List<Variety> findByCharacter(String mainCharacter){
		return (List<Variety>) varietyrepository.findByCharacter(mainCharacter);
	}
	
	public Variety findByName(String name) {
		return varietyrepository.findByName(name);
	}
	
	public List<Variety> findByKey(String apicalLeavesColor,String youngLeavesColor,String petioleColor,String stemColor,String mainCharacter){
		return (List<Variety>) varietyrepository.findByKey(apicalLeavesColor, youngLeavesColor, petioleColor, stemColor, mainCharacter);
	}
	
	public List<Variety> findAll(){
		return (List<Variety>) varietyrepository.findAll();
	}
	
	
	//Delete
	public void deleteById(int id) {
		deleteFileByVarietyID(id);
		varietyrepository.deleteById(id);
	}
	
	public List<Variety> findByPlantingId(int plantingId){
		return varietyrepository.findByPlantingId(plantingId);
	}

	public Integer checkFkByVarietyId(int VarietyId) {
		return varietyrepository.checkFkByVarietyId(VarietyId);
	}
}