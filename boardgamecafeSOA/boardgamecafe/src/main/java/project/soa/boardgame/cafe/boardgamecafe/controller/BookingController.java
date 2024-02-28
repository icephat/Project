package project.soa.boardgame.cafe.boardgamecafe.controller;

import java.sql.Date;
import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.soa.boardgame.cafe.boardgamecafe.model.Booking;
import project.soa.boardgame.cafe.boardgamecafe.model.Table;
import project.soa.boardgame.cafe.boardgamecafe.services.BookingService;
import project.soa.boardgame.cafe.boardgamecafe.services.TableService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class BookingController {

	@Autowired
	private BookingService bookingService;
	
	@Autowired
	private TableService tableService;
	
	@GetMapping("/bookings")
	public List<Booking> getAllBooking(){
		return bookingService.findAll();
	}
	
	@GetMapping("/bookings/{id}")
	public Booking getById(@PathVariable int id) {
		return bookingService.findById(id);
	}
	
	@GetMapping("/bookings/today")
	public List<Booking> getBookingInToDay(){
		long millis = System.currentTimeMillis();
		return bookingService.findByDate(new Date(millis));
	}
	
	@PostMapping("/bookings")
	public void addBooking(@RequestBody Booking booking,@PathParam("tableId")int tableId) {
		Table table = tableService.findById(tableId);
		
		booking.setTable(table);
		
		bookingService.save(booking);
		
		
		System.out.println(booking.getNumberbooking());
		
	}
	
	@PutMapping("/bookings/{bookingId}")
	public void updateBooking(@RequestBody Booking booking,@PathVariable("bookingId")int bookingId) {
		Booking booked = bookingService.findById(bookingId);
		booking.setBookingId(booked.getBookingId());
		bookingService.save(booking);
	}
	
	@DeleteMapping("/bookings/{bookingId}")
	public void deleteBooking(@PathVariable("bookingId")int bookingId) {
		bookingService.deleteById(bookingId);
	}
}
