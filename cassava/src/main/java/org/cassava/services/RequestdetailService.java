package org.cassava.services;

import java.util.List;

import org.cassava.model.Field;
import org.cassava.model.Planting;
import org.cassava.model.RequestDetail;
import org.cassava.repository.RequestdetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequestdetailService {
	
	@Autowired
	private RequestdetailRepository requestdetailRepository;
	
	public List<RequestDetail> findAll(){
		return (List<RequestDetail>) requestdetailRepository.findAll();
	}

	public RequestDetail findById(int id) {
		return requestdetailRepository.findById(id).get();
	}
	
	public RequestDetail save(RequestDetail request) {
		return requestdetailRepository.save(request);
	}
	
	public List<Object> findByUserId(int userid) {
		return (List<Object>)requestdetailRepository.findByUserId(userid);
	}

	public Object findByUserIdAndRequestId(int userid, int reid) {
		return (List<RequestDetail>)requestdetailRepository.findByUserIdAndRequestId(userid,reid) ;
	}
	
	public void deleteById(int id) {
		requestdetailRepository.deleteById(id);
	}

	public List<RequestDetail> findByRequestId(int reid) {
		return (List<RequestDetail>)requestdetailRepository.findByRequestId(reid) ;
	}

	public List<Object> findByUserIdAndWaiting(int userid) {
		return (List<Object>)requestdetailRepository.findByUserIdAndWaiting(userid);
	}

	public List<Object> findByUserIdAndApprove(int userid) {
		return (List<Object>)requestdetailRepository.findByUserIdAndApprove(userid);
	}


	public List<Object> findCountRequestAndRequestDetailAndByUserIdAndWaiting(int userid) {
		return (List<Object>)requestdetailRepository.findCountRequestAndRequestDetailAndByUserIdAndWaiting(userid);
	}
	public List<Object> findCountRequestAndRequestDetailAndByUserId(int userid) {
		return (List<Object>)requestdetailRepository.findCountRequestAndRequestDetailAndByUserId(userid);
	}
	public List<Field> findFieldByRequestId(int rid) {
		return requestdetailRepository.findFieldByRequestId(rid);
	}

	public List<Planting> findPlatingdByRequestId(int rid) {
		return requestdetailRepository.findPlatingdByRequestId(rid);
	}
}
