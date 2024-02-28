package se.springboot.sharitytest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import se.springboot.sharitytest.model.User;
import se.springboot.sharitytest.repository.UserRepository;

@Service
public class UserService {

	@Autowired

	private UserRepository userRepository;

	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	public User findById(int id) {
		return userRepository.findById(id).get();
	}

	public void save(User user) {
		userRepository.save(user);
	}

	public void delete(int id) {
		userRepository.deleteById(id);
	}

	public User findByUsername(String username) {

		return userRepository.findByUsername(username);
	}

	public List<User> findByRole(String role) {
		return (List<User>) userRepository.findByRole(role);
	}
}
