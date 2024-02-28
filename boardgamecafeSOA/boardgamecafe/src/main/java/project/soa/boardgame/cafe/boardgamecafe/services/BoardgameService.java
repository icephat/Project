package project.soa.boardgame.cafe.boardgamecafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.soa.boardgame.cafe.boardgamecafe.model.Boardgame;
import project.soa.boardgame.cafe.boardgamecafe.repository.BoardgameRepository;

@Service
public class BoardgameService {

	@Autowired
	BoardgameRepository boardgameRepository;
	
	public List<Boardgame> findAll(){
		return (List<Boardgame>) boardgameRepository.findAll();
	}
	
	public Boardgame findById(int boardgameId) {
		return boardgameRepository.findById(boardgameId).get();
	}
	
	public void save(Boardgame boardgame) {
		boardgameRepository.save(boardgame);
	}
	
	public void deleteById(int boardgameId) {
		boardgameRepository.deleteById(boardgameId);
	}
	
}
