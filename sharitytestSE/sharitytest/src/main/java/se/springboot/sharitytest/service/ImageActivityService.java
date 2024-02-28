package se.springboot.sharitytest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.springboot.sharitytest.model.ImageActivity;
import se.springboot.sharitytest.repository.ImageActivityRepository;

@Service
public class ImageActivityService {

	@Autowired(required=true)
	private ImageActivityRepository imageRepository;
	public void save(ImageActivity imageActivity) {
		imageRepository.save(imageActivity);
	}
	
}
