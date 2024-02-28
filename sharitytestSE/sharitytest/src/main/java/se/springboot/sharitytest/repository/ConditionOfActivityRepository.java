package se.springboot.sharitytest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import se.springboot.sharitytest.model.ConditionOfActivity;

@Repository
public interface ConditionOfActivityRepository extends CrudRepository<ConditionOfActivity, Integer> {

}
