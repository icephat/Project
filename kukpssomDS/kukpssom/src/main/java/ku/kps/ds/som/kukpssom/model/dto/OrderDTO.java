package ku.kps.ds.som.kukpssom.model.dto;

import ku.kps.ds.som.kukpssom.model.Menu;
import ku.kps.ds.som.kukpssom.model.OrderFood;

public class OrderDTO {
	
	private int menuId;
	private int price;
	private String level;
	private String addOn;
	private String note;
	
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getAddOn() {
		return addOn;
	}
	public void setAddOn(String addOn) {
		this.addOn = addOn;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}


}
