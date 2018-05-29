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
import cn.sky.zookeepermonitor.util.MyZkClient;

@Controller
public class MyController {
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
	@RequestMapping("/getall")
	@ResponseBody
	public List<ZkNode> getall(@RequestParam String p) {
		try {
			List<ZkNode> list = client.getChildren(new ZkNode(null, p, null));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public String create(@RequestParam String p) {
		try {
			client.createNode(p);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "no";
		}
	}
}
