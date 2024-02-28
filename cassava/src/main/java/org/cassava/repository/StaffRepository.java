package org.cassava.repository;

import java.util.List;

import org.cassava.model.Farmer;
import org.cassava.model.Field;
import org.cassava.model.Organization;
import org.cassava.model.Staff;
import org.cassava.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.beust.jcommander.Parameter;

@Repository
public interface StaffRepository extends CrudRepository<Staff, Integer> {

	@Query("from Staff s where s.position like '%:position%' or s.division like '%:division%'")
	public Staff findByKey(@Param("position") String position, @Param("division") String division);

	@Query("select s.staffId,s.position,s.division,o.organizationId FROM Staff as s INNER JOIN s.organization as o INNER JOIN o.fields as f INNER JOIN s.user as u INNER JOIN u.userinfields as uf INNER JOIN uf.id as ufid  WHERE f.fieldId = :fieldId AND o.organizationId = :organizationId AND ufid.role = ':role'")
	public List<Object> findStaffByFieldIdAndOrganizationIdAndRoleId(@Param("fieldId") int fieldId,
			@Param("organizationId") int organizationId, @Param("role") String role);
	/*
	@Query("from Staff s  where (s.staffId not in (select s.staffId from Staff s join s.user u join u.userinfields uf join uf.id as ufid join uf.field f join s.organization as o where  f = :field and ufid.role= :roleName)) and s.organization = :organization ")
	public List<Staff> findStaffByOrganizationAndRolenameNotIn(@Param("organization") Organization organization,
			@Param("field") Field field, @Param("roleName") String roleName);
	*/
	
	@Query("from Staff s  where s.organization = :organization and (s.staffId not in (select s.staffId from Staff s join s.user u join u.userinfields uf join uf.id as ufid join uf.field f join s.organization as o where  f = :field))")
	public List<Staff> findByOrganizationAndFieldNotIn(@Param("organization") Organization organization,
			@Param("field") Field field);
	
	@Query("from Staff s  where s.organization = :organization and (s.staffId not in (select s.staffId from Staff s join s.user u join u.userinfields uf join uf.id as ufid join uf.field f join s.organization as o where  f = :field)and s.user.userStatus in :status)")
	public List<Staff> findByOrganizationAndFieldNotInAndStatus(@Param("organization") Organization organization,
			@Param("field") Field field,Pageable pageable,@Param("status") List<String> status);
	
	
	@Query("from Staff s  where s.organization = :organization and s.user.userStatus in :status and (s.user.firstName like %:key% or s.user.lastName like %:key% or s.user.username like %:key%) and (s.staffId not in (select s.staffId from Staff s join s.user u join u.userinfields uf join uf.id as ufid join uf.field f join s.organization as o where  f = :field))")
	public List<Staff> findByOrganizationAndFieldNotInAndKeyAndStatus(@Param("organization") Organization organization,
			@Param("field") Field field,@Param("key") String key,Pageable pageable,@Param("status") List<String> status);
	
	@Query("from Staff s  where s.organization = :organization and (s.user.firstName like %:key% or s.user.lastName like %:key% or s.user.username like %:key%) and (s.staffId not in (select s.staffId from Staff s join s.user u join u.userinfields uf join uf.id as ufid join uf.field f join s.organization as o where  f = :field))")
	public List<Staff> findByOrganizationAndFieldNotInAndKey(@Param("organization") Organization organization,
			@Param("field") Field field,@Param("key") String key);
	
	@Query("select s FROM Staff AS s WHERE s NOT IN (select s FROM Staff AS s INNER JOIN s.user as u INNER JOIN u.roles as r WHERE r.userDefine = 'Y' )")
	public List<Staff> findRegularStaff();
	
	@Query("select s FROM Staff AS s WHERE s NOT IN (select s FROM Staff AS s INNER JOIN s.user as u INNER JOIN u.roles as r WHERE r.userDefine = 'Y' ) and s.user.userStatus in :status")
	public List<Staff> findRegularStaffByListStatus(@Param("status") List<String> status);

	@Query("select s FROM Staff AS s INNER JOIN s.user as u INNER JOIN u.roles as r WHERE r.userDefine = 'Y'")
	public List<Staff> findAdminStaff();

	@Query("FROM Staff AS s JOIN s.user as u JOIN u.roles as r WHERE s.staffId in (select s.staffId FROM Staff AS s JOIN s.organization as fo where fo = :Organization)and s NOT IN (select s FROM Staff AS s INNER JOIN s.user as u INNER JOIN u.roles as r WHERE r.userDefine = 'Y' ) and r.userDefine != 'Y' ")
	public List<Staff> findRegularStaffbyOrganization(@Param("Organization") Organization Organization);

	@Query("select DISTINCT s FROM Staff AS s JOIN s.user as u JOIN u.roles as r where r.userDefine = :userDefine")
	public List<Staff> findByUserDefine(@Param("userDefine") String userDefine);

	@Query("select s from Staff s join s.user u where u.username = :username")
	public Staff findByUserName(@Param("username") String username);

	@Query(value = "SELECT role.roleID , role.nameTH,if(u.roleID > 0,1,0) as status FROM role LEFT JOIN (SELECT * FROM user natural join userrole WHERE user.userID =:id) as u on role.roleID = u.roleID WHERE userDefine = 'Y'", nativeQuery = true)
	public List<Object> findByUserRole(@Param("id") int id);

	@Query("from Staff s join s.user u where u.userStatus =:status")
	public List<Staff> findByStatus(@Param("status") String status);

	@Query("from Staff s join s.user u where u.userStatus in :status")
	public List<Staff> findByListStatus(@Param("status") List<String> status);
	
	@Query("from Staff s join s.user u where u.userStatus in :status and (s.position like %:key% or s.division like %:key% or u.firstName like %:key% or u.lastName like %:key% or u.title like %:key% or u.username like %:key% or s.organization.name like %:key%)")
	public List<Staff> findByListStatusAndKey(@Param("status") List<String> status , @Param("key") String key);
	
	@Query("from Staff s join s.user u where u.userStatus in :status")
	public List<Staff> findByListStatus(@Param("status") List<String> status,Pageable pageable);
	
	@Query("from Staff s join s.user u where u.userStatus in :status and (s.position like %:key% or s.division like %:key% or u.firstName like %:key% or u.lastName like %:key% or u.title like %:key% or u.username like %:key% or s.organization.name like %:key% )")
	public List<Staff> findByListStatusAndKey(@Param("status") List<String> status, @Param("key") String key,Pageable pageable);

	@Query("from Staff s join s.user u join s.organization o inner join u.roles as r where o.organizationId=:organizationId and r.nameEng =:role")
	public List<Staff> findByOrganizationAndUserrole(@Param("organizationId") int organizationId, @Param("role") String role);
		
	@Query("from Staff s join s.organization o  where o.organizationId=:organizationId and s not in (select s from Staff as s join s.user u join s.organization o join u.roles r where o.organizationId=:organizationId and r.nameEng =:role)")
	public List<Staff> findByOrganizationAndNotUserrole(@Param("organizationId") int organizationId, @Param("role") String role);
	
	@Query("from Staff s join s.user u join s.organization o  where o.organizationId=:organizationId and s not in (select s from Staff as s join s.user u join s.organization o join u.roles r where o.organizationId=:organizationId and r.nameEng =:role) and (u.userStatus ='active' or u.userStatus ='inactive')")
	public List<Staff> findByOrganizationAndStatusValidAndNotUserrole(@Param("organizationId") int organizationId, @Param("role") String role);	
	
	@Query("from Staff s join s.user u join s.organization o where o =:organization and u.userStatus =:status")
	public List<Staff> findByOrganizationAndStatus(@Param("organization") Organization organization, @Param("status") String status);

	@Query("from Staff s join s.user u join s.organization o where o =:organization and (u.userStatus ='active' or u.userStatus ='inactive') ")
	public List<Staff> findByOrganizationAndStatusValid(@Param("organization") Organization organization);

	@Query("from Staff s join s.user u join s.organization o where o =:organization and (u.userStatus ='active' or u.userStatus ='inactive') and (s.position like %:key% or s.division like %:key% or u.firstName like %:key% or u.lastName like %:key% or u.title like %:key% or u.username like %:key% or o.name like %:key%)")
	public List<Staff> findByOrganizationAndStatusValidAndKey(@Param("organization") Organization organization, @Param("key") String key);

	@Query("from Staff s join s.user u join s.organization o where o =:organization and (u.userStatus ='active' or u.userStatus ='inactive')")
	public List<Staff> findByOrganizationAndStatusValid(@Param("organization") Organization organization, Pageable pageable);
	
	@Query("from Staff s join s.user u join s.organization o where o =:organization and (u.userStatus ='active' or u.userStatus ='inactive') and (s.position like %:key% or s.division like %:key% or u.firstName like %:key% or u.lastName like %:key% or u.title like %:key% or u.username like %:key% or o.name like %:key%)")
	public List<Staff> findByOrganizationAndStatusValidAndKey(@Param("organization") Organization organization, @Param("key") String key, Pageable pageable);
	
	@Query("from Staff s  where s.organization = :organization")
	public List<Staff> findByOrganization(@Param("organization")Organization organization, Pageable pageable);
	
	@Query("select count(s) from User u inner join u.staff s inner join u.userinfields uif inner join uif.id uifid inner join uif.field f join s.organization as o where  uif.field = :field and uifid.role in :roleName and s.staffId = :staffId")
	public int checkStaffInFieldByOrganizationAndField(@Param("field")Field field, @Param("roleName")List<String> roleName, @Param("staffId")int staffId);
	
	@Query("select count(s) from Staff s  where s.organization = :organization and s.staffId = :staffId and (s.staffId not in (select s.staffId from Staff s join s.user u join u.userinfields uf join uf.id as ufid join uf.field f join s.organization as o where  f = :field))")
	public int checkStaffNotInFieldByOrganizationAndField(@Param("organization") Organization organization,
			@Param("field") Field field,@Param("staffId")int staffId);
	

}
