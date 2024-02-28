package project.soa.boardgame.cafe.boardgamecafe.controller;

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

import project.soa.boardgame.cafe.boardgamecafe.model.ManuBooking;
import project.soa.boardgame.cafe.boardgamecafe.model.Menu;
import project.soa.boardgame.cafe.boardgamecafe.services.MenuBookingService;
import project.soa.boardgame.cafe.boardgamecafe.services.MenuService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class MenuBookingController {

	@Autowired
	private MenuBookingService menuBookingService;
	@Autowired
	private MenuService menuService;
	
	
	@GetMapping("/menubooking")
	public List<ManuBooking> getAllManuBooking() {
		return menuBookingService.findAll();
	}
	
	@GetMapping("/menubooking/{menubookingId}")
	public ManuBooking getManuBookingById(@PathVariable("menubookingId")int menubookingId) {
		return menuBookingService.findById(menubookingId);
	}
	
	@PostMapping("/menubooking")
	public void createMenu(@RequestBody ManuBooking menuBooking,@PathParam("menuId")int menuId) {
		Menu menu = menuService.findById(menuId);
		menuBooking.setMenu(menu);
		menuBookingService.save(menuBooking);
	}
	
	@PutMapping("/menubooking/{menubookingId}")
	public void updateManuBooking(@PathVariable("menubookingId")int menubookingId,@RequestBody ManuBooking menuBooking) {
		ManuBooking manuBooking2 = menuBookingService.findById(menubookingId);
		manuBooking2.setName(menuBooking.getName());
		manuBooking2.setTel(menuBooking.getTel());
		menuBookingService.save(manuBooking2);
	}
	
	@DeleteMapping("/menubooking/{menubookingId}")
	public void deleteManuBooking(@PathVariable("menubookingId")int menubookingId) {
		menuBookingService.deleteById(menubookingId);
	}
}
