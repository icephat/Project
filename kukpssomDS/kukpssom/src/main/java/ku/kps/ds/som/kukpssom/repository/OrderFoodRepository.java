package ku.kps.ds.som.kukpssom.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ku.kps.ds.som.kukpssom.model.OrderFood;

@Repository
public interface OrderFoodRepository extends CrudRepository<OrderFood, Integer> {

	@Query("from OrderFood o join o.menuInOrders where o.date = :date and o.status in :status and o.storeId = :storeId")
	public List<OrderFood> findByDateAndListStatusAndStoreId(@Param("date") Date date,
			@Param("status") List<String> status, @Param("storeId") int storeId);

	@Query("from OrderFood o join o.user u where u.userId = :userId and o.status in :status")
	public List<OrderFood> findByUserIdAndListStatus(@Param("userId") int userId, @Param("status") List<String> status);
}
