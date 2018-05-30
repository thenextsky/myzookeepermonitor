package cn.sky.zookeepermonitor.controller;

import cn.sky.zookeepermonitor.common.Result;

public class BaseController {

	public static Result failResult(String msg) {
		Result result = new Result(Result.HANDLE_FAIL,msg);
		return result;
	}
}
