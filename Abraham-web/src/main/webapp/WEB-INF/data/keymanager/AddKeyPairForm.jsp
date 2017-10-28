<%--
  Created by IntelliJ IDEA.
  User: panqingrong
  Date: 28/10/2017
  Time: 1:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    <h4 class="modal-title">生成密钥对</h4>
</div>
<div class="modal-body">
    <form id="addKeyPairForm" data-ajax="true" method="post" class="form-horizontal">
        <div class="alert alert-danger display-hide">

        </div>
        <fieldset>
            <div class="form-group">
                <label class="col-sm-3 control-label">内部名称</label>
                <div class="col-sm-4">
                    <input type="text" class="form-control" name="name" autofocus="autofocus"
                           value="${keypPair.name}"/>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">密钥类型</label>
                <div class="col-sm-4">
                    <select class="form-control" name="type" value="${keyPair.type}">
                        <option value="RSA">RSA</option>
                        <option value="DSA">DSA</option>
                        <option value="EC">EC</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label">密钥长度</label>
                <div class="col-sm-4">
                    <select class="form-control" name="size" value="${keyPair.size}">
                        <option value="1024">1024</option>
                        <option value="2048">2048</option>
                        <option value="4096">4096</option>
                    </select>
                </div>
            </div>
        </fieldset>
    </form>
</div>
<div class="modal-footer">
    <button type="button" data-dismiss="modal" class="btn btn-outline dark">关闭</button>
    <button type="button" class="btn green" onclick="addKeyPair();">生成</button>
</div>

<script>
    $(document).ready(function() {
        $(".modal").on('shown.bs.modal', function() {
            $(this).find("[autofocus]:first").focus();
        });
    });
</script>