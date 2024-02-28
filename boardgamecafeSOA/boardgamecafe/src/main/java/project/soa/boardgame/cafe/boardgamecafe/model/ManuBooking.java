package project.soa.boardgame.cafe.boardgamecafe.model;
// Generated Apr 5, 2023, 12:47:56 AM by Hibernate Tools 5.6.3.Final

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Manubooking generated by hbm2java
 */
public class ManuBooking implements java.io.Serializable {

	private Integer manubookingId;
	@JsonIgnore
	private Menu menu;
	private String name;
	private String tel;

	public ManuBooking() {
	}

	public ManuBooking(Menu menu, String name, String tel) {
		this.menu = menu;
		this.name = name;
		this.tel = tel;
	}

	public Integer getManubookingId() {
		return this.manubookingId;
	}

	public void setManubookingId(Integer manubookingId) {
		this.manubookingId = manubookingId;
	}

	public Menu getMenu() {
		return this.menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

}