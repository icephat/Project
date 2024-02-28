package org.cassava.repository;

import java.util.List;

import org.cassava.model.ImgPest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ImgPestRepository extends CrudRepository<ImgPest, Integer>{

	@Query("from ImgPest imgp")
	public List<ImgPest> findAllByPagination(Pageable pageable);

}
