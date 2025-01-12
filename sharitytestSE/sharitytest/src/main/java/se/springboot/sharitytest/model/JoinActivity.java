package se.springboot.sharitytest.model;
// Generated Mar 10, 2023, 12:04:42 AM by Hibernate Tools 5.6.3.Final

import java.util.Date;

/**
 * Joinactivity generated by hbm2java
 */
public class JoinActivity implements java.io.Serializable {

	private Integer joinActivityId;
	private Activity activity;
	private ConditionActivity conditionActivity;
	private User user;
	private String status;
	private Integer score;
	private String detailRequest;
	private String comment;
	private Date dateRequest;
	private Date dateApprove;

	public JoinActivity() {
	}

	public JoinActivity(Activity activity, User user, String status) {
		this.activity = activity;
		this.user = user;
		this.status = status;
	}

	public JoinActivity(Activity activity, ConditionActivity conditionActivity, User user, String status, Integer score,
			String comment, Date dateRequest, Date dateApprove) {
		this.activity = activity;
		this.conditionActivity = conditionActivity;
		this.user = user;
		this.status = status;
		this.score = score;
		this.comment = comment;
		this.dateRequest = dateRequest;
		this.dateApprove = dateApprove;
	}

	public Integer getJoinActivityId() {
		return this.joinActivityId;
	}

	public void setJoinActivityId(Integer joinActivityId) {
		this.joinActivityId = joinActivityId;
	}

	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public ConditionActivity getConditionActivity() {
		return this.conditionActivity;
	}

	public void setConditionActivity(ConditionActivity conditionActivity) {
		this.conditionActivity = conditionActivity;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getScore() {
		return this.score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getComment() {
		return this.comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDateRequest() {
		return this.dateRequest;
	}

	public void setDateRequest(Date dateRequest) {
		this.dateRequest = dateRequest;
	}

	public Date getDateApprove() {
		return this.dateApprove;
	}

	public void setDateApprove(Date dateApprove) {
		this.dateApprove = dateApprove;
	}

	public String getDetailRequest() {
		return detailRequest;
	}

	public void setDetailRequest(String detailRequest) {
		this.detailRequest = detailRequest;
	}

}
