package org.cassava.repository;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

import org.cassava.model.District;
import org.cassava.model.Farmer;
import org.cassava.model.Field;
import org.cassava.model.Organization;
import org.cassava.model.Province;
import org.cassava.model.Subdistrict;
import org.cassava.model.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Pageable;

@Repository
public interface FarmerRepository extends CrudRepository<Farmer, Integer> {

	@Query("from Farmer f where f.idcard=:idcard")
	public Farmer findByIDcard(@Param("idcard") String idcard);

	@Query("select f from Farmer f inner join f.organizations as o where o.organizationId= :organizationId")
	public List<Farmer> findByOrganization(@Param("organizationId") int organizationId);

	@Query("select f FROM Farmer as f left JOIN f.organizations as fo WHERE fo.organizationId IS NULL OR fo.organizationId != :organizationId Group by f.farmerId")
	public List<Farmer> findFarmerWithoutOrganization(@Param("organizationId") int organizationId);

	@Query("from Farmer f join f.organizations fo where fo = :organization and f.farmerId not in (select f.farmerId from Farmer f join f.user u join u.userinfields uf join uf.id ufi join uf.field fi where fi = :field and (ufi.role = 'farmerOwner' or ufi.role = 'farmerSurvey'))")
	public List<Farmer> findFarmerByOrganizationAndFarmerOwnerNotIn(@Param("organization") Organization organization,
			@Param("field") Field field);
	
	@Query("from Farmer f join f.organizations fo where fo = :organization and f.user.userStatus in :status and f.farmerId not in (select f.farmerId from Farmer f join f.user u join u.userinfields uf join uf.id ufi join uf.field fi where fi = :field and (ufi.role = 'farmerOwner' or ufi.role = 'farmerSurvey'))")
	public List<Farmer> findFarmerByOrganizationAndFarmerOwnerNotInAndStatus(@Param("organization") Organization organization,
			@Param("field") Field field, Pageable pageable,@Param("status")List<String> status);

	@Query("from Farmer f join f.organizations fo where fo = :organization and (f.user.firstName like %:key% or f.user.lastName like %:key% or f.user.username like %:key%) and f.farmerId not in (select f.farmerId from Farmer f join f.user u join u.userinfields uf join uf.id ufi join uf.field fi where fi = :field and (ufi.role = 'farmerOwner' or ufi.role = 'farmerSurvey')) ")
	public List<Farmer> findFarmerByOrganizationAndFarmerOwnerNotInAndKey(@Param("organization") Organization organization,
			@Param("field") Field field,@Param("key") String key);
	
	@Query("from Farmer f join f.organizations fo where  f.user.userStatus in :status and fo = :organization and (f.user.firstName like %:key% or f.user.lastName like %:key% or f.user.username like %:key%) and f.farmerId not in (select f.farmerId from Farmer f join f.user u join u.userinfields uf join uf.id ufi join uf.field fi where fi = :field and (ufi.role = 'farmerOwner' or ufi.role = 'farmerSurvey')) ")
	public List<Farmer> findFarmerByOrganizationAndFarmerOwnerNotInAndKeyAndStatus(@Param("organization") Organization organization,
			@Param("field") Field field,@Param("key") String key, Pageable pageable,@Param("status")List<String> status);
	
	

	@Query("FROM Farmer as f WHERE f.farmerId  not in (select f.farmerId from Farmer as f JOIN f.organizations as fo where fo = :Organization)")
	public List<Farmer> findFarmerOrganizationNotIn(@Param("Organization") Organization Organization);

	@Query("select f from Farmer f join f.organizations fo where f.farmerId not in (select f.farmerId from Farmer f join f.organizations fo join fo.fields fi where fi = :field) and fo = :organization")
	public List<Farmer> findByOrganizationFieldNotIn(@Param("organization") Organization organization,
			@Param("field") Field field);

	@Query("from Farmer f join f.user u where u.userStatus =:status")
	public List<Farmer> findByStatus(@Param("status") String status);
	
	@Query("from Farmer f join f.user u where u.userStatus in :status")
	public List<Farmer> findByListStatus(@Param("status")List<String> status);
	
	@Query("from Farmer f join f.user u where u.userStatus in :status")
	public List<Farmer> findByListStatus(@Param("status")List<String> status,Pageable pageable);
	
	@Query("from Farmer f join f.user u join f.organizations o where u.userStatus in :status and (f.address like %:key% or u.firstName like %:key% or u.lastName like %:key% or u.title like %:key% or u.username like %:key% or o.name like %:key%)")
	public List<Farmer> findByListStatusAndKey(@Param("key") String key,@Param("status")List<String> status,Pageable pageable);
	
	@Query("from Farmer f join f.user u join f.organizations o where u.userStatus in :status and (f.address like %:key% or u.firstName like %:key% or u.lastName like %:key% or u.title like %:key% or u.username like %:key% or u.username like %:key% or o.name like %:key%)")
	public List<Farmer> findByListStatusAndKey(@Param("key") String key,@Param("status")List<String> status);

	@Query("from Farmer f join f.user u join f.organizations o where o =:organization and u.userStatus =:status")
	public List<Farmer> findByOrganizationAndStatus(@Param("organization") Organization organization,
			@Param("status") String status);

	@Query("from Farmer f join f.user u join f.organizations o where o =:organization and u.userStatus in :listStatus")
	public List<Farmer> findByOrganizationAndListStatus(@Param("organization") Organization organization,
			@Param("listStatus") ArrayList<String> listStatus);
	
	@Query("from Farmer f join f.user u join f.organizations o where o =:organization and u.userStatus in :listStatus")
	public List<Farmer> findByOrganizationAndListStatus(@Param("organization") Organization organization,
			@Param("listStatus") ArrayList<String> listStatus,Pageable pageable);
	
	@Query("from Farmer f join f.user u join f.organizations o where o =:organization and u.userStatus in :listStatus and (f.address like %:key% or u.firstName like %:key% or u.lastName like %:key% or u.title like %:key% or u.username like %:key% or o.name like %:key%)")
	public List<Farmer> findByOrganizationAndListStatusAndKey(@Param("organization") Organization organization,@Param("key") String key,
			@Param("listStatus") ArrayList<String> listStatus);
	
	@Query("from Farmer f join f.user u join f.organizations o where o =:organization and u.userStatus in :listStatus and (f.address like %:key% or u.firstName like %:key% or u.lastName like %:key% or u.title like %:key% or u.username like %:key% or o.name like %:key%)")
	public List<Farmer> findByOrganizationAndListStatusAndKey(@Param("organization") Organization organization,@Param("key") String key,
			@Param("listStatus") ArrayList<String> listStatus,Pageable pageable);

	@Modifying
	@Transactional
	@Query(value = "Delete fo from FarmerOrganization fo where fo.farmerID = :farmerId and fo.organizationID  = :organizationId", nativeQuery = true)
	public void deleteFarmerOrganizationByFarmerIdAndOrganizationId(@Param("farmerId") int farmerId,
			@Param("organizationId") int organizationId);

	@Query("from Farmer f join f.user u where u.userStatus ='active' or u.userStatus ='inactive' ")
	public List<Farmer> findByStatusValid(Pageable pageable);
	
	@Query("from Farmer f join f.user u where u.userStatus ='active' or u.userStatus ='inactive' and (f.address like %:key% or u.firstName like %:key% or u.lastName like %:key% or u.title like %:key%)")
	public List<Farmer> findByStatusValidAndKey(@Param("key") String key,Pageable pageable);

	@Query("from Farmer f join f.user u join f.organizations o where o =:organization and (u.userStatus ='active' or u.userStatus ='inactive') ")
	public List<Farmer> findByOrganizationAndStatusValid(@Param("organization") Organization organization,
			Pageable pageable);
	
	@Query("from Farmer f join f.user u join f.organizations o where o =:organization and u.userStatus in :listStatus and (u.userStatus ='active' or u.userStatus ='inactive') and (f.address like %:key% or u.firstName like %:key% or u.lastName like %:key% or u.title like %:key%)")
	public List<Farmer> findByOrganizationAndStatusValidAndKey(@Param("organization") Organization organization,@Param("key") String key,
			@Param("listStatus") ArrayList<String> listStatus,Pageable pageable);

	@Query("from Farmer f inner join f.user u  where u.username = :username")
	public Farmer findByUsername(@Param("username") String username);

	@Query("from Farmer f inner join f.user u inner join f.organizations o where o = :organization and u.username = :username")
	public Farmer findByOrgannizationAndUsername(@Param("organization") Organization organization,
			@Param("username") String username);

	@Query("from Farmer f inner join f.user u where f not in ( select f from Farmer f inner join f.organizations o inner join f.user u where o = :organization )and u.username = :username")
	public Farmer findfarmerNotinOrgannizationbyusername(@Param("organization") Organization organization,
			@Param("username") String username);

	@Query("select f from Farmer f inner join f.user u inner join u.roles r where r.nameEng = :roleName")
	public List<Farmer> findAllByRoleName(@Param("roleName") String roleName, Pageable pageable);

	@Query("select count(*) from Farmer f where f.farmerId = :farmerId and" + "("
			+ "f in (select f.farmerId from Farmer f inner join f.user u inner join u.userinfields uif where f.farmerId = :farmerId)"
			+ ")")
	public Integer checkFkByFarmerId(@Param("farmerId") int farmerId);

	@Query("select count(*) from Farmer as f inner join f.subdistrict as sd inner join sd.district as d inner join d.province as p inner join f.user u where p = :province and d = :district and sd = :subdistrict  and u.username is not null")
	public Integer checkByProvinceAndDistrictAndSubdistrict(@Param("province") Province province,@Param("district") District district,@Param("subdistrict") Subdistrict subdistrict);

	@Query("select f from Farmer as f inner join f.subdistrict as sd inner join sd.district as d inner join d.province as p inner join f.user u where p = :province and d = :district and sd = :subdistrict and u.username is not null")
	public List<Farmer> findByProvinceAndDistrictAndSubdistrict(@Param("province") Province province,@Param("district") District district,@Param("subdistrict") Subdistrict subdistrict);
	
	@Query("select f.user from Farmer f inner join f.organizations as o where o.organizationId= :organizationId")
	public List<User> findUserByOrganization(@Param("organizationId") int organizationId, Pageable pageable);
	
	@Query("select count(f) from User u inner join u.farmer f inner join u.userinfields uif inner join uif.id uifid inner join f.organizations o where  uif.field = :field and uifid.role in :roleName and f.farmerId = :farmerId and o = :organization")
	public int checkFarmerInFieldByOrganizationAndField(@Param("field")Field field, @Param("roleName")List<String> roleName, @Param("farmerId")int farmerId, @Param("organization")Organization organization);
	
	@Query("select count(f) from Farmer f inner join f.organizations o  where o = :organization and f.farmerId = :farmerId and (f.farmerId not in (select f.farmerId from Farmer f join f.user u join u.userinfields uf join uf.id as ufid join uf.field fi join f.organizations o where  fi = :field and o = :organization))")
	public int checkFarmerNotInFieldByOrganizationAndField(@Param("organization") Organization organization,
			@Param("field") Field field,@Param("farmerId")int farmerId);
}
