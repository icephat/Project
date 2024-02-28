package org.cassava.services;

import java.io.File;
import java.util.List;

import org.cassava.model.ImgNaturalEnemy;
import org.cassava.model.NaturalEnemy;
import org.cassava.repository.ImgNaturalEnemyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@PropertySource(value = "classpath:/application.properties")
public class ImgNaturalEnemyService {
	
	@Value("${external.resoures.path}")
	private String externalPath;

	@Autowired
	private ImgNaturalEnemyRepository imgNaturalEnemyRepository;

	public ImgNaturalEnemy save(ImgNaturalEnemy imgnaturalenemy) {
		return imgNaturalEnemyRepository.save(imgnaturalenemy);
	}
	
	public void delete(ImgNaturalEnemy imgnaturalenemy) {
		imgNaturalEnemyRepository.delete(imgnaturalenemy);
	}
	
	public ImgNaturalEnemy findById(int id) {
		return imgNaturalEnemyRepository.findById(id).orElse(null);
	}

	public void deleteByID(int id) {
		File f = new File(externalPath + File.separator+"Naturalenemy"+File.separator + imgNaturalEnemyRepository.findById(id).get().getFilePath());
		if (f.exists())
			f.delete();
		imgNaturalEnemyRepository.deleteById(id);
	}
	
	public List<ImgNaturalEnemy> findAllByPagination (int page, int value){
		Pageable pageable = PageRequest.of(page-1, value);
		return (List<ImgNaturalEnemy>) imgNaturalEnemyRepository.findAllByPagination(pageable);
	}

}
