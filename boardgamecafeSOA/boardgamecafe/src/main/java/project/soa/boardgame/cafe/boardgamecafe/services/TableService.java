package project.soa.boardgame.cafe.boardgamecafe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.soa.boardgame.cafe.boardgamecafe.model.Table;
import project.soa.boardgame.cafe.boardgamecafe.repository.TableRepository;

@Service
public class TableService {

	@Autowired
	TableRepository tableRepository;
	
	public List<Table> findAll(){
		return (List<Table>) tableRepository.findAll();
	}
	
	public Table findById(int tableId) {
		return tableRepository.findById(tableId).get();
	}
	
	public void save(Table table) {
		tableRepository.save(table);
	}
	
	public void deleteById(int tableId) {
		tableRepository.deleteById(tableId);
	}
	
	public List<Table> findByStatus(String status){
		return tableRepository.findByStatus(status);
	}
}
