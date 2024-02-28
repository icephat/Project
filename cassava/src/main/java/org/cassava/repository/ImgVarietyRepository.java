package org.cassava.repository;

import java.util.List;

import org.cassava.model.ImgVariety;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgVarietyRepository extends CrudRepository<ImgVariety, Integer> {
	
	@Query("from ImgVariety imgv")
	public List<ImgVariety> findAllByPagination (Pageable pageable);
}
