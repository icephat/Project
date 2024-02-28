package org.cassava.repository;

import java.util.List;

import org.cassava.model.User;
import org.cassava.model.UserInField;
import org.cassava.model.Field;
import org.cassava.model.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInFieldRepository extends CrudRepository<UserInField,Integer> {
//	@Query("from Userinfield uf where uf.fieldID  = :fid")
//	public List<Userinfield> findUserinfield(@Param("fid") int fid);	
	
	@Query("select uf from UserInField as uf inner join uf.id as uinfield where  role = :roleName")
	public List<UserInField> findByRolename(@Param("roleName")String roleName);
	
	@Query("select uf from UserInField as uf inner join uf.id as uinfield where  fieldId= :fieldId and userId = :userId and role = :roleName")
	public UserInField findByFieldIdAndUserIdAndRolename(@Param("fieldId")int fieldId,@Param("userId")int userId,@Param("roleName")String roleName);
	
	@Query("select uf from UserInField as uf inner join uf.id as uinfield where  fieldId=:fieldId and role in :roleName")
	public List<UserInField> findAllByRolenameAndFieldId(@Param("fieldId")int fieldId,@Param("roleName")List<String> roleName);
	
	@Query("select uf from UserInField as uf inner join uf.id as uinfield where  userId=:userId and role = :roleName")
	public List<UserInField> findAllByUserIdAndRoleName(@Param("userId")int userId,@Param("roleName")String roleName);
	
//	@Query("select uif from Survey s inner join s.planting p inner join p.field f inner join f.userinfields uif inner join uif.id where uif.id.role = 'farmerOwner' and s.surveyId =:surveyId")
//	public UserInField findFarmerOwnerBySurveyId(@Param("surveyId")int surveyId);
	
	@Query("select uif from Survey s inner join s.planting p inner join p.field as f inner join f.userinfields as uif inner join uif.id as uifid where s in (select s from Survey s inner join s.planting p inner join p.field as f inner join f.userinfields as uif inner join uif.id as uifid where uifid.role = :roleName and s.surveyId = :surveyId) and uif.user = :user")
	public UserInField findByUserIdAndSurveyIdAndRoleName(@Param("user")User user,@Param("surveyId")int surveyId,@Param("roleName")String roleName);
	
	@Query("select uf from UserInField as uf inner join uf.id as uinfield where  fieldId= :fieldId and role = :roleName")
	public UserInField findByFieldIdAndRoleName(@Param("fieldId")int fieldId,@Param("roleName")String roleName);
	
	@Query("select uf from UserInField as uf inner join uf.id as ufid inner join uf.field as uff where uff.organization=:organization and role=:roleName")
	public List<UserInField> findAllByOrganizationAndRolename(@Param("organization")Organization organization,@Param("roleName")String roleName);
	
	@Query("select uf from UserInField as uf inner join uf.id as ufid inner join uf.field as uff where uff in (:fields) and role=:roleName")
	public List<UserInField> findAllByFieldsAndRolename(@Param("fields")List<Field> fields, @Param("roleName")String roleName);
	
	@Query("select  count(userId) from UserInField as uf inner join uf.id as uinfield where  fieldId = :fieldId and userId = :userId and role = :roleName")
	public int checkFieldIdAndUserIdAndRolename(@Param("fieldId")int fieldId,@Param("userId")int userId,@Param("roleName")String roleName);
	
}