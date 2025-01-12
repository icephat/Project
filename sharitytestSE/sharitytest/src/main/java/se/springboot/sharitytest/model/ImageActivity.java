package se.springboot.sharitytest.model;
// Generated Feb 23, 2023, 5:00:50 PM by Hibernate Tools 5.6.3.Final

/**
 * Imageactivity generated by hbm2java
 */
public class ImageActivity implements java.io.Serializable {

	private Integer imageId;
	private Activity activity;
	private String imagePath;

	public ImageActivity() {
	}

	public ImageActivity(Activity activity, String imagePath) {
		this.activity = activity;
		this.imagePath = imagePath;
	}

	public Integer getImageId() {
		return this.imageId;
	}

	public void setImageId(Integer imageId) {
		this.imageId = imageId;
	}

	public Activity getActivity() {
		return this.activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getImagePath() {
		return this.imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

}
