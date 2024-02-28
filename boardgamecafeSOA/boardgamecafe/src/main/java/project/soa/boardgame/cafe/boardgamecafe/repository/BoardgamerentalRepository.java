package project.soa.boardgame.cafe.boardgamecafe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.soa.boardgame.cafe.boardgamecafe.model.BoardgameRental;
import project.soa.boardgame.cafe.boardgamecafe.model.Booking;

@Repository
public interface BoardgamerentalRepository extends CrudRepository<BoardgameRental, Integer> {

}
