package org.cassava.repository;

import java.util.List;

import org.cassava.model.Permission;
import org.cassava.model.Province;
import org.cassava.model.PermissionProvince;
import org.cassava.model.Survey;
import org.cassava.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionProvinceRepository extends CrudRepository<PermissionProvince, Integer> {
	
	@Query("select pv from PermissionProvince pp inner join pp.province pv where pp.permission = :permission")
	public List<Province> findProvinceByPermission(@Param("permission")Permission permission);
	
	@Query("select pv.provinceId from PermissionProvince pp inner join pp.province pv where pp.permission = :permission")
	public List<Integer> findProvinceIdByPermission(@Param("permission")Permission permission);
}
