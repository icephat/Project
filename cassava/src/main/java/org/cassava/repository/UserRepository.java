package org.cassava.repository;

import java.util.Date;

import java.util.List;

import org.cassava.model.Field;
import org.cassava.model.Survey;
import org.cassava.model.User;
import org.cassava.model.UserInField;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("from User u where u.username=:username")
	public User findByUsername(@Param("username") String username);

	@Query("from User u where u.username=:username and u.userStatus=:userStatus")
	public User findByUserNameAndStatus(@Param("username") String username, @Param("userStatus") String userStatus);

	@Query("from User u where u.userStatus=:userStatus")
	public User findByUserStatus(@Param("userStatus") String userStatus);

	@Query("from User as u inner join u.userinfields as uf where uf.id.role=:role and uf.field.fieldId=:fieldId")
	public List<User> findAllByFieldIdAndRole(@Param("fieldId") int fieldId, @Param("role") String role);

	@Query("select u from User as u inner join u.userinfields as uf inner join uf.field f where f in (select f from User as u inner join u.userinfields as uf inner join uf.field f where u = :user  and f.fieldId = :fieldId) and uf.id.role = :role")
	public List<User> findByFieldIdAndRoleAndUser(@Param("fieldId") int fieldId, @Param("role") String role ,@Param("user") User user);

	@Query("from User u where u.requestInfoStatus=:requestInfoStatus")
	public User findByRequestInfoStatus(@Param("requestInfoStatus") String requestInfoStatus);

	@Query("from User u where u.phoneNo=:phoneNo")
	public User findByPhoneNo(@Param("phoneNo") String phoneNo);

	@Query("from User u where u.firstName like '%:firstName%' or u.lastName	like '%:lastName%'")
	public User findByKey(@Param("firstName") String firstName, @Param("lastName") String lastName);

	@Query("select DISTINCT u FROM User as u inner join u.roles as r where r.userDefine = :userDefine")
	public List<User> findAllByUserDefine(@Param("userDefine") String userDefine);

	@Query("select DISTINCT u FROM User as u inner join u.roles as r where r.userDefine = :userDefine")
	public List<User> findAllByUserDefine(@Param("userDefine") String userDefine, Pageable pageable);

	@Query("select u from User as u INNER JOIN u.userinfields as uf INNER JOIN uf.id as ufid INNER JOIN uf.field as f WHERE f IN :fields AND role=:role")
	public List<User> findAllByFieldsAndRole(@Param("fields") List<Field> fields, @Param("role") String role);

	@Query("select count(u.userId) FROM User as u inner join u.roles as r where u = :user and r.nameEng = :roleEng")
	public int checkUserAndRoleEng(@Param("user")User user , @Param("roleEng") String roleEng);

	@Query("select u from User u inner join u.roles r where r.nameEng = :roleName")
	public List<User> findAllByRoleName(@Param("roleName") String roleName, Pageable pageable);
	
	@Modifying
    @Transactional
    @Query(value = "Delete ur from UserRole ur where ur.userID = :userID and ur.roleID  = :roleID", nativeQuery = true)
    public void deleteUserRoleByUserIdAndRoleId(@Param("userID") int userID,@Param("roleID") int roleID);
	
	@Query("select count(u) from User u inner join u.roles r where r.nameEng = :nameEng")
	public int countUserByroleName(@Param("nameEng")String nameEng);
	
	@Query("select count(u) from User u")
	public int countUser();
	
	@Query("select uif.user, f, sd.name, dt.name, pv.name "
			+ "from Planting p inner join p.field f inner join f.userinfields uif inner join uif.id uifid "
			+ "inner join f.subdistrict sd inner join sd.district dt inner join dt.province pv "
			+ "where uifid.role = :role and p.plantingId = :plantingId")
	public Object findByPlantingIdAndRole(@Param("role")String role, @Param("plantingId")int plantingId);

}
