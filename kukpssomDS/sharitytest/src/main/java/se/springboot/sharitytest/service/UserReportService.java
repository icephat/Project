package se.springboot.sharitytest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import se.springboot.sharitytest.model.UserReport;
import se.springboot.sharitytest.repository.UserReportRepository;

@Service
public class UserReportService {

	@Autowired
	private UserReportRepository userReportRepository;
	
	public List<UserReport> findAll() {
		return (List<UserReport>) userReportRepository.findAll();
	}

	public UserReport findById(int reportId) {
		return userReportRepository.findById(reportId).get();
	}

	public void save(UserReport user) {
		userReportRepository.save(user);
	}

	public void delete(int reportId) {
		userReportRepository.deleteById(reportId);
	}
}
