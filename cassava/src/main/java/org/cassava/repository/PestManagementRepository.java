package org.cassava.repository;

import java.util.List;

import org.cassava.model.PestManagement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PestManagementRepository extends CrudRepository<PestManagement,Integer>{
	
	@Query("from PestManagement p where p.pesticideName=:pesticideName")
	public PestManagement findByName(@Param("pesticideName")String pesticideName);
	
	@Query("from PestManagement p where p.pesticideNameEng=:pesticideNameEng")
	public PestManagement findByEngName(@Param("pesticideNameEng")String pesticideNameEng);
	
	@Query("from PestManagement p where p.pest.pestId=:pestId")
	public List<PestManagement>findAllById(@Param("pestId")int id);
	
	@Query("from PestManagement p where p.instruction=:instruction")
	public List<PestManagement> findByInstruction(@Param("instruction")String instruction);

}
