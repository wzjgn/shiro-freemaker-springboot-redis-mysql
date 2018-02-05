<#assign base=request.contextPath />
<div class="page-sidebar nav-collapse">
    <!-- BEGIN SIDEBAR MENU -->
    <ul class="page-sidebar-menu">
        <li>
            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
            <div class="sidebar-toggler hidden-phone"></div>
            <!-- BEGIN SIDEBAR TOGGLER BUTTON -->
        </li>
       
       
        
        <@shiro.hasPermission name="1-test">
        <li>
            <a href="javascript:;">
                <i class="icon-th"></i>
                <span class="title">测试菜单</span>
                <span class="selected"></span>
                <span class="arrow"></span>
            </a>
            <ul class="sub-menu">
            <@shiro.hasPermission name="2-1">
                <li id="menu_table_managed">
                    <a href="1">测试-1</a>
                </li>
             </@shiro.hasPermission>
              <@shiro.hasPermission name="2-2">
                <li>
                    <a href="2">测试-2</a>
                </li>
              </@shiro.hasPermission>
             
            </ul>
        </li>
        </@shiro.hasPermission>
        
        <@shiro.hasPermission name="4">
        <li>
            <a href="javascript:;">
                <i class="icon-th"></i>
                <span class="title">系统管理功能</span>
                <span class="selected"></span>
                <span class="arrow"></span>
            </a>
            <ul class="sub-menu">
            <@shiro.hasPermission name="4-1">
                <li id="menu_table_managed">
                    <a class="ss" href="${base}/admin/table_managed">用户管理</a>
                </li>
             </@shiro.hasPermission>
              <@shiro.hasPermission name="4-2">
                <li>
                    <a class="ss" href="${base}/admin/sysFunctionManager/function_manager">功能管理</a>
                </li>
              </@shiro.hasPermission>
             <@shiro.hasPermission name="4-3">
                <li>
                    <a class="ss" href="${base}/admin/sysFunctionManager/role_manager">角色管理</a>
                </li>
             </@shiro.hasPermission>
            </ul>
        </li>
        </@shiro.hasPermission>
    </ul>
    <!-- END SIDEBAR MENU -->
</div> 
</script>
