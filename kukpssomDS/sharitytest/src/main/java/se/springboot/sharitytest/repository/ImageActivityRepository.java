package se.springboot.sharitytest.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import se.springboot.sharitytest.model.ImageActivity;

@Repository
public interface ImageActivityRepository extends CrudRepository<ImageActivity, Integer> {
}
