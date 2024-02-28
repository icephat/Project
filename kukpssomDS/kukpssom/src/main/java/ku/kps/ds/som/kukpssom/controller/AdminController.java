package ku.kps.ds.som.kukpssom.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ku.kps.ds.som.kukpssom.model.Store;
import ku.kps.ds.som.kukpssom.model.User;
import ku.kps.ds.som.kukpssom.model.dto.StoreDTO;
import ku.kps.ds.som.kukpssom.service.StoreService;
import ku.kps.ds.som.kukpssom.service.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	private StoreService storeService;

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String adminIndex(Model model) {

		List<Store> stores = storeService.findAll();

		model.addAttribute("stores", stores);

		return "admin/index";
	}

	@RequestMapping(value = "/addStore", method = RequestMethod.GET)
	public String adminAddStore(Model model) {

		return "admin/addstore";
	}

	@RequestMapping(value = "/createStore", method = RequestMethod.POST)
	public String createStore(Model model, @ModelAttribute("storeDTO") StoreDTO storeDTO) {

		User user = new User();
		user.setName(storeDTO.getName());
		user.setSuename(storeDTO.getSurname());
		user.setRole("seller");
		String pwd = String.valueOf(storeDTO.getPassword().hashCode());

		user.setPassword(pwd);
		user.setUsername(storeDTO.getUsername());

		userService.save(user);

		Store store = new Store(user, storeDTO.getStoreName(), storeDTO.getOpen(), storeDTO.getClose(), "open",
				storeDTO.getType());
		
		storeService.save(store);

		return "redirect:/admin/home";
	}

}
