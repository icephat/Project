package org.cassava.repository;

import java.util.List;
import org.cassava.model.Role;
import org.cassava.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer>{
	@Query("from Role r where r.nameEng=:nameEng")
	public Role findByNameEng(@Param("nameEng")String nameEng);
	
	@Query("select r from Role as r inner join r.users as u  where u.userId = :userId AND  r.nameEng = :roleName")
	public Role findByUserIdAndRoleName(@Param("userId")int userId,@Param("roleName")String roleName); 

	@Query("from Role as r where r.userDefine = :userDefine order by r.roleId")
	public List<Role> findByUserDefine(@Param("userDefine")String userDefine);
	
	@Query("from Role as r where r.userDefine = :userDefine order by r.roleId")
	public List<Role> findByUserDefine(@Param("userDefine")String userDefine,Pageable pageable);
}