package project.soa.boardgame.cafe.boardgamecafe.model;
// Generated Mar 16, 2023, 3:22:57 AM by Hibernate Tools 5.6.3.Final

/**
 * Boardgamebought generated by hbm2java
 */
public class BoardgameBought implements java.io.Serializable {

	private Integer boardgameboughtId;
	private Boardgame boardgame;
	private BuyBoardgame buyBoardgame;

	public BoardgameBought() {
	}

	public BoardgameBought(Boardgame boardgame, BuyBoardgame buyBoardgame) {
		this.boardgame = boardgame;
		this.buyBoardgame = buyBoardgame;
	}

	public Integer getBoardgameboughtId() {
		return this.boardgameboughtId;
	}

	public void setBoardgameboughtId(Integer boardgameboughtId) {
		this.boardgameboughtId = boardgameboughtId;
	}

	public Boardgame getBoardgame() {
		return this.boardgame;
	}

	public void setBoardgame(Boardgame boardgame) {
		this.boardgame = boardgame;
	}

	public BuyBoardgame getBuyBoardgame() {
		return this.buyBoardgame;
	}

	public void setBuyBoardgame(BuyBoardgame buyBoardgame) {
		this.buyBoardgame = buyBoardgame;
	}

}