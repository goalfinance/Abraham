<%--
  Created by IntelliJ IDEA.
  User: panqingrong
  Date: 01/11/2016
  Time: 10:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    <h4 class="modal-title">密钥导出</h4>
</div>
<div class="modal-body">
    <form id="exportKeyForm" data-ajax="true" method="post" class="form-horizontal">
        <div class="alert alert-danger display-hide">

        </div>

        <div class="form-group">
            <div class="row">
                <label class="col-md-2 control-label">密钥对名称</label>
                <div class="col-md-10">
                    <input type="text" readonly="true" class="form-control" name="keypairName"
                           value="${keypairName}"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <label class="col-md-2 control-label">导出文件名</label>
                <div class="col-md-10">
                    <input type="text" class="form-control" name="keypairFileName" autofocus="autofocus"
                           value="${keypairFileName}"/>
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="row">
                <label class="col-md-2 control-label">导出类型</label>
                <div class="col-md-10">
                    <select class="form-control" name="keypairExportFormat" value="${keypairExportFormat}">
                        <option value="pemPrivate">PEM Private(*.pem)</option>
                        <option value="pemPublic">PEM Public(*.pem)</option>
                    </select>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn btn-outline dark">关闭</button>
    <button type="button" class="btn green" onclick="exportKey(${sId});">确定</button>
</div>
