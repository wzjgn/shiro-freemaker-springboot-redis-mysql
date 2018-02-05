<div>
    <form id="password-form" class="form-horizontal" action="">
        <div class="modal  fade " id="Modal-Password" style="position: absolute">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"></button>
                        <h4 class="modal-title">修改密码</h4>
                    </div>
                    <div class="modal-body">

                        <div class="row" style="padding-top: 10px;">
                            <!-- 登录用户名 -->
                            <div class="col-md-8  input-lg">
                                <div class="form-group form-group-lg">
                                    <label class="col-sm-4 control-label">登录用户名  </label>
                                    <div class="col-sm-8">
                                        <input type="text" class="form-control  input-lg" id="username1" placeholder="登录用户名"  disabled name="username1" value="<@shiro.principal/>" />
                                        <input type="text"  style="display:none" id="password_form_username"   name="password_form_username"  value="<@shiro.principal/>"/>
                                       
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="row" style="padding-top: 10px;">
                            <!-- 单位名称 -->
                            <div class="col-md-8  input-lg">
                                <div class="form-group form-group-lg">
                                    <label class="col-sm-4 control-label">原密码</label>
                                    <div class="col-sm-8">
                                        <input type="password" class="form-control  input-lg" id="password_form_oldpassword" placeholder="原密码" name="password_form_oldpassword" value="" />
                                    </div>

                                </div>
                            </div>
                        </div>





 						<div class="row" style="padding-top: 10px;">
                            <!-- 邮箱 -->
                            <div class="col-md-8  input-lg">
                                <div class="form-group form-group-lg">
                                    <label class="col-sm-4 control-label">新密码</label>
                                    <div class="col-sm-8">
                                        <input type="password" class="form-control  input-lg" id="password_form_password" placeholder="新密码" name="password_form_password" />
                                    </div>

                                </div>
                            </div>
                        </div>
                        <div class="row" style="padding-top: 10px;">
                            <!-- 传真 -->
                            <div class="col-md-8  input-lg">
                                <div class="form-group form-group-lg">
                                    <label class="col-sm-4 control-label">再次输入密码</label>
                                    <div class="col-sm-8">
                                        <input type="password" class="form-control  input-lg" id="password_form_rpassword" placeholder="再次输入密码" name="password_form_rpassword" />
                                        
                                    </div>

                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default pull-right" data-dismiss="modal">关闭</button>
                        <button type="button" class="btn btn-primary" onclick="appcommon.passwordSubmit();" >保存</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>