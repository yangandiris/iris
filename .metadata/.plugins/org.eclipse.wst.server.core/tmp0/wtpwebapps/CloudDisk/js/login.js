$(document).ready(function() {
			var btn = $.fn.button.noConflict()
			$.fn.btn = btn // 以上2行代码解决bootstrap与jqueryui的button冲突问题
			$('#reg-slider').tooltip({
						title : "向右滑动解锁",
						placement : 'right'

					});
			$('#reg-slider').button();
			$('#emailreg').click(function() {
						$(this).addClass('btn-warning active');
						$('#phonereg').removeClass('btn-warning active');
						$('.regformbyphone').slideUp();
						$('.regformbyemail').slideDown();
						$('#regbutton').addClass("disabled ");
						regvalidate.resetForm();
						$('#regform')[0].reset();
						layer.closeTips()
					});
			$('#phonereg').click(function() {
						$(this).addClass('btn-warning active');
						$('#emailreg').removeClass('btn-warning active');
						$('.regformbyphone').slideDown();
						$('.regformbyemail').slideUp();
						$('#regbutton').addClass("disabled ");
						regvalidate.resetForm();
						$('#regform')[0].reset();
						layer.closeTips()
					});
			/* 滑动验证模块 */
			$("#reg-slider").draggable({
						containment : "parent",
						revert : true,
						stop : function(event, ui) {

							if (ui.position.left > 0.6
									* $('.well-slider').width()) {
								var code = getcode();
								$.post("code.action", {
											yzmcode : code
										})
								$('#code').val(code);
								$('#regbutton').removeClass("disabled ")
							}
						}
					});
			/*登录*/
			$('#username,#password').val('').focus(function(){layer.closeAll()})
			$('#login_btn').click(function(){
				var u=$('#username')
				var p=$('#password')
				if(''==u.val()){
					layer.tips('用户名不能为空', '#username')
				}else if(''==p.val()){
					layer.tips('密码不能为空', '#password')
				}else{
					$.post('login.action',{
						'username':u.val(),
						'password':p.val()
					},function(data){
						if(data.msg=='yes'){
							layer.msg("登陆成功！正在跳转",2,{type:1},function(){
								window.location.href='mainUI.jsp'
							})
							
						}else if(data.msg=='name'){
							layer.tips('用户名不存在', '#username',{'time':1})
						}else{
							layer.tips('密码错误', '#password',{'time':1})
						}
					})
				}
				
			})

			/* 注册验证模块 */
			var regvalidate = $("#regform").validate({
						rules : {
							phonenum : {
								required : true,
								phone : true
							},
							emailnum : {
								required : true,
								email : true
							},
							password1 : {
								required : true,
								minlength : 8
							},
							password2 : {
								required : true,
								equalTo : '#password1'
							},
							dxyzm : {
								required : true
							}
						},
						messages : {
							phonenum : {
								required : "请输入您的手机号码"
							},
							emailnum : {
								required : "请输入您的邮箱账号"

							},
							password1 : {
								required : "密码不能为空",
								minlength : "密码长度不能小于8位"
							},
							password2 : {
								required : "密码不能为空"

							},
							dxyzm : {
								required : "请输入您收到的验证码"
							}
						},
						errorPlacement:function(error,element){
							var errormsg=layer.tips($(error).text(),'#'+element.attr('id'),{'time':1,'guide':1,'more':true})
						},
						submitHandler : function(form) {
							$(form).ajaxSubmit({
										type : "post",
										dataType : "json",
										url : "reg.action",
										success : function(data) {
											if(data.msg[0]=='existphone'){
												layer.tips('该手机号已被注册', '#phonenum',{'time':1,'guide':2})
											}else if(data.msg[0]=='existemail'){
												layer.tips('该邮箱已被注册', '#emailnum',{'time':1,'guide':2})
											}else if(data.msg=='yes'){
												layer.msg("注册成功！请登陆",3,{type:1},function(){
													layer.closeAll()
												});
												
											}else{
												layer.tips(data.msg, '#regform')
											}
										}

									});
						}

					});

			
		})


// 定义一个产生随机6位数的函数
function getcode() {
	var code = "";
	for (var i = 0; i < 6; i++) {

		code += Math.floor(Math.random() * 10)
	}
	return code;

}
// 退出登录函数
  function loginquit(){
		$.post ("quit.action",function(){
		      window.location.reload();
		});
						
}
  function reg(){
	  $('#regform input').val('');
	  $.layer({
			type:1,
			title:"注册",
			shade : [0.5, '#000'],
			area : ['400px', '350px'],
			closeBtn : [1, true],
			page: {
			    dom:"#regmodal"
			}
					
		
		});
  }
  
 