package project.soa.boardgame.cafe.boardgamecafe.model;
// Generated Mar 16, 2023, 3:22:57 AM by Hibernate Tools 5.6.3.Final

/**
 * Reservedgameboard generated by hbm2java
 */
public class ReservedGameboard implements java.io.Serializable {

	private Integer reservedgameboardId;
	private Boardgame boardgame;
	private Booking booking;

	public ReservedGameboard() {
	}

	public ReservedGameboard(Boardgame boardgame, Booking booking) {
		this.boardgame = boardgame;
		this.booking = booking;
	}

	public Integer getReservedgameboardId() {
		return this.reservedgameboardId;
	}

	public void setReservedgameboardId(Integer reservedgameboardId) {
		this.reservedgameboardId = reservedgameboardId;
	}

	public Boardgame getBoardgame() {
		return this.boardgame;
	}

	public void setBoardgame(Boardgame boardgame) {
		this.boardgame = boardgame;
	}

	public Booking getBooking() {
		return this.booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

}
