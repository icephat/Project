package se.springboot.sharitytest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import se.springboot.sharitytest.model.TypeOfActivity;


@Repository
public interface TypeOfActivityRepository extends CrudRepository<TypeOfActivity, Integer> {

}
