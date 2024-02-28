package project.soa.boardgame.cafe.boardgamecafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.soa.boardgame.cafe.boardgamecafe.model.ManuBooking;
import project.soa.boardgame.cafe.boardgamecafe.model.Menu;
import project.soa.boardgame.cafe.boardgamecafe.repository.MenuBookingRepository;
import project.soa.boardgame.cafe.boardgamecafe.repository.MenuRepository;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepository ;
	
	public List<Menu> findAll(){
		return (List<Menu>) menuRepository.findAll();
	}
	
	public Menu findById(int menuId) {
		return menuRepository.findById(menuId).get();
	}
	
	public void save(Menu menu ) {
		menuRepository.save(menu);
	}
	
	public void deleteById(int menuId) {
		menuRepository.deleteById(menuId);
	}
}
