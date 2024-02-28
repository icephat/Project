package ku.kps.ds.som.kukpssom.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ku.kps.ds.som.kukpssom.model.Menu;
import ku.kps.ds.som.kukpssom.model.MenuInOrder;
import ku.kps.ds.som.kukpssom.model.OrderFood;
import ku.kps.ds.som.kukpssom.model.QueueOrder;
import ku.kps.ds.som.kukpssom.model.Store;
import ku.kps.ds.som.kukpssom.model.User;
import ku.kps.ds.som.kukpssom.model.dto.OrderAndStoreDTO;
import ku.kps.ds.som.kukpssom.model.dto.OrderDTO;
import ku.kps.ds.som.kukpssom.model.dto.StoreDTO;
import ku.kps.ds.som.kukpssom.service.MenuInOrderService;
import ku.kps.ds.som.kukpssom.service.MenuService;
import ku.kps.ds.som.kukpssom.service.OrderFoodService;
import ku.kps.ds.som.kukpssom.service.QueueOrderService;
import ku.kps.ds.som.kukpssom.service.StoreService;
import ku.kps.ds.som.kukpssom.service.UserService;

@Controller
public class CustomerController {

	@Autowired
	StoreService storeService;

	@Autowired
	MenuService menuService;

	@Autowired
	UserService userService;

	@Autowired
	OrderFoodService foodService;

	@Autowired
	MenuInOrderService menuInOrderService;

	@Autowired
	QueueOrderService queueOrderService;

	@RequestMapping(value = "/customer/", method = RequestMethod.GET)
	public String index(Model model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		System.out.println(username);
		User user = userService.findByUsername(username);

		if (!user.getRole().equals("customer")) {
			return "error/error403";
		}
		List<Store> stores = storeService.findByStatus("Open");
		model.addAttribute("stores", stores);
		System.out.println("check");

		return "customer/index";

	}

	@RequestMapping(value = "/{id}/menu", method = RequestMethod.GET)
	public String showMenu(HttpSession session, @PathVariable int id, Model model) {

		String username = (String) session.getAttribute("username");
		System.out.println(username);
		User user = userService.findByUsername(username);

		if (!user.getRole().equals("customer")) {
			return "error/error403";
		}

		Store store = storeService.findById(id);

		List<Menu> menus = menuService.findByStoreName(store.getName());
		model.addAttribute("menus", menus);
		model.addAttribute("store", store);
		return "customer/menu";

	}

	@RequestMapping(value = "/{id}/detail", method = RequestMethod.GET)
	public String showdetail(HttpSession session, @PathVariable int id, Model model) {

		String username = (String) session.getAttribute("username");
		System.out.println(username);
		User user = userService.findByUsername(username);

		if (!user.getRole().equals("customer")) {
			return "error/error403";
		}

		Menu menu = menuService.findById(id);
		Store store = menu.getStore();

		model.addAttribute("store", store);
		model.addAttribute("menu", menu);

		return "customer/menudetail";

	}

	@RequestMapping(value = "/customer/createOrder", method = RequestMethod.POST)
	public String createOrder(HttpSession session, Model model, @ModelAttribute("orderDTO") OrderDTO orderDTO) {

		String username = (String) session.getAttribute("username");
		System.out.println(username);
		User user = userService.findByUsername(username);

		if (!user.getRole().equals("customer")) {
			return "error/error403";
		}

		Menu menu = menuService.findById(orderDTO.getMenuId());
		Store store = menu.getStore();

		OrderFood orderFood = new OrderFood();
		orderFood.setDate(new Date());
		orderFood.setNote(orderDTO.getNote());
		orderFood.setStatus("waitOrder");
		orderFood.setStoreId(store.getStoreId());
		orderFood.setTime(new Date());
		orderFood.setTimeCount(-1);
		orderFood.setUser(user);
		String randomStr = RandomStringUtils.randomAlphabetic(2);
		String randomnum = RandomStringUtils.randomNumeric(3);
		orderFood.setOrderCode(randomStr + randomnum);

		MenuInOrder menuInOrder = new MenuInOrder();
		menuInOrder.setLevel(orderDTO.getLevel());
		menuInOrder.setMenu(menu);
		menuInOrder.setOrderFood(orderFood);
		menuInOrder.setAddOn(orderDTO.getAddOn());
		orderFood.setPrice(orderDTO.getPrice());
		menuInOrder.setPrice(orderDTO.getPrice());

		if (!menuInOrder.getAddOn().equals("none")) {
			orderFood.setPrice(orderDTO.getPrice() + 7);
			menuInOrder.setPrice(orderDTO.getPrice() + 7);

		}

		if (menuInOrder.getLevel().equals("extra")) {
			orderFood.setPrice(orderDTO.getPrice() + 5);
			menuInOrder.setPrice(menuInOrder.getPrice() + 5);
		}

		foodService.save(orderFood);
		menuInOrderService.save(menuInOrder);

		return "redirect:/customer/order";
	}

	@RequestMapping(value = "/customer/{id}/order", method = RequestMethod.GET)
	public String showOrder(HttpSession session, @PathVariable int id, Model model) {

		String username = (String) session.getAttribute("username");
		System.out.println(username);
		User user = userService.findByUsername(username);

		if (!user.getRole().equals("customer")) {
			return "error/error403";
		}
		MenuInOrder menuInOrder = menuInOrderService.findById(id);
		OrderFood orderFood = foodService.findById(menuInOrder.getOrderFood().getOrderId());
		String add;
		if (menuInOrder.getAddOn().equals("none")) {
			add = "ไม่มี";
		} else if (menuInOrder.getAddOn().equals("omelet")) {
			add = "ไข่เจียว";
		} else {
			add = "ไข่ดาว";
		}

		model.addAttribute("add", add);
		model.addAttribute("orderFood", orderFood);
		model.addAttribute("menuInOrder", menuInOrder);

		return "redirect:/customer/order";

	}

	@RequestMapping(value = "/customer/order", method = RequestMethod.GET)
	public String orderList(HttpSession session, Model model) {

		String username = (String) session.getAttribute("username");
		User user = userService.findByUsername(username);

		if (!user.getRole().equals("customer")) {
			return "error/error403";
		}
		List<String> status = new ArrayList<String>();

		status.add("waitOrder");
		status.add("waitCook");
		status.add("success");

		List<OrderFood> orders = foodService.findByUserIdAndListStatus(user.getUserId(), status);

		List<OrderAndStoreDTO> orderStores = new ArrayList<OrderAndStoreDTO>();
		for (OrderFood order : orders) {
			Store store = storeService.findById(order.getStoreId());

			OrderAndStoreDTO os = new OrderAndStoreDTO();

			os.setOrder(order);
			os.setStore(store);

			orderStores.add(os);
		}

		model.addAttribute("orders", orderStores);

		return "customer/orderlist";
	}

	@RequestMapping(value = "/customer/{orderId}/{status}", method = RequestMethod.GET)
	public String changeStatus(HttpSession session, Model model, @PathVariable("orderId") int orderId,
			@PathVariable("status") String status) {

		String username = (String) session.getAttribute("username");
		System.out.println(username);
		User user = userService.findByUsername(username);

		if (!user.getRole().equals("customer")) {
			return "error/error403";
		}
		if (status.equals("cancle") || status.equals("finish")) {

			OrderFood orderFood = foodService.findById(orderId);

			if (status.equals("cancle")) {
				foodService.delete(orderId);
			} else {
				orderFood.setStatus(status);
				foodService.save(orderFood);
			}
		}

		return "redirect:/customer/order";
	}

}
