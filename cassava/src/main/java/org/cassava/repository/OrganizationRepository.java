package org.cassava.repository;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.cassava.model.Organization;
import org.cassava.model.SurveyTarget;
@Repository
public interface OrganizationRepository extends CrudRepository<Organization,Integer>{
	
	@Query("from Organization o where o.name like '%:name%'")
	public Organization findByName(@Param("name")String name);
	
	@Query("from Organization o where o.phone =:phone")
	public Organization findByPhone(@Param("phone")String phone);
	
	@Query("from Organization o where o.code =:code")
	public Organization findByCode(@Param("code")String code);
	
	@Query("from Organization as o inner join o.farmers as f inner join f.user as u where u.username=:username")
	public List<Organization> findByFarmerUsername(@Param("username")String username);
	
	@Query("from Organization as o inner join o.staffs as s inner join s.user as u where u.username=:username")
	public Organization findByStaffUsername(@Param("username")String username);
		
	@Query("select count(*) from Organization as o where o.organizationId=:organizationId and "
			+ "(o in (select o.organizationId from Organization as o inner join o.farmers as far where o.organizationId=:organizationId)"
			+ "or o in (select o.organizationId from Organization as o inner join o.staffs as s where o.organizationId=:organizationId)"
			+ "or o in (select o.organizationId from Organization as o inner join o.fields as f where o.organizationId=:organizationId)"
			//+ "or o in (select o.organizationId from Organization as o inner join o.registercodes as r where o.organizationId=:organizationId)"
			+ ")"
			)
	public Integer checkFkByOrganizationId(@Param("organizationId")int organizationId);
	


	@Query("select o.organizationId,count(s.staffId) from Organization as o inner join o.staffs as s inner join s.user as u inner join u.roles as r where r.nameEng =:role group by o.organizationId")
	public List<Object> countStaffInRole(@Param("role")String role);
	
	@Query("select count(s.staffId) from Organization as o inner join o.staffs as s inner join s.user as u inner join u.roles as r where o.organizationId=:organizationId and r.nameEng =:role")
	public int countStaffInOrganizationRole(@Param("organizationId")int organizationId, @Param("role")String role);
	/*
	@Query("select o.organizationId,s from Organization o inner join o.staffs s where o.organizationId=:organizationId and s.staffId not in (select u.userId from User u inner join u.roles r where r.nameEng =:role)")
	public List<Object> staffNonRole(@Param("organizationId")int organizationId, @Param("role")String role);
	
	@Query("select o.organizationId,s from Organization as o inner join o.staffs as s inner join s.user as u inner join u.roles as r where r.nameEng =:role and o.organizationId=:organizationId")
	public List<Object> staffInRole(@Param("organizationId")int organizationId, @Param("role")String role);
	 */
	@Query("from Organization o")
	public List<Organization> findAll(Pageable pageable);

	@Query("select distinct o from SurveyTarget st inner join st.survey s inner join s.planting p inner join p.field f inner join f.organization o where st in :serveyTarget")
	public List<Organization> findBySurveyTarget(@Param("serveyTarget") List<SurveyTarget> serveyTarget);
	
	
	
}
