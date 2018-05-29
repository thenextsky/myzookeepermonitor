<!DOCTYPE html>
<#assign base=request.contextPath>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>login</title>

    <script type='text/javascript' src='${base}/js/jquery-3.3.1.min.js'></script>
	<script type='text/javascript' src='${base}/bootstrap-3.3.7-dist/js/bootstrap.min.js'></script>
	<link rel='stylesheet' href='${base}/bootstrap-3.3.7-dist/css/bootstrap.min.css' type='text/css' />
	<link rel='stylesheet' href='${base}/css/signin.css' type='text/css' />
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
