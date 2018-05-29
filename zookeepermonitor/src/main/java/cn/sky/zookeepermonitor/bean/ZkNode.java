package cn.sky.zookeepermonitor.bean;

import java.util.Arrays;
import java.util.Set;

public class ZkNode {
	public ZkNode(){}
	public ZkNode(ZkNode parent, String path, byte[] data) {
		this.parent = parent;
		this.path = path;
		this.data = data;
	}
	/**
	 * 父节点
	 */
	private ZkNode parent;
	/**
	 * 子节点
	 */
	private Set<ZkNode> children = null;
	/**
	 * znode子节点修改次数
	 */
	private int cversion;
	/**
	 * znode数据的修改次数
	 */
	private int dataVersion;
	/**
	 * 以距离时间原点(epoch)的毫秒数表示的znode创建时间
	 */
	private long ctime;
	/**
	 * 以距离时间原点(epoch)的毫秒数表示的znode最近修改时间
	 */
	private long mtime;
	
	/**
	 * znode数据长度
	 */
	private int dataLength;
	/**
	 * znode子节点个数
	 */
	private int numChildren;
	/**
	 * 节点路径
	 */
	private String path;
	/**
	 * 节点数据
	 */
	private byte[] data;
	/**
	 * 是否临时节点
	 */
	private boolean ephemeral;
	/**
	 * 如果znode是临时节点，则指示节点所有者的会话ID；如果不是临时节点，则为零
	 */
	private long ephemeralOwner;
	
	
	public long getEphemeralOwner() {
		return ephemeralOwner;
	}
	public void setEphemeralOwner(long ephemeralOwner) {
		this.ephemeralOwner = ephemeralOwner;
	}
	public boolean isEphemeral() {
		return ephemeral;
	}
	public void setEphemeral(boolean ephemeral) {
		this.ephemeral = ephemeral;
	}
	public ZkNode getParent() {
		return parent;
	}
	public void setParent(ZkNode parent) {
		this.parent = parent;
	}
	public Set<ZkNode> getChildren() {
		return children;
	}
	public void setChildren(Set<ZkNode> children) {
		this.children = children;
	}
	public int getCversion() {
		return cversion;
	}
	public void setCversion(int cversion) {
		this.cversion = cversion;
	}
	public int getDataVersion() {
		return dataVersion;
	}
	public void setDataVersion(int dataVersion) {
		this.dataVersion = dataVersion;
	}
	public long getCtime() {
		return ctime;
	}
	public void setCtime(long ctime) {
		this.ctime = ctime;
	}
	public long getMtime() {
		return mtime;
	}
	public void setMtime(long mtime) {
		this.mtime = mtime;
	}
	public int getDataLength() {
		return dataLength;
	}
	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}
	public int getNumChildren() {
		return numChildren;
	}
	public void setNumChildren(int numChildren) {
		this.numChildren = numChildren;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	
	
	@Override
	public String toString() {
		return "ZkNode [parent=" + parent + ", children=" + children + ", cversion=" + cversion + ", dataVersion="
				+ dataVersion + ", ctime=" + ctime + ", mtime=" + mtime + ", dataLength=" + dataLength
				+ ", numChildren=" + numChildren + ", path=" + path + ", data=" + Arrays.toString(data) + ", ephemeral="
				+ ephemeral + ", ephemeralOwner=" + ephemeralOwner + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((path == null) ? 0 : path.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ZkNode other = (ZkNode) obj;
		if (path == null) {
			if (other.path != null)
				return false;
		} else if (!path.equals(other.path))
			return false;
		return true;
	}
	
}
