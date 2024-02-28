package se.springboot.sharitytest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.springboot.sharitytest.model.ReportComment;
import se.springboot.sharitytest.repository.ReportCommentRepository;

@Service
public class ReportCommentService {

	@Autowired
	private ReportCommentRepository reportCommentRepository;
	
	public List<ReportComment> findAll() {
		return (List<ReportComment>) reportCommentRepository.findAll();
	}

	public ReportComment findById(int reportCommentId) {
		return reportCommentRepository.findById(reportCommentId).get();
	}

	public void save(ReportComment user) {
		reportCommentRepository.save(user);
	}

	public void delete(int reportCommentId) {
		reportCommentRepository.deleteById(reportCommentId);
	}
	
	
}
