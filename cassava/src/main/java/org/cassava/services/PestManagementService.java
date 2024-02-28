package org.cassava.services;

import java.util.List;

import org.cassava.model.Pest;
import org.cassava.model.PestManagement;
import org.cassava.repository.PestManagementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("pestmanagement")
public class PestManagementService {

	@Autowired
	private PestManagementRepository pestmanagementRepository;
	
	public List<PestManagement> findAll(){
		List<PestManagement> pestmanagement = (List<PestManagement>) pestmanagementRepository.findAll();
		
		return pestmanagement;
	}
	
	public List<PestManagement> findAllById(int id){
		return (List<PestManagement>) pestmanagementRepository.findAllById(id);
	}
	
	public PestManagement findByName(String name) {
		return pestmanagementRepository.findByName(name);
	}
	
	public PestManagement findByEngName(String name) {
		return pestmanagementRepository.findByEngName(name);
	}
	
	public List<PestManagement> findByInstruction(String instrutction){
		List<PestManagement> pestmanagement = (List<PestManagement>) pestmanagementRepository.findByInstruction(instrutction);
		return pestmanagement;
	}
	
	public PestManagement save(PestManagement p) {
		return pestmanagementRepository.save(p);
	}
	
	public void deleteById(int id) {
		pestmanagementRepository.deleteById(id);
	}
	
	public PestManagement findById(int id) {
		return pestmanagementRepository.findById(id).get();
	}
	
}
