<%--
  Created by IntelliJ IDEA.
  User: panqingrong
  Date: 28/10/2016
  Time: 2:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal-header">
    <button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
    <h4 class="modal-title">确认删除</h4>
</div>
<div class="modal-body" id="keypairDeleteConfirm">
    <div class="alert alert-danger display-hide">

    </div>
    请确认是否需要删除密钥对"${keypairName}"?
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-outline dark" data-dismiss="modal">取消</button>
    <button type="button" class="btn green" onclick="deleteKeyPair(${sId});">确定</button>
</div>
