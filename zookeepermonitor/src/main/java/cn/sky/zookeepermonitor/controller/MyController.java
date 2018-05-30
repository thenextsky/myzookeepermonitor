package cn.sky.zookeepermonitor.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.sky.zookeepermonitor.bean.ZkNode;
import cn.sky.zookeepermonitor.common.Result;
import cn.sky.zookeepermonitor.util.MyZkClient;

@Controller
public class MyController extends BaseController {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private MyZkClient client;

	@RequestMapping("/index")
	public String index() {
		logger.info("index");
		return "index";
	}

	@RequestMapping("/loginUI")
	public String loginUI() {
		logger.info("loginUI");
		return "loginUI";
	}

	@RequestMapping("/listUI")
	public String listUI() {
		logger.info("listUI");
		return "listUI";
	}

	@ResponseBody
	@RequestMapping("/list")
	public Result list(@RequestParam String parentnode) throws Exception {
		Result result = new Result();
		List<ZkNode> list = client.getChildren(new ZkNode(null, parentnode, null));
		result.setCode(Result.HANDLE_SUCCESS);
		result.setData(list);
		return result;
	}

	@RequestMapping("/getall")
	@ResponseBody
	public Result getall(@RequestParam String parentnode) throws Exception {
		Result result = new Result();
		List<ZkNode> list = client.getChildren(new ZkNode(null, parentnode, null));
		result.setCode(Result.HANDLE_SUCCESS);
		result.setData(list);
		return result;
	}

	@RequestMapping("/create")
	@ResponseBody
	public Result create(@RequestParam String parentnode) throws Exception {
		Result result = new Result();
		client.createNode(parentnode);
		result.setCode(Result.HANDLE_SUCCESS);
		return result;
	}
}
