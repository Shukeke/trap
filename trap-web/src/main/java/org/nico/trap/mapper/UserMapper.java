package org.nico.trap.mapper;

import org.apache.ibatis.annotations.Param;
import org.nico.ourbatis.mapper.SimpleMapper;
import org.nico.trap.domain.po.User;
import org.nico.trap.domain.vo.user.UserRestVo;

public interface UserMapper extends SimpleMapper<User, String>{

	public UserRestVo selectRestUseInfo(@Param("userId") String userId);
}
