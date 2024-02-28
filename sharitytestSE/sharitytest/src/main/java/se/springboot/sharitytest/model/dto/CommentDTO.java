package se.springboot.sharitytest.model.dto;

public class CommentDTO {
	private int activity;
    private String detail;

	public int getActivity() {
		return activity;
	}
	public void setActivity(int activity) {
		this.activity = activity;
	}

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
