package se.springboot.sharitytest.repository;

import org.springframework.data.repository.CrudRepository;

import se.springboot.sharitytest.model.UserReport;



public interface UserReportRepository extends CrudRepository<UserReport, Integer> {

}
