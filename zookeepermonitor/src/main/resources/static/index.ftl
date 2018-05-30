<!DOCTYPE html>
<#assign base=request.contextPath>
<html lang="en">
	<head>
		<title>首页</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type='text/javascript' src='${base}/js/all.js'></script>
	</head>
	<body>
		<#include "head.ftl">
	
	
	    <div class="container">
	
	      <div class="panel panel-default">
			  <!-- Default panel contents -->
			  <div class="panel-heading">zk节点列表</div>
			  <!-- Table -->
			  <table class="table">
			    <thead>
			    	<tr>
			    		<th>path</th>
			    		<th>data</th>
			    		<th>ephemeral</th>
			    		<th>numChildren</th>
			    		<th>ctime</th>
			    		<th>mtime</th>
			    	</tr>
			    </thead>
			    <tbody>
			    	<tr>
			    		<td>/dubbo</td>
			    		<td>呵呵</td>
			    		<td>true</td>
			    		<td>0</td>
			    		<td></td>
			    		<td>？？</td>
			    	</tr>
			    </tbody>
			  </table>
			</div>
	
	    </div> <!-- /container -->
	</body>
</html>