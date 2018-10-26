package org.nico.trap.controller;

import java.util.List;

import org.nico.noson.Noson;
import org.nico.noson.entity.NoType;
import org.nico.noson.util.string.StringUtils;
import org.nico.trap.component.TrapComponent;
import org.nico.trap.domain.po.Game;
import org.nico.trap.domain.vo.ResponseCode;
import org.nico.trap.domain.vo.ResponseVo;
import org.nico.trap.domain.vo.game.GameVo;
import org.nico.trap.domain.vo.game.GrammerProcessResultGameVo;
import org.nico.trap.service.GameService;
import org.nico.trap.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/game")
public class GameController {

	@Autowired
	private GameService gameService;

	@Autowired
	private TrapComponent trapComponent;

	@GetMapping("/{id}")
	public ResponseVo<Game> getGame(@PathVariable String id) {
		return new ResponseVo<Game>(ResponseCode.SUCCESS, gameService.selectById(id));
	}

	@PostMapping("/upload")
	public ResponseVo<Game> postGame(@RequestBody GameVo gameVo){
		ResponseCode code = null;
		Game game = null;
		
		String result = trapComponent.trapGrammerProcess(gameVo.getContent());
		if(StringUtils.isNotBlank(result)) {
			game = CommonUtils.convertPovo(gameVo, Game.class);
			game.setId(CommonUtils.getUUID());
			game.setContent(result);
			int modify = gameService.insert(game);
			if(modify == 1) {
				code = ResponseCode.SUCCESS;
			}else {
				code = ResponseCode.ERROR_ON_INSERT;
			}
		}else {
			code = ResponseCode.ERROR_ON_PROCESS;
		}
		return new ResponseVo<Game>(code, game);
	}
}
