package org.cassava.model;
// Generated Nov 30, 2022, 10:33:28 PM by Hibernate Tools 5.5.9.Final

/**
 * Imgpest generated by hbm2java
 */
public class ImgPest implements java.io.Serializable {

	private Integer imgPestId;
	private Pest pest;
	private String filePath;

	public ImgPest() {
	}

	public ImgPest(Pest pest, String filePath) {
		this.pest = pest;
		this.filePath = filePath;
	}

	public Integer getImgPestId() {
		return this.imgPestId;
	}

	public void setImgPestId(Integer imgPestId) {
		this.imgPestId = imgPestId;
	}

	public Pest getPest() {
		return this.pest;
	}

	public void setPest(Pest pest) {
		this.pest = pest;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
