package org.cassava.services;

import java.io.File;
import java.util.List;

import org.cassava.model.ImgPest;
import org.cassava.repository.ImgPestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ImgPestService {
	@Value("${external.resoures.path}")
	private String externalPath;
	
	@Autowired
	private ImgPestRepository imgpestRepository;
	
	public ImgPest save(ImgPest imgpest) {
		return imgpestRepository.save(imgpest);
	}
	
	public ImgPest findById(int id) {
		return imgpestRepository.findById(id).orElse(null);
	}
	
	public void delete(ImgPest imgpest) {
		imgpestRepository.delete(imgpest);
	}

	public void deleteByID(int id) {		
		File f = new File(externalPath + File.separator+"Naturalenemy"+File.separator + imgpestRepository.findById(id).get().getFilePath());
		if (f.exists())
			f.delete();
		imgpestRepository.deleteById(id);
	}
	
	public List<ImgPest> findAllByPagination(int page, int value){
		Pageable pageable = PageRequest.of(page-1, value);
		return (List<ImgPest>) imgpestRepository.findAllByPagination(pageable);
	}
}
