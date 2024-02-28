package org.cassava.repository;

import java.util.Date;  
import java.util.List;

import org.cassava.model.RegisterCode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterCodeRepository extends CrudRepository<RegisterCode,Integer>{
	
	@Query("from RegisterCode r where userType='Farmer' Order by expireDate DESC, startDate DESC")
	public List<RegisterCode> findAllFarmerOrder();	
	
	@Query("from RegisterCode r where userType='Staff' Order by expireDate DESC, startDate DESC")
	public List<RegisterCode> findAllUserOrder();

	@Query("from RegisterCode r where r.code=:code and r.expireDate >= current_timestamp and r.startDate <= current_timestamp and r.numUseRegist < r.numUserPermit")//
	public RegisterCode findValidCodeByCode(@Param("code") String code);
	
	@Query(value = "SELECT IF(r.code=:code,'active','inactive') from RegisterCode r where r.expireDate >= current_timestamp and r.startDate <= current_timestamp and r.numUseRegist < r.numUserPermit", nativeQuery = true)
	public String findStatusByCode(@Param("code")String code);

	@Query("FROM RegisterCode r WHERE userType=:userType AND r.expireDate < :dt")
	public List<RegisterCode> findByTypeAndExpiredate(@Param("userType")String userType, @Param("dt")Date dt);
	
	@Query("select r.userType from RegisterCode r where r.code = :code")
	public String findUserTypeByCode(@Param("code")String code);
	
	@Query("FROM RegisterCode r where r.code = :code")
	public RegisterCode findByCode(@Param("code")String code);
}