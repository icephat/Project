package org.cassava.repository;

import org.cassava.model.PestPhase;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PestPhaseRepository extends CrudRepository<PestPhase, Integer>{
	
}
