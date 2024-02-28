package ku.kps.ds.som.kukpssom.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ku.kps.ds.som.kukpssom.model.Menu;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Integer> {

	@Query("from Menu m where m.store.name = :name")
	public List<Menu> findByStoreName(@Param("name") String name);

	@Query("from Menu m join m.store s where s.storeId = :storeId")
	public List<Menu> findByStoreId(@Param("storeId") int storeId);
}
