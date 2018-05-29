package cn.sky.zookeepermonitor.init;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class Initor {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@PostConstruct
	public void init() {
		logger.info("init");
	}
}
