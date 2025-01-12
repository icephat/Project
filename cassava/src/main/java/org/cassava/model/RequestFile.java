package org.cassava.model;
// Generated Nov 30, 2022, 10:33:28 PM by Hibernate Tools 5.5.9.Final

/**
 * Requestfile generated by hbm2java
 */
public class RequestFile implements java.io.Serializable {

	private Integer requestFileId;
	private Request request;
	private String filePath;

	public RequestFile() {
	}

	public RequestFile(Request request, String filePath) {
		this.request = request;
		this.filePath = filePath;
	}

	public Integer getRequestFileId() {
		return this.requestFileId;
	}

	public void setRequestFileId(Integer requestFileId) {
		this.requestFileId = requestFileId;
	}

	public Request getRequest() {
		return this.request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

}
