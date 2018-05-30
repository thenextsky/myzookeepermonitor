var loading;
var toast;
function getTree(){
	var data = [
	{
		text:"/",
		nodes:[
			{
				text:"/dubbo",
				nodes:[
					{
						text:"/dubbo/cn.sky.testdubbo.service.UserService",
						nodes:[
							{text:"/dubbo/cn.sky.testdubbo.service.UserService/consumers"},
							{text:"/dubbo/cn.sky.testdubbo.service.UserService/configurators"},
							{text:"/dubbo/cn.sky.testdubbo.service.UserService/routers"},
							{text:"/dubbo/cn.sky.testdubbo.service.UserService/providers"},
							]
					},
					{
						text:"/dubbo/com.alibaba.dubbo.monitor.MonitorService",
						nodes:[
							{text:"/dubbo/com.alibaba.dubbo.monitor.MonitorService/consumers"},
							{text:"/dubbo/com.alibaba.dubbo.monitor.MonitorService/configurators"},
							{text:"/dubbo/com.alibaba.dubbo.monitor.MonitorService/routers"},
							{text:"/dubbo/com.alibaba.dubbo.monitor.MonitorService/providers"},
							]
					}
					]
			},
			{
				text:"/zookeeper",
				nodes:[
					{text:"/zookeeper/quota"}
					]
			}]
	}];
	return data;
}
$(function(){
	//初始化树状结构
	$("#tree").treeview({data:getTree()});
	
	//初始化进度圈
	loading = new Loading();
	loading.init();
	
	//初始化Toast
	toast = new Toast();
	
	//在拦截发送请求之前可以使用：
	$.ajaxSetup({
		beforeSend:function(xhr) {
			console.log("beforesend");
			xhr.setRequestHeader('Authorization','Token 123');
		}
	});
	//在接受到数据后做统一处理
	$( document ).ajaxSuccess(function( event, request, settings ) {
	    console.log("success:"+request.status);
	});
	$( document ).ajaxError(function( event, request, settings ) {
		console.log("error:"+request.status);
	});
	//
});