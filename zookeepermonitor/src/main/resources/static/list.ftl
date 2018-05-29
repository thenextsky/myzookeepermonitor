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
	</head>
	<body>
		<#include "head.ftl">
	
	
	    <div class="container">
	
	      <div class="panel panel-default">
			  <!-- Default panel contents -->
			  <div class="panel-heading">列表</div>
			  <!-- Table -->
			  <table class="table">
			    <thead>
			    	<tr>
			    		<th>id</th>
			    		<th>账号</th>
			    		<th>角色</th>
			    	</tr>
			    </thead>
			    <tbody>
			    	<#if userList??>
				    	<#list userList as user>
					    	<tr>
					    		<td>${user.id}</td>
					    		<td>${user.name}</td>
					    		<td>${user.role}</td>
					    	</tr>
				    	</#list>
			    	</#if>
			    </tbody>
			  </table>
			</div>
	
	    </div> <!-- /container -->
	</body>
</html>