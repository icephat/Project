package ku.kps.ds.som.kukpssom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import ku.kps.ds.som.kukpssom.model.Menu;
import ku.kps.ds.som.kukpssom.repository.MenuRepository;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;

	public void save(Menu menu) {
		menuRepository.save(menu);
	}

	public void deleteById(int id) {
		menuRepository.deleteById(id);
	}

	public Menu findById(int id) {
		return menuRepository.findById(id).get();
	}

	public List<Menu> findAll() {
		return (List<Menu>) menuRepository.findAll();
	}

	public List<Menu> findByStoreName(String name) {
		return menuRepository.findByStoreName(name);
	}

	public List<Menu> findByStoreId(int storeId) {
		return menuRepository.findByStoreId(storeId);
	}
}
