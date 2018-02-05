<div>
    <form id="editUser-form" class="form-horizontal" action="">
        <div class="modal hide fade " id="Modal-EditUser">
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
                                        <input type="text" class="form-control  input-lg" id="username1" placeholder="登录用户名"  disabled name="username1" />
                                        <input type="text"  style="display:none" id="username"   name="username" />
                                       
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="row" style="padding-top: 10px;">
                            <!-- 单位名称 -->
                            <div class="col-md-8  input-lg">
                                <div class="form-group form-group-lg">
                                    <label class="col-sm-4 control-label">单位名称</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control  input-lg" id="company" placeholder="单位名称" name="company" />
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="row" style="padding-top: 10px;">
                            <!-- 职务 -->
                            <div class="col-md-8  input-lg">
                                <div class="form-group form-group-lg">
                                    <label class="col-sm-4 control-label">职务</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control  input-lg" id="position" placeholder="职务" name="position" />
                                    </div>

                                </div>
                            </div>
                        </div>

                        <div class="row" style="padding-top: 10px;">
                            <!-- 姓名 -->
                            <div class="col-md-8  input-lg">
                                <div class="form-group form-group-lg">
                                    <label class="col-sm-4 control-label">姓名</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control  input-lg" id="realName" placeholder="姓名" name="realName" />
                                    </div>

                                </div>
                            </div>
                        </div>

                        <div class="row" style="padding-top: 10px;">
                            <!-- 联系电话 -->
                            <div class="col-md-8  input-lg">
                                <div class="form-group form-group-lg">
                                    <label class="col-sm-4 control-label">联系电话</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control  input-lg" id="mobile" placeholder="联系电话" name="mobile" />
                                    </div>

                                </div>
                            </div>
                        </div>
 						<div class="row" style="padding-top: 10px;">
                            <!-- 邮箱 -->
                            <div class="col-md-8  input-lg">
                                <div class="form-group form-group-lg">
                                    <label class="col-sm-4 control-label">邮箱</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control  input-lg" id="email" placeholder="邮箱" name="email" />
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="row" style="padding-top: 10px;">
                            <!-- 传真 -->
                            <div class="col-md-8  input-lg">
                                <div class="form-group form-group-lg">
                                    <label class="col-sm-4 control-label">传真</label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control  input-lg" id="fax" placeholder="传真" name="fax" />
                                        
                                    </div>

                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-right" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="editUserSubmit();" >保存</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>