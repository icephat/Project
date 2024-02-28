package ku.kps.ds.som.kukpssom.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import ku.kps.ds.som.kukpssom.model.dto.MenuDTO;
import ku.kps.ds.som.kukpssom.model.dto.OrderDTO;
import ku.kps.ds.som.kukpssom.service.MenuInOrderService;
import ku.kps.ds.som.kukpssom.service.MenuService;
import ku.kps.ds.som.kukpssom.service.OrderFoodService;
import ku.kps.ds.som.kukpssom.service.QueueOrderService;
import ku.kps.ds.som.kukpssom.service.StoreService;
import ku.kps.ds.som.kukpssom.service.UserService;

@Controller
public class StoreController {

	@Autowired
	private UserService userService;

	@Autowired
	private StoreService storeService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private OrderFoodService orderFoodService;

	@Autowired
	private MenuInOrderService menuInOrderService;

	@Autowired
	QueueOrderService queueOrderService;

	@RequestMapping(value = "/store/home", method = RequestMethod.GET)
	public String storeIndex(Model model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		System.out.println(username);
		User user = userService.findByUsername(username);

		if (!user.getRole().equals("seller")) {
			return "error/error403";
		}

		Store store = storeService.findByUsername(username);

		List<Menu> menus = menuService.findByStoreId(store.getStoreId());
		List<String> status = new ArrayList<String>();

		status.add("waitOrder");
		status.add("waitCook");

		List<OrderFood> orderFoods = orderFoodService.findByDateAndListStatus(new Date(), status, store.getStoreId());
		int q = orderFoods.size();
		model.addAttribute("q", q);

		model.addAttribute("menus", menus);
		model.addAttribute("store", store);

		return "store/index";
	}

	@RequestMapping(value = "/store/addmenu", method = RequestMethod.GET)
	public String storeAddMenu(Model model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		User user = userService.findByUsername(username);
		if (!user.getRole().equals("seller")) {
			return "error/error403";
		}

		Store store = storeService.findByUsername(username);
		List<String> status = new ArrayList<String>();

		status.add("waitOrder");
		status.add("waitCook");

		List<OrderFood> orderFoods = orderFoodService.findByDateAndListStatus(new Date(), status, store.getStoreId());
		int q = orderFoods.size();
		model.addAttribute("q", q);

		model.addAttribute("store", store);
		return "store/addmenu";
	}

	@RequestMapping(value = "/store/createMenu", method = RequestMethod.POST)
	public String storeCreateMenu(Model model, HttpSession session, @ModelAttribute("menu") MenuDTO menuDTO) {

		String username = (String) session.getAttribute("username");
		User user = userService.findByUsername(username);
		if (!user.getRole().equals("seller")) {
			return "error/error403";
		}

		Store store = storeService.findById(menuDTO.getStoreId());
		List<String> status = new ArrayList<String>();

		status.add("waitOrder");
		status.add("waitCook");

		List<OrderFood> orderFoods = orderFoodService.findByDateAndListStatus(new Date(), status, store.getStoreId());
		int q = orderFoods.size();
		model.addAttribute("q", q);

		Menu menu = new Menu(store, menuDTO.getName(), Integer.parseInt(menuDTO.getPrice()));

		menuService.save(menu);

		return "redirect:/store/home";
	}

	@RequestMapping(value = "/store/editmenu/{menuId}", method = RequestMethod.GET)
	public String editMenu(@PathVariable("menuId") int menuId, Model model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		User user = userService.findByUsername(username);
		if (!user.getRole().equals("seller")) {
			return "error/error403";
		}

		Menu menu = menuService.findById(menuId);
		List<String> status = new ArrayList<String>();

		status.add("waitOrder");
		status.add("waitCook");

		Store store = storeService.findByUsername(username);

		List<OrderFood> orderFoods = orderFoodService.findByDateAndListStatus(new Date(), status, store.getStoreId());
		int q = orderFoods.size();
		model.addAttribute("q", q);

		model.addAttribute("menu", menu);

		return "store/editmenu";
	}

	@RequestMapping(value = "/store/updateMenu", method = RequestMethod.POST)
	public String storeUpdateMenu(Model model, HttpSession session, @ModelAttribute("menu") Menu menu) {

		String username = (String) session.getAttribute("username");
		User user = userService.findByUsername(username);
		if (!user.getRole().equals("seller")) {
			return "error/error403";
		}

		Menu menuUpdate = menuService.findById(menu.getMenuId());

		menuUpdate.setName(menu.getName());
		menuUpdate.setPrice(menu.getPrice());

		menuService.save(menuUpdate);

		return "redirect:/store/home";
	}

	@RequestMapping(value = "/store/deletemenu/{menuId}", method = RequestMethod.GET)
	public String storeUpdateMenu(Model model, HttpSession session, @PathVariable("menuId") int menuId) {

		String username = (String) session.getAttribute("username");
		User user = userService.findByUsername(username);
		if (!user.getRole().equals("seller")) {
			return "error/error403";
		}

		menuService.deleteById(menuId);

		return "redirect:/store/home";
	}

	@RequestMapping(value = "/store/orderlist", method = RequestMethod.GET)
	public String storeOrderList(Model model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		User user = userService.findByUsername(username);
		if (!user.getRole().equals("seller")) {
			return "error/error403";
		}

		System.out.println(new Date());

		List<String> status = new ArrayList<String>();

		status.add("waitOrder");
		status.add("waitCook");

		Store store = storeService.findByUsername(username);

		List<OrderFood> orderFoods = orderFoodService.findByDateAndListStatus(new Date(), status, store.getStoreId());
		int q = orderFoods.size();
		model.addAttribute("q", q);
		model.addAttribute("orderFoods", orderFoods);
		model.addAttribute("countOrder", orderFoods.size());
		model.addAttribute("store", store);

		return "store/orderlist";
	}

	@RequestMapping(value = "/store/{orderId}/{status}", method = RequestMethod.GET)
	public String changeOrderStatus(Model model, HttpSession session, @PathVariable("orderId") int orderId,
			@PathVariable("status") String status) {

		String username = (String) session.getAttribute("username");
		User user = userService.findByUsername(username);
		if (!user.getRole().equals("seller")) {
			return "error/error403";
		}

		if (status.equals("waitCook") || status.equals("success")) {

			Store store = storeService.findByUsername(username);

			OrderFood order = orderFoodService.findById(orderId);
			order.setStatus(status);
			orderFoodService.save(order);

			if (status.equals("waitCook")) {

				QueueOrder queueOrder = new QueueOrder();

				queueOrder.setOrderFood(order);
				queueOrder.setOrderCode(order.getOrderCode());

				List<String> statuss = new ArrayList<String>();

				statuss.add("waitCook");

				List<QueueOrder> queueOrders = queueOrderService.findByDateAndStoreId(new Date(), store.getStoreId());

				queueOrder.setOrderNumber(queueOrders.size());
				queueOrder.setTimeCount((queueOrders.size() + 1) * 5);
				order.setTimeCount(queueOrders.size());
				Date date = new Date();

				date.setMinutes(date.getMinutes() + queueOrder.getTimeCount());

				queueOrder.setTime(date);
				order.setTime(date);
				queueOrderService.save(queueOrder);
			} else if (status.equals("success")) {

				List<QueueOrder> queueOrders = queueOrderService.findByDateAndStoreId(new Date(), store.getStoreId());

				for (QueueOrder queueOrder : queueOrders) {
					String od = queueOrder.getOrderCode();

					if (od.equals(order.getOrderCode())) {
						queueOrderService.delete(queueOrder.getQueueOrderId());
						break;
					}
				}

				queueOrders = queueOrderService.findByDateAndStoreId(new Date(), store.getStoreId());

				for (QueueOrder queueOrder : queueOrders) {

					OrderFood of = queueOrder.getOrderFood();

					queueOrder.setOrderNumber(queueOrder.getOrderNumber() - 1);
					queueOrder.setTimeCount((queueOrder.getOrderNumber() + 1) * 5);
					of.setTimeCount(queueOrders.size());
					Date date = new Date();

					date.setMinutes(date.getMinutes() + queueOrder.getTimeCount());

					queueOrder.setTime(date);
					of.setTime(date);
					queueOrderService.save(queueOrder);
					orderFoodService.save(of);

				}

				if (store.getUser() == order.getUser()) {
					order.setStatus("finish");
					orderFoodService.save(order);
				}

			}
		}
		List<String> status1 = new ArrayList<String>();

		status1.add("waitOrder");
		status1.add("waitCook");

		Store store = storeService.findByUsername(username);
		List<OrderFood> orderFoods = orderFoodService.findByDateAndListStatus(new Date(), status1, store.getStoreId());
		int q = orderFoods.size();
		model.addAttribute("q", q);

		return "redirect:/store/orderlist";
	}

	@RequestMapping(value = "/store/orderfront", method = RequestMethod.GET)
	public String storeOrderFront(Model model, HttpSession session) {

		String username = (String) session.getAttribute("username");
		User user = userService.findByUsername(username);
		if (!user.getRole().equals("seller")) {
			return "error/error403";
		}

		Store store = storeService.findByUsername(username);

		List<Menu> menus = menuService.findByStoreId(store.getStoreId());
		List<String> status1 = new ArrayList<String>();

		status1.add("waitOrder");
		status1.add("waitCook");
		List<OrderFood> orderFoods = orderFoodService.findByDateAndListStatus(new Date(), status1, store.getStoreId());
		int q = orderFoods.size();
		model.addAttribute("q", q);

		model.addAttribute("menus", menus);
		model.addAttribute("store", store);

		return "store/orderfront";
	}

	@RequestMapping(value = "/store/orderfront/{menuId}", method = RequestMethod.GET)
	public String orderMenuFront(Model model, HttpSession session, @PathVariable("menuId") int menuId) {

		String username = (String) session.getAttribute("username");
		User user = userService.findByUsername(username);
		if (!user.getRole().equals("seller")) {
			return "error/error403";
		}

		Menu menu = menuService.findById(menuId);

		Store store = menu.getStore();
		List<String> status1 = new ArrayList<String>();

		status1.add("waitOrder");
		status1.add("waitCook");

		List<OrderFood> orderFoods = orderFoodService.findByDateAndListStatus(new Date(), status1, store.getStoreId());
		int q = orderFoods.size();
		model.addAttribute("q", q);

		model.addAttribute("menu", menu);
		model.addAttribute("store", store);

		return "store/ordermenu";
	}

	@RequestMapping(value = "/store/createOrder", method = RequestMethod.POST)
	public String createOrder(HttpSession session, Model model, @ModelAttribute("orderDTO") OrderDTO orderDTO) {

		String username = (String) session.getAttribute("username");
		User user = userService.findByUsername(username);
		if (!user.getRole().equals("seller")) {
			return "error/error403";
		}

		Menu menu = menuService.findById(orderDTO.getMenuId());
		Store store = menu.getStore();

		OrderFood orderFood = new OrderFood();
		orderFood.setDate(new Date());
		orderFood.setNote(orderDTO.getNote());
		orderFood.setStatus("waitCook");
		orderFood.setStoreId(store.getStoreId());
		orderFood.setTime(new Date());
		orderFood.setTimeCount(25);
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

		orderFoodService.save(orderFood);
		menuInOrderService.save(menuInOrder);

		QueueOrder queueOrder = new QueueOrder();

		queueOrder.setOrderFood(orderFood);
		queueOrder.setOrderCode(orderFood.getOrderCode());

		List<String> status = new ArrayList<String>();

		status.add("waitCook");
		List<OrderFood> orderFoods = orderFoodService.findByDateAndListStatus(new Date(), status, store.getStoreId());

		List<QueueOrder> queueOrders = queueOrderService.findByDateAndStoreId(new Date(), store.getStoreId());

		queueOrder.setOrderNumber(queueOrders.size());
		queueOrder.setTimeCount((queueOrders.size() + 1) * 5);
		Date date = new Date();

		date.setMinutes(date.getMinutes() + queueOrder.getTimeCount());

		queueOrder.setTime(date);

		queueOrderService.save(queueOrder);

		return "redirect:/store/orderlist";
	}

	@RequestMapping(value = "/store/ordertoday", method = RequestMethod.GET)
	public String orderToday(HttpSession session, Model model) {

		String username = (String) session.getAttribute("username");
		User user = userService.findByUsername(username);
		if (!user.getRole().equals("seller")) {
			return "error/error403";
		}

		List<String> status = new ArrayList<String>();

		Store store = storeService.findByUsername(username);

		int price = 0;
		int count = 0;

		status.add("finish");

		List<OrderFood> orderFoods = orderFoodService.findByDateAndListStatus(new Date(), status, store.getStoreId());

		for (OrderFood order : orderFoods) {

			for (MenuInOrder menuInOrder : order.getMenuInOrders()) {
				count++;
			}

			price += order.getPrice();
		}
		List<String> status1 = new ArrayList<String>();

		status1.add("waitOrder");
		status1.add("waitCook");

		List<OrderFood> orderFoods1 = orderFoodService.findByDateAndListStatus(new Date(), status1, store.getStoreId());
		int q = orderFoods1.size();
		model.addAttribute("q", q);
		model.addAttribute("price", price);
		model.addAttribute("count", count);
		model.addAttribute("orderFoods", orderFoods);

		return "store/ordertoday";
	}

	@RequestMapping(value = "/store/infomation", method = RequestMethod.GET)
	public String storeInfo(HttpSession session, Model model) {

		String username = (String) session.getAttribute("username");
		User user = userService.findByUsername(username);
		if (!user.getRole().equals("seller")) {
			return "error/error403";
		}

		Store store = storeService.findByUsername(username);
		List<String> status1 = new ArrayList<String>();

		status1.add("waitOrder");
		status1.add("waitCook");

		List<OrderFood> orderFoods = orderFoodService.findByDateAndListStatus(new Date(), status1, store.getStoreId());
		int q = orderFoods.size();
		model.addAttribute("q", q);

		model.addAttribute("store", store);

		return "store/info";
	}

	@RequestMapping(value = "/store/edit", method = RequestMethod.GET)
	public String storeEdit(HttpSession session, Model model) {

		String username = (String) session.getAttribute("username");
		User user = userService.findByUsername(username);
		if (!user.getRole().equals("seller")) {
			return "error/error403";
		}

		Store store = storeService.findByUsername(username);
		List<String> status1 = new ArrayList<String>();

		status1.add("waitOrder");
		status1.add("waitCook");

		List<OrderFood> orderFoods = orderFoodService.findByDateAndListStatus(new Date(), status1, store.getStoreId());
		int q = orderFoods.size();
		model.addAttribute("q", q);

		model.addAttribute("store", store);

		return "store/edit";
	}

	@RequestMapping(value = "/store/update", method = RequestMethod.POST)
	public String storeUpdate(HttpSession session, Model model, @ModelAttribute("store") Store store) {

		String username = (String) session.getAttribute("username");
		User user = userService.findByUsername(username);
		if (!user.getRole().equals("seller")) {
			return "error/error403";
		}
		Store storeR = storeService.findByUsername(username);
		List<String> status1 = new ArrayList<String>();

		status1.add("waitOrder");
		status1.add("waitCook");

		List<OrderFood> orderFoods = orderFoodService.findByDateAndListStatus(new Date(), status1, store.getStoreId());
		int q = orderFoods.size();
		model.addAttribute("q", q);

		storeR.setName(store.getName());
		storeR.setTimeOpen(store.getTimeOpen());
		storeR.setTimeClose(store.getTimeClose());

		storeService.save(storeR);

		return "redirect:/store/infomation";
	}

}
