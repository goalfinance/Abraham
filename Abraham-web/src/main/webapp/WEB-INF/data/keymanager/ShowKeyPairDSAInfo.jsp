<%--
  Created by IntelliJ IDEA.
  User: panqingrong
  Date: 28/10/2016
  Time: 12:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
    <h4 class="modal-title">DSA密钥对信息</h4>
</div>
<div class="modal-body">
    <div class="form-group">
        <div class="row">
            <label class="col-md-2 control-label">密钥对名称</label>
            <div class="col-md-10">
                <input type="text" disabled="true" class="form-control" name="name"
                       value="${dsaKeyInfo.name}"/>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="row">
            <label class="col-md-2 control-label">Sub prime</label>
            <div class="col-md-5">
                <textarea disabled="true" row="10" class="form-control" name="subprime">${dsaKeyInfo.q}</textarea>
            </div>
            <label class="col-md-2 control-label">密钥对长度</label>
            <div class="col-md-3">
                <input type="text" disabled="true" class="form-control" name="size"
                       value="${dsaKeyInfo.size}"/>
            </div>

        </div>
    </div>

    <div class="form-group">
        <div class="row">
            <label class="col-md-2 control-label">Private key</label>
            <div class="col-md-10">
                <input type="text" disabled="true" class="form-control" name="privateKey"
                       value="${dsaKeyInfo.x}"/>
            </div>
        </div>
    </div>

    <div class="form-group">
        <div class="row">
            <label class="col-md-2 control-label">Public key</label>
            <div class="col-md-10">
                <textarea class="form-control" rows="10" disabled="true">${dsaKeyInfo.y}</textarea>
            </div>
        </div>
    </div>

</div>
<div class="modal-footer">
    <button type="button" class="btn green" data-dismiss="modal">关闭</button>
</div>
