package ku.kps.ds.som.kukpssom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import ku.kps.ds.som.kukpssom.model.MenuInOrder;
import ku.kps.ds.som.kukpssom.repository.MenuInOrderRepository;

@Service
public class MenuInOrderService {

	@Autowired
	private MenuInOrderRepository menuInOrderRepository;

	public void save(MenuInOrder menuInOrder) {
		menuInOrderRepository.save(menuInOrder);
	}

	public void deleteById(int id) {
		menuInOrderRepository.deleteById(id);
	}

	public MenuInOrder findById(int id) {
		return menuInOrderRepository.findById(id).get();
	}

	public List<MenuInOrder> findAll() {
		return (List<MenuInOrder>) menuInOrderRepository.findAll();
	}

	public List<MenuInOrder> findByOrderCode(String orderCode) {
		return menuInOrderRepository.findByOrderCode(orderCode);
	}
}
