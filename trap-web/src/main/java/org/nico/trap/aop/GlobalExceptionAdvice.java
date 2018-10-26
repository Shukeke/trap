package org.nico.trap.aop;

import java.util.HashMap;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.nico.trap.domain.vo.ResponseCode;
import org.nico.trap.domain.vo.ResponseVo;
import org.nico.trap.exception.TrapException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


@ControllerAdvice
public class GlobalExceptionAdvice {

	@ResponseBody
	@ExceptionHandler(value = Exception.class)
	public ResponseVo<?> errorHandler(Exception ex) {
		ex.printStackTrace();
		return new ResponseVo<>(ResponseCode.ERROR);
	}
	
	@ResponseBody
	@ExceptionHandler(value = TrapException.class)
	public ResponseVo<?> errorHandler(TrapException ex) {
		ex.printStackTrace();
		return new ResponseVo<>(ex.getResponseCode());
	}
	
	@ResponseBody
	@ExceptionHandler(value = ConstraintViolationException.class)
	public ResponseVo<?> errorHandler(ConstraintViolationException ex) {
		ex.printStackTrace();
		return new ResponseVo<>(ResponseCode.ERROR).setMsg(ex.getMessage());
	}

}
