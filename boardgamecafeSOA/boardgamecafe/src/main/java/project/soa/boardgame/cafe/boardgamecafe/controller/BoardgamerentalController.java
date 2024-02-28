package project.soa.boardgame.cafe.boardgamecafe.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.soa.boardgame.cafe.boardgamecafe.model.Boardgame;
import project.soa.boardgame.cafe.boardgamecafe.model.BoardgameRental;
import project.soa.boardgame.cafe.boardgamecafe.model.BoardgameRented;
import project.soa.boardgame.cafe.boardgamecafe.model.Table;

import project.soa.boardgame.cafe.boardgamecafe.repository.BoardgamerentalRepository;
import project.soa.boardgame.cafe.boardgamecafe.services.BoardgameRentedService;
import project.soa.boardgame.cafe.boardgamecafe.services.BoardgameService;
import project.soa.boardgame.cafe.boardgamecafe.services.BoardgamerentalService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class BoardgamerentalController {
	
	@Autowired
	BoardgamerentalService boardgamerentalService;
	

	
	@Autowired
	BoardgameService boardgameService;
	
	@Autowired
	BoardgameRentedService boardgameRentedService;
	
	
	@GetMapping("/boardgamerental")
	public List<BoardgameRental> allBoardgameRental(){
		//System.out.println("doGet");
		return (List<BoardgameRental>) boardgamerentalService.findAll();
	}
	
	@GetMapping("/boardgamerental/{boardgamerentalId}")
	public BoardgameRental getBoardgameRentalById(@PathVariable("boardgamerentalId")int boardgamerentalId) {
		return boardgamerentalService.findById(boardgamerentalId);
	}
	
	@PostMapping("/boardgamerental")
	public void addBoardgameRental(@RequestBody BoardgameRental boardgameRental,@PathParam("boardgameId")int boardgameId) {
		
		//User user = userService.findById(1);
		//boardgameRental.setUser(user);
		Boardgame boardgame = boardgameService.findById(boardgameId);
		BoardgameRented rented = new BoardgameRented(boardgame,boardgameRental);
		boardgamerentalService.save(boardgameRental);
		boardgameRentedService.save(rented);

	}
	
	@DeleteMapping("/boardgamerental/{boardgamerentalId}")
	public void deleteBoardgameRental(@PathVariable("boardgamerentalId") int boardgamerentalId) {
		boardgamerentalService.deleteById(boardgamerentalId);
	}

}
