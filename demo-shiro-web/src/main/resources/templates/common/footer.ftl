
	<!-- BEGIN JAVASCRIPTS(Load javascripts at bottom, this will reduce page load time) -->

	<!-- BEGIN CORE PLUGINS -->

	<script src="${request.contextPath}/media/js/jquery-1.10.1.min.js" type="text/javascript"></script>

	<script src="${request.contextPath}/media/js/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>

	<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->

	<script src="${request.contextPath}/media/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>      

	<script src="${request.contextPath}/media/js/bootstrap.min.js" type="text/javascript"></script>
	<script src="${request.contextPath}/media/js/common.js" type="text/javascript"></script>

	<!--[if lt IE 9]>

	<script src="${request.contextPath}/media/js/excanvas.min.js"></script>

	<script src="${request.contextPath}/media/js/respond.min.js"></script>  

	<![endif]-->   

	<script src="${request.contextPath}/media/js/jquery.slimscroll.min.js" type="text/javascript"></script>

	<script src="${request.contextPath}/media/js/jquery.blockui.min.js" type="text/javascript"></script>  

	<script src="${request.contextPath}/media/js/jquery.cookie.min.js" type="text/javascript"></script>

	<script src="${request.contextPath}/media/js/jquery.uniform.min.js" type="text/javascript" ></script>
	 <script src="${request.contextPath}/media/js/layer/layer.js" type="text/javascript"></script>

	<!-- END CORE PLUGINS -->

	<!-- BEGIN PAGE LEVEL PLUGINS -->

	<script type="text/javascript" src="${request.contextPath}/media/js/select2.min.js"></script>

	<!--<script type="text/javascript" src="${request.contextPath}/media/js/jquery.dataTables.js"></script>-->
	<script src="${request.contextPath}/media/js/datatables/jquery.dataTables.min.js" type="text/javascript"></script>

	<script type="text/javascript" src="${request.contextPath}/media/js/DT_bootstrap.js"></script>
	
	<script src="${request.contextPath}/media/js/validation/bootstrapValidator.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="${request.contextPath}/media/js/bootstrap-datetimepicker.js"></script>

	<!-- END PAGE LEVEL PLUGINS -->


	<!-- BEGIN PAGE LEVEL SCRIPTS -->

 <!-- BEGIN EDIGUSER PAGE -->
	<#include "${request.contextPath}/admin/user/password.ftl"/>
	 
    <!-- END EDIGUSER PAGE -->

	<script src="${request.contextPath}/media/js/app.js"></script>

<div class="footer">

		<div class="footer-inner">

			shiro+freemaker+springboot+redis +mysql 权限控制后台演示. 

		</div>

		<div class="footer-tools">

			<span class="go-top">

			<i class="icon-angle-up"></i>

			</span>

		</div>

	</div>