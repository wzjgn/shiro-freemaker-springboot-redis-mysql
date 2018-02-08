
var TableManaged = function () {
	 
    return {
    	
        //main function to initiate the module
        init: function () {
            table=$('#sample_1').DataTable({
                "processing": true,
                "serverSide": true,
                "searching":false,
                "autoWidth":false,
                "ajax": {
                    "url": "/admin/findUserList",
                    "type": "POST",
                    "dataType": "json",
                    "contentType":"application/json",
                    "data": function ( d ) {
                    	var searchtable=$("#searchtable").serialize();

                    	searchtable = decodeURIComponent(searchtable,true);
                    	d.extra_search =searchtable;
                    	
                        return JSON.stringify( d );
                    }
                },
                "aaSorting": [],
                "columns": [
							{
							    data:   "username",
							    bSortable:false,
							    render: function ( data, type, row ) {
							            return '<input type="checkbox" name="selecevalue"   value='+data+' class="editor-active">';
							    }
							    
							},
                            { "data": "username","bSortable": false,"searchable":true},
                            { "data": "status","bSortable": true,"searchable":true,
                                "mRender": function(data, type, full) {
                                    var value = ""
                                    if ("0" == data){
                                        value = "未审核"
                                    } else if("1" == data){
                                        value = "已审核"
                                    }else if("2" == data){
                                        value = "锁定"
                                    }
                                    return value
                                }  },
                            { "data": "createTime","bSortable": true,"searchable":true} 
                ],

                // set the initial value
                "aLengthMenu": [
                    [5, 15, 20],
                    [5, 15, 20] // change per page values here
                ],
                "iDisplayLength":5,
                "oLanguage": {
                    "sLengthMenu": "每页显示 _MENU_ 条记录",
                    "sZeroRecords": "抱歉， 没有找到",
                    "sInfo": "从 _START_ 到 _END_ /共 _TOTAL_ 条数据",
                    "sInfoEmpty": "没有数据",
                    "sInfoFiltered": "(从 _MAX_ 条数据中检索)",
                    "sSearch": "搜索",
                    "sProcessing": "处理中...",
                    "oPaginate": {
                        "sFirst": "首页",
                        "sPrevious": "前一页",
                        "sNext": "后一页",
                        "sLast": "尾页"
                    },
                    "sZeroRecords": "没有检索到数据",
                },

            });
            
            
            //查询    
            $("#searchButton").click(function(){
            	TableManaged.reload();

                 });

            $("#addUser").on('click',function(){
            	 $('#Modal-AddUser').modal('show')
            	 
                 TableManaged.addUserValidator();
               //  datatable.reload(null, false);

            		    });

            
            $("#delUser").on('click',function(){
            	 var arr = setSelectAll();
                 if (arr[0] == 0) {
                     appcommon.alert("请选择记录", 0, 2000);
                     return;
                 }

                  del();
           	});
            
            $("#addRole").on('click',function(){
            	var arr= setSelectAll();
            	if(arr[0]>1){
            		 
		        	 appcommon.alert("每次只能修改一条记录",0,2000);
            		return;
            	}
            	
            	 if(arr[0]==0){ 
            		 appcommon.alert("请选择记录",0,2000);
            		 return ;
            	 }
            	 toAddRolePage(arr[1]);

           		    });
            
            $("#audit_yes").on('click',function(){
            	var arr= setSelectAll();
            	var formvalue= "username="+arr[1]+"&auditStatus=1";
            	 if(arr[0]==0){
            		 appcommon.alert("请选择记录",0,2000);
            		 
            		 return ;
            	 }
                audit(formvalue);

            });
            $("#audit_no").on('click',function(){
            	var arr= setSelectAll();
            	var formvalue= "username="+arr[1]+"&auditStatus=0";
            	 if(arr[0]==0){
            		 appcommon.alert("请选择记录",0,2000);
            		 return ;
            	 }
                audit(formvalue);
            	  
            	  
            		    });
            
       },
       
       //添加校验
       addUserValidator:function(){
    	   
		  $('#addUser-form').bootstrapValidator({
	    	feedbackIcons: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields: {
				username: {
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

				  realName: {
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
				 		  },
				   }
	    })
	    
       },
       //修改校验
       editUserValidator:function(){
		  $('#editUser-form').bootstrapValidator({
	    	feedbackIcons: {
				valid: 'glyphicon glyphicon-ok',
				invalid: 'glyphicon glyphicon-remove',
				validating: 'glyphicon glyphicon-refresh'
			},
			fields: {
				username: {
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

				  realName: {
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
				 		  },
				   }
	    })
       },
	 
        reload: function () {
          	table.ajax.reload();
        }
        

    };

}();

function  audit(formvalue){
	 
	layer.confirm('确定吗?', {icon: 3, title:'提示'}, function(index){
		  //do something
		$.ajax({   
		     url:'/admin/audit',   
		     type:'POST',  
	         dataType: "json",
		     data:formvalue,
		     success:function(data){   
		    	 
		        if (data.msg=='success'){
		           appcommon.alert("操作成功",1,2000);
		        	TableManaged.reload(); 
			        
		        }else if(data.msg=='roleNotExist'){
		        	 appcommon.alert("请先给用户添加角色",2,2000);
		        }else{
		        	 appcommon.alert("系统异常",2,2000);
		         }
		       
		        }
		 });
		  layer.close(index);
		});
	
	
}


function addUserSubmit(){

	var t = $('#addUser-form').data('bootstrapValidator').validate();
		var flag = t.isValid();
		if(flag==false){
			return ;
		}
	
	
	 var formvalue= $("#addUser-form").serialize();
    	$.ajax({   
	     url:'/admin/addUser',   
	     type:'POST',  
         dataType: "json",
	     data:formvalue,
	     success:function(data){   
	        if (data.msg=='success'){
	        	 appcommon.alert("添加成功",1);
	        }else if(data.msg=='emailExist'){
	        	appcommon.alert("email已存在，无法重复注册",2,2000);
	        	
	        }else if(data.msg=='usernameExist'){
	        	appcommon.alert("登录名已经存在，无法重复注册",2,2000);
	        }else{
	        	appcommon.alert("系统异常",2);
	         }
	        window.location.href = "/admin/table_managed";
	        
	        }
	 });
}




/**
 * 进入添加用户角色页面
 */
function toAddRolePage(username){
		 $.ajax({   
		     url:'/admin/toAddRolePage',   
		     type:'post',   
		     data:'username='+username,
		     async : true,// 默认为true 异步   
	         error:function(){   
		         
	        	appcommon.alert("系统异常",2);
		     },   
		     success:function(data){
		    	 var data = $.parseJSON(data); 
		    	 
		    	  $("#roleList").html("");
		    	
		    	 for(i=0;i<data.length;i++)
		    	 {
		    	    var obj = data[i];
		    	    username = obj.username;
		    	    if(data[i].check){
		    	    	 var row = 
				    	    	
				    	    	"<div style='float:left; margin:10px 10px 5px 10px;' ><input name='role' type='checkbox' checked  value='"+data[i].roleName+"'/>"+data[i].roleName+" </div>";
		                    
		                        $("#roleList").append(row);
		    	    }else{
		    	    	 var row = 
				    	    	
				    	    	"<div style='float:left; margin:10px 10px 5px 10px;' ><input name='role' type='checkbox'  value='"+data[i].roleName+"'/>"+data[i].roleName+" </div>";
		                    
		                        $("#roleList").append(row);
		    	    }
		    	   
                
		    	  
		    	 } 
		    	 $("#addRole-form input[name=username]").val(username);
		    	 
		    	 $('#Modal-addRole').modal('show')
		    	  
		     }
		 });
}


/**
 * 用户角色修改-提交
 */
function addRoleSubmit(){
	
	 var formvalue= $("#addRole-form").serialize();
	if(formvalue.indexOf("role")==-1){
		appcommon.alert("请选择角色",2,2000);
	}else{
		$.ajax({   
		     url:'/admin/editUserRole',   
		     type:'POST',  
	         dataType: "json",
		     data:formvalue,
		     success:function(data){   
		    	 
		        if (data.msg=='success'){
		        	appcommon.alert("修改成功",1,2000);
		        
		           window.location.href = "/admin/table_managed";
		        }
		       }
		 });
	}
 	
}

/**
 * 用户信息修改-提交
 */
function editUserSubmit(){
	
	var t = $('#editUser-form').data('bootstrapValidator').validate();
		var flag = t.isValid();
		if(flag==false){
			return ;
		}
	
		
	 var formvalue= $("#editUser-form").serialize();
    	$.ajax({   
	     url:'/admin/editUser',   
	     type:'POST',  
         dataType: "json",
	     data:formvalue,
	     success:function(data){   
	    	 
	        if (data.msg=='success'){
	        	appcommon.alert("修改成功",1,2000);
	        }else if(data.msg=='emailExist'){
	        	appcommon.alert("email已存在，无法重复注册，",2,2000);
	        }else{
	           appcommon.alert("系统异常",2,2000);
	         }
	        window.location.href = "/admin/table_managed";
	        }
	 });
}


/**
 * 删除
 */
function del() {

    layer.confirm('确定删除吗?', {icon: 3, title: '提示'}, function (index) {
        var arr = setSelectAll()

        $.ajax({
        	 url:'/admin/user/del/'+arr[1],
            type: 'post',
            data: '',
            async: false,// 默认为true 异步
            success: function (data) {
                var data = $.parseJSON(data);
                if (data.msg == 'success') {
                    layer.alert('删除成功', function (index) {
                    	window.location.href = "/admin/table_managed";
                        layer.close(index);

                    });

                } else if (data.msg == 'admin') {
                    layer.alert('admin用户不可以删除', function (index) {
                    	 
                        layer.close(index);

                    });

                } else {
                    layer.alert('该用户有上报信息，不可以删除', function (index) {
                        
                        layer.close(index);
                    });
                }

            }
        });
        layer.close(index);
    });

}


/**
 * 全选，全部取消
 */
function selectAll(){  
    if ($("#selectall").attr("checked")) {  
        //$(":checkbox").attr("checked", true);  
        $("[name='selecevalue']:checkbox").attr("checked", true);  
    } else {  
        //$(":checkbox").attr("checked", false);  
        $("[name='selecevalue']:checkbox").attr("checked", false);  
    }  
}

/**
 * 获取选择复选框数据和具体哪些复选框。
 */ 
function setSelectAll(){
	
    var chsub = $("input[type='checkbox'][name='selecevalue']").length; //获取subcheck的个数  
    
    var checkedsub = $("input[type='checkbox'][name='selecevalue']:checked").length; //获取选中的subcheck的个数 
    
    if (checkedsub == chsub) {  
        $("input[name='selectall']:checkbox").attr("checked", true);  
    } 
    
    var selectvalue="";
    $("input[name='selecevalue']:checked").each(function(){ 
 	   selectvalue+=$(this).val()+","; 
    }) 
     var arr=[];
      arr[0]=checkedsub;
      arr[1]=selectvalue.substring(0,selectvalue.length-1)
   
    return arr; 
}  


