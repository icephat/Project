package org.cassava.services;

import java.io.File;
import java.util.List;

import org.cassava.model.ImgDisease;
import org.cassava.repository.ImgDiseaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service("ImgDisease")
@PropertySource(value = "classpath:/application.properties")
public class ImgDiseaseService {
	
	@Value("${external.resoures.path}")
	private String externalPath;
	
	@Autowired
	private ImgDiseaseRepository imgdiseaseRepository;
	
	public ImgDisease findByFilepath(String f) {
		ImgDisease img = imgdiseaseRepository.findByFilepath(f);
		return img;
	}
	
	public ImgDisease findById(int id){
		ImgDisease Disease = imgdiseaseRepository.findById(id).orElse(null);
		return Disease; 
	}
	
	public ImgDisease save(ImgDisease k){
		return imgdiseaseRepository.save(k); 
	}
	
	public void deleteById(int id){
		File f = new File(externalPath + File.separator+"Disease"+File.separator + imgdiseaseRepository.findById(id).get().getFilePath());
		if (f.exists())
			f.delete();
		imgdiseaseRepository.deleteById(id); 
	}

	public void delete(ImgDisease img) {
		imgdiseaseRepository.delete(img);
	}
	public List<Object> findAmountOnUploadYear(){
    		return  imgdiseaseRepository.findAmountOnUploadYear();
	}

	public List<Object> findImgDiseaseByDiseaseAndYear(int d,int y){
    		return imgdiseaseRepository.findImgdiseaseByDiseaseAndYear(d,y);
	}
	
	public List<ImgDisease> findAllByPagination(int page, int value){
		Pageable pageable = PageRequest.of(page-1, value);
		return (List<ImgDisease>) imgdiseaseRepository.findAllByPagination(pageable);
	}
}
