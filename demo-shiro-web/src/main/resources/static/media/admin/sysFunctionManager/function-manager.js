
var FunctionManaged = function () {
	var pid="";
	var pidFunctionName="";
	var flag=false;
    return {
    	
    	
        //main function to initiate the module
        init: function () {
        	FunctionManaged.flag=true;
        	 table=$('#sample_1').DataTable({
        		                 "processing": false,
        		                 "serverSide": false,
        		                 "searching":false,
        		                 "autoWidth":false,
        		                 "lengthChange": false,
        		                 "bPaginate":false,//是否使用分页 
        		                 "bInfo": false,//页脚信息
        		                 "ajax": {
        		                     "url": "/admin/sysFunctionManager/function_manager/list",
        		                     "type": "POST",
        		                     "dataType": "json",
        		                     "contentType":"application/json",
        		                     "data": function ( d ) {
        		                     	  
        		                     	 d.extra_search =FunctionManaged.pid;
        		                         return JSON.stringify( d );
        		                     }
        		                 },
        		                 "aaSorting": [],
        		                 "columns": [
        		                             { "data": "functionName","bSortable": false,"searchable":true},
        		                             { "data": "permissionName","bSortable": false,"searchable":true},
        		                             { "data": "createTime","bSortable": false,"searchable":true} 
        		                 ],
        		 
        		             });
             

       },
       
       initButton: function () {
    	   FunctionManaged.pid="";
    	   
    	   $("#addFunction").on('click',function(){
    		   
    		    
           	 if(FunctionManaged.pid==""){
           		 appcommon.alert("请选择目录",0,2000);
           		 
           	 }else{
           		 
           		 //modal模式框bug的处理：清除上一次输入的值。
           		$('#functionName').val("");
           		$('#permissionName').val("");
           		
           		//本次选中的上一级目录的值
           	 	$('#pidFunctionName').val(FunctionManaged.pidFunctionName);
               	$('#pid').val(FunctionManaged.pid);
               	
               	 $('#Modal-AddFunction').modal('show')
           	 }
          });
    	   
    	   $("#delFunction").on('click',function(){
    		   
             	 if(!FunctionManaged.flag||FunctionManaged.pid==""){
             		 appcommon.alert("请选择目录",0,2000);
             		 
             	 }else{
             	 	 
             		delFunction(FunctionManaged.pid);
             	 }
            });
    	   
           $("#editFunction").on('click',function(){
           
        	   if(FunctionManaged.pid==""){
             		 appcommon.alert("请选择目录",0,2000);
             		 
             	 }else{
             	    toEditFuntion();
             	 }
          	});
    	   
       },
       reloadList: function (name,id) {
    	   
    	   FunctionManaged.pidFunctionName=name;
    	   FunctionManaged.pid=id;
    	   
    	   if(!FunctionManaged.flag){
    		   FunctionManaged.init();
    	   }else{
    		   table.ajax.reload();
    	   }
    	   
       }
      

    };

}();
 

function initTree(){
	var zNodes;
	$.ajax({   
	     url:'/admin/sysFunctionManager/initTree/list',   
	     type:'POST',  
	     async: false,
         dataType: "json",
	     success:function(data){   
	    	 zNodes = data;
	        }
	 });
	
	return zNodes;
	
}

function addFunctionSubmit(){
	
var pid=$("#pid").val();
var functionName=$("#functionName").val();
var permissionName=	$("#permissionName").val();

	 $.ajax({   
	     url:'/admin/sysFunctionManager/addFunction',   
	     type:'post',   
	     data:'pid='+pid+"&functionName="+functionName+"&permissionName="+permissionName,
	     async : true,// 默认为true 异步   
         error:function(){   
        	appcommon.alert("系统异常",2);
	     },   
	     success:function(data){
	    	 var data = $.parseJSON(data); 
	    	if(data.msg=="exist"){
	    		 appcommon.alert("功能编码重复，请重新填写",2,2000);
	    	} else if(data.msg=="success"){
	    		 appcommon.alert("添加成功",1,2000);
	    		 reloadTree();
	    	}else{
	    		 appcommon.alert("系统异常",0,2000);
	    	}
	    	 $('#Modal-AddFunction').modal('close')
	     }
	 });
}


function delFunction(id){
	layer.confirm('确定吗?', {icon: 3, title:'提示'}, function(index){
	 $.ajax({   
	     url:'/admin/sysFunctionManager/delFunction',   
	     type:'post',   
	     data:'id='+id,
	     async : true,// 默认为true 异步   
         error:function(){   
        	appcommon.alert("系统异常",2);
	     },   
	     success:function(data){
	    	 var data = $.parseJSON(data); 
	    	if(data.msg=="existChilden"){
	    		 appcommon.alert("请先删除子目录",2,2000);
	    		 
	    	}else if(data.msg=="existRoleFunction"){
	    		 appcommon.alert("该功能已被角色绑定，请先解除绑定",2,2000);
	    		 
	    	} else  if(data.msg=="success"){
	    		
	    		 appcommon.alert("删除成功",1,2000);
	    		 reloadTree();
	    	}else{
	    		 appcommon.alert("系统异常",0,2000);
	    	}
	    	  
	     }
	 });
	 
	 layer.close(index);
	});
}


function reloadTree(){

	   FunctionManaged.pidFunctionName="";
	   FunctionManaged.pid="";
	
	 window.iframepage.reloadTree();
}



/**
 * 获取待修改功能的值
 */
function toEditFuntion(){
		 $.ajax({   
		     url:'/admin/sysFunctionManager/toEditFunction',   
		     type:'post',   
		     data:'id='+FunctionManaged.pid,
		     async : true,// 默认为true 异步   
	         error:function(){   
		         
	        	appcommon.alert("系统异常",2);
		     },   
		     success:function(data){
		    	 
		    	 var data = $.parseJSON(data); 
		    	 
		    	 $("#editFunction-form input[name=functionName]").val(data.functionName)
		    	 $("#editFunction-form input[name=id]").val(data.id)
		    	 $("#editFunction-form input[name=permissionName]").val(data.permissionName)
		    	 
		    	 
		    	 $('#Modal-EditFunction').modal('show')
		    	  
		     }
		 });
}



/**
 * 功能修改-提交
 */
function editFunctionSubmit(){
	
	 var formvalue= $("#editFunction-form").serialize();
    	$.ajax({   
	     url:'/admin/sysFunctionManager/editFunction',   
	     type:'POST',  
         dataType: "json",
	     data:formvalue,
	     success:function(data){   
	    	 
	        if (data.msg=='success'){
	        	appcommon.alert("修改成功",1,2000);
	        	reloadTree();
	        }else if(data.msg=='exist'){
	        	appcommon.alert("功能编码重复，请重新填写，",2,2000);
	        }else{
	           appcommon.alert("系统异常",2,2000);
	         }
	        
	        }
	 });
}


 


