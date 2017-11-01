<%--
  Created by IntelliJ IDEA.
  User: panqingrong
  Date: 04/01/2017
  Time: 9:32 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    <h4 class="modal-title">新增用户</h4>
</div>
<div class="modal-body">
    <form id="form_add_security_user" data-ajax="true" method="post" class="form-horizontal">
        <div class="alert alert-danger display-hide">

        </div>
        <fieldset>
            <div class="form-group">
                <label class="col-md-3 control-label">用户ID
                    <span class="required"> * </span>
                </label>
                <div class="col-md-4">
                    <div class="input-icon right">
                        <i class="fa"></i>
                        <input type="text" class="form-control" name="userId" autofocus value="${securityUser.userId}"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">全名
                </label>
                <div class="col-md-4">
                    <input type="text" class="form-control" name="fullName" value="${securityUser.fullName}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">昵称
                    <span class="required"> * </span>
                </label>
                <div class="col-md-4">
                    <input type="text" class="form-control" name="nickName" value="${securityUser.nickName}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">登录密码
                    <span class="required"> * </span>
                </label>
                <div class="col-md-4">
                    <input type="password" class="form-control" autocomplete="off" name="passwd"
                           value="${securityUser.passwd}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">密码确认
                    <span class="required"> * </span>
                </label>
                <div class="col-md-4">
                    <input type="password" class="form-control" autocomplete="off" name="passwdConfirmed"
                           value="${securityUser.passwdConfirmed}"/>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn btn-outline dark">关闭</button>
    <button type="button" class="btn green" onclick="addSecurityUser();">确认</button>
</div>

<%
    //设置缓存为空
    response.setHeader("Pragma", "No-cache");
    response.setHeader("Cache-Control", "no-cache");
    response.setDateHeader("Expires", 0);
%>

<script type="text/javascript" src="/assets/pages/scripts/security/maintain/user/add-user-modal.js"></script>
