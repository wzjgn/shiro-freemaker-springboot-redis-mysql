<div>
    <form id="addUser-form" class="form-horizontal" action="">
        <div class="modal hide fade " id="Modal-AddUser">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                        <h4 class="modal-title">添加用户</h4>
                    </div>
                    <div class="modal-body">
                        <div class="row" style="padding-top: 10px;">
                            <!-- 登录用户名 -->
                            <div class="col-md-8  input-lg">
                                <div class="form-group form-group-lg">
                                    <label class="col-sm-4 control-label">登录用户名</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control  input-lg" id="username" placeholder="登录用户名" name="username" />
                                    </div>

                                </div>
                            </div>
                        </div>




                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-right" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="addUserSubmit();" >保存</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>