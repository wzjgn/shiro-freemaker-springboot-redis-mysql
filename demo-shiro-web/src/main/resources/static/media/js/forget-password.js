var Login = function () {
    
    return {
        //main function to initiate the module
        init: function () {
        	




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
	            		    	 
	            		        if (data=='success'){
	            		        	alert("请进入邮箱点击链接修改密码");
	            		        }else{
	            		        	alert("系统异常");
	            		        }
	            		     }
	            		 });
	            	
	            	 
	            	
	            	 
	            	
	               // window.location.href = "index.html";
	            }
	        });





	        $('.resetPassword-form').validate({
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
	                rpassword: {
	                    equalTo: "#password"
	                },
	                email: {
	                    required: true,
	                    email: true
	                },
	                tnc: {
	                    required: true
	                }
	            },

	            messages: { // custom messages for radio buttons and checkboxes
	                tnc: {
	                    required: "Please accept TNC first."
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
	                if (element.attr("name") == "tnc") { // insert checkbox errors after the container                  
	                    error.addClass('help-small no-left-padding').insertAfter($('#register_tnc_error'));
	                } else {
	                    error.addClass('help-small no-left-padding').insertAfter(element.closest('.input-icon'));
	                }
	            },

	            submitHandler: function (form) {
	            	
	            	var formvalue= $("#resetPassword-form").serialize();
	            	$.ajax({   
           		     url:'/resetPassword',   
           		     type:'POST',  
           	         dataType: "json",
           		     data:formvalue,
           		     success:function(data){   
           		        if (data.msg=='success'){
           		        	alert("密码修改成功，请重新登录");
        	                window.location.href = "/login";
           		        }else{
           		        	alert("系统异常");
           		        }
           		     }
           		 });
	            }
	        });
        }

    };

}();