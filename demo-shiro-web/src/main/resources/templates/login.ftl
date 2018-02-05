<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>shiro+freemaker+springboot+redis +mysql 权限控制后台演示</title>
		<link href="${request.contextPath}/media/css/common.css" rel="stylesheet" />
		<link href="${request.contextPath}/media/css/login.css" rel="stylesheet" />
		
		<script> 
		   (function() {
		     if (! 
		     /*@cc_on!@*/
		     0) return;
		     var e = "abbr, article, aside, audio, canvas, datalist, details, dialog, eventsource, figure, footer, header, hgroup, mark, menu, meter, nav, output, progress, section, time, video".split(', ');
		     var i= e.length;
		     while (i--){
		         document.createElement(e[i])
		     } 
		})() 
		</script>
		
		<script src="${request.contextPath}/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>
	</head>
	<body>
		<header>
			<div class="container">
				<span class="logo-title">shiro+freemaker+springboot+redis +mysql 权限控制后台演示</span>
			</div>			
		</header>
		<section class="login-wrap">
			<div class="login-inner">
				<div class="login-left">
					<!--<img src="images/earth.png" />-->
				</div>
				<div class="login-right">
					<div class="login-title">用户登录</div>
					<div class="warning" id="loginError" ></div>
					<div class="passport-login">
						<form id="loginForm" method="post">
							<p class="p1">
								<label for="userName" class="user-icon"></label>
								<input type="text" name="username" id="username"  placeholder="用户名"/>
							</p>
							<p class="p2">
								<label for="userPwd" class="psd-icon"></label>
								<input type="password" name="password" id="password"  placeholder="密码"/>
							</p>
							<p class="p3">
								<input type="button" onClick="login();" class="submit-btn" value="登录"/>
							</p>
							 
						</form>
					</div>
				</div>
			</div>
		</section>
		<footer class="foot container">
				<p> shiro+freemaker+springboot+redis +mysql 权限控制后台演示</p>
		</footer>
		
	</body>
</html>
<script>
		function login() {
	            	$.ajax({   
           		     url:'/login',   
           		     type:'post',   
           		     data:'username='+ $("#username").val()+'&password='+ $("#password").val(),
           		     async : true,// 默认为true 异步   
           	         error:function(){   
           		        alert('error');   
           		     },   
           		     success:function(data){   
           		    	// alert(data);
           		    	var data = $.parseJSON(data); 
           		    	
           		    	 if(data.msg=="success"){
           		    		 window.location.href =data.url
           		    	 }else{
           		    		// alert(data.reason);

           		    		 $("#loginError").html(data.returnStr);
           		    		 
           		    	 }
           		    	 
           		     }
           		 });
	            	
	            }
</script>
