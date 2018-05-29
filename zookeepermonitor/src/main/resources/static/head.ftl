<!-- Static navbar -->
<nav class="navbar navbar-default navbar-static-top">
  <div class="container">
    <div class="navbar-header">
      <a class="navbar-brand" href="${base}/index">ZookeeperMonitor</a>
    </div>
    <ul class="nav navbar-nav">
	    <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">下拉菜单1<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li class="dropdown-header">菜单1</li>
            <li><a href="#">菜单11</a></li>
            <li><a href="#">菜单12</a></li>
            <li role="separator" class="divider"></li>
            <li class="dropdown-header">菜单2</li>
            <li><a href="#">菜单21</a></li>
            <li><a href="#">菜单22</a></li>
          </ul>
        </li>
	    <li><a href="#">普通菜单1</a></li>
	    <li><a href="#">普通菜单2</a></li>
	    <li><a href="#">普通菜单3</a></li>
	</ul>
    <div id="navbar" class="navbar-collapse collapse">
      <ul class="nav navbar-nav navbar-right">
      	<#if user??>
	        <li class="active"><a href="${base}/logout">退出</a></li>
	    	<#else>
	    	<li class="active"><a href="${base}/loginUI">登录</a></li>
	    </#if>
      </ul>
    </div>
  </div>
</nav>