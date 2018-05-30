package cn.sky.zookeepermonitor.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sky.zookeepermonitor.common.Result;

@ControllerAdvice(basePackages= {"cn.sky.zookeepermonitor.controller"})
public class MyExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@ExceptionHandler
	@ResponseBody
	public Result handleException(Exception e) {
		logger.error(e.getMessage(),e);
		Result result = new Result();
		result.setCode(Result.HANDLE_FAIL);
		result.setData(e.getMessage());
		return result;
//		return Result.RESULT_HANDLE_FAIL;
	}
}
