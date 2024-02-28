package ku.kps.ds.som.kukpssom.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ku.kps.ds.som.kukpssom.model.User;
import ku.kps.ds.som.kukpssom.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public void save(User user) {
		userRepository.save(user);
	}

	public void delete(int id) {
		userRepository.deleteById(id);
	}

	public User findById(int id) {
		return userRepository.findById(id).get();
	}

	public List<User> findAll() {
		return (List<User>) userRepository.findAll();

	}

	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	public List<User> findByRole(String role) {
		return (List<User>) userRepository.findByRole(role);
	}

}
