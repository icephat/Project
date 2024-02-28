package ku.kps.ds.som.kukpssom.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import ku.kps.ds.som.kukpssom.model.OrderFood;
import ku.kps.ds.som.kukpssom.repository.OrderFoodRepository;



@Service
public class OrderFoodService {

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
	public List<OrderFood> findByDateAndListStatus(Date date,List<String> status,int storeId){
		return orderFoodRepository.findByDateAndListStatusAndStoreId(date, status,storeId);
	}
	
	public List<OrderFood> findByUserIdAndListStatus(int userId,List<String> status){
		return orderFoodRepository.findByUserIdAndListStatus(userId, status);
	}
}
