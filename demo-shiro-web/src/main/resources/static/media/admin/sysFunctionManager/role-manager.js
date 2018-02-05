
var RoleManaged = function () {
	 
    return {
    	
        //main function to initiate the module
        init: function () {
            table=$('#sample_1').DataTable({
                "processing": true,
                "serverSide": true,
                "searching":false,
                "autoWidth":false,
                "ajax": {
                    "url": "/admin/sysFunctionManager/role_manager/list",
                    "type": "POST",
                    "dataType": "json",
                    "contentType":"application/json",
                    "data": function ( d ) {
                    	
                        return JSON.stringify( d );
                    }
                },
                "aaSorting": [],
                "columns": [
							{
							    data:   "id",
							    bSortable:false,
							    render: function ( data, type, row ) {
							            return '<input type="checkbox" name="selecevalue"   value='+data+' class="editor-active">';
							    }
							    
							},
                            { "data": "roleName","bSortable": false,"searchable":true},
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
            

            $("#addRole").on('click',function(){
            	 $('#Modal-AddRole').modal('show')

            		    });
            
            $("#editRole").on('click',function(){
            	var arr= setSelectAll();
            	if(arr[0]>1){
            		 
		        	 appcommon.alert("每次只能修改一条记录",0,2000);
            		return;
            	}
            	
            	 if(arr[0]==0){ 
            		 appcommon.alert("请选择记录",0,2000);
            		 return ;
            	 }
            	toEditRolePage(arr[1]);

           		    });
            
            $("#addRoleFunction").on('click',function(){
            	var arr= setSelectAll();
            	if(arr[0]>1){
            		 
		        	 appcommon.alert("每次只能操作一条记录",0,2000);
            		return;
            	}
            	
            	 if(arr[0]==0){ 
            		 appcommon.alert("请选择记录",0,2000);
            		 return ;
            	 }
            	 addRoleFunction(arr[1]);

           		    });
            
            $("#delRole").on('click',function(){
            	var arr= setSelectAll();
            	if(arr[0]>1){
            		 
		        	 appcommon.alert("每次只能修改一条记录",0,2000);
            		return;
            	}
            	
            	 if(arr[0]==0){ 
            		 appcommon.alert("请选择记录",0,2000);
            		 return ;
            	 }
            	delRole(arr[1]);

           		    });
            
       },
	 
        reload: function () {
          	table.ajax.reload();
        }
        

    };

}();
 



function addRoleSubmit(){
 
	if($("#roleName").val()==""){
		 appcommon.alert("请添加角色名称",2,2000);
		 return;
	}
	
	
	
	
	 var formvalue= $("#addRole-form").serialize();
    	$.ajax({   
	     url:'/admin/sysFunctionManager/addRole',   
	     type:'POST',  
         dataType: "json",
	     data:formvalue,
	     success:function(data){   
	        if (data.msg=='success'){
	        	 appcommon.alert("添加成功",1);
	        	 window.location.href = "/admin/sysFunctionManager/role_manager";
	        }else if(data.msg=='exist'){
	        	appcommon.alert("已存在，无法重复添加",2,3000);
	        	
	        }else if(data.msg=='admin'){
	        	
	        	appcommon.alert("内置超级管理员权限，不可以重复注册",2,3000);
	        	
	        }else{
	        	appcommon.alert("系统异常",2);
	         }
	       
	        }
	 });
}

/**
 * 获取待修改用户的值
 */
function toEditRolePage(id){
		 $.ajax({   
		     url:'/admin/sysFunctionManager/toEditRolePage',   
		     type:'post',   
		     data:'id='+id,
		     async : false,// 默认为true 异步   
	         error:function(){   
		         
	        	appcommon.alert("系统异常",2);
		     },   
		     success:function(data){
		    	 var data = $.parseJSON(data); 
		    	
		    	 $("#editRole-form input[name=roleName]").val(data.roleName);
		    	 $("#editRole-form input[name=id]").val(data.id);
		    	 
		    	 $('#Modal-EditRole').modal('show')
		    	  
		     }
		 });
	 
}

var roleId="";
function addRoleFunction(id){
	roleId = id;
		var messageId = '';
		var pushPageIndex = layer.open({
		  title:'给角色赋权限',
		  type: 2,
		  shadeClose: true,
		  shade: 0,
		  area: ['360px', '500px'],
		  skin: 'layui-layer-rim', //加上边框
		  content: '/admin/sysFunctionManager/role_function_tree',
		  cancel: function(){ 
			    //右上角关闭回调
			  layer.close(pushPageIndex); 
		  }
		  
		  ,
		  btn: ['保存', '关闭', ]
		  ,yes: function(index, layero){
		    //按钮【按钮一】的回调
		 //   alert("成功");
			    
		    
		    var iframeWin = window[layero.find('iframe')[0]['name']];  //得到iframe页的窗口对象
		    var functionIds =  iframeWin.getCheckFunctionIds();
		    
		    $.ajax({   
			     url:'/admin/sysFunctionManager/addRoleFunction?functionIds='+functionIds+"&roleId="+roleId,   
			     type:'POST',  
			     async: false,
		         dataType: "json",
			     success:function(data){   
			    	 if(data.msg=="success"){
		    	
			    		 appcommon.alert("操作成功",1,2000);
			    		 layer.close(index); 
		               }
			        }
			 });
		   
		    
		    //下面是 不错的例子
		    //子页面 给父页面赋值. 父页面从子页面 获取值  但是一般都要通过jQuery去取.
		    //父页面 给子页面赋值.
		    //var body = layer.getChildFrame('body', index);
		    //var iframeWin = window[layero.find('iframe')[0]['name']]; //得到iframe页的窗口对象，执行iframe页的方法：iframeWin.method();
		    //console.log(body.html()) //得到iframe页的body内容
		    //body.find('input').val('Hi，我是从父页来的')
		    
		    
		  },btn2: function(index, layero){
		    //按钮【按钮二】的回调
			//  alert("失败");
		  }
		});
}



function roleFucntionTreeData(){
	var zNodes;
	$.ajax({   
	     url:'/admin/sysFunctionManager/roleFucntionTreeData?roleId='+roleId,   
	     type:'POST',  
	     async: false,
         dataType: "json",
	     success:function(data){   
	    	 zNodes = data;
	        }
	 });
	
	return zNodes;
	
}


/**
 * 用户信息修改-提交
 */
function editRoleSubmit(){
	 
		
	 var formvalue= $("#editRole-form").serialize();
    	$.ajax({   
    		  url:'/admin/sysFunctionManager/editRole',   
	     type:'POST',  
         dataType: "json",
	     data:formvalue,
	     success:function(data){   
	    	 
	        if (data.msg=='success'){
	        	appcommon.alert("修改成功",1,2000);
	        	  window.location.href = "/admin/sysFunctionManager/role_manager";
	        }else if(data.msg=='exist'){
	        	appcommon.alert("已存在，无法重复添加",2,3000);
	        }else if(data.msg=='admin'){
	        	
	        	appcommon.alert("管理员角色，不可以修改！",2,3000);
	        	 
	        }else{
	           appcommon.alert("系统异常",2,2000);
	         }
	      
	        }
	 });
}


/**
 * 删除角色
 */
function delRole(id){
	
	layer.confirm('确定删除吗?', {icon: 3, title:'提示'}, function(index){
		
		 $.ajax({   
			     url:'/admin/sysFunctionManager/delRole/'+id,   
			     type:'post',   
			     data:'',
			     async : false,// 默认为true 异步   
			     success:function(data){   
			    	var data = $.parseJSON(data); 
			    	 if (data.msg=='success'){
			        	 layer.alert('删除成功', function(index){
			        		 window.location.href =  "/admin/sysFunctionManager/role_manager";
		  		        	  layer.close(index);
		  		        	  
		  		        	}); 
			        	 
			        }else if(data.msg=='exist'){
			        	 layer.alert('删除失败，有用户在使用该角色，请先解除使用再删除！', function(index){
			        		 window.location.href =  "/admin/sysFunctionManager/role_manager";
		  		        	  layer.close(index);
		  		        	  
		  		        	}); 
			        }else if(data.msg=='admin'){
			        	 layer.alert('管理员角色，不可以删除！', function(index){
			        		 
		  		        	  layer.close(index);
		  		        	  
		  		        	}); 
			        }
			        else{
			        	layer.alert('系统异常', function(index){
			        		 window.location.href = "/admin/sysFunctionManager/role_manager";
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


