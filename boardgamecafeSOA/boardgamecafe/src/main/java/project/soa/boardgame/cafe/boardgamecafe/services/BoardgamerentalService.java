package project.soa.boardgame.cafe.boardgamecafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.soa.boardgame.cafe.boardgamecafe.model.BoardgameRental;
import project.soa.boardgame.cafe.boardgamecafe.model.Booking;
import project.soa.boardgame.cafe.boardgamecafe.repository.BoardgamerentalRepository;
import project.soa.boardgame.cafe.boardgamecafe.repository.BookingRepository;

@Service
public class BoardgamerentalService{
	
	@Autowired
	private BoardgamerentalRepository boardgamerentalRepository;
	
	
	public List<BoardgameRental> findAll(){
		return (List<BoardgameRental>) boardgamerentalRepository.findAll();
	}
	
	public BoardgameRental findById(int boardgamerentalId) {
		return boardgamerentalRepository.findById(boardgamerentalId).get();
	}
	
	public void save(BoardgameRental boardgameRental) {
		boardgamerentalRepository.save(boardgameRental);
	}
	
	public void deleteById(int boardgamerentalId) {
		boardgamerentalRepository.deleteById(boardgamerentalId);
	}
	
//	public List<Booking> findAll(){
//		return (List<Booking>) bookingRepository.findAll();
//	}
//	
//	public Booking findById(int bookingId) {
//		return bookingRepository.findById(bookingId).get();
//	}
//	
//	public void save(Booking booking) {
//		bookingRepository.save(booking);
//	}
//	
//	public void deleteById(int bookingId) {
//		bookingRepository.deleteById(bookingId);
//	}
}
