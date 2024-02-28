package org.cassava.repository;

import org.cassava.model.Herbicide;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface HerbicideRepository extends CrudRepository<Herbicide, Integer>{
	
}