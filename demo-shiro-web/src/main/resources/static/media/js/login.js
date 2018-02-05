var Login = function () {
    
    return {
        //main function to initiate the module
        init: function () {
        	
           $('.login-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            rules: {
	                username: {
	                    required: true
	                },
	                password: {
	                    required: true
	                },
	                remember: {
	                    required: false
	                }
	            },

	            messages: {
	                username: {
	                    required: "请输入用户名."
	                },
	                password: {
	                    required: "请输入密码."
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   
	                $('.alert-error', $('.login-form')).show();
	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.control-group').addClass('error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.control-group').removeClass('error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	            },

	            submitHandler: function (form) {
	            	$.ajax({   
           		     url:'/login',   
           		     type:'post',   
           		     data:'username='+ $("#username").val()+'&password='+ $("#password1").val(),
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
           		    		 $('.alert-error', $('.login-form')).show();
           		    	 }
           		    	 
           		     }
           		 });
	            	
	            }
	        });

	        $('.login-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.login-form').validate().form()) {
	                    window.location.href = "index.html";
	                }
	                return false;
	            }
	        });

	        $('.forget-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            ignore: "",
	            rules: {
	                email: {
	                    required: true,
	                    email: true
	                }
	            },

	            messages: {
	                email: {
	                    required: "请填写邮箱."
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   

	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.control-group').addClass('error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.control-group').removeClass('error');
	                label.remove();
	            },

	            errorPlacement: function (error, element) {
	                error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	            },

	            submitHandler: function (form) {
	            	$.ajax({   
	            		     url:'/forgetPassword',   
	            		     type:'post',   
	            		     data:'email='+ $("#forget_email").val(),
	            		     async : true,// 默认为true 异步   
	            	         error:function(){   
	            		        alert('error');   
	            		     },   
	            		     success:function(data){   
	            		        
	            		        var data = $.parseJSON(data); 
	               		    	
	              		    	 if(data.msg=="success"){
	              		    		layer.msg("请进入邮箱点击链接修改密码", {
			        				    icon: 1,
			        				    time: 5000 //2秒关闭（如果不配置，默认是3秒）
			        				}, function(){
			        					 
			        				}); 
	              		    		
	              		    	 }else if(data.msg=="emailNotExit") {
	              		    		
			           		      	layer.msg("邮箱不存在", {
			        				    icon: 2,
			        				    time: 3000 //2秒关闭（如果不配置，默认是3秒）
			        				}, function(){
			        					 
			        				}); 
	           		      	
	              		    		 
	              		    	 }else{
	              		    		 
	              		    	 }
	            		     }
	            		 });
	            	
	            	 
	            	
	            	 
	            	
	               // window.location.href = "index.html";
	            }
	        });

	        $('.forget-form input').keypress(function (e) {
	            if (e.which == 13) {
	                if ($('.forget-form').validate().form()) {
	                    window.location.href = "index.html";
	                }
	                return false;
	            }
	        });

	        jQuery('#forget-password').click(function () {
	            jQuery('.login-form').hide();
	            jQuery('.forget-form').show();
	        });

	        jQuery('#back-btn').click(function () {
	            jQuery('.login-form').show();
	            jQuery('.forget-form').hide();
	        });
	        
	        jQuery('#back-btn2').click(function () {
	            jQuery('.login-form').show();
	            jQuery('.register-form').hide();
	        });

	        $('.register-form').validate({
	            errorElement: 'label', //default input error message container
	            errorClass: 'help-inline', // default input error message class
	            focusInvalid: false, // do not focus the last invalid input
	            ignore: "",
	            rules: {
	                username: {
	                    required: true
	                },
	                password: {
	                    required: true
	                },
	                company: {
	                    required: true
	                },
	                rpassword: {
	                    equalTo: "#password"
	                },
	                position: {
	                    required: true
	                },
	                mobile: {
	                    required: true
	                },
	                realName: {
	                    required: true
	                },
	                fax: {
	                    required: true
	                },
	                email: {
	                    required: true,
	                    email: true
	                }
	            },

	            invalidHandler: function (event, validator) { //display error alert on form submit   

	            },

	            highlight: function (element) { // hightlight error inputs
	                $(element)
	                    .closest('.control-group').addClass('error'); // set error class to the control group
	            },

	            success: function (label) {
	                label.closest('.control-group').removeClass('error');
	                label.remove();
	            },

	            submitHandler: function (form) {
	            	var formvalue= $("#register-form").serialize();
	            	$.ajax({   
           		     url:'/register',   
           		     type:'POST',  
           	         dataType: "json",
           		     data:formvalue,
           		     success:function(data){   
           		    	 
           		        if (data.msg=='success'){

           		        	layer.alert('注册申请成功，等待审核', function(index){
           		        	  //do something
           		        		window.location.href = "/login";
           		        	  layer.close(index);
           		        	}); 
           		        	
           		        	
           		        	
        	                
           		        }else if(data.msg=='emailExist'){
           		        	layer.alert("email已存在，无法重复注册");

           		        	
           		        	
           		        }else if(data.msg=='usernameExist'){
           		        	 
           		        	layer.alert("登录名已经存在，无法重复注册");
           		        	
           		        	
           		        }else{
           		        	layer.msg("系统异常", {
	        				    icon: 5,
	        				    time: 3000 //2秒关闭（如果不配置，默认是3秒）
	        				}, function(){
	        					 
	        				}); 
           		         }
           		        }
           		 });
	            }
	        });

	        jQuery('#register-btn').click(function () {
	            jQuery('.login-form').hide();
	            jQuery('.register-form').show();
	        });

	        jQuery('#register-back-btn').click(function () {
	            jQuery('.login-form').show();
	            jQuery('.register-form').hide();
	        });
	        
	        
        }

    };

}();