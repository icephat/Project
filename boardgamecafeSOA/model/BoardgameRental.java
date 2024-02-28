package project.soa.boardgame.cafe.boardgamecafe.model;
// Generated Mar 16, 2023, 3:22:57 AM by Hibernate Tools 5.6.3.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Boardgamerental generated by hbm2java
 */
public class BoardgameRental implements java.io.Serializable {

	private Integer boardgamerentalId;
	private User user;
	private Date datestart;
	private Date dateend;
	private String name;
	private String tel;
	private Set boardgameRenteds = new HashSet(0);

	public BoardgameRental() {
	}

	public BoardgameRental(User user, Date datestart, Date dateend, String name, String tel) {
		this.user = user;
		this.datestart = datestart;
		this.dateend = dateend;
		this.name = name;
		this.tel = tel;
	}

	public BoardgameRental(User user, Date datestart, Date dateend, String name, String tel, Set boardgamerenteds) {
		this.user = user;
		this.datestart = datestart;
		this.dateend = dateend;
		this.name = name;
		this.tel = tel;
		this.boardgameRenteds = boardgamerenteds;
	}

	public Integer getBoardgamerentalId() {
		return this.boardgamerentalId;
	}

	public void setBoardgamerentalId(Integer boardgamerentalId) {
		this.boardgamerentalId = boardgamerentalId;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getDatestart() {
		return this.datestart;
	}

	public void setDatestart(Date datestart) {
		this.datestart = datestart;
	}

	public Date getDateend() {
		return this.dateend;
	}

	public void setDateend(Date dateend) {
		this.dateend = dateend;
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

	public Set getBoardgameRenteds() {
		return this.boardgameRenteds;
	}

	public void setBoardgameRenteds(Set boardgamerenteds) {
		this.boardgameRenteds = boardgamerenteds;
	}

}