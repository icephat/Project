package org.cassava.repository;

import java.util.List;

import org.cassava.model.Field;
import org.cassava.model.Planting;
import org.cassava.model.RequestDetail;
import org.cassava.model.Request;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestdetailRepository extends CrudRepository<RequestDetail, Integer> {

	
	@Query("SELECT rd,count(r.requestId) FROM RequestDetail rd inner join rd.request as r inner join r.staffByStaffId rs where rs.staffId = :userid GROUP BY r.requestId")
    public List<Object> findByUserId(@Param("userid")int userid);

	@Query("FROM RequestDetail rd inner join rd.request as r where r.requestId = :userid and rd.requestDetailId= :reid")
	public List<RequestDetail> findByUserIdAndRequestId(@Param("userid")int userid,@Param("reid")int reid);

	@Query("FROM RequestDetail rd inner join rd.request as r where r.requestId = :reid")
	public List<RequestDetail> findByRequestId(@Param("reid")int reid);

	@Query("SELECT rd,count(r.requestId) FROM RequestDetail rd inner join rd.request as r inner join r.staffByStaffId rs where rs.staffId = :userid and r.status='Waiting' GROUP BY r.requestId")
	public List<Object> findByUserIdAndWaiting(@Param("userid")int userid);

	@Query("SELECT rd,count(r.requestId) FROM RequestDetail rd inner join rd.request as r inner join r.staffByStaffId rs where rs.staffId = :userid and r.status='Approve' GROUP BY r.requestId")
	public List<Object> findByUserIdAndApprove(@Param("userid")int userid);

		@Query("SELECT rd,count(r.requestId) FROM RequestDetail rd inner join rd.request as r inner join r.staffByStaffId rs where rs.staffId = :userid and r.status='Waiting' GROUP BY r.requestId")
	public List<Object> findCountRequestAndRequestDetailAndByUserIdAndWaiting(@Param("userid")int userid);
	
	@Query("SELECT rd,count(r.requestId) FROM RequestDetail rd inner join rd.request as r inner join r.staffByStaffId rs where rs.staffId = :userid GROUP BY r.requestId")
	public List<Object> findCountRequestAndRequestDetailAndByUserId(@Param("userid")int userid);

	@Query("SELECT ff from Field as ff where ff.fieldId in (SELECT DISTINCT(f.fieldId) as fid FROM RequestDetail as rd  INNER JOIN rd.request as r INNER JOIN rd.surveyTarget as st INNER JOIN st.survey as s INNER JOIN s.planting as p INNER JOIN p.field as f WHERE r.requestId = :rid)")
	public List<Field> findFieldByRequestId (@Param("rid")int rid);

	@Query("SELECT pp from Planting as pp where pp.plantingId in (SELECT DISTINCT(p.plantingId) as pid FROM RequestDetail as rd  INNER JOIN rd.request as r INNER JOIN rd.surveyTarget as st INNER JOIN st.survey as s INNER JOIN s.planting as p WHERE r.requestId = :rid)")
	public List<Planting> findPlatingdByRequestId(@Param("rid")int rid);

}
