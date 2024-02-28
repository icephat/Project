package ku.kps.ds.som.kukpssom.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ku.kps.ds.som.kukpssom.model.OrderFood;
import ku.kps.ds.som.kukpssom.model.QueueOrder;

@Repository
public interface QueueOrderRepository extends CrudRepository<QueueOrder, Integer> {

	@Query("from QueueOrder qo join qo.orderFood o where o.date = :date and o.storeId = :storeId")
	public List<QueueOrder> findByDateAndStoreId(@Param("date") Date date, @Param("storeId") int storeId);

}
