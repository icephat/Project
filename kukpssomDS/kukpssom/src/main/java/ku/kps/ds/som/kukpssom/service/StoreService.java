package ku.kps.ds.som.kukpssom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ku.kps.ds.som.kukpssom.model.Store;
import ku.kps.ds.som.kukpssom.repository.StoreRepository;

@Service
public class StoreService {

	@Autowired
	StoreRepository storeRepository;

	public void save(Store store) {
		storeRepository.save(store);
	}

	public void delete(int id) {
		storeRepository.deleteById(id);
	}

	public Store findById(int id) {
		return storeRepository.findById(id).get();
	}

	public List<Store> findAll() {
		return (List<Store>) storeRepository.findAll();

	}

	public Store findByUsername(String username) {
		return storeRepository.findByUsername(username);
	}

	public List<Store> findByStatus(String status) {
		return storeRepository.findByStatus(status);
	}
}
