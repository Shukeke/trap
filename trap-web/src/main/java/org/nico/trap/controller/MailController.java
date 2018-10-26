package org.nico.trap.controller;

import java.util.concurrent.TimeUnit;

import javax.validation.constraints.Pattern;

import org.nico.trap.component.CacheComponent;
import org.nico.trap.component.MailComponent;
import org.nico.trap.consts.CacheConst;
import org.nico.trap.consts.SystemConst;
import org.nico.trap.domain.vo.ResponseCode;
import org.nico.trap.domain.vo.ResponseVo;
import org.nico.trap.utils.SmsUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/main")
@Validated
public class MailController {

	@Autowired
	private MailComponent mailComponent;
	
	@Autowired
	private CacheComponent cacheComponent;
	
	@ApiOperation(value = "发送业务邮件")
	@PostMapping("/business/send")
	public ResponseVo<String> send(
			@ApiParam(value = "账户", required = true) @Pattern(regexp = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$", message = "不符合邮箱格式") @RequestParam String account,
			@ApiParam(value = "类型，1：注册，2：登录", required = true) @RequestParam int type
			){
		ResponseCode code = null;
		String authCode = SmsUtils.createCode(SystemConst.MAIL_AUTH_CODE_LENGTH);
		boolean success = mailComponent.sendSimpleMail(account, "Trap Register Auth Code Mail !", "Auth Code: " + authCode);
		if(success) {
			if(type == 1) {
				cacheComponent.setEx(CacheConst.TRAP_USER_REGISTER_AUTH_CODE + account, authCode, 3L, TimeUnit.MINUTES);
			}else {
				cacheComponent.setEx(CacheConst.TRAP_USER_LOGIN_AUTH_CODE + account, authCode, 3L, TimeUnit.MINUTES);
			}
			
			code = ResponseCode.SUCCESS;
		}else {
			code = ResponseCode.ERROR;
		}
		return new ResponseVo<>(code);
	}
	
	
	
	
	
}
