<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="box">
			<div class="box-header">
				<div class="box-name">
					<i class="fa fa-search"></i>
					<span>查询</span>
				</div>
				<div class="box-icons">
					<a class="collapse-link">
						<i class="fa fa-chevron-up"></i>
					</a>
					<a class="expand-link">
						<i class="fa fa-expand"></i>
					</a>
					<a class="close-link">
						<i class="fa fa-times"></i>
					</a>
				</div>
				<div class="no-move"></div>
			</div>
			<div class="box-content">
				<form id="modifyUsersProfileForm" data-ajax="true" method="post" class="form-horizontal" role="form">
					<div class="form-group">
						<label class="col-sm-2 control-label">用户ID</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="userId"
								value="${securityUser.userId}" />
						</div>
						<label class="col-sm-2 control-label">全名</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="fullName"
								value="${securityUser.fullName}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">昵称</label>
						<div class="col-sm-3">
							<input type="text" class="form-control" name="nickName"
								value="${securityUser.nickName}" />
						</div>
					</div>
					<div class="form-group">
						<label class="col-sm-2 control-label">创建时间</label>
						<div class="col-sm-3">
							<input type="text" id="input_date" class="form-control">
							<span class="fa fa-calendar form-control-feedback"></span>
						</div>
						<label class="col-sm-2 control-label">创建时间</label>
						<div class="col-sm-3">
							<input type="text" id="input_time" class="form-control">
							<span class="fa fa-clock-o form-control-feedback"></span>
						</div>
					</div>
				
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
	
<div class="row">
	<div class="col-xs-12 col-sm-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">维护登录用户信息</div>
			</div>
			<div class="panel-body">
				<table id="securityUserTable"
					class="table table-bordered table-striped table-hover">
					<thead>
						<tr>
							<th>SID</th>
							<th>用户ID</th>
							<th>全名</th>
							<th>昵称</th>
							<th>创建时间</th>
							<th>更新时间</th>
							<th>修改密码时间</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function InitSecurityUserTable() {
		$("#securityUserTable")
				.dataTable(
						{
							processing : true,
							serverSide : true,
							pagination : true,
							searching : false,
							pageLength : 5,
							language : {
								lengthMenu : '<select class="form-control input-xsmall">'
										+ '<option value="5">5</option>'
										+ '<option value="10">10</option>'
										+ '<option value="20">20</option>'
										+ '<option value="30">30</option>'
										+ '<option value="40">40</option>'
										+ '<option value="50">50</option>'
										+ '</select>条记录',//左上角的分页大小显示。
								processing : "载入中",//处理页面数据的时候的显示
								paginate : {//分页的样式文本内容。
									previous : "上一页",
									next : "下一页",
									first : "第一页",
									last : "最后一页"
								},
								zeroRecords : "没有内容",//table tbody内容为空时，tbody的内容。
								//下面三者构成了总体的左下角的内容。
								info : "总共_PAGES_ 页，显示第_START_ 到第 _END_ ，筛选之后得到 _TOTAL_ 条，初始_MAX_ 条 ",//左下角的信息显示，大写的词为关键字。
								infoEmpty : "0条记录",//筛选为空时左下角的显示。
								infoFiltered : ""//筛选之后的左下角筛选提示(另一个是分页信息显示，在上面的info中已经设置，所以可以不显示)，
							},
							ajax : {
								type : "POST",
								data : function(data) {
									return JSON.stringify(data);
								},
								dataType : "json",
								processData : false,
								contentType : 'application/json;charset=UTF-8',
								url : "/restapis/security/maintaining/user/list"
							},
							columns : [ {
								"data" : "sId"
							}, {
								"data" : "userId"
							}, {
								"data" : "fullName"
							}, {
								"data" : "nickName"
							}, {
								"data" : "createTime"
							}, {
								"data" : "updateTime"
							}, {
								"data" : "pwdChangeTime"
							} ],
							dom : 'B<"clear">lrtip',
							buttons : [ 'copy', 'excel', 'pdf' ]
						});
	}
	$(document).ready(function() {
		LoadDataTablesScripts(InitSecurityUserTable);
		$('#input_date').datepicker({setDate: new Date()});
	});
</script>