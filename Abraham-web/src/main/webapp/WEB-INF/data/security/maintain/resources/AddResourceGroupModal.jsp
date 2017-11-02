<%--
  Created by IntelliJ IDEA.
  User: panqingrong
  Date: 01/11/2017
  Time: 12:15 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    <h4 class="modal-title">新增资源组</h4>
</div>
<div class="modal-body">
    <form id="form_add_resource_group" data-ajax="true" method="post" class="form-horizontal">
        <div class="alert alert-danger display-hide">

        </div>
        <fieldset>
            <div class="form-group">
                <label class="col-md-3 control-label">名称
                    <span class="required"> * </span>
                </label>
                <div class="col-md-6">
                    <div class="input-icon right">
                        <i class="fa"></i>
                        <input type="text" class="form-control" name="name" autofocus value="${securityResourceGroup.name}"/>
                    </div>
                </div>
            </div>
            <div class="form-group">
                <label class="col-md-3 control-label">描述</label>
                <div class="col-md-6">
                    <input type="text" class="form-control" name="description" value="${securityResourceGroup.description}"/>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn btn-outline dark">关闭</button>
    <button type="button" class="btn green" onclick="addResourceGroup();">确定</button>
</div>
<script type="text/javascript" src="/assets/pages/scripts/security/maintain/resource/add-resource-group.js"></script>
