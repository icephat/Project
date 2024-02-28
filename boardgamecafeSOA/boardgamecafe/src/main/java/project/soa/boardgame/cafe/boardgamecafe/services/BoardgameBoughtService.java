package project.soa.boardgame.cafe.boardgamecafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.soa.boardgame.cafe.boardgamecafe.model.BoardgameBought;
import project.soa.boardgame.cafe.boardgamecafe.repository.BoardgameBoughtRepository;

@Service
public class BoardgameBoughtService {

	@Autowired
	BoardgameBoughtRepository boardgameBoughtRepository;
	
	public List<BoardgameBought> findAll(){
		return (List<BoardgameBought>) boardgameBoughtRepository.findAll();
	}
	
	public BoardgameBought findById(int boardgameBoughtId) {
		return boardgameBoughtRepository.findById(boardgameBoughtId).get();
	}
	
	public void save(BoardgameBought boardgameBought) {
		boardgameBoughtRepository.save(boardgameBought);
	}
	
	public void deleteById(int boardgameBoughtId) {
		boardgameBoughtRepository.deleteById(boardgameBoughtId);
	}
	
	
}
