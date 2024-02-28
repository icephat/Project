package org.cassava.repository;

import java.util.List;

import org.cassava.model.Subdistrict;
import org.cassava.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubdistrictRepository extends CrudRepository<Subdistrict,Integer> {
	@Query("select s from Subdistrict as s inner join s.district as d where  d.districtId = :districtId")
    public List<Subdistrict> findByDistrictId(@Param("districtId")int districtId);

//	@Query("SELECT s from Subdistrict as s inner join s.fields as f inner join f.userinfields as uf inner join uf.user as u where u = :user ORDER BY s.subdistrictId")
//	public List<Subdistrict> findAllByUserinField(@Param("user") User user);
	
	@Query("SELECT s from Subdistrict as s inner join s.fields as f inner join f.userinfields as uf inner join uf.user as u where u = :user ORDER BY s.subdistrictId")
	public List<Subdistrict> findAllByUserinField(@Param("user") User user,Pageable pageable);
    
	@Query("select s from Subdistrict as s inner join s.fields as f inner join f.userinfields as uf inner join uf.user as u where u = :user and s in (select s from Subdistrict as s inner join s.fields as f inner join f.userinfields as uf inner join uf.user as u where u.userId =:userId)")
	public List<Subdistrict> findByUserInField(@Param("userId") int userId,@Param("user") User user,Pageable pageable); 
	
//	@Query("select s from Subdistrict as s inner join s.fields as f inner join f.userinfields as uf inner join uf.user as u where u = :user and s in (select s from Subdistrict as s inner join s.fields as f inner join f.userinfields as uf inner join uf.user as u where u.userId =:userId)")
//	public List<Subdistrict> findSubdistrictByUserInField(@Param("userId") int userId,@Param("user") User user);
}
