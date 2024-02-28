package org.cassava.model.dto;

import java.util.ArrayList;
import java.util.List;

import org.cassava.model.PestManagement;

public class HomePagePestDTO extends HomePageDataDTO {
	private List<PestManagement> pestManagement = new ArrayList<PestManagement>();

	public HomePagePestDTO() {
		super();
	}

	public HomePagePestDTO(List<PestManagement> pestManagement) {
		super();
		this.pestManagement = pestManagement;
	}

	public List<PestManagement> getPestManagement() {
		return pestManagement;
	}

	public void setPestManagement(List<PestManagement> pestManagement) {
		this.pestManagement = pestManagement;
	}

	
	
	
}
