package ku.kps.ds.som.kukpssom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ku.kps.ds.som.kukpssom.model.Store;

@Repository
public interface StoreRepository extends CrudRepository<Store, Integer> {

	@Query("from Store s join s.user u where u.username = :username")
	public Store findByUsername(@Param("username") String username);

	@Query("from Store s where s.status = :status")
	public List<Store> findByStatus(@Param("status") String status);

}
