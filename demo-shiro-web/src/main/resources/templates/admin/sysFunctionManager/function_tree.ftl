<!DOCTYPE html>
<HTML>
<HEAD>
	<TITLE> ZTREE DEMO - beforeCheck / onCheck</TITLE>
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7;IE=edge,chrome=1">	
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="${request.contextPath}/media/zTree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="${request.contextPath}/media/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
    <script src="${request.contextPath}/media/zTree/js/jquery-1.4.4.min.js" type="text/javascript"></script>
	<script src="${request.contextPath}/media/zTree/js/jquery.ztree.core.js" type="text/javascript"></script>
     
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
				onCheck: onCheck,
				onClick: zTreeOnClick
			}
		};


		function zTreeOnClick(event, treeId, treeNode){
		   
		   window.parent.FunctionManaged.reloadList(treeNode.name,treeNode.id);
		}
		
		
		function onCheck(e, treeId, treeNode) {

			 var treeObj = $.fn.zTree.getZTreeObj("tree");
			 var nodes = treeObj.getCheckedNodes(true);
			 for(var i=0;i<nodes.length;i++){
 
				alert(nodes[i].id+"--"+nodes[i].name); //获取每个节点的id
			}

		}

		$(document).ready(function(){
           var zNodes =  window.parent.initTree();
			$.fn.zTree.init($("#tree"), setting, zNodes);
 
			 
		});
 
 		
 		//操作 添加，修改，删除按钮后，重新加载tree
         function reloadTree(){
		    var zNodes =  window.parent.initTree();
			$.fn.zTree.init($("#tree"), setting, zNodes);
			
		}
 
	</SCRIPT>
</HEAD>

<BODY>
<div>
	<div class="zTreeDemoBackground" style="background:#090; color:#FFF; height:20px; width:200px;">
		<ul id="tree" class="ztree"></ul>
	</div>
 
</div>
</BODY>
</HTML>