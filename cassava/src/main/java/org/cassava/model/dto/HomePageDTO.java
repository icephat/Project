package org.cassava.model.dto;

import org.cassava.util.ImageBase64Helper;

public class HomePageDTO {
	private int id;
	private String name ;
	private String image ;
	
	public HomePageDTO() {
		super();
	}

	public HomePageDTO(int id, String name, String image) {
		super();
		this.id = id;
		this.name = name;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		if (image == null)
			return;
		
		this.image = ImageBase64Helper.toImageBase64(image);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setImageBase64(String image) {
		this.image = image;
	}

}
