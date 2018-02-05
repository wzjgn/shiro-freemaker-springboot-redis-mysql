<div>
    <form id="addRole-form" class="form-horizontal" action="">
        <div class="modal hide fade " id="Modal-addRole">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                        <h4 class="modal-title">添加用户角色</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row" style="padding-top: 10px;">
                            <!-- 登录用户名 -->
                            <div class="col-md-8  input-lg">
                                <div class="form-group form-group-lg">
                                    <label class="col-sm-4 control-label">登录用户名</label>
                                    <div class="col-sm-8">
                                        <label class="form-control  input-lg"  placeholder="登录用户名" />
                                        <input type="text"  readOnly id="username"   name="username" value="{userName}" />
                                       
                                    </div>

                                </div>
                            </div>
                        </div>
                        
                         
                        <div class="row" style="padding-top: 10px;">
                            
                                <div class="form-group form-group-lg">
                                    <label class="col-sm-4 control-label">角色</label>
                                </div>
                             <div style="overflow-x: scroll; overflow-y: hidden; margin:10px 40px 10px 140px;" id = "roleList"></div>
                        </div>
                        
                           
						 

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-right" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="addRoleSubmit();" >保存</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>