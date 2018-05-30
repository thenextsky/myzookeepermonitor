function flush() {
	var params = {
		parentnode : "/"
	};
	$.ajax({
		type : 'POST',
		async : true,
		contentType : "application/x-www-form-urlencoded",
		url : "list",
		data : params,
		datatype : "json",// "xml", "html", "script", "json", "jsonp", "text".
		beforeSend : function() {
			loading.show();
		},
		success : function(data) {
			if (data.code == 200) {

			} else {
				alert("你未登录，请跳转到登录页面");
				window.location.href = "#loginUI";
			}
		},
		complete : function(XMLHttpRequest, textStatus) {
			loading.hide();
		},
		error : function() {
			alert("出错了，请稍后再试");
		}
	});
}
function Loading() {
	this.showed = false;
	var opts = {
		lines : 13, // The number of lines to draw
		length : 7, // The length of each line
		width : 4, // The line thickness
		radius : 14, // The radius of the inner circle
		corners : 1, // Corner roundness (0..1)
		rotate : 0, // The rotation offset
		color : '#000', // #rgb or #rrggbb
		speed : 1, // Rounds per second
		trail : 60, // Afterglow percentage
		shadow : false, // Whether to render a shadow
		hwaccel : false, // Whether to use hardware acceleration
		className : 'spinner', // The CSS class to assign to the spinner
		zIndex : 2e9, // The z-index (defaults to 2000000000)
		top : 'auto', // Top position relative to parent in px
		left : 'auto' // Left position relative to parent in px
	};
	var target;
	var spinner;
	this.init = function() {
		target = document.getElementById('foo');
		spinner = new Spinner(opts);
	}
	this.show = function() {
		this.showed = true;
		spinner.spin(target);
	}
	this.hide = function() {
		this.showed = false;
		spinner.stop();
	}
}
function Toast() {
	this.info = function(msg){
		bootoast({
			type:"info",
			message : msg,
			position : 'right-bottom',
			timeout : 2
		});
	}
	this.success = function(msg){
		bootoast({
			type:"success",
			message : msg,
			position : 'right-bottom',
			timeout : 2
		});
	}
	this.warning = function(msg){
		bootoast({
			type:"warning",
			message : msg,
			position : 'right-bottom',
			timeout : 2
		});
	}
	this.danger = function(msg){
		bootoast({
			type:"danger",
			message : msg,
			position : 'right-bottom',
			timeout : 2
		});
	}
}