package se.springboot.sharitytest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import se.springboot.sharitytest.model.ReportComment;

@Repository
public interface ReportCommentRepository extends CrudRepository<ReportComment, Integer>{

}
