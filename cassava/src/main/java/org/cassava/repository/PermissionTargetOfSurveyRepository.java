package org.cassava.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.cassava.model.Permission;
import org.cassava.model.PermissionTargetOfSurvey;
import org.cassava.model.Province;
import org.cassava.model.TargetOfSurvey;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PermissionTargetOfSurveyRepository extends CrudRepository<PermissionTargetOfSurvey, Integer>{

	@Query("SELECT ptos FROM PermissionTargetOfSurvey as ptos INNER JOIN ptos.permission as p where p.permisisonId=:id")
	List<PermissionTargetOfSurvey> findByPermissionId(@Param("id") int id);

	@Query("select t from PermissionTargetOfSurvey pt inner join pt.targetofsurvey t where pt.permission = :permission")
	public List<TargetOfSurvey> findtargetofsurveyByPermission(@Param("permission")Permission permission);
	
	@Query("select t from PermissionTargetOfSurvey pt inner join pt.targetofsurvey t where pt.permission = :permission and pt.type=:type")
	public List<TargetOfSurvey> findtargetofsurveyByPermissionAndType(@Param("permission")Permission permission,@Param("type") String type);
	
	@Query("select t.targetOfSurveyId from PermissionTargetOfSurvey pt inner join pt.targetofsurvey t where pt.permission = :permission")
	public List<Integer> findtargetofsurveyIdByPermission(@Param("permission")Permission permission);
	
	@Modifying
	@Transactional
	@Query(value = "Delete pts from permissiontargetofsurvey pts where pts.targetOfSurveyID = :targetOfSurveyID", nativeQuery = true)
	public void deletePermissiontargetofsurveyByTargetOfSurveyID(@Param("targetOfSurveyID") int targetOfSurveyID);
}
