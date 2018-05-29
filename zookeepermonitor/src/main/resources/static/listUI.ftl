<!DOCTYPE html>
<#assign base=request.contextPath>
<html lang="en">
	<head>
		<title>列表</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type='text/javascript' src='${base}/js/jquery-3.3.1.min.js'></script>
		<script type='text/javascript' src='${base}/bootstrap-3.3.7-dist/js/bootstrap.min.js'></script>
		<link rel='stylesheet' href='${base}/bootstrap-3.3.7-dist/css/bootstrap.min.css' type='text/css' />
		<script type='text/javascript' src='${base}/js/bootstrap-treeview.js'></script>
		<link rel='stylesheet' href='${base}/css/bootstrap-treeview.css' type='text/css' />
	</head>
	<body>
		<#include "head.ftl">
	
	    <div class="container">
	      <div class="panel panel-default">
			  <!-- Default panel contents -->
			  <div class="panel-heading">列表</div>
			  <!-- Table -->
			  <div id="tree"></div>
			</div>
	
	    </div> <!-- /container -->
	</body>
	<script>
		var data = "";
		function getTree(){
			return data;
		}
		data = [
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
		$(function(){
			$("#tree").treeview({data:getTree()});
		});
	</script>
</html>