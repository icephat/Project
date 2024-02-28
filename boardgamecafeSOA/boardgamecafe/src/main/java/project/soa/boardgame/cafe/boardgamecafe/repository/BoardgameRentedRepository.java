package project.soa.boardgame.cafe.boardgamecafe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.soa.boardgame.cafe.boardgamecafe.model.BoardgameRented;
import project.soa.boardgame.cafe.boardgamecafe.model.Booking;

@Repository
public interface BoardgameRentedRepository extends CrudRepository<BoardgameRented, Integer> {

}
