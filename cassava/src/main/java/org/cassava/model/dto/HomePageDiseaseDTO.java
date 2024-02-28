package org.cassava.model.dto;

import java.util.List;

import org.cassava.model.Disease;

public class HomePageDiseaseDTO extends HomePageDataDTO {
	private Disease disease;
	private List<String> pestName;
	private List<String> pestSciName;
	private List<String> pathogenName; 
	private List<String> pathogenTypeName; 
	
	public HomePageDiseaseDTO() {
		super();
	}

	public HomePageDiseaseDTO(Disease disease, List<String> pestName, List<String> pestSciName,
			List<String> pathogenName, List<String> pathogenTypeName) {
		super();
		this.disease = disease;
		this.pestName = pestName;
		this.pestSciName = pestSciName;
		this.pathogenName = pathogenName;
		this.pathogenTypeName = pathogenTypeName;
	}

	public Disease getDisease() {
		return disease;
	}

	public void setDisease(Disease disease) {
		this.disease = disease;
	}

	public List<String> getPathogenName() {
		return pathogenName;
	}

	public void setPathogenName(List<String> pathogenName) {
		this.pathogenName = pathogenName;
	}

	public List<String> getPathogenTypeName() {
		return pathogenTypeName;
	}

	public void setPathogenTypeName(List<String> pathogenTypeName) {
		this.pathogenTypeName = pathogenTypeName;
	}

	public List<String> getPestName() {
		return pestName;
	}

	public void setPestName(List<String> pestName) {
		this.pestName = pestName;
	}

	public List<String> getPestSciName() {
		return pestSciName;
	}

	public void setPestSciName(List<String> pestSciName) {
		this.pestSciName = pestSciName;
	}	
	
	
}
