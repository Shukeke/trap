package org.nico.trap.controller;

import org.nico.noson.util.string.StringUtils;
import org.nico.trap.component.CacheComponent;
import org.nico.trap.consts.CacheConst;
import org.nico.trap.domain.po.Role;
import org.nico.trap.domain.po.User;
import org.nico.trap.domain.vo.ResponseCode;
import org.nico.trap.domain.vo.ResponseVo;
import org.nico.trap.service.RoleService;
import org.nico.trap.service.UserService;
import org.nico.trap.utils.CommonUtils;
import org.nico.trap.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CacheComponent cacheComponent;
	
	@PostMapping("/register")
	public ResponseVo<User> register(
			@ApiParam(value = "账户", required = true) @RequestParam String account,
			@ApiParam(value = "密码", required = true) @RequestParam String password,
			@ApiParam(value = "昵称", required = true) @RequestParam String nickname,
			@ApiParam(value = "验证码", required = true) @RequestParam String authCode
			){
		ResponseCode code = null;
		
		String cacheAuthCode = cacheComponent.get(CacheConst.TRAP_USER_REGISTER_AUTH_CODE);
		if(StringUtils.isNotBlank(cacheAuthCode)) {
			if(authCode.equalsIgnoreCase(cacheAuthCode)) {
				User user = new User();
				user.setId(CommonUtils.getUUID());
				user.setAccount(account);
				user.setNickname(nickname);
				user.setPassword(EncryptUtils.encode(password));
				
				Role defaultRole = roleService.selectDefaultRole();
				user.setRuleId(defaultRole.getId());
				user.setRuleName(defaultRole.getName());
				user.setRuleType(defaultRole.getType());
				
				int modify = userService.insert(user);
				if(modify == 1) {
					code = ResponseCode.SUCCESS;
				}else {
					code = ResponseCode.ERROR_ON_INSERT;
				}
			}else {
				code = ResponseCode.ERROR_ON_AUTH_CODE_AUTH_FAIL;
			}
		}else {
			code = ResponseCode.ERROR_ON_AUTH_CODE_NOT_EXIST;
		}
		return new ResponseVo<>(code);
	}
	
	
	
}
