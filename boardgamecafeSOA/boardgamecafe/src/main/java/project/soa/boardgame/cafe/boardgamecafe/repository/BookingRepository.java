package project.soa.boardgame.cafe.boardgamecafe.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.soa.boardgame.cafe.boardgamecafe.model.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking, Integer> {
	
	@Query("from Booking b where b.date = :date")
	public List<Booking> findByDate(@Param("date")Date date);

}
