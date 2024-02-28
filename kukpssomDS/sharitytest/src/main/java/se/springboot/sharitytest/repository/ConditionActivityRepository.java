package se.springboot.sharitytest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import se.springboot.sharitytest.model.ConditionActivity;

@Repository
public interface ConditionActivityRepository extends CrudRepository<ConditionActivity, Integer> {

}
