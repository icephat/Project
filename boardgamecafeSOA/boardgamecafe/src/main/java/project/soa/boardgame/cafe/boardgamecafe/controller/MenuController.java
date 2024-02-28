package project.soa.boardgame.cafe.boardgamecafe.controller;

import java.util.List;

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

import project.soa.boardgame.cafe.boardgamecafe.model.Menu;
import project.soa.boardgame.cafe.boardgamecafe.model.Table;
import project.soa.boardgame.cafe.boardgamecafe.services.MenuService;
import project.soa.boardgame.cafe.boardgamecafe.services.TableService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class MenuController {

	@Autowired
	private MenuService menuService;
	
	@GetMapping("/menus")
	public List<Menu> getAllMenu() {
		return menuService.findAll();
	}
	
	@GetMapping("/menu/{menuId}")
	public Menu getMenuById(@PathVariable("menuId")int menuId) {
		return menuService.findById(menuId);
	}
	
	@PostMapping("/menu")
	public void createMenu(@RequestBody Menu menu) {
		menuService.save(menu);
	}
	
	@PutMapping("/menu/{menuId}")
	public void updateMenu(@RequestBody Menu menu,@PathVariable("menuId")int menuId) {
		Menu menu2 = menuService.findById(menuId);
		menu2.setImgPath(menu.getImgPath());
		menu2.setName(menu.getName());
		menu2.setPrice(menu.getPrice());
		menu2.setType(menu.getType());
		menuService.save(menu2);
	}
	
	@DeleteMapping("/menu/{menuId}")
	public void deleteMenu(@PathVariable("menuId")int menuId) {
		menuService.deleteById(menuId);
	}
	
}
