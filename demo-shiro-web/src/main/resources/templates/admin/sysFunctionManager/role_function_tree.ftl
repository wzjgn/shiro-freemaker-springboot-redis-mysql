<!DOCTYPE html>
<HTML>
<HEAD>
	<TITLE> ZTREE DEMO - beforeCheck / onCheck</TITLE>
	
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7;IE=edge,chrome=1">
    <link rel="stylesheet" href="${request.contextPath}/media/zTree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="${request.contextPath}/media/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script src="${request.contextPath}/media/zTree/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="${request.contextPath}/media/zTree/js/jquery.ztree.core.js" type="text/javascript"></script>
    <script src="${request.contextPath}/media/zTree/js/jquery.ztree.excheck.js" type="text/javascript"></script>
     
	<SCRIPT type="text/javascript">
		var setting = {
			view: {
				selectedMulti: false
			},
			check: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				onCheck: onCheck
			}
		};

		function onCheck(e, treeId, treeNode) {

			 var treeObj = $.fn.zTree.getZTreeObj("tree");
			 var nodes = treeObj.getCheckedNodes(true);
			// for(var i=0;i<nodes.length;i++){
 
				//alert(nodes[i].id+"--"+nodes[i].name); //获取每个节点的id
			//}
		}
		 
		//获取functionId
		function getCheckFunctionIds(){
		 
		    var treeObj = $.fn.zTree.getZTreeObj("tree");
			 var nodes = treeObj.getCheckedNodes(true);
			 var functionIds="";
			 for(var i=0;i<nodes.length;i++){
				functionIds+=nodes[i].id+","; 
			}
			
			if(functionIds==""){
			 //  alert("请选择功能");
			 //  return "";
			 functionIds="";
			}else{
			  functionIds=functionIds.substring(0,functionIds.length-1);
			}
			 
			return functionIds;
		}

		$(document).ready(function(){
           var zNodes =  window.parent.roleFucntionTreeData();
			$.fn.zTree.init($("#tree"), setting, zNodes);
 
			 
		});
 
	</SCRIPT>
</HEAD>

<BODY> 

	<div>
		<ul id="tree" class="ztree"></ul>
	</div>
  
</BODY>
</HTML>