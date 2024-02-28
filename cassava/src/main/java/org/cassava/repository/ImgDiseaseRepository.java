package org.cassava.repository;

import java.util.List;
import org.cassava.model.ImgDisease;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ImgDiseaseRepository extends CrudRepository<ImgDisease, Integer> {

	@Query("from ImgDisease d where d.filePath like '%:filePath%'")
	public ImgDisease findByFilepath(@Param("filePath")String filePath);

	@Query("select count(Year(imgstp.uploadDate)),Year(imgstp.uploadDate) from ImgSurveyTargetPoint imgstp join imgstp.surveytargetpoint stp join stp.surveytarget st join st.targetofsurvey tos join tos.disease GROUP BY Year(imgstp.uploadDate)")
   	 public List<Object> findAmountOnUploadYear();
	
	@Query(value = "SELECT count(Month(imgstp.uploadDate)),Month(imgstp.uploadDate) from imgsurveytargetpoint imgstp join surveytargetpoint stp on imgstp.surveyTargetPointID= stp.surveyTargetPointID join surveytarget st on stp.surveyTargetID=st.surveyTargetID join targetofsurvey tos on st.targetOfSurveyID=tos.targetOfSurveyID join disease di on tos.targetOfSurveyID=di.diseaseID where di.diseaseID = :id AND Year(imgstp.uploadDate) = :y GROUP BY Month(imgstp.uploadDate)", nativeQuery = true)
    public List<Object> findImgdiseaseByDiseaseAndYear(@Param("id")int id,@Param("y")int y);
	
	@Query("from ImgDisease imgd")
	public List<ImgDisease> findAllByPagination (Pageable pageable);
}
