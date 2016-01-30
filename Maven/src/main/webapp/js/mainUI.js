$(document).ready(function() {
	username=getusername();//设置全局用户名
	if(username==null){
		window.location.href='login.html'
	}
	isloginlock=0;
	var btn = $.fn.button.noConflict()
			  $.fn.btn = btn // 以上2行代码解决bootstrap与jqueryui的button冲突问题
	var loads=''
	$(document).ajaxStart(function() {loads=layer.load('正在加载',2);}); 
	$(document).ajaxSuccess(function() { layer.close(loads)  }); 
	$(document.body).ajaxError(function() { layer.alert("请求出错，请刷新页面并稍候再试！",8) }); 
	$('#usermanager').click(function(){
	   $('.user-menu').filter(':not(:animated)').slideToggle();
	});
	$('.user-menu').hover(function(){ 
		  
		}, 
		function(){ 
		$('.user-menu').filter(':not(:animated)').slideToggle();
	});

	//网盘初始化
	$('.networkdisk-left').find('a:eq(0)').click();
})
 


function network_disk(_this){
	
	$('.networkdisk-left').find('a:eq(0)').click();
	
}
function getusername(){
	var username=''
	$.ajax({
		type:"POST",
		url:"getusername.action",
		async:false,
		success:function(data){
			username=data.username;
		}
	})
	return username;
	
}

