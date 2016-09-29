<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"
		aria-hidden="true">&times;</button>
	<h4 class="modal-title" id="appModalLabel">修改登录密码</h4>
</div>
<div class="modal-body">
	<div id="warningDiv" class="alert alert-danger hidden">
		<strong>警告！</strong>
	</div>
	<form id="changePasswordForm" data-ajax="true" method="post"
		class="form-horizontal">
		<div class="form-group">
			<label class="col-sm-3 control-label">旧密码</label>
			<div class="col-sm-5">
				<input type="password" name="oldPassword" class="form-control" />
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label">新密码</label>
			<div class="col-sm-5">
				<input type="password" name="newPassword" class="form-control" />
			</div>
		</div>

		<div class="form-group">
			<label class="col-sm-3 control-label">确认新密码</label>
			<div class="col-sm-5">
				<input type="password" name="confirmNewPassword"
					class="form-control" />
			</div>
		</div>

	</form>
</div>
<div class="modal-footer">
	<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
	<button type="button" class="btn btn-primary" id="changePasswordConfirmButton"
		onClick="changePassword(${userSid});">确认</button>
</div>