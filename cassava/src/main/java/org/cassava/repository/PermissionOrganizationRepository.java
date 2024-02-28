package org.cassava.repository;

import java.util.List;

import org.cassava.model.Permission;
import org.cassava.model.PermissionOrganization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionOrganizationRepository extends CrudRepository<PermissionOrganization, Integer> {
	
	@Query("select o.organizationId from PermissionOrganization po inner join po.organization o where po.permission = :permission")
	public List<Integer> findOrganizationIdByPermission(@Param("permission")Permission permission);
	
}
