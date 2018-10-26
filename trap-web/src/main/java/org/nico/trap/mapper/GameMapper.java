package org.nico.trap.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.nico.ourbatis.mapper.SimpleMapper;
import org.nico.trap.domain.po.Game;

public interface GameMapper extends SimpleMapper<Game, String>{

	public List<Game> selectHomeGames(@Param("condition") String condition);
	
	public List<Game> selectHomeGamesByUser(@Param("condition") String condition, @Param("userId") String userId);
}
