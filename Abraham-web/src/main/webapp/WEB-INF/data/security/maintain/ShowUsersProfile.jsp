<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
			<div class="box-content">
				<div id="warningDiv" class="alert alert-danger hidden">
					<strong>警告！</strong>
				</div>
				<form id="modifyUsersProfileForm" data-ajax="true" method="post"
					class="form-horizontal">
					<fieldset>
						<legend>用户信息</legend>
						<div class="form-group">
							<label class="col-sm-3 control-label">用户ID</label>
							<div class="col-sm-3">
								<input type="text" class="form-control" name="userId"
									value="${securityUser.userId}" />
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label">用户全名</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="fullName"
									value="${securityUser.fullName}" />
							</div>
						</div>

						<div class="form-group">
							<label class="col-sm-3 control-label">昵称</label>
							<div class="col-sm-4">
								<input type="text" class="form-control" name="nickName"
									value="${securityUser.nickName}" />
							</div>
						</div>
					</fieldset>

					<fieldset>
						<legend></legend>
						<div class="form-group">
							<div class="col-sm-9"></div>
							<div class="col-sm-3">
								<button type="button" class="btn btn-primary" data-toggle="modal"
									data-target="#appModal" href="/data/security/maintain/user/change_password_modal?sId=${securityUser.getSId()}">修改登录密码</button>
								<button type="submit" class="btn btn-primary"
									onClick="modifyUsersProfile(${securityUser.getSId()});">提交修改</button>
							</div>
						</div>
					</fieldset>
				</form>
			</div>
		</div>

	</div>
</div>
