package org.nico.trap.aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Arrays;
import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.nico.trap.aop.AuthAop.AuthBy.AuthType;
import org.nico.trap.component.AuthComponent;
import org.nico.trap.domain.bo.UserBo;
import org.nico.trap.domain.po.User;
import org.nico.trap.domain.vo.ResponseCode;
import org.nico.trap.domain.vo.ResponseVo;
import org.nico.trap.exception.TrapException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



@Aspect
@Order(1)
@Component
public class AuthAop {

	@Autowired
	private AuthComponent authComponent;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(AuthAop.class);

	@Pointcut("@annotation(authBy)")
	public void authPath(AuthBy authBy){}
	
	@Around(value="authPath(authBy)")
	public Object aroundMethod(ProceedingJoinPoint point, AuthBy authBy){
		Object result = null;
		try {
			authComponent.getUser();
			result = point.proceed();
		} catch(TrapException e) {
			LOGGER.debug("Auth Faild：" + e.getResponseCode().getMsg());
			result = new ResponseVo<>(e.getResponseCode());
		} catch (Throwable e) {
			throw new RuntimeException(e);
		}
		return result;
	}


	@Target({ElementType.METHOD})
	@Retention(RetentionPolicy.RUNTIME)
	@Inherited
	public @interface AuthBy {
		
		/**
		 * 权限验证类型{@link AuthType}
		 * 
		 * @return
		 */
		AuthType[] value() default AuthType.LOGIN;
		
		public static enum AuthType{
			
			//登录
			LOGIN,

		}
	}

}
