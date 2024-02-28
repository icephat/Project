package org.cassava.services;

import java.util.List;
import java.util.Optional;

import org.cassava.model.Province;
import org.cassava.model.SurveyTarget;
import org.cassava.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

@Service
public class ProvinceService {

	@Autowired
	private ProvinceRepository provinceRepository;

	public Province findByName(String name) {
		return provinceRepository.findByName(name);
	}

	public Province findByCode(String code) {
		return provinceRepository.findByCode(code);
	}

	public List<Province> findAll() {
		return (List<Province>) provinceRepository.findAll();
	}

	public Province findById(int id) {
		Optional<Province> p = provinceRepository.findById(id);
		if (p.isPresent())
			return p.get();
		return null;
	}

	public List<Province> findBySurveyTarget(List<SurveyTarget> sts) {
		return provinceRepository.findBySurveyTarget(sts);
	}

	public List<Province> findByListId(List<Integer> pvIds) {
		return provinceRepository.findByListId(pvIds);
	}

}
