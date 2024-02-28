package ku.kps.ds.som.kukpssom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ku.kps.ds.som.kukpssom.model.OrderFood;
import ku.kps.ds.som.kukpssom.repository.OrderFoodRepository;



@Service
public class OerderFoodService {

	@Autowired
	private OrderFoodRepository orderFoodRepository;

	public void save(OrderFood orderFood) {
		orderFoodRepository.save(orderFood);
	}

	public void delete(int id) {
		orderFoodRepository.deleteById(id);
	}
	
	public OrderFood findById(int id) {
		return orderFoodRepository.findById(id).get();
	}
	
	public List<OrderFood> findAll() {
		return (List<OrderFood>) orderFoodRepository.findAll();
		
	}
}
