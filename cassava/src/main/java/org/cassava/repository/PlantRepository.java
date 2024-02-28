package org.cassava.repository;

import org.cassava.model.Plant;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface PlantRepository extends CrudRepository<Plant,Integer> {
	@Query("from Plant p where p.name = :name")
	public Plant findByName(@Param("name")  String name);
}
