package project.soa.boardgame.cafe.boardgamecafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.soa.boardgame.cafe.boardgamecafe.model.Boardgame;
import project.soa.boardgame.cafe.boardgamecafe.services.BoardgameService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class BoardgameController {
	
	@Autowired
	BoardgameService boardgameService;
	
	@GetMapping("/boardgames")
	public List<Boardgame> AllBoardgame(){
		return (List<Boardgame>) boardgameService.findAll();
	}
	
	

}
