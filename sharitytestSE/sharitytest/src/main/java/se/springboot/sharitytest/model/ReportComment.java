package se.springboot.sharitytest.model;
// Generated Feb 23, 2023, 5:00:50 PM by Hibernate Tools 5.6.3.Final

/**
 * Reportcomment generated by hbm2java
 */
public class ReportComment implements java.io.Serializable {

	private Integer reportCommentId;
	private Comment comment;
	private User user;
	private String detail;

	public ReportComment() {
	}

	public ReportComment(Comment comment, User user, String detail) {
		this.comment = comment;
		this.user = user;
		this.detail = detail;
	}

	public Integer getReportCommentId() {
		return this.reportCommentId;
	}

	public void setReportCommentId(Integer reportCommentId) {
		this.reportCommentId = reportCommentId;
	}

	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDetail() {
		return this.detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
