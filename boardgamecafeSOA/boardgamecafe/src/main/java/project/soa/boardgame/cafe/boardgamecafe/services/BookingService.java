package project.soa.boardgame.cafe.boardgamecafe.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.soa.boardgame.cafe.boardgamecafe.model.Booking;
import project.soa.boardgame.cafe.boardgamecafe.repository.BookingRepository;

@Service
public class BookingService {
	
	@Autowired
	private BookingRepository bookingRepository;
	
	public List<Booking> findAll(){
		return (List<Booking>) bookingRepository.findAll();
	}
	
	public Booking findById(int bookingId) {
		return bookingRepository.findById(bookingId).get();
	}
	
	public void save(Booking booking) {
		bookingRepository.save(booking);
	}
	
	public void deleteById(int bookingId) {
		bookingRepository.deleteById(bookingId);
	}
	
	public List<Booking> findByDate(Date date){
		return bookingRepository.findByDate(date);
	}
}
