package org.cassava.model.dto;

import org.cassava.util.ImageBase64Helper;

public class ImageDTO {
	
	private String image ;

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		if (image == null)
			return;
		
		this.image = ImageBase64Helper.toImageBase64(image);
	}
	
	public void setImageBase64(String image) {
		this.image = image;
	}

}