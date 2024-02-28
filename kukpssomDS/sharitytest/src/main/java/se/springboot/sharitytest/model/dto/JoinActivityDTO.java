package se.springboot.sharitytest.model.dto;

public class JoinActivityDTO {
	
	private int activity;
	private String detailRequest;
	private int conditionActivity;
	private int score;
	private String comment;
	private int joinActivityId;
	
	public int getJoinActivityId() {
		return joinActivityId;
	}
	public void setJoinActivityId(int joinActivityId) {
		this.joinActivityId = joinActivityId;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getActivity() {
		return activity;
	}
	public void setActivity(int activity) {
		this.activity = activity;
	}
	public String getDetailRequest() {
		return detailRequest;
	}
	public void setDetailRequest(String detailRequest) {
		this.detailRequest = detailRequest;
	}
	public int getConditionActivity() {
		return conditionActivity;
	}
	public void setConditionActivity(int conditionActivity) {
		this.conditionActivity = conditionActivity;
	}

}
