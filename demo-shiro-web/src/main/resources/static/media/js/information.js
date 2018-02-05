var Information = function () {
    
    return {
        //main function to initiate the module
        init: function () {
        	 
        	 $('#information-submit-btn').click(function () {
        		 
        		 
        	 if(validate()){
        		 
        	  var formvalue= $("#information-form").serialize();
             	
             	$.ajax({   
        		     url:'/admin/user/information',   
        		     type:'POST',  
        	         dataType: "json",
        		     data:formvalue,
        		     success:function(data){   
        		        if (data.msg=='success'){
        		        	
        		     	   layer.alert('操作成功', function(index){
        		     		  //do something
        		     		  window.location.href =data.url;
        		     		  layer.close(index);
        		     		});  
        		     	   
        		     	   
     	               
        		        }else if(data.msg=='emailExist'){

        		        	layer.alert("email已存在，无法重复注册");
        		        	
        		        	
        		        }else{
            		      
            		       layer.alert("系统异常"); 
        		     	
        		         }
        		        }
        		 });
        		 
        	  }
        	 });
        	 
        	}
        	
        };
    }();
    
  function validate(){
	
		if($("#password").val()==""){
			layer.msg('请填写密码', {icon: 0});
			 $("#password").focus();
			 return false;
		}
		
		
		if($("#rpassword").val()!=$("#password").val()){
			layer.msg('密码输入不一致', {icon: 0});
			 $("#rpassword").focus();
			 return false;
		}
		 
		if($("#email").val()==""){
			
			 layer.msg('请填写邮箱', {icon: 0});
			 
			 $("#email").focus();
			 return false;
		}
			var search_str = /^[\w\-\.]+@[\w\-\.]+(\.\w+)+$/;
			 var email_val = $("#email").val();
			 if(!search_str.test(email_val)){       
				 layer.msg('邮箱格式不正确', {icon: 0});
			      $('#email').focus();
			     return false;
			 }
			 
			 
			 
		if($("#company").val()==""){
			 layer.msg('请填写公司名称', {icon: 0});
			 $("#company").focus();
			 return false;
		}
		if($("#realName").val()==""){
			 layer.msg('请填写姓名', {icon: 0});
			 $("#realName").focus();
			 return false;
		}
		
		
		if (!$("#mobile").val().match(/^(((13[0-9]{1})|159|153)+\d{8})$/)) {
			layer.msg('手机号码格式不正确', {icon: 0});
			$("#mobile").focus();
			 return false;
			} 

		return true;

}
		    
 
	        	
