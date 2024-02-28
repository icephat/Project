package ku.kps.ds.som.kukpssom.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ku.kps.ds.som.kukpssom.model.OrderFood;
import ku.kps.ds.som.kukpssom.model.QueueOrder;
import ku.kps.ds.som.kukpssom.model.Store;
import ku.kps.ds.som.kukpssom.service.OrderFoodService;
import ku.kps.ds.som.kukpssom.service.QueueOrderService;
import ku.kps.ds.som.kukpssom.service.StoreService;

@Controller
public class QueueOrderController {

	@Autowired
	private QueueOrderService queueOrderService;

	@Autowired
	private OrderFoodService orderFoodService;

	@Autowired
	private StoreService storeService;

	@RequestMapping(value = "/queue/{storeId}", method = RequestMethod.GET)
	public String queueInOrder(Model model, @PathVariable("storeId") int storeId) {

		Store store = storeService.findById(storeId);

		List<QueueOrder> queueOrders = queueOrderService.findByDateAndStoreId(new Date(), storeId);

		model.addAttribute("queueOrders", queueOrders);
		model.addAttribute("store", store);

		return "queueorder/orderinstore";

	}
}
