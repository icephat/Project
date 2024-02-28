package org.cassava.services;

import java.util.List;
import java.util.Optional;

import org.cassava.model.District;
import org.cassava.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictService {

	@Autowired
	private DistrictRepository districtRepository;
	
	public List<District> findAll(){
		return (List<District>) districtRepository.findAll();
	}

	public District findById(int id) {
		Optional<District> district = districtRepository.findById(id);
		if (district.isPresent())
			return district.get();
		return null;
	}
	
	public List<District> findByProvinceId(Integer provinceId) {
        return districtRepository.findByProvinceId(provinceId) ;
    }

}
