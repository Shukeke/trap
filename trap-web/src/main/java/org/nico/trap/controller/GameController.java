package org.nico.trap.controller;

import org.nico.noson.Noson;
import org.nico.trap.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired
	private GameService gameService;
	
	@GetMapping("/{id}")
	public String getGame(@PathVariable String id) {
		return Noson.reversal(gameService.selectById(id));
	}
}
