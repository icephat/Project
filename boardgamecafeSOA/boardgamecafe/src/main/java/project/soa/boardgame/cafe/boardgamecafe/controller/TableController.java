package project.soa.boardgame.cafe.boardgamecafe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import project.soa.boardgame.cafe.boardgamecafe.model.Table;
import project.soa.boardgame.cafe.boardgamecafe.services.TableService;

@RestController
@RequestMapping("/api")
@CrossOrigin("http://localhost:4200/")
public class TableController {

	@Autowired
	private TableService tableService;
	
	@GetMapping("/tables")
	public List<Table> getAllTable() {
		return tableService.findAll();
	}
	
	@GetMapping("/tables/{tableId}")
	public Table getTableById(@PathVariable("tableId")String tableId) {
		System.out.println(tableId);
		return tableService.findById(Integer.parseInt(tableId));
	}
	
	@PostMapping("/tables")
	public void addTable(@RequestBody Table table) {
		tableService.save(table);
	}
}
