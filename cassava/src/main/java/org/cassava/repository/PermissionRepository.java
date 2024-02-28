package org.cassava.repository;

import java.util.Date;
import java.util.List;

import org.cassava.model.Organization;
import org.cassava.model.Permission;
import org.cassava.model.Staff;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionRepository extends CrudRepository<Permission, Integer> {

	@Query("select p FROM Permission p where p.staffByStaffId = :staff and p.type = :type")
	public List<Permission> findByStaffAndType(@Param("staff") Staff staff, @Param("type") String type);

	@Query("select p FROM Permission p where p.status in :status and p.type = :type")
	public List<Permission> findByListStatusAndType(@Param("status") List<String> status, @Param("type") String type);

	@Query("select p FROM Permission p where p.status in :status and p.type = :type and p.dateExpire <= :dateExpire")
	public List<Permission> findByListStatusAndTypeAndLessThanDateExpire(@Param("status") List<String> status,
			@Param("type") String type, @Param("dateExpire") Date dateExpire);

	@Query("select count(s.surveyId), tos.targetOfSurveyId from TargetOfSurvey tos inner join tos.surveytargets st inner join "
			+ "st.survey s inner join s.planting p inner join p.field f inner join f.subdistrict sd inner join sd.district d inner join d.province pv "
			+ "where tos.targetOfSurveyId in :tosId and (s.date between :frmDate and :enDate) and pv.provinceId in :pvId group by tos.targetOfSurveyId")
	public List<Object> findCountSurveyAndTargetOfSurveyIdByTargetOfSurveyIdAndDateAndProvince(
			@Param("tosId") List<Integer> tosId, @Param("frmDate") Date frmDate, @Param("enDate") Date enDate,
			@Param("pvId") List<Integer> pvId);

	@Query("select distinct p FROM Permission p join p.permissiontargetofsurveys pts join pts.targetofsurvey t join t.disease d join p.permissionprovinces ppv join p.permissionfiles ppf join p.staffByStaffId s join s.organization o where p.status in :status and p.type = :type and o = :organization")
	public List<Permission> findByListStatusAndTypeAndOrganization(@Param("status") List<String> status,
			@Param("type") String type, @Param("organization") Organization organization);

	@Query("select distinct p FROM Permission p inner join p.staffByStaffId s where s = :staff and p.status='Approve' and p.type = :type ")
	public List<Permission> findByStaffAndApproveStatusAndOrganization(@Param("staff")Staff staff,@Param("type")String type);

	@Query("select distinct p FROM Permission p inner join p.staffByStaffId s where s = :staff")
	public List<Permission> findByStaff(Staff staff);

}