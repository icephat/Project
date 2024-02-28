package org.cassava.repository;

import java.util.ArrayList;
import java.util.List;

import org.cassava.model.Organization;
import org.cassava.model.Request;
import org.cassava.model.TargetOfSurvey;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface RequestRepository extends CrudRepository<Request, Integer> {

	@Query("FROM Request r inner join r.staffByStaffId rs where rs.staffId = :userid")
	public List<Request> findByUserId(int userid);
	
	@Query("FROM Request r inner join r.staffByStaffId rs where rs.staffId = :userid and r.type = :type")
	public List<Request> findByUserIdAndType(@Param("userid")int userid, @Param("type")String type);
	
	@Query("FROM Request r inner join r.staffByStaffId rs where rs.organization.organizationId = :orid and r.type = :type and r.status in :status")
	public List<Request> findByOrganizationIdAndTypeAndStatus(@Param("orid")int orid, @Param("type")String type, @Param("status")ArrayList<String> status);

	@Query("FROM Request r where r.status = 'Approve'")
	public List<Request> findByStatusApprove();

	@Query("select distinct(tos) FROM Request r inner join r.requestdetails rede inner join rede.surveyTarget st inner join st.targetofsurvey tos  where r.requestId = :rdid ")
	public List<TargetOfSurvey> findtargetOfSurveyByRequestId(@Param("rdid")int rdid);

	@Query("FROM Request r where r.statusDppatho in :status and r.type = :type and r.status in :statusre")
	public List<Request> findByStatusDppathoAndOrganizationAndTypeAndStatusRequest(@Param("status")List<String> status, @Param("type")String type,@Param("statusre")List<String> statusre);
	
	@Query("FROM Request r where r.statusDae in :status and r.type = :type and r.status in :statusre")
	public List<Request> findByStatusDaeAndOrganizationAndTypeAndStatusRequest(@Param("status")List<String> status, @Param("type")String type,@Param("statusre")List<String> statusre);

	@Query("FROM Request r where r.statusDa in :status and r.type = :type and r.status in :statusre")
	public List<Request> findByStatusDaoAndOrganizationAndTypeAndStatusRequest(@Param("status")List<String> status, @Param("type")String type,@Param("statusre")List<String> statusre);

	@Query("FROM Request r where r.status in :status and r.type = :type")
	public List<Request> findByStatus(@Param("status")List<String> status, @Param("type")String type);

}
