<div>
    <form id="addRole-form" class="form-horizontal" action="">
        <div class="modal hide fade " id="Modal-AddRole">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                        <h4 class="modal-title">添加角色</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row" style="padding-top: 10px;">
                           
                            <div class="col-md-8  input-lg">
                                <div class="form-group form-group-lg">
                                    <label class="col-sm-4 control-label">用户角色：</label>
                                    <div class="col-sm-8">
                                        <label class="form-control  input-lg"  placeholder="用户角色" />
                                        <input type="text"  id="roleName"   name="roleName"  />
                                       
                                    </div>

                                </div>
                            </div>
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