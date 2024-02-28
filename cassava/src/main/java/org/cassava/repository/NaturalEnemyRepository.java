package org.cassava.repository;

import java.util.List;

import org.cassava.model.NaturalEnemy;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface NaturalEnemyRepository extends CrudRepository<NaturalEnemy,Integer>{
	
	@Query("from NaturalEnemy n where n.commonName = :commonName")
	public NaturalEnemy findByCommonName(@Param("commonName") String commonName);
	
	@Query("from NaturalEnemy n where n.scientificName = :scientificName")
	public NaturalEnemy findByScientificName(@Param("scientificName") String scientificName);
	
	@Query("from NaturalEnemy n where n.type = :type")
	public List<NaturalEnemy> findByType(@Param("type") String type);
	
	@Query("from NaturalEnemy n where n.controlMethod = :controlMethod")
	public List<NaturalEnemy> findByControlMethod(@Param("controlMethod") String controlMethod);
	
	@Query("select n from NaturalEnemy n inner join n.targetofsurvey tos inner join tos.surveytargets st inner join st.survey s inner join s.planting p inner join p.field f inner join f.userinfields uif inner join uif.user u where s.surveyId =:id and u.username =:name")
    public List<NaturalEnemy> findBySurveyIDAndUserName(@Param("id")int id,@Param("name")String userName);
	
	@Query("select d from NaturalEnemy d inner join d.targetofsurvey tos where tos.name like %:name%")
    public NaturalEnemy findByName(@Param("name")String name);
	
	@Query("select count(*) from NaturalEnemy as n where n.naturalEnemyId = :naturalEnemyId and "
			+ "(n in (select n.naturalEnemyId from NaturalEnemy as n inner join n.targetofsurvey as t inner join t.permissiontargetofsurveys pts where n.naturalEnemyId = :naturalEnemyId)"
			+ "or n in (select n.naturalEnemyId from NaturalEnemy as n inner join n.targetofsurvey as t inner join t.surveytargets s where n.naturalEnemyId = :naturalEnemyId)"
			+ ")"
			)
	public Integer checkFkByNaturalEnemyId(@Param("naturalEnemyId")int naturalEnemyId);
}
