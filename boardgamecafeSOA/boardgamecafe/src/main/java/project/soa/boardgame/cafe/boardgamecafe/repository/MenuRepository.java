package project.soa.boardgame.cafe.boardgamecafe.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import project.soa.boardgame.cafe.boardgamecafe.model.Menu;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Integer> {

}
