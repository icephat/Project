package se.springboot.sharitytest.model;
// Generated Feb 23, 2023, 5:00:50 PM by Hibernate Tools 5.6.3.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Comment generated by hbm2java
 */
public class Comment implements java.io.Serializable {

	private Integer commentId;
	private Activity activity;
	private User user;
	private String detail;
	private Set reportComments = new HashSet(0);

	public Comment() {
	}

	public Comment(Activity activity, User user, String detail) {
		this.activity = activity;
		this.user = user;
		this.detail = detail;
	}

	public Comment(Activity activity, User user, String detail, Set reportcomments) {
		this.activity = activity;
		this.user = user;
		this.detail = detail;
		this.reportComments = reportcomments;
	}

	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
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

	public Set getReportComments() {
		return this.reportComments;
	}

	public void setReportComments(Set reportcomments) {
		this.reportComments = reportcomments;
	}

}
