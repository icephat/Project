package org.cassava.repository;

import java.util.List;

import org.cassava.model.Province;
import org.cassava.model.SurveyTarget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends CrudRepository<Province, Integer> {

	public Province findByName(String name);

	public Province findByCode(String code);

	@Query("select distinct p from SurveyTarget st inner join st.survey s inner join s.planting p inner join p.field f inner join f.subdistrict sd inner join sd.district d inner join d.province p where st in :sts")
	public List<Province> findBySurveyTarget(@Param("sts") List<SurveyTarget> sts);

	@Query("from Province pv where pv.provinceId in :pvIds")
	public List<Province> findByListId(@Param("pvIds") List<Integer> pvIds);
}
