package project.soa.boardgame.cafe.boardgamecafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.soa.boardgame.cafe.boardgamecafe.model.BoardgameRental;
import project.soa.boardgame.cafe.boardgamecafe.model.BoardgameRented;
import project.soa.boardgame.cafe.boardgamecafe.model.Booking;
import project.soa.boardgame.cafe.boardgamecafe.repository.BoardgameRentedRepository;
import project.soa.boardgame.cafe.boardgamecafe.repository.BoardgamerentalRepository;
import project.soa.boardgame.cafe.boardgamecafe.repository.BookingRepository;

@Service
public class BoardgameRentedService{
	
	@Autowired
	private BoardgameRentedRepository boardgameRentedRepository;
	
	
	public List<BoardgameRented> findAll(){
		return (List<BoardgameRented>) boardgameRentedRepository.findAll();
	}
	
	public BoardgameRented findById(int boardgamerentedId) {
		return boardgameRentedRepository.findById(boardgamerentedId).get();
	}
	
	public void save(BoardgameRented boardgameRented) {
		boardgameRentedRepository.save(boardgameRented);
	}
	
	public void deleteById(int boardgamerentedId) {
		boardgameRentedRepository.deleteById(boardgamerentedId);
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
