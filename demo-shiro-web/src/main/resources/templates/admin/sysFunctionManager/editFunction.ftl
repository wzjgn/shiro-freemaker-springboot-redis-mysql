<div>
    <form id="editFunction-form" class="form-horizontal" action="">
        <div class="modal hide fade " id="Modal-EditFunction">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                        <h4 class="modal-title">添加功能</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row" style="padding-top: 10px;">
                           
                            <div class="col-md-8  input-lg">
                                <div class="form-group form-group-lg">
                                    <label class="col-sm-4 control-label">功能名称：</label>
                                    <div class="col-sm-8">
                                        <label class="form-control  input-lg"  placeholder="功能名称" />
                                        <input type="text"  name="functionName"   />
                                        <input type="hidden"  name="id"   />
                                    </div>
                                </div>
                                <div class="form-group form-group-lg">
                                    <label class="col-sm-4 control-label">功能编码：</label>
                                    <div class="col-sm-8">
                                        <label class="form-control  input-lg"  placeholder="功能编码" />
                                        <input type="text"  name="permissionName"  />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-right" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="editFunctionSubmit();" >保存</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>