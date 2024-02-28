package project.soa.boardgame.cafe.boardgamecafe.model;
// Generated Apr 5, 2023, 12:47:56 AM by Hibernate Tools 5.6.3.Final

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Boardgamerented generated by hbm2java
 */
public class BoardgameRented implements java.io.Serializable {

	private Integer boardgamerentedId;
	@JsonIgnore
	private Boardgame boardgame;
	@JsonIgnore
	private BoardgameRental boardgameRental;
	private float rent;
	private float deposit;

	public BoardgameRented() {
	}
	public BoardgameRented(Boardgame boardgame, BoardgameRental boardgameRental) {
		this.boardgame = boardgame;
		this.boardgameRental = boardgameRental;
		this.rent = (float)0.2*boardgame.getPrice();
		this.deposit = (float)0.5*boardgame.getPrice();
	}
	public BoardgameRented(Boardgame boardgame, BoardgameRental boardgameRental, float rent, float deposit) {
		this.boardgame = boardgame;
		this.boardgameRental = boardgameRental;
		this.rent = rent;
		this.deposit = deposit;
	}

	public Integer getBoardgamerentedId() {
		return this.boardgamerentedId;
	}

	public void setBoardgamerentedId(Integer boardgamerentedId) {
		this.boardgamerentedId = boardgamerentedId;
	}

	public Boardgame getBoardgame() {
		return this.boardgame;
	}

	public void setBoardgame(Boardgame boardgame) {
		this.boardgame = boardgame;
	}

	public BoardgameRental getBoardgamerental() {
		return this.boardgameRental;
	}

	public void setBoardgamerental(BoardgameRental boardgameRental) {
		this.boardgameRental = boardgameRental;
	}

	public float getRent() {
		return this.rent;
	}

	public void setRent(float rent) {
		this.rent = rent;
	}

	public float getDeposit() {
		return this.deposit;
	}

	public void setDeposit(float deposit) {
		this.deposit = deposit;
	}

}