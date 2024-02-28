package org.cassava.model.dto;



public class MessageReceiverDTO {

	private String date;
	private String title;
	private String text;
	private String type;
	
	public MessageReceiverDTO() {

	}
	public MessageReceiverDTO(String title,String text,String date,String type) {
		this.date = date;
		this.text = text;
		this.title = title;
		this.type = type;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
}
