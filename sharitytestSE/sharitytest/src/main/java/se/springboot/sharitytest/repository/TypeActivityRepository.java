package se.springboot.sharitytest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import se.springboot.sharitytest.model.TypeActivity;

@Repository
public interface TypeActivityRepository extends CrudRepository<TypeActivity, Integer> {

}
