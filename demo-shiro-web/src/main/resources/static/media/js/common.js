var appcommon = function () {
    return {
        password:function(){
        	
        	 $("#password_form_oldpassword").val("");
        	 $("#password_form_password").val("");
        	 $("#password_form_rpassword").val("");
        	 $('#Modal-Password').modal('show')

         	appcommon.validator();
        },
        passwordSubmit:function(){
        	
        	var t = $('#password-form').data('bootstrapValidator').validate();
      		var flag = t.isValid();
      		if(flag==false){
      			return ;
      		}
        	username  =  $("#password_form_username").val();
        	oldpassword =$("#password_form_oldpassword").val();
        	password =$("#password_form_password").val();
        	 $.ajax({   
       		     url:'/admin/user/password',   
       		     type:'post',   
       		     data:'username='+ username+'&password='+ password+'&oldpassword='+ oldpassword,
       		     async : false,// 默认为true 异步   
       	         error:function(){   
       		        alert('error');   
       		     },   
       		     success:function(data){   
       		    	var data = $.parseJSON(data); 
       		    	
       		    	 if(data.msg=="success"){
       		    		appcommon.alert("操作成功",1,2000);
       		    		 $('#Modal-Password').modal('hide');
       		    	 }else if(data.msg=="passwordError"){
       		    		appcommon.alert("原密码输入错误",2,2000);
       		    	 }else{
       		    		appcommon.alert("操作失败",2,2000);
       		    	 }
       		    	 
       		     }
       		 });
        	 
    	  },
    	  
    	  validator:function(){
    		$('#password-form').bootstrapValidator({
        		feedbackIcons: {
            		valid: 'glyphicon glyphicon-ok',
            		invalid: 'glyphicon glyphicon-remove',
            		validating: 'glyphicon glyphicon-refresh'
        		},
        		fields: {
        			password_form_oldpassword: {
           			 	validators: {
           			 		notEmpty: {
                     			message: '必填项,不能为空!!!'
                    		}
                    		/* regexp: {
                    			regexp: /^COM.*$/,
                            	message: '请选择！'
                    		} */
           			 	}
           			 },
           			password_form_password: {
           			 	validators: {
           			 		notEmpty: {
                     			message: '必填项,不能为空!!!'
                    		}
                    		/* regexp: {
                    			regexp: /^COM.*$/,
                            	message: '请选择！'
                    		} */
           			 	}
           			 },
            			password_form_rpassword: {
               			 	validators: {
               			 		notEmpty: {
                         			message: '必填项,不能为空!!!'
                        		},
                        		identical: {//相同
                                    field: 'password_form_password',
                                    message: '两次密码不一致'
                                },
                        		
               			 	}
               			 }
           		}
          });
    		
    	},
    	editInformation:function(){
          	var username = $("#edit-information-form input[name=username]").val();
    		 $.ajax({   
    		     url:'/admin/toEditUserPage',   
    		     type:'post',   
    		     data:'username='+username,
    		     async : false,// 默认为true 异步   
    	         error:function(){   
    		         
    	        	appcommon.alert("系统异常",2);
    		     },   
    		     success:function(data){
    		    	 var data = $.parseJSON(data); 
    		    	 
    		    	 $("#edit-information-form input[name=company]").val(data.company);
    		    	 $("#edit-information-form input[name=email]").val(data.email);
    		    	 $("#edit-information-form input[name=position]").val(data.position);
    		    	 $("#edit-information-form input[name=fax]").val(data.fax);
    		    	 $("#edit-information-form input[name=mobile]").val(data.mobile);
    		    	 $("#edit-information-form input[name=realName]").val(data.realName);
    		    	 $("#edit-information-form input[name=username1]").val(data.username);
    		    	 $("#edit-information-form input[name=username]").val(data.username);
    		    	 
    		    	 $('#Modal-information').modal('show')
    		    	 
    		    	appcommon.editInformationValidator();
    		     }
    		 });

    		
         },
         editInformationSubmit:function(){
         	
         	var t = $('#edit-information-form').data('bootstrapValidator').validate();
       		var flag = t.isValid();
       		if(flag==false){
       			return ;
       		}
          
         	 

        	 var formvalue= $("#edit-information-form").serialize();
        	 
            	$.ajax({   
        	     url:'/admin/user/editInformation',   
        	     type:'POST',  
                 dataType: "json",
        	     data:formvalue,
        	     success:function(data){   
        	        if (data.msg=='success'){
        	        	 appcommon.alert("操作成功",1);
        	        	 $('#Modal-information').modal('hide');
        	        }else if(data.msg=='emailExist'){
        	        	appcommon.alert("邮箱已存在，请更换邮箱",2,2000);
        	        	
        	        }else if(data.msg=='passwordError'){
        	        	appcommon.alert("密码错误",2,2000);
        	        }else{
        	        	appcommon.alert("系统异常",2);
        	         }
        	        }
        	 });
            	
            	
         	 
     	  },
     	  
     	  editInformationValidator:function(){
     		$('#edit-information-form').bootstrapValidator({
         		feedbackIcons: {
             		valid: 'glyphicon glyphicon-ok',
             		invalid: 'glyphicon glyphicon-remove',
             		validating: 'glyphicon glyphicon-refresh'
         		},
         		fields: {
         			password: {
            			 	validators: {
            			 		notEmpty: {
                      			message: '必填项,不能为空!!!'
                     		}
                     		 
            			 	}
            			 },
            			company: {
            			 	validators: {
            			 		notEmpty: {
                      			message: '必填项,不能为空!!!'
                     		}
                     	 
            			 	}
            			 },
             			positon: {
                			 	validators: {
                			 		notEmpty: {
                          			message: '必填项,不能为空!!!'
                         		}
                         		 
                         		
                			 	}
                			 },
                       mobile: {
         						validators: {
         							notEmpty: {
         						          message: '手机号,不能为空!!!'
         							     },
         			                     regexp: {
         			                         regexp: /^1[3|5|8]{1}[0-9]{9}$/,
         			                         message: '请输入正确的手机号码'
         			                     }
         					 	        }
         					 		  },
         				email: {
         						validators: {
         							notEmpty: {
         						           message: '必填项,不能为空!!!'
         						      },
         						    emailAddress: {
         		                         message: '请输入正确的邮件地址如：123@qq.com'
         		                     }
         				 	        }
         				 		  }
            		}
           });
     		
     	},
        alert:function(msg,i,s){ 
        	layer.msg(msg, {
				    icon: i,
				    time: s //2秒关闭（如果不配置，默认是3秒）
				}, function(){
					 
				}); 
    	  }
    	  
    	  
    	  
 
    };
}()