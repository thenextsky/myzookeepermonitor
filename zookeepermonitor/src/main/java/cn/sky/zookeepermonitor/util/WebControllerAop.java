package cn.sky.zookeepermonitor.util;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.WebUtils;

import cn.sky.zookeepermonitor.annotation.LoginNeed;
import cn.sky.zookeepermonitor.bean.User;
import cn.sky.zookeepermonitor.common.Constants;
import cn.sky.zookeepermonitor.common.Result;

@Component
@Aspect
public class WebControllerAop {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Pointcut("execution(* cn.sky.zookeepermonitor.controller.*..*.*(..))")
	public void executeService() {
	}

	@Around(value="executeService()")
	public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint) throws NoSuchMethodException, SecurityException {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		@SuppressWarnings("unused")
		HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
		//
		Signature signature = proceedingJoinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature)signature;    
		Method targetMethod = methodSignature.getMethod();
		Method realMethod = proceedingJoinPoint.getTarget().getClass().getDeclaredMethod(signature.getName(), targetMethod.getParameterTypes()); 
		
		LoginNeed[] loginNeeds = realMethod.getAnnotationsByType(LoginNeed.class);
		boolean needLogin = false;
		if(loginNeeds!=null&&loginNeeds.length>0) {
			needLogin = true;
		}
		User user = (User) WebUtils.getSessionAttribute(request,Constants.LOGIN_USER);
		boolean logined = user!=null;
		try {
			Result result = new Result();
			if(needLogin&&!logined) {
                return Result.RESULT_LOGIN_NO;
			}
			result = (Result) proceedingJoinPoint.proceed();
			return result;
		} catch (Throwable throwable) {//realMethod抛出的异常不在这里捕获，在MyExceptionHandler处理
			logger.error(throwable.getMessage(),throwable);
			throwable.printStackTrace();
			return Result.RESULT_HANDLE_FAIL;
		}
	}
}
