<!DOCTYPE html>
<#assign base=request.contextPath>
<html lang="en">
  <head>
    <title>login</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<script type='text/javascript' src='${base}/js/all.js'></script>
    <link rel='stylesheet' href='css/signin.css' type='text/css'/>
  </head>

  <body>

    <div class="container">
      <form class="form-signin" action="${base}/user/login" method="post">
      	<h2 class="form-signin-heading">Please sign in</h2>
        <select name="name" class="form-control" style="margin-bottom:10px">
        	<#if userList??>
	        	<#list userList as user>
				  <option value ="${user.name}">${user.name}(${user.role})</option>
	        	</#list>
        	</#if>
		</select>
        <button class="btn btn-lg btn-primary btn-block" type="submit">登录</button>
      	<h3 class="form-signin-heading"><#if msg??>${msg}</#if></h3>
      </form>

    </div> <!-- /container -->
  </body>
</html>
