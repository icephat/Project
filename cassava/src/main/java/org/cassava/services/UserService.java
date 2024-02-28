package org.cassava.services;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.cassava.model.Field;
import org.cassava.model.Organization;
import org.cassava.model.User;
import org.cassava.model.UserInField;
import org.cassava.repository.UserRepository;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;

@Service("userService")
public class UserService {

	public static String ROLE_PREFIX = "ROLE_";

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private final EntityManager entityManager;
	
	@Autowired
	public UserService(EntityManager entityManager) {
		this.entityManager = entityManager.getEntityManagerFactory().createEntityManager();
	}
	
	public List<User> findAllByFieldIdAndRole(int fieldId, String role) {
		return userRepository.findAllByFieldIdAndRole(fieldId, role);
	}

	public User findByUserNameAndStatus(String name, String status) {
		return userRepository.findByUserNameAndStatus(name, status);
	}

	public User findByUsername(String name) {

		User user = userRepository.findByUsername(name);

		return user;
	}

	@SuppressWarnings("unchecked")
	public List<User> findByUserStatus(String status) {

		List<User> statu = (List<User>) userRepository.findByUserStatus(status);
		return statu;
	}

	@SuppressWarnings("unchecked")
	public List<User> findByRequestInfoStatus(String ris) {
		List<User> Request = (List<User>) userRepository.findByRequestInfoStatus(ris);
		return Request;
	}

	@SuppressWarnings("unchecked")
	public List<User> findByPhoneNo(String ph) {
		List<User> Phones = (List<User>) userRepository.findByPhoneNo(ph);
		return Phones;
	}

	public User findByKey(String first, String last) {
		User name = userRepository.findByKey(first, last);
		return name;
	}

	public List<User> findAll() {
		return (List<User>) userRepository.findAll();
	}

	public User findById(int id) {
		User user = userRepository.findById(id).orElse(null);
		return user;
	}

	public User save(User k) {
		return userRepository.save(k);
	}

	public void deleteById(int id) {
		userRepository.deleteById(id);
	}

	public List<User> findByUserDefine(String userDefine) {
		return userRepository.findAllByUserDefine(userDefine);
	}

	public List<User> findByUserDefine(String userDefine, int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		return userRepository.findAllByUserDefine(userDefine, pageable);
	}

	public List<User> findByFieldsAndRole(List<Field> fields, String role) {
		// String roleName ="farmerOwner" ;
		return userRepository.findAllByFieldsAndRole(fields, role);
	}

	public List<User> findByFieldIdAndRoleAndUser(int fieldId, String role ,String username) {
		User user = userRepository.findByUsername(username);
		return userRepository.findByFieldIdAndRoleAndUser(fieldId, role,user);
	}
	
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	public List<User> findAllByRoleFarmer(int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		String roleName = "farmer";
		return userRepository.findAllByRoleName(roleName, pageable);
	}

	public List<User> findAllByRoleStaff(int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		String roleName = "staff";
		return userRepository.findAllByRoleName(roleName, pageable);
	}

	public List<User> findAllByRoleSystemAdmin(int page, int value) {
		Pageable pageable = PageRequest.of(page - 1, value);
		String roleName = "systemAdmin";
		return userRepository.findAllByRoleName(roleName, pageable);
	}
	
	public void deleteUserRoleByUserIdandRoleId(int userId,int roleId) {
		userRepository.deleteUserRoleByUserIdAndRoleId(userId, roleId);
	}
	
	public int countStaff() {
		return userRepository.countUserByroleName("staff");
	}
	
	public int countSystemAdmin() {
		return userRepository.countUserByroleName("systemAdmin");
	}
	
	public int countFarmer() {
		return userRepository.countUserByroleName("farmer");
	}
	
	public int countUser() {
		return userRepository.countUser();
	}
	
	public Object findByPlantingIdAndRole(int plantingId, String role) {
		return userRepository.findByPlantingIdAndRole(role, plantingId);
	}
	
	public List<User> findFarmerByKey(String firstname,String lastname, Organization organization, int page, int value)
			throws JsonProcessingException, ParseException {

		StringBuilder sb = new StringBuilder();

		sb.append("select * from user as u where u.userID in "
				+ "(SELECT us.userID FROM user as us INNER JOIN farmer as f ON f.farmerID = us.userID "
				+ "INNER JOIN farmerorganization as fo ON fo.farmerID = f.farmerID "
				+ "WHERE fo.organizationID = "+ organization.getOrganizationId() +" AND "
				+ "(us.firstName like \"%" + firstname + "%\" and u.lastName like \"%" + lastname +"%\" )");
		
		sb.append(") order by u.userID DESC ");
		
		Query q = entityManager.createNativeQuery(sb.toString(), User.class).setFirstResult((page - 1) * value)
				.setMaxResults(value);
		return q.getResultList();

	}
	
	public List<User> findStaffByKey(String firstname,String lastname, Organization organization, int page, int value)
			throws JsonProcessingException, ParseException {

		StringBuilder sb = new StringBuilder();

		sb.append("select * from user as u where u.userID in "
				+ "(SELECT us.userID FROM user as us INNER JOIN staff as s ON s.staffID = us.userID "
				+ "WHERE s.organizationID = "+ organization.getOrganizationId() +" AND "
				+ "(us.firstName like \"%" + firstname + "%\" and u.lastName like \"%" + lastname +"%\" )");
		
		sb.append(") order by u.userID DESC ");
		
		Query q = entityManager.createNativeQuery(sb.toString(), User.class).setFirstResult((page - 1) * value)
				.setMaxResults(value);
		return q.getResultList();

	}
	
	public int checkUserNameAndRoleEng (User user,String roleEng) {
		return userRepository.checkUserAndRoleEng(user, roleEng);
	}
}
