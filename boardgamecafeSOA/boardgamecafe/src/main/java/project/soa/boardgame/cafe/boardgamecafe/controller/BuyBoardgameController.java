package project.soa.boardgame.cafe.boardgamecafe.controller;

import java.util.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.soa.boardgame.cafe.boardgamecafe.model.Boardgame;
import project.soa.boardgame.cafe.boardgamecafe.model.BoardgameBought;
import project.soa.boardgame.cafe.boardgamecafe.model.BuyBoardgame;
import project.soa.boardgame.cafe.boardgamecafe.services.BoardgameBoughtService;
import project.soa.boardgame.cafe.boardgamecafe.services.BoardgameService;
import project.soa.boardgame.cafe.boardgamecafe.services.BuyBoardgameService;


@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class BuyBoardgameController {
	
	
	@Autowired
	BuyBoardgameService buyBoardgameService;
	
	
	
	@Autowired
	BoardgameService boardgameService;
	
	@Autowired
	BoardgameBoughtService boardgameBoughtService;
	

	@GetMapping("/buyboardgames")
	public List<BuyBoardgame> AllBuyBoardgame(){
		return (List<BuyBoardgame>) buyBoardgameService.findAll();
	}
	
	
	@PostMapping("/buyboardgame")
	public void addBuyBoardgame(@RequestBody BuyBoardgame buyBoardgame,@PathParam("boardgameId")int boardgameId) {
		
		Boardgame boardgame = boardgameService.findById(boardgameId);
		BoardgameBought bought = new BoardgameBought(boardgame,buyBoardgame);
		buyBoardgame.setDate(new Date());
		buyBoardgame.setTransferslip("https://s359.kapook.com/pagebuilder/ba154685-db18-4ac7-b318-a4a2b15b9d4c.jpg");
		buyBoardgameService.save(buyBoardgame);
		boardgameBoughtService.save(bought);

	}
	
	@DeleteMapping("/buyboardgame/{buyboardgameId}")
	public void deleteBuyBoardgame(@PathVariable("buyboardgameId") int buyboardgameId) {
		buyBoardgameService.deleteById(buyboardgameId);
	}
	


}
