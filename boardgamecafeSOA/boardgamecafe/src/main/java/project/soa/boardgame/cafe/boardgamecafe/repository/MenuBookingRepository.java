package project.soa.boardgame.cafe.boardgamecafe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.soa.boardgame.cafe.boardgamecafe.model.ManuBooking;

@Repository
public interface MenuBookingRepository extends CrudRepository<ManuBooking, Integer> {

}
