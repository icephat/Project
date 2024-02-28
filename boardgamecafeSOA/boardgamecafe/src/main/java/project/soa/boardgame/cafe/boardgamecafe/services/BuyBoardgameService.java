package project.soa.boardgame.cafe.boardgamecafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.soa.boardgame.cafe.boardgamecafe.model.BuyBoardgame;

import project.soa.boardgame.cafe.boardgamecafe.repository.BuyBoardgameRepository;

@Service
public class BuyBoardgameService {

	@Autowired
	private BuyBoardgameRepository buyBoardgameRepository;
	
	public List<BuyBoardgame> findAll(){
		return (List<BuyBoardgame>) buyBoardgameRepository.findAll();
	}
	
	public BuyBoardgame findById(int buyBoardgameId) {
		return buyBoardgameRepository.findById(buyBoardgameId).get();
	}
	
	public void save(BuyBoardgame buyBoardgame) {
		buyBoardgameRepository.save(buyBoardgame);
	}
	
	public void deleteById(int buyBoardgameId) {
		buyBoardgameRepository.deleteById(buyBoardgameId);
	}
}
