package org.nico.trap.service;

import java.util.List;

import org.nico.ourbatis.entity.Page;
import org.nico.trap.domain.po.Game;
import org.nico.trap.mapper.GameMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService{
	
	@Autowired
	private GameMapper gameMapper;
	
	public Game selectById(String key) {
		return gameMapper.selectById(key);
	}

	public Game selectEntity(Game condition) {
		return gameMapper.selectEntity(condition);
	}

	public List<Game> selectList(Game condition) {
		return gameMapper.selectList(condition);
	}

	public long selectCount(Object condition) {
		return gameMapper.selectCount(condition);
	}

	public List<Game> selectPage(Page<Object> page) {
		return gameMapper.selectPage(page);
	}

	public String selectId(Game condition) {
		return gameMapper.selectId(condition);
	}

	public List<String> selectIds(Game condition) {
		return gameMapper.selectIds(condition);
	}

	public int insert(Game entity) {
		return gameMapper.insert(entity);
	}

	public int insertSelective(Game entity) {
		return gameMapper.insertSelective(entity);
	}

	public int insertBatch(List<Game> list) {
		return gameMapper.insertBatch(list);
	}

	public int update(Game entity) {
		return gameMapper.update(entity);
	}

	public int updateSelective(Game entity) {
		return gameMapper.updateSelective(entity);
	}

	public int updateBatch(List<Game> list) {
		return gameMapper.updateBatch(list);
	}

	public int delete(Game condition) {
		return gameMapper.delete(condition);
	}

	public int deleteById(String key) {
		return gameMapper.deleteById(key);
	}

	public int deleteBatch(List<String> list) {
		return gameMapper.deleteBatch(list);
	}

}
