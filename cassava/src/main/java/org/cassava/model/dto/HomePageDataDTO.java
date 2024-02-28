package org.cassava.model.dto;

import java.util.ArrayList;
import java.util.List;

public class HomePageDataDTO {
	protected String name;
	protected List<String> image = new ArrayList<String>();
	
	public HomePageDataDTO() {
		
	}

	public HomePageDataDTO(String name, List<String> image) {
		super();
		this.name = name;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getImage() {
		return image;
	}

	public void setImage(List<String> image) {
		this.image = image;
	}
	
	

}
