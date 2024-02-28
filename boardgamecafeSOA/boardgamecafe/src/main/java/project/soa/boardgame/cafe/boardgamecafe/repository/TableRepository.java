package project.soa.boardgame.cafe.boardgamecafe.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.soa.boardgame.cafe.boardgamecafe.model.Table;

@Repository
public interface TableRepository extends CrudRepository<Table, Integer> {
	
	@Query("from Table t where t.status = : status")
	public List<Table> findByStatus(@Param("status")String status);

}
