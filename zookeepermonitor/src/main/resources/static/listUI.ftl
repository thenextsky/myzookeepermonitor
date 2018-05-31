<!DOCTYPE html>
<#assign base=request.contextPath>
<html lang="en">
	<head>
		<title>列表</title>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<script type='text/javascript' src='${base}/js/all.js'></script>
	</head>
	<body>
		<#include "head.ftl">
	
	    <div class="container">
	    	<div class="panel panel-default">
				<div class="panel-heading">
					<button type="button" onclick="flush()" class="btn btn-default">刷新</button>
				</div>
				<div id="foo"></div>
				<div id="tree"></div>
			</div>
	
	    </div> <!-- /container -->
	</body>
	
</html>