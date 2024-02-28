package org.cassava.services;

import java.util.ArrayList;
import java.util.List;

import org.cassava.model.Organization;
import org.cassava.model.Request;
import org.cassava.model.Staff;
import org.cassava.model.TargetOfSurvey;
import org.cassava.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestService {
	
	@Autowired
	private RequestRepository requestRepository;
	
	public List<Request> findAll(){
		return (List<Request>) requestRepository.findAll();
	}

	public Request findById(int id) {
		return requestRepository.findById(id).orElse(null);
	}
	
	public Request save(Request request) {
		return requestRepository.save(request);
	}

	public List<Request> findByUserIdAndType(int userid, String type) {
		return requestRepository.findByUserIdAndType(userid,type);	
	}
	
	public List<Request> findByOrganizationIdAndTypeAndStatus(Staff staff, String type,ArrayList<String> status) {
		return requestRepository.findByOrganizationIdAndTypeAndStatus(staff.getOrganization().getOrganizationId(),type,status);	
	}
	
	public void deleteById(int redid) {
		requestRepository.deleteById(redid);	
	}

	public List<Request> findByStatusApprove() {
		return (List<Request>)requestRepository.findByStatusApprove();
	}

	public List<TargetOfSurvey> findtargetOfSurveyByRequestId(int rdid) {
		return requestRepository.findtargetOfSurveyByRequestId(rdid) ;
	}
	
	public List<Request> findByStatusDppathoAndOrganizationAndType(List<String> status  ,String type,List<String> statusre) {
		return requestRepository.findByStatusDppathoAndOrganizationAndTypeAndStatusRequest(status,type,statusre);	
	}
	
	public List<Request> findByStatusDaeAndOrganizationAndTypeAndStatusRequest(List<String> status,String type,List<String> statusre) {
		return requestRepository.findByStatusDaeAndOrganizationAndTypeAndStatusRequest(status,type,statusre);	
	}
	
	public List<Request> findByStatusDaoAndOrganizationAndType(List<String> status ,String type,List<String> statusre) {
		return requestRepository.findByStatusDaoAndOrganizationAndTypeAndStatusRequest(status,type,statusre);	
	}

	public List<Request> findByStatus(ArrayList<String> status, String type) {
		return requestRepository.findByStatus(status,type);
	}

}
