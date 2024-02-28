package se.springboot.sharitytest.model.dto;

public class ReportCommentDTO {

	private int commentId;
	private String detail;
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
}
