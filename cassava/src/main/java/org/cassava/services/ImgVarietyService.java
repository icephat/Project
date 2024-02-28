package org.cassava.services;

import java.io.File;
import java.util.List;

import org.cassava.model.ImgVariety;
import org.cassava.repository.ImgVarietyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImgVarietyService {
	@Value("${external.resoures.path}")
	private String externalPath;
	
	
	@Autowired
	private ImgVarietyRepository imgVarietyRepository;
	
	public ImgVariety save(ImgVariety imgvariety) {
		return imgVarietyRepository.save(imgvariety);
	}
	
	public ImgVariety findById(int id) {
		return imgVarietyRepository.findById(id).orElse(null);
	}
	
	public void delete(ImgVariety imgvariety) {
		
		File f = new File(externalPath + File.separator+"Variety"+File.separator + imgvariety.getFilePath());
		if (f.exists())
			f.delete();	
		
		imgVarietyRepository.delete(imgvariety);
	}

	public void deleteByID(int id) {

		delete(findById(id));

	}
	
	public List<ImgVariety> findAllByPagination (int page, int value){
		Pageable pageable = PageRequest.of(page-1, value);
		return imgVarietyRepository.findAllByPagination(pageable);
	}
	
	
}