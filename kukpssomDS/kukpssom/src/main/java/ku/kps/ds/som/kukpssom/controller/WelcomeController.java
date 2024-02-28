package ku.kps.ds.som.kukpssom.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ku.kps.ds.som.kukpssom.model.Store;
import ku.kps.ds.som.kukpssom.model.User;
import ku.kps.ds.som.kukpssom.model.dto.LoginDTO;
import ku.kps.ds.som.kukpssom.service.StoreService;
import ku.kps.ds.som.kukpssom.service.UserService;

@Controller
public class WelcomeController {

	@Autowired
	private UserService userService;

	@Autowired
	private StoreService storeService;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Model model) {

		List<Store> stores = storeService.findAll();

		model.addAttribute("stores", stores);

		return "welcome/welcome";

	}

	@RequestMapping(value = "/home", method = RequestMethod.POST)
	public String login(Model model, HttpServletRequest request, @ModelAttribute("loginDTO") LoginDTO loginDTO) {

		User user = userService.findByUsername(loginDTO.getUsername());

		int pwd = loginDTO.getPassword().hashCode();

		if (user == null || pwd != Integer.parseInt(user.getPassword())) {

			
			return "redirect:/loginerror";
		}

		System.out.println(user.getName());

		HttpSession session = request.getSession();
		session.setAttribute("username", user.getUsername());
		session.setAttribute("role", user.getRole());
		session.setAttribute("name", user.getName());
		session.setAttribute("surname", user.getSuename());

		System.out.println(user.getRole());

		if (user.getRole().equals("admin")) {

			System.out.println("Hello");
			return "redirect:/admin/home";
		} else if (user.getRole().equals("customer")) {
			System.out.println("customer");
			return "redirect:/customer/";
		} else if (user.getRole().equals("seller")) {
			System.out.println("store");

			return "redirect:/store/home";
		}

		return "welcome/welcome";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {

		return "welcome/loginPage";
	}

	@RequestMapping(value = "/loginerror", method = RequestMethod.GET)
	public String loginError(Model model) {

		return "welcome/loginErrorPage";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session) {

		session.invalidate();

		return "redirect:/";
	}

}
