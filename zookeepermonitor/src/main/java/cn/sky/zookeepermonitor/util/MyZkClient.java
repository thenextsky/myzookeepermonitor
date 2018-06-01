package cn.sky.zookeepermonitor.util;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent.Type;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import cn.sky.zookeepermonitor.bean.ZkNode;

@Component
@Scope("prototype")
public class MyZkClient {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final String ZNODE_ENCODING = "UTF-8";
	private CuratorFramework client;
	
	@Value("${cn.sky.zookeepermonitor.zookeeperiplist}")
	private String connectString;
	@Value("${cn.sky.zookeepermonitor.reinitsleepsecond}")
	private long reinitsleepsecond;
	
	@PostConstruct
	public void init(){
        client = CuratorFrameworkFactory.newClient(connectString, new ExponentialBackoffRetry(1000, 3));
        if(client==null) {
        	logger.error("zookeeper client init failed!");
			throw new RuntimeException("zookeeper client init failed!");
		}else {
			if(!client.getState().equals(CuratorFrameworkState.STARTED)) {
				client.start();
				logger.info("zookeeper client init success!");
			}
			watchConnectionState();
			watchNode("/");
		}
    }
	
	 /**
     * 打印path下的子节点
     * @param path
     * @param prex
     * @throws Exception
     */
    public void printZK(String path,String prex) throws Exception {
        System.out.println(prex+URLDecoder.decode(path.substring(path.lastIndexOf("/"), path.length()), ZNODE_ENCODING));
        if(client.checkExists().forPath(path)!=null) {
            List<String> cs = client.getChildren().forPath(path);
            for (String s : cs) {
                if(path.equals("/")) {
                    printZK(path+s,prex+"  ");
                }else {
                    printZK(path+"/"+s,prex+"  ");
                }
            }
        }
    }
    
    private class MyPathChildrenCacheListener implements PathChildrenCacheListener{
        private String parentPath;
        public MyPathChildrenCacheListener(String parentPath) {
            this.parentPath = parentPath;
        }
        @Override
        public void childEvent(CuratorFramework client, PathChildrenCacheEvent event) throws Exception {
            Type type = event.getType();
            ChildData childData = event.getData();
            String childPath = null;
            byte[] data = null;
           
            switch(type) {
	            case CHILD_ADDED://在父节点(parentPath)下添加子节点(childPath)后触发
	            	 if(childData!=null) {
	                 	childPath = childData.getPath();
	                 	if(client.checkExists().forPath(childPath)!=null) {
	                 		data = client.getData().forPath(childPath);
	                 	}
	                 }
	            	logger.info("znode_added:"+childPath+(data==null?"":",data:"+new String(data,ZNODE_ENCODING)));
	                break;
	            case CHILD_REMOVED://父节点下的子节点被删除后触发
	            	 if(childData!=null) {
	                 	childPath = childData.getPath();
	                 }
	            	logger.info("znode_removed:"+childPath);
	                break;
	            case CHILD_UPDATED://父节点下的子节点的值被修改后触发
	            	 if(childData!=null) {
	                 	childPath = childData.getPath();
	                 	if(client.checkExists().forPath(childPath)!=null) {
	                 		data = client.getData().forPath(childPath);
	                 	}
	                 }
	            	logger.info("znode_updated:"+childPath+(data==null?"":",data:"+new String(data,ZNODE_ENCODING)));
	                break;
	            case CONNECTION_LOST:
	            	logger.warn("connection_lost");
	                break;
	            case CONNECTION_SUSPENDED:
	            	logger.info("connection_suspended");
	                break;
	            case INITIALIZED:
	            	logger.info("initialized");
	                break;
	            case CONNECTION_RECONNECTED:
	            	logger.warn("connection_reconnected");
	                break;
	            default:
	            	logger.warn("default");
	                break;
            }
        }
    }
    
    private class MyConnectionStateListener implements ConnectionStateListener{
        @Override
        public void stateChanged(CuratorFramework client, ConnectionState newState) {
            switch (newState) {
            case CONNECTED://第一次连接zkServer成功后触发
            	logger.info("zookeeper connected");
                break;
            case LOST://关闭zkServer，十几秒（时间不一定）后触发
            	logger.warn("zookeeper connection lost");
                break;
            case READ_ONLY:
            	logger.warn("zookeeper connection read_only");
                break;
            case RECONNECTED://关闭zkServer后，再启动zkServer马上触发
            	logger.warn("zookeeper reconnected");
                break;
            case SUSPENDED://关闭zkServer后马上触发
            	logger.warn("zookeeper connection suspended");
                break;
            default:
            	logger.warn("default");
                break;
            }
        }
    }
    
    /**
     * 监听客户端连接状态
     */
    public void watchConnectionState() {
        client.getConnectionStateListenable().addListener(new MyConnectionStateListener());
    }

    /**
     * 监听path节点下的子节点变化
     * @param parentNode 父节点
     * @throws Exception
     */
    public void watchNode(String parentNode) {
		PathChildrenCache cache = new PathChildrenCache(client, parentNode, false);
        try {
			cache.start();
//			调用该方法之后，path节点会被创建，且为永久节点。
			cache.getListenable().addListener(new MyPathChildrenCacheListener(parentNode));
//			cache不能关闭
		} catch (Exception e) {
			logger.error("监听子节点失败",e);
			e.printStackTrace();
		}
    }
    
    /**
     * 获取子节点
     * @param parentNode
     * @throws Exception
     */
    public List<ZkNode> getChildren(ZkNode parentNode) throws Exception {
    	List<ZkNode> list = new ArrayList<ZkNode>();
        List<String> nodes = client.getChildren().forPath(parentNode.getPath());
        if(nodes!=null&&nodes.size()>0) {
        	for(String node:nodes) {
        		String path = (parentNode.getPath().equals("/")?"":parentNode.getPath())+"/"+node;
        		logger.info(path);
        		Stat stat = client.checkExists().forPath(path);
        		if(stat!=null) {
        			ZkNode zkNode = new ZkNode();
        			list.add(zkNode);
        			BeanUtils.copyProperties(stat, zkNode);
        			zkNode.setEphemeral(stat.getEphemeralOwner()==0?false:true);
        			zkNode.setPath(path);
        			zkNode.setParent(null);
        			zkNode.setData(client.getData().forPath(path));
        		}
        	}
        }
        return list;
    }
    
    /**
     * 创建ZNode节点
     * @param nodepath 节点path
     * @param modes 创建模式，不传参则为CreateMode.EPHEMERAL
     * @throws Exception
     */
    public void createNode(String nodepath,byte[] data,CreateMode...modes) throws Exception {
        Stat stat = client.checkExists().forPath(nodepath);
        if(stat!=null) {
        	throw new RuntimeException(nodepath+"节点已存在");
        }else {
        	logger.info(nodepath+"节点不存在，现在创建");
        	if(modes.length>=1) {
        		client.create().withMode(modes[0]).forPath(nodepath,data);
        	}else {
        		client.create().withMode(CreateMode.EPHEMERAL).forPath(nodepath,data);
        	}
        }
    }

	
}
