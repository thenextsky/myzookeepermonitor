package cn.sky.zookeepermonitor.common;

public class Result {
	/**
	 * 登录成功
	 */
	public static final int LOGIN_SUCCESS = 100;
	/**
	 * 登录失败
	 */
	public static final int LOGIN_FAIL = 101;
	/**
	 * 未登录
	 */
	public static final int LOGIN_NO = 102;
	/**
	 * 处理成功
	 */
	public static final int HANDLE_SUCCESS = 200;
	/**
	 * 处理失败
	 */
	public static final int HANDLE_FAIL = 201;
	
	public static final Result RESULT_LOGIN_NO = new Result(LOGIN_NO, "您未登录");
	public static final Result RESULT_LOGIN_FAIL = new Result(LOGIN_FAIL, "登录失败");
	public static final Result RESULT_LOGIN_SUCCESS = new Result(LOGIN_SUCCESS, "登录成功");
	public static final Result RESULT_HANDLE_SUCCESS = new Result(HANDLE_SUCCESS, "处理成功");
	public static final Result RESULT_HANDLE_FAIL = new Result(HANDLE_FAIL, "处理失败");
	
	
	private int code;
	private Object data;
	public Result(){}
	public Result(int code, Object data) {
		super();
		this.code = code;
		this.data = data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}
