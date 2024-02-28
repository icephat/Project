package ku.kps.ds.som.kukpssom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ku.kps.ds.som.kukpssom.model.MenuInOrder;



@Repository
public interface MenuInOrderRepository extends CrudRepository<MenuInOrder,Integer>{
	
	
	@Query("from MenuInOrder mio join mio.orderFood o where o.orderCode = :orderCode")
	public List<MenuInOrder> findByOrderCode(@Param("orderCode")String orderCode);

}
