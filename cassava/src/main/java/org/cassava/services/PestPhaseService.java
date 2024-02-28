package org.cassava.services;

import java.util.List;

import org.cassava.model.NaturalEnemy;
import org.cassava.model.PestPhase;
import org.cassava.repository.PestPhaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PestPhaseService {

	@Autowired
	private PestPhaseRepository pestphaseRepository;
	
	public PestPhase findById(int id) {
		return pestphaseRepository.findById(id).get();
	}
	
	public List<PestPhase> findAll() {
		return (List<PestPhase>) pestphaseRepository.findAll();
	}
}
