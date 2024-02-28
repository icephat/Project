package org.cassava.repository;

import java.util.List;

import org.cassava.model.ImgNaturalEnemy;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgNaturalEnemyRepository extends CrudRepository<ImgNaturalEnemy, Integer> {

	@Query("from ImgNaturalEnemy imgn")
	public List<ImgNaturalEnemy> findAllByPagination(Pageable pageable);

}
