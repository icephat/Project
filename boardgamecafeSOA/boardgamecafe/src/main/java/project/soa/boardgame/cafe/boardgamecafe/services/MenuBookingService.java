package project.soa.boardgame.cafe.boardgamecafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.soa.boardgame.cafe.boardgamecafe.model.ManuBooking;
import project.soa.boardgame.cafe.boardgamecafe.repository.MenuBookingRepository;

@Service
public class MenuBookingService {

	@Autowired
	private MenuBookingRepository menuBookingRepository;
	
	public List<ManuBooking> findAll(){
		return (List<ManuBooking>) menuBookingRepository.findAll();
	}
	
	public ManuBooking findById(int manuBookingId) {
		return menuBookingRepository.findById(manuBookingId).get();
	}
	
	public void save(ManuBooking manuBooking ) {
		menuBookingRepository.save(manuBooking);
	}
	
	public void deleteById(int bookingId) {
		menuBookingRepository.deleteById(bookingId);
	}
}
