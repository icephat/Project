package org.cassava.repository;

import org.cassava.model.PathogenType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PathogenTypeRepository extends CrudRepository<PathogenType,Integer>{
	@Query("from PathogenType p where p.name = :name")
	public PathogenType findByName(@Param("name") String name);
}
